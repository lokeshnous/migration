package com.advanceweb.afc.jb.document;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.ContactInformationDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;


/**
 * 
 * @author Rajesh Bhaskar
 * 
 * @Version 1.0
 * @since 9th October 2012 
 */

@Component("pdfGenerator")
public class PDFGenerator {

	private @Value("${resume.paragraphFontName}")
	String paragraphFontName;
	
	private @Value("${resume.paragraphFontSize}")
	float paragraphFontSize;
	
	private @Value("${resume.sectionHeadingFontSize}")
	float sectionHeadingFontSize;
	
	private @Value("${resume.sectionHeadingFontName}")
	String sectionHeadingFontName;
	
	/**
	 * Main method that begins to draw the information onto the generated PDF
	 * @param document the new pdf document that is being created
	 * @param resumeDTO the resume information from the data source for the candidate
	 * @throws DocumentException thrown if there is any exception occurring during pdf generation
	 */
	public void generatePDFResume(Document document, ResumeDTO resumeDTO) throws DocumentException {
		
		
		//TODO change the Creator / Author to the logged-in User / Candidate Name
		document.addAuthor("User Name");
		document.addCreator(resumeDTO.getResumeName());
		document.addCreationDate();
		document.addTitle(resumeDTO.getResumeName());

		ContactInformationDTO contactInfoDTO = resumeDTO.getContactInfoDTO();

		
		if (null != contactInfoDTO) {
		populateCandidateInfoSection(document, contactInfoDTO);
		
		//Address Information
		AddressDTO addressDTO = contactInfoDTO.getAddressDTO();
		if (null != addressDTO) {
			populateCandidateAddressInfo(document, addressDTO);
			populateCandidateContactNumbers(document, addressDTO);
		}
		
		//Objective Section
		populateObjectiveSection(document, resumeDTO);
		
		// Work Experience Section
		populateWorkExperienceSection(document, resumeDTO);

		// Education Section
		populateEducationSection(document, resumeDTO);

		// Certification Section
		populateCertificationSection(document, resumeDTO);
		

		// Skills Section
		populateSkillsSection(document, resumeDTO);


		// Awards Section
		populateAwardsSection(document, resumeDTO);


		// Memberships Section
		populateMembershipsSection(document, resumeDTO);

		
		// Other Section
		populateOtherSection(document, resumeDTO);

		
		// References Section
		populateReferencesSection(document, resumeDTO);

		
		
		
		} else {
			// Thrown Exception if there is no contact information for the candidate
			createAndThrowCreatePDFException("ContactInformation not available");
		}
		
	}


	/**
	 *  Create an general Reference section section on the PDF and populate the Reference provided by the candidate 
	 * @param document
	 * @param resumeDTO
	 */
	
	private void populateReferencesSection(Document document,
			ResumeDTO resumeDTO) throws DocumentException {
		//Creating the References Section
		if ((null != resumeDTO.getListRefDTO()) && (resumeDTO.getListRefDTO().size() > 0 )) {
		
		Paragraph referencesPara = generateReferencesPara(resumeDTO.getListRefDTO(),  paragraphFontName, paragraphFontSize,Font.DEFAULTSIZE, BaseColor.BLACK);
		if (null != referencesPara) {
			Paragraph languageHeadingPara = generateParagraph("Personal References",  paragraphFontName, paragraphFontSize ,Font.BOLD, BaseColor.BLACK);
			document.add(Chunk.NEWLINE);
			document.add(languageHeadingPara);
			document.add(referencesPara);
		}
		//Section separator  - Draw Line
		drawLineSeperator(document);
		}
	}

	/**
	 * populate the References provided by the candidate in a List format 
	 * @param listRefDTO
	 * @param paraFontName
	 * @param paraFontSize
	 * @param paraFontStyle
	 * @param fontColor
	 * @return void
	 */
	
