package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.data.entities.AdmSaveJob;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
/**
 * @author sharadk
 */
public interface JobSeekerJobDetailDAO {
	/**
	 * This method is used to update the delete data of the applied or saved depending on the appliedJobId , which is the PK of the AdmSaveJob table
	  * @param appliedJobId , which is the PK of the AdmSaveJobs table
	 * @return true or false 
	 */
	boolean updateAppliedSavedJobs(int jobId)throws JobBoardDataException;

	/**
	 * This method is used to get the list of the all job applied by the corresponding job seeker
	 * @param jobSeekerId
	 * @return List<AppliedJobDTO>
	 */
	List<AppliedJobDTO> getAppliedJobs(int jobSeekerId)throws JobBoardDataException;

	/**
	 * This method is used to get the list of the all job saved by the corresponding job seeker
	 * @param jobSeekerId
	 * @return List<AppliedJobDTO>
	 */
	List<AppliedJobDTO> getSavedJobs(int jobSeekerId)throws JobBoardDataException;

	/**
	 * This method gets the number of times the resume was viewed by an
	 * Employer and also the number of time the resume appeared in Search
	 * 
	 * @param jobSeekerId
	 * @return List<Integer>
	 */
	List<Integer> getEmployerViews(int jobSeekerId);

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
	List<AppliedJobDTO> getAppliedJobsByCriteria(int jobSeekerId, String startDate,
			String endDate) throws JobBoardDataException;

}
