package com.advanceweb.afc.jb.data.jobseeker.activity;

import java.util.List;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;

public interface JobSeekerActivityDAO {

	boolean deleteAppliedJobs(long appliedJobId);

	List<AppliedJobDTO> getAppliedJobs(long jobSeekerId);

	boolean deleteSavedJobs(long savedJobId);

	List<SavedJobDTO> getSavedJobs(long jobSeekerId);

}
