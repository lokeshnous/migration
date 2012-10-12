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

	private static final Logger LOGGER = Logger
			.getLogger(AbstractSolrSearchDelegate.class);

	private static final String URL_SEPARATOR = "/";

	@Autowired
	private SOLRSearchHelper solrSearchHelper;

	@Autowired
	private SearchDAO searchDAO;

	@Autowired
	private SearchParamBuilder searchParamBuilder;

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

			// Merge the parameters
			List<SearchParamDTO> queryParams = searchParamBuilder.buildParams(
					queryDTO.getmSrchParamList(), inputParams);

			// Get the SOLR query response by execution of the query.
			QueryResponse response = executeSolrQuery(solrServerURL,
					queryParams);
			
			// Convert and return the result
			return fillSearchResult(response, clazz);
		} else {
			LOGGER.info("The SOLR Server " + solrServerURL
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

		LOGGER.info("Search query: " + searchquery);
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

		LOGGER.info("Job Search returned " + resultCount + " records");

		SearchResultDTO<T> resultDTO = new SearchResultDTO<T>();
		resultDTO.setResultCount(resultCount);

		// Binding search result data
		resultDTO.setResultList(response.getBeans(clazz));

		// Extract the facet fields
		Map<String, List<SearchFacetDTO>> facetMap = new HashMap<String, List<SearchFacetDTO>>();
		List<FacetField> facetFieldList = response.getFacetFields();

		// Creating Lists of Facets(List<String>) by iterating the
		// FacetFieldList
		
		for (FacetField facetField : facetFieldList) {
			List<SearchFacetDTO> searchFacetDTOList = new ArrayList<SearchFacetDTO>();
			if(facetField.getValues() == null){
				LOGGER.info(facetField.getName()+" facet not found.");
			}else{
				for (Count countObj : facetField.getValues()) {
					searchFacetDTOList.add(new SearchFacetDTO(countObj.getName(),
							countObj.getCount()));
				}
				facetMap.put(facetField.getName(), searchFacetDTOList);
			}
		}
		resultDTO.setFacetMap(facetMap);

		return resultDTO;
	}

}
