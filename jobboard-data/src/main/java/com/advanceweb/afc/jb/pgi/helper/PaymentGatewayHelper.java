package com.advanceweb.afc.jb.pgi.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.common.AddOnDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.OrderPaymentDTO;
import com.advanceweb.afc.jb.common.ResumePackageDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.data.entities.AdmOrderAddress;
import com.advanceweb.afc.jb.data.entities.AdmOrderHeader;
import com.advanceweb.afc.jb.data.entities.AdmOrderItem;
import com.advanceweb.afc.jb.data.entities.AdmOrderPayment;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;


/**
 * @author muralikc
 * 
 */

@Repository("paymentGatewayHelper")
public class PaymentGatewayHelper {

	/**
	 * Converting AccountAddressDAO to DTO
	 * 
	 * @param entity
	 * @return
	 */
	public AccountAddressDTO convertAccountAddressDaoToDto(
			AdmFacilityContact entity) {
		AccountAddressDTO admFacilityContactDTO = new AccountAddressDTO();

		if (entity != null) {
			admFacilityContactDTO.setFirstName(entity.getFirstName());
			admFacilityContactDTO.setLastName(entity.getLastName());
			admFacilityContactDTO.setMiddleName(entity.getMiddleName());
			admFacilityContactDTO.setEmail(entity.getEmail());
			admFacilityContactDTO.setCompany(entity.getCompany());
			admFacilityContactDTO.setStreetAddress(entity.getStreet());
			admFacilityContactDTO.setCityOrTown(entity.getCity());
			admFacilityContactDTO.setState(entity.getState());
			admFacilityContactDTO.setCountry(entity.getCountry());
			admFacilityContactDTO.setZipCode(entity.getPostcode());
			admFacilityContactDTO.setPhone(entity.getPhone());
		}
		return admFacilityContactDTO;

	}

	/**
	 * Converting BillingAddressDAO to DTo
	 * 
	 * @param entity
	 * @return
	 */
	public AccountAddressDTO convertBillingAddressDaoToDto(
			AdmFacilityContact entity) {
		AccountAddressDTO billingAddressDTO = new AccountAddressDTO();
		if (entity != null) {
			billingAddressDTO.setFirstName(entity.getFirstName());
			billingAddressDTO.setLastName(entity.getLastName());
			billingAddressDTO.setCityOrTown(entity.getCity());
			billingAddressDTO.setCountry(entity.getCountry());
			billingAddressDTO.setState(entity.getState());
			billingAddressDTO.setStreetAddress(entity.getStreet());
			billingAddressDTO.setZipCode(entity.getPostcode());
			billingAddressDTO.setFacilityContactId(entity.getFacilityContactId());
			billingAddressDTO.setCreateDate(entity.getCreateDt());
		}

		return billingAddressDTO;

	}

	/**
	 * Converting BillingAddressDTO to Entity
	 * 
	 * @param billingAddressDTO
	 * @return
	 */
	public AdmFacilityContact convertBillingAddressDtoToEntity(
			AccountAddressDTO billingAddressDTO) {
		AdmFacilityContact admFacilityContact = new AdmFacilityContact();
		if (billingAddressDTO != null) {
			admFacilityContact.setFirstName(billingAddressDTO
					.getFirstName());
			admFacilityContact.setLastName(billingAddressDTO.getLastName());
			admFacilityContact.setCity(billingAddressDTO.getCityOrTown());
			admFacilityContact.setCountry(billingAddressDTO.getCountry());
			admFacilityContact.setState(billingAddressDTO.getState());
			admFacilityContact.setStreet(billingAddressDTO
					.getStreetAddress());
			admFacilityContact.setPostcode(billingAddressDTO.getZipCode());
		}

		return admFacilityContact;

	}
	
	
	/**
	 * Converting BillingAddressDTO to Entity
	 * 
	 * @param billingAddressDTO
	 * @return
	 */
	public AdmFacilityContact convertBillingDataAddressDtoToEntity(
			AccountBillingDTO billingAddressDTO) {
		AdmFacilityContact admFacilityContact = new AdmFacilityContact();
		
		admFacilityContact.setAdmFacility(new AdmFacility());
		if (billingAddressDTO != null) {
			admFacilityContact.setFirstName(billingAddressDTO.getFirstName());
			admFacilityContact.setLastName(billingAddressDTO.getLastName());
			admFacilityContact.setCity(billingAddressDTO.getCity());
			admFacilityContact.setCountry(billingAddressDTO.getCountry());
			admFacilityContact.setState(billingAddressDTO.getState());
			admFacilityContact.setStreet(billingAddressDTO.getStreet());
			admFacilityContact.setPostcode(billingAddressDTO.getZipCode());
			admFacilityContact.setEmail(billingAddressDTO.getEmail());
			admFacilityContact.setPhone(billingAddressDTO.getPhone());
			admFacilityContact.setCompany(billingAddressDTO.getCompanyName());
			admFacilityContact.setCreateDt(billingAddressDTO.getCreateDate());
		
//			admFacilityContact.getAdmFacility().setFacilityId(billingAddressDTO.getFacilityId());
			
			//System.err.println("dhfgg");
		}

		return admFacilityContact;

	}
	
