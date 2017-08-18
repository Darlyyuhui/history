package com.xiangxun.atms.module.statistics.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Table;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.bs.cache.TRegionCache;
import com.xiangxun.atms.module.statistics.service.LandService;
import com.xiangxun.atms.module.statistics.vo.LandLine;
import com.xiangxun.atms.module.statistics.vo.LandPie;
import com.xiangxun.atms.module.util.EchartData;
import com.xiangxun.atms.module.util.FtlJsonUtil;

@Controller
@RequestMapping(value = "statistics/land")
public class LandCtl extends BaseCtl {

	@Resource
	LandService landService;
	@Resource
    Cache cache;
	
	@RequestMapping(value = "query/{menuid}/")
	public String query(@PathVariable String menuid, String regionId, String beginTime, String endTime
			, HttpServletRequest requet, Model model) {
		//饼图数据
		List<LandPie> pieList = null;
		if (StringUtils.isEmpty(regionId)) {
			pieList = landService.getPieDataAll(beginTime, endTime);
		} else {
			pieList = landService.getPieData(regionId, beginTime, endTime);
		}
		//柱状图数据
		List<LandLine> lineList = landService.getLineData(regionId, beginTime, endTime);
		
		String title1 = "";
		String title2 = "";
		if (StringUtils.isEmpty(regionId)) {
			title1 = "全市";
			title2 = "各乡镇";
		} else{
			title1 = this.getRegionNameByCache(regionId);
			title2 = this.getRegionNameByCache(regionId);
		}
		model.addAttribute("title1", title1);
		model.addAttribute("title2", title2);
		model.addAttribute("pieOpt", this.makePieJson(regionId, pieList));
		model.addAttribute("lineOpt", this.makeLineJson(regionId, lineList));
		
		model.addAttribute("menuid", menuid);
		model.addAttribute("regionId", regionId);
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		
		return "statistics/land/chart";
	}
	
	/**
	 * 生成饼图json
	 * @param regionId
	 * @param pieList
	 * @return
	 */
	private String makePieJson(String regionId, List<LandPie> pieList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datas", pieList);
		
		FtlJsonUtil t = new FtlJsonUtil("land", "pie.ftl");
		return t.process(map);
	}
	
	/**
	 * 生成柱状图json
	 * @param regionId
	 * @param lineList
	 * @return
	 */
	private String makeLineJson(String regionId, List<LandLine> lineList) {
		String title = "土壤污染因子分析";
		if (StringUtils.isEmpty(regionId)) {
			title = "各乡镇" + title;
		} else{
			title = this.getRegionNameByCache(regionId) + title;
		}
		
		List<EchartData> list = new ArrayList<EchartData>();
		
		EchartData ed = null;
		List<Object> phData = new ArrayList<Object>();
		List<Object> cdData = new ArrayList<Object>();
		List<Object> acdData = new ArrayList<Object>();
		
		List<Object> regions = new ArrayList<Object>();
		for (LandLine ll : lineList) {
			regions.add(ll.getRegionName());
			phData.add(ll.getPh());
			cdData.add(ll.getCd());
			acdData.add(ll.getAcd());
		}
		
		List<Object> names = new ArrayList<Object>();
		names.add("PH值");
		names.add("镉");
		names.add("有效态镉");
		
		ed = new EchartData("PH值");
		ed.setDatas(phData);
		list.add(ed);
		ed = new EchartData("镉");
		ed.setDatas(cdData);
		ed.setyAxisIndex("1");
		list.add(ed);
		ed = new EchartData("有效态镉");
		ed.setDatas(acdData);
		ed.setyAxisIndex("1");
		list.add(ed);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("datas", list);
		map.put("names", names);
		map.put("regions", regions);
		
		FtlJsonUtil t = new FtlJsonUtil("land", "line.ftl");
		return t.process(map);
	}
	
	@SuppressWarnings("unchecked")
	private String getRegionNameByCache(String regionId) {
		Table<String, String, String> regionTable = (Table<String, String, String>)cache.get(TRegionCache.ID_NAME);
		Map<String, String> regionMap = regionTable.column(TRegionCache.ID_NAME);
		return regionMap.get(regionId);
	}
	
}
