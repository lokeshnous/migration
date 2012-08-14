package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
/**
 * Created JobSeekerActivity DAO
 * @author sharadk
 *
 */
public interface JobSeekerActivityDAO {
	/**
	 * delete appliedJob
	 * @param appliedJobId
	 */
	boolean updateAppliedSavedJobs(int jobId);

	/**
	 * get appliedjob
	 * @param jobSeekerId
	 * @return
	 */
	List<AppliedJobDTO> getAppliedJobs(int jobSeekerId);

	/**
	 * get savedjob
	 * @param jobSeekerId
	 * @return
	 */

	List<AppliedJobDTO> getSavedJobs(int jobSeekerId);

}
