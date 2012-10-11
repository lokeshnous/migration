package com.advanceweb.afc.jb.webservice;


public class CustomerDetailsWSTest{/*
	private static final Logger LOGGER = Logger
			.getLogger(CustomerDetailsWSTest.class);


	public static void main(String[] args){

        CustomerDetailsWSTest customerDetailsWSTest = new CustomerDetailsWSTest();

        
        
        
        // Calling for customer details
       // customerDetailsWSTest.getCustomerDetails();
        // Calling to authorize an user
        //customerDetailsWSTest.authorizeUser("customer", "NS101");
        //calling to authorise user using POST
        customerDetailsWSTest.getCustomerDetails();
        //Call for item services
        //customerDetailsWSTest.getItemServices();
        // Call fro craeting salesorder
       
        //customerDetailsWSTest.createSalesOrder();
        // Call for create Customer
        	//customerDetailsWSTest.createCustomer("Customer1", "company");
        //customerDetailsWSTest.createCashSales();
      //customerDetailsWSTest.updateCustomer();

	}


	*//**
	 * This method is used to authorize an user from netsuite.
	 * 
	 * @return
	 *//*
	
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
		LOGGER.info("Json Response String for Authorize User="+ jsonResponseString);
		return jsonResponseString;
	}
	*//**
	 * This method is used to create a WebClient Object by taking the 
	 * web services URL. 
	 * @return WebClient obj
	 *//*
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
        //String authorization = "NLAuth nlauth_account=" + account + ", nlauth_email=" + email + ", nlauth_signature=" + password + ", nlauth_role=" + role;
		return "NLAuth nlauth_account=" + account + ", nlauth_email=" + email + ", nlauth_signature=" + password + ", nlauth_role=" + role;
	 }
	 
	 
	 
	 private String getCustomerDetails(){
		// String authorization = getAuthString();
		
	    
	     //WebClient client = createWebClient("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=152&deploy=1&recordtype="+recordType+"&id="+cutomerId);
	     
		 WebClient client = createWebClient("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=152&deploy=1&recordtype=customer&id=469773");//465670
	     client.header("Authorization", authorization);
	     client.header("Content-Type", "application/json");
		 Response response = client.get();
		 
		 
		   {"phone":"000-999-7777","contactroles":[{"contact":{"name":"NS63744","internalid":"462667"},"contactname":"NS63744 ss d sdgsdgsd","email":"sd@gmail.com","giveaccess":false,"role":{"name":"Customer Center","internalid":"14"},"sendemail":false}],"globalsubscriptionstatus":{"name":"Soft Opt-In","internalid":"1"},"giveaccess":false,"isperson":true,"lastname":"sdgsdgsd","datecreated":"9/7/2012 8:48 am","custentity_ccrequired":false,"custentity_ahafacility":false,"lastmodifieddate":"9/7/2012 8:48 am","id":"462667","custentity_invindividually":false,"balance":0,"emailtransactions":false,"custentity_porequired":false,"custentity_holdsecnotice":false,"custentityfeaturedemployee":false,"entitystatus":{"name":"CUSTOMER-- New Pending Approval","internalid":"17"},"isbudgetapproved":false,"faxtransactions":false,"recordtype":"customer","emailpreference":{"name":"Default","internalid":"DEFAULT"},"sendemail":false,"unbilledorders":0,"middlename":"dgsdg","subscriptions":[{"lastmodifieddate":"9/7/2012 8:48 am","subscribed":false,"subscription":{"name":"Advance for NPs & PAs","internalid":"6"}},{"lastmodifieddate":"9/7/2012 8:48 am","subscribed":true,"subscription":{"name":"Billing Communication","internalid":"2"}},{"lastmodifieddate":"9/7/2012 8:48 am","subscribed":true,"subscription":{"name":"Marketing","internalid":"1"}},{"lastmodifieddate":"9/7/2012 8:48 am","subscribed":true,"subscription":{"name":"Newsletters","internalid":"4"}},{"lastmodifieddate":"9/7/2012 8:48 am","subscribed":true,"subscription":{"name":"Product Updates","internalid":"5"}},{"lastmodifieddate":"9/7/2012 8:48 am","subscribed":true,"subscription":{"name":"Surveys","internalid":"3"}}],"taxable":true,"custentity_autopickup":false,"entityid":"NS63744 ss d sdgsdgsd","isinactive":false,"depositbalance":0,"printtransactions":false,"firstname":"ss","companyname":"asffas","custentityinvoiceenabled":false,"email":"sd@gmail.com","weblead":"No","receivablesaccount":{"name":"Use System Preference","internalid":"-10"},"overduebalance":0,"custentitycustxmlfeed":false,"creditholdoverride":{"name":"Auto","internalid":"AUTO"},"custentitycreditappstatus":{"name":"Pending","internalid":"3"}}
		  
		 
		 
		String jsonResult = null;
		try {
			jsonResult = IOUtils.readStringFromStream((InputStream)response.getEntity());
			
			
			try {
				org.codehaus.jettison.json.JSONObject jsonObject  = new org.codehaus.jettison.json.JSONObject(jsonResult);
				//jsonObject.has("contactroles");
				System.out.println(jsonObject.has("contactroles"));
				JSONArray jsonArray = new JSONArray();
				if(jsonObject.has("contactroles")){
					jsonArray = (JSONArray) jsonObject.get("contactroles");
				}
				System.out.println("jsonArray="+jsonArray);
				for(int index = 0; index < jsonArray.length(); index++ ){
					JSONObject innerJsonObj = jsonArray.getJSONObject(index);
				System.out.println("contacts=>"+innerJsonObj.getString("email"));
				}
				org.codehaus.jettison.json.JSONObject jsonObject  = new org.codehaus.jettison.json.JSONObject(jsonResult);
				//jsonObject.has("contactroles");
				System.out.println(jsonObject.has("custentitypackagetype"));
				org.codehaus.jettison.json.JSONObject jsonObj  = (JSONObject) jsonObject.get("custentitypackagetype");
				if(jsonObj.has("name")){
					System.out.println(jsonObj.get("name"));
				}
				
				//Iterator<String> myIter = jsonObject.keys();
				if(jsonObject.has("contactroles")){
					
				}
				
				org.codehaus.jettison.json.JSONObject jsonObject  = new org.codehaus.jettison.json.JSONObject(jsonResult);
				//System.out.println(jsonObject);
				JSONArray jsonArray = new JSONArray();
				List<String> emailList = new ArrayList<String>();
				if(jsonObject.has("contactroles")){
					jsonArray = (JSONArray) jsonObject.get("contactroles");
				}
				for(int index = 0; index < jsonArray.length(); index++ ){
					org.codehaus.jettison.json.JSONObject innerJsonObj = jsonArray.getJSONObject(index);
					emailList.add(innerJsonObj.getString("email"));
					//System.out.println("contacts=>"+innerJsonObj.getString("email"));
				}
//				System.out.println("emailList=="+emailList);
				
//				System.out.println(jsonObject.get("contactroles"));
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				LOGGER.info(e);
			}
			
			
			if(jsonObject.has(FEATURED_START_DATE_STRING)){
				userDTO.setFeaturedStartDate(convertToDate(jsonObject.get(FEATURED_START_DATE_STRING).toString()));
				LOGGER.info("FEATURED START DATE IS=>"+jsonObject.get(FEATURED_START_DATE_STRING));
			}
			
			
		} catch (IOException e) {
			LOGGER.info(e);
			//throw new RuntimeException("Failed to get a string represenation of the response",e);
		}
//		System.out.println("Result for get Customer Details user="+jsonResult);
		
		return jsonResult;
	 }
	 
	 *//**
	  * This method is used to get the item services from NetSuite
	  * @return
	  *//*
	 
	 private String getItemServices(){
		//String authorization = getAuthString();
		WebClient client = createWebClient("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=149&deploy=1");
		client.header("Authorization", authorization);
	    client.header("Content-Type", "application/json");
		
        Response response = client.get();
        String jsonResponseString= null;

        try {
        	jsonResponseString = IOUtils.readStringFromStream((InputStream)response.getEntity());

        } catch (IOException e) {
        	LOGGER.info(e);
           // throw new RuntimeException("Failed to get a string represenation of the response",e);
        }
//        System.out.println("Json Response String for GET ItemServices details="+jsonResponseString);
        return jsonResponseString;
		 
	 }
	 
	 
	 public String createSalesOrder(String itemIntrnlId, int custId, int locId, int discId){
		String authorization = getAuthString();
		//WebClient client = WebClient.create("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=151&deploy=1");
		WebClient client = WebClient.create("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl");
		client.query("script", "151");
		client.query("deploy", "1");
		client.header("Authorization", authorization);
	    client.header("Content-Type", "application/json");
	    //"Your Transaction has Successful: transaction number (Payment for future=14806"
		Object obj = "{ \"item\": [{\"item\": \"3006\",\"quantity\": \"3\"}, { \"item\": \"3006\", \"quantity\": \"4\" }], \"entity\" : \"467173\", \"paymentmethod\": \"ccp\", \"cardType\" : \"5\", \"ccnumber\": \"4111111111111111\", \"ccexpiredate\" : \"12/2014\", \"ccname\": \"Alain Gendre\", \"cczipcode\" : \"760002\", \"ccstreet\" : \"Suite Avenue\" }";	
		//Object obj ="{internalid:0, phone:(000) 999-7777, recordtype:customer, companyname:drrd, ccnumber:null, ccexpiredate:null, ccname:null, cczipcode:null, ccstreet:null, item:[], firstname:dhrrdh, middlename:dfhdfh, lastname:dfhdfh, email:null, zip:225215, state:ae, country:us, altphone:, isperson:T, addr1:dhdf@gnamil.com, city:sddd}";
		Response response = client.post(obj);
		 
		 
		 //Response response = client.get();
	     String jsonResponseString= null;
		 
	     try {
        	jsonResponseString = IOUtils.readStringFromStream((InputStream)response.getEntity());

        } catch (IOException e) {
        	LOGGER.info(e);
           // throw new RuntimeException("Failed to get a string represenation of the response",e);
        }
        //System.out.println("Json Response String for create Sales Order ="+jsonResponseString);
        return jsonResponseString;
	 }
	 
	 
	 public String createCustomer(String compName, String recdType){
		// String companyName = compName;
		 //String recordType = recdType;
		 
		 String authorization = getAuthString();
		 
		//Working
		 //Object entityId = "{\"internalid\":0,\"phone\":\"(000) 888-9878\",\"recordtype\":\"customer\",\"companyname\":\"jjff@gmail.com\",\"ccnumber\":null,\"ccexpiredate\":null,\"ccname\":null,\"cczipcode\":null,\"ccstreet\":null,\"item\":[],\"firstname\":\"jjj\",\"middlename\":\"jjj\",\"lastname\":\"jjj\",\"email\":null,\"zip\":\"123456\",\"state\":\"ga\",\"country\":\"us\",\"altphone\":\"\",\"isperson\":\"T\",\"addr1\":\"jjff@gmail.com\",\"city\":\"jjff@gmail.com\"}";
		//error
		//Object entityId = "{internalid:0,phone:(000) 999-7777,recordtype:customer,companyname:drrd,ccnumber:null,ccexpiredate:null,ccname:null,cczipcode:null,ccstreet:null,item:[],firstname:dhrrdh,middlename:dfhdfh,lastname:dfhdfh,email:null,zip:225215,state:ae,country:us,altphone:,isperson:T,addr1:dhdf@gnamil.com,city:sddd}";
		 //WORKING
		// Object entityId = "{\"internalid\":\"0\",\"phone\":\"(000) 999-7777\",\"recordtype\":\"customer\",\"companyname\":\"asdsa\",\"ccnumber\":\"null\",\"ccexpiredate\":\"null\",\"ccname\":\"null\",\"cczipcode\":\"null\",\"ccstreet\":\"null\",\"item\":\"[]\",\"firstname\":\"bdd\",\"middlename\":\"dbdfb\",\"lastname\":\"dbdf\",\"email\":\"null\",\"zip\":\"123456\",\"state\":\"gu\",\"country\":\"us\",\"altphone\":\"\",\"isperson\":\"T\",\"addr1\":\"bdbfd@gmail.com\",\"city\":\"sasa\"}";
		//Json Response String for create Customer ="463566"
		 //Json Response String for create Customer ="Record already exist.Try again with other company name"
		 Object entityId = "{\"internalid\":0,\"phone\":\"(000) 666-6666\",\"recordtype\":\"customer\",\"companyname\":\"xsss\",\"ccnumber\":null,\"ccexpiredate\":null,\"ccname\":null,\"cczipcode\":null,\"ccstreet\":null,\"item\":[],\"firstname\":\"test456\",\"middlename\":\"teststs\",\"lastname\":\"test456\",\"email\":null,\"zip\":\"111111\",\"state\":\"ae\",\"country\":\"us\",\"altphone\":\"(000) 666-6666\",\"isperson\":\"T\",\"addr1\":\"teststs@gmail.com\",\"city\":\"teststs@gmail.com\"}";
		 
		WebClient client = WebClient.create("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=154&deploy=1");
		client.header("Authorization", authorization);
	    client.header("Content-Type", "application/json");
	    
	    
		Response response = client.post(entityId);
		 
		 //Response response = client.get();
	     String jsonResponseString= null;
		 
	     try {
        	jsonResponseString = IOUtils.readStringFromStream((InputStream)response.getEntity());

        } catch (IOException e) {
        	LOGGER.info(e);
           // throw new RuntimeException("Failed to get a string represenation of the response",e);
        }
       // System.out.println("Json Response String for create Customer ="+jsonResponseString);
        return jsonResponseString;
	 }
	 
	 
	 public String updateCustomer(){
		 String authorization = getAuthString();
		 //update
		 //Object entityId = "{\"entityid\": \"Sample Customer\", \"companyname\" : \"Sample Customer\",\"phone\" : \"121-456-789\"}";
		 //Create
		 
		 //JSONObject json = (JSONObject)new JSONParser().parse("{\"name\":\"MyNode\", \"width\":200, \"height\":100}");
		
		 //String test =  JSONObject.escape("{"customerId":460460,"customerName":"Customer5","recordType":"customer"}");

		 //Object ent="{"customerId":"\460460,"customerName":"Customer5","recordType":"customer"}";

		 //Working
		 Object entityId = "{\"recordtype\": \"customer\", \"internalid\": 474281, \"phone\" : \"(000) 999-9999\", \"companyname\":\"nstest66116\",\"firstname\":\"nstest66611\",\"middlename\":\"test11\",\"lastname\":\"nstest66611\",\"altphone\":\"(000) 666-6666\",\"custentityfeaturedemployee\":false,\"item\":[],}";
		 
		 //Object entityId = "{\"internalid\":467173,\"phone\":\"(000) 666-6666\",\"recordtype\":\"customer\",\"companyname\":\"dddd\",\"ccnumber\":null,\"ccexpiredate\":null,\"ccname\":null,\"cczipcode\":null,\"ccstreet\":null,\"item\":[],\"firstname\":\"tesFGFtsts\",\"middlename\":\"teststs\",\"lastname\":\"teststs\",\"email\":null,\"zip\":\"111111\",\"state\":\"ae\",\"country\":\"us\",\"altphone\":\"(000) 666-6666\",\"isperson\":\"T\",\"addr1\":\"teststs@gmail.com\",\"city\":\"teststs@gmail.com\"}";
		 //471880
		 //Object entityId = " {\"internalid\":474281,\"phone\":\"(000) 999-9999\",\"recordtype\":\"customer\",\"companyname\":\"nstest666\",\"ccnumber\":null,\"ccexpiredate\":null,\"ccname\":null,\"cczipcode\":null,\"ccstreet\":null,\"firstname\":\"nstest666\",\"middlename\":null,\"lastname\":\"nstest666\",\"email\":null,\"state\":\"gu\",\"country\":\"us\",\"altphone\":null,\"entity\":null,\"paymentmethod\":null,\"cardtype\":null,\"isperson\":\"T\",\"custentityfeaturedemployee\":false,\"item\":[]}";
		// Object entityId = "{\"internalid\":474281,\"phone\":\"(000) 999-9999\",\"recordtype\":\"customer\",\"companyname\":\"nstest666\",\"firstname\":\"nstest666\",\"middlename\":\"test\",\"lastname\":\"nstest666\",\"zip\":\"44654\",\"state\":\"gu\",\"country\":\"us\",\"altphone\":\"(000) 999-9999\",\"isperson\":\"T\",\"addr1\":\"nstest666\",\"city\":\"nstest666\"}";
		 
		WebClient client = WebClient.create("https://rest.sandbox.netsuite.com/app/site/hosting/restlet.nl?script=156&deploy=2");
		client.header("Authorization", authorization);
	    client.header("Content-Type", "application/json");
	    
	    //"Record updated successfully"
		Response response = client.post(entityId);
		 
		 //Response response = client.get();
	     String jsonResponseString= null;
		 
	     try {
        	jsonResponseString = IOUtils.readStringFromStream((InputStream)response.getEntity());

        } catch (IOException e) {
        	LOGGER.info(e);
            //throw new RuntimeException("Failed to get a string represenation of the response",e);
        }
       // System.out.println("Json Response String for create Customer ="+jsonResponseString);
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
        	LOGGER.info(e);
           // throw new RuntimeException("Failed to get a string represenation of the response",e);
        }
        //System.out.println("Json Response String for Cash Sales ="+jsonResponseString);
        return jsonResponseString;
	 }
	
	
*/}
