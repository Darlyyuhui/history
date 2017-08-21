package com.xiangxun.atms.icabinet.sdk;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import org.apache.log4j.Logger;

public class SDKTools {
	private static Logger logger = Logger.getLogger(SDKTools.class);

	public static File getAppPath() {
		try {
			URL url = SDKTools.class.getProtectionDomain().getCodeSource().getLocation();
			logger.info(url.toString());
			File path = new File(url.toURI());
			if (path.isFile())//jar文件
				path = path.getParentFile();
			return path;
		} catch (URISyntaxException ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

	public static String getAppPathStr() {
		File appPath = getAppPath();
		if (appPath != null)
			return appPath.getPath();
		return "";
	}

	public static String getAppSubPath(String... subPath) {
		Path path = java.nio.file.Paths.get(getAppPathStr(), subPath);
		logger.info(path.toString());
		return path.toString();
	}	

	public static String bytesToGBK(byte[] data) {
		try {
			return new String(data, "GBK");
		} catch (UnsupportedEncodingException ex) {
			logger.error(ex.getMessage());
		}
		return "";
	}	
}
