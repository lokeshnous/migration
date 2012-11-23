package com.advanceweb.common.index.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.index.KeywordIndexer;

@Component
public class LuceneKeywordIndex {
	private static final Logger LOGGER = Logger
			.getLogger(LuceneKeywordIndex.class);
	
	public static final String FIELD_KEYWORD = "keyword";
	public static final String FIELD_RELATED = "related";

	private static final Version LUCENE_VERSION = Version.LUCENE_36;

	private static final float KEYWORD_FIELD_BOOST = 1.25F;
	
	@Autowired				
	private KeywordIndexer keywordIndexer;
	
	private Directory indexDirectory;

	public LuceneKeywordIndex() {
		indexDirectory = new RAMDirectory();
	}

	@PostConstruct
	private void init() {
		LOGGER.trace("LuceneKeywordIndex init:");

		StandardAnalyzer analyzer = new StandardAnalyzer(LUCENE_VERSION);
		IndexWriterConfig config = new IndexWriterConfig(LUCENE_VERSION,
				analyzer);
		config.setOpenMode(OpenMode.CREATE);
		try {
			IndexWriter indexWriter = new IndexWriter(indexDirectory, config);
			keywordIndexer.index(indexWriter);
			indexWriter.commit();
		} catch (CorruptIndexException ex) {
			LOGGER.error("Error creating index writer", ex);
		} catch (LockObtainFailedException ex) {
			LOGGER.error("Error creating index writer", ex);
		} catch (IOException ex) {
			LOGGER.error("Error creating index writer", ex);
		} catch (JobBoardServiceException ex) {
			LOGGER.error("Error creating index writer", ex);
		}
	}

	public List<LuceneResult> search(String queryString)
			throws JobBoardServiceException {

		LOGGER.debug("searching for " + queryString);

		List<LuceneResult> result = new ArrayList<LuceneResult>();
		IndexSearcher searcher = null;
		try {

			BooleanQuery query = new BooleanQuery();
			for (String str : queryString.toLowerCase().split(" ")) {
				FuzzyQuery nameQuery = new FuzzyQuery(new Term(FIELD_KEYWORD, str));
				nameQuery.setBoost(KEYWORD_FIELD_BOOST);
				query.add(nameQuery, Occur.SHOULD);
				query.add(new FuzzyQuery(new Term(FIELD_RELATED, str)), Occur.SHOULD);
			}

			IndexReader reader = IndexReader.open(indexDirectory);
			searcher = new IndexSearcher(reader);

			TopScoreDocCollector collector = TopScoreDocCollector.create(
					reader.numDocs(), true);
			searcher.search(query, collector);
			ScoreDoc[] scoreDocs = collector.topDocs().scoreDocs;

			for (ScoreDoc sDoc : scoreDocs) {
				result.add(new LuceneResult(searcher.doc(sDoc.doc), sDoc.score));
			}

		} catch (IOException ex) {
			LOGGER.error("Error querying Lucene index", ex);
			throw new JobBoardServiceException("Error querying lucene index");
		} finally {
			if (searcher != null) {
				try {
					searcher.close();
				} catch (IOException ex) {
					LOGGER.error("Error closing Index Searcher", ex);
				}
			}
		}
		return result;
	}
}
