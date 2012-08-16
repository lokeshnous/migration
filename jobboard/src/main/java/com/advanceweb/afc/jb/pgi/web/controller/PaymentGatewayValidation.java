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

	/**
	 * @param form
	 * @param errors
	 */
	public void validateCreditCardInfo(CreditCardInfoForm form, Errors errors) {
		if (StringUtils.isEmpty(form.getCreditCardNo())) {
			errors.rejectValue("creditCardInfoForm.creditCardNo", "NotEmpty",
					"Credit Card Number Should not be empty");
		} else if (!(form.getCreditCardNo().length() == 13)
				&& !(form.getCreditCardNo().length() == 16)) {
			errors.rejectValue("creditCardInfoForm.creditCardNo", "NotEmpty",
					"Please enter valid credit card number");
		}
		if (StringUtils.isEmpty(form.getName())) {
			errors.rejectValue("creditCardInfoForm.name", "NotEmpty",
					"Name Should not be empty");
		}
		if (StringUtils.isEmpty(form.getExpMonth())
				|| StringUtils.isEmpty(form.getExpYear())) {
			errors.rejectValue("creditCardInfoForm.expMonth", "NotEmpty",
					"Expiry Month, Year Should not be empty ");
		}
		if (StringUtils.isEmpty(form.getSecuriyCode())) {
			errors.rejectValue("creditCardInfoForm.securiyCode", "NotEmpty",
					"Security Code Should not be empty");
		} else if (!(form.getSecuriyCode().length() == 3)
				&& !(form.getSecuriyCode().length() == 4)) {
			errors.rejectValue("creditCardInfoForm.securiyCode", "NotEmpty",
					"Please enter valid security code");
		}
		if (StringUtils.isEmpty(form.getCardType())) {
			errors.rejectValue("creditCardInfoForm.cardType", "NotEmpty",
					"Card Type Should not be empty");
		}
	}

	/**
	 * @param form
	 * @param errors
	 */
	public void validateBillAddrInfo(BillingAddressForm form, Errors errors) {

		if (StringUtils.isEmpty(form.getFnameForBillingAddr())) {
			errors.rejectValue("billingAddressForm.fnameForBillingAddr",
					"NotEmpty", "First Name Should not be empty");
		}
		if (StringUtils.isEmpty(form.getLnameForBillingAddr())) {
			errors.rejectValue("billingAddressForm.lnameForBillingAddr",
					"NotEmpty", "Last Name Should not be empty");
		}
		if (StringUtils.isEmpty(form.getStreetForBillingAddr())) {
			errors.rejectValue("billingAddressForm.streetForBillingAddr",
					"NotEmpty", "Street Should not be empty");
		}
		if (StringUtils.isEmpty(form.getCityOrTownForBillingAddr())) {
			errors.rejectValue("billingAddressForm.cityOrTownForBillingAddr",
					"NotEmpty", "City Should not be empty");
		}

		if (StringUtils.isEmpty(form.getStateBillingAddress())
				|| MMJBCommonConstants.ZERO.equals(form
						.getStateBillingAddress())) {
			errors.rejectValue("billingAddressForm.stateBillingAddress",
					"NotEmpty", "State Should not be empty");
		}
		if (StringUtils.isEmpty(form.getCountryForBillingAddr())
				|| MMJBCommonConstants.ZERO.equals(form
						.getCountryForBillingAddr())) {
			errors.rejectValue("billingAddressForm.countryForBillingAddr",
					"NotEmpty", "Country Should not be empty");
		}
		if (StringUtils.isEmpty(form.getZipCodeForBillingAddr())) {
			errors.rejectValue("billingAddressForm.zipCodeForBillingAddr",
					"NotEmpty", "Zipcode Should not be empty");
		}
	}

	/**
	 * @param form
	 * @param errors
	 */
	private void validatePurchaseOrderNO(InvoiceForm form, Errors errors) {
		if (StringUtils.isEmpty(form.getPurchaseOrderNo())) {
			errors.rejectValue("invoiceForm.purchaseOrderNo", "NotEmpty",
					"Invoice Number Shold not be empty");
		}

	}

	/**
	 * Validating form
	 * 
	 * @param target
	 * @param errors
	 */
	public void validate(Object target, Errors errors) {
		PaymentMethodForm methodForm = (PaymentMethodForm) target;
		validateCreditCardInfo(methodForm.getCreditCardInfoForm(), errors);
		validateBillAddrInfo(methodForm.getBillingAddressForm(), errors);
	}

	/**
	 * @param target
	 * @param errors
	 */
	public void validateInvoice(Object target, Errors errors) {
		PaymentMethodForm methodForm = (PaymentMethodForm) target;
		validateBillAddrInfo(methodForm.getBillingAddressForm(), errors);
		validatePurchaseOrderNO(methodForm.getInvoiceForm(), errors);

	}

}
