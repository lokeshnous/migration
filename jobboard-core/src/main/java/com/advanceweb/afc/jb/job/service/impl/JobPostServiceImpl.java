package com.advanceweb.afc.jb.job.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.employer.dao.JobPostDAO;
import com.advanceweb.afc.jb.job.service.JobPostService;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class will implement all the methods of EmployerJobPost interface 
 */
@Service("JobPostService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JobPostServiceImpl implements JobPostService {
	
	@Autowired
	JobPostDAO employerJobPostDAO;
	
	/**
	   @Author :Prince Mathew
	   @Purpose:To get the information of the employer while posting the job
	   @Created:Jul 19, 2012
	   @Param  :userId
	   @Return :EmployerInfoDTO which contain the employer information
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#getEmployerInfo(int)
	 */
	@Override
	public EmployerInfoDTO getEmployerInfo(int userId, String roleName) {
		return employerJobPostDAO.getEmployerInfo(userId, roleName);
		
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the List of States 
	   @Created:Jul 19, 2012
	   @Param  :not required
	   @Return :List of the StateDTO
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#getStateList()
	 */
	@Override
	public List<StateDTO> getStateList() {
		return employerJobPostDAO.getStateList();
		
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To save the new job
	   @Created:Jul 20, 2012
	   @Param  :EmployerInfoDTO object
	   @Return :boolean result
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#savePostJob(com.advanceweb.afc.jb.common.EmployerInfoDTO)
	 */
	@Override
	public boolean savePostJob(JobPostDTO dto) {
		return employerJobPostDAO.savePostJob(dto);
	}

}
