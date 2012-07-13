package com.advanceweb.afc.jb.data.jobapply;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AnonymousUserJobApplyDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class implements all the DAO operations related to applying job for Anonymous User

 */
@Repository
public class AnonymousUserJobApplyDAOImpl implements AnonymousUserJobApplyDAO {

	/**
	   @Author :Prince Mathew
	   @Purpose:To apply a job for Anonymous User
	   @Created:Jul 12, 2012
	   @Param  :AnonymousUserJobApplyDTO
	   @Return :boolean value
	 * @see com.advanceweb.afc.jb.data.jobapply.AnonymousUserJobApplyDAO#applyJobAnonymousUser(com.advanceweb.afc.jb.common.AnonymousUserJobApplyDTO)
	 */
	@Override
	public boolean applyJobAnonymousUser(AnonymousUserJobApplyDTO dto) {
		
		return false;
	}

}
