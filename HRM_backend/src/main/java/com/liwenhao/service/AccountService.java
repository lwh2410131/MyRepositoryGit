package com.liwenhao.service;

import java.util.List;
import java.util.Map;

public interface AccountService {

	public List<Map<String, Object>> findAllAccount();

	public Integer accountCheck(Map<String, Object> param);

}
