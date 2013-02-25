/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.dao;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.data.domain.Job;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:41 PM
 */
@Repository
public class JobDAOImpl implements JobDAO {

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