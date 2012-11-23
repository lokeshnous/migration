package com.advanceweb.common.index;

import org.apache.lucene.index.IndexWriter;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface KeywordIndexer {

	void index(IndexWriter indexWriter) throws JobBoardServiceException;

}