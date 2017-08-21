package com.xiangxun.atms.framework.base;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.xiangxun.atms.common.dictionary.vo.KeyValue;
import com.xiangxun.atms.framework.constant.Constant;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.StringUtils;
/***
 * 所有Controller需要继承此接口，各个业务Controller的公共类环境
 * @author yantao
 * @2013-3-21 下午02:56:11
 */
public class BaseCtl {
	
	/***
	 * 可继承的日志记录器
	 */
	protected Logging logger = new Logging(getClass());
	
	/***
	 * 返回成功的json字符
	 * @return
	 */
	protected  String returnJson(){
		JsonObject json = new JsonObject();
		json.addProperty("result", "ok");
		return json.toString();
	}
	
	
	/***
	 * 返回失败的json字符
	 * @return
	 */
	protected  String returnErrorJson(){
		JsonObject json = new JsonObject();
		json.addProperty("result", "error");
		return json.toString();
	}

	

	/***
	 * 获取本产品是否为发布模式
	 * @param req
	 * @return
	 */
	protected boolean isRealse(ServletContext sc){
		Object object = sc.getAttribute(Constant.APPLICATION_CONTEXT_MODE);
		if(object!=null){
			String contextMode = object.toString();
			if(Constant.RELEASE_MODE.equals(contextMode)){
				return true;	
			}
		}
		return false;
	}
	
	/***
	 * 获取登陆用户
	 * @return
	 */
	protected OperatorDetails getCurrentUser(){
		return SpringSecurityUtils.getCurrentUser();
	}
	
	/***
	 * 获取登陆用户id
	 * @return
	 */
	protected String getCurrentUserId(){
		OperatorDetails userInfo =  getCurrentUser();
		if(userInfo==null){
			throw new UsernameNotFoundException("用户不存在");
		}
		return userInfo.getId();
	}
	protected File upload(MultipartFile patch, HttpServletRequest request,
			HttpServletResponse resp, List<KeyValue> errors, String uploadPath,String rexPostfix
            ,String filename)
			throws IllegalStateException, IOException {

		File uploadfile = null;
		if (!patch.isEmpty()) {
			String fileName = patch.getOriginalFilename();
			/**
			 * 获取文件后缀
			 */
			logger.debug("upload file name:" + fileName);
			String suffix = fileName.substring(fileName.lastIndexOf("."));

			/**
			 * 判断上传的文件格式是否正确
			 */
			if ((rexPostfix.indexOf(suffix
					.toLowerCase()) != -1)) {
				Integer fileSize = (int) patch.getSize() / 1024;

				/**
				 * 如果文件小于10M，则上传文件，否则提示用户不能超过10M
				 */
				if (fileSize <= 10240) {
					logger.debug("file save path: " + uploadPath);
					File filePath = new File(uploadPath);
					logger.debug(filePath.getAbsolutePath());
					/**
					 * 判读存储文件路是否存在，不存在则创建
					 */
					if (!filePath.exists()) {
						filePath.mkdirs();
						logger.debug("上传文件路径不存在，创建成功！");
					}
					/**
					 * 文件开始上传到服务器上
					 */
					uploadfile = new File(filePath + "\\" + filename+suffix);
					patch.transferTo(uploadfile);
					errors.add(new KeyValue("msg", "上传成功！"));

				} else {
					logger.error("上传的文件太大，文件大小不能超过10M");
					errors.add(new KeyValue("msg", "上传的文件太大，文件大小不能超过10M"));
				}
			} else {
				logger.error("上传的文件格式不支持");
				errors.add(new KeyValue("msg", "上传的文件格式不支持"));

			}
		}
		return uploadfile;
	}
	protected File upload(MultipartFile patch,HttpServletRequest request,HttpServletResponse resp, List<KeyValue> errors) throws IllegalStateException, IOException {
		String uploadPath="/upload_res";
		File uploadfile = null;
		if (!patch.isEmpty()) {
				String fileName = patch.getOriginalFilename();
				/**
				 * 获取文件后缀
				 */
				logger.debug("upload file name:"+fileName);
				String suffix = fileName.substring(fileName.lastIndexOf("."));

				/**
				 * 判断上传的文件格式是否正确
				 */
				if ((".zip.rar.gz.tar.bz2.txt.xls.xlsx.doc.docx.pdf".indexOf(suffix.toLowerCase()) != -1)) {
					Integer fileSize = (int) patch.getSize() / 1024;

					/**
					 * 如果文件小于10M，则上传文件，否则提示用户不能超过10M  
					 */
					if (fileSize <= 10240) {
						logger.debug("file save path: "+uploadPath);
						File filePath = new File(request.getSession().getServletContext().getRealPath(uploadPath));
						logger.debug(filePath.getAbsolutePath());
						/**
						 * 判读存储文件路是否存在，不存在则创建
						 */
						if (! filePath.exists()) {
							filePath.mkdirs();
							logger.debug("上传文件路径不存在，创建成功！");
						}
						/**
						 * 文件开始上传到服务器上
						 */
						uploadfile = new File(filePath.getAbsolutePath()+"\\"+fileName);
						patch.transferTo(uploadfile);
						errors.add(new KeyValue("msg", "上传成功！"));

					} else {
						logger.error("上传的文件太大，文件大小不能超过10M");
						errors.add(new KeyValue("msg", "上传的文件太大，文件大小不能超过10M"));
					}
				} else {
					logger.error("上传的文件格式不支持");
					errors.add(new KeyValue("msg", "上传的文件格式不支持"));
					
				}
		}
		return uploadfile;
	}
	
