package com.advanceweb.afc.jb.data.dropdowns;



import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmploymentInfoDTO;
import com.advanceweb.afc.jb.common.EthenticityDTO;
import com.advanceweb.afc.jb.common.GenderDTO;
import com.advanceweb.afc.jb.common.SubscriptionsDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;
import com.advanceweb.afc.jb.data.common.helpers.PopulateDropdownConversionHelper;
import com.advanceweb.afc.jb.data.entities.MerLookup;


public class PopulateDropdownsDAOImpl implements PopulateDropdownsDAO{
		
	@Autowired
	private PopulateDropdownConversionHelper dropdownHelper;
	
	
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactoryMerionTracker);
	}

	@Override
	public List<CountryDTO> getCountryList() {
		
		try {

			List<MerLookup> merUtilityList = hibernateTemplate.find("from MerLookup e where e.lookupCategory='Country' and e.lookupStatus='1'");
			return dropdownHelper.convertMerUtilityToCountryDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EmploymentInfoDTO> getEmployementInfoList() {
		
		try {
			List<MerLookup> merUtilityList =hibernateTemplate.find("from MerLookup e where e.lookupCategory='EmploymentInformation' and e.lookupStatus='1'"); 
			return dropdownHelper.convertMerUtilityToEmploymentInfoDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SubscriptionsDTO> getSubscriptionsList() {
		
		try {			
			List<MerLookup> merUtilityList =hibernateTemplate.find("from MerLookup e where e.lookupCategory='Subscriptions' and e.lookupStatus='1'");  
			return dropdownHelper.convertMerUtilityToSubscriptionsDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<GenderDTO> getGenderList() {

		try {
			List<MerLookup> merUtilityList =hibernateTemplate.find("from MerLookup e where e.lookupCategory='Gender' and e.lookupStatus='1'"); 
			return dropdownHelper.convertMerUtilityToGenderDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<VeteranStatusDTO> getVeteranStatusList() {

		try {

			List<MerLookup> merUtilityList =hibernateTemplate.find("from MerLookup e where e.lookupCategory='VeteranStatus' and e.lookupStatus='1'"); 
			return dropdownHelper.convertMerUtilityToVeteranStatusDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EthenticityDTO> getEthenticityList() {

		try {
			List<MerLookup> merUtilityList =hibernateTemplate.find("from MerLookup e where e.lookupCategory='Ethnicity' and e.lookupStatus='1'"); 
			return dropdownHelper.convertMerUtilityToEthenticityDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
