/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.pgi.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.common.AddOnDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.OrderDetailsDTO;
import com.advanceweb.afc.jb.common.ResumePackageDTO;
import com.advanceweb.afc.jb.common.SalesItemDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.data.entities.AdmFacilityInventory;
import com.advanceweb.afc.jb.data.entities.AdmInventoryDetail;
import com.advanceweb.afc.jb.data.entities.AdmOrderAddress;
import com.advanceweb.afc.jb.data.entities.AdmOrderHeader;
import com.advanceweb.afc.jb.data.entities.AdmOrderItem;
import com.advanceweb.afc.jb.data.entities.AdmOrderPayment;
import com.advanceweb.afc.jb.data.entities.JpAddon;
import com.advanceweb.afc.jb.data.entities.JpJobTypeCombo;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;
import com.advanceweb.afc.jb.pgi.helper.PaymentGatewayHelper;

/**
 * @author muralikc
 * 
 */
@Repository("paymentGatewayDao")
public class PaymentGatewayDaoImpl implements PaymentGatewayDao {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(PaymentGatewayDaoImpl.class);

	/** The Constant ORDER_STATUS_FAILURE. */
	private static final String ORDER_STATUS_FAILURE = "FAILURE";

	/** The Constant ORDER_STATUS_APPROVED. */
	private static final String ORDER_STATUS_APPROVED = "APPROVED";
	
	/** The Constant FIND_LAST_RESUME_PACKAGE_PURCHASED. */
	private static final String FIND_LAST_RESUME_PACKAGE_PURCHASED = "from AdmFacilityInventory admInv where admInv.expireDt in (select max(inv.expireDt) from AdmFacilityInventory inv, AdmInventoryDetail admInvDetail where admInvDetail.productType = ? and inv.admFacility = ?) group by order_id";
	
	/** The hibernate template. */
	private HibernateTemplate hibernateTemplate;

	/** The Constant BLANK. */
	private static final String BLANK = "BLANK";

