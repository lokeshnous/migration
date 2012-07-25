package com.advanceweb.afc.jb.job.service;

/*import org.apache.solr.client.solrj.impl.HttpSolrServer;
 import org.apache.solr.client.solrj.response.QueryResponse;*/

import com.advanceweb.afc.jb.common.SaveOrApplyJobDTO;
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
	 * save or apply the job for logged in user
	 * 
	 * @param jobDTO
	 * @return
	 */
	void saveOrApplyJob(SaveOrApplyJobDTO jobDTO);

	/**
	 * saves the job for logged in user
	 * 
	 * @param searchedJobDTO
	 */
	void saveJob(SearchedJobDTO searchedJobDTO);

}