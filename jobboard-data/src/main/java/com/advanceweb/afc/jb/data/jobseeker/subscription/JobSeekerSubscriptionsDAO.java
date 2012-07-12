package com.advanceweb.afc.jb.data.jobseeker.subscription;

import java.util.List;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;

/**
 * Created JobSeekerActivity DAO
 * 
 * @author sharadk
 * 
 */
public interface JobSeekerSubscriptionsDAO {
	/**
	 * 
	 * @param jobSeekerSubscriptionsDTO
	 * @return
	 */
	public void saveJobSeekerSubscription(Long id);
}
