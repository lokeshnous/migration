package com.advanceweb.afc.jb.employer.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.job.service.JobPostService;

/**
 * This class is added for purchase job postings. Employer can add job postings to cart, 
 * remove job postings from cart 
 * @author anilm
 * @version 1.0
 * @created Aug 22, 2012
 */
@Controller
@RequestMapping(value = "/purchaseJobPosting")
@SessionAttributes("purchaseJobPostForm")
public class PurchaseJobPostingController {

	private static final Logger LOGGER = Logger
			.getLogger(PurchaseJobPostingController.class);
	private static final String PURCHASE_JOB_POSTINGS = "empPurchaseJobPostingsPopup";
	private static final String _JOBPOST_JSON = "jobPostJson";

	@Autowired
	private JobPostService employerJobPost;

	@Autowired
	private TransformJobPost transformJobPost;
	
	/**
	 * This method is to display the Job Type & Respective AddOns
	 * @param
	 * @return model
	 */
	@RequestMapping(value = "/purchaseJobPostings", method = RequestMethod.GET)
	public ModelAndView purchaseJobPostings(
			@RequestParam(value = "page", required = false) String page) {

		ModelAndView model = new ModelAndView();
		PurchaseJobPostForm purchaseJobPostForm = new PurchaseJobPostForm();

		if (page != null && page.equals(MMJBCommonConstants.INVENTORY)) {
			purchaseJobPostForm.setInventoryPage("true");
		}
		List<JobPostingPlanDTO> jobPostingPlanDTOList = employerJobPost
				.getJobPostingPlans();

		List<JobPostingsForm> jobPostingsForm = transformJobPost
				.transformToJobPostingsFormList(jobPostingPlanDTOList);

		purchaseJobPostForm.setJobPostingsForm(jobPostingsForm);
		model.addObject(MMJBCommonConstants.PURCHASE_JOB_POST_FORM, purchaseJobPostForm);
		model.setViewName(PURCHASE_JOB_POSTINGS);
		return model;
	}

	/**
	 * This method is to add the selected Job Type & AddOns to the Job Posting Cart 
	 * @param purchaseJobPostForm
	 * @param request
	 * @return model
	 */
	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public ModelAndView addToCart(PurchaseJobPostForm purchaseJobPostForm,
			HttpServletRequest request) {

		int packageSubTotal = 0, planCreditAmt = 0, addOnCreditAmtTotal = 0;
		String jobPostJson = request.getParameter(_JOBPOST_JSON);
		ObjectMapper mapper = new ObjectMapper();

		try {

			JobPostingsForm jobPostingsCart = mapper.readValue(jobPostJson,
					JobPostingsForm.class);

			planCreditAmt = Integer.parseInt(jobPostingsCart
					.getJobPostPlanCretitAmt());

			for (AddOnForm addOnForm : jobPostingsCart.getAddOnForm()) {
				addOnCreditAmtTotal = addOnCreditAmtTotal
						+ Integer.parseInt(addOnForm.getAddOnCreditAmt());
			}

			packageSubTotal = (planCreditAmt + addOnCreditAmtTotal)
					* jobPostingsCart.getQuantity();
			jobPostingsCart.setPackageSubTotal(packageSubTotal);

			purchaseJobPostForm.setGrandTotal(purchaseJobPostForm
					.getGrandTotal() + packageSubTotal);

			purchaseJobPostForm.getJobPostingsCart().add(jobPostingsCart);

		} catch (JsonParseException e) {
			LOGGER.error(e);
		} catch (JsonMappingException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}

		ModelAndView model = new ModelAndView();
		model.addObject(MMJBCommonConstants.PURCHASE_JOB_POST_FORM, purchaseJobPostForm);
		model.setViewName(PURCHASE_JOB_POSTINGS);
		return model;
	}

