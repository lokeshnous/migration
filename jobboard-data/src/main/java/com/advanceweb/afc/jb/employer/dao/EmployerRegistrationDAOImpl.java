/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.dao;

import java.sql.Timestamp;
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
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AdmFacilityContactDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
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
import com.advanceweb.afc.jb.data.entities.WebMembership;
import com.advanceweb.afc.jb.data.entities.WebMembershipEmail;
import com.advanceweb.afc.jb.data.entities.WebMembershipInfo;
import com.advanceweb.afc.jb.employer.helper.EmployerRegistrationConversionHelper;
import com.advanceweb.afc.jb.user.helper.RegistrationConversionHelper;
import com.mysql.jdbc.StringUtils;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:55 PM
 */
@SuppressWarnings("unchecked")
@Transactional
@Repository("employerRegistrationDAO")
public class EmployerRegistrationDAOImpl implements EmployerRegistrationDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(EmployerRegistrationDAOImpl.class);

	/** The Constant FIND_EMPLOYER_ROLE_ID. */
	private static final String FIND_EMPLOYER_ROLE_ID = "from AdmRole role where role.name=?";
	
	/** The Constant REGISTRATION_ATTRIBS. */
	private static final String REGISTRATION_ATTRIBS = "from MerProfileAttrib prof";
	
	/** The Constant VERIFY_EMAIL. */
	private static final String VERIFY_EMAIL = "from MerUser e where e.email = ? and e.deleteDt is NULL";
	
	/** The Constant FIND_EMPLOYER_PROFILE. */
	private static final String FIND_EMPLOYER_PROFILE = "from MerUserProfile prof where prof.id.userId=?";
	
	/** The Constant VERIFY_EMAIL_ADVANCEPASS. */
	private static final String VERIFY_EMAIL_ADVANCEPASS = "from WebMembershipEmail e where e.email = ? and e.deleteDate is NULL";

	/** The hibernate template tracker. */
	private HibernateTemplate hibernateTemplateTracker;

	/** The hibernate template careers. */
	private HibernateTemplate hibernateTemplateCareers;
	
	/** The hibernate template advance pass. */
	private HibernateTemplate hibernateTemplateAdvancePass;

	/** The emp helper. */
	@Autowired
	private EmployerRegistrationConversionHelper empHelper;

	/** The registration conversion helper. */
	@Autowired
	private RegistrationConversionHelper registrationConversionHelper;

	/**
	 * Sets the hibernate template.
	 *
	 * @param sessionFactoryMerionTracker the session factory merion tracker
	 * @param sessionFactory the session factory
	 * @param sessionFactoryAdvancePass the session factory advance pass
	 */
	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory,
			SessionFactory sessionFactoryAdvancePass) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);
		this.hibernateTemplateAdvancePass = new HibernateTemplate(
				sessionFactoryAdvancePass);

	}

	/**
	 * This method is used to create a User(Employer) in job board.
	 * 
	 * @param Object
	 *            of EmployerProfileDTO
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
			AdmFacility facility=null;
			AdmFacility nsFacility=null;
			boolean nsExists=false;
			boolean primaryAddrExists=false;
			empDTO.getMerUserDTO().setFacilityCreateDate(new Date());
			
			List<AdmFacility> listAdmFacility = hibernateTemplateCareers.find("from AdmFacility where nsCustomerID=?",empDTO.getMerUserDTO().getNsCustomerID());
			if(null != listAdmFacility && !listAdmFacility.isEmpty()){
				nsFacility = listAdmFacility.get(0);
				empDTO.getMerUserDTO().setHelthSystem(nsFacility.getFacilityType().equalsIgnoreCase(MMJBCommonConstants.FACILITY_GROUP));
				empDTO.getMerUserDTO().setEmailId(nsFacility.getEmail());
				empDTO.getMerUserDTO().setFacilityParentId(nsFacility.getFacilityParentId());
				empDTO.getMerUserDTO().setFacilityCreateDate(nsFacility.getCreateDt());
				nsExists = true;
				
			}
			
			if (empDTO.getMerUserDTO().getFacilityId() > 0) {
				facility = hibernateTemplateCareers.get(AdmFacility.class, empDTO
						.getMerUserDTO().getFacilityId());
				LOGGER.info("NetSuite record already exists: "+empDTO.getMerUserDTO().getNsCustomerID()+" . Hence new facility not created.");
			}
			else if(nsExists){
				facility = nsFacility;
				LOGGER.info("NetSuite record already exists: "+empDTO.getMerUserDTO().getNsCustomerID()+" . Hence new facility not created.");
			}
			else{
			facility = empHelper
					.transformEmpDTOToAdmFAcility(empDTO);
			LOGGER.info("NetSuite record does not exist: "+empDTO.getMerUserDTO().getNsCustomerID()+" . Hence new facility will be created.");
			}
			if (empDTO.getMerUserDTO().isHelthSystem()) {
				int parentFacilityId = 0;
				setFacility(facility, MMJBCommonConstants.FACILITY_GROUP,
						empDTO, empDTO.getMerUserDTO().getFacilityParentId());
				if (!nsExists) {
					parentFacilityId = (Integer) hibernateTemplateCareers
							.save(facility);
				}
				if (null != facility.getAdmFacilityContacts()
						&& !facility.getAdmFacilityContacts().isEmpty()) {
					for (AdmFacilityContact admFacilityContact : facility
							.getAdmFacilityContacts()) {
						primaryAddrExists = admFacilityContact.getContactType()
								.equals(MMJBCommonConstants.PRIMARY) ? true
								: false;
						if (primaryAddrExists) {
							break;
						}
					}
				}
				if (!primaryAddrExists) {
					// saving the data in adm_facility_contact
					AdmFacilityContact contact = empHelper
							.transformEmpDTOToAdmFacilityContact(empDTO,
									facility);
					/**
					 * creating employer Users in OpenAM
					 */
					// boolean
					// isCreated=OpenAMEUtility.openAMCreateEmp(merUser,contact);
					// LOGGER.info("Open AM :Employee User is created!"+isCreated);
					// Ends OpenAM code

					hibernateTemplateCareers.saveOrUpdate(contact);
				}
				// saving the data in the adm_user_facility
				setUserFacility(facility, merUser.getUserId(), roleId);

				// saving the data for child facility related to the facility
				// group
				if (!nsExists) {
					AdmFacility childFacility = empHelper
							.transformEmpDTOToAdmFAcility(empDTO);
					setFacility(childFacility, MMJBCommonConstants.FACILITY,
							empDTO, parentFacilityId);
					childFacility.setCreateUserId(merUser.getUserId());
					hibernateTemplateCareers.save(childFacility);
					AdmFacilityContact childFacilityContact = empHelper
							.transformEmpDTOToAdmFacilityContact(empDTO,
									childFacility);
					hibernateTemplateCareers.save(childFacilityContact);
				}

			} else {
				setFacility(facility, MMJBCommonConstants.FACILITY, empDTO,
						MMJBCommonConstants.ZERO_INT);
				hibernateTemplateCareers.saveOrUpdate(facility);

				if (null != facility.getAdmFacilityContacts()
						&& !facility.getAdmFacilityContacts().isEmpty()) {
					for (AdmFacilityContact admFacilityContact : facility
							.getAdmFacilityContacts()) {
						primaryAddrExists = admFacilityContact.getContactType()
								.equals(MMJBCommonConstants.PRIMARY) ? true
								: false;
						if (primaryAddrExists) {
							break;
						}
					}
				}
				if (!primaryAddrExists) {
					// saving the data in adm_facility_contact
					AdmFacilityContact contact = empHelper
							.transformEmpDTOToAdmFacilityContact(empDTO,
									facility);
					/**
					 * creating employer Users in OpenAM
					 */
					// boolean
					// isCreated=OpenAMEUtility.openAMCreateEmp(merUser,contact);
					// LOGGER.info("Open AM :Employee User is created!"+isCreated);
					// Ends OpenAM code
					hibernateTemplateCareers.saveOrUpdate(contact);
				}
				// saving the data in the adm_user_facility
				setUserFacility(facility, merUser.getUserId(), roleId);
			}
			if(!empDTO.getMerUserDTO().isOldUser() && !empDTO.getMerUserDTO().isAdvPassUser() && !empDTO.getMerUserDTO().isAdvPassUserWithNullPass()){
			saveAdvancePassDetails(facility.getFacilityId(),merUser);
			}else{
				AccountProfileDTO apDto=empHelper.transformToAccountProfileDTO(empDTO);
				editAdvancePassDetails(apDto,merUser.getEmail(),empDTO.getMerUserDTO().isAdvPassUserWithNullPass());
			}
			return empHelper.transformMerUserToUserDTO(merUser);

		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return null;
	}

	// This method will be called to set the details of facility
	/**
	 * Sets the facility.
	 *
	 * @param facility the facility
	 * @param role the role
	 * @param empDTO the emp dto
	 * @param parentFacilityId the parent facility id
	 * @return the adm facility
	 */
	private AdmFacility setFacility(AdmFacility facility, String role,
			EmployerProfileDTO empDTO, int parentFacilityId) {

		facility.setFacilityType(role);
		facility.setEmail(empDTO.getMerUserDTO().getEmailId());
		facility.setFacilityParentId(parentFacilityId);
		facility.setNsCustomerID(empDTO.getMerUserDTO().getNsCustomerID());
		facility.setCreateDt(empDTO.getMerUserDTO().getFacilityCreateDate());
		return facility;
	}

	// This method will be called to save the data in adm_user_facility
	/**
	 * Sets the user facility.
	 *
	 * @param facility the facility
	 * @param userId the user id
	 * @param roleId the role id
	 */
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
			
			WebMembershipEmail webMembershipEmail = (WebMembershipEmail)DataAccessUtils.uniqueResult(hibernateTemplateAdvancePass.find("from WebMembershipEmail where email = ?", merUser.getEmail()));
			WebMembership membership = hibernateTemplateAdvancePass.get(WebMembership.class, webMembershipEmail.getWebMembership().getWebMembershipID());
			
			if(null != merUser && null != membership){
				membership.setPassword(merUser.getPassword());
			}
			
			// OpenAM User Update password
			// Added by Santhosh Gampa on 4th Sep 2012

			// boolean isUdated
			// =OpenAMEUtility.openAMUpdatePassword(merUser.getEmail(),merUser.getPassword());
			// LOGGER.info("User password updated - "+isUdated);
			// OpenAM code ends

			hibernateTemplateTracker.saveOrUpdate(merUser);
			hibernateTemplateAdvancePass.saveOrUpdate(membership);
			return true;
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return false;
	}

	/**
	 * Gets the country list.
	 *
	 * @return the country list
	 */
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

	/**
	 * Gets the state list.
	 *
	 * @return the state list
	 */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.EmployerRegistrationDAO#validateEmail(java.lang.String)
	 */
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
			// boolean isinvaliduser= OpenAMEUtility.openAMValidateEmail(email);
			// if(isinvaliduser){
			// LOGGER.info("OpenAM : user is already exist !");
			// //model.setViewName("jobSeekerCreateAccount");
			// //result.rejectValue("emailId", "NotEmpty",
			// "Email address already exists");
			// return true;
			// }else{
			// LOGGER.info("OpenAM : valid user!");
			//
			// }
			// End of OpenAM code

			if (!StringUtils.isEmptyOrWhitespaceOnly(email)) {

				List<MerUser> usersList = hibernateTemplateTracker.find(
						VERIFY_EMAIL, email);
				List<WebMembershipEmail> webMembershipEmails = hibernateTemplateAdvancePass.find(
						VERIFY_EMAIL_ADVANCEPASS, email);
				
				if (null != usersList && !usersList.isEmpty()) {
					MerUser user = usersList.get(0);
					boolean isUser = false;
					if (user != null) {
					isUser = true;
					}
					return isUser;
				}
				// validating the email id in the advance pass server or not
				if (null != webMembershipEmails
						&& !webMembershipEmails.isEmpty()) {
					WebMembershipEmail webMembershipEmail = webMembershipEmails
							.get(0);
					boolean isUser = false;
					if (webMembershipEmail != null) {
						isUser = true;
					}
					return isUser;
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.EmployerRegistrationDAO#getEmployeeData(int, java.lang.String)
	 */
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
			LOGGER.error("Error for update of employee data",e);
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

		try {
			if ("PRIMARY".equals(billing)) {
				// update meruser Entity
				MerUser mer = hibernateTemplateTracker.get(MerUser.class,
						userId);
				editAdvancePassDetails(apd,mer.getEmail(),false);
				mer.setFirstName(apd.getFirstName());
				mer.setLastName(apd.getLastName());
				if(apd.isAdminLogin()){
				mer.setEmail(apd.getEmail());
				}
				hibernateTemplateTracker.update(mer);

				// update admfacilitycontact
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
				if(apd.isAdminLogin()){
				facility.setEmail(apd.getEmail());
				}
				facility.setPhone(apd.getPhone());
				hibernateTemplateCareers.update(facility);
				isUpdate = true;

				//Need to update Adm_facility also
				//Added for chilli issue #1290
				AdmFacility admFacility = facility.getAdmFacility();
				admFacility.setName(apd.getCompanyName());
				hibernateTemplateCareers.update(admFacility);

			} else {
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
			
		} catch (Exception e) {
			LOGGER.error("Error im Meruser duplicate Data insert",e);
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
			List<AdmFacilityContact> adm = new ArrayList<AdmFacilityContact>();
			if (userId > 0) {
				adm = hibernateTemplateCareers
						.find("select a from AdmFacilityContact a,AdmFacility b,AdmUserFacility c where a.admFacility.facilityId= b.facilityId and a.admFacility.facilityId=c.id.facilityId "
								+ "and c.id.userId="
								+ userId
								+ "and a.contactType= '" + contactType + "'");

			}
			accountProfileDTO = empHelper.transformListToDTOList(adm);
		} catch (DataAccessException e) {
			LOGGER.error("Error for update of employee data",e);
		}
		return accountProfileDTO;
	}

	/**
	 * This method is called to check, whether registration is completed
	 * properly or not (To migrate old users to new application)
	 */
	@Override
	public boolean validateProfileAttributes(int employerId) {
		try {
			List<MerUserProfile> profAttribList = hibernateTemplateTracker
					.find(" from MerUserProfile prof where prof.profilePK.userId=?",
							employerId);
			if (null != profAttribList && !profAttribList.isEmpty()) {
				return true;
			}
		} catch (DataAccessException e) {
			LOGGER.error("Error for update of employee data",e);
		}

		return false;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */

	public FacilityDTO getNSCustomerIDFromAdmFacility(int userId) {

		FacilityDTO admFacilityDTO = new FacilityDTO();
		try {

			List<AdmFacility> admFacilityList = hibernateTemplateCareers
					.find("select b from AdmFacility b,AdmUserFacility c where b.facilityId= c.id.facilityId and c.id.userId=?",userId);
			if (admFacilityList != null) {
				admFacilityDTO.setNsCustomerID(admFacilityList.get(0)
						.getNsCustomerID());
			}

		} catch (DataAccessException e) {
			LOGGER.error("Error while getting the NSCustomerID from AdmFacility."
					+ e);
		}
		return admFacilityDTO;

	}

	/**
	 * Method to insert required Data Into Advance Pass
	 * 
	 * @param facilityIdP
	 * @param merUser
	 */
	private void saveAdvancePassDetails(int facilityIdP, MerUser merUser) {
		AdmFacility facility = (AdmFacility) hibernateTemplateCareers.get(
				AdmFacility.class, facilityIdP);
		Timestamp timestamp = new Timestamp(new Date().getTime());
		WebMembership webMembership = new WebMembership();
		WebMembershipEmail membershipEmail = new WebMembershipEmail();
		// setting data into webmemberwhipinfo table
		WebMembershipInfo membershipInfo = new WebMembershipInfo();
		membershipInfo.setFirstName(merUser.getFirstName());
		membershipInfo.setLastName(merUser.getLastName());
		membershipInfo.setCreateDate(timestamp);
		membershipInfo.setZipCode(facility.getPostcode());
		if (null != facility.getCountry()
				&& (facility.getCountry().equalsIgnoreCase(
						MMJBCommonConstants.COUNTRY_USA) || facility
						.getCountry().equals("US"))) {
			membershipInfo.setCountryId(MMJBCommonConstants.COUNTRY_USA_VAL);
		} else if (null != facility.getCountry()
				&& (facility.getCountry()
						.equalsIgnoreCase(MMJBCommonConstants.COUNTRY_CA))) {
			membershipInfo.setCountryId(MMJBCommonConstants.COUNTRY_CA_VAL);
		}

		hibernateTemplateAdvancePass.saveOrUpdate(membershipInfo);
		// setting data into webmemberwhip table
		webMembership.setWebMembershipInfo(membershipInfo);
		webMembership.setPassword(merUser.getPassword());
		webMembership.setWebMembershipLevelID(2);
		webMembership.setCreateDate(timestamp);
		hibernateTemplateAdvancePass.saveOrUpdate(webMembership);
		// setting data into webmemberwhipemail table
		membershipEmail.setWebMembership(webMembership);
		membershipEmail.setEmail(merUser.getEmail());
		membershipEmail.setCreateDate(timestamp);
		membershipEmail.setPrimaryEmail(true);

		hibernateTemplateAdvancePass.saveOrUpdate(membershipEmail);
	}

	/**
	 * Method to update required Data Into Advance Pass
	 * 
	 * @param facilityIdP
	 * @param merUser
	 */
	private void editAdvancePassDetails(AccountProfileDTO empDTO, String email,Boolean advPassUserWithNullPass) {
		WebMembership webMembership = new WebMembership();
		WebMembershipInfo membershipInfo = new WebMembershipInfo();

		List<WebMembershipEmail> membershipEmailList = hibernateTemplateAdvancePass
				.find("from  WebMembershipEmail WHERE  email=?", email);

		if (null != membershipEmailList && !membershipEmailList.isEmpty()) {

			// Update WebMembershipEmail
			WebMembershipEmail webMembershipEmail = membershipEmailList.get(0);
			if (empDTO.isAdminLogin()) {
				webMembershipEmail.setEmail(empDTO.getEmail());
			}

			// Update WebMembership
			webMembership = webMembershipEmail.getWebMembership();
			if(advPassUserWithNullPass){
				webMembership.setPassword(empDTO.getPassword());
				webMembership.setEncryptPassword(null);
				webMembership.setSalt(null);
			}
			// Update WebMembershipInfo
			membershipInfo = webMembership.getWebMembershipInfo();

			membershipInfo.setFirstName(empDTO.getFirstName());
			membershipInfo.setLastName(empDTO.getLastName());
			membershipInfo.setZipCode(empDTO.getZipCode());
			if (null != empDTO.getCountry()
					&& (empDTO.getCountry().equalsIgnoreCase(
							MMJBCommonConstants.COUNTRY_USA) || empDTO
							.getCountry().equalsIgnoreCase("US"))) {
				membershipInfo
						.setCountryId(MMJBCommonConstants.COUNTRY_USA_VAL);
			} else if (null != empDTO.getCountry()
					&& (empDTO.getCountry()
							.equalsIgnoreCase(MMJBCommonConstants.COUNTRY_CA))) {
				membershipInfo.setCountryId(MMJBCommonConstants.COUNTRY_CA_VAL);
			}

			webMembership.setWebMembershipInfo(membershipInfo);
			webMembershipEmail.setWebMembership(webMembership);

			hibernateTemplateAdvancePass.saveOrUpdate(webMembershipEmail);
			hibernateTemplateAdvancePass.saveOrUpdate(webMembership);
			hibernateTemplateAdvancePass.saveOrUpdate(membershipInfo);
		}

	}
}