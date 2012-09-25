/**
 * 
 */
package com.advanceweb.afc.jb.pgi.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.OrderDetailsDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
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
		// call to NetSuite WS 
		userDTO = nsSalesOrderService.createSalesOrder(userDTO);		
		// save order details 
		if(null != userDTO){
			int netSuiteStatus = Integer.parseInt(userDTO.getNsStatus());
			if(netSuiteStatus == MMJBCommonConstants.STATUS_CODE_200){
				//payment is success - save both order & inventory details
				orderDetailsDTO.setOrderStatus(netSuiteStatus);
				
				String netSuiteResponse = userDTO.getNsStatusCode().get(MMJBCommonConstants.STATUS_CODE_200);
				orderDetailsDTO.getOrderPaymentDTO().setTransactionResponse(netSuiteResponse);
				
				int start = netSuiteResponse.lastIndexOf("(");
				int end = netSuiteResponse.lastIndexOf(")");				
				String transactionId = netSuiteResponse.substring(start+1, end);
				
				orderDetailsDTO.getOrderPaymentDTO().setTransactionDate(new Date());
				orderDetailsDTO.getOrderPaymentDTO().setTransactionId(transactionId);
				paymentGatewayDao.saveOrderDetails(orderDetailsDTO);
				
				LOGGER.error("Transaction success : "+userDTO.getNsStatusCode());
			}
			else{
				
				//payment is failed - save only order
				orderDetailsDTO.setOrderStatus(netSuiteStatus);
				
				String netSuiteResponse = userDTO.getNsStatusCode().get(netSuiteStatus);
				orderDetailsDTO.getOrderPaymentDTO().setTransactionResponse(netSuiteResponse);
				orderDetailsDTO.getOrderPaymentDTO().setTransactionDate(new Date());
				
				
				//orderDetailsDTO.setOrderPaymentDTO(null);
				paymentGatewayDao.saveOrderDetails(orderDetailsDTO);
				LOGGER.error("Transaction failed :"+userDTO.getNsStatusCode());
			}	
		}	
		return userDTO;
	}
}
