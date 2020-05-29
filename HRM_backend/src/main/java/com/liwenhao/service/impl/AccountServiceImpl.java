package com.liwenhao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liwenhao.dao.AccountDao;
import com.liwenhao.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountDao accountDao;
	
	public List<Map<String, Object>> findAllAccount(){
		return  accountDao.findAllAccount();
	}

	@Override
	public Integer accountCheck(Map<String, Object> param) {
		return accountDao.accountCheck(param);
	};

}