	/***
	 * 下载
	 * @param filePath
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected void download(String filePath,String fileName,HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		try {
			long fileLength = new File(filePath).length();
			response.setContentType("application/x-msdownload;");response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(filePath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	/***
	 * 下载支持中文文件名称
	 * @param filePath
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected void downloadFile(String filePath,String fileName,HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		try {
			long fileLength = new File(filePath).length();
			response.setContentType("application/x-download;");
			String name =java.net.URLEncoder.encode(fileName,"utf-8");
			//edit by kouyunhao 2014-02-10 完善判断条件
			String filename=name.indexOf('+') == -1?name:name.replace('+', ' ');
			response.setHeader("Content-disposition", "attachment; filename="+ filename);
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(filePath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			//edit by kouyunhao 2014-02-11 解决取消下载出现异常：ClientAbortException:  java.io.IOException
			logger.info("文件下载操作被取消");
			//e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	/***
	 * 下载任何文件，支持中文文件名称
	 * @param filePath
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected void downloadFileAll(String filePath,String fileName,HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		try {
			long fileLength = new File(filePath).length();
			response.setContentType("unknown");
			String name =java.net.URLEncoder.encode(fileName,"utf-8");
			//edit by kouyunhao 2014-02-10 完善判断条件
			String filename=name.indexOf('+') == -1?name:name.replace('+', ' ');
			response.setHeader("Content-disposition", "attachment; filename="+ filename);
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(filePath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			//edit by kouyunhao 2014-02-11 解决取消下载出现异常：ClientAbortException:  java.io.IOException
			logger.info("文件下载操作被取消");
			//e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	/***
	 * 下载网络文件，支持中文文件名称
	 * @param filePath
	 * @param fileName
	 * @param request
	 * @param response
	 * @param path 网络文件存放在本地
	 * @throws Exception
	 * @author lhh
	 */
	protected void downloadFileInternet(String filePath,String fileName,HttpServletRequest request,HttpServletResponse response,String path) throws Exception {
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		int bytesum=0;
		int byteread=0;
		try {
			URL url= new URL(filePath);
			URLConnection connection= url.openConnection();
			InputStream inputStream = null;
			inputStream = connection.getInputStream();
			FileOutputStream fStream = new FileOutputStream(path+"//"+fileName);
			byte[] buffer= new byte[1024*100];
			while((byteread=inputStream.read(buffer))!=-1)
			{
				bytesum+=byteread;
				System.out.println(bytesum);
				fStream.write(buffer,0,byteread);
			}
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			
			long fileLength = new File(path+"//"+fileName).length();
			response.setContentType("application/x-download;");
			String name =java.net.URLEncoder.encode(fileName,"utf-8");
			//edit by kouyunhao 2014-02-10 完善判断条件
			String filename=name.indexOf('+') == -1?name:name.replace('+', ' ');
			response.setHeader("Content-disposition", "attachment; filename="+ filename);
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(path+"//"+fileName));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			//edit by kouyunhao 2014-02-11 解决取消下载出现异常：ClientAbortException:  java.io.IOException
			logger.info("文件下载操作被取消");
			//e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	
	/**
	 * 增加对模糊查询参数的修改，用于查询
	 * 2014.07.17 haoxiang
	 * @param searchParams
	 * @param key
	 */
	protected void addLikeParmMap(Map<String, Object> searchParams, String[] parms) {
		if (parms == null) {
			return;
		}
		for (String parm : parms) {
			if(StringUtils.notEmpty(searchParams.get(parm)+"") && null != searchParams.get(parm)) {
				searchParams.put(parm, "'%"+searchParams.get(parm).toString()+"%'");
			}
		}
	}
	/**
	 * 删除对模糊查询参数的修改，用于展示
	 * 2014.07.17 haoxiang
	 * @param searchParams
	 * @param key
	 */
	protected void removeLikeParmMap(Map<String, Object> searchParams, String[] parms) {
		if (parms == null) {
			return;
		}
		for (String parm : parms) {
			if(StringUtils.notEmpty(searchParams.get(parm)+"") && null != searchParams.get(parm)) {
				searchParams.put(parm, searchParams.get(parm).toString().replace("'", "").replace("%", ""));
			}	
		}
	}
	
	/**
	 * 从session中取出查询参数
	 * @param request
	 * @param menuId
	 * @param searchParams
	 */
	protected void updateSession(HttpServletRequest request, 
			String menuId, Map<String, Object> searchParams) {
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuId, searchParams);
	}
}
