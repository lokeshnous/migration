package com.advanceweb.afc.jb.employer.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

//import org.hibernate.validator.util.NewInstance;

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
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;

import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.data.entities.AdmRole;

import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.AdmUserRolePK;
import com.advanceweb.afc.jb.data.entities.MerLocation;
import com.advanceweb.afc.jb.data.entities.MerProfileAttrib;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.MerUserProfile;
import com.advanceweb.afc.jb.employer.helper.EmployerRegistrationConversionHelper;
import com.advanceweb.afc.jb.user.helper.RegistrationConversionHelper;
import com.mysql.jdbc.StringUtils;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:55 PM
 */
@Repository("employerRegistrationDAO")
public class EmployerRegistrationDAOImpl implements EmployerRegistrationDAO {

	private static final Logger LOGGER = Logger
			.getLogger("EmployerRegistrationDAOImpl.class");

	
	private final String FIND_EMPLOYER_ROLE_ID="from AdmRole role where role.name=?";
	private final String REGISTRATION_ATTRIBS = "from MerProfileAttrib prof";
	private final String VERIFY_EMAIL = "from MerUser e where e.email = ?";
	private final String FIND_EMPLOYER_PROFILE="from MerUserProfile prof where prof.id.userId=?";


	private HibernateTemplate hibernateTemplateTracker;
	
	private HibernateTemplate hibernateTemplateCareers;
	
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private EmployerRegistrationConversionHelper empHelper;
	
