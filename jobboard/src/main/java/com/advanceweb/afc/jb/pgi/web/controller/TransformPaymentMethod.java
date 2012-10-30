package com.advanceweb.afc.jb.pgi.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.common.AddOnDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.OrderDetailsDTO;
import com.advanceweb.afc.jb.common.OrderPaymentDTO;
import com.advanceweb.afc.jb.common.ResumePackageDTO;
import com.advanceweb.afc.jb.common.SalesItemDTO;
import com.advanceweb.afc.jb.common.SalesOrderDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.web.controller.AddOnForm;
import com.advanceweb.afc.jb.employer.web.controller.JobPostingsForm;
import com.advanceweb.afc.jb.employer.web.controller.PurchaseJobPostForm;
import com.advanceweb.afc.jb.employer.web.controller.PurchaseResumeSearchForm;
import com.advanceweb.afc.jb.employer.web.controller.ResumeSearchPackageForm;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;

/**
 * This class has been created to transform dto to form & form dto 
 * @author muralikc
 * @version 1.0
 * @created Aug 27, 2012
 */
@Repository("transformPaymentMethod")
public class TransformPaymentMethod {

	/**
	 * Converting AccontAddressDTO to Form
	 * @param accountAddressDTO
	 * @return accountAddressForm
	 */
	public AccountAddressForm transformAccountAddrDtoToForm(
			AccountAddressDTO accountAddressDTO) {
		AccountAddressForm accountAddressForm = new AccountAddressForm();
		if(null != accountAddressDTO){
			accountAddressForm.setFirstName(accountAddressDTO.getFirstName());
			accountAddressForm.setLastName(accountAddressDTO.getLastName());
			accountAddressForm.setCompany(accountAddressDTO.getCompany());
			accountAddressForm.setCountry(accountAddressDTO.getCountry());
			accountAddressForm.setEmail(accountAddressDTO.getEmail());
			accountAddressForm.setPhone(accountAddressDTO.getPhone());
			accountAddressForm.setState(accountAddressDTO.getState());
			accountAddressForm.setStreetAddress(accountAddressDTO
					.getStreetAddress());
			accountAddressForm.setMiddleName(accountAddressDTO.getMiddleName());
			accountAddressForm.setZipCode(accountAddressDTO.getZipCode());
			accountAddressForm.setCityOrTown(accountAddressDTO.getCityOrTown());
		}
		return accountAddressForm;
	}

	/**
	 * Converting BillingAddressDTO to Form
	 * @param billingAddressDTO
	 * @return billingAddressForm
	 */
	public BillingAddressForm transformBillingAddressDtoToForm(
			AccountAddressDTO billingAddressDTO) {
		BillingAddressForm billingAddressForm = new BillingAddressForm();
		if(null != billingAddressDTO){
			billingAddressForm.setFnameForBillingAddr(billingAddressDTO
					.getFirstName());
			billingAddressForm.setLnameForBillingAddr(billingAddressDTO
					.getLastName());
			billingAddressForm.setCountryForBillingAddr(billingAddressDTO
					.getCountry());
			billingAddressForm.setStateBillingAddress(billingAddressDTO
					.getState());
			billingAddressForm.setCityOrTownForBillingAddr(billingAddressDTO
					.getCityOrTown());
			billingAddressForm.setZipCodeForBillingAddr(billingAddressDTO
					.getZipCode());
			billingAddressForm.setStreetForBillingAddr(billingAddressDTO
					.getStreetAddress());
			billingAddressForm.setFacilityContactId(billingAddressDTO
					.getFacilityContactId());
			billingAddressForm.setCreateDate(billingAddressDTO.getCreateDate());
		}
		return billingAddressForm;

	}

	/**
	 * Converting BillingAddressForm To DTO for saving the data
	 * @param billingAddressForm
	 * @return billingAddressDTO
	 */
	public AccountAddressDTO transformBillingAddreFormToDto(
			BillingAddressForm billingAddressForm) {
		AccountAddressDTO billingAddressDTO = new AccountAddressDTO();

		billingAddressDTO.setCityOrTown(billingAddressForm
				.getCityOrTownForBillingAddr());
		billingAddressDTO.setFacilityContactId(billingAddressForm
				.getFacilityContactId());
		billingAddressDTO.setCountry(billingAddressForm
				.getCountryForBillingAddr());
		billingAddressDTO.setFirstName(billingAddressForm
				.getFnameForBillingAddr());
		billingAddressDTO.setLastName(billingAddressForm
				.getLnameForBillingAddr());
		billingAddressDTO.setState(billingAddressForm
				.getStateBillingAddress());
		billingAddressDTO.setStreetAddress(billingAddressForm
				.getStreetForBillingAddr());
		billingAddressDTO.setZipCode(billingAddressForm
				.getZipCodeForBillingAddr());
		billingAddressDTO.setCreateDate(billingAddressForm.getCreateDate());
		return billingAddressDTO;
	}
	
