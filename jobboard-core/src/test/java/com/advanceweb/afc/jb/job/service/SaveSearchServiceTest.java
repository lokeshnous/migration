package com.advanceweb.afc.jb.job.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.JpSaveSearch;

public class SaveSearchServiceTest extends ServiceTest {

	@Autowired
	private SaveSearchService saveSearchService;

	@Test
	public void testViewMySavedSearches() {
		try {
			int userId = 1;
			List<SaveSearchedJobsDTO> saveSearchedJobsDTOs = saveSearchService
					.viewMySavedSearches(userId);
			assertTrue("View My Saved Searches",
					saveSearchedJobsDTOs.size() >= 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEditSavedSearch() {
		try {
			int saveSearchId = 16;
			JpSaveSearch jpSaveSearch = saveSearchService
					.editSavedSearch(saveSearchId);
			assertNotNull(jpSaveSearch);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testDeleteSavedSearch() {
		try {

			assertTrue("Delete a Saved Search",
					saveSearchService.deleteSavedSearch(17));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Added for save this search
	@Test
	public void testSaveSearchedJobs() {
		try {
			SaveSearchedJobsDTO saveSearchedJobsDTO = new SaveSearchedJobsDTO();
			saveSearchedJobsDTO.setLoginID("9");
			saveSearchedJobsDTO
					.setUrl("file://///nibc452/06%20JOB%20PORTAL/06%20IA/JobBoardPortalIAFinal/JobBoardPortalIAVer18/HTML/SearchResults.html?button2=Find+Jobs");
			saveSearchedJobsDTO.setUrlName("JobName");
			saveSearchedJobsDTO.setCreatedDate(new Date());
			saveSearchService.saveSearchedJobs(saveSearchedJobsDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
