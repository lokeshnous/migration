package com.advanceweb.afc.jb.event.service;

import java.util.List;

import com.advanceweb.afc.jb.common.ClickEventDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;

public interface ClickService {
	boolean saveClickEvent(ClickEventDTO clickEventDTO);
	ClickEventDTO retrieveAllClicks(int jobId);
	
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
