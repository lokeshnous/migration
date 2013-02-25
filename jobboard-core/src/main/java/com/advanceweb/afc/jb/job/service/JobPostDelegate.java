/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.service;

import com.advanceweb.afc.jb.common.UserDTO;

public interface JobPostDelegate {
	
	/**
	 * Gets the nS customer details.
	 *
	 * @param nsCustomerID the ns customer id
	 * @return the nS customer details
	 */
	UserDTO getNSCustomerDetails(int nsCustomerID);
}
