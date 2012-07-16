package com.advanceweb.afc.jb.job.service;

/*import org.apache.solr.client.solrj.impl.HttpSolrServer;
 import org.apache.solr.client.solrj.response.QueryResponse;*/

import java.util.Map;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.advanceweb.afc.jb.common.ApplyJobDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.search.engine.solr.SearchResultDTO;

/**
 * <code> JobSearchActivity </code> is a Service class.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */
public interface JobSearchActivity {

	/**
	 * get job details by job ID
	 * 
	 * @param jobId
	 * @return
	 */
	SearchedJobDTO viewJobDetails(long jobId);

	/**
	 * apply job
	 * 
	 * @param applyJobDTO
	 * @return
	 */
	void applyJob(ApplyJobDTO applyJobDTO);

	/**
	 * Gets the job search result by searchString
	 * 
	 * @param SearchString
	 * @return SearchResultDTO
	 */
	public SearchResultDTO getJobSearchResult(String SearchString,
			Map<String, String> serverDetailsMap, Map<String, String> solrQueryDetails, String rows, String start);

	/**
	 * saves the job for logged in user
	 * 
	 * @param searchedJobDTO
	 */
	public void saveJob(SearchedJobDTO searchedJobDTO);

	/**
	 * Returns the SOLR Server object
	 * 
	 * @param
	 * @return HttpSolrServer
	 */
	public HttpSolrServer connectToSOLRURL(Map<String, String> serverDetailsMap);

	/**
	 * Returns the SOLR Server response object
	 * 
	 * @param HttpSolrServer
	 * @return QueryResponse
	 */
	public QueryResponse executeSearchQuery(HttpSolrServer server,
			String SearchString, Map<String, String> solrQueryDetails, String rows, String start);

}