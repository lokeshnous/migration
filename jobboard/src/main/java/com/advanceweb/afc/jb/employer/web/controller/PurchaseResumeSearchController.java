/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.ResumePackageDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.ResumePackageService;
import com.advanceweb.afc.jb.exception.JobBoardException;

/**
 * This class has been added to Purchase Resume Search Packages.
 * @author anilm
 * @version 1.0
 * @created Aug 22, 2012
 */
@Controller
@RequestMapping(value = "/purchaseResumeSearch")
@SessionAttributes("purchaseResumeSearchForm")
public class PurchaseResumeSearchController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(PurchaseResumeSearchController.class);
	
	/** The Constant PURCHASE_RESUME_SEARCH_FORM. */
	private static final String PURCHASE_RESUME_SEARCH_FORM = "purchaseResumeSearchForm";
	
	/** The Constant PURCHASE_RESUME_SEARCH_POPUP. */
	private static final String PURCHASE_RESUME_SEARCH_POPUP = "empPurchaseResumeSearchPopup";
	
	/** The Constant PAYMENT_METHOD. */
	private static final String PAYMENT_METHOD = "redirect:/pgiController/callPaymentMethod.html?purchaseType=resumeSearch";
	
	/** The resume package service. */
	@Autowired
	private ResumePackageService resumePackageService;
	
	/** The transform resume package. */
	@Autowired
	private TransformResumePackage transformResumePackage;
	
	/**
	 * This method is called to fetch the available Resume Packages & display in the UI
	 * @param 
	 * @return model
	 */
	@RequestMapping(value = "/showResumeSearchPachages", method = RequestMethod.GET)
	public ModelAndView showResumeSearchPachages() {
		ModelAndView model = new ModelAndView();
		PurchaseResumeSearchForm purchaseResumeSearchForm = new PurchaseResumeSearchForm();
		List<ResumePackageDTO> resSearchPackageDTOList = null;
		try {
			resSearchPackageDTOList = resumePackageService.showResumeSearchPackages();
		
			List<ResumeSearchPackageForm> resSearchPackageFormList= transformResumePackage.tranformToResumeSearchPackageFormList(resSearchPackageDTOList);
			purchaseResumeSearchForm.setResumeSearchPackageList(resSearchPackageFormList);
			model.addObject(PURCHASE_RESUME_SEARCH_FORM,purchaseResumeSearchForm);
			model.setViewName(PURCHASE_RESUME_SEARCH_POPUP);
		} catch (JobBoardException e) {
			LOGGER.error("Error while fetching Resume Search Packages"+ e);
		}
		return model;
	}
	
	/**
	 * This method is to add the selected Resume Search Package to the Cart 
	 * @param purchaseJobPostForm
	 * @param packageIndex - index value of the package selected
	 * @param quantity - package quantity 
	 * @return model
	 */
	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public ModelAndView addToCart(PurchaseResumeSearchForm purchaseResumeSearchForm, 
			@RequestParam("packageIndex") int packageIndex, @RequestParam("quantity") int quantity) {
		
		ModelAndView model = new ModelAndView();
		try {
			
			ResumeSearchPackageForm resSearchPackage = purchaseResumeSearchForm.getResumeSearchPackageList().get(packageIndex);
			ResumeSearchPackageForm resSearchPackageForm = (ResumeSearchPackageForm)resSearchPackage.clone();
			resSearchPackageForm.setQuantity(quantity);
			// PackageTotal = PriceAmtOfPackage * Quantity  
			resSearchPackageForm.setPackageTotal(resSearchPackageForm.getPriceAmt()*resSearchPackageForm.getQuantity());
			purchaseResumeSearchForm.getResumeSearchPackageCart().add(resSearchPackageForm);
			//GrandTotal = Sum of all PackageTotal
			purchaseResumeSearchForm.setGrandTotal(purchaseResumeSearchForm.getGrandTotal() + resSearchPackageForm.getPackageTotal());
			
		} catch (CloneNotSupportedException e) {
			LOGGER.error("Error while adding the Resume Search Package Item to the cart "+e);
		} catch(Exception e){
			LOGGER.error("Error while adding the Resume Search Package Item to the cart "+e);
		}
		
		model.addObject(PURCHASE_RESUME_SEARCH_FORM,purchaseResumeSearchForm);
		model.setViewName(PURCHASE_RESUME_SEARCH_POPUP);		
		return model;
	}
	
	/**
	 * This method is added to show the purchase job postings & items in the cart 
	 * @param purchaseJobPostForm
	 * @return model
	 */
	@RequestMapping(value = "/showResumeSearchPackageCart", method = RequestMethod.GET)
	public ModelAndView showPurchaseJobPostCart(
			PurchaseResumeSearchForm purchaseResumeSearchForm) {
		ModelAndView model = new ModelAndView();
		model.addObject(PURCHASE_RESUME_SEARCH_FORM,purchaseResumeSearchForm);
		model.setViewName(PURCHASE_RESUME_SEARCH_POPUP);
		return model;
	}
	
	/**
	 * This method is added to remove the selected items from the job posting cart 
	 * @param purchaseJobPostForm
	 * @param cartItemIndex - index of the cart item to be removed
	 * @return model
	 */
	@RequestMapping(value = "/removeResumeSearchPack", method = RequestMethod.POST)
	public ModelAndView removeResumeSearchPack(PurchaseResumeSearchForm purchaseResumeSearchForm,
			@RequestParam("cartItemIndex") int cartItemIndex) {
		ModelAndView model = new ModelAndView();
		ResumeSearchPackageForm cartItem = purchaseResumeSearchForm.getResumeSearchPackageCart().get(cartItemIndex);
		//Deduct PackageTotal from the GrandTotal  
		purchaseResumeSearchForm.setGrandTotal(purchaseResumeSearchForm.getGrandTotal()- cartItem.getPackageTotal());
		//Remove the item from the cart
		purchaseResumeSearchForm.getResumeSearchPackageCart().remove(cartItemIndex);
		model.addObject(PURCHASE_RESUME_SEARCH_FORM,purchaseResumeSearchForm);
		model.setViewName(PURCHASE_RESUME_SEARCH_POPUP);
		return model;
	}
	
	/**
	 * This method is added to update the cart if quantity value is changed in the cart 
	 * @param purchaseResumeSearchForm
	 * @param cartItemIndex - index of the cart item to be updated
	 * @param quantity - new quantity value of the package
	 * @return model
	 */
	@RequestMapping(value = "/updateQuantity", method = RequestMethod.POST)
	public ModelAndView updateQuantity(PurchaseResumeSearchForm purchaseResumeSearchForm,
			@RequestParam("cartItemIndex") int cartItemIndex, @RequestParam("quantity") int quantity) {

		ModelAndView model = new ModelAndView();
		
		ResumeSearchPackageForm cartItem = purchaseResumeSearchForm.getResumeSearchPackageCart()
				.get(cartItemIndex);
		//Deduct PackageTotal from the GrandTotal 
		purchaseResumeSearchForm.setGrandTotal(purchaseResumeSearchForm.getGrandTotal()
				- cartItem.getPackageTotal());
		//Update the new quantity & PackageTotal
		cartItem.setQuantity(quantity);
		cartItem.setPackageTotal(cartItem.getPriceAmt() * cartItem.getQuantity());
		//Change the GrandTotal
		purchaseResumeSearchForm.setGrandTotal(purchaseResumeSearchForm
				.getGrandTotal() + cartItem.getPackageTotal());

		model.addObject(PURCHASE_RESUME_SEARCH_FORM,purchaseResumeSearchForm);
		model.setViewName(PURCHASE_RESUME_SEARCH_POPUP);
		return model;
	}
	
	/**
	 * This method is added to proceed to payment gateway screen  
	 * @param purchaseResumeSearchForm
	 * @param session
	 * @return model
	 */
	@RequestMapping(value = "/proceedToCheckOut", method = RequestMethod.GET)
	public ModelAndView proceedToCheckOut(
			PurchaseResumeSearchForm purchaseResumeSearchForm, HttpSession session) {
		ModelAndView model = new ModelAndView();
		
		//put the Resume Search Cart into session - this will be accessed in payment gateway
		//(PaymentGatewayController) screens 
		session.setAttribute(MMJBCommonConstants.PURCHASE_RESUME_SEARCH_FORM, purchaseResumeSearchForm);
		//redirecting to payment gateway method
		model.setViewName(PAYMENT_METHOD);
		return model;
	}

}