	private Paragraph generateReferencesPara(List<ReferenceDTO> listRefDTO,
			String paraFontName, float paraFontSize,
			int paraFontStyle, BaseColor fontColor) {
		Paragraph referencesParagraph = null;
		if ((null != listRefDTO) && (listRefDTO.size() > 0)) {

		
			 PdfPTable referencesTable = new PdfPTable(2);
			 
	         for (ReferenceDTO referenceDTO: listRefDTO) {
	        	 
	         referencesTable.addCell("Name");
	         referencesTable.addCell(referenceDTO.getName());
	         
	         referencesTable.addCell("Job Title");
	         referencesTable.addCell(referenceDTO.getJobTitle());

	     	 referencesTable.addCell("Company Name");
	         referencesTable.addCell(referenceDTO.getCompanyName());

	         referencesTable.addCell("Phone Number");
	         referencesTable.addCell(referenceDTO.getPhoneNo());
	        
	     	 referencesTable.addCell("Email Address");
	         referencesTable.addCell(referenceDTO.getEmail());

	     	 referencesTable.addCell("Reference Type");
	         referencesTable.addCell(referenceDTO.getRefType());
	        
	     	}
	
	         if (null == referencesParagraph) {
	        	 referencesParagraph = new Paragraph();
	         }
	         referencesParagraph.add(referencesTable);
		}
	         
		return referencesParagraph;
	}

	/**
	 *  Create an Other section on the PDF and populate the candidate has provided in the Other information section while
	 *  registering his information
	 * @param document
	 * @param resumeDTO
	 */
	private void populateOtherSection(Document document, ResumeDTO resumeDTO) throws DocumentException {
		//Creating the Membership Section
		if ((null != resumeDTO.getOtherDetails()) && (resumeDTO.getOtherDetails().trim().length() > 0 )) {
		
		Paragraph otherDetailsPara = generateParagraph(resumeDTO.getOtherDetails(),  paragraphFontName, paragraphFontSize,Font.DEFAULTSIZE, BaseColor.BLACK);
		if (null != otherDetailsPara) {
			Paragraph otherDetailsHeadingPara = generateParagraph("Memberships",  paragraphFontName, sectionHeadingFontSize ,Font.BOLD, BaseColor.BLACK);
			document.add(Chunk.NEWLINE);
			document.add(otherDetailsHeadingPara);
			document.add(otherDetailsPara);
		}
		//Section separator  - Draw Line
		drawLineSeperator(document);
		}
		
	}

	/**
	 *  Create an Membership section on the PDF and populate the all the registered membership organizations 
	 *  that the candidate has been enrolled into
	 * @param document
	 * @param resumeDTO
	 */
	private void populateMembershipsSection(Document document,
			ResumeDTO resumeDTO)  throws DocumentException {
		//Creating the Membership Section
		if ((null != resumeDTO.getMemberships()) && (resumeDTO.getMemberships().trim().length() > 0 )) {
		
		Paragraph membershipPara = generateParagraph(resumeDTO.getMemberships(),  paragraphFontName, paragraphFontSize,Font.DEFAULTSIZE, BaseColor.BLACK);
		if (null != membershipPara) {
			Paragraph membershipHeadingPara = generateParagraph("Others",  paragraphFontName, sectionHeadingFontSize ,Font.BOLD, BaseColor.BLACK);
			document.add(Chunk.NEWLINE);
			document.add(membershipHeadingPara);
			document.add(membershipPara);
		}
		//Section separator  - Draw Line
		drawLineSeperator(document);
		}
		
	}

	/**
	 *  Create an Awards Section section on the PDF and populate the Candidate's Awards information
	 * @param document
	 * @param resumeDTO
	 */
	private void populateAwardsSection(Document document, ResumeDTO resumeDTO) throws DocumentException {
		//Creating the Awards Section
		if ((null != resumeDTO.getAwards()) && (resumeDTO.getAwards().trim().length() > 0 )) {
		
		Paragraph awardsPara = generateParagraph(resumeDTO.getAwards(),  paragraphFontName, paragraphFontSize,Font.DEFAULTSIZE, BaseColor.BLACK);
		if (null != awardsPara) {
			Paragraph awardsHeadingPara = generateParagraph("Awards",  paragraphFontName, sectionHeadingFontSize ,Font.BOLD, BaseColor.BLACK);
			document.add(Chunk.NEWLINE);
			document.add(awardsHeadingPara);
			document.add(awardsPara);
		}
		
		//Section separator  - Draw Line
		drawLineSeperator(document);
		}
				
		
	}

