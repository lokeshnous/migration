package com.advanceweb.afc.jb.jobseeker.activity;

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
	boolean deleteAppliedJobs(long appliedJobId);

	/**
	 * 
	 * @param jobSeekerId
	 * @return
	 */
	List<AppliedJobDTO> getAppliedJobs(long jobSeekerId);

	/**
	 * Saved Job methods
	 * 
	 * @param savedJobId
	 * @return
	 */
	boolean deleteSavedJobs(long savedJobId);
	
	/**
	 * 
	 * @param jobSeekerId
	 * @return
	 */
	
	List<SavedJobDTO> getSavedJobs(long jobSeekerId);

}