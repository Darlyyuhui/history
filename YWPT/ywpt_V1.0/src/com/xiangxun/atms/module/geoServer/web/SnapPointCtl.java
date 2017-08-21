package com.xiangxun.atms.module.geoServer.web;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangxun.atms.module.geoServer.domain.SnapPoint;
import com.xiangxun.atms.module.geoServer.mapper.SnapPointMapper;


@Controller
@RequestMapping("openmap/snappoint")
public class SnapPointCtl {
	@Resource 
	SnapPointMapper snapPointMapper; 
	
	/**
	 * 获取所有的路中线要素
	 * @return
	 */
	@RequestMapping(value="getroad")
	@ResponseBody
	public List<String> getRoadGeometrys(){
		return snapPointMapper.getRoadGeometrys();
	}
	
	@RequestMapping(value="snap")
	@ResponseBody
	public List<SnapPoint> searchSnapPoints(String geometryText,String distance){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("geometryText", geometryText);
		map.put("distance", Double.valueOf(distance));
		return snapPointMapper.searchSnapPoints(map);
    }
	
	@RequestMapping(value="intersect")
	@ResponseBody
	public List<SnapPoint> searchIntersectGeometryList(String geometryText){
		return snapPointMapper.searchIntersectGeometryList(geometryText);
    }
	
	@RequestMapping(value="project")
	@ResponseBody
	public SnapPoint getProjectPointFromLine(String geometryText){
		String linewkt=snapPointMapper.getMinDistanceLine(geometryText).getGeometryText();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pointText", geometryText);
		map.put("lineText", linewkt);
		
		SnapPoint sp=snapPointMapper.queryClosedPoint(map);
		sp.setLineText(linewkt);
		return sp;
	}
	
	@RequestMapping(value="projects")
	@ResponseBody
	public SnapPoint[] getProjectPointFromLines(String geometryTextStr){
		String[] geometryTexts = geometryTextStr.split(";");
		if(null == geometryTexts || geometryTexts.length < 1)return null;
		int len=geometryTexts.length;
		SnapPoint[] result = new SnapPoint[len];
		for(int i=0; i<len; i++) {
			String geometryText = geometryTexts[i];
			String linewkt=snapPointMapper.getMinDistanceLine(geometryText).getGeometryText();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pointText", geometryText);
			map.put("lineText", linewkt);
			result[i] = snapPointMapper.queryClosedPoint(map);
		}
		return result;
	}
	
}