	/**
	 *  Create an Skills section on the PDF and populate the Candidate's registered Skills information
	 * @param document
	 * @param resumeDTO
	 */
	private void populateSkillsSection(Document document, ResumeDTO resumeDTO) throws DocumentException {
		
		//Creating the Skills Section
		if ((null != resumeDTO.getListLangDTO()) && (resumeDTO.getListLangDTO().size() > 0 )) {
		
		Paragraph skillsPara = generateParagraph(resumeDTO.getSkills(),  paragraphFontName, paragraphFontSize,Font.DEFAULTSIZE, BaseColor.BLACK);
		if (null != skillsPara) {
			Paragraph skillsHeadingPara = generateParagraph("Skills",  paragraphFontName, sectionHeadingFontSize ,Font.BOLD, BaseColor.BLACK);
			document.add(Chunk.NEWLINE);
			document.add(skillsHeadingPara);
			document.add(skillsPara);
		}
		
		
		Paragraph languagesPara = generateLanguagesPara(resumeDTO.getListLangDTO(),  paragraphFontName, paragraphFontSize,Font.DEFAULTSIZE, BaseColor.BLACK);
		if (null != languagesPara) {
			Paragraph languageHeadingPara = generateParagraph("Language",  paragraphFontName, paragraphFontSize ,Font.BOLD, BaseColor.BLACK);
			document.add(Chunk.NEWLINE);
			document.add(languageHeadingPara);
			document.add(languagesPara);
		}
		//Section separator  - Draw Line
		drawLineSeperator(document);
		}
		
	}
	
	/**
	 * populate the Languages known by the candidate in a List format 
	 * @param listLangDTO
	 * @param paraFontName
	 * @param paraFontSize
	 * @param paraFontStyle
	 * @param fontColor
	 * @return void
	 */
	private Paragraph generateLanguagesPara(List<LanguageDTO> listLangDTO,
			String paraFontName, float paraFontSize,
			int paraFontStyle, BaseColor fontColor) {

		Paragraph languageParagraph = null;
		if ((null != listLangDTO) && (listLangDTO.size() > 0)) {

		
	        // creates tabs
	        Chunk languageTitleTab = new Chunk(new VerticalPositionMark(), Element.ALIGN_LEFT, true);
	        Chunk languageExpLvlTab = new Chunk(new VerticalPositionMark(), 350, true);
	        
	        String languageName = null;
	        String languageExpInYrs = null;
	        Paragraph singleLanguagePara = null;
			for (LanguageDTO langDTO: listLangDTO) {
				
		            // creates a paragraph with the language name
		            languageName = langDTO.getLanguage();
		            languageExpInYrs = langDTO.getExpLvl();
		          
		            	// create a Paragraph with the Language title
		            	singleLanguagePara = new Paragraph();
		               
		         
		                if (null != languageName) {
		                	// insert a tab FOR LANGUAGE NAME
		                	singleLanguagePara.add(new Chunk(languageTitleTab));
		                	singleLanguagePara.add(new Chunk(languageName));
		                }
		               
		                
		                if (null != languageName) {
		                	 // insert a tab FOR NUMBER OF YEARS
			                singleLanguagePara.add(new Chunk(languageExpLvlTab));
		                	singleLanguagePara.add(new Chunk(languageExpInYrs));
		                }

		                if (null == languageParagraph) {
		                	languageParagraph = new Paragraph();
		                }
		                languageParagraph.add(singleLanguagePara);
		                languageParagraph.add(Chunk.NEWLINE);
		        }
			

			}
	
		return languageParagraph;
		}

