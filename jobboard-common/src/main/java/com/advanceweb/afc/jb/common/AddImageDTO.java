/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * @author HarshaV
 * @Version 1.0
 * @Since 30th Aug, 2012
 */

public class AddImageDTO {

	/** The add image file data. */
	private CommonsMultipartFile addImageFileData;
	
	/** The media type. */
	private String mediaType;
	
	/** The media path. */
	private String mediaPath;
	
	/** The add image id. */
	private int addImageId;

	/**
	 * Gets the adds the image file data.
	 *
	 * @return the adds the image file data
	 */
	public CommonsMultipartFile getAddImageFileData() {
		return addImageFileData;
	}

	/**
	 * Sets the adds the image file data.
	 *
	 * @param addImageFileData the new adds the image file data
	 */
	public void setAddImageFileData(CommonsMultipartFile addImageFileData) {
		this.addImageFileData = addImageFileData;
	}

	/**
	 * Gets the media type.
	 *
	 * @return the media type
	 */
	public String getMediaType() {
		return mediaType;
	}

	/**
	 * Sets the media type.
	 *
	 * @param mediaType the new media type
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * Gets the media path.
	 *
	 * @return the media path
	 */
	public String getMediaPath() {
		return mediaPath;
	}

	/**
	 * Sets the media path.
	 *
	 * @param mediaPath the new media path
	 */
	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	/**
	 * Gets the adds the image id.
	 *
	 * @return the adds the image id
	 */
	public int getAddImageId() {
		return addImageId;
	}

	/**
	 * Sets the adds the image id.
	 *
	 * @param addImageId the new adds the image id
	 */
	public void setAddImageId(int addImageId) {
		this.addImageId = addImageId;
	}

	
}
