package com.liwenhao.service;

import java.util.Map;

public interface DepService {
	public Map<String, Object> findDepByAccount(Map<String, Object> param);

	public Map<String, Object> selectDepByCondition(Map<String, Object> param);

	public int update(Map<String, Object> paramMap);
}
