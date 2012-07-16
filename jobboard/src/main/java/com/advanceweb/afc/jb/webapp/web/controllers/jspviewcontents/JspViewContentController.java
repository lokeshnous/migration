package com.advanceweb.afc.jb.webapp.web.controllers.jspviewcontents;
/**
 * @author nishantn
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.advanceweb.afc.jb.web.utils.CopyUtil;
import com.advanceweb.afc.jb.web.utils.ReadFile;

@Controller
@RequestMapping(value="/jspContentView")

public class JspViewContentController {


	private @Value("${basedirectorypath}") String basedirectorypath;
	private @Value("${directory}") String directory;
	private @Value("${healthcarenewsfilename}") String healthcarenewsfilename;
	private @Value("${careertoolfilename}") String careertoolfilename;


	@RequestMapping(value = "/viewcareerhtmlContents", method = RequestMethod.GET)
	public String gethtmlContents(HttpServletRequest request,Model model) {
		model.addAttribute("viewhtml", true);
		try {

			if(new File(basedirectorypath+directory+healthcarenewsfilename).exists()){
				String htmlhealthcontent=ReadFile.htmlReader(basedirectorypath+directory+healthcarenewsfilename);
				model.addAttribute("healthcarenew", htmlhealthcontent);
			}

			if(new File(basedirectorypath+directory+careertoolfilename).exists()){
				String htmlcareercontent=ReadFile.htmlReader(basedirectorypath+directory+careertoolfilename);
				model.addAttribute("careerstoolresource", htmlcareercontent);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("healthcarenew", "");
			model.addAttribute("careerstoolresource", "");
			e.printStackTrace();
		}
		return "jspviewcontent";
	}

	@RequestMapping(value = "/copyhtmltolocal", method = RequestMethod.GET)
	public String copyHtmlFiles(HttpServletRequest request,Model model) {
		try{
			(new File(basedirectorypath+directory)).mkdir();
			List<String> li=new ArrayList<String>();
			li.add(healthcarenewsfilename);
			li.add(careertoolfilename);
			CopyUtil.copy(li,basedirectorypath+directory);
			model.addAttribute("copyhtml", true);
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
			model.addAttribute("copyhtml", "");
		}

		return "jspviewcontent";
	}

}
