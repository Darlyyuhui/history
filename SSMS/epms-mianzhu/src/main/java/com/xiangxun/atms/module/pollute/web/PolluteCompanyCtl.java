package com.xiangxun.atms.module.pollute.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
import com.xiangxun.atms.framework.constant.FORMAT;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.pollute.service.PolluteCompanyService;
import com.xiangxun.atms.module.pollute.vo.PolluteCompany;
import com.xiangxun.atms.module.pollute.vo.PolluteCompanySearch;

@Controller
@RequestMapping(value = "pollute/pollutecompany")
public class PolluteCompanyCtl extends BaseCtl<PolluteCompany, PolluteCompanySearch> {
    @Resource
    protected PolluteCompanyService polluteCompanyService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT.DATETIME.DEFAULT);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

    @RequestMapping(value="list/{menuid}/")
    public String list(@PathVariable String menuid, ModelMap model, @RequestParam(value = "sortType", defaultValue = "CREATE_TIME DESC") 
     String sortType, @RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
        logger.info("正在进行PolluteCompany数据列表查询。。。。。。");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		
		Page page = polluteCompanyService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		
        return "pollute/pollutecompany/list";
    }

    @RequestMapping(value="add/{menuid}/")
    public String add(@PathVariable String menuid, ModelMap model, HttpServletRequest request) {
        model.addAttribute("menuid",menuid);
        return "pollute/pollutecompany/add";
    }

    @RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
    @LogAspect(desc="添加【污染企业】数据")
    public String doAdd(PolluteCompany polluteCompany, @PathVariable String menuid, RedirectAttributes redirectAttributes, ModelMap model
    		,String isContinue,MultipartHttpServletRequest fileRequest) {
    	polluteCompany.setId(UuidGenerateUtil.getUUIDLong());
    	polluteCompany.setCreateId(getCurrentUserId());
    	polluteCompany.setCreateTime(new Date());
        polluteCompanyService.saveInfo(polluteCompany,fileRequest);
        redirectAttributes.addFlashAttribute("message","添加成功");
        if ("1".equals(isContinue)) {
			return "redirect:/pollute//pollutecompany/add/"+menuid+"/";
		}
        return "redirect:/pollute/pollutecompany/list/"+menuid+"/";
    }

    @RequestMapping(value = "update/{menuid}/{id}/", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") String id, @PathVariable String menuid, String page, ModelMap model) {
        PolluteCompany polluteCompany = polluteCompanyService.getById(id);
        model.addAttribute("menuid",menuid);
        model.addAttribute("page", page);
        model.addAttribute("info",polluteCompany);
        return "pollute/pollutecompany/update";
    }

    @RequestMapping(value = "doUpdate/{menuid}/", method = RequestMethod.POST)
    @LogAspect(desc="修改【污染企业】数据")
    public String doUpdate(@ModelAttribute("info") PolluteCompany info,  @PathVariable("menuid") String menuid, String page, HttpServletRequest request, RedirectAttributes redirectAttributes
    		,MultipartHttpServletRequest fileRequest) {
    	info.setUpdateId(getCurrentUserId());
    	info.setUpdateTime(new Date());
        polluteCompanyService.updateInfo(info,fileRequest);
        redirectAttributes.addFlashAttribute("message", "修改成功");
        return "redirect:/pollute/pollutecompany/list/"+menuid+"/?page="+page;
    }

    @RequestMapping(value="delete/{ids}/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【污染企业】数据")
    public ResponseEntity delete(@PathVariable("ids") String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行PolluteCompany数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                polluteCompanyService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }

    @RequestMapping(value = "showView/{menuid}/{id}/", method = RequestMethod.GET)
    @LogAspect(desc="查看【污染企业】数据")
    public String showView(@PathVariable("id") String id, @PathVariable String menuid, String page, ModelMap model) {
        PolluteCompany polluteCompany = polluteCompanyService.getById(id);
        model.addAttribute("menuid",menuid);
        model.addAttribute("page", page);
        model.addAttribute("message", null);
        model.addAttribute("info",polluteCompany);
        return "pollute/pollutecompany/showView";
    }

    @ModelAttribute("loadPolluteCompany")
    public PolluteCompany getPolluteCompany(@RequestParam(value = "id", required = false) String id) {
        if (id != null) {
            return polluteCompanyService.getById(id);
        }
        return null;
    }

	@Override
	protected BaseService<PolluteCompany, PolluteCompanySearch> getBaseService() {
		
		return polluteCompanyService;
	}


}