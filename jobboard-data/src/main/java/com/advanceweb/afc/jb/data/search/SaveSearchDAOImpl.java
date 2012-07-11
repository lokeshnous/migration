package com.advanceweb.afc.jb.data.search;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.common.helpers.SaveSearchConversionHelper;
import com.advanceweb.afc.jb.data.entities.JpSaveSearch;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */
@SuppressWarnings("unchecked")
@Transactional
public class SaveSearchDAOImpl implements SaveSearchDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SaveSearchConversionHelper saveSearchConversionHelper;

	// To Save the save searched job details to DB
	public void saveSearchedJObs(SaveSearchedJobsDTO saveSearchedJobsDTO) {
		//Transforming the saveSearchedJobsDTO to Save Search Entity
		JpSaveSearch jpSaveSearch = saveSearchConversionHelper.transformSaveSearch(saveSearchedJobsDTO);		
		sessionFactory.getCurrentSession().saveOrUpdate(jpSaveSearch);
	}

}
