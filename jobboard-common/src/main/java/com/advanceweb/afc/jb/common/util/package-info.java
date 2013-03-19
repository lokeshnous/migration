@XmlSchema (
	       xmlns={
	    		   @XmlNs(prefix="image" , 
	    				    namespaceURI="http://www.google.com/schemas/sitemap-image/1.1"),
	    		   @XmlNs(prefix="xsi" , 
	    		   			namespaceURI="http://www.w3.org/2001/XMLSchema-instance"),
	       },
	       namespace="http://www.sitemaps.org/schemas/sitemap/0.9",
	      elementFormDefault = XmlNsForm.QUALIFIED)

package com.advanceweb.afc.jb.common.util;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;