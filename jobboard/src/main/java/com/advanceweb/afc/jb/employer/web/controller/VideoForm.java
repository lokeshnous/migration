package com.advanceweb.afc.jb.employer.web.controller;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * @author HarshaV
 * @Version 1.0
 * @Since 30th Aug, 2012
 */

public class VideoForm {

	private CommonsMultipartFile videoFileData;
	private String mediaType;
	private String mediaPath;
	private String chosenVideo;
	private int videoId;
	
	public CommonsMultipartFile getVideoFileData() {
		return videoFileData;
	}

	public void setVideoFileData(CommonsMultipartFile videoFileData) {
		this.videoFileData = videoFileData;
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

	public String getChosenVideo() {
		return chosenVideo;
	}

	public void setChosenVideo(String chosenVideo) {
		this.chosenVideo = chosenVideo;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	
}
