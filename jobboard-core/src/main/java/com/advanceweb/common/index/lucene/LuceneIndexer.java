/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.index.lucene;

import org.apache.lucene.index.IndexWriter;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface LuceneIndexer {

	/**
	 * Index.
	 *
	 * @param indexWriter the index writer
	 * @throws JobBoardServiceException the job board service exception
	 */
	void index(IndexWriter indexWriter) throws JobBoardServiceException;

}