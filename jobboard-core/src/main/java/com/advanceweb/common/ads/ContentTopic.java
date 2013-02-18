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
	private int id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String string) {
		this.text = string;
	}

	@Override
	public String toString() {
		return "ContentTopic [id=" + id + ", text=" + text + "]";
	}
}
