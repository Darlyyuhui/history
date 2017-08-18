package com.xiangxun.atms.module.apb.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.module.apb.service.ApbHomeService;
import com.xiangxun.atms.module.util.EchartData;
import com.xiangxun.atms.module.util.EchartPieData;
import com.xiangxun.atms.module.util.FtlJsonUtil;

@Controller
@RequestMapping(value = "apb/home")
public class ApbHomeCtl extends BaseCtl {

	@Resource
	ApbHomeService apbHomeService;
	
	@RequestMapping(value = "index/")
	public String index(HttpServletRequest request, Model model) {
		model.addAttribute("topMap", apbHomeService.getTopCount());
		model.addAttribute("newList", apbHomeService.getApbProductList(5));
		model.addAttribute("lineChart", this.getLineChart());
		model.addAttribute("pieChart", this.getPieChart());
		return "home/apb_main";
	}
	
	private String getLineChart() {
		List<Map<String, Object>> list = apbHomeService.getApbInfoAreaCount();
		
		List<String> names = new ArrayList<String>();
		List<EchartData> datas = new ArrayList<EchartData>();
		
		Map<String, List<Object>> dataMap = new TreeMap<String, List<Object>>();
		for (Map<String, Object> map : list) {
			names.add(map.get("NAME").toString());
			this.putMap("面积（亩）", map.get("AREA"), dataMap);
			this.putMap("年产量（万吨）", map.get("ANNUAL_OUTPUT"), dataMap);
		}
		EchartData ed = null;
		for (String k : dataMap.keySet()) {
			ed = new EchartData(k);
			ed.setDatas(dataMap.get(k));
			datas.add(ed);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("names", names);
		map.put("datas", datas);
		
		FtlJsonUtil t = new FtlJsonUtil("apb", "line.ftl");
		return t.process(map);
	}
	
	private void putMap(String key, Object value, Map<String, List<Object>> map) {
		List<Object> list = map.get(key);
		if (list == null) {
			list = new ArrayList<Object>();
		}
		list.add(value);
		map.put(key, list);
	}
	
	private String getPieChart() {
		List<Map<String, Object>> list = apbHomeService.getProductTypeCount();
		
		List<EchartPieData> datas = new ArrayList<EchartPieData>();
		for (Map<String, Object> map : list) {
			if (map.get("NAME_") == null) {
				continue;
			}
			datas.add(new EchartPieData(map.get("NAME_").toString(), map.get("NUM_")));
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datas", datas);
		
		FtlJsonUtil t = new FtlJsonUtil("apb", "pie.ftl");
		return t.process(map);
	}
	
}
