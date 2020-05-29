package com.liwenhao.dao;

import java.util.List;
import java.util.Map;

public interface DepDao {
	List<Map<String, Object>> findDepByAccount(Map<String, Object> param);

	List<Map<String, Object>> selectDepByCondition(Map<String, Object> param);
	
	int selectDepByConditionCount(Map<String, Object> param);

	int update(Map<String, Object> paramMap);
}
