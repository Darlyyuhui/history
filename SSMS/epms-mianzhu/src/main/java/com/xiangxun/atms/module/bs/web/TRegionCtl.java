package com.xiangxun.atms.module.bs.web;

import java.util.HashMap;
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

import com.google.common.collect.Table;
import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.cache.TRegionCache;
import com.xiangxun.atms.module.bs.service.TRegionService;
import com.xiangxun.atms.module.bs.vo.Region;
import com.xiangxun.atms.module.bs.vo.RegionSearch;

@Controller
@RequestMapping(value = "bs/region")
public class TRegionCtl extends BaseCtl<Region, RegionSearch> {
	
    @Resource
    TRegionService regionService;
    @Resource
    Cache cache;

	@Override
	protected BaseService<Region, RegionSearch> getBaseService() {
		return regionService;
	}

	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid, Model model) {
		model.addAttribute("treeData", regionService.makeTreeData());
		return "bs/region/list";
	}
	
	@RequestMapping(value = "add/{menuid}/{pid}/")
	public String add(@PathVariable("menuid") String menuid
			, @PathVariable("pid") String pid, Model model, HttpServletRequest request, RedirectAttributes attr) {
		
		if (StringUtils.isEmpty(pid)) {
			attr.addFlashAttribute("message", "未选择上级节点。");
			return "redirect:/bs/region/list/"+menuid+"/";
		}
		Region r = regionService.getById(pid);
		if (r == null) {
			attr.addFlashAttribute("message", "上级节点信息获取错误。");
			return "redirect:/bs/region/list/"+menuid+"/";
		}
		
		model.addAttribute("pid", pid);
		model.addAttribute("pname", r.getName());
		model.addAttribute("menuid", menuid);
		return "bs/region/add";
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【行政区域】")
	public String doAdd(@PathVariable String menuid, Region region, String isContinue, RedirectAttributes attr) {
		region.setId(UuidGenerateUtil.getUUIDLong());
		regionService.save(region);
		attr.addFlashAttribute("message", "新增成功");
		if ("1".equals(isContinue)) {
			return "redirect:/bs/region/add/"+menuid+"/"+region.getPid()+"/";
		}
		return "redirect:/bs/region/list/"+menuid+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, Model model) {
		Region r = regionService.getById(id);
		Region pr = regionService.getById(r.getPid());
		model.addAttribute("pname", pr.getName());
		model.addAttribute("info", r);
		model.addAttribute("menuid", menuid);
		return "bs/region/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【行政区域】")
	public String doUpdate(@PathVariable String menuid, @ModelAttribute("info")Region info, RedirectAttributes attr) {
		regionService.updateByIdSelective(info);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/bs/region/list/"+menuid+"/";
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【行政区域】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【行政区域】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
            	if ("0".equals(string)) {
            		continue;
            	}
                regionService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
    
    @RequestMapping(value = "showRegion/",method = RequestMethod.GET)
    public String showRegion(String idElement, String nameElement, String isCallback, Model model) {
    	model.addAttribute("treeData", regionService.makeTreeData());
    	model.addAttribute("idElement", idElement);
    	model.addAttribute("nameElement", nameElement);
    	model.addAttribute("isCallback", isCallback==null?"0":isCallback);
		return "bs/region/show_region";
    }

    @ResponseBody
    @RequestMapping(value = "getLocation/{regionId}/")
    public Map<String, Object> getLocation(@PathVariable String regionId) {
    	@SuppressWarnings("unchecked")
		Table<String, String, String> table = (Table<String, String, String>)cache.get(TRegionCache.ID_LOCATION);
    	if (table != null) {
    		Map<String, String> map = table.column(TRegionCache.ID_LOCATION);
    		if (map != null) {
    			String location = map.get(regionId);
    			if (StringUtils.isNotEmpty(location)) {
    				String[] ss = location.split(",");
    				Map<String, Object> m = new HashMap<String, Object>();
        			m.put("longitude", ss[0]);
        			m.put("latitude", ss[1]);
        			return m;
    			}
    		}
    	}
    	return null;
    }
    
}