package com.advanceweb.afc.jb.webservice;

/*import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;*/

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

import com.advanceweb.afc.jb.ServiceTest;

public class CustomerDetailsWSTest extends ServiceTest{

	//RestTemplate template = new RestTemplate();
	
	public static void main(String[] args){

        CustomerDetailsWSTest customerDetailsWSTest = new CustomerDetailsWSTest();

        customerDetailsWSTest.getCustomerDetails();

	}


	
	public void getCustomerDetails(){
		/*String uri = "https://rest.netsuite.com/app/site/hosting/restlet.nl?script=65&deploy=1";

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
		*/
		
		WebClient client = WebClient.create("https://rest.netsuite.com/app/site/hosting/restlet.nl?script=65&deploy=1");
		client.replaceHeader("Authorization","NLAuth nlauth_account=TSTDRV617993, nlauth_email=dharmarajns@nous.soft.net, nlauth_signature=dharma123, nlauth_role=3");
        Response r = client.accept("application/json").type("application/json").get();

        String rOut= null;

        try {

                rOut = IOUtils.readStringFromStream((InputStream)r.getEntity());

                

        } catch (IOException e) {

                e.printStackTrace();

                throw new RuntimeException("Failed to get a string represenation of the response",e);

        }

        System.out.println("rOut"+rOut);

        


	}
}
