package com.advanceweb.afc.jb.search.engine.solr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.search.SearchParamBuilder;
import com.advanceweb.afc.jb.search.SearchParamDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

@Component("jobSearchParamBuilder")
public class SOLRSearchParamBuilder implements SearchParamBuilder {

	private static final Logger LOGGER = Logger
			.getLogger(SOLRSearchParamBuilder.class);

	private static final String[] KEYWORD_FIELDS = { "keywords", "rows",
			"start", "sessionid", "searchName", "search_seq" };
	private static final String[] LOCATION_FIELDS = { "position", "radius",
			"keywords", "rows", "start", "sessionid", "searchName",
			"search_seq" };

	private String[] fields;

	private static final String REGEX_PATTERN = ":b\\d\\d";
	private static final Pattern PATTERN = Pattern.compile(REGEX_PATTERN);

	/*
	 * Method buildParams is used to replace the positional parameters in a
	 * search query. The placeholders named like :b01 :b02 etc will be replaced
	 * with corresponding values. The order of the values replaced are
	 * determined by a predefined array of keys
	 */
	@Override
	public List<SearchParamDTO> buildParams(List<SearchParamDTO> searchParams,
			Map<String, String> inputParams) throws JobBoardServiceException {

		String searchName = inputParams.get(SearchParamDTO.SEARCH_NAME);

		if (MMJBCommonConstants.LOCATION_SEARCH.equalsIgnoreCase(searchName)) {
			fields = LOCATION_FIELDS;
			// return createParamsForLocationSearch(queryDTO, paramMap, rows,
			// start);
		} else {
			fields = KEYWORD_FIELDS;
			// return createParamsForKeywordSearch(queryDTO, paramMap, rows,
			// start);
		}

		// Iterate through the search parameters and replace the placehoders
		List<SearchParamDTO> result = new ArrayList<SearchParamDTO>();

		for (SearchParamDTO param : searchParams) {

			// TODO create and use a constructor
			SearchParamDTO resultParam = new SearchParamDTO();
			resultParam.setParameterName(param.getParameterName());
			resultParam.setParameterValue(replacePlaceholders(
					param.getParameterValue(), inputParams));
			resultParam.setSearchParamId(param.getSearchParamId());
			resultParam.setSeq(param.getSeq());

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
	 * @param inputs
	 *            - The user input to be used for replacing the placeholders
	 * @return - A string with the positional parameters replaced with
	 *         appropriate user input values
	 */
	private String replacePlaceholders(String value, Map<String, String> inputs) {

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
			LOGGER.info("SOLRSearchParamBuilder.replacePlaceholders - Replacing "
					+ placeholder + " with " + fields[offset - 1]);
			result = result
					.replace(placeholder, inputs.get(fields[offset - 1]));
		}
		return result;
	}

}