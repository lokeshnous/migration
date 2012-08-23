package com.advanceweb.afc.jb.search.engine.solr;

import com.advanceweb.afc.jb.common.QueryDTO;

public abstract class AbstractSolrSearchDelegate {
	private static final String URL_SEPARATOR = "/";

	protected String createSolrBaseURL(QueryDTO queryDTO) {
		
		// TODO optimize using spring builder
		return queryDTO.getSearchHost().concat(URL_SEPARATOR)
				.concat(queryDTO.getSearchIndexGroup()).concat(URL_SEPARATOR)
				.concat(queryDTO.getSearchIndexName()).concat(URL_SEPARATOR);
	}

}
