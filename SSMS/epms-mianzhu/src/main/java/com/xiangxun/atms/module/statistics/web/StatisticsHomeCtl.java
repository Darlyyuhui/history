package com.xiangxun.atms.module.statistics.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.statistics.service.LandService;
import com.xiangxun.atms.module.statistics.vo.LandACd;
import com.xiangxun.atms.module.statistics.vo.LandCd;
import com.xiangxun.atms.module.statistics.vo.LandLine;
import com.xiangxun.atms.module.statistics.vo.LandPh;
import com.xiangxun.atms.module.statistics.vo.LandPie;
import com.xiangxun.atms.module.util.EchartData;
import com.xiangxun.atms.module.util.FtlJsonUtil;

@Controller
@RequestMapping(value = "statistics/home")
public class StatisticsHomeCtl extends BaseCtl {

	@Resource
	LandService landService;
	@Resource
    Cache cache;
	
	@RequestMapping(value = "index")
	public String query(String regionId, String beginTime, String endTime
			, HttpServletRequest requet, Model model) {
		//饼图数据
		List<LandPie> pieList = landService.getPieDataAll(beginTime, endTime);
		//柱状图数据
		List<LandLine> lineList = landService.getLineData(regionId, beginTime, endTime);
		
		LandPh ph = landService.getPhData(regionId, beginTime, endTime);
		LandCd cd = landService.getCdData(regionId, beginTime, endTime);
		LandACd acd = landService.getACdData(regionId, beginTime, endTime);
		
		model.addAttribute("pieOpt", this.makePieJson(pieList));
		model.addAttribute("lineOpt", this.makeLineJson(lineList));
		
		model.addAttribute("phOpt", this.makePhLineJson(ph));
		model.addAttribute("cdOpt", this.makeCdLineJson(cd));
		model.addAttribute("acdOpt", this.makeACdLineJson(acd));
		
		return "home/statistics_main";
	}
	
	/**
	 * 生成饼图json
	 * @param regionId
	 * @param pieList
	 * @return
	 */
	private String makePieJson(List<LandPie> pieList) {
		//获得顺序
		List<String[]> names = landService.getStandMByDicTypeCode("002");
		List<String> namesList = new ArrayList<String>();
		for (String[] strs : names) {
			namesList.add(strs[1]);
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("datas", pieList);
		dataMap.put("names", namesList);
		
		FtlJsonUtil t = new FtlJsonUtil("land", "pie.ftl");
		return t.process(dataMap);
	}
	
	/**
	 * 生成柱状图json
	 * @param regionId
	 * @param lineList
	 * @return
	 */
	private String makeLineJson(List<LandLine> lineList) {
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
		map.put("datas", list);
		map.put("names", names);
		map.put("regions", regions);
		
		FtlJsonUtil t = new FtlJsonUtil("land", "line.ftl");
		return t.process(map);
	}
	
	private String makePhLineJson(LandPh ph) {
		List<String[]> names = landService.getStandMByDicTypeCode("001"); 
		List<String> xNames = new ArrayList<String>();
		
		List<String> datas1 = new ArrayList<String>();
		List<String> datas2 = new ArrayList<String>();
		int i = 1;
		long total = ph.getPhTotal();
		long num;
		for (String[] strs : names) {
			xNames.add(strs[0] + "\\n" + strs[1]);
			num = ph.getValByNum(i);
			datas1.add(num+"");
			datas2.add(this.makePerc(total, num));
			i++;
		}
		return this.makeJson(xNames, datas1, datas2);
	}
	
	private String makeCdLineJson(LandCd cd) {
		List<String[]> names = landService.getStandMByDicTypeCode("002"); 
		List<String> xNames = new ArrayList<String>();
		
		List<String> datas1 = new ArrayList<String>();
		List<String> datas2 = new ArrayList<String>();
		int i = 1;
		long total = cd.getCdTotal();
		long num;
		for (String[] strs : names) {
			xNames.add(strs[1]);
			num = cd.getValByNum(i);
			datas1.add(num+"");
			datas2.add(this.makePerc(total, num));
			i++;
		}
		return this.makeJson(xNames, datas1, datas2);
	}
	
	private String makeACdLineJson(LandACd acd) {
		List<String[]> names = landService.getStandMByDicTypeCode("003"); 
		List<String> xNames = new ArrayList<String>();
		
		List<String> datas1 = new ArrayList<String>();
		List<String> datas2 = new ArrayList<String>();
		int i = 1;
		long total = acd.getAcdTotal();
		long num;
		for (String[] strs : names) {
			xNames.add(strs[0]);
			num = acd.getValByNum(i);
			datas1.add(num+"");
			datas2.add(this.makePerc(total, num));
			i++;
		}
		return this.makeJson(xNames, datas1, datas2);
	}
	
	/**
	 * 计算占比
	 * @param total
	 * @param num
	 * @return
	 */
	private String makePerc(Long total, Long num) {
		if (total == null || total == 0L) {
			return "0";
		}
		DecimalFormat df = new DecimalFormat("#0.00");
		double n = (double)num/total*100;
		return df.format(n);
	}
	
	/**
	 * 计算占比
	 * @param total
	 * @param num
	 * @return
	 */
	private String makeJson(List<String> xNames, List<String> datas1, List<String> datas2) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xNames", xNames);
		map.put("datas1", datas1);
		map.put("datas2", datas2);
		
		FtlJsonUtil t = new FtlJsonUtil("land", "twoYLine.ftl");
		return t.process(map);
	}
	
}
