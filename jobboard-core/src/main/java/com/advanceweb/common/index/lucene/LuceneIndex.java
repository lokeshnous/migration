package com.advanceweb.common.index.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public class LuceneIndex {
	private static final Logger LOGGER = Logger.getLogger(LuceneIndex.class);

	public static final String FIELD_KEYWORD = "keyword";
	public static final String FIELD_RELATED = "related";

	private static final Version LUCENE_VERSION = Version.LUCENE_36;

	private LuceneIndexer indexer;

	private LuceneQueryBuilder queryBuilder;

	private Directory indexDirectory;

	public LuceneIndex(LuceneIndexer indexer, LuceneQueryBuilder queryBuilder,
			String indexPath) {
		this.indexer = indexer;
		this.queryBuilder = queryBuilder;

		try {
			indexDirectory = new SimpleFSDirectory(new File(indexPath));
		} catch (IOException ex) {
			LOGGER.error("Error opening lucene index dirctory" + indexPath, ex);
			LOGGER.warn("The initialization of index directory failed. Switching to RamDirectory");
			indexDirectory = new RAMDirectory();
		}

		try {
			if (!IndexReader.indexExists(indexDirectory)) {
				LOGGER.warn("The Lucene index in " + indexPath
						+ " is missing or corrupt. Attempting to reindex");
				reindex();
				LOGGER.info("Reindex completed successfully");
			}
		} catch (IOException ex) {
			LOGGER.error("Error verifying lucene index dirctory" + indexPath,
					ex);
			LOGGER.warn("The initialization of index directory failed. Switching to RamDirectory");
			indexDirectory = new RAMDirectory();
			reindex();
		}
	}

	private void reindex() {
		LOGGER.trace("LuceneIndex init:");

		StandardAnalyzer analyzer = new StandardAnalyzer(LUCENE_VERSION);
		IndexWriterConfig config = new IndexWriterConfig(LUCENE_VERSION,
				analyzer);
		config.setOpenMode(OpenMode.CREATE);
		try {
			IndexWriter indexWriter = new IndexWriter(indexDirectory, config);
			indexer.index(indexWriter);
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

	public List<LuceneResult> search(Map<String, String> params)
			throws JobBoardServiceException {

		List<LuceneResult> result = new ArrayList<LuceneResult>();

		try {
			Query query = queryBuilder.buildQuery(params);

			IndexReader reader = IndexReader.open(indexDirectory);
			TopScoreDocCollector collector = TopScoreDocCollector.create(
					reader.numDocs(), true);

			IndexSearcher searcher = new IndexSearcher(reader);
			searcher.search(query, collector);
			ScoreDoc[] scoreDocs = collector.topDocs().scoreDocs;

			for (ScoreDoc sDoc : scoreDocs) {
				result.add(new LuceneResult(searcher.doc(sDoc.doc), sDoc.score));
			}

			searcher.close();
			reader.close();

		} catch (IOException ex) {
			LOGGER.error("Error querying Lucene index", ex);
			throw new JobBoardServiceException("Error querying lucene index");
		}
		return result;
	}
}
