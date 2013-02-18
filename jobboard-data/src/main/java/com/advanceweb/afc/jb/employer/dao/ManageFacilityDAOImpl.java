/**
 * 
 */
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

import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.ManageFacilityDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

/**
 * @author deviprasadm
 * 
 */

@SuppressWarnings("unchecked")
@Repository("manageFacilityDAO")
public class ManageFacilityDAOImpl implements ManageFacilityDAO {

	private static final Logger LOGGER = Logger
			.getLogger(ManageFacilityDAOImpl.class);
	private static final String FIND_ADM_FACILITY = "from AdmFacility where facilityId=? and facilityType='FACILITY_GROUP'";
	private static final String FIND_ADM_FACILITY_DETAILS = "from AdmFacility where facilityId=?";
	private static final String NONE = "None";
	
	private HibernateTemplate hibernateTemplateCareers;

	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);

	}

	@Override
	public ManageFacilityDTO getFacilityList(int facilityId, boolean isGroup)
			throws JobBoardDataException {
		LOGGER.info("Fetching Facility Details");
		ManageFacilityDTO facilityDTO = new ManageFacilityDTO();
		List<AdmFacility> admFacilityList;
		AdmFacility admFacility = null;
		if (isGroup) {
			admFacilityList = hibernateTemplateCareers.find(FIND_ADM_FACILITY,
					facilityId);
		} else {
			admFacilityList = hibernateTemplateCareers.find(
					FIND_ADM_FACILITY_DETAILS, facilityId);
		}
		if (null != admFacilityList && !admFacilityList.isEmpty()) {
			admFacility = admFacilityList.get(0);
		}
		if (null != admFacility) {
			facilityDTO.setFacilityId(admFacility.getFacilityId());
			facilityDTO.setFacilityName(admFacility.getName());
			facilityDTO.setFacilityType(admFacility.getFacilityType());
			facilityDTO.setFacilityCity(admFacility.getCity());
			facilityDTO.setFacilityCountry(admFacility.getCountry());
			facilityDTO.setFacilityState(admFacility.getState());
			facilityDTO.setFacilityStreet(admFacility.getStreet());
			if (null != admFacility.getAdmFacilityContacts()
					&& admFacility.getAdmFacilityContacts().size() > 0) {
				facilityDTO.setPhoneNumber(admFacility.getAdmFacilityContacts()
						.get(0).getPhone());
			}
			facilityDTO.setZipCode(admFacility.getPostcode());
			facilityDTO.setTemplateId(String.valueOf(admFacility
					.getTemplateId()));
		}

		return facilityDTO;
	}

	@Override
	public List<FacilityDTO> getFacilityListByGroup(int facilityId)
			throws JobBoardDataException {

		try {
			List<AdmFacility> facilityList = new ArrayList<AdmFacility>();

			List<AdmFacility> admFacilityList = hibernateTemplateCareers
					.find("from AdmFacility adm where adm.facilityParentId=? and deleteDt is null",
							facilityId);
			facilityList.addAll(admFacilityList);

			List<FacilityDTO> facilityDTOList = new ArrayList<FacilityDTO>();
			for (AdmFacility facility : admFacilityList) {
				FacilityDTO facilityDTO = new FacilityDTO();
				Object[] inputs = { facility.getFacilityId(), 5, 6 };
				List<AdmUserFacility> admUsersList = hibernateTemplateCareers
						.find("from AdmUserFacility admFacility where admFacility.id.facilityId=? and (admFacility.id.roleId!=? or admFacility.id.roleId!=?)",
								inputs);
				if (null == admUsersList || admUsersList.isEmpty()) {
					facilityDTO.setFacilityId(facility.getFacilityId());
					facilityDTO.setName(facility.getName());
					if (facility.getTemplateId() > 0) {
						List<Object> templateNames = hibernateTemplateCareers
								.find("select tnam.templateName from  JpTemplate tnam where tnam.templateId=? and tnam.deleteDt is null",
										facility.getTemplateId());
						if (null != templateNames && !templateNames.isEmpty()) {
							facilityDTO.setTemplateName(templateNames.get(0)
									.toString());
						} else {
							facilityDTO.setTemplateName(NONE);
						}
					}
					facilityDTOList.add(facilityDTO);
				}

			}
			return facilityDTOList;

		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public void createFacility(ManageFacilityDTO manageFacilityDTO,
			int facilityIdParent) throws JobBoardDataException {
		AdmFacility facilityP = (AdmFacility) hibernateTemplateCareers.find(
				FIND_ADM_FACILITY, facilityIdParent).get(0);
		AdmFacility facility = new AdmFacility();
		if (manageFacilityDTO.getFacilityId() > 0) {
			facility = hibernateTemplateCareers.get(AdmFacility.class,
					manageFacilityDTO.getFacilityId());
		}else{
			facility.setCreateDt(new Date());
		}
		facility.setFacilityType(MMJBCommonConstants.FACILITY);
		facility.setFacilityParentId(facilityIdParent);
		// facility.setCreateUserId(userIdp);
		if (facilityP != null) {
			facility.setEmail(facilityP.getEmail());
			facility.setName(manageFacilityDTO.getFacilityName());
			facility.setStreet(manageFacilityDTO.getFacilityStreet());
			facility.setState(manageFacilityDTO.getFacilityState());
			facility.setCountry(manageFacilityDTO.getFacilityCountry());
			facility.setPostcode(manageFacilityDTO.getZipCode());
			facility.setCity(manageFacilityDTO.getFacilityCity());
			facility.setAccountNumber(facilityP.getAccountNumber());
			facility.setNameDisplay(manageFacilityDTO.getFacilityName());
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
			facility.setNsCustomerID(facilityP.getNsCustomerID());
			facility.setTemplateId(Integer.parseInt(manageFacilityDTO.getTemplateId()));
			facility.setFeStartDt(facilityP.getFeStartDt());
			facility.setFeEndDt(facilityP.getFeEndDt());

		}

		AdmFacilityContact admFacilityContactP = facilityP
				.getAdmFacilityContacts().get(0);
		hibernateTemplateCareers.saveOrUpdate(facility);

		// saving the data in adm_facility_contact as per the logged in User

		Date currentDate = new Date();
		if (null != admFacilityContactP) {

			AdmFacilityContact admFacilityContact = new AdmFacilityContact();
			admFacilityContact.setCity(manageFacilityDTO.getFacilityCity());
			admFacilityContact.setCompany(manageFacilityDTO.getFacilityName());
			admFacilityContact.setCountry(manageFacilityDTO
					.getFacilityCountry());
			admFacilityContact.setFirstName(admFacilityContactP.getFirstName());
			admFacilityContact.setStreet(manageFacilityDTO.getFacilityStreet());
			admFacilityContact.setState(manageFacilityDTO.getFacilityState());
			admFacilityContact.setPostcode(manageFacilityDTO.getZipCode());
			admFacilityContact.setLastName(admFacilityContactP.getLastName());
			admFacilityContact.setMiddleName(admFacilityContactP
					.getMiddleName());
			admFacilityContact.setJobTitle(admFacilityContactP.getJobTitle());
			admFacilityContact.setPhone(manageFacilityDTO.getPhoneNumber());
			admFacilityContact.setPhone2(admFacilityContactP.getPhone2());
			admFacilityContact.setContactType(admFacilityContactP
					.getContactType());
			admFacilityContact.setCreateDt(currentDate);
			admFacilityContact.setEmail(admFacilityContactP.getEmail());
			admFacilityContact.setActive(admFacilityContactP.getActive());
			admFacilityContact.setAdmFacility(facility);
			hibernateTemplateCareers.saveOrUpdate(admFacilityContact);

		}

	}

	@Override
	public void deleteFacility(int facilityId) throws JobBoardDataException {
		LOGGER.info("delete Facility Id ----" + facilityId);
		AdmFacility admFacility = hibernateTemplateCareers.get(
				AdmFacility.class, facilityId);
		try {
			admFacility.setDeleteDt(new Timestamp(new Date().getTime()));
			hibernateTemplateCareers.saveOrUpdate(admFacility);

		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

	}
}
