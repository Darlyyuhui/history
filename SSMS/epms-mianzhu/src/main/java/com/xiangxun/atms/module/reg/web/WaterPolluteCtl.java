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
import com.xiangxun.atms.module.reg.service.WaterPolluteService;
import com.xiangxun.atms.module.reg.service.WaterRegService;
import com.xiangxun.atms.module.reg.vo.WaterPollute;
import com.xiangxun.atms.module.reg.vo.WaterPolluteSearch;
import com.xiangxun.atms.module.reg.vo.WaterReg;

@Controller
@RequestMapping(value = "reg/water/pollute")
@SessionAttributes(value = {"waterInfo"})
public class WaterPolluteCtl extends BaseCtl<WaterPollute, WaterPolluteSearch> {

	@Resource
	WaterPolluteService waterPolluteService;
	@Resource
	WaterRegService waterRegService;
	
	@Override
	protected BaseService<WaterPollute, WaterPolluteSearch> getBaseService() {
		return waterPolluteService;
	}

	@RequestMapping(value = "list/{menuid}/{waterId}/")
	public String list(@PathVariable("menuid") String menuid, @PathVariable("waterId") String waterId
			, @RequestParam(value = "sortType", defaultValue = "CREATE_TIME DESC") String sortType
			, @RequestParam(value = "page", defaultValue = "0") int pageNumber
			, Model model, HttpServletRequest request) {
		this.initModel(waterId, model);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("waterId", waterId);
		
		Page page = waterPolluteService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		model.addAttribute("menuid", menuid);
		return "reg/water/pollute/list";
	}
	
	/**
	 * 将水样采集登记信息查出并放入session
	 * @param blockId
	 * @param model
	 */
	private void initModel(String waterId, Model model) {
		WaterReg wr = waterRegService.getById(waterId);
		if (wr != null) {
			model.addAttribute("waterInfo", wr);
		}
	}
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String waterId, String page, Model model, HttpServletRequest request) {
		if (StringUtils.isNotEmpty(waterId)) {
			this.initModel(waterId, model);
		}
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "reg/water/pollute/add";
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【水样采样污染源】")
	public String doAdd(@PathVariable String menuid, WaterPollute info, MultipartHttpServletRequest fileRequest, RedirectAttributes attr) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		waterPolluteService.saveInfo(info, fileRequest);
		attr.addFlashAttribute("message", "新增成功");
		return "redirect:/reg/water/pollute/list/"+menuid+"/"+info.getWaterId()+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		WaterPollute info = waterPolluteService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "reg/water/pollute/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【水样采样污染源】")
	public String doUpdate(@PathVariable("menuid") String menuid,
			@ModelAttribute("info")WaterPollute info, MultipartHttpServletRequest fileRequest, RedirectAttributes attr) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		waterPolluteService.updateInfo(info, fileRequest);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/reg/water/pollute/list/"+menuid+"/"+info.getWaterId()+"/";
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【水样采样污染源】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【水样采样污染源】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                waterPolluteService.deleteById(string);
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
    	WaterPollute info = waterPolluteService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "reg/water/pollute/view";
	}
}