	/**
	 * Create Certification Section section on the PDF and populate the Candaidate's registered certification information
	 * @param document
	 * @param resumeDTO
	 */
	private void populateCertificationSection(Document document,
			ResumeDTO resumeDTO) throws DocumentException {
		Paragraph certificationHeadingPara = null;
		Chunk certTab = new Chunk(new VerticalPositionMark(),  Element.ALIGN_CENTER, true);		
		if ((null != resumeDTO.getListCertDTO()) && (resumeDTO.getListCertDTO().size() > 0 )) {
			certificationHeadingPara = new Paragraph(new Chunk(Chunk.NEWLINE));
			certificationHeadingPara.add(certTab);
			certificationHeadingPara.add(generateParagraph("Certification",  paragraphFontName, sectionHeadingFontSize ,Font.BOLD, BaseColor.BLACK));
			document.add(Chunk.NEWLINE);
			document.add(certificationHeadingPara);
		
		Paragraph certificationParagraph = generateCertificationParagraph(resumeDTO.getListCertDTO(),  paragraphFontName, paragraphFontSize,Font.DEFAULTSIZE, BaseColor.BLACK);
		document.add(certificationParagraph);
		
		//Section separator  - Draw Line
		drawLineSeperator(document);
		}
		
	}

	/**
	 * Create an Certification section on the PDF and populate the Certificates of candidate 
	 * @param paraFontName
	 * @param paraFontSize
	 * @param paraFontStyle
	 * @param fontColor
	 */
	private Paragraph generateCertificationParagraph(
			List<CertificationDTO> listCertDTO, String paraFontName,
			float paraFontSize, int paraFontStyle, BaseColor fontColor) {
		Paragraph certificationParagraph = null;
		if ((null != listCertDTO) && (listCertDTO.size() > 0)) {

		
			 PdfPTable certificationTable = new PdfPTable(2);
			 
	         for (CertificationDTO certificationDTO: listCertDTO) {
	        	 
	         certificationTable.addCell("Certification Name");
	         certificationTable.addCell(certificationDTO.getCertificationName());
	         
	         certificationTable.addCell("Certificatying Authority");
	         certificationTable.addCell(certificationDTO.getCertifyingAuthority());

	     	 certificationTable.addCell("Institution Name");
	         certificationTable.addCell(certificationDTO.getInstituteName());

	         certificationTable.addCell("Received Date");
	         certificationTable.addCell(certificationDTO.getDateOfReceipt());
	        
	     	 certificationTable.addCell("Summary");
	         certificationTable.addCell(certificationDTO.getSummary());
	        
	     	}
	
	         if (null == certificationParagraph) {
	        	 certificationParagraph = new Paragraph();
	         }
	         certificationParagraph.add(certificationTable);
		}
	         
		return certificationParagraph;
	}

	/**
	 * Create an EducationSection section on the PDF and populate the Candidate's education information
	 * @param document
	 * @param resumeDTO
	 */
	private void populateEducationSection(Document document, ResumeDTO resumeDTO) throws DocumentException {
		Paragraph educationHeadingPara = null;
		Chunk expTab = new Chunk(new VerticalPositionMark(),  Element.ALIGN_CENTER, true);		
		if ((null != resumeDTO.getListEduDTO()) && (resumeDTO.getListEduDTO().size() > 0 )) {
			educationHeadingPara = new Paragraph(new Chunk(Chunk.NEWLINE));
			educationHeadingPara.add(expTab);
			educationHeadingPara.add(generateParagraph("Education",  paragraphFontName, sectionHeadingFontSize ,Font.BOLD, BaseColor.BLACK));
			document.add(Chunk.NEWLINE);
			document.add(educationHeadingPara);
		
		Paragraph educationParagraph = generateEducationParagraph(resumeDTO.getListEduDTO(),  paragraphFontName, paragraphFontSize,Font.DEFAULTSIZE, BaseColor.BLACK);
		document.add(educationParagraph);
		
		//Section separator  - Draw Line
		drawLineSeperator(document);
		}
		
	}

