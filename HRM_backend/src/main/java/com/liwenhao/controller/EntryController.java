package com.liwenhao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liwenhao.service.EntryService;
import com.liwenhao.service.RoleService;

@RestController
@RequestMapping("entry/")
public class EntryController {
	@Autowired
	private EntryService entryService;
	
	@GetMapping("selectEntryByCondition")
	public Map<String, Object> selectEntryByCondition(@RequestParam Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> result=	entryService.selectEntryByCondition(param);
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
	
	@PostMapping("insertEntry")
	public Map<String, Object> insertEntry(@RequestBody Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int result=	entryService.insertEntry(param);
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
	
	@PutMapping("/delete")
	public Map<String, Object> delObj(@RequestBody Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int dbResultInt = this.entryService.deleteEntry(param);
			resultMap.put("status", 1);
			resultMap.put("msg", "");
			resultMap.put("resultData", dbResultInt);

		} catch (Exception e) {
			resultMap.put("status", 0);
			resultMap.put("msg", "EC00002");
			resultMap.put("resultData", null);
		}
		return resultMap;
	}
	
}