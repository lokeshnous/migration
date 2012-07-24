package com.advanceweb.afc.jb.search.engine.solr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.springframework.stereotype.Repository;

/**
 * This class has been created as a helper class for the Solr related Job search
 * functionalities.
 * 
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 24 July 2012
 */ 

@Repository("solrJobSearchResult")
public class SolrJobSearchResult {
	
	private static final Logger LOGGER = Logger
			.getLogger("SolrJobSearchResult.class");
	
	public SolrJobSearchResultDTO getSolrJSResult(QueryResponse response){
		
		
		SolrJobSearchResultDTO solrJSResultDTO = new SolrJobSearchResultDTO();
		//System.out.println("Number of search records===>>>"+ response.getResults().getNumFound());

		solrJSResultDTO.setTotalNumSearchResult(response
				.getResults().getNumFound());

		List<JobSearchDTO> jobSearchDTOList = new ArrayList<JobSearchDTO>();
		jobSearchDTOList = response.getBeans(JobSearchDTO.class);

		final Map<String, List<Count>> facetMap = new HashMap<String, List<Count>>();
		final List<FacetField> facetFieldList = response.getFacetFields();

		for (FacetField facetField : facetFieldList) {
			facetMap.put(facetField.getName(), facetField.getValues());
			LOGGER.debug("@Facet Name===>>"+
			 facetField.getName()+",@Facet Values(Categories)===>>>"
			 + facetMap.get(facetField.getName()));
		}
		solrJSResultDTO.setFacetMap(facetMap);
		solrJSResultDTO.setSearchResultList(jobSearchDTOList);
		
		return solrJSResultDTO;
	}
	

}
