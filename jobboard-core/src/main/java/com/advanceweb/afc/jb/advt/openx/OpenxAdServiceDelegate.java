package com.advanceweb.afc.jb.advt.openx;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.advanceweb.afc.jb.advt.service.impl.AdServiceDelegate;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.ads.Banner;
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

	@Resource(name = "openxConfiguration")
	@Autowired
	Properties openxProperties;

	@Autowired
	private AdvanceTemplate adTagTemplate;

	@Autowired
	private AdvanceTemplate defaultAdTemplate;

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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Banner getBanner(ClientContext context, AdSize size,
			AdPosition position) {
		Banner banner = new Banner();
		if (size != null) {
			banner.setSize(new AdSize(size));
		}

		LOGGER.debug(("Received request from context " + context.toString()
				+ " for banner size " + size.toString()));

		Map params = new HashMap();
		params.put("url", openxProperties.getProperty("openx.url"));
		params.put("size", size);

		String auid = openxProperties.getProperty(getAuidKey(context, size));
		if (auid == null || auid.isEmpty()) {
			// Set the default banner
			banner.setTag(defaultAdTemplate.process(params));
		} else {
			params.put("auid", auid);
			banner.setTag(adTagTemplate.process(params));
		}
		LOGGER.debug("Received ad tag " + banner.getTag());

		return banner;
	}

	/**
	 * Genreate the adunit id key of the form <b>auid.WidthxHeight</b>
	 * 
	 * @param context
	 *            The context passed from the client. This parameter is not used
	 *            now.
	 * @param size
	 *            The Size of the ad requested
	 * @return The property key to access the auid for the given ad.
	 */
	private String getAuidKey(ClientContext context, AdSize size) {
		return String.format("openx.auid.%sx%s", size.getWidth(),
				size.getHeight());
	}
}
