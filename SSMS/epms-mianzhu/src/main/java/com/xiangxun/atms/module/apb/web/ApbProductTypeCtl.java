package com.xiangxun.atms.module.apb.web;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.constant.FORMAT;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.apb.cache.ApbProductTypeCache;
import com.xiangxun.atms.module.apb.service.ApbProductTypeService;
import com.xiangxun.atms.module.apb.vo.ApbProductType;
import com.xiangxun.atms.module.apb.vo.ApbProductTypeSearch;

@Controller
@RequestMapping(value = "apb/apbproducttype")
public class ApbProductTypeCtl extends BaseCtl<ApbProductType, ApbProductTypeSearch> {
    @Resource
    protected ApbProductTypeService apbProductTypeService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT.DATETIME.DEFAULT);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

    @RequestMapping(value="list/{menuid}/")
    public String list(@PathVariable String menuid, ModelMap model, @RequestParam(value = "sortType", defaultValue = "ID") String sortType, @RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
        logger.info("正在进行【农产品类型】数据列表查询。。。。。。");
        model.addAttribute("treeData", apbProductTypeService.makeTreeData());
        return "apb/apbproducttype/list";
    }

    @RequestMapping(value="add/{menuid}/{pid}/")
    public String add(@PathVariable("menuid") String menuid, ModelMap model, HttpServletRequest request,
    		@PathVariable("pid") String pid, RedirectAttributes attr) {

		if (StringUtils.isEmpty(pid)) {
			attr.addFlashAttribute("message", "未选择上级节点。");
			return "redirect:/apb/apbproducttype/list/"+menuid+"/";
		}
		String pname = "";
		if ("0".equals(pid)) {
			pname = "农产品类型";
		} else {
			ApbProductType plt = apbProductTypeService.getById(pid);
			if (plt == null) {
				attr.addFlashAttribute("message", "上级节点信息获取错误。");
				return "redirect:/apb/apbproducttype/list/"+menuid+"/";
			}
			pname = plt.getName();
		}
		
		model.addAttribute("pid", pid);
		model.addAttribute("pname", pname);
		model.addAttribute("menuid", menuid);
		return "apb/apbproducttype/add";
    }

    @RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
    @LogAspect(desc="添加【农产品类型】")
    public String doAdd(ApbProductType apbProductType,String isContinue, @PathVariable String menuid, RedirectAttributes redirectAttributes, ModelMap model) {
    	apbProductType.setId(UuidGenerateUtil.getUUIDLong());
    	apbProductTypeService.save(apbProductType);
        redirectAttributes.addFlashAttribute("message","添加成功");
        redirectAttributes.addFlashAttribute("message", "新增成功");
		if ("1".equals(isContinue)) {
			return "redirect:/apb/apbproducttype/add/"+menuid+"/"+apbProductType.getPid()+"/";
		}
        return "redirect:/apb/apbproducttype/list/"+menuid+"/";
    }

    @RequestMapping(value = "update/{menuid}/{id}/", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") String id, @PathVariable("menuid") String menuid, String page, ModelMap model) {
        ApbProductType lt = apbProductTypeService.getById(id);
        String pname = "";
        if ("0".equals(lt.getPid())) {
			pname = "农产品类型";
		} else {
			ApbProductType plt = apbProductTypeService.getById(lt.getPid());
			pname = plt.getName();
		}
        model.addAttribute("pname", pname);
		model.addAttribute("info", lt);
        model.addAttribute("menuid",menuid);
        model.addAttribute("page", page);
        return "/apb/apbproducttype/update";
    }

    @RequestMapping(value = "doUpdate/{menuid}/", method = RequestMethod.POST)
    @LogAspect(desc="修改【农产品类型】")
    public String doUpdate(@ModelAttribute("info") ApbProductType info, @PathVariable("menuid") String menuid, String page, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    	apbProductTypeService.updateByIdSelective(info);
        redirectAttributes.addFlashAttribute("message", "修改成功");
        return "redirect:/apb/apbproducttype/list/"+menuid+"/";
    }

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【农产品类型】")
    public ResponseEntity delete(@RequestParam(value = "ids" ,required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        System.out.println("123");
        try {
            logger.info("正在进行【农产品类型】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
            	if ("0".equals(string)) {
            		continue;
            	}
                apbProductTypeService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            System.out.println(e);
            return entity;
        }
    }

    @RequestMapping(value = "showView/{menuid}/{id}/", method = RequestMethod.GET)
    public String showView(@PathVariable("id") String id, @PathVariable String menuid, String page, ModelMap model) {
        ApbProductType apbProductType = apbProductTypeService.getById(id);
        model.addAttribute("menuid",menuid);
        model.addAttribute("page", page);
        model.addAttribute("info",apbProductType);
        model.addAttribute("ID_NAME",ApbProductTypeCache.ID_NAME);
        return "apb/apbproducttype/showView";
    }

	@Override
	protected BaseService<ApbProductType, ApbProductTypeSearch> getBaseService() {
		return apbProductTypeService;
	}
}