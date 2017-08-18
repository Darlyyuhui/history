package com.xiangxun.atms.module.geoServer.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.module.geoServer.domain.Geometry;
import com.xiangxun.atms.module.geoServer.domain.Graphic;
import com.xiangxun.atms.module.geoServer.domain.LayerBean;
import com.xiangxun.atms.module.geoServer.domain.LayerEnum;
import com.xiangxun.atms.module.geoServer.domain.SnapPoint;
import com.xiangxun.atms.module.geoServer.mapper.LayerBeanMapper;
import com.xiangxun.atms.module.geoServer.mapper.SnapPointMapper;
import com.xiangxun.atms.module.geoServer.service.IMapOperation;
import com.xiangxun.atms.module.geoServer.service.SpatialOperationService;
import com.xiangxun.atms.module.geoServer.util.GeoUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("openmap/query")
public class MapQueryCtl {	
	@Resource(name="geoLayerOperationImpl")
	IMapOperation geoLayerOperation;
	
	@Resource 
	SnapPointMapper snapPointMapper;
	
	@Resource
	SpatialOperationService spatialOperationService;
	
	@Resource 
	LayerBeanMapper layerBeanMapper;


    /**
     * 获取兴趣点数据的类型
     * */
	@RequestMapping("poitype")
	@ResponseBody
	@LogAspect(desc="获取兴趣点类型")
	public Properties getPoiType() {
		String poiSouce = "/com/xiangxun/atms/module/geoServer/config/poi.properties";
		Properties properties = new Properties();
		try {
			InputStream inputStream = getClass().getResourceAsStream(poiSouce);
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	/**
	 * 查询兴趣点
	 * */
	@RequestMapping("poisearch")
	@ResponseBody
	@LogAspect(desc="查询兴趣点")
	public List<LayerBean> getPoiList(@RequestParam(value = "name") String name, @RequestParam(value = "type") String type) {
		
		if (type.equals(null) || type.equals("")) return null;
		
		List<LayerBean> list;
		LayerEnum tableName = LayerEnum.valueOf(type.toUpperCase());
		
		if (name.equals(null) || name.equals("")) {
			list = geoLayerOperation.getAll(tableName);
		} else {
			list = geoLayerOperation.getByName(tableName, name);
		}
		
		return list;
	}
	
	
	/**
	 * identify 以几何图形查询兴趣点
	 * @param geometryText 表示查询范围几何体的字符串
	 * */
	@RequestMapping("identify")
	@ResponseBody
	@LogAspect(desc="几何图形查询")
	public List<LayerBean> identify(String geometryText) {
		Properties properties = getPoiType();
		Set<Object> propNames = properties.keySet();
		List<LayerEnum> list = new ArrayList<LayerEnum>();
		
		for (Object object : propNames) {
			String name = (String) object;
			list.add(LayerEnum.valueOf(name.toUpperCase()));
		}
		
		List<LayerBean> results = spatialOperationService.identify(geometryText, list);
		
		return results;		
	}

	/**
	 * 缓冲区分析服务
	 * @param geometries String 几何体数组,JSON字符串
	 * @param distance String 缓冲距离，单位：米
	 * @return resGeometryList List 分析后JSON字符串列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="buffer/",method=RequestMethod.POST)
	@LogAspect(desc="使用地理缓冲区分析服务")
	@ResponseBody public List<Geometry> bufferAnalysis(
		@RequestParam String url,
		@RequestParam String geometries,
		@RequestParam String distance
	){
		if("".equals(geometries) || "".equals(distance)){
			return null;
		}
		ArrayList<Geometry> resGeometryList = new ArrayList<Geometry>();
		JSONArray geoJSONArr = JSONArray.fromObject(geometries);
		List<Geometry> geoList = (List<Geometry>)JSONArray.toCollection(geoJSONArr, Geometry.class);
		Iterator iter = geoList.iterator();
		String geoms = "";
		int i=0;

		while(iter.hasNext()){
			Geometry geo = (Geometry)iter.next();
			if(i!=0){
				geoms += "|";
			}
			geoms += GeoUtil.convertGeoClassToWKT(geo);
			i++;
		}

		String bufferRes = spatialOperationService.bufferTask(url,geoms,"getBuffers",distance);
		if(bufferRes.contains("xml")){
			return null;
		}
		String[] resGeoArr = bufferRes.split("\\|");
		for(int j=0,resLen=resGeoArr.length;j<resLen;j++){
			resGeometryList.add(GeoUtil.convertWKTToGeoClass(resGeoArr[j]));
		}

		return resGeometryList;
	}

	/**
	 * 路径分析
	 * @param url String 传入路径分析服务url
	 * @param startPoint String 起始点
	 * @param endPoint String 结束点
	 * @param stopPoints String 停靠点JSON数组
	 * @param barriers String 障碍点JSON数组
	 * @return resList List<Graphic> 返回处理结果列表
	 */
	@RequestMapping(value="route/",method=RequestMethod.POST)
	@LogAspect(desc="使用路径分析服务")
	@ResponseBody public List<Graphic> routeAnalysis(
		@RequestParam String url,
		@RequestParam String startPoint,
		@RequestParam String endPoint,
		@RequestParam String stopPoints,
		@RequestParam String barriers
	){
		if("".equals(url) || "".equals(startPoint) || "".equals(endPoint)){
			return null;
		}
		ArrayList<Graphic> resList = new ArrayList<Graphic>();
		Geometry start = (Geometry)JSONObject.toBean(JSONObject.fromObject(startPoint),Geometry.class);
		Geometry end = (Geometry)JSONObject.toBean(JSONObject.fromObject(endPoint),Geometry.class);

		String startWkt = GeoUtil.convertGeoClassToWKT(start);
		String endWkt = GeoUtil.convertGeoClassToWKT(end);

		//////////////////////////////////////////////////////////////////////////////
		//xiongjie 修改
		//不能用spatialOperationService.snap(stopWkt,LayerEnum.ROAD_LINE,0.001);
		//靠近点到线上，设置的容差小的时候返回null程序会报错
		//使用getProjectPointsFromLine方法进行统一转换
		//////////////////////////////////////////////////////////////////////////////
		String stopStr = "|";
		String barriersStr = "";
		if(!"".equals(stopPoints)){
			JSONArray stopsJSONArr = JSONArray.fromObject(stopPoints);
			Object[] stopsArr = stopsJSONArr.toArray();
			for(int i=0,len=stopsArr.length;i<len;i++){
				Geometry stop = (Geometry)JSONObject.toBean(JSONObject.fromObject(stopsArr[i]),Geometry.class);
				if(i!=0){
					stopStr += "|";
				}
				String stopWkt = GeoUtil.convertGeoClassToWKT(stop);
				stopStr += stopWkt;
			}
			stopStr += "|";
		}
		if(!"".equals(barriers)){
			JSONArray barriersJSONArr = JSONArray.fromObject(barriers);
			Object[] barriersArr = barriersJSONArr.toArray();
			for(int i=0,len=barriersArr.length;i<len;i++){
				Geometry barrier = (Geometry)JSONObject.toBean(JSONObject.fromObject(barriersArr[i]),Geometry.class);
				if(i!=0){
					barriersStr += "|";
				}
				barriersStr += GeoUtil.convertGeoClassToWKT(barrier);
			}
		}
		
		stopStr = startWkt + stopStr + endWkt;
		stopStr = getProjectPointsFromLine(stopStr);
		
		String res = spatialOperationService.routeTask(url,stopStr,"getroute",barriersStr,"roadnet");
		if(res.contains("xml")){
			System.err.println(res);
			return resList;
		}
		String[] resArr = res.split("\\|");
		for(int i=0,len=resArr.length;i<len;i++){
			resList.add(new Graphic(GeoUtil.convertWKTToGeoClass(resArr[i]),null,null));
			//resList.add(new Graphic(GeoUtil.reDirection(GeoUtil.convertWKTToGeoClass(resArr[i])),null,null));
		}

		return resList;
	}

	/**
	 * 查询服务
	 * @param layername 图层名
	 * @param geometry 几何体查询
	 * @param geometryPrecision 几何体精度
	 * @param spatialRelationship
	 * @param where 条件查询
	 * @return graphicList List 返回Graphic列表
	 */
	@RequestMapping(value="q/{layername}/",method=RequestMethod.POST)
	@LogAspect(desc="使用地理查询服务")
	@ResponseBody public List<Graphic> query(
		@PathVariable String layername,
		@RequestParam String geometry,
		@RequestParam String geometryPrecision,
		@RequestParam String spatialRelationship,
		@RequestParam String where
	){
		if("".equals(layername)){
			return null;
		}
		LayerEnum layer = LayerEnum.valueOf(layername.toUpperCase());
		ArrayList<LayerEnum> layerList = new ArrayList<LayerEnum>();
		List<LayerBean> resList = new ArrayList<LayerBean>(); 
		List<Graphic> graphicList = new ArrayList<Graphic>();
		layerList.add(layer);

		if(!"".equals(geometry)){
			Geometry geo = (Geometry)JSONObject.toBean(JSONObject.fromObject(geometry),Geometry.class);
			resList = spatialOperationService.identify(GeoUtil.convertGeoClassToWKT(geo),layerList);
		}

		if(!"".equals(where)){
			resList = geoLayerOperation.getByWhere(layer,where);
		}
		Iterator<LayerBean> iter = resList.iterator();
		while(iter.hasNext()){
			graphicList.add(GeoUtil.convertFromLayerBeanToGraphic(iter.next()));
		}
		return graphicList;
	}	
	
	/**
	 * 获取距离一点最近的道路上的投影点
	 * @param geometry
	 * @return
	 */
	@RequestMapping(value="closedpoint/",method=RequestMethod.POST)
	@ResponseBody
	public List<Graphic> getClosedPointOnRoadLine(String geometry) {
		List<Graphic> graphicList = new ArrayList<Graphic>();
		Geometry geo = (Geometry)JSONObject.toBean(JSONObject.fromObject(geometry), Geometry.class);
		LayerBean point = layerBeanMapper.getClosedPointOnRoadLine(GeoUtil.convertGeoClassToWKT(geo));
		graphicList.add(GeoUtil.convertFromLayerBeanToGraphic(point));
		return graphicList;
	}
	
	/**
	 * @param point
	 * @param geos
	 * @return
	 */
	@RequestMapping(value="closedpointfromgeos/",method=RequestMethod.POST)
	@ResponseBody
	public Geometry getClosedPointFromGeos(String point, String geos, String layernames) {
		Geometry resultGeo = null;
		// 先在图层上进行查找最近点
		Geometry pointGeo = (Geometry)JSONObject.toBean(JSONObject.fromObject(point),Geometry.class);
		if(null != layernames && !"".equals(layernames)) {
			String[] layers = layernames.split(",");
			String result = spatialOperationService.getClosestPointFromLayer(GeoUtil.convertGeoClassToWKT(pointGeo), layers);
			resultGeo = GeoUtil.convertWKTToGeoClass(result);
		}
		// 将图层上查询得到的最近点添加到geos中，在进行查询要素的最近点
		if(null != geos && !"".equals(geos)) {
			String[] gs = geos.split(";");
			
			String[] ggss = new String[gs.length];
			if(null != resultGeo) {
				ggss = new String[gs.length+1];
				ggss[gs.length] = GeoUtil.convertGeoClassToWKT(resultGeo);
			}
			for(int i=0,len=gs.length; i<len; i++) {
				Geometry geo = (Geometry)JSONObject.toBean(JSONObject.fromObject(gs[i]),Geometry.class);
				ggss[i] = GeoUtil.convertGeoClassToWKT(geo);
			}
			String wktResult = spatialOperationService.getClosestPointFromGeos(GeoUtil.convertGeoClassToWKT(pointGeo), ggss);
			if(null != wktResult && !("".equals(wktResult))) {
				resultGeo = GeoUtil.convertWKTToGeoClass(wktResult);
			}
		}
		return resultGeo;
	}
	
	/**
	 * 截断操作
	 * @param geotarget 目标几何体
	 * @param geosplit 截断的工具几何体
	 * @return
	 */
	@RequestMapping(value="split/",method=RequestMethod.POST)
	@ResponseBody
	public Geometry split(String geotarget, String geosplit) {
		return GeoUtil.convertWKTToGeoClass(spatialOperationService.split(geotarget, geosplit));
	}
	
	/**
	 * 合并操作
	 * @param geos 几何体数组用；分号分开
	 * @return
	 */
	@RequestMapping(value="union/",method=RequestMethod.POST)
	@ResponseBody
	public Geometry union(String geos) {
		String[] ggss = null;
		if(null != geos && !"".equals(geos)) {
			JSONArray jsonArray = JSONArray.fromObject(geos);
			Object[] geoArr = jsonArray.toArray();
			int len = geoArr.length;
			ggss = new String[len];
			for(int i=0;i<len;i++){
				Geometry geo = (Geometry)JSONObject.toBean(JSONObject.fromObject(geoArr[i]),Geometry.class);
				ggss[i] = GeoUtil.convertGeoClassToWKT(geo);
			}
		}
		return GeoUtil.convertWKTToGeoClass(spatialOperationService.union(ggss));
	}
	
	/**
	 * 查询临时表的空间数据
	 * */
	@RequestMapping("gettmp")
	@ResponseBody
	public List<Graphic> getTmpGeometry(String type, String where) {
		List<Graphic> result = new ArrayList<Graphic>();
		List<LayerBean> list = new ArrayList<LayerBean>();
		String tableName = getTmpTableName(type);
		if(!"".equals(tableName))list = layerBeanMapper.selectByWhere(tableName, where);
		for(int i=0,len=list.size(); i<len; i++) {
			result.add(GeoUtil.convertFromLayerBeanToGraphic(list.get(i)));
		}
		return result;
	}
	
	/**
	 * 添加临时表的空间数据
	 * */
	@RequestMapping("addtmp")
	@ResponseBody
	public int addTmpGeometry(String type, String tmps) {
		int result = -1;
		String tableName = getTmpTableName(type);
		if("".equals(tableName))return result;
		List<LayerBean> list = new ArrayList<LayerBean>();
		JSONArray jsonArr = JSONArray.fromObject(tmps);
		for(int i=0,len=jsonArr.size();i<len;i++){
			JSONObject jsonO = (JSONObject)jsonArr.get(i);
			//Graphic graphic = (Graphic)jsonArr.get(i);
			JSONObject geometry = jsonO.getJSONObject("geo");
			JSONObject attributes = jsonO.getJSONObject("attributes");
			LayerBean layerBean = new LayerBean();
			if(attributes.containsKey("CODE")){
				layerBean.setCode(attributes.getString("CODE"));
			}
			if(attributes.containsKey("NAME")){
				layerBean.setName(attributes.getString("NAME"));
			}
			Geometry geo = (Geometry)JSONObject.toBean(geometry, Geometry.class);
			layerBean.setGeometry(GeoUtil.convertGeoClassToWKT(geo));
			list.add(layerBean);
		}
		result = layerBeanMapper.add(list, tableName);
		return result;
	}
	
	/**
	 * 更新临时表的空间数据
	 * */
	@RequestMapping("updatetmp")
	@ResponseBody
	public int updateTmpGeometry(String type, String tmps) {
		int result = -1;
		String tableName = getTmpTableName(type);
		if("".equals(tableName))return result;
		JSONArray jsonArr = JSONArray.fromObject(tmps);
		for(int i=0,len=jsonArr.size();i<len;i++){
			JSONObject jsonO = (JSONObject)jsonArr.get(i);
			//Graphic graphic = (Graphic)jsonArr.get(i);
			JSONObject geometry = jsonO.getJSONObject("geo");
			JSONObject attributes = jsonO.getJSONObject("attributes");
			LayerBean layerBean = new LayerBean();
			String id = "";
			if(attributes.containsKey("OBJECTID")){
				id = attributes.getString("OBJECTID");
			}else{
				id = attributes.getString("ID");
			}
			layerBean.setId(Long.parseLong(id));
			if(attributes.containsKey("CODE")){
				layerBean.setCode(attributes.getString("CODE"));
			}
			if(attributes.containsKey("NAME")){
				layerBean.setName(attributes.getString("NAME"));
			}
			Geometry geo = (Geometry)JSONObject.toBean(geometry, Geometry.class);
			layerBean.setGeometry(GeoUtil.convertGeoClassToWKT(geo));
			result += layerBeanMapper.updateById(layerBean);
		}
		return result;
	}
	
	/**
	 * 删除临时的空间表数据
	 * */
	@RequestMapping("deltmp")
	@ResponseBody
	public int delTmpGeometry(String type, String where) {
		int result = -1;
		String tableName = getTmpTableName(type);
		if(!"".equals(tableName))result = layerBeanMapper.deleteByWhere(tableName, where);
		return result;
	}
	
	private String getTmpTableName(String type) {
		String tableName = "";
		if("point".equals(type)) {
			tableName = "tmp_point";
		}
		else if("polyline".equals(type)) {
			tableName = "tmp_polyline";
		}
		else if("polygon".equals(type)) {
			tableName = "tmp_polygon";
		}
		return tableName;
	}
	
	/**
	 * 根据wkt点集合，获取点停靠到最近线上的投影点的wkt集合
	 * @param wktPoints
	 * @return
	 */
	private String getProjectPointsFromLine(String wktPoints){
		String[] stops = wktPoints.split("[|]");
		StringBuffer sb = new StringBuffer("");
		for(int i=0,len=stops.length; i<len; i++) {
			String point = stops[i];
			String linewkt=snapPointMapper.getMinDistanceLine(point).getGeometryText();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pointText", point);
			map.put("lineText", linewkt);
			SnapPoint sp=snapPointMapper.queryClosedPoint(map);
			if(i != 0)sb.append("|");
			sb.append(sp.getGeometryText());
		}
		return sb.toString();
	}
	
}
