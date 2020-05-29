package com.liwenhao.dao;

import java.util.List;
import java.util.Map;

public interface AttenceDao {
	List<Map<String,Object>> selectAttenceByCondition(Map<String, Object> param);
	
	int selectAttenceByConditionCount(Map<String, Object> param);

	int insertAttence(Map<String, Object> param);
	
	int insertAttenceConn(Map<String, Object> param);
}