	@Autowired
	private RegistrationConversionHelper registrationConversionHelper;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker,SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(sessionFactoryMerionTracker);
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);
		
	}
	

	@Autowired
	public void setHibernateTemplateCareers(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public EmployerRegistrationDAOImpl(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * To save employer registrations
	 * 
	 * @param empDTO
	 * @return boolean
	 */
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public MerUserDTO createNewEmployer(EmployerProfileDTO empDTO) {
		try {
			MerUser merUser = empHelper.transformMerUserDTOToMerUser(empDTO,
					null);
			if (merUser != null) {
				hibernateTemplateTracker.save(merUser);
			}
			// AdmFacility facility =
			// registrationConversionHelper.transformMerUserToUserDTO(merUser);

			List<MerUserProfile> merUserProfiles = empHelper
					.transformMerUserDTOToMerUserProfiles(empDTO, merUser);
			if (merUserProfiles != null) {
				hibernateTemplateTracker.saveOrUpdateAll(merUserProfiles);
			}
			// Getting role
			@SuppressWarnings("unchecked")
			List<AdmRole> roleList = hibernateTemplateCareers.find(
					FIND_EMPLOYER_ROLE_ID, "facility_admin");
			if (null != roleList && roleList.size() > 0) {
				AdmRole role = roleList.get(0);
				AdmUserRole userRole = new AdmUserRole();
				userRole.setCreateUserId(MMJBCommonConstants.ZERO_INT);
				userRole.setCreateDt(new Date());
				AdmUserRolePK pk = new AdmUserRolePK();
				pk.setUserId(merUser.getUserId());
				pk.setRoleId(role.getRoleId());
				userRole.setId(pk);
				hibernateTemplateCareers.saveOrUpdate(userRole);
			}
			return empHelper.transformMerUserToUserDTO(merUser);
			// AdmFacility admFacility = new AdmFacility();
			// admFacility.setFacilityType("FACILITY");

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param employerId
	 */
	public boolean deleteEmployer(long employerId){
		return false;
	}

	/**
	 * 
	 * @param employerId
	 */
	public EmployerProfileDTO getEmployerDetails(int employerId){
		EmployerProfileDTO emRegistrationDTO = new EmployerProfileDTO();
		try {
			if (employerId != 0) {
				MerUser merUser = hibernateTemplateTracker.load(MerUser.class, employerId);
				EmployerProfileDTO jsDTO = getProfileAttributes();
				@SuppressWarnings("unchecked")
				List<MerUserProfile> profiles = hibernateTemplateTracker.find(FIND_EMPLOYER_PROFILE,employerId);
								
				emRegistrationDTO = empHelper.transformMerUserProfilesToDTO(merUser, jsDTO, profiles);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return emRegistrationDTO;
	}

	/**
	 * 
	 * @param employer
	 */
	public boolean updateEmployerDetails(EmployerProfileDTO empDTO){
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
			MerUser merUser = empHelper.transformMerUserDTOToMerUser(empDTO, null);
			hibernateTemplateTracker.saveOrUpdate(merUser);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	return false;
	}
	
	private List<DropDownDTO> getCountryList() {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(MerLocation.class);
			criteria.setProjection(Projections.distinct(Projections.property("country")));
			@SuppressWarnings("unchecked")
			List<Object> merUtilityList = hibernateTemplateTracker.findByCriteria(criteria);
			return registrationConversionHelper.transformMerUtilityToDropDownDTO(merUtilityList);

		} catch (HibernateException e) {
			e.printStackTrace();
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
			@SuppressWarnings("unchecked")
			List<Object> merUtilityList = hibernateTemplateTracker
					.findByCriteria(criteria);
			return registrationConversionHelper.transformMerUtilityToDropDownDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
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
			@SuppressWarnings("unchecked")
			List<MerProfileAttrib> listProfAttrib = hibernateTemplateTracker
					.find(REGISTRATION_ATTRIBS);
			List<DropDownDTO> countryList = getCountryList();
			List<DropDownDTO> stateList = getStateList();
			dto = empHelper.transformProfileAttrib(
					listProfAttrib, countryList, stateList);

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return dto;
	}

	@Override
	public boolean validateEmail(String email) {
		try {
			if (!StringUtils.isEmptyOrWhitespaceOnly(email)) {
				@SuppressWarnings("unchecked")
				List<MerUser> usersList = hibernateTemplateTracker.find(VERIFY_EMAIL,email);
				if(null != usersList && usersList.size()>0){
					MerUser user = usersList.get(0);
					return (null != user ? true : false);
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
	


	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AdmFacilityContact> getEmployeeData(int userId,
			String contactType) {
		List<AdmFacilityContact> accountProfileDTO = new ArrayList<AdmFacilityContact>();
		try {
			if (userId > 0) {

				AdmFacility admFacility = new AdmFacility();
				admFacility.setFacilityId(userId);
				accountProfileDTO = hibernateTemplate
						.find("from AdmFacilityContact af where af.admFacility = ? and af.contactType=?",
								admFacility, contactType);

			}

		} catch (DataAccessException e) {
			LOGGER.info("Error for update of employee data");
		}
		return accountProfileDTO;
	}

	@Override
	public void editEmployeeAccount(AccountProfileDTO apd,int admfacilityid) {
				
		AdmFacilityContact facility=hibernateTemplate.get(AdmFacilityContact.class,  admfacilityid);
		facility.setFirstName(apd.getFirstName());
		facility.setCompany(apd.getCompanyName());
		facility.setStreet(apd.getStreet());
		facility.setCity(apd.getCity());
		facility.setState(apd.getState());
		facility.setPostcode(apd.getZipCode());
		facility.setCountry(apd.getCountry());
		facility.setEmail(apd.getEmail());
		facility.setPhone(apd.getPhone());
		hibernateTemplate.update(facility);
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<AdmFacilityContact> getEmployeePrimaryKey(int userId,
			String contactType) {

		List<AdmFacilityContact> accountProfileDTO=null;
		try {
			if (userId > 0) {
				 accountProfileDTO = hibernateTemplate
						.find("select a from AdmFacilityContact a,AdmFacility b,AdmUserFacility c where a.admFacility.facilityId= b.facilityId and a.admFacility.facilityId=c.id.facilityId "
								+"and c.id.userId="
								+ userId
								+ "and a.contactType= '" + contactType +"'");

				
				if(null != accountProfileDTO && accountProfileDTO.size() !=0){
					
				}
			}

		} catch (DataAccessException e) {
			LOGGER.info("Error for update of employee data");
		}
		return accountProfileDTO;
	}

}