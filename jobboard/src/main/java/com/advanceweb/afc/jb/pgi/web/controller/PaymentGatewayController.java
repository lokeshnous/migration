package com.advanceweb.afc.jb.pgi.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * @version 1.0
 * @since 02 Aug 2012
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

	@RequestMapping(value = "/paymentMethod", method = RequestMethod.POST)
	public ModelAndView gatewayPaymentMethod(
			@Valid PaymentMethodForm paymentMethodForm, BindingResult result,
			HttpSession session, Model model) {

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

		paymentMethodForm.setAccountAddressForm(accountAddressForm);
		paymentMethodForm.setBillingAddressForm(billingAddressForm);

		model.addAttribute("paymentMethodForm", paymentMethodForm);
		model.addAttribute("countryList", countryList);
		model.addAttribute("stateList", stateList);

		if (paymentMethodForm.getPaymentMethod().equalsIgnoreCase(
				MMJBCommonConstants.CREDIT_CARD)) {
			return new ModelAndView("gatewayCreditBilling");
		} else {
			return new ModelAndView("gatewayInvoiceBilling");
		}
	}

	@RequestMapping(value = "/paymentBackMethod", method = RequestMethod.GET)
	public ModelAndView gatewayPaymentBackMethod(
			@Valid PaymentMethodForm paymentMethodForm, Model model,
			HttpSession session) {
		List<CountryDTO> countryList = populateDropdownsService
				.getCountryList();
		List<StateDTO> stateList = populateDropdownsService.getStateList();
		model.addAttribute("countryList", countryList);
		model.addAttribute("stateList", stateList);
		return new ModelAndView("gatewayCreditBilling");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/callPaymentMethod", method = RequestMethod.GET)
	public ModelAndView callPaymentMethod(
			@Valid PaymentMethodForm paymentMethodForm, Map map,
			HttpSession session) {
		map.put("paymentMethodForm", paymentMethodForm);
		return new ModelAndView("gatewayPaymentMethod");
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/callInvoiceConfirmOrder", method = RequestMethod.POST)
	public ModelAndView gatewayConfirmOrder(
			@Valid PaymentMethodForm paymentMethodForm, Map map,
			HttpSession session) {
		return new ModelAndView("gatewayInvoiceConfirmOrder");
	}

	@RequestMapping(value = "/callCreditConfirmOrder", method = RequestMethod.POST)
	public ModelAndView gatewayCreditConfirmOrder(
			@Valid PaymentMethodForm paymentMethodForm, Model model,
			HttpSession session, BindingResult result) {
		// validate the credit card information
		paymentGatewayValidation.validate(paymentMethodForm, result);
		// Getting street address, city, state, country, zipcode, credit card
		// number
		// for displaying the confirm order page
		String stAddr = paymentMethodForm.getBillingAddressForm()
				.getStreetForBillingAddr();
		String city = paymentMethodForm.getBillingAddressForm()
				.getCityOrTownForBillingAddr();
		String state = paymentMethodForm.getBillingAddressForm()
				.getStateBillingAddress();
		String country = paymentMethodForm.getBillingAddressForm()
				.getCountryForBillingAddr();
		String zipCode = paymentMethodForm.getBillingAddressForm()
				.getZipCodeForBillingAddr();
		String creditCardNo = paymentMethodForm.getCreditCardInfoForm()
				.getCreditCardNo();
		// Getting last 4 digits of the credit card
		creditCardNo = creditCardNo.substring(creditCardNo.length() - 4);
		model.addAttribute("stAddr", stAddr);
		model.addAttribute("city", city);
		model.addAttribute("state", state);
		model.addAttribute("country", country);
		model.addAttribute("zipCode", zipCode);
		model.addAttribute("creditCardNo", creditCardNo);

		if (result.hasErrors()) {
			return new ModelAndView("gatewayCreditBilling");
		}
		return new ModelAndView("gatewayCreditConfirmOrder");
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/callThankYouPage", method = RequestMethod.POST)
	public ModelAndView gatewayThankyouPage(@Valid PaymentMethodForm pgiMethod,
			Map map, HttpSession session) {
		// save or updating the billing address into the database
		BillingAddressForm billingAddressForm = pgiMethod
				.getBillingAddressForm();
		BillingAddressDTO billingAddressDTO = transformPaymentMethod
				.transformBillingAddreFormToDto(billingAddressForm);
		fetchAdmFacilityConatact.saveBillingAddress(billingAddressDTO);
		return new ModelAndView("gatewayThankYou");
	}

}
