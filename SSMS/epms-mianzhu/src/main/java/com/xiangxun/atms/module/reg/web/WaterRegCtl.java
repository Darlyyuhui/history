package com.xiangxun.atms.module.reg.web;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.land.service.LandMissionService;
import com.xiangxun.atms.module.reg.service.WaterRegService;
import com.xiangxun.atms.module.reg.vo.WaterReg;
import com.xiangxun.atms.module.reg.vo.WaterRegSearch;

@Controller
@RequestMapping(value = "reg/water")
public class WaterRegCtl extends RegBaseCtl<WaterReg, WaterRegSearch> {

	@Resource
	WaterRegService waterRegService;
	@Resource
	LandMissionService landMissionService;
	@Resource
    Cache cache;

	@Override
	protected BaseService<WaterReg, WaterRegSearch> getBaseService() {
		return waterRegService;
	}
	
	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid
			, @RequestParam(value = "sortType", defaultValue = "T.SAMPLING_TIME DESC") String sortType
			, @RequestParam(value = "page", defaultValue = "0") int pageNumber
			, Model model, HttpServletRequest request) {
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		super.initModel("DBS,DN,DXS", landMissionService, request);
		
		Page page = waterRegService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		return "reg/water/info/list";
	}
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String page, Model model, HttpServletRequest request) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "reg/water/info/add";
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【水样底泥登记】")
	public String doAdd(@PathVariable String menuid, WaterReg info
			, RedirectAttributes attr, MultipartHttpServletRequest fileRequest) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		//采样来源
		info.setSamplingSource("1");
		//审查状态
		info.setCheckStatus(0);
		waterRegService.saveInfo(info, fileRequest);
		attr.addFlashAttribute("message", "新增成功");
		return "redirect:/reg/water/list/"+menuid+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		WaterReg info = waterRegService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "reg/water/info/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【水样底泥登记】")
	public String doUpdate(@PathVariable String menuid, String page, @ModelAttribute("info")WaterReg info
			, RedirectAttributes attr, MultipartHttpServletRequest fileRequest) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		waterRegService.updateInfo(info, fileRequest);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/reg/water/list/"+menuid+"/?isgetsession=1&page="+page;
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【水样底泥登记】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【水样底泥登记】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                waterRegService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
    
    @RequestMapping(value = "showView/{menuid}/{id}/")
	public String showView(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
    	WaterReg info = waterRegService.getById(id);
    	super.viewModel(info.getMissionId(), landMissionService, cache, model);
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "reg/water/info/view";
	}
    
    @ResponseBody
    @RequestMapping(value = "checkRegs/", method = RequestMethod.POST)
    @LogAspect(desc="审查【水样底泥登记】")
    public ResponseEntity checkRegs(@RequestParam(value = "ids", required = true) String ids
    		, @RequestParam(value = "status", required = true) int status) {
    	ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【水样底泥登记】审查。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
            	waterRegService.checkById(string, status, this.getCurrentUserId());
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
	
}
