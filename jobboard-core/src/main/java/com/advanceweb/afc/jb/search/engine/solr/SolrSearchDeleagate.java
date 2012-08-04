package com.advanceweb.afc.jb.search.engine.solr;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.MetaSearchParamDTO;
import com.advanceweb.afc.jb.common.QueryDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.SolrParameter;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.search.JobSearchDeleagate;
import com.advanceweb.afc.jb.search.dao.SearchDao;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * This class has been created for the Solr Server Job search functionalities.
 * 
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 10 July 2012
 */

@Service("jobSearchDeleagate")
public class SolrSearchDeleagate implements JobSearchDeleagate {

	private static final Logger LOGGER = Logger
			.getLogger("JobSearchDeleagateImpl.class");

	@Autowired
	private SOLRSearchHelper solrSrchHelper;

	@Autowired
	@Resource(name = "solrConfiguration")
	private Properties solrConfiguration;

	@Autowired
	private SearchDao searchDao;

	@Autowired
	private SolrParameter solrParameter;

	@PostConstruct
	public void init() {
		// init() for loading the Properties file
	}

	/**
	 * This method is used to do the Solr Server Job Search based on the passed
	 * parameters.
	 * 
	 * @param searchName
	 *            represents the type of the job search
	 * @param paramMap
	 *            contains the input parameters from the UI
	 * @param rows
	 *            represents how many rows will be displayed
	 * @param start
	 *            represents the starting point of the search
	 * @return JobSearchResultDTO
	 * @throws JobBoardServiceException
	 * @throws JobBoardDataException
	 * @throws UnsupportedEncodingException
	 */

