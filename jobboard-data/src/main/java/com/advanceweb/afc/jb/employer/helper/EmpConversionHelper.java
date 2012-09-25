package com.advanceweb.afc.jb.employer.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.common.util.DateUtils;
import com.advanceweb.afc.jb.data.entities.AdmAlert;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityAlert;
import com.advanceweb.afc.jb.data.entities.JpJobStat;
import com.advanceweb.afc.jb.data.entities.MerUser;

/**
 * 
 * @author Bharati Umarani
 * @version 1.0
 * @since 27th August, 2012
 */

@Repository("EmpConversionHelper")
public class EmpConversionHelper {

	/**
	 * This method is used to convert the from JpJobStat entity to DTO values
	 * 
	 * @param jobStats
	 * @return
	 */
	public List<MetricsDTO> transformJpJobStatToMetricsDTO(
			List<JpJobStat> jobStatsList) {
		List<MetricsDTO> metricsDTOs = new ArrayList<MetricsDTO>();
		for (JpJobStat jpJobPost : jobStatsList) {
			MetricsDTO metricsDTO = new MetricsDTO();
			metricsDTO.setViews(jpJobPost.getViews());
			metricsDTO.setClicks(jpJobPost.getClicks());
			metricsDTO.setApplies(jpJobPost.getApplies());
			metricsDTOs.add(metricsDTO);
		}
		return metricsDTOs;
	}

	/**
	 * This method is used to convert the AdmFacilityAlert entity to DTO values
	 * 
	 * @param userAlerts
	 * @return
	 */
	public List<UserAlertDTO> transformAdmUserAlertToAlertDTO(
			List<ManageAccessPermissionDTO> jbOwnerList,
			List<AdmFacilityAlert> userAlerts) {
		List<UserAlertDTO> alertDTOs = new ArrayList<UserAlertDTO>();
		if (jbOwnerList != null && !jbOwnerList.isEmpty()) {
			for (ManageAccessPermissionDTO permissionDTO : jbOwnerList) {
				for (AdmFacilityAlert admUserAlert : userAlerts) {
					if (permissionDTO.getOwnerId() == admUserAlert.getUserId()) {
						UserAlertDTO alertDTO = new UserAlertDTO();
						AdmAlert alert = admUserAlert.getAdmAlert();
						alertDTO.setAlertId(alert.getAlertId());
						alertDTO.setAlertType(alert.getName());
						alertDTO.setUserId(admUserAlert.getUserId());
						alertDTO.setFacilityAlertId(admUserAlert
								.getFacilityAlertId());
						alertDTO.setJobOwner(permissionDTO.getOwnerName());
						alertDTO.setSetDate(DateUtils
								.convertSQLDateToStdDate(admUserAlert
										.getCreateDt().toString()));
						alertDTOs.add(alertDTO);
					}
				}
			}
		}
		return alertDTOs;
	}

	/**
	 * This method is used to convert AdmAlert entity to DTO values
	 * 
	 * @param admAlerts
	 * @return
	 */
	public List<DropDownDTO> convertAdmAlertToDropDownDTO(
			List<AdmAlert> admAlerts) {

		DropDownDTO lookUpDTO = null;
		List<DropDownDTO> list = new ArrayList<DropDownDTO>();
		for (AdmAlert admAlert : admAlerts) {

			lookUpDTO = new DropDownDTO();

			lookUpDTO.setOptionId(String.valueOf(admAlert.getAlertId()));
			lookUpDTO.setOptionName(admAlert.getName());

			list.add(lookUpDTO);
		}
		return list;
	}

