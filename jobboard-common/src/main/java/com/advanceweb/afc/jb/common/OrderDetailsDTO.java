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
	
	/** The user id. */
	private int userId;
	
	/** The facility id. */
	private int facilityId;
	
	/** The ns custome id. */
	private int nsCustomeId;
	
	/** The job posting plan dto list. */
	private List<JobPostingPlanDTO> jobPostingPlanDTOList;
	
	/** The res search package dto list. */
	private List<ResumePackageDTO> resSearchPackageDTOList;
	
	/** The order address dto. */
	private AccountAddressDTO orderAddressDTO;
	
	/** The order payment dto. */
	private OrderPaymentDTO orderPaymentDTO;
	
	/** The sales order dto. */
	private SalesOrderDTO salesOrderDTO;
	
	/** The order id. */
	private int orderId;
	
	/** The order total. */
	private float orderTotal;
	
	/** The order status. */
	private int orderStatus;
	
	/** The purchase type. */
	private String purchaseType;
	
	/** The discount item. */
	private String discountItem;
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public int getFacilityId() {
		return facilityId;
	}
	
	/**
	 * Sets the facility id.
	 *
	 * @param facilityId the new facility id
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	
	/**
	 * Gets the ns custome id.
	 *
	 * @return the ns custome id
	 */
	public int getNsCustomeId() {
		return nsCustomeId;
	}
	
	/**
	 * Sets the ns custome id.
	 *
	 * @param nsCustomeId the new ns custome id
	 */
	public void setNsCustomeId(int nsCustomeId) {
		this.nsCustomeId = nsCustomeId;
	}
	
	/**
	 * Gets the job posting plan dto list.
	 *
	 * @return the job posting plan dto list
	 */
	public List<JobPostingPlanDTO> getJobPostingPlanDTOList() {
		return jobPostingPlanDTOList;
	}
	
	/**
	 * Sets the job posting plan dto list.
	 *
	 * @param jobPostingPlanDTOList the new job posting plan dto list
	 */
	public void setJobPostingPlanDTOList(
			List<JobPostingPlanDTO> jobPostingPlanDTOList) {
		this.jobPostingPlanDTOList = jobPostingPlanDTOList;
	}
	
	/**
	 * Gets the order address dto.
	 *
	 * @return the order address dto
	 */
	public AccountAddressDTO getOrderAddressDTO() {
		return orderAddressDTO;
	}
	
	/**
	 * Sets the order address dto.
	 *
	 * @param orderAddressDTO the new order address dto
	 */
	public void setOrderAddressDTO(AccountAddressDTO orderAddressDTO) {
		this.orderAddressDTO = orderAddressDTO;
	}
	
	/**
	 * Gets the order payment dto.
	 *
	 * @return the order payment dto
	 */
	public OrderPaymentDTO getOrderPaymentDTO() {
		return orderPaymentDTO;
	}
	
	/**
	 * Sets the order payment dto.
	 *
	 * @param orderPaymentDTO the new order payment dto
	 */
	public void setOrderPaymentDTO(OrderPaymentDTO orderPaymentDTO) {
		this.orderPaymentDTO = orderPaymentDTO;
	}
	
	/**
	 * Gets the sales order dto.
	 *
	 * @return the sales order dto
	 */
	public SalesOrderDTO getSalesOrderDTO() {
		return salesOrderDTO;
	}
	
	/**
	 * Sets the sales order dto.
	 *
	 * @param salesOrderDTO the new sales order dto
	 */
	public void setSalesOrderDTO(SalesOrderDTO salesOrderDTO) {
		this.salesOrderDTO = salesOrderDTO;
	}
	
	/**
	 * Gets the order total.
	 *
	 * @return the order total
	 */
	public float getOrderTotal() {
		return orderTotal;
	}
	
	/**
	 * Sets the order total.
	 *
	 * @param orderTotal the new order total
	 */
	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}
	
	/**
	 * Gets the order status.
	 *
	 * @return the order status
	 */
	public int getOrderStatus() {
		return orderStatus;
	}
	
	/**
	 * Sets the order status.
	 *
	 * @param orderStatus the new order status
	 */
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	public int getOrderId() {
		return orderId;
	}
	
	/**
	 * Sets the order id.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * Gets the res search package dto list.
	 *
	 * @return the res search package dto list
	 */
	public List<ResumePackageDTO> getResSearchPackageDTOList() {
		return resSearchPackageDTOList;
	}
	
	/**
	 * Sets the res search package dto list.
	 *
	 * @param resSearchPackageDTOList the new res search package dto list
	 */
	public void setResSearchPackageDTOList(
			List<ResumePackageDTO> resSearchPackageDTOList) {
		this.resSearchPackageDTOList = resSearchPackageDTOList;
	}
	
	/**
	 * Gets the purchase type.
	 *
	 * @return the purchase type
	 */
	public String getPurchaseType() {
		return purchaseType;
	}
	
	/**
	 * Sets the purchase type.
	 *
	 * @param purchaseType the new purchase type
	 */
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
