package com.advanceweb.afc.jb.employer.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.common.util.DateUtils;
import com.advanceweb.afc.jb.data.entities.AdmUserAlert;
import com.advanceweb.afc.jb.data.entities.JpJobStat;
import com.advanceweb.afc.jb.data.entities.MerUser;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 27th August, 2012
 */

@Repository("EmpConversionHelper")
public class EmpConversionHelper {

	private List<MerUser> user;

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
	 * This method is used to convert the AdmUserAlert entity to DTO values
	 * 
	 * @param userAlerts
	 * @return
	 */
	public List<UserAlertDTO> transformAdmUserAlertToAlertDTO(
			List<MerUser> user, List<AdmUserAlert> userAlerts) {
		List<UserAlertDTO> alertDTOs = new ArrayList<UserAlertDTO>();
		String owner = user.get(0).getFirstName() + " "
				+ user.get(0).getLastName();
		for (AdmUserAlert admUserAlert : userAlerts) {
			UserAlertDTO alertDTO = new UserAlertDTO();
			alertDTO.setAlertId(admUserAlert.getAdmAlert().getAlertId());
			alertDTO.setAlertType(admUserAlert.getAlertValue());
			alertDTO.setJobOwner(owner);
			if (admUserAlert.getCreateDt() != null) {
				alertDTO.setSetDate(DateUtils
						.convertSQLDateToStdDate(admUserAlert.getCreateDt()
								.toString()));
			}
			alertDTOs.add(alertDTO);
		}
		return alertDTOs;
	}

}