	/**
	 * Converting BillingAddressForm To DTO for saving the data
	 * @param billingAddressForm
	 * @return accountBillingDTO
	 */
	public AccountBillingDTO transformDataBillingAddreFormToDto(
			BillingAddressForm billingAddressForm) {
		AccountBillingDTO accountBillingDTO = new AccountBillingDTO();
//		accountBillingDTO.setFacilityContactId(billingAddressForm.getFacilityContactId());
		accountBillingDTO.setFirstName(billingAddressForm.getFnameForBillingAddr());		
		accountBillingDTO.setLastName(billingAddressForm.getLnameForBillingAddr());
		accountBillingDTO.setStreet(billingAddressForm.getStreetForBillingAddr());
		accountBillingDTO.setCity(billingAddressForm.getCityOrTownForBillingAddr());
		accountBillingDTO.setState(billingAddressForm.getStateBillingAddress());
		accountBillingDTO.setCountry(billingAddressForm.getCountryForBillingAddr());
		accountBillingDTO.setZipCode(billingAddressForm.getZipCodeForBillingAddr());
		//accountBillingDTO.setCreateDate(billingAddressForm.getCreateDate());
		return accountBillingDTO;
	}
	
	/**
	 * This method will transform paymentGatewayForm to OrderDetailsDTO
	 * @param paymentGatewayForm
	 * @return orderDetailsDTO
	 */
	public OrderDetailsDTO transformToOrderDetailsDTO(PaymentGatewayForm paymentGatewayForm){
		
		OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
		
		List<SalesItemDTO> salesItemDTOList = new ArrayList<SalesItemDTO>();
		
		OrderPaymentDTO orderPaymentDTO = new OrderPaymentDTO();
		
		if(MMJBCommonConstants.PURCHASE_RESUME_SEARCH.equals(paymentGatewayForm.getPurchaseType())){
			PurchaseResumeSearchForm purchaseResumeSearchForm = paymentGatewayForm.getPurchaseResumeSearchForm();
			
			List<ResumePackageDTO> resSearchPackageDTOList = transformToResSearchPackageDTOList(purchaseResumeSearchForm.getResumeSearchPackageCart());
			orderDetailsDTO.setResSearchPackageDTOList(resSearchPackageDTOList);
			
			salesItemDTOList = transformToSaleItemDTO(purchaseResumeSearchForm.getResumeSearchPackageCart());
			
			orderPaymentDTO.setPaidAmount(String.valueOf(purchaseResumeSearchForm.getGrandTotal()));
			
			orderDetailsDTO.setOrderTotal(purchaseResumeSearchForm.getGrandTotal());
		}
		else if(MMJBCommonConstants.PURCHASE_JOB_POST.equals(paymentGatewayForm.getPurchaseType())){
			
			PurchaseJobPostForm purchaseJobPostForm = paymentGatewayForm.getPurchaseJobPostForm();
			
			List<JobPostingPlanDTO> jobPostingPlanDTOList = transformTojobPostingPlanDTOList(purchaseJobPostForm.getJobPostingsCart());
			orderDetailsDTO.setJobPostingPlanDTOList(jobPostingPlanDTOList);
			
			salesItemDTOList = transformToSalesItemDTO(purchaseJobPostForm.getJobPostingsCart());
			
			orderPaymentDTO.setPaidAmount(String.valueOf(purchaseJobPostForm.getGrandTotal()));
			
			orderDetailsDTO.setOrderTotal(purchaseJobPostForm.getGrandTotal());
		}
		
		AccountAddressDTO accountAddressDTO = transformToAccountAddressDTO(paymentGatewayForm.getBillingAddressForm());
		orderDetailsDTO.setOrderAddressDTO(accountAddressDTO);
		
		//payment detail will come from netsuite  
		orderPaymentDTO.setMethod(paymentGatewayForm.getPaymentMethod());
		
		if(MMJBCommonConstants.INVOICE.equals(orderPaymentDTO.getMethod())){
			orderPaymentDTO.setPaymentNumber(paymentGatewayForm.getInvoiceForm().getPurchaseOrderNo());
		}	
		
		orderDetailsDTO.setOrderPaymentDTO(orderPaymentDTO);
		
		SalesOrderDTO salesOrderDTO = transformToSalesOrderDTO(paymentGatewayForm);
		salesOrderDTO.setCcStreet(accountAddressDTO.getStreetAddress());
		salesOrderDTO.setCcZipcode(accountAddressDTO.getZipCode());
		orderDetailsDTO.setSalesOrderDTO(salesOrderDTO);
		
		salesOrderDTO.setSalesItemDTOList(salesItemDTOList);
		orderDetailsDTO.setSalesOrderDTO(salesOrderDTO);
		
		orderDetailsDTO.setPurchaseType(paymentGatewayForm.getPurchaseType());
		
		return orderDetailsDTO;
		
	}
	
