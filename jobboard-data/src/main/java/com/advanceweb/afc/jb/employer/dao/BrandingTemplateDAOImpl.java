package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.EmpBrandTempDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.data.entities.JpTemplate;
import com.advanceweb.afc.jb.data.entities.MerJpBrandingTemp;
import com.advanceweb.afc.jb.employer.helper.EmpBrandTempConversionHelper;

/**
 * <code>EmpBrandTempDAOImpl</code>is a DAO implementation class
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
@Repository("empBrandTempDAO")
public class BrandingTemplateDAOImpl implements BrandingTemplateDAO {

//	private HibernateTemplate hibernateTemplateTracker;
//	
//	@Autowired
//	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker,SessionFactory sessionFactory) {
//		this.hibernateTemplateTracker = new HibernateTemplate(sessionFactoryMerionTracker);
//	}
	@Autowired
	private EmpBrandTempConversionHelper empBrandTempConversionHelper;

	private static final Logger LOGGER = Logger
			.getLogger(BrandingTemplateDAOImpl.class);

//	@Autowired
//	public void setHibernateTemplate(SessionFactory sessionFactory) {
//		this.hibernateTemplateTracker = new HibernateTemplate(
//				sessionFactory);
//	}

	private HibernateTemplate hibernateTemplateTracker;
	private HibernateTemplate hibernateTemplateCareer;

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker,SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(sessionFactoryMerionTracker);
		this.hibernateTemplateCareer = new HibernateTemplate(sessionFactory);
	}

	/**
	 * Fetch the job posting Branding Templates
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EmpBrandTempDTO> fetchEmpBrandTemp(MerUserDTO merUserDTO) {
		List<EmpBrandTempDTO> templatesDTOs = null;

//		try {
//			int userId = merUserDTO.getUserId();
//			if (userId != 0) {
//				List<MerJpBrandingTemp> merJpBrandingTemp = hibernateTemplateTracker
//						.find("from  MerJpBrandingTemp where userId = "
//								+ userId);
//				List<EmpBrandTempDTO> brandingTemplatesDTOs = new ArrayList<EmpBrandTempDTO>();
//				for (MerJpBrandingTemp jpBrandingTemp : merJpBrandingTemp) {
//					brandingTemplatesDTOs.add(empBrandTempConversionHelper
//							.transformEmpTempToEmpTempDTO(jpBrandingTemp));
//				}
//				templatesDTOs = brandingTemplatesDTOs;
//			}
//		} catch (HibernateException e) {
//			// logger call
//			LOGGER.info("ERROR1");
//		}
		return templatesDTOs;
	}

	/**
	 * Create the job posting Branding Template.
	 */
	@Override
	public Boolean createEmpBrandTemp(EmpBrandTempDTO brandingTemplatesDTO) {
//		Boolean status = null;
//		try {
//			MerJpBrandingTemp merJpBrandingTemp = empBrandTempConversionHelper
//					.transformEmpTempDTOToEmpTemp(brandingTemplatesDTO);
//			hibernateTemplateTracker.save(merJpBrandingTemp);
//			status = Boolean.TRUE;
//		} catch (HibernateException e) {
//			status = Boolean.FALSE;
//			// logger call
//			LOGGER.info("ERROR2");
//		}
//		return status;
//		
		
		Boolean status = null;
		try {
			JpTemplate jpBrandingTemp = empBrandTempConversionHelper
					.transformEmpTempDTOToEmpTemp(brandingTemplatesDTO);
//			hibernateTemplateTracker.save(jpBrandingTemp);
			hibernateTemplateCareer.save(jpBrandingTemp);
//			hibernateTemplateCareer.save(jpBrandingTemp.getJpTemplateMedias());
//			hibernateTemplateCareer.save(jpBrandingTemp.getJpTemplateTestimonials());
			status = Boolean.TRUE;
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
	public EmpBrandTempDTO viewEmpBrandTemp(EmpBrandTempDTO brandingTemplatesDTO) {
		EmpBrandTempDTO templatesDTO = null;
//		try {
//			long tempId = brandingTemplatesDTO.getJpBrandTempId();
//			if (tempId != 0) {
//				MerJpBrandingTemp merJpBrandingTemp = (MerJpBrandingTemp) hibernateTemplateTracker
//						.get(MerJpBrandingTemp.class, (int) tempId);
//				templatesDTO = empBrandTempConversionHelper
//						.transformEmpTempToEmpTempDTO(merJpBrandingTemp);
//			}
//		} catch (HibernateException e) {
//			// logger call
//			LOGGER.info("ERROR3");
//		}
		return templatesDTO;
	}

	/**
	 * Edit the job posting Branding Template.
	 */
	@Override
	public EmpBrandTempDTO editEmpBrandTemp(EmpBrandTempDTO brandingTemplatesDTO) {
		EmpBrandTempDTO templatesDTO = null;
//		try {
//			/**
//			 * Get the details of template
//			 */
//			long tempId = brandingTemplatesDTO.getJpBrandTempId();
//			if (tempId != 0) {
//				MerJpBrandingTemp merJpBrandingTemp = (MerJpBrandingTemp) hibernateTemplateTracker
//						.get(MerJpBrandingTemp.class, (int) tempId);
//				templatesDTO = empBrandTempConversionHelper
//						.transformEmpTempToEmpTempDTO(merJpBrandingTemp);
//			}
//			/**
//			 * update the template
//			 */
//			MerJpBrandingTemp merJpBrandingTemp = empBrandTempConversionHelper
//					.transformEmpTempDTOToEmpTemp(brandingTemplatesDTO);
//			merJpBrandingTemp.setCreatedDate(templatesDTO.getCreatedDate());
//			hibernateTemplateTracker.merge(merJpBrandingTemp);
//			templatesDTO = empBrandTempConversionHelper
//					.transformEmpTempToEmpTempDTO(merJpBrandingTemp);
//		} catch (HibernateException e) {
//			// logger call
//			LOGGER.info("ERROR4");
//		}
		return templatesDTO;
	}

	/**
	 * Delete the job posting Branding Template.
	 */
	@Override
	public Boolean deleteEmpBrandTemp(EmpBrandTempDTO brandingTemplatesDTO) {
		Boolean status = null;
		try {
			long tempId = brandingTemplatesDTO.getJpBrandTempId();
			if (tempId != 0) {
				MerJpBrandingTemp merJpBrandingTemp = (MerJpBrandingTemp) hibernateTemplateTracker
						.get(MerJpBrandingTemp.class, (int) tempId);
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