	/**
	 * Create an Education Section on the PDF and populate the Education Related information
	 * @param paraFontName
	 * @param paraFontSize
	 * @param paraFontStyle
	 * @param fontColor
	 */
	private Paragraph generateEducationParagraph(List<EducationDTO> listEduDTO,
			String paraFontName, float paraFontSize,
			int paraFontStyle, BaseColor fontColor) {
		Paragraph educationParagraph = null;
		if ((null != listEduDTO) && (listEduDTO.size() > 0)) {

		
			 PdfPTable educationTable = new PdfPTable(2);
			 
	         for (EducationDTO educationDTO: listEduDTO) {
	        	 
	         educationTable.addCell("Institution Name");
	         educationTable.addCell(educationDTO.getInstituteName());
	         
	         educationTable.addCell("Degree Level");
	         educationTable.addCell(educationDTO.getDegreeLvl());
	         
	         educationTable.addCell("Field Of Study");
	         educationTable.addCell(educationDTO.getFieldOfStudy());
	        
	     	 educationTable.addCell("Start Date");
	         educationTable.addCell(educationDTO.getStartDate());
	        
	     	 educationTable.addCell("End Date");
	         educationTable.addCell(educationDTO.getEndDate());
	        
	     	 educationTable.addCell("Degree");
	         educationTable.addCell(educationDTO.getDegrees());
	        
	     	 educationTable.addCell("Certifications");
	         educationTable.addCell(educationDTO.getCertifications());
	        
	     	}
	
	         if (null == educationParagraph) {
	        	 educationParagraph = new Paragraph();
	         }
	         educationParagraph.add(educationTable);
		}
	         
		return educationParagraph;
	}

	/**
	 * Create an WorkExperienceSection section on the PDF and populate the Previous Work Related information
	 * @param document
	 * @param resumeDTO
	 */
	private void populateWorkExperienceSection(Document document,
			ResumeDTO resumeDTO)  throws DocumentException {
		//Creating the Work Experience Section
		Chunk expTab = new Chunk(new VerticalPositionMark(),  Element.ALIGN_CENTER, true);		
		if ((null != resumeDTO.getListWorkExpDTO()) && (resumeDTO.getListWorkExpDTO().size() > 0 )) {
		Paragraph workExpHeadingPara = new Paragraph(new Chunk(Chunk.NEWLINE));
		workExpHeadingPara.add(expTab);
		workExpHeadingPara.add(generateParagraph("Work Experience",  paragraphFontName, sectionHeadingFontSize ,Font.BOLD, BaseColor.BLACK));
		document.add(Chunk.NEWLINE);
		document.add(workExpHeadingPara);
		
		Paragraph workExpParagraph = generateWorkExpParagraph(resumeDTO.getListWorkExpDTO(),  paragraphFontName, paragraphFontSize,Font.DEFAULTSIZE, BaseColor.BLACK);
		document.add(workExpParagraph);
		
		//Section separator  - Draw Line
		drawLineSeperator(document);
		}
	}

