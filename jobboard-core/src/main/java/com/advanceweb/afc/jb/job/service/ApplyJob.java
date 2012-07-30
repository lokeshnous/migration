package com.advanceweb.afc.jb.job.service;

import com.advanceweb.afc.jb.common.ApplyJobDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This will work as a service interface for  Anonymous User for applying job
 */
public interface ApplyJob {
 boolean applyJobAnonymousUser(ApplyJobDTO dto);
}