	/**
	 * This method is added to show the purchase job postings & items in the cart 
	 * @param purchaseJobPostForm
	 * @return model
	 */
	@RequestMapping(value = "/showPurchaseJobPostCart", method = RequestMethod.GET)
	public ModelAndView showPurchaseJobPostCart(
			PurchaseJobPostForm purchaseJobPostForm) {
		ModelAndView model = new ModelAndView();
		model.addObject(MMJBCommonConstants.PURCHASE_JOB_POST_FORM, purchaseJobPostForm);
		model.setViewName(PURCHASE_JOB_POSTINGS);
		return model;
	}

	/**
	 * This method is added to remove the selected items from the job posting cart 
	 * @param purchaseJobPostForm
	 * @param cartItemIndex
	 * @return model
	 */
	@RequestMapping(value = "/removeJobPost", method = RequestMethod.POST)
	public ModelAndView removeJobPost(PurchaseJobPostForm purchaseJobPostForm,
			@RequestParam("cartItemIndex") int cartItemIndex) {

		ModelAndView model = new ModelAndView();
		JobPostingsForm cartItem = purchaseJobPostForm.getJobPostingsCart()
				.get(cartItemIndex);
		purchaseJobPostForm.setGrandTotal(purchaseJobPostForm.getGrandTotal()
				- cartItem.getPackageSubTotal());
		purchaseJobPostForm.getJobPostingsCart().remove(cartItemIndex);
		model.addObject(MMJBCommonConstants.PURCHASE_JOB_POST_FORM, purchaseJobPostForm);
		model.setViewName(PURCHASE_JOB_POSTINGS);
		return model;
	}
	
	/**
	 * This method is added to update the cart if quantity value is changed in the cart 
	 * @param purchaseJobPostForm
	 * @param cartItemIndex
	 * @param quantity
	 * @return model
	 */
	@RequestMapping(value = "/updateQuantity", method = RequestMethod.POST)
	public ModelAndView updateQuantity(PurchaseJobPostForm purchaseJobPostForm,
			@RequestParam("cartItemIndex") int cartItemIndex, @RequestParam("quantity") int quantity) {

		ModelAndView model = new ModelAndView();
		int packageSubTotal = 0, planCreditAmt = 0, addOnCreditAmtTotal = 0;
		
		JobPostingsForm cartItem = purchaseJobPostForm.getJobPostingsCart()
				.get(cartItemIndex);
		
		purchaseJobPostForm.setGrandTotal(purchaseJobPostForm.getGrandTotal()
				- cartItem.getPackageSubTotal());
		
		planCreditAmt = Integer.parseInt(cartItem
				.getJobPostPlanCretitAmt());

		for (AddOnForm addOnForm : cartItem.getAddOnForm()) {
			addOnCreditAmtTotal = addOnCreditAmtTotal
					+ Integer.parseInt(addOnForm.getAddOnCreditAmt());
		}
		
		packageSubTotal = (planCreditAmt + addOnCreditAmtTotal)
				* quantity;
		cartItem.setQuantity(quantity);
		cartItem.setPackageSubTotal(packageSubTotal);

		purchaseJobPostForm.setGrandTotal(purchaseJobPostForm
				.getGrandTotal() + packageSubTotal);

		model.addObject(MMJBCommonConstants.PURCHASE_JOB_POST_FORM, purchaseJobPostForm);
		model.setViewName(PURCHASE_JOB_POSTINGS);
		return model;
	}

	/**
	 * This method is added to proceed to payment gateway screen  
	 * @param purchaseJobPostForm
	 * @param session
	 * @return model
	 */
	@RequestMapping(value = "/proceedToCheckOut", method = RequestMethod.GET)
	public ModelAndView proceedToCheckOut(
			PurchaseJobPostForm purchaseJobPostForm, HttpSession session) {
		ModelAndView model = new ModelAndView();
		session.setAttribute(MMJBCommonConstants.PURCHASE_JOB_POST_FORM, purchaseJobPostForm);

		model.setViewName("redirect:/pgiController/callPaymentMethod.html");
		return model;
	}
}
