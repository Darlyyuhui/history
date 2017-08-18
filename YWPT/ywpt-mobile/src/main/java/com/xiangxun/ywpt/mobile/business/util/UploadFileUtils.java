package com.xiangxun.ywpt.mobile.business.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



/**
 * sprmvc上传文件类型
 * 
 * @author lhh
 * 
 */
public class UploadFileUtils {
	 private static Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

	/**
	 * 根据上传文件和文件名称上传文件。
	 * @param CommonsMultipartFile
	 *            上传文件类型
	 * @param filePath
	 *            上传文件路径
	 */
	public static void up(CommonsMultipartFile commonsMultipartFile, String filePath) {
		InputStream streamIn =null;
		OutputStream streamOut =null;
		try {
			streamIn = commonsMultipartFile.getInputStream();
			streamOut = new FileOutputStream(filePath);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = streamIn.read(buffer, 0, 1024)) != -1) {
				streamOut.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			logger.error("up() 上传文件io流出错","");
		} finally {
			try {
				streamOut.close();
				streamOut = null;
			} catch (IOException e) {
				logger.error("up() 关闭输出流出错");
			}
			try {
				streamIn.close();
				streamIn = null;
			} catch (IOException e) {
				logger.error("up() 关闭输出入流出错");
			}
		}
	}
	
	/**
	 * 根据上传文件和文件名称上传文件。
	 * @param CommonsMultipartFile
	 *            上传文件类型
	 * @param filePath
	 *            上传文件路径
	 */
	public static void up(MultipartFile multipartFile, String filePath) {
		InputStream streamIn =null;
		OutputStream streamOut =null;
		try {
			streamIn = multipartFile.getInputStream();
			streamOut = new FileOutputStream(filePath);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = streamIn.read(buffer, 0, 1024)) != -1) {
				streamOut.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			logger.error("up() 上传文件io流出错","");
		} finally {
			try {
				streamOut.close();
				streamOut = null;
			} catch (IOException e) {
				logger.error("up() 关闭输出流出错");
			}
			try {
				streamIn.close();
				streamIn = null;
			} catch (IOException e) {
				logger.error("up() 关闭输出入流出错");
			}
		}
	}
	
	/**
	 * 
	 * 保存文件   1,文件   2，保存路径 3，文件名称
	 * @author kouyunhao
	 * @version 1.0
	 * @param stream
	 * @param path
	 * @param filename
	 * @throws IOException
	 * Oct 10, 2013
	 */
	public static void SaveFileFromInputStream(InputStream stream, String path, String filename) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + "/" + filename);
		byte[] buffer = new byte[1024 * 1024];
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}
	
	/**
	 * 根据路径创建一系列的目录,只创建文件夹
	 * 
	 * @param path
	 */
	public static boolean mkDirectory(String path) {
		File file = null;
		try {
			file = new File(path);
			if (!file.exists()) {
				return file.mkdirs();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			file = null;
		}
		return false;
	}
	
	/**
	 * 根据路径和文件名称，创建该路径下文件
	 * @param path 创建文件目录
	 * @param name 创建文件的名称
	 * @return File 返回该文件
	 */
	public static File getCreateFile(String path,String name){
		File file = new File (path+File.separator+name);
		if (file.exists()) {
			return file;
		}else {
			try {
				if (file.createNewFile()) {
					return file;
				}else {
					return  new File("");
				}
			} catch (IOException e) {
				return new File("");
			}
		}
	}
}
