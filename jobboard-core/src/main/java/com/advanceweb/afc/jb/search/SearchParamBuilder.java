package com.advanceweb.afc.jb.search;

import java.util.List;
import java.util.Map;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface SearchParamBuilder {

	List<SearchParamDTO> buildParams(List<SearchParamDTO> searchParams,
			Map<String, String> inputParams) throws JobBoardServiceException;
}
