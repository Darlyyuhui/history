package com.xiangxun.atms.common.install;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;


/**
 * 安装类
 * 
 */
public class Install {
	
	public static void dbXml(String dbXmlPath,String dbport,String dbhost,String dbname,
			String cdbhost,String cdbname,String gdbhost,String gdbname,
			String dbuser,String dbpassword,String postgisip,String postgisport,String postgisname) throws Exception {
		
		String s = FileUtils.readFileToString(new File(dbXmlPath));
		
		s = StringUtils.replace(s, "POSTGIS_IP", postgisip);
		s = StringUtils.replace(s, "POSTGIS_PORT", postgisport);
		s = StringUtils.replace(s, "POSTGIS_NAME", postgisname);
		
		s = StringUtils.replace(s, "DB_HOST", dbhost);
		s = StringUtils.replace(s, "DB_CHOST", cdbhost);
		s = StringUtils.replace(s, "DB_GHOST", gdbhost);
		
		s = StringUtils.replace(s, "DB_PORT", dbport);
		
		s = StringUtils.replace(s, "DB_NAME", dbname);
		s = StringUtils.replace(s, "DB_CNAME", cdbname);
		s = StringUtils.replace(s, "DB_GNAME", gdbname);
		
		s = StringUtils.replace(s, "DB_USER", dbuser);
		s = StringUtils.replace(s, "DB_PASSWORD", dbpassword);
		
		FileUtils.writeStringToFile(new File(dbXmlPath), s);
	}

