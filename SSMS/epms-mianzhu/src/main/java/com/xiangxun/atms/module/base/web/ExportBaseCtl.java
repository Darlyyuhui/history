package com.xiangxun.atms.module.base.web;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import com.google.common.collect.Table;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.bs.cache.TRegionCache;

/**
 * 用于导出的基础类
 * @author HX
 *
 */
public class ExportBaseCtl extends BaseCtl {

	/**
	 * 获取导出模板文件
	 * @param templatePath
	 * @return
	 * @throws Exception
	 */
	public InputStream getTemplateFile(String templatePath) throws Exception {
		URL url = this.getClass().getClassLoader().getResource(templatePath);
		if(url == null){
			url = ResourceUtils.getURL("classpath:" + templatePath);
		}
		if(url == null){
			url = ResourceUtils.getURL("file:" + templatePath);
		}
		InputStream ins = new FileInputStream(url.getFile());
		return ins;
	}
	
	/**
	 * 获取区域名称
	 * @param regionId		区域ID
	 * @param isFullName	是否返回全名称
	 * @param cache			缓存类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getRegionNameById(String regionId, boolean isFullName, Cache cache) {
		String key = isFullName ? TRegionCache.ID_FULLNAME : TRegionCache.ID_NAME;
		Table<String, String, String> regionTable = (Table<String, String, String>)cache.get(key);
		Map<String, String> regionMap = regionTable.column(key);
		return regionMap.get(regionId);
	}
	
}
