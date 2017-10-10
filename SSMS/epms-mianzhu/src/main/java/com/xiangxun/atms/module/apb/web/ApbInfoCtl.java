package com.xiangxun.atms.module.apb.web;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.xiangxun.atms.module.apb.cache.ApbProductTypeCache;
import com.xiangxun.atms.module.apb.service.ApbInfoProductLinkService;
import com.xiangxun.atms.module.apb.service.ApbInfoService;
import com.xiangxun.atms.module.apb.service.ApbProductService;
import com.xiangxun.atms.module.apb.service.ApbProductTypeService;
import com.xiangxun.atms.module.apb.vo.ApbInfo;
import com.xiangxun.atms.module.apb.vo.ApbInfoProductLink;
import com.xiangxun.atms.module.apb.vo.ApbInfoSearch;
import com.xiangxun.atms.module.apb.vo.ApbProductType;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.cache.LandTypeCache;
import com.xiangxun.atms.module.bs.constant.AutoCode;
import com.xiangxun.atms.module.bs.service.LandTypeService;
import com.xiangxun.atms.module.bs.vo.LandType;
import com.xiangxun.atms.module.geoServer.domain.LayerBean;
import com.xiangxun.atms.module.geoServer.domain.LayerEnum;
import com.xiangxun.atms.module.geoServer.service.IMapOperation;

