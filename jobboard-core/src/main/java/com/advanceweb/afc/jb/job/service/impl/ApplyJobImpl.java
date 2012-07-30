package com.advanceweb.afc.jb.job.service.impl;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.ApplyJobDTO;
import com.advanceweb.afc.jb.job.service.ApplyJob;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class will act as a implementation class of the AnonymousUserJobApply interface and implement all the service
 */
@Service
public class ApplyJobImpl implements ApplyJob {

/*	@Autowired 
	AnonymousUserJobApplyDAO anonymousUserJobApplyDAO;
*/	
	/**
	   @Author :Prince Mathew
	   @Purpose:this method will call the DAO layer Method for apply a job for Anonymous User
	   @Created:Jul 12, 2012
	   @Param  :Object of AnonymousUserJobApplyDTO
	   @Return :boolean value
	 * @see com.advanceweb.afc.jb.job.service.ApplyJob#applyJobAnonymousUser(com.advanceweb.afc.jb.common.ApplyJobDTO)
	 */
	public boolean applyJobAnonymousUser(ApplyJobDTO dto){
		boolean result=false;
		try {
			//result=anonymousUserJobApplyDAO.applyJobAnonymousUser(dto);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
