package com.xiangxun.epms.mobile.business.web;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiangxun.epms.mobile.business.cache.DicCache;
import com.xiangxun.epms.mobile.business.domain.MobileDevice;
import com.xiangxun.epms.mobile.business.domain.SessionData;
import com.xiangxun.epms.mobile.business.domain.User;
import com.xiangxun.epms.mobile.business.service.MobileDeviceService;
import com.xiangxun.epms.mobile.business.service.UserService;
import com.xiangxun.epms.mobile.business.util.Session;
import com.xiangxun.epms.mobile.util.DeEncryptUtil;

@Controller
@RequestMapping(value = "/samply" )
public class MainCtl extends BaseCtl {

	@Resource
	UserService userService;
	@Resource
	MobileDeviceService mobileDeviceService;
	/**
	 * 登录
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST )
    public void login(User info,HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String account = request.getParameter("account");
		//此时密码为密文
		String pwd = request.getParameter("pwd");
		//设备IMEI
		String imei = request.getParameter("imei");
		if (StringUtils.isEmpty(pwd)) {
			super.simpleResult("1001", "缺失登录参数", request, response);
			return;
		}
	
	  if(StringUtils.isEmpty(imei)){
	    	super.simpleResult("1001", "缺失设备编号", request, response);
	    	return;
		}
	    MobileDevice mobileDevice=mobileDeviceService.findByImei(imei);
	    if(mobileDevice==null){
	    	super.simpleResult("1001", "登录失败，设备未授权。", request, response);
			return;
	    }
		User user = userService.getUserByAccount(account);
		if (user == null) {
			super.simpleResult("1001", "登录失败，账户名错误。", request, response);
			return;
		}
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String ss=this.decryptPwd(pwd);
		pwd = md5.encodePassword(ss, user.getName());
		if (!pwd.equals(user.getPwd())) {
			super.simpleResult("1001", "登录失败，账户密码错误。", request, response);
			return;
		}
		SessionData sd = new SessionData();
		sd.setId(user.getId());
		sd.setAccount(user.getAccount());
		sd.setName(user.getName());
		sd.setDeptId(user.getDeptId());
		sd.setDeptName(user.getDeptName());
		sd.setMobile(user.getMobile());
		super.dataResult("1000", "登录成功。", sd, request, response);
		//保存session
		sd.setImei(imei);
		sd.setLoginDate(new Date());
		logger.info(request.getContextPath());
		Session.SESSION_MAP.put(Session.makeSessionKey(request), sd);
		logger.info("user logging  success.");
    }
	
	/**
	 * 密码解密
	 * @param pwd 原始密码
	 * @return
	 */
	public  String decryptPwd(String pwd) {
		try {
			DeEncryptUtil t = new DeEncryptUtil();
			return t.decrypt(pwd);
		} catch (Exception e) {
			logger.error("密码解密失败。"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 登出
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
		Session.SESSION_MAP.remove(Session.makeSessionKey(request));
		super.simpleResult("1000", "注销成功。", request, response);
    }
	
	/**
	 * 查询数据字典
	 * @param dicTypes
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "server/dic/query")
	public void query(String dicTypes, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isEmpty(dicTypes)) {
			super.simpleResult("1001", "缺失参数。", request, response);
			return;
		}
		Map<String, Object> args = new HashMap<>();
		String[] typeArray = dicTypes.split(",");
		for (String type : typeArray) {
			args.put(type, DicCache.DIC_MAP.get(type));
		}
		super.dataResult("1000", "查询成功。", args, request, response);
	}
	
	/**
	 * 修改密码
	 * @param oldP
	 * @param newP
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "server/account/updateP", method = RequestMethod.POST)
	public void query(String oldP, String newP,HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isEmpty(oldP) || StringUtils.isEmpty(newP)) {
			super.simpleResult("1001", "缺少参数", request, response);
			return;
		}
		User user = userService.getUserByAccount(super.getLoginData(request).getAccount());
		if (user == null) {
			super.simpleResult("1001", "查询用户信息失败", request, response);
			return;
		}
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String pwd = md5.encodePassword(this.decryptPwd(oldP), user.getName());
		if (!pwd.equals(user.getPwd())) {
			super.simpleResult("1001", "旧密码错误", request, response);
			return;
		}
		String newPwd = md5.encodePassword(this.decryptPwd(newP), user.getName());
		userService.updatePwdByAccount(newPwd, user.getAccount());
		SessionData sd = new SessionData();
		sd.setId(user.getId());
		sd.setAccount(user.getAccount());
		sd.setName(user.getName());
		sd.setDeptId(user.getDeptId());
		sd.setDeptName(user.getDeptName());
		sd.setMobile(user.getMobile());
		super.dataResult("1000", "修改成功",sd, request, response);
		logger.info("user update pwd  success.");
	}

	
}
