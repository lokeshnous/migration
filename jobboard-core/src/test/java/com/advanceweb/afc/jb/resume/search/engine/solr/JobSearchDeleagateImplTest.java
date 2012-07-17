package com.advanceweb.afc.jb.resume.search.engine.solr;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.transaction.JOnASTransactionManagerLookup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.resume.ResumeService;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchDTO;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchDeleagate;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchDeleagateImpl;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchResultDTO;
import com.advanceweb.afc.jb.search.engine.solr.ReadSolrServerDetails;
import com.advanceweb.afc.jb.search.engine.solr.SolrJobSearchResultDTO;

public class JobSearchDeleagateImplTest extends ServiceTest{
	
	@Autowired
	private JobSearchDeleagate jobSearchDeleagate ;//= new JobSearchDeleagateImpl();

	@Test
	public void jobSearchTest1() {
		
		String searchName = "basicjobsearch";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("titlesearch", "doctor");
		//paramMap.put("", arg1);
		//paramMap.put("", arg1);
		long rows = 4;
		long start = 0;
		
		JobSearchResultDTO jobSearchResultDTO = jobSearchDeleagate.jobSearch(searchName, paramMap, rows, start);
		assertTrue("Job Search", jobSearchResultDTO.getSolrJobSearchResultDTO() != null);
		SolrJobSearchResultDTO solrJobSearchResultDTO = jobSearchResultDTO.getSolrJobSearchResultDTO();
		List<JobSearchDTO> searchResultList = solrJobSearchResultDTO.getSearchResultList();
		
		System.out.println("Size of searchResultList==>>"+searchResultList.size());
		
	}
	
	@Test
	public void jobSearchTest2() {
		
		String searchName = "basicjobsearch";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("titlesearch", "doctor");
		//paramMap.put("", arg1);
		//paramMap.put("", arg1);
		long rows = 4;
		long start = 0;
		
		JobSearchResultDTO jobSearchResultDTO = jobSearchDeleagate.jobSearch(searchName, paramMap, rows, start);
		assertTrue("Job Search", jobSearchResultDTO.getSolrJobSearchResultDTO() != null);
		SolrJobSearchResultDTO solrJobSearchResultDTO = jobSearchResultDTO.getSolrJobSearchResultDTO();
		List<JobSearchDTO> searchResultList = solrJobSearchResultDTO.getSearchResultList();
		
		System.out.println("Size of searchResultList==>>"+searchResultList.size());
		
	}
	
	@Test
	public void jobSearchTest3() {
		
		String searchName = "basicjobsearch";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("titlesearch", "doctor");
		//paramMap.put("", arg1);
		//paramMap.put("", arg1);
		long rows = 0;
		long start = 0;
		
		JobSearchResultDTO jobSearchResultDTO = jobSearchDeleagate.jobSearch(searchName, paramMap, rows, start);
		assertTrue("Job Search", jobSearchResultDTO.getSolrJobSearchResultDTO() != null);
		SolrJobSearchResultDTO solrJobSearchResultDTO = jobSearchResultDTO.getSolrJobSearchResultDTO();
		List<JobSearchDTO> searchResultList = solrJobSearchResultDTO.getSearchResultList();
		
		System.out.println("Size of searchResultList==>>"+searchResultList.size());
		
	}
	
	@Test
	public void jobSearchTest4() {
		
		String searchName = "basicjobsearch";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("titlesearch", "");
		//paramMap.put("", arg1);
		//paramMap.put("", arg1);
		long rows = 4;
		long start = 0;
		
		JobSearchResultDTO jobSearchResultDTO = jobSearchDeleagate.jobSearch(searchName, paramMap, rows, start);
		assertTrue("Job Search", jobSearchResultDTO.getSolrJobSearchResultDTO() == null);
		SolrJobSearchResultDTO solrJobSearchResultDTO = jobSearchResultDTO.getSolrJobSearchResultDTO();
		List<JobSearchDTO> searchResultList = solrJobSearchResultDTO.getSearchResultList();
		
		System.out.println("Size of searchResultList==>>"+searchResultList.size());
		
	}
	
	@Test
	public void jobSearchTest5() {
		
		String searchName = "";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("titlesearch", "doctor");
		//paramMap.put("", arg1);
		//paramMap.put("", arg1);
		long rows = 4;
		long start = 0;
		
		JobSearchResultDTO jobSearchResultDTO = jobSearchDeleagate.jobSearch(searchName, paramMap, rows, start);
		assertTrue("Job Search", jobSearchResultDTO.getSolrJobSearchResultDTO() != null);
		SolrJobSearchResultDTO solrJobSearchResultDTO = jobSearchResultDTO.getSolrJobSearchResultDTO();
		List<JobSearchDTO> searchResultList = solrJobSearchResultDTO.getSearchResultList();
		
		System.out.println("Size of searchResultList==>>"+searchResultList.size());
		
	}

	
	

}
