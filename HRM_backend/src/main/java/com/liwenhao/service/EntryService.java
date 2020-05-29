package com.liwenhao.service;

import java.util.Map;

public interface EntryService {
	
	public Map<String, Object> selectEntryByCondition(Map<String, Object> param);
	
	public int insertEntry(Map<String, Object> param);

	public int deleteEntry(Map<String, Object> paramMap);
	
}
