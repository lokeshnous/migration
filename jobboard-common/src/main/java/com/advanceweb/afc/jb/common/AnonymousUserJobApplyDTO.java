package com.advanceweb.afc.jb.common;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @Author : Prince Mathew
   @Version: 1.0 
   @Created: Jul 12, 2012
   @Purpose: This class will act as a DTO for the Anonymous User to apply for job
 */
public class AnonymousUserJobApplyDTO implements Serializable {


	private String employerEmail;
	private String jobTitle;
	private String jobDescription;
	
	// currently not using 
}
