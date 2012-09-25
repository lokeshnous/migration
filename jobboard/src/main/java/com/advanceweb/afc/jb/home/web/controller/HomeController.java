package com.advanceweb.afc.jb.home.web.controller;

/**
 * @author nishantn
 */

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.employer.service.ManageFeatureEmployerProfile;
import com.advanceweb.afc.jb.employer.web.controller.EmployerProfileManagementForm;
import com.advanceweb.afc.jb.job.web.controller.JobSearchResultForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.CheckSessionMap;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.search.service.JobSearchService;
import com.advanceweb.afc.jb.web.utils.CopyUtil;
import com.advanceweb.afc.jb.web.utils.ReadFile;

@Controller
@RequestMapping(value = "/healthcarejobs")
public class HomeController {

	private static final Logger LOGGER = Logger
			.getLogger(HomeController.class);
	
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

	@Value("${logoPath}")
	private String logoPath;

	@Value("${windowmediaplayerfilepath}")
	private String windowmediaplayerfilepath;

	@Value("${followuplinkfacebook}")
	private String followuplinkfacebook;

	@Value("${followuplinktwitter}")
	private String followuplinktwitter;

	@Value("${followuplinkyoutube}")
	private String followuplinkyoutube;

	@Value("${followuplinklinkedin}")
	private String followuplinklinkedin;
	
	@Autowired
	private CheckSessionMap checkSessionMap;

	@Autowired
	private ManageFeatureEmployerProfile manageFeatureEmployerProfile;
	
	@Autowired
	private JobSearchService jobSearchService;

