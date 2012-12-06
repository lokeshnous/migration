package com.advanceweb.afc.jb.advt.openx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.advanceweb.afc.jb.advt.service.impl.AdServiceDelegate;
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.ads.Banner;
import com.advanceweb.common.client.ClientContext;
import com.advanceweb.common.index.service.KeywordIndexService;
import com.advanceweb.common.index.service.LocationIndexService;
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
		params.put("url", openxProperties.getProperty("openx.url"));
		params.put("size", size);

		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("keyword", getKeyword(context));		
		vars.putAll(getLocationParameters(context));
		params.put("vars", vars);

		String auid = openxProperties.getProperty(getAuidKey(size));
		if (auid == null || auid.isEmpty()) {
			// Set the default banner
			banner.setTag(defaultAdTemplate.process(params));
		} else {
			params.put("auid", auid);
			banner.setTag(imageAdTemplate.process(params));
		}
		LOGGER.debug("Received ad tag " + banner.getTag());

		//banner.setTag("<div><a href=\"http://ox-d.advanceweb.com/w/1.0/rc?cs=50bf58b99304b&cb=INSERT_RANDOM_NUMBER_HERE&c.keyword=Nursing\"><img src=\"http://ox-d.advanceweb.com/w/1.0/ai?auid=284880&cs=50bf58b99304b&cb=INSERT_RANDOM_NUMBER_HERE&c.keyword=Nursing\" border=\"0\" alt=\"\"></a></div>");

		
		return banner;
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
	private String getKeyword(ClientContext context) {
		String keyword = null;
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
					keyword = keywordIndexService.findBestMatch(str);
					if (!StringUtils.isEmpty(keyword)) {
						LOGGER.debug("Found Ad keyword " + keyword);
						break;
					}
				}
			}
		} catch (JobBoardServiceException ex) {
			// Just log the error and proceed. The keyword will be set to
			// default
			LOGGER.error("Error while resolving keyword for Openx Ad", ex);
		}

		if (StringUtils.isEmpty(keyword)) {
			keyword = "Nursing";
			LOGGER.debug("Ad keyword set to default value " + keyword);
		}

		return keyword;

	}

	private Map<String, String> getLocationParameters(ClientContext context) {
		Map<String, String> params = new HashMap<String, String>();

		String[] locationString = {
				context.getProperty(ClientContext.USER_SELECTED_LOCATION),
				context.getProperty(ClientContext.USER_LOCATION),
				context.getProperty(ClientContext.CLIENT_LOCATION) };

		for (String locationName : locationString) {			
			if(locationName == null) {
				continue;
			}

			// Resolve city and state names
			String[] cityState = locationName.split(",");
			boolean hasCity = cityState.length > 1;

			String city, state;
			if (hasCity) {
				city = cityState[0];
				state = cityState[1];
			} else {
				city = null;
				state = cityState[0];
			}

			// The location is expected to be of the form City,state. If no
			// comma is present, it is assumed to be a state.
			try {
				List<LocationDTO> locationList = locationIndexService
						.findMatchingLocation(city, state);
				if (!locationList.isEmpty()) {
					LocationDTO location = locationList.get(0);
					if (hasCity) {
						params.put("city", location.getCityAlias());
						params.put("state", location.getStateFullName());
					} else {
						params.put("state", location.getStateFullName());
					}
					LOGGER.debug("Created location specific tags for " + locationName);
					break;
				}
			} catch (JobBoardServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return params;
	}
}
