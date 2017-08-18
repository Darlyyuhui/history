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
import com.xiangxun.atms.module.reg.service.ManureInfoService;
import com.xiangxun.atms.module.reg.service.ManureRegService;
import com.xiangxun.atms.module.reg.vo.ManureInfo;
import com.xiangxun.atms.module.reg.vo.ManureInfoSearch;
import com.xiangxun.atms.module.reg.vo.ManureReg;

/**
 * 肥料信息
 * @author HaoXiang
 * 2017年6月28日
 */
@Controller
@RequestMapping(value = "reg/manure/msg")
@SessionAttributes(value = {"manureReg"})
public class ManureMsgCtl extends BaseCtl<ManureInfo, ManureInfoSearch> {

	@Resource
	ManureInfoService manureInfoService;
	@Resource
	ManureRegService manureRegService;

	@Override
	protected BaseService<ManureInfo, ManureInfoSearch> getBaseService() {
		return manureInfoService;
	}
	
	@RequestMapping(value = "list/{menuid}/{manureId}/")
	public String list(@PathVariable("menuid") String menuid, @PathVariable("manureId") String manureId
			, @RequestParam(value = "sortType", defaultValue = "CREATE_TIME DESC") String sortType
			, @RequestParam(value = "page", defaultValue = "0") int pageNumber
			, Model model, HttpServletRequest request) {
		this.initModel(manureId, model);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("manureRegId", manureId);
		
		Page page = manureInfoService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		model.addAttribute("menuid", menuid);
		return "reg/manure/msg/list";
	}
	
	/**
	 * 将水样采集登记信息查出并放入session
	 * @param blockId
	 * @param model
	 */
	private void initModel(String manureId, Model model) {
		ManureReg wr = manureRegService.getById(manureId);
		if (wr != null) {
			model.addAttribute("manureReg", wr);
		}
	}
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String manureId, String page, Model model, HttpServletRequest request) {
		if (StringUtils.isNotEmpty(manureId)) {
			this.initModel(manureId, model);
		}
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "reg/manure/msg/add";
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【肥料信息】")
	public String doAdd(@PathVariable String menuid, ManureInfo info, MultipartHttpServletRequest fileRequest, RedirectAttributes attr) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		manureInfoService.save(info);
		attr.addFlashAttribute("message", "新增成功");
		return "redirect:/reg/manure/msg/list/"+menuid+"/"+info.getManureRegId()+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		ManureInfo info = manureInfoService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "reg/manure/msg/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【肥料信息】")
	public String doUpdate(@PathVariable("menuid") String menuid,
			@ModelAttribute("info")ManureInfo info, MultipartHttpServletRequest fileRequest, RedirectAttributes attr) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		manureInfoService.updateByIdSelective(info);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/reg/manure/msg/list/"+menuid+"/"+info.getManureRegId()+"/";
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【肥料信息】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【肥料信息】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                manureInfoService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
	
}
