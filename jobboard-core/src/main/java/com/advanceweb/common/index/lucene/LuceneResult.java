package com.advanceweb.common.index.lucene;

import org.apache.lucene.document.Document;

public class LuceneResult {
	private Document document;
	private Float score;

	public LuceneResult(Document document, Float score) {
		this.document = document;
		this.score = score;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

}
