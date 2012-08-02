package com.advanceweb.afc.jb.search.engine.solr;

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
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.FacetField.Count;
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
	 * This method is used to do the Solr Server Job Search based on the passed parameters.
	 * 
	 * @param searchName	represents the type of the job search 
	 * @param paramMap		contains the input parameters from the UI
	 * @param rows			represents how many rows will be displayed
	 * @param start			represents the starting point of the search
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
		
		/** Calling the DAO layer to get the QueryDTO which contains all the details
		 * of the solr search query with server details.*/
		queryDTO = searchDao.getSearchQueryDTO(
				solrParameter.getSearchIndexName(),
				solrParameter.getEnvironment(),
				solrParameter.getSearchIndexGroup(), searchName);

		/** Creation of server url to check whether it is accessible or not.*/
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

			LOGGER.info("Server URL is Accessible.");

			/** Getting the Server details from the properties file **/
			final Map<String, String> serverDetailsMap = solrSrchHelper
					.getServerDetails(solrConfiguration);

			/**Getting the SOLR query response by execution of the query.**/
			response = getSolrResponseForKeywords(serverDetailsMap,
					queryDTO, paramMap, rows, start);

			if (response == null) {

				LOGGER.info("No Results Found...");
				return null;

			} else {

				/** Calling getSolrJSResult()by passing the response object
				 * and returning the JobSearchResultDTO object into service layer.**/
				return getSolrJSResult(response);
			}

		} else {
			LOGGER.info("Server url is not correct. Please check the url.");
			return null;
		}

	

	}

	/**
	 * This method will be used to set the server details to HttpSolrServer
	 * object, query the query string and get the QueryResponse from the Solr
	 * server
	 * 
	 * @param serverDetailsMap 	represents the Map containing all the 
	 * 							values of server details from the property file 
	 *  
	 * @param paramMap		Contains all the input parameters from the UI
	 * @param start			represents the starting point of the search
	 * @param rows			represents how many rows will be displayed
	 * @return QueryResponse represents the solr query object
	 * @throws JobBoardServiceException
	 * @throws UnsupportedEncodingException
	 */
	private QueryResponse getSolrResponseForKeywords(
			final Map<String, String> serverDetailsMap, QueryDTO queryDTO,
			Map<String, String> paramMap, long rows, long start)
			throws JobBoardServiceException {
		QueryResponse response = null;
		HttpSolrServer server = null;

		server = new HttpSolrServer(queryDTO.getSearchHost()
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

		/** Creating the SOLR query from the SearchString formed by the
		* parameters got from DB **/
		SolrQuery searchquery = createSOLRQueryForKeywords(queryDTO, paramMap,
				rows, start);

		LOGGER.info("Search query===>>>" + searchquery);

		try {
			// Execution of the solr query
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
	 * @param queryDTO	Represents the QueryDTO object which contails all the details 
	 * 					of the solr query parameters taken from the DB 
	 * @param paramMap  Contains all the input parameters from the UI
	 * @param rows represents number of rows will be displayed
	 * @param start represents the starting point of the search
	 * @return object of SolrQuery
	 */

	public SolrQuery createSOLRQueryForKeywords(QueryDTO queryDTO,
			Map<String, String> paramMap, long rows, long start) {

		SolrQuery searchquery = new SolrQuery();

		/** Getting the Search parameter List from QueryDTo for iteration **/
		List<MetaSearchParamDTO> srchParamDTOList = queryDTO
				.getmSrchParamList();

		/** Iterating Search parameter List and forming the SOLR query.**/
		for (MetaSearchParamDTO mSrchParamDTO : srchParamDTOList) {

			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.DEF_TYPE)) {
				searchquery.setParam(MMJBCommonConstants.DEF_TYPE,
						mSrchParamDTO.getParameterValue());

			}
			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.QF)) {
				searchquery.setParam(MMJBCommonConstants.QF,
						mSrchParamDTO.getParameterValue());

			}
			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.PF)) {
				searchquery.setParam(MMJBCommonConstants.PF,
						mSrchParamDTO.getParameterValue());

			}
			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.PS)) {
				searchquery.setParam(MMJBCommonConstants.PS,
						mSrchParamDTO.getParameterValue());

			}
			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.MM)) {
				searchquery.setParam(MMJBCommonConstants.MM,
						mSrchParamDTO.getParameterValue());

			}
			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.BQ)) {
				searchquery.setParam(MMJBCommonConstants.BQ,
						mSrchParamDTO.getParameterValue());

			}
			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.PF)) {
				searchquery.setParam(MMJBCommonConstants.PF,
						mSrchParamDTO.getParameterValue());

			}
			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.SORT)) {
				searchquery.setParam(MMJBCommonConstants.SORT,
						mSrchParamDTO.getParameterValue());

			}
			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.Q_ALT)) {
				searchquery.setParam(MMJBCommonConstants.Q_ALT,
						mSrchParamDTO.getParameterValue());

			}
			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.Q)) {
				searchquery.setQuery(mSrchParamDTO.getParameterValue().replace(
						MMJBCommonConstants.B_01,
						paramMap.get(MMJBCommonConstants.KEYWORDS)
								+ MMJBCommonConstants.PLUS
								+ paramMap.get(MMJBCommonConstants.CITY_STATE)
								+ MMJBCommonConstants.PLUS
								+ paramMap.get(MMJBCommonConstants.RADIUS)));

			}
			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.ROWS)) {
				searchquery
						.setParam(
								MMJBCommonConstants.ROWS,
								mSrchParamDTO.getParameterValue().replace(
										MMJBCommonConstants.B_02,
										String.valueOf(rows)));

			}
			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.START)) {
				searchquery.setParam(
						MMJBCommonConstants.START,
						mSrchParamDTO.getParameterValue()
								.replace(MMJBCommonConstants.B_03,
										String.valueOf(start)));

			}
			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.SESSION_ID)) {
				searchquery.setParam(
						MMJBCommonConstants.SESSION_ID,
						mSrchParamDTO.getParameterValue().replace(
								MMJBCommonConstants.B_04,
								paramMap.get(MMJBCommonConstants.SESSION_ID)));

			}
			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.QUERY_TYPE)) {
				searchquery.setParam(
						MMJBCommonConstants.QUERY_TYPE,
						mSrchParamDTO.getParameterValue().replace(
								MMJBCommonConstants.B_05,
								paramMap.get(MMJBCommonConstants.QUERY_TYPE)));

			}
			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.SEARCH_SEQ)) {
				searchquery.setParam(
						MMJBCommonConstants.SEARCH_SEQ,
						mSrchParamDTO.getParameterValue().replace(
								MMJBCommonConstants.B_06,
								paramMap.get(MMJBCommonConstants.SEARCH_SEQ)));

			}

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
	 * This method parse the response and set the values into the bean
	 * 
	 * @param response
	 * @return JobSearchResultDTO
	 */
	private JobSearchResultDTO getSolrJSResult(QueryResponse response) {

		JobSearchResultDTO jobSResultDTO = new JobSearchResultDTO();
		LOGGER.info("Number of search records===>"
				+ response.getResults().getNumFound());

		jobSResultDTO.setTotalNumSearchResult(response.getResults()
				.getNumFound());

		List<JobSearchDTO> jobSearchDTOList = new ArrayList<JobSearchDTO>();
		jobSearchDTOList = response.getBeans(JobSearchDTO.class);

		final Map<String, List<String>> facetMap = new HashMap<String, List<String>>();
		final List<FacetField> facetFieldList = response.getFacetFields();

		for (FacetField facetField : facetFieldList) {
			List<String> facetsList = new ArrayList<String>();
			List<Count> facetFieldValList = facetField.getValues();
			
			for(Count countObj: facetFieldValList){
				
				String facetVal = countObj.getName().toString();
				long count = countObj.getCount();
				facetsList.add(facetVal.concat(" (").concat(String.valueOf(count)).concat(")"));
			}
			
			LOGGER.info("facetsList==="+facetsList);
			facetMap.put(facetField.getName(), facetsList);
			
		}
		jobSResultDTO.setFacetMap(facetMap);
		jobSResultDTO.setSearchResultList(jobSearchDTOList);

		return jobSResultDTO;
	}

}
