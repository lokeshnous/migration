package com.advanceweb.afc.jb.jobseeker.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;
import com.advanceweb.afc.jb.common.util.DateUtils;
import com.advanceweb.afc.jb.data.entities.AdmSaveJob;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.employer.helper.JobPostConversionHelper;


/**
 * @version 1.0
 * @author sharadk
 *
 */
@Repository("jobSeekerActivityConversionHelper")
public class JobSeekerActivityConversionHelper {

	@Autowired
	JobPostConversionHelper jobPostConversionHelper;
	
	
	
	/**
	 * Entity to applied job dto
	 * @param entity
	 * @return
	 */
	public List<AppliedJobDTO> transformToApplidJobDTO(List<AdmSaveJob> entity) {
		List<AppliedJobDTO> appliedJobDTOList=new ArrayList<AppliedJobDTO>();
		if (entity != null) {
			
			for(AdmSaveJob job:entity){
				AppliedJobDTO appliedJobDTO = new AppliedJobDTO();
				appliedJobDTO.setSaveJobId(job.getSaveJobId());
				appliedJobDTO.setAppliedDt(DateUtils.convertSQLDateToStdDate(job.getAppliedDt().toString()));
				appliedJobDTO.setCreateDt(job.getCreateDt());
				appliedJobDTO.setDeleteDt(job.getDeleteDt());
				appliedJobDTO.setFacilityName(job.getFacilityName());
				appliedJobDTO.setJobTitle(job.getJobtitle());
				appliedJobDTO.setJpJob(jobPostConversionHelper.transformToJpJobDTO(job.getJpJob()) );
				appliedJobDTOList.add(appliedJobDTO);
			}
			
		}
		return appliedJobDTOList;

	}

	/**
	 * Entity to saved job dto
	 * @param entity
	 * @return
	 */
	public SavedJobDTO transformJpJobToSavedJobDTO(JpJob entity) {
		SavedJobDTO savedJobDTO = new SavedJobDTO();
		if (entity != null) {
			savedJobDTO.setJobTitle(entity.getJobtitle());

		}
		return savedJobDTO;

	}

}
