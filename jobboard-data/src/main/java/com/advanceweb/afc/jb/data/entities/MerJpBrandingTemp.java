/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The jp brand temp id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="jp_brand_temp_id")
	private int jpBrandTempId;

	/** The user id. */
	@Column(name="user_id")
	private int userId;

	/** The description. */
	@Column(name="jp_brand_description")
	private String description;

	/** The image path. */
	@Column(name="jp_brand_image_temp_path")
	private String imagePath;
	
	/** The logo path. */
	@Column(name="jp_brand_logo_path")
	private String logoPath;
	
	/** The color. */
	@Column(name="jp_brand_color")
	private String color;

    /** The created date. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
    
    /** The updated date. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name="updated_date")
    private Date updatedDate;


	//bi-directional many-to-one association to JpSource
    /** The mer user. */
	@ManyToOne
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	private MerUser merUser;


	/**
	 * Gets the jp brand temp id.
	 *
	 * @return the jp brand temp id
	 */
	public int getJpBrandTempId() {
		return jpBrandTempId;
	}


	/**
	 * Sets the jp brand temp id.
	 *
	 * @param jpBrandTempId the new jp brand temp id
	 */
	public void setJpBrandTempId(int jpBrandTempId) {
		this.jpBrandTempId = jpBrandTempId;
	}


	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}


	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Gets the image path.
	 *
	 * @return the image path
	 */
	public String getImagePath() {
		return imagePath;
	}


	/**
	 * Sets the image path.
	 *
	 * @param imagePath the new image path
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	/**
	 * Gets the logo path.
	 *
	 * @return the logo path
	 */
	public String getLogoPath() {
		return logoPath;
	}


	/**
	 * Sets the logo path.
	 *
	 * @param logoPath the new logo path
	 */
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}


	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return color;
	}


	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(String color) {
		this.color = color;
	}


	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}


	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	/**
	 * Gets the updated date.
	 *
	 * @return the updated date
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}


	/**
	 * Sets the updated date.
	 *
	 * @param updatedDate the new updated date
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	/**
	 * Gets the mer user.
	 *
	 * @return the mer user
	 */
	public MerUser getMerUser() {
		return merUser;
	}


	/**
	 * Sets the mer user.
	 *
	 * @param merUser the new mer user
	 */
	public void setMerUser(MerUser merUser) {
		this.merUser = merUser;
	}


	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
}