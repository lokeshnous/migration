package com.advanceweb.afc.jb.pgi.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;
import com.advanceweb.afc.jb.pgi.BillingAddressDTO;
import com.advanceweb.afc.jb.pgi.service.FetchAdmFacilityConatact;

/**
 * @author muralikc
 * 
 */
@Controller
@RequestMapping("/pgiController")
@SessionAttributes("paymentMethodForm")
public class PaymentGatewayController {

	@Autowired
	FetchAdmFacilityConatact fetchAdmFacilityConatact;

	@Autowired
	private PopulateDropdowns populateDropdownsService;

	@Autowired
	TransformPaymentMethod transformPaymentMethod;

	@Autowired
	PaymentGatewayValidation paymentGatewayValidation;
	
	@RequestMapping(value = "/callPaymentMethod", method = RequestMethod.GET)
	public ModelAndView callPaymentMethod(@Valid PaymentMethodForm form,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("gatewayPaymentMethod");
		return model;
	}

	@RequestMapping(value = "/paymentMethod", method = RequestMethod.POST)
	public ModelAndView gatewayPaymentMethod(@Valid PaymentMethodForm form,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		// Getting the facility id from the session
		int facilityId = 110;

		// Fetching the Account address from the database
		AccountAddressDTO accountAddressDTO = fetchAdmFacilityConatact
				.getConatactByFacilityId(facilityId);
		// Fetching the Billing address from the database
		BillingAddressDTO billingAddressDTO = fetchAdmFacilityConatact
				.getBillingAddByFacilityId(facilityId);
		if (billingAddressDTO == null) {
			billingAddressDTO = new BillingAddressDTO();
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

		form.setAccountAddressForm(accountAddressForm);
		form.setBillingAddressForm(billingAddressForm);

		model.addObject("paymentMethodForm", form);
		model.addObject("countryList", countryList);
		model.addObject("stateList", stateList);

		if (form.getPaymentMethod().equalsIgnoreCase(
				MMJBCommonConstants.CREDIT_CARD)) {
			model.setViewName("gatewayCreditBilling");
		} else {
			model.setViewName("gatewayInvoiceBilling");
		}
		return model;
	}

	@RequestMapping(value = "/paymentCreditBackMethod", method = RequestMethod.GET)
	public ModelAndView gatewayPaymentBackMethod(@Valid PaymentMethodForm form,
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
	public ModelAndView gatewayInvoiceBackMethod(@Valid PaymentMethodForm form,
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
	public ModelAndView gatewayConfirmOrder(@Valid PaymentMethodForm form,
			BindingResult result, HttpSession session) {
		ModelAndView model = new ModelAndView();
		paymentGatewayValidation.validateInvoice(form, result);

		// Getting street address, city, state, country, zip code, purchase oder
		// number for displaying the confirm order page
		String stAddr = form.getBillingAddressForm().getStreetForBillingAddr();
		String city = form.getBillingAddressForm()
				.getCityOrTownForBillingAddr();
		String state = form.getBillingAddressForm().getStateBillingAddress();
		String country = form.getBillingAddressForm()
				.getCountryForBillingAddr();
		String zipCode = form.getBillingAddressForm()
				.getZipCodeForBillingAddr();
		String orderNo = form.getInvoiceForm().getPurchaseOrderNo();
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
			@Valid PaymentMethodForm form, HttpSession session,
			BindingResult result) {
		// validate the credit card information
		ModelAndView model = new ModelAndView();
		paymentGatewayValidation.validate(form, result);
		// Getting street address, city, state, country, zip code, credit card
		// number, cardType for displaying the confirm order page
		String stAddr = form.getBillingAddressForm().getStreetForBillingAddr();
		String city = form.getBillingAddressForm()
				.getCityOrTownForBillingAddr();
		String state = form.getBillingAddressForm().getStateBillingAddress();
		String country = form.getBillingAddressForm()
				.getCountryForBillingAddr();
		String zipCode = form.getBillingAddressForm()
				.getZipCodeForBillingAddr();
		String creditCardNo = form.getCreditCardInfoForm().getCreditCardNo();
		String cardType = form.getCreditCardInfoForm().getCardType();
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

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/callThankYouPage", method = RequestMethod.POST)
	public ModelAndView gatewayThankyouPage(@Valid PaymentMethodForm form,
			Map map, HttpSession session) {
		ModelAndView model = new ModelAndView();
		// save or updating the billing address into the database
		BillingAddressForm billingAddressForm = form.getBillingAddressForm();
		BillingAddressDTO billingAddressDTO = transformPaymentMethod
				.transformBillingAddreFormToDto(billingAddressForm);
		fetchAdmFacilityConatact.saveBillingAddress(billingAddressDTO);
		model.setViewName("gatewayThankYou");
		return model;
	}

}
