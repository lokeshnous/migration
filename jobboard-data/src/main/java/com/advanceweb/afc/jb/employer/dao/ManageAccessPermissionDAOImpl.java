package com.advanceweb.afc.jb.employer.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
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
import com.advanceweb.afc.jb.employer.helper.EmployerRegistrationConversionHelper;

/**
 * To save new job Owner * @author deviprasadm
 * 
 */
@SuppressWarnings("unchecked")
@Repository("manageAccessPermissionDAO")
public class ManageAccessPermissionDAOImpl implements ManageAccessPermissionDAO {
	private static final Logger LOGGER = Logger
			.getLogger(ManageAccessPermissionDAOImpl.class);
	private static final String FIND_ADM_FACILITY = "from AdmFacility where facilityId=?";

	private HibernateTemplate hibernateTemplateTracker;

	private HibernateTemplate hibernateTemplateCareers;
	@Autowired
	private EmployerRegistrationConversionHelper empHelper;

	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);

	}

	@Override
	public UserDTO createJobOwner(EmployerProfileDTO empDTO, int facilityIdP,
			int userIdp) {
		try {
			MerUser merUser = empHelper.transformMerUserDTOToMerUser(empDTO);
			if (null != merUser) {
				// saving employer credentials
				hibernateTemplateTracker.saveOrUpdate(merUser);
			}

			// saving the data in Adm_User_Role
			AdmUserRole userRole = new AdmUserRole();
			userRole.setCreateUserId(userIdp);
			userRole.setCreateDt(new Date());

			AdmUserRolePK admUserRolePK = new AdmUserRolePK();
			admUserRolePK.setUserId(merUser.getUserId());
			admUserRolePK.setRoleId(empDTO.getRollId());
			userRole.setRolePK(admUserRolePK);
			hibernateTemplateCareers.saveOrUpdate(userRole);

			// saving the data in adm_facility
			AdmFacility facilityP = (AdmFacility) hibernateTemplateCareers
					.find(FIND_ADM_FACILITY, facilityIdP).get(0);
			AdmFacility facility = new AdmFacility();

			facility.setFacilityType(MMJBCommonConstants.FACILITY);
			facility.setFacilityParentId(facilityIdP);
			facility.setCreateDt(new Date());
			facility.setCreateUserId(userIdp);
			if (facilityP != null) {
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

			}

			List<AdmFacilityContact> admFacilityContactP = facilityP
					.getAdmFacilityContacts();

			// saving the data in adm_facility_contact as per the logged in User
			List<AdmFacilityContact> admFacilityContactList = new ArrayList<AdmFacilityContact>();
			if (null != admFacilityContactP) {

				for (AdmFacilityContact contact : admFacilityContactP) {

					AdmFacilityContact admFacilityContact = new AdmFacilityContact();
					admFacilityContact.setCity(contact.getCity());
					admFacilityContact.setCompany(contact.getCompany());
					admFacilityContact.setCountry(contact.getCountry());
					admFacilityContact.setFirstName(contact.getFirstName());
					admFacilityContact.setStreet(contact.getStreet());
					admFacilityContact.setState(contact.getState());
					admFacilityContact.setPostcode(contact.getPostcode());
					admFacilityContact.setLastName(contact.getLastName());
					admFacilityContact.setMiddleName(contact.getMiddleName());
					admFacilityContact.setJobTitle(contact.getJobTitle());
					admFacilityContact.setPhone(contact.getPhone());
					admFacilityContact.setPhone2(contact.getPhone2());
					admFacilityContact.setContactType(contact.getContactType());
					admFacilityContact.setCreateDt(new Date());
					admFacilityContact.setEmail(contact.getEmail());
					admFacilityContact.setActive(contact.getActive());
					admFacilityContact.setCreateDt(new Date());
					admFacilityContact.setAdmFacility(facility);
					admFacilityContactList.add(admFacilityContact);

				}
			}

			facility.setAdmFacilityContacts(admFacilityContactList);
			hibernateTemplateCareers.save(facility);

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
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return null;
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
		boolean bDelete = false;
		try {
			ownerDetails.setDeleteDt(new Timestamp(new Date().getTime()));
			hibernateTemplateTracker.save(ownerDetails);
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
		LOGGER.info("Update Job Owners");
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
				if (null != admUserRoleList && admUserRoleList.size() > 0) {
					admUserRole = admUserRoleList.get(0);
				}
				if (null != admUserFacility && null != admUserRole) {
					// update the role in AdmUserFacility
					AdmUserFacility admUserFacilityNew = (AdmUserFacility) hibernateTemplateCareers
							.load(AdmUserFacility.class,
									admUserFacility.getFacilityPK());

					// update the role in AdmUserRole

					if (null != admUserRole.getRolePK()
							&& admUserRole.getRolePK().getUserId() > 0) {
						AdmUserRole admUserRoleNew = (AdmUserRole) hibernateTemplateCareers
								.load(AdmUserRole.class,
										admUserRole.getRolePK());
						if (admUserRole.getRolePK().getRoleId() != accessPermissionDTO
								.getTypeOfAccess()) {
							admUserFacilityNew.getFacilityPK().setRoleId(
									accessPermissionDTO.getTypeOfAccess());
							admUserRoleNew.getRolePK().setRoleId(
									accessPermissionDTO.getTypeOfAccess());

							hibernateTemplateCareers.saveOrUpdate(
									"AdmUserFacility", admUserFacilityNew);
							hibernateTemplateCareers.saveOrUpdate(
									"AdmUserRole", admUserRoleNew);

						}
						LOGGER.info("Updated Job Owners Role To:"
								+ accessPermissionDTO.getTypeOfAccess());
					}
				}
			}

		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return true;
	}

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

}
