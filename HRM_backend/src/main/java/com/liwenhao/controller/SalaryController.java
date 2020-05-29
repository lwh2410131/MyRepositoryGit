package com.liwenhao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liwenhao.service.RoleService;
import com.liwenhao.service.SalaryService;


@RestController
@RequestMapping("salary/")
public class SalaryController {
	@Autowired
	private SalaryService salaryService;
	
	@GetMapping("selectSalaryByCondition")
	public Map<String, Object> selectSalaryByCondition(@RequestParam Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> result=	salaryService.selectSalaryByCondition(param);
			resultMap.put("status", 1);
			resultMap.put("msg", "");
			resultMap.put("resultData", result);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", 0);
			resultMap.put("msg", "ROLE_ERROR");
			resultMap.put("resultData", null);
		}
		return resultMap;
	}
	
}