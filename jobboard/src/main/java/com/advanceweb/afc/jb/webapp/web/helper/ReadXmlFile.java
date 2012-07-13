package com.advanceweb.afc.jb.webapp.web.helper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.advanceweb.afc.jb.webapp.web.forms.xmldata.CareersToolResource;
import com.advanceweb.afc.jb.webapp.web.forms.xmldata.HealthCareNew;

public class ReadXmlFile {

	public static List xmlParser(String file) {
		List list = null;
		try {
			File fXmlFile = new File(file);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			if(file.equalsIgnoreCase(Constants.XML_FILE_NAME_HEALTHCARE)){
				NodeList nList = doc.getElementsByTagName("healthcarenew");
				list=setHealthCareNewValue(nList);
			}
			if(file.equalsIgnoreCase(Constants.XML_FILE_NAME_CAREERTOOL)){
				NodeList nList = doc.getElementsByTagName("careertoolresource");
				list=setCareersToolResource(nList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	private static List<HealthCareNew> setHealthCareNewValue(NodeList nList) {
		List<HealthCareNew> HealthCareNewlist=new ArrayList<HealthCareNew>();
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				HealthCareNew healthCareNew=new HealthCareNew();
				Element eElement = (Element) nNode;
				healthCareNew.setId(Integer.parseInt(getTagValue("id", eElement)));
				healthCareNew.setContent(getTagValue("content", eElement));
				healthCareNew.setUrl(getTagValue("url", eElement));
				HealthCareNewlist.add(healthCareNew);

			}
		}
		return HealthCareNewlist;
	}

	private static List<CareersToolResource> setCareersToolResource(NodeList nList) {
		List<CareersToolResource> CareersToolResourcelist=new ArrayList<CareersToolResource>();
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				CareersToolResource careersToolResource=new CareersToolResource();
				Element eElement = (Element) nNode;
				careersToolResource.setId(Integer.parseInt(getTagValue("id", eElement)));
				careersToolResource.setContent(getTagValue("content", eElement));
				careersToolResource.setUrl(getTagValue("url", eElement));
				careersToolResource.setName(getTagValue("name", eElement));
				careersToolResource.setDescription(getTagValue("description", eElement));
				CareersToolResourcelist.add(careersToolResource);

			}
		}
		return CareersToolResourcelist;
	}


	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		String value;
		Node nValue = (Node) nlList.item(0);
		try{
			value=nValue.getNodeValue();
		}catch (Exception e) {
			value="";
		}
		return value;
	}


}
