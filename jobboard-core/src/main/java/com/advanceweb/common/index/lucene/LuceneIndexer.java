package com.advanceweb.common.index.lucene;

import org.apache.lucene.index.IndexWriter;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface LuceneIndexer {

	void index(IndexWriter indexWriter) throws JobBoardServiceException;

}