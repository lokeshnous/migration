package com.advanceweb.afc.jb.job.service;

/*import org.apache.solr.client.solrj.impl.HttpSolrServer;
 import org.apache.solr.client.solrj.response.QueryResponse;*/

import com.advanceweb.afc.jb.common.ApplyJobDTO;
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
	 * apply job
	 * 
	 * @param applyJobDTO
	 * @return
	 */
	void applyJob(ApplyJobDTO applyJobDTO);

	/**
	 * saves the job for logged in user
	 * 
	 * @param searchedJobDTO
	 */
	public void saveJob(SearchedJobDTO searchedJobDTO);

}