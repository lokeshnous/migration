package com.advanceweb.afc.jb.common;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:24:23 PM
 */
public class BaseProfileDTO extends ProfileDTO {

	public AlertsListDTO alertsListDTO;
	public ContactInformationDTO contactInformationDTO;
	public AlertsListDTO m_AlertsListDTO;
	public ContactInformationDTO m_ContactInformationDTO;
	public SubscriptionListDTO m_SubscriptionListDTO;
	public SubscriptionListDTO subscriptionListDTO;

	public BaseProfileDTO() {

	}

	@Override
	public void finalize() throws Throwable {

	}

}