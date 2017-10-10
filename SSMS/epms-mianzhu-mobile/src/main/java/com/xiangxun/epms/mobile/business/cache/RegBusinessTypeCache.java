
package com.xiangxun.epms.mobile.business.cache;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.xiangxun.epms.mobile.business.domain.SamplingAirReg;
import com.xiangxun.epms.mobile.business.domain.SamplingBackReg;
import com.xiangxun.epms.mobile.business.domain.SamplingFarmReg;
import com.xiangxun.epms.mobile.business.domain.SamplingLandReg;
import com.xiangxun.epms.mobile.business.domain.SamplingManureReg;
import com.xiangxun.epms.mobile.business.domain.SamplingWaterReg;
@Component
public class RegBusinessTypeCache implements CommandLineRunner{
	public static Map<String,String> DIC_REGCODE_TABLENAME= new HashMap<>();
	public static Map<String,String> BUSINESSTYPE= new HashMap<String,String>();
	private static final Logger logger = LoggerFactory.getLogger(RegBusinessTypeCache.class);
	@Override
	public void run(String... args) throws Exception {
		
		logger.info("loading all data RegBusinessType...");
		BUSINESSTYPE.put("SD", SamplingFarmReg.class.getSimpleName());
		BUSINESSTYPE.put("NTTR", SamplingLandReg.class.getSimpleName());
		BUSINESSTYPE.put("WATER", SamplingWaterReg.class.getSimpleName());
		BUSINESSTYPE.put("BJTR", SamplingBackReg.class.getSimpleName());
		BUSINESSTYPE.put("FL", SamplingManureReg.class.getSimpleName());
		BUSINESSTYPE.put("DQ", SamplingAirReg.class.getSimpleName());
	   logger.info("load all data RegBusinessType success.");
	}

}