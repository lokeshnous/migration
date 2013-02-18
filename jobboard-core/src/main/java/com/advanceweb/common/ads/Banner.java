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
	public static final int TAG_TYPE_JAVASCRIPT = 1;
	public static final int TAG_TYPE_IFRAME = 3;
	public static final int TAG_TYPE_IMAGE = 4;

	private AdSize size;
	private String tagType;
	private String tag;

	public AdSize getSize() {
		return size;
	}

	public void setSize(AdSize size) {
		this.size = size;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
