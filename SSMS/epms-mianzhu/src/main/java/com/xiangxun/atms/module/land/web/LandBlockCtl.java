package com.xiangxun.atms.module.land.web;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.cache.OwnerCahe;
import com.xiangxun.atms.module.geoServer.domain.LayerBean;
import com.xiangxun.atms.module.geoServer.domain.LayerEnum;
import com.xiangxun.atms.module.geoServer.service.IMapOperation;
import com.xiangxun.atms.module.land.service.LandBlockService;
import com.xiangxun.atms.module.land.vo.LandBlock;
import com.xiangxun.atms.module.land.vo.LandBlockSearch;
@Controller
@RequestMapping(value = "land/block")
@SessionAttributes(value = {"owners"})
public class LandBlockCtl extends BaseCtl<LandBlock, LandBlockSearch>  {
	@Resource
	LandBlockService landBlockService;
	@Resource
	IMapOperation iMapOperation;
	@Resource
	Cache cache;
	@Override
	protected BaseService<LandBlock, LandBlockSearch> getBaseService() {
		return landBlockService;
	}
	
	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid
			, @RequestParam(value = "sortType", defaultValue = "CREATE_TIME DESC") String sortType
			, @RequestParam(value = "page", defaultValue = "0") int pageNumber
			, Model model, HttpServletRequest request) {
		this.initModel(model);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		
		Page page = landBlockService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		return "land/block/info/list";
	}
	
	private void initModel(Model model) {
		Object o =cache.get(OwnerCahe.ALL_ITEM);
		model.addAttribute("owners", o);
	}
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String page, Model model, HttpServletRequest request) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "land/block/info/add";
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【土壤地块】")
	public String doAdd(@PathVariable String menuid, LandBlock info, String isContinue, RedirectAttributes attr) {
		String id=UuidGenerateUtil.getUUIDLong();
		info.setId(id);
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		LayerBean layerBean=new LayerBean();
		layerBean.setCode(id);
		layerBean.setGeometry(info.getGeoJson());
		layerBean.setName(info.getName());
		iMapOperation.add(LayerEnum.LAND, layerBean);
		landBlockService.save(info);
		attr.addFlashAttribute("message", "新增成功");
		return "redirect:/land/block/list/"+menuid+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model)  {
		LandBlock info = landBlockService.getById(id);
		List<LayerBean>  layerList= iMapOperation.getByCode(LayerEnum.LAND, id);
		if(layerList!=null){
			 for (LayerBean layerBean : layerList) {
		        	info.setGeoJson(layerBean.getGeometry());
				}
		}
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "land/block/info/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【土壤地块】")
	public String doUpdate(@PathVariable String menuid, String page, @ModelAttribute("info")LandBlock info, RedirectAttributes attr) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		LayerBean layerBean=new LayerBean();
		layerBean.setCode(info.getId()); 
		if(info.getGeoJson()==""){
			iMapOperation.deleteByCode(LayerEnum.LAND, info.getId());	
		}else{
			layerBean.setGeometry(info.getGeoJson());
			layerBean.setName(info.getName());
			iMapOperation.save(LayerEnum.LAND, layerBean);
		}
		landBlockService.updateById(info);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/land/block/list/"+menuid+"/?isgetsession=1&page="+page;
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【土壤地块】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【土壤地块】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
            	iMapOperation.deleteByCode(LayerEnum.LAND, string);
                landBlockService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
    
    @RequestMapping(value = "showView/{menuid}/{id}/")
	public String showView(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		LandBlock info = landBlockService.getById(id);
		List<LayerBean>  layerList= iMapOperation.getByCode(LayerEnum.LAND, id);
		if(layerList!=null && layerList.size() > 0){
			 for (LayerBean layerBean : layerList) {
		        	info.setGeoJson(layerBean.getGeometry());
				}
		}
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		
		return "land/block/info/view";
	}

}
