package com.xiangxun.atms.module.geoServer.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.xiangxun.atms.framework.util.HttpClientUtil;

@Controller
@RequestMapping(value = "openmap")
public class GeoServerResourceCtl extends BaseCtl {
	@Resource
	ResourceService resourceService;

	// 开源gis页面跳转
	@RequestMapping(value = "home/{menuid}")
	public String openMapUrl(@PathVariable String menuid, ModelMap model) {
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

        Map<String, Object> args = new HashMap<String, Object>();
        args.put("request", methodname);
        args.put("service", "na");
        args.put("version", "1.0.0");
        args.put("graph", roadnet);
        args.put("stops", stopPoint);
        if("".equals(barriers)){
            args.put("barriers", "");
        }  else{
            args.put("barriers", barriers);
        }

        try {
            loginback = HttpClientUtil.httpPost(reqUrl,args);
        } catch (Exception e) {
            e.printStackTrace();
        }

		return loginback;
	}

	// 路径分析模拟url请求
	@RequestMapping(value = "gpServer")
	@ResponseBody
	public String gpServer(String reqUrl,String geoms,String methodname,String distance, HttpServletRequest request, HttpServletResponse response) {
		String loginback="";

        Map<String, Object> args = new HashMap<String, Object>();
        args.put("request", methodname);
        args.put("service", "gp");
        args.put("version", "1.0.0");
        args.put("geoms",geoms);
        args.put("distance",distance);

        try {
            loginback = HttpClientUtil.httpPost(reqUrl,args);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
