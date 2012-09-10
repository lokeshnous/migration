package com.advanceweb.afc.jb.netsuite.service;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardNetSuiteServiceException;


/**
 * This interface helps to call the different WebServices from NetSuite.
 * 
 * @author Reetesh R N
 * @version 1.0
 * @since 04 Sept 2012
 * 
 */

public interface NSCustomerService {
	
	/**
	 * This method is used to create a customer through NetSuite.
	 * 
	 * @param Object
	 *            of UserDTO
	 * @return Object of UserDTO
	 * @throws JobBoardNetSuiteServiceException
	 */
	
	UserDTO createCustomer(UserDTO userDTO) throws JobBoardNetSuiteServiceException;
	
	
	/**
	 * This method is used to edit a customer through NetSuite.
	 * 
	 * @param Object
	 *            of UserDTO
	 * @return Object of UserDTO
	 * @throws JobBoardNetSuiteServiceException
	 */

	UserDTO editCustomer(UserDTO userDTO) throws JobBoardNetSuiteServiceException;
	
}
