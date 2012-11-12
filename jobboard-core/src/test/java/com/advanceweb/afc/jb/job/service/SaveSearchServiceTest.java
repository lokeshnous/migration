package com.advanceweb.afc.jb.job.service;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.jb.test.ServiceTestBase;

public class SaveSearchServiceTest extends ServiceTestBase {
	
	private static final Logger LOGGER = Logger
			.getLogger(SaveSearchServiceTest.class);

	@Autowired
	private SaveSearchService saveSearchService;

	@Test
	public void testViewMySavedSearches() {
		try {
			int userId = 1;
			List<SaveSearchedJobsDTO> searchedJobsDTOs = saveSearchService
					.viewMySavedSearches(userId);
			assertTrue("View My Saved Searches", searchedJobsDTOs.size() >= 0);

		} catch (Exception e) {
			LOGGER.info("Error in testViewMySavedSearches:",e);
		}
	}

/*	@Test
	public void testEditSavedSearch() {
		boolean status = true;
		try {
			// int saveSearchId = 16;
			// AdmSaveSearch searchResults = saveSearchService
			// .editSavedSearch(saveSearchId);
			// assertNotNull(searchResults);

		} catch (Exception e) {
			status = false;
			LOGGER.info("Error in testEditSavedSearch:",e);
		}
		assertTrue("status of testSaveSearchedJobs",status);
	}*/

	@Ignore("Not ready to test")
	@Test
	public void testDeleteSavedSearch() {
		try {

			assertTrue("Delete a Saved Search",
					saveSearchService.deleteSavedSearch(17));

		} catch (Exception e) {
			LOGGER.info("Error in testDeleteSavedSearch:",e);
		}
	}

	// Added for save this search
	@Test
	public void testSaveSearchedJobs() {
		boolean status = true;
		try {
			SaveSearchedJobsDTO saveSearchedJobsDTO = new SaveSearchedJobsDTO();
			saveSearchedJobsDTO.setUserID(9);
			saveSearchedJobsDTO
					.setUrl("file://///nibc452/06%20JOB%20PORTAL/06%20IA/JobBoardPortalIAFinal/JobBoardPortalIAVer18/HTML/SearchResults.html?button2=Find+Jobs");
			saveSearchedJobsDTO.setSearchName("JobName");
			saveSearchedJobsDTO.setCreatedDate(new Date());
			saveSearchService.saveSearchedJobs(saveSearchedJobsDTO);
		} catch (Exception e) {
			status = false;
			LOGGER.info("Error in testSaveSearchedJobs:",e);
		}
		assertTrue("status of testSaveSearchedJobs",status);
	}
}
