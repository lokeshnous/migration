package com.advanceweb.afc.jb.pgi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.AddOnDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.OrderDetailsDTO;
import com.advanceweb.afc.jb.common.OrderPaymentDTO;
import com.advanceweb.afc.jb.common.SalesOrderDTO;
import com.advanceweb.afc.jb.common.SalesItemDTO;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;

public class PaymentGatewayServiceTest extends ServiceTest{

	@Autowired
	private PaymentGatewayService paymentGatewayService;
	
	@Autowired
	private JobPostService employerJobPost;
	
	
	/**
	 * This method is used to test the Save Order details
	 * @param 
	 * @return void
	 */
	@Test
	public void createOrderToNetSuite(){
		OrderDetailsDTO orderDetailsDTO = getOrderDetails();
//		Assert.assertTrue(paymentGatewayService.createOrder(orderDetailsDTO));
	}

	private OrderDetailsDTO getOrderDetails() {
		//Fetching the available job_types 
		List<JobPostingPlanDTO> jobPostingPlanDTOList = employerJobPost.getJobPostingPlans();
		
		//Selection of job_type, addons, quantity 
		jobPostingPlanDTOList.get(0).setQuanity(2);
		jobPostingPlanDTOList.get(0).getAddOnDTOList().remove(0);
		jobPostingPlanDTOList.get(0).getAddOnDTOList().remove(0);
		jobPostingPlanDTOList.get(0).getAddOnDTOList().remove(0);
		
		//jobPostingPlanDTOList.remove(1);
		jobPostingPlanDTOList.get(1).setQuanity(3);
		jobPostingPlanDTOList.get(1).getAddOnDTOList().remove(0);
		//jobPostingPlanDTOList.get(1).getAddOnDTOList().remove(1);
		//jobPostingPlanDTOList.get(1).getAddOnDTOList().remove(2);
		
		int userId = 1;
		int facilityId = 432;
		int orderTotal = 1600;
		int nsCustomeId = 459468;
		
		OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
		orderDetailsDTO.setUserId(userId);
		orderDetailsDTO.setFacilityId(facilityId);
		orderDetailsDTO.setJobPostingPlanDTOList(jobPostingPlanDTOList);
		orderDetailsDTO.setNsCustomeId(nsCustomeId);
		
		//fetching an address from db to save as order address 
		AccountAddressDTO accountAddressDTO = paymentGatewayService.getConatactByFacilityId(facilityId);
		orderDetailsDTO.setOrderAddressDTO(accountAddressDTO);
		
		//payment details will come from netsuite  
		OrderPaymentDTO orderPaymentDTO = new OrderPaymentDTO();
		orderPaymentDTO.setMethod("ccp");
		//orderPaymentDTO.setMethod("inv");
		orderPaymentDTO.setPaidAmount(String.valueOf(orderTotal));
		orderPaymentDTO.setPaymentNumber("1234");
		orderPaymentDTO.setTransactionDate(new Date());
		orderPaymentDTO.setTransactionId("1111");
		orderPaymentDTO.setTransactionResponse("transactionResponse");
		orderDetailsDTO.setOrderPaymentDTO(orderPaymentDTO);
		
		SalesOrderDTO salesOrderDTO  = new SalesOrderDTO();
		
		salesOrderDTO.setCcName("Alain Gendre");
		salesOrderDTO.setCardType("5"); // 5 - visa card
		salesOrderDTO.setCcNumber("4111111111111111");
		salesOrderDTO.setCcExpiredate("12/2014");
		salesOrderDTO.setPaymentMethod("ccp");
		salesOrderDTO.setCcStreet("Alain Gendre");
		salesOrderDTO.setCcZipcode("760002");
		
		//salesOrderDTO.setPaymentMethod("inv");
		
		//Add the selected Items 
		List<SalesItemDTO> salesItemDTOList = transformToSalesItemDTO(jobPostingPlanDTOList);
		salesOrderDTO.setSalesItemDTOList(salesItemDTOList);
		orderDetailsDTO.setSalesOrderDTO(salesOrderDTO);
		orderDetailsDTO.setOrderTotal(orderTotal);
		return orderDetailsDTO;
	}

	private List<SalesItemDTO> transformToSalesItemDTO(List<JobPostingPlanDTO> jobPostingPlanDTOList){
		List<SalesItemDTO> salesItemDTOList = new ArrayList<SalesItemDTO>();
		SalesItemDTO salesItemDTO = null;
		for(JobPostingPlanDTO jobPostingPlanDTO : jobPostingPlanDTOList){
			salesItemDTO = new SalesItemDTO();
			salesItemDTO.setItem(jobPostingPlanDTO.getJobPostNetSuiteId());
			salesItemDTO.setQuantity(String.valueOf(jobPostingPlanDTO.getQuanity()));
			salesItemDTOList.add(salesItemDTO);
			
			for(AddOnDTO addOnDTO : jobPostingPlanDTO.getAddOnDTOList()){
				salesItemDTO = new SalesItemDTO();
				salesItemDTO.setItem(addOnDTO.getAddOnNetSuiteId());
				salesItemDTO.setQuantity(String.valueOf(jobPostingPlanDTO.getQuanity()));
				salesItemDTOList.add(salesItemDTO);
			}
		}
		return salesItemDTOList;
	}
	
}
