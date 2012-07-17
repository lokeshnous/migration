package com.advanceweb.afc.jb.search.engine.solr;

import java.io.Serializable;

import com.advanceweb.afc.jb.search.engine.solr.SolrJobSearchResultDTO;

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
