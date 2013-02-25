/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.index.lucene;

import java.util.Map;

import org.apache.lucene.search.Query;

public interface LuceneQueryBuilder {
	
	/**
	 * Builds the query.
	 *
	 * @param params the params
	 * @return the query
	 */
	Query buildQuery(Map<String,String> params);
}