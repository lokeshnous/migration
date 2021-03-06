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
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserFacilityPK;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.AdmUserRolePK;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.MerUserProfile;
import com.advanceweb.afc.jb.data.entities.MerUserProfilePK;
import com.advanceweb.afc.jb.data.entities.WebMembership;
import com.advanceweb.afc.jb.data.entities.WebMembershipEmail;
import com.advanceweb.afc.jb.data.entities.WebMembershipInfo;
import com.advanceweb.afc.jb.employer.helper.EmployerRegistrationConversionHelper;
import com.mysql.jdbc.StringUtils;

/**
 * To save new job Owner * @author deviprasadm
 * 
 */
@SuppressWarnings("unchecked")
@Repository("manageAccessPermissionDAO")
public class ManageAccessPermissionDAOImpl implements ManageAccessPermissionDAO {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(ManageAccessPermissionDAOImpl.class);
	
	/** The Constant FIND_ADM_FACILITY. */
	private static final String FIND_ADM_FACILITY = "from AdmFacility where facilityId=?";
	
	/** The Constant VERIFY_EMAIL_ADVANCEPASS. */
	private static final String VERIFY_EMAIL_ADVANCEPASS = "from WebMembershipEmail e where e.email = ? and e.deleteDate is NULL";
	
	/** The Constant VERIFY_EMAILID. */
	private static final String VERIFY_EMAILID = "from WebMembershipEmail e where e.email = ?";
	
	/** The hibernate template tracker. */
	private HibernateTemplate hibernateTemplateTracker;

	/** The hibernate template careers. */
	private HibernateTemplate hibernateTemplateCareers;
	
	/** The hibernate template advance pass. */
	private HibernateTemplate hibernateTemplateAdvancePass;
	
	/** The emp helper. */
	@Autowired
	private EmployerRegistrationConversionHelper empHelper;
	
	/** The manage featured employer profile dao. */
	@Autowired
	private ManageFeaturedEmployerProfileDAO manageFeaturedEmployerProfileDAO;

