/**
 * 
 */
package com.xiangxun.atms.common.user.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.xiangxun.atms.common.user.service.SkinService;
import com.xiangxun.atms.common.user.vo.Skin;
import com.xiangxun.atms.framework.base.BaseCtl;

/**
 * 皮肤管理
 * @author kouyunhao
 * @version 1.0
 * Apr 9, 2014
 */
@Controller
@RequestMapping(value="system/skin")
public class SkinCtl extends BaseCtl {
	
	@Resource
	SkinService skinService;
	
	@RequestMapping(value = "change_skin/{skin}/", method = RequestMethod.GET)
	@ResponseBody
	public void changeSkin(@PathVariable String skin, HttpServletResponse resp){
		List<Skin> skinList = skinService.findByUser(getCurrentUserId());
		Skin skinInfo = skinList.get(0);
		skinInfo.setSkin(skin);
		skinService.updateByIdSelective(skinInfo);
		JsonObject json = new JsonObject();
		json.addProperty("message", "ok");
		try {
			resp.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
