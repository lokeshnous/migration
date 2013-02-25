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

public class VideoDTO {

	/** The video file data. */
	private CommonsMultipartFile videoFileData;
	
	/** The media type. */
	private String mediaType;
	
	/** The media path. */
	private String mediaPath;
	
	/** The video id. */
	private int videoId;
	
	/**
	 * Gets the video file data.
	 *
	 * @return the video file data
	 */
	public CommonsMultipartFile getVideoFileData() {
		return videoFileData;
	}

	/**
	 * Sets the video file data.
	 *
	 * @param videoFileData the new video file data
	 */
	public void setVideoFileData(CommonsMultipartFile videoFileData) {
		this.videoFileData = videoFileData;
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
	 * Gets the video id.
	 *
	 * @return the video id
	 */
	public int getVideoId() {
		return videoId;
	}

	/**
	 * Sets the video id.
	 *
	 * @param videoId the new video id
	 */
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

}
