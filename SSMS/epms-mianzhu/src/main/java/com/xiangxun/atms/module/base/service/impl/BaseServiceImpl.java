package com.xiangxun.atms.module.base.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.module.base.dao.BaseDao;
import com.xiangxun.atms.module.base.service.BaseService;

@Service
public class BaseServiceImpl implements BaseService {

	@Resource
	BaseDao bd;

	@Override
	public boolean checkCode(String tableName, String codeColumn, String code) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("tableName", tableName);
		args.put("codeColumn", codeColumn);
		args.put("codeValue", code);
		Integer count = bd.checkCode(args);
		return count == null || count == 0 ? true : false;
	}
	
}
