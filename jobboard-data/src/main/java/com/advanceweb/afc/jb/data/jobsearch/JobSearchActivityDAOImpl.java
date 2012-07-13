package com.advanceweb.afc.jb.data.jobsearch;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.data.common.helpers.JobSearchActivityConversionHelper;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpSaveJob;

/**
 * <code> JobSearchActivityDAOImpl </code> is a DAO implementation class.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */
@Repository("jobSearchActivityDAO")
public class JobSearchActivityDAOImpl implements JobSearchActivityDAO {

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Autowired
	private JobSearchActivityConversionHelper jobSearchActivityConversionHelper;

	public JobSearchActivityDAOImpl() {

	}

	@Override
	public void finalize() throws Throwable {

	}

	/**
	 * implementation of viewJobDetails
	 */
	@Override
	@Transactional(readOnly = true)
	public SearchedJobDTO viewJobDetails(long jobId) {
		SearchedJobDTO jobDetail = null;

		try {
			if (jobId != 0) {
				JpJob jpJob = (JpJob) hibernateTemplate.get(JpJob.class,
						new Long(jobId).intValue());
				SearchedJobDTO searchedJobDTO = jobSearchActivityConversionHelper
						.transformJpJobToSearchedJobDTO(jpJob);
				jobDetail = searchedJobDTO;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return jobDetail;
	}

	/**
	 * implementation of apply job
	 */
	@SuppressWarnings("unused")
	@Override
	@Transactional(readOnly = true)
	public void applyJob(long jobId) {
		try {
			if (jobId != 0) {
				
				JpJob jpJob = (JpJob) hibernateTemplate.get(JpJob.class,
						new Long(jobId).intValue());

				/**
				 * save the job in DB
				 * 
				 */
				// Session session = sessionFactory.getCurrentSession();
				// session.saveOrUpdate(jpJob);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	// To Save the save searched job details to DB
	@SuppressWarnings("unused")
	@Override
	public void saveTheJob(SearchedJobDTO searchedJobDTO) {
		// Transforming the saveSearchedJobsDTO to Save Search Entity
		JpSaveJob jpSaveJob = jobSearchActivityConversionHelper
				.transformSearchedJobDTOtoJpSaveJob(searchedJobDTO);
		hibernateTemplate.saveOrUpdate(jpSaveJob);
	}

}