@Controller
@RequestMapping(value = "apb/apbinfo")
public class ApbInfoCtl extends BaseCtl<ApbInfo,ApbInfoSearch> {
    @Resource
    protected ApbInfoService apbInfoService;
    @Resource
    protected ApbProductService apbProductService;
    @Resource
    protected ApbProductTypeService apbProductTypeService;
    @Resource
    protected ApbInfoProductLinkService apbInfoProductLinkService;
    @Resource
    Cache cache;
    @Resource
	IMapOperation iMapOperation;
    @Resource
    LandTypeService landTypeService;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT.DATETIME.DEFAULT);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

    @RequestMapping(value="list/{menuid}/")
    public String list(@PathVariable("menuid") String menuid, ModelMap model, @RequestParam(value = "sortType", defaultValue = "CREATE_TIME DESC") String sortType, @RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
        logger.info("正在进行【农产品基地】数据列表查询。。。。。。");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        super.updateSession(request, menuid, searchParams);
        Page page = apbInfoService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType,menuid);
        model.addAttribute("menuid", menuid);
        model.addAttribute("sortType", sortType);
        model.addAttribute("pageList", page);
        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams,"search_"));
        ApbInfo apbInfo = new ApbInfo();
        try {
            //将查询的map转换成对象
            ConvertUtils.register(new DateConverter(),Date.class);
            BeanUtils.populate(apbInfo, searchParams);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        model.addAllAttributes(searchParams);
        model.addAttribute("apbInfo",apbInfo);
        model.addAttribute("page",pageNumber);
        model.addAttribute("ID_APBPRODUCTTYPENAME",ApbInfoCache.ID_MAINPRODUCT);
        model.addAttribute("APBPRODUCTTYPE",ApbProductTypeCache.CODE_NAME);
        
        model.addAttribute("SOILE_TYPE",LandTypeCache.CODE_NAME);
        return "apb/apbinfo/list";
    }

    @SuppressWarnings("unchecked")
	@RequestMapping(value="add/{menuid}/")
    public String add(@PathVariable("menuid") String menuid, ModelMap model, HttpServletRequest request) {
    	List<ApbProductType> productTypeList =(List<ApbProductType>) cache.get(ApbProductTypeCache.ALL_ITEM);
        List<LandType> landTypeList = (List<LandType>) cache.get(LandTypeCache.ALL_ITEM);
    	model.addAttribute("menuid",menuid);
        model.addAttribute("soilTypeList",getLandType());
        model.addAttribute("productTypeList",productTypeList);
        model.addAttribute("SOILE_TYPE",LandTypeCache.CODE_NAME);
        model.addAttribute("landTypeList",landTypeList);
        return "apb/apbinfo/add";
    }

    @RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
    @LogAspect(desc="添加【农产品基地】")
    public String doAdd(ApbInfo apbInfo,@PathVariable("menuid") String menuid,String isContinue, RedirectAttributes redirectAttributes,
    		ModelMap model, HttpServletRequest request) {
    	String id=UuidGenerateUtil.getUUIDLong();
    	LayerBean layerBean=new LayerBean();
		layerBean.setCode(id);
		layerBean.setGeometry(apbInfo.getGeoJson());
		layerBean.setName(apbInfo.getName());
		iMapOperation.add(LayerEnum.APBLAND, layerBean);
    	apbInfo.setId(id);
    	apbInfo.setCode(AutoCode.APB_INFO);
    	apbInfo.setCreateId(getCurrentUserId());
    	apbInfo.setCreateTime(new Date());
    	apbInfoService.save(apbInfo);
    	
    	
        redirectAttributes.addFlashAttribute("message","添加成功");
        if ("1".equals(isContinue)) {
			return "redirect:/apb/apbinfo/add/"+menuid+"/";
		}
        return "redirect:/apb/apbinfo/list/"+menuid+"/";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "update/{menuid}/{id}/", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") String id, @PathVariable("menuid") String menuid, String page, ModelMap model) {
    	ApbInfo apbInfo = apbInfoService.getById(id);
    	List<ApbProductType> productTypeList =(List<ApbProductType>) cache.get(ApbProductTypeCache.ALL_ITEM);
    	com.google.common.collect.Table table3 = (com.google.common.collect.Table)cache.get(ApbInfoCache.ID_MAINPRODUCT);
    	List<String> productTypeNameList=(List<String>)table3.column(ApbInfoCache.ID_MAINPRODUCT).get(apbInfo.getId());
    	List<LayerBean>  layerList= iMapOperation.getByCode(LayerEnum.APBLAND, id);
		if(layerList!=null && layerList.size()>0){
			 for (LayerBean layerBean : layerList) {
				 apbInfo.setGeoJson(layerBean.getGeometry());
				}
		}
		List<LandType> landTypeList = (List<LandType>) cache.get(LandTypeCache.ALL_ITEM);
        model.addAttribute("menuid",menuid);
        model.addAttribute("page", page);
        model.addAttribute("info",apbInfo);
        model.addAttribute("soilTypeList",getLandType());
        model.addAttribute("productTypeList",productTypeList);
        model.addAttribute("productTypeNameList",productTypeNameList);
        model.addAttribute("SOILE_TYPE",LandTypeCache.CODE_NAME);
        model.addAttribute("landTypeList",landTypeList);
        return "apb/apbinfo/update";
    }

    @RequestMapping(value = "doUpdate/{menuid}", method = RequestMethod.POST)
    @LogAspect(desc="修改【农产品基地】")
    public String doUpdate(@ModelAttribute("info") ApbInfo info,@PathVariable("menuid") String menuid, String page, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    	info.setUpdateId(getCurrentUserId());
    	info.setUpdateTime(new Date());
    	LayerBean layerBean=new LayerBean();
		layerBean.setCode(info.getId()); 
		if("".equals(info.getGeoJson())){
			iMapOperation.deleteByCode(LayerEnum.APBLAND, info.getId());	
		}else{
			layerBean.setGeometry(info.getGeoJson());
			layerBean.setName(info.getName());
			iMapOperation.save(LayerEnum.APBLAND, layerBean);
		}
    	ApbInfoProductLink apbInfoProductLink =new ApbInfoProductLink();
    	apbInfoProductLink.setInfoId(info.getId());
    	apbInfoProductLinkService.deleteByApbInfoProductLink(apbInfoProductLink);
    	apbInfoService.updateByIdSelective(info);
        redirectAttributes.addFlashAttribute("message", "修改成功");
        return "redirect:/apb/apbinfo/list/"+menuid+"/?page="+page;
    }

    @RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
    @ResponseBody
    @LogAspect(desc="删除【农产品基地】")
    public ResponseEntity delete(@PathVariable("ids") String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【农产品基地】数据删除。。。。。。");
            String[] id = ids.split(",");
            ApbInfoProductLink apbInfoProductLink =new ApbInfoProductLink();
            for (String string : id) {

            	iMapOperation.deleteByCode(LayerEnum.APBLAND, string);

                apbInfoService.deleteById(string);
                apbInfoProductLink.setInfoId(string);
                apbInfoProductLinkService.deleteByApbInfoProductLink(apbInfoProductLink);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }

    @RequestMapping(value = "showView/{menuid}/{id}/", method = RequestMethod.GET)
    public String showView(@PathVariable("id") String id, @PathVariable("menuid") String menuid, String page, ModelMap model) {
        ApbInfo apbInfo = apbInfoService.getById(id);
        model.addAttribute("menuid",menuid);
        List<LayerBean>  layerList= iMapOperation.getByCode(LayerEnum.APBLAND, id);
		if(layerList!=null && layerList.size() > 0){
			 for (LayerBean layerBean : layerList) {
				 apbInfo.setGeoJson(layerBean.getGeometry());
				}
		}
		model.addAttribute("ID_APBPRODUCTTYPENAME",ApbInfoCache.ID_MAINPRODUCT);
        model.addAttribute("SOILE_TYPE",LandTypeCache.CODE_NAME);
        model.addAttribute("page", page);
        model.addAttribute("info",apbInfo);
        return "apb/apbinfo/showView";
    }

    @ModelAttribute("loadApbInfo")
    public ApbInfo getApbInfo(@RequestParam(value = "id", required = false) String id) {
        if (id != null) {
            return apbInfoService.getById(id);
        }
        return null;
    }

	@Override
	protected BaseService<ApbInfo, ApbInfoSearch> getBaseService() {
		
		return apbInfoService;
	}
	@SuppressWarnings("unchecked")
	public List<String> getLandType(){
		List<LandType> landTypeList = (List<LandType>)cache.get(LandTypeCache.ALL_ITEM);
		List<String> list = new ArrayList<String>();
		for(LandType it:landTypeList){
			list.add(it.getName());
		}
		return list;
	}
}