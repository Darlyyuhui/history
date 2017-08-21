package com.xiangxun.atms.module.property.web;

import java.io.IOException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.framework.base.BaseCtl;

/**
 * 资产管理
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value = "property/asset")
public class AssetManageCtl extends BaseCtl {
	
	/**
	 * 跳转到主界面
	 * @param menuid
	 * @param modal
	 * @return
	 */
	@RequestMapping(value = "list/{menuid}/", method = RequestMethod.GET)
	public String list(@PathVariable
	String menuid, ModelMap modal, HttpServletRequest request) {
		String type = request.getParameter("type");
		modal.addAttribute("type", type);
		modal.addAttribute("menuid", menuid);
		return "property/asset/list";
	}

	@RequestMapping(value = "showTree/", method = RequestMethod.POST)
	public void showTree(@RequestParam(value = "id", defaultValue = "0")
	String id, ServletResponse resp) {
		logger.info("正在进行设备信息查询。。。。。。");
		JsonArray jsonArray = new JsonArray();
		JsonObject json = null;
		if(id.equals("0")){
			json = new JsonObject();
			json.addProperty("id", "00");
			json.addProperty("pid", "0");
			json.addProperty("name", "设备分类");
			json.addProperty("isParent",id.equals("0") ? true : false);
			jsonArray.add(json);
		}else if(id.equals("00")){
			json = new JsonObject();
			json.addProperty("id", "01");
			json.addProperty("pid", "00");
			json.addProperty("name", "场外设备");
			json.addProperty("isParent",id.equals("00") ? true : false);
			jsonArray.add(json);
			json = new JsonObject();
			json.addProperty("id", "02");
			json.addProperty("pid", "00");
			json.addProperty("name", "场内设备");
			json.addProperty("isParent",id.equals("00") ? true : false);
			jsonArray.add(json);
		}else if(id.equals("01")){
			json = new JsonObject();
			json.addProperty("id", "001");
			json.addProperty("pid", "01");
			json.addProperty("name", "卡口设备");
			json.addProperty("isParent",false);
			jsonArray.add(json);
			json = new JsonObject();
			json.addProperty("id", "007");
			json.addProperty("pid", "01");
			json.addProperty("name", "智能机柜");
			json.addProperty("isParent",false);
			jsonArray.add(json);
			//电警设备展示无法监控，屏蔽
//			json = new JsonObject();
//			json.addProperty("id", "002");
//			json.addProperty("pid", "01");
//			json.addProperty("name", "电警设备");
//			json.addProperty("isParent",false);
//			jsonArray.add(json);
		}else{
			json = new JsonObject();
			json.addProperty("id", "005");
			json.addProperty("pid", "02");
			json.addProperty("name", "服务器");
			json.addProperty("isParent",false);
			jsonArray.add(json);
			json = new JsonObject();
			json.addProperty("id", "003");
			json.addProperty("pid", "02");
			json.addProperty("name", "数据库");
			json.addProperty("isParent",false);
			jsonArray.add(json);
			json = new JsonObject();
			json.addProperty("id", "004");
			json.addProperty("pid", "02");
			json.addProperty("name", "FTP信息");
			json.addProperty("isParent",false);
			jsonArray.add(json);
			json = new JsonObject();
			json.addProperty("id", "006");
			json.addProperty("pid", "02");
			json.addProperty("name", "平台信息");
			json.addProperty("isParent",false);
			jsonArray.add(json);
		}
		logger.debug("response json:{0}", jsonArray.toString());
		try {
			resp.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
