package com.advanceweb.afc.jb.search;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.ResBuilderCertification;
import com.advanceweb.afc.jb.data.entities.ResBuilderEdu;
import com.advanceweb.afc.jb.data.entities.ResBuilderEmployment;
import com.advanceweb.afc.jb.data.entities.ResBuilderReference;
import com.advanceweb.afc.jb.data.entities.ResBuilderResume;
import com.advanceweb.afc.jb.data.search.SaveSearchDAO;

/**
 * Method to save the searched jobs
 * 
 * @author bharatiu
 * @Version 1.0
 * @Since 10th July, 2012
 */

public class SaveSearchServiceImpl implements SaveSearchService{
	
	@Autowired
	private SaveSearchDAO saveSearchDAO;

	public void saveSearchedJobs(SaveSearchedJobsDTO saveSearchedJobsDTO) {	
		saveSearchDAO.saveSearchedJObs(saveSearchedJobsDTO);
	}
}
