package com.liwenhao.dao;

import java.util.List;
import java.util.Map;

public interface EntryDao {
	List<Map<String, Object>> selectEntryByCondition(Map<String, Object> param);

	int selectEntryByConditionCount(Map<String, Object> param);

	int insertEntry(Map<String, Object> param);

	int deleteEntry(Map<String, Object> param);
}
