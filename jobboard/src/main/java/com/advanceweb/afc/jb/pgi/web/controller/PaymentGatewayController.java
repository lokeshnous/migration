package com.advanceweb.afc.jb.pgi.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		//invoiceForm.setInvoiceEnabled(true);
		invoiceForm.setInvoiceEnabled(userDTO.isInvoiceEnabled());
		paymentGatewayForm.setInvoiceForm(invoiceForm);
		model.addObject("paymentGatewayForm",paymentGatewayForm);
		
		paymentGatewayForm.setPurchaseJobPostForm((PurchaseJobPostForm)session.getAttribute("purchaseJobPostForm"));
		model.setViewName("gatewayPaymentMethod");
		return model;
	}

	@RequestMapping(value = "/paymentMethod", method = RequestMethod.POST)
	public ModelAndView gatewayPaymentMethod(@Valid PaymentGatewayForm paymentGatewayForm,
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
		// Getting the countries from the database
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		// Getting the States from the database
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		// Converting AccountAddressDTO to Form
		AccountAddressForm accountAddressForm = transformPaymentMethod
				.transformAccountAddrDtoToForm(accountAddressDTO);
		// Converting BillingAddressDTO to Form
		BillingAddressForm billingAddressForm = transformPaymentMethod
				.transformBillingAddressDtoToForm(billingAddressDTO);

		paymentGatewayForm.setAccountAddressForm(accountAddressForm);
		paymentGatewayForm.setBillingAddressForm(billingAddressForm);
		
		
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);

		if (paymentGatewayForm.getPaymentMethod().equalsIgnoreCase(
				MMJBCommonConstants.CREDIT_CARD)) {
			if(null != paymentGatewayForm.getCreditCardInfoForm()){
				paymentGatewayForm.getCreditCardInfoForm().setCreditCardNo("");
				paymentGatewayForm.getCreditCardInfoForm().setSecuriyCode("");
			}
			paymentGatewayForm.setInvoiceForm(null);
			model.addObject("paymentGatewayForm", paymentGatewayForm);
			model.setViewName("gatewayCreditBilling");
		} else {
			paymentGatewayForm.setCreditCardInfoForm(null);
			model.addObject("paymentGatewayForm", paymentGatewayForm);
			model.setViewName("gatewayInvoiceBilling");
		}
		return model;
	}

	@RequestMapping(value = "/paymentCreditBackMethod", method = RequestMethod.GET)
	public ModelAndView gatewayPaymentBackMethod(@Valid PaymentGatewayForm paymentGatewayForm,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);
		model.setViewName("gatewayCreditBilling");
//		form.getCreditCardInfoForm().setCardType("");
//		form.getCreditCardInfoForm().setCreditCardNo("");
//		form.getCreditCardInfoForm().setExpMonth("");
//		form.getCreditCardInfoForm().setExpYear("");
//		form.getCreditCardInfoForm().setName("");
		
		return model;
	}
	@RequestMapping(value = "/paymentInvoiceBackMethod", method = RequestMethod.GET)
	public ModelAndView gatewayInvoiceBackMethod(@Valid PaymentGatewayForm paymentGatewayForm,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);
		model.setViewName("gatewayInvoiceBilling");
