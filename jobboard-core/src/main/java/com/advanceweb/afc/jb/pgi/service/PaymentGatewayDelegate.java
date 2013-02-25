/**
 * 
 */
package com.advanceweb.afc.jb.pgi.service;

import com.advanceweb.afc.jb.common.OrderDetailsDTO;
import com.advanceweb.afc.jb.common.UserDTO;

/**
 * @author anilm
 *
 */
public interface PaymentGatewayDelegate {
	
	/**
	 * Creates the order.
	 *
	 * @param orderDetailsDTO the order details dto
	 * @return the user dto
	 */
	UserDTO createOrder(OrderDetailsDTO orderDetailsDTO); 
}
