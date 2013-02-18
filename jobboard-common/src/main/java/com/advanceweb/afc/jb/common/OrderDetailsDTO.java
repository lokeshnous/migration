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
	private List<ResumePackageDTO> resSearchPackageDTOList;
	private AccountAddressDTO orderAddressDTO;
	private OrderPaymentDTO orderPaymentDTO;
	private SalesOrderDTO salesOrderDTO;
	private int orderId;
	private float orderTotal;
	private int orderStatus;
	private String purchaseType;
	private String discountItem;
	
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
	public float getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<ResumePackageDTO> getResSearchPackageDTOList() {
		return resSearchPackageDTOList;
	}
	public void setResSearchPackageDTOList(
			List<ResumePackageDTO> resSearchPackageDTOList) {
		this.resSearchPackageDTOList = resSearchPackageDTOList;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	/**
	 * @return the discountItem
	 */
	public String getDiscountItem() {
		return discountItem;
	}
	/**
	 * @param discountItem the discountItem to set
	 */
	public void setDiscountItem(String discountItem) {
		this.discountItem = discountItem;
	}
}
