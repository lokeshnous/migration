package com.advanceweb.afc.jb.jobseeker.service;

import java.util.List;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * <code> JobSeekerService </code> is a Service class. 
 * 
 * 
 * @author sharad kumar
 * @version 1.0
 * @since 2 July 2012
 * 
 * 
 */
public interface JobSeekerJobDetailService {

	/**
	 * This method is used to update the delete data of the applied or saved job depending on the appliedJobId , which is the PK of the AdmSaveJob table
	 * @param appliedJobId , which is the PK of the AdmSaveJobs table
	 * @return true or false 
	 */
	boolean updateAppliedSavedJobs(int appliedJobId)throws JobBoardServiceException;

	/**
	 * This method is used to get the list of the all job applied by the corresponding job seeker
	 * @param jobSeekerId
	 * @return List<AppliedJobDTO>
	 */
	List<AppliedJobDTO> getAppliedJobs(int jobSeekerId)throws JobBoardServiceException ;

	
	/**
	 * This method is used to get the list of the all job saved by the corresponding job seeker
	 * @param jobSeekerId
	 * @return List<AppliedJobDTO>
	 */
	List<AppliedJobDTO> getSavedJobs(int jobSeekerId)throws JobBoardServiceException ;

}