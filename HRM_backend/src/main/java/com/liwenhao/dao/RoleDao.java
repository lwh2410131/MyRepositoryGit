package com.liwenhao.dao;

import java.util.List;
import java.util.Map;

public interface RoleDao {

	List<Map<String, Object>> findRoleByAccount(Map<String, Object> param);
	
	List<Map<String, Object>> selectRoleByCondition(Map<String, Object> param);

	int selectRoleByConditionCount(Map<String, Object> param);

	int insertRole(Map<String, Object> param);

	int insertRoleToDep(Map<String, Object> insertParam);

	int insertRoleToDepConn(Map<String, Object> insertDepParam);
}
