package com.advanceweb.afc.jb.webservice;

import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.core.Response;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

import com.advanceweb.afc.jb.ServiceTest;

public class CustomerDetailsWSTest extends ServiceTest{

	public static void main(String[] args){

        CustomerDetailsWSTest customerDetailsWSTest = new CustomerDetailsWSTest();

        // Calling for customer details
        customerDetailsWSTest.getCustomerDetails();
        // Calling to authorize an user
        customerDetailsWSTest.authorizeUser();

	}


	/**
	 * This method is used to get customer details from netsuite.
	 * @return
	 */
	
	public String getCustomerDetails(){
		
		WebClient client = WebClient.create("https://rest.netsuite.com/app/site/hosting/restlet.nl?script=65&deploy=1");
		client.header("Authorization", "NLAuth nlauth_account=TSTDRV617993, nlauth_email=dharmarajns@nous.soft.net, nlauth_signature=dharma123, nlauth_role=3");
		client.header("Content-Type", "application/json");
        Response response = client.get();
        String jsonResponseString= null;

        try {
        	jsonResponseString = IOUtils.readStringFromStream((InputStream)response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get a string represenation of the response",e);
        }
        System.out.println("Json Response String for getCustomer details="+jsonResponseString);
        return jsonResponseString;

	}
	
	/**
	 * This method is used to authorize an user from netsuite.
	 * @return
	 */
	
	public String authorizeUser(){
		
		WebClient client = WebClient.create("https://rest.netsuite.com/app/site/hosting/restlet.nl?script=85&deploy=1");
		client.header("Authorization", "NLAuth nlauth_account=TSTDRV617993, nlauth_email=dharmarajns@nous.soft.net, nlauth_signature=dharma123, nlauth_role=3");
		client.header("Content-Type", "application/json");
        Response response = client.get();
        String jsonResponseString= null;

        try {
        	jsonResponseString = IOUtils.readStringFromStream((InputStream)response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get a string represenation of the response",e);
        }
        System.out.println("Json Response String for Authorize User="+jsonResponseString);
        return jsonResponseString;
		
	}
	
	
}
