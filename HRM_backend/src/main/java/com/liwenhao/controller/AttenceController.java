package com.liwenhao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liwenhao.service.AccountService;
import com.liwenhao.service.AttenceService;

@RestController
@RequestMapping("attence/")
public class AttenceController {
	@Autowired
	private AttenceService attenceService;
	
	@GetMapping("selectAttenceByCondition")
	public Map<String, Object> selectAttenceByCondition(@RequestParam Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> result=	attenceService.selectAttenceByCondition(param);
			resultMap.put("status", 1);
			resultMap.put("msg", "");
			resultMap.put("resultData", result);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", 0);
			resultMap.put("msg", "ATTENCE_ERROR");
			resultMap.put("resultData", null);
		}
		return resultMap;
	}
	
	@PostMapping("insertAttence")
	public Map<String, Object> insertAttence(@RequestBody Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int result=	attenceService.insertAttence(param);
			resultMap.put("status", 1);
			resultMap.put("msg", "");
			resultMap.put("resultData", result);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", 0);
			resultMap.put("msg", "ENTRY_ERROR");
			resultMap.put("resultData", null);
		}
		return resultMap;
	}
}