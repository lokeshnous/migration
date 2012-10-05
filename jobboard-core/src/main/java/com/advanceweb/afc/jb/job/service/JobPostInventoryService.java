package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;

/**
 * 
 * @author Bharati Umarani
 * @version 1.0
 * @since 10th sept, 2012
 * 
 */

public interface JobPostInventoryService {

	/**
	 * This method to get job posting inventory details
	 * 
	 * @param userId
	 * @param facilityId
	 * @return JobPostingInventoryDTO
	 */
	List<JobPostingInventoryDTO> getInventoryDetails(int userId, int facilityId);
}
