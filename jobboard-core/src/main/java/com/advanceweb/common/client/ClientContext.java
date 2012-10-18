package com.advanceweb.common.client;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ClientContext provides the ability to specify the context information of the
 * client accessing the Merion application. The ClientContext has various
 * properties to define the context of the client. Constants are defined for
 * specifying a standard set of default property keys. The default property keys
 * are mainly of two types.
 * <ol>
 * <li>Property pertaining to the client machine.</li>
 * <li>Property related to the logged in user.</li>
 * </ol>
 * Depending on the type of application that creates the client some, all, or
 * one of the properties may be set. Application are free to add their own
 * properties to the context. However other applications may not be able to
 * recognize these properties.
 * 
 * @author sukeshnambiar
 * 
 */
public class ClientContext {

	/**
	 * The user agent setting for the application that is accessing the advance
	 * application. This is automatically set by the browser.
	 */
	public static final String CLIENT_USER_AGENT = "client.useragent";

	/**
	 * Hostname of the client from which the advance application is accessed.
	 */
	public static final String CLIENT_HOSTNAME = "client.hostname";

	/**
	 * The IP address of the client. Will help to identify the location
	 */
	public static final String CLIENT_IP = "client.ip";

	/**
	 * An application identifier. this would typically be one of the Merion
	 * applications
	 */
	public static final String CLIENT_APPLICATION = "client.application";

	/**
	 * The request URL. (The URL used to reach the page from where the context
	 * is passed)
	 */
	public static final String CLIENT_REQUEST_URL = "client.request.url";

	/**
	 * The referral url. (The url from where the page is reached)
	 */
	public static final String CLIENT_REFERRER = "cleint.referrer";

	/**
	 * The geographic location of the client. This can be expressed in various
	 * ways like 1. As co-ordinates latitude, longitude 2. As well known city
	 * names, like KOP, PA, etc
	 */
	public static final String CLIENT_LOCATION = "client.location";

	/**
	 * A page identifier from where the call is made. This is application
	 * specific.
	 */
	public static final String CLIENT_PAGE = "client.page";

	/**
	 * The session id for the client session. This is used by various
	 * applications to track the user activities. This is defined as a client
	 * level parameter, as the user may not have logged in
	 */
	public static final String CLIENT_SESSIONID = "client.sessionid";

	/**
	 * The current search performed by the user. This is useful to place the
	 * context for targeted contents and advertisements.
	 */
	public static final String USER_CURRENT_SEARCH = "user.currentsearch";

	/**
	 * The previous search performed by the client. This also is used for place
	 * the context properly. The previous search need not be exactly the
	 * previous search, an application can browse the history of the search and
	 * select an appropriate phrase
	 */
	public static final String USER_PREVIOUS_SEARCH = "user.previoussearch";

	/**
	 * The location of the user. This is mostly the location of the user
	 * provided in the profile. This could also be an application specific input
	 * like Preferred Location received from the user
	 */
	public static final String USER_LOCATION = "user.location";

	/**
	 * The procession of the user. This should be one of the selection made from
	 * the organizational taxonomy
	 */
	public static final String USER_PROFESSION = "user.profession";

	/**
	 * The gender of the user. This is useful in providing targeted contents.
	 */
	public static final String USER_GENDER = "user.gender";

	/**
	 * The role of the user within the application. Used for providing any
	 * targeted ads and contents
	 */
	public static final String USER_ROLE = "user.role";

	/**
	 * The id of the user within the application
	 */
	public static final String USER_ID = "user.id";

	private Map<String, String> params;

	/**
	 * Construct a Context from a map. All the contents in the map is copied to
	 * the parameters
	 * 
	 * @param parms
	 */
	public ClientContext(Map<String, String> params) {
		this();
		this.params.putAll(params);
	}

	/**
	 * Default constructor for ClientContext. initializes the map
	 */
	public ClientContext() {
		params = new LinkedHashMap<String, String>();
	}

	/**
	 * Copy constructor, avoid the trouble of using more code to duplicate the
	 * context information.
	 */
	public ClientContext(ClientContext source){
		this();
		params.putAll(source.params);
	}

	/**
	 * Retrieves the property from the context.
	 * 
	 * @param key
	 *            The key to identify the property
	 * @return The property value corresponding to the key
	 */
	public String getProperty(String key) {
		return params.get(key);
	}

	/**
	 * Set the property for the Client Context
	 * 
	 * @param key
	 *            the property key
	 * @param value
	 *            the property value
	 */
	public void setProperty(String key, String value) {
		params.put(key, value);
	}
}
