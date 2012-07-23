package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * anilm
 * @version 1.0
 * @created Jul 20, 2012
 */
public class AdmBrndngTempDTO {

	int brandTempId;
	int userId;
	String templateName;
	String brandOverview;
	byte[] templateImage;
	byte[] templateLogo;
	String brandColor;
	Date createdDate;
	Date modifiedDate;
	
	public int getBrandTempId() {
		return brandTempId;
	}
	public void setBrandTempId(int brandTempId) {
		this.brandTempId = brandTempId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getBrandOverview() {
		return brandOverview;
	}
	public void setBrandOverview(String brandOverview) {
		this.brandOverview = brandOverview;
	}
	public byte[] getTemplateImage() {
		return templateImage;
	}
	public void setTemplateImage(byte[] templateImage) {
		this.templateImage = templateImage;
	}
	public byte[] getTemplateLogo() {
		return templateLogo;
	}
	public void setTemplateLogo(byte[] templateLogo) {
		this.templateLogo = templateLogo;
	}
	public String getBrandColor() {
		return brandColor;
	}
	public void setBrandColor(String brandColor) {
		this.brandColor = brandColor;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}	
	
}