	@Override
	public JobSearchResultDTO jobSearch(final String searchName,
			final Map<String, String> paramMap, final long start,
			final long rows) throws JobBoardServiceException,
			JobBoardDataException {

		QueryResponse response = null;
		QueryDTO queryDTO = null;

		if ("".equalsIgnoreCase(paramMap.get(MMJBCommonConstants.SESSION_ID))
				|| paramMap.get(MMJBCommonConstants.SESSION_ID) == null) {
			LOGGER.info("Session ID is not present in the Search query.");
		}
		if ("".equalsIgnoreCase(paramMap.get(MMJBCommonConstants.SEARCH_SEQ))
				|| paramMap.get(MMJBCommonConstants.SEARCH_SEQ) == null) {
			LOGGER.info(" Sequence ID is not present in the Search query.");
		}

		/**
		 * It is checking whether all the parameters coming from the UI is blank
		 * or not.
		 */
		if (("".equalsIgnoreCase(paramMap.get(MMJBCommonConstants.KEYWORDS)) || paramMap
				.get(MMJBCommonConstants.KEYWORDS) == null)
				&& ("".equalsIgnoreCase(paramMap
						.get(MMJBCommonConstants.CITY_STATE)) || paramMap
						.get(MMJBCommonConstants.CITY_STATE) == null)
				&& ("".equalsIgnoreCase(paramMap
						.get(MMJBCommonConstants.RADIUS)) || paramMap
						.get(MMJBCommonConstants.RADIUS) == null)) {

			LOGGER.info("Empty Search criteria. Please enter a search criteria to search jobs.");
			return null;

		} else {

			/**
			 * Calling the DAO layer to get the QueryDTO which contains all the
			 * details of the solr search query with server details.
			 */
			queryDTO = searchDao.getSearchQueryDTO(
					solrParameter.getSearchIndexName(),
					solrParameter.getEnvironment(),
					solrParameter.getSearchIndexGroup(), searchName);

			/** Creation of server url to check whether it is accessible or not. */
			String serverURlToCheck = queryDTO.getSearchHost()
					.concat(MMJBCommonConstants.SLASH)
					.concat(queryDTO.getSearchIndexGroup())
					.concat(MMJBCommonConstants.SLASH)
					.concat(queryDTO.getSearchIndexName())
					.concat(MMJBCommonConstants.SLASH)
					.concat(MMJBCommonConstants.SELECT);

			LOGGER.info("Server URL To Check===>" + serverURlToCheck);

			/** Checking whether server url is accessible. */
			boolean serverAccessible = solrSrchHelper
					.isServerAccessible(serverURlToCheck);

			if (serverAccessible) {

				/** Getting the Server details from the properties file **/
				final Map<String, String> serverDetailsMap = solrSrchHelper
						.getServerDetails(solrConfiguration);

				/** Getting the SOLR query response by execution of the query. **/
				response = getSolrResponse(serverDetailsMap,
						queryDTO, paramMap, rows, start);

				if (response == null) {

					LOGGER.info("No Results Found...");
					return null;

				} else {

					/**
					 * Calling getSolrJSResult()by passing the response object
					 * and returning the JobSearchResultDTO object into service
					 * layer.
					 **/
					return getSolrJSResult(response);
				}

			} else {
				LOGGER.info("Server url is not correct. Please check the url.");
				return null;
			}
		}

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
	 * @throws UnsupportedEncodingException
	 */
	private QueryResponse getSolrResponse(
			final Map<String, String> serverDetailsMap, QueryDTO queryDTO,
			Map<String, String> paramMap, long rows, long start)
			throws JobBoardServiceException {
		
		QueryResponse response = null;

		/** Get the instance of the HttpSolrServer by passing the QueryDTO and 
		 * values read from the properties file. **/
		HttpSolrServer server = getSolrServerInstance(queryDTO, serverDetailsMap);

		/**
		 * Creating and getting the search parameter list from the QueryDTO 
		 * got from the DB and then creating the Search Parameter list by the replacing
		 * the parameter values. 
		 **/
		List<MetaSearchParamDTO> srchParamRlpcdDTOList = createParamsForKeywordSearch(
				queryDTO, paramMap, rows, start);

		/** Creating the SOLR query by passing the replaced searched parameter list. 
		 * **/
		SolrQuery searchquery = creatSOLRQuery(srchParamRlpcdDTOList);
		LOGGER.info("Search query===>>>" + searchquery);

		try {
			/** Execution of the solr query*/
			response = server.query(searchquery);

		} catch (SolrServerException e) {
			LOGGER.debug(e);
			throw new JobBoardServiceException(
					"Error occurred while trying to Execute the SOLR query...");
		}
		return response;

	}

	/**
	 * This method creates the search SOLR query.
	 * 
	 * @param queryDTO
	 *            Represents the QueryDTO object which contails all the details
	 *            of the solr query parameters taken from the DB
	 * @param paramMap
	 *            Contains all the input parameters from the UI
	 * @param rows
	 *            represents number of rows will be displayed
	 * @param start
	 *            represents the starting point of the search
	 * @return object of SolrQuery
	 */

	private List<MetaSearchParamDTO> createParamsForKeywordSearch(
			QueryDTO queryDTO, Map<String, String> paramMap, long rows,
			long start) {

		List<MetaSearchParamDTO> srchReplacedParamDTOList = new ArrayList<MetaSearchParamDTO>();

		/** Getting the Search parameter List from QueryDTO. **/
		List<MetaSearchParamDTO> srchParamDTOList = queryDTO
				.getmSrchParamList();

		for (MetaSearchParamDTO mSrchParamDTO : srchParamDTOList) {

			int value = 0;
			String temp = "";
			if (mSrchParamDTO.getParameterValue().contains(":b")) {
				temp = mSrchParamDTO.getParameterValue().substring(
						mSrchParamDTO.getParameterValue().indexOf(":b")
								+ ":b".length(),
						mSrchParamDTO.getParameterValue().length());

				if (isIntNumber(temp)) {
					value = Integer.parseInt(temp);

					switch (value) {
					case 01:
						mSrchParamDTO.setParameterValue(paramMap
								.get(MMJBCommonConstants.KEYWORDS)
								+ "+"
								+ paramMap.get(MMJBCommonConstants.CITY_STATE)
								+ "+"
								+ paramMap.get(MMJBCommonConstants.RADIUS));
						break;
					case 02:
						mSrchParamDTO.setParameterValue(String.valueOf(rows));
						break;
					case 03:
						mSrchParamDTO.setParameterValue(String.valueOf(start));
						break;
					case 04:
						mSrchParamDTO.setParameterValue(paramMap
								.get(MMJBCommonConstants.SESSION_ID));
						break;
					case 05:
						mSrchParamDTO.setParameterValue(paramMap
								.get(MMJBCommonConstants.QUERY_TYPE));
						break;
					case 06:
						mSrchParamDTO.setParameterValue(paramMap
								.get(MMJBCommonConstants.SEARCH_SEQ));
						break;
					default:
						LOGGER.debug("No Matching found for param value from Search parameters got from DB.");
						break;
					}

				} 

			}
			/**Adding the SearchParamDTO to the ReplacedSearchParamDTO list to return it back.**/
			srchReplacedParamDTOList.add(mSrchParamDTO);

		}
		
		return srchReplacedParamDTOList;

	}

	// Set the param into the SOLR query
	private SolrQuery creatSOLRQuery(List<MetaSearchParamDTO> srchReplacedParamDTOList) {

		SolrQuery searchquery = new SolrQuery();

		/** Iterating Search parameter List and forming the SOLR query. **/
		for (MetaSearchParamDTO mSrchParamDTO : srchReplacedParamDTOList) {
			searchquery.setParam(mSrchParamDTO.getParameterName(), mSrchParamDTO.getParameterValue());
		}

		/** Adding the facets to SOLR query */
		searchquery.addFacetField(MMJBCommonConstants.CITY);
		searchquery.addFacetField(MMJBCommonConstants.COMPANY);
		// searchquery.addFacetField(MMJBCommonConstants.RADIUS);
		searchquery.addFacetField(MMJBCommonConstants.POSTED_DT);
		searchquery.addFacetField(MMJBCommonConstants.STATE);

		return searchquery;

	}

	/**
	 * This method parse the response and set the values into the bean for
	 * further processing.
	 * @param response represents the instance of QueryResponse 
	 * @return JobSearchResultDTO 
	 */
	private JobSearchResultDTO getSolrJSResult(QueryResponse response) {

		JobSearchResultDTO jobSResultDTO = new JobSearchResultDTO();
		LOGGER.info("Number of search records===>"
				+ response.getResults().getNumFound());

		jobSResultDTO.setTotalNumSearchResult(response.getResults()
				.getNumFound());

		List<JobSearchDTO> jobSearchDTOList = new ArrayList<JobSearchDTO>();
		
		/** Binding the JobSearchDTO.class into the QueryResponse object**/
		jobSearchDTOList = response.getBeans(JobSearchDTO.class);

		final Map<String, List<String>> facetMap = new HashMap<String, List<String>>();
		final List<FacetField> facetFieldList = response.getFacetFields();
		
		/**Creating Lists of Facets(List<String>) by iterating the FaceeFieldList**/
		for (FacetField facetField : facetFieldList) {
			List<String> facetsList = new ArrayList<String>();
			List<Count> facetFieldValList = facetField.getValues();

			for (Count countObj : facetFieldValList) {
				String facetVal = countObj.getName().toString();
				long count = countObj.getCount();
				facetsList.add(facetVal.concat(" (")
						.concat(String.valueOf(count)).concat(")"));
			}

			LOGGER.info("facetsList===>" + facetsList);
			facetMap.put(facetField.getName(), facetsList);

		}
		jobSResultDTO.setFacetMap(facetMap);
		jobSResultDTO.setSearchResultList(jobSearchDTOList);

		return jobSResultDTO;
	}

	/**
	 * This method creates a HttpSolrServer instance by setting all the required
	 * server parameters.
	 * @param queryDTO	represents all the SOLR server parameter values from the DB. 
	 * @param serverDetailsMap contains all the server details being red from the property file.
	 * @return instance of HttpSolrServer 
	 */
	
	private HttpSolrServer getSolrServerInstance(QueryDTO queryDTO,
			Map<String, String> serverDetailsMap) {

		HttpSolrServer server = new HttpSolrServer(queryDTO.getSearchHost()
				.concat(MMJBCommonConstants.SLASH)
				.concat(queryDTO.getSearchIndexGroup())
				.concat(MMJBCommonConstants.SLASH)
				.concat(queryDTO.getSearchIndexName())
				.concat(MMJBCommonConstants.SLASH));

		server.setSoTimeout(Integer.parseInt(serverDetailsMap
				.get(MMJBCommonConstants.SO_TIMEOUT)));
		server.setConnectionTimeout(Integer.parseInt(serverDetailsMap
				.get(MMJBCommonConstants.CONNECTION_TIMEOUT)));
		server.setDefaultMaxConnectionsPerHost(Integer
				.parseInt(serverDetailsMap
						.get(MMJBCommonConstants.MAX_CONNECTION_HOST)));
		server.setMaxTotalConnections(Integer.parseInt(serverDetailsMap
				.get(MMJBCommonConstants.MAX_TOTAL_CONNECTION)));

		server.setFollowRedirects(Boolean.parseBoolean(serverDetailsMap
				.get(MMJBCommonConstants.FOLLOW_REDIRECTS))); // defaults to
																// false
		server.setAllowCompression(Boolean.parseBoolean(serverDetailsMap
				.get(MMJBCommonConstants.ALLOW_COMPRESSION)));
		server.setMaxRetries(Integer.parseInt(serverDetailsMap
				.get(MMJBCommonConstants.MAX_RETRIES)));
		server.setParser(new XMLResponseParser());

		return server;

	}
	
	/**
	 * This method checks whether the String parameter is int or not.
	 * @param String 
	 * @return boolean
	 */

	private boolean isIntNumber(String num) {
		try {
			Integer.parseInt(num);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
