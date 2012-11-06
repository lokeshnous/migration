package com.advanceweb.afc.jb.event.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;

public interface ClickDAO {

	/**
	 * This method updates the click event based on the click type 
	 * 
	 * @param jobId
	 * @param type
	 * @return void
	 */
	void saveClickEvent(int jobId, String type);

	/**
	 * This method updates the Views whenever the job appears in job search
	 * 
	 * @param jobDTOList
	 */
	void saveJobViews(List<JobDTO> jobDTOList);

	/**
	 * This method updates the number of times the resume was viewed by an
	 * Employer
	 * 
	 * @param resumeId
	 */
	void saveResumeEmpViews(int resumeId);

	/**
	 * This method updates the number of times the resume appeared in resume
	 * search
	 * 
	 * @param resumeDTOList
	 */
	void saveResAppearance(List<ResumeDTO> resumeDTOList);

}
