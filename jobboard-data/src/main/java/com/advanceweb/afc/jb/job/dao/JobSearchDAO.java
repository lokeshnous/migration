/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.AdminSeoDTO;
import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobTitleDTO;

/**
 * <code> JobSearchDAO </code> is a DAO.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */
public interface JobSearchDAO {

	/**
	 * get job details by job ID
	 * 
	 * @param jobId
	 * @return
	 */
	JobDTO viewJobDetails(long jobId);

	/**
	 * This method provides the total active job count
	 * 
	 * @return jobCount
	 */
	long getActiveJobs();
	
	/**
	 * Fetch the saved or applied job of jobseeker
	 * 
	 * @param jobId
	 * @return
	 */
	AppliedJobDTO fetchSavedOrAppliedJob(JobDTO jobDTO,
			int userId);

	/**
	 * create save or apply the job for logged in user
	 * 
	 * @param jobDTO
	 * @return
	 */
	boolean saveOrApplyJob(AppliedJobDTO jobDTO);

	/**
	 * update save or apply the job for logged in user
	 * 
	 * @param jobDTO
	 * @return
	 */
	boolean updateSaveOrApplyJob(AppliedJobDTO jobDTO);

	/**
	 * To Save the save job details to DB
	 * 
	 * @param jobDTO
	 */
	void saveTheJob(JobDTO jobDTO);

	/**
	 * Fetch the apply type of job
	 * 
	 * @param jobId
	 */
	JobApplyTypeDTO applyJobDetails(int jobId);

	/**
	 * This method will fetch the last five job details based on posted date for
	 * the selected employer.
	 * 
	 * @param
	 * @param jobId
	 * @return List<JobPostDTO> object
	 */

	List<JobPostDTO> getRecentJobsPostedByEmployer(long facilityID, long jobID);

	/**
	 * This method is used to remove the data in database
	 * 
	 * @return
	 */
	public void clearRecentSearches(int userId, int recentSearchsLimit);
	/**
	 * This method is used to check existing session in application
	 * 
	 * @return
	 */
	public void inserSessinfo(String session_id,int userId);
	
	//here implementation work after descsion
     /*public List<VstSessioninfo> getSessionId(String newSession_id);
	 
	 public void insertSessionId(Integer sessioninfo_id);
	
	 */
	
	/**
	 * The method is used to save the SEO info for job title
	 * 
	 * @param seoDTO
	 */
	boolean saveJobTitleSeoInfo(AdminSeoDTO seoDTO);
	
	/**
	 * The method is used to get the SEO info by job title
	 * 
	 * @param jobDTO
	 */
	AdminSeoDTO getSeoJobInfoByTitle(String title);
	
	/**
	 * The method is used to get the job title list
	 * 
	 */
	List<JobTitleDTO> getJobTitleList();

}
