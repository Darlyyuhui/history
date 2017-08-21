package com.xiangxun.atms.common.user.service.impl;


/***
 * 定义用户权限的SQL语句
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public interface UserAuthoritySQL{
	
	 //定义只能查看用户所在部门及子部门的sql，目前只检查到了部门的三级子部门
	public String DEPT_SQL ="SELECT deptid FROM SYS_USER  WHERE ID = {0}"+
					" UNION "+     
					" SELECT id FROM SYS_ORG org WHERE ORG.PARENTID in(SELECT deptid FROM SYS_USER  WHERE ID = {1})"+
					" UNION "+
					" SELECT id FROM SYS_ORG o WHERE O.PARENTID in ( select id from SYS_ORG org where ORG.PARENTID in(SELECT DEPTID FROM SYS_USER  WHERE ID = {2}))"+
					" UNION "+
					" SELECT id FROM SYS_ORG WHERE PARENTID in(select id from SYS_ORG o where O.PARENTID in(select id from SYS_ORG org where ORG.PARENTID in(SELECT DEPTID FROM SYS_USER  WHERE ID = {3})))";
	
	//带部门权限的子查询sql	
	public String PERMISSION_AUTHORITY_SQL =" SELECT DEPTID  FROM SYS_USER_DATA_AUTHORITY   WHERE USERID = {0} AND MENUID = {1}";
	
	public String BASE_AUTHORITY_SQL = " ORG_ID IN ({0})";
	
	//业务功能权限sql，根据条件将devicode分成3段，避免sql语句过程问题
	public String DEVICE_AUTHORITY_SQL =" CODE IN({0}) or CODE IN({1}) or CODE IN({2})";
	
	public String BASE_DEVICE_AUTHORITY_SQL =" CODE IN({0})";
	
	public String ORG_AUTHORITY_SQL =" ORG_ID IN({0}) or ORG_ID IN({1}) or ORG_ID IN({2})";

}
 
