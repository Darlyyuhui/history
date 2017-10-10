package com.xiangxun.atms.module.reg.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Table;
import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.bs.constant.AutoCode;
import com.xiangxun.atms.module.land.cache.LandBlockCache;
import com.xiangxun.atms.module.land.service.LandMissionService;
import com.xiangxun.atms.module.land.vo.LandBlock;
import com.xiangxun.atms.module.reg.service.LandRegService;
import com.xiangxun.atms.module.reg.vo.LandReg;
import com.xiangxun.atms.module.reg.vo.LandRegSearch;

@Controller
@RequestMapping(value = "reg/land")
public class LandRegCtl extends RegBaseCtl<LandReg, LandRegSearch> {

	@Resource
	LandRegService landRegService;
	@Resource
	LandMissionService landMissionService;
	@Resource
    Cache cache;

	@Override
	protected BaseService<LandReg, LandRegSearch> getBaseService() {
		return landRegService;
	}
	
	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid
			, @RequestParam(value = "sortType", defaultValue = "T.SAMPLING_TIME DESC") String sortType
			, @RequestParam(value = "page", defaultValue = "0") int pageNumber
			, Model model, HttpServletRequest request) {
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		super.initModel("NTTR", landMissionService, request);
		
		Page page = landRegService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		LandReg lr = null;
		for (int i = 0 ; i < page.getResult().size(); i++) {
			lr = (LandReg)page.getResult().get(i);
			lr.setBlockIds(this.getLbMsg(lr.getId()));
		}
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		return "reg/land/list";
	}
	
	private String getLbMsg(String id) {
		List<String> list = landRegService.getLandBlocksByRegId(id);
		if (list == null || list.size() == 0) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		@SuppressWarnings("unchecked")
		Table<String, String, String> table = (Table<String, String, String>)cache.get(LandBlockCache.LB_ID_CODENAME);
		Map<String, String> cacheMap = table.column(LandBlockCache.LB_ID_CODENAME);
		for (String lbId : list) {
			str.append(cacheMap.get(lbId));
			str.append(",");
		}
		return str.substring(0, str.length()-1);
	}
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String page, Model model, HttpServletRequest request) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "reg/land/add";
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【土壤样品登记】")
	public String doAdd(@PathVariable String menuid, LandReg info
			, RedirectAttributes attr, MultipartHttpServletRequest fileRequest) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCode(AutoCode.SAMPLING_LAND_REG);
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		//采样来源
		info.setSamplingSource("1");
		//审查状态
		info.setCheckStatus(0);
		landRegService.saveInfo(info, fileRequest);
		attr.addFlashAttribute("message", "新增成功");
		return "redirect:/reg/land/list/"+menuid+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		LandReg info = landRegService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "reg/land/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【土壤样品登记】")
	public String doUpdate(@PathVariable String menuid, String page, @ModelAttribute("info")LandReg info
			, RedirectAttributes attr, MultipartHttpServletRequest fileRequest) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		landRegService.updateInfo(info, fileRequest);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/reg/land/list/"+menuid+"/?isgetsession=1&page="+page;
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【土壤样品登记】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【土壤样品登记】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                landRegService.deleteById(string);
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
    	LandReg info = landRegService.getById(id);
    	super.viewModel(info.getMissionId(), landMissionService, cache, model);
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "reg/land/view";
	}
    
    @ResponseBody
    @RequestMapping(value = "checkRegs/", method = RequestMethod.POST)
    @LogAspect(desc="审查【土壤样品登记】")
    public ResponseEntity checkRegs(@RequestParam(value = "ids", required = true) String ids
    		, @RequestParam(value = "status", required = true) int status) {
    	ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【土壤样品登记】审查。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
            	landRegService.checkById(string, status, this.getCurrentUserId());
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
    
    /**
     * 根据区域选择地块信息
     * @param regionId
     * @return
     */
    @RequestMapping(value = "getLbByRegion/{regionId}/", method = RequestMethod.POST)
    public String getLbByRegion(@PathVariable String regionId
    		, @RequestParam(value = "id", required = false) String id
    		, @RequestParam(value = "isEdit", required = false, defaultValue = "0") String isEdit, Model model) {
    	@SuppressWarnings("unchecked")
		List<LandBlock> list = (List<LandBlock>)cache.get(LandBlockCache.LB_ALL);
    	List<LandBlock> rList = new ArrayList<LandBlock>();
    	for (LandBlock lb : list) {
    		if (regionId.equals(lb.getRegionId())) {
    			rList.add(lb);
    		}
    	}
    	
    	Map<String, Boolean> checkedMap = new HashMap<String, Boolean>();
    	if (StringUtils.isNotEmpty(id)) {
    		//获取已选择地块信息列表
    		List<String> checkIdList = landRegService.getLandBlocksByRegId(id);
    		for (String str : checkIdList) {
    			checkedMap.put(str, true);
    		}
    	}
    	model.addAttribute("lbList", rList);
    	model.addAttribute("checkedMap", checkedMap);
    	model.addAttribute("isEdit", isEdit);
    	return "reg/land/sel_landblock";
    }
	
}
