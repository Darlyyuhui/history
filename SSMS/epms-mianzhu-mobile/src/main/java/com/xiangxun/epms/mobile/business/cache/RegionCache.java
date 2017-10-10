package com.xiangxun.epms.mobile.business.cache;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
//缓存行政区域
import com.xiangxun.epms.mobile.business.service.RegionService;
@Component
public class RegionCache implements CommandLineRunner{
    @Resource
    RegionService regionService;
    public static Map<String,String> NAME_ID = new HashMap<>();
	
	private static final Logger logger = LoggerFactory.getLogger(DicCache.class);
	@Override
	public void run(String... args) throws Exception {
		logger.info("loading all data region...");
		regionService.initRegion();
		logger.info("load all data region success.");
		
	}

}
