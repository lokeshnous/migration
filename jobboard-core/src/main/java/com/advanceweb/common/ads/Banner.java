/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.ads;

/**
 * Banner represent an ad tag to be placed in the web page. This class represent
 * the ad text size and the type indicator for the ad. The type indicators are
 * defined as constants in this class and follows the standard used by OpenX ad
 * server
 * 
 * @author sukeshnambiar
 * 
 */
public class Banner {

	// The following tag type are the same as that of OpenX API
	/** The Constant TAG_TYPE_JAVASCRIPT. */
	public static final int TAG_TYPE_JAVASCRIPT = 1;
	
	/** The Constant TAG_TYPE_IFRAME. */
	public static final int TAG_TYPE_IFRAME = 3;
	
	/** The Constant TAG_TYPE_IMAGE. */
	public static final int TAG_TYPE_IMAGE = 4;

	/** The size. */
	private AdSize size;
	
	/** The tag type. */
	private String tagType;
	
	/** The tag. */
	private String tag;

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public AdSize getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(AdSize size) {
		this.size = size;
	}

	/**
	 * Gets the tag type.
	 *
	 * @return the tag type
	 */
	public String getTagType() {
		return tagType;
	}

	/**
	 * Sets the tag type.
	 *
	 * @param tagType the new tag type
	 */
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Sets the tag.
	 *
	 * @param tag the new tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

}
