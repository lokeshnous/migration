package com.advanceweb.afc.jb.search.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.data.entities.JpLocation;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;

/**
 * @Author : Reetesh RN
 * @Version: 1.0
 * @Created: 06th Aug, 2012
 * @Purpose: This class implements all the DAO operations related to solr location search
 */
@Transactional
@Repository("locationDAO")
public class LocationDAOImpl implements LocationDAO{
	
	private static final Logger LOGGER = Logger
			.getLogger("LocationDAOImpl.class");
	
	
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	
	/**
	 * This method gets the latitude and longitude from the JPLoaction table.
	 * @param String  Postcode
	 * @return List<LocationDTO> of latitude and longitude
	 */
	public List<LocationDTO> getLocationByPostcode(String postcode) throws JobBoardDataException{

		List<LocationDTO> latLonList = new ArrayList<LocationDTO>();
		try {
			@SuppressWarnings("unchecked")
			List<JpLocation> joLocationList = hibernateTemplate.find(" from  JpLocation WHERE  postcode  = '"+postcode+"'");
			
			if(joLocationList != null){
				for(JpLocation locObj: joLocationList){
					LocationDTO locDTO = new LocationDTO();
					locDTO.setLatitude(locObj.getLatitude());
					locDTO.setLongitude(locObj.getLongitude());
					latLonList.add(locDTO);
				}
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
	 * @return List<LocationDTO> of latitude and longitude
	 */
	public List<LocationDTO> getLocationByCityState(String city, String state) throws JobBoardDataException{
		
		LOGGER.info("City=["+city+"], State=["+state+"]");
		List<LocationDTO> latLonList = new ArrayList<LocationDTO>();
		try {
			@SuppressWarnings("unchecked")
			List<JpLocation> jpLocationList = hibernateTemplate.find(" from  JpLocation WHERE  city  = '"+city+"' and state = '"+state+"'");
			
			if(jpLocationList != null){
				for(JpLocation locObj: jpLocationList){
					LocationDTO locDTO = new LocationDTO();
					locDTO.setLatitude(locObj.getLatitude());
					locDTO.setLongitude(locObj.getLongitude());
					latLonList.add(locDTO);
				}
			}

		} catch (HibernateException e) {
			LOGGER.debug(e);
			throw new JobBoardDataException("Error while fetching the latitude and longitude By CityState from the Database...");
		}
		return latLonList;
		
	}
	
	
	/**
	 * This method gets the Postcode  from the JPLocation table.
	 * @param String  city and state
	 * @return List<LocationDTO> of postcode
	 */
	
	public List<LocationDTO> getPostcodeLocationByKeyword(String keywords){
		
		LOGGER.info("The value of passes keyword is "+keywords);
		List<LocationDTO> locationList = new ArrayList<LocationDTO>();
		
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("select distinct jloc.postcode from  JpLocation jloc WHERE  jloc.postcode like '"+keywords+"%' ORDER BY  jloc.postcode ASC");
		//query.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<JpLocation> jpLocationList = query.list();
		
		if(jpLocationList != null){
			Iterator<?> itr = jpLocationList.iterator();
  			while(itr.hasNext()){
  				String locObj = (String) itr.next();
  				LocationDTO locDTO = new LocationDTO();
				locDTO.setPostcode(locObj);
				locationList.add(locDTO);
  				
  			}
			
		}
		LOGGER.info("Location List size after Post code search is "+locationList.size());	
		return locationList;
	}
	
	/**
	 * This method gets the City and State from the JPLocation table.
	 * @param String postcode
	 * @return List<LocationDTO> of city and state
	 */
	
	public List<LocationDTO> getCityStateLocationByKeyword(String keywords){
		
		LOGGER.info("The value of passes keyword is "+keywords);
		List<LocationDTO> locationList = new ArrayList<LocationDTO>();
		
		@SuppressWarnings("unchecked")
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("select distinct jloc.city, jloc.state from  JpLocation jloc WHERE  jloc.city like '"+keywords+"%' ORDER BY  jloc.city, jloc.state  ASC");
		//query.setMaxResults(10);
		List<JpLocation> jpLocationList = query.list();
		
  		if(jpLocationList != null){
  			Iterator<?> itr = jpLocationList.iterator();
  			while(itr.hasNext()){
  				Object[] locObj = (Object[])itr.next();
  				LocationDTO locDTO = new LocationDTO();
				locDTO.setCity(String.valueOf(locObj[0]));
				locDTO.setState(String.valueOf(locObj[1]));
				locationList.add(locDTO);
  				
  			}
  			
		}
  		
  		LOGGER.info("Location List size after city state search is "+locationList.size());
  		return locationList;
		
	}
	
	
	
	
	
	
	
	

}
