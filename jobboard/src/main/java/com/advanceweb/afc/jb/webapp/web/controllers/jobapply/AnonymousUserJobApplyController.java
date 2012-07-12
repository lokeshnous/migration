package com.advanceweb.afc.jb.webapp.web.controllers.jobapply;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AnonymousUserJobApplyDTO;
import com.advanceweb.afc.jb.jobapply.AnonymousUserJobApply;
import com.advanceweb.afc.jb.webapp.web.forms.jobapply.AnonymousUserJobApplyForm;
import com.advanceweb.afc.jb.webapp.web.transformers.TransformAnonymousUserJobApply;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class will act as a controller for the Anonymous User to apply for a Job
 */

@Controller
@RequestMapping("/anonymoususerjobapply")
public class AnonymousUserJobApplyController {

	
}
