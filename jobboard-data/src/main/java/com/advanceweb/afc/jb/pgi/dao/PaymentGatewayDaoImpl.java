package com.advanceweb.afc.jb.pgi.dao;

import java.util.ArrayList;
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
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.data.entities.AdmFacilityInventory;
import com.advanceweb.afc.jb.data.entities.AdmInventoryDetail;
import com.advanceweb.afc.jb.data.entities.AdmOrderAddress;
import com.advanceweb.afc.jb.data.entities.AdmOrderHeader;
import com.advanceweb.afc.jb.data.entities.AdmOrderItem;
import com.advanceweb.afc.jb.data.entities.AdmOrderPayment;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;
import com.advanceweb.afc.jb.pgi.helper.PaymentGatewayHelper;

/**
 * @author muralikc
 * 
 */

@Repository("paymentGatewayDao")
public class PaymentGatewayDaoImpl implements PaymentGatewayDao {

	private static final String ORDER_STATUS_FAILURE = "FAILURE";

	private static final String ORDER_STATUS_APPROVED = "APPROVED";

	private static final Logger LOGGER = Logger
			.getLogger(PaymentGatewayDaoImpl.class);

	private HibernateTemplate hibernateTemplate;

	private static final String BLANK = "BLANK";

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Autowired
	private PaymentGatewayHelper paymentGatewayHelper;

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
			LOGGER.info("getAccountAddressByFacilityId ERROR");
		} catch (Exception ex) {
			// logger call
			LOGGER.info(ex);
		}
		return contactDTO;
	}

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
			LOGGER.info("getBillingAddressByFacilityId ERROR");
		} catch (Exception ex) {
			// logger call
			LOGGER.info("ex-ERROR");
		}
		return billingAddressDTO;
	}

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
			LOGGER.info("ex-ERROR");
		}

		return false;
	}

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
			LOGGER.info("ex-ERROR");
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
					
					for(ResumePackageDTO resSearchPackageDTO : orderDetailsDTO.getResSearchPackageDTOList()){
						
						admFacilityInventory = new AdmFacilityInventory();
						admFacilityInventory.setOrderId(orderId);
						admFacilityInventory.setAdmFacility(admFacility);
						admFacilityInventory.setCreateDt(new Date());
						
						AdmInventoryDetail admInventoryDetail = transformToAdmInventoryDetail(admFacilityInventory,resSearchPackageDTO);
						admFacilityInventory.setAdmInventoryDetail(admInventoryDetail);
						admFacilityInventoryList.add(admFacilityInventory);
					}
				}
				else if(MMJBCommonConstants.PURCHASE_JOB_POST.equals(orderDetailsDTO.getPurchaseType())){
						
					for(JobPostingPlanDTO jobPostingPlanDTO : orderDetailsDTO.getJobPostingPlanDTOList()){
						
						admFacilityInventory = new AdmFacilityInventory();
						admFacilityInventory.setOrderId(orderId);
						admFacilityInventory.setAdmFacility(admFacility);
						admFacilityInventory.setCreateDt(new Date());
						
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
			
			//if its just basic then this is fine to go with 
			if(jobPostingPlanDTO.getAddOnDTOList().size() == 0){
				admInventoryDetail.setProductId(Integer.parseInt(jobPostingPlanDTO.getJobPostPlanId()));
				admInventoryDetail.setProductType(MMJBCommonConstants.JOB_TYPE);
			}	
			else{
				//call the SP to get the combo id & add it as product id 
				int jobTypeId = Integer.parseInt(jobPostingPlanDTO.getJobPostPlanId());
				int [] inputs={0,0,0,0,0,0,0};
				int index = 0;
				inputs[index] = jobTypeId;
				
				for(AddOnDTO addOnDTO : jobPostingPlanDTO.getAddOnDTOList()){
					// product id is combo id
					index = Integer.parseInt(addOnDTO.getAddOnId());
					inputs[index] = index;
				}
				
				Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(" { call Get_comboId(?,?,?,?,?,?,?) }");
				for(int i = 0;i<inputs.length; i++){
					query.setInteger(i, inputs[i]);
				}	
				// call SP here to get the combo id & set it as product id 
				int comboId = DataAccessUtils.intResult(query.list());
				
				admInventoryDetail.setProductId(comboId);
				admInventoryDetail.setProductType(MMJBCommonConstants.JOB_TYPE_COMBO);
			}
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
}