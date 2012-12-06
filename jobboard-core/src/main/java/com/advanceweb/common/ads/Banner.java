package com.advanceweb.common.ads;

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
