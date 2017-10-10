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

import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.base.web.ExportBaseCtl;
import com.xiangxun.atms.module.statistics.service.AnalysisService;
import com.xiangxun.atms.module.statistics.vo.AnalysisInfo;

import net.sf.jxls.transformer.XLSTransformer;

@Controller
@RequestMapping(value = "statistics/analysis")
public class AnalysisCtl extends ExportBaseCtl {

	@Resource
	AnalysisService analysisService;
	@Resource
    Cache cache;
	
	@RequestMapping(value = "landCount/{menuid}/")
	public String count(@PathVariable String menuid, String regionId, String beginTime, String endTime, Model model, HttpServletRequest request) {
		List<AnalysisInfo> list = analysisService.getLandList(regionId, beginTime, endTime);
		model.addAttribute("countList", list);
		model.addAttribute("menuid", menuid);
		model.addAttribute("regionId", regionId);
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		return "statistics/analysis/land_count";
	}
	
	@RequestMapping(value = "doLandExport/", method = RequestMethod.POST)
	public void doLandExport(String regionId, String beginTime, String endTime, HttpServletRequest request, HttpServletResponse response) {
		List<AnalysisInfo> list = analysisService.getLandList(regionId, beginTime, endTime);
		
		for (AnalysisInfo ai : list) {
			ai.setRegionId(super.getRegionNameById(ai.getRegionId(), false, cache));
		}
		
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("dataList", list);
		
		String fileName = "土壤分析数据统计" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".xls";
		
		InputStream is = null;
		OutputStream os = null;
		try {
			response.reset();
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
		
			is = this.getTemplateFile("xls" + File.separator + "land_analysis_count_export.xls");
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