	/**
	 * Generates the work Experience Pragraph in Tabular format
	 * @param objective
	 * @param paraFontName
	 * @param paraFontSize
	 * @param paraFontStyle
	 * @param fontColor
	 * @return
	 */
	private Paragraph generateWorkExpParagraph(List<WorkExpDTO> workExpDTOLst, String paraFontName, float paraFontSize,
			int paraFontStyle, BaseColor fontColor) {
		Paragraph workExpParagraph = null;
		if ((null != workExpDTOLst) && (workExpDTOLst.size() > 0)) {

			if (null == paraFontName) {
				//if theFont Name  is not set then the default Font  from the Properties File is set
				paraFontName = paragraphFontName;
			}
			
			if (paraFontSize == 0) {
				//set the default Font Style from the properties file
				paraFontSize = paragraphFontSize;
			}
			
			
			if (paraFontStyle == 0) {
				//if the fontStyle is not set then the default fontStyle from the Font class
				paraFontStyle = Font.DEFAULTSIZE;
			}
			
			if (null != fontColor) {
				//if the font Color is not set then the default Font color - black is set
				fontColor = BaseColor.BLACK;
			}
		
			 PdfPTable workExpTable = new PdfPTable(2);
			 
	         for (WorkExpDTO workExpDTO: workExpDTOLst) {
	        	 
	         workExpTable.addCell("Job Title");
	         workExpTable.addCell(workExpDTO.getJobTitle());
	         
	         workExpTable.addCell("employer Name");
	         workExpTable.addCell(workExpDTO.getEmployerName());
	         
	         workExpTable.addCell("Employement Type");
	         workExpTable.addCell(workExpDTO.getEmploymentType());
	        
	     	 workExpTable.addCell("Start Date");
	         workExpTable.addCell(workExpDTO.getStartDate());
	        
	     	 workExpTable.addCell("End Date");
	         workExpTable.addCell(workExpDTO.getEndDate());
	        
	     	 workExpTable.addCell("Years at Position");
	         workExpTable.addCell(workExpDTO.getYrsAtPostion());
	        
	     	 workExpTable.addCell("Current Level");
	         workExpTable.addCell(workExpDTO.getCurrentCareerLvl());
	        
	     	 workExpTable.addCell("Annual Salary");
	         workExpTable.addCell(workExpDTO.getAnnualSalary());
	        
	     	 workExpTable.addCell("Hourly Pay Rate");
	         workExpTable.addCell(workExpDTO.getHrlyPayRate());
	        
	     	 workExpTable.addCell("Summary / Job Description");
	         workExpTable.addCell(workExpDTO.getDescription());
	        
	     	 workExpTable.addCell("City");
	         workExpTable.addCell(workExpDTO.getCity());
	        
	     	 workExpTable.addCell("State");
	         workExpTable.addCell(workExpDTO.getState());
	        
	     	 workExpTable.addCell("Coutry");
	         workExpTable.addCell(workExpDTO.getCountry());
	        
	     	 workExpTable.addCell("Still Employed");
	         workExpTable.addCell(new Boolean(workExpDTO.getStillEmployed()==0?false:true).toString());
	        
	     	 workExpTable.addCell("Is this my current career level");
	         workExpTable.addCell(new Boolean(workExpDTO.isbCurrentCareerLevel()).toString());
	        
	     	 workExpTable.addCell("Present");
	         workExpTable.addCell(new Boolean(workExpDTO.isbPresent()).toString());

			}
	
	         if (null == workExpParagraph) {
	        	 workExpParagraph = new Paragraph();
	         }
	         workExpParagraph.add(workExpTable);
		}
	         
		return workExpParagraph;
	}

	/**
	 * Create an Objective section on the PDF and populate the objective information
	 * @param document
	 * @param resumeDTO
	 * @throws DocumentException
	 */
	private void populateObjectiveSection(Document document, ResumeDTO resumeDTO)
			throws DocumentException {
		//Creating the Objective Section
		Chunk objectiveTab = new Chunk(new VerticalPositionMark(), Element.ALIGN_CENTER, true);	
		if ((null != resumeDTO.getObjective()) && (resumeDTO.getObjective().trim().length() > 0 )) {
		Paragraph objectiverHeadingPara = new Paragraph(Chunk.NEWLINE);
		objectiverHeadingPara.add(objectiveTab);
		objectiverHeadingPara.add(generateParagraph("Objective",  paragraphFontName, sectionHeadingFontSize ,Font.BOLD, BaseColor.BLACK));
		document.add(objectiverHeadingPara);
		
		Paragraph paragraph = generateParagraph(resumeDTO.getObjective(),  paragraphFontName, paragraphFontSize,Font.DEFAULTSIZE, BaseColor.BLACK);
		document.add(Chunk.NEWLINE);
		document.add(paragraph);
		
		//Section separator  - Draw Line
		drawLineSeperator(document);
		}
	}

	/**
	 * Draw a line to mark separation between the sections
	 */
	private void drawLineSeperator(Document document) throws DocumentException {
		LineSeparator sectionSeperator = new LineSeparator(1, 100, BaseColor.BLACK, Element.ALIGN_CENTER, -2);
		document.add(Chunk.NEWLINE);
		document.add(sectionSeperator);
	}

