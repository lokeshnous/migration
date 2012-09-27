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
	UserDTO createOrder(OrderDetailsDTO orderDetailsDTO); 
}
