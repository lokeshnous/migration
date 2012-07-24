package com.advanceweb.afc.jb.home.web.controller;
/**
 * @author nishantn
 */

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.employer.service.ManageFeatureEmployerProfile;
import com.advanceweb.afc.jb.employer.web.controller.EmployerProfileManagementForm;
import com.advanceweb.afc.jb.web.utils.CopyUtil;
import com.advanceweb.afc.jb.web.utils.ReadFile;

@Controller
@RequestMapping(value="/healthcarejobs")

public class HomeController {


	@Value("${IMG_WIDTH}")
	private String imgwidth;
	
	@Value("${IMG_HEIGHT}")
	private String imgheight;
	
	@Value("${basedirectorypath}")
	private String basedirectorypath;
	
	@Value("${directory}")
	private String directory;
	
	@Value("${healthcarenewsfilename}")
	private String healthcarenewsfilename;
	
	@Value("${careertoolfilename}")
	private String careertoolfilename;
	
	



	@Autowired
	private ManageFeatureEmployerProfile manageFeatureEmployerProfile;



	@RequestMapping(value = "/advanceweb", method = RequestMethod.GET)
	public String gethtmlContents(HttpServletRequest request,Model model) {
		model.addAttribute("viewhtml", true);
		try {

			if(new File(basedirectorypath+directory+healthcarenewsfilename).exists()){
				String htmlhealthcontent=ReadFile.htmlReader(basedirectorypath+directory+healthcarenewsfilename);
				model.addAttribute("healthcarenew", htmlhealthcontent);
			}else{
				model.addAttribute("healthcarenew", "<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
			}

			if(new File(basedirectorypath+directory+careertoolfilename).exists()){
				String htmlcareercontent=ReadFile.htmlReader(basedirectorypath+directory+careertoolfilename);
				model.addAttribute("careerstoolresource", htmlcareercontent);
			}
			
//			List<CompanyProfileDTO> companyProfileDTOList = manageFeatureEmployerProfile.getEmployerList();
//			model.addAttribute("companyProfileDTOList", companyProfileDTOList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("healthcarenew", "<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
			model.addAttribute("careerstoolresource", "");
			e.printStackTrace();
		}
		//return "jspviewcontent";
		return "home";
	}

	@RequestMapping(value = "/featuredemployers", method = RequestMethod.GET)
	public String getfeaturedemployerslist(HttpServletRequest request,Model model) {
		List<CompanyProfileDTO> companyProfileDTOList = manageFeatureEmployerProfile.getEmployerList();
		model.addAttribute("companyProfileDTOList", companyProfileDTOList);

		return "featuredemployers";
	}

	@RequestMapping(value = "/featuredemployerdetails", method = RequestMethod.GET)
	public String getfeaturedemployerbyid(EmployerProfileManagementForm employerProfileManagementForm,HttpServletRequest request,Model model) {
		CompanyProfileDTO companyProfileDTO = manageFeatureEmployerProfile.getEmployerDetails(Integer.parseInt(request.getParameter("id")));
		employerProfileManagementForm.setCompanyName(companyProfileDTO.getCompanyName());
		employerProfileManagementForm.setCompanyNews(companyProfileDTO.getCompanyNews());
		employerProfileManagementForm.setCompanyOverview(companyProfileDTO.getCompanyOverview());
		employerProfileManagementForm.setCompanyWebsite(companyProfileDTO.getCompanyWebsite());
		employerProfileManagementForm.setCompanyEmail(companyProfileDTO.getCompanyEmail());
		employerProfileManagementForm.setPositionTitle(companyProfileDTO.getPositionTitle());
		employerProfileManagementForm.setLogoPath(companyProfileDTO.getLogoPath());

		model.addAttribute("employerProfileManagementForm", employerProfileManagementForm);
		return "featuredemployerdetails";
	}

	
	@RequestMapping("/logo")
	public ResponseEntity<byte[]> retriveLogo() throws IOException {
		byte[] data = extractBytes("D:\\images\\MercyRNlogo.jpg");
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);
	    return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
	}

	
	public byte[] extractBytes (String ImageName) throws IOException {
		// open image
		File imgPath = new File(ImageName);
		BufferedImage bufferedImage = ImageIO.read(imgPath);
		int type = bufferedImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();

		BufferedImage resizeImageHintJpg = resizeImageWithHint(bufferedImage, type);

		// get DataBufferBytes from Raster
		WritableRaster raster = resizeImageHintJpg .getRaster();
		DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

		return ( data.getData() );
	}


	private BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){

		BufferedImage resizedImage = new BufferedImage(Integer.parseInt(imgwidth), Integer.parseInt(imgheight), type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, Integer.parseInt(imgwidth), Integer.parseInt(imgheight), null);
		g.dispose();	
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}	

	@RequestMapping(value = "/copyhtmltolocal", method = RequestMethod.GET)
	public String copyHtmlFiles(HttpServletRequest request,Model model) {
		try{
			File directorycreation=new File(basedirectorypath+directory);
			directorycreation.mkdir();
			List<String> li=new ArrayList<String>();
			li.add(healthcarenewsfilename);
			li.add(careertoolfilename);
			CopyUtil.copy(li,basedirectorypath+directory);
			model.addAttribute("copyhtml", true);
		}catch (Exception e){//Catch exception if any
			//System.err.println("Error: " + e.getMessage());
			model.addAttribute("copyhtml", "");
			e.printStackTrace();
		}

		return "jspviewcontent";
	}

}
