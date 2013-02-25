/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.ads;

/**
 * ContentTopic is used to represent a ContentTopic set for an Ad in the openX
 * server. The openX server identifies the content topic based on the Tag id
 * (tid). This class represent the content topic and the correspondign tid
 * 
 * @author sukeshnambiar
 * 
 */
public class ContentTopic {
	
	/** The id. */
	private int id;
	
	/** The text. */
	private String text;

	/**
	 * Construct a new content topic
	 * 
	 * @param id
	 *            The id (tid) for the content topic
	 * @param text
	 *            The textual representation of the content topi
	 */
	public ContentTopic(int id, String text) {
		this.id = id;
		this.text = text;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param string the new text
	 */
	public void setText(String string) {
		this.text = string;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContentTopic [id=" + id + ", text=" + text + "]";
	}
}
