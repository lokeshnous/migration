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
	boolean deleteAppliedJobs(long appliedJobId);

	/**
	 * get appliedjob
	 * @param jobSeekerId
	 * @return
	 */
	List<AppliedJobDTO> getAppliedJobs(long jobSeekerId);

	/**
	 * delete savedJob
	 * @param savedJobId
	 */
	boolean deleteSavedJobs(long savedJobId);

	/**
	 * get savedjob
	 * @param jobSeekerId
	 * @return
	 */

	List<SavedJobDTO> getSavedJobs(long jobSeekerId);

}
