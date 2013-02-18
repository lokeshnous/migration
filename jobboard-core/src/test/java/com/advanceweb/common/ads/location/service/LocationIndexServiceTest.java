package com.advanceweb.common.ads.location.service;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.search.dao.LocationDAO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.ads.AdLocation;
import com.advanceweb.jb.test.ServiceTestBase;

public class LocationIndexServiceTest extends ServiceTestBase {

	private static final Logger LOGGER = Logger
			.getLogger(LocationIndexServiceTest.class);

	@Autowired
	private LocationDAO locationDAO;

	@Autowired
	private LocationIndexService locationService;

	// @Test
	public void findNearbyLocationTest() {
		// NORTH BATTLEFORD, SK 52.779,-108.298
		// REGINA SOUTHEAST, SK 50.4364,-104.544
		// REGINA SOUTHWEST, SK 50.4896,-104.669

		try {
			LOGGER.debug("[findNearbyLocationTest] Searching for location");
			for (AdLocation result : locationService.findNearByLocations(
					50.4364F, -104.544F, 2.00F)) {
				LOGGER.debug("findNearbyLocationTest] finding location at coordinates "
						+ result.getCity());
			}
		} catch (JobBoardServiceException ex) {
			LOGGER.error(
					"[findNearbyLocationTest]Error in findMatchingStateTest",
					ex);
		}
	}

	@Test
	public void findMatchingStateTest() {
		String city = null, state = null;
		// city = "SASKATOON NORTHWEST";
		// city = "SASKATOON NORTHWEST";
		// city = "YABUCOA";
		city = "NYACK";
		state = "NY";

		// state = "Puerto";
		// state ="Puerto Rico";
		try {
			LOGGER.debug("[findMatchingStateTest] Searching for matching city / state");
			for (AdLocation result : locationService.findMatchingLocation(
					city, state)) {
			}
		} catch (JobBoardServiceException ex) {
			LOGGER.error(
					"[findMatchingStateTest] Error in findMatchingStateTest",
					ex);
		}
	}

	@Test
	public void testMatchAndNearby() {
		String city = null, state = null;
		// city = "SASKATOON NORTHWEST";
		// city = "SASKATOON NORTHWEST";
		// city = "YABUCOA";
//		city = "Amarillo";
//		state = "Texas";
//		city = "New York";
//		state = "New York";
		city = "Irving";
		state = "Texas";
		try {
			LOGGER.debug("[testMatchAndNearby] Searching for matching city / state");
			for (AdLocation result : locationService.findMatchingLocation(
					city, state)) {
				LOGGER.debug(" Found " + result);

				// Search nearby Locations
				LOGGER.debug("Finding Location near to " + result);
				for (AdLocation nearbyLocation : locationService
						.findNearByLocations(result.getLatitude(),
								result.getLongitude(), 50F)) {
					LOGGER.debug("[testMatchAndNearby]  " + result + " is near to "
									+ nearbyLocation);
				}
			}
		} catch (JobBoardServiceException ex) {
			LOGGER.error("Error in findMatchingStateTest", ex);
		}
	}
}
