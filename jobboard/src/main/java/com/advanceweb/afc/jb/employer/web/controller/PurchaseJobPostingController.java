package com.advanceweb.afc.jb.employer.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.advanceweb.afc.jb.job.service.JobPostService;

/**
 * anilm
 * @version 1.0
 * @created Aug 22, 2012
 */
@Controller
@RequestMapping(value = "/purchaseJobPosting")
@SessionAttributes("purchaseJobPostForm")
@SuppressWarnings("unchecked")
public class PurchaseJobPostingController {
	
	@Autowired
	private JobPostService employerJobPost;
	
	@Autowired
	private TransformJobPost transformJobPost;
	
	@RequestMapping(value="/purchaseJobPostings",method = RequestMethod.GET)
	public ModelAndView showPurchaseJobPostings() {
		
		ModelAndView model = new ModelAndView();
		List<JobPostingPlanDTO> jobPostingPlanDTOList = employerJobPost.getJobPostingPlans();
		
		List<JobPostingsForm> jobPostingsForm = transformJobPost.transformToJobPostingsFormList(jobPostingPlanDTOList);
		PurchaseJobPostForm purchaseJobPostForm = new PurchaseJobPostForm();
		purchaseJobPostForm.setJobPostingsForm(jobPostingsForm);
		model.addObject("purchaseJobPostForm", purchaseJobPostForm);
		model.setViewName("empPurchaseJobPostingsPopup");
		return 	model;
	}
	
	@RequestMapping(value="/addToCart",method = RequestMethod.POST)
	public ModelAndView addJobPostingToCart(PurchaseJobPostForm purchaseJobPostForm,HttpServletRequest request) {
		
		String jobPostJson = request.getParameter("jobPostJson");
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			int packageSubTotal = 0 , planCreditAmt = 0 , addOnCreditAmtTotal = 0;
			JobPostingsForm jobPostingsCart = mapper.readValue(jobPostJson, JobPostingsForm.class);
			
			planCreditAmt = Integer.parseInt(jobPostingsCart.getJobPostPlanCretitAmt());
			
			for(AddOnForm addOnForm : jobPostingsCart.getAddOnForm()){
				addOnCreditAmtTotal = addOnCreditAmtTotal + Integer.parseInt(addOnForm.getAddOnCreditAmt()); 
			}
			
			packageSubTotal = (planCreditAmt + addOnCreditAmtTotal)*jobPostingsCart.getQuantity();
			jobPostingsCart.setPackageSubTotal(packageSubTotal);
			
			purchaseJobPostForm.setGrandTotal(purchaseJobPostForm.getGrandTotal() + packageSubTotal);
			
			purchaseJobPostForm.getJobPostingsCart().add(jobPostingsCart);
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ModelAndView model = new ModelAndView();
		model.addObject("purchaseJobPostForm", purchaseJobPostForm);
		model.setViewName("empPurchaseJobPostingsPopup");
		return 	model;
	}
	
	@RequestMapping(value="/showPurchaseJobPostCart",method = RequestMethod.GET)
	public ModelAndView showPurchaseJobPostCart(PurchaseJobPostForm purchaseJobPostForm) {
		ModelAndView model = new ModelAndView();
		model.addObject("purchaseJobPostForm", purchaseJobPostForm);
		model.setViewName("empPurchaseJobPostingsPopup");
		return 	model;
	}
	
	@RequestMapping(value="/removeJobPost",method = RequestMethod.POST)
	public ModelAndView removeJobPostFromCart(PurchaseJobPostForm purchaseJobPostForm,
			@RequestParam("cartItemIndex") int cartItemIndex) {
		ModelAndView model = new ModelAndView();
		JobPostingsForm cartItem = purchaseJobPostForm.getJobPostingsCart().get(cartItemIndex);
		purchaseJobPostForm.grandTotal = purchaseJobPostForm.grandTotal - cartItem.getPackageSubTotal();
		purchaseJobPostForm.getJobPostingsCart().remove(cartItemIndex);
		model.addObject("purchaseJobPostForm", purchaseJobPostForm);
		model.setViewName("empPurchaseJobPostingsPopup");
		return 	model;
	}
	
	@RequestMapping(value="/proceedToCheckOut",method = RequestMethod.POST)
	public ModelAndView checkOut(PurchaseJobPostForm purchaseJobPostForm) {
		ModelAndView model = new ModelAndView();
		model.addObject("purchaseJobPostForm", purchaseJobPostForm);
		model.setViewName("empPurchaseJobPostingsPopup");
		return 	model;
	}
}
