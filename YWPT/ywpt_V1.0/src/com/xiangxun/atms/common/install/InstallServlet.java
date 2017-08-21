package com.xiangxun.atms.common.install;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InstallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/install/setting.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		String dbPort = request.getParameter("dbport");
		
		String dbhost = request.getParameter("dbhost");
		String dbname = request.getParameter("dbname");
		
		String cdbhost = request.getParameter("cdbhost");
		String cdbname = request.getParameter("cdbname");
		
		String gdbhost = request.getParameter("gdbhost");
		String gdbname = request.getParameter("gdbname");
		
		String dbUser = request.getParameter("dbuser");
		String dbPassword = request.getParameter("dbpassword");
		
		String postgishost = request.getParameter("postgishost");
		String postgisname = request.getParameter("postgisname");
		String postgisport = request.getParameter("postgisport");
		
		//保存环境配置信息到SESSION
		request.getSession().setAttribute("dbPort", dbPort);
		request.getSession().setAttribute("dbhost", dbhost);
		request.getSession().setAttribute("dbname", dbname);
		request.getSession().setAttribute("cdbhost", cdbhost);
		request.getSession().setAttribute("cdbname", cdbname);
	    request.getSession().setAttribute("gdbhost", gdbhost);
	    request.getSession().setAttribute("gdbname", gdbname);
	    request.getSession().setAttribute("dbuser", dbUser);
	    request.getSession().setAttribute("dbpassword", dbPassword);
	    request.getSession().setAttribute("postgishost", postgishost);
	    request.getSession().setAttribute("postgisname", postgisname);
	    request.getSession().setAttribute("postgisport", postgisport);
		
		boolean setupflag = true;
		//检查ORACLE安装环境
		
		//================主库===============
		
			//目标正确个数
			request.setAttribute("tablenum", "<font color=green>"+CheckConstant.ITMS_TABLE.length+"</font>");
			request.setAttribute("triggernum","<font color=green>"+CheckConstant.ITMS_TRIGGER.length+"</font>");
			request.setAttribute("procedurenum","<font color=green>"+CheckConstant.ITMS_PROCEDURE.length+"</font>");
			request.setAttribute("packagenum","<font color=green>"+CheckConstant.ITMS_PACKAGE.length+"</font>");
			request.setAttribute("packagelistnum","<font color=green>"+CheckConstant.ITMS_PACKAGEBODY.length+"</font>");
			
			try {
			  Connection conn = Install.getConn(dbhost, dbPort, dbname, dbUser, dbPassword);
			  request.setAttribute("connstatus","<font color=green>连接成功！</font>");
			  
			  if(conn!=null){
				    List<String> tableList = Install.getAllObjByType(dbhost, dbPort, dbname, dbUser, dbPassword, "TABLE","itms");
					List<String> triggerList = Install.getAllObjByType(dbhost, dbPort, dbname, dbUser, dbPassword, "TRIGGER","itms");
					List<String> procedureList = Install.getAllObjByType(dbhost, dbPort, dbname, dbUser, dbPassword, "PROCEDURE","itms");
					List<String> packageList = Install.getAllObjByType(dbhost, dbPort, dbname, dbUser, dbPassword, "PACKAGE","itms");
					List<String> packagebodyList = Install.getAllObjByType(dbhost, dbPort, dbname, dbUser, dbPassword, "PACKAGE BODY","itms");
					conn.close();
					
					//实际个数
					request.setAttribute("stablenum", tableList.size());
					request.setAttribute("striggernum", triggerList.size());
					request.setAttribute("sprocedurenum", procedureList.size());
					request.setAttribute("spackagenum", packageList.size());
					request.setAttribute("spackagelistnum", packagebodyList.size());
					
					//个数不符则不通过
					if(tableList.size()<CheckConstant.ITMS_TABLE.length || triggerList.size()<CheckConstant.ITMS_TRIGGER.length || 
							procedureList.size()<CheckConstant.ITMS_PROCEDURE.length || packageList.size()<CheckConstant.ITMS_PACKAGE.length 
							|| packagebodyList.size()< CheckConstant.ITMS_PACKAGEBODY.length){
						setupflag = false;
					}
					
					//验证角色权限
					boolean flag_connect = Install.getRoleNameExist(dbhost, dbPort, dbname, dbUser, dbPassword, "connect");
					boolean flag_resource = Install.getRoleNameExist(dbhost, dbPort, dbname, dbUser, dbPassword, "resource");
					boolean flag_dba = Install.getRoleNameExist(dbhost, dbPort, dbname, dbUser, dbPassword, "dba");
					//验证系统权限
					boolean flag_createtable = Install.getSysRoleNameExist(dbhost, dbPort, dbname, dbUser, dbPassword, "create table");
					boolean flag_createsession = Install.getSysRoleNameExist(dbhost, dbPort, dbname, dbUser, dbPassword, "create session");
					
					request.setAttribute("flag_connect", flag_connect?"w":"nw");
					request.setAttribute("flag_resource", flag_resource?"w":"nw");
					request.setAttribute("flag_dba", flag_dba?"w":"nw");
					request.setAttribute("flag_createtable", flag_createtable?"w":"nw");
					request.setAttribute("flag_createsession", flag_createsession?"w":"nw");
			  }
			  
			}catch (Exception e) {
			    request.setAttribute("connstatus","<font color=red>连接失败！</font>");
			    
			    request.setAttribute("stablenum", 0);
			    request.setAttribute("striggernum", 0);
			    request.setAttribute("sprocedurenum", 0);
			    request.setAttribute("spackagenum", 0);
				request.setAttribute("spackagelistnum", 0);
				
				request.setAttribute("flag_connect", "nw");
				request.setAttribute("flag_resource", "nw");
				request.setAttribute("flag_dba", "nw");
				request.setAttribute("flag_createtable", "nw");
				request.setAttribute("flag_createsession", "nw");
				
				setupflag = false;
			}
			
		
			
		//================卡口===============
		try {
			//目标正确个数
			request.setAttribute("ctablenum", "<font color=green>"+CheckConstant.CROSS_TABLE.length+"</font>");
			request.setAttribute("ctriggernum", "<font color=green>"+CheckConstant.CROSS_TRIGGER.length+"</font>");
			request.setAttribute("cprocedurenum", "<font color=green>"+CheckConstant.CROSS_PROCEDURE.length+"</font>");
			request.setAttribute("cpackagenum","<font color=green>"+CheckConstant.CROSS_PACKAGE.length+"</font>");
			request.setAttribute("cpackagelistnum","<font color=green>"+CheckConstant.CROSS_PACKAGEBODY.length+"</font>");
			
			Connection cconn = Install.getConn(cdbhost, dbPort, cdbname, dbUser, dbPassword);
			request.setAttribute("cconnstatus", "<font color=green>连接成功！</font>");
			
			if(cconn!=null){
				List<String> ctableList = Install.getAllObjByType(cdbhost, dbPort, cdbname, dbUser, dbPassword, "TABLE","cross");
				List<String> ctriggerList = Install.getAllObjByType(cdbhost, dbPort, cdbname, dbUser, dbPassword, "TRIGGER","cross");
				List<String> cprocedureList = Install.getAllObjByType(cdbhost, dbPort, cdbname, dbUser, dbPassword, "PROCEDURE","cross");
				List<String> cpackageList = Install.getAllObjByType(cdbhost, dbPort, cdbname, dbUser, dbPassword, "PACKAGE","cross");
				List<String> cpackagebodyList = Install.getAllObjByType(cdbhost, dbPort, cdbname, dbUser, dbPassword, "PACKAGE BODY","cross");
				cconn.close();
				
				//实际个数
				request.setAttribute("sctablenum", ctableList.size());
				request.setAttribute("sctriggernum", ctriggerList.size());
				request.setAttribute("scprocedurenum",cprocedureList.size());
				request.setAttribute("scpackagenum", cpackageList.size());
				request.setAttribute("scpackagelistnum", cpackagebodyList.size());
				
				//个数不符则不通过
				if(ctableList.size()<CheckConstant.CROSS_TABLE.length || ctriggerList.size()<CheckConstant.CROSS_TRIGGER.length || 
						cprocedureList.size()<CheckConstant.CROSS_PROCEDURE.length || cpackageList.size()<CheckConstant.CROSS_PACKAGE.length 
						|| cpackagebodyList.size()< CheckConstant.CROSS_PACKAGEBODY.length){
					setupflag = false;
				}
				
				//验证角色权限
				boolean flag_connect = Install.getRoleNameExist(cdbhost, dbPort, cdbname, dbUser, dbPassword, "connect");
				boolean flag_resource = Install.getRoleNameExist(cdbhost, dbPort, cdbname, dbUser, dbPassword, "resource");
				boolean flag_dba = Install.getRoleNameExist(cdbhost, dbPort, cdbname, dbUser, dbPassword, "dba");
				//验证系统权限
				boolean flag_createtable = Install.getSysRoleNameExist(cdbhost, dbPort, cdbname, dbUser, dbPassword, "create table");
				boolean flag_createsession = Install.getSysRoleNameExist(cdbhost, dbPort, cdbname, dbUser, dbPassword, "create session");
				
				request.setAttribute("cflag_connect", flag_connect?"w":"nw");
				request.setAttribute("cflag_resource", flag_resource?"w":"nw");
				request.setAttribute("cflag_dba", flag_dba?"w":"nw");
				request.setAttribute("cflag_createtable", flag_createtable?"w":"nw");
				request.setAttribute("cflag_createsession", flag_createsession?"w":"nw");
			}
			
			
		} catch (Exception e) {
			request.setAttribute("cconnstatus", "<font color=red>连接失败！</font>");
			
			request.setAttribute("sctablenum", 0);
			request.setAttribute("sctriggernum", 0);
			request.setAttribute("scprocedurenum", 0);
			request.setAttribute("scpackagenum", 0);
			request.setAttribute("scpackagelistnum", 0);
			
			request.setAttribute("cflag_connect", "nw");
			request.setAttribute("cflag_resource", "nw");
			request.setAttribute("cflag_dba", "nw");
			request.setAttribute("cflag_createtable", "nw");
			request.setAttribute("cflag_createsession", "nw");
			
			setupflag = false;
		}
		
		//================GPS===============
		try {
			//目标正确个数
			request.setAttribute("gtablenum", "<font color=green>"+CheckConstant.GPS_TABLE.length+"</font>");
			request.setAttribute("gtriggernum", "<font color=green>"+CheckConstant.GPS_TRIGGER.length+"</font>");
			request.setAttribute("gprocedurenum", "<font color=green>"+CheckConstant.GPS_PROCEDURE.length+"</font>");
			request.setAttribute("gpackagenum","<font color=green>"+CheckConstant.GPS_PACKAGE.length+"</font>");
			request.setAttribute("gpackagelistnum","<font color=green>"+CheckConstant.GPS_PACKAGEBODY.length+"</font>");
			
			Connection gconn = Install.getConn(gdbhost, dbPort, gdbname, dbUser, dbPassword);
			request.setAttribute("gconnstatus", "<font color=green>连接成功！</font>");
			
			if(gconn!=null){
				List<String> gtableList = Install.getAllObjByType(gdbhost, dbPort, gdbname, dbUser, dbPassword, "TABLE","gps");
				List<String> gtriggerList = Install.getAllObjByType(gdbhost, dbPort, gdbname, dbUser, dbPassword, "TRIGGER","gps");
				List<String> gprocedureList = Install.getAllObjByType(gdbhost, dbPort, gdbname, dbUser, dbPassword, "PROCEDURE","gps");
				List<String> gpackageList = Install.getAllObjByType(gdbhost, dbPort, gdbname, dbUser, dbPassword, "PACKAGE","gps");
				List<String> gpackagebodyList = Install.getAllObjByType(gdbhost, dbPort, gdbname, dbUser, dbPassword, "PACKAGE BODY","gps");
				gconn.close();
				
				//实际个数
				request.setAttribute("sgtablenum",gtableList.size());
				request.setAttribute("sgtriggernum", gtriggerList.size());
				request.setAttribute("sgprocedurenum", gprocedureList.size());
				request.setAttribute("sgpackagenum",gpackageList.size());
				request.setAttribute("sgpackagelistnum",gpackagebodyList.size());
				
				//个数不符则不通过
				if(gtableList.size()<CheckConstant.GPS_TABLE.length || gtriggerList.size()<CheckConstant.GPS_TRIGGER.length || 
						gprocedureList.size()<CheckConstant.GPS_PROCEDURE.length || gpackageList.size()<CheckConstant.GPS_PACKAGE.length 
						|| gpackagebodyList.size()< CheckConstant.GPS_PACKAGEBODY.length){
					setupflag = false;
				}
				
				//验证角色权限
				boolean flag_connect = Install.getRoleNameExist(gdbhost, dbPort, gdbname, dbUser, dbPassword, "connect");
				boolean flag_resource = Install.getRoleNameExist(gdbhost, dbPort, gdbname, dbUser, dbPassword, "resource");
				boolean flag_dba = Install.getRoleNameExist(gdbhost, dbPort, gdbname, dbUser, dbPassword, "dba");
				//验证系统权限
				boolean flag_createtable = Install.getSysRoleNameExist(gdbhost, dbPort, gdbname, dbUser, dbPassword, "create table");
				boolean flag_createsession = Install.getSysRoleNameExist(gdbhost, dbPort, gdbname, dbUser, dbPassword, "create session");
				
				request.setAttribute("gflag_connect", flag_connect?"w":"nw");
				request.setAttribute("gflag_resource", flag_resource?"w":"nw");
				request.setAttribute("gflag_dba", flag_dba?"w":"nw");
				request.setAttribute("gflag_createtable", flag_createtable?"w":"nw");
				request.setAttribute("gflag_createsession", flag_createsession?"w":"nw");
			}
			
		} catch (Exception e) {
			request.setAttribute("gconnstatus", "<font color=red>连接失败！</font>");
			
			request.setAttribute("sgtablenum",0);
			request.setAttribute("sgtriggernum", 0);
			request.setAttribute("sgprocedurenum", 0);
			request.setAttribute("sgpackagenum",0);
			request.setAttribute("sgpackagelistnum",0);
			
			request.setAttribute("gflag_connect", "nw");
			request.setAttribute("gflag_resource", "nw");
			request.setAttribute("gflag_dba", "nw");
			request.setAttribute("gflag_createtable", "nw");
			request.setAttribute("gflag_createsession", "nw");
			
			setupflag = false;
		}
		
		
		//检查关键文件可写
		String realpath = request.getSession().getServletContext().getRealPath("/");
		
		File webxml = new File(realpath+"WEB-INF/web.xml");
		request.setAttribute("webxmlwrite", webxml.canWrite()?"w":"nw");
		if(!webxml.canWrite()){
			setupflag = false;
		}
		
		File propertiesxml = new File(realpath+"WEB-INF/classes/application.properties");
		request.setAttribute("propertiesxmlwrite", propertiesxml.canWrite()?"w":"nw");
		if(!propertiesxml.canWrite()){
			setupflag = false;
		}
		
		File mongodbxml = new File(realpath+"WEB-INF/classes/mongodb.properties");
		request.setAttribute("mongodbxmlwrite", mongodbxml.canWrite()?"w":"nw");
		if(!mongodbxml.canWrite()){
			setupflag = false;
		}
		
		File cachefile = new File(realpath+"WEB-INF/cache/");
		request.setAttribute("cachefilewrite", cachefile.canWrite()?"w":"nw");
		if(!cachefile.canWrite()){
			setupflag = false;
		}
		
		request.setAttribute("setupflag", setupflag?"":"disabled");

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/install/checking.jsp");
		dispatcher.forward(request, response);
	}
}
