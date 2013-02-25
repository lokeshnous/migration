/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.service;

import com.advanceweb.afc.jb.common.JobApplicationDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This will work as a service interface for  Anonymous User for applying job
 */
public interface JobApplicationService {
 
 /**
  * Apply job anonymous user.
  *
  * @param dto the dto
  * @return true, if successful
  */
 boolean applyJobAnonymousUser(JobApplicationDTO dto);
}
