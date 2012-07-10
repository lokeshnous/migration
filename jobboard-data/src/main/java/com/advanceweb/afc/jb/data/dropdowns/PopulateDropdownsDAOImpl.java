package com.advanceweb.afc.jb.data.dropdowns;



import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
	private SessionFactory sessionFactory;
	
	@Autowired
	private PopulateDropdownConversionHelper dropdownHelper;
	
	@Override
	public List<CountryDTO> getCountryList() {
		
		try {
			Session session= sessionFactory.openSession();
			List<MerLookup> merUtilityList = session.createQuery("from MerLookup e where e.lookupCategory='Country' and e.lookupStatus='1'").list();
			return dropdownHelper.convertMerUtilityToCountryDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EmploymentInfoDTO> getEmployementInfoList() {
		
		try {
			Session session= sessionFactory.openSession();
			List<MerLookup> merUtilityList = session.createQuery("from MerLookup e where e.lookupCategory='EmploymentInformation' and e.lookupStatus='1'").list();
			return dropdownHelper.convertMerUtilityToEmploymentInfoDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SubscriptionsDTO> getSubscriptionsList() {
		
		try {
			Session session= sessionFactory.openSession();
			List<MerLookup> merUtilityList = session.createQuery("from MerLookup e where e.lookupCategory='Subscriptions' and e.lookupStatus='1'").list();
			return dropdownHelper.convertMerUtilityToSubscriptionsDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<GenderDTO> getGenderList() {

		try {
			Session session= sessionFactory.openSession();
			List<MerLookup> merUtilityList = session.createQuery("from MerLookup e where e.lookupCategory='Gender' and e.lookupStatus='1'").list();
			return dropdownHelper.convertMerUtilityToGenderDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<VeteranStatusDTO> getVeteranStatusList() {

		try {
			Session session= sessionFactory.openSession();
			List<MerLookup> merUtilityList = session.createQuery("from MerLookup e where e.lookupCategory='VeteranStatus' and e.lookupStatus='1'").list();
			return dropdownHelper.convertMerUtilityToVeteranStatusDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EthenticityDTO> getEthenticityList() {

		try {
			Session session= sessionFactory.openSession();
			List<MerLookup> merUtilityList = session.createQuery("from MerLookup e where e.lookupCategory='Ethnicity' and e.lookupStatus='1'").list();
			return dropdownHelper.convertMerUtilityToEthenticityDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
