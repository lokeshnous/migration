package com.advanceweb.afc.jb.data.jobseeker.activity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;

public class JobSeekerActivityDAOImpl implements JobSeekerActivityDAO {

	public JobSeekerActivityDAOImpl() {

	}

	@Override
	public void finalize() throws Throwable {

	}

	@Override
	public boolean deleteAppliedJobs(long appliedJobId) {
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public List<AppliedJobDTO> getAppliedJobs(long jobSeekerId) {
		List<AppliedJobDTO> appliedJobDTO = new ArrayList<AppliedJobDTO>();
		return appliedJobDTO;
	}

	@Override
	public boolean deleteSavedJobs(long savedJobId) {
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SavedJobDTO> getSavedJobs(long jobSeekerId) {
		List<SavedJobDTO> savedJobDTO = new ArrayList<SavedJobDTO>();
		return savedJobDTO;
	}

}
