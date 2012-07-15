package com.advanceweb.afc.jb.webapp.web.controllers.jspviewcontents;
/**
 * @author nishantn
 */

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.advanceweb.afc.jb.web.utils.Constants;
import com.advanceweb.afc.jb.web.utils.CopyUtil;
import com.advanceweb.afc.jb.web.utils.ReadXmlFile;
import com.advanceweb.afc.jb.webapp.web.forms.xmldata.CareersToolResource;
import com.advanceweb.afc.jb.webapp.web.forms.xmldata.HealthCareNew;

@Controller
@RequestMapping(value="/jspContentView")

public class JspViewContentController {

	@RequestMapping(value = "/viewxmlContents", method = RequestMethod.GET)
	public String getXmlContents(HttpServletRequest request,Model model) {
		model.addAttribute("viewxml", true);
		try {
			List<HealthCareNew> healthlist=ReadXmlFile.xmlParser(Constants.XML_FILE_NAME_HEALTHCARE);
			List<CareersToolResource> careerlist=ReadXmlFile.xmlParser(Constants.XML_FILE_NAME_CAREERTOOL);

			model.addAttribute("healthcarenew", healthlist);
			model.addAttribute("careerstoolresource", careerlist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("healthcarenew", "");
			model.addAttribute("careerstoolresource", "");
			e.printStackTrace();
		}
		return "jspviewcontent";
	}
	
	
	@RequestMapping(value = "/viewcareerlinkContents", method = RequestMethod.GET)
	public String getcareerlinkContents(HttpServletRequest request,Model model) {
		model.addAttribute("viewcareer", true);
		try {
			List<CareersToolResource> careerlist=ReadXmlFile.xmlParser(Constants.XML_FILE_NAME_CAREERTOOL);
			model.addAttribute("careerstoolresource", careerlist);
			for(CareersToolResource cr:careerlist){
				if(request.getParameter("id").equalsIgnoreCase(String.valueOf(cr.getId()))){
					model.addAttribute("careerstoolresourcedescription", cr.getDescription());
					model.addAttribute("careerstoolresourcename", cr.getName());
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("healthcarenew", "");
			model.addAttribute("careerstoolresource", "");
			e.printStackTrace();
		}
		return "jspviewcontent";
	}

	@RequestMapping(value = "/copyxmltolocal", method = RequestMethod.GET)
	public String copyFiles(HttpServletRequest request,Model model) {
		List<String> li=new ArrayList<String>();
		li.add("HealthCareNews.xml");
		li.add("CareerToolResource.xml");

		CopyUtil.copy(li);
		return "jspviewcontent";
	}


}
