package com.advanceweb.afc.jb.employer.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AdmFacilityContactDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.data.entities.AdmRole;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserFacilityPK;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.AdmUserRolePK;
import com.advanceweb.afc.jb.data.entities.MerLocation;
import com.advanceweb.afc.jb.data.entities.MerProfileAttrib;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.MerUserProfile;
import com.advanceweb.afc.jb.employer.helper.EmployerRegistrationConversionHelper;
import com.advanceweb.afc.jb.user.helper.RegistrationConversionHelper;
import com.mysql.jdbc.StringUtils;

import com.advanceweb.afc.jb.common.util.OpenAMEUtility;
/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:55 PM
 */
@SuppressWarnings("unchecked")
@Repository("employerRegistrationDAO")
public class EmployerRegistrationDAOImpl implements EmployerRegistrationDAO {

	private static final Logger LOGGER = Logger
			.getLogger(EmployerRegistrationDAOImpl.class);

	private static final String FIND_EMPLOYER_ROLE_ID = "from AdmRole role where role.name=?";
	private static final String REGISTRATION_ATTRIBS = "from MerProfileAttrib prof";
	private static final String VERIFY_EMAIL = "from MerUser e where e.email = ? and e.deleteDt is NULL";
	private static final String FIND_EMPLOYER_PROFILE = "from MerUserProfile prof where prof.id.userId=?";

	private HibernateTemplate hibernateTemplateTracker;

	private HibernateTemplate hibernateTemplateCareers;

	@Autowired
	private EmployerRegistrationConversionHelper empHelper;