	/**
	 * Sets the hibernate template.
	 *
	 * @param sessionFactory the new hibernate template
	 */
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/** The payment gateway helper. */
	@Autowired
	private PaymentGatewayHelper paymentGatewayHelper;

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.pgi.dao.PaymentGatewayDao#getAccountAddressByFacilityId(int)
	 */
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
				AccountAddressDTO fetchAccountAddressDTO = paymentGatewayHelper
						.convertAccountAddressDaoToDto(admFacilityContact
								.get(0));
				contactDTO = fetchAccountAddressDTO;
			}
		} catch (HibernateException e) {
			// logger call
			LOGGER.error("getAccountAddressByFacilityId ERROR",e);
		} catch (Exception ex) {
			// logger call
			LOGGER.error(ex);
		}
		return contactDTO;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.pgi.dao.PaymentGatewayDao#getBillingAddressByFacilityId(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public AccountAddressDTO getBillingAddressByFacilityId(int facilityId) {
		AccountAddressDTO billingAddressDTO = null;
		try {
			if (facilityId != 0) {
				AdmFacility admFacility = new AdmFacility();
				admFacility.setFacilityId(facilityId);
				@SuppressWarnings("unchecked")
				List<AdmFacilityContact> admFacilityContact = hibernateTemplate
						.find("from AdmFacilityContact where admFacility = ? and contactType = 'BILLING'",
								admFacility);
				if (admFacilityContact.size() > 0) {
					AccountAddressDTO fetchAccountAddressDTO = paymentGatewayHelper
							.convertBillingAddressDaoToDto(admFacilityContact
									.get(0));
					billingAddressDTO = fetchAccountAddressDTO;
				}
			}
		} catch (HibernateException e) {
			// logger call
			LOGGER.error("getBillingAddressByFacilityId ERROR",e);
		} catch (Exception ex) {
			// logger call
			LOGGER.error("ex-ERROR",ex);
		}
		return billingAddressDTO;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.pgi.dao.PaymentGatewayDao#saveBillingAddress(com.advanceweb.afc.jb.pgi.AccountAddressDTO)
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean saveBillingAddress(AccountAddressDTO billingAddressDTO) {
		try {
			AdmFacilityContact admFacilityContact = paymentGatewayHelper
					.convertBillingAddressDtoToEntity(billingAddressDTO);
			AdmFacility admFacility = new AdmFacility();
			// admFacility.setFacilityId(110);
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
			LOGGER.error("ex-ERROR",e);
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.pgi.dao.PaymentGatewayDao#saveDataBillingAddress(com.advanceweb.afc.jb.common.AccountBillingDTO)
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean saveDataBillingAddress(AccountBillingDTO billingAddressDTO) {
		try {
			AdmFacilityContact admFacilityContact = paymentGatewayHelper
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
			LOGGER.error("ex-ERROR",e);
		}

		return false;
	}

	/**
	 * This method is used to save the order details by taking the following
	 * parameters.
	 * 
	 * @param orderDetailsDTO
	 * @return boolean
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean saveOrderDetails(OrderDetailsDTO orderDetailsDTO) {
		try {
			AdmOrderHeader admOrderHeader = new AdmOrderHeader();

			admOrderHeader.setUserId(orderDetailsDTO.getUserId());

			AdmFacility admFacility = new AdmFacility();
			admFacility.setFacilityId(orderDetailsDTO.getFacilityId());
			admOrderHeader.setAdmFacility(admFacility);

			String orderStatusStr = null;
			
			if(MMJBCommonConstants.STATUS_CODE_200 == orderDetailsDTO.getOrderStatus()){
				orderStatusStr = ORDER_STATUS_APPROVED;
			}
			else{
				orderStatusStr = ORDER_STATUS_FAILURE;
			}
			
			List<AdmOrderItem> admOrderItemList = null; 
			
			if(MMJBCommonConstants.PURCHASE_RESUME_SEARCH.equals(orderDetailsDTO.getPurchaseType())){
				admOrderItemList = paymentGatewayHelper
						.transformToAdmOrderItemList(admOrderHeader,orderStatusStr,
							orderDetailsDTO.getResSearchPackageDTOList());
			}
			else if(MMJBCommonConstants.PURCHASE_JOB_POST.equals(orderDetailsDTO.getPurchaseType())){
				admOrderItemList = paymentGatewayHelper
						.transformToAdmOrderItemList(admOrderHeader,
							orderDetailsDTO.getJobPostingPlanDTOList(),orderStatusStr);
			}
			
			admOrderHeader.setAdmOrderItem(admOrderItemList);

			AdmOrderAddress admOrderAddress = paymentGatewayHelper
					.transformToAdmOrderAddress(admOrderHeader,
							orderDetailsDTO.getOrderAddressDTO());
			admOrderHeader.setAdmOrderAddress(admOrderAddress);

			if(null != orderDetailsDTO.getOrderPaymentDTO()){
				AdmOrderPayment admOrderPayment = paymentGatewayHelper
						.transformToAdmOrderPayment(admOrderHeader,
								orderDetailsDTO.getOrderPaymentDTO(),orderDetailsDTO.getOrderStatus());
				admOrderHeader.setAdmOrderPayment(admOrderPayment);
			}

			admOrderHeader.setOrderDate(new Date());
			admOrderHeader.setOrderTotal(orderDetailsDTO.getOrderTotal()); 
			
			admOrderHeader.setStatus(orderStatusStr);

			hibernateTemplate.saveOrUpdate(admOrderHeader);
			
			//if order is success then save the inventory details 
			if(MMJBCommonConstants.STATUS_CODE_200 == orderDetailsDTO.getOrderStatus()){
				orderDetailsDTO.setOrderId(admOrderHeader.getOrderId());
				saveInventoryDetails(orderDetailsDTO);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("ERROR : " + e);
			return false;
		}
		return true;
	}

	/**
	 * This method is used to save the inventory details by taking the following
	 * parameters.
	 * 
	 * @param orderDetailsDTO
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false)
	public boolean saveInventoryDetails(OrderDetailsDTO orderDetailsDTO) {
		try {
				List<AdmFacilityInventory> admFacilityInventoryList = new ArrayList<AdmFacilityInventory>();
				
				AdmFacilityInventory admFacilityInventory = null;
				AdmFacility admFacility = new AdmFacility();
				admFacility.setFacilityId(orderDetailsDTO.getFacilityId());
				int orderId = orderDetailsDTO.getOrderId();
				if(MMJBCommonConstants.PURCHASE_RESUME_SEARCH.equals(orderDetailsDTO.getPurchaseType())){
					int numberOfDays = 0;
					
					List<AdmFacilityInventory> admInvObj = hibernateTemplate.find(FIND_LAST_RESUME_PACKAGE_PURCHASED,MMJBCommonConstants.RESUME_SEARCH_PACKAGE,admFacility);
					
					//If its first time purchase then today's date + numberOfDays otherwise find the last purchase expire date + numberOfDays 
					Date expireDate = new Date();
					if(!admInvObj.isEmpty()){
						expireDate = admInvObj.get(0).getExpireDt();
					}
					Calendar calendar = Calendar.getInstance(); 
					
					
					for(ResumePackageDTO resSearchPackageDTO : orderDetailsDTO.getResSearchPackageDTOList()){
						
						admFacilityInventory = new AdmFacilityInventory();
						admFacilityInventory.setOrderId(orderId);
						admFacilityInventory.setAdmFacility(admFacility);
						admFacilityInventory.setCreateDt(new Date());
						admFacilityInventory.setFacilityId(admFacility.getFacilityId());
						
						AdmInventoryDetail admInventoryDetail = transformToAdmInventoryDetail(admFacilityInventory,resSearchPackageDTO);
						admFacilityInventory.setAdmInventoryDetail(admInventoryDetail);
						//find the number of days of expire & add this to expire date of latest inventory of that facility 
						numberOfDays = resSearchPackageDTO.getDuration() * resSearchPackageDTO.getQuantity();
						
						calendar.setTime(expireDate);
						calendar.add(Calendar.DATE, numberOfDays);
						expireDate = calendar.getTime(); 
						admFacilityInventory.setExpireDt(expireDate);
						admFacilityInventoryList.add(admFacilityInventory);
						
					}
				}
				else if(MMJBCommonConstants.PURCHASE_JOB_POST.equals(orderDetailsDTO.getPurchaseType())){
						
					for(JobPostingPlanDTO jobPostingPlanDTO : orderDetailsDTO.getJobPostingPlanDTOList()){
						
						admFacilityInventory = new AdmFacilityInventory();
						admFacilityInventory.setOrderId(orderId);
						admFacilityInventory.setAdmFacility(admFacility);
						admFacilityInventory.setCreateDt(new Date());
						admFacilityInventory.setFacilityId(admFacility.getFacilityId());
						AdmInventoryDetail admInventoryDetail = transformToAdmInventoryDetail(admFacilityInventory,jobPostingPlanDTO);
						admFacilityInventory.setAdmInventoryDetail(admInventoryDetail);
						admFacilityInventoryList.add(admFacilityInventory);
					}
				}	
				hibernateTemplate.saveOrUpdateAll(admFacilityInventoryList);
		} catch (Exception e) {
			LOGGER.error("ERROR : " + e);
			return false;
		}
		return true;
	}
	
	/**
	 * @param admFacilityInventory
	 * @param jobPostingPlanDTO
	 * @return admInventoryDetail
	 */
	private AdmInventoryDetail transformToAdmInventoryDetail(AdmFacilityInventory admFacilityInventory, JobPostingPlanDTO jobPostingPlanDTO){
		 AdmInventoryDetail admInventoryDetail = new AdmInventoryDetail();
			
			admInventoryDetail.setAdmFacilityInventory(admFacilityInventory);
			admInventoryDetail.setProductId(Integer.parseInt(jobPostingPlanDTO.getJobPostPlanId()));
			admInventoryDetail.setProductType(MMJBCommonConstants.JOB_TYPE_COMBO);
			admInventoryDetail.setOrderQty(jobPostingPlanDTO.getQuanity());
			admInventoryDetail.setAvailableqty(jobPostingPlanDTO.getQuanity());
			
			return admInventoryDetail;
	}
	
	/**
	 * @param admFacilityInventory
	 * @param resSearchPackageDTO
	 * @return admInventoryDetail
	 */
	private AdmInventoryDetail transformToAdmInventoryDetail(AdmFacilityInventory admFacilityInventory, ResumePackageDTO resSearchPackageDTO){
		 AdmInventoryDetail admInventoryDetail = new AdmInventoryDetail();
			
		admInventoryDetail.setAdmFacilityInventory(admFacilityInventory);
		admInventoryDetail.setProductId(resSearchPackageDTO.getPackageId());
		admInventoryDetail.setProductType(MMJBCommonConstants.RESUME_SEARCH_PACKAGE);
		admInventoryDetail.setOrderQty(resSearchPackageDTO.getQuantity());
		admInventoryDetail.setAvailableqty(resSearchPackageDTO.getQuantity());
		
		return admInventoryDetail;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.pgi.dao.PaymentGatewayDao#createJobPostOrderItems(com.advanceweb.afc.jb.common.OrderDetailsDTO)
	 */
	@Override
	public void createJobPostOrderItems(OrderDetailsDTO orderDetailsDTO) {
		
		List<SalesItemDTO> salesItemDTOList = new ArrayList<SalesItemDTO>();
		SalesItemDTO salesItemDTO = null;
		
		List<JobPostingPlanDTO> jobPostingPlanDTOList = new ArrayList<JobPostingPlanDTO>();
		JobPostingPlanDTO jobPostingDTO = null;
		
		for (JobPostingPlanDTO jobPostingPlanDTO : orderDetailsDTO.getJobPostingPlanDTOList()) {
			salesItemDTO = new SalesItemDTO();
			// call the SP to get the combo id & add it as product id
			int jobTypeId = Integer.parseInt(jobPostingPlanDTO
					.getJobPostPlanId());
			int[] inputs = { 0, 0, 0, 0, 0, 0, 0 };
			int index = 0;
			inputs[index] = jobTypeId;
			
			//if its just basic then find the combo id for basic
			if(jobPostingPlanDTO.getAddOnDTOList().size() == 0){
				//This value find it from db using 'Basic' String from jp_addon table
				JpAddon jpAddOn = (JpAddon)DataAccessUtils.uniqueResult(hibernateTemplate.find("from JpAddon where name = ?", MMJBCommonConstants.BASIC_JOB_TYPE));
				inputs[jpAddOn.getAddonId()] = jpAddOn.getAddonId(); 
			}
			
			for (AddOnDTO addOnDTO : jobPostingPlanDTO.getAddOnDTOList()) {
				// product id is combo id
				index = Integer.parseInt(addOnDTO.getAddOnId());
				inputs[index] = index;
			}

			Query query = hibernateTemplate.getSessionFactory().getCurrentSession().
					createSQLQuery(" { call Get_comboId(?,?,?,?,?,?,?) }");
			for (int i = 0; i < inputs.length; i++) {
				query.setInteger(i, inputs[i]);
			}
			// call SP here to get the combo id & set it as item
			int comboId = DataAccessUtils.intResult(query.list());
			
			//Find the netsuite id using the combo id
			JpJobTypeCombo jobTypeCombo  = hibernateTemplate.get(JpJobTypeCombo.class, comboId);
			
			//creating the salesItem DTO
			salesItemDTO.setItem(String.valueOf(jobTypeCombo.getNetSuiteId()));
			salesItemDTO.setQuantity(String.valueOf(jobPostingPlanDTO.getQuanity()));
			salesItemDTOList.add(salesItemDTO);
			
			
			//create the jobPostingPlanDTO list to store in order & inventory tables
			jobPostingDTO = new JobPostingPlanDTO();
			jobPostingDTO.setJobPostPlanId(String.valueOf(jobTypeCombo.getComboId()));
			jobPostingDTO.setJobPostPlanName(jobTypeCombo.getAddons());
			jobPostingDTO.setJobPostPlanCretitAmt(String.valueOf(jobTypeCombo.getBasePrice()));
			jobPostingDTO.setQuanity(jobPostingPlanDTO.getQuanity());
			jobPostingPlanDTOList.add(jobPostingDTO);
			
		}
		orderDetailsDTO.setJobPostingPlanDTOList(jobPostingPlanDTOList);
		orderDetailsDTO.getSalesOrderDTO().setSalesItemDTOList(salesItemDTOList);
	}
}