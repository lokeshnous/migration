package com.advanceweb.common.index.lucene;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.index.KeywordIndexer;

public class ExcelKeywordIndexer implements KeywordIndexer {

	private static final Logger LOGGER = Logger
			.getLogger(ExcelKeywordIndexer.class);

	private String filePath;

	public ExcelKeywordIndexer(String filePath) {
		this.filePath = filePath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.advanceweb.common.index.KeywordIndexer#index(org.apache.lucene.index
	 * .IndexWriter)
	 */
	@Override
	public void index(IndexWriter indexWriter) throws JobBoardServiceException {
		InputStream is;
		try {
			// FIXME - The path to excel file is assumed to be part of the
			// classpath. Ideally this will be outsidethe classpath
			is = this.getClass().getResourceAsStream(filePath);

			Workbook wb = WorkbookFactory.create(is);
			Sheet sheet = wb.getSheetAt(0);

			int col = 0;
			while (true) {
				List<String> keywords = new ArrayList<String>();
				for (Row row : sheet) {
					Cell cell = row.getCell(col);
					if (cell == null || cell.getStringCellValue() == null) {
						break;
					} else {
						keywords.add(cell.getStringCellValue());
					}
				}
				if (keywords.isEmpty()) {
					break;
				} else {
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

	private Document createDocument(List<String> keywords) {
		Document document = new Document();
		String name = keywords.get(0);
		document.add(new Field(LuceneKeywordIndex.FIELD_KEYWORD, name, Field.Store.YES,
				Field.Index.ANALYZED));
		for (String kw : keywords) {
			document.add(new Field(LuceneKeywordIndex.FIELD_RELATED, kw, Field.Store.YES,
					Field.Index.ANALYZED));
		}
		return document;
	}
}
