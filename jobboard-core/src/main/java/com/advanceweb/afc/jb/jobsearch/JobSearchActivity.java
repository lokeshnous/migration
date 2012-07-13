package com.advanceweb.afc.jb.jobsearch;

/*import org.apache.solr.client.solrj.impl.HttpSolrServer;
 import org.apache.solr.client.solrj.response.QueryResponse;*/

import com.advanceweb.afc.jb.common.SearchResultDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;

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
	 * apply job by job ID
	 * 
	 * @param jobId
	 * @return
	 */
	void applyJob(long jobId);

	/**
	 * Gets the job search result by searchString
	 * 
	 * @param SearchString
	 * @return SearchResultDTO
	 */
	public SearchResultDTO getJobSearchResult(String SearchString);

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
	public HttpSolrServer connectToSOLRURL();

	/**
	 * Returns the SOLR Server response object
	 * 
	 * @param HttpSolrServer
	 * @return QueryResponse
	 */
	public QueryResponse executeSearchQuery(HttpSolrServer server,
			String SearchString, String rows, String start);

}