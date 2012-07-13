package com.advanceweb.afc.jb.jobapply;

import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.AnonymousUserJobApplyDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class will act as a implementation class of the AnonymousUserJobApply interface and implement all the serv
 */
@Service
public class AnonymousUserJobApplyImpl implements AnonymousUserJobApply {

	public boolean applyJobAnonymousUser(AnonymousUserJobApplyDTO dto){
		
		return true;
	}
}
