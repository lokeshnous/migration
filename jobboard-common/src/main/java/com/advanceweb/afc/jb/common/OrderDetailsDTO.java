/**
 * 
 */
package com.advanceweb.afc.jb.common;

import java.util.List;

import com.advanceweb.afc.jb.pgi.AccountAddressDTO;

/**
 * @author anilm
 *
 */
public class OrderDetailsDTO {
	
	private int userId;
	private int facilityId;
	private int nsCustomeId;
	private List<JobPostingPlanDTO> jobPostingPlanDTOList;
	private AccountAddressDTO orderAddressDTO;
	private OrderPaymentDTO orderPaymentDTO;
	private SalesOrderDTO salesOrderDTO;
	private int orderTotal;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	public int getNsCustomeId() {
		return nsCustomeId;
	}
	public void setNsCustomeId(int nsCustomeId) {
		this.nsCustomeId = nsCustomeId;
	}
	public List<JobPostingPlanDTO> getJobPostingPlanDTOList() {
		return jobPostingPlanDTOList;
	}
	public void setJobPostingPlanDTOList(
			List<JobPostingPlanDTO> jobPostingPlanDTOList) {
		this.jobPostingPlanDTOList = jobPostingPlanDTOList;
	}
	public AccountAddressDTO getOrderAddressDTO() {
		return orderAddressDTO;
	}
	public void setOrderAddressDTO(AccountAddressDTO orderAddressDTO) {
		this.orderAddressDTO = orderAddressDTO;
	}
	public OrderPaymentDTO getOrderPaymentDTO() {
		return orderPaymentDTO;
	}
	public void setOrderPaymentDTO(OrderPaymentDTO orderPaymentDTO) {
		this.orderPaymentDTO = orderPaymentDTO;
	}
	public SalesOrderDTO getSalesOrderDTO() {
		return salesOrderDTO;
	}
	public void setSalesOrderDTO(SalesOrderDTO salesOrderDTO) {
		this.salesOrderDTO = salesOrderDTO;
	}
	public int getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}
}
