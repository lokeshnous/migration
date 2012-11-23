package com.advanceweb.common.index;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.index.service.KeywordIndexService;
import com.advanceweb.jb.test.ServiceTestBase;

public class KeywordIndexServiceTest extends ServiceTestBase {

	private static final Logger LOGGER = Logger
			.getLogger(KeywordIndexServiceTest.class);

	@Autowired
	private KeywordIndexService keywordIndexService;

	private String[] testData = {""};

	@Test
	public void findMatchesTest() {
		try {
			List<String> result = keywordIndexService.findMatches(testData[0]);
			LOGGER.debug("Found " + result.size() + " Matches");
			for (String str : result) {
				LOGGER.debug(str);
			}
		} catch (JobBoardServiceException ex) {
			LOGGER.error("findMatchesTest", ex);
		}

	}

	@Test
	public void findBestMatchTest() {
		String result;
		try {
			result = keywordIndexService.findBestMatch(testData[0]);
			LOGGER.debug("Best match found " + result);
		} catch (JobBoardServiceException ex) {
			LOGGER.error("findBestMatchTest", ex);
		}
	}

}
