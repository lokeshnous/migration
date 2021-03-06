/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search.dao;

import com.advanceweb.afc.jb.common.QueryDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

public interface SearchDAO {
	
	/**
	 * This method is used for getting the parameter lists from the DB for creating the SOLR query.
	 * @param searchIndexName represents search index name
	 * @param environment
	 * @param searchIndexGroup represents search index group
	 * @param searchTypeName represents search type name
	 * @return QueryDTO object of QueryDTO
	 * @throws JobBoardDataException
	 */
	
    QueryDTO getSearchQueryDTO(String searchIndexName,String environment, String searchIndexGroup, String searchTypeName) throws JobBoardDataException;
    

}
