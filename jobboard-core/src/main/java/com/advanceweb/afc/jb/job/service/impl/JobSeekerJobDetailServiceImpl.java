package com.advanceweb.afc.jb.job.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.jobseeker.dao.JobSeekerJobDetailDAO;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerJobDetailService;

/**
 * 
 * @author sharadk
 * 
 */
@Service("jobSeekerJobDetailService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JobSeekerJobDetailServiceImpl implements JobSeekerJobDetailService {

	@Autowired
	private JobSeekerJobDetailDAO jobSeekerJobDetailDAO;

	/**
	 * to get list of applied job
	 */

	@Override
	public List<AppliedJobDTO> getAppliedJobs(int jobSeekerId) {
		return jobSeekerJobDetailDAO.getAppliedJobs(jobSeekerId);

	}

	/**
	 * delete applied job
	 */

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public boolean updateAppliedSavedJobs(int jobId) {
		return jobSeekerJobDetailDAO.updateAppliedSavedJobs(jobId);

	}

	/**
	 * to get list of Saved job
	 */

	@Override
	public List<AppliedJobDTO> getSavedJobs(int jobSeekerId) {
		return jobSeekerJobDetailDAO.getSavedJobs(jobSeekerId);
	}

}
