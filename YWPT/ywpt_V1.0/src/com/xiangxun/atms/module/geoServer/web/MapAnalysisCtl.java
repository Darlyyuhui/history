package com.xiangxun.atms.module.geoServer.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangxun.atms.module.geoServer.service.SpatialOperationService;
import com.xiangxun.atms.module.gis.domain.LayerEnum;

@Controller
@RequestMapping("openmap/analysis")
public class MapAnalysisCtl {
	@Resource
	SpatialOperationService spatialOperationService;
	
	
	/**
	 * 路径分析操作
	 * @param analaysisUrl 分析请求的服务路径
	 * @param stops 格式为'POINT() | POINT()' 如果为空则传入""
	 * @param barriers 障碍点 格式为'POINT() | POINT()' 如果为空则传入""
	 * @param layerName 线状图层 在此图层中进行路径分析
	 * */
	@RequestMapping(value = "routeTask")
	@ResponseBody
	public String routeTask(String analysisUrl, String stops, String barriers, String layer, HttpServletRequest request, HttpServletResponse response) {
		String method = "getClosedRoute";
		return  spatialOperationService.routeTask(analysisUrl, stops, method, barriers, layer);
	}

	/**
	 * 缓冲区分析操作
	 * @param analysisUrl 分析操作的服务路径
	 * @param geoms 几何体
	 * @method 调用的方法
	 * @distance 距离
	 * */	
	@RequestMapping(value = "bufferTask")
	@ResponseBody
	public String gpServer(String analysisUrl, String geoms, String distance, HttpServletRequest request, HttpServletResponse response) {
		String method = "getBuffers";//待定
		return spatialOperationService.bufferTask(analysisUrl, geoms, method, distance);
	}
	
	/**
	 * 捕捉 默认容差为0.001
	 * @param geo捕捉点的wkt表示
	 * @param layer 要捕捉的的线图层
	 * @param tolerance 容差
	 * @return 结果点的wkt表示
	 * */
	@RequestMapping(value="snap")
	@ResponseBody
	public String snap(@RequestParam("geo") String geo, @RequestParam("layers") String layerName, @RequestParam("tolerance") double tolerance) {		
		if (tolerance == 0) {
			tolerance = 0.001;
		}
		LayerEnum layer = LayerEnum.valueOf(layerName.toUpperCase());
		
		return spatialOperationService.snap(geo, layer, tolerance);
	}
}
