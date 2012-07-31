package com.advanceweb.afc.jb.job.dao;

import com.advanceweb.afc.jb.common.JobApplicationDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This interface defines all the DAO operations related to applying job for Anonymous User
 */
public interface JobApplicationDAO {
	boolean applyJobAnonymousUser(JobApplicationDTO dto);
}
