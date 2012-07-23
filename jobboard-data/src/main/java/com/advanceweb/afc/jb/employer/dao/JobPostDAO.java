package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.StateDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This interface defines all the DAO operations related to Posting New Job 
 */
public interface JobPostDAO {
	
	EmployerInfoDTO getEmployerInfo(int userId);
	List<StateDTO> getStateList();
	boolean savePostJob(JobPostDTO dto) ;

}
