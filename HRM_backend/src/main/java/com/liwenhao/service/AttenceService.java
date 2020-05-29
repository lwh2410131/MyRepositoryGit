package com.liwenhao.service;

import java.util.Map;

public interface AttenceService {
	Map<String, Object> selectAttenceByCondition(Map<String, Object> param);

	int insertAttence(Map<String, Object> param);
}
