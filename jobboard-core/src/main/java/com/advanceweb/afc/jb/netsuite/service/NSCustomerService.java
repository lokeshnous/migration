package com.advanceweb.afc.jb.netsuite.service;

import java.util.List;

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
	
	/**
	 * This method is used to get the customer details through NetSuite.
	 * @param userDTO
	 * @return userDTO
	 * @throws JobBoardNetSuiteServiceException 
	 */
	
	UserDTO getNSCustomerDetails(UserDTO userDTO) throws JobBoardNetSuiteServiceException;
	
	/**
	 * This method is used to get the Featured employer package start/End dates.
	 * 
	 * @param userDTO
	 * @return userDTO
	 * @throws JobBoardNetSuiteServiceException
	 */
	
	UserDTO getNSFeatureDates(UserDTO userDTO) throws JobBoardNetSuiteServiceException;
	
	/**
	 * This method is used to get the customer purchased packages through
	 * NetSuite.
	 * 
	 * @param userDTO
	 * @return List<String>
	 * @throws JobBoardNetSuiteServiceException
	 */

	List<String> getNSCustomerPackages(UserDTO userDTO) throws JobBoardNetSuiteServiceException;
}