	/**
	 * Retrieve the values from the addressDTO that has been set for the given candidate and 
	 * place these values into the address section in the pdf resume 
	 * @param document
	 * @param addressDTO
	 * @throws DocumentException
	 */
	private void populateCandidateAddressInfo(Document document,
			AddressDTO addressDTO) throws DocumentException {
		Paragraph addressPara = null;
		Chunk candidateAddressTab = new Chunk(new VerticalPositionMark(),  Element.ALIGN_CENTER, true);
		// add Address1
		if ((null != addressDTO.getAddress1()) && (addressDTO.getAddress1().trim().length() > 0)) {
			addressPara = new Paragraph(candidateAddressTab);
			addressPara.add(new Chunk(addressDTO.getAddress1(), FontFactory.getFont(paragraphFontName)));
		}
		
		//address2 - NOT MANDATORY
		if((null != addressDTO.getAddress2()) && (addressDTO.getAddress2().trim().length() > 0)) {
			if (null == addressPara) {
				addressPara = new Paragraph();
			} 
			addressPara.add(new Chunk(", "));
			addressPara.add(new Chunk(addressDTO.getAddress2(), FontFactory.getFont(paragraphFontName)));
		}
		
		// Street
		if((null != addressDTO.getStreet()) && (addressDTO.getStreet().trim().length() > 0)) {
			if (null == addressPara) {
				addressPara = new Paragraph();
				addressPara.add(candidateAddressTab);
			} else {
				addressPara.add(new Chunk(", "));
			}
			
			addressPara.add(new Chunk(addressDTO.getStreet(), FontFactory.getFont(paragraphFontName)));
		}
		
		//City
		if((null != addressDTO.getCity()) && (addressDTO.getCity().trim().length() > 0)) {
			if (null == addressPara) {
				addressPara = new Paragraph();
				addressPara.add(candidateAddressTab);
			} else {
				addressPara.add(new Chunk(", "));
			}	
			addressPara.add(new Chunk(addressDTO.getCity(), FontFactory.getFont(paragraphFontName)));
		}
		
		//State
		if((null != addressDTO.getState()) && (addressDTO.getState().trim().length() > 0)) {
			if (null == addressPara) {
				addressPara = new Paragraph();
				addressPara.add(candidateAddressTab);
			} else {
				addressPara.add(new Chunk(", "));
			}
			addressPara.add(new Chunk(addressDTO.getState(), FontFactory.getFont(paragraphFontName)));
		}

		// ZipCode
		if((null != addressDTO.getZipCode()) && (addressDTO.getZipCode().trim().length() > 0)) {
			if (null == addressPara) {
				addressPara = new Paragraph();
				addressPara.add(candidateAddressTab);
			} else {
				addressPara.add(new Chunk("-"));
			}
			addressPara.add(new Chunk(addressDTO.getZipCode(), FontFactory.getFont(paragraphFontName)));
		}
		
		if((null != addressDTO.getCountry()) && (addressDTO.getCountry().trim().length() > 0)) {
			if (null == addressPara) {
				addressPara = new Paragraph();
				addressPara.add(candidateAddressTab);
			} else {
				addressPara.add(new Chunk(", "));
			}
			addressPara.add(new Chunk(addressDTO.getCountry(), FontFactory.getFont(paragraphFontName)));
		}
		
		if(null != addressPara) {
			addressPara.add(Chunk.NEWLINE);
			document.add(addressPara);
		}
	}

