package com.xiangxun.atms.module.bs.web;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.service.AirPointService;
import com.xiangxun.atms.module.bs.vo.AirPoint;
import com.xiangxun.atms.module.bs.vo.AirPointSearch;

@Controller
@RequestMapping(value = "bs/airpoint")
public class AirPointCtl extends BaseCtl<AirPoint, AirPointSearch> {

	@Resource
	AirPointService airPointService;
	
	@Override
	protected BaseService<AirPoint, AirPointSearch> getBaseService() {
		return airPointService;
	}

	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid
			, @RequestParam(value = "sortType", defaultValue = "CODE ASC") String sortType
			, @RequestParam(value = "page", defaultValue = "0") int pageNumber
			, Model model, HttpServletRequest request) {
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		
		Page page = airPointService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		return "bs/airpoint/list";
	}
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String page, Model model, HttpServletRequest request) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "bs/airpoint/add";
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【空气点位】")
	public String doAdd(@PathVariable String menuid, AirPoint info
			, RedirectAttributes attr, HttpServletRequest request) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		airPointService.save(info);
		attr.addFlashAttribute("message", "新增成功");
		return "redirect:/bs/airpoint/list/"+menuid+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		AirPoint info = airPointService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "bs/airpoint/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【空气点位】")
	public String doUpdate(@PathVariable String menuid, String page, @ModelAttribute("info")AirPoint info
			, RedirectAttributes attr, HttpServletRequest request) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		airPointService.updateByIdSelective(info);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/bs/airpoint/list/"+menuid+"/?isgetsession=1&page="+page;
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【空气点位】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【空气点位】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                airPointService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
}
