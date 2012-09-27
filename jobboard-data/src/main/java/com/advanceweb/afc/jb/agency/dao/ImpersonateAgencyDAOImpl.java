package com.advanceweb.afc.jb.agency.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.employer.helper.FacilityConversionHelper;

@Repository("impersonateAgencyDAO")
public class ImpersonateAgencyDAOImpl implements  ImpersonateAgencyDAO{
	
	private static final Logger LOGGER = Logger

			.getLogger(ImpersonateAgencyDAOImpl.class);
	@Autowired
	private FacilityConversionHelper facilityConversionHelper;

	private HibernateTemplate hibernateTemplateCareers;
	private HibernateTemplate hibernateTemplateTracker;
	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);

	}
	@Override
	public boolean addEmployer(AccountProfileDTO accountDto,
			int agencyFacilityId, int userId) {
		AdmFacility facility = hibernateTemplateCareers.get(AdmFacility.class,
				accountDto.getFacilityId());
		facility.setFacilityParentId(agencyFacilityId);
		hibernateTemplateCareers.update(facility);
		return true;
	}

	@Override
	public List<FacilityDTO> getAssocEmployerNames(int userId,
			int agencyFacilityId) {
		@SuppressWarnings("unchecked")
		List<AdmFacility> assocEmplyrs = hibernateTemplateCareers
				.find("from AdmFacility where facilityParentId=?",agencyFacilityId);
		List<AdmFacility> facilityList= new ArrayList<AdmFacility>();
		for(AdmFacility facility:assocEmplyrs){
			int roleId=facility.getAdmUserFacilities().get(0).getAdmRole().getRoleId();
			if(!(roleId==5 | roleId==6)){
				facilityList.add(facility);
			}
		}
		return facilityConversionHelper.transformToFacilityDTO(facilityList);
	}

	@Override
	public boolean deleteAssocEmployer(String facilityId, int userId) {
		AdmFacility facility = hibernateTemplateCareers.get(AdmFacility.class,
				Integer.parseInt(facilityId));
		facility.setFacilityParentId(0);
		hibernateTemplateCareers.update(facility);
		return true;
	}
	
	/**
	 * This method is used to get the net suite customer id based on
	 * adm facility id.
	 * @param int admFacilityID
	 * @return int NSCustomerID
	 */
	
	public List<FacilityDTO> getNSCustomerIDFromAdmFacility(int admFacilityID) {

		List<FacilityDTO> admFacilityDTOList = new ArrayList<FacilityDTO>();
		try {
			@SuppressWarnings("unchecked")
			List<AdmFacility> admFacilityList = hibernateTemplateCareers
					.find(" from  AdmFacility WHERE  facilityId  = '" + admFacilityID + "'");

			if (admFacilityList != null) {
				for (AdmFacility admFacilityObj : admFacilityList) {
					FacilityDTO admFacilityDTO = new FacilityDTO();
					admFacilityDTO.setNsCustomerID(admFacilityObj.getNsCustomerID());
					admFacilityDTOList.add(admFacilityDTO);
				}
			}

		} catch (HibernateException e) {
			LOGGER.debug(e);
		}
		return admFacilityDTOList;
	}

	
	@Override
	public List<FacilityDTO> getEmployerNamesList(String employerName) {
		List<FacilityDTO> emplyrNamesList = new ArrayList<FacilityDTO>();
		try {
			
			List<AdmFacility> facility= hibernateTemplateCareers
					.find("from AdmFacility adm where adm.name like '"+employerName+"%' and adm.facilityType!='FACILITY_SYSTEM' and adm.facilityParentId='0'");
			for(AdmFacility adm:facility){
				FacilityDTO dto= new FacilityDTO();
				dto.setName(adm.getName());
				dto.setFacilityId(adm.getFacilityId());
				emplyrNamesList.add(dto);
			}
		} catch (Exception e) {
			LOGGER.info("Exception while getting the employer name"+e);
		}
		return emplyrNamesList;
	}

	@Override
	public Map<String, Object> getEmployerDetails(int facilityId) {
		Map<String, Object> employerDetails = new HashMap<String, Object>();
		AdmFacility facility = (AdmFacility)DataAccessUtils.uniqueResult(hibernateTemplateCareers
				.find("from AdmFacility admFacility where admFacility.facilityId=?",
						facilityId));
		if (facility != null ) {
			employerDetails.put("city", facility.getCity());
			employerDetails.put("street", facility.getStreet());
			employerDetails.put("postcode", facility.getPostcode());
			employerDetails.put("state", facility.getState());
			employerDetails.put("country", facility.getCountry());
			employerDetails.put("facilityId", facility.getFacilityId());
			employerDetails.put("name", facility.getName());
			employerDetails.put("phone",  facility.getAdmFacilityContacts().get(0).getPhone());
		}
		return employerDetails;
	}
}
