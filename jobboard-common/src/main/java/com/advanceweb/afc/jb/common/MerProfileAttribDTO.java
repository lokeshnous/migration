package com.advanceweb.afc.jb.common;

import java.util.List;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class MerProfileAttribDTO {
	
	private String strLabelName;
	private String strLabelValue;
	private String strProfileAttribId;
	private String strAttribType;
	private String strSectionName;
	private int bRequired;
	private String strToolTip;
	private List<DropDownDTO> dropdown;
	
	public String getStrLabelName() {
		return strLabelName;
	}
	public void setStrLabelName(String strLabelName) {
		this.strLabelName = strLabelName;
	}
	public String getStrLabelValue() {
		return strLabelValue;
	}
	public void setStrLabelValue(String strLabelValue) {
		this.strLabelValue = strLabelValue;
	}
	public String getStrProfileAttribId() {
		return strProfileAttribId;
	}
	public void setStrProfileAttribId(String strProfileAttribId) {
		this.strProfileAttribId = strProfileAttribId;
	}
	public String getStrAttribType() {
		return strAttribType;
	}
	public void setStrAttribType(String strAttribType) {
		this.strAttribType = strAttribType;
	}
	public String getStrSectionName() {
		return strSectionName;
	}
	public void setStrSectionName(String strSectionName) {
		this.strSectionName = strSectionName;
	}
	public List<DropDownDTO> getDropdown() {
		return dropdown;
	}
	public void setDropdown(List<DropDownDTO> dropdown) {
		this.dropdown = dropdown;
	}
	public String getStrToolTip() {
		return strToolTip;
	}
	public void setStrToolTip(String strToolTip) {
		this.strToolTip = strToolTip;
	}
	public int getbRequired() {
		return bRequired;
	}
	public void setbRequired(int bRequired) {
		this.bRequired = bRequired;
	}

}
