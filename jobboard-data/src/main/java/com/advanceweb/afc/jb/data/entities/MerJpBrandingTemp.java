package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the mer_jp_branding_temp database table.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 */
@Entity
@Table(name="mer_jp_branding_temp")
public class MerJpBrandingTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="jp_brand_temp_id")
	private int jpBrandTempId;

	@Column(name="user_id")
	private int userId;

	@Column(name="jp_brand_description")
	private String description;

	@Column(name="jp_brand_image_temp_path")
	private String imagePath;
	
	@Column(name="jp_brand_logo_path")
	private String logoPath;
	
	@Column(name="jp_brand_color")
	private String color;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
    
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name="updated_date")
    private Date updatedDate;


	//bi-directional many-to-one association to JpSource
    @ManyToOne
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	private MerUser merUser;


	public int getJpBrandTempId() {
		return jpBrandTempId;
	}


	public void setJpBrandTempId(int jpBrandTempId) {
		this.jpBrandTempId = jpBrandTempId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public String getLogoPath() {
		return logoPath;
	}


	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	public MerUser getMerUser() {
		return merUser;
	}


	public void setMerUser(MerUser merUser) {
		this.merUser = merUser;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	
}