package com.advanceweb.afc.jb.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.apache.log4j.Logger;


import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.common.AccountProfileDTO;



public class OpenAMEUtility {

	private static final Logger LOGGER = Logger.getLogger("OpenAMUtility.class");

	private final static String _TOKEN_URL = "http://wisw0013.nousinfo.com:8080/openam/identity/authenticate?username=amadmin&password=nous@123";
	private final static String _CREATE_URL = "http://wisw0013.nousinfo.com:8080/openam/identity/create?admin=";
	private final static String _UPDATE_URL = "http://wisw0013.nousinfo.com:8080/openam/identity/update?admin=";
	private final static String _DELETE_URL = "http://wisw0013.nousinfo.com:8080/openam/identity/delete?admin=";
	private final static String _READ_URL = "http://wisw0013.nousinfo.com:8080/openam/identity/read?admin=";
	private final static String _AUTHENTICATE_URL = "http://wisw0013.nousinfo.com:8080/openam/identity/authenticate?";
	private final static String _LOGOUT_URL = "http://wisw0013.nousinfo.com:8080/openam/identity/logout?subjectid=";

	/**
	 * To get a token id from OpenAM instance
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getToken() {

		URLConnection uc = null;
		URL url = null;
		String inputLine = "";
		String token = "";

		try {

			url = new URL(_TOKEN_URL);
			uc = url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));

			while ((inputLine = in.readLine()) != null) {
				token = inputLine;
			}

			in.close();
			token = token.replace("token.id=", "");

		} catch (MalformedURLException e) {
			LOGGER.error("Exception : While URL reading - " + e);
		} catch (IOException e) {
			LOGGER.error("Exception : While URL buffering - " + e);
		}

		return token;
	}

	/**
	 * To open an URL connection to connect to  OpenAM
	 * 
	 * @param openurl
	 * @throws Exception
	 */

	public static boolean openUrl(String openurl) {

		boolean isAuthenticated = false;
		try {
			
			openurl = openurl.replaceAll(" ", "%20");
			openurl = openurl.replaceAll("#", "%23");
			openurl = openurl.replaceAll(",", "%2C");
			
			URL url = new URL(openurl);
			URLConnection uc = url.openConnection();
			uc.getInputStream();
			isAuthenticated = true;
			
		} catch (MalformedURLException e) {
			LOGGER.error("Exception : While URL reading - " + e);			
		} catch (IOException e) {
			LOGGER.error("Exception : While Opening Connection - " + e);
		}
		return isAuthenticated;
	}

	/**
	 * Creating the users in OpenAM
	 * 
	 * @param attributes
	 * @throws Exception
	 */

	public static boolean createUser(String attributes) {
		return openUrl(_CREATE_URL + getToken() + attributes);
		
	}

	/**
	 * update the users in OpenAM
	 * 
	 * @param attributes
	 * @throws Exception
	 */
	public static boolean UpdateUser(String attributes) {
		return openUrl(_UPDATE_URL + getToken() + attributes);
		
	}
	
	/**
	 * update the users in OpenAM
	 * 
	 * @param attributes
	 * @throws Exception
	 */
	public static boolean DeleteUser(String attributes) {
		return openUrl(_DELETE_URL + getToken() + attributes);
		
	}



	/**
	 * This Method is used to Read the user from OpenAM for validation 
	 * 
	 * @param attributes
	 * @throws Exception
	 */
	public static boolean readUser(String attributes) {
		return openUrl(_READ_URL + getToken() + attributes);
		
	}

	/**
	 * This Method is used to authenticate the User In OpenAM
	 * 
	 * @param attributes
	 * @return 
	 * @throws Exception
	 */
	public static boolean authenticateUser(String attributes) {
		return openUrl(_AUTHENTICATE_URL + getToken() + attributes);
	}

