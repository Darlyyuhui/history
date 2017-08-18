package com.xiangxun.ywpt.mobile.business.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiangxun.ywpt.mobile.business.cache.DicCache;
import com.xiangxun.ywpt.mobile.business.domain.SessionData;
import com.xiangxun.ywpt.mobile.business.domain.User;
import com.xiangxun.ywpt.mobile.business.service.UserService;
import com.xiangxun.ywpt.mobile.business.util.Session;

@Controller
public class LoginCtl extends BaseCtl {

	@Resource
	UserService userService;

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(@RequestBody Map<String, String> userDetails,
			HttpServletRequest request, HttpServletResponse response) {

		String account = userDetails.get("loginName");
		String pwd = userDetails.get("password");
		// 获取app端传过来的keyValue
		//String keyValue = userDetails.get("keyValue");
		//String account = request.getParameter("loginName");

		// 此时密码为密文
		//String pwd = request.getParameter("password");
		if (StringUtils.isEmpty(pwd)) {
			super.simpleResult("0", "缺失登录参数", request, response);
			return;
		}
		User user = userService.getUserByAccount(account);
		if (user == null) {
			super.simpleResult("0", "登录失败，账户名错误。", request, response);
			return;
		}

		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		pwd = md5.encodePassword(/*this.decryptPwd(pwd)*/pwd, user.getName());

		if (! (pwd.trim()).equals(user.getPwd().trim())) {
			super.simpleResult("0", "登录失败，账户密码错误。", request, response);
			return;
		}
		
		//存入session的数据集
		SessionData sd = new SessionData();
		sd.setId(user.getId());
		sd.setAccount(user.getAccount());
		sd.setName(user.getName());
		sd.setOrgId(user.getOrgId());
		sd.setOrgName(user.getOrgName());
		sd.setIpRule(user.getIpRule());
		sd.setDisabled(user.getDisabled());
		sd.setMobile(user.getMobile());
		sd.setMemo(user.getMemo());
		sd.setLimitVisitTimes(user.getLimitVisitTimes());
		sd.setSetVisitTimes(user.getSetVisitTimes());

		 super.dataResult("1", "登录成功。", sd, request, response);
		// 保存session
		sd.setLoginDate(new Date());
		Session.SESSION_MAP.put(Session.makeSessionKey(request), sd);
	}


	/**
	 * 登出
	 * 
	 * @param request
	 * @param response
	 */
	/*
	 * @RequestMapping(value = "/logout") public void logout(HttpServletRequest
	 * request, HttpServletResponse response) {
	 * Session.SESSION_MAP.remove(Session.makeSessionKey(request));
	 * super.simpleResult("1000", "注销成功。", request, response); }
	 */

	/**
	 * 查询数据字典
	 * 
	 * @param dicTypes
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "server/dic/query")
	public void query(String dicTypes, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isEmpty(dicTypes)) {
			// super.simpleResult("1001", "缺失参数。", request, response);
			return;
		}
		Map<String, Object> args = new HashMap<>();
		String[] typeArray = dicTypes.split(",");
		for (String type : typeArray) {
			args.put(type, DicCache.DIC_MAP.get(type));
		}
		// super.dataResult("1000", "查询成功。", args, request, response);
	}

	/**
	 * 修改密码
	 * 
	 * @param oldP
	 * @param newP
	 * @param request
	 * @param response
	 */
	/*
	 * @RequestMapping(value = "server/account/updateP", method =
	 * RequestMethod.POST) public void query(String oldP, String
	 * newP,HttpServletRequest request, HttpServletResponse response) { if
	 * (StringUtils.isEmpty(oldP) || StringUtils.isEmpty(newP)) {
	 * super.simpleResult("1001", "缺少参数", request, response); return; } User
	 * user =
	 * userService.getUserByAccount(super.getLoginData(request).getAccount());
	 * if (user == null) { super.simpleResult("1001", "查询用户信息失败", request,
	 * response); return; } Md5PasswordEncoder md5 = new Md5PasswordEncoder();
	 * String pwd = md5.encodePassword(this.decryptPwd(oldP), user.getName());
	 * if (!pwd.equals(user.getPwd())) { super.simpleResult("1001", "旧密码错误",
	 * request, response); return; } String newPwd =
	 * md5.encodePassword(this.decryptPwd(newP), user.getName());
	 * userService.updatePwdByAccount(newPwd, user.getAccount());
	 * super.simpleResult("1000", "修改成功", request, response); }
	 */

}
