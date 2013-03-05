/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;
import com.advanceweb.afc.jb.job.dao.JobPostInventoryDAO;
import com.advanceweb.afc.jb.job.service.JobPostInventoryService;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th sept, 2012
 */
@Service("inventoryService")
public class JobPostInventoryServiceImpl implements JobPostInventoryService{

	/** The inventory dao. */
	@Autowired
	private JobPostInventoryDAO inventoryDAO;

	/**
	 * This method to get job posting inventory details
	 * 
	 * @param userId
	 * @param facilityId
	 * @return JobPostingInventoryDTO
	 */
	public List<JobPostingInventoryDTO> getInventoryDetails(int userId,
			int facilityId) {
		return inventoryDAO.getInventoryDetails(userId, facilityId);
	}
	
	/**
	 * This method to get resume inventory details
	 * 
	 * @param userId
	 * @param facilityId
	 * @return JobPostingInventoryDTO
	 */
	public List<JobPostingInventoryDTO> getResumeInventoryDetails(int userId,
			int facilityId) {
		return inventoryDAO.getResumeInventoryDetails(userId, facilityId);
	}
}
