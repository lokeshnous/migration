package com.advanceweb.afc.jb.jobseeker.activity;

import java.util.List;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;
import com.advanceweb.afc.jb.data.jobseeker.activity.JobSeekerActivityDAO;

public class JobSeekerActivityService implements JobSeekerActivity {

	public AppliedJobDTO appliedJobDTO;
	public JobSeekerActivityDAO activityDAO;

	JobSeekerActivityService(){
		
	}
	
	
	@Override
	public List<AppliedJobDTO> getAppliedJobs(long jobSeekerId) {

		return activityDAO.getAppliedJobs(jobSeekerId);

	}

	@Override
	public boolean deleteAppliedJobs(long appliedJobId) {

		activityDAO.deleteAppliedJobs(appliedJobId);
		return false;
	}

	
	
	@Override
	public List<SavedJobDTO> getSavedJobs(long jobSeekerId) {
		return activityDAO.getSavedJobs(jobSeekerId);
	}

	@Override
	public boolean deleteSavedJobs(long savedJobId) {
		activityDAO.deleteSavedJobs(savedJobId);
		return false;
	}

}
