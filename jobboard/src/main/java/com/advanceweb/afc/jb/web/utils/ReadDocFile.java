package com.advanceweb.afc.jb.web.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.HeaderStories;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReadDocFile {

	public static void readMyDocument(String fileName,StringBuffer strbuffer ){
		POIFSFileSystem fs = null;
		try {
			fs = new POIFSFileSystem(new FileInputStream(fileName));
			HWPFDocument doc = new HWPFDocument(fs);

			/** Read the content **/
			readParagraphs(doc,strbuffer);

			int pageNumber=1;

			/** We will try reading the header for page 1**/
			readHeader(doc, pageNumber);

			/** Let's try reading the footer for page 1**/
			readFooter(doc, pageNumber);

			/** Read the document summary**/
			readDocumentSummary(doc);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}  

	public static void readParagraphs(HWPFDocument doc,StringBuffer strbuffer) throws Exception{
		WordExtractor we = new WordExtractor(doc);

		/**Get the total number of paragraphs**/
		String[] paragraphs = we.getParagraphText();
		// System.out.println("Total Paragraphs: "+paragraphs.length);

		for (int i = 0; i < paragraphs.length; i++) {

			//System.out.println("Length of paragraph "+(i +1)+": "+ paragraphs[i].length());
			strbuffer.append(paragraphs[i].toString());

		}

	}

	public static void readHeader(HWPFDocument doc, int pageNumber){
		HeaderStories headerStore = new HeaderStories( doc);
		String header = headerStore.getHeader(pageNumber);

	}

	public static void readFooter(HWPFDocument doc, int pageNumber){
		HeaderStories headerStore = new HeaderStories( doc);
		String footer = headerStore.getFooter(pageNumber);

	}

	public static void readDocumentSummary(HWPFDocument doc) {
//		DocumentSummaryInformation summaryInfo=doc.getDocumentSummaryInformation();
//		String category = summaryInfo.getCategory();
//		String company = summaryInfo.getCompany();
//		int lineCount=summaryInfo.getLineCount();
//		int sectionCount=summaryInfo.getSectionCount();
//		int slideCount=summaryInfo.getSlideCount();

		/*      System.out.println("---------------------------");
        System.out.println("Category: "+category);
        System.out.println("Company: "+company);
        System.out.println("Line Count: "+lineCount);
        System.out.println("Section Count: "+sectionCount);
        System.out.println("Slide Count: "+slideCount);
		 */
	}
	
	public static void DocxFileReader(String fileName,StringBuffer strbuffer ) {
		// TODO Auto-generated method stub
		try{
			
			org.apache.poi.POITextExtractor extractor = ExtractorFactory.createExtractor(new File(fileName));
			String mess = extractor.getText();
			strbuffer.append(mess);

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}


}
