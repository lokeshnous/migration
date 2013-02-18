package com.advanceweb.afc.jb.advt.openx;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.advanceweb.afc.jb.advt.service.impl.AdServiceDelegate;
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.search.dao.LocationDAO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.ads.AdLocation;
import com.advanceweb.common.ads.AdLocationCache;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.ads.Banner;
import com.advanceweb.common.ads.ContentTopic;
import com.advanceweb.common.ads.keyword.service.KeywordIndexService;
import com.advanceweb.common.ads.location.service.LocationIndexService;
import com.advanceweb.common.client.ClientContext;
import com.advanceweb.common.template.AdvanceTemplate;

/**
 * This class implement the AdServerDelegate for usage with the openX server.
 * The OpenX server requires a set of parameters to be passed to the javascript
 * method to identify the ad to be served. The auid is specific to an ad size.
 * So a property file based mapping is used for mapping the size to the auid.
 * The Key used for retrieving the auid is of the form <b>auid.WidthxHeight</b>.
 * For example the key to retrieve the auid for an ad of size 320 x 200 is
 * <b>auid.320x200</b> The ad server url also is retrieved from the property
 * file. The actual ad-tag is created using a template. Parameters to the
 * template are sent as a map. The template engine has the following inputs
 * available for merging.
 * <ul>
 * <li>auid - The ad unit id</li>
 * <li>url - The base url for the ad server</li>
 * <li>size - The AdSize object representing the size of the ad</li>
 * </ul>
 * 
 * The class used two template adTagTemplate is used if an auid is retrieved
 * from the property file. If there is no auid corresponding to the size
 * provided, a default ad is displayed. The default ad is created by the
 * defaultAdTemplate template.
 * 
 * @author sukeshnambiar
 * 
 */
@Component
public class OpenxAdServiceDelegate implements AdServiceDelegate {

	private static final Logger LOGGER = Logger
			.getLogger(OpenxAdServiceDelegate.class);

	/**
	 * The radius vector is used to work around the problem of lucene not
	 * setting the score of the spatial search result in the order of distance.
	 * To circumvent the problem, the spatial search is performed for various
	 * distances till we get sufficient number of locations close to the given
	 * location.
	 */
	private static final Float[] RADIUS_VECTOR = { 0F, 5F, 10F, 20F, 40F, 80F,
			100.0F };
	private static final int LOCATION_PARAM_COUNT = 5;

	private static final int LOCATION_CACHE_SIZE = 100;
	
	private static final int TID_PARAM_COUNT = 10;

	@Resource(name = "openxConfiguration")
	@Autowired
	private Properties openxProperties;

	@Autowired
	private AdvanceTemplate jsAdTemplate;

	@Autowired
	private AdvanceTemplate iframeAdTemplate;

	@Autowired
	private AdvanceTemplate imageAdTemplate;

	@Autowired
	private AdvanceTemplate defaultAdTemplate;

	@Autowired
	private KeywordIndexService keywordIndexService;

	@Autowired
	private LocationIndexService locationIndexService;

	@Autowired
	private LocationDAO locationDao;

	private AdLocationCache locationCache = new AdLocationCache(100);

	private SecureRandom random = new SecureRandom();

