/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.ads.location.index;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.advanceweb.common.ads.AdLocation;

/**
 * This class is used to retrieve the AdLocations stored in the excel format
 * used by Merion Matters
 * 
 * @author sukeshnambiar
 * 
 */
public class ExcelLocationSource implements LocationIndexSource {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(ExcelLocationSource.class);

	/** The file path. */
	private String filePath;

	/** The column refs. */
	private Map<String, Integer> columnRefs;

	/** The location list. */
	private List<AdLocation> locationList;

	/**
	 * Constructs the ExcelLocationSource from the given file path
	 * 
	 * @param filePath
	 *            The path to the file.
	 */
	public ExcelLocationSource(String filePath) {
		columnRefs = new HashMap<String, Integer>();
		this.filePath = filePath;
	}

	/**
	 * Returns all the lcoations listed in the excel file
	 */
	@Override
	public List<AdLocation> findAll() {
		synchronized (this) {
			if (locationList == null) {
				loadLocations();
			}
		}
		return locationList;
	}

	/**
	 * Loads all the locations from the excel file.
	 */
	private synchronized void loadLocations() {
		InputStream is = this.getClass().getResourceAsStream(filePath);
		locationList = new ArrayList<AdLocation>();
		try {
			Workbook wb = WorkbookFactory.create(is);
			Sheet sheet = wb.getSheetAt(0);
			Boolean header = true;
			for (Row row : sheet) {
				if (header) {
					LOGGER.debug("Processing the Header row " + filePath);
					processHeader(row);
					header = false;
				} else {
					processRow(row);
				}
			}
			is.close();
		} catch (IOException ex) {
			LOGGER.error("Error while processing excel file " + filePath, ex);
		} catch (InvalidFormatException ex) {
			LOGGER.error("Error while processing excel file " + filePath, ex);
		}

	}

	/**
	 * This method uses the excel file obtained by exporting the jplocation
	 * table in the Merion database. This method is maintained for reusability
	 * purpose and should be removed in future versions.
	 * 
	 * @param row
	 *            The excel row to read data from
	 */
	@Deprecated
	private void processRowDBFormat(Row row) {
		// colunNames = { "location_id", "city",
		// "state","Metro Label for Open X", "postcode", "country",
		// "latitude","longitude", "create_dt", "state_fullname", "area",
		// "region", "city_alias" };
		AdLocation location = new AdLocation();
		location.setState(row.getCell(columnRefs.get("state")).toString());
		location.setCity(row.getCell(columnRefs.get("city")).toString());
		location.setCountry(row.getCell(columnRefs.get("country")).toString());
		location.setLabel(row.getCell(columnRefs.get("Metro Label for Open X"))
				.getStringCellValue());
		location.setLatitude(CellUtil.getFloatValue(row.getCell(columnRefs
				.get("latitude"))));
		location.setLongitude(CellUtil.getFloatValue(row.getCell(columnRefs
				.get("longitude"))));
		locationList.add(location);
	}

	/**
	 * Reads one row of data from the excel file representing one AdLocation
	 * 
	 * @param row
	 *            The excel row to read data from
	 */
	private void processRow(Row row) {
		// colunNames = { "City-State", "latitude","longitude"};
		AdLocation location = new AdLocation();
		String label = row.getCell(columnRefs.get("City-State"))
				.getStringCellValue();
		location.setLabel(label);

		int offset = label.indexOf('-');
		if (offset > 0) {
			location.setCity(label.substring(0, offset));
			location.setState(label.substring(offset + 1));
		} else {
			location.setCity(label);
			location.setState("");
		}
		location.setCountry("");
		location.setLatitude(CellUtil.getFloatValue(row.getCell(columnRefs
				.get("latitude"))));
		location.setLongitude(CellUtil.getFloatValue(row.getCell(columnRefs
				.get("longitude"))));
		locationList.add(location);
		System.out.println(location);
	}

	/**
	 * Reads the header row and create a map of column names for processing the
	 * other rows efficiently
	 * 
	 * @param row
	 *            The excel row to read data from
	 */
	private void processHeader(Row row) {
		int col = 0;
		while (true) {
			Cell cell = row.getCell(col);
			if (cell == null || cell.getStringCellValue() == null) {
				break;
			} else {
				columnRefs.put(cell.getStringCellValue(), col);
				col++;
			}
		}
	}

	/**
	 * The Excel cell data requires processing based on the cell type as there
	 * are no generic methods available to get the cell data in an objet format
	 * that can be converted easily to other data types
	 * 
	 * @author sukeshnambiar
	 * 
	 */
	private static class CellUtil {

		/**
		 * Returns the cell value in a string form
		 * 
		 * @param cell
		 *            The cell from which data should be retrieved
		 * @return The String representation of the cell data
		 */
		public static String getStringValue(Cell cell) {
			return cell.toString();
		}

		/**
		 * Returns the cell value in an Integer form
		 * 
		 * @param cell
		 *            The cell from which data should be retrieved
		 * @return The Integer representation of the cell data
		 */
		public static Integer getIntegerValue(Cell cell) {
			if (cell == null) {
				return null;
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return (int) cell.getNumericCellValue();
			} else {
				try {
					return Integer.valueOf(cell.toString());
				} catch (NumberFormatException ex) {
					return null;
				}
			}
		}

		/**
		 * Returns the cell value in a Float form
		 * 
		 * @param cell
		 *            The cell from which data should be retrieved
		 * @return The Float representation of the cell data
		 */
		public static Float getFloatValue(Cell cell) {
			if (cell == null) {
				return null;
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return (float) cell.getNumericCellValue();
			} else {
				try {
					return Float.valueOf(cell.toString());
				} catch (NumberFormatException ex) {
					return null;
				}
			}
		}
	}
}
