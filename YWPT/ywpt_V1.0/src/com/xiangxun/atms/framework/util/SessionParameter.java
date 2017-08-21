package com.xiangxun.atms.framework.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/*******************************************************************************
 * author:岩涛 reviseTime:2014-06-09
 * -------------------------------------------------------------------------------
 * 本类的作用是： 解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
 ******************************************************************************/

public class SessionParameter {

	
	@SuppressWarnings("unchecked")
	public void updateSessionMap(HttpServletRequest request,String menuid,Map<String, Object> searchParams){
		//-------------解决返回后 搜索条件消失问题-----start-----------
		String isgetsession = request.getParameter("isgetsession");
		Map<String, Object> sessionParams = null;
		if("1".equals(isgetsession)){
			Map<String,Map<String, Object>> sessionMap = (Map<String,Map<String, Object>>)request.getSession().getAttribute("sessionMap");
			sessionParams = sessionMap.get(menuid);
			if (!"".equals(sessionParams.get("devCode")) && null != sessionParams.get("devCode")) {
				String devCode = sessionParams.get("devCode").toString();
				sessionParams.put("devCode", org.apache.commons.lang.StringUtils.removeEnd(devCode, ","));
			}
		}
		//-------------构建一个2纬MAP 放入SESSION中
		if(sessionParams!=null){
			searchParams.putAll(sessionParams);
		}else{
			Map<String,Map<String, Object>> sessionMap = new HashMap();
			sessionMap.put(menuid, searchParams);
			request.getSession().setAttribute("sessionMap", sessionMap);
		}
		//-------------解决返回后 搜索条件消失问题-----end-----------
	}
}
