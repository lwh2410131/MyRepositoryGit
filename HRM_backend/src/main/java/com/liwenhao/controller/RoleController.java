package com.liwenhao.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liwenhao.service.AccountService;
import com.liwenhao.service.RoleService;

@RestController
@RequestMapping("role/")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@GetMapping("findRoleByAccount")
	public Map<String, Object> findRoleByAccount(@RequestParam Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> result=	roleService.findRoleByAccount(param);
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
	
	@GetMapping("selectRoleByCondition")
	public Map<String, Object> selectRoleByCondition(@RequestParam Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> result=	roleService.selectRoleByCondition(param);
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
	

	@PostMapping("insertRole")
	public Map<String, Object> insertRole(@RequestBody Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int result=	roleService.insertRole(param);
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