	/**
	 * This method will transform BillingAddressForm to AccountAddressDTO
	 * @param addressForm
	 * @return accountAddressDTO
	 */
	private AccountAddressDTO transformToAccountAddressDTO(BillingAddressForm addressForm){
		AccountAddressDTO accountAddressDTO = new AccountAddressDTO();
		
		accountAddressDTO.setFirstName(addressForm.getFnameForBillingAddr());
		accountAddressDTO.setLastName(addressForm.getLnameForBillingAddr());
		accountAddressDTO.setStreetAddress(addressForm.getStreetForBillingAddr());
		accountAddressDTO.setCityOrTown(addressForm.getCityOrTownForBillingAddr());
		accountAddressDTO.setState(addressForm.getStateBillingAddress());
		accountAddressDTO.setCountry(addressForm.getCountryForBillingAddr());
		accountAddressDTO.setZipCode(addressForm.getZipCodeForBillingAddr());
		accountAddressDTO.setPhone(addressForm.getPhone());
		
		return accountAddressDTO;
		
	}
	
	/**
	 * This method will transform PaymentGatewayForm to SalesOrderDTO
	 * @param paymentGatewayForm
	 * @return salesOrderDTO
	 */
	private SalesOrderDTO transformToSalesOrderDTO(PaymentGatewayForm paymentGatewayForm){
		SalesOrderDTO salesOrderDTO  = new SalesOrderDTO();
		
		if(null != paymentGatewayForm.getCreditCardInfoForm()){
			CreditCardInfoForm creditCardInfoForm=  paymentGatewayForm.getCreditCardInfoForm();
			salesOrderDTO.setCcName(creditCardInfoForm.getName());
			salesOrderDTO.setCardType(creditCardInfoForm.getCardType()); // 5 - visa card
			salesOrderDTO.setCcNumber(creditCardInfoForm.getCreditCardNo());
			salesOrderDTO.setCcExpiredate(creditCardInfoForm.getExpMonth()+"/"+creditCardInfoForm.getExpYear());
			salesOrderDTO.setSecuriyCode(creditCardInfoForm.getSecuriyCode());
			salesOrderDTO.setPaymentMethod(paymentGatewayForm.getPaymentMethod());
		}
		if(null != paymentGatewayForm.getInvoiceForm()){
			salesOrderDTO.setPaymentMethod(paymentGatewayForm.getPaymentMethod());
		}
		return salesOrderDTO;
	}
	
	/**
	 * This method will transform JobPostingsForm list to SalesItemDTO
	 * @param jobPostingsCart
	 * @return salesItemDTOList
	 */
	private List<SalesItemDTO> transformToSalesItemDTO(List<JobPostingsForm> jobPostingsCart){
		List<SalesItemDTO> salesItemDTOList = new ArrayList<SalesItemDTO>();
		SalesItemDTO salesItemDTO = null;
		for(JobPostingsForm jobPostingsForm : jobPostingsCart){
			salesItemDTO = new SalesItemDTO();
			salesItemDTO.setItem(jobPostingsForm.getJobPostNetSuiteId());
			salesItemDTO.setQuantity(String.valueOf(jobPostingsForm.getQuantity()));
			salesItemDTOList.add(salesItemDTO);
			
			for(AddOnForm addOnForm : jobPostingsForm.getAddOnForm()){
				salesItemDTO = new SalesItemDTO();
				salesItemDTO.setItem(addOnForm.getAddOnNetSuiteId());
				salesItemDTO.setQuantity(String.valueOf(jobPostingsForm.getQuantity()));
				salesItemDTOList.add(salesItemDTO);
			}
		}
		return salesItemDTOList;
	}
	
