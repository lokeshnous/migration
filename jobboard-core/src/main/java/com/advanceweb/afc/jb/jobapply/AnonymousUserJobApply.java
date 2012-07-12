package com.advanceweb.afc.jb.jobapply;

import com.advanceweb.afc.jb.common.AnonymousUserJobApplyDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This will work as a service interface for  Anonymous User for applying job
 */
public interface AnonymousUserJobApply {
	public boolean applyJobAnonymousUser(AnonymousUserJobApplyDTO dto);
}
