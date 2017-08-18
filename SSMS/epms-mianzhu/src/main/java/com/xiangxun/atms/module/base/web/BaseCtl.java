package com.xiangxun.atms.module.base.web;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Table;
import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.bs.cache.TRegionCache;

public abstract class BaseCtl<T, S> extends com.xiangxun.atms.framework.base.BaseCtl {

	protected abstract BaseService<T, S> getBaseService();
	
	protected Logging logger = new Logging(this.getClass());
	
	@Resource
	protected com.xiangxun.atms.module.base.service.BaseService baseService;

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }
	
	@ModelAttribute("info")
	public T getInfo(@RequestParam(value = "id", required = false) String id) {
		if (StringUtils.isNotEmpty(id)) {
			return getBaseService().getById(id);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "checkCode/{code}/", method = RequestMethod.POST)
	public ResponseEntity checkCode(@PathVariable String code, String tName, String cName) {
		ResponseEntity re = new ResponseEntity();
		if (StringUtils.isEmpty(tName) || StringUtils.isEmpty(cName) || StringUtils.isEmpty(code)) {
			re.setResult("exception");
			re.setMessage("缺失查询参数");
		} else {
			try {
				if (baseService.checkCode(tName, cName, code)) {
					re.setResult("ok");
				} else {
					re.setResult("error");
					re.setMessage("编号重复");
				}
			}catch(Exception e) {
				logger.error("校验编号重复出错，" +e.getMessage());
				re.setResult("exception");
				re.setMessage("请检查查询参数");
			}
		}
		return re;
	}
	
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
