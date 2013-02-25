/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.admin.helper.AdminConversionHelper;
import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.data.entities.AdmFacilityInventory;
import com.advanceweb.afc.jb.data.entities.AdmInventoryDetail;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.JpJobTypeCombo;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.WebMembership;
import com.advanceweb.afc.jb.data.entities.WebMembershipEmail;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

@Transactional
@Repository("adminDAO")
@SuppressWarnings("unchecked")
public class AdminDAOImpl implements AdminDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("AdminDAOImpl.class");

	/** The admin conversion helper. */
	@Autowired
	private AdminConversionHelper adminConversionHelper;

	/** The Constant GET_EMAIL. */
	private static final String GET_EMAIL = "from MerUser e where e.email = ?";
	
	/** The Constant USER_ROLE. */
	private static final String USER_ROLE = "from AdmUserRole aur where aur.rolePK.userId = ?";
	
	/** The Constant FACILITY_ID. */
	private static final String FACILITY_ID = "from AdmUserFacility auf where auf.facilityPK.userId =?";
	
	/** The Constant VALIDATE_ADMIN. */
	private static final String VALIDATE_ADMIN = "from MerUser e where e.email=?";
	
	/** The Constant VALIDATE_ADM_USERID. */
	private static final String VALIDATE_ADM_USERID = "from AdmFacility af where af.adminUserId =?";
	
	/** The Constant GET_ADM_FACILITY_BY_NS_ID. */
	private static final String GET_ADM_FACILITY_BY_NS_ID = "from AdmFacility af1 where af1.nsCustomerID =? and af1.facilityType in ('FACILITY','FACILITY_GROUP') and af1.deleteDt is NULL";
	
	/** The Constant GET_NS_ID_BY_COMPNAME. */
	private static final String GET_NS_ID_BY_COMPNAME = "from AdmFacility af1 where af1.name =? and af1.facilityType in ('FACILITY','FACILITY_GROUP') and af1.deleteDt is NULL";
	
	/** The Constant GET_USERID_BY_FAC_ID. */
	private static final String GET_USERID_BY_FAC_ID = "from AdmUserFacility auf1 where auf1.facilityPK.facilityId=?";
	
	/** The Constant GET_FACILITY_CONTACT_BY_FAC_ID. */
	private static final String GET_FACILITY_CONTACT_BY_FAC_ID = "from AdmFacilityContact ac where ac.admFacility.facilityId = ? and ac.contactType='PRIMARY'";

	/** The hibernate template tracker. */
	private HibernateTemplate hibernateTemplateTracker;

	/** The hibernate template careers. */
	private HibernateTemplate hibernateTemplateCareers;
	
	/** The hibernate template advance pass. */
	private HibernateTemplate hibernateTemplateAdvancePass;

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
			SessionFactory sessionFactory, SessionFactory sessionFactoryAdvancePass) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);
		this.hibernateTemplateAdvancePass = new HibernateTemplate(sessionFactoryAdvancePass);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.dao.AdminDAO#validateEmail(java.lang.String)
	 */
	@Override
	public boolean validateEmail(String email) {
		boolean status = false;
		try {
//			if (!StringUtils.isEmptyOrWhitespaceOnly(email)) {
				List<MerUser> usersList = hibernateTemplateTracker.find(
						GET_EMAIL, email);
				if (null != usersList && !usersList.isEmpty()) {
					MerUser user = usersList.get(0);
					List<AdmUserRole> useList = hibernateTemplateCareers.find(
							USER_ROLE, user.getUserId());
					if (null != useList && !useList.isEmpty() && useList.get(0).getAdmRole().getRoleId() == 3) {
//						if (useList.get(0).getAdmRole().getRoleId() == 3) {
							status = true;
//						}
					}
				}
//			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return status;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.dao.AdminDAO#validateAdminCredentials(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validateAdminCredentials(String email, String password) {
		boolean status = false;
		boolean advancePassValidation=false;
		try {
			
				WebMembershipEmail webMembershipEmail = (WebMembershipEmail) DataAccessUtils
						.uniqueResult(hibernateTemplateAdvancePass.find(
								"from WebMembershipEmail where email = ?", email));
				if (webMembershipEmail != null) {
					WebMembership membership = hibernateTemplateAdvancePass.get(
							WebMembership.class, webMembershipEmail
									.getWebMembership().getWebMembershipID());
					if (membership != null) {
						advancePassValidation=membership.getPassword().equals(password)?true:false;
					}
				}
			if(advancePassValidation){
				List<MerUser> usersList = hibernateTemplateTracker.find(
						VALIDATE_ADMIN, email);

				if (null != usersList && !usersList.isEmpty()) {
					MerUser user = usersList.get(0);
					List<AdmUserRole> useList = hibernateTemplateCareers.find(
							USER_ROLE, user.getUserId());
					if (null != useList && !useList.isEmpty() && useList.get(0).getAdmRole().getRoleId() == 1) {
							status = true;
						}
					}
			}
				
		} catch (HibernateException e) {
			LOGGER.error("Exception while Authenticating the Admin user"+e.getMessage());
		}
		return status;
	}


	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.dao.AdminDAO#impersonateUser(com.advanceweb.afc.jb.common.AdminDTO)
	 */
	@Override
	public boolean impersonateUser(AdminDTO adminDTO) {
		boolean status = true;
		try {
			List<MerUser> usersList1 = hibernateTemplateTracker.find(GET_EMAIL,
					adminDTO.getEmpOrAgencyEmail());
			AdmFacility admfacility = null;
			if (null != usersList1 && !usersList1.isEmpty()) {
				MerUser user1 = usersList1.get(0);
				List<AdmUserFacility> facilityList = hibernateTemplateCareers
						.find(FACILITY_ID, user1.getUserId());
				if (null != facilityList && !facilityList.isEmpty()) {
					admfacility = facilityList.get(0).getAdmFacility();
				}
			}
			List<AdmFacility> admList = hibernateTemplateCareers.find(
					VALIDATE_ADM_USERID, adminDTO.getAdminUserId());
			admfacility.setAdminUserId(adminDTO.getAdminUserId());
			if (!admList.isEmpty()) {
				AdmFacility fac = admList.get(0);
				fac.setAdminUserId(0);
				hibernateTemplateCareers.update(fac);
			}
			hibernateTemplateCareers.saveOrUpdate(admfacility);

		} catch (Exception e) {
			LOGGER.error(e);
			return false;
		}
		return status;

	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.dao.AdminDAO#validateCompName(java.lang.String)
	 */
	@Override
	public EmpSearchDTO validateCompName(String empList) {
		EmpSearchDTO dto = new EmpSearchDTO();
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.dao.AdminDAO#validateNetSuitId(int)
	 */
	@Override
	public boolean validateNetSuitId(int nsId) {
		boolean status = false;
		try {
			if (nsId != 0) {
				List<AdmFacility> usersList = hibernateTemplateCareers.find(
						GET_ADM_FACILITY_BY_NS_ID, nsId);

				if (null != usersList && !usersList.isEmpty() &&  usersList.get(0) != null) {
//					AdmFacility user = usersList.get(0);
//					if (null != user) {
						status = true;
//					}
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return status;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.dao.AdminDAO#getUserIdAndFacilityId(int)
	 */
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
			dto.setCompanyName(af.getName());
		}
		List<AdmUserFacility> userFacility = hibernateTemplateCareers.find(
				GET_USERID_BY_FAC_ID, facId);
		if (null != userFacility && !userFacility.isEmpty()) {
			AdmUserFacility facilityList = userFacility.get(0);
			dto.setUserId(facilityList.getFacilityPK().getUserId());
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.dao.AdminDAO#saveModifiedData(java.util.List)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveModifiedData(
			List<JobPostingInventoryDTO> searchedJobsDTOs) {

		JobPostingInventoryDTO jobPostInvDTO = new JobPostingInventoryDTO();
		try {
			for (int i = 0; i < searchedJobsDTOs.size(); i++) {
				jobPostInvDTO = (JobPostingInventoryDTO) searchedJobsDTOs
						.get(i);
				List<AdmInventoryDetail> searchResults = hibernateTemplateCareers
						.find("from AdmInventoryDetail a where a.invDetailId =?",
								jobPostInvDTO.getInvDetailId());
				AdmInventoryDetail list = searchResults.get(0);
				list.setAvailableqty(jobPostInvDTO.getAvailableQty());
				hibernateTemplateCareers.saveOrUpdate(list);
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage() , e);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.dao.AdminDAO#getEmpdataByNetSuiteId(int)
	 */
	@Override
	public List<EmpSearchDTO> getEmpdataByNetSuiteId(int nsId) {
		List<EmpSearchDTO> emplist = null;
		try {
			if (nsId != 0) {
				List<AdmFacility> usersList = hibernateTemplateCareers.find(
						GET_ADM_FACILITY_BY_NS_ID, nsId);
				// facility without job owners
				List<AdmFacility> facilityList = new ArrayList<AdmFacility>();
				boolean isFacilityGrp = usersList.get(0).getFacilityType()
						.equalsIgnoreCase(MMJBCommonConstants.FACILITY_GROUP);
				for (AdmFacility adm : usersList) {
					if (adm.getAdmUserFacilities().isEmpty()) {
						facilityList.add(adm);
					}
				}
				if(!isFacilityGrp){
					facilityList.addAll(usersList);
				}
				emplist = adminConversionHelper.convertEntityTodDTO(facilityList);
				if(isFacilityGrp){
					emplist.get(0).setFacilityType(MMJBCommonConstants.FACILITY_GROUP);
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage(), e);
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
			dto.setInventoryId((Integer) row[7]);
			inventoryDTOs.add(dto);
		}
		return inventoryDTOs;
	}

	/**
	 * Save facility.
	 *
	 * @param nsId the ns id
	 */
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

	/**
	 * Method helps to edit the facility type changes by admin
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveEditFacilityGroup(EmpSearchDTO dto) {
		int nsId = dto.getNsId();
		boolean healthSys = dto.isHealthSystem();
		try {
			if (healthSys) {
				// convert Facility to Facility Group
				AdmFacility usersList = (AdmFacility) hibernateTemplateCareers
						.find(GET_ADM_FACILITY_BY_NS_ID, nsId).get(0);

				AdmFacility childFacility = new AdmFacility();
				childFacility.setEmail(usersList.getEmail());
				childFacility.setStreet(usersList.getStreet());
				childFacility.setCity(usersList.getCity());
				childFacility.setState(usersList.getState());
				childFacility.setPostcode(usersList.getPostcode());
				childFacility.setCountry(usersList.getCountry());
				childFacility.setName(usersList.getName());
				childFacility.setFacilityType(MMJBCommonConstants.FACILITY);
				childFacility.setFacilityParentId(usersList.getFacilityId());
				childFacility.setNsCustomerID(usersList.getNsCustomerID());
				childFacility.setCreateDt(new Date());
				childFacility.setCreateUserId(usersList.getCreateUserId());
				hibernateTemplateCareers.save(childFacility);
				AdmFacilityContact childFacilityContact = new AdmFacilityContact();
				AdmFacilityContact contact = usersList.getAdmFacilityContacts()
						.get(0);
				childFacilityContact.setEmail(contact.getEmail());
				childFacilityContact.setStreet(contact.getStreet());
				childFacilityContact.setCity(contact.getCity());
				childFacilityContact.setState(contact.getState());
				childFacilityContact.setPostcode(contact.getPostcode());
				childFacilityContact.setCountry(contact.getCountry());
				childFacilityContact.setFirstName(contact.getFirstName());
				childFacilityContact.setLastName(contact.getLastName());
				childFacilityContact.setMiddleName(contact.getMiddleName());
				childFacilityContact.setCompany(contact.getCompany());
				childFacilityContact.setJobTitle(contact.getJobTitle());
				childFacilityContact.setPhone(contact.getPhone());
				childFacilityContact.setPhone2(contact.getPhone2());
				childFacilityContact
						.setContactType(MMJBCommonConstants.PRIMARY);
				childFacilityContact.setCreateDt(new Date());
				// childFacilityContact.setEmail(dto.getMerUserDTO().getEmailId());
				childFacilityContact.setActive(1);
				childFacilityContact.setAdmFacility(childFacility);
				hibernateTemplateCareers.save(childFacilityContact);
				// Update the facility to facility group
				usersList.setFacilityType(MMJBCommonConstants.FACILITY_GROUP);
				hibernateTemplateCareers.saveOrUpdate(usersList);

			} else {
				// Get the facility group details
				List<AdmFacility> usersList = hibernateTemplateCareers.find(
						GET_ADM_FACILITY_BY_NS_ID, nsId);// by date

				// delete the sub facilities of parent facility & contacts by
				// ignoring job owners
				int size = usersList.size();
				List<AdmFacility> subFacility = usersList.subList(1, size);
				AdmFacility mainFacilities = usersList.get(0);
				for (AdmFacility admFacility : subFacility) {
					List<AdmFacilityContact> facilityContacts = hibernateTemplateCareers
							.find(GET_FACILITY_CONTACT_BY_FAC_ID,
									admFacility.getFacilityId());
					for (AdmFacilityContact admFacilityContact : facilityContacts) {
						admFacilityContact.setDeleteDt(new Date());
						hibernateTemplateCareers
								.saveOrUpdate(admFacilityContact);
					}
					admFacility.setDeleteDt(new Date());
					hibernateTemplateCareers.saveOrUpdate(admFacility);
				}

				// convert Facility Group to Facility
				mainFacilities.setFacilityType(MMJBCommonConstants.FACILITY);
				hibernateTemplateCareers.saveOrUpdate(mainFacilities);
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.dao.AdminDAO#listJobPostings()
	 */
	@Override
	public List<DropDownDTO> listJobPostings() {
		
		List<DropDownDTO> dropDownDTOList= new ArrayList<DropDownDTO>();
		try{
			List<JpJobTypeCombo> jobTypeComboList = hibernateTemplateCareers.find("from JpJobTypeCombo");
			DropDownDTO dropDownDTO = null;
			for(JpJobTypeCombo jobTypeCombo :jobTypeComboList){
				dropDownDTO = new DropDownDTO();
				
				dropDownDTO.setOptionId(String.valueOf(jobTypeCombo.getComboId()));
				dropDownDTO.setOptionName(jobTypeCombo.getAddons());
				dropDownDTO.setNetSuiteId(String.valueOf(jobTypeCombo.getNetSuiteId()));
				dropDownDTOList.add(dropDownDTO);
			}
		}
		catch(Exception e){
			LOGGER.error("ERROR :" + e);
		}
		return dropDownDTOList;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.admin.dao.AdminDAO#updateJobPostInventory(int, int, int)
	 */
	@Override
	public boolean updateJobPostInventory(int facilityId, int jobTypeId,
			int quantity) {
		try {
			AdmFacilityInventory admFacilityInventory = new AdmFacilityInventory();
			admFacilityInventory.setCreateDt(new Date());
			admFacilityInventory.setFacilityId(facilityId);
			AdmInventoryDetail admInventoryDetail = transformToAdmInventoryDetail(
					admFacilityInventory, jobTypeId, quantity);
			admFacilityInventory.setAdmInventoryDetail(admInventoryDetail);
			hibernateTemplateCareers.save(admFacilityInventory);
		} catch (Exception e) {
			LOGGER.error(e);
			return false;
		}
		return true;
	}
	
	/**
	 * @param admFacilityInventory
	 * @param jobPostingPlanDTO
	 * @return admInventoryDetail
	 */
	private AdmInventoryDetail transformToAdmInventoryDetail(
			AdmFacilityInventory admFacilityInventory, int jobTypeId,
			int quantity) {
		AdmInventoryDetail admInventoryDetail = new AdmInventoryDetail();
		admInventoryDetail.setAdmFacilityInventory(admFacilityInventory);
		admInventoryDetail.setProductId(jobTypeId);
		admInventoryDetail.setProductType(MMJBCommonConstants.JOB_TYPE_COMBO);
		admInventoryDetail.setOrderQty(quantity);
		admInventoryDetail.setAvailableqty(quantity);

		return admInventoryDetail;
	}
	/**
	 * This method is used to get the list of the Facility whose name is
	 * matching with the given facility name
	 * 
	 * @param String
	 *            facilityName
	 * @return List<FacilityDTO>
	 */
	@Override
	public List<FacilityDTO> getFacilityNames(String employerName)
			throws JobBoardDataException {
		List<FacilityDTO> emplyrNamesList = new ArrayList<FacilityDTO>();
		try {

			List<AdmFacility> facility = hibernateTemplateCareers
					.find("from AdmFacility adm where adm.name like '"
							+ employerName
							+ "%' and adm.facilityType!='FACILITY_SYSTEM' group by nsCustomerID");
			for (AdmFacility adm : facility) {
				FacilityDTO dto = new FacilityDTO();
				dto.setName(adm.getName()+", "+adm.getNsCustomerID());
				dto.setFacilityId(adm.getFacilityId());
				emplyrNamesList.add(dto);
			}
		} catch (Exception e) {
			LOGGER.debug("Exception while getting the employer name" + e);
			throw new JobBoardDataException(
					"Error while fetching data from AdmFacility based on the facilityName"
							+ e);
		}
		return emplyrNamesList;
	}

	/**
	 * This method is used to get the details of the Facility depending on the
	 * facilityId
	 * 
	 * @param int facilityId
	 * @return FacilityDTO
	 */
	@Override
	public FacilityDTO getLinkedFacilityDetails(int facilityId)
			throws JobBoardDataException {

		FacilityDTO employerDetails;
		try {
			employerDetails = new FacilityDTO();
			AdmFacility facility = (AdmFacility) DataAccessUtils
					.uniqueResult(hibernateTemplateCareers
							.find("from AdmFacility admFacility where admFacility.facilityId=?",
									facilityId));
			if (facility != null) {
				employerDetails.setCity(facility.getCity());
				employerDetails.setStreet(facility.getStreet());
				employerDetails.setPostcode(facility.getPostcode());
				employerDetails.setState(facility.getState());
				employerDetails.setCountry(facility.getCountry());
				employerDetails.setFacilityId(facility.getFacilityId());
				employerDetails.setName(facility.getName());
				employerDetails.setNsCustomerID(facility.getNsCustomerID());
				for (AdmFacilityContact contact : facility
						.getAdmFacilityContacts()) {
					if (contact.getContactType().equals("PRIMARY")) {
						employerDetails.setPhone(contact.getPhone());
					}
				}
				/*
				 * employerDetails.setPhone(facility.getAdmFacilityContacts()
				 * .get(0).getPhone());
				 */
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			throw new JobBoardDataException(
					"Error while fetching data from AdmFacility based on the facilityId"
							+ e);
		}
		return employerDetails;
	}
}
