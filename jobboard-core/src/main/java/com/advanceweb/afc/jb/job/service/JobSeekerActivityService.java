package com.advanceweb.afc.jb.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;
import com.advanceweb.afc.jb.jobseeker.dao.JobSeekerActivityDAO;

/**
 * 
 * @author sharadk
 * 
 */
@Service("jobSeekerActivity")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JobSeekerActivityService implements JobSeekerActivity {

	@Autowired
	public JobSeekerActivityDAO activityDAO;

	JobSeekerActivityService() {

	}

	/**
	 * to get list of applied job
	 */

	@Override
	public List<AppliedJobDTO> getAppliedJobs(int jobSeekerId) {

		return activityDAO.getAppliedJobs(jobSeekerId);

	}

	/**
	 * delete applied job
	 */

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public boolean deleteAppliedJobs(int appliedJobId) {

		return activityDAO.deleteAppliedJobs(appliedJobId);

	}

	/**
	 * to get list of Saved job
	 */

/*	@Override
	public List<SavedJobDTO> getSavedJobs(int jobSeekerId) {
		return activityDAO.getSavedJobs(jobSeekerId);
	}
*/
	/**
	 * delete Saved job
	 */
/*	@Override
	public boolean deleteSavedJobs(int savedJobId) {
		return activityDAO.deleteSavedJobs(savedJobId);
	}
*/
	public JobSeekerActivityDAO getActivityDAO() {
		return activityDAO;
	}

	public void setActivityDAO(JobSeekerActivityDAO activityDAO) {
		this.activityDAO = activityDAO;
	}

}
