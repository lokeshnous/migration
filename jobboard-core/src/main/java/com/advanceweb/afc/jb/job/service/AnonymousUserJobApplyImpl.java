package com.advanceweb.afc.jb.job.service;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.AnonymousUserJobApplyDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class will act as a implementation class of the AnonymousUserJobApply interface and implement all the service
 */
@Service
public class AnonymousUserJobApplyImpl implements AnonymousUserJobApply {

/*	@Autowired 
	AnonymousUserJobApplyDAO anonymousUserJobApplyDAO;
*/	
	/**
	   @Author :Prince Mathew
	   @Purpose:this method will call the DAO layer Method for apply a job for Anonymous User
	   @Created:Jul 12, 2012
	   @Param  :Object of AnonymousUserJobApplyDTO
	   @Return :boolean value
	 * @see com.advanceweb.afc.jb.job.service.AnonymousUserJobApply#applyJobAnonymousUser(com.advanceweb.afc.jb.common.AnonymousUserJobApplyDTO)
	 */
	public boolean applyJobAnonymousUser(AnonymousUserJobApplyDTO dto){
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
