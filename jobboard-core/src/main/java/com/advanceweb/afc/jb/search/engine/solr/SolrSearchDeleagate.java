package com.advanceweb.afc.jb.search.engine.solr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
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

import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.JobSearchResultDTO;
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.SearchFacetDTO;
import com.advanceweb.afc.jb.common.SearchParamDTO;
import com.advanceweb.afc.jb.common.QueryDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.common.util.SolrParameter;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
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
public class SolrSearchDeleagate implements JobSearchDeleagate {

	private static final Logger LOGGER = Logger
			.getLogger("JobSearchDeleagateImpl.class");

	@Autowired
	private SOLRSearchHelper solrSrchHelper;

	@Autowired
	@Resource(name = "solrConfiguration")
	private Properties solrConfiguration;

	@Autowired
	private SearchDAO searchDAO;

	@Autowired
	private LocationDAO locationDAO;

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
	 * @throws UnsupportedEncodingException
	 */

	@Override
	public JobSearchResultDTO jobSearch(final String searchName,
			final Map<String, String> paramMap, final long start,
			final long rows) throws JobBoardServiceException {

		QueryResponse response = null;
		QueryDTO queryDTO = null;

		
		if (StringUtils.isEmpty(paramMap.get(MMJBCommonConstants.SEARCH_SEQ))) {
			LOGGER.info(" Sequence ID is not present in the Search query.");
		}

		/**
		 * It is checking whether all the parameters coming from the UI is blank
		 * or not.
		 */
		if ((StringUtils.isEmpty(paramMap.get(MMJBCommonConstants.KEYWORDS)))
				&& (StringUtils.isEmpty(paramMap
						.get(MMJBCommonConstants.CITY_STATE)))
				&& (StringUtils.isEmpty(paramMap
						.get(MMJBCommonConstants.RADIUS)))) {

			LOGGER.info("Empty Search criteria. Please enter a search criteria to search jobs.");
			return null;

		} else {

			/**
			 * Calling the DAO layer to get the QueryDTO which contains all the
			 * details of the solr search query with server details.
			 */
			try {
				queryDTO = searchDAO.getSearchQueryDTO(
						solrParameter.getSearchIndexName(),
						solrParameter.getEnvironment(),
						solrParameter.getSearchIndexGroup(), searchName);
			} catch (JobBoardDataException e) {
				throw new JobBoardServiceException("Error while fetching the SOLR parameters from the Database..."+e);
			}

			/** Creation of server url to check whether it is accessible or not. */
			String serverURlToCheck = queryDTO.getSearchHost()
					.concat(MMJBCommonConstants.SLASH)
					.concat(queryDTO.getSearchIndexGroup())
					.concat(MMJBCommonConstants.SLASH)
					.concat(queryDTO.getSearchIndexName())
					.concat(MMJBCommonConstants.SLASH)
					.concat(MMJBCommonConstants.SELECT);

			LOGGER.info("Server URL To Check is " + serverURlToCheck);

			/** Checking whether server url is accessible. */
			boolean serverAccessible = solrSrchHelper
					.isServerAccessible(serverURlToCheck);

			if (serverAccessible) {

				/** Getting the Server details from the properties file **/
				final Map<String, String> serverDetailsMap = solrSrchHelper
						.getServerDetails(solrConfiguration);

				/** Getting the SOLR query response by execution of the query. **/
				response = getSolrResponse(serverDetailsMap, queryDTO,
						paramMap, rows, start);

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

		SolrQuery searchquery = null;
		QueryResponse response = null;

		List<SearchParamDTO> srchParamRlpcdDTOList = null;

		/**
		 * Get the instance of the HttpSolrServer by passing the QueryDTO and
		 * values read from the properties file.
		 **/
		HttpSolrServer server = getSolrServerInstance(queryDTO,
				serverDetailsMap);

		if (MMJBCommonConstants.LOCATION.equalsIgnoreCase(paramMap
				.get(MMJBCommonConstants.SEARCH_NAME))) {

			srchParamRlpcdDTOList = createParamsForLocationSearch(queryDTO,
					paramMap, rows, start);

		} else {

			/**
			 * Creating and getting the search parameter list from the QueryDTO
			 * got from the DB and then creating the Search Parameter list by
			 * the replacing the parameter values.
			 **/
			srchParamRlpcdDTOList = createParamsForKeywordSearch(queryDTO,
					paramMap, rows, start);

		}

		/**
		 * Creating the SOLR query by passing the replaced searched parameter
		 * list.
		 * **/
		searchquery = creatSOLRQuery(srchParamRlpcdDTOList);

		LOGGER.info("Search query===>>>" + searchquery);

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
	 * This method creates the search SOLR query.
	 * 
	 * @param queryDTO
	 *            Represents the QueryDTO object which contains all the details
	 *            of the solr query parameters taken from the DB
	 * @param paramMap
	 *            Contains all the input parameters from the UI
	 * @param rows
	 *            represents number of rows will be displayed
	 * @param start
	 *            represents the starting point of the search
	 * @return object of SolrQuery
	 */

	private List<SearchParamDTO> createParamsForKeywordSearch(
			QueryDTO queryDTO, Map<String, String> paramMap, long rows,
			long start) {

		List<SearchParamDTO> srchReplacedParamDTOList = new ArrayList<SearchParamDTO>();

		/** Getting the Search parameter List from QueryDTO. **/
		List<SearchParamDTO> srchParamDTOList = queryDTO.getmSrchParamList();

		for (SearchParamDTO mSrchParamDTO : srchParamDTOList) {

			int value = MMJBCommonConstants.ZERO_INT;
			String temp = MMJBCommonConstants.EMPTY;
			if (mSrchParamDTO.getParameterValue().contains(
					MMJBCommonConstants.B)) {
				temp = mSrchParamDTO.getParameterValue().substring(
						mSrchParamDTO.getParameterValue().indexOf(
								MMJBCommonConstants.B)
								+ MMJBCommonConstants.B.length(),
						mSrchParamDTO.getParameterValue().length());

				if (MMUtils.isIntNumber(temp)) {
					value = Integer.parseInt(temp);

					switch (value) {
					case 1:
						mSrchParamDTO.setParameterValue(paramMap
								.get(MMJBCommonConstants.KEYWORDS));
						break;
					case 2:
						mSrchParamDTO.setParameterValue(String.valueOf(rows));
						break;
					case 3:
						mSrchParamDTO.setParameterValue(String.valueOf(start));
						break;
					case 4:
						mSrchParamDTO.setParameterValue(paramMap
								.get(MMJBCommonConstants.SESSION_ID));
						break;
					case 5:
						mSrchParamDTO.setParameterValue(paramMap
								.get(MMJBCommonConstants.SEARCH_NAME));
						break;
					case 6:
						mSrchParamDTO.setParameterValue(paramMap
								.get(MMJBCommonConstants.SEARCH_SEQ));
						break;
					default:
						LOGGER.debug("No Matching found for param value from Search parameters got from DB.");
						break;
					}

				}

			}
			/**
			 * Adding the SearchParamDTO to the ReplacedSearchParamDTO list to
			 * return it back.
			 **/
			srchReplacedParamDTOList.add(mSrchParamDTO);

		}

		return srchReplacedParamDTOList;

	}

	/**
	 * This method creates the search SOLR query.
	 * 
	 * @param queryDTO
	 *            Represents the QueryDTO object which contains all the details
	 *            of the solr query parameters taken from the DB
	 * @param paramMap
	 *            Contains all the input parameters from the UI
	 * @param rows
	 *            represents number of rows will be displayed
	 * @param start
	 *            represents the starting point of the search
	 * @return object of SolrQuery
	 * @throws JobBoardServiceException 
	 
	 */

	private List<SearchParamDTO> createParamsForLocationSearch(
			QueryDTO queryDTO, Map<String, String> paramMap, long rows,
			long start) throws JobBoardServiceException {

		List<SearchParamDTO> srchReplacedParamDTOList = new ArrayList<SearchParamDTO>();

		List<LocationDTO> latLonList = null;
		if (MMUtils.isIntNumber(paramMap.get(MMJBCommonConstants.CITY_STATE))) {

			try {
				latLonList = locationDAO.getLocationByPostcode(paramMap
						.get(MMJBCommonConstants.CITY_STATE));
			} catch (JobBoardDataException e) {
				throw new JobBoardServiceException("Error while fetching the postcode details..."+e);
			}

		} else {

			String[] cityState = paramMap.get(MMJBCommonConstants.CITY_STATE)
					.trim().split(MMJBCommonConstants.COMMA);
			if (cityState.length >= 2) {
				try {
					latLonList = locationDAO.getLocationByCityState(
							cityState[0].trim(), cityState[1].trim());
				} catch (JobBoardDataException e) {
					throw new JobBoardServiceException("Error while fetching the city state details..."+e);
				}
			} else {
				LOGGER.info("Please Enter City and State by provinding comma(,) in between them. ");
			}

		}

		if (latLonList == null || latLonList.isEmpty()) {

			LOGGER.info("Latitude and Longitude not found in the DB for the provided City State..");

		} else {

			/** Getting the Search parameter List from QueryDTO. **/
			List<SearchParamDTO> srchParamDTOList = queryDTO
					.getmSrchParamList();

			for (SearchParamDTO mSrchParamDTO : srchParamDTOList) {

				int value = MMJBCommonConstants.ZERO_INT;
				String strValue = mSrchParamDTO.getParameterValue();
				String tStrValue = MMJBCommonConstants.EMPTY;

				if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
						MMJBCommonConstants.FQ)) {

					/**
					 * Calling the method to replace the :b01 and :b02 value for
					 * fq filed
					 **/
					strValue = formAndRepalceFQParam(strValue, latLonList,
							paramMap);
					mSrchParamDTO.setParameterValue(strValue);

				} else {

					if (mSrchParamDTO.getParameterValue().contains(
							MMJBCommonConstants.B)) {
						tStrValue = mSrchParamDTO
								.getParameterValue()
								.substring(
										mSrchParamDTO.getParameterValue()
												.indexOf(MMJBCommonConstants.B)
												+ MMJBCommonConstants.B
														.length(),
										mSrchParamDTO.getParameterValue()
												.length());

						if (MMUtils.isIntNumber(tStrValue)) {
							value = Integer.parseInt(tStrValue);

							switch (value) {

							case 3:
								mSrchParamDTO.setParameterValue(paramMap
										.get(MMJBCommonConstants.KEYWORDS));
								break;
							case 4:
								mSrchParamDTO.setParameterValue(String
										.valueOf(rows));
								break;
							case 5:
								mSrchParamDTO.setParameterValue(String
										.valueOf(start));
								break;
							case 6:
								mSrchParamDTO.setParameterValue(paramMap
										.get(MMJBCommonConstants.SESSION_ID));
								break;
							case 7:
								mSrchParamDTO.setParameterValue(paramMap
										.get(MMJBCommonConstants.SEARCH_NAME));
								break;
							case 8:
								mSrchParamDTO.setParameterValue(paramMap
										.get(MMJBCommonConstants.SEARCH_SEQ));
								break;
							default:
								LOGGER.debug("No Matching found for param value from Search parameters got from DB.");
								break;
							}

						}

					}
				}

				/**
				 * Adding the SearchParamDTO to the ReplacedSearchParamDTO list
				 * to return it back.
				 **/

				srchReplacedParamDTOList.add(mSrchParamDTO);

			}
		}

		return srchReplacedParamDTOList;

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
		
		

		/** Binding the JobSearchDTO.class into the QueryResponse object **/
		jobSearchDTOList = response.getBeans(JobSearchDTO.class);
		
		/** Copying the JobSearchDTO list to JobDTO list to separate the dependency on solr.**/
		List<JobDTO> jobDTOList = copyToJobDTO(jobSearchDTOList);

		final Map<String, List<SearchFacetDTO>> facetMap = new HashMap<String, List<SearchFacetDTO>>();
		final List<FacetField> facetFieldList = response.getFacetFields();

		/**
		 * Creating Lists of Facets(List<String>) by iterating the
		 * FacetFieldList
		 **/
		for (FacetField facetField : facetFieldList) {
			List<SearchFacetDTO> searchFacetDTOList = new ArrayList<SearchFacetDTO>();
			List<Count> facetFieldValList = facetField.getValues();

			if(facetFieldValList != null){
				for (Count countObj : facetFieldValList) {
					SearchFacetDTO searchFacetDTO = new SearchFacetDTO();
					String facetValue = countObj.getName().toString();
					searchFacetDTO.setFacetValue(facetValue);
					
					long count = countObj.getCount();
					searchFacetDTO.setCount(count);
					
					searchFacetDTOList.add(searchFacetDTO);
				}
			}
			facetMap.put(facetField.getName(), searchFacetDTOList);

		}
		jobSResultDTO.setFacetMap(facetMap);
		jobSResultDTO.setJobResultList(jobDTOList);

		return jobSResultDTO;
	}
	
	
	/**
	 * This method creates a HttpSolrServer instance by setting all the required
	 * server parameters.
	 * 
	 * @param queryDTO
	 *            represents all the SOLR server parameter values from the DB.
	 * @param serverDetailsMap
	 *            contains all the server details being red from the property
	 *            file.
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
	 * This method parse the passed string and replace the :b01 and :b02
	 * occurrence with Lat long value and radius.
	 * 
	 * @param strValue
	 * @param latLonList
	 * @param paramMap
	 * @return String which contains the replaced value for FQ parameter
	 */

	private String formAndRepalceFQParam(String stringValue,
			List<LocationDTO> latLonList, Map<String, String> paramMap) {
		
		String strValue = stringValue;
		/** Checking for how many occurrence are there for :b in the string **/
		for (int i = 0; i <= StringUtils.countMatches(strValue,
				MMJBCommonConstants.B); i++) {
			/** Checking if :b is present or not **/
			if (strValue.contains(MMJBCommonConstants.B)) {
				/** Getting the next string after :b **/
				String tStrValue = strValue.substring(
						strValue.indexOf(MMJBCommonConstants.B)
								+ MMJBCommonConstants.B.length(),
						strValue.length());
				String valStr = tStrValue.split(MMJBCommonConstants.SPACE)[0];
				if (valStr.contains(MMJBCommonConstants.CLSD_BRACES)) {
					valStr = valStr
							.replace(MMJBCommonConstants.CLSD_BRACES, "");
				}
				if (MMUtils.isIntNumber(valStr)) {
					int value = Integer.parseInt(valStr);
					switch (value) {
					case 1:
						strValue = strValue.replace(MMJBCommonConstants.B_01,
								latLonList.get(0).getLatitude() + ","
										+ latLonList.get(0).getLongitude());
						break;
					case 2:
						strValue = strValue.replace(MMJBCommonConstants.B_02,
								paramMap.get(MMJBCommonConstants.RADIUS));
						break;
					default:
						LOGGER.debug("No Matching found for param value from Search parameters got from DB.");
						break;

					}

				}

			}

		}
		return strValue;

	}
	
	/**
	 * This method is used to copy the content of JobSearchDTO list 
	 * to JobDTO list to make the returning list independent of Solr.
	 * @param jobSearchDTOList
	 * @return List<JobDTO>
	 */
	
	private List<JobDTO> copyToJobDTO(List<JobSearchDTO> jobSearchDTOList ){
		
		List<JobDTO> jobDTOList = new ArrayList<JobDTO>();
		
		for(JobSearchDTO jobSearchDTO: jobSearchDTOList){
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
	

}
