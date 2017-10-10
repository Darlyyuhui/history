package com.xiangxun.atms.module.reg.web;

import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.bs.cache.AirPointCache;
import com.xiangxun.atms.module.bs.constant.AutoCode;
import com.xiangxun.atms.module.bs.vo.AirPoint;
import com.xiangxun.atms.module.land.service.LandMissionService;
import com.xiangxun.atms.module.reg.service.AirRegService;
import com.xiangxun.atms.module.reg.vo.AirReg;
import com.xiangxun.atms.module.reg.vo.AirRegSearch;

@Controller
@RequestMapping(value = "reg/air")
@SessionAttributes(value = "airpoints")
public class AirRegCtl extends RegBaseCtl<AirReg, AirRegSearch> {

	@Resource
	AirRegService airRegService;
	@Resource
	LandMissionService landMissionService;
	@Resource
    Cache cache;

	@Override
	protected BaseService<AirReg, AirRegSearch> getBaseService() {
		return airRegService;
	}
	
	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid
			, @RequestParam(value = "sortType", defaultValue = "SAMPLING_TIME DESC") String sortType
			, @RequestParam(value = "page", defaultValue = "0") int pageNumber
			, Model model, HttpServletRequest request) {
		this.initModel(model);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		super.initModel("DQ", landMissionService, request);
		
		Page page = airRegService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		return "reg/air/list";
	}
	
	private void initModel(Model model) {
		@SuppressWarnings("unchecked")
		List<AirPoint> apList = (List<AirPoint>)cache.get(AirPointCache.ALL_ITEM);
		model.addAttribute("airpoints", apList);
	}
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String page, Model model, HttpServletRequest request) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "reg/air/add";
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【空气样品登记】")
	public String doAdd(@PathVariable String menuid, AirReg info
			, RedirectAttributes attr, MultipartHttpServletRequest fileRequest) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCode(AutoCode.SAMPLING_AIR_REG);
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		//采样来源
		info.setSamplingSource("1");
		//审查状态
		info.setCheckStatus(0);
		airRegService.saveInfo(info, fileRequest);
		attr.addFlashAttribute("message", "新增成功");
		return "redirect:/reg/air/list/"+menuid+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		AirReg info = airRegService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "reg/air/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【空气样品登记】")
	public String doUpdate(@PathVariable String menuid, String page, @ModelAttribute("info")AirReg info
			, RedirectAttributes attr, MultipartHttpServletRequest fileRequest) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		airRegService.updateInfo(info, fileRequest);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/reg/air/list/"+menuid+"/?isgetsession=1&page="+page;
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【空气样品登记】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【空气样品登记】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                airRegService.deleteById(string);
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
    	AirReg info = airRegService.getById(id);
    	super.viewModel(info.getMissionId(), landMissionService, cache, model);
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		
		return "reg/air/view";
	}
    
    @ResponseBody
    @RequestMapping(value = "checkRegs/", method = RequestMethod.POST)
    @LogAspect(desc="审查【空气样品登记】")
    public ResponseEntity checkRegs(@RequestParam(value = "ids", required = true) String ids
    		, @RequestParam(value = "status", required = true) int status) {
    	ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【空气样品登记】审查。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
            	airRegService.checkById(string, status, this.getCurrentUserId());
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
	
}
