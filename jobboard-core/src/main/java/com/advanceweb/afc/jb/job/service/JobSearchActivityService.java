package com.advanceweb.afc.jb.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.ApplyJobDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.job.dao.JobSearchActivityDAO;


/**
 * <code> JobSearchActivityService </code> is a implementation for Service
 * class.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */
@Service("jobSearchActivity")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JobSearchActivityService implements JobSearchActivity {

	@Autowired
	public JobSearchActivityDAO jobSearchActivityDAO;

	JobSearchActivityService() {

	}

	/**
	 * view searched job
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public SearchedJobDTO viewJobDetails(long jobId) {

		return jobSearchActivityDAO.viewJobDetails(jobId);

	}

	/**
	 * apply job
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void applyJob(ApplyJobDTO applyJobDTO) {
		jobSearchActivityDAO.applyJob(applyJobDTO);
	}

	/*@Override
	public SearchResultDTO getJobSearchResult(String searchString,
			Map<String, String> serverDetailsMap,
			Map<String, String> solrQueryDetails, String rows, String start) {

		HttpSolrServer server = null;
		QueryResponse response = null;
		SearchResultDTO searhResultDTO = new SearchResultDTO();
		JobSearchDTO jobSearchDTO = new JobSearchDTO();

		if (searchString.length() > 0) {

			server = connectToSOLRURL(serverDetailsMap);
			response = executeSearchQuery(server, searchString, solrQueryDetails, rows, start);

			System.out.println("Number of search records===>>>"
					+ response.getResults().getNumFound());

			searhResultDTO.setTotalNumSearchResult(response.getResults()
					.getNumFound());

			List<JobSearchDTO> jobSearchDTOList = new ArrayList<JobSearchDTO>();
			jobSearchDTOList = response.getBeans(JobSearchDTO.class);

			Iterator<JobSearchDTO> itr = jobSearchDTOList.iterator();

			while (itr.hasNext()) {

				jobSearchDTO = new JobSearchDTO();
				jobSearchDTO = itr.next();
				System.out.println("@Company===>>" + jobSearchDTO.getCompany());
				System.out.println("@Job Title===>>"
						+ jobSearchDTO.getJobTitle());
				System.out.println("@City===>>" + jobSearchDTO.getCity());
				System.out.println("@Posted Date===>>"
						+ jobSearchDTO.getPostedDate());
				System.out.println("@Apply Online===>>"
						+ jobSearchDTO.getApplyOnline());
				System.out
						.println("@Blind Ad===>>" + jobSearchDTO.getBlindAd());
				System.out.println("@Facility Name===>>"
						+ jobSearchDTO.getFacilityName());
				System.out.println("@Email Display===>>"
						+ jobSearchDTO.getEmailDisplay());
				System.out.println("@Email===>>" + jobSearchDTO.getEmail());
				System.out.println("@Is Inter===>>"
						+ jobSearchDTO.isInternational());
				System.out.println("@Is National===>>"
						+ jobSearchDTO.isNational());
				System.out.println("@Is Featured===>>"
						+ jobSearchDTO.isFeatured());
				System.out.println("@Job count===>>"
						+ jobSearchDTO.getJobCount());
				System.out.println("@Job id===>>" + jobSearchDTO.getJobId());
				System.out.println("@Job Number===>>"
						+ jobSearchDTO.getJobNumber());
				System.out.println("@Job Geo===>>" + jobSearchDTO.getJobGeo());
				System.out.println("@Job position===>>"
						+ jobSearchDTO.getJobPosition());
				System.out.println("@jobGeo0LatLon===>>"
						+ jobSearchDTO.getJobGeo0LatLon());
				System.out.println("@jobGeo1LatLon===>>"
						+ jobSearchDTO.getJobGeo1LatLon());
				System.out.println("@URL Display===>>"
						+ jobSearchDTO.getUrlDisplay());

			}

			Map<String, List<Count>> facetMap = new HashMap<String, List<Count>>();
			List<FacetField> facetFieldList = response.getFacetFields();

			for (FacetField facetField : facetFieldList) {

				System.out.println("@Facet Name===>>" + facetField.getName());
				facetMap.put(facetField.getName(), facetField.getValues());

				System.out.println("@Facet Values(Categories)===>>>"
						+ facetMap.get(facetField.getName()));
			}

			searhResultDTO.setFacetMap(facetMap);
			searhResultDTO.setSearchResultList(jobSearchDTOList);

			return searhResultDTO;
		} else {

			return null;
		}

	}*/

	/**
	 * 
	 */
	//@Override
	/*public HttpSolrServer connectToSOLRURL(Map<String, String> serverDetailsMap) {

		HttpSolrServer server = new HttpSolrServer(serverDetailsMap.get(
				"serverUrl").toString()
				+ serverDetailsMap.get("solrservice").toString());
		server.setSoTimeout(Integer.parseInt(serverDetailsMap.get("sotimeout")));
		server.setConnectionTimeout(Integer.parseInt(serverDetailsMap
				.get("connectiontimeout")));
		server.setDefaultMaxConnectionsPerHost(Integer
				.parseInt(serverDetailsMap.get("maxconnectionperhost")));
		server.setMaxTotalConnections(Integer.parseInt(serverDetailsMap
				.get("maxtotalconnection")));

		server.setFollowRedirects(Boolean.parseBoolean(serverDetailsMap
				.get("followredirects"))); // defaults to false
		server.setAllowCompression(Boolean.parseBoolean(serverDetailsMap
				.get("allowcompression")));
		server.setMaxRetries(Integer.parseInt(serverDetailsMap
				.get("maxretries")));
		server.setParser(new XMLResponseParser()); // binary parser is used by
													// default
		return server;
	}

	*//**
	 * 
	 *//*
	@Override
	public QueryResponse executeSearchQuery(HttpSolrServer server,
			String searchString, Map<String, String> solrQueryDetails, String rows, String start) {
		QueryResponse response = null;

		if (searchString != null && searchString.length() > 0) {

			SolrQuery searchquery = new SolrQuery();
			searchquery.setQuery(searchString);
			searchquery.setFacet(true);
			searchquery.addFacetField(solrQueryDetails.get("city"));
			searchquery.addFacetField(solrQueryDetails.get("company"));
			// searchquery.addFacetField(solrQueryDetails.get("radius"));
			searchquery.addFacetField(solrQueryDetails.get("posted_dt"));
			searchquery.addFacetField(solrQueryDetails.get("state"));
			searchquery.add("rows", rows);
			searchquery.add("start", start);
			System.out.println("searchquery===>>>" + searchquery);

			try {
				response = server.query(searchquery);
			} catch (SolrServerException e1) {
				e1.printStackTrace();
			}
		}

		return response;
	}
*/
	/**
	 * It saves the job with the details of company name,jobTitle, CreatedDate
	 * 
	 * @param searchedJobDTO
	 */
	@Override
	public void saveJob(SearchedJobDTO searchedJobDTO) {
		jobSearchActivityDAO.saveTheJob(searchedJobDTO);
	}
}
