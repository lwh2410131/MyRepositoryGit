package com.liwenhao.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liwenhao.service.AccountService;

@RestController
@RequestMapping("account/")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping("findAllAction")
	public Map<String, Object> findAllAction(@RequestParam Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> result=	null;
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
	
	@GetMapping("accountCheck")
	public Map<String, Object> accountCheck(@RequestParam Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Integer result=	accountService.accountCheck(param);
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
