package com.xiangxun.atms.framework.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**显示远程图片
 * @author zhouhaij
 */
public class LsRemoteImageSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LsRemoteImageSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String url = request.getParameter("upfile");
		String state = "远程图片抓取成功！";

		String filePath = "upload";
		String[] arr = url.split("ue_separate_ue");
		String[] outSrc = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			//保存文件路径
			String str = request.getSession().getServletContext().getRealPath(request.getServletPath());
			File f = new File(str);
			String savePath = f.getParent() + "/" + filePath;
			//格式验证
			String type = getFileType(arr[i]);
			if (type.equals("")) {
				state = "图片类型不正确！";
				continue;
			}
			String saveName = Long.toString(new Date().getTime()) + type;
			//大小验证
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection conn = (HttpURLConnection) new URL(arr[i]).openConnection();
			if (conn.getContentType().indexOf("image") == -1) {
				state = "请求地址头不正确";
				continue;
			}
			if (conn.getResponseCode() != 200) {
				state = "请求地址不存在！";
				continue;
			}
			File dir = new File(savePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File savetoFile = new File(savePath + "/" + saveName);
			outSrc[i] = filePath + "/" + saveName;
			try {
				InputStream is = conn.getInputStream();
				OutputStream os = new FileOutputStream(savetoFile);
				int b;
				while ((b = is.read()) != -1) {
					os.write(b);
				}
				os.close();
				is.close();
				// 这里处理 inputStream
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("页面无法访问");
			}
		}
		String outstr = "";
		for (int i = 0; i < outSrc.length; i++) {
			outstr += outSrc[i] + "ue_separate_ue";
		}
		outstr = outstr.substring(0, outstr.lastIndexOf("ue_separate_ue"));
		response.getWriter().print("{'url':'" + outstr + "','tip':'" + state + "','srcUrl':'" + url + "'}");
	}

	
	public String getFileType(String fileName) {
		String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
		Iterator<String> type = Arrays.asList(fileType).iterator();
		while (type.hasNext()) {
			String t = type.next();
			if (fileName.endsWith(t)) {
				return t;
			}
		}
		return "";
	}
}
