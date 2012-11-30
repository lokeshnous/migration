package com.advanceweb.afc.jb.job.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.data.entities.AdmSaveJob;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.jobseeker.dao.JobSeekerJobDetailDAO;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerJobDetailService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * @author sharadk
 */
@Service("jobSeekerJobDetailService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JobSeekerJobDetailServiceImpl implements JobSeekerJobDetailService {

	@Autowired
	private JobSeekerJobDetailDAO jobSeekerJobDetailDAO;
	/**
	 * This method is used to get the list of the all job applied by the corresponding job seeker
	 * @param jobSeekerId
	 * @return List<AppliedJobDTO>
	 */
	@Override
	public List<AppliedJobDTO> getAppliedJobs(int jobSeekerId) throws JobBoardServiceException {
		try {
		return jobSeekerJobDetailDAO.getAppliedJobs(jobSeekerId);
	} catch (JobBoardDataException e) {
		throw new JobBoardServiceException(e);
	}

	}

	/**
	 * This method is used to update the delete data of the applied or saved job depending on the appliedJobId , which is the PK of the AdmSaveJob table
	 * @param appliedJobId , which is the PK of the AdmSaveJobs table
	 * @return true or false 
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public boolean updateAppliedSavedJobs(int jobId)throws JobBoardServiceException {
		try {
			return jobSeekerJobDetailDAO.updateAppliedSavedJobs(jobId);
		} catch (JobBoardDataException e) {
			throw new JobBoardServiceException(e);
		}

	}

	/**
	 * This method is used to get the list of the all job saved by the corresponding job seeker
	 * @param jobSeekerId
	 * @return List<AppliedJobDTO>
	 */
	@Override
	public List<AppliedJobDTO> getSavedJobs(int jobSeekerId)throws JobBoardServiceException  {
		try {
		return jobSeekerJobDetailDAO.getSavedJobs(jobSeekerId);
	} catch (JobBoardDataException e) {
		throw new JobBoardServiceException(e);
	}

	}

	/**
	 * This method gets the number of times the resume was viewed by an
	 * Employer and also the number of time the resume appeared in Search
	 * 
	 * @param jobSeekerId
	 * @return List<Integer>
	 */
	@Override
	public List<Integer> getEmployerViews(int jobSeekerId){
		
		return jobSeekerJobDetailDAO.getEmployerViews(jobSeekerId);
	}

	/**
	 * Method to Get all the job applied by the specified jobseeker in the given
	 * date
	 * 
	 * @param jobSeekerId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws JobBoardDataException
	 */
	public List<AppliedJobDTO> getAppliedJobsByCriteria(int jobSeekerId, String startDate,
			String endDate) throws JobBoardServiceException{
				try {
					return jobSeekerJobDetailDAO.getAppliedJobsByCriteria(jobSeekerId, startDate, endDate);
				} catch (JobBoardDataException e) {
					throw new JobBoardServiceException(e);
				}
			}
}
