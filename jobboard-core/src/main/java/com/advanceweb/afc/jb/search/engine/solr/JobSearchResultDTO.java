package com.advanceweb.afc.jb.search.engine.solr;

import java.io.Serializable;

/**
 * @author ReeteshRN
 * @version 1.0
 * @Date 10th July 2012 onwards
 */

public class JobSearchResultDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private SolrJobSearchResultDTO solrJobSearchResultDTO;
	
	public SolrJobSearchResultDTO getSolrJobSearchResultDTO() {
		return solrJobSearchResultDTO;
	}
	public void setSolrJobSearchResultDTO(
			SolrJobSearchResultDTO solrJobSearchResultDTO) {
		this.solrJobSearchResultDTO = solrJobSearchResultDTO;
	}
	
}
