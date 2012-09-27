package com.advanceweb.afc.jb.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.admin.helper.AdminConversionHelper;
import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.data.entities.AdmInventoryDetail;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.mysql.jdbc.StringUtils;

@Transactional
@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO {

	private static final Logger LOGGER = Logger.getLogger("AdminDAOImpl.class");

	@Autowired
	AdminConversionHelper adminConversionHelper;

	private static final String GET_EMAIL = "from MerUser e where e.email = ?";
	private static final String USER_ROLE = "from AdmUserRole aur where aur.rolePK.userId = ?";
	private static final String FACILITY_ID = "from AdmUserFacility auf where auf.facilityPK.userId =?";
	private static final String ADM_FACILITY = "from AdmFacility af where af.facilityId=?";
	private static final String VALIDATE_ADMIN = "from MerUser e where e.email=? and e.password=?";
	private static final String VALIDATE_ADM_USERID = "from AdmFacility af where af.adminUserId =?";
	private static final String GET_ADM_FACILITY_BY_NS_ID = "from AdmFacility af1 where af1.nsCustomerID =?";
	private static final String GET_NS_ID_BY_COMPNAME = "from AdmFacility af1 where af1.name =?";
	private static final String GET_USERID_BY_FAC_ID = "from AdmUserFacility auf1 where auf1.facilityPK.facilityId=?";
	private static final String GET_FACILITY_CONTACT_BY_FAC_ID = "from AdmFacilityContact ac where ac.admFacility.facilityId = ? and ac.contactType='PRIMARY'";

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

	@SuppressWarnings("unchecked")
	@Override
	public boolean validateEmail(String email) {
		boolean status = false;
		try {
			if (!StringUtils.isEmptyOrWhitespaceOnly(email)) {
				List<MerUser> usersList = hibernateTemplateTracker.find(
						GET_EMAIL, email);

				if (null != usersList && !usersList.isEmpty()) {
					MerUser user = usersList.get(0);
					List<AdmUserRole> useList = hibernateTemplateCareers.find(
							USER_ROLE, user.getUserId());
					if (null != useList && !useList.isEmpty()) {
						if (useList.get(0).getAdmRole().getRoleId() == 3) {
							status = true;
						}
					}
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean validateAdminCredentials(String email, String password) {
		boolean status = false;
		try {
			if (!StringUtils.isEmptyOrWhitespaceOnly(email)
					&& !StringUtils.isEmptyOrWhitespaceOnly(password)) {
				List<MerUser> usersList = hibernateTemplateTracker.find(
						VALIDATE_ADMIN, email, password);

				if (null != usersList && !usersList.isEmpty()) {
					MerUser user = usersList.get(0);
					List<AdmUserRole> useList = hibernateTemplateCareers.find(
							USER_ROLE, user.getUserId());
					if (null != useList && !useList.isEmpty()) {
						if (useList.get(0).getAdmRole().getRoleId() == 1) {
							status = true;
//							return (null != useList ? true : false);
						}
					}
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean impersonateUser(AdminDTO adminDTO) {
		boolean status = true;
		try {
			// getting Admin User Id
			List<MerUser> usersList = hibernateTemplateTracker.find(GET_EMAIL,
					adminDTO.getUserEmail());
			int admUserId = 0;
			if (null != usersList && !usersList.isEmpty()) {
				MerUser user = usersList.get(0);
				admUserId = user.getUserId();
			}
			// getting user Id for emp / agency
			List<MerUser> usersList1 = hibernateTemplateTracker.find(GET_EMAIL,
					adminDTO.getEmpOrAgencyEmail());
			int facilityId = 0;
			AdmUserFacility facility = null;
			AdmFacility admfacility=null;
			if (null != usersList1 && !usersList1.isEmpty()) {
				MerUser user1 = usersList1.get(0);
				List<AdmUserFacility> facilityList = hibernateTemplateCareers
						.find(FACILITY_ID, user1.getUserId());
				if (null != facilityList && !facilityList.isEmpty()) {
					facility = facilityList.get(0);
					facilityId = facility.getFacilityPK().getFacilityId();
				}
				//hibernateTemplateCareers.evict(facilityList);
				 admfacility = facilityList.get(0).getAdmFacility();
			}
			
//			List<AdmFacility> admFacilityList = hibernateTemplateCareers.find(
//					ADM_FACILITY, facilityId);
			//AdmFacility admfacility = //admFacilityList.get(0);
					//AdmFacility admfacility = facilityList.get(0).getAdmFacility();
			
			List<AdmFacility> admList = hibernateTemplateCareers.find(
					VALIDATE_ADM_USERID, admUserId);
			admfacility.setAdminUserId(admUserId);
			if (!admList.isEmpty()) {
				AdmFacility fac = admList.get(0);
				fac.setAdminUserId(0);
				hibernateTemplateCareers.update(fac);
				hibernateTemplateCareers.saveOrUpdate(admfacility);
			} else {		
				hibernateTemplateCareers.saveOrUpdate(admfacility);
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return status;

	}

	@SuppressWarnings("unchecked")
	@Override
	public EmpSearchDTO validateCompName(String empList) {
		EmpSearchDTO dto = new EmpSearchDTO();
		boolean status = false;
		try {
			if (!empList.isEmpty()) {
				List<AdmFacility> usersList = hibernateTemplateCareers.find(
						GET_NS_ID_BY_COMPNAME, empList);

				if (null != usersList && !usersList.isEmpty()) {
					AdmFacility user = usersList.get(0);
					int nsId = user.getNsCustomerID();
					dto.setNsId(nsId);
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return dto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean validateNetSuitId(int nsId) {
		boolean status = false;
		try {
			if (nsId != 0) {
				List<AdmFacility> usersList = hibernateTemplateCareers.find(
						GET_ADM_FACILITY_BY_NS_ID, nsId);

				if (null != usersList && !usersList.isEmpty()) {
					AdmFacility user = usersList.get(0);
					if (null != user) {
						status = true;
					}
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EmpSearchDTO getUserIdAndFacilityId(int nsId) {
		EmpSearchDTO dto = new EmpSearchDTO();
		int facId = 0;
		List<AdmFacility> facility = hibernateTemplateCareers.find(
				GET_ADM_FACILITY_BY_NS_ID, nsId);
		if (facility != null) {
			AdmFacility af = facility.get(0);
			facId = af.getFacilityId();
			dto.setFacilityId(facId);
		}
		List<AdmUserFacility> userFacility = hibernateTemplateCareers.find(
				GET_USERID_BY_FAC_ID, facId);
		if (userFacility != null) {
			AdmUserFacility facilityList = userFacility.get(0);
			dto.setUserId(facilityList.getFacilityPK().getUserId());
		}
		return dto;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveModifiedData(
			List<JobPostingInventoryDTO> searchedJobsDTOs) {

		JobPostingInventoryDTO jobPostInvDTO = new JobPostingInventoryDTO();
		try {
			for (int i = 0; i < searchedJobsDTOs.size(); i++) {
				jobPostInvDTO = (JobPostingInventoryDTO) searchedJobsDTOs
						.get(i);
				AdmInventoryDetail searchResults = (AdmInventoryDetail) hibernateTemplateCareers
						.get(AdmInventoryDetail.class,
								jobPostInvDTO.getInvDetailId());
				searchResults.setAvailableqty(jobPostInvDTO.getAvailableQty());
				hibernateTemplateCareers.saveOrUpdate(searchResults);
			}
		} catch (HibernateException e) {
			LOGGER.error("ERROR" + e);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmpSearchDTO> getEmpdataByNetSuiteId(int nsId) {
		List<EmpSearchDTO> emplist = null;
		try {
			if (nsId != 0) {
				List<AdmFacility> usersList = hibernateTemplateCareers.find(
						GET_ADM_FACILITY_BY_NS_ID, nsId);
				emplist = adminConversionHelper.convertEntityTodDTO(usersList);
			}
		} catch (HibernateException e) {
			LOGGER.info("ERROR" + e);
		}
		return emplist;
	}

	/**
	 * This method to get job posting inventory details
	 * 
	 * @param userId
	 * @param facilityId
	 * @return JobPostingInventoryDTO
	 */
	public List<JobPostingInventoryDTO> getInventoryDetails(int userId,
			int facilityId) {
		Query getInventoryData = hibernateTemplateCareers.getSessionFactory()
				.getCurrentSession()
				.createSQLQuery(" { call AdminGetInventoryDetails(?) }");
		getInventoryData.setInteger(0, facilityId);
		List<?> invetoryDeatil = getInventoryData.list();
		Iterator<?> iterator = invetoryDeatil.iterator();
		List<JobPostingInventoryDTO> inventoryDTOs = new ArrayList<JobPostingInventoryDTO>();
		while (iterator.hasNext()) {
			JobPostingInventoryDTO dto = new JobPostingInventoryDTO();
			Object[] row = (Object[]) iterator.next();
			BigDecimal qty = (BigDecimal) row[4];
			BigDecimal availqty = (BigDecimal) row[5];
			dto.setProductId((Integer) row[0]);
			dto.setProductType((String) row[1]);
			dto.setJbType((String) row[2]);
			dto.setAddon((String) row[3]);
			dto.setQuantity(qty.intValue());
			dto.setAvailableQty(availqty.intValue());
			dto.setInvDetailId((Integer) row[6]);
			inventoryDTOs.add(dto);
		}
		return inventoryDTOs;
	}

	@SuppressWarnings("unchecked")
	public void saveFacility(int nsId) {
		List<AdmFacility> usersList = hibernateTemplateCareers.find(
				GET_ADM_FACILITY_BY_NS_ID, nsId);
		if (!usersList.isEmpty() && usersList != null) {
			AdmFacility facility = usersList.get(0);
			int facilityId = facility.getFacilityId();
			List<AdmFacilityContact> faciCont = hibernateTemplateCareers.find(
					GET_FACILITY_CONTACT_BY_FAC_ID, facilityId);

			AdmFacilityContact contact = faciCont.get(0);
			AdmFacility facility2 = new AdmFacility();
			facility2.setFacilityType(MMJBCommonConstants.FACILITY);
			facility2.setFacilityParentId(facility.getFacilityId());
			facility2.setName(facility.getName());
			facility2.setStreet(facility.getStreet());
			facility2.setCity(facility.getCity());
			facility2.setState(facility.getState());
			facility2.setPostcode(facility.getPostcode());
			facility2.setCountry(facility.getCountry());
			facility2.setEmail(facility.getEmail());
			facility2.setCreateUserId(facility.getCreateUserId());
			facility2.setNsCustomerID(facility.getNsCustomerID());
			facility2.setCreateDt(new Date());
			// AdmFacility facility3 = (AdmFacility)
			// hibernateTemplateCareers.save(facility2);
			hibernateTemplateCareers.save(facility2);
			AdmFacilityContact admFaclityContact = new AdmFacilityContact();
			admFaclityContact.setAdmFacility(facility2);
			admFaclityContact.setFirstName(contact.getFirstName());
			admFaclityContact.setMiddleName(contact.getLastName());
			admFaclityContact.setContactType(MMJBCommonConstants.PRIMARY);
			admFaclityContact.setLastName(contact.getLastName());
			admFaclityContact.setJobTitle(contact.getJobTitle());
			admFaclityContact.setCompany(contact.getCompany());
			admFaclityContact.setStreet(contact.getStreet());
			admFaclityContact.setState(contact.getState());
			admFaclityContact.setPostcode(contact.getPostcode());
			admFaclityContact.setCity(contact.getCity());
			admFaclityContact.setStreet2(contact.getStreet2());
			admFaclityContact.setCountry(contact.getCountry());
			admFaclityContact.setEmail(contact.getEmail());
			admFaclityContact.setPhone(contact.getPhone());
			admFaclityContact.setPhone2(contact.getPhone2());
			admFaclityContact.setCreateDt(new Date());
			admFaclityContact.setActive(1);
			hibernateTemplateCareers.save(admFaclityContact);

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveEditFacilityGroup(EmpSearchDTO dto) {
		int nsId = dto.getNsId();
		boolean healthSys = dto.isHealthSystem();
		try {
			if (healthSys) {
				List<AdmFacility> usersList = hibernateTemplateCareers.find(
						GET_ADM_FACILITY_BY_NS_ID, nsId);
				if (usersList != null && !usersList.isEmpty()) {
					AdmFacility facility = usersList.get(0);
					facility.setFacilityType(MMJBCommonConstants.FACILITY_GROUP);
					facility.setFacilityParentId(Integer
							.parseInt(MMJBCommonConstants.ZERO));
					hibernateTemplateCareers.update(facility);
					saveFacility(nsId);
				}
			} else {
				List<AdmFacility> usersList = hibernateTemplateCareers.find(
						GET_ADM_FACILITY_BY_NS_ID, nsId);
				for (AdmFacility admFacility : usersList) {

					if (admFacility.getFacilityType().equalsIgnoreCase(
							MMJBCommonConstants.FACILITY_GROUP)) {
						admFacility
								.setFacilityType(MMJBCommonConstants.FACILITY);
						hibernateTemplateCareers.update(admFacility);
					} else {
						// get facility contact by facility & delete
						List<AdmFacilityContact> facilityContacts = hibernateTemplateCareers
								.find(GET_FACILITY_CONTACT_BY_FAC_ID,
										admFacility.getFacilityId());
						AdmFacilityContact facilityContact = facilityContacts
								.get(0);
						hibernateTemplateCareers.delete(facilityContact);
						hibernateTemplateCareers.delete(admFacility);

					}
				}
			}
		} catch (HibernateException e) {
			LOGGER.error("ERROR" + e);
		}
		return false;
	}

}
