/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class LanguageDTO {
	
	/** The language. */
	private String language;
	
	/** The exp lvl. */
	private String expLvl;
	
	/** The n lang id. */
	private int nLangId;
	
	/** The item id. */
	private int itemId;
	
	/** The edit mode. */
	private boolean editMode;
	
	
	
	/**
	 * Gets the item id.
	 *
	 * @return the item id
	 */
	public int getItemId() {
		return itemId;
	}
	
	/**
	 * Sets the item id.
	 *
	 * @param itemId the new item id
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	/**
	 * Checks if is edits the mode.
	 *
	 * @return true, if is edits the mode
	 */
	public boolean isEditMode() {
		return editMode;
	}
	
	/**
	 * Sets the edits the mode.
	 *
	 * @param editMode the new edits the mode
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * Sets the language.
	 *
	 * @param language the new language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	/**
	 * Gets the exp lvl.
	 *
	 * @return the exp lvl
	 */
	public String getExpLvl() {
		return expLvl;
	}
	
	/**
	 * Sets the exp lvl.
	 *
	 * @param expLvl the new exp lvl
	 */
	public void setExpLvl(String expLvl) {
		this.expLvl = expLvl;
	}
	
	/**
	 * Gets the n lang id.
	 *
	 * @return the n lang id
	 */
	public int getnLangId() {
		return nLangId;
	}
	
	/**
	 * Sets the n lang id.
	 *
	 * @param nLangId the new n lang id
	 */
	public void setnLangId(int nLangId) {
		this.nLangId = nLangId;
	}
	
	
}
