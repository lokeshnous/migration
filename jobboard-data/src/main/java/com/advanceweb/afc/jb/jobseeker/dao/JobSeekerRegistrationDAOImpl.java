package com.advanceweb.afc.jb.jobseeker.dao;

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
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmRole;
import com.advanceweb.afc.jb.data.entities.AdmSubscription;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.AdmUserRolePK;
import com.advanceweb.afc.jb.data.entities.AdmUserSubscription;
import com.advanceweb.afc.jb.data.entities.MerLocation;
import com.advanceweb.afc.jb.data.entities.MerProfileAttrib;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.MerUserProfile;
import com.advanceweb.afc.jb.data.entities.WebMembership;
import com.advanceweb.afc.jb.data.entities.WebMembershipEmail;
import com.advanceweb.afc.jb.data.entities.WebMembershipInfo;
import com.advanceweb.afc.jb.user.helper.RegistrationConversionHelper;
import com.mysql.jdbc.StringUtils;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:56 PM
 */
@SuppressWarnings("unchecked")
@Transactional
@Repository("jobSeekerRegistrationDAO")
public class JobSeekerRegistrationDAOImpl implements JobSeekerRegistrationDAO {

	private static final Logger LOGGER = Logger.getLogger(JobSeekerRegistrationDAOImpl.class);
	@Autowired
	private RegistrationConversionHelper registrationConversionHelper;
	private HibernateTemplate hibernateTemplate;
	private HibernateTemplate hibernateTemplateCareers;
	private HibernateTemplate hibernateTemplateAdvancePass;
	private static final String VERIFY_EMAIL = "from MerUser e where e.email = ?";
	private static final String REGISTRATION_ATTRIBS = "from MerProfileAttrib prof";
	private static final String FIND_JOBSEEKER_ROLE_ID="from AdmRole role where role.name=?";
	private static final String FIND_JOBSEEKER_SUBSCRIPTIONS="from AdmSubscription sub where sub.subscriptionType=?";
	private static final String FIND_JOBSEEKER_PROFILE = "from MerUserProfile prof where prof.id.userId=?";

	@Autowired
	public void setHibernateTemplate(
			final SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory,
			SessionFactory sessionFactoryAdvancePass) {
		this.hibernateTemplate = new HibernateTemplate(
				sessionFactoryMerionTracker);
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);

