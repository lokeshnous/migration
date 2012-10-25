package com.advanceweb.afc.jb.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.ClickEventDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.event.dao.ClickDAO;
import com.advanceweb.afc.jb.event.service.ClickService;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Service("clickService")
public class ClickServiceImpl implements ClickService {
	
	@Autowired
	public ClickDAO clickDAO;

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean saveClickEvent(ClickEventDTO clickEventDTO) {
		// TODO Auto-generated method stub
		return clickDAO.saveClickEvent( clickEventDTO);
	}

	@Override
	public ClickEventDTO retrieveAllClicks(int jobId) {
		// TODO Auto-generated method stub
		return clickDAO.retrieveAllClicks(jobId);
	}

	/**
	 * This method updates the number of times the resume was viewed by an
	 * Employer
	 * 
	 * @param resumeId
	 */
	@Override
	public void saveResumeEmpViews(int resumeId){
		clickDAO.saveResumeEmpViews(resumeId);
	}
	
	/**
	 * This method updates the number of times the resume appeared in resume
	 * search
	 * 
	 * @param resumeDTOList
	 */
	@Override
	public void saveResAppearance(List<ResumeDTO> resumeDTOList){
		clickDAO.saveResAppearance(resumeDTOList);
	}
	
}
