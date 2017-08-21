package com.xiangxun.atms.module.eventalarm.web;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.eventalarm.service.AlaICabLogService;

/**
 * 开门报警显示页面
 * 
 * @author yangzhenyu
 *
 */
@Controller
@RequestMapping(value = "alarm/opendoor")
public class OpendoorCtl extends BaseCtl {
	@Resource
	AlaICabLogService alaicablogservice;

	@RequestMapping(value = "list/{menuid}/", method = {RequestMethod.POST,RequestMethod.GET})
	public String list(@PathVariable String menuid, ModelMap modal, HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "0") String pageNo,
			@RequestParam(value = "assetname", defaultValue = "") String assetname,
			@RequestParam(value = "ip", defaultValue = "") String ip,
			@RequestParam(value = "installplace", defaultValue = "") String installplace) {
		
		

		Page page = alaicablogservice.getAll(Integer.valueOf(pageNo), Page.DEFAULT_PAGE_SIZE, assetname, ip,
				installplace);
		modal.addAttribute("pageList", page);
		modal.addAttribute("menuid", menuid);
		modal.addAttribute("assetname", assetname);
		modal.addAttribute("installplace", installplace);
		modal.addAttribute("ip", ip);
		modal.addAttribute("searchParams",
				String.format("assetname=%s&ip=%s&installplace=%s", assetname, ip, installplace));

		return "alarm/opendoor/list";
	}


	/**
	 * 点击查看显示图片
	 * 
	 * @param menuid
	 * @param resp
	 * @param id
	 * @param modal
	 * @param photoNo
	 */
	@RequestMapping(value = "view/{menuid}", method = RequestMethod.GET)
	public void view(@PathVariable String menuid, HttpServletResponse resp, String id, ModelMap modal, int photoNo) {
		resp.setContentType("image/jpeg");
		byte[] photo = new byte[0];
		try (OutputStream out = resp.getOutputStream()) {
			if (photoNo == 1)
				photo = alaicablogservice.getphoto1(id);
			else
				photo = alaicablogservice.getphoto2(id);
			out.write(photo);
			out.flush();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

}
