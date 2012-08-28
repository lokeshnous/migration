package com.advanceweb.afc.jb.employer.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.data.entities.JpJobStat;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 27th August, 2012
 */

@Repository("EmpMetricsConversionHelper")
public class EmpMetricsConversionHelper {

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
}
