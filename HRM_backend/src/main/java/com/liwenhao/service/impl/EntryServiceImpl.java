package com.liwenhao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liwenhao.dao.EntryDao;
import com.liwenhao.service.EntryService;

@Service
@Transactional
public class EntryServiceImpl implements EntryService{
	@Autowired
	private EntryDao entryDao;

	@Override
	public Map<String, Object> selectEntryByCondition(Map<String, Object> param) {
		int page = Integer.valueOf(param.get("page").toString());
		int rows = Integer.valueOf(param.get("rows").toString());
		int start = (page - 1) * rows;
		param.put("start", start);
		param.put("pageSize", rows);
		List<Map<String, Object>> dataResultList = this.entryDao.selectEntryByCondition(param);
		int dataCount = this.entryDao.selectEntryByConditionCount(param);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", dataCount);
		resultMap.put("rows", dataResultList);
		return resultMap;
	}

	@Override
	public int insertEntry(Map<String, Object> param) {
		int result = this.entryDao.insertEntry(param);
		return result;
	}

	@Override
	public int deleteEntry(Map<String, Object> param) {
		int result = this.entryDao.deleteEntry(param);
		return result;
	}
	
}