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
	public void testRetrieveAllResumes() {
		
		String searchName = "basicjobsearch";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("titlesearch", "doctor");
		//paramMap.put("", arg1);
		//paramMap.put("", arg1);
		
		long rows = 3;
		long start = 0;
		
		try {
			
			JobSearchResultDTO jobSearchResultDTO = jobSearchDeleagate.jobSearch(searchName, paramMap, rows, start);
			
			assertTrue("Job Search", jobSearchResultDTO.getSolrJobSearchResultDTO() != null);
			SolrJobSearchResultDTO solrJobSearchResultDTO = jobSearchResultDTO.getSolrJobSearchResultDTO();
			
			
			
			System.out.println(solrJobSearchResultDTO);
			List<JobSearchDTO> searchResultList = solrJobSearchResultDTO.getSearchResultList();
			
			
			for (JobSearchDTO jobSearchDTO : searchResultList) {
				System.out.println(jobSearchDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*@Test
	public void testEditResume() {
		try {

			ResumeDTO resumeDTO = resumeService.editResume(2);
			assertTrue("Edit Resume", resumeDTO != null);
			System.out.println(resumeDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	

}
