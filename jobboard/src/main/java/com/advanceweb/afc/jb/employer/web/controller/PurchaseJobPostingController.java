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
 * anilm
 * 
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
	private final String _JOBPOST_JSON = "jobPostJson";

	@Autowired
	private JobPostService employerJobPost;

	@Autowired
	private TransformJobPost transformJobPost;

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
		model.addObject("purchaseJobPostForm", purchaseJobPostForm);
		model.setViewName(PURCHASE_JOB_POSTINGS);
		return model;
	}

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
		model.addObject("purchaseJobPostForm", purchaseJobPostForm);
		model.setViewName(PURCHASE_JOB_POSTINGS);
		return model;
	}

	@RequestMapping(value = "/showPurchaseJobPostCart", method = RequestMethod.GET)
	public ModelAndView showPurchaseJobPostCart(
			PurchaseJobPostForm purchaseJobPostForm) {
		ModelAndView model = new ModelAndView();
		model.addObject("purchaseJobPostForm", purchaseJobPostForm);
		model.setViewName(PURCHASE_JOB_POSTINGS);
		return model;
	}

	@RequestMapping(value = "/removeJobPost", method = RequestMethod.POST)
	public ModelAndView removeJobPost(PurchaseJobPostForm purchaseJobPostForm,
			@RequestParam("cartItemIndex") int cartItemIndex) {

		ModelAndView model = new ModelAndView();
		JobPostingsForm cartItem = purchaseJobPostForm.getJobPostingsCart()
				.get(cartItemIndex);
		purchaseJobPostForm.setGrandTotal(purchaseJobPostForm.getGrandTotal()
				- cartItem.getPackageSubTotal());
		purchaseJobPostForm.getJobPostingsCart().remove(cartItemIndex);
		model.addObject("purchaseJobPostForm", purchaseJobPostForm);
		model.setViewName(PURCHASE_JOB_POSTINGS);
		return model;
	}

	@RequestMapping(value = "/proceedToCheckOut", method = RequestMethod.GET)
	public ModelAndView proceedToCheckOut(
			PurchaseJobPostForm purchaseJobPostForm, HttpSession session) {
		ModelAndView model = new ModelAndView();
		session.setAttribute("purchaseJobPostForm", purchaseJobPostForm);

		model.setViewName("redirect:/pgiController/callPaymentMethod.html");
		return model;
	}
}
