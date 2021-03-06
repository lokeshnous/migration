/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search.engine.solr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.advanceweb.afc.jb.common.QueryDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.search.SearchFacetDTO;
import com.advanceweb.afc.jb.search.SearchIndex;
import com.advanceweb.afc.jb.search.SearchParamBuilder;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.search.SearchResultDTO;
import com.advanceweb.afc.jb.search.dao.SearchDAO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

@Component
public abstract class AbstractSolrSearchDelegate {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(AbstractSolrSearchDelegate.class);

	/** The Constant URL_SEPARATOR. */
	private static final String URL_SEPARATOR = "/";

	/** The solr search helper. */
	@Autowired
	private SOLRSearchHelper solrSearchHelper;

	/** The search dao. */
	@Autowired
	private SearchDAO searchDAO;

	/** The search param builder. */
	@Autowired
	private SearchParamBuilder searchParamBuilder;

	/** The search index. */
	private SearchIndex searchIndex;

	/**
	 * Constructor for the base class. The search Index parameter is used as the
	 * constructor argument.
	 * 
	 * @param searchIndex
	 */
	public AbstractSolrSearchDelegate(SearchIndex searchIndex) {
		this.searchIndex = searchIndex;
	}

	/**
	 * This method is used to do the Solr Server Job Search based on the passed
	 * parameters.
	 * 
	 * @param searchName
	 *            represents the type of the job search
	 * @param inputParams
	 *            contains the input parameters from the UI
	 * @param rows
	 *            represents how many rows will be displayed
	 * @param start
	 *            represents the starting point of the search
	 * @return JobSearchResultDTO
	 * @throws JobBoardServiceException
	 * @throws JobBoardServiceException
	 */
	public <T> SearchResultDTO<T> search(final String searchName,
			final Map<String, String> inputParams, Class<T> clazz)
			throws JobBoardServiceException {

		/*
		 * Get the QueryDTO which contains all the details of the solr search
		 * query with server details.
		 */
		QueryDTO queryDTO = null;
			try {
				queryDTO = searchDAO.getSearchQueryDTO(searchIndex.getName(),
						searchIndex.getEnvironment(), searchIndex.getGroup(),
						searchName);
			} catch (JobBoardDataException jde) {
				LOGGER.debug(jde);
				throw new JobBoardServiceException(
						"Error while fetching the q parameters from the Database..."
								+ jde);
			}

		// Create the solr url and check if it is accessible
		String solrServerURL = createSolrBaseURL(queryDTO);

		boolean serverAccessible = solrSearchHelper
				.isServerAccessible(solrServerURL);

		if (serverAccessible) {
			QueryResponse response = null;
			if (!inputParams.get(SearchParamDTO.KEYWORDS).equalsIgnoreCase("*")){
	
				// Merge the parameters
				List<SearchParamDTO> queryParams = searchParamBuilder.buildParams(
						queryDTO.getmSrchParamList(), inputParams);
				
				// Get the SOLR query response by execution of the query.
				response = executeSolrQuery(solrServerURL,
						queryParams);
			} else {
				List<SearchParamDTO> queryParams = createParamForAllJobs(inputParams);
				response = executeSolrQuery(solrServerURL, queryParams);
			}
			
			// Convert and return the result
			return fillSearchResult(response, clazz);
		} else {
			LOGGER.error("The SOLR Server " + solrServerURL
					+ " is not accesible. Check the url");
			return null;
		}

	}

	/**
	 * Create base SOLR url from the QueryDTO. The base URL is formed by
	 * appending the parameters indexgroup and index name to the host name.
	 * 
	 * @param queryDTO
	 *            The QueryDTO from which the URL is to be formed
	 * @return Fully formed URL for executing the sOLR query
	 */
	private String createSolrBaseURL(QueryDTO queryDTO) {
		// TODO optimize using spring builder
		return queryDTO.getSearchHost().concat(URL_SEPARATOR)
				.concat(queryDTO.getSearchIndexGroup()).concat(URL_SEPARATOR)
				.concat(queryDTO.getSearchIndexName()).concat(URL_SEPARATOR);
	}

	/**
	 * This method will be used to set the server details to HttpSolrServer
	 * object, query the query string and get the QueryResponse from the Solr
	 * server
	 * 
	 * @param serverDetailsMap
	 *            represents the Map containing all the values of server details
	 *            from the property file
	 * 
	 * @param paramMap
	 *            Contains all the input parameters from the UI
	 * @param start
	 *            represents the starting point of the search
	 * @param rows
	 *            represents how many rows will be displayed
	 * @return QueryResponse represents the solr query object
	 * @throws JobBoardServiceException
	 * @throws JobBoardServiceException
	 */
	private QueryResponse executeSolrQuery(String solrServerURL,
			List<SearchParamDTO> queryParams) throws JobBoardServiceException {

		/*
		 * Get the instance of the HttpSolrServer by passing the QueryDTO and
		 * values read from the properties file.
		 */
		HttpSolrServer server = solrSearchHelper
				.getSolrServerInstance(solrServerURL);

		/*
		 * Creating the SOLR query by passing the replaced searched parameter
		 * list.
		 */
		SolrQuery searchquery = creatSOLRQuery(queryParams);

		// Execute the query
		QueryResponse response;
		try {
			/** Execution of the SOLR query */
			response = server.query(searchquery);

		} catch (SolrServerException e) {
			LOGGER.debug(e);
			throw new JobBoardServiceException(
					"Error occurred while trying to Execute the SOLR query...");
		}
		return response;

	}

