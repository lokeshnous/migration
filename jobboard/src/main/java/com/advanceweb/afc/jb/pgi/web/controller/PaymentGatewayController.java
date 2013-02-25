/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.pgi.web.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.common.controller.AbstractController;
import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.OrderDetailsDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.constants.PageNames;
import com.advanceweb.afc.jb.employer.service.FacilityService;
import com.advanceweb.afc.jb.employer.service.ManageFeaturedEmployerProfile;
import com.advanceweb.afc.jb.employer.web.controller.AddOnForm;
import com.advanceweb.afc.jb.employer.web.controller.JobPostingsForm;
import com.advanceweb.afc.jb.employer.web.controller.PurchaseJobPostForm;
import com.advanceweb.afc.jb.employer.web.controller.PurchaseResumeSearchForm;
import com.advanceweb.afc.jb.employer.web.controller.ResumeSearchPackageForm;
import com.advanceweb.afc.jb.lookup.service.LookupService;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;
import com.advanceweb.afc.jb.pgi.service.PaymentGatewayService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.UserAlertService;
import com.advanceweb.afc.jb.user.UserService;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.client.ClientContext;

/**
 * This class has been added to handle the Purchase Job Posting activities such as 
 * select payment method, fill the billing information, confirm the order & place the order 
 * @author muralikc
 * @since 
 */
@Controller
@RequestMapping("/pgiController")
@SessionAttributes("paymentGatewayForm")
public class PaymentGatewayController extends AbstractController{

	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(PaymentGatewayController.class);
	
	/** The Constant STATE_LIST. */
	private static final String STATE_LIST = "stateList";
	
	/** The Constant COUNTRY_LIST. */
	private static final String COUNTRY_LIST = "countryList";
	
	/** The Constant STATUS_CODE. */
	private static final String STATUS_CODE = "statusCode";
	
	/** The Constant THANK_YOU_FORM. */
	private static final String THANK_YOU_FORM = "gatewayThankYou";
	
	/** The Constant CONFIRM_ORDER_FORM. */
	private static final String CONFIRM_ORDER_FORM = "gatewayConfirmOrder";
	
	/** The Constant BILLING_INFO_FORM. */
	private static final String BILLING_INFO_FORM = "gatewayBillingInfo";
	
	/** The Constant GATEWAY_PAYMENT_FORM. */
	private static final String GATEWAY_PAYMENT_FORM = "gatewayPaymentMethod";
	
	/** The Constant PAYMENT_GATEWAY_FORM. */
	private static final String PAYMENT_GATEWAY_FORM = "paymentGatewayForm";
	
	/** The payment gateway service. */
	@Autowired
	private PaymentGatewayService paymentGatewayService;

	/** The populate dropdowns service. */
	@Autowired
	private PopulateDropdowns populateDropdownsService;

	/** The transform payment method. */
	@Autowired
	private TransformPaymentMethod transformPaymentMethod;
	
	/** The manage featured employer profile. */
	@Autowired
	private ManageFeaturedEmployerProfile manageFeaturedEmployerProfile;
	
	/** The payment gateway validation. */
	@Autowired
	private PaymentGatewayValidation paymentGatewayValidation;
	
	/** The ad service. */
	@Autowired
	private AdService adService;
	
	/** The advance web address. */
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	
	/** The email configuration. */
	@Value("${dothtmlExtention}")
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	
	/** The email service. */
	@Autowired
	private MMEmailService emailService;
	
	/** The facility service. */
	@Autowired
	private FacilityService facilityService;
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The alert service. */
	@Autowired
	private UserAlertService alertService;
	
	/** The validate city state. */
	@Value("${validateCityState}")
	private String validateCityState;
	
	/** The lookup service. */
	@Autowired
	private LookupService lookupService;
	
