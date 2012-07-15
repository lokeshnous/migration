package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;

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
public interface JobSeekerActivity {

	/**
	 * Applied job methods
	 * 
	 * @param appliedJobId 
	 * 
	 * @return
	 */
	void deleteAppliedJobs(long appliedJobId);

	/**
	 * 
	 * @param jobSeekerId
	 * @return
	 */
	List<AppliedJobDTO> getAppliedJobs(long jobSeekerId);

	/**
	 * get applied job
	 * 
	 * @param savedJobId
	 * @return
	 */
	void deleteSavedJobs(long savedJobId);
	
	/**
	 * get saved job
	 * @param jobSeekerId
	 * @return
	 */
	
	List<SavedJobDTO> getSavedJobs(long jobSeekerId);

}