	/**
	 * This method will transform ResumeSearchPackageForm list to SalesItemDTO
	 * @param resSearchPackageCart
	 * @return salesItemDTOList
	 */
	private List<SalesItemDTO> transformToSaleItemDTO(List<ResumeSearchPackageForm> resSearchPackageCart){
		List<SalesItemDTO> salesItemDTOList = new ArrayList<SalesItemDTO>();
		SalesItemDTO salesItemDTO = null;
		for(ResumeSearchPackageForm resSearchPackageForm : resSearchPackageCart){
			salesItemDTO = new SalesItemDTO();
			salesItemDTO.setItem(String.valueOf(resSearchPackageForm.getNetsuiteId()));
			salesItemDTO.setQuantity(String.valueOf(resSearchPackageForm.getQuantity()));
			salesItemDTOList.add(salesItemDTO);
			
		}
		return salesItemDTOList;
	}
	
	
	/**
	 * This method will transform JobPostingsForm list to jobPostingPlanDTO list
	 * @param jobPostingCart
	 * @return jobPostingPlanDTOList
	 */
	private List<JobPostingPlanDTO> transformTojobPostingPlanDTOList(List<JobPostingsForm> jobPostingCart){
		List<JobPostingPlanDTO> jobPostingPlanDTOList = new ArrayList<JobPostingPlanDTO>();
		
		JobPostingPlanDTO jobPostingPlanDTO = null; 
		List<AddOnDTO> addOnDTOList = null;
		AddOnDTO addOnDTO = null;  
		
		for(JobPostingsForm jobPostingsForm : jobPostingCart){
			jobPostingPlanDTO = new JobPostingPlanDTO();
			jobPostingPlanDTO.setJobPostPlanId(jobPostingsForm.getJobPostPlanId());
			jobPostingPlanDTO.setJobPostPlanName(jobPostingsForm.getJobPostPlanName());
			jobPostingPlanDTO.setJobPostPlanCretitAmt(jobPostingsForm.getJobPostPlanCretitAmt());
			jobPostingPlanDTO.setQuanity(jobPostingsForm.getQuantity());
			jobPostingPlanDTO.setJobPostNetSuiteId(jobPostingsForm.getJobPostNetSuiteId());
			
			addOnDTOList = new ArrayList<AddOnDTO>();
			for(AddOnForm addOnForm : jobPostingsForm.getAddOnForm()){
				addOnDTO = new AddOnDTO();
				addOnDTO.setAddOnId(addOnForm.getAddOnId());
				addOnDTO.setAddOnName(addOnForm.getAddOnName());
				addOnDTO.setAddOnCreditAmt(addOnForm.getAddOnCreditAmt());
				addOnDTO.setAddOnNetSuiteId(addOnForm.getAddOnNetSuiteId());
				addOnDTOList.add(addOnDTO);
			}
			jobPostingPlanDTO.setAddOnDTOList(addOnDTOList);
			jobPostingPlanDTOList.add(jobPostingPlanDTO);
		}
		return jobPostingPlanDTOList;
	}
	
	/**
	 * This method will transform ResumeSearchPackageForm list to ResumeSearchPackageDTO list
	 * @param resumeSearchPackageCart
	 * @return resSearchPackageDTOList
	 */
	private List<ResumePackageDTO> transformToResSearchPackageDTOList(List<ResumeSearchPackageForm> resumeSearchPackageCart){
		List<ResumePackageDTO> resSearchPackageDTOList = new ArrayList<ResumePackageDTO>();
		
		ResumePackageDTO resSearchPackageDTO= null;
		for(ResumeSearchPackageForm resSearchPackageForm : resumeSearchPackageCart){
			resSearchPackageDTO = new ResumePackageDTO();
			
			resSearchPackageDTO.setPackageId(resSearchPackageForm.getPackageId());
			resSearchPackageDTO.setPackageName(resSearchPackageForm.getPackageName());
			resSearchPackageDTO.setPackageType(resSearchPackageForm.getPackageType());
			resSearchPackageDTO.setDuration(resSearchPackageForm.getDuration());
			resSearchPackageDTO.setQuantity(resSearchPackageForm.getQuantity());
			resSearchPackageDTO.setPriceAmt(resSearchPackageForm.getPriceAmt());
			resSearchPackageDTOList.add(resSearchPackageDTO);
			
		}
		return resSearchPackageDTOList;
	}
	
}
