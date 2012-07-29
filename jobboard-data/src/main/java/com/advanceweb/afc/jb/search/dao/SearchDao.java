package com.advanceweb.afc.jb.search.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.MetaSearchIndexDTO;
import com.advanceweb.afc.jb.common.MetaSearchInputDTO;

public interface SearchDao {
	
	/**
	 * This method is used for getting the parameter lists from the DB for 
	 * creating the SOLR query.
	 */
	List<MetaSearchInputDTO> getParamList(String searchIndexName,String environment, String searchTypeName);
	
	/**
	 * This method is used for getting the rqsults related to Solr server URL.
	 */
	
	List<MetaSearchIndexDTO> getURLQuery(String searchIndexName,String environment, String searchIndexGroup);

}
