package com.advanceweb.common.index.service;

import java.util.List;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface KeywordIndexService {

	String findBestMatch(String pattern) throws JobBoardServiceException;

	List<String> findMatches(String pattern) throws JobBoardServiceException;

}