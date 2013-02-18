package com.advanceweb.common.ads.keyword.index;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.index.lucene.LuceneIndexer;

/**
 * This class is used to index the excel sheets with keywords for the ad tags.
 * The excel sheet has the following row formats <li>
 * <ul>
 * Row 0 The tag id (tid) for the content topic
 * </ul>
 * <ul>
 * Row 1 (Top Row) the main ad keywords that are entered as content topics
 * </ul>
 * </ul>Other rows contain the additional keywords (synonyms)</ul></li>
 * 
 * @author sukeshnambiar
 * 
 */
public class ExcelKeywordIndexer implements LuceneIndexer {

	private static final Logger LOGGER = Logger
			.getLogger(ExcelKeywordIndexer.class);

	/*
	 * The Row containing the topi id (tid)
	 */
	private static final int TID_ROW = 0;
	/*
	 * The row containing the actual content topics
	 */
	private static final int KEYWORD_ROW = 1;

	/*
	 * The path where the file is stored.
	 */
	private String filePath;

	/**
	 * Constructs the indexer with the given file path.
	 * 
	 * @param filePath
	 */
	public ExcelKeywordIndexer(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Create lucene index documents and writes to the writer finally committing
	 * the writer once the entire excel is processed.
	 * 
	 * @see com.advanceweb.common.index.KeywordIndexer#index(org.apache.lucene.index
	 *      .IndexWriter)
	 */
	@Override
	public void index(IndexWriter indexWriter) throws JobBoardServiceException {
		InputStream is;
		try {
			// FIXME - The path to excel file is assumed to be part of the
			// classpath. Ideally this will be outside the classpath
			is = this.getClass().getResourceAsStream(filePath);

			Workbook wb = WorkbookFactory.create(is);
			Sheet sheet = wb.getSheetAt(0);

			DataFormatter df = new DataFormatter();

			int col = 0;
			while (true) {
				List<String> keywords = new ArrayList<String>();
				for (Row row : sheet) {
					Cell cell = row.getCell(col);
					String cellValue = df.formatCellValue(cell);
					if (StringUtils.isEmpty(cellValue)) {
						break;
					} else {
						keywords.add(cellValue);
					}
				}
				if (keywords.isEmpty()) {
					break;
				} else {
					LOGGER.debug("Indexed " + keywords.size() + "words for "
							+ keywords.get(0));
					indexWriter.addDocument(createDocument(keywords));
				}
				col++;
			}
			indexWriter.commit();
			is.close();
			LOGGER.debug("Finished Indexing Excel file " + filePath);
		} catch (FileNotFoundException ex) {
			LOGGER.error("Error indexing Excel file", ex);
			throw new JobBoardServiceException(ex.getMessage());
		} catch (InvalidFormatException ex) {
			LOGGER.error("Error indexing Excel file", ex);
			throw new JobBoardServiceException(ex.getMessage());
		} catch (IOException ex) {
			LOGGER.error("Error indexing Excel file", ex);
			throw new JobBoardServiceException(ex.getMessage());
		}
	}

	/**
	 * Creates the document for a column in the execel. The content topic and
	 * keywords are selected from the list based on the appropriate rows defined
	 * by TID_ROW adn KEYWORD_ROW
	 * 
	 * @param keywords
	 * @return
	 */
	private Document createDocument(List<String> keywords) {
		Document document = new Document();
		String name = keywords.get(KEYWORD_ROW);

		// Set the Topic Id to 0 by default
		int tid = 0;
		document.add(new Field(KeywordIndex.KEYWORD_FIELD, name,
				Field.Store.YES, Field.Index.ANALYZED));
		for (int row = 0; row < keywords.size(); row++) {
			String kw = keywords.get(row);
			if (row == TID_ROW) {
				try {
					tid = Integer.parseInt(kw);
				} catch (NumberFormatException nfe) {
					LOGGER.error("Topic ID is set to non numeric value [" + kw
							+ "] for keyword " + keywords.get(0));
				}
			} else {
				document.add(new Field(KeywordIndex.RELATED_FIELD, kw,
						Field.Store.YES, Field.Index.ANALYZED));
			}
		}
		document.add(new Field(KeywordIndex.TOPIC_ID_FIELD, Integer
				.toString(tid), Field.Store.YES, Field.Index.NOT_ANALYZED));
		return document;
	}
}
