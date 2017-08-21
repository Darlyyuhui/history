package com.xiangxun.atms.module.sergrade.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.compnents.fusioncharts.SingleStackedColumn3DChart;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.IDDStorage;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.sergrade.domain.SergradeCountReport;
import com.xiangxun.atms.module.sergrade.service.SergradeCountService;
/***
 * 服务商下责任资产数量统计分析FLASH图表处理
 * @author guikaiping
 */
@Controller
@RequestMapping("sergrade/count/chart")
public class SergradeCountChartCtl extends BaseCtl{

	@Resource
	SergradeCountService sergradeCountService;
	
	//FLASH图标颜色数组
	private String color[] = { "FFCC33", "51DCFF","5F96FC", "8BBA00", "FF8E46",
			"008E8E", "D64646", "8E468E", "588526", "B3AA00", "008ED6",
			"9D080D", "A186BE", "346DB2", "C13A36", "A02F2B", "7C9D35",
			"6A4A8D", "2B8CAC", "D2721E", "7792C1", "BF7B7A", "A6BC7C",
			"9183A7" };
	
	/***
	 * FLASH图表显示
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@LogAspect(desc="FLASH图表 服务商下责任资产数量统计信息")
	@RequestMapping(value = "chart/{menuid}/", method = RequestMethod.POST)
	public String chart(@PathVariable String menuid, ModelMap model, @RequestParam(value="screenWidth") String screenWidth, HttpServletRequest request){
		return columnChart(menuid, model, screenWidth, request);
	}
	
	//===================生成 时间段 违法车次 环比FLASH 动态图标 ==========================================
	public String columnChart(String menuid, ModelMap model, String screenWidth, HttpServletRequest request) {
		//获得页面传参
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//获取数据
		List<SergradeCountReport> list = sergradeCountService.getSerChartReport(searchParams, menuid);
		
		//初始化数据
    	if(null != list && list.size()>0){
    		SingleStackedColumn3DChart chart = this.getStackedColumn3DChart(" 服务商下责任资产数量统计分析图", "", "服务商名称", "运维设备数(台)", 
 					"0", "0", "3", "", "", "0",(int)Double.parseDouble(screenWidth));
    		this.createDataXml(list,request);
    		String chartHeadStr = chart.createRootChart();
    		request.setAttribute("chartHeadStr", chartHeadStr);
    		request.setAttribute("chart", chart);
		}else {
			request.setAttribute("nodata", "nodata");
		}
    	
		return "sergrade/count/form/chart/stackedFlash";
	}
	
	//===================生成FLASH 动态图标的数据集转换为XML ==========================================
	private void createDataXml(List<SergradeCountReport> list,ServletRequest request) {
		//以下xml为生成局部的数据集合所用
		StringBuffer factoryNameXml = new StringBuffer("");
		StringBuffer deviceStackedXml = new StringBuffer("");
		//StringBuffer videoStackedXml = new StringBuffer("");
		StringBuffer serverStackedXml = new StringBuffer("");
		StringBuffer databaseStackedXml = new StringBuffer("");
		StringBuffer ftpStackedXml = new StringBuffer("");
		StringBuffer projectStackedXml = new StringBuffer("");
		StringBuffer cabinetStackedXml = new StringBuffer("");
		//数据封装
		if (list != null && list.size() > 0) {
			//封装头部
			factoryNameXml.append("<categories>");
			deviceStackedXml.append("<dataset seriesName='卡口' color='"+color[0 % 10]+"' >");
			//videoStackedXml.append("<dataset seriesName='监控' color='"+color[1 % 10]+"' >");
			serverStackedXml.append("<dataset seriesName='服务器' color='"+color[2 % 10]+"' >");
			databaseStackedXml.append("<dataset seriesName='数据库' color='"+color[3 % 10]+"' >");
			ftpStackedXml.append("<dataset seriesName='FTP' color='"+color[4 % 10]+"' >");
			projectStackedXml.append("<dataset seriesName='平台' color='"+color[5 % 10]+"' >");
			cabinetStackedXml.append("<dataset seriesName='机柜' color='"+color[6 % 10]+"' >");
			//迭代内部数据
			for (int i = 0; i < list.size(); i++) {
				//计算对应的数
				SergradeCountReport allVo = list.get(i);
				int sum = Integer.valueOf(allVo.getDeviceCounts().add(allVo.getServerCounts()).add(allVo.getDatabaseCounts()).add(allVo.getFtpCounts()).add(allVo.getProjectCounts()).add(allVo.getCabinetCounts()).toString());
				if(sum > 0){
					factoryNameXml.append("<category name='"+allVo.getFactoryName()+"' />");
					deviceStackedXml.append("<set value='"+allVo.getDeviceCounts()+"' />");
					//videoStackedXml.append("<set value='"+allVo.getVideoCounts()+"' />");
					serverStackedXml.append("<set value='"+allVo.getServerCounts()+"' />");
					databaseStackedXml.append("<set value='"+allVo.getDatabaseCounts()+"' />");
					ftpStackedXml.append("<set value='"+allVo.getFtpCounts()+"' />");
					projectStackedXml.append("<set value='"+allVo.getProjectCounts()+"' />");
					cabinetStackedXml.append("<set value='"+allVo.getCabinetCounts()+"' />");
				}

			}
		}
		//柱子粗细调整，默认按照30根显示 2根一组，判断值为15
		if(list.size()<15){
			int temp = 15 - list.size();
			for(int j=0;j<temp;j++){
				factoryNameXml.append("<category name='     '/>");
			}
		}
		//封装尾部
		factoryNameXml.append("</categories>");
		request.setAttribute("factoryNameXml", factoryNameXml);
		deviceStackedXml.append("</dataset>");
		request.setAttribute("deviceStackedXml", deviceStackedXml);
		
		//videoStackedXml.append("</dataset>");
		//request.setAttribute("videoStackedXml", videoStackedXml);
		
		serverStackedXml.append("</dataset>");
		request.setAttribute("serverStackedXml", serverStackedXml);
		
		databaseStackedXml.append("</dataset>");
		request.setAttribute("databaseStackedXml", databaseStackedXml);
		
		ftpStackedXml.append("</dataset>");
		request.setAttribute("ftpStackedXml", ftpStackedXml);
		
		projectStackedXml.append("</dataset>");
		request.setAttribute("projectStackedXml", projectStackedXml);
		
		cabinetStackedXml.append("</dataset>");
		request.setAttribute("cabinetStackedXml", cabinetStackedXml);
		
	}
	
	//===================堆栈图公共 属性赋值方法======================
	private SingleStackedColumn3DChart getStackedColumn3DChart(String caption,String subCaption,String xname,String yname,String decimalPrecision,
			String rotateNames,String numdivlines,String numberPrefix,String numberSuffix,String formatNumberScale,Integer width){
		SingleStackedColumn3DChart chart = new SingleStackedColumn3DChart();
		if(width!=null && width > 600){
			chart.setWide(width-300);
		}else{
			chart.setWide(IDDStorage.flashChart_Wide);
		}
		chart.setHigh(IDDStorage.flahsChart_High);
		chart.setBgColor(IDDStorage.flahsChart_BgColor);
		chart.setCaption(caption);
		chart.setSubcaption(subCaption);
		//chart.setXAxisName(xname);
		chart.setYAxisName(yname);
		chart.setDecimalPrecision(decimalPrecision);
		chart.setRotateNames(rotateNames);
		chart.setNumdivlines(numdivlines);
		chart.setNumberPrefix(numberPrefix);
		chart.setNumberSuffix(numberSuffix);
		chart.setFormatNumberScale(formatNumberScale);
		chart.setBaseFontSize("12");
		chart.setAnimation("1");
		return chart;
	}
	
}
