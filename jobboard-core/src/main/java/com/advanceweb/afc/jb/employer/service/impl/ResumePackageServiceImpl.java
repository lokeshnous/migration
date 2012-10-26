/**
 * 
 */
package com.advanceweb.afc.jb.employer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.ResumePackageDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.employer.dao.ResumePackageDAO;
import com.advanceweb.afc.jb.employer.service.ResumePackageService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * This class is used to handle the Resume Search Packages functionalities
 * @author anilm
 * @version 1.0
 * @since 15 Oct 2012
 */
@Service("resumePackageService")
public class ResumePackageServiceImpl implements ResumePackageService{
	
	private static final Logger LOGGER = Logger
			.getLogger(ResumePackageServiceImpl.class);
	
	@Autowired
	private ResumePackageDAO resumePackageDAO;
	
	@Override
	public List<ResumePackageDTO> showResumeSearchPackages() throws JobBoardServiceException{
		try {
			return resumePackageDAO.showResumeSearchPackages();
		} catch (JobBoardDataException e) {
			LOGGER.info("Error occurred while fetching the Resume Search Packages"+ e);
			throw new JobBoardServiceException("Error occurred while fetching the Resume Search Packages"+ e);
			
		}
	}

}
