package com.advanceweb.afc.jb.resume.search.engine.solr;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchDeleagate;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchResultDTO;

public class JobSearchDeleagateImplTest extends ServiceTest{
	
	
	@Autowired
	private JobSearchDeleagate jobSearchDeleagate ;//= new JobSearchDeleagateImpl();

	private static final String SEARCH_NAME = "basicjobsearch";
	private static final Map<String, String> PARAM_MAP = new HashMap<String, String>();
	private static final String TITLE_SEARCH = "titlesearch" ; 
	
	@Ignore("Not ready to test")
	@Test
	public void jobSearchTest1() {
		
		PARAM_MAP.put(TITLE_SEARCH, "doctor");
		long rows = 4;
		long start = 0;
		
		JobSearchResultDTO jobSearchResultDTO = jobSearchDeleagate.jobSearch(SEARCH_NAME, PARAM_MAP, start, rows);
		assertNotNull("Job Search 1", jobSearchResultDTO.getSolrJobSearchResultDTO());
		
		
	}
	
	@Ignore("Not ready to test")
	@Test
	public void jobSearchTest2() {
		
		PARAM_MAP.put(TITLE_SEARCH, "nurse");
		long rows = 4;
		long start = 0;
		
		JobSearchResultDTO jobSearchResultDTO = jobSearchDeleagate.jobSearch(SEARCH_NAME, PARAM_MAP, start, rows);
		assertNotNull("Job Search 2", jobSearchResultDTO.getSolrJobSearchResultDTO());
				
	}
	
	@Ignore("Not ready to test")
	@Test
	public void jobSearchTest3() {
		
		PARAM_MAP.put(TITLE_SEARCH, "test");
		long rows = 0;
		long start = 0;
		
		JobSearchResultDTO jobSearchResultDTO = jobSearchDeleagate.jobSearch(SEARCH_NAME, PARAM_MAP, start, rows);
		assertNotNull("Job Search 3", jobSearchResultDTO.getSolrJobSearchResultDTO());
				
	}
	
	@Test
	public void jobSearchTest4() {
		
		PARAM_MAP.put("titlesearch", "");
		long rows = 4;
		long start = 0;
		
		JobSearchResultDTO jobSearchResultDTO = jobSearchDeleagate.jobSearch(SEARCH_NAME, PARAM_MAP, start, rows);
		assertNull("Job Search 4", jobSearchResultDTO);
				
	}
	
	@Ignore("Not ready to test")
	@Test
	public void jobSearchTest5() {
		
		String searchName = "";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("titlesearch", "doctor");
		long rows = 4;
		long start = 0;
		
		JobSearchResultDTO jobSearchResultDTO = jobSearchDeleagate.jobSearch(searchName, paramMap, start, rows);
		assertNotNull("Job Search 5", jobSearchResultDTO.getSolrJobSearchResultDTO());
				
	}

	
	

}
