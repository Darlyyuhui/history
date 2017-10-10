package com.xiangxun.epms.mobile.business.cache;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class RegTypeCache implements CommandLineRunner{
	public static Map<String,String> DIC_REGCODE_TABLENAME= new HashMap<>();
	private static final Logger logger = LoggerFactory.getLogger(RegTypeCache.class);
	@Override
	public void run(String... args) throws Exception {
		
		logger.info("loading all data dic...");
		DIC_REGCODE_TABLENAME.put("SD", "T_SAMPLING_FARM_REG");
		DIC_REGCODE_TABLENAME.put("NTTR", "T_SAMPLING_LAND_REG");
		DIC_REGCODE_TABLENAME.put("DBS", "T_SAMPLING_WATER_REG");
		DIC_REGCODE_TABLENAME.put("DN", "T_SAMPLING_WATER_REG");
		DIC_REGCODE_TABLENAME.put("DQ", "T_SAMPLING_AIR_REG");
		DIC_REGCODE_TABLENAME.put("FL", "T_SAMPLING_MANURE_REG");
		DIC_REGCODE_TABLENAME.put("DXS", "T_SAMPLING_WATER_REG");
	   logger.info("load all data dic success.");
	}

}
