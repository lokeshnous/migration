package com.advanceweb.afc.jb.job.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th sept, 2012
 */

@Transactional
@Repository("inventoryDAO")
public class JobPostInventoryDAOImpl implements JobPostInventoryDAO {

	/**
	 * This method to get job posting inventory details
	 * 
	 * @param userId
	 * @param facilityId
	 * @return JobPostingInventoryDTO
	 */
	public List<JobPostingInventoryDTO> getInventoryDetails(int userId,
			int facilityId) {

		List<JobPostingInventoryDTO> inventoryDTOs = new ArrayList<JobPostingInventoryDTO>();
		return inventoryDTOs;
	}
}
