package com.advanceweb.common.index.lucene;

import java.util.Map;

import org.apache.lucene.search.Query;

public interface LuceneQueryBuilder {
	Query buildQuery(Map<String,String> params);
}