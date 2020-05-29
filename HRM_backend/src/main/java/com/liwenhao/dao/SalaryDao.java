package com.liwenhao.dao;

import java.util.List;
import java.util.Map;

public interface SalaryDao {

	List<Map<String, Object>> selectSalaryByCondition(Map<String, Object> param);

	int selectSalaryByConditionCount(Map<String, Object> param);

}
