package com.xiangxun.atms.module.geoServer.web;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.common.system.service.ResourceService;
import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.common.user.type.MenuType;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;

@Controller
@RequestMapping(value = "openmap")
public class GeoServerResourceCtl extends BaseCtl {
	@Resource
	ResourceService resourceService;

	// 开源gis页面跳转
	@RequestMapping(value = "home/{menuid}")
	public String openMapUrl(@PathVariable String menuid, ModelMap model) {
		// OperatorDetails userInfo = SpringSecurityUtils.getCurrentUser();
		// List<SystemResource> list =
		// resourceService.getChildMenusByUserIdAndParentId(userInfo.getId(),
		// menuid);
		// 保持和esrimap同样的处理方法，获取所有的menu
		model.addAttribute("menuid", menuid);
		model.addAttribute("menulist", createTree(menuid));
		return "openmap/home";
	}

	// 辅助jsp跳转
	@RequestMapping(value = "address/{url}")
	public String addressUrl(@PathVariable String url) {
		return "openmap/" + url.replace('.', '/');
	}

	// 路径分析模拟url请求
	@RequestMapping(value = "naServer")
	@ResponseBody
	public String naServer(String reqUrl,String stopPoint,String methodname,String barriers,String roadnet, HttpServletRequest request, HttpServletResponse response) {
		String loginback="";
		if(null == roadnet || "".equals(roadnet))roadnet = "roadnet";
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(reqUrl);
		NameValuePair param = new NameValuePair("request", methodname);
		NameValuePair param2 = new NameValuePair("service", "na");
		NameValuePair param3 = new NameValuePair("version", "1.0.0");
		NameValuePair param4 = new NameValuePair("graph", roadnet);
		NameValuePair param5 = new NameValuePair("stops", stopPoint);
		String point="";
		NameValuePair param6=null;
		if("".equals(barriers)){
	      param6 = new NameValuePair("barriers", point);
		}  else{
			
			param6 = new NameValuePair("barriers", barriers);
		}
		NameValuePair[] params = new NameValuePair[6];
		params[0] = param;
		params[1] = param2;
		params[2] = param3;
		params[3] = param4;
		params[4] = param5;
		params[5] = param6;
		method.setRequestBody(params);
		try {
			client.executeMethod(method);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	
		try {
			loginback = method.getResponseBodyAsString();
			System.out.println(loginback);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(200 == method.getStatusCode()) {
			// 请求登录成功
			System.out.println("请求成功！");
		}
		// 释放连接
		method.releaseConnection();
		return loginback;

	}

	// 路径分析模拟url请求
	@RequestMapping(value = "gpServer")
	@ResponseBody
	public String gpServer(String reqUrl,String geoms,String methodname,String distance, HttpServletRequest request, HttpServletResponse response) {
		String loginback="";
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(reqUrl);
		NameValuePair param = new NameValuePair("request", methodname);
		NameValuePair param2 = new NameValuePair("service", "gp");
		NameValuePair param3 = new NameValuePair("version", "1.0.0");
		NameValuePair param4 = new NameValuePair("geoms",geoms);
		NameValuePair param5 = new NameValuePair("distance",distance);
		NameValuePair[] params = new NameValuePair[5];
		params[0] = param;
		params[1] = param2;
		params[2] = param3;
		params[3] = param4;
		params[4] = param5;
		method.setRequestBody(params);
		try {
			client.executeMethod(method);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	
		try {
			loginback = method.getResponseBodyAsString();
			System.out.println(loginback);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(200 == method.getStatusCode()) {
			// 请求登录成功
			System.out.println("请求成功！");
		}
		// 释放连接
		method.releaseConnection();
		return loginback;

	}

	private String createTree(String menuid) {
		OperatorDetails userInfo = SpringSecurityUtils.getCurrentUser();
		if (userInfo == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		JsonArray gattay = new JsonArray();
		// 获取第一级节点:
		SystemResource sys = resourceService.getById(menuid);

		// 给二级菜单添加level(菜单级别)和childCount(孩子节点个数)属性 by LHF
		List<SystemResource> resList = resourceService.getChildMenusByUserId(
				userInfo.getId(), sys.getId(), MenuType.MODULE);
		JsonObject object;
		for (SystemResource res : resList) {
			JsonArray temp = new JsonArray();// 获取节点的临时数组 LHF
			// 构建一个json对象
			object = new JsonObject();
			object.addProperty("id", res.getId());
			object.addProperty("pid", res.getParentid());
			object.addProperty("name", res.getName());
			object.addProperty("rest", res.getContent());

			getTreeNode(userInfo.getId(), res.getId(), temp);
			// 判断是否有子节点，若有则在二级节点中标明孩子节点的个数；
			if (temp.size() > 0) {
				object.addProperty("level", "2");// 菜单级别
				object.addProperty("childCount", temp.size());// 孩子节点个数
				gattay.add(object);
				gattay.addAll(temp);
			} else {
				gattay.add(object);
			}
		}
		return gattay.toString();
	}

	// 递归得到所有的子节点操作：
	private JsonArray getTreeNode(String userId, String nodeId, JsonArray gattay) {
		JsonObject object = null;
		// 第二级叶子节点
		List<SystemResource> resList = resourceService.getChildMenusByUserId(
				userId, nodeId, MenuType.MODULE);
		for (SystemResource res : resList) {
			// 构建一个json对象
			object = new JsonObject();
			object.addProperty("id", res.getId());
			object.addProperty("pid", res.getParentid());
			object.addProperty("name", res.getName());
			object.addProperty("rest", res.getContent());
			gattay.add(object);
			getTreeNode(userId, res.getId(), gattay);
		}
		return gattay;
	}
	
	
	

}
