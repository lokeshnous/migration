package com.advanceweb.afc.jb.webapp.web.controllers.resume;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.advanceweb.afc.jb.dto.CreateResumeDTO;
import com.advanceweb.afc.jb.resume.CreateResumeService;
import com.advanceweb.afc.jb.webapp.web.forms.resume.CreateResume;
import com.advanceweb.afc.jb.webapp.web.helper.ReadDocFile;

@Controller
public class CreateResumeController {


	@Autowired
	private CreateResumeService createResumeService;



	@RequestMapping(value = "/createResumePopUp", method = RequestMethod.GET)
	public String getTime(HttpServletRequest request, HttpSession session,Model model,Map<String, Object> map) {

		CreateResume createResume=new CreateResume();
		Map<String,String> resumeType = new LinkedHashMap<String,String>();
		resumeType.put("CR", "Create");
		resumeType.put("UPL", "Upload");
		resumeType.put("CP", "CopyPaste");
		model.addAttribute("resumeTypeList", resumeType);  
		Map<String,String> employmentType = new LinkedHashMap<String,String>();
		employmentType.put("FT", "Full Time");
		employmentType.put("PT", "Part Time");
		employmentType.put("PD", "Per Diem");
		employmentType.put("CT", "Contract/Travel");
		model.addAttribute("employmentTypeList", employmentType);  
		Map<String,String> workauthUS = new LinkedHashMap<String,String>();
		workauthUS.put("CR", "1");
		workauthUS.put("UPL", "2");
		workauthUS.put("CP", "3");
		model.addAttribute("workauthUSList", workauthUS);  
		createResume.setResume_visibility("Pu");
		createResume.setWilling_to_relocate("Y");
		map.put("createResume", createResume);

		return "createResume";
	}

	@RequestMapping(value = "/copyPasteResume", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("createResume")
	CreateResume createResume, BindingResult result,Model model,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		if(createResume.getResumeType().equalsIgnoreCase("CP")){
			model.addAttribute("createResume", createResume);
			return "copyPasteResumeText";
		}else if (createResume.getResumeType().equalsIgnoreCase("UPL")) {
			System.out.println("In upload++++++++++");
			System.err.println("-------------------------------------------");
			try {
				MultipartFile file = createResume.getFileData();
				String fileName = null;
				InputStream inputStream = null;
				OutputStream outputStream = null;
				if (file.getSize() > 0) {
					inputStream = file.getInputStream();
					if (file.getSize() > 10000) {
						System.out.println("File Size:::" + file.getSize());
						//return "/uploadfile";
					}
					System.out.println("size::" + file.getSize());
					fileName = request.getRealPath("") + "/resources/images/"
							+ file.getOriginalFilename();
					outputStream = new FileOutputStream(fileName);
					System.out.println("fileName:" + file.getOriginalFilename()+"+++++++++++++++++++++"+fileName);

					int readBytes = 0;
					byte[] buffer = new byte[10000];
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}
					outputStream.close();
					inputStream.close();
				}

				// ..........................................
				session.setAttribute("uploadFile", file.getOriginalFilename());


				//==============================
				//Reading File
				//==============================
				FileInputStream fstream = new FileInputStream(fileName);
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF8"));
				String strLine;
				//Read File Line By Line
				StringBuffer resumeTextData=new StringBuffer();
				while ((strLine = br.readLine()) != null)   {
					// Print the content on the console
					System.out.println (strLine);
					resumeTextData.append(strLine+"\n");
				}

				InetAddress ownIP=InetAddress.getLocalHost();
				//POI File Reader


				String fname="";
				String ext="";
				int mid= fileName.lastIndexOf(".");
				fname=fileName.substring(0,mid);
				ext=fileName.substring(mid+1,fileName.length());  
				System.out.println("File name ="+fname);
				System.out.println("Extension ="+ext); 
				if(ext.equalsIgnoreCase("doc")){
					resumeTextData.delete(0, resumeTextData.length());
					new ReadDocFile().readMyDocument(fileName, resumeTextData);
				}else if (ext.equalsIgnoreCase("docx")) {
					resumeTextData.delete(0, resumeTextData.length());
					new ReadDocFile().DocxFileReader(fileName, resumeTextData);
				}
				//Data Insertion part   
				CreateResumeDTO createResumeDTO=new CreateResumeDTO();
				createResumeDTO.setResumeType(createResume.getResumeType());
				createResumeDTO.setResume_name(createResume.getResume_name());
				createResumeDTO.setDesired_job_title(createResume.getDesired_job_title());
				createResumeDTO.setDesired_employment_type(createResume.getDesired_employment_type());
				createResumeDTO.setResume_visibility(createResume.getResume_visibility());
				createResumeDTO.setWork_authorization_US(createResume.getWorkauthUS());
				createResumeDTO.setResumeText(resumeTextData.toString());
				createResumeDTO.setFileServer(ownIP.getHostAddress());
				createResumeDTO.setFilePath(fileName);
				createResumeDTO.setFileName(file.getOriginalFilename());
				createResumeDTO.setIsPublished("12");
				createResumeService.addCreateResumeUpload(createResumeDTO);

				//Close the input stream
				in.close();
				resumeTextData.delete(0, resumeTextData.length());

			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/createResumePopUp.html";
		}else{
			return "copyPasteResumeText";
		}
	}

	@RequestMapping(value = "/copyPasteResumeSubmit", method = RequestMethod.POST)
	public String addResume(@ModelAttribute("createResume")
	CreateResume createResume, BindingResult result,Model model) throws Exception {
		CreateResumeDTO createResumeDTO=new CreateResumeDTO();
		System.out.println(createResume.getResumeType()+"=========\n"+createResume.getResume_name()+"=========\n"+createResume.getDesired_job_title()+"=========\n"+createResume.getDesired_employment_type()+"=========\n"+createResume.getWorkauthUS()+"=========\n"+createResume.getResume_visibility());

		createResumeDTO.setResumeType(createResume.getResumeType());
		createResumeDTO.setResume_name(createResume.getResume_name());
		createResumeDTO.setDesired_job_title(createResume.getDesired_job_title());
		createResumeDTO.setDesired_employment_type(createResume.getDesired_employment_type());
		createResumeDTO.setResume_visibility(createResume.getResume_visibility());
		createResumeDTO.setWork_authorization_US(createResume.getWorkauthUS());
		createResumeDTO.setResumeText(createResume.getResumeText());
		createResumeService.addCreateResumeCopyPaste(createResumeDTO);

		return "redirect:/createResumePopUp.html";
	}
	
	public String createResume(@ModelAttribute("createResume")
	CreateResume createResume, BindingResult result,Model model){
		
		
		return null;
		
	}

}
