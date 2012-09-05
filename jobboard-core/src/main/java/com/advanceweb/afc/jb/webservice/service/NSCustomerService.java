package com.advanceweb.afc.jb.webservice.service;

public interface NSCustomerService {
	
	/**
	  * This method is used to create a customer through NetSuite
	  * @param Object in JSON format
	  * @return String in JSON format
	  */
	public String createCustomer(Object jsonCustomer);
	

}
