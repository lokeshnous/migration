package com.advanceweb.afc.jb.agency.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.employer.helper.FacilityConversionHelper;

@Repository("impersonateAgencyDAO")
public class AgencyDAOImpl implements AgencyDAO {

	private static final Logger LOGGER = Logger

	.getLogger(AgencyDAOImpl.class);
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

	/**
	 * This method is used to link the Facility to the corresponding Agency
	 * based on the parentFacilityId
	 * 
	 * @param AccountProfileDTO
	 *            accountDto
	 * @param int agencyFacilityId
	 * @return true or false
	 */
	@Override
	public boolean linkFacility(AccountProfileDTO accountDto,
			int agencyFacilityId) throws JobBoardDataException {
		boolean result = false;
		try {
			AdmFacility facility = hibernateTemplateCareers.get(
					AdmFacility.class, accountDto.getFacilityId());
			facility.setFacilityParentId(agencyFacilityId);
			hibernateTemplateCareers.update(facility);
			result = true;
		} catch (Exception e) {
			LOGGER.debug("Error while linking the Facility to the corresponding Agency"
					+ e);
			throw new JobBoardDataException(
					"Error while linking the Facility to the corresponding Agency"
							+ e);
		}
		return result;
	}

	/**
	 * This method is used to get all the linked Facility to the corresponding
	 * Agency based on the Agency facilityId
	 * 
	 * @param int agencyFacilityId
	 * @return List<FacilityDTO>
	 */
	@Override
	public List<FacilityDTO> getLinkedFacilityNames(int agencyFacilityId)
			throws JobBoardDataException {
		List<AdmFacility> facilityList = new ArrayList<AdmFacility>();
		try {
			List<AdmFacility> assocEmplyrs = hibernateTemplateCareers.find(
					"from AdmFacility where facilityParentId=?",
					agencyFacilityId);
			for (AdmFacility facility : assocEmplyrs) {
				int roleId = facility.getAdmUserFacilities().get(0)
						.getAdmRole().getRoleId();
				if (!(roleId == 5 | roleId == 6)) {
					facilityList.add(facility);
				}
			}
		} catch (Exception e) {
			LOGGER.debug("Error while fetching all linked facility based on the parentFacilityId"
					+ e);
			throw new JobBoardDataException(
					"Error while fetching all linked facility based on the parentFacilityId"
							+ e);
		}
		return facilityConversionHelper.transformToFacilityDTO(facilityList);
	}

	/**
	 * This method is used to unlink the Facility from the corresponding Agency
	 * 
	 * @param int facilityId
	 * @return true or false
	 */
	@Override
	public boolean deleteAssocEmployer(int facilityId)
			throws JobBoardDataException {
		boolean result = false;
		try {
			AdmFacility facility = hibernateTemplateCareers.get(
					AdmFacility.class, facilityId);
			facility.setFacilityParentId(0);
			hibernateTemplateCareers.update(facility);
			result = true;
		} catch (Exception e) {
			LOGGER.debug("Error while unlinking Facility from corresponding Agency"
					+ e);
			throw new JobBoardDataException(
					"Error while unlinking Facility from corresponding Agency"
							+ e);
		}
		return result;
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
							+ "%' and adm.facilityType!='FACILITY_SYSTEM' and adm.facilityParentId='0'");
			for (AdmFacility adm : facility) {
				FacilityDTO dto = new FacilityDTO();
				dto.setName(adm.getName());
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