	/**
	 * This method will be used to create SOLR query by passing
	 * MetaSearchParamDTO list
	 * 
	 * @param srchReplacedParamDTOList
	 * @return instance of the SolrQuery
	 */
	// Set the param into the SOLR query
	private SolrQuery creatSOLRQuery(
			List<SearchParamDTO> srchReplacedParamDTOList) {

		SolrQuery searchquery = new SolrQuery();

		/** Iterating Search parameter List and forming the SOLR query. **/
		for (SearchParamDTO mSrchParamDTO : srchReplacedParamDTOList) {
			/*searchquery.setParam(mSrchParamDTO.getParameterName(),
					mSrchParamDTO.getParameterValue());*/
			searchquery.add(mSrchParamDTO.getParameterName(),
					mSrchParamDTO.getParameterValue());
		}

		/** Adding the facets to SOLR query */
		/*searchquery.addFacetField(SearchFacetDTO.FACET_CITY);
		searchquery.addFacetField(SearchFacetDTO.FACET_COMPANY);
		// searchquery.addFacetField(MMJBCommonConstants.RADIUS);
		searchquery.addFacetField(SearchFacetDTO.FACET_POSTED_DATE);
		searchquery.addFacetField(SearchFacetDTO.FACET_STATE);*/

		LOGGER.debug("Created SOLR Search query: " + searchquery);
		return searchquery;

	}

	/**
	 * This method parse the response and set the values into the bean for
	 * further processing.
	 * 
	 * @param response
	 *            represents the instance of QueryResponse
	 * @return JobSearchResultDTO
	 */

	private <T> SearchResultDTO<T> fillSearchResult(QueryResponse response,
			Class<T> clazz) {

		long resultCount = response.getResults().getNumFound();

		LOGGER.debug("Job Search returned " + resultCount + " records");

		SearchResultDTO<T> resultDTO = new SearchResultDTO<T>();
		resultDTO.setResultCount(resultCount);

		// Binding search result data
		resultDTO.setResultList(response.getBeans(clazz));

		// Extract the facet fields
		Map<String, List<SearchFacetDTO>> facetMap = new HashMap<String, List<SearchFacetDTO>>();
		List<FacetField> facetFieldList = response.getFacetFields();

		// Creating Lists of Facets(List<String>) by iterating the
		// FacetFieldList
		if(facetFieldList != null && !facetFieldList.isEmpty() ){
			for (FacetField facetField : facetFieldList) {
				List<SearchFacetDTO> searchFacetDTOList = new ArrayList<SearchFacetDTO>();
				if(facetField.getValues() == null){
					LOGGER.error("Facet [" +facetField.getName()+"] is not found.");
				}else{
					for (Count countObj : facetField.getValues()) {
						searchFacetDTOList.add(new SearchFacetDTO(countObj.getName(),
								countObj.getCount()));
					}
					facetMap.put(facetField.getName(), searchFacetDTOList);
				}
			}
		}
		resultDTO.setFacetMap(facetMap);

		return resultDTO;
	}
	
	/**
	 * The method helps to create the search params to get the all jobs
	 * 
	 * @param inputParams
	 * @return
	 */
	private List<SearchParamDTO> createParamForAllJobs(Map<String, String> inputParams) {
		List<SearchParamDTO> result = new ArrayList<SearchParamDTO>();
		// TODO create and use a constructor
		SearchParamDTO resultParam = new SearchParamDTO();
		resultParam.setParameterName("q");
		resultParam.setParameterValue(inputParams.get(SearchParamDTO.KEYWORDS));
		// resultParam.setSearchParamId(param.getSearchParamId());
		// resultParam.setSeq(param.getSeq());
		result.add(resultParam);
		resultParam = new SearchParamDTO();
		resultParam.setParameterName("start");
		resultParam.setParameterValue(inputParams.get(SearchParamDTO.START));
		// resultParam.setSearchParamId(param.getSearchParamId());
		// resultParam.setSeq(param.getSeq());
		result.add(resultParam);
		resultParam = new SearchParamDTO();
		resultParam.setParameterName("rows");
		resultParam.setParameterValue(inputParams.get(SearchParamDTO.ROWS));
		// resultParam.setSearchParamId(param.getSearchParamId());
		// resultParam.setSeq(param.getSeq());
		result.add(resultParam);
		
		return result;
	}

}
