package com.advanceweb.afc.jb.webservice;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import net.sf.json.JSONObject;

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
        //customerDetailsWSTest.authorizeUser("customer", "NS101");
        //calling to authorise user using POST
        //customerDetailsWSTest.getCustomerDetails("customer",1596);
        //Call for item services
        //customerDetailsWSTest.getItemServices();
        // Call fro craeting salesorder
        
        //customerDetailsWSTest.createSalesOrder("459468", 1596, 1, 2930);
        // Call for create Customer
        	//customerDetailsWSTest.createCustomer("Customer1", "company");
        //customerDetailsWSTest.createCashSales();
      // customerDetailsWSTest.updateCustomer("{"customerId":460460,"customerName":"Customer5","recordType":"customer"}");

	}

	/**
	 * This method is used to get customer details from netsuite.
	 * 
	 * @return
	 */

	
	/*public String getCustomerDetails(){
		
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
	}*/

	/**
	 * This method is used to authorize an user from netsuite.
	 * 
	 * @return
	 */

	
	public String authorizeUser(String recdType, String entId){
		
		Object recordType = recdType;
		Object entityID = entId;
		String authorization = getAuthString();
		WebClient client = WebClient.create("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=153&deploy=1&recordtype="+recordType+"&entityid="+entityID);
		client.header("Authorization", authorization);
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
		System.out.println("Json Response String for Authorize User="+ jsonResponseString);
		return jsonResponseString;
	}
	/**
	 * This method is used to create a WebClient Object by taking the 
	 * web services URL. 
	 * @return WebClient obj
	 */
	 private WebClient createWebClient(String wsUrl) {
        String authorization = getAuthString();
		WebClient client = WebClient.create(wsUrl);
        client.header("Authorization", authorization);
        client.header("Content-Type", "application/json");
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
	 
	 
	 
	 private String getCustomerDetails(String recdType, int custId){
		// String authorization = getAuthString();
		 Object recordType = recdType;
		 Object cutomerId = custId;
	    
	     WebClient client = createWebClient("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=152&deploy=1&recordtype="+recordType+"&id="+cutomerId);
	     /*client.header("Authorization", authorization);
	     client.header("Content-Type", "application/json");*/
		 Response response = client.get();
		String jsonResult = null;
		try {
			jsonResult = IOUtils.readStringFromStream((InputStream)response.getEntity());
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get a string represenation of the response",e);
		}
		System.out.println("Result for get Customer Details user="+jsonResult);
		
		return jsonResult;
	 }
	 
	 /**
	  * This method is used to get the item services from NetSuite
	  * @return
	  */
	 
	 private String getItemServices(){
		//String authorization = getAuthString();
		WebClient client = createWebClient("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=149&deploy=1");
		/*client.header("Authorization", authorization);
	    client.header("Content-Type", "application/json");*/
		
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
	 
	 
	 public String createSalesOrder(String itemIntrnlId, int custId, int locId, int discId){
		String authorization = getAuthString();
		WebClient client = WebClient.create("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=151&deploy=1");
		client.header("Authorization", authorization);
	    client.header("Content-Type", "application/json");
		Object obj = "{ \"item\": [{\"item\": \"2956\",\"quantity\": \"3\", \"taxcode\" : \"2984\"}, { \"item\": \"2955\", \"quantity\": \"4\", \"taxcode\" : \"2984\" }], \"entity\" : \"459468\", \"location\" :\"1\", \"discountitem\" : \"2930\", \"paymentmethod\" : \"6\", \"ccnumber\": \"378282246310005\", \"ccexpiredate\" : \"09/2013\", \"ccname\": \"Mohammed Zubair Ahmed\", \"cczipcode\" : \"560002\", \"ccstreet\" : \"HSR Layout\" }";	
		//Object obj ="{ item : 2956 , entity : 459468 , location : 1 , discountitem : 2930 }";
		Response response = client.post(obj);
		 
		 
		 //Response response = client.get();
	     String jsonResponseString= null;
		 
	     try {
        	jsonResponseString = IOUtils.readStringFromStream((InputStream)response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get a string represenation of the response",e);
        }
        System.out.println("Json Response String for create Sales Order ="+jsonResponseString);
        return jsonResponseString;
	 }
	 
	 
	 public String createCustomer(String compName, String recdType){
		 String companyName = compName;
		 String recordType = recdType;
		 
		 String authorization = getAuthString();
		 //Object entityId = "{"customerId":460460,"customerName":"Customer5","recordType":"customer"}";
		 Object entityId = "{\"companyname\": \"Customer2\", \"recordtype\" : \"Customer\"}";
		 
		 	// Object entityId = "{companyname : Customer1, recordtype : Customer}";
		 
		WebClient client = WebClient.create("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=154&deploy=1&companyname="+companyName+"&recordtype"+recordType);
		client.header("Authorization", authorization);
	    client.header("Content-Type", "application/json");
	    
	    
		Response response = client.post(entityId);
		 
		 //Response response = client.get();
	     String jsonResponseString= null;
		 
	     try {
        	jsonResponseString = IOUtils.readStringFromStream((InputStream)response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get a string represenation of the response",e);
        }
        System.out.println("Json Response String for create Customer ="+jsonResponseString);
        return jsonResponseString;
	 }
	 
	 
	 public String updateCustomer(Object json){
		 String authorization = getAuthString();
		 //update
		 //Object entityId = "{\"entityid\": \"Sample Customer\", \"companyname\" : \"Sample Customer\",\"phone\" : \"121-456-789\"}";
		 //Create
		 
		 //JSONObject json = (JSONObject)new JSONParser().parse("{\"name\":\"MyNode\", \"width\":200, \"height\":100}");
		
		 //String test =  JSONObject.escape("{"customerId":460460,"customerName":"Customer5","recordType":"customer"}");

		 //Object ent="{"customerId":"\460460,"customerName":"Customer5","recordType":"customer"}";

		 
		// Object entityId = "{\"recordtype\": \"customer\", \"internalid\": 460460, \"phone\" : \"121-454-789\"}";
		 
		WebClient client = WebClient.create("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=156&deploy=1");
		client.header("Authorization", authorization);
	    client.header("Content-Type", "application/json");
	    
	    
		Response response = client.post(json);
		 
		 //Response response = client.get();
	     String jsonResponseString= null;
		 
	     try {
        	jsonResponseString = IOUtils.readStringFromStream((InputStream)response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get a string represenation of the response",e);
        }
        System.out.println("Json Response String for create Customer ="+jsonResponseString);
        return jsonResponseString;
	 }
	 
	 

	 
	 public String createCashSales(){
		 String authorization = getAuthString();
		 
		WebClient client = WebClient.create("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=155&deploy=1");
		client.header("Authorization", authorization);
	    client.header("Content-Type", "application/json");
		Object obj = "{ \"item\": [{\"item\": \"2956\",\"quantity\": \"3\", \"taxcode\" : \"2984\"}, { \"item\": \"2955\", \"quantity\": \"4\", \"taxcode\" : \"2984\" }], \"entity\" : \"459468\", \"location\" :\"1\", \"discountitem\" : \"2930\", \"paymentmethod\" : \"6\", \"ccnumber\": \"378282246310005\", \"ccexpiredate\" : \"09/2013\", \"ccname\": \"Mohammed Zubair Ahmed\", \"cczipcode\" : \"560002\", \"ccstreet\" : \"HSR Layout\", \"saleOrderNumber\" : \"12108\" }";	
		Response response = client.post(obj);
		 //Response response = client.get();
	     String jsonResponseString= null;
		 
	     try {
        	jsonResponseString = IOUtils.readStringFromStream((InputStream)response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get a string represenation of the response",e);
        }
        System.out.println("Json Response String for Cash Sales ="+jsonResponseString);
        return jsonResponseString;
	 }
	
	
}