	/**
	 * Call payment method.
	 *
	 * @param paymentGatewayForm the payment gateway form
	 * @param session the session
	 * @param purchaseType the purchase type
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/callPaymentMethod", method = RequestMethod.GET)
	public ModelAndView callPaymentMethod(@Valid PaymentGatewayForm paymentGatewayForm,
			HttpSession session,@RequestParam(value = "purchaseType", required = false ) String purchaseType, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
		int facilityId = (Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID);
		int nsCustomerId = manageFeaturedEmployerProfile.getNSCustomerIDFromAdmFacility(facilityId);
		UserDTO userDTO = manageFeaturedEmployerProfile.getNSCustomerDetails(nsCustomerId);
		
		paymentGatewayForm.setNsCustomerId(nsCustomerId);
		InvoiceForm invoiceForm = new InvoiceForm();
		
		invoiceForm.setInvoiceEnabled(userDTO.isInvoiceEnabled());
		paymentGatewayForm.setInvoiceForm(invoiceForm);
		
		paymentGatewayForm.setPurchaseType(purchaseType);
		
		model.addObject(PAYMENT_GATEWAY_FORM,paymentGatewayForm);
		
		if(MMJBCommonConstants.PURCHASE_RESUME_SEARCH.equals(paymentGatewayForm.getPurchaseType())){
			paymentGatewayForm.setPurchaseResumeSearchForm(
					(PurchaseResumeSearchForm)session.getAttribute(MMJBCommonConstants.PURCHASE_RESUME_SEARCH_FORM));
		}else if(MMJBCommonConstants.PURCHASE_JOB_POST.equals(paymentGatewayForm.getPurchaseType())){
			paymentGatewayForm.setPurchaseJobPostForm(
					(PurchaseJobPostForm)session.getAttribute(MMJBCommonConstants.PURCHASE_JOB_POST_FORM));
		}
		model.setViewName(GATEWAY_PAYMENT_FORM);
		// get the Ads
		populateAds (request, session, model, PageNames.EMPLOYER_PG_METHOD);
		return model;
	}
	
	/**
	 * populate Ads for payment method page
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @param pageName 
	 */
	private void populateAds (HttpServletRequest request,
			HttpSession session, ModelAndView model, String pageName) {
		String bannerString = null;
		try {
			ClientContext clientContext = getClientContextDetails(request,
					session, PageNames.EMPLOYER_PG_METHOD);
			AdSize size = AdSize.IAB_LEADERBOARD;
			AdPosition position = AdPosition.TOP;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGETOP, bannerString);

			
			size = AdSize.IAB_LEADERBOARD;
			position = AdPosition.BOTTOM;
			bannerString = adService.getBanner(clientContext, size, position)
					.getTag();
			model.addObject(MMJBCommonConstants.ADPAGEBOTTOM, bannerString);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);		}
	}

	
	/**
	 * Gateway payment method.
	 *
	 * @param paymentGatewayForm the payment gateway form
	 * @param session the session
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/paymentMethodForBack", method = RequestMethod.GET)
	public ModelAndView gatewayPaymentMethod(@Valid PaymentGatewayForm paymentGatewayForm,
			HttpSession session, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		//add country & state list to model
		addCountryStateList(model);
		
		model.setViewName(GATEWAY_PAYMENT_FORM);
		// get the Ads
		populateAds (request, session, model, PageNames.EMPLOYER_PG_METHOD);
		return model;
	}

	
	/**
	 * Payment billing info.
	 *
	 * @param paymentGatewayForm the payment gateway form
	 * @param result the result
	 * @param session the session
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/paymentBillingInfo", method = RequestMethod.POST)
	public ModelAndView paymentBillingInfo(@Valid PaymentGatewayForm paymentGatewayForm,
			BindingResult result, HttpSession session, HttpServletRequest request) {
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
		
		//add country & state list to model
		addCountryStateList(model);

		if (paymentGatewayForm.getPaymentMethod().equalsIgnoreCase(
				MMJBCommonConstants.CREDIT_CARD) && null != paymentGatewayForm.getCreditCardInfoForm()) {
			paymentGatewayForm.getCreditCardInfoForm().setCreditCardNo(null);
			paymentGatewayForm.getCreditCardInfoForm().setSecuriyCode(null);
			paymentGatewayForm.setInvoiceForm(null);
		
		} else {
			paymentGatewayForm.setCreditCardInfoForm(null);
		}
		
		model.addObject(PAYMENT_GATEWAY_FORM, paymentGatewayForm);
		model.setViewName(BILLING_INFO_FORM);
		// get the Ads
		populateAds (request, session, model, PageNames.EMPLOYER_PG_BILLING);
		return model;
	}
	
	/**
	 * Payment billing info back.
	 *
	 * @param paymentGatewayForm the payment gateway form
	 * @param session the session
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/paymentBillingInfoBack", method = RequestMethod.GET)
	public ModelAndView paymentBillingInfoBack(@Valid PaymentGatewayForm paymentGatewayForm,
			HttpSession session, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		//add country & state list to model
		
		addCountryStateList(model);
		
		if(MMJBCommonConstants.CREDIT_CARD.equals(paymentGatewayForm.getPaymentMethod()) 
				&& null != paymentGatewayForm.getCreditCardInfoForm()){
			paymentGatewayForm.getCreditCardInfoForm().setCreditCardNo(null);
			paymentGatewayForm.getCreditCardInfoForm().setSecuriyCode(null);
		}
		else if(MMJBCommonConstants.INVOICE.equals(paymentGatewayForm.getPaymentMethod()) 
				&& null != paymentGatewayForm.getInvoiceForm()){
			paymentGatewayForm.getInvoiceForm().setPurchaseOrderNo(null);
		}
		
		model.addObject(PAYMENT_GATEWAY_FORM, paymentGatewayForm);
		model.setViewName(BILLING_INFO_FORM);
		// get the Ads
		populateAds (request, session, model, PageNames.EMPLOYER_PG_BILLING);
		return model;
	}
	
	/**
	 * Back to confirm order.
	 *
	 * @param paymentGatewayForm the payment gateway form
	 * @param session the session
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/backToConfirmOrder", method = RequestMethod.GET)
	public ModelAndView backToConfirmOrder(PaymentGatewayForm paymentGatewayForm,
			HttpSession session, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
		model.addObject(PAYMENT_GATEWAY_FORM, paymentGatewayForm);
		model.setViewName(CONFIRM_ORDER_FORM);
		// get the Ads
		populateAds(request, session, model, PageNames.EMPLOYER_PG_CONFIRMORDER);
		return model;
	}
	
	/**
	 * Confirm order.
	 *
	 * @param paymentGatewayForm the payment gateway form
	 * @param result the result
	 * @param session the session
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
	public ModelAndView confirmOrder(@Valid PaymentGatewayForm paymentGatewayForm,
			BindingResult result, HttpSession session, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
		validateBillingForm(paymentGatewayForm, result);
		
		if (result.hasErrors()) {
			//add country & state list to model
			addCountryStateList(model);
    		model.setViewName(BILLING_INFO_FORM);
			// get the Ads
			populateAds(request, session, model, PageNames.EMPLOYER_PG_BILLING);
			return model;
		}
		// validate city,state,zipcode cmbination
		boolean validateStateCityZip;
		try {
			validateStateCityZip = lookupService.validateCityStateZip(
					paymentGatewayForm.getBillingAddressForm().getCityOrTownForBillingAddr(),
					paymentGatewayForm.getBillingAddressForm().getStateBillingAddress(),
					paymentGatewayForm.getBillingAddressForm().getZipCodeForBillingAddr());

			if (!validateStateCityZip) {
				//add country & state list to model
				addCountryStateList(model);
	    		model.setViewName(BILLING_INFO_FORM);
				// get the Ads
				populateAds(request, session, model, PageNames.EMPLOYER_PG_BILLING);
				model.addObject("errorMessage",validateCityState);		
				return model;
			}
		} catch (JobBoardServiceException ex) {

			ex.printStackTrace();
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
		// get the Ads
		populateAds(request, session, model, PageNames.EMPLOYER_PG_BILLING);
		model.setViewName(CONFIRM_ORDER_FORM);
		return model;
	}

	/**
	 * Validate billing form.
	 *
	 * @param paymentGatewayForm the payment gateway form
	 * @param result the result
	 */
	private void validateBillingForm(PaymentGatewayForm paymentGatewayForm,
			BindingResult result) {
		if(MMJBCommonConstants.CREDIT_CARD.equals(paymentGatewayForm.getPaymentMethod()) 
				&& null != paymentGatewayForm.getCreditCardInfoForm()){
			paymentGatewayValidation.validate(paymentGatewayForm, result);
		}
		else if(MMJBCommonConstants.INVOICE.equals(paymentGatewayForm.getPaymentMethod()) 
				&& null != paymentGatewayForm.getInvoiceForm()){
			paymentGatewayValidation.validateInvoice(paymentGatewayForm, result);
		}
	}

	/**
	 * Adds the country state list.
	 *
	 * @param model the model
	 */
	private void addCountryStateList(ModelAndView model) {
		// Getting the countries from the database & Getting the States from the database
		List<CountryDTO> countryList = populateDropdownsService.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		model.addObject(COUNTRY_LIST, countryList);
		model.addObject(STATE_LIST, stateList);
	}
	 
	/**
	 * This method will be called on clicking cancel. It clears out all the session data
	 * @param paymentGatewayForm
	 * @param session
	 * @return model
	 */
	@RequestMapping(value="/cancelPayment",method = RequestMethod.GET)
	public ModelAndView cancelPayment(PaymentGatewayForm paymentGatewayFormP,
			HttpSession session) {
		PaymentGatewayForm paymentGatewayForm=paymentGatewayFormP;
		ModelAndView model = new ModelAndView();
		paymentGatewayForm = clearSessionFormData(session, paymentGatewayForm);
		
		model.addObject(PAYMENT_GATEWAY_FORM, paymentGatewayForm);
		
		model.setViewName("redirect:/employer/employerDashBoard.html");
		return 	model;
	}
	
	/**
	 * This method is added to update the cart if quantity value is changed in the cart 
	 * @param paymentGatewayForm
	 * @param cartItemIndex
	 * @param quantity
	 * @return model
	 */
	@RequestMapping(value = "/updateQuantity", method = RequestMethod.POST)
	public ModelAndView updateQuantity(PaymentGatewayForm paymentGatewayForm,
			@RequestParam("cartItemIndex") int cartItemIndex, @RequestParam("quantity") int quantity) {
		
		ModelAndView model = new ModelAndView();
		
		if(MMJBCommonConstants.PURCHASE_RESUME_SEARCH.equals(paymentGatewayForm.getPurchaseType())){
			PurchaseResumeSearchForm purchaseResumeSearchForm = paymentGatewayForm.getPurchaseResumeSearchForm(); 
			ResumeSearchPackageForm cartItem = purchaseResumeSearchForm.getResumeSearchPackageCart().get(cartItemIndex);
			
			//Deduct PackageTotal from the GrandTotal
			purchaseResumeSearchForm.setGrandTotal(purchaseResumeSearchForm.getGrandTotal()
					- cartItem.getPackageTotal());
			//Update the new quantity & PackageTotal
			cartItem.setQuantity(quantity);
			cartItem.setPackageTotal(cartItem.getPriceAmt() * cartItem.getQuantity());
			//Change the GrandTotal
			purchaseResumeSearchForm.setGrandTotal(purchaseResumeSearchForm
					.getGrandTotal() + cartItem.getPackageTotal());
			
		}
		else if(MMJBCommonConstants.PURCHASE_JOB_POST.equals(paymentGatewayForm.getPurchaseType())){
			int packageSubTotal = 0, planCreditAmt = 0, addOnCreditAmtTotal = 0;
			PurchaseJobPostForm purchaseJobPostForm = paymentGatewayForm.getPurchaseJobPostForm();		
			JobPostingsForm cartItem = purchaseJobPostForm.getJobPostingsCart().get(cartItemIndex);
			if (purchaseJobPostForm.getDiscountAmt()>0) {
				purchaseJobPostForm.setGrandTotal(purchaseJobPostForm.getTotal());
			}
			purchaseJobPostForm.setGrandTotal(purchaseJobPostForm.getGrandTotal() - cartItem.getPackageSubTotal());
			
			planCreditAmt = Integer.parseInt(cartItem.getJobPostPlanCretitAmt());

			for (AddOnForm addOnForm : cartItem.getAddOnForm()) {
				addOnCreditAmtTotal = addOnCreditAmtTotal + Integer.parseInt(addOnForm.getAddOnCreditAmt());
			}
			
			packageSubTotal = (planCreditAmt + addOnCreditAmtTotal) * quantity;
			cartItem.setQuantity(quantity);
			cartItem.setPackageSubTotal(packageSubTotal);
			purchaseJobPostForm.setGrandTotal(purchaseJobPostForm.getGrandTotal() + packageSubTotal);
			//calculating the Total amount after deducting the discount
			calculateDiscount(purchaseJobPostForm);
		}
		
		model.addObject(PAYMENT_GATEWAY_FORM, paymentGatewayForm);
		model.setViewName("redirect:/pgiController/backToConfirmOrder.html");
		return 	model;
	}
	
	/**
	 * This method will be called to remove the item from job posting cart
	 * @param cartItemIndex
	 * @param session
	 * @return String
	 */
	@RequestMapping(value="/removeCartItem",method = RequestMethod.GET)
	public ModelAndView removeJobPost(PaymentGatewayForm paymentGatewayForm,
			@RequestParam("cartItemIndex") int cartItemIndex, HttpSession session) {
		ModelAndView model = new ModelAndView();
		if(MMJBCommonConstants.PURCHASE_RESUME_SEARCH.equals(paymentGatewayForm.getPurchaseType())){
			PurchaseResumeSearchForm purchaseResumeSearchForm = paymentGatewayForm.getPurchaseResumeSearchForm(); 
			ResumeSearchPackageForm cartItem = purchaseResumeSearchForm.getResumeSearchPackageCart().get(cartItemIndex);
			purchaseResumeSearchForm.setGrandTotal(purchaseResumeSearchForm.getGrandTotal()- cartItem.getPackageTotal());
			purchaseResumeSearchForm.getResumeSearchPackageCart().remove(cartItemIndex);
		}
		else if(MMJBCommonConstants.PURCHASE_JOB_POST.equals(paymentGatewayForm.getPurchaseType())){
			PurchaseJobPostForm purchaseJobPostForm = paymentGatewayForm.getPurchaseJobPostForm();		
			JobPostingsForm cartItem = purchaseJobPostForm.getJobPostingsCart().get(cartItemIndex);
			if (purchaseJobPostForm.getDiscountAmt()>0) {
				purchaseJobPostForm.setGrandTotal(purchaseJobPostForm.getTotal());
			}
			purchaseJobPostForm.setGrandTotal(purchaseJobPostForm.getGrandTotal() - cartItem.getPackageSubTotal());
			//calculating the Total amount after deducting the discount 
			calculateDiscount(purchaseJobPostForm);
			purchaseJobPostForm.getJobPostingsCart().remove(cartItemIndex);
		}
		
		model.addObject(PAYMENT_GATEWAY_FORM, paymentGatewayForm);
		model.setViewName("redirect:/pgiController/backToConfirmOrder.html");
		return 	model;
	}

	/**
	 * calculating the Total amount after deducting the discount 
	 * @param purchaseJobPostForm
	 */
	private void calculateDiscount(PurchaseJobPostForm purchaseJobPostForm) {
		if (purchaseJobPostForm.getDiscountAmt()>0) {
			int total =  (int) purchaseJobPostForm.getGrandTotal();
			double discountAmt = Math.round((total * .15)*10.0)/10.0;
			double grandTotal = total - discountAmt;
			purchaseJobPostForm.setTotal(total);
			purchaseJobPostForm.setDiscountAmt(discountAmt);
			purchaseJobPostForm.setGrandTotal(grandTotal);
		}
	}
	
	/**
	 * Place order.
	 *
	 * @param paymentGatewayFormP the payment gateway form p
	 * @param session the session
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	public ModelAndView placeOrder(PaymentGatewayForm paymentGatewayFormP,
			HttpSession session, HttpServletRequest request) {
		PaymentGatewayForm paymentGatewayForm = paymentGatewayFormP;
		// call web service here. If order success save order details in db &
		// move to Thank you page else move to error page
		OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();

		orderDetailsDTO = transformPaymentMethod
				.transformToOrderDetailsDTO(paymentGatewayForm);
		if(paymentGatewayForm.getPurchaseJobPostForm().getDiscountAmt()>0){
			orderDetailsDTO.setDiscountItem(MMJBCommonConstants.NS_DISCOUNT_ITEM_ID);
		}
		/*int parentFacilityId = facilityService.getFacilityByFacilityId((Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID)).getFacilityParentId();*/
		int parentFacilityId = facilityService.getFacilityByFacilityId((Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID)).getFacilityParentId();
		FacilityDTO facilityDao=null;
		UserDTO parentUserDTO=null;
		if(parentFacilityId>0){
		facilityDao=facilityService.getFacilityByFacilityId(parentFacilityId);
		parentUserDTO = userService.getUserByUserId(facilityService.getfacilityUserId(facilityDao.getFacilityId()));
		}
		if(parentFacilityId<0||(facilityDao!=null && facilityDao.getFacilityType().equals(MMJBCommonConstants.FACILITY_SYSTEM))){
		orderDetailsDTO.setFacilityId((Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID));
		}else{
			orderDetailsDTO.setFacilityId(parentFacilityId);
		}
	/*if( facilityDao!=null && facilityDao.getFacilityType().equals(MMJBCommonConstants.FACILITY_SYSTEM)){
		orderDetailsDTO.setFacilityId((Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID));
	}*/
		orderDetailsDTO.setUserId((Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID));
		orderDetailsDTO.setNsCustomeId(paymentGatewayForm.getNsCustomerId());

		UserDTO userDTO = paymentGatewayService.createOrder(orderDetailsDTO);
		userDTO.setUserId((Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID));
		int netSuiteStatus = Integer.parseInt(userDTO.getNsStatus());

		Map<Integer, String> statusCode = userDTO.getNsStatusCode();

		ModelAndView model = new ModelAndView();

		String errorMessage = "";

		if (netSuiteStatus == MMJBCommonConstants.STATUS_CODE_200) {
			LOGGER.debug(statusCode.get(netSuiteStatus));
			model.addObject(STATUS_CODE, MMJBCommonConstants.STATUS_CODE_200);
			paymentGatewayForm = clearSessionFormData(session,
					paymentGatewayForm);
			UserDTO merUserdto = userService.getUserByUserId(userDTO.getUserId());
			EmployerInfoDTO facilityDetail = facilityService
					.facilityDetails(userDTO.getUserId());
			if (null != facilityDetail && null != merUserdto) {
				userDTO.setCompany(facilityDetail.getCustomerName());
				userDTO.setEmailId(merUserdto.getEmailId());
				userDTO.setFirstName(merUserdto.getFirstName());
				userDTO.setLastName(merUserdto.getLastName());
				sendDetailEmail(session, request, parentUserDTO,
						userDTO, orderDetailsDTO);				
			}
		} else {
			switch (netSuiteStatus) {
			case MMJBCommonConstants.STATUS_CODE_400:
				LOGGER.error(statusCode.get(netSuiteStatus) + "\n"
						+ MMJBCommonConstants.BAD_REQUEST_400);
				errorMessage = MMJBCommonConstants.BAD_REQUEST_400;
				model.addObject(STATUS_CODE,
						MMJBCommonConstants.STATUS_CODE_400);
				break;
			case MMJBCommonConstants.STATUS_CODE_401:
				LOGGER.error(statusCode.get(netSuiteStatus) + "\n"
						+ MMJBCommonConstants.UNAUTHORIZED_401);
				errorMessage = MMJBCommonConstants.UNAUTHORIZED_401;
				break;
			case MMJBCommonConstants.STATUS_CODE_403:
				LOGGER.error(statusCode.get(netSuiteStatus) + "\n"
						+ MMJBCommonConstants.FORBIDDEN_403);
				errorMessage = MMJBCommonConstants.FORBIDDEN_403;
				break;
			case MMJBCommonConstants.STATUS_CODE_404:
				LOGGER.error(statusCode.get(netSuiteStatus) + "\n"
						+ MMJBCommonConstants.NOT_FOUND_404);
				errorMessage = MMJBCommonConstants.NOT_FOUND_404;
				break;
			case MMJBCommonConstants.STATUS_CODE_405:
				LOGGER.error(statusCode.get(netSuiteStatus) + "\n"
						+ MMJBCommonConstants.METHOD_NOT_ALLOWED_405);
				errorMessage = MMJBCommonConstants.METHOD_NOT_ALLOWED_405;
				break;
			case MMJBCommonConstants.STATUS_CODE_415:
				LOGGER.error(statusCode.get(netSuiteStatus) + "\n"
						+ MMJBCommonConstants.UNSUPPORTED_MEDIA_TYPE_415);
				errorMessage = MMJBCommonConstants.UNSUPPORTED_MEDIA_TYPE_415;
				break;
			case MMJBCommonConstants.STATUS_CODE_500:
				LOGGER.error(statusCode.get(netSuiteStatus) + "\n"
						+ MMJBCommonConstants.INTERNAL_SERVER_ERROR_500);
				errorMessage = MMJBCommonConstants.INTERNAL_SERVER_ERROR_500;
				break;
			case MMJBCommonConstants.STATUS_CODE_503:
				LOGGER.error(statusCode.get(netSuiteStatus) + "\n"
						+ MMJBCommonConstants.SERVICE_UNAVAILABLE_503);
				errorMessage = MMJBCommonConstants.SERVICE_UNAVAILABLE_503;
				break;
			default:
				LOGGER.debug(statusCode.get(netSuiteStatus));
				model.addObject(STATUS_CODE,
						MMJBCommonConstants.STATUS_CODE_DEFAULT);
				errorMessage = MMJBCommonConstants.DEFAULT_NSERROR_MSG;
				paymentGatewayForm = clearSessionFormData(session,
						paymentGatewayForm);
			}

			if (netSuiteStatus != MMJBCommonConstants.STATUS_CODE_400) {
				paymentGatewayForm = clearSessionFormData(session,
						paymentGatewayForm);
			}
		}
		model.addObject("errorMessage", errorMessage);
		model.addObject(PAYMENT_GATEWAY_FORM, paymentGatewayForm);
		model.setViewName(THANK_YOU_FORM);
		// get the Ads
		populateAds(request, session, model, PageNames.EMPLOYER_PG_CONCLUSION);

		return model;
	}
	
	/**
	 * @param session
	 * @param paymentGatewayForm
	 * @return
	 */
	private PaymentGatewayForm clearSessionFormData(HttpSession session, PaymentGatewayForm paymentGatewayFormP) {
		PaymentGatewayForm paymentGatewayForm = paymentGatewayFormP;
		//once the payment is success clear out the form data & related session data
		if(MMJBCommonConstants.PURCHASE_RESUME_SEARCH.equals(paymentGatewayForm.getPurchaseType())){
			session.removeAttribute(MMJBCommonConstants.PURCHASE_RESUME_SEARCH_FORM);
		}else if(MMJBCommonConstants.PURCHASE_JOB_POST.equals(paymentGatewayForm.getPurchaseType())){
			session.removeAttribute(MMJBCommonConstants.PURCHASE_JOB_POST_FORM);
		}
		//clear out all the form data		
		paymentGatewayForm = new PaymentGatewayForm();
		return paymentGatewayForm;
	}
	/**
	 * Method To send New Job Posting Credit Detail Email
	 * @param session
	 * @param request
	 * @param userDTO
	 */
	private void sendDetailEmail(HttpSession session,
			HttpServletRequest request, UserDTO parentUserDTO,UserDTO userDTO,
			OrderDetailsDTO orderDetailsDTO) {
		StringBuffer stringBuffer = new StringBuffer();
		InternetAddress[] jsToAddress = new InternetAddress[1];
		EmailDTO emailDTO = new EmailDTO();

		emailDTO.setFromAddress(advanceWebAddress);
		emailDTO.setSubject(emailConfiguration
				.getProperty("new.credit.message").trim());

		String employerloginUrl = request
				.getRequestURL()
				.toString()
				.replace(
						request.getServletPath(),
						emailConfiguration.getProperty(
								"employer.email.login.url").trim());

		if (null != orderDetailsDTO
				&& null != orderDetailsDTO.getJobPostingPlanDTOList()
				&& !orderDetailsDTO.getJobPostingPlanDTOList().isEmpty()) {

			String userName = userDTO.getFirstName() + " "
					+ userDTO.getLastName();
			String comapnyName = userDTO.getCompany();

			// if Logged in user is a job owner, check whether the admin of that
			// owner is interested for the mails or not. and send the mail as
			// per the requirements
			int facilityIdParent = (Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID);
			int parentUserId = (Integer) session.getAttribute(MMJBCommonConstants.USER_ID);
			String parentMail = (String) session.getAttribute(MMJBCommonConstants.USER_EMAIL);
			FacilityDTO mainFacilityDTO = facilityService.getParentFacility(facilityIdParent);
		    UserDTO mainuserDto = userService.getUserByUserId(mainFacilityDTO
						.getUserId());
			
			//check for job owner and send mail on interest
			if(facilityIdParent != mainFacilityDTO.getFacilityId()){
				List<UserAlertDTO> joAlertDTOs = alertService
						.viewAlerts(parentUserId);
				try {
					jsToAddress[0] = new InternetAddress(parentMail);
				} catch (AddressException e) {
					LOGGER.error(
							"Error occured while geting InternetAddress reference",
							e);
				}
				emailDTO.setToAddress(jsToAddress);
				//send mail if not set any alerts like default set 
				if(joAlertDTOs.size() == 0){
					LOGGER.debug("Jobowner has the default job posting credits alerts");
					// New Job Posting -send mail -Starts
					sendPurchageDetailEmail(orderDetailsDTO, stringBuffer,
							emailDTO, employerloginUrl, userName,
							comapnyName);
					// Purchase receipt -send mail -Starts
					sendPurcahgeReceipt(orderDetailsDTO, emailDTO,
							employerloginUrl, userName, comapnyName);
				}else{					
					for (UserAlertDTO palertDTO : joAlertDTOs) {
						if (palertDTO.getAlertId() > 0
								&& palertDTO.getAlertId() == MMJBCommonConstants.NEW_JOB_POSTING_CREDITS_AVAILABLE) {
							// New Job Posting -send mail -Starts
							sendPurchageDetailEmail(orderDetailsDTO, stringBuffer,
									emailDTO, employerloginUrl, userName,
									comapnyName);
							// New Job Posting -send mail -Ends
						}else if (palertDTO.getAlertId() > 0
								&& palertDTO.getAlertId() == MMJBCommonConstants.SALES_RECEIPT) {
							// Purchase receipt -send mail -Starts
							sendPurcahgeReceipt(orderDetailsDTO, emailDTO,
									employerloginUrl, userName, comapnyName);
							// Purchase receipt -send mail -end
						}
					}
				}
			}
			
			// send mail to employer on interest
			List<UserAlertDTO> parentAlertDTOs = alertService.viewAlerts(mainuserDto.getUserId());
			if (null != parentAlertDTOs && (parentAlertDTOs.size() > 0 || parentAlertDTOs.size() == 0)) {				
				if (null != mainuserDto && null != mainuserDto.getEmailId()
						&& !mainuserDto.getEmailId().isEmpty()) {
					try {
						jsToAddress[0] = new InternetAddress(
								mainuserDto.getEmailId());
					} catch (AddressException jbex) {
						LOGGER.error(
								"Error occured while geting InternetAddress reference",
								jbex);
					}
					emailDTO.setToAddress(jsToAddress);
				}
				//send mail if not set any alerts 
				if(parentAlertDTOs.size() == 0){
					LOGGER.debug("Employer has the default administrator alerts");
					// New Job Posting -send mail -Starts
					sendPurchageDetailEmail(orderDetailsDTO, stringBuffer,
							emailDTO, employerloginUrl, userName,
							comapnyName);
					// Purchase receipt -send mail -Starts
					sendPurcahgeReceipt(orderDetailsDTO, emailDTO,
							employerloginUrl, userName, comapnyName);
					
				}else{					
					for (UserAlertDTO palertDTO : parentAlertDTOs) {
						if (palertDTO.getAlertId() > 0
								&& palertDTO.getAlertId() == MMJBCommonConstants.NEW_JOB_POSTING_CREDITS_AVAILABLE) {
							// New Job Posting -send mail -Starts
							sendPurchageDetailEmail(orderDetailsDTO, stringBuffer,
									emailDTO, employerloginUrl, userName,
									comapnyName);
							// New Job Posting -send mail -Ends
						}else if (palertDTO.getAlertId() > 0
								&& palertDTO.getAlertId() == MMJBCommonConstants.SALES_RECEIPT) {
							// Purchase receipt -send mail -Starts
							sendPurcahgeReceipt(orderDetailsDTO, emailDTO,
									employerloginUrl, userName, comapnyName);
							// Purchase receipt -send mail -end
						}
					}
				}

			}/*else{
				if (null != parentUserDTO && null != parentUserDTO.getEmailId()
						&& !parentUserDTO.getEmailId().isEmpty()) {
					try {
						jsToAddress[0] = new InternetAddress(
								parentUserDTO.getEmailId());
					} catch (AddressException jbex) {
						LOGGER.error(
								"Error occured while geting InternetAddress reference",
								jbex);
					}
					emailDTO.setToAddress(jsToAddress);
					// New Job Posting -send mail -Starts
					sendPurchageDetailEmail(orderDetailsDTO, stringBuffer,
							emailDTO, employerloginUrl, userName,
							comapnyName);
					// New Job Posting -send mail -Ends
					// Purchase receipt -send mail -starts
					sendPurcahgeReceipt(orderDetailsDTO, emailDTO,
							employerloginUrl, userName, comapnyName);
					// Purchase receipt -send mail -end
				}
			}*/
			// if Logged in user is a job owner, check whether the
			// owner is interested for the mails or not. and send the mail as
			// per the requirements
			/*List<UserAlertDTO> alertDTOs = alertService.viewAlerts(userDTO
					.getUserId());
			if (null != alertDTOs && alertDTOs.size() > 0) {
				if (null != userDTO && null != userDTO.getEmailId()
						&& !userDTO.getEmailId().isEmpty()) {
					try {
						jsToAddress[0] = new InternetAddress(
								userDTO.getEmailId());
					} catch (AddressException jbex) {
						LOGGER.error(
								"Error occured while geting InternetAddress reference",
								jbex);
					}
					emailDTO.setToAddress(jsToAddress);
				}
				for (UserAlertDTO alertDTO : alertDTOs) {
					if (alertDTO.getAlertId() > 0
							&& alertDTO.getAlertId() == MMJBCommonConstants.NEW_JOB_POSTING_CREDITS_AVAILABLE) {
						// New Job Posting -send mail -Starts
						sendPurchageDetailEmail(orderDetailsDTO, stringBuffer,
								emailDTO, employerloginUrl, userName,
								comapnyName);
						// New Job Posting -send mail -Ends
					}
					if (alertDTO.getAlertId() > 0
							&& alertDTO.getAlertId() == MMJBCommonConstants.SALES_RECEIPT) {
						// Purchase receipt -send mail -Starts
						sendPurcahgeReceipt(orderDetailsDTO, emailDTO,
								employerloginUrl, userName, comapnyName);
						// Purchase receipt -send mail -end
					}
				}
			}else{
				if (null != userDTO && null != userDTO.getEmailId()
						&& !userDTO.getEmailId().isEmpty()) {
					try {
						jsToAddress[0] = new InternetAddress(
								userDTO.getEmailId());
					} catch (AddressException jbex) {
						LOGGER.error(
								"Error occured while geting InternetAddress reference",
								jbex);
					}
					emailDTO.setToAddress(jsToAddress);
					// New Job Posting -send mail -Starts
					sendPurchageDetailEmail(orderDetailsDTO, stringBuffer,
							emailDTO, employerloginUrl, userName,
							comapnyName);
					// New Job Posting -send mail -Ends
					// Purchase receipt -send mail -Starts
					sendPurcahgeReceipt(orderDetailsDTO, emailDTO,
							employerloginUrl, userName, comapnyName);
					// Purchase receipt -send mail -end
				}
			}*/
		}
	}

	/**
	 * @param orderDetailsDTO
	 * @param emailDTO
	 * @param employerloginUrl
	 * @param userName
	 * @param comapnyName
	 */
	private void sendPurcahgeReceipt(OrderDetailsDTO orderDetailsDTO,
			EmailDTO emailDTO, String employerloginUrl, String userName,
			String comapnyName) {
		StringBuffer receiptDetail = new StringBuffer();
		String salesrcptMailBody = emailConfiguration.getProperty(
				"sales.receipt.body").trim();
		salesrcptMailBody = salesrcptMailBody.replace("?orderNumber",
				orderDetailsDTO.getOrderPaymentDTO().getTransactionId());
		salesrcptMailBody = salesrcptMailBody.replace("?userName", userName);
		salesrcptMailBody = salesrcptMailBody.replace("?companyName",
				comapnyName);
		
		InternetAddress[] ccAddress = new InternetAddress[1];
		try {
			ccAddress[0]=new InternetAddress(emailConfiguration.getProperty("rep.email.address").trim());
		} catch (AddressException jbex) {
			LOGGER.error(
					"Error occured while geting InternetAddress reference",
					jbex);
		}

		
		String packageName="";
		String paymentType="Credit Card";
		if(null!=orderDetailsDTO.getJobPostingPlanDTOList()){
			for(JobPostingPlanDTO postingPlanDTO:orderDetailsDTO.getJobPostingPlanDTOList()){
				if(!packageName.isEmpty()){
					packageName=packageName+"/";
				}
				packageName=packageName+postingPlanDTO.getJobPostPlanName();
			}
		}
		if (null != orderDetailsDTO.getOrderPaymentDTO().getMethod()
				&& MMJBCommonConstants.INVOICE.equals(orderDetailsDTO.getOrderPaymentDTO().getMethod())) {
			paymentType="Invoice";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(
				MMJBCommonConstants.SQL_DATE_PATTERN, Locale.US);
		salesrcptMailBody = salesrcptMailBody.replace("?ordersum",
				"<b>Order summary :</b>"
						+ "<br> Package Name(s):"
						+ packageName
						+ "<br> Total Payment: $"
						+ orderDetailsDTO.getOrderPaymentDTO().getPaidAmount()
						+ "<br> Payment Method:"
						+ paymentType
						+ "<br> Payment Date:"
						+ formatter.format(orderDetailsDTO.getOrderPaymentDTO()
								.getTransactionDate()));
		salesrcptMailBody = salesrcptMailBody.replace("?empdashboardLink",
				employerloginUrl);
		emailDTO.setCcAddress(ccAddress);
		emailDTO.setSubject(emailConfiguration.getProperty(
				"purchageReceipt").trim().replace("?ordernumber",
				orderDetailsDTO.getOrderPaymentDTO().getTransactionId()));
		receiptDetail.append(emailConfiguration.getProperty(
				"employer.email.header").trim());
		receiptDetail.append(salesrcptMailBody);
		receiptDetail.append(emailConfiguration.getProperty("email.footer")
				.trim());
		emailDTO.setBody(receiptDetail.toString());
		emailDTO.setHtmlFormat(true);
		emailService.sendEmail(emailDTO);
	}

	/**
	 * @param orderDetailsDTO
	 * @param stringBuffer
	 * @param emailDTO
	 * @param employerloginUrl
	 * @param userName
	 * @param comapnyName
	 */
	private void sendPurchageDetailEmail(OrderDetailsDTO orderDetailsDTO,
			StringBuffer stringBuffer, EmailDTO emailDTO,
			String employerloginUrl, String userName, String comapnyName) {
		String jobPostType;
		String quantiy;
		String newJobPostCreditAvailable = emailConfiguration.getProperty(
				"new.jobpost.credit.available").trim();
		for (JobPostingPlanDTO jobPostPlaningDto : orderDetailsDTO
				.getJobPostingPlanDTOList()) {
			jobPostType = jobPostPlaningDto.getJobPostPlanName();
			quantiy = String.valueOf(jobPostPlaningDto.getQuanity());
			newJobPostCreditAvailable=newJobPostCreditAvailable+"<tr><td width=\"25%\" align=\"center\" valign=\"middle\" style=\"padding-top:5px; padding-bottom:5px; border:1px solid #cccccc;\"><span style=\"font-family:Arial, Helvetica, sans-serif; font-size:14px;\">"
							+ userName
							+ "</span>"
							+ "</td><td width=\"25%\" align=\"center\" valign=\"middle\" style=\"padding-top:5px; padding-bottom:5px; border:1px solid #cccccc;\"><span style=\"font-family:Arial, Helvetica, sans-serif; font-size:14px;\">"
							+ comapnyName
							+ "</span>"
							+ "</td><td width=\"25%\" align=\"center\" valign=\"middle\" style=\"padding-top:5px; padding-bottom:5px; border:1px solid #cccccc;\"><span style=\"font-family:Arial, Helvetica, sans-serif; font-size:14px;\">"
							+ quantiy
							+ "</span>"
							+ "</td><td width=\"25%\" align=\"center\" valign=\"middle\" style=\"padding-top:5px; padding-bottom:5px; border:1px solid #cccccc;\"><span style=\"font-family:Arial, Helvetica, sans-serif; font-size:14px;\">"
							+ jobPostType + "</span>" + "</td></tr>";
		}
		newJobPostCreditAvailable=newJobPostCreditAvailable+"</table><span style=\"font-family:Arial, Helvetica, sans-serif; font-size:15px; color:#333333;\"><br /><a href=\""
						+ employerloginUrl
						+ "\" target=\"_blank\" style=\"color:#FF9900;\"><strong>Head over to your dashboard now</strong></a> to start posting new jobs. "
						+ "<br /><br /><br /></span> </td></tr></table>";
		
		stringBuffer.append(emailConfiguration
				.getProperty("employer.email.header").trim());
		stringBuffer.append(newJobPostCreditAvailable);
		stringBuffer.append(emailConfiguration
				.getProperty("email.footer").trim());
		emailDTO.setBody(stringBuffer.toString());
		emailDTO.setHtmlFormat(true);
		emailService.sendEmail(emailDTO);
	}
}
