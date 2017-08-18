package com.xiangxun.epms.mobile.business.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.xiangxun.epms.mobile.business.domain.SessionData;
import com.xiangxun.epms.mobile.business.util.DiscernCode;
import com.xiangxun.epms.mobile.business.util.Page;
import com.xiangxun.epms.mobile.business.util.Session;
import com.xiangxun.epms.mobile.config.parameter.CustomParameters;
import com.xiangxun.epms.mobile.util.DateUtils;
import com.xiangxun.epms.mobile.util.ResponseUtil;

public class BaseCtl {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
    CustomParameters customSetting;
	
	Map<String, Object> resultMap;
	
	/**
	 * 默认页码
	 */
	int pageNo = 1;
	
	/**
	 * 默认页数
	 */
	int pageSize = 20;
	
	/**
	 * 初始化返回结果集
	 */
	protected void initResultMap() {
		resultMap = new HashMap<>();
	}
	
	/**
	 * 获取页面和页码参数
	 * @param request
	 */
	protected void pageParams(HttpServletRequest request) {
		String size = request.getParameter("pageSize");
		try {
			if (!StringUtils.isEmpty(size)) {
				pageSize = Integer.parseInt(size);
				if (pageSize > 100) {
					pageSize = 100;
				}
			}
		}catch(NumberFormatException e) {}
		String num = request.getParameter("pageNo");
		try {
			if (!StringUtils.isEmpty(num)) {
				pageNo = Integer.parseInt(num);
			}
		}catch(NumberFormatException e) {}
	}
	
	/**
	 * 获取查询参数
	 * @param queryKey
	 * @param request
	 * @return
	 */
	protected Map<String, Object> getQueryParams(HttpServletRequest request, String... queryKeys) {
		Map<String, Object> args = new HashMap<>();
		if (queryKeys != null) {
			String str = null;
			for (String key : queryKeys) {
				str = request.getParameter(key);
				if (!StringUtils.isEmpty(str)) {
					args.put(key, str);
				}
			}
		}
		return args;
	}
	
	/**
	 * 简单格式返回
	 * @param code
	 * @param desc
	 * @param request
	 * @param response
	 */
	protected void simpleResult(String code, String desc, HttpServletRequest request, HttpServletResponse response) {
		this.initResultMap();
		resultMap.put(ResponseUtil.RES_KEY_CODE, code);
		resultMap.put(ResponseUtil.RES_KEY_DESC, desc);
		ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
	}
	
	/**
	 * 数据返回
	 * @param code
	 * @param desc
	 * @param data
	 * @param request
	 * @param response
	 */
	protected void dataResult(String code, String desc, Object data, HttpServletRequest request, HttpServletResponse response) {
		this.initResultMap();
		resultMap.put(ResponseUtil.RES_KEY_CODE, code);
		resultMap.put(ResponseUtil.RES_KEY_DESC, desc);
		resultMap.put(ResponseUtil.RES_KEY_RESULT, data);
		ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
	}
	/**
	 * 数据返回带时间
	 * @param code
	 * @param desc
	 * @param data
	 * @param request
	 * @param response
	 */
	protected void TimeResult(String code, String desc, Object data, HttpServletRequest request, HttpServletResponse response) {
		this.initResultMap();
		Date date = new Date();
		String srtTime = DateUtils.dateToString(date);
		resultMap.put(ResponseUtil.RES_KEY_CODE, code);
		resultMap.put(ResponseUtil.RES_KEY_TIME, srtTime);
		resultMap.put(ResponseUtil.RES_KEY_DESC, desc);
		resultMap.put(ResponseUtil.RES_KEY_RESULT, data);
		ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
	}
	/**
	 * 数据返回带Id
	 * @param code
	 * @param desc
	 * @param data
	 * @param request
	 * @param response
	 */
	protected void idResult(String code, String desc, String id,Object data, HttpServletRequest request, HttpServletResponse response) {
		this.initResultMap();
		resultMap.put(ResponseUtil.RES_KEY_CODE, code);
		resultMap.put(ResponseUtil.RES_KEY_ID, id);
		resultMap.put(ResponseUtil.RES_KEY_DESC, desc);
		resultMap.put(ResponseUtil.RES_KEY_RESULT, data);
		ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
	}
	/**
	 * 分页数据返回
	 * @param code
	 * @param desc
	 * @param data
	 * @param request
	 * @param response
	 */
	protected void pageResult(String code, String desc,String id, Page page, HttpServletRequest request, HttpServletResponse response) {
		this.initResultMap();
		resultMap.put(ResponseUtil.RES_KEY_CODE, code);
		resultMap.put(ResponseUtil.RES_KEY_DESC, desc);
		resultMap.put(ResponseUtil.RES_REGION_ID, id);
		resultMap.put(ResponseUtil.RES_KEY_RESULT, page.getResult());
		resultMap.put(ResponseUtil.RES_KEY_PAGESIZE, page.getPageSize());
		resultMap.put(ResponseUtil.RES_KEY_PAGE, page.getCurrentPageNo());
		resultMap.put(ResponseUtil.RES_KEY_TOTALSIZE, page.getTotalSize());
		resultMap.put(ResponseUtil.RES_KEY_TOTALPAGE, page.getTotalPageCount());
		ResponseUtil.printWriteResponse(request.getParameter("callback"), resultMap, response);
	}
	
