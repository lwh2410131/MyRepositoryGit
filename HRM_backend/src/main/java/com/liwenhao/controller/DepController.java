package com.liwenhao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liwenhao.service.DepService;
import com.liwenhao.service.RoleService;

@RestController
@RequestMapping("dep/")
public class DepController {
	@Autowired
	private DepService depService;
	
	@GetMapping("findDepByAccount")
	public Map<String, Object> findDepbyAccount(@RequestParam Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> result=	depService.findDepByAccount(param);
			resultMap.put("status", 1);
			resultMap.put("msg", "");
			resultMap.put("resultData", result);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", 0);
			resultMap.put("msg", "DEP_ERROR");
			resultMap.put("resultData", null);
		}
		return resultMap;
	}
	
	@GetMapping("selectDepByCondition")
	public Map<String, Object> selectRoleByCondition(@RequestParam Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> result=	depService.selectDepByCondition(param);
			resultMap.put("status", 1);
			resultMap.put("msg", "");
			resultMap.put("resultData", result);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", 0);
			resultMap.put("msg", "DEP_ERROR");
			resultMap.put("resultData", null);
		}
		return resultMap;
	}
	
	@PutMapping("updateDep")
	public Map<String, Object> updateObj(@RequestBody Map<String, Object> paramMap) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int dbResultInt = this.depService.update(paramMap);
			resultMap.put("status", 1);
			resultMap.put("msg", "");
			resultMap.put("resultData", dbResultInt);
		} catch (Exception e) {
			resultMap.put("status", 0);
			resultMap.put("msg", "EC00003");
			resultMap.put("resultData", null);
		}
		return resultMap;
	}
}
