package com.advanceweb.afc.jb.employer.helper;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.data.entities.JpJob;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class will work as a converter from EmployerJobPostDTO to Entity class Object or from Entity class Object to EmployerJobPostDTO
 */
@Repository("jobPostConversionHelper")
public class JobPostConversionHelper {
	
	 public JpJob  transformJobDtoToJpJob(JobPostDTO dto){
		
		 JpJob jpJob=new JpJob();
		 jpJob.setName(dto.getCompanyName());
		 jpJob.setJobNumber(dto.getJobId());
		 jpJob.setJobtitle(dto.getJobTitle());
		 
		 return jpJob;
	 }
	 
	 public JobPostDTO  transformToJpJobDTO(JpJob dto){
			
		 JobPostDTO jobPostDTO=new JobPostDTO();
		 jobPostDTO.setCompanyName(dto.getFacility());
		 jobPostDTO.setJobTitle(dto.getJobtitle());
		 jobPostDTO.setJobId(dto.getJobNumber());
		 return jobPostDTO;
	 }

}