	/**
	 * Retrieve the values from the contact numbers from addressDTO that has been set for the given candidate and 
	 * place these values into the contact information  section in the pdf resume 
	 * @param document
	 * @param addressDTO
	 * @throws DocumentException
	 */
	private void populateCandidateContactNumbers(Document document, AddressDTO addressDTO)
			throws DocumentException {
		Paragraph contactPara = null;
		Chunk candidateAddressTab = new Chunk(new VerticalPositionMark(), Element.ALIGN_CENTER, true);
		
		// Contact Information- Mobile Number  - NOT MANDATORY
		if((null != addressDTO.getMobileNumber()) && (addressDTO.getMobileNumber().trim().length() > 0)) {
			contactPara = new Paragraph(candidateAddressTab);
			contactPara.add(new Chunk(addressDTO.getMobileNumber(), FontFactory.getFont(paragraphFontName)));
		}
		
		//phone - NOT MANDATORY
		if((null != addressDTO.getPhone()) && (addressDTO.getPhone().trim().length() > 0)) {
			if (null ==  contactPara) {
				contactPara = new Paragraph();
				contactPara.add(candidateAddressTab);
			} else {
				contactPara.add(new Chunk(", "));
			}
			contactPara.add(new Chunk(addressDTO.getPhone(), FontFactory.getFont(paragraphFontName)));
		}
		
		if (null != contactPara) {
			contactPara.add(Chunk.NEWLINE);
			document.add(contactPara);
		}
		
		//Section separator  - Draw Line
		drawLineSeperator(document);
	}

	/**
	 * Retrieve the values from the contactInfoDTO that has been set for the given candidate and 
	 * place these values into the contact section in the pdf resume. Values like the candidates first name,
	 * last name are retrieved and displayed in the PDF in this section. 
	 * @param document
	 * @param contactInfoDTO
	 * @throws DocumentException
	 */
	private void populateCandidateInfoSection(Document document,
			ContactInformationDTO contactInfoDTO) throws DocumentException {
		/** Candidate Name 
		 * 			  Address 
		 * 			  Contact 
		 * Section
		 * 	
		 */
		Chunk candidateNameTab = new Chunk(new VerticalPositionMark(), Element.ALIGN_CENTER, true);	
		//Candidate Name Information
		//First Name
		Paragraph candidateNamePara =  new Paragraph(new Chunk(candidateNameTab));
		candidateNamePara.add(new Chunk(contactInfoDTO.getFirstName(), FontFactory.getFont(sectionHeadingFontName)));
		
		//Middle Name - NOT MANDATORY 
		if(null != contactInfoDTO.getMiddleNameInitial() && (contactInfoDTO.getMiddleNameInitial().trim().length() > 0)) {
			candidateNamePara.add(new Chunk(" "));
			candidateNamePara.add(new Chunk(contactInfoDTO.getMiddleNameInitial(), FontFactory.getFont(sectionHeadingFontName)));
		}

		//Last Name 
		if(null != contactInfoDTO.getLastName() && (contactInfoDTO.getLastName().trim().length() > 0)) {
			candidateNamePara.add(new Chunk(" "));
			candidateNamePara.add(new Chunk(contactInfoDTO.getLastName(), FontFactory.getFont(sectionHeadingFontName)));
		}
		
		if (null !=  candidateNamePara) {
			document.add(candidateNamePara);
		}
	}

	/**
	 * Method to create a Document Exception and throw to the business class that calls the PDF Generator
	 * @param excetionMessage the message to be set to the Exception Class
	 * @throws DocumentException 
	 */
	private void createAndThrowCreatePDFException(String excetionMessage) throws DocumentException {
		throw new DocumentException(excetionMessage);
	}

	/**
	 * Common method to generate a paragraph in the document being created with default font and colors
	 */

	private Paragraph generateParagraph(String paragraphText, String paraFontName, float paraFontSize,  int paraFontStyle, BaseColor fontColor) {
		
		Paragraph paragraph = null;
		if (null != paragraphText) {

			if (null == paraFontName) {
				//if theFont Name  is not set then the default Font  from the Properties File is set
				paraFontName = paragraphFontName;
			}
			
			if (paraFontSize == 0) {
				//set the default Font Style from the properties file
				paraFontSize = paragraphFontSize;
			}
			
			
			if (paraFontStyle == 0) {
				//if the fontStyle is not set then the default fontStyle from the Font class
				paraFontStyle = Font.DEFAULTSIZE;
			}
			
			if (null != fontColor) {
				//if the font Color is not set then the default Font color - black is set
				fontColor = BaseColor.BLACK;
			}
			
			
			paragraph = new Paragraph(paragraphText,FontFactory.getFont(paraFontName, paraFontSize , paraFontStyle, fontColor));
	
		}
		return paragraph;
	}



}
