package com.advanceweb.afc.jb.agency.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
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
//		facility.setAdminUserId(2162);// TODO: REMOVE HARD CODE AGENCY USER ID
//		facility.setFacilityParentId(393);
		facility.setAdminUserId(userId);
		facility.setFacilityParentId(agencyFacilityId);
		hibernateTemplateCareers.update(facility);
		return true;
	}

	@Override
	public List<FacilityDTO> getAssocEmployerNames(int userId,
			int agencyFacilityId) {
		@SuppressWarnings("unchecked")
//		List<AdmFacility> assocEmplyrs = hibernateTemplateCareers
//				.find("from AdmFacility where adminUserId=2162 and facilityParentId=393 and deleteUserId=0");
		List<AdmFacility> assocEmplyrs = hibernateTemplateCareers
				.find("from AdmFacility where adminUserId=" + userId
						+ " and facilityParentId=" + agencyFacilityId
						+ " and deleteUserId=0");
		List<FacilityDTO> facilityDTO=facilityConversionHelper.transformToFacilityDTO(assocEmplyrs);
		return facilityDTO;
	}

	/*@Override
	public boolean saveEmployerDetails(AccountProfileDTO dto) {
		AdmFacility facility = hibernateTemplateCareers.get(AdmFacility.class,
				dto.getFacilityId());
		facility.setCity(dto.getCity());
		facility.setState(dto.getState());
		facility.setStreet(dto.getStreet());
		facility.setCountry(dto.getCountry());
		facility.setPostcode(dto.getZipCode());
		hibernateTemplateCareers.update(facility);
		return true;
	}
*/
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
		/*List<String> emplyrNamesList = new ArrayList<String>();*/
		List<FacilityDTO> emplyrNamesList = new ArrayList<FacilityDTO>();
		try {
			// List<MerUser> usersList = hibernateTemplateTracker
			// .find("from MerUser merUsr where merUsr.firstName like '%"
			// + employerName + "%'");
			// for (MerUser user : usersList) {
			// List<Object> emplyrNamesList = hibernateTemplate
			// .find("select admUsrRole.id from AdmUserRole admUsrRole where admUsrRole.id.roleId="
			// + MMJBCommonConstants.EMPLOYER_ROLE_ID
			// + " and admUsrRole.id.userId='"
			// + user.getUserId() + "'");
			// if (null != emplyrNamesList && emplyrNamesList.size() > 0) {
			// emplyrs.add(user.getFirstName());
			// }
			// }

			/*
			 * List<Integer> emplyrs = hibernateTemplate .find(
			 * "select admFacility.facilityId from AdmFacility admFacility where admFacility.name like '%"
			 * + employerName + "%'"); if (emplyrs != null &&
			 * !emplyrs.isEmpty()) { for (Integer object : emplyrs) {
			 * List<Integer> facilities = hibernateTemplate .find(
			 * "select admUsrFaclty.admFacility.facilityId from AdmUserFacility admUsrFaclty where admUsrFaclty.admRole="
			 * + MMJBCommonConstants.EMPLOYER_ROLE_ID +
			 * " and admUsrFaclty.admFacility.facilityId=?", object); if
			 * (facilities != null && !facilities.isEmpty()) { List<Object>
			 * emplyrNames = hibernateTemplate .find(
			 * "select admFacility.name from AdmFacility admFacility where admFacility.facilityId=?"
			 * , facilities.get(0)); if (emplyrNames != null &&
			 * !emplyrNames.isEmpty()) {
			 * emplyrNamesList.add(emplyrNames.get(0).toString()); } } }
			 * 
			 * }
			 */

			/*
			 * List<Object[]> emplyrs = hibernateTemplate .find(
			 * "select admFacility.facilityId,admFacility.name from AdmFacility admFacility where admFacility.name like '%"
			 * + employerName + "%'"); if (emplyrs != null &&
			 * !emplyrs.isEmpty()) { for (Object[] emplyr : emplyrs) {
			 * List<Integer> facilities = hibernateTemplate .find(
			 * "select admUsrFaclty.admFacility.facilityId from AdmUserFacility admUsrFaclty where admUsrFaclty.admRole="
			 * + MMJBCommonConstants.EMPLOYER_ROLE_ID +
			 * " and admUsrFaclty.admFacility.facilityId=?",
			 * Integer.parseInt(String.valueOf(emplyr[0]))); if (facilities !=
			 * null && !facilities.isEmpty()) { EmployerInfoDTO empDto = new
			 * EmployerInfoDTO(); empDto.setFacilityId(Integer.parseInt(String
			 * .valueOf(emplyr[0])));
			 * empDto.setCustomerNamel(String.valueOf(emplyr[1]));
			 * emplyrNamesList.add(empDto); }
			 * 
			 * }
			 * 
			 * }
			 */
			List<AdmFacility> facility= hibernateTemplateCareers
					.find("from AdmFacility where name like '%"
							+ employerName + "%' and facilityType='FACILITY'");
			
			for(AdmFacility adm:facility){
				FacilityDTO dto= new FacilityDTO();
				dto.setName(adm.getName());
				dto.setFacilityId(adm.getFacilityId());
				emplyrNamesList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emplyrNamesList;
	}

	@Override
	public Map<String, Object> getEmployerDetails(int facilityId) {

		Map<String, Object> employerDetails = new HashMap<String, Object>();
		// TODO: PHONE NUMBER DISPLAY
		// List<Object> emplyrDetailsList = hibernateTemplate
		// .find("select admFacility.city,admFacility.street,admFacility.postcode,admFacility.state,admFacility.country,admFacility.admFacilityContacts.phone from AdmFacility admFacility where admFacility.name=?",
		// employerName);
		List<Object> emplyrDetailsList = hibernateTemplateCareers
				.find("select admFacility.city,admFacility.street,admFacility.postcode,admFacility.state,admFacility.country,admFacility.facilityId,admFacility.name from AdmFacility admFacility where admFacility.facilityId=?",
						facilityId);
		if (emplyrDetailsList != null && !emplyrDetailsList.isEmpty()) {
			Object[] emplyrDetails = (Object[]) emplyrDetailsList.get(0);
			employerDetails.put("city", emplyrDetails[0]);
			employerDetails.put("street", emplyrDetails[1]);
			employerDetails.put("postcode", emplyrDetails[2]);
			employerDetails.put("state", emplyrDetails[3]);
			employerDetails.put("country", emplyrDetails[4]);
			employerDetails.put("facilityId", emplyrDetails[5]);
			employerDetails.put("name", emplyrDetails[6]);
			List<Object> phoneattrIdList = hibernateTemplateTracker
					.find("select merProfAttrb.profileAttribId from MerProfileAttrib merProfAttrb where merProfAttrb.name= '"
							+ MMJBCommonConstants.PHONE + "'");
			// List<Object> phoneattrIdList = hibernateTemplateTracker
			// .find("select merProfAttrb.profileAttribId from MerProfileAttrib merProfAttrb where merProfAttrb.name='Phone'");
			List<Object> userIdList = hibernateTemplateCareers
					.find("select admUsrFacilty.facilityPK.userId from AdmUserFacility admUsrFacilty where admUsrFacilty.admFacility.facilityId="
							+ emplyrDetails[5]);
			List<Object> phoneNumList = hibernateTemplateTracker
					.find("select merUsrProfile.attribValue from MerUserProfile merUsrProfile where merUsrProfile.merProfileAttrib.profileAttribId="
							+ phoneattrIdList.get(0)
							+ "and merUsrProfile.merUser.userId="
							+ userIdList.get(0));
			employerDetails.put("phone", phoneNumList.get(0));
		}

		return employerDetails;
	}
}
