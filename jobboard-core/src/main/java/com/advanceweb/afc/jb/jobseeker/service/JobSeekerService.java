package com.advanceweb.afc.jb.jobseeker.service;

import java.util.List;

import com.advanceweb.afc.jb.common.AppliedJobDTO;

/**
 * <code> JobSeekerActivity </code> is a Service class. 
 * 
 * 
 * @author sharad kumar
 * @version 1.0
 * @since 2 July 2012
 * 
 * 
 */
public interface JobSeekerService {

	/**
	 * Applied job methods
	 * 
	 * @param appliedJobId 
	 * 
	 * @return
	 */
	boolean deleteAppliedJobs(int appliedJobId);

	/**
	 * 
	 * @param jobSeekerId
	 * @return
	 */
	List<AppliedJobDTO> getAppliedJobs(int jobSeekerId);

	/**
	 * get applied job
	 * 
	 * @param savedJobId
	 * @return
	 */
	boolean deleteSavedJobs(int savedJobId);
	
	/**
	 * get saved job
	 * @param jobSeekerId
	 * @return
	 */
	
	List<AppliedJobDTO> getSavedJobs(int jobSeekerId);

}