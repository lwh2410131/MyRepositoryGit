package com.liwenhao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liwenhao.dao.DepDao;
import com.liwenhao.dao.RoleDao;
import com.liwenhao.service.DepService;
import com.liwenhao.service.RoleService;

@Service
@Transactional
public class DepServiceImpl implements DepService{
	@Autowired
	private DepDao depDao;
	
	@Override
	public Map<String, Object> findDepByAccount(Map<String, Object> param){
		List<Map<String, Object>> dataResultList = this.depDao.findDepByAccount(param);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("rows", dataResultList);
		return resultMap;
	};

	@Override
	public Map<String, Object> selectDepByCondition(Map<String, Object> param){
		int page = Integer.valueOf(param.get("page").toString());
		int rows = Integer.valueOf(param.get("rows").toString());
		int start = (page - 1) * rows;
		param.put("start", start);
		param.put("pageSize", rows);
		List<Map<String, Object>> dataResultList = this.depDao.selectDepByCondition(param);
		int dataCount = this.depDao.selectDepByConditionCount(param);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", dataCount);
		resultMap.put("rows", dataResultList);
		return resultMap;
	}

	@Override
	public int update(Map<String, Object> paramMap) {
		return depDao.update(paramMap);
	};
}
