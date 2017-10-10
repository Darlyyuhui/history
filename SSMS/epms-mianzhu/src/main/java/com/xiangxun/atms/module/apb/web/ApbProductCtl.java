package com.xiangxun.atms.module.apb.web;


import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.constant.FORMAT;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.apb.cache.ApbInfoCache;
import com.xiangxun.atms.module.apb.cache.ApbInfoProductLinkCache;
import com.xiangxun.atms.module.apb.cache.ApbProductTypeCache;
import com.xiangxun.atms.module.apb.service.ApbInfoProductLinkService;
import com.xiangxun.atms.module.apb.service.ApbProductService;
import com.xiangxun.atms.module.apb.vo.ApbInfo;
import com.xiangxun.atms.module.apb.vo.ApbInfoProductLink;
import com.xiangxun.atms.module.apb.vo.ApbProduct;
import com.xiangxun.atms.module.apb.vo.ApbProductSearch;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.constant.AutoCode;
/**
 * 农产品基地产品
 * @author admin
 *
 */
@Controller
@RequestMapping(value = "apb/apbproduct")
public class ApbProductCtl extends BaseCtl<ApbProduct,ApbProductSearch> {
    @Resource
    protected ApbProductService apbProductService;
    @Resource
    protected ApbInfoProductLinkService apbInfoProductLinkService;
    @Resource
    Cache cache;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT.DATETIME.DEFAULT);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

    @RequestMapping(value="list/{menuid}/")
    public String list(@PathVariable String menuid, ModelMap model, @RequestParam(value = "sortType", defaultValue = "CREATE_TIME DESC") String sortType, @RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
        logger.info("正在进行【基地产品】数据列表查询。。。。。。");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Page page = apbProductService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType,menuid);
        model.addAttribute("menuid", menuid);
        model.addAttribute("sortType", sortType);
        model.addAttribute("pageList", page);
        model.addAttribute("page",pageNumber);
        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams,"search_"));
        ApbProduct apbProduct = new ApbProduct();
        try {
            //将查询的map转换成对象
            ConvertUtils.register(new DateConverter(),Date.class);
            BeanUtils.populate(apbProduct, searchParams);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        model.addAllAttributes(searchParams);
        model.addAttribute("apbProduct",apbProduct);
        model.addAttribute("CODE_NAME",ApbProductTypeCache.CODE_NAME);
        model.addAttribute("INFO_NAME",ApbInfoProductLinkCache.ID_INFONAME);
        return "apb/apbproduct/list";
    }

    @SuppressWarnings({  "unchecked" })
	@RequestMapping(value="add/{menuid}/")
    public String add(@PathVariable("menuid") String menuid, ModelMap model, HttpServletRequest request) {
        model.addAttribute("menuid",menuid);
        List<ApbInfo> list=(List<ApbInfo>) cache.get(ApbInfoCache.ALL_ITEM);
        model.addAttribute("CODE_NAME",ApbProductTypeCache.CODE_NAME);
        model.addAttribute("infoListAll",list);
        return "apb/apbproduct/add";
    }

    @RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
    @LogAspect(desc="添加【基地产品】")
  
   public String doAdd(ApbProduct apbProduct,@PathVariable String menuid,String isContinue, RedirectAttributes redirectAttributes,String infoid,
		   MultipartHttpServletRequest fileRequest, HttpServletRequest request) {
    
    	String id=UuidGenerateUtil.getUUIDLong();
    	apbProduct.setId(id);
    	apbProduct.setCode(AutoCode.APB_PRODUCT);
    	apbProduct.setCreateId(getCurrentUserId());
    	apbProduct.setCreateTime(new Date());
    	String[] infoids=request.getParameterValues("info_name");
    	apbProductService.saveInfo(apbProduct,fileRequest);
    	ApbInfoProductLink apbInfoProductLink =new ApbInfoProductLink();
    	apbInfoProductLink.setProductId(id);
    	if(infoids!=null&&infoids.length>0){
          for(String infoid1:infoids){
        	apbInfoProductLink.setInfoId(infoid1.trim());
        	apbInfoProductLinkService.save(apbInfoProductLink);
    	 }
    	}
    	
        redirectAttributes.addFlashAttribute("message","添加成功");
        if ("1".equals(isContinue)) {
			return "redirect:/apb/apbproduct/add/"+menuid+"/";
		}
        return "redirect:/apb/apbproduct/list/"+menuid+"/";
    }

    @SuppressWarnings({  "unchecked", "rawtypes" })
	@RequestMapping(value = "update/{menuid}/{id}/", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") String id, @PathVariable("menuid") String menuid, String page, ModelMap model) {
        ApbProduct apbProduct = apbProductService.getById(id);
        com.google.common.collect.Table table1 = (com.google.common.collect.Table)cache.get(ApbProductTypeCache.CODE_NAME);
		Map map1=table1.column(ApbProductTypeCache.CODE_NAME);
		List<ApbInfo> list=(List<ApbInfo>) cache.get(ApbInfoCache.ALL_ITEM);
		com.google.common.collect.Table table3 = (com.google.common.collect.Table)cache.get(ApbInfoProductLinkCache.ID_APBINFO);
		List<ApbInfo> list1=(List<ApbInfo>) table3.column(ApbInfoProductLinkCache.ID_APBINFO).get(apbProduct.getId());
		String typeName= (String) map1.get(apbProduct.getTypeCode());
        model.addAttribute("menuid",menuid);
        model.addAttribute("page", page);
        model.addAttribute("info",apbProduct);
        model.addAttribute("CODE_NAME",ApbProductTypeCache.CODE_NAME);
        model.addAttribute("infoListAll",list);
        model.addAttribute("typeName",typeName);
        model.addAttribute("infoName",list1);
        return "apb/apbproduct/update";
    }
  
	@RequestMapping(value = "doUpdate/{menuid}/{id}", method = RequestMethod.POST)
    @LogAspect(desc="修改【基地产品】")
    public String doUpdate(@ModelAttribute("info") ApbProduct info, @PathVariable("menuid") String menuid, String page, HttpServletRequest request, RedirectAttributes redirectAttributes,String infoid,MultipartHttpServletRequest fileRequest) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
    	String[] infoids=request.getParameterValues("info_name");
    	apbProductService.updateInfo(info,fileRequest);
    	ApbInfoProductLink apbInfoProductLink =new ApbInfoProductLink();
    	apbInfoProductLink.setProductId(info.getId());
    	apbInfoProductLinkService.deleteByApbInfoProductLink(apbInfoProductLink);
    	if(infoids!=null&&infoids.length>0){
    	  for(String id:infoids){
        	apbInfoProductLink.setInfoId(id.trim());
        	apbInfoProductLinkService.save(apbInfoProductLink);
    	  }
    	}
        redirectAttributes.addFlashAttribute("message", "修改成功");
        return "redirect:/apb/apbproduct/list/"+menuid+"/?page="+page;
    }

    @RequestMapping(value="delete/{ids}/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【基地产品】数据")
    public ResponseEntity delete(@PathVariable("ids") String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【基地产品】数据删除。。。。。。");
            String[] id = ids.split(",");
            ApbInfoProductLink apbInfoProductLink =new ApbInfoProductLink();
            for (String string : id) {
                apbProductService.deleteById(string);
                apbInfoProductLink.setProductId(string);
                apbInfoProductLinkService.deleteByApbInfoProductLink(apbInfoProductLink);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
    @SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@RequestMapping(value = "showView/{menuid}/{id}/", method = RequestMethod.GET)
    public String showView(@PathVariable("id") String id, @PathVariable("menuid") String menuid, String page, ModelMap model) {
        ApbProduct apbProduct = apbProductService.getById(id);
        model.addAttribute("menuid",menuid);
        model.addAttribute("page", page);
        model.addAttribute("info",apbProduct);
        com.google.common.collect.Table table1 = (com.google.common.collect.Table)cache.get(ApbProductTypeCache.CODE_NAME);
		Map map1=table1.column(ApbProductTypeCache.CODE_NAME);
		com.google.common.collect.Table table3 = (com.google.common.collect.Table)cache.get(ApbInfoProductLinkCache.ID_APBINFO);
		String typeName= (String) map1.get(apbProduct.getTypeCode());
		model.addAttribute("typeName",typeName);
        model.addAttribute("infoName",ApbInfoProductLinkCache.ID_INFONAME);
        return "apb/apbproduct/showView";
    }
    @ModelAttribute("loadApbProduct")
    public ApbProduct getApbProduct(@RequestParam(value = "id", required = false) String id) {
        if (id != null) {
            return apbProductService.getById(id);
        }
        return null;
    }
	@Override
	protected BaseService<ApbProduct, ApbProductSearch> getBaseService() {
		return apbProductService;
	}
	
}