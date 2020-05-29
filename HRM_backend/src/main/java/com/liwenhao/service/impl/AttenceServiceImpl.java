package com.liwenhao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liwenhao.dao.AttenceDao;
import com.liwenhao.service.AttenceService;

@Service
@Transactional
public class AttenceServiceImpl implements AttenceService{
	@Autowired
	private AttenceDao attenceDao;
	
	@Override
	public Map<String, Object> selectAttenceByCondition(Map<String, Object> param){
		int page = Integer.valueOf(param.get("page").toString());
		int rows = Integer.valueOf(param.get("rows").toString());
		int start = (page - 1) * rows;
		param.put("start", start);
		param.put("pageSize", rows);
		List<Map<String, Object>> dataResultList = this.attenceDao.selectAttenceByCondition(param);
		int dataCount = this.attenceDao.selectAttenceByConditionCount(param);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", dataCount);
		resultMap.put("rows", dataResultList);
		return resultMap;
	}

	@Override
	public int insertAttence(Map<String, Object> param) {
		int result = this.attenceDao.insertAttence(param);
		Map<String, Object> insertParam = param;
		insertParam.put("a_id", param.get("id"));
		this.attenceDao.insertAttenceConn(insertParam);
		return result;
	};

}
