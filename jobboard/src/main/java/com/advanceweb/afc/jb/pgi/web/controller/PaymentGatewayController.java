package com.advanceweb.afc.jb.pgi.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.OrderDetailsDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.ManageFeatureEmployerProfile;
import com.advanceweb.afc.jb.employer.web.controller.JobPostingsForm;
import com.advanceweb.afc.jb.employer.web.controller.PurchaseJobPostForm;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;
import com.advanceweb.afc.jb.pgi.service.PaymentGatewayService;

/**
 * @author muralikc
 * 
 */
@Controller
@RequestMapping("/pgiController")
@SessionAttributes("paymentGatewayForm")
public class PaymentGatewayController {
	private static final String STATUS_CODE = "statusCode";

	private static final Logger LOGGER = Logger
			.getLogger(PaymentGatewayController.class);
	
	@Autowired
	private PaymentGatewayService paymentGatewayService;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	private TransformPaymentMethod transformPaymentMethod;
	
	@Autowired
	private ManageFeatureEmployerProfile manageFeatureEmployerProfile;
	
	@Autowired
	private PaymentGatewayValidation paymentGatewayValidation;
	
	@RequestMapping(value = "/callPaymentMethod", method = RequestMethod.GET)
	public ModelAndView callPaymentMethod(@Valid PaymentGatewayForm paymentGatewayForm,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		
		int facilityId = (Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID);
		int nsCustomerId = manageFeatureEmployerProfile.getNSCustomerIDFromAdmFacility(facilityId);
		UserDTO userDTO = manageFeatureEmployerProfile.getNSCustomerDetails(nsCustomerId);
		
		paymentGatewayForm.setNsCustomerId(nsCustomerId);
		InvoiceForm invoiceForm = new InvoiceForm();
		
		invoiceForm.setInvoiceEnabled(userDTO.isInvoiceEnabled());
		paymentGatewayForm.setInvoiceForm(invoiceForm);
		model.addObject("paymentGatewayForm",paymentGatewayForm);
		
		paymentGatewayForm.setPurchaseJobPostForm((PurchaseJobPostForm)session.getAttribute("purchaseJobPostForm"));
		model.setViewName("gatewayPaymentMethod");
		return model;
	}
	
