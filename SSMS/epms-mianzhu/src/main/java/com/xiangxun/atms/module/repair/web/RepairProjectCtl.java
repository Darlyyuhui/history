package com.xiangxun.atms.module.repair.web;

import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Table;
import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.cache.RepairStageCache;
import com.xiangxun.atms.module.bs.constant.AutoCode;
import com.xiangxun.atms.module.bs.vo.RepairStage;
import com.xiangxun.atms.module.geoServer.domain.LayerBean;
import com.xiangxun.atms.module.geoServer.domain.LayerEnum;
import com.xiangxun.atms.module.geoServer.service.IMapOperation;
import com.xiangxun.atms.module.land.cache.LandBlockCache;
import com.xiangxun.atms.module.repair.service.RepairProcessService;
import com.xiangxun.atms.module.repair.service.RepairProjectService;
import com.xiangxun.atms.module.repair.vo.RepairProcess;
import com.xiangxun.atms.module.repair.vo.RepairProcessSearch;
import com.xiangxun.atms.module.repair.vo.RepairProject;
import com.xiangxun.atms.module.repair.vo.RepairProjectSearch;

@Controller
@RequestMapping(value = "repair/project")
public class RepairProjectCtl extends BaseCtl<RepairProject, RepairProjectSearch> {
    
	@Resource
    RepairProjectService repairProjectService;
    @Resource
    Cache cache;
	@Resource
	IMapOperation iMapOperation;
	
	@Resource
	RepairProcessService repairProcessService;
	
	@Override
	protected BaseService<RepairProject, RepairProjectSearch> getBaseService() {
		return repairProjectService;
	}

	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid
			, @RequestParam(value = "sortType", defaultValue = "CREATE_TIME DESC") String sortType
			, @RequestParam(value = "page", defaultValue = "0") int pageNumber
			, Model model, HttpServletRequest request) {
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		
		Page page = repairProjectService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		RepairProject lr = null;
		for (int i = 0 ; i < page.getResult().size(); i++) {
			lr = (RepairProject)page.getResult().get(i);
			lr.setBlockNames(this.getLbMsg(lr.getBlockIds()));
		}
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		return "repair/project/list";
	}
	
	private String getLbMsg(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return "";
		}
		StringBuilder str = new StringBuilder();
		@SuppressWarnings("unchecked")
		Table<String, String, String> table = (Table<String, String, String>)cache.get(LandBlockCache.LB_ID_NAME);
		Map<String, String> cacheMap = table.column(LandBlockCache.LB_ID_NAME);
		String[] idArray  = ids.split(",");
		for (String lbId : idArray) {
			str.append(cacheMap.get(lbId));
			str.append(",");
		}
		return str.substring(0, str.length()-1);
	}
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String page, Model model, HttpServletRequest request) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "repair/project/add";
	}
	//添加是保存
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【土壤修复项目档案】")
	public String doAdd(@PathVariable String menuid, RepairProject info
			, RedirectAttributes attr, MultipartHttpServletRequest fileRequest) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCode(AutoCode.LAND_REPAIR_PROJECT);
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		repairProjectService.saveInfo(info, fileRequest);
		attr.addFlashAttribute("message", "新增成功");
		return "redirect:/repair/project/list/"+menuid+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		RepairProject info = repairProjectService.getById(id);
		
		if (StringUtils.isNotEmpty(info.getBlockIds())) {
			String[] blockIds=info.getBlockIds().split(",");
	   	    List<LayerBean> lands=new  ArrayList<LayerBean>();
	   	    String landIds="";
	     	if(blockIds.length>0){
	  		for(int i=0;i<blockIds.length;i++){
	  			List<LayerBean> lands_tmp=  iMapOperation.getByCode(LayerEnum.LAND,blockIds[i]); 
	  			lands.addAll(lands_tmp);
	           }
	    	}
	    	for (LayerBean layerBean : lands) {
			 landIds=landIds+layerBean.getGeometry()+"#";
			}
	    	info.setGeoJson(landIds);
		}
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "repair/project/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【土壤修复项目档案】")
	public String doUpdate(@PathVariable String menuid, String page, @ModelAttribute("info")RepairProject info
			, RedirectAttributes attr, MultipartHttpServletRequest fileRequest) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		repairProjectService.updateInfo(info, fileRequest);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/repair/project/list/"+menuid+"/?isgetsession=1&page="+page;
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【土壤修复项目档案】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【土壤修复项目档案】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                repairProjectService.deleteById(string);
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
		RepairProject info = repairProjectService.getById(id);
		if (StringUtils.isNotEmpty(info.getBlockIds())) {
			String[] blockIds = info.getBlockIds().split(",");
			List<LayerBean> lands = new ArrayList<LayerBean>();
			String landIds = "";
			if (blockIds.length > 0) {
				for (int i = 0; i < blockIds.length; i++) {
					List<LayerBean> lands_tmp = iMapOperation.getByCode(LayerEnum.LAND, blockIds[i]);
					lands.addAll(lands_tmp);
				}
			}

			for (LayerBean layerBean : lands) {
				landIds = landIds + layerBean.getGeometry() + "#";
			}
			info.setGeoJson(landIds);
		}

		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "repair/project/view";
	}
	
	/**
	 * 展示项目的修复过程
	 * @param proId	项目ID
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "showProcess/{proId}/")
	public String showProcess(@PathVariable String proId, String backType, String menuid, Model model) {
		List<RepairStage> topList = (List<RepairStage>)cache.get(RepairStageCache.TOP_LIST);
		model.addAttribute("topList", topList);
		
		Map<String, List<RepairStage>> map = (Map<String, List<RepairStage>>)cache.get(RepairStageCache.STAGE_MAP);
		if (topList.get(0) != null) {
			model.addAttribute("firstTopChild", map.get(topList.get(0).getId()));
		}
		model.addAttribute("proId", proId);
		model.addAttribute("backType", backType);
		model.addAttribute("menuid", menuid);
		return "repair/project/show_process";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "changeChild/{topId}/")//, method = RequestMethod.POST
	public String changeChild(@PathVariable String topId, Model model) {
		Map<String, List<RepairStage>> map = (Map<String, List<RepairStage>>)cache.get(RepairStageCache.STAGE_MAP);
		model.addAttribute("childState", map.get(topId));
		return "repair/project/child_stage";
	}
	
	@RequestMapping(value = "processDetail/{proId}/{stageId}/")
	public String processDetail(@PathVariable("proId") String proId
			, @PathVariable("stageId") String stageId, Model model) {
		RepairProcessSearch search = new RepairProcessSearch();
		search.createCriteria().andProIdEqualTo(proId).andStageIdEqualTo(stageId);
		search.setOrderByClause("WORK_DATE DESC");
		List<RepairProcess> list = repairProcessService.selectByExample(search);
		
		RepairProcess rp = null;
		if (list != null && list.size() > 0) {
			rp = list.get(0);
		} else {
			rp = new RepairProcess();
		}
		model.addAttribute("info", rp);
		return "repair/project/process_detail";
	}
	
}