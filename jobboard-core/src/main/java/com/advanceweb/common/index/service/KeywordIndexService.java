package com.advanceweb.common.index.service;

import java.util.List;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface KeywordIndexService {

	String findBestMatch(String search) throws JobBoardServiceException;

	List<String> findMatches(String search) throws JobBoardServiceException;

}