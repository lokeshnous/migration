package com.advanceweb.afc.jb.webservice;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import com.advanceweb.afc.jb.ServiceTest;

public class CustomerDetailsWSTest extends ServiceTest {
	private static final Logger LOGGER = Logger
			.getLogger(CustomerDetailsWSTest.class);


	public static void main(String[] args){

        CustomerDetailsWSTest customerDetailsWSTest = new CustomerDetailsWSTest();

        // Calling for customer details
       // customerDetailsWSTest.getCustomerDetails();
        // Calling to authorize an user
        //customerDetailsWSTest.authorizeUser();
        //calling to authorise user using POST
        customerDetailsWSTest.getCustomerDetailsPOST(1593);
        //Call for item services
        //customerDetailsWSTest.getItemServices();


	}

	/**
	 * This method is used to get customer details from netsuite.
	 * 
	 * @return
	 */

	
	public String getCustomerDetails(){
		
		WebClient client = createWebClient();

		Response response = client.get();
		String jsonResponseString = null;
		try {
			jsonResponseString = IOUtils
					.readStringFromStream((InputStream) response.getEntity());
		} catch (IOException e) {
			LOGGER.info("Failed to get a string represenation of the response",
					e);
		}
		LOGGER.info("Json Response String for getCustomer details="
				+ jsonResponseString);
		return jsonResponseString;
	}

	/**
	 * This method is used to authorize an user from netsuite.
	 * 
	 * @return
	 */

	
	public String authorizeUser(){
		
		WebClient client = WebClient.create("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=153&deploy=1");
		client.header("Authorization", "NLAuth nlauth_account=TSTDRV617993, nlauth_email=dharmarajns@nous.soft.net, nlauth_signature=dharma123, nlauth_role=3");

		client.header("Content-Type", "application/json");
		Response response = client.get();
		String jsonResponseString = null;

		try {
			jsonResponseString = IOUtils
					.readStringFromStream((InputStream) response.getEntity());

		} catch (IOException e) {
			LOGGER.info("Failed to get a string represenation of the response",
					e);
		}
		LOGGER.info("Json Response String for Authorize User="
				+ jsonResponseString);
		return jsonResponseString;
	}
	 private WebClient createWebClient() {
        String authorization = getAuthString();
        //getCustomerDetails()
        Object jsonval = "[{entityid:1593}]";
		WebClient client = WebClient.create("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=152&deploy=1&customerid="+jsonval);
        //get item service
		//WebClient client = WebClient.create("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=149&deploy=1");
		

		/*Cookie cookie = new Cookie();
		cookie.*/
		
        client.header("Authorization", authorization);
        client.header("Content-Type", "application/json");
        //client.
        return client;
	 }
	 
	 private String getAuthString(){
		String account = "1338577";
        String email = "pravinma@nousinfo.com";
        String password = "pravin123";
        String role = "3";
        String authorization = "NLAuth nlauth_account=" + account + ", nlauth_email=" + email + ", nlauth_signature=" + password + ", nlauth_role=" + role + "";
		return authorization;
	 }
	 
	 
	 
	 private String getCustomerDetailsPOST(int cutomerId){
		 
		WebClient client = createWebClient();
		//Object jsonPayload ="{\"fiddle:FiddleSelection\":{\"xmlns:jaxb\":\"http://java.sun.com/xml/ns/jaxb\",\"xmlns:fiddle\":\"http://www.wuhutravel.com/fiddle\",\"xmlns:xsi\":\"http://www.w3.org/2001/XMLSchema-instance\",\"xsi:schemaLocation\":\"http://www.wuhutravel.com/fiddle file:/C:/wuhu/code/server/trunk/projects/Build/ServiceFrontBeans/xsd/Fiddle.xsd\",\"venue_name\":\"fly\",\"venue_code\":\"FLY\",\"venue_quantifier_selection\":{\"venue_name\":\"fly\",\"venue_code\":\"FLY\",\"quantifier_id\":[1,2,3]},\"venue_qualifier_selection\":{\"venue_name\":\"fly\",\"venue_code\":\"FLY\",\"qualifier_id\":[103]}}}";
		//Object jsonval = "[{customerId:156}]";
		
		
		//Object jsonval = "[{\"entityid\": \"1593\"}]";
		 Response response = client.get();
		//Response response = client.post((Object)new Integer(cutomerId));
		//client.query("", values)
		//Response response = client.put(jsonval);
		//Response response = client.invoke("GET", jsonval);;
		//client.post(body);
		String jsonResult = null;
		try {
			jsonResult = IOUtils.readStringFromStream((InputStream)response.getEntity());
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get a string represenation of the response",e);
		}
		System.out.println("Result for POST get Customer Details user="+jsonResult);
		
		return jsonResult;
	 }
	 
	 
	 private String getItemServices(){
		 
		WebClient client = createWebClient();
        Response response = client.get();
        String jsonResponseString= null;

        try {
        	jsonResponseString = IOUtils.readStringFromStream((InputStream)response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get a string represenation of the response",e);
        }
        System.out.println("Json Response String for GET ItemServices details="+jsonResponseString);
        return jsonResponseString;
		 
	 }
	
	
}
