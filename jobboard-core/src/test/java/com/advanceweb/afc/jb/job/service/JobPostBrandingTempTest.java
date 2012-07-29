package com.advanceweb.afc.jb.job.service;

import static org.junit.Assert.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.AdmBrndngTempDTO;

/**
 * anilm
 * 
 * @version 1.0
 * @created Jul 20, 2012
 */
public class JobPostBrandingTempTest extends ServiceTest {

	@Autowired
	JobPostBrandingTemplate jobPostBrandingTempl;

	private static final int IMG_WIDTH = 200;
	private static final int IMG_HEIGHT = 200;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.job.service.impl.JobPostBrandingTemplateImpl#createTemaplate(com.advanceweb.afc.jb.common.AdmBrndngTempDTO)}
	 * .
	 */
	@Ignore("image paths hard coded")
	@Test
	public void testCreateTemaplate() {
		AdmBrndngTempDTO templateDTO = new AdmBrndngTempDTO();

		try {
			// save image into database
			File file = new File("C://Development/images/ablogo.jpg");

			BufferedImage originalImage = ImageIO.read(file);
			
			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			
			BufferedImage resizeImageJpg = resizeImage(originalImage, type);
			ImageIO.write(resizeImageJpg, "jpg", new File("C://Development/images/ablogo123.jpg"));
			
			File file1 = new File("C://Development/images/ablogo123.jpg");
			byte[] bFile = new byte[(int) file1.length()];

			FileInputStream fileInputStream = new FileInputStream(file1);
			// convert file into array of bytes
			fileInputStream.read(bFile);
			fileInputStream.close();


			templateDTO.setUserId(1);
			templateDTO.setTemplateName("Template 1");
			templateDTO
					.setBrandOverview("brandOverview  brandOverview brandOverview brandOverview brandOverview");
			templateDTO.setTemplateImage(bFile);
			templateDTO.setTemplateLogo(bFile);
			templateDTO.setBrandColor("#FFFFF");
			templateDTO.setCreatedDate(new Date());
			templateDTO.setModifiedDate(new Date());

			jobPostBrandingTempl.createTemaplate(templateDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// jobPostBrandingTempl.createTemaplate(templateDTO);
	}
	
	private static BufferedImage resizeImage(BufferedImage originalImage, int type){
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
	 
		return resizedImage;
	}

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.job.service.impl.JobPostBrandingTemplateImpl#updateTemplate(com.advanceweb.afc.jb.common.AdmBrndngTempDTO)}
	 * .
	 */
	@Ignore("image paths hard coded")
	@Test
	public void testUpdateTemplate() {
		AdmBrndngTempDTO templateDTO = new AdmBrndngTempDTO();

		try {
			// save image into database
			File file = new File("C://Development/images/PAN.jpg");

			BufferedImage originalImage = ImageIO.read(file);
			
			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			
			BufferedImage resizeImageJpg = resizeImage(originalImage, type);
			ImageIO.write(resizeImageJpg, "jpg", new File("C://Development/images/image.jpg"));
			
			File file1 = new File("C://Development/images/image.jpg");
			byte[] bFile = new byte[(int) file1.length()];

			FileInputStream fileInputStream = new FileInputStream(file1);
			// convert file into array of bytes
			fileInputStream.read(bFile);
			fileInputStream.close();

			templateDTO.setBrandTempId(4);
			templateDTO.setUserId(1);
			templateDTO.setTemplateName("Template 1");
			templateDTO
					.setBrandOverview("brandOverview 1  brandOverview 2");
			templateDTO.setTemplateImage(bFile);
			templateDTO.setTemplateLogo(bFile);
			templateDTO.setBrandColor("#CCCCC");
			templateDTO.setCreatedDate(new Date());
			templateDTO.setModifiedDate(new Date());

			jobPostBrandingTempl.updateTemplate(templateDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.job.service.impl.JobPostBrandingTemplateImpl#deleteTemplate(int)}
	 * .
	 */
	@Ignore("image paths hard coded")
	@Test
	public void testDeleteTemplate() {
		
		jobPostBrandingTempl.deleteTemplate(6);
		
	}

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.job.service.impl.JobPostBrandingTemplateImpl#retrieveAllTemplates(int)}
	 * .
	 */
	@Test
	public void testRetrieveAllTemplates() {
		List<AdmBrndngTempDTO> brandTempList= jobPostBrandingTempl.retrieveAllTemplates(1);
		System.out.println(brandTempList.size());
		
	}

}