	/**
	 * @param admOrderHeader
	 * @param jobPostingPlanDTOList
	 * @return
	 */
	public List<AdmOrderItem> transformToAdmOrderItemList(AdmOrderHeader admOrderHeader, List<JobPostingPlanDTO> jobPostingPlanDTOList, 
			String orderStatusStr){
		
		List<AdmOrderItem> admOrderItemList = new ArrayList<AdmOrderItem>();
		AdmOrderItem admOrderItem = null;
		int itemNumber = 0;
		
		for(JobPostingPlanDTO jobPostingPlanDTO : jobPostingPlanDTOList){
			admOrderItem = new AdmOrderItem();
			
			admOrderItem.setAdmOrderHeader(admOrderHeader);
			admOrderItem.setPrice(Float.parseFloat(jobPostingPlanDTO.getJobPostPlanCretitAmt()));
			admOrderItem.setProductId(Integer.parseInt(jobPostingPlanDTO.getJobPostPlanId()));
			admOrderItem.setProductType(MMJBCommonConstants.JOB_TYPE);
			admOrderItem.setProductName(jobPostingPlanDTO.getJobPostPlanName());
			admOrderItem.setQtyOrdered(jobPostingPlanDTO.getQuanity());
			admOrderItem.setOrderStatus(orderStatusStr);
			admOrderItem.setItemNumber(++itemNumber);
			admOrderItemList.add(admOrderItem);
			
			for(AddOnDTO addOnDTO : jobPostingPlanDTO.getAddOnDTOList()){
				admOrderItem = new AdmOrderItem();
				admOrderItem.setAdmOrderHeader(admOrderHeader);
				admOrderItem.setPrice(Float.parseFloat(addOnDTO.getAddOnCreditAmt()));
				admOrderItem.setProductId(Integer.parseInt(addOnDTO.getAddOnId()));
				admOrderItem.setProductType(MMJBCommonConstants.JOB_TYPE_ADDON);
				admOrderItem.setProductName(addOnDTO.getAddOnName());
				admOrderItem.setQtyOrdered(jobPostingPlanDTO.getQuanity());
				admOrderItem.setOrderStatus(orderStatusStr);
				admOrderItem.setItemNumber(itemNumber);
				admOrderItemList.add(admOrderItem);
			}
		}
		return admOrderItemList;
	}
	
	/**
	 * @param admOrderHeader
	 * @param resSearchPackageDTOList
	 * @return
	 */
	public List<AdmOrderItem> transformToAdmOrderItemList(AdmOrderHeader admOrderHeader,String orderStatusStr, List<ResumePackageDTO> resSearchPackageDTOList){
		
		List<AdmOrderItem> admOrderItemList = new ArrayList<AdmOrderItem>();
		AdmOrderItem admOrderItem = null;
		int itemNumber = 0;
		
		for(ResumePackageDTO resSearchPackageDTO : resSearchPackageDTOList){
			admOrderItem = new AdmOrderItem();
			
			admOrderItem.setAdmOrderHeader(admOrderHeader);
			admOrderItem.setPrice(resSearchPackageDTO.getPriceAmt());
			admOrderItem.setProductId(resSearchPackageDTO.getPackageId());
			admOrderItem.setProductType(resSearchPackageDTO.getPackageType());
			admOrderItem.setProductName(resSearchPackageDTO.getPackageName());
			admOrderItem.setQtyOrdered(resSearchPackageDTO.getQuantity());
			admOrderItem.setOrderStatus(orderStatusStr);
			admOrderItem.setItemNumber(++itemNumber);
			admOrderItemList.add(admOrderItem);
		}
		return admOrderItemList;
	}
	
	/**
	 * @param admOrderHeader
	 * @param orderAddressDTO
	 * @return
	 */
	public AdmOrderAddress transformToAdmOrderAddress(AdmOrderHeader admOrderHeader, AccountAddressDTO orderAddressDTO){
		AdmOrderAddress admOrderAddress = new AdmOrderAddress();
		admOrderAddress.setAdmOrderHeader(admOrderHeader);
		admOrderAddress.setFirstName(orderAddressDTO.getFirstName());
		admOrderAddress.setLastName(orderAddressDTO.getLastName());
		admOrderAddress.setStreet(orderAddressDTO.getStreetAddress());
		admOrderAddress.setCity(orderAddressDTO.getCityOrTown());
		admOrderAddress.setAddressType("BILLING");
		admOrderAddress.setPostcode(orderAddressDTO.getZipCode());
		admOrderAddress.setPhone(orderAddressDTO.getPhone());
		//admOrderAddress.setEmail(orderAddressDTO.getEmail());
		admOrderAddress.setState(orderAddressDTO.getState());
		admOrderAddress.setCountry(orderAddressDTO.getCountry());
		//admOrderAddress.setCompany(orderAddressDTO.getCompany());
		return admOrderAddress;
	}
	
	
	/**
	 * @param admOrderHeader
	 * @param orderPaymentDTO
	 * @return admOrderPayment
	 */
	public AdmOrderPayment transformToAdmOrderPayment(AdmOrderHeader admOrderHeader, OrderPaymentDTO orderPaymentDTO, int orderStatus){
		AdmOrderPayment admOrderPayment = new AdmOrderPayment(); 
		admOrderPayment.setAdmOrderHeader(admOrderHeader);
		admOrderPayment.setMethod(orderPaymentDTO.getMethod());
		
		if(MMJBCommonConstants.INVOICE.equals(admOrderPayment.getMethod())){
			admOrderPayment.setPaymentNumber(Integer.parseInt(orderPaymentDTO.getPaymentNumber()));
		}
		
		if(MMJBCommonConstants.STATUS_CODE_200 == orderStatus){
			admOrderPayment.setPaidAmt(Float.parseFloat(orderPaymentDTO.getPaidAmount()));
			admOrderPayment.setTransactionId(orderPaymentDTO.getTransactionId());
		}
		
		admOrderPayment.setTranResponse(orderPaymentDTO.getTransactionResponse());
		admOrderPayment.setTransDate(orderPaymentDTO.getTransactionDate());
		return admOrderPayment;
	}
}
