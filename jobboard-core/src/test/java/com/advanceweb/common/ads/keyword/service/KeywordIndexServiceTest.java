package com.advanceweb.common.ads.keyword.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.ads.ContentTopic;
import com.advanceweb.common.ads.keyword.service.KeywordIndexService;
import com.advanceweb.jb.test.ServiceTestBase;

public class KeywordIndexServiceTest extends ServiceTestBase {

	private static final Logger LOGGER = Logger
			.getLogger(KeywordIndexServiceTest.class);

	@Autowired
	private KeywordIndexService keywordIndexService;

	private String[] testData = { "Nurse" };

	@Test
	public void findMatchesTest() {
		try {
			List<ContentTopic> result = keywordIndexService
					.findMatches(testData[0]);
			LOGGER.debug("Found " + result.size() + " Matches");
			for (ContentTopic topic : result) {
				LOGGER.debug(topic);
			}
		} catch (JobBoardServiceException ex) {
			LOGGER.error("findMatchesTest", ex);
		}

	}

	@Test
	public void findBestMatchTest() {
		String result;
		try {
			result = keywordIndexService.findBestMatch(testData[0]).toString();
			LOGGER.debug("Best match found " + result);
		} catch (JobBoardServiceException ex) {
			LOGGER.error("findBestMatchTest", ex);
		}
	}

}
