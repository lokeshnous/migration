package com.advanceweb.afc.jb.webservice.service.impl;


/**
 * This service interface helps to call the different WebServices 
 * from NetSuite.
 * @author Reetesh R N
 * @version 1.0
 * @since 04 Sept 2012
 * 
 */

public interface NetSuiteService {
	
	
	 /**
	  * This method is used to create a customer through NetSuite
	  * @param Object in JSON format
	  * @return String in JSON format
	  */
	public String createCustomer(Object jsonCustomer);
	

}
