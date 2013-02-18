package com.advanceweb.afc.jb.employer.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.advanceweb.afc.jb.common.NewsDTO;
import com.advanceweb.afc.jb.employer.service.EmployerNewsFeedService;

/**
 * <code> EmployerNewsFeedServiceImpl </code> is the service class for reading
 * the XML news feed.
 * 
 * @author Reetesh RN
 * @version 1.0
 * @since 22nd September 2012
 * 
 */

@Service("employerNewsFeedService")
public class EmployerNewsFeedServiceImpl implements EmployerNewsFeedService {

	private static final Logger LOGGER = Logger
			.getLogger(EmployerNewsFeedServiceImpl.class);
	
	private static final String ITEM_STRING = "item";
	private static final String TITLE_STRING = "title";
	private static final String LINK_STRING = "link";
	//private static final String DESC_STRING = "description";
	private static final String FACILITY_STRING = "facility";
	private static final String PLATINUM_LIST = "PlatinumNewsList";
	private static final String HOMEPAGE_LIST = "HomePageNewsList";
	

	@Value("${baseDirectoryPathForXMLNewsFeeds}")
	private String baseDirectoryPathForXMLNewsFeeds;

	/**
	 * This method is used to get the XML news feed in the form of NewsDTO to
	 * display for the Platinum user.
	 * 
	 * @param
	 * @return object of Map<String, List<NewsDTO>>
	 */

	public Map<String, List<NewsDTO>> getNewsFromXML() {

		Map<String, List<NewsDTO>> newsMap = new HashMap<String, List<NewsDTO>>();
		List<NewsDTO> newsDTOListForPlatinum = new ArrayList<NewsDTO>();
		List<NewsDTO> newsDTOListForHomePage = new ArrayList<NewsDTO>();
		try {

			File fXmlFile = new File(baseDirectoryPathForXMLNewsFeeds);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName(ITEM_STRING);
			for (int val = 0; val < nList.getLength(); val++) {
				Node nNode = nList.item(val);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if(Integer.parseInt(getTagValue(FACILITY_STRING, eElement)) == 0){
						NewsDTO newsDTOForHomePage = new NewsDTO();
						newsDTOForHomePage.setTitle(getTagValue(TITLE_STRING, eElement));
						newsDTOForHomePage.setLink(getTagValue(LINK_STRING, eElement));
						newsDTOListForHomePage.add(newsDTOForHomePage);
					}else{
						NewsDTO newsDTOForPlatinum = new NewsDTO();
						newsDTOForPlatinum.setTitle(getTagValue(TITLE_STRING, eElement));
						newsDTOForPlatinum.setLink(getTagValue(LINK_STRING, eElement));
						newsDTOForPlatinum.setFacility(getTagValue(FACILITY_STRING, eElement));
						newsDTOListForPlatinum.add(newsDTOForPlatinum);
					}
				}
			}
			
			newsMap.put(PLATINUM_LIST, newsDTOListForPlatinum);
			newsMap.put(HOMEPAGE_LIST, newsDTOListForHomePage);

		} catch (Exception e) {
			LOGGER.error("Error occurred while parsing the XML."+e);
		}
		
		
		return newsMap;
	}

	/**
	 * This method is used to get the node value from the node list.
	 * @param sTag
	 * @param eElement
	 * @return
	 */
	
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

}
