package com.advanceweb.afc.jb.jobseeker.subscription;

import java.util.List;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;

/**
 * <code> JobSeekerActivity </code> is a Service class. 
 * 
 * 
 * @author sharad kumar
 * @version 1.0
 * @since 10 July 2012
 * 
 * 
 */
public interface JobSeekerSubscriptions {
	/**
	 * 
	 * @param jobSeekerSubscriptionsDTO
	 * @return
	 */
	
	public void saveJobSeekerSubscription(Long id);
	
}