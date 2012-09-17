package com.advanceweb.afc.jb.netsuite.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.helpers.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.SalesItemDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.JsonUtil;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.netsuite.NSCustomer;
import com.advanceweb.afc.jb.netsuite.NSItem;
import com.advanceweb.afc.jb.netsuite.NetSuiteHelper;
import com.advanceweb.afc.jb.netsuite.service.NSSalesOrderService;
import com.advanceweb.afc.jb.netsuite.service.NetSuiteMethod;

/**
 * @author anilm
 * @version 1.0
 * @since 
 */
@Service("nsSalesOrderService")
public class NSSalesOrderServiceImpl implements NSSalesOrderService{
	
	private static final Logger LOGGER = Logger
			.getLogger(NSSalesOrderServiceImpl.class);
	
	@Autowired
	private NetSuiteMethod netSuiteMethod;
	
	@Autowired
	private NetSuiteHelper netSuiteHelper;
	
	
	/**
	 * This method is used to create a customer through NetSuite
	 * 
	 * @param Object
	 *            in JSON format
	 * @return String in JSON format
	 */

	public UserDTO createSalesOrder(UserDTO userDTO) {
		
		NSCustomer nsCustomer = createNSCustomer(userDTO);	
		
		String jsonCustomer = JsonUtil.toJson(nsCustomer);
		
		LOGGER.info("Json for Customer=>"+jsonCustomer.toLowerCase());
		
		Map<String, String> queryparamMap = createQueryMap();
		
		Response response = netSuiteMethod.netSuitePost(queryparamMap, jsonCustomer.toLowerCase());
		
		return getJSONFromResponse(response);
	}
	
	
	/**
	 * 
	 * @param empProfileDTO
	 * @return
	 */
	
	private NSCustomer createNSCustomer(UserDTO userDTO){
		
		NSCustomer nsCustomer = new NSCustomer();
		List<NSItem> nsItemList = new ArrayList<NSItem>();
		NSItem nsItem = null ; 
		for(SalesItemDTO salesItemDTO : userDTO.getSalesOrderDTO().getSalesItemDTOList()){
			nsItem = new NSItem();
			nsItem.setItem(salesItemDTO.getItem());
			nsItem.setQuantity(salesItemDTO.getQuantity());
			nsItemList.add(nsItem);
		}
		nsCustomer.setEntity(String.valueOf(userDTO.getNsCustomerID()));
		nsCustomer.setItem(nsItemList);
		if(MMJBCommonConstants.INVOICE.equals(userDTO.getSalesOrderDTO().getPaymentMethod())){
			nsCustomer.setPaymentMethod(userDTO.getSalesOrderDTO().getPaymentMethod());
		}
		else if(MMJBCommonConstants.CREDIT_CARD.equals(userDTO.getSalesOrderDTO().getPaymentMethod())){
			nsCustomer.setPaymentMethod(userDTO.getSalesOrderDTO().getPaymentMethod());
			nsCustomer.setCardType(userDTO.getSalesOrderDTO().getCardType());
			nsCustomer.setCcName(userDTO.getSalesOrderDTO().getCcName());
			nsCustomer.setCcNumber(userDTO.getSalesOrderDTO().getCcNumber());
			nsCustomer.setCcExpireDate(userDTO.getSalesOrderDTO().getCcExpiredate());
			nsCustomer.setCcStreet(userDTO.getSalesOrderDTO().getCcStreet());
			nsCustomer.setCcZipCode(userDTO.getSalesOrderDTO().getCcZipcode());
		}	
		return nsCustomer;
	}
	
	
	/**
	 * 
	 * @return
	 */
	
	private Map<String, String> createQueryMap() {
		
		Properties entries = netSuiteHelper.getNSProperties();
		Map<String, String> queryParamMap = new HashMap<String, String>();
		queryParamMap.put("baseUrl", entries.getProperty("baseUrl"));
		queryParamMap.put("script", entries.getProperty("scriptForCreateSalesOrder"));
		queryParamMap.put("deploy", entries.getProperty("deployForCreateSalesOrder"));
		
		return queryParamMap;
	}
	
	/**
	 * 
	 * @param response
	 * @return
	 */
	
	private UserDTO getJSONFromResponse(Response response){
		UserDTO userDTO = new UserDTO();
		String jsonResponse = null;
		Map<Integer,String> nsStatusCode = new HashMap<Integer,String>();
		try {
			jsonResponse = IOUtils.readStringFromStream((InputStream) response
					.getEntity());
			if(response.getStatus() != MMJBCommonConstants.STATUS_CODE_200 && 
					!StringUtils.isEmpty(jsonResponse) && jsonResponse.contains("error")){
				LOGGER.error("Transaction is failed :"+jsonResponse);
				nsStatusCode.put(response.getStatus(), jsonResponse);
				userDTO.setNsStatusCode(nsStatusCode);
				userDTO.setNsStatus(String.valueOf(response.getStatus()));
			}else{
				LOGGER.info("Transaction is success :"+jsonResponse);
				nsStatusCode.put(response.getStatus(), jsonResponse);
				userDTO.setNsStatusCode(nsStatusCode);
				userDTO.setNsStatus(String.valueOf(response.getStatus()));
			}

		} catch (IOException e) {
			LOGGER.error(e);
			throw new RuntimeException(
					"Failed to get a string represenation of the response", e);
		}
		
		return userDTO;
	}
	
}
