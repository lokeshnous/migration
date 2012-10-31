/**
 * 
 */
package com.advanceweb.afc.jb.employer.service;

import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.common.ResumePackageDTO;
import com.advanceweb.afc.jb.employer.service.impl.ResumePackageServiceImpl;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.jb.test.ServiceTest;

/**
 * This class has been created to unit test the Resume Search Packages
 * @author anilm
 * @version 1.0
 * @since 15 Oct 2012
 */
public class ResumeSearchPackageTest extends ServiceTest {
	
	private static final Logger LOGGER = Logger
			.getLogger(ResumePackageServiceImpl.class);
	
	@Autowired
	ResumePackageService resumePackageService;
	
	/**
	 * This is the test case to display all the Resume Search Packages available for an employer
	 */
	@Test
	public void showResumeSearchPackages(){
		List<ResumePackageDTO> resSearchPackageDTOList = null;
		try {
			resSearchPackageDTOList = resumePackageService.showResumeSearchPackages();
		} catch (JobBoardServiceException e) {
			LOGGER.error(e);
		}
		Assert.assertNotNull("show resume search packages",resSearchPackageDTOList);
	}
}
