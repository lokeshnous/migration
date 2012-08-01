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
	 * Does the Solr Server Job Search
	 * 
	 * @param searchName
	 * @param paramMap
	 * @param rows
	 * @param start
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
		JobSearchResultDTO jSResultDTO = null;
		SolrJobSearchResultDTO solrJSResultDTO = null;
		QueryDTO queryDTO = null;

		// Should return a QueryDTO
		queryDTO = searchDao.getSearchQueryDTO(
				solrParameter.getSearchIndexName(),
				solrParameter.getEnvironment(),
				solrParameter.getSearchIndexGroup(),
				solrParameter.getSearchName());

		String serverURlToCheck = createServerUrl(queryDTO);

		LOGGER.info("serverURlToCheck===>" + serverURlToCheck);

		// Checking whether server url is accessible
		boolean serverAccessible = solrSrchHelper
				.isServerAccessible(serverURlToCheck.concat(
						MMJBCommonConstants.USER).concat(
						MMJBCommonConstants.SLASH));
		
		if (serverAccessible) {

			//Getting the Search String with All the parameters
			String srchStrWithParam = formSearchQuery(queryDTO,
					paramMap.get(MMJBCommonConstants.KEYWORDS),
					paramMap.get(MMJBCommonConstants.CITY_STATE),
					paramMap.get(MMJBCommonConstants.RADIUS), rows, start,
					paramMap.get(MMJBCommonConstants.SESSION_ID),
					paramMap.get(MMJBCommonConstants.QUERY_TYPE),
					paramMap.get(MMJBCommonConstants.SEARCH_SEQ));

			LOGGER.info("srchStrWithParam===>>" + srchStrWithParam);

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

				//Getting the Server details from the properties file
				final Map<String, String> serverDetailsMap = solrSrchHelper
						.getServerDetails(solrConfiguration);
				
				//Getting the SOLR query response by execution of the query
				response = getSolrResponse(serverDetailsMap, srchStrWithParam);

				if (response == null) {

					LOGGER.info("No Results Found...");
					return null;

				} else {
					
					solrJSResultDTO = getSolrJSResult(response);
					jSResultDTO = new JobSearchResultDTO();
					jSResultDTO.setSolrJobSearchResultDTO(solrJSResultDTO);

					return jSResultDTO;
				}

			}

		} else {
			LOGGER.info("Server url is not correct. Please check the url.");
			return null;
		}

	}

	/**
	 * This methos forms the SOLR search query from the
	 * parameter list got from the DB
	 * @param queryDTO
	 * @param keywords
	 * @param cityState
	 * @param radius
	 * @param rows
	 * @param start
	 * @param sessionId
	 * @param queryType
	 * @param searchSeq
	 * @return String
	 */
	

	private String formSearchQuery(QueryDTO queryDTO, String keywords,
			String cityState, String radius, long rows, long start,
			String sessionId, String queryType, String searchSeq) {

		String serverUrlStr = createServerUrl(queryDTO).concat(
				MMJBCommonConstants.SELECT_SLASH_QUESTIONMARK);

		LOGGER.info("serverUrlStr===>>" + serverUrlStr);
		String urlParams = new String();
		List<MetaSearchParamDTO> srchParamDTOList = queryDTO
				.getmSrchParamList();

		for (MetaSearchParamDTO mSrchParamDTO : srchParamDTOList) {

			if (mSrchParamDTO.getParameterName().equalsIgnoreCase(
					MMJBCommonConstants.DEF_TYPE)) {
				urlParams = urlParams.concat(mSrchParamDTO.getParameterName())
						.concat(MMJBCommonConstants.EQUAL_TO)
						.concat(mSrchParamDTO.getParameterValue());
			} else {
				urlParams = urlParams.concat(MMJBCommonConstants.AMP)
						.concat(mSrchParamDTO.getParameterName())
						.concat(MMJBCommonConstants.EQUAL_TO)
						.concat(mSrchParamDTO.getParameterValue());
			}
		}

		//Replacing the place holders with the actual input parameters from UI
		urlParams = urlParams.replace(MMJBCommonConstants.B_01, keywords
				+ MMJBCommonConstants.PLUS + cityState
				+ MMJBCommonConstants.PLUS + radius);
		urlParams = urlParams.replace(MMJBCommonConstants.B_02,
				String.valueOf(rows));
		urlParams = urlParams.replace(MMJBCommonConstants.B_03,
				String.valueOf(start));
		urlParams = urlParams.replace(MMJBCommonConstants.B_04, sessionId);
		urlParams = urlParams.replace(MMJBCommonConstants.B_05, queryType);
		urlParams = urlParams.replace(MMJBCommonConstants.B_06, searchSeq);

		LOGGER.info("URLParams after replacement======>>>" + urlParams);

		return serverUrlStr.concat(urlParams);

	}

	/**
	 * This method will return a server url which will be used for checking
	 * whether it is accessible
	 * 
	 * @param queryDTO
	 * @return String serverURL
	 */

	private String createServerUrl(QueryDTO queryDTO) {

		return queryDTO.getSearchHost().concat(MMJBCommonConstants.SLASH)
				.concat(queryDTO.getSearchIndexGroup())
				.concat(MMJBCommonConstants.SLASH)
				.concat(queryDTO.getSearchIndexName())
				.concat(MMJBCommonConstants.SLASH);

	}

	/**
	 * This method will be used to set the server details to HttpSolrServer
	 * object, query the query string and get the QueryResponse from the Solr
	 * server
	 * 
	 * @param serverDetailsMap
	 * @param solrQueryDetails
	 * @param paramMap
	 * @param start
	 * @param rows
	 * @return QueryResponse
	 * @throws JobBoardServiceException
	 * @throws UnsupportedEncodingException
	 */
	private QueryResponse getSolrResponse(
			final Map<String, String> serverDetailsMap,
			final String srchStrWithParam) throws JobBoardServiceException {
		QueryResponse response = null;
		HttpSolrServer server = null;
		server = new HttpSolrServer(srchStrWithParam.substring(0,
				srchStrWithParam.indexOf(MMJBCommonConstants.SELECT)));
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

		//Creating the SOLR query from the SearchString formed by the parameters got from DB
		SolrQuery searchquery = createSOLRQuery(srchStrWithParam);

		LOGGER.info("Search query===>>>" + searchquery);

		try {
			//Execution of the solr query
			response = server.query(searchquery);

		} catch (SolrServerException e) {
			throw new JobBoardServiceException(
					"Error occurred while trying to Execute the SOLR query...");
			// return null;
		}
		return response;

	}

	/**
	 * This method creates the search SOLR query
	 * @param String srchStrWithParam
	 * @return
	 */
	
	public SolrQuery createSOLRQuery(String srchStrWithParam) {

		String qVal = "";
		SolrQuery searchquery = null;
		// Getting the &q value from the search string to put it as params to SolrQuery
		qVal = srchStrWithParam
				.substring(
						srchStrWithParam.indexOf(MMJBCommonConstants.AMP
								+ MMJBCommonConstants.Q
								+ MMJBCommonConstants.EQUAL_TO)
								+ (MMJBCommonConstants.AMP
										+ MMJBCommonConstants.Q + MMJBCommonConstants.EQUAL_TO)
										.length(),
						srchStrWithParam.indexOf(MMJBCommonConstants.AMP
								+ MMJBCommonConstants.ROWS));
		LOGGER.info("QVal===>" + qVal);

		if (qVal != null && qVal.length() > 0) {
			//Instantiating the SolrQuery
			searchquery = new SolrQuery();
			
			
			//Setting the deftype param into SolrQuery
			if (isPresent(srchStrWithParam, MMJBCommonConstants.DEF_TYPE)) {
				searchquery
						.setParam(
								MMJBCommonConstants.DEF_TYPE,
								srchStrWithParam.substring(
										srchStrWithParam
												.indexOf(MMJBCommonConstants.DEF_TYPE
														+ MMJBCommonConstants.EQUAL_TO)
												+ (MMJBCommonConstants.DEF_TYPE + MMJBCommonConstants.EQUAL_TO)
														.length(),
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.QF)));
			}

			//Setting the qf param into SolrQuery
			if (isPresent(srchStrWithParam, MMJBCommonConstants.AMP
					+ MMJBCommonConstants.QF)) {
				searchquery
						.setParam(
								MMJBCommonConstants.QF,
								srchStrWithParam.substring(
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.QF
														+ MMJBCommonConstants.EQUAL_TO)
												+ (MMJBCommonConstants.AMP
														+ MMJBCommonConstants.QF + MMJBCommonConstants.EQUAL_TO)
														.length(),
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.PF)));
			}

			//Setting the pf param into SolrQuery
			if (isPresent(srchStrWithParam, MMJBCommonConstants.AMP
					+ MMJBCommonConstants.PF)) {
				searchquery
						.setParam(
								MMJBCommonConstants.PF,
								srchStrWithParam.substring(
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.PF
														+ MMJBCommonConstants.EQUAL_TO)
												+ (MMJBCommonConstants.AMP
														+ MMJBCommonConstants.PF + MMJBCommonConstants.EQUAL_TO)
														.length(),
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.PS)));
			}

			//Setting the ps param into SolrQuery
			if (isPresent(srchStrWithParam, MMJBCommonConstants.AMP
					+ MMJBCommonConstants.PS)) {
				searchquery
						.setParam(
								MMJBCommonConstants.PS,
								srchStrWithParam.substring(
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.PS
														+ MMJBCommonConstants.EQUAL_TO)
												+ (MMJBCommonConstants.AMP
														+ MMJBCommonConstants.PS + MMJBCommonConstants.EQUAL_TO)
														.length(),
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.MM)));
			}

			//Setting the mm param into SolrQuery
			if (isPresent(srchStrWithParam, MMJBCommonConstants.AMP
					+ MMJBCommonConstants.MM)) {
				searchquery
						.setParam(
								MMJBCommonConstants.MM,
								srchStrWithParam.substring(
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.MM
														+ MMJBCommonConstants.EQUAL_TO)
												+ (MMJBCommonConstants.AMP
														+ MMJBCommonConstants.MM + MMJBCommonConstants.EQUAL_TO)
														.length(),
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.BQ)));
			}

			//Setting the bq param into SolrQuery
			if (isPresent(srchStrWithParam, MMJBCommonConstants.AMP
					+ MMJBCommonConstants.BQ)) {
				searchquery
						.setParam(
								MMJBCommonConstants.BQ,
								srchStrWithParam.substring(
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.BQ
														+ MMJBCommonConstants.EQUAL_TO)
												+ (MMJBCommonConstants.AMP
														+ MMJBCommonConstants.BQ + MMJBCommonConstants.EQUAL_TO)
														.length(),
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.BF)));
			}

			//Setting the bf param into SolrQuery
			if (isPresent(srchStrWithParam, MMJBCommonConstants.AMP
					+ MMJBCommonConstants.BF)) {
				searchquery
						.setParam(
								MMJBCommonConstants.BF,
								srchStrWithParam.substring(
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.BF
														+ MMJBCommonConstants.EQUAL_TO)
												+ (MMJBCommonConstants.AMP
														+ MMJBCommonConstants.BF + MMJBCommonConstants.EQUAL_TO)
														.length(),
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.SORT)));
			}

			//Setting the sort param into SolrQuery
			if (isPresent(srchStrWithParam, MMJBCommonConstants.AMP
					+ MMJBCommonConstants.SORT)) {
				searchquery
						.setParam(
								MMJBCommonConstants.SORT,
								srchStrWithParam.substring(
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.SORT
														+ MMJBCommonConstants.EQUAL_TO)
												+ (MMJBCommonConstants.AMP
														+ MMJBCommonConstants.SORT + MMJBCommonConstants.EQUAL_TO)
														.length(),
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.Q_ALT)));
			}
			
			//Setting the q.alt param into SolrQuery
			if (isPresent(srchStrWithParam, MMJBCommonConstants.AMP
					+ MMJBCommonConstants.Q_ALT)) {
				searchquery
						.setParam(
								MMJBCommonConstants.Q_ALT,
								srchStrWithParam.substring(
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.Q_ALT
														+ MMJBCommonConstants.EQUAL_TO)
												+ (MMJBCommonConstants.AMP
														+ MMJBCommonConstants.Q_ALT + MMJBCommonConstants.EQUAL_TO)
														.length(),
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.Q
														+ MMJBCommonConstants.EQUAL_TO)));
			}

			//Setting the 'q' param into SolrQuery
			searchquery.setQuery(qVal);
			searchquery.setFacet(true);
			//Setting the rows param into SolrQuery
			if (isPresent(srchStrWithParam, MMJBCommonConstants.AMP
					+ MMJBCommonConstants.ROWS)) {
				searchquery
						.setParam(
								MMJBCommonConstants.ROWS,
								srchStrWithParam.substring(
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.ROWS
														+ MMJBCommonConstants.EQUAL_TO)
												+ (MMJBCommonConstants.AMP
														+ MMJBCommonConstants.ROWS + MMJBCommonConstants.EQUAL_TO)
														.length(),
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.START)));
			}

			//Setting the start param into SolrQuery
			if (isPresent(srchStrWithParam, MMJBCommonConstants.AMP
					+ MMJBCommonConstants.START)) {
				searchquery
						.setParam(
								MMJBCommonConstants.START,
								srchStrWithParam.substring(
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.START
														+ MMJBCommonConstants.EQUAL_TO)
												+ (MMJBCommonConstants.AMP
														+ MMJBCommonConstants.START + MMJBCommonConstants.EQUAL_TO)
														.length(),
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.SESSION_ID)));
			}

			//Setting the sessionid param into SolrQuery
			if (isPresent(srchStrWithParam, MMJBCommonConstants.AMP
					+ MMJBCommonConstants.SESSION_ID)) {

				searchquery.setParam(MMJBCommonConstants.SESSION_ID,
						srchStrWithParam.substring(
								(srchStrWithParam.indexOf("&sessionid="))
										+ ("&sessionid=").length(),
								srchStrWithParam.indexOf("&querytype")));
			}

			//Setting the querytype param into SolrQuery
			if (isPresent(srchStrWithParam, MMJBCommonConstants.AMP
					+ MMJBCommonConstants.QUERY_TYPE)) {
				searchquery
						.setParam(
								MMJBCommonConstants.QUERY_TYPE,
								srchStrWithParam.substring(
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.QUERY_TYPE
														+ MMJBCommonConstants.EQUAL_TO)
												+ (MMJBCommonConstants.AMP
														+ MMJBCommonConstants.QUERY_TYPE + MMJBCommonConstants.EQUAL_TO)
														.length(),
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.SEARCH_SEQ)));
			}

			//Setting the search_seq param into SolrQuery
			if (isPresent(srchStrWithParam, MMJBCommonConstants.AMP
					+ MMJBCommonConstants.SEARCH_SEQ)) {
				searchquery
						.setParam(
								MMJBCommonConstants.SEARCH_SEQ,
								srchStrWithParam.substring(
										srchStrWithParam
												.indexOf(MMJBCommonConstants.AMP
														+ MMJBCommonConstants.SEARCH_SEQ
														+ MMJBCommonConstants.EQUAL_TO)
												+ (MMJBCommonConstants.AMP
														+ MMJBCommonConstants.SEARCH_SEQ + MMJBCommonConstants.EQUAL_TO)
														.length(),
										srchStrWithParam.length()));
			}

			//Adding the facets to SOLR query
			searchquery.addFacetField(MMJBCommonConstants.CITY);
			searchquery.addFacetField(MMJBCommonConstants.COMPANY);
			// searchquery.addFacetField(MMJBCommonConstants.RADIUS);
			searchquery.addFacetField(MMJBCommonConstants.POSTED_DT);
			searchquery.addFacetField(MMJBCommonConstants.STATE);

		}

		return searchquery;
	}

	/**
	 * This method checks whether the value presents in the search string or not
	 * @param srchStrWithParam
	 * @param name
	 * @return boolean
	 */
	private boolean isPresent(String srchStrWithParam, String name) {

		if (srchStrWithParam.indexOf(name) > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * This method parse the response and set the values into the bean
	 * @param response
	 * @returnSolrJobSearchResultDTO
	 */
	private SolrJobSearchResultDTO getSolrJSResult(QueryResponse response){
		
		
		SolrJobSearchResultDTO solrJSResultDTO = new SolrJobSearchResultDTO();
		LOGGER.info("Number of search records===>>>"+ response.getResults().getNumFound());

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
