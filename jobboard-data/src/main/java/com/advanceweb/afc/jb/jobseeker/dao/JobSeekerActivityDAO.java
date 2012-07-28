package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;
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
	boolean deleteAppliedJobs(int appliedJobId);

	/**
	 * get appliedjob
	 * @param jobSeekerId
	 * @return
	 */
	List<AppliedJobDTO> getAppliedJobs(int jobSeekerId);

	/**
	 * delete savedJob
	 * @param savedJobId
	 */
	boolean deleteSavedJobs(int savedJobId);

	/**
	 * get savedjob
	 * @param jobSeekerId
	 * @return
	 */

	List<AppliedJobDTO> getSavedJobs(int jobSeekerId);

}
