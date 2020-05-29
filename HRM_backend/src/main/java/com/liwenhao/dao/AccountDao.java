package com.liwenhao.dao;

import java.util.List;
import java.util.Map;

public interface AccountDao {

	List<Map<String,Object>> findAllAccount();

	Integer accountCheck(Map<String, Object> param);
}
