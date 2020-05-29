package com.liwenhao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liwenhao.dao.AccountDao;
import com.liwenhao.dao.RoleDao;
import com.liwenhao.service.AccountService;
import com.liwenhao.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Map<String, Object> findRoleByAccount(Map<String, Object> param){
		List<Map<String, Object>> dataResultList = this.roleDao.findRoleByAccount(param);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("rows", dataResultList);
		return resultMap;
	};
	
	@Override
	public Map<String, Object> selectRoleByCondition(Map<String, Object> param){
		int page = Integer.valueOf(param.get("page").toString());
		int rows = Integer.valueOf(param.get("rows").toString());
		int start = (page - 1) * rows;
		param.put("start", start);
		param.put("pageSize", rows);
		List<Map<String, Object>> dataResultList = this.roleDao.selectRoleByCondition(param);
		int dataCount = this.roleDao.selectRoleByConditionCount(param);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", dataCount);
		resultMap.put("rows", dataResultList);
		return resultMap;
	}

	@Override
	public int insertRole(Map<String, Object> param) {
		int result = this.roleDao.insertRole(param);
		Map<String, Object> insertParam = param;
		insertParam.put("r_id", param.get("id"));
		result = result*this.roleDao.insertRoleToDep(insertParam);
		return result;
	};


}