	/**
	 * This Method is used to logout the user from OpenAM
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	public static void logoutUser(String username, String password) {

		String inputLine = "";
		String subjectid = "";
		
		StringBuffer  logouttoken= new StringBuffer();		
		logouttoken.append(_AUTHENTICATE_URL);
		logouttoken.append("username=" + username);
		logouttoken.append("&password=" + password);
		
		try {
			
			URL url = new URL(logouttoken.toString());
			URLConnection uc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));

			while ((inputLine = in.readLine()) != null) {
				subjectid = inputLine;
			}

			in.close();
			subjectid = subjectid.replace("token.id=", "");
			openUrl(_LOGOUT_URL + subjectid);
			LOGGER.info("User Logged out successfully");

		} catch (MalformedURLException e) {
			LOGGER.error("Exception - LOGOUT-USER : While URL reading - " + e);
		} catch (IOException e) {
			LOGGER.error("Exception - LOGOUT-USER : While URL buffering - " + e);
		}
	}
	
	
	/**
	 * This Method is used to create new user in OpenAM
	 * 
	 * @param userDTO
	 *            : User information
	 * @param hashmap
	 *            : User first name
	 * @return
	 */

	public static boolean openAMCreateUser(UserDTO userDTO,
			HashMap<String, String> hashmap) {

		String telephone = (String) hashmap.get("Phone Number");
		String address = (String) hashmap.get("Street Address");
		StringBuffer attributes = new StringBuffer();

		attributes.append("&identity_name=" + userDTO.getEmailId());
		attributes.append("&identity_attribute_names=givenname&identity_attribute_values_givenname="+ userDTO.getFirstName());
		attributes.append("&identity_attribute_names=sn&identity_attribute_values_sn="+ userDTO.getLastName());
		attributes.append("&identity_attribute_names=cn&identity_attribute_values_cn="+ userDTO.getFirstName()+" " + userDTO.getLastName());
		attributes.append("&identity_attribute_names=userpassword&identity_attribute_values_userpassword="+ userDTO.getPassword());
		attributes.append("&identity_attribute_names=mail&identity_attribute_values_mail="+ userDTO.getEmailId());
		attributes.append("&identity_attribute_names=postaladdress&identity_attribute_values_postaladdress="+ address);
		attributes.append("&identity_attribute_names=telephonenumber&identity_attribute_values_telephonenumber="+ telephone);
		attributes.append("&identity_realm=%2F&identity_type=user");

		return OpenAMEUtility.createUser(attributes.toString());
	}

	/**
	 * This Method is used to Update the  Users in OpenAM
	 * 
	 * @param userDTO
	 * @param hm
	 * @return
	 */

	public static boolean openAMUpdateUser(UserDTO userDTO,
			HashMap<String, String> hashMap) {

		String firstname = (String) hashMap.get("First Name");
		String lastname = (String) hashMap.get("Last Name");
		String telephone = (String) hashMap.get("Phone Number");
		String address = (String) hashMap.get("Street Address");

		StringBuffer attributes = new StringBuffer();

		attributes.append("&identity_name=" + userDTO.getEmailId());
		attributes.append("&identity_attribute_names=givenname&identity_attribute_values_givenname="+ firstname);
		attributes.append("&identity_attribute_names=sn&identity_attribute_values_sn="+ lastname);
		attributes.append("&identity_attribute_names=cn&identity_attribute_values_cn="+ firstname+" "+ lastname);
		attributes.append("&identity_attribute_names=postaladdress&identity_attribute_values_postaladdress="+ address);
		attributes.append("&identity_attribute_names=telephonenumber&identity_attribute_values_telephonenumber="+ telephone);
		attributes.append("&identity_realm=%2F&identity_type=user");
		return OpenAMEUtility.UpdateUser(attributes.toString());

	}
	
	/**
	 * This method is used to Updating Password for OpenAM User
	 * 
	 * @param form
	 * @return
	 */

	public static boolean openAMUpdatePassword(String email,String password) {

		StringBuffer updateattributes = new StringBuffer();
		updateattributes.append("&identity_name="+ email);
		updateattributes.append("&identity_attribute_names=userpassword&identity_attribute_values_userpassword="+ password);
		updateattributes.append("&identity_realm=%2F&identity_type=user");

		return OpenAMEUtility.UpdateUser(updateattributes.toString());

	}
	/**
	 * This method is used to delete for OpenAM User
	 * 
	 * @param form
	 * @return
	 */