		this.hibernateTemplateAdvancePass = new HibernateTemplate(
				sessionFactoryAdvancePass);
	}

	/**
	 * This method is called to save Job seeker registration information into
	 * Database
	 * 
	 * @param jobSeekerRegistrationDTO
	 */
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public UserDTO createNewJobSeeker(JobSeekerRegistrationDTO jsDTO) {
				
		try {
			MerUser merUser = registrationConversionHelper.transformMerUserDTOToMerUser(jsDTO, null);
			if (merUser != null) {
				hibernateTemplate.saveOrUpdate(merUser);			
			}
			
			List<MerUserProfile> merUserProfiles = registrationConversionHelper.transformMerUserDTOToMerUserProfiles(jsDTO, merUser);
			if (merUserProfiles != null) {
				hibernateTemplate.saveOrUpdateAll(merUserProfiles);
			}
			
			List<AdmUserSubscription> admUserSubs = registrationConversionHelper.transformMerUserDTOToAdmUserSubs(jsDTO, merUser);
			if (admUserSubs != null) {
				hibernateTemplateCareers.saveOrUpdateAll(admUserSubs);
			}
			
			List<AdmRole> roleList = hibernateTemplateCareers.find(FIND_JOBSEEKER_ROLE_ID,"jobseeker");
			if(null != roleList && !roleList.isEmpty()){
				AdmRole role = roleList.get(0);
				AdmUserRole userRole = new AdmUserRole();
				userRole.setCreateUserId(MMJBCommonConstants.ZERO_INT);
				AdmUserRolePK admUserRolePK = new AdmUserRolePK();
				admUserRolePK.setUserId(merUser.getUserId());
				admUserRolePK.setRoleId(role.getRoleId());
				userRole.setRolePK(admUserRolePK);
				hibernateTemplateCareers.saveOrUpdate(userRole);
			}
			saveAdvancePassDetails(jsDTO);
			return registrationConversionHelper.transformMerUserToUserDTO(merUser);
			
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}

	/**
	 * 
	 * @param jobSeekerId
	 */
	@Override
	public boolean deleteJobSeeker(int jobSeekerId) {
		return false;
	}

	/**
	 * 
	 * @param jobSeekerId
	 */
	@Override
	@Transactional(readOnly=true)
	public JobSeekerRegistrationDTO getJobSeekerDetails(int jobSeekerId) {
		JobSeekerRegistrationDTO jsRegistrationDTO = new JobSeekerRegistrationDTO();
		try {
			if (jobSeekerId != 0) {
				MerUser merUser = hibernateTemplate.load(MerUser.class, jobSeekerId);
				JobSeekerRegistrationDTO jsDTO = getProfileAttributes();
				List<MerUserProfile> profiles = hibernateTemplate.find(FIND_JOBSEEKER_PROFILE,jobSeekerId);
								
				jsRegistrationDTO = registrationConversionHelper.transformMerUserProfilesToDTO(merUser, jsDTO, profiles);
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return jsRegistrationDTO;
	}

	/**
	 * 
	 * @param jobSeeker
	 */
	@Override
	public boolean updateJobSeekerDetails(JobSeekerRegistrationDTO jsDTO) {

		try {				
				MerUser merUser = hibernateTemplate.get(MerUser.class,jsDTO.getMerUserDTO().getUserId());
				
				merUser = registrationConversionHelper.transformMerUserDTOToMerUser(jsDTO, merUser);
				
				if (merUser != null) {
					hibernateTemplate.saveOrUpdate(merUser);			
				}
				
				List<MerUserProfile> merUserProfiles = registrationConversionHelper.transformMerUserDTOToMerUserProfiles(jsDTO, merUser);
				if (merUserProfiles != null) {
					hibernateTemplate.saveOrUpdateAll(merUserProfiles);
				}
				//updateAdvancePassDetails(jsDTO);
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return true;
	}



	@Override
	public boolean jsChangePassword( JobSeekerRegistrationDTO dto) {
		try {
			if (dto.getMerUserDTO() != null) {
				MerUser user = hibernateTemplate.get(MerUser.class, dto.getMerUserDTO().getUserId());
				WebMembershipEmail webMembershipEmail = (WebMembershipEmail)DataAccessUtils.uniqueResult(hibernateTemplateAdvancePass.find("from WebMembershipEmail where email = ?", user.getEmail()));
				WebMembership membership = hibernateTemplateAdvancePass.get(WebMembership.class, webMembershipEmail.getWebMembership().getWebMembershipID());
				if(null != user && null != membership){
					user.setPassword(dto.getMerUserDTO().getPassword());
					membership.setPassword(user.getPassword());
				}
				hibernateTemplate.saveOrUpdate(user);
				hibernateTemplateAdvancePass.saveOrUpdate(membership);
				return true;
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}catch (Exception e) {
			LOGGER.error("Failed to change the password : "+e);
		}
		return false;
	}

	@Override
	public boolean validatePassword(JobSeekerRegistrationDTO dto) {
		try {
			if (dto.getMerUserDTO() != null) {
				MerUser user = hibernateTemplate.get(MerUser.class, dto.getMerUserDTO().getUserId());
				if(null != user){
					return user.getPassword().equals(dto.getMerUserDTO().getCurrentPassword());
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		
		return false;
	}

	@Override
	public boolean validateEmail(String email) {
		try {
			if (!StringUtils.isEmptyOrWhitespaceOnly(email)) {
				List<MerUser> usersList = hibernateTemplate.find(VERIFY_EMAIL,email);
				if(null != usersList && !usersList.isEmpty()){
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
	public JobSeekerRegistrationDTO getProfileAttributes() {
		JobSeekerRegistrationDTO dto = null;
		try {
			  List<MerProfileAttrib> listProfAttrib = hibernateTemplate.find(REGISTRATION_ATTRIBS);
			  List<DropDownDTO> countryList = getCountryList();
			  List<DropDownDTO> stateList = getStateList();
			  List<DropDownDTO> subsList = getSubscriptions();
			  dto = registrationConversionHelper.transformProfileAttrib(listProfAttrib, countryList, stateList, subsList);
			
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		
		return dto;
	}
	
	
	
	
	private List<DropDownDTO> getCountryList() {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(MerLocation.class);
			criteria.setProjection(Projections.distinct(Projections.property("country")));
			List<Object> merUtilityList = hibernateTemplate.findByCriteria(criteria);
			return registrationConversionHelper.transformMerUtilityToDropDownDTO(merUtilityList);

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
			List<Object> merUtilityList = hibernateTemplate
					.findByCriteria(criteria);
			return registrationConversionHelper.transformMerUtilityToDropDownDTO(merUtilityList);
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	private List<DropDownDTO> getSubscriptions(){
		
		try {
			List<AdmSubscription> subsList = hibernateTemplateCareers.find(FIND_JOBSEEKER_SUBSCRIPTIONS,"USER");
			return registrationConversionHelper.transformAdmSubscriptionToDropDownDTO(subsList);
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		
		return null;
	}
	
	/**
	 * This method is called to check, whether registration is completed 
	 * properly or not (To migrate old users to new application)
	 */
	public boolean validateProfileAttributes(int jobseekerId){
		try {
			List<MerUserProfile> profAttribList = hibernateTemplate.find(" from MerUserProfile prof where prof.profilePK.userId=?",jobseekerId);
			if(null != profAttribList && !profAttribList.isEmpty()){
				return true;
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		
		return false;
	}
	/**
	 * Method to insert required Data Into Advance Pass
	 * @param facilityIdP
	 * @param merUser
	 */
	private void saveAdvancePassDetails(JobSeekerRegistrationDTO jsDTO) {
		UserDTO merUser = jsDTO.getMerUserDTO();
		Timestamp timestamp = new Timestamp(new Date().getTime());
		WebMembership webMembership = new WebMembership();
		WebMembershipEmail membershipEmail = new WebMembershipEmail();
		WebMembershipInfo membershipInfo = new WebMembershipInfo();
		membershipInfo.setFirstName(merUser.getFirstName());
		membershipInfo.setLastName(merUser.getLastName());
		membershipInfo.setCreateDate(timestamp);
		membershipInfo.setZipCode(merUser.getZipCode());
		if (null != merUser.getCountry()
				&& (merUser.getCountry().equalsIgnoreCase(
						MMJBCommonConstants.COUNTRY_USA) || merUser
						.getCountry().equalsIgnoreCase("US"))) {
			membershipInfo.setCountryId(MMJBCommonConstants.COUNTRY_USA_VAL);
		} else if (null != merUser.getCountry()
				&& (merUser.getCountry()
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
		membershipEmail.setEmail(merUser.getEmailId());
		membershipEmail.setCreateDate(timestamp);

		hibernateTemplateAdvancePass.saveOrUpdate(membershipEmail);

	}
	
	/**
	 * Method to update required Data Into Advance Pass
	 * 
	 * @param facilityIdP
	 * @param merUser
	 */
	private void updateAdvancePassDetails(JobSeekerRegistrationDTO jsDTO) {
		UserDTO merUser = jsDTO.getMerUserDTO();
		Timestamp timestamp = new Timestamp(new Date().getTime());
		WebMembership webMembership = new WebMembership();
		WebMembershipEmail membershipEmail = new WebMembershipEmail();
		WebMembershipInfo membershipInfo = new WebMembershipInfo();
		membershipInfo.setFirstName(merUser.getFirstName());
		membershipInfo.setLastName(merUser.getLastName());
		membershipInfo.setCreateDate(timestamp);
		membershipInfo.setZipCode(merUser.getZipCode());
		if (null != merUser.getCountry()
				&& (merUser.getCountry().equalsIgnoreCase(
						MMJBCommonConstants.COUNTRY_USA) || merUser
						.getCountry().equalsIgnoreCase("US"))) {
			membershipInfo.setCountryId(MMJBCommonConstants.COUNTRY_USA_VAL);
		} else if (null != merUser.getCountry()
				&& (merUser.getCountry()
						.equalsIgnoreCase(MMJBCommonConstants.COUNTRY_CA))) {
			membershipInfo.setCountryId(MMJBCommonConstants.COUNTRY_CA_VAL);
		}

		hibernateTemplateAdvancePass.saveOrUpdate(membershipInfo);
		// setting data into webmemberwhip table
//		webMembership.setWebMembershipInfoID(membershipInfo
//				.getWebMembershipInfoID());
		webMembership.setPassword(merUser.getPassword());
		webMembership.setWebMembershipLevelID(2);
		webMembership.setCreateDate(timestamp);
		hibernateTemplateAdvancePass.saveOrUpdate(webMembership);
		// setting data into webmemberwhipemail table
//		membershipEmail.setWebMembershipID(webMembership.getWebMembershipID());
		membershipEmail.setEmail(merUser.getEmailId());
		membershipEmail.setCreateDate(timestamp);

		hibernateTemplateAdvancePass.saveOrUpdate(membershipEmail);

	}
	
	
}