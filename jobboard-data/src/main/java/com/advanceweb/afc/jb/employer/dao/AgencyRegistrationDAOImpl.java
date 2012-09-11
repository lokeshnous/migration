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
import com.advanceweb.afc.jb.common.AccountProfileDTO;
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
import com.advanceweb.afc.jb.user.helper.RegistrationConversionHelper;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:52 PM
 */
@Repository("agencyRegistrationDAO")
@SuppressWarnings("unchecked")
public class AgencyRegistrationDAOImpl implements AgencyRegistrationDAO {

	private static final String FIND_AGENCY_ROLE_ID = "from AdmRole role where role.name=?";
	private static final String REGISTRATION_ATTRIBS = "from MerProfileAttrib prof";
	@Autowired
	private AgencyRegistrationConversionHelper agencyHelper;

	@Autowired
	private RegistrationConversionHelper registrationConversionHelper;

	private HibernateTemplate hibernateTemplateTracker;

	private HibernateTemplate hibernateTemplateCareers;

	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);

	}

	private static final Logger LOGGER = Logger

	.getLogger(AgencyRegistrationDAOImpl.class);

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
			facility.setFacilityType(MMJBCommonConstants.FACILITY_GROUP);
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
			@SuppressWarnings("unchecked")
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
			@SuppressWarnings("unchecked")
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
			@SuppressWarnings("unchecked")
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
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean addEmployer(AccountProfileDTO accountDto,
			int agencyFacilityId, int userId) {
		AdmFacility facility = hibernateTemplateCareers.get(AdmFacility.class,
				accountDto.getFacilityId());
//		facility.setAdminUserId(2162);// TODO: REMOVE HARD CODE AGENCY USER ID
//		facility.setFacilityParentId(393);
		facility.setAdminUserId(userId);
		facility.setFacilityParentId(agencyFacilityId);
		hibernateTemplateCareers.update(facility);
		return true;
	}

	@Override
	public List<AdmFacility> getAssocEmployerNames(int userId,
			int agencyFacilityId) {
		@SuppressWarnings("unchecked")
//		List<AdmFacility> assocEmplyrs = hibernateTemplateCareers
//				.find("from AdmFacility where adminUserId=2162 and facilityParentId=393 and deleteUserId=0");
		List<AdmFacility> assocEmplyrs = hibernateTemplateCareers
				.find("from AdmFacility where adminUserId=" + userId
						+ " and facilityParentId=" + agencyFacilityId
						+ " and deleteUserId=0");

		return assocEmplyrs;
	}

	@Override
	public boolean saveEmployerDetails(AccountProfileDTO dto) {
		AdmFacility facility = hibernateTemplateCareers.get(AdmFacility.class,
				dto.getFacilityId());
		facility.setCity(dto.getCity());
		facility.setStreet(dto.getStreet());
		facility.setCountry(dto.getCountry());
		facility.setPostcode(dto.getZipCode());
		hibernateTemplateCareers.update(facility);
		return true;
	}

	@Override
	public boolean deleteAssocEmployer(String facilityId, int userId) {
		AdmFacility facility = hibernateTemplateCareers.get(AdmFacility.class,
				Integer.parseInt(facilityId));
		facility.setDeleteUserId(userId);
		Date deleteDt = new Timestamp(new Date().getTime());
		facility.setDeleteDt(deleteDt);
		hibernateTemplateCareers.update(facility);
		return true;

	}
}