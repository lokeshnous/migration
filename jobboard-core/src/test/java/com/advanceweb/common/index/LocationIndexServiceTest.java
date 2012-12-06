package com.advanceweb.common.index;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.search.dao.LocationDAO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.index.service.LocationIndexService;
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
			System.out.println("Searching for location");
			for (LocationDTO result : locationService.findLocation(50.4364,
					-104.544, 2.00)) {
				System.out.println(result.getCity());
			}
		} catch (JobBoardServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void findMatchingStateTest() {
		String city = null, state = null;
		//  city = "SASKATOON NORTHWEST";
		//  city = "SASKATOON NORTHWEST";
//		city = "YABUCOA";
		city = "NYACK";
		state="NY";

//		 state = "Puerto";
		//  state ="Puerto Rico";
		try {
			System.out.println("Searching for matching city / state");
			for (LocationDTO result : locationService.findMatchingLocation(
					city, state)) {
				System.out.println(result.getCity() + "\t" + result.getState());
			}
		} catch (JobBoardServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
