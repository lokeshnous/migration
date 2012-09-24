package com.advanceweb.afc.jb.employer.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.BrandingTemplateDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityPackage;
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
@SuppressWarnings("unchecked")
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
				// List<JpTemplate> brandingTemplateList =
				// hibernateTemplateCareer
				// .find("from  JpTemplate where admFacility.facilityId=?",
				// userFacility.getAdmFacility().getFacilityId());
				List<JpTemplate> brandingTemplateList = hibernateTemplateCareer
						.find("from  JpTemplate where admFacility.facilityId=? and deleteDt is null",
								userFacility.getAdmFacility().getFacilityId());
				templatesDTO = new ArrayList<BrandingTemplateDTO>();
				for (JpTemplate template : brandingTemplateList) {
					// get the count of template id used by
					int count = hibernateTemplateCareer
							.find("from JpJob jb where jb.jpTemplate.templateId=? and jb.active=1",
									template.getTemplateId()).size();
					templatesDTO.add(brandTemplateConversionHelper
							.convertToBrandTemplateDTO(template, count));
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

		Boolean status = null;
		try {

			JpTemplate jpTemplate = brandTemplateConversionHelper.convertToJPTemplate(brandingTemplatesDTO);
			
			hibernateTemplateCareer.saveOrUpdate(jpTemplate);

			status = Boolean.TRUE;
		} catch (HibernateException e) {
			status = Boolean.FALSE;
			// logger call
			LOGGER.info("ERROR2"+e);
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
		int count = 0;
		try {
			if (templateId != 0) {
				JpTemplate template = (JpTemplate) hibernateTemplateCareer.get(
						JpTemplate.class, templateId);
				templatesDTO = brandTemplateConversionHelper
						.convertToBrandTemplateDTO(template, count);
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
	public boolean deleteBrandingTemplate(int templateId, int deleteUserId) {
		boolean status = false;
		try {
			if (templateId != 0) {
				JpTemplate template = hibernateTemplateCareer.get(
						JpTemplate.class, templateId);
				template.setDeleteDt(new Date());
				template.setDeleteUserId(deleteUserId);
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
		template.setCreateDt(new Date());
		template.setTemplateName(brandingTemplatesDTO.getTemplateName());
		template.setMainImagePath(brandingTemplatesDTO.getMainImagePath());
		template.setLogoPath(brandingTemplatesDTO.getLogoPath());
		hibernateTemplateCareer.update(template);
		return true;
	}

	@Override
	public int getBrandingInformation(int facilityId) {
		int packageId = 0;

		try {
			if (facilityId != 0) {
				AdmFacilityPackage admFacilityPackage = (AdmFacilityPackage) hibernateTemplateCareer
						.find("from AdmFacilityPackage where facilityId=?",
								facilityId).get(0);
				packageId = admFacilityPackage.getPackageId();
			}
		} catch (HibernateException e) {

			LOGGER.error(e);
		}
		return packageId;

	}

	/**
	 * This method is used to get the net suite customer id based on adm
	 * facility id.
	 * 
	 * @param int admFacilityID
	 * @return int NSCustomerID
	 */

	public List<FacilityDTO> getNSCustomerIDFromAdmFacility(int admFacilityID) {

		List<FacilityDTO> admFacilityDTOList = new ArrayList<FacilityDTO>();
		try {
			List<AdmFacility> admFacilityList = hibernateTemplateCareer
					.find(" from  AdmFacility WHERE  facilityId  = '"
							+ admFacilityID + "'");

			if (admFacilityList != null) {
				for (AdmFacility admFacilityObj : admFacilityList) {
					FacilityDTO admFacilityDTO = new FacilityDTO();
					admFacilityDTO.setNsCustomerID(admFacilityObj
							.getNsCustomerID());
					admFacilityDTOList.add(admFacilityDTO);
				}
			}

		} catch (HibernateException e) {
			LOGGER.debug(e);
		}
		return admFacilityDTOList;
	}

	
	/**
	 * This method is used the get the Branding Template Purchase information
	 * @param facilityId
	 * @return
	 */
	public boolean getBrandPurchaseInfo(int facilityId) {
		boolean isBTEnabled = false;
		int productId = 0;
		Query getInventoryData = hibernateTemplateCareer.getSessionFactory()
				.getCurrentSession()
				.createSQLQuery(" { call GetInventoryDetails(?) }");
		getInventoryData.setInteger(0, facilityId);
		List<?> invetoryDeatil = getInventoryData.list();
		Iterator<?> iterator = invetoryDeatil.iterator();
		while (iterator.hasNext()) {
			Object[] row = (Object[]) iterator.next();
			productId = ((Integer) row[0]);
			if (productId == 1) {
				// If the value is 1 implies the customer has purchased Branding
				// Template
				isBTEnabled = true;
				return isBTEnabled;
			}
		}
		return isBTEnabled;
	}

	
}