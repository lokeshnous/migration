package com.advanceweb.afc.jb.pgi.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * @author muralikc
 * 
 */
@Component("paymentGatewayValidation")
public class PaymentGatewayValidation {

	public void validateCreditCardInfo(CreditCardInfoForm creditCardInfoForm,
			Errors errors) {
		if (StringUtils.isEmpty(creditCardInfoForm.getCreditCardNo())) {
			errors.rejectValue("creditCardInfoForm.creditCardNo", "NotEmpty",
					"Credit Card Number Should not be empty");
		}
		if (StringUtils.isEmpty(creditCardInfoForm.getName())) {
			errors.rejectValue("creditCardInfoForm.name", "NotEmpty",
					"Name Should not be empty");
		}
		if (StringUtils.isEmpty(creditCardInfoForm.getExpMonth())
				|| StringUtils.isEmpty(creditCardInfoForm.getExpYear())) {
			errors.rejectValue("creditCardInfoForm.expMonth", "NotEmpty",
					"Expiry Month, Year Should not be empty ");
		}
		if (StringUtils.isEmpty(creditCardInfoForm.getSecuriyCode())) {
			errors.rejectValue("creditCardInfoForm.securiyCode", "NotEmpty",
					"Security Code Should not be empty");
		}
		if (StringUtils.isEmpty(creditCardInfoForm.getCardType())) {
			errors.rejectValue("creditCardInfoForm.cardType", "NotEmpty",
					"Card Type Should not be empty");
		}

	}

	/**
	 * Validating form
	 * 
	 * @param target
	 * @param errors
	 */
	public void validate(Object target, Errors errors) {

		PaymentMethodForm paymentMethodForm = (PaymentMethodForm) target;

		validateCreditCardInfo(paymentMethodForm.getCreditCardInfoForm(),
				errors);
	}

}
