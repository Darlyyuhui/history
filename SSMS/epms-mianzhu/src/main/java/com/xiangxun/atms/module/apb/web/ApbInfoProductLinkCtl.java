package com.xiangxun.atms.module.apb.web;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
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

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.constant.FORMAT;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.apb.service.ApbInfoProductLinkService;
import com.xiangxun.atms.module.apb.vo.ApbInfoProductLink;

@Controller
@RequestMapping(value = "/apbinfoproductlink")
public class ApbInfoProductLinkCtl extends BaseCtl {
    @Resource
    protected ApbInfoProductLinkService apbInfoProductLinkService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT.DATETIME.DEFAULT);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

    @RequestMapping(value="list/{menuid}/")
    public String list(@PathVariable String menuid, ModelMap model, @RequestParam(value = "sortType", defaultValue = "null") String sortType, @RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
        logger.info("正在进行【基地产品关联】数据列表查询。。。。。。");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Page page = apbInfoProductLinkService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType,menuid);
        model.addAttribute("menuid", menuid);
        model.addAttribute("sortType", sortType);
        model.addAttribute("pageList", page);
        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams,"search_"));
        ApbInfoProductLink apbInfoProductLink = new ApbInfoProductLink();
        try {
            //将查询的map转换成对象
            ConvertUtils.register(new DateConverter(),Date.class);
            BeanUtils.populate(apbInfoProductLink, searchParams);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        model.addAttribute("apbInfoProductLink",apbInfoProductLink);
        return "apbinfoproductlink/list";
    }

    @RequestMapping(value="add/{menuid}/")
    public String add(@PathVariable String menuid, ModelMap model, HttpServletRequest request) {
        model.addAttribute("menuid",menuid);
        return "apbinfoproductlink/add";
    }

    @RequestMapping(value = "doAdd/menuid/", method = RequestMethod.POST)
    @LogAspect(desc="添加【基地产品关联】")
    public String doAdd(ApbInfoProductLink apbInfoProductLink, String menuid, RedirectAttributes redirectAttributes, ModelMap model) {
        apbInfoProductLinkService.save(apbInfoProductLink);
        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/apbinfoproductlink/list/"+menuid+"/";
    }

    @RequestMapping(value = "update/{menuid}/{id}/", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") String id, @PathVariable String menuid, String page, ModelMap model) {
        ApbInfoProductLink apbInfoProductLink = apbInfoProductLinkService.getById(id);
        model.addAttribute("menuid",menuid);
        model.addAttribute("page", page);
        model.addAttribute("apbInfoProductLink",apbInfoProductLink);
        return "apbinfoproductlink/update";
    }

    @RequestMapping(value = "doUpdate", method = RequestMethod.POST)
    @LogAspect(desc="修改【基地产品关联】")
    public String doUpdate(@ModelAttribute("loadApbInfoProductLink") ApbInfoProductLink apbInfoProductLink, String menuid, String page, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        apbInfoProductLinkService.updateByIdSelective(apbInfoProductLink);
        redirectAttributes.addFlashAttribute("message", "修改成功");
        return "redirect:/apbinfoproductlink/list/"+menuid+"/?page="+page;
    }

    @RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
    @ResponseBody
    @LogAspect(desc="删除【基地产品关联】")
    public ResponseEntity delete(@PathVariable("ids") String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【基地产品关联】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                apbInfoProductLinkService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }

    @RequestMapping(value = "showView/{id}/{menuid}/", method = RequestMethod.GET)
    public String showView(@PathVariable("id") String id, @PathVariable String menuid, String page, ModelMap model) {
        ApbInfoProductLink apbInfoProductLink = apbInfoProductLinkService.getById(id);
        model.addAttribute("menuid",menuid);
        model.addAttribute("page", page);
        model.addAttribute("apbInfoProductLink",apbInfoProductLink);
        return "apbinfoproductlink/showView";
    }

    @ModelAttribute("loadApbInfoProductLink")
    public ApbInfoProductLink getApbInfoProductLink(@RequestParam(value = "id", required = false) String id) {
        if (id != null) {
            return apbInfoProductLinkService.getById(id);
        }
        return null;
    }
}