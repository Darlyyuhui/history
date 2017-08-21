package com.xiangxun.atms.framework.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xiangxun.atms.framework.log.Logging;



/**
 * sprmvc上传文件类型
 * 
 * @author lhh
 * 
 */
public class UploadFileUtils {
	 Logging logger = new Logging(UploadFileUtils.class);

	/**
	 * 根据上传文件和文件名称上传文件。
	 * @param CommonsMultipartFile
	 *            上传文件类型
	 * @param filePath
	 *            上传文件路径
	 */
	public static void up(CommonsMultipartFile commonsMultipartFile, String filePath) {
		// String fname=CommonsMultipartFile.getName();
		// String size=Integer.toString(file.getFileSize())+"bytes";

		InputStream streamIn =null;
		OutputStream streamOut =null;
		// try {
		try {
			streamIn = commonsMultipartFile.getInputStream();
			streamOut = new FileOutputStream(filePath);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = streamIn.read(buffer, 0, 1024)) != -1) {
				streamOut.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
//			logger.e
//			logger.error("up() 上传文件io流出错","");
		} finally {
			try {
				streamOut.close();
				streamOut = null;
			} catch (IOException e) {
//				logger.
//				logger.error("up() 关闭输出流出错");
			}
			try {
				streamIn.close();
				streamIn = null;
			} catch (IOException e) {
//				logger.error("up() 关闭输出入流出错");
			}
		}
	}
	
	/**
	 * 
	 * @author kouyunhao
	 * @version 1.0
	 * @param request
	 * @param response
	 * @throws IOException
	 * Oct 10, 2013
	 */
	public static void picUploadAjax(MultipartHttpServletRequest request, 
			HttpServletResponse response, String savePath, String uploadid) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String suffix = null;
		String fileName = null;
		String errorinfo = "";
		if (null != uploadid && !"".equals(uploadid)) {
			try {
				MultipartFile mf = request.getFile(uploadid);
				fileName = mf.getOriginalFilename();
				if (null != mf && !"".equals(mf)) {
					suffix = fileName.substring(fileName.lastIndexOf("."));
					//判断上传的文件格式是否正确
					if ((".png.jpg".indexOf(suffix.toLowerCase()) != -1)) {
						Integer fileSize = (int) mf.getSize()/1024;
						//如果文件小于1M，则上传文件，否则提示用户不能超过1M
						if(fileSize < 1024){
							String uploadName = FileUtils.getFormattedFileName(suffix);
							SaveFileFromInputStream(mf.getInputStream(), savePath, uploadName);
							out.write("{'state':'0','fileName':'" + uploadName + "','uploadid':'" + uploadid + "'}");
						}else{
							errorinfo = "上传的文件大小不能超过1M";
							out.write("{'state':'1','message':'" + errorinfo + "'}");
						}
					}else{
						errorinfo = "上传的文件格式不支持";
						out.write("{'state':'1','message':'" + errorinfo + "'}");
					}
				}
			} catch (Exception e) {
				errorinfo = "请刷新页面重新上传";
				out.write("{'state':'1','message':'" + errorinfo + "'}");
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
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}
}
