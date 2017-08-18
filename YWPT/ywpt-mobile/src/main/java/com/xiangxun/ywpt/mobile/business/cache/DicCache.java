package com.xiangxun.ywpt.mobile.business.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.xiangxun.ywpt.mobile.business.domain.Dic;
import com.xiangxun.ywpt.mobile.business.service.UserService;

@Component
public class DicCache implements CommandLineRunner {

	public static Map<String, List<Dic>> DIC_MAP = new HashMap<>();
	
	private static final Logger logger = LoggerFactory.getLogger(DicCache.class);

	@Resource
	UserService userService;

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("loading all data dic...");
		userService.initDic();
		logger.info("load all data dic success.");
	}
	
}
