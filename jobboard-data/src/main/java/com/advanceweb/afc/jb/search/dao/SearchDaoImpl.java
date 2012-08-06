package com.advanceweb.afc.jb.search.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.MetaSearchParamDTO;
import com.advanceweb.afc.jb.common.QueryDTO;
import com.advanceweb.afc.jb.data.entities.JpLocation;
import com.advanceweb.afc.jb.data.entities.MetaSearchParam;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

/**
 * @Author : Reetesh RN
 * @Version: 1.0
 * @Created: Jul 25, 2012
 * @Purpose: This class implements all the DAO operations related to solr search
 */
@Transactional
@Repository("searchDao")
public class SearchDaoImpl implements SearchDao {

	private static final Logger LOGGER = Logger
			.getLogger("SearchDaoImpl.class");
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method is used for getting the parameter lists from the DB for creating the SOLR query.
	 * @param searchIndexName represents search index name
	 * @param environment
	 * @param searchIndexGroup represents search index group
	 * @param searchTypeName represents search type name
	 * @return QueryDTO object of QueryDTO
	 * @throws JobBoardDataException
	 */
	public QueryDTO getSearchQueryDTO(String searchIndexName,
			String environment, String searchIndexGroup, String searchTypeName) throws JobBoardDataException {
		
		QueryDTO queryDTO = new QueryDTO();
		List<MetaSearchParamDTO> srchParamDTOList = new ArrayList<MetaSearchParamDTO>();
		try {
			@SuppressWarnings("unchecked")
			List<MetaSearchParam> mSrchParamList = hibernateTemplate.find("SELECT a from  MetaSearchParam a, MetaSearchIndex b, MetaSearchType c  where "+
					" a.metaSearchIndex.searchIndexId = b.searchIndexId  and "+
					"a.metaSearchType.searchTypeId = c.searchTypeId and b.searchIndexName = '"+searchIndexName+"' and "+ 
					"b.environment = '"+environment+"' and c.searchTypeName = '"+searchTypeName+"'and b.searchIndexGroup = '"+searchIndexGroup+"' order by a.seq");
			
			for(MetaSearchParam obj: mSrchParamList){
				MetaSearchParamDTO msParamDTO = new MetaSearchParamDTO();
				msParamDTO.setParameterName(obj.getParameterName());
				msParamDTO.setParameterValue(obj.getParameterValue());
				srchParamDTOList.add(msParamDTO);
			}
			
			if(mSrchParamList != null && mSrchParamList.size() > 0){
				queryDTO.setEnvironment(mSrchParamList.get(0).getMetaSearchIndex().getEnvironment());
				queryDTO.setSearchIndexName(mSrchParamList.get(0).getMetaSearchIndex().getSearchIndexName());
				queryDTO.setSearchIndexGroup(mSrchParamList.get(0).getMetaSearchIndex().getSearchIndexGroup());
				queryDTO.setSearchName(mSrchParamList.get(0).getMetaSearchType().getSearchTypeName());
				queryDTO.setSearchHost(mSrchParamList.get(0).getMetaSearchIndex().getSearchHost());
				queryDTO.setmSrchParamList(srchParamDTOList);
			}
			
			//LOGGER.info("Search Param List from DAO===>{"+mSrchParamList+"}");

		} catch (HibernateException e) {
			LOGGER.debug(e);
			throw new JobBoardDataException("Error while fetching the SOLR parameters from the Database...");
		}
		return queryDTO;
	}
	
	
	/**
	 * This method gets the latitude and longitude from the JPLoaction table.
	 * @param String  Postcode
	 * @return List<Float> of latitude and longitude
	 */
	public List<Float> getLatitudeLongitudebyPostcode(String postcode) throws JobBoardDataException{

		List<Float> latLonList = new ArrayList<Float>();
		try {
			@SuppressWarnings("unchecked")
			List<JpLocation> joLocationList = hibernateTemplate.find(" from  JpLocation WHERE  postcode  = '"+postcode+"'");
			
			for(JpLocation locObj: joLocationList){
				
				latLonList.add(locObj.getLatitude());
				latLonList.add(locObj.getLongitude());
			}

		} catch (HibernateException e) {
			LOGGER.debug(e);
			throw new JobBoardDataException("Error while fetching the Latitude Longitude by Postcode from the Database...");
		}
		return latLonList;
		
	}
	
	/**
	 * This method gets the latitude and longitude from the JPLoaction table.
	 * @param String  city and state
	 * @return List<Float> of latitude and longitude
	 */
	public List<Float> getLatitudeLongitudeByCityState(String city, String state) throws JobBoardDataException{
		
		LOGGER.info("City=["+city+"], State=["+state+"]");
		List<Float> latLonList = new ArrayList<Float>();
		try {
			@SuppressWarnings("unchecked")
			List<JpLocation> joLocationList = hibernateTemplate.find(" from  JpLocation WHERE  city  = '"+city+"' and state = '"+state+"'");
			
			for(JpLocation locObj: joLocationList){
				
				latLonList.add(locObj.getLatitude());
				latLonList.add(locObj.getLongitude());
			}

		} catch (HibernateException e) {
			LOGGER.debug(e);
			throw new JobBoardDataException("Error while fetching the latitude and longitude By CityState from the Database...");
		}
		return latLonList;
		
	}
	

}
