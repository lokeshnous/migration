package com.advanceweb.afc.jb.pgi.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

/**
 * @author muralikc
 * 
 */
@Component("paymentGatewayValidation")
public class PaymentGatewayValidation {
	private static final String NOTEMPTY = "NotEmpty";

	/**
	 * @param form
	 * @param errors
	 */
	public void validateCreditCardInfo(CreditCardInfoForm form, Errors errors) {
		if (StringUtils.isEmpty(form.getCreditCardNo())) {
			errors.rejectValue("creditCardInfoForm.creditCardNo", NOTEMPTY,
					"Credit card number should not be blank.");
		} else if (!(form.getCreditCardNo().length() == 13)
				&& !(form.getCreditCardNo().length() == 16) &&  !(form.getCreditCardNo().length() == 15)) {
			errors.rejectValue("creditCardInfoForm.creditCardNo", NOTEMPTY,
					"Please enter valid credit card number.");
		}
		if (StringUtils.isEmpty(form.getName())) {
			errors.rejectValue("creditCardInfoForm.name", NOTEMPTY,
					"Name should not be blank.");
		}
		if (StringUtils.isEmpty(form.getExpMonth())
				|| StringUtils.isEmpty(form.getExpYear())) {
			errors.rejectValue("creditCardInfoForm.expMonth", NOTEMPTY,
					"Please select a Expiry Month and Year.");
		}
		validateCardTypeSecurityCode(form, errors);
		
	}

	private void validateCardTypeSecurityCode(CreditCardInfoForm form,
			Errors errors) {
		
		if (StringUtils.isEmpty(form.getCardType())) {
			errors.rejectValue("creditCardInfoForm.cardType", NOTEMPTY,
					"Please select a Card type.");
		}
		if (StringUtils.isEmpty(form.getSecuriyCode())) {
			errors.rejectValue("creditCardInfoForm.securiyCode", NOTEMPTY,
					"Security code should not be blank.");
		}else if((!("6").equals(form.getCardType()) 
				&& form.getSecuriyCode().length() != 3) 
		|| (("6").equals(form.getCardType()) && form.getSecuriyCode().length() < 4 )){
			//Discover - 3, Visa - 5, Master card - 4, American Express - 6 
			//These values are hard coded in jsp
			errors.rejectValue("creditCardInfoForm.securiyCode", NOTEMPTY,
					"Please enter valid security code.");
		}
	}

	/**
	 * @param form
	 * @param errors
	 */
	public void validateBillAddrInfo(BillingAddressForm form, Errors errors) {
		if (StringUtils.isEmpty(form.getFnameForBillingAddr())) {
			errors.rejectValue("billingAddressForm.fnameForBillingAddr",
					NOTEMPTY, "First name should not be blank.");
		}
		if (StringUtils.isEmpty(form.getLnameForBillingAddr())) {
			errors.rejectValue("billingAddressForm.lnameForBillingAddr",
					NOTEMPTY, "Last name should not be blank.");
		}
		if (StringUtils.isEmpty(form.getStreetForBillingAddr())) {
			errors.rejectValue("billingAddressForm.streetForBillingAddr",
					NOTEMPTY, "Street should not be blank.");
		}
		if (StringUtils.isEmpty(form.getCityOrTownForBillingAddr())) {
			errors.rejectValue("billingAddressForm.cityOrTownForBillingAddr",
					NOTEMPTY, "City should not be blank.");
		}

		if (StringUtils.isEmpty(form.getStateBillingAddress())
				|| MMJBCommonConstants.ZERO.equals(form
						.getStateBillingAddress())) {
			errors.rejectValue("billingAddressForm.stateBillingAddress",
					NOTEMPTY, "Please select a State.");
		}
		if (StringUtils.isEmpty(form.getCountryForBillingAddr())
				|| MMJBCommonConstants.ZERO.equals(form
						.getCountryForBillingAddr())) {
			errors.rejectValue("billingAddressForm.countryForBillingAddr",
					NOTEMPTY, "Please select a Country.");
		}
		if (StringUtils.isEmpty(form.getZipCodeForBillingAddr())) {
			errors.rejectValue("billingAddressForm.zipCodeForBillingAddr",
					NOTEMPTY, "Zipcode should not be blank.");
		}
	}

	/**
	 * @param form
	 * @param errors
	 */
	private void validatePurchaseOrderNO(InvoiceForm form, Errors errors) {
		if (StringUtils.isEmpty(form.getPurchaseOrderNo())) {
			errors.rejectValue("invoiceForm.purchaseOrderNo", NOTEMPTY,
					"Invoice Number should not be blank.");
		}

	}

	/**
	 * Validating form
	 * 
	 * @param target
	 * @param errors
	 */
	public void validate(Object target, Errors errors) {
		PaymentGatewayForm methodForm = (PaymentGatewayForm) target;
		validateCreditCardInfo(methodForm.getCreditCardInfoForm(), errors);
		validateBillAddrInfo(methodForm.getBillingAddressForm(), errors);
	}

	/**
	 * @param target
	 * @param errors
	 */
	public void validateInvoice(Object target, Errors errors) {
		PaymentGatewayForm methodForm = (PaymentGatewayForm) target;
		validateBillAddrInfo(methodForm.getBillingAddressForm(), errors);
		validatePurchaseOrderNO(methodForm.getInvoiceForm(), errors);

	}

}
