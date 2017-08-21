package com.xiangxun.atms.common.install;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiangxun.atms.framework.util.DeEncryptUtil;

public class RuningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/install/run.jsp");
		dispatcher.forward(request, response);
	}
	
	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//平台初始管理员账号设置（暂不实现，使用约定的管理员）
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		
		//平台初始化数据
//		if ("true".equals(isInitData)) {
//			String initPath = getServletContext().getRealPath(initFileName);
//			List<String> initList = Install.readSql(initPath);
//			Install.createTable(dbHost, dbPort, dbName, dbUser, dbPassword,
//					initList);
//		}
		
		//修改配置文件
		String dbXmlFileNameFrom = "/install/config/application.properties";
		String dbXmlFileNameTo = "/WEB-INF/classes/application.properties";
		
		String webXmlFrom = "/install/config/web.xml";
		String webXmlTo = "/WEB-INF/web.xml";
		
		// 更新配置
		String dbport = (String)request.getSession().getAttribute("dbPort");
		
		String dbhost = (String)request.getSession().getAttribute("dbhost");
		String dbname = (String)request.getSession().getAttribute("dbname");
		 
		String cdbhost = (String)request.getSession().getAttribute("cdbhost");
		String cdbname = (String)request.getSession().getAttribute("cdbname");
		 
		String gdbhost = (String)request.getSession().getAttribute("gdbhost");
		String gdbname = (String)request.getSession().getAttribute("gdbname");
	     
		String dbuser = (String)request.getSession().getAttribute("dbuser");
		String dbpassword = (String)request.getSession().getAttribute("dbpassword");
		
		String postgishost = (String)request.getSession().getAttribute("postgishost");
		String postgisport = (String)request.getSession().getAttribute("postgisport");
		String postgisname = (String)request.getSession().getAttribute("postgisname");
		
	     
//		Install.updateConfig(dbHost, dbPort, dbName, dbUser, dbPassword,domain, cxtPath, port);
		
		// 处理数据库配置文件
		String dbXmlfromPath = getServletContext().getRealPath(dbXmlFileNameFrom);
		String dbXmltoPath = getServletContext().getRealPath(dbXmlFileNameTo);
		
		try {
			//加密明文数据库账号
			DeEncryptUtil des = new DeEncryptUtil();
			dbuser = des.encrypt(dbuser);
			dbpassword = des.encrypt(dbpassword);
			Install.webXml(dbXmlfromPath, dbXmltoPath);
			Install.dbXml(dbXmltoPath,dbport,dbhost,dbname,cdbhost,cdbname,gdbhost,gdbname,dbuser,dbpassword,postgishost,postgisport,postgisname);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// 处理web.xml
	    try {
		  String webXmlFromPath = getServletContext().getRealPath(webXmlFrom);
		  String webXmlToPath = getServletContext().getRealPath(webXmlTo);
		  Install.webXml(webXmlFromPath, webXmlToPath);
	    } catch (Exception e) {
			throw new ServletException("install failed!", e);
		}
	    
		String host = request.getRemoteHost();
		request.setAttribute("hostip", host);
		int port = request.getServerPort();
		request.setAttribute("hostport", port);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/install/complete.jsp");
		dispatcher.forward(request, response);
	}
}