	@RequestMapping(value = "/paymentMethodForBack", method = RequestMethod.GET)
	public ModelAndView gatewayPaymentMethod(@Valid PaymentGatewayForm paymentGatewayForm,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);
		model.setViewName("gatewayPaymentMethod");
		return model;
	}

	
	@RequestMapping(value = "/paymentBillingInfo", method = RequestMethod.POST)
	public ModelAndView paymentBillingInfo(@Valid PaymentGatewayForm paymentGatewayForm,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		// Getting the facility id from the session
		int facilityId = (Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID);

		// Fetching the Account address from the database
		AccountAddressDTO accountAddressDTO = paymentGatewayService
				.getConatactByFacilityId(facilityId);
		// Fetching the Billing address from the database
		AccountAddressDTO billingAddressDTO = paymentGatewayService
				.getBillingAddByFacilityId(facilityId);
		if (billingAddressDTO == null) {
			billingAddressDTO = new AccountAddressDTO();
		}
		
		AccountAddressForm accountAddressForm = null;
		BillingAddressForm billingAddressForm = null;
		
		if(null == paymentGatewayForm.getAccountAddressForm()){
			// Converting AccountAddressDTO to Form
			accountAddressForm = transformPaymentMethod.transformAccountAddrDtoToForm(accountAddressDTO);
			paymentGatewayForm.setAccountAddressForm(accountAddressForm);
		}
		if(null == paymentGatewayForm.getBillingAddressForm()){
			// Converting BillingAddressDTO to Form
			billingAddressForm = transformPaymentMethod.transformBillingAddressDtoToForm(billingAddressDTO);
			paymentGatewayForm.setBillingAddressForm(billingAddressForm);
		}
		
		// Getting the countries from the database
		List<CountryDTO> countryList = populateDropdownsService
						.getCountryList();
		// Getting the States from the database
		List<StateDTO> stateList = populateDropdownsService.getStateList();
				
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);

		if (paymentGatewayForm.getPaymentMethod().equalsIgnoreCase(
				MMJBCommonConstants.CREDIT_CARD) && null != paymentGatewayForm.getCreditCardInfoForm()) {
			paymentGatewayForm.getCreditCardInfoForm().setCreditCardNo(null);
			paymentGatewayForm.getCreditCardInfoForm().setSecuriyCode(null);
			paymentGatewayForm.setInvoiceForm(null);
		
		} else {
			paymentGatewayForm.setCreditCardInfoForm(null);
		}
		
		model.addObject("paymentGatewayForm", paymentGatewayForm);
		model.setViewName("gatewayBillingInfo");
		return model;
	}
	
	@RequestMapping(value = "/paymentBillingInfoBack", method = RequestMethod.GET)
	public ModelAndView paymentBillingInfoBack(@Valid PaymentGatewayForm paymentGatewayForm,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);
		
		if(MMJBCommonConstants.CREDIT_CARD.equals(paymentGatewayForm.getPaymentMethod()) 
				&& null != paymentGatewayForm.getCreditCardInfoForm()){
			paymentGatewayForm.getCreditCardInfoForm().setCreditCardNo(null);
			paymentGatewayForm.getCreditCardInfoForm().setSecuriyCode(null);
		}
		else if(MMJBCommonConstants.INVOICE.equals(paymentGatewayForm.getPaymentMethod()) 
				&& null != paymentGatewayForm.getInvoiceForm()){
			paymentGatewayForm.getInvoiceForm().setPurchaseOrderNo(null);
		}
		
		model.addObject("paymentGatewayForm", paymentGatewayForm);
		model.setViewName("gatewayBillingInfo");
		return model;
	}
	
	@RequestMapping(value = "/backToConfirmOrder", method = RequestMethod.GET)
	public ModelAndView backToConfirmOrder(PaymentGatewayForm paymentGatewayForm,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("paymentGatewayForm", paymentGatewayForm);
		model.setViewName("gatewayConfirmOrder");
		return model;
	}
	
	@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
	public ModelAndView confirmOrder(@Valid PaymentGatewayForm paymentGatewayForm,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		
		if(MMJBCommonConstants.CREDIT_CARD.equals(paymentGatewayForm.getPaymentMethod()) 
				&& null != paymentGatewayForm.getCreditCardInfoForm()){
			paymentGatewayValidation.validate(paymentGatewayForm, result);
		}
		else if(MMJBCommonConstants.INVOICE.equals(paymentGatewayForm.getPaymentMethod()) 
				&& null != paymentGatewayForm.getInvoiceForm()){
			paymentGatewayValidation.validateInvoice(paymentGatewayForm, result);
		}
		
		if (result.hasErrors()) {
			
			List<CountryDTO> countryList = populateDropdownsService.getCountryList();
			List<StateDTO> stateList = populateDropdownsService.getStateList();
			model.addObject("countryList", countryList);
			model.addObject("stateList", stateList);
			
			model.setViewName("gatewayBillingInfo");
			return model;
		}
		
		if(MMJBCommonConstants.CREDIT_CARD.equals(paymentGatewayForm.getPaymentMethod()) 
				&& null != paymentGatewayForm.getCreditCardInfoForm()){
			String creditCardNo = paymentGatewayForm.getCreditCardInfoForm().getCreditCardNo();
			String cardType = paymentGatewayForm.getCreditCardInfoForm().getCardType();
			// Getting last 4 digits of the credit card
			if (creditCardNo.length() != 0) {
				creditCardNo = creditCardNo.substring(creditCardNo.length() - 4);
			}
			model.addObject("creditCardNo", creditCardNo);
			model.addObject("cardType", cardType);
		}
		else if(MMJBCommonConstants.INVOICE.equals(paymentGatewayForm.getPaymentMethod()) && 
				null != paymentGatewayForm.getInvoiceForm()){
			String orderNo = paymentGatewayForm.getInvoiceForm().getPurchaseOrderNo();
			model.addObject("orderNo", orderNo);
		}
		model.setViewName("gatewayConfirmOrder");
		return model;
	}
	 
	/**
	 * This method will be called on clicking cancel. It clears out all the session data
	 * @param paymentGatewayForm
	 * @param session
	 * @return model
	 */
	@RequestMapping(value="/cancelPayment",method = RequestMethod.GET)
	public ModelAndView cancelPayment(PaymentGatewayForm paymentGatewayForm,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		
		session.removeAttribute(MMJBCommonConstants.PURCHASE_JOB_POST_FORM);
		paymentGatewayForm = new PaymentGatewayForm();
		model.addObject("paymentGatewayForm", paymentGatewayForm);
		
		model.setViewName("redirect:/employer/employerDashBoard.html");
		return 	model;
	}
	
	/**
	 * This method will be called to remove the item from job posting cart
	 * @param cartItemIndex
	 * @param session
	 * @return String
	 */
	@RequestMapping(value="/removeJobPost",method = RequestMethod.GET)
	public ModelAndView removeJobPost(PaymentGatewayForm paymentGatewayForm,
			@RequestParam("cartItemIndex") int cartItemIndex, HttpSession session) {
		PurchaseJobPostForm purchaseJobPostForm = paymentGatewayForm.getPurchaseJobPostForm();		
		JobPostingsForm cartItem = purchaseJobPostForm.getJobPostingsCart().get(cartItemIndex);
		purchaseJobPostForm.setGrandTotal(purchaseJobPostForm.getGrandTotal() - cartItem.getPackageSubTotal());
		purchaseJobPostForm.getJobPostingsCart().remove(cartItemIndex);
		ModelAndView model = new ModelAndView();
		model.addObject("paymentGatewayForm", paymentGatewayForm);
		model.setViewName("redirect:/pgiController/backToConfirmOrder.html");
		return 	model;
	}
	
	@RequestMapping(value="/placeOrder",method = RequestMethod.POST)
	public ModelAndView placeOrder(PaymentGatewayForm paymentGatewayForm, HttpSession session) {
		//call web service here. If order success save order details in db & move to Thank you page else move to error page
		OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
		
		orderDetailsDTO = transformPaymentMethod.transformToOrderDetailsDTO(paymentGatewayForm);
		
		orderDetailsDTO.setFacilityId((Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID));
		orderDetailsDTO.setUserId((Integer) session.getAttribute(MMJBCommonConstants.USER_ID));
		orderDetailsDTO.setNsCustomeId(paymentGatewayForm.getNsCustomerId());
		
		UserDTO userDTO = paymentGatewayService.createOrder(orderDetailsDTO);		
		
		int netSuiteStatus = Integer.parseInt(userDTO.getNsStatus());
		
		Map<Integer,String> statusCode = userDTO.getNsStatusCode();
		
		ModelAndView model = new ModelAndView();
		
		String errorMessage ="";
		
		if(netSuiteStatus == MMJBCommonConstants.STATUS_CODE_200){
			LOGGER.info(statusCode.get(netSuiteStatus));
			model.addObject(STATUS_CODE, MMJBCommonConstants.STATUS_CODE_200);	
			//once the payment is success clear out the form data & related session data
			session.removeAttribute(MMJBCommonConstants.PURCHASE_JOB_POST_FORM);
			//clear out all the form data
			paymentGatewayForm = new PaymentGatewayForm();
						
		}
		else
		{   
			switch(netSuiteStatus){
				case MMJBCommonConstants.STATUS_CODE_400 :
						LOGGER.error(statusCode.get(netSuiteStatus)+"\n"+
										MMJBCommonConstants.BAD_REQUEST_400);
						errorMessage = MMJBCommonConstants.BAD_REQUEST_400;
						model.addObject(STATUS_CODE, MMJBCommonConstants.STATUS_CODE_400);
						break;
				case MMJBCommonConstants.STATUS_CODE_401 :
						LOGGER.error(statusCode.get(netSuiteStatus)+"\n"+
										MMJBCommonConstants.UNAUTHORIZED_401);
						errorMessage = MMJBCommonConstants.UNAUTHORIZED_401;
						break;
				case MMJBCommonConstants.STATUS_CODE_403 :
						LOGGER.error(statusCode.get(netSuiteStatus)+"\n"+
										MMJBCommonConstants.FORBIDDEN_403);
						errorMessage = MMJBCommonConstants.FORBIDDEN_403;
						break;
				case MMJBCommonConstants.STATUS_CODE_404 :
						LOGGER.error(statusCode.get(netSuiteStatus)+"\n"+
										MMJBCommonConstants.NOT_FOUND_404);
						errorMessage = MMJBCommonConstants.NOT_FOUND_404;
						break;	
				case MMJBCommonConstants.STATUS_CODE_405 :
						LOGGER.error(statusCode.get(netSuiteStatus)+"\n"+
										MMJBCommonConstants.METHOD_NOT_ALLOWED_405);
						errorMessage = MMJBCommonConstants.METHOD_NOT_ALLOWED_405;
						break;	
				case MMJBCommonConstants.STATUS_CODE_415 :
						LOGGER.error(statusCode.get(netSuiteStatus)+"\n"+
										MMJBCommonConstants.UNSUPPORTED_MEDIA_TYPE_415);
						errorMessage = MMJBCommonConstants.UNSUPPORTED_MEDIA_TYPE_415;
						break;
				case MMJBCommonConstants.STATUS_CODE_500 :
						LOGGER.error(statusCode.get(netSuiteStatus)+"\n"+
										MMJBCommonConstants.INTERNAL_SERVER_ERROR_500);
						errorMessage = MMJBCommonConstants.INTERNAL_SERVER_ERROR_500;
						break;
				case MMJBCommonConstants.STATUS_CODE_503 :
						LOGGER.error(statusCode.get(netSuiteStatus)+"\n"+
										MMJBCommonConstants.SERVICE_UNAVAILABLE_503);
						errorMessage = MMJBCommonConstants.SERVICE_UNAVAILABLE_503;
						break;	
			}
			
			if(netSuiteStatus != MMJBCommonConstants.STATUS_CODE_400){
				//once the payment is success clear out the form data & related session data
				session.removeAttribute(MMJBCommonConstants.PURCHASE_JOB_POST_FORM);
					paymentGatewayForm = new PaymentGatewayForm();
			}		
		}
		model.addObject("errorMessage", errorMessage);		
		model.addObject("paymentGatewayForm", paymentGatewayForm);
		model.setViewName("gatewayThankYou");
		return model;
	}

}
