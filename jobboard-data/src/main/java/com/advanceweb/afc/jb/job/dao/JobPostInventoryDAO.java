/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th sept, 2012
 */

public interface JobPostInventoryDAO {

	/**
	 * This method to get job posting inventory details
	 * 
	 * @param userId
	 * @param facilityId
	 * @return JobPostingInventoryDTO
	 */
	List<JobPostingInventoryDTO> getInventoryDetails(int userId, int facilityId);
	/**
	 * This method to get resume inventory details
	 * 
	 * @param userId
	 * @param facilityId
	 * @return JobPostingInventoryDTO
	 */
	List<JobPostingInventoryDTO> getResumeInventoryDetails(int userId, int facilityId);
}
