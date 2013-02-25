/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.index.lucene;

import org.apache.lucene.document.Document;

public class LuceneResult {
	
	/** The document. */
	private Document document;
	
	/** The score. */
	private Float score;

	/**
	 * Instantiates a new lucene result.
	 *
	 * @param document the document
	 * @param score the score
	 */
	public LuceneResult(Document document, Float score) {
		this.document = document;
		this.score = score;
	}

	/**
	 * Gets the document.
	 *
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * Sets the document.
	 *
	 * @param document the new document
	 */
	public void setDocument(Document document) {
		this.document = document;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public Float getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(Float score) {
		this.score = score;
	}

}
