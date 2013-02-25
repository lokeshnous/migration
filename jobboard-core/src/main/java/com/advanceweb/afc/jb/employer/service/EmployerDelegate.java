/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.service;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;


/**
 * This is a interface to call the NetSuite service layer from the Job board.
 * 
 * @author Reetesh R N
 * @version 1.0
 * @since 04 Sept 2012
 * 
 */

public interface EmployerDelegate {
	
	/**
	 * This method is used to create a customer through NetSuite
	 * and based on the status returned from Net Suite, it will
	 * create the user in the Job board.
	 * 
	 * @param Object of EmployerProfileDTO
	 * @return Object of UserDTO
	 * @throws JobBoardServiceException
	 */
	
	UserDTO createUser(EmployerProfileDTO empProfileDTO) throws JobBoardServiceException;
	
	
	/**
	 * This method is used to edit and update a customer through NetSuite
	 * and based on the status returned from Net Suite, it will
	 * update the user in the Job board.
	 * 
	 * @param Object of AccountProfileDTO, admFacilityid, userId, billing
	 * @return boolean 
	 * @throws JobBoardServiceException
	 */
	
	boolean editUser(AccountProfileDTO apd, int admFacilityid, int userId, String billing) throws JobBoardServiceException;

}
