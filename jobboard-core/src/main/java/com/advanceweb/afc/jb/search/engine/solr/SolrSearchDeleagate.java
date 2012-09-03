package com.advanceweb.afc.jb.search.engine.solr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.JobSearchResultDTO;
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.QueryDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.common.util.SolrParameter;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.search.SearchFacetDTO;
import com.advanceweb.afc.jb.search.SearchParamBuilder;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.search.dao.LocationDAO;
import com.advanceweb.afc.jb.search.dao.SearchDAO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * This class has been created for the Solr Server Job search functionalities.
 * 
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 10 July 2012
 */

@Service("jobSearchDeleagate")
public class SolrSearchDeleagate extends AbstractSolrSearchDelegate implements
		JobSearchDeleagate {

	private static final Logger LOGGER = Logger
			.getLogger(SolrSearchDeleagate.class);

	@Autowired
	private SOLRSearchHelper solrSrchHelper;

	@Autowired
	private LocationDAO locationDAO;

	@Autowired
	private SearchDAO searchDAO;

	@Autowired
	private SolrParameter solrParameter;

	@Autowired
	private SearchParamBuilder searchParamBuilder;

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

	@Override
	public JobSearchResultDTO jobSearch(final String searchName,
			final Map<String, String> inputParams, final long start,
			final long rows) throws JobBoardServiceException {

		/*
		 * Check whether all the parameters coming from the UI is blank or not.
		 */
		if (validateInputParams(searchName, inputParams)) {
			LOGGER.info("Empty Search criteria. Please enter a search criteria to search jobs.");
			return null;
		}

		/*
		 * Get the QueryDTO which contains all the details of the solr search
		 * query with server details.
		 */
		QueryDTO queryDTO = null;

		try {
			queryDTO = searchDAO.getSearchQueryDTO(
					solrParameter.getSearchIndexName(),
					solrParameter.getEnvironment(),
					solrParameter.getSearchIndexGroup(), searchName);
		} catch (JobBoardDataException e) {
			LOGGER.debug(e);
			throw new JobBoardServiceException(
					"Error while fetching the q parameters from the Database..."
							+ e);
		}

		// Create the solr url and check if it is accessible
		String solrServerURL = createSolrBaseURL(queryDTO);

		boolean serverAccessible = solrSrchHelper
				.isServerAccessible(solrServerURL);

		if (serverAccessible) {

			List<SearchParamDTO> queryParams = prepaerQueryParams(
					queryDTO.getmSrchParamList(), inputParams, start, rows);

			// Get the SOLR query response by execution of the query.
			QueryResponse response = null;
			response = executeSolrQuery(solrServerURL, queryParams);

			if (response == null) {

				LOGGER.info("No Results Found...");
				return null;

			} else {

				/*
				 * Calling getSolrJSResult()by passing the response object and
				 * returning the JobSearchResultDTO object into service layer.
				 */
				return getSolrJSResult(response);
			}

		} else {
			LOGGER.info("The SOLR Server " + solrServerURL
					+ " is not accesible. Check the url");
			return null;
		}

	}

	/**
	 * This method prepares the query parameters to be passed into the solr
	 * search query. This method retrieve the query information from the
	 * database and replace the positional parameters in the query parametes
	 * with those from the user inputs
	 * 
	 * @param searchParams
	 *            The search parameters with placehoders
	 * @param inputParams
	 *            the user inputs
	 * @param start
	 *            The start parameters from input. This will be added to the
	 *            result as another item
	 * @param rows
	 *            The number of rows parameter. This will be added tot he result
	 *            as another item
	 * @return The list of parmaeters after replacing the placeholders with
	 *         appropriate user inputs
	 * @throws JobBoardServiceException
	 */
	private List<SearchParamDTO> prepaerQueryParams(
			List<SearchParamDTO> searchParams,
			final Map<String, String> inputParams, final long start,
			final long rows) throws JobBoardServiceException {

		// Prepare the parameters to be replaced in the buildParams method
		inputParams.put(SearchParamDTO.ROWS, Long.toString(rows));
		inputParams.put(SearchParamDTO.START, Long.toString(start));

		// The query parameter related to location is received as a name of a
		// city / state or as a pincode. The search parameter requires a position
		// represented as a sting of latitude and longitude in the format
		// "(latitude, longitude)". If a parameter of type cityState is found we
		// calculate the position and add it to the list
		String cityState = inputParams.get(SearchParamDTO.CITY_STATE);
		if (!StringUtils.isEmpty(cityState)) {
			LocationDTO location = getLocationFromCityStateOrZipCode(cityState);
			String position = location.getLatitude() + ","
					+ location.getLongitude();
			inputParams.put("position", position);
		}

		// Merge the parameters
		List<SearchParamDTO> queryParams = searchParamBuilder.buildParams(
				searchParams, inputParams);

		return queryParams;
	}

	/**
	 * Validate the input parameters passed to the search service from the UI
	 * 
	 * @param searchName
	 *            - The name of the search to be used
	 * @param inputParams
	 *            - The user input parameters
	 * @return true if the parameters are valid, else false
	 */
	private boolean validateInputParams(final String searchName,
			final Map<String, String> inputParams) {

		if (StringUtils.isEmpty(inputParams.get(SearchParamDTO.SEARCH_SEQ))) {
			LOGGER.info(" Sequence ID is not present in the Search query.");
		}

		LOGGER.info("Validating search inputs"
				+ inputParams.get(SearchParamDTO.KEYWORDS) + ","
				+ inputParams.get(SearchParamDTO.CITY_STATE) + ","
				+ inputParams.get(SearchParamDTO.RADIUS));
		
		// TODO implement searchName specific validations as appropriate
		return (StringUtils.isEmpty(inputParams.get(SearchParamDTO.KEYWORDS)))
				&& (StringUtils.isEmpty(inputParams
						.get(SearchParamDTO.CITY_STATE)))
				&& (StringUtils.isEmpty(inputParams.get(SearchParamDTO.RADIUS)));
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
		HttpSolrServer server = solrSrchHelper
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
			searchquery.setParam(mSrchParamDTO.getParameterName(),
					mSrchParamDTO.getParameterValue());
		}

		/** Adding the facets to SOLR query */
		searchquery.addFacetField(SearchFacetDTO.FACET_CITY);
		searchquery.addFacetField(SearchFacetDTO.FACET_COMPANY);
		// searchquery.addFacetField(MMJBCommonConstants.RADIUS);
		searchquery.addFacetField(SearchFacetDTO.FACET_POSTED_DATE);
		searchquery.addFacetField(SearchFacetDTO.FACET_STATE);

		LOGGER.info("Search query===>>>" + searchquery);
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

	private JobSearchResultDTO getSolrJSResult(QueryResponse response) {

		JobSearchResultDTO jobSResultDTO = new JobSearchResultDTO();
		LOGGER.info("Number of search records===>"
				+ response.getResults().getNumFound());

		jobSResultDTO.setTotalNumSearchResult(response.getResults()
				.getNumFound());

		List<JobSearchDTO> jobSearchDTOList = new ArrayList<JobSearchDTO>();

		/* Binding the JobSearchDTO.class into the QueryResponse object * */
		jobSearchDTOList = response.getBeans(JobSearchDTO.class);

		/*
		 * Copying the JobSearchDTO list to JobDTO list to separate the
		 * dependency on solr.
		 */
		List<JobDTO> jobDTOList = copyToJobDTO(jobSearchDTOList);

		final Map<String, List<SearchFacetDTO>> facetMap = new HashMap<String, List<SearchFacetDTO>>();
		final List<FacetField> facetFieldList = response.getFacetFields();

		/*
		 * Creating Lists of Facets(List<String>) by iterating the
		 * FacetFieldList
		 */
		for (FacetField facetField : facetFieldList) {
			List<SearchFacetDTO> searchFacetDTOList = new ArrayList<SearchFacetDTO>();
			List<Count> facetFieldValList = facetField.getValues();

			if (facetFieldValList != null) {
				for (Count countObj : facetFieldValList) {
					searchFacetDTOList.add(new SearchFacetDTO(countObj
							.getName(), countObj.getCount()));
				}
			}
			facetMap.put(facetField.getName(), searchFacetDTOList);

		}
		jobSResultDTO.setFacetMap(facetMap);
		jobSResultDTO.setJobResultList(jobDTOList);

		return jobSResultDTO;
	}

	/**
	 * This method is used to copy the content of JobSearchDTO list to JobDTO
	 * list to make the returning list independent of Solr.
	 * 
	 * @param jobSearchDTOList
	 * @return List<JobDTO>
	 */

	private List<JobDTO> copyToJobDTO(List<JobSearchDTO> jobSearchDTOList) {

		List<JobDTO> jobDTOList = new ArrayList<JobDTO>();

		for (JobSearchDTO jobSearchDTO : jobSearchDTOList) {
			JobDTO jobDTO = new JobDTO();
			jobDTO.setAdText(jobSearchDTO.getAdText());
			jobDTO.setApplyOnline(jobSearchDTO.getApplyOnline());
			jobDTO.setBlindAd(jobSearchDTO.getBlindAd());
			jobDTO.setCity(jobSearchDTO.getCity());
			jobDTO.setCompany(jobSearchDTO.getCompany());
			jobDTO.setEmail(jobSearchDTO.getEmail());
			jobDTO.setEmailDisplay(jobSearchDTO.getEmailDisplay());
			jobDTO.setFacilityName(jobSearchDTO.getFacilityName());
			jobDTO.setFeatured(jobSearchDTO.isFeatured());
			jobDTO.setInternationalJob(jobSearchDTO.isInternationalJob());
			jobDTO.setJobCount(jobSearchDTO.getJobCount());
			jobDTO.setJobGeo(jobSearchDTO.getJobGeo());
			jobDTO.setJobGeo0LatLon(jobSearchDTO.getJobGeo0LatLon());
			jobDTO.setJobGeo1LatLon(jobSearchDTO.getJobGeo1LatLon());
			jobDTO.setJobId(jobSearchDTO.getJobId());
			jobDTO.setJobNumber(jobSearchDTO.getJobNumber());
			jobDTO.setJobPosition(jobSearchDTO.getJobPosition());
			jobDTO.setJobTitle(jobSearchDTO.getJobTitle());
			jobDTO.setNationalJob(jobSearchDTO.isNationalJob());
			jobDTO.setPostCode(jobSearchDTO.getPostCode());
			jobDTO.setPostedDate(jobSearchDTO.getPostedDate());
			jobDTO.setState(jobSearchDTO.getState());
			jobDTO.setUrl(jobSearchDTO.getUrl());
			jobDTO.setUrlDisplay(jobSearchDTO.getUrlDisplay());

			jobDTOList.add(jobDTO);
		}

		return jobDTOList;

	}

	/**
	 * Finds a city from the given combination of city and state or zip code. If
	 * multiple results are found matching the result, the first result is
	 * returned.
	 * 
	 * @param cityState
	 *            String representing the city and state in the format
	 *            <city>,<state> or a zip code
	 * @return The Location representing the input parameter. Returns null if no
	 *         match is found
	 * @throws JobBoardServiceException
	 *             TODO Ideally be part of a location service
	 */
	private LocationDTO getLocationFromCityStateOrZipCode(String cityState)
			throws JobBoardServiceException {
		List<LocationDTO> locations = null;
		if (MMUtils.isIntNumber(cityState)) {
			try {
				locations = locationDAO.getLocationByPostcode(cityState);
			} catch (JobBoardDataException e) {
				throw new JobBoardServiceException(
						"Error while fetching the postcode details..." + e);
			}

		} else {

			String[] tokens = cityState.split(MMJBCommonConstants.COMMA);
			if (tokens.length >= 2) {
				try {
					locations = locationDAO.getLocationByCityState(
							tokens[0].trim(), tokens[1].trim());
				} catch (JobBoardDataException e) {
					throw new JobBoardServiceException(
							"Error while fetching the city state details..."
									+ e);
				}
			} else {
				LOGGER.info("Please Enter City and State by provinding comma(,) in between them. ");
			}

		}

		return (locations.isEmpty()) ? null : locations.get(0);

	}

}