	/**
	 * This method retrieves the ad for the parameters given by the user.
	 * <p>
	 * Implementation
	 * </p>
	 * This method uses a property file to retrieve the ad size to the
	 * appropriate auid to be consumed by the openx server. A template engine
	 * will use the auid and the other information to form an ad-tag to be used
	 * in the web page. If no auid is received this method returns a default ad
	 * for the given size.
	 */
	@Override
	public Banner getBanner(ClientContext context, AdSize size,
			AdPosition position) {

		Banner banner = new Banner();
		banner.setSize(new AdSize(size));

		LOGGER.debug(("Received request from context " + context.toString()
				+ " for banner size " + size.toString()));

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("url", getOpenxUrl(context));
		params.put("size", size);
		params.put("id", Long.toHexString(getRandomId()));
		params.put("random", Long.toHexString(getRandom()));

		Map<String, Object> vars = new HashMap<String, Object>();

		// Keyword variables
		List<ContentTopic> topicList = getKeyword(context);
		StringBuffer tidBuffer = new StringBuffer();
		StringBuffer keywordBuffer = new StringBuffer();
		int counter = 0;
		if (null != topicList && !topicList.isEmpty()) {
			for (ContentTopic topic : topicList) {
				tidBuffer.append(topic.getId()).append(
						MMJBCommonConstants.COMMA);
				keywordBuffer.append(topic.getText()).append(
						MMJBCommonConstants.COMMA);
				counter++;
				if (counter == TID_PARAM_COUNT)
					break;
			}

			tidBuffer.deleteCharAt(tidBuffer.length() - 1);
			keywordBuffer.deleteCharAt(keywordBuffer.length() - 1);
			params.put("tid", tidBuffer);
			// vars.put("keyword", keywordBuffer);
		}
		
//		if (topic != null) {
//			params.put("tid", topic.getId());
//			vars.put("keyword", topic.getText());
//		}

		// Location variables
		String location = getLocation(context);
		if (!StringUtils.isEmpty(location)) {
			vars.put("location", location);
		}

		// Add vars if any variable is set
		if (!vars.isEmpty()) {
			params.put("vars", vars);
		}

		// Ad unit id
		String auid = openxProperties.getProperty(getAuidKey(size));
		if (auid == null || auid.isEmpty()) {
			// Set the default banner
			banner.setTag(defaultAdTemplate.process(params));
		} else {
			params.put("auid", auid);
			banner.setTag(iframeAdTemplate.process(params));
		}
		LOGGER.debug("Received ad tag " + banner.getTag());

		// banner.setTag("<div><a href=\"http://ox-d.advanceweb.com/w/1.0/rc?cs=50bf58b99304b&cb=INSERT_RANDOM_NUMBER_HERE&c.keyword=Nursing\"><img src=\"http://ox-d.advanceweb.com/w/1.0/ai?auid=284880&cs=50bf58b99304b&cb=INSERT_RANDOM_NUMBER_HERE&c.keyword=Nursing\" border=\"0\" alt=\"\"></a></div>");

		return banner;
	}

	/**
	 * OpenX ads require the ad server url to be replaced with https on secure
	 * pages. This method uses the CLIENT_REQUEST_URL to replace the protocol
	 * http with https
	 * 
	 * @param context
	 *            The client context
	 * @return The openx ad server url, wth https prefix wherever required.
	 */
	private String getOpenxUrl(ClientContext context) {
		String url = openxProperties.getProperty("openx.url");

		String clientUrl = context
				.getProperty(ClientContext.CLIENT_REQUEST_URL);
		if (clientUrl != null && clientUrl.startsWith("https")) {
			url = url.replace("^\\s*http:", "https:");
			LOGGER.debug("Openx URL modified to https");
		}
		return url;
	}

	/**
	 * Genreate the adunit id key of the form <b>auid.WidthxHeight</b>
	 * 
	 * 
	 * now.
	 * 
	 * @param size
	 *            The Size of the ad requested
	 * @return The property key to access the auid for the given ad.
	 */
	private String getAuidKey(AdSize size) {
		return String.format("openx.auid.%sx%s", size.getWidth(),
				size.getHeight());
	}

	/**
	 * Select the keyword from the taxonomy that has the best match to the
	 * context string. The matching is done in the following order
	 * <ol>
	 * <li>Current search by the user</li>
	 * <li>Previous search by the user</li>
	 * <li>The profession of the logged in user</li>
	 * <li>The role of the logged in user</li>
	 * </ol>
	 * 
	 * @param context
	 *            The context passed from the client. This is used to determine
	 *            the keywords to match
	 * @return The keyword in the taxonomy that has the best match to the
	 *         context keyword
	 */
	private List<ContentTopic> getKeyword(ClientContext context) {
//		ContentTopic topic = null;
		List<ContentTopic> topicList = null;
		try {
			// Try the following searches in sequence to find a match
			String[] searchStrings = {
					context.getProperty(ClientContext.USER_CURRENT_SEARCH),
					context.getProperty(ClientContext.USER_PREVIOUS_SEARCH),
					context.getProperty(ClientContext.USER_PROFESSION),
					context.getProperty(ClientContext.USER_ROLE) };

			for (String str : searchStrings) {
				if (!StringUtils.isEmpty(str)) {
					LOGGER.debug("Searching for Ad keyword matching " + str);
//					topic = keywordIndexService.findBestMatch(str);
					topicList = keywordIndexService.findMatches(str);
					if (null != topicList && !topicList.isEmpty()) {
						LOGGER.debug("Found Ad keywords " + topicList);
						break;
					}
				}
			}
		} catch (JobBoardServiceException ex) {
			// Just log the error and proceed. The keyword will be set to
			// default
			LOGGER.error("Error while resolving keywords for Openx Ad", ex);
		}

		return topicList;

	}

