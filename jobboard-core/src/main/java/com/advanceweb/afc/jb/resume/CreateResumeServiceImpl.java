package com.advanceweb.afc.jb.resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.data.resume.CreateResumeDao;
import com.advanceweb.afc.jb.dto.CreateResumeDTO;


public class CreateResumeServiceImpl implements CreateResumeService {

	
	public CreateResumeDTO createResumeDTO;
	
	@Autowired
	public CreateResumeDao createResumeDao;


	public CreateResumeServiceImpl() {
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addCreateResumeCopyPaste(CreateResumeDTO createResumeDTO) {
		createResumeDao.saveCreateResumeCopyPaste(createResumeDTO);
		
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addCreateResumeUpload(CreateResumeDTO createResumeDTO) {
		createResumeDao.saveCreateResumeUpload(createResumeDTO);
		
	}




}