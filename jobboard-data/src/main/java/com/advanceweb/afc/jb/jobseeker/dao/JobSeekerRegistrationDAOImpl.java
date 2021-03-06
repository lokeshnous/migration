/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
import com.advanceweb.afc.jb.data.entities.MerLocation;
import com.advanceweb.afc.jb.data.entities.MerProfileAttrib;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.MerUserProfile;
import com.advanceweb.afc.jb.data.entities.WebMembership;
import com.advanceweb.afc.jb.data.entities.WebMembershipEmail;
import com.advanceweb.afc.jb.data.entities.WebMembershipInfo;
import com.advanceweb.afc.jb.user.dao.UserDao;
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

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(JobSeekerRegistrationDAOImpl.class);
	
	/** The registration conversion helper. */
	@Autowired
	private RegistrationConversionHelper registrationConversionHelper;
	
	/** The hibernate template. */
	private HibernateTemplate hibernateTemplate;
	
	/** The hibernate template careers. */
	private HibernateTemplate hibernateTemplateCareers;
	
	/** The hibernate template advance pass. */
	private HibernateTemplate hibernateTemplateAdvancePass;
	
	/** The Constant VERIFY_EMAIL. */
	private static final String VERIFY_EMAIL = "from MerUser e where e.email = ?";
	
	/** The Constant REGISTRATION_ATTRIBS. */
	private static final String REGISTRATION_ATTRIBS = "from MerProfileAttrib prof";
	
	/** The Constant FIND_JOBSEEKER_ROLE_ID. */
	private static final String FIND_JOBSEEKER_ROLE_ID="from AdmRole role where role.name=?";
	
	/** The Constant FIND_JOBSEEKER_SUBSCRIPTIONS. */
	private static final String FIND_JOBSEEKER_SUBSCRIPTIONS="from AdmSubscription sub where sub.subscriptionType=?";
	
	/** The Constant FIND_JOBSEEKER_PROFILE. */
	private static final String FIND_JOBSEEKER_PROFILE = "from MerUserProfile prof where prof.id.userId=?";
	
	/** The Constant VERIFY_EMAIL_ADVANCEPASS. */
	private static final String VERIFY_EMAIL_ADVANCEPASS = "from WebMembershipEmail e where e.email = ? and e.deleteDate is NULL";
	
	/** The user dao. */
	@Autowired
	private UserDao userDAO;
	
	/**
	 * Sets the hibernate template.
	 *
	 * @param sessionFactoryMerionTracker the session factory merion tracker
	 * @param sessionFactory the session factory
	 * @param sessionFactoryAdvancePass the session factory advance pass
	 */
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
			
			//Commenting here but we are saving subscription along with publications later
			/*List<AdmUserSubscription> admUserSubs = registrationConversionHelper.transformMerUserDTOToAdmUserSubs(jsDTO, merUser);
			if (admUserSubs != null) {
				hibernateTemplateCareers.saveOrUpdateAll(admUserSubs);
			}*/
			
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
			UserDTO userDTO=registrationConversionHelper.transformMerUserToUserDTO(merUser,jsDTO.getMerUserDTO().isOldUser(),jsDTO.getMerUserDTO().isAdvPassUser(),jsDTO.getMerUserDTO().isAdvPassUserWithNullPass());
			jsDTO.setMerUserDTO(userDTO);
			if(!userDTO.isOldUser() && !userDTO.isAdvPassUser() && !userDTO.isAdvPassUserWithNullPass()){
			saveAdvancePassDetails(jsDTO);
			}
			else{
				updateAdvancePassDetails(jsDTO);
			}
			return registrationConversionHelper.transformMerUserToUserDTO(merUser,jsDTO.getMerUserDTO().isOldUser(),jsDTO.getMerUserDTO().isAdvPassUser(),jsDTO.getMerUserDTO().isAdvPassUserWithNullPass());
			
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
				updateAdvancePassDetails(jsDTO);
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return true;
	}



	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.jobseeker.dao.JobSeekerRegistrationDAO#jsChangePassword(com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO)
	 */
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
					membership.setSalt(null);
					membership.setEncryptPassword(null);
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.jobseeker.dao.JobSeekerRegistrationDAO#validatePassword(com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO)
	 */
	@Override
	public boolean validatePassword(JobSeekerRegistrationDTO dto) {
		try {
			if (dto.getMerUserDTO() != null) {
				// commented code to fix the issue of checking password from advance pass not by JB2
//				MerUser user = hibernateTemplate.get(MerUser.class, dto.getMerUserDTO().getUserId());
				UserDTO user = userDAO.getAdvancePassUser(dto.getMerUserDTO().getEmailId());
				if(null != user){
					return user.getPassword().equals(dto.getMerUserDTO().getCurrentPassword());
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.jobseeker.dao.JobSeekerRegistrationDAO#validateEmail(java.lang.String)
	 */
	@Override
	public boolean validateEmail(String email) {
		try {
			if (!StringUtils.isEmptyOrWhitespaceOnly(email)) {
				List<MerUser> usersList = hibernateTemplate.find(VERIFY_EMAIL,email);
				List<WebMembershipEmail> webMembershipEmails = hibernateTemplateAdvancePass.find(
						VERIFY_EMAIL_ADVANCEPASS, email);
				if(null != usersList && !usersList.isEmpty()){
					MerUser user = usersList.get(0);
					return (null != user ? true : false);
				}
				// validating the email id in the advance pass server or not
				if (null != webMembershipEmails
						&& !webMembershipEmails.isEmpty()) {
					WebMembershipEmail webMembershipEmail = webMembershipEmails
							.get(0);
					return (null != webMembershipEmail ? true : false);
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.jobseeker.dao.JobSeekerRegistrationDAO#getProfileAttributes()
	 */
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
	
	
	
	
	/**
	 * Gets the country list.
	 *
	 * @return the country list
	 */
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
		
		merUser = registrationConversionHelper.transformJSRegnToUserDTO(jsDTO, merUser);		
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
		membershipEmail.setPrimaryEmail(true);

		hibernateTemplateAdvancePass.saveOrUpdate(membershipEmail);
	}
	
	/**
	 * Method to update required Data Into Advance Pass
	 * 
	 * @param jsDTO
	 */
	private void updateAdvancePassDetails(JobSeekerRegistrationDTO jsDTO) {
	
		UserDTO merUser = jsDTO.getMerUserDTO();
		WebMembership webMembership = new WebMembership();
		WebMembershipInfo membershipInfo = new WebMembershipInfo();

		List<WebMembershipEmail> membershipEmailList = hibernateTemplateAdvancePass
				.find("from  WebMembershipEmail WHERE  email=?",
						merUser.getEmailId());

		if (null != membershipEmailList && !membershipEmailList.isEmpty()) {

			merUser = registrationConversionHelper.transformJSRegnToUserDTO(
					jsDTO, merUser);

			// Update WebMembershipEmail
			WebMembershipEmail webMembershipEmail = membershipEmailList.get(0);

			// Update WebMembership
			webMembership = webMembershipEmail.getWebMembership();
			if(jsDTO.getMerUserDTO().isAdvPassUserWithNullPass()){
				webMembership.setPassword(jsDTO.getMerUserDTO().getPassword());
				webMembership.setEncryptPassword(null);
				webMembership.setSalt(null);
			}
//			webMembership.setWebMembershipLevelID(2);

			// Update WebMembershipInfo
			membershipInfo = webMembership.getWebMembershipInfo();

			membershipInfo.setFirstName(merUser.getFirstName());
			membershipInfo.setLastName(merUser.getLastName());
			membershipInfo.setZipCode(merUser.getZipCode());
			if (null != merUser.getCountry()
					&& (merUser.getCountry().equalsIgnoreCase(
							MMJBCommonConstants.COUNTRY_USA) || merUser
							.getCountry().equalsIgnoreCase("US"))) {
				membershipInfo
						.setCountryId(MMJBCommonConstants.COUNTRY_USA_VAL);
			} else if (null != merUser.getCountry()
					&& (merUser.getCountry()
							.equalsIgnoreCase(MMJBCommonConstants.COUNTRY_CA))) {
				membershipInfo.setCountryId(MMJBCommonConstants.COUNTRY_CA_VAL);
			}

			webMembership.setWebMembershipInfo(membershipInfo);
			webMembershipEmail.setWebMembership(webMembership);

			hibernateTemplateAdvancePass.saveOrUpdate(webMembershipEmail);
	
	}
	
	}	
}