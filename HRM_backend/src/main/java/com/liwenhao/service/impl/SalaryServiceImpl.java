package com.liwenhao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liwenhao.dao.RoleDao;
import com.liwenhao.dao.SalaryDao;
import com.liwenhao.service.SalaryService;

@Service
@Transactional
public class SalaryServiceImpl implements SalaryService{
	@Autowired
	private SalaryDao salaryDao;

	@Override
	public Map<String, Object> selectSalaryByCondition(Map<String, Object> param) {
		int page = Integer.valueOf(param.get("page").toString());
		int rows = Integer.valueOf(param.get("rows").toString());
		int start = (page - 1) * rows;
		param.put("start", start);
		param.put("pageSize", rows);
		List<Map<String, Object>> dataResultList = this.salaryDao.selectSalaryByCondition(param);
		int dataCount = this.salaryDao.selectSalaryByConditionCount(param);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", dataCount);
		resultMap.put("rows", dataResultList);
		return resultMap;
	}
}