//		form.getInvoiceForm().setPurchaseOrderNo("");
		return model;
	}

	
	@RequestMapping(value = "/callInvoiceConfirmOrder", method = RequestMethod.POST)
	public ModelAndView gatewayConfirmOrder(@Valid PaymentGatewayForm paymentGatewayForm,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		paymentGatewayValidation.validateInvoice(paymentGatewayForm, result);

		// Getting street address, city, state, country, zip code, purchase oder
		// number for displaying the confirm order page
		String stAddr = paymentGatewayForm.getBillingAddressForm().getStreetForBillingAddr();
		String city = paymentGatewayForm.getBillingAddressForm()
				.getCityOrTownForBillingAddr();
		String state = paymentGatewayForm.getBillingAddressForm().getStateBillingAddress();
		String country = paymentGatewayForm.getBillingAddressForm()
				.getCountryForBillingAddr();
		String zipCode = paymentGatewayForm.getBillingAddressForm()
				.getZipCodeForBillingAddr();
		String orderNo = paymentGatewayForm.getInvoiceForm().getPurchaseOrderNo();
		model.addObject("stAddr", stAddr);
		model.addObject("city", city);
		model.addObject("state", state);
		model.addObject("country", country);
		model.addObject("zipCode", zipCode);
		model.addObject("orderNo", orderNo);
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();

		if (result.hasErrors()) {
			model.addObject("countryList", countryList);
			model.addObject("stateList", stateList);
			model.setViewName("gatewayInvoiceBilling");
			return model;
		}
		model.setViewName("gatewayInvoiceConfirmOrder");
		return model;
	}

	@RequestMapping(value = "/callCreditConfirmOrder", method = RequestMethod.POST)
	public ModelAndView gatewayCreditConfirmOrder(
			@Valid PaymentGatewayForm paymentGatewayForm, HttpSession session,
			BindingResult result) {
		// validate the credit card information
		ModelAndView model = new ModelAndView();
		paymentGatewayValidation.validate(paymentGatewayForm, result);
		// Getting street address, city, state, country, zip code, credit card
		// number, cardType for displaying the confirm order page
		String stAddr = paymentGatewayForm.getBillingAddressForm().getStreetForBillingAddr();
		String city = paymentGatewayForm.getBillingAddressForm()
				.getCityOrTownForBillingAddr();
		String state = paymentGatewayForm.getBillingAddressForm().getStateBillingAddress();
		String country = paymentGatewayForm.getBillingAddressForm()
				.getCountryForBillingAddr();
		String zipCode = paymentGatewayForm.getBillingAddressForm()
				.getZipCodeForBillingAddr();
		String creditCardNo = paymentGatewayForm.getCreditCardInfoForm().getCreditCardNo();
		String cardType = paymentGatewayForm.getCreditCardInfoForm().getCardType();
		// Getting last 4 digits of the credit card
		if (creditCardNo.length() != 0) {
			creditCardNo = creditCardNo.substring(creditCardNo.length() - 4);
		}
		model.addObject("stAddr", stAddr);
		model.addObject("city", city);
		model.addObject("state", state);
		model.addObject("country", country);
		model.addObject("zipCode", zipCode);
		model.addObject("creditCardNo", creditCardNo);
		model.addObject("cardType", cardType);

		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		if (result.hasErrors()) {
			model.addObject("countryList", countryList);
			model.addObject("stateList", stateList);
			model.setViewName("gatewayCreditBilling");
			return model;
		}
		model.setViewName("gatewayCreditConfirmOrder");
		return model;
	}

	@RequestMapping(value = "/callThankYouPage", method = RequestMethod.GET)
	public ModelAndView gatewayThankyouPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("gatewayThankYou");
		return model;
	}
	
	/**
	 * This method will be called to remove the item from job posting cart
	 * @param cartItemIndex
	 * @param session
	 * @return String
	 */
	@RequestMapping(value="/removeJobPost",method = RequestMethod.POST)
	public @ResponseBody String removeJobPost(PaymentGatewayForm paymentGatewayForm,
			@RequestParam("cartItemIndex") int cartItemIndex, HttpSession session) {
		PurchaseJobPostForm purchaseJobPostForm = paymentGatewayForm.getPurchaseJobPostForm();		
		JobPostingsForm cartItem = purchaseJobPostForm.getJobPostingsCart().get(cartItemIndex);
		purchaseJobPostForm.setGrandTotal(purchaseJobPostForm.getGrandTotal() - cartItem.getPackageSubTotal());
		purchaseJobPostForm.getJobPostingsCart().remove(cartItemIndex);
		return 	"success";
	}
	
	@RequestMapping(value="/placeOrder",method = RequestMethod.POST)
	public ModelAndView placeOrder(PaymentGatewayForm paymentGatewayForm, HttpSession session) {
		//call web service here. If order success save order details in db & move to Thank you page else move to error page
		OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
		
		orderDetailsDTO = transformPaymentMethod.transformToOrderDetailsDTO(paymentGatewayForm);
		orderDetailsDTO.setFacilityId((Integer) session.getAttribute(MMJBCommonConstants.FACILITY_ID));
		orderDetailsDTO.setUserId((Integer) session.getAttribute(MMJBCommonConstants.USER_ID));
		orderDetailsDTO.setNsCustomeId(paymentGatewayForm.getNsCustomerId());
		
		boolean status = paymentGatewayService.createOrder(orderDetailsDTO);		

		//once the payment is success clear out the form data & related session data
		session.removeAttribute("purchaseJobPostForm");
		paymentGatewayForm = null;		
		ModelAndView model = new ModelAndView();
		if(status){
			model.setViewName("redirect:/pgiController/callThankYouPage.html");
		}
		else
		{
			model.setViewName("redirect:/pgiController/callThankYouPage.html");
		}
		return model;
	}

}
