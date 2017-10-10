package com.xiangxun.atms.module.check.web;

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
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.check.service.DataCheckRuleService;
import com.xiangxun.atms.module.check.vo.DataCheckRule;
import com.xiangxun.atms.module.check.vo.DataCheckRuleSearch;

@Controller
@RequestMapping(value = "check/rule")
public class DataCheckRuleCtl extends BaseCtl<DataCheckRule, DataCheckRuleSearch> {

	@Resource
	DataCheckRuleService dataCheckRuleService;

	@Override
	protected BaseService<DataCheckRule, DataCheckRuleSearch> getBaseService() {
		return dataCheckRuleService;
	}
	
	@RequestMapping(value="list/{menuid}/")
    public String list(@PathVariable String menuid, Model model, @RequestParam(value = "sortType", defaultValue = "CREATE_TIME DESC") String sortType
    		, @RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
        logger.info("正在进行【数据校验规则】数据列表查询。。。。。。");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		Page page = dataCheckRuleService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
        return "check/rule/list";
    }

    @RequestMapping(value="add/{menuid}/")
    public String add(@PathVariable String menuid, Model model, HttpServletRequest request) {
        model.addAttribute("menuid",menuid);
        return "check/rule/add";
    }

    @RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
    @LogAspect(desc="添加【数据校验规则】")
    public String doAdd(DataCheckRule info, @PathVariable String menuid
    		, RedirectAttributes redirectAttributes, HttpServletRequest fileRequest) {
    	info.setId(UuidGenerateUtil.getUUIDLong());
    	info.setCreateId(super.getCurrentUserId());
    	info.setCreateTime(new Date());
    	dataCheckRuleService.save(info);
        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/check/rule/list/"+menuid+"/";
    }
    
    @ResponseBody
	@RequestMapping(value = "checkOnly/", method = RequestMethod.POST)
	public ResponseEntity checkOnly(String type, String objCode, String dimension) {
		ResponseEntity re = new ResponseEntity();
		if (StringUtils.isEmpty(type) || StringUtils.isEmpty(objCode) || StringUtils.isEmpty(dimension)) {
			re.setResult("exception");
			re.setMessage("缺失查询参数");
		} else {
			try {
				if (dataCheckRuleService.checkOnly(type, objCode, dimension)) {
					re.setResult("ok");
				} else {
					re.setResult("error");
					re.setMessage("校验规则重复");
				}
			}catch(Exception e) {
				logger.error("校验重复出错，" +e.getMessage());
				re.setResult("exception");
				re.setMessage("请检查查询参数");
			}
		}
		return re;
	}
    
    @RequestMapping(value = "update/{menuid}/{id}/", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") String id, @PathVariable String menuid, String page, Model model) {
    	DataCheckRule info = dataCheckRuleService.getById(id);
        model.addAttribute("menuid",menuid);
        model.addAttribute("page", page);
        model.addAttribute("info",info);
        return "check/rule/update";
    }

    @RequestMapping(value = "doUpdate/{menuid}/", method = RequestMethod.POST)
    @LogAspect(desc="修改【移动端设备】")
    public String doUpdate(@ModelAttribute("info") DataCheckRule info, @PathVariable String menuid, String page, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    	info.setUpdateId(super.getCurrentUserId());
    	info.setUpdateTime(new Date());
    	dataCheckRuleService.updateByIdSelective(info);
        redirectAttributes.addFlashAttribute("message", "修改成功");
        return "redirect:/check/rule/list/"+menuid+"/?page="+page;
    }
    
    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【数据校验规则】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【数据校验规则】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
            	dataCheckRuleService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
    
}
