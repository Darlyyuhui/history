package com.xiangxun.atms.framework.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>文件下载</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
@Controller
public class DownLoadFileCtl {
	/***
	 * 文件下载
	 * @param fileName
	 * @param response
	 */
	@RequestMapping(value="/downloadfile/{fileName}/")
	public void download(@RequestParam("filepath") String filepath,@PathVariable String fileName,HttpServletResponse response){
		java.io.BufferedInputStream bis = null;
		OutputStream bos = null;
		try {
			response.setContentType("text/plain");
			String root  = System.getProperty("atms.root");
			String filePath = root+File.separator+filepath;
			File fileLength = new File(filePath);
			response.setHeader("Content-type", "application/force-download");
			response.setHeader("Content-Transfer-Encoding", "Binary");
			response.setHeader("Content-length", "" + fileLength.list());
			//设置中文文件名
			String name =java.net.URLEncoder.encode(fileName,"utf-8");
			String filename=name.indexOf('+') == -1?name:name.replace('+', ' ');
			response.setHeader("Content-Disposition", "attachment;filename="+ filename);
			bis = new BufferedInputStream(new FileInputStream(fileLength));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[1024];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}
}