	public static boolean openAMDeleteUser(String email) {

		StringBuffer updateattributes = new StringBuffer();
		updateattributes.append("&identity_name="+ email);
		updateattributes.append("&identity_realm=%2F&identity_type=user");

		return OpenAMEUtility.DeleteUser(updateattributes.toString());

	}




	/**
	 * 
	 * ThisMethod is used for OpenAM Email Validation
	 * 
	 *   
	 * @author Santhosh Gampa 
	 * 
	 */

	public static boolean openAMValidateEmail(String emailid) {

		StringBuffer attributes = new StringBuffer();
		attributes.append("&name="+emailid);
		attributes.append("&attributes_names_realm&attributes_values_realm=%2F");
		
    	return OpenAMEUtility.readUser(attributes.toString());
    	
	}



	/**
	 * 
	 * Method is used to authenticate with respect to open AM. It return true if
	 * authentication success otherwise false.
	 * 
	 * @author Santhosh Gampa 
	 * 
	 */

	public static boolean getOpenAMAuthentication(String username,String password) {

		boolean isAuthenticated = false;

		StringBuffer authenticateURL = new StringBuffer();
		authenticateURL.append("&username=" + username);
		authenticateURL.append("&password=" + password);

		isAuthenticated = OpenAMEUtility.authenticateUser(authenticateURL
				.toString());

		return isAuthenticated;
	}

	
	public static boolean openAMCreateEmp(MerUser meruserDTO,AdmFacilityContact contact) {
		LOGGER.info("-----------------------------------");
		LOGGER.info(meruserDTO.getEmail()+ meruserDTO.getPassword()+ meruserDTO.getFirstName()+" " + meruserDTO.getLastName()+ meruserDTO.getEmail());

		StringBuffer attributes = new StringBuffer();

		attributes.append("&identity_name=" + meruserDTO.getEmail());
		attributes.append("&identity_attribute_names=givenname&identity_attribute_values_givenname="+ meruserDTO.getFirstName());
		attributes.append("&identity_attribute_names=sn&identity_attribute_values_sn="+ meruserDTO.getLastName());
		attributes.append("&identity_attribute_names=cn&identity_attribute_values_cn="+ meruserDTO.getFirstName()+" " + meruserDTO.getLastName());
		attributes.append("&identity_attribute_names=userpassword&identity_attribute_values_userpassword="+ meruserDTO.getPassword());
		attributes.append("&identity_attribute_names=mail&identity_attribute_values_mail="+ meruserDTO.getEmail());
		attributes.append("&identity_attribute_names=postaladdress&identity_attribute_values_postaladdress="+contact.getStreet()+" "+contact.getCity()+" "+contact.getState()+" "+contact.getCountry() );
		attributes.append("&identity_attribute_names=telephonenumber&identity_attribute_values_telephonenumber="+ contact.getPhone());
		attributes.append("&identity_realm=%2F&identity_type=user");

		return OpenAMEUtility.createUser(attributes.toString());
	}


	public static boolean openAMUpdateEmp(AccountProfileDTO apd) {

		
		StringBuffer attributes = new StringBuffer();

		attributes.append("&identity_name=" + apd.getEmail());
		attributes.append("&identity_attribute_names=givenname&identity_attribute_values_givenname="+apd.getFirstName());
		attributes.append("&identity_attribute_names=sn&identity_attribute_values_sn="+ apd.getLastName());
		attributes.append("&identity_attribute_names=cn&identity_attribute_values_cn="+ apd.getFirstName()+" "+ apd.getLastName());
		attributes.append("&identity_attribute_names=postaladdress&identity_attribute_values_postaladdress="+ apd.getStreet()+" "+apd.getCity()+" "+apd.getState()+" "+apd.getCountry());
		attributes.append("&identity_attribute_names=telephonenumber&identity_attribute_values_telephonenumber="+apd.getPhone());
		attributes.append("&identity_realm=%2F&identity_type=user");
		return OpenAMEUtility.UpdateUser(attributes.toString());

	}


}
