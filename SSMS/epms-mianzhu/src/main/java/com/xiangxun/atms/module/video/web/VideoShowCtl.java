package com.xiangxun.atms.module.video.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.util.HttpClientUtil;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.bs.service.VideoDeviceService;
import com.xiangxun.atms.module.video.util.VideoConfig;

/**
 * 视频展示
 * @author HaoXiang
 * 2016年12月13日
 */
@Controller
@RequestMapping(value = "video/show")
public class VideoShowCtl extends BaseCtl {

	@Resource
	VideoDeviceService videoDeviceService;
	
	/**
	 * 视频配置session key
	 */
	public static final String VIDEO_CONFIG_SESSION = "videoConfigSession";
	
	/**
	 * 地图上展示视频
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "map/{id}/{ip}/")
	public String map(@PathVariable("id") String id, @PathVariable("ip") String ip, Model model, HttpServletRequest request) {
		//读取配置
		this.readConfigToSession(request);
		String str = "";
		try {
			str = HttpClientUtil.httpGet("http://"+ip+"/previewAction!getAllCameraDeviceInfoTree.action", null);
		}catch(Exception e) { }
		//海康平台树形的树节点ID
		model.addAttribute("videoId", id);
		model.addAttribute("treeData", str);
		model.addAttribute("ip", ip);
		return "videoshow/mapshow";
	}
	
	/**
	 * 处理跨域请求
	 * @param url	请求url
	 * @param type	请求方式，默认get
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getData/")
	public ResponseEntity getDataByUrl(@RequestParam(value = "url", required = true) String url
			, @RequestParam(value = "type", defaultValue = "get") String type) {
		ResponseEntity entity = new ResponseEntity();
		String str = "";
		try {
			if ("get".equals(type)) {
				str = HttpClientUtil.httpGet(url, null);
			} else if ("post".equals(type)) {
				String data = url.substring(url.indexOf("?")+1, url.length());
				url = url.substring(0, url.indexOf("?"));
				Map<String, Object> args = new HashMap<String, Object>();
				String[] ds = data.split("&");
				String[] temp;
				for (String d : ds) {
					temp = d.split("=");
					if (temp.length == 1) {
						args.put(temp[0], "");
					} else {
						args.put(temp[0], temp[1]);
					}
				}
				str = HttpClientUtil.httpPost(url, args);
			}
		}catch(Exception e) { }
		entity.setResult(str);
		return entity;
	}
	
	@RequestMapping(value = "showVideoTree/")
	public String showVideoTree(HttpServletRequest request, Model model) {
		//读取配置
		this.readConfigToSession(request);
		String str = "";
		HttpSession session = request.getSession();
		Object path = session.getAttribute(VIDEO_CONFIG_SESSION);
		try {
			str = HttpClientUtil.httpGet("http://"+path+"/previewAction!getAllCameraDeviceInfoTree.action", null);
		}catch(Exception e) { }
		model.addAttribute("treeNode", str);
		return "videoshow/hk_tree";
	}
	
	/**
	 * 读取86平台配置，并放入session和静态map中
	 * @param request
	 */
	private void readConfigToSession(HttpServletRequest request) {
		//读取配置文件
		VideoConfig.readConfig();
		HttpSession session = request.getSession();
		String str = VideoConfig.PROP_MAP.get("video_service_ip");
		if (StringUtils.isNotEmpty(str)) {
			session.setAttribute(VIDEO_CONFIG_SESSION, str);
		}
	}	
	
}