	@Autowired
	private RegistrationConversionHelper registrationConversionHelper;

	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method is used to create a User(Employer) in job board.
	 * 
	 * @param Object of EmployerProfileDTO
	 * @return Object of UserDTO
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserDTO createUser(EmployerProfileDTO empDTO) {
		try {
			MerUser merUser = empHelper.transformMerUserDTOToMerUser(empDTO,
					null);
			if (merUser != null) {
				// saving employer credentials
				hibernateTemplateTracker.saveOrUpdate(merUser);
			}
			// saving the employer profile
			List<MerUserProfile> merUserProfiles = empHelper
					.transformMerUserDTOToMerUserProfiles(empDTO, merUser);
			if (merUserProfiles != null) {
				hibernateTemplateTracker.saveOrUpdateAll(merUserProfiles);
			}
			// Getting role

			List<AdmRole> roleList = hibernateTemplateCareers.find(
					FIND_EMPLOYER_ROLE_ID, "facility_admin");
			int roleId = 0;
			if (null != roleList && !roleList.isEmpty()) {
				AdmRole role = roleList.get(0);
				AdmUserRole userRole = new AdmUserRole();
				userRole.setCreateUserId(MMJBCommonConstants.ZERO_INT);
				userRole.setCreateDt(new Date());

				AdmUserRolePK admUserRolePK = new AdmUserRolePK();
				admUserRolePK.setUserId(merUser.getUserId());
				admUserRolePK.setRoleId(role.getRoleId());
				userRole.setRolePK(admUserRolePK);
				hibernateTemplateCareers.saveOrUpdate(userRole);
				roleId = role.getRoleId();
			}
			// saving the data in adm_facility
			AdmFacility facility = empHelper
					.transformEmpDTOToAdmFAcility(empDTO);
			if(empDTO.getMerUserDTO().isHelthSystem()){
				setFacility(facility,MMJBCommonConstants.FACILITY_GROUP,empDTO,MMJBCommonConstants.ZERO_INT);
				int parentFacilityId=(Integer) hibernateTemplateCareers.save(facility);
				// saving the data in adm_facility_contact
				AdmFacilityContact contact = empHelper
						.transformEmpDTOToAdmFacilityContact(empDTO, facility);
				/**
				 *  creating employer Users in OpenAM 
				 */
				boolean isCreated=OpenAMEUtility.openAMCreateEmp(merUser,contact);
				LOGGER.info("Open AM :Employee User is created!"+isCreated);
				// Ends OpenAM code

				hibernateTemplateCareers.saveOrUpdate(contact);

				// saving the data in the adm_user_facility
				setUserFacility(facility,merUser.getUserId(),roleId);
				
				// saving the data for child facility related to the facility group
				AdmFacility childFacility = empHelper
						.transformEmpDTOToAdmFAcility(empDTO);
				setFacility(childFacility,MMJBCommonConstants.FACILITY,empDTO,parentFacilityId);
				childFacility.setCreateUserId(merUser.getUserId());
				hibernateTemplateCareers.save(childFacility);
				AdmFacilityContact childFacilityContact = empHelper
						.transformEmpDTOToAdmFacilityContact(empDTO, childFacility);
				hibernateTemplateCareers.save(childFacilityContact);
				
			}
			else{
				setFacility(facility,MMJBCommonConstants.FACILITY,empDTO,MMJBCommonConstants.ZERO_INT);
			hibernateTemplateCareers.save(facility);
			
			// saving the data in adm_facility_contact
			AdmFacilityContact contact = empHelper
					.transformEmpDTOToAdmFacilityContact(empDTO, facility);
			/**
			 *  creating employer Users in OpenAM 
			 */
			boolean isCreated=OpenAMEUtility.openAMCreateEmp(merUser,contact);
			LOGGER.info("Open AM :Employee User is created!"+isCreated);
			// Ends OpenAM code
			hibernateTemplateCareers.saveOrUpdate(contact);

			// saving the data in the adm_user_facility
			setUserFacility(facility,merUser.getUserId(),roleId);
			}
			return empHelper.transformMerUserToUserDTO(merUser);

		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return null;
	}
	// This method will be called to set the details of facility
	private AdmFacility setFacility(AdmFacility facility,String role,EmployerProfileDTO empDTO,int parentFacilityId){
		
		facility.setFacilityType(role);
		facility.setEmail(empDTO.getMerUserDTO().getEmailId());
		facility.setFacilityParentId(parentFacilityId);
		facility.setNsCustomerID(empDTO.getMerUserDTO().getNsCustomerID());
		facility.setCreateDt(new Date());
		return facility;
	}
	//This method will be called to save the data in adm_user_facility 
	private void setUserFacility(AdmFacility facility, int userId, int roleId) {
		AdmUserFacility userfacility = new AdmUserFacility();
		AdmUserFacilityPK facilityPK = new AdmUserFacilityPK();
		facilityPK.setFacilityId(facility.getFacilityId());
		facilityPK.setUserId(userId);
		facilityPK.setRoleId(roleId);
		userfacility.setFacilityPK(facilityPK);
		userfacility.setCreateUserId(0);
		userfacility.setCreateDt(new Date());
		hibernateTemplateCareers.saveOrUpdate(userfacility);
	}
	/**
	 * 
	 * @param employerId
	 */
	public boolean deleteEmployer(long employerId) {
		return false;
	}

	/**
	 * 
	 * @param employerId
	 */
	public EmployerProfileDTO getEmployerDetails(int employerId) {
		EmployerProfileDTO emRegistrationDTO = new EmployerProfileDTO();
		try {
			if (employerId != 0) {
				MerUser merUser = hibernateTemplateTracker.load(MerUser.class,
						employerId);
				EmployerProfileDTO jsDTO = getProfileAttributes();

				List<MerUserProfile> profiles = hibernateTemplateTracker.find(
						FIND_EMPLOYER_PROFILE, employerId);

				emRegistrationDTO = empHelper.transformMerUserProfilesToDTO(
						merUser, jsDTO, profiles);
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}

		return emRegistrationDTO;
	}

	/**
	 * 
	 * @param employer
	 */
	public boolean updateEmployerDetails(EmployerProfileDTO empDTO) {
		return false;
	}

	/**
	 * To Change employer password
	 * 
	 * @param empDTO
	 * @return boolean
	 */
	@Override
	// TODO: Parameter 'empDTO' is not assigned and could be declared final
	public boolean changePassword(EmployerProfileDTO empDTO) {
		try {
			MerUser merUser = empHelper.transformMerUserDTOToMerUser(empDTO,
					null);
						// OpenAM User Update password
						// Added by Santhosh Gampa on 4th Sep 2012
						
						boolean isUdated =OpenAMEUtility.openAMUpdatePassword(merUser.getEmail(),merUser.getPassword());
						LOGGER.info("User password updated - "+isUdated);
						//OpenAM code ends 

			hibernateTemplateTracker.saveOrUpdate(merUser);
			return true;
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return false;
	}

	private List<DropDownDTO> getCountryList() {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(MerLocation.class);
			criteria.setProjection(Projections.distinct(Projections
					.property("country")));

			List<Object> merUtilityList = hibernateTemplateTracker
					.findByCriteria(criteria);
			return registrationConversionHelper
					.transformMerUtilityToDropDownDTO(merUtilityList);

		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	private List<DropDownDTO> getStateList() {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(MerLocation.class);
			criteria.setProjection(Projections.distinct(Projections
					.property("state")));
			criteria.addOrder(Order.asc("state"));

			List<Object> merUtilityList = hibernateTemplateTracker
					.findByCriteria(criteria);
			return registrationConversionHelper
					.transformMerUtilityToDropDownDTO(merUtilityList);
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.advanceweb.afc.jb.employer.dao.EmployerRegistrationDAO#
	 * getProfileAttributes()
	 */
	@Override
	public EmployerProfileDTO getProfileAttributes() {
		EmployerProfileDTO dto = null;
		try {
		
			List<MerProfileAttrib> listProfAttrib = hibernateTemplateTracker
					.find(REGISTRATION_ATTRIBS);
			List<DropDownDTO> countryList = getCountryList();
			List<DropDownDTO> stateList = getStateList();
			dto = empHelper.transformProfileAttrib(listProfAttrib, countryList,
					stateList);

		} catch (HibernateException e) {
			LOGGER.error(e);
		}

		return dto;
	}

	@Override
	public boolean validateEmail(String email) {
		try {
			/**
			 * OpenAM code starts here for Validate Email-Id
	         * 
		     * @auther Santhosh Gampa
		     * @since Sep 4 2012
	         *
			 */
			boolean isinvaliduser= OpenAMEUtility.openAMValidateEmail(email);
			if(isinvaliduser){
	    		LOGGER.info("OpenAM : user is already exist !");
	    		//model.setViewName("jobSeekerCreateAccount");
				//result.rejectValue("emailId", "NotEmpty", "Email address already exists");
				return true;
	    	}else{
	    		LOGGER.info("OpenAM : valid user!");
	    		
	    	}
	    	//End of OpenAM code
	    	
			
			if (!StringUtils.isEmptyOrWhitespaceOnly(email)) {
			
				List<MerUser> usersList = hibernateTemplateTracker.find(
						VERIFY_EMAIL, email);
				if (null != usersList && !usersList.isEmpty()) {
					MerUser user = usersList.get(0);
					return (null != user ? true : false);
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return false;
	}


	@Override
	public List<AdmFacilityContact> getEmployeeData(int userId,
			String contactType) {
		List<AdmFacilityContact> accountProfileDTO = new ArrayList<AdmFacilityContact>();
		try {
			if (userId > 0) {

				AdmFacility admFacility = new AdmFacility();
				admFacility.setFacilityId(userId);
				accountProfileDTO = hibernateTemplateCareers
						.find("from AdmFacilityContact af where af.admFacility = ? and af.contactType=?",
								admFacility, contactType);

			}

		} catch (DataAccessException e) {
			LOGGER.info("Error for update of employee data");
		}
		return accountProfileDTO;
	}

	/**
	 * This method is called to Account Setting update page and
	 * editAccountSetting method take Bean class binding result from Jsp pages
	 * Seession for UserId and FacilityId.
	 * 
	 * @author kartikm
	 * @param apd
	 * @param admfacilityid
	 * @return true
	 */	
	public boolean editUser(AccountProfileDTO apd, int admfacilityid,

			int userId, String billing) {
		boolean isUpdate = false;
		
		try{
			if ("PRIMARY".equals(billing)){
			// update meruser Entity		
						MerUser mer = hibernateTemplateTracker.get(MerUser.class, userId);
						mer.setFirstName(apd.getFirstName());
						mer.setLastName(apd.getLastName());
						mer.setEmail(apd.getEmail());
						hibernateTemplateTracker.update(mer);
			//update admfacilitycontact
		AdmFacilityContact facility = hibernateTemplateCareers.get(
				AdmFacilityContact.class, admfacilityid);
		// OpenAM code for Modify the user profile details
		// Added by Santhosh Gampa on Sep 6 2012
		boolean isUdated = OpenAMEUtility.openAMUpdateEmp(apd);
		LOGGER.info("User Profile updated in OpenAM - "+isUdated);
		//OpenAm code ends

		facility.setFirstName(apd.getFirstName());
		facility.setLastName(apd.getLastName());
		facility.setCompany(apd.getCompanyName());
		facility.setStreet(apd.getStreet());
		facility.setCity(apd.getCity());
		facility.setState(apd.getState());
		facility.setPostcode(apd.getZipCode());
		facility.setCountry(apd.getCountry());
		facility.setEmail(apd.getEmail());
		facility.setPhone(apd.getPhone());
		hibernateTemplateCareers.update(facility);
		isUpdate = true;
		
		}else{
			AdmFacilityContact facility = hibernateTemplateCareers.get(
					AdmFacilityContact.class, admfacilityid);
			facility.setFirstName(apd.getFirstName());
			facility.setLastName(apd.getLastName());
			facility.setCompany(apd.getCompanyName());
			facility.setStreet(apd.getStreet());
			facility.setCity(apd.getCity());
			facility.setState(apd.getState());
			facility.setPostcode(apd.getZipCode());
			facility.setCountry(apd.getCountry());
			facility.setEmail(apd.getEmail());
			facility.setPhone(apd.getPhone());
			hibernateTemplateCareers.update(facility);
			isUpdate = true;
		}
		}catch(Exception e)
		{
			LOGGER.info("Error im Meruser duplicate Data insert");
		}
		
		return isUpdate;

	}
	/**
	 * This method is called to Account Setting update page and
	 * editAccountSetting method take Bean class binding result from Jsp pages
	 * Seession for UserId and FacilityId.
	 * 
	 * @author kartikm
	 * @param userId
	 * @param contactType
	 * @return AdmFacilityContactDTO
	 */

	
	@Override
	public AdmFacilityContactDTO getEmployeePrimaryKey(int userId,
			String contactType) {

		AdmFacilityContactDTO accountProfileDTO = new AdmFacilityContactDTO();
		
		try {
			List<AdmFacilityContact> adm=new ArrayList<AdmFacilityContact>();
			if (userId > 0) {
				adm = hibernateTemplateCareers
						.find("select a from AdmFacilityContact a,AdmFacility b,AdmUserFacility c where a.admFacility.facilityId= b.facilityId and a.admFacility.facilityId=c.id.facilityId "
								+ "and c.id.userId="
								+ userId
								+ "and a.contactType= '" + contactType + "'");

			}
			accountProfileDTO=empHelper.transformListToDTOList(adm);
		} catch (DataAccessException e) {
			LOGGER.info("Error for update of employee data");
		}
		return accountProfileDTO;
	}

	
	/**
	 * This method is called to check, whether registration is completed 
	 * properly or not (To migrate old users to new application)
	 */
	@Override
	public boolean validateProfileAttributes(int employerId){
		try {
			List<MerUserProfile> profAttribList = hibernateTemplateTracker.find(" from MerUserProfile prof where prof.profilePK.userId=?",employerId);
			if(null != profAttribList && !profAttribList.isEmpty()){
				return true;
			}
		} catch (DataAccessException e) {
			LOGGER.info("Error for update of employee data");
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	
	
	public FacilityDTO getNSCustomerIDFromAdmFacility(String email) {
		
		FacilityDTO admFacilityDTO = new FacilityDTO();
		try{
			
			List<AdmFacility> admFacilityList = hibernateTemplateCareers
					.find(" from  AdmFacility WHERE  email='" + email +"'");
			
			if (admFacilityList != null) {
				admFacilityDTO.setNsCustomerID(admFacilityList.get(0).getNsCustomerID());
			}
			
		}catch(DataAccessException e){
			LOGGER.error("Error while getting the NSCustomerID from AdmFacility."+e);
		}
		return admFacilityDTO;
		
	}
	

	

}