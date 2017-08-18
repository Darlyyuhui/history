package com.xiangxun.atms.module.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FtlJsonUtil {

	private static final Logger logger = LoggerFactory.getLogger(FtlJsonUtil.class);
	// 模板配置对象
	private Configuration cfg;
	// 模板文件名
	private String ftlName;
	// 模板文件根目录
	private String rootPath;

	public FtlJsonUtil(String rootPath, String ftlName) {
		this.rootPath = rootPath;
		this.ftlName = ftlName;
		this.init();
	}
	
	/**
	 * 初始化配置
	 */
	private void init() {
		cfg = new Configuration(Configuration.VERSION_2_3_23);
		File dir = null;
		try {
			//存放模板文件的根目录
			dir = new File(ResourceUtils.getFile("classpath:ftl/"+rootPath).getPath());
			cfg.setDirectoryForTemplateLoading(dir);
			cfg.setDefaultEncoding("utf-8");
		} catch (IOException e) {
			logger.error("创建模板配置出错：" + e.getMessage());
		}
	}

	/**
	 * 根据模板生成json串
	 * @param map
	 * @return java.lang.String
	 */
	public String process(Map<String, Object> map) {
		StringWriter sw = null;
		try {
			sw = new StringWriter();
			Template template = cfg.getTemplate(this.ftlName);
			template.process(map, sw);
			return sw.getBuffer().toString();
		} catch (Exception e) {
			logger.error("根据模板生成JSON出错：" + e.getMessage());
			return null;
		} finally {
			if (sw != null) {
				try {
					sw.flush();
					sw.close();
				} catch (IOException e) {
					sw = null;
				}
			}
		}
	}
	
	/**
	 * 根据模板生成json对象
	 * @param map
	 * @return com.google.gson.JsonObject
	 */
	public JsonObject processJson(Map<String, Object> map) {
		String str = this.process(map);
		try {
			return new JsonParser().parse(str).getAsJsonObject();
		}catch (Exception e) {
			logger.error("转换JSON对象时出错：" + e.getMessage());
			return null;
		}
	}
	
}