	public static Connection getConn(String dbHost, String dbPort,
			String dbName, String dbUser, String dbPassword) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		String connStr = "jdbc:oracle:thin:@" + dbHost + ":" + dbPort + ":" + dbName;
		Connection conn = DriverManager.getConnection(connStr,dbUser,dbPassword);
		return conn;
	}

	public static void webXml(String fromFile, String toFile) throws Exception {
		FileUtils.copyFile(new File(fromFile), new File(toFile));
	}

	/**
	 * 创建数据库
	 * 
	 * @param dbHost
	 * @param dbName
	 * @param dbPort
	 * @param dbUser
	 * @param dbPassword
	 * @throws Exception
	 */
	public static void createDb(String dbHost, String dbPort, String dbName,
			String dbUser, String dbPassword) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String connStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "?user="
				+ dbUser + "&password=" + dbPassword
				+ "&characterEncoding=UTF8";
		Connection conn = DriverManager.getConnection(connStr);
		Statement stat = conn.createStatement();
		String sql = "drop database if exists " + dbName;
		stat.execute(sql);
		sql = "create database " + dbName + " CHARACTER SET UTF8";
		stat.execute(sql);
		stat.close();
		conn.close();
	}

	public static void changeDbCharset(String dbHost, String dbPort,
			String dbName, String dbUser, String dbPassword) throws Exception {
		Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
		Statement stat = conn.createStatement();
		String sql = "ALTER DATABASE " + dbName + " CHARACTER SET UTF8";
		stat.execute(sql);
		stat.close();
		conn.close();
	}

	/**
	 * 创建表
	 * 
	 * @param dbHost
	 * @param dbName
	 * @param dbPort
	 * @param dbUser
	 * @param dbPassword
	 * @param sqlList
	 * @throws Exception
	 */
	public static void createTable(String dbHost, String dbPort, String dbName,
			String dbUser, String dbPassword, List<String> sqlList)
			throws Exception {
		Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
		Statement stat = conn.createStatement();
		for (String dllsql : sqlList) {
			System.out.println(dllsql);
			stat.execute(dllsql);
		}
		stat.close();
		conn.close();
	}

	/**
	 * 更新配置
	 * 
	 * @param dbHost
	 * @param dbName
	 * @param dbPort
	 * @param dbUser
	 * @param dbPassword
	 * @param domain
	 * @param cxtPath
	 * @param port
	 * @throws Exception
	 */
	public static void updateConfig(String dbHost, String dbPort,
			String dbName, String dbUser, String dbPassword, String domain,
			String cxtPath, String port) throws Exception {
		Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
		Statement stat = conn.createStatement();
		String sql = "update jc_site set domain='" + domain + "'";
		stat.executeUpdate(sql);
		sql = "update jc_config set context_path='" + cxtPath + "',port="
				+ port;
		stat.executeUpdate(sql);
		stat.close();
		conn.close();
	}

	/**
	 * 读取sql语句。“/*”开头为注释，“;”为sql结束。
	 * 
	 * @param fileName
	 *            sql文件地址
	 * @return list of sql
	 * @throws Exception
	 */
	public static List<String> readSql(String fileName) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
		List<String> sqlList = new ArrayList<String>();
		StringBuilder sqlSb = new StringBuilder();
		String s = "";
		while ((s = br.readLine()) != null) {
			if (s.startsWith("/*") || s.startsWith("#")
					|| StringUtils.isBlank(s)) {
				continue;
			}
			if (s.endsWith(";")) {
				sqlSb.append(s);
				sqlSb.setLength(sqlSb.length() - 1);
				sqlList.add(sqlSb.toString());
				sqlSb.setLength(0);
			} else {
				sqlSb.append(s);
			}
		}
		br.close();
		return sqlList;
	}
	
	public static List<String> getAllObjByType(String dbHost, String dbPort,String dbName, String dbUser, String dbPassword,String typename,String targname) throws Exception {
		List<String> list= new ArrayList<String>();	
		Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
		Statement stat = conn.createStatement();
		StringBuffer sqlbuff = new StringBuffer("Select object_name From user_objects ");
		sqlbuff.append(" Where object_type='"+typename.toUpperCase()+"' ");
		if("cross".equals(targname)){
			sqlbuff.append(" and object_name not like 'CROSS_RECORDLIST_20%'");
		}
		if("gps".equals(targname)){
			sqlbuff.append(" and object_name not like 'GPS_RECORD_20%' and object_name not like 'T_ANALYSIS_20%'");
		}
		ResultSet rs = stat.executeQuery(sqlbuff.toString());
		while(rs.next()){
			list.add(rs.getString(1));
		}
		if(rs!=null){
			rs.close();
		}
		if(stat!=null){
			stat.close();
		}
		if(conn!=null){
			conn.close();
		}
		return list;
	}
	
	
	
	public static boolean getRoleNameExist(String dbHost, String dbPort,String dbName, String dbUser, String dbPassword,String rolename) throws Exception {
		Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
		Statement stat = conn.createStatement();
		StringBuffer sqlbuff = new StringBuffer("select GRANTED_ROLE from DBA_ROLE_PRIVS WHERE GRANTEE = '"+dbUser.toUpperCase()+"' ");
		sqlbuff.append(" and GRANTED_ROLE = '"+rolename.toUpperCase()+"' ");
		ResultSet rs = stat.executeQuery(sqlbuff.toString());
		String rolenameRs = "";
		while(rs.next()){
			rolenameRs = rs.getString(1);
		}
		if(rs!=null){
			rs.close();
		}
		if(stat!=null){
			stat.close();
		}
		if(conn!=null){
			conn.close();
		}
		
		if(rolenameRs.equals(rolename.toUpperCase())){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean getSysRoleNameExist(String dbHost, String dbPort,String dbName, String dbUser, String dbPassword,String rolename) throws Exception {
		Connection conn = getConn(dbHost, dbPort, dbName, dbUser, dbPassword);
		Statement stat = conn.createStatement();
		StringBuffer sqlbuff = new StringBuffer("select PRIVILEGE from DBA_SYS_PRIVS  WHERE GRANTEE = '"+dbUser.toUpperCase()+"' ");
		sqlbuff.append(" AND PRIVILEGE = '"+rolename.toUpperCase()+"' ");
		ResultSet rs = stat.executeQuery(sqlbuff.toString());
		String rolenameRs = "";
		while(rs.next()){
			rolenameRs = rs.getString(1);
		}
		if(rs!=null){
			rs.close();
		}
		if(stat!=null){
			stat.close();
		}
		if(conn!=null){
			conn.close();
		}
		
		if(rolenameRs.equals(rolename.toUpperCase())){
			return true;
		}else{
			return false;
		}
	}

}
