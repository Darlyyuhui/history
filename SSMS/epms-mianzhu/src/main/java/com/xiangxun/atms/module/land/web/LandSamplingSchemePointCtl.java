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

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.geoServer.domain.LayerBean;
import com.xiangxun.atms.module.geoServer.domain.LayerEnum;
import com.xiangxun.atms.module.geoServer.service.IMapOperation;
import com.xiangxun.atms.module.land.service.LandBlockService;
import com.xiangxun.atms.module.land.service.LandSamplingSchemePointService;
import com.xiangxun.atms.module.land.service.LandSamplingSchemeService;
import com.xiangxun.atms.module.land.vo.LandSamplingScheme;
import com.xiangxun.atms.module.land.vo.LandSamplingSchemePoint;
import com.xiangxun.atms.module.land.vo.LandSamplingSchemePointSearch;


@Controller
@RequestMapping(value = "land/sampling/scheme/point")
@SessionAttributes(value = {"schemeInfo"})
public class LandSamplingSchemePointCtl extends BaseCtl<LandSamplingSchemePoint, LandSamplingSchemePointSearch> {

	@Resource
	LandSamplingSchemePointService landSamplingSchemePointService;
	@Resource
	LandSamplingSchemeService landSamplingSchemeService;
	
	@Resource
	LandBlockService landBlockService;

	@Resource
	IMapOperation iMapOperation;
	@Override
	protected BaseService<LandSamplingSchemePoint, LandSamplingSchemePointSearch> getBaseService() {
		return landSamplingSchemePointService;
	}
	
	@RequestMapping(value = "list/{menuid}/{schemeId}/")
	public String list(@PathVariable("menuid") String menuid, @PathVariable("schemeId") String schemeId
			, @RequestParam(value = "sortType", defaultValue = "CREATE_TIME DESC") String sortType
			, @RequestParam(value = "page", defaultValue = "0") int pageNumber
			, Model model, HttpServletRequest request) {
		this.initModel(schemeId, model);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("schemeId", schemeId);
		Page page = landSamplingSchemePointService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		model.addAttribute("menuid", menuid);
		return "land/scheme/point/list";
	}
	
	/**
	 * 将方案信息查出并放入session
	 * @param blockId
	 * @param model
	 */
	private void initModel(String schemeId, Model model) {
		LandSamplingScheme lss = landSamplingSchemeService.getById(schemeId);
		if (lss != null) {
			if(StringUtils.isNotEmpty(lss.getBlockId())){
				List<LayerBean>  layerList= iMapOperation.getByCode(LayerEnum.LAND, lss.getBlockId());
				if(layerList!=null && layerList.size() > 0){
					 for (LayerBean layerBean : layerList) {
				        	lss.setGeoJson(layerBean.getGeometry());
						}
				}
			}
			model.addAttribute("schemeInfo", lss);
		}
	}
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String schemeId, String page, Model model, HttpServletRequest request) {
		if (StringUtils.isNotEmpty(schemeId)) {
			this.initModel(schemeId, model);
		}
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "land/scheme/point/add";
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【采样点位】")
	public String doAdd(@PathVariable String menuid, LandSamplingSchemePoint info, HttpServletRequest fileRequest, RedirectAttributes attr) {
		//保存地块异常信息
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		landSamplingSchemePointService.save(info);
		
		attr.addFlashAttribute("message", "新增成功");
		return "redirect:/land/sampling/scheme/point/list/"+menuid+"/"+info.getSchemeId()+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		LandSamplingSchemePoint info = landSamplingSchemePointService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "land/scheme/point/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【采样点位】")
	public String doUpdate(@PathVariable("menuid") String menuid,
			@ModelAttribute("info")LandSamplingSchemePoint info, HttpServletRequest request, RedirectAttributes attr) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		landSamplingSchemePointService.updateByIdSelective(info);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/land/sampling/scheme/point/list/"+menuid+"/"+info.getSchemeId()+"/";
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【采样点位】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【采样点位】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                landSamplingSchemePointService.deleteById(string);
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
    	LandSamplingSchemePoint info = landSamplingSchemePointService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "land/scheme/point/view";
	}
    
    @ResponseBody
    @RequestMapping(value = "checkPoints/", method = RequestMethod.POST)
    @LogAspect(desc="核查【采样点位】")
    public ResponseEntity checkPoints(@RequestParam(value = "ids", required = true) String ids
    		, @RequestParam(value = "status", required = true) int status) {
    	ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【采样点位】核查。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
            	landSamplingSchemePointService.checkById(string, status);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "releasePoints/", method = RequestMethod.POST)
    @LogAspect(desc="发布【采样点位】")
    public ResponseEntity releasePoints(@RequestParam(value = "ids", required = true) String ids) {
    	ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【采样点位】发布。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
            	landSamplingSchemePointService.releaseById(string, 1);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
	
}
