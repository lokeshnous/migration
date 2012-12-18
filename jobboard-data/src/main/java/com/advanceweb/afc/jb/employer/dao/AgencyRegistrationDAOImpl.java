package com.advanceweb.afc.jb.employer.dao;

import java.sql.Timestamp;
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

import com.advanceweb.afc.jb.agency.helper.AgencyRegistrationConversionHelper;
import com.advanceweb.afc.jb.common.AgencyProfileDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
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
import com.advanceweb.afc.jb.user.helper.RegistrationConversionHelper;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:52 PM
 */
@Repository("agencyRegistrationDAO")
@SuppressWarnings("unchecked")
public class AgencyRegistrationDAOImpl implements AgencyRegistrationDAO {
	
	private static final Logger LOGGER = Logger
			.getLogger("AgencyRegistrationDAOImpl.class");

	private static final String FIND_AGENCY_ROLE_ID = "from AdmRole role where role.name=?";
	private static final String REGISTRATION_ATTRIBS = "from MerProfileAttrib prof";
	private static final String VERIFY_EMAIL = "from MerUser e where e.email = ? and e.deleteDt is NULL";
	private static final String VERIFY_EMAIL_ADVANCEPASS = "from WebMembershipEmail e where e.email = ? and e.deleteDate is NULL";
	@Autowired
	private AgencyRegistrationConversionHelper agencyHelper;

	@Autowired
	private RegistrationConversionHelper registrationConversionHelper;
	
	private HibernateTemplate hibernateTemplateTracker;

	private HibernateTemplate hibernateTemplateCareers;

	private HibernateTemplate hibernateTemplateAdvancePass;

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
	 * 
	 * @param agencyDTO
	 */
	public UserDTO createUser(AgencyProfileDTO agencyDTO) {
		try {
			MerUser merUser = agencyHelper.transformMerUserDTOToMerUser(
					agencyDTO, null);
			if (merUser != null) {
				// saving employer credentials
				hibernateTemplateTracker.save(merUser);
			}
			// saving the employer profile
			List<MerUserProfile> merUserProfiles = agencyHelper
					.transformMerUserDTOToMerUserProfiles(agencyDTO, merUser);
			if (merUserProfiles != null) {
				hibernateTemplateTracker.saveOrUpdateAll(merUserProfiles);
			}
			// Getting role

			List<AdmRole> roleList = hibernateTemplateCareers.find(
					FIND_AGENCY_ROLE_ID, "facility_admin");
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
			AdmFacility facility = agencyHelper
					.transformEmpDTOToAdmFAcility(agencyDTO);
			facility.setFacilityType(MMJBCommonConstants.FACILITY_SYSTEM);
			// TODO: Remove hard code values
			facility.setEmail(agencyDTO.getMerUserDTO().getEmailId());
			facility.setFacilityParentId(MMJBCommonConstants.ZERO_INT);
			facility.setNsCustomerID(agencyDTO.getMerUserDTO()
					.getNsCustomerID());
			facility.setCreateDt(new Date());
			facility.setCreateUserId(null);
			facility.setAccountNumber(null);
			facility.setNameDisplay(null);
			facility.setUrl(null);
			facility.setUrlDisplay(null);
			facility.setEmailDisplay(null);
			facility.setLogoPath(null);
			facility.setAdminUserId(null);
			facility.setCreateUserId(0);
			facility.setPromoMediaPath(null);
			facility.setColorPalette(null);
			facility.setCompanyNews(null);
			facility.setCompanyOverview(null);
			hibernateTemplateCareers.save(facility);

			// saving the data in adm_facility_contact
			AdmFacilityContact contact = agencyHelper
					.transformEmpDTOToAdmFacilityContact(agencyDTO, facility);
			contact.setContactType(MMJBCommonConstants.PRIMARY);
			contact.setCreateDt(new Date());
			contact.setEmail(agencyDTO.getMerUserDTO().getEmailId());
			contact.setActive(1);
			hibernateTemplateCareers.save(contact);

			// saving the data in the adm_user_facility
			AdmUserFacility userfacility = new AdmUserFacility();
			AdmUserFacilityPK facilityPK = new AdmUserFacilityPK();
			facilityPK.setFacilityId(facility.getFacilityId());
			facilityPK.setUserId(merUser.getUserId());
			facilityPK.setRoleId(roleId);
			userfacility.setFacilityPK(facilityPK);
			userfacility.setCreateUserId(0);
			// userfacility.setAdmRole();
			userfacility.setCreateDt(new Date());
			hibernateTemplateCareers.save(userfacility);
			saveAdvancePassDetails(facility.getFacilityId(),merUser);
			return agencyHelper.transformMerUserToUserDTO(merUser);

		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return null;
	}

	private List<DropDownDTO> getCountryList() {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(MerLocation.class);
			criteria.setProjection(Projections.distinct(Projections
					.property("country")));
		//	@SuppressWarnings("unchecked")
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
		//	@SuppressWarnings("unchecked")
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
	public AgencyProfileDTO getProfileAttributes() {
		AgencyProfileDTO dto = null;
		try {
		//	@SuppressWarnings("unchecked")
			List<MerProfileAttrib> listProfAttrib = hibernateTemplateTracker
					.find(REGISTRATION_ATTRIBS);
			List<DropDownDTO> countryList = getCountryList();
			List<DropDownDTO> stateList = getStateList();
			dto = agencyHelper.transformProfileAttrib(listProfAttrib,
					countryList, stateList);

		} catch (HibernateException e) {
			LOGGER.error(e);
		}

		return dto;
	}

	/**
	 * 
	 * @param agencyId
	 */
	public boolean deleteAgency(long agencyId) {
		return false;
	}

	/**
	 * 
	 * @param agencyId
	 */
	public AgencyProfileDTO getAgencyDetails(long agencyId) {
		return null;
	}

	/**
	 * 
	 * @param agencyDomain
	 */
	public boolean updateAgencyDetails(AgencyProfileDTO agencyDomain) {
		return false;
	}

	/**
	 * This method is called to check, whether registration is completed
	 * properly or not (To migrate old users to new application)
	 */
	@Override
	public boolean validateProfileAttributes(int agencyId) {
		try {
			List<MerUserProfile> profAttribList = hibernateTemplateTracker
					.find(" from MerUserProfile prof where prof.profilePK.userId=?",
							agencyId);
			if (null != profAttribList && !profAttribList.isEmpty()) {
				return true;
			}
		} catch (DataAccessException e) {
			LOGGER.error("ERROR" +e);
		}

		return false;
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
//			boolean isinvaliduser= OpenAMEUtility.openAMValidateEmail(email);
//			if(isinvaliduser){
//	    		LOGGER.info("OpenAM : user is already exist !");
	    		//model.setViewName("jobSeekerCreateAccount");
				//result.rejectValue("emailId", "NotEmpty", "Email address already exists");
//				return true;
//	    	}else{
//	    		LOGGER.info("OpenAM : valid user!");
//	    		
//	    	}
	    	//End of OpenAM code
//			if (!StringUtils.isEmptyOrWhitespaceOnly(email)) {
//			
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
			
//			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return false;
	}

	/**
	 * Method to insert required Data Into Advance Pass
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

		hibernateTemplateAdvancePass.saveOrUpdate(membershipEmail);
	}

	
}