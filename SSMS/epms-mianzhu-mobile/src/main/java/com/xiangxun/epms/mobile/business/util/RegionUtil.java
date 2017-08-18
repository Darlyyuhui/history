package com.xiangxun.epms.mobile.business.util;

import java.util.Map;

import com.xiangxun.epms.mobile.business.cache.RegionCache;

public class RegionUtil {
  public static String getRegionId(String name){
	  Map<String,String> map= RegionCache.NAME_ID;
	  for(String str:map.keySet()){
		  if(name.contains(str)){
			  String regionId= map.get(str);
			  return regionId;
		  }
	  }
	  return null;
  }
}
