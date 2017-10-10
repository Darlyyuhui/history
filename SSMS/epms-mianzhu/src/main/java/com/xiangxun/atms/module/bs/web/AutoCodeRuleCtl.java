package com.xiangxun.atms.module.bs.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.service.AutoCodeRuleService;
import com.xiangxun.atms.module.bs.vo.AutoCodeRule;
import com.xiangxun.atms.module.bs.vo.AutoCodeRuleSearch;

@Controller
@RequestMapping(value = "bs/autocode")
public class AutoCodeRuleCtl extends BaseCtl<AutoCodeRule, AutoCodeRuleSearch> {
	
	@Resource
	AutoCodeRuleService autoCodeRuleService;

	@Override
	protected BaseService<AutoCodeRule, AutoCodeRuleSearch> getBaseService() {
		return autoCodeRuleService;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="list/{menuid}/")
    public String list(@PathVariable String menuid, Model model, @RequestParam(value = "sortType", defaultValue = "TYPE_CODE ASC") String sortType
    		, @RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
        logger.info("正在进行【自动编号规则】数据列表查询。。。。。。");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		Page page = autoCodeRuleService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		List list = page.getResult();
		String dateType = null;
		for (int i = 0; i < list.size(); i++) {
			String dateTypeStr = "";
			dateType = ((AutoCodeRule)list.get(i)).getDateType();
			if (StringUtils.isEmpty(dateType)) {
				continue;
			}
			if (dateType.indexOf("yyyy") > -1) {
				dateTypeStr += "年份+";
			}
			if (dateType.indexOf("mm") > -1) {
				dateTypeStr += "月份+";
			}
			if (dateType.indexOf("dd") > -1) {
				dateTypeStr += "日期+";
			}
			if (dateTypeStr.length() > 0) {
				((AutoCodeRule)list.get(i)).setDateType(dateTypeStr.substring(0, dateTypeStr.length()-1));
			}
		}
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
        return "bs/autocode/list";
    }

    @RequestMapping(value="add/{menuid}/")
    public String add(@PathVariable String menuid, Model model, HttpServletRequest request) {
        model.addAttribute("menuid",menuid);
        return "bs/autocode/add";
    }

    @RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
    @LogAspect(desc="添加【自动编号规则】")
    public String doAdd(AutoCodeRule info, @PathVariable String menuid, RedirectAttributes redirectAttributes, Model model) {
    	info.setCreateId(super.getCurrentUserId());
    	info.setCreateTime(new Date());
        autoCodeRuleService.save(info);
        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/bs/autocode/list/"+menuid+"/";
    }
    
    @ResponseBody
    @RequestMapping(value = "checkOnly/{typeCode}/")
    public ResponseEntity checkOnly(@PathVariable String typeCode) {
    	ResponseEntity re = new ResponseEntity();
    	AutoCodeRule info = autoCodeRuleService.getById(typeCode);
    	if (info == null) {
    		re.setResult("ok");
    	} else {
    		re.setResult("no");
    	}
    	return re;
    }

    @RequestMapping(value = "update/{menuid}/{id}/", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") String id, @PathVariable String menuid, String page, Model model) {
    	AutoCodeRule info = autoCodeRuleService.getById(id);
        model.addAttribute("menuid",menuid);
        model.addAttribute("page", page);
        model.addAttribute("info",info);
        return "bs/autocode/update";
    }

    @RequestMapping(value = "doUpdate/{menuid}/", method = RequestMethod.POST)
    @LogAspect(desc="修改【自动编号规则】")
    public String doUpdate(@ModelAttribute("info") AutoCodeRule info, @PathVariable String menuid, String page, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    	info.setUpdateId(super.getCurrentUserId());
    	info.setUpdateTime(new Date());
    	autoCodeRuleService.updateByIdSelective(info);
        redirectAttributes.addFlashAttribute("message", "修改成功");
        return "redirect:/bs/autocode/list/"+menuid+"/?page="+page;
    }

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【自动编号规则】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【自动编号规则】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                autoCodeRuleService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }

	@Override
	@ModelAttribute("info")
	public AutoCodeRule getInfo(@RequestParam(value = "typeCode", required = false) String typeCode) {
		if (StringUtils.isNotEmpty(typeCode)) {
			return autoCodeRuleService.getById(typeCode);
		}
		return null;
	}
	
}
