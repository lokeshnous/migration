package com.advanceweb.afc.jb.data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * anilm
 * @version 1.0
 * @created Jul 20, 2012
 */
@Entity
@Table(name="adm_branding_temp")
public class AdmBrandingTemp {
	
	int brandTempId;
	int userId;
	String templateName;
	String brandOverview;
	byte[] templateImage;
	byte[] templateLogo;
	String brandColor;
	Date createdDate;
	Date modifiedDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="jp_brand_temp_id")
	public int getBrandTempId() {
		return brandTempId;
	}
	public void setBrandTempId(int brandTempId) {
		this.brandTempId = brandTempId;
	}
	
	@Column(name="user_id")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name="jp_template_name")
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	@Column(name="jp_brand_overview")
	public String getBrandOverview() {
		return brandOverview;
	}
	public void setBrandOverview(String brandOverview) {
		this.brandOverview = brandOverview;
	}
	
	@Column(name="jp_brand_image_temp_path")
	public byte[] getTemplateImage() {
		return templateImage;
	}
	public void setTemplateImage(byte[] templateImage) {
		this.templateImage = templateImage;
	}
	
	@Column(name="jp_brand_logo_path")
	public byte[] getTemplateLogo() {
		return templateLogo;
	}
	public void setTemplateLogo(byte[] templateLogo) {
		this.templateLogo = templateLogo;
	}
	
	@Column(name="jp_brand_color")
	public String getBrandColor() {
		return brandColor;
	}
	public void setBrandColor(String brandColor) {
		this.brandColor = brandColor;
	}
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
