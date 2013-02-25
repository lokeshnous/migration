/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.netsuite.service;

import com.advanceweb.afc.jb.common.UserDTO;

public interface NSSalesOrderService {
	
	/**
	 * Creates the sales order.
	 *
	 * @param userDTO the user dto
	 * @return the user dto
	 */
	UserDTO createSalesOrder(UserDTO userDTO);
}
 