package com.xiangxun.atms.module.land.web;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.land.service.LandBlockErrorService;
import com.xiangxun.atms.module.land.service.LandBlockService;
import com.xiangxun.atms.module.land.vo.LandBlock;
import com.xiangxun.atms.module.land.vo.LandBlockError;
import com.xiangxun.atms.module.land.vo.LandBlockErrorSearch;

@Controller
@RequestMapping(value = "land/block/error")
@SessionAttributes(value = {"blockId", "blockCode", "blockName"})
public class LandBlockErrorCtl extends BaseCtl<LandBlockError, LandBlockErrorSearch> {

	@Resource
	LandBlockService landBlockService;
	@Resource
	LandBlockErrorService landBlockErrorService;
	
	@Override
	protected BaseService<LandBlockError, LandBlockErrorSearch> getBaseService() {
		return landBlockErrorService;
	}
	
	@RequestMapping(value = "list/{menuid}/{blockId}/")
	public String list(@PathVariable("menuid") String menuid, @PathVariable("blockId") String blockId
			, @RequestParam(value = "sortType", defaultValue = "CREATE_TIME DESC") String sortType
			, @RequestParam(value = "page", defaultValue = "0") int pageNumber
			, Model model, HttpServletRequest request) {
		this.initModel(blockId, model);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("landBlockId", blockId);
		Page page = landBlockErrorService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		model.addAttribute("menuid", menuid);
		return "land/block/error/list";
	}
	
	/**
	 * 将地块信息查出并放入session
	 * @param blockId
	 * @param model
	 */
	private void initModel(String blockId, Model model) {
		model.addAttribute("blockId", blockId);
		LandBlock lb = landBlockService.getById(blockId);
		if (lb != null) {
			model.addAttribute("blockCode", lb.getCode());
			model.addAttribute("blockName", lb.getName());
		}
	}
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String blockId, String page, Model model, HttpServletRequest request) {
		if (StringUtils.isNotEmpty(blockId)) {
			this.initModel(blockId, model);
		}
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "land/block/error/add";
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【土壤地块异常】")
	public String doAdd(@PathVariable String menuid, LandBlockError info, MultipartHttpServletRequest fileRequest, RedirectAttributes attr) {
		//保存地块异常信息
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		landBlockErrorService.saveInfo(info, fileRequest);
		attr.addFlashAttribute("message", "新增成功");
		return "redirect:/land/block/error/list/"+menuid+"/"+info.getLandBlockId()+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		LandBlockError info = landBlockErrorService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "land/block/error/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【土壤地块异常】")
	public String doUpdate(@PathVariable("menuid") String menuid,
			@ModelAttribute("info")LandBlockError info, MultipartHttpServletRequest fileRequest, RedirectAttributes attr) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		landBlockErrorService.updateInfo(info, fileRequest);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/land/block/error/list/"+menuid+"/"+info.getLandBlockId()+"/";
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【土壤地块异常】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【土壤地块异常】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                landBlockErrorService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
    
    @RequestMapping(value = "showView/{menuid}/{id}/")
	public String showView(@PathVariable("menuid") String menuid,@PathVariable("id") String id, String page, Model model) {
		LandBlockError info = landBlockErrorService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "land/block/error/view";
	}

}
