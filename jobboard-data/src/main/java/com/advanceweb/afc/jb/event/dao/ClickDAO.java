package com.advanceweb.afc.jb.event.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.ClickEventDTO;
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;

public interface ClickDAO {

	boolean saveClickEvent(ClickEventDTO clickEventDTO);

	ClickEventDTO retrieveAllClicks(int jobId);

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
