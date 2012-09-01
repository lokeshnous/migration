package com.advanceweb.afc.jb.employer.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.BrandingTemplateDTO;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.JpTemplate;
import com.advanceweb.afc.jb.employer.helper.BrandTemplateConversionHelper;

/**
 * <code>EmpBrandTempDAOImpl</code>is a DAO implementation class
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
@Repository("brandingTemplateDAO")
public class BrandingTemplateDAOImpl implements BrandingTemplateDAO {
	@Autowired
	private BrandTemplateConversionHelper brandTemplateConversionHelper;

	private static final Logger LOGGER = Logger
			.getLogger(BrandingTemplateDAOImpl.class);

	private HibernateTemplate hibernateTemplateCareer;

	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateCareer = new HibernateTemplate(sessionFactory);
	}

	/**
	 * Fetch the job posting Branding Templates
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BrandingTemplateDTO> getBrandingTemplate(int userId) {
		List<BrandingTemplateDTO> templatesDTO = null;
		try {
			if (userId != 0) {
				AdmUserRole userRole = (AdmUserRole) hibernateTemplateCareer
						.find("from AdmUserRole a where a.id.userId=?", userId)
						.get(0);
				AdmUserFacility userFacility = (AdmUserFacility) hibernateTemplateCareer
						.find("from AdmUserFacility f where f.id.userId=? and f.id.roleId=?",
								userRole.getRolePK().getUserId(),
								userRole.getRolePK().getRoleId()).get(0);
				List<JpTemplate> brandingTemplateList = hibernateTemplateCareer
						.find("from  JpTemplate where admFacility.facilityId=?",
								userFacility.getAdmFacility().getFacilityId());
				templatesDTO = new ArrayList<BrandingTemplateDTO>();
				for (JpTemplate template : brandingTemplateList) {
					templatesDTO.add(brandTemplateConversionHelper
							.convertToBrandTemplateDTO(template));
				}
			}
		} catch (HibernateException e) {
			// logger call
			LOGGER.info("ERROR1");
		}
		return templatesDTO;
	}

	/**
	 * Create the job posting Branding Template.
	 */
	@Override
	public Boolean createEmpBrandTemp(BrandingTemplateDTO brandingTemplatesDTO) {
		// Boolean status = null;
		// try {
		// MerJpBrandingTemp merJpBrandingTemp = empBrandTempConversionHelper
		// .transformEmpTempDTOToEmpTemp(brandingTemplatesDTO);
		// hibernateTemplateTracker.save(merJpBrandingTemp);
		// status = Boolean.TRUE;
		// } catch (HibernateException e) {
		// status = Boolean.FALSE;
		// // logger call
		// LOGGER.info("ERROR2");
		// }
		// return status;
		//

		Boolean status = null;
		try {
			JpTemplate jpBrandingTemp = brandTemplateConversionHelper
					.convertToJPTemplate(brandingTemplatesDTO);
			// hibernateTemplateTracker.save(jpBrandingTemp);
			hibernateTemplateCareer.save(jpBrandingTemp);
			if (!brandingTemplatesDTO.getIsSilverCustomer()) {
				hibernateTemplateCareer.save(jpBrandingTemp
						.getJpTemplateMedias().get(0));
				hibernateTemplateCareer.save(jpBrandingTemp
						.getJpTemplateMedias().get(1));
				hibernateTemplateCareer.save(jpBrandingTemp
						.getJpTemplateTestimonials().get(0));
			}
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
	public BrandingTemplateDTO viewEmpBrandTemp(
			BrandingTemplateDTO brandingTemplatesDTO) {
		BrandingTemplateDTO templatesDTO = null;
		// try {
		// long tempId = brandingTemplatesDTO.getJpBrandTempId();
		// if (tempId != 0) {
		// MerJpBrandingTemp merJpBrandingTemp = (MerJpBrandingTemp)
		// hibernateTemplateTracker
		// .get(MerJpBrandingTemp.class, (int) tempId);
		// templatesDTO = empBrandTempConversionHelper
		// .transformEmpTempToEmpTempDTO(merJpBrandingTemp);
		// }
		// } catch (HibernateException e) {
		// // logger call
		// LOGGER.info("ERROR3");
		// }
		return templatesDTO;
	}

	/**
	 * Edit the job posting Branding Template.
	 */
	@Override
	public BrandingTemplateDTO editBrandingTemplate(int templateId) {
		BrandingTemplateDTO templatesDTO = new BrandingTemplateDTO();
		try {
			if (templateId != 0) {
				JpTemplate template = (JpTemplate) hibernateTemplateCareer.get(
						JpTemplate.class, templateId);
				templatesDTO = brandTemplateConversionHelper
						.convertToBrandTemplateDTO(template);
			}
		} catch (HibernateException e) {
			// logger call
			LOGGER.info("ERROR4");
		}
		return templatesDTO;
	}

	/**
	 * update the delete_dt column for the corresponding job posting Branding
	 * Template.
	 */
	@Override
	public boolean deleteBrandingTemplate(int templateId) {
		boolean status = false;
		try {
			if (templateId != 0) {
				JpTemplate template = hibernateTemplateCareer.get(
						JpTemplate.class, templateId);
				template.setDeleteDt(new Date());
				hibernateTemplateCareer.update(template);
				status = true;
			}
		} catch (HibernateException e) {
			status = false;
			// logger call
			LOGGER.info("ERROR5");
		}
		return status;
	}

	/**
	 * update the job posting Branding Template.
	 */
	@Override
	public boolean updateBrandingTemplate(
			BrandingTemplateDTO brandingTemplatesDTO) {
		JpTemplate template = hibernateTemplateCareer.load(JpTemplate.class,
				brandingTemplatesDTO.getJpBrandTempId());
		template.setColorPalette(brandingTemplatesDTO.getColor());
		template.setCompanyOverview(brandingTemplatesDTO.getCompanyOverview());
		template.setCreateDt(brandingTemplatesDTO.getCreatedDate());
		template.setTemplateName(brandingTemplatesDTO.getTemplateName());
		template.setMainImagePath(brandingTemplatesDTO.getMainImagePath());
		template.setLogoPath(brandingTemplatesDTO.getLogoPath());
		hibernateTemplateCareer.update(template);
		return true;
	}

}