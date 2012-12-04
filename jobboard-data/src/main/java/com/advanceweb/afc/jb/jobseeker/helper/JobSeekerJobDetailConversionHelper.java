package com.advanceweb.afc.jb.jobseeker.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.CommonUtil;
import com.advanceweb.afc.jb.common.SavedJobDTO;
import com.advanceweb.afc.jb.data.entities.AdmSaveJob;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.employer.helper.JobPostConversionHelper;

/**
 * @version 1.0
 * @author sharadk
 * 
 */
@Repository("jobSeekerJobDetailConversionHelper")
public class JobSeekerJobDetailConversionHelper {

	@SuppressWarnings("rawtypes")
	@Autowired
	private JobPostConversionHelper jobPostConversionHelper;

	/**
	 * Entity to applied job dto
	 * 
	 * @param entity
	 * @return
	 */
	public List<AppliedJobDTO> transformToApplidJobDTO(List<AdmSaveJob> entity) {
		List<AppliedJobDTO> appliedJobDTOList = new ArrayList<AppliedJobDTO>();
		if (entity != null) {

			for (AdmSaveJob job : entity) {
				AppliedJobDTO appliedJobDTO = new AppliedJobDTO();
				appliedJobDTO.setSaveJobId(job.getSaveJobId());

				 Date appliedDate = job.getAppliedDt();
				 if(appliedDate != null){
				 appliedJobDTO.setAppliedDt(CommonUtil
				 .convertSQLDateTimeToStdDateTime(appliedDate
				 .toString()));
				 }
				 Date createdDate = job.getCreateDt();
				 if(createdDate != null){
				 appliedJobDTO.setCreateDt(CommonUtil
				 .convertSQLDateTimeToStdDateTime(createdDate
				 .toString()));
				 }
				
				appliedJobDTO.setFacilityName(job.getFacilityName());
				appliedJobDTO.setJobTitle(job.getJobtitle());
				appliedJobDTO.setJpJob(jobPostConversionHelper
						.transformToJpJobDTO(job.getJpJob()));
				appliedJobDTOList.add(appliedJobDTO);
			}

		}
		return appliedJobDTOList;

	}

	public List<AppliedJobDTO> transformToDTOForSavedJob(List<AdmSaveJob> entity) {
		List<AppliedJobDTO> appliedJobDTOList = new ArrayList<AppliedJobDTO>();
		if (entity != null) {
			Date date = new Date();
			for (AdmSaveJob job : entity) {
				AppliedJobDTO appliedJobDTO = new AppliedJobDTO();
				appliedJobDTO.setSaveJobId(job.getSaveJobId());
				appliedJobDTO.setCreateDt(CommonUtil.convertSQLDateToStdDateString(job
						.getCreateDt().toString()));
				appliedJobDTO.setFacilityName(job.getFacilityName());
				appliedJobDTO.setJobTitle(job.getJobtitle());
				appliedJobDTO.setJobAge(getJobAge(date,
						job.getCreateDt()));
				appliedJobDTO.setJpJob(jobPostConversionHelper
						.transformToJpJobDTO(job.getJpJob()));
				appliedJobDTO.setAppliedDt(String.valueOf(job.getAppliedDt()));
				appliedJobDTO.setUserId(job.getUserId());
				appliedJobDTOList.add(appliedJobDTO);
			}

		}
		return appliedJobDTOList;

	}

	private int getJobAge(Date startDate, Date endDate) {
		Calendar startCal;
		Calendar endCal;
		startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		int workDays = 0;

		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			return 0;
		}

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

		do {
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			++workDays;
		} while (startCal.getTimeInMillis() < endCal.getTimeInMillis());

		return workDays - 1;
	}

	/**
	 * Entity to saved job dto
	 * 
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
