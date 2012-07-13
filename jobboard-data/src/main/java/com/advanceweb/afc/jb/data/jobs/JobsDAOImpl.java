package com.advanceweb.afc.jb.data.jobs;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.data.domain.Job;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:41 PM
 */
@Repository
public class JobsDAOImpl implements JobsDAO {

	public JobsDAOImpl(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param job
	 */
	public boolean createNewJob(Job job){
		return false;
	}

	/**
	 * 
	 * @param jobId
	 */
	public boolean applyForJob(long jobId){
		return false;
	}

}