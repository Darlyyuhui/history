package com.xiangxun.atms.module.statistics.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.base.web.ExportBaseCtl;
import com.xiangxun.atms.module.statistics.service.RegService;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * 采样数据统计查询
 * @author HX
 */
@Controller
@RequestMapping(value = "statistics/reg")
public class RegCtl extends ExportBaseCtl {

	@Resource
	RegService regService;
	@Resource
    Cache cache;

	@RequestMapping(value = "count/{menuid}/")
	public String count(@PathVariable String menuid, String regionId, String beginTime, String endTime, Model model, HttpServletRequest request) {
		Map<String, List<Dic>> dicMap = regService.getDicMap();
		//计算各个类型数据字典总数
		Map<String, Integer> dicSizeMap = new HashMap<String, Integer>();
		for (String k : dicMap.keySet()) {
			dicSizeMap.put(k, dicMap.get(k).size());
		}
		List<Map<String, Object>> list = regService.getList(regionId, beginTime, endTime, dicMap);
		model.addAttribute("dicMap", dicMap);
		model.addAttribute("dicSizeMap", dicSizeMap);
		model.addAttribute("countList", list);
		model.addAttribute("menuid", menuid);
		//查询参数
		model.addAttribute("regionId", regionId);
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		return "statistics/reg/count";
	}
	
	@RequestMapping(value = "doExport/", method = RequestMethod.POST)
	public void doExport(String regionId, String beginTime, String endTime, HttpServletRequest request, HttpServletResponse response) {
		Map<String, List<Dic>> dicMap = regService.getDicMap();
		List<Map<String, Object>> list = regService.getList(regionId, beginTime, endTime, dicMap);
		
		for (Map<String, Object> m : list) {
			if (m.get("REGION_ID") != null) {
				m.put("REGION_ID", super.getRegionNameById(m.get("REGION_ID").toString(), false, cache));
			}
		}
		
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("dataList", list);
		
		String fileName = "采样数据统计" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".xls";
		
		InputStream is = null;
		OutputStream os = null;
		try {
			response.reset();
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
		
			is = this.getTemplateFile("xls" + File.separator + "reg_count_export.xls");
		    os = response.getOutputStream();
		    
		    XLSTransformer transformer = new XLSTransformer();
		    Workbook workbook = transformer.transformXLS(is, beans);
		    
			workbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//释放资源
			try {
				if (os != null) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				os = null;
			}
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				is = null;
			}
		}
	}
	
}
