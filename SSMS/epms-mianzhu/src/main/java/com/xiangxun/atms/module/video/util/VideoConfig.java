package com.xiangxun.atms.module.video.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.util.ResourceUtils;

import com.xiangxun.atms.framework.log.Logging;

public class VideoConfig {

	public static Logging logger = new Logging(VideoConfig.class);
	
	public static Map<String, String> PROP_MAP = new HashMap<String, String>();
	
	public static void readConfig() {
		InputStreamReader isr = null;
		try {
			String filePath = ResourceUtils.getFile("classpath:video.properties").getPath();
			isr = new InputStreamReader(new FileInputStream(filePath), "utf-8");
			Properties p = new Properties();
			p.load(isr);
			PROP_MAP = new HashMap<String, String>();
			for (Object key : p.keySet()) {
				PROP_MAP.put(key.toString(), p.getProperty(key.toString()));
			}
		} catch (Exception e) {
			logger.error("读取视频配置文件出错：" + e.getMessage());
		} finally {
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					isr = null;
				}
			}
		}
	}
	
}
