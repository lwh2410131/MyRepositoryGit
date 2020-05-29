package com.liwenhao.service;

import java.util.List;
import java.util.Map;

public interface RoleService {

	public Map<String, Object> findRoleByAccount(Map<String, Object> param);

	public Map<String, Object> selectRoleByCondition(Map<String, Object> param);

	public int insertRole(Map<String, Object> param);

}
