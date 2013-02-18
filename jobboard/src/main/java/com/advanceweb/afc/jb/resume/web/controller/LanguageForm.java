package com.advanceweb.afc.jb.resume.web.controller;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class LanguageForm {
	private String language;
	private String expLvl;
	private int nLangId;
	private int itemId;
	private boolean editMode;
	
	
	public boolean isEditMode() {
		return editMode;
	}
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getExpLvl() {
		return expLvl;
	}
	public void setExpLvl(String expLvl) {
		this.expLvl = expLvl;
	}
	public int getnLangId() {
		return nLangId;
	}
	public void setnLangId(int nLangId) {
		this.nLangId = nLangId;
	}
	
	
	
}