	/**
	 * 生成编号
	 * @param topStr
	 * @return
	 */
	protected String makeCode(String topStr) {
		return topStr + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
	
	/**
	 * 获取当前登录人信息
	 * @param request
	 * @return
	 */
	protected SessionData getLoginData(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		Object user = session.getAttribute(Session.SESSION_KEY);
		Object user = Session.SESSION_MAP.get(Session.makeSessionKey(request));
		
		return (SessionData)user;
	}
	/**
	 * 获取登录人信息
	 * @param account
	 * @param imei
	 * @return
	 */
	protected SessionData getLoginUser(String account,String imei){
		Object user = Session.SESSION_MAP.get(Session.makeSessionKey(account, imei));
		
		return (SessionData)user;
	}
	/**
	 * 获取当前登录人ID
	 * @param request
	 * @return
	 */
	protected String getLoginId(HttpServletRequest request) {
		SessionData sd = this.getLoginData(request);
		return sd.getId();
	}
	
	/**
	 * 校验修改数据时的基本数据
	 * @param obj
	 * @param request
	 * @param response
	 * @return
	 */
	protected boolean validateUpdate(Object obj, HttpServletRequest request, HttpServletResponse response) {
		if (obj == null) {
			this.simpleResult("1001", "未能成功构建实体对象", request, response);
			return false;
		}
		String id = null;
		//使用ReflectASM类库，提高反射性能
		MethodAccess ma = MethodAccess.get(obj.getClass());
		try {
			Object idObj = ma.invoke(obj, "getId");
			if (idObj != null) {
				id = idObj.toString();
			}
		}catch(Exception e) {
			logger.error("获取"+obj.getClass().getName()+"类中的getId()方法异常。" + e.getMessage());
		}
		if (StringUtils.isEmpty(id)) {
			this.simpleResult("1001", "实体对象缺失ID参数", request, response);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验新增数据时的基本数据
	 * @param obj
	 * @param request
	 * @param response
	 * @return
	 */
	protected boolean validateAdd(Object obj, HttpServletRequest request, HttpServletResponse response) {
		if (obj == null) {
			this.simpleResult("1001", "未能成功构建实体对象", request, response);
			return false;
		}
		return true;
	}
	/**
	 * 判断点位是否做过增加修改操作
	 * @return
	 */
	protected boolean validateOperatRecord(String record,HttpServletRequest request, HttpServletResponse response){
		if(record==null){
			this.simpleResult("1001", "没有操作识别码",  request,  response);
			return false;
		}
		if("".equals(record)){
			this.simpleResult("1001", "青传入有效的操作识别码",  request,  response);
			return false;
		}
		if(DiscernCode.DISCERN_MAP!=null){
			if(record.equals(DiscernCode.DISCERN_MAP.get(DiscernCode.DISCERN_KEY))){
			  return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