	// TODO Refactor once the logic is finalized
	private String getLocation(ClientContext context) {

		String[] locationString = {
				context.getProperty(ClientContext.USER_SELECTED_LOCATION),
				context.getProperty(ClientContext.USER_LOCATION),
				context.getProperty(ClientContext.CLIENT_LOCATION) };

		String location = null;

		for (String locationName : locationString) {
			if (locationName == null) {
				continue;
			}

			// Initialize location to empty string
			location = locationCache.get(locationName);

			if (location != null) {
				LOGGER.debug("Location key found in cache for " + locationName);
				return location;
			} else {
				location = "";
			}

			// Resolve city and state names
			String[] cityState = locationName.split(MMJBCommonConstants.COMMA);

			// The location is expected to be of the form City,state. If no
			// comma is present, it is assumed to be a state.
			boolean hasCity = cityState.length > 1;

			if (hasCity) {

				try {
					// Find the JB location
					LocationDTO dto = getClientLocation(cityState[0].trim(),
							cityState[1].trim());
					if (dto != null) {

						// Add the location keyword
						for (AdLocation adLocation : getAdLocations(
								dto.getLatitude(), dto.getLongitude())) {
							location = location + adLocation.getLabel() + MMJBCommonConstants.COMMA;
						}

						// Add the state keyword as a second parameter
						String state = getFullStateName(cityState[1]);
						location = location + state;
						locationCache.put(locationName, location);
						break;
					}
				} catch (JobBoardServiceException ex) {
					// Silently catch the exception and continue with the next
					// locationName
					LOGGER.warn("Exception in finding Ad Location", ex);
				}

			} else {
				// Add the state full name as the location parameter
				String state = getFullStateName(cityState[0]);
				location = state;
				locationCache.put(locationName, location);
				break;
			}

		}
		return location;
	}

	private String getFullStateName(String shortStateName) {
		String state = locationDao.getStateFullName(shortStateName.trim());
		if (state == null) {
			state = shortStateName.trim();
		}
		return state;
	}

	/**
	 * Returns the advertisement location for a given location. The
	 * advertisement location is a the nearest of the cities close to RADIUS
	 * miles from the location passed to the function
	 * 
	 * @param city
	 *            The reference city
	 * @param state
	 *            The reference state
	 * @return The nearest ad location within the RADIUS
	 * @throws JobBoardServiceException
	 */
	private List<AdLocation> getAdLocations(Float latitude, Float longitude)
			throws JobBoardServiceException {

		Set<AdLocation> resultSet = new LinkedHashSet<AdLocation>();

		for (float distance : RADIUS_VECTOR) {
			// Find a closest ad location for the given location
			List<AdLocation> adLocationList = locationIndexService
					.findNearByLocations(latitude, longitude, distance);

			// Pickup the first LOCATION_PARAM_COUNT locations
			for (AdLocation adLocation : adLocationList) {
				if (adLocation != null) {
					resultSet.add(adLocation);

					// Exit the inner loop if we have enough locations
					if (resultSet.size() >= LOCATION_PARAM_COUNT) {
						break;
					}
				}
			}

			// Exit the outer loop if we have enough locations
			if (resultSet.size() >= LOCATION_PARAM_COUNT) {
				break;
			}
		}

		List<AdLocation> result = new ArrayList<AdLocation>();
		result.addAll(resultSet);
		return result;
	}

	private LocationDTO getClientLocation(String city, String state)
			throws JobBoardServiceException {
		// Find the coordinates
		List<LocationDTO> locationList = null;
		try {
			locationList = locationDao.getLocationByCityState(city, state);
		} catch (JobBoardDataException ex) {
			LOGGER.debug("Error retrieving location from city and state value",
					ex);
		}
		if (locationList == null || locationList.isEmpty()) {
			return null;
		} else {
			return locationList.get(0);
		}
	}

	/**
	 * The OpenX ad Tags add a 12 char id for iframes. This looks like a random
	 * number. This method generate a random long number with 12 hexadecimal
	 * digits.
	 * 
	 * @return A long integer with 12 hexadecimal digits
	 */
	private long getRandomId() {
		return random.nextLong() & 0xffffffffffffL;
	}

	/**
	 * Generate a long random number
	 * 
	 * @return random long number
	 */
	private long getRandom() {
		return random.nextLong();
	}

}
