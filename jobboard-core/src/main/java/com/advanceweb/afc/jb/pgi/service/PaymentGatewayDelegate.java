/**
 * 
 */
package com.advanceweb.afc.jb.pgi.service;

import com.advanceweb.afc.jb.common.OrderDetailsDTO;

/**
 * @author anilm
 *
 */
public interface PaymentGatewayDelegate {
	public boolean createOrder(OrderDetailsDTO orderDetailsDTO); 
}
