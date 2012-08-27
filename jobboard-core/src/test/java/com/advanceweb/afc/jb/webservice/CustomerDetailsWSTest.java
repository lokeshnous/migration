package com.advanceweb.afc.jb.webservice;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.advanceweb.afc.jb.ServiceTest;

public class CustomerDetailsWSTest extends ServiceTest{

	RestTemplate template = new RestTemplate();
	
	public void getCustomerDetails(){
		String uri = "https://rest.netsuite.com/app/site/hosting/restlet.nl?script=65&deploy=1";

		try {

			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("Authorization","NLAuth nlauth_account=TSTDRV617993, nlauth_email=dharmarajns@nous.soft.net, nlauth_signature=dharma123, nlauth_role=3");
			requestHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<?> requestEntity = new HttpEntity(requestHeaders);
			HttpEntity<String> response = template.exchange(uri, HttpMethod.GET, requestEntity, String.class);
			Assert.notNull(response.getBody());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
