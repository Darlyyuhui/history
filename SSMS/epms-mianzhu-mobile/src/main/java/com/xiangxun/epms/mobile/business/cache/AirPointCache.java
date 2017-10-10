package com.xiangxun.epms.mobile.business.cache;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.xiangxun.epms.mobile.business.domain.AirPoint;
import com.xiangxun.epms.mobile.business.service.AirPointService;
@Component
public class AirPointCache implements CommandLineRunner {
	
	@Resource 
	AirPointService airPointService;
	public static  List<AirPoint> AIRPOINT = new ArrayList<AirPoint>();
	private static final Logger logger = LoggerFactory.getLogger(AirPointCache.class);
	@Override
	public void run(String... args) throws Exception {
		logger.info("loading air point ...");
		airPointService.getIdName();
		logger.info("loading air point success...");
		
	}

}