	/**
	 * Converting into AdmFacilityAlert entity
	 * 
	 * @param listSubsAlerts
	 * @return
	 */
	public List<AdmFacilityAlert> transformAlertDTOToAdmFacilityAlert(
			List<UserAlertDTO> alertDTOs, List<AdmFacilityAlert> listAlerts,
			int facilityId) {

		List<AdmFacilityAlert> admFacilityAlerts = new ArrayList<AdmFacilityAlert>();

		if (null != alertDTOs) {
			for (UserAlertDTO dto : alertDTOs) {
				if (!validateAdmUserSubscriptions(dto, listAlerts)) {
					AdmFacilityAlert entity = new AdmFacilityAlert();
					AdmAlert admAlert = new AdmAlert();
					admAlert.setAlertId(dto.getAlertId());
					entity.setUserId(dto.getUserId());
					entity.setFacilityId(facilityId);
					entity.setAdmAlert(admAlert);
					entity.setCreateDt(new Date());
					admFacilityAlerts.add(entity);
				}
			}
		}
		return admFacilityAlerts;
	}

	/**
	 * validating existing alerts and selected alerts into AdmFacilityAlert
	 * entity
	 * 
	 * @param listSubsAlerts
	 * @return
	 */
	private boolean validateAdmUserSubscriptions(UserAlertDTO alertDTO,
			List<AdmFacilityAlert> listAlerts) {

		if (null != alertDTO && null != listAlerts) {
			for (AdmFacilityAlert entity : listAlerts) {
				if (alertDTO.getAlertId() == entity.getAdmAlert().getAlertId()
						&& alertDTO.getUserId() == entity.getUserId()) {
					listAlerts.remove(entity);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This method is used to convert from admFacility to
	 * ManageAccessPermissionDTO
	 * 
	 * @param merUsers
	 * @param roleId
	 * @return
	 */
	public List<ManageAccessPermissionDTO> transformAdmFacilityToManageAccessPermissionDTO(
			List<MerUser> merUsers, List<Integer> roleId) {
		List<ManageAccessPermissionDTO> manageAccessPermissionDTOList = new ArrayList<ManageAccessPermissionDTO>();
		int i = 0;
		for (MerUser merUser : merUsers) {
			ManageAccessPermissionDTO manageAccessPermissionDTO = new ManageAccessPermissionDTO();
			manageAccessPermissionDTO.setOwnerId(merUser.getUserId());
			manageAccessPermissionDTO.setOwnerName(merUser.getLastName() + " "
					+ merUser.getFirstName());
			if (roleId.size() > i && null != roleId.get(i)) {
				manageAccessPermissionDTO.setTypeOfAccess(roleId.get(i));
			}
			manageAccessPermissionDTOList.add(manageAccessPermissionDTO);
			i = i + 1;
		}
		return manageAccessPermissionDTOList;

	}

	/**
	 * This method is used to convert form UserDTO to ManageAccessPermissionDTO
	 * 
	 * @param merUsers
	 * @return
	 */
	public List<ManageAccessPermissionDTO> transformMerUserToManageAccessPermissionDTO(
			List<MerUser> merUsers) {
		List<ManageAccessPermissionDTO> manageAccessPermissionDTOList = new ArrayList<ManageAccessPermissionDTO>();
		for (MerUser merUser : merUsers) {
			ManageAccessPermissionDTO manageAccessPermissionDTO = new ManageAccessPermissionDTO();
			manageAccessPermissionDTO.setOwnerId(merUser.getUserId());
			manageAccessPermissionDTO.setOwnerName(merUser.getLastName() + " "
					+ merUser.getFirstName());
			manageAccessPermissionDTOList.add(manageAccessPermissionDTO);
		}
		return manageAccessPermissionDTOList;
	}

	/**
	 * This method is to get all list of facilities
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	public List<DropDownDTO> transformFacilityToDropDownDTO(
			List<AdmFacility> facilityList, AdmFacility admFacility,
			int facilityId) {
		List<DropDownDTO> downDTOs = new ArrayList<DropDownDTO>();
		DropDownDTO dto = new DropDownDTO();
		dto.setOptionId(admFacility.getFacilityId().toString());
		dto.setOptionName("*" + " " + admFacility.getName());
		downDTOs.add(dto);
		for (AdmFacility facility : facilityList) {
			dto = new DropDownDTO();
			dto.setOptionId(facility.getFacilityId().toString());
			dto.setOptionName(facility.getName());
			downDTOs.add(dto);
		}
		return downDTOs;
	}
}
