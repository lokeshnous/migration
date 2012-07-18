package com.advanceweb.afc.jb.employer.dao;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.EmployerBrandingTemplatesDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.data.entities.MerJpBrandingTemp;
import com.advanceweb.afc.jb.employer.helper.EmployerBrandingTemplateConversionHelper;

/**
 * <code>EmployerBrandingTemplatesDAOImpl</code>This controller belongs to all
 * searched jobs details.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
@Repository("employerBrandingTemplatesDAO")
public class EmployerBrandingTemplatesDAOImpl implements EmployerBrandingTemplatesDAO {

	private HibernateTemplate hibernateTemplateTracker;
	
	@Autowired
	private EmployerBrandingTemplateConversionHelper employerBrandingTemplateConversionHelper;

	private static final Logger LOGGER = Logger
			.getLogger(EmployerBrandingTemplatesDAOImpl.class);

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker) {
		this.hibernateTemplateTracker = new HibernateTemplate(sessionFactoryMerionTracker);
	}
	
	/**
	 * Fetch the job posting Branding Templates
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployerBrandingTemplatesDTO> fetchJobPostingBrandingTemplates(
			MerUserDTO merUserDTO) {
		List<EmployerBrandingTemplatesDTO> templatesDTOs = null;

		try {
			int userId = merUserDTO.getUserId();
			if (userId != 0) {
				List<MerJpBrandingTemp> merJpBrandingTemp = (List<MerJpBrandingTemp>) hibernateTemplateTracker
						.get(MerJpBrandingTemp.class,
								Long.valueOf(userId));
				List<EmployerBrandingTemplatesDTO> brandingTemplatesDTOs = new ArrayList<EmployerBrandingTemplatesDTO>();
				for (MerJpBrandingTemp jpBrandingTemp : merJpBrandingTemp) {
					brandingTemplatesDTOs
							.add(employerBrandingTemplateConversionHelper
									.transformEmpTempToEmpTempDTO(jpBrandingTemp));
				}
				templatesDTOs = brandingTemplatesDTOs;
			}
		} catch (HibernateException e) {
			// logger call
			LOGGER.info("ERROR1");
		}
		return templatesDTOs;
	}

	/**
	 * Create the job posting Branding Template.
	 */
	@Override
	public Boolean createJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO) {
		Boolean status = null;
		try {
			long tempId = brandingTemplatesDTO.getJpBrandTempId();
			if (tempId != 0) {
				MerJpBrandingTemp merJpBrandingTemp = employerBrandingTemplateConversionHelper
						.transformEmpTempDTOToEmpTemp(brandingTemplatesDTO);
				hibernateTemplateTracker.save(merJpBrandingTemp);
				status = Boolean.TRUE;
			}
		} catch (HibernateException e) {
			status = Boolean.FALSE;
			// logger call
			LOGGER.info("ERROR2");
		}
		return status;
	}

	/**
	 * View the job posting Branding Template.
	 */
	@Override
	public EmployerBrandingTemplatesDTO viewJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO) {
		EmployerBrandingTemplatesDTO templatesDTO = null;
		try {
			long tempId = brandingTemplatesDTO.getJpBrandTempId();
			if (tempId != 0) {
				MerJpBrandingTemp merJpBrandingTemp = (MerJpBrandingTemp) hibernateTemplateTracker
						.get(MerJpBrandingTemp.class,
								Long.valueOf(tempId));
				templatesDTO = employerBrandingTemplateConversionHelper
						.transformEmpTempToEmpTempDTO(merJpBrandingTemp);
			}
		} catch (HibernateException e) {
			// logger call
			LOGGER.info("ERROR3");
		}
		return templatesDTO;
	}

	/**
	 * Edit the job posting Branding Template.
	 */
	@Override
	public EmployerBrandingTemplatesDTO editJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO) {
		EmployerBrandingTemplatesDTO templatesDTO = null;
		try {
			long tempId = brandingTemplatesDTO.getJpBrandTempId();
			if (tempId != 0) {
				MerJpBrandingTemp merJpBrandingTemp = (MerJpBrandingTemp) hibernateTemplateTracker
						.get(MerJpBrandingTemp.class,
								Long.valueOf(tempId));
				templatesDTO = employerBrandingTemplateConversionHelper
						.transformEmpTempToEmpTempDTO(merJpBrandingTemp);
			}
		} catch (HibernateException e) {
			// logger call
			LOGGER.info("ERROR4");
		}
		return templatesDTO;
	}

	/**
	 * Delete the job posting Branding Template.
	 */
	@Override
	public Boolean deleteJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO) {
		Boolean status = null;
		try {
			long tempId = brandingTemplatesDTO.getJpBrandTempId();
			if (tempId != 0) {
				MerJpBrandingTemp merJpBrandingTemp = (MerJpBrandingTemp) hibernateTemplateTracker
						.get(MerJpBrandingTemp.class,
								Long.valueOf(tempId));
				hibernateTemplateTracker.delete(merJpBrandingTemp);
				status = Boolean.TRUE;
			}
		} catch (HibernateException e) {
			status = Boolean.FALSE;
			// logger call
			LOGGER.info("ERROR5");
		}
		return status;
	}

}