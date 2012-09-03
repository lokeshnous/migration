package com.advanceweb.afc.jb.employer.web.controller;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * @author HarshaV
 * @Version 1.0
 * @Since 30th Aug, 2012
 */

public class AddImageForm {

	private CommonsMultipartFile addImageFileData;
	private String mediaType;
	private String mediaPath;
	
	public CommonsMultipartFile getAddImageFileData() {
		return addImageFileData;
	}

	public void setAddImageFileData(CommonsMultipartFile addImageFileData) {
		this.addImageFileData = addImageFileData;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	
}