	/** The Constant VERIFY_EMAIL. */
	private static final String VERIFY_EMAIL = "from MerUser where email = ? and deleteDt is NOT NULL";

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
		this.hibernateTemplateAdvancePass = new HibernateTemplate(sessionFactoryAdvancePass);

	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageAccessPermissionDAO#createJobOwner(com.advanceweb.afc.jb.common.EmployerProfileDTO, int, int)
	 */
	@Override
	public UserDTO createJobOwner(EmployerProfileDTO empDTO, int facilityIdP,
			int userIdp) {
		try {
			MerUser merUser = empHelper.transformMerUserDTOToMerUser(empDTO);

			if (null != merUser) {
				if (null != empDTO.getMerUserDTO()
						&& empDTO.getMerUserDTO().getUserId() > 0) {
					merUser.setUserId(empDTO.getMerUserDTO().getUserId());
					// saving employer credentials
					hibernateTemplateTracker.saveOrUpdate(merUser);
					
					saveAdvancePassDetails(facilityIdP, merUser);
					
				} else {
					if (null != merUser) {
						// saving employer credentials
						hibernateTemplateTracker.saveOrUpdate(merUser);						
						saveAdvancePassDetails(facilityIdP, merUser);
					}
					// saving the employer profile
					List<MerUserProfile> merUserProfilesList = hibernateTemplateTracker
							.find(" from MerUserProfile prof where prof.profilePK.userId=?",
									userIdp);
					List<MerUserProfile> merUserProfileList =new ArrayList<MerUserProfile>();
					if (null != merUserProfilesList && !merUserProfilesList.isEmpty()) {
						for (MerUserProfile merUserProfiles : merUserProfilesList) {
							MerUserProfile userProfile = new MerUserProfile();

							MerUserProfilePK merUserProfilePK = new MerUserProfilePK();
							merUserProfilePK.setUserId(merUser.getUserId());
							merUserProfilePK
									.setProfileAttribId(merUserProfiles.getProfilePK()
											.getProfileAttribId());
							userProfile.setProfilePK(merUserProfilePK);
							userProfile.setAttribValue(merUserProfiles.getAttribValue());
							merUserProfileList.add(userProfile);
							
						}
						hibernateTemplateTracker.saveOrUpdateAll(merUserProfileList);
					}
					// saving the data in Adm_User_Role
					saveAdmUserRole(empDTO, userIdp, merUser);

					// saving the data in adm_facility
					AdmFacility facility = saveFacilityDetails(facilityIdP,
							userIdp, merUser);

					// saving the data in the adm_user_facility
					AdmUserFacility userfacility = new AdmUserFacility();
					AdmUserFacilityPK facilityPK = new AdmUserFacilityPK();
					facilityPK.setFacilityId(facility.getFacilityId());
					facilityPK.setUserId(merUser.getUserId());
					facilityPK.setRoleId(empDTO.getRollId());
					userfacility.setFacilityPK(facilityPK);
					userfacility.setCreateUserId(0);
					// userfacility.setAdmRole();
					userfacility.setCreateDt(new Date());
					hibernateTemplateCareers.saveOrUpdate(userfacility);
				}
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return null;
	}

	/**
	 * Method to insert required Data Into Advance Pass
	 * @param facilityIdP
	 * @param merUser
	 */
	private void saveAdvancePassDetails(int facilityIdP, MerUser merUser) {
		AdmFacility facility = (AdmFacility) hibernateTemplateCareers.find(
				FIND_ADM_FACILITY, facilityIdP).get(0);
		Timestamp timestamp = new Timestamp(new Date().getTime());
		List<WebMembershipEmail> webMembershipEmails = hibernateTemplateAdvancePass
				.find(VERIFY_EMAILID, merUser.getEmail());
		WebMembershipEmail membershipEmail = null;
		WebMembership webMembership = null;
		WebMembershipInfo membershipInfo = null;
		if (null != webMembershipEmails && !webMembershipEmails.isEmpty()) {
			membershipEmail = webMembershipEmails.get(0);
			membershipEmail.setDeleteDate(null);
			webMembership = membershipEmail.getWebMembership();
			membershipInfo = webMembership.getWebMembershipInfo();
		} else {
			webMembership = new WebMembership();
			membershipEmail = new WebMembershipEmail();
			// setting data into webmemberwhipinfo table
			membershipInfo = new WebMembershipInfo();
		}
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
	 * Method to save the facility and facility detail
	 * 
	 * @param facilityIdP
	 * @param userIdp
	 * @param merUser
	 * @return
	 */
	private AdmFacility saveFacilityDetails(int facilityIdP, int userIdp,
			MerUser merUser) {
		AdmFacility facilityP = (AdmFacility) hibernateTemplateCareers.find(
				FIND_ADM_FACILITY, facilityIdP).get(0);

		int nsCustomerId = 0;
		List<FacilityDTO> admFacilityDTOList = manageFeaturedEmployerProfileDAO
				.getNSCustomerIDFromAdmFacility(facilityIdP);
		nsCustomerId = admFacilityDTOList.get(0).getNsCustomerID();
		
		AdmFacility facility = new AdmFacility();

		facility.setFacilityParentId(facilityIdP);
		facility.setCreateDt(new Date());
		facility.setCreateUserId(userIdp);
		// saving the data in adm_facility as per the logged in User
		if (facilityP != null) {
			setFacilityDetails(facilityP, nsCustomerId, facility);
		}

		List<AdmFacilityContact> admFacilityContactP = facilityP
				.getAdmFacilityContacts();

		// saving the data in adm_facility_contact as per the logged in User
		List<AdmFacilityContact> admFacilityContactList = new ArrayList<AdmFacilityContact>();
		if (null != admFacilityContactP) {
			Date currentDate = new Date();
			for (AdmFacilityContact contact : admFacilityContactP) {

				setFacilityContactDetails(facility, admFacilityContactList,
						currentDate, contact, merUser);
			}
		}

		// facility.setAdmFacilityContacts(admFacilityContactList);

		return facility;
	}

	/**
	 *  Method to save the data in adm_facility_contact as per the logged in User
	 * @param facility
	 * @param admFacilityContactList
	 * @param currentDate
	 * @param contact
	 */
	private void setFacilityContactDetails(AdmFacility facility,
			List<AdmFacilityContact> admFacilityContactList, Date currentDate,
			AdmFacilityContact contact, MerUser merUser) {
		/**
		 * creating add Job owner Users in OpenAM
		 */
//				boolean isCreated = OpenAMEUtility.openAMCreateEmp(merUser,
//						contact);
//				LOGGER.info("Open AM :Employee add owner User is created!"
//						+ isCreated);
		// Ends OpenAM code

		AdmFacilityContact admFacilityContact = new AdmFacilityContact();
		admFacilityContact.setCity(contact.getCity());
		admFacilityContact.setCompany(contact.getCompany());
		admFacilityContact.setCountry(contact.getCountry());
		admFacilityContact.setFirstName(merUser.getFirstName());
		admFacilityContact.setStreet(contact.getStreet());
		admFacilityContact.setState(contact.getState());
		admFacilityContact.setPostcode(contact.getPostcode());
		admFacilityContact.setLastName(merUser.getLastName());
		admFacilityContact.setMiddleName(contact.getMiddleName());
		admFacilityContact.setJobTitle(contact.getJobTitle());
		admFacilityContact.setPhone(contact.getPhone());
		admFacilityContact.setPhone2(contact.getPhone2());
		admFacilityContact.setContactType(contact.getContactType());
		admFacilityContact.setCreateDt(currentDate);
		admFacilityContact.setEmail(merUser.getEmail());
		admFacilityContact.setActive(contact.getActive());
		admFacilityContact.setCreateDt(currentDate);
		admFacilityContact.setAdmFacility(facility);
		admFacilityContactList.add(admFacilityContact);
		admFacilityContact.setAdmFacility(facility);
		hibernateTemplateCareers.save(admFacilityContact);
	}

	/**
	 * Method to save the data in adm_facility as per the logged in User
	 * @param facilityP
	 * @param nsCustomerId
	 * @param facility
	 */
	private void setFacilityDetails(AdmFacility facilityP, int nsCustomerId,
			AdmFacility facility) {
		if (facilityP.getFacilityType().equals(
				MMJBCommonConstants.FACILITY_SYSTEM)) {
			facility.setFacilityType(MMJBCommonConstants.FACILITY_SYSTEM);
		} else {
			facility.setFacilityType(MMJBCommonConstants.FACILITY);
		}
		facility.setEmail(facilityP.getEmail());
		facility.setName(facilityP.getName());
		facility.setAccountNumber(facilityP.getAccountNumber());
		facility.setNameDisplay(facilityP.getNameDisplay());
		facility.setUrl(facilityP.getUrl());
		facility.setUrlDisplay(facilityP.getUrlDisplay());
		facility.setEmailDisplay(facilityP.getEmailDisplay());
		facility.setLogoPath(facilityP.getLogoPath());
		facility.setAdminUserId(facilityP.getAdminUserId());
		facility.setCreateUserId(facilityP.getCreateUserId());
		facility.setPromoMediaPath(facilityP.getPromoMediaPath());
		facility.setColorPalette(facilityP.getColorPalette());
		facility.setCompanyNews(facilityP.getCompanyNews());
		facility.setCompanyOverview(facilityP.getCompanyOverview());
		facility.setNsCustomerID(nsCustomerId);
		hibernateTemplateCareers.save(facility);
	}

	/**
	 * saving the data in Adm_User_Role
	 * 
	 * @param empDTO
	 * @param userIdp
	 * @param merUser
	 */
	private void saveAdmUserRole(EmployerProfileDTO empDTO, int userIdp,
			MerUser merUser) {
		AdmUserRole userRole = new AdmUserRole();
		userRole.setCreateUserId(userIdp);
		userRole.setCreateDt(new Date());

		AdmUserRolePK admUserRolePK = new AdmUserRolePK();
		admUserRolePK.setUserId(merUser.getUserId());
		admUserRolePK.setRoleId(empDTO.getRollId());
		userRole.setRolePK(admUserRolePK);
		hibernateTemplateCareers.saveOrUpdate(userRole);
	}

	/**
	 * This method is called to delete the Job Owner
	 * 
	 * @param accessPermissionDTOList
	 * @return delete status
	 */

	@Override
	public boolean deleteJobOwner(int jobOwnerId) {
		MerUser ownerDetails = hibernateTemplateTracker.get(MerUser.class,
				jobOwnerId);
		List<WebMembershipEmail> webMembershipEmails = hibernateTemplateAdvancePass
				.find(VERIFY_EMAIL_ADVANCEPASS, ownerDetails.getEmail());
		LOGGER.debug("delete Emailid ----" + ownerDetails.getEmail());
		/**
		 * Delete Job owner from OpenAM
		 */

		// boolean isDeleted = OpenAMEUtility.openAMDeleteUser(ownerDetails
		// .getEmail());
		// LOGGER.info("Open AM :Employee add owner User is created!" +
		// isDeleted);
		// Ends OpenAM code

		boolean bDelete = false;
		try {
			hibernateTemplateTracker.delete(ownerDetails);
			/*
			 * ownerDetails.setDeleteDt(new Timestamp(new Date().getTime()));
			 * hibernateTemplateTracker.save(ownerDetails);
			 */
			// update Delete date in Advence pass

			if (null != webMembershipEmails && !webMembershipEmails.isEmpty()) {
				WebMembershipEmail webMembershipEmail = webMembershipEmails
						.get(0);
				WebMembership membership = hibernateTemplateAdvancePass.get(
						WebMembership.class, webMembershipEmail
								.getWebMembership().getWebMembershipID());
				webMembershipEmail.setDeleteDate(new Timestamp(new Date()
						.getTime()));
				membership.setSalt(null);
				membership.setEncryptPassword(null);
				hibernateTemplateAdvancePass.saveOrUpdate(membership);
				hibernateTemplateAdvancePass.saveOrUpdate(webMembershipEmail);
			}
			bDelete = true;

		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return bDelete;
	}

	/**
	 * This method is called to update the Job Owner
	 * 
	 * @param accessPermissionDTOList
	 * @return update status
	 */
	@Override
	public boolean updateJobOwner(
			List<ManageAccessPermissionDTO> accessPermissionDTOList) {
		LOGGER.debug("Update Job Owners");
		try {
			for (ManageAccessPermissionDTO accessPermissionDTO : accessPermissionDTOList) {
				AdmUserRole admUserRole = new AdmUserRole();
				AdmUserFacility admUserFacility = (AdmUserFacility) hibernateTemplateCareers
						.find("from AdmUserFacility af where af.id.userId ="
								+ accessPermissionDTO.getOwnerId()).get(0);
				List<AdmUserRole> admUserRoleList = new ArrayList<AdmUserRole>();
				admUserRoleList = (List<AdmUserRole>) hibernateTemplateCareers
						.find("from AdmUserRole a where a.id.userId=?",
								accessPermissionDTO.getOwnerId());
				if (null != admUserRoleList && !admUserRoleList.isEmpty()) {
					admUserRole = admUserRoleList.get(0);
				}
				if (null != admUserFacility && null != admUserRole) {
					 updateRole(
							accessPermissionDTO, admUserRole, admUserFacility);
				}
			}

		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return true;
	}

	/**
	 * Method to update the role in AdmUserFacility
	 * @param accessPermissionDTO
	 * @param admUserRole
	 * @param admUserFacility
	 * @return
	 */
	private AdmUserFacility updateRole(
			ManageAccessPermissionDTO accessPermissionDTO,
			AdmUserRole admUserRole, AdmUserFacility admUserFacility) {
	
		AdmUserFacility admUserFacilityNew = (AdmUserFacility) hibernateTemplateCareers
				.load(AdmUserFacility.class,
						admUserFacility.getFacilityPK());

		if (null != admUserRole.getRolePK()
				&& admUserRole.getRolePK().getUserId() > 0) {
			AdmUserRole admUserRoleNew = (AdmUserRole) hibernateTemplateCareers
					.load(AdmUserRole.class,
							admUserRole.getRolePK());
			if (admUserRole.getRolePK().getRoleId() != accessPermissionDTO
					.getTypeOfAccess()) {
				// update the role in AdmUserRole
				Query updateAdmUserRole = hibernateTemplateCareers
						.getSessionFactory()
						.getCurrentSession()
						.createSQLQuery(
								" { call UpdateAdmUserRole(?,?,?) }");
				updateAdmUserRole.setInteger(0,
						accessPermissionDTO.getTypeOfAccess()); 
				updateAdmUserRole.setInteger(1, admUserRoleNew
						.getRolePK().getRoleId()); 
				updateAdmUserRole.setInteger(2, admUserRoleNew
						.getRolePK().getUserId());
				// update the role in AdmUserFacility
				Query updateAdmFacilityUserRole = hibernateTemplateCareers
						.getSessionFactory()
						.getCurrentSession()
						.createSQLQuery(
								" { call UpdateAdmUserFacility(?,?,?,?) }");
				updateAdmFacilityUserRole.setInteger(0,
						accessPermissionDTO.getTypeOfAccess()); 
				updateAdmFacilityUserRole.setInteger(1,
						admUserFacilityNew.getFacilityPK()
								.getRoleId()); 
				updateAdmFacilityUserRole.setInteger(2,
						admUserFacilityNew.getFacilityPK()
								.getUserId());
				updateAdmFacilityUserRole.setInteger(3,
						admUserFacilityNew.getFacilityPK()
								.getFacilityId());

				updateAdmFacilityUserRole.executeUpdate();
				updateAdmUserRole.executeUpdate();
			}
			LOGGER.debug("Updated Job Owners Role To:"
					+ accessPermissionDTO.getTypeOfAccess());
		}
		return admUserFacilityNew;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageAccessPermissionDAO#getJobOwnerList(int, int)
	 */
	@Override
	public List<ManageAccessPermissionDTO> getJobOwnerList(int facilityId,
			int userId) {

		try {
			List<MerUser> merUsers = new ArrayList<MerUser>();
			List<AdmFacility> facilityList = new ArrayList<AdmFacility>();
			List<Integer> roleId = new ArrayList<Integer>();
			List<AdmFacility> admFacilityList = hibernateTemplateCareers.find(
					"from AdmFacility adm where adm.facilityParentId=?",
					facilityId);

			facilityList.addAll(admFacilityList);
			for (AdmFacility facility : facilityList) {
				Object[] inputs = { facility.getFacilityId() };
				List<AdmUserFacility> admUsersList = hibernateTemplateCareers
						.find("from AdmUserFacility admFacility where admFacility.id.facilityId=?",
								inputs);
				if (null != admUsersList && !admUsersList.isEmpty()) {
					AdmUserFacility admUserFacility = admUsersList.get(0);
					List<MerUser> merUserList = hibernateTemplateTracker
							.find("from MerUser user where user.userId=? and user.deleteDt is null",
									admUserFacility.getFacilityPK().getUserId());
					merUsers.addAll(merUserList);

				}

			}
			for (MerUser merUser : merUsers) {
				List<AdmUserRole> admUserRolesList = new ArrayList<AdmUserRole>();
				admUserRolesList = (List<AdmUserRole>) hibernateTemplateCareers
						.find("from AdmUserRole a where a.id.userId=?",
								merUser.getUserId());
				AdmUserRole admUserRole = admUserRolesList.get(0);
				roleId.add(admUserRole.getRolePK().getRoleId());

			}
			return empHelper.transformAdmFacilityToManageAccessPermissionDTO(
					merUsers, roleId);

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;

	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageAccessPermissionDAO#getUserListByEmail(java.lang.String)
	 */
	@Override
	public UserDTO getUserListByEmail(String email) {
		MerUser user = null;
		try {
			if (!StringUtils.isEmptyOrWhitespaceOnly(email)) {

				List<MerUser> usersList = hibernateTemplateTracker.find(
						VERIFY_EMAIL, email);

				if (null != usersList && !usersList.isEmpty()) {
					user = usersList.get(0);

				}

			}
		} catch (Exception exception) {
			LOGGER.error(exception);
		}
		return empHelper.transformMerUserToUserDTO(user);

	}

}
