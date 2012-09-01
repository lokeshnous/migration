package com.advanceweb.afc.jb.pgi.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;
import com.advanceweb.afc.jb.pgi.BillingAddressDTO;
import com.advanceweb.afc.jb.pgi.helper.AdmFacilityContactHelper;

/**
 * @author muralikc
 * 
 */

@Repository("fetchAccountAddressDAO")
public class FetchAccountAndBillingAddressDAOImpl implements
		FetchAccountAndBillingAddressDAO {

	private static final Logger LOGGER = Logger
			.getLogger(FetchAccountAndBillingAddressDAOImpl.class);

	private HibernateTemplate hibernateTemplate;

	private static final String BLANK= "BLANK";
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Autowired
	private AdmFacilityContactHelper admFacilityContactHelper;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public AccountAddressDTO getAccountAddressByFacilityId(int facilityId) {

		AccountAddressDTO contactDTO = null;
		try {
			if (facilityId != 0) {
				AdmFacility admFacility = new AdmFacility();
				admFacility.setFacilityId(facilityId);
				List<AdmFacilityContact> admFacilityContact = hibernateTemplate
						.find("from AdmFacilityContact where admFacility = ? and contactType = 'PRIMARY'",
								admFacility);
				AccountAddressDTO fetchAccountAddressDTO = admFacilityContactHelper
						.convertAccountAddressDaoToDto(admFacilityContact
								.get(0));
				contactDTO = fetchAccountAddressDTO;
			}
		} catch (HibernateException e) {
			// logger call
			LOGGER.info("getAccountAddressByFacilityId ERROR");
		} catch (Exception ex) {
			// logger call
			LOGGER.info(ex);
		}
		// TODO Auto-generated method stub
		return contactDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public BillingAddressDTO getBillingAddressByFacilityId(int facilityId) {
		BillingAddressDTO billingAddressDTO = null;
		try {
			if (facilityId != 0) {
				AdmFacility admFacility = new AdmFacility();
				admFacility.setFacilityId(facilityId);
				@SuppressWarnings("unchecked")
				List<AdmFacilityContact> admFacilityContact = hibernateTemplate
						.find("from AdmFacilityContact where admFacility = ? and contactType = 'BILLING'",
								admFacility);
				if (admFacilityContact.size() > 0) {
					BillingAddressDTO fetchAccountAddressDTO = admFacilityContactHelper
							.convertBillingAddressDaoToDto(admFacilityContact
									.get(0));
					billingAddressDTO = fetchAccountAddressDTO;
				}
			}
		} catch (HibernateException e) {
			// logger call
			LOGGER.info("getBillingAddressByFacilityId ERROR");
		} catch (Exception ex) {
			// logger call
			LOGGER.info("ex-ERROR");
		}
		return billingAddressDTO;
	}
	
	@Override
	@Transactional(readOnly = false)
	public boolean saveBillingAddress(BillingAddressDTO billingAddressDTO) {
		try {
			AdmFacilityContact admFacilityContact = admFacilityContactHelper
					.convertBillingAddressDtoToEntity(billingAddressDTO);
			AdmFacility admFacility = new AdmFacility();
//			admFacility.setFacilityId(110);
			admFacilityContact.setActive(1);
			admFacilityContact.setAdmFacility(admFacility);
			admFacilityContact.setContactType("BILLING");
			admFacilityContact.setCompany(BLANK);
			admFacilityContact.setEmail(BLANK);
			admFacilityContact.setJobTitle(BLANK);
			admFacilityContact.setPhone(BLANK);

			admFacilityContact.setFacilityContactId(billingAddressDTO
					.getFacilityContactId());
			if (billingAddressDTO == null
					|| billingAddressDTO.getFacilityContactId() == 0) {
				admFacilityContact.setCreateDt(new Date());
				// save
				hibernateTemplate.save(admFacilityContact);

			} else { // update
				admFacilityContact.setCreateDt(billingAddressDTO
						.getCreateDate());
				hibernateTemplate.update(admFacilityContact);

			}
		} catch (HibernateException e) {
			LOGGER.info("ex-ERROR");
		}

		return false;
	}
	
	
	@Override
	@Transactional(readOnly = false)
	public boolean saveDataBillingAddress(AccountBillingDTO billingAddressDTO) {
		try {
			AdmFacilityContact admFacilityContact = admFacilityContactHelper
					.convertBillingDataAddressDtoToEntity(billingAddressDTO);
			AdmFacility admFacility = new AdmFacility();
			admFacilityContact.setActive(1);
			admFacility.setFacilityId(billingAddressDTO.getFacilityId());
			admFacilityContact.setAdmFacility(admFacility);
			admFacilityContact.setContactType("BILLING");
			admFacilityContact.setJobTitle("BLANK");
			if (billingAddressDTO != null) {				
				hibernateTemplate.save(admFacilityContact);
			}
		} catch (HibernateException e) {
			LOGGER.info("ex-ERROR");
		}

		return false;
	}
	

}
