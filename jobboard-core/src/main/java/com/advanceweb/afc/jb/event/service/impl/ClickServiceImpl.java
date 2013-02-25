/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.event.dao.ClickDAO;
import com.advanceweb.afc.jb.event.service.ClickService;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Service("clickService")
public class ClickServiceImpl implements ClickService {
	
	/** The click dao. */
	@Autowired
	public ClickDAO clickDAO;

	
	/**
	 * This method updates the click event based on the click type 
	 * 
	 * @param jobId
	 * @param type
	 * @return void
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveClickEvent(int jobId, String type){
		clickDAO.saveClickEvent(jobId, type);
	}

	/**
	 * This method updates the Views whenever the job appears in job search
	 * 
	 * @param jobDTOList
	 */
	@Override
	public void saveJobViews(List<JobDTO> jobDTOList){
		clickDAO.saveJobViews(jobDTOList);
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
