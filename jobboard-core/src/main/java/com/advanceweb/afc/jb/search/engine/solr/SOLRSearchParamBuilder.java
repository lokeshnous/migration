/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search.engine.solr;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.search.SearchParamBuilder;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

//@Component("jobSearchParamBuilder")
public class SOLRSearchParamBuilder implements SearchParamBuilder {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(SOLRSearchParamBuilder.class);

	/** The field map. */
	private final Map<String, String[]> fieldMap;

	/** The Constant REGEX_PATTERN. */
	private static final String REGEX_PATTERN = ":b\\d\\d";
	
	/** The Constant PATTERN. */
	private static final Pattern PATTERN = Pattern.compile(REGEX_PATTERN);

	/**
	 * Constructs the parameter builder with the field values provided in the
	 * map. The key in the map is used to identify the name of the search. The
	 * values passed are comma separated names of the fields. This method will
	 * split the values using comma (",") as a delimiter and populate the
	 * fieldMap. For better efficiency, the values are not trimmed. So care
	 * should be taken not to leave spaces around the comma
	 * 
	 * @param fieldValues
	 *            The map containing the place holder names with search name as
	 *            key and the field names provided as a string with comma
	 *            separated values
	 */
	public SOLRSearchParamBuilder(Map<String, String> fieldValues) {
		LOGGER.trace("Constructing " + getClass().getName());
		fieldMap = new LinkedHashMap<String, String[]>();
		for (Map.Entry<String, String> entry : fieldValues.entrySet()) {
			fieldMap.put(entry.getKey(), entry.getValue().split(","));
		}
	}

	/*
	 * Method buildParams is used to replace the positional parameters in a
	 * search query. The placeholders named like :b01 :b02 etc will be replaced
	 * with corresponding values. The order of the values replaced are
	 * determined by a predefined array of keys
	 */
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.search.SearchParamBuilder#buildParams(java.util.List, java.util.Map)
	 */
	@Override
	public List<SearchParamDTO> buildParams(List<SearchParamDTO> searchParams,
			Map<String, String> inputParams) throws JobBoardServiceException {

		String searchName = inputParams.get(SearchParamDTO.SEARCH_NAME);

		// Make sure the search name is known
		if (!fieldMap.containsKey(searchName)) {
			throw new JobBoardServiceException("Unknown Search Name "
					+ searchName);
		}

		String[] fields = fieldMap.get(searchName);

		// Iterate through the search parameters and replace the placehoders
		List<SearchParamDTO> result = new ArrayList<SearchParamDTO>();

		for (SearchParamDTO param : searchParams) {

			// TODO create and use a constructor
			SearchParamDTO resultParam = new SearchParamDTO();
			resultParam.setParameterName(param.getParameterName());
			resultParam.setParameterValue(replacePlaceholders(
					param.getParameterValue(), fields, inputParams));
			resultParam.setSearchParamId(param.getSearchParamId());
			resultParam.setSeq(param.getSeq());

			result.add(resultParam);
		}
		String schedulerParam = inputParams.get(MMJBCommonConstants.SCHEDULER_DAY);
		if (!StringUtils.isEmpty(schedulerParam)) {
			SearchParamDTO resultParam = new SearchParamDTO();
			resultParam.setParameterName("fq");
			resultParam.setParameterValue(MMJBCommonConstants.SOLR_PARAM_FQ.replace("?day", schedulerParam));
			// resultParam.setSearchParamId(param.getSearchParamId());
			// resultParam.setSeq(param.getSeq());
			
			result.add(resultParam);
		}

		return result;
	}

	/**
	 * This method replaces the placeholder values like :b01, :b02 etc with the
	 * corresponding values in the input map. The keys for the replacement
	 * values are determined by the values defined in the fields array
	 * 
	 * @param value
	 *            - The string where the placeholders needs to be replaced
	 * @param fields
	 *            The Array of fields to be replaced based on the positional
	 *            parameters
	 * @param inputs
	 *            - The user input to be used for replacing the placeholders
	 * @return - A string with the positional parameters replaced with
	 *         appropriate user input values
	 */
	private String replacePlaceholders(String value, String[] fields,
			Map<String, String> inputs) {

		Matcher matcher = PATTERN.matcher(value);
		List<String> patterns = new ArrayList<String>();

		// Get matching patterns
		while (matcher.find()) {
			patterns.add(matcher.group());
		}

		// Replace the patterns
		String result = value;
		for (String placeholder : patterns) {
			int offset = Integer.parseInt(placeholder.substring(2));
			LOGGER.debug("SOLRSearchParamBuilder.replacePlaceholders - Replacing "
					+ placeholder + " with " + fields[offset - 1]);
			result = result
					.replace(placeholder, inputs.get(fields[offset - 1]));
		}
		return result;
	}

}
