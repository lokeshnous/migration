/**
 * 
 */
package com.advanceweb.afc.jb.pgi.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.OrderDetailsDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.employer.service.impl.EmployerDelegateImpl;
import com.advanceweb.afc.jb.netsuite.service.NSSalesOrderService;
import com.advanceweb.afc.jb.pgi.dao.PaymentGatewayDao;
import com.advanceweb.afc.jb.pgi.service.PaymentGatewayDelegate;

/**
 * @author anilm
 *
 */
@Service("paymentGatewayDelegate")
public class PaymentGatewayDelegateImpl implements PaymentGatewayDelegate{
	private static final Logger LOGGER = Logger
			.getLogger(EmployerDelegateImpl.class);
	
	@Autowired(required = true)
	private PaymentGatewayDao paymentGatewayDao;
	
	@Autowired(required = true)
	
	private NSSalesOrderService nsSalesOrderService;
	
	@Override
	public UserDTO createOrder(OrderDetailsDTO orderDetailsDTO) {
		
		//get the entity id from DB using facility id 
		UserDTO userDTO = new UserDTO();
		userDTO.setNsCustomerID(orderDetailsDTO.getNsCustomeId());
		userDTO.setSalesOrderDTO(orderDetailsDTO.getSalesOrderDTO());
		boolean status = false;
		// call to NetSuite WS 
		userDTO = nsSalesOrderService.createSalesOrder(userDTO);		
		userDTO.setNsStatus("true");
		// save order details 
		if(null != userDTO){
			if(userDTO.getNsStatus().equals("true")){
				//payment is success - save both order & inventory details 
				paymentGatewayDao.saveOrderDetails(orderDetailsDTO);
				paymentGatewayDao.saveInventoryDetails(orderDetailsDTO);
				LOGGER.error("Transaction success : "+userDTO.getNsStatusCode());
				status = true;
			}
			else{
				//payment is failed - save only order
				orderDetailsDTO.setOrderPaymentDTO(null);
				paymentGatewayDao.saveOrderDetails(orderDetailsDTO);
				LOGGER.error("Transaction failed :"+userDTO.getNsStatusCode());
				status = false;
			}	
		}	
		return userDTO;
	}
}