	@RequestMapping(value = "/advanceweb", method = RequestMethod.GET)
	public String gethtmlContents(HttpServletRequest request, Model model) {
		model.addAttribute("viewhtml", true);
		try {

			if (new File(basedirectorypath + directory + healthcarenewsfilename)
					.exists()) {
				String htmlhealthcontent = ReadFile
						.htmlReader(basedirectorypath + directory
								+ healthcarenewsfilename);
				model.addAttribute("healthcarenew", htmlhealthcontent);
			} else {
				model.addAttribute("healthcarenew",
						"<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
			}

			if (new File(basedirectorypath + directory + careertoolfilename)
					.exists()) {
				String htmlcareercontent = ReadFile
						.htmlReader(basedirectorypath + directory
								+ careertoolfilename);
				model.addAttribute("careerstoolresource", htmlcareercontent);
			}

			List<CompanyProfileDTO> companyProfileDTOList = manageFeatureEmployerProfile
					.getEmployerList();
			model.addAttribute("companyProfileDTOList", companyProfileDTOList);
			model.addAttribute("followuplinkfacebook", followuplinkfacebook);
			model.addAttribute("followuplinktwitter", followuplinktwitter);
			model.addAttribute("followuplinkyoutube", followuplinkyoutube);
			model.addAttribute("followuplinklinkedin", followuplinklinkedin);
			JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
			model.addAttribute("jobSearchResultForm", jobSearchResultForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("healthcarenew",
					"<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
			model.addAttribute("careerstoolresource", "");
			e.printStackTrace();
		}
		// return "jspviewcontent";
		return "home";
	}

	@RequestMapping(value = "/featuredemployers", method = RequestMethod.GET)
	public String getfeaturedemployerslist(HttpServletRequest request,
			Model model) {
		List<CompanyProfileDTO> companyProfileDTOList = manageFeatureEmployerProfile
				.getEmployerList();
		model.addAttribute("companyProfileDTOList", companyProfileDTOList);
		JobSearchResultForm jobSearchResultForm = new JobSearchResultForm();
		model.addAttribute("jobSearchResultForm", jobSearchResultForm);
		return "featuredemployers";
	}

	@RequestMapping(value = "/featuredemployerdetails", method = RequestMethod.GET)
	public String getfeaturedemployerbyid(
			EmployerProfileManagementForm employerProfileManagementForm,
			HttpServletRequest request, Model model) {
		CompanyProfileDTO companyProfileDTO = manageFeatureEmployerProfile
				.getEmployerDetails(Integer.parseInt(request.getParameter("id")));
		employerProfileManagementForm.setCompanyName(companyProfileDTO
				.getCompanyName());
		employerProfileManagementForm.setCompanyNews(companyProfileDTO
				.getCompanyNews());
		employerProfileManagementForm.setCompanyOverview(companyProfileDTO
				.getCompanyOverview());
		employerProfileManagementForm.setCompanyWebsite(companyProfileDTO
				.getCompanyWebsite());
		employerProfileManagementForm.setCompanyEmail(companyProfileDTO
				.getCompanyEmail());
		employerProfileManagementForm.setPositionTitle(companyProfileDTO
				.getPositionTitle());
		employerProfileManagementForm.setLogoPath(companyProfileDTO
				.getLogoPath());
		employerProfileManagementForm.setPrimaryColor(companyProfileDTO.getPrimaryColor().replace("HEX #", "#"));
		model.addAttribute("windowmediaplayerfilepath",
				windowmediaplayerfilepath);
		model.addAttribute("employerProfileManagementForm",
				employerProfileManagementForm);
		return "featuredemployerdetails";
	}

	@RequestMapping("/logo")
	public void getPhoto(@RequestParam("id") Long id,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			BufferedImage originalImage = ImageIO.read(new File(logoPath
					+ request.getParameter("id") + ".jpg"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();

			ResponseEntity<byte[]> result = handleGetMyBytesRequest(imageInByte);
			// Display the image
			write(response, result.getBody());
		} catch (Exception e) {

		}
	}

	@RequestMapping("/viewImage")
	public void getImage(@RequestParam("id") String imageId,
			HttpServletResponse response, HttpServletRequest request
			) {

		try {
			BufferedImage originalImage = ImageIO.read(new File(imageId));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage,
					imageId.substring(imageId.length() - 3, imageId.length()),
					baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();

			ResponseEntity<byte[]> result = handleGetMyBytesRequest(imageInByte);
			// Display the image
			write(response, result.getBody());
		} catch (Exception e) {

//			LOGGER.error(e);

		}
	}
	
	public ResponseEntity<byte[]> handleGetMyBytesRequest(byte[] imageInByte) {
		// Get bytes from somewhere...
		byte[] byteData = imageInByte;

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.IMAGE_PNG);
		responseHeaders.setContentLength(byteData.length);

		return new ResponseEntity<byte[]>(byteData, responseHeaders,
				HttpStatus.OK);
	}

	/*
	 * public void getPhoto( HttpServletResponse response) { try{ //
	 * BufferedImage originalImage = // ImageIO.read(new
	 * File("C:\\Users\\SHISHER\\Desktop\\images\\123.jpg")); //
	 * System.out.println("in photo////////////////"); // ByteArrayOutputStream
	 * baos = new ByteArrayOutputStream(); // ImageIO.write( originalImage,
	 * "jpg", baos ); // baos.flush(); // byte[] imageInByte =
	 * baos.toByteArray(); // baos.close();
	 * 
	 * // Send the request as GET //ResponseEntity<byte[]> result =
	 * restTemplate.
	 * exchange("http://localhost:8080/spring-rest-provider/krams/person/{id}",
	 * HttpMethod.GET, entity, byte[].class, id); ResponseEntity<byte[]> result
	 * =retriveLogo(); // Display the image write(response, result.getBody());
	 * }catch(Exception e){
	 * 
	 * } }
	 * 
	 * 
	 * 
	 * public ResponseEntity<byte[]> retriveLogo() throws IOException { byte[]
	 * data = extractBytes("D:\\images\\MercyRNlogo.jpg"); final HttpHeaders
	 * headers = new HttpHeaders();
	 * headers.setContentType(MediaType.IMAGE_JPEG); return new
	 * ResponseEntity<byte[]>(data, headers, HttpStatus.OK); }
	 * 
	 * 
	 * public byte[] extractBytes (String ImageName) throws IOException { //
	 * open image File imgPath = new File(ImageName); BufferedImage
	 * bufferedImage = ImageIO.read(imgPath); int type = bufferedImage.getType()
	 * == 0? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
	 * 
	 * BufferedImage resizeImageHintJpg = resizeImageWithHint(bufferedImage,
	 * type);
	 * 
	 * // get DataBufferBytes from Raster WritableRaster raster =
	 * resizeImageHintJpg .getRaster(); DataBufferByte data = (DataBufferByte)
	 * raster.getDataBuffer();
	 * 
	 * return ( data.getData() ); }
	 * 
	 * 
	 * private BufferedImage resizeImageWithHint(BufferedImage originalImage,
	 * int type){
	 * 
	 * BufferedImage resizedImage = new
	 * BufferedImage(Integer.parseInt(imgwidth), Integer.parseInt(imgheight),
	 * type); Graphics2D g = resizedImage.createGraphics();
	 * g.drawImage(originalImage, 0, 0, Integer.parseInt(imgwidth),
	 * Integer.parseInt(imgheight), null); g.dispose();
	 * g.setComposite(AlphaComposite.Src);
	 * 
	 * g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	 * RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	 * g.setRenderingHint(RenderingHints.KEY_RENDERING,
	 * RenderingHints.VALUE_RENDER_QUALITY);
	 * g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	 * RenderingHints.VALUE_ANTIALIAS_ON);
	 * 
	 * return resizedImage; }
	 */
	public void write(HttpServletResponse response, ByteArrayOutputStream bao) {

		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			bao.writeTo(outputStream);
			// Flush the stream
			outputStream.flush();
			// Close the stream
			outputStream.close();

		} catch (Exception e) {
		}
	}

	/**
	 * Writes the report to the output stream
	 */
	public void write(HttpServletResponse response, byte[] byteArray) {

		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			outputStream.write(byteArray);
			// Flush the stream
			outputStream.flush();
			// Close the stream
			outputStream.close();

		} catch (Exception e) {
		}
	}

	@RequestMapping(value = "/copyhtmltolocal", method = RequestMethod.GET)
	public String copyHtmlFiles(HttpServletRequest request, Model model) {
		try {
			File directorycreation = new File(basedirectorypath + directory);
			directorycreation.mkdir();
			List<String> li = new ArrayList<String>();
			li.add(healthcarenewsfilename);
			li.add(careertoolfilename);
			CopyUtil.copy(li, basedirectorypath + directory);
			model.addAttribute("copyhtml", true);
		} catch (Exception e) {// Catch exception if any
			// System.err.println("Error: " + e.getMessage());
			model.addAttribute("copyhtml", "");
			e.printStackTrace();
		}

		return "jspviewcontent";
	}
     
		/**
		 * This method is used to get the total number of Active jobs.
		 * @param HttpServletRequest
		 * @return String
		 */
		//To do: Take it from SOLR. Not from DB.
		@ResponseBody
		@RequestMapping(value = "/activeJobs", method = RequestMethod.GET)
		public String activeJobs(HttpServletRequest request) {
			long totalNoOfActiveJobs = 0;
			try {
				totalNoOfActiveJobs = jobSearchService.getTotalActiveJobs();
				
			} catch (Exception e) {// Catch exception if any
				LOGGER.error(e);
			}
			return String.valueOf(totalNoOfActiveJobs);
		}
		
		/**
		 * Called  to play the video
		 * 
		 * @param response
		 * @param request
		 * @param brandingTemplateForm
		 */
		/*@RequestMapping("/viewVideo")
		public void getPhoto(HttpServletResponse response, HttpServletRequest request,
				BrandingTemplateForm brandingTemplateForm) {

			try {
				
				String imageId = (String)request.getAttribute("id");
//				FileInputStream fileInputStream = new FileInputStream(new File(imageId)); 
//				BufferedImage originalImage = ImageIO.read(new File(imageId));
				
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				File file = new File("C://video.mp4");
//				String strFileContent= null;  
			    try
			    {
			      //create FileInputStream object
			      FileInputStream fin = new FileInputStream(file);
			     
			      
			       * Create byte array large enough to hold the content of the file.
			       * Use File.length to determine size of the file in bytes.
			       
			     
			       byte fileContent[] = new byte[(int)file.length()];
			     
			       
			        * To read content of the file in byte array, use
			        * int read(byte[] byteArray) method of java FileInputStream class.
			        *
			        
			       fin.read(fileContent);
			     
			       //create string from byte array
//			       strFileContent = new String(fileContent);
			     
			       System.out.println("File content : " +fileContent.length);
//			       System.out.println(strFileContent);
			       writeVideo(response, fileContent);
			     
			    }
			    catch(FileNotFoundException e)
			    {
			      System.out.println("File not found" + e);
			    }
			    catch(IOException ioe)
			    {
			      System.out.println("Exception while reading the file " + ioe);
			    }
//				ImageIO.write(fileInputStream,
//						imageId.substring(imageId.length() - 3, imageId.length()),
//						baos);
//				baos.flush();
//				byte[] imageInByte = baos.toByteArray();
//				baos.close();
	//
//				ResponseEntity<byte[]> result = handleGetMyBytesRequest(imageInByte);
//				// Display the image
			} catch (Exception e) {
				
				//LOGGER.error(e);

			}
		}*/
		
		/**
		 * Writes the report to the output stream
		 *//*
		public void writeVideo(HttpServletResponse response, byte[] fileContent) {

			try {
				// Retrieve the output stream
				ServletOutputStream outputStream = response.getOutputStream();
				// Write to the output stream
				outputStream.write(fileContent);
				// Flush the stream
				outputStream.flush();
				// Close the stream
				outputStream.close();

			} catch (Exception e) {
				//LOGGER.error(e);
			}
		}*/
		
			
		/**
		 * This method is called to get the search results by company 
		 * name for feature employer
		 * 
		 * @param request
		 * @param response
		 * @param session
		 * @param keywords
		 * @return
		 */
		@RequestMapping(value = "/searchByCompany", method = RequestMethod.GET)
		public @ResponseBody
		JSONObject searchByCompany(HttpServletRequest request,
				HttpServletResponse response, HttpSession session,
				@RequestParam("keywords") String keywords) {

			JSONObject jsonObject = new JSONObject();

			Map<String, String> sessionMap = checkSessionMap
					.getSearchSessionMap(session);

			sessionMap.put(MMJBCommonConstants.SEARCH_TYPE,
					MMJBCommonConstants.BASIC_SEARCH_TYPE);
			sessionMap.put(SearchParamDTO.KEYWORDS, keywords);
			sessionMap.put(SearchParamDTO.CITY_STATE, MMJBCommonConstants.EMPTY);
			sessionMap.put(SearchParamDTO.RADIUS, MMJBCommonConstants.ZERO);
			sessionMap.put(MMJBCommonConstants.AUTOLOAD, String.valueOf(true));

			session.setAttribute(SearchParamDTO.SEARCH_SESSION_MAP, sessionMap);

			jsonObject.put(MMJBCommonConstants.SEARCH_TYPE,
					MMJBCommonConstants.BASIC_SEARCH_TYPE);
			return jsonObject;
		}

}
