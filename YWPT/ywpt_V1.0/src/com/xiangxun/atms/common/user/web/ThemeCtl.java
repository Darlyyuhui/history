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
import com.xiangxun.atms.common.user.service.ThemeService;
import com.xiangxun.atms.common.user.vo.Theme;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;

/**
 * 皮肤管理
 * @author kouyunhao
 * @version 1.0
 * Apr 9, 2014
 */
@Controller
@RequestMapping(value="system/theme")
public class ThemeCtl extends BaseCtl {
	
	@Resource
	ThemeService themeService;
	
	@RequestMapping(value = "change_theme/{theme}/", method = RequestMethod.GET)
	@ResponseBody
	public void changetheme(@PathVariable String theme, HttpServletResponse resp){
		List<Theme> themeList = themeService.findByUser(getCurrentUserId());
		if(themeList != null && themeList.size() != 0){
			Theme themeInfo = themeList.get(0);
			themeInfo.setTheme(theme);
			themeService.updateByIdSelective(themeInfo);
		}else{
			//add by kouyunhao 2014-05-13 为新增用户添加默认皮肤。
			Theme themeInfo = new  Theme();
			themeInfo.setId(UuidGenerateUtil.getUUIDLong());
			themeInfo.setUserId(getCurrentUserId());
			themeInfo.setTheme(theme);
			themeService.save(themeInfo);
		}
		JsonObject json = new JsonObject();
		json.addProperty("message", "ok");
		try {
			resp.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
