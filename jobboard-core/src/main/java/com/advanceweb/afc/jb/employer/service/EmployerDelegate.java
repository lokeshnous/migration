package com.advanceweb.afc.jb.employer.service;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface EmployerDelegate {
	
	public UserDTO createEmployerProfile(EmployerProfileDTO empProfileDTO) throws JobBoardServiceException;

}
