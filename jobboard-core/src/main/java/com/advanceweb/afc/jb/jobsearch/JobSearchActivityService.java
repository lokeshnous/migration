package com.advanceweb.afc.jb.jobsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.JobSearchDTO;
import com.advanceweb.afc.jb.common.SearchResultDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.data.jobsearch.JobSearchActivityDAO;

/**
 * <code> JobSearchActivityService </code> is a implementation for Service class. 
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 *  
 */
@Service("articleSearchService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JobSearchActivityService implements JobSearchActivity {

	@Autowired
	public JobSearchActivityDAO jobSearchActivityDAO;


	JobSearchActivityService() {
		
	}

	/**
	 * view searched job
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public SearchedJobDTO viewJobDetails(long jobId) {
		return jobSearchActivityDAO.viewJobDetails(jobId);
	}
	
	/**
	 * apply job
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void applyJob(long jobId) {		
		jobSearchActivityDAO.applyJob(jobId);
	}
	
	
	@Override
	public SearchResultDTO getJobSearchResult(String searchString) {
		// TODO Auto-generated method stub
		  HttpSolrServer server = null;
		  QueryResponse response = null;
		  SearchResultDTO searhResultDTO = new SearchResultDTO();
		  JobSearchDTO jobSearchDTO = new JobSearchDTO();
		  String rows = "30";// Deafault set to 30
		  String start = "0";// Deafault set to 0
		  
		  server = connectToSOLRURL();
		  response = executeSearchQuery(server,searchString, rows, start);
			
			System.out.println("Number of search records===>>>"+response.getResults().getNumFound());
			searhResultDTO.setTotalNumSearchResult(response.getResults().getNumFound());
			
			List<JobSearchDTO> jobSearchDTOList = new ArrayList<JobSearchDTO>();
			jobSearchDTOList = response.getBeans(JobSearchDTO.class);
			
			Iterator<JobSearchDTO> itr = jobSearchDTOList.iterator();
			
			while (itr.hasNext()) {
				jobSearchDTO = new JobSearchDTO();
				jobSearchDTO = itr.next();
				System.out.println("@Company===>>"+jobSearchDTO.getCompany());
				System.out.println("@Job Title===>>"+jobSearchDTO.getJobtitle());
				System.out.println("@City===>>"+jobSearchDTO.getCity());
				System.out.println("@Posted Date===>>"+jobSearchDTO.getPosted_dt());
			}
			
			 
			Map<String, List<Count>> facetMap = new HashMap<String, List<Count>>();
			FacetField facetField = null;
			List<FacetField> facetFieldList = null;
			facetFieldList = response.getFacetFields();
			Iterator<FacetField> iterator = facetFieldList.iterator();
			
			while (iterator.hasNext()) {
				facetField = iterator.next();
				System.out.println("@Facet Name===>>"+facetField.getName());
				facetMap.put(facetField.getName(), facetField.getValues());
				System.out.println("@Facet Values(Categories)===>>>"+facetMap.get(facetField.getName()));		
			}
			 
			  searhResultDTO.setFacetMap(facetMap);
			  searhResultDTO.setSearchResultList(jobSearchDTOList);
			  
			  return searhResultDTO;
		
	}

	@Override
	public HttpSolrServer connectToSOLRURL() {
		  String url = "http://192.168.25.30:8080/careers/jobPost/";
		  HttpSolrServer server = new HttpSolrServer(url);
		  server.setSoTimeout(1000);  // socket read timeout
		  server.setConnectionTimeout(100);
		  server.setDefaultMaxConnectionsPerHost(100);
		  server.setMaxTotalConnections(100);
		  server.setFollowRedirects(false);  // defaults to false
		  server.setAllowCompression(true);
		  server.setMaxRetries(0); 
		  server.setParser(new XMLResponseParser()); // binary parser is used by default
		  return server;
	}

	@Override
	public QueryResponse executeSearchQuery(HttpSolrServer server, String searchString, String rows, String start) {
		QueryResponse response = null;
		
		if(searchString != null){
			SolrQuery searchquery = new  SolrQuery();
			searchquery.setQuery(searchString);
			searchquery.setFacet(true);
			searchquery.addFacetField("city");
			searchquery.addFacetField("company");
			searchquery.addFacetField("jobtitle");
			searchquery.addFacetField("posted_dt");
			searchquery.add("rows", rows);
			searchquery.add("start", start);
			System.out.println("searchquery===>>>"+searchquery);
			
			try {
				response = server.query(searchquery);
				} catch (SolrServerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
		return response;
	}
	

	public JobSearchActivityDAO getJobSearchActivityDAO() {
		return jobSearchActivityDAO;
	}
	
	public void setJobSearchActivityDAO(JobSearchActivityDAO jobSearchActivityDAO) {
		this.jobSearchActivityDAO = jobSearchActivityDAO;
	}

	

}
