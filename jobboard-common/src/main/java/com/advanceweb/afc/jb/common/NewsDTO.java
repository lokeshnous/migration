package com.advanceweb.afc.jb.common;

/**
 * <code>NewsDTO</code>This bean for keeping the attributes of 
 * News related attributes.
 * @author Reetesh RN
 * @version 1.0
 * @since 22nd September 2012
 * 
 */

public class NewsDTO {

	private String title;
	private String link;
	private String description;
	private String facility;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	 
	
}
