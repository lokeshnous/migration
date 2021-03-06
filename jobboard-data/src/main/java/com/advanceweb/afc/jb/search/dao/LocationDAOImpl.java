/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
 * @Purpose: This class implements all the DAO operations related to solr
 *           location search
 */
@Transactional
@Repository("locationDAO")
public class LocationDAOImpl implements LocationDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(LocationDAOImpl.class);

	/** The hibernate template. */
	private HibernateTemplate hibernateTemplate;

	/**
	 * Sets the hibernate template.
	 *
	 * @param sessionFactory the new hibernate template
	 */
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method gets the latitude and longitude from the JPLoaction table.
	 * 
	 * @param String
	 *            Postcode
	 * @return List<LocationDTO> of latitude and longitude
	 */
	public List<LocationDTO> getLocationByPostcode(String postcode)
			throws JobBoardDataException {

		List<LocationDTO> latLonList = new ArrayList<LocationDTO>();
		try {
			@SuppressWarnings("unchecked")
			List<JpLocation> joLocationList = hibernateTemplate
					.find(" from  JpLocation WHERE  postcode  = '" + postcode
							+ "'");

			if (joLocationList != null) {
				for (JpLocation locObj : joLocationList) {
					LocationDTO locDTO = new LocationDTO();
					locDTO.setLatitude(locObj.getLatitude());
					locDTO.setLongitude(locObj.getLongitude());
					latLonList.add(locDTO);
				}
			}

		} catch (HibernateException e) {
			LOGGER.debug(e);
			throw new JobBoardDataException(
					"Error while fetching the Latitude Longitude by Postcode from the Database..."
							+ e);
		}
		return latLonList;

	}

	/**
	 * This method gets the latitude and longitude from the JPLoaction table.
	 * 
	 * @param String
	 *            city and state
	 * @return List<LocationDTO> of latitude and longitude
	 */
	public List<LocationDTO> getLocationByCityState(String city, String state)
			throws JobBoardDataException {

		LOGGER.debug("City=[" + city + "], State=[" + state + "]");
		List<LocationDTO> latLonList = new ArrayList<LocationDTO>();
		try {
			@SuppressWarnings("unchecked")
			List<JpLocation> jpLocationList = hibernateTemplate
					.find(" from  JpLocation WHERE  city  = '" + city
							+ "' and state = '" + state + "'");

			if (jpLocationList != null) {
				for (JpLocation locObj : jpLocationList) {
					LocationDTO locDTO = new LocationDTO();
					locDTO.setCity(locObj.getCity());
					locDTO.setState(locObj.getState());
					locDTO.setCountry(locObj.getCountry());
					locDTO.setLatitude(locObj.getLatitude());
					locDTO.setLongitude(locObj.getLongitude());
					latLonList.add(locDTO);
				}
			}

		} catch (HibernateException e) {
			LOGGER.error(e);
			throw new JobBoardDataException(
					"Error while fetching the latitude and longitude By CityState from the Database..."
							+ e);
		}
		return latLonList;

	}

	/**
	 * This method gets the Postcode from the JPLocation table.
	 * 
	 * @param String
	 *            city and state
	 * @return List<LocationDTO> of postcode
	 */

	public List<LocationDTO> getPostcodeLocationByKeyword(String keywords) {

		LOGGER.debug("The value of passes keyword is " + keywords);
		List<LocationDTO> locationList = new ArrayList<LocationDTO>();

		Query query = hibernateTemplate
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"select distinct jloc.postcode from  JpLocation jloc WHERE  jloc.postcode like '"
								+ keywords + "%' ORDER BY  jloc.postcode ASC");
		// query.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<JpLocation> jpLocationList = query.list();

		if (jpLocationList != null) {
			Iterator<?> itr = jpLocationList.iterator();
			while (itr.hasNext()) {
				String locObj = (String) itr.next();
				LocationDTO locDTO = new LocationDTO();
				locDTO.setPostcode(locObj);
				locationList.add(locDTO);

			}

		}
		LOGGER.debug("Location List size after Post code search is "
				+ locationList.size());
		return locationList;
	}

	/**
	 * This method gets the City and State from the JPLocation table.
	 * 
	 * @param String
	 *            postcode
	 * @return List<LocationDTO> of city and state
	 */

	public List<LocationDTO> getCityStateLocationByKeyword(String keywords) {

		LOGGER.debug("The value of passes keyword is " + keywords);
		List<LocationDTO> locationList = new ArrayList<LocationDTO>();

		@SuppressWarnings("unchecked")
		Query query = hibernateTemplate
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"select distinct jloc.city, jloc.state from  JpLocation jloc WHERE  jloc.city like '"
								+ keywords
								+ "%' ORDER BY  jloc.city, jloc.state  ASC");
		// query.setMaxResults(10);
		List<JpLocation> jpLocationList = query.list();

		if (jpLocationList != null) {
			Iterator<?> itr = jpLocationList.iterator();
			while (itr.hasNext()) {
				Object[] locObj = (Object[]) itr.next();
				LocationDTO locDTO = new LocationDTO();
				locDTO.setCity(String.valueOf(locObj[0]));
				locDTO.setState(String.valueOf(locObj[1]));
				locationList.add(locDTO);

			}

		}

		LOGGER.debug("Location List size after city state search is "
				+ locationList.size());
		return locationList;

	}
	
	/**
	 * This method gets the City and State from the JPLocation table.
	 * 
	 * @param String
	 *            postcode
	 * @return List<LocationDTO> of city and state
	 */

	public List<LocationDTO> getCityAndStateLocationByKeyword(String keywords) {

		LOGGER.debug("The value of passes keyword is " + keywords);
		String[] data = keywords.split(",");
		List<LocationDTO> locationList = new ArrayList<LocationDTO>();

		@SuppressWarnings("unchecked")
		Query query = hibernateTemplate
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"select distinct jloc.city, jloc.state from  JpLocation jloc WHERE  replace(jloc.city, ' ', '') like '"
								+ data[0]
								+ "%' AND jloc.state like '"+data[1]+"' ORDER BY  jloc.city, jloc.state  ASC");
		// query.setMaxResults(10);
		List<JpLocation> jpLocationList = query.list();

		if (jpLocationList != null) {
			Iterator<?> itr = jpLocationList.iterator();
			while (itr.hasNext()) {
				Object[] locObj = (Object[]) itr.next();
				LocationDTO locDTO = new LocationDTO();
				locDTO.setCity(String.valueOf(locObj[0]));
				locDTO.setState(String.valueOf(locObj[1]));
				locationList.add(locDTO);

			}

		}

		LOGGER.debug("Location List size after city state search is "
				+ locationList.size());
		return locationList;

	}

	/**
	 * This method gets the State full name by short name in JPLocation table.
	 * 
	 * @param String
	 *            stateShortForm
	 * @return
	 */
	@Override
	public String getStateFullName(String stateShortForm) {
		LOGGER.debug("The short form of state is :" + stateShortForm);
		String stateFullName = null;

		Query query = hibernateTemplate
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"select jloc from  JpLocation jloc WHERE  jloc.state = '"
								+ stateShortForm + "'");
		query.setMaxResults(1);

		List<JpLocation> jpLocationList = query.list();

		if (jpLocationList != null && !jpLocationList.isEmpty()) {
			JpLocation jpLocation = jpLocationList.get(0);
			stateFullName = jpLocation.getStateFullname();
		}

		LOGGER.debug(" The state full name is :" + stateFullName);
		return stateFullName;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.search.dao.LocationDAO#findAll()
	 */
	@Override
	public List<LocationDTO> findAll() {
		List<JpLocation> entityList = hibernateTemplate
				.loadAll(JpLocation.class);
		List<LocationDTO> dtoList = new ArrayList<LocationDTO>();
		for (JpLocation entity : entityList) {
			dtoList.add(convertLocationEntity(entity));
		}
		return dtoList;
	}

	/**
	 * Convert location entity.
	 *
	 * @param entity the entity
	 * @return the location dto
	 */
	private LocationDTO convertLocationEntity(JpLocation entity) {
		LocationDTO dto = new LocationDTO();
		dto.setArea(entity.getArea());
		dto.setId(entity.getLocationId());
		dto.setCity(entity.getCity());
		dto.setCityAlias(entity.getCityAlias());
		dto.setCountry(entity.getCountry());
		dto.setLatitude(entity.getLatitude());
		dto.setLongitude(entity.getLongitude());
		dto.setPostcode(entity.getPostcode());
		dto.setState(entity.getState());
		dto.setStateFullName(entity.getStateFullname());
		return dto;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.search.dao.LocationDAO#validateCityStateZip(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean validateCityStateZip(String city,String state,String zipCode) throws JobBoardDataException {
		boolean valied=false;
		try {
			
			Object[] inputs = { city,state,zipCode};
			List<JpLocation> jpLocationList = hibernateTemplate
					.find("from JpLocation loc where loc.city=? and loc.state=? and  loc.postcode=?",
							inputs);
			if(jpLocationList.size()>0){
				valied=true;
			}
		} catch (HibernateException e) {
			LOGGER.debug(e);
			throw new JobBoardDataException(
					"Error while fetching the latitude and longitude By CityState from the Database..."
							+ e);
		}
		return valied;
	}

	
}
