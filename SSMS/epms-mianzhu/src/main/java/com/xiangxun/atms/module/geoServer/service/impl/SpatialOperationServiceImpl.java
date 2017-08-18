package com.xiangxun.atms.module.geoServer.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import com.xiangxun.atms.framework.util.HttpClientUtil;
import org.springframework.stereotype.Service;

import com.xiangxun.atms.module.geoServer.mapper.SpatialOperationMapper;
import com.xiangxun.atms.module.geoServer.service.SpatialOperationService;
import com.xiangxun.atms.module.geoServer.domain.LayerBean;
import com.xiangxun.atms.module.geoServer.domain.LayerEnum;

@Service
public class SpatialOperationServiceImpl implements SpatialOperationService {
	
	@Resource
	SpatialOperationMapper spatialOperationMapper;
	
	
	private static Properties prop;
	
	/**
	 * 以几何图形查询
	 * @param geo 集合图形的字符串表示
	 * */
	@Override
	public List<LayerBean> identify(String geo, List<LayerEnum> layerEnums) {
		List<String> tableNames = new ArrayList<String>();
		
		for (LayerEnum layerEnum : layerEnums) {
			String tableName = getTableName(layerEnum);
			tableNames.add(tableName);
		}
		
		if (!(geo.startsWith("'") && geo.endsWith("'"))) {
			geo = "'" + geo + "'";
		}
				
		List<LayerBean> identifyResults = spatialOperationMapper.getIntersections(geo, tableNames);
		
		return identifyResults;
	}
	
	/**
	 * 捕捉
	 * @param geo捕捉点的wkt表示
	 * @param layer 要捕捉的的线图层
	 * @param tolerance 容差
	 * @return 结果点的wkt表示
	 * */
	@Override
	public String snap(String geo, LayerEnum layer, double tolerance) {
		String tableName = getTableName(layer);
	
		if(!(geo.startsWith("'") && geo.endsWith("'"))) {
			geo = "'" + geo + "'";
		}
		return spatialOperationMapper.snap(geo, tableName, tolerance);
	}
		
	/**
	 * 路径分析操作
	 * */
	@Override
	public String routeTask(String analysisUrl, String stops, String method, String barriers, String layerName) {
	
		String returnBacks = "";

		if("".equals(layerName)){
			layerName = "roadnet";
		}

        Map<String, Object> args = new HashMap<String, Object>();
        args.put("request", method);
        args.put("service", "na");
        args.put("version", "1.0.0");
        args.put("graph", layerName);
        args.put("stops", stops);
        if("".equals(barriers)){
            args.put("barriers", "");
        }  else{
            args.put("barriers", barriers);
        }

        try {
            returnBacks = HttpClientUtil.httpPost(analysisUrl, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

		return returnBacks;
	}

	/**
	 * 缓冲分析
	 * */
	@Override
	public String bufferTask(String analysisUrl, String geoms, String method, String distance) {
		String loginback = "";

        Map<String, Object> args = new HashMap<String, Object>();
        args.put("request", method);
        args.put("service", "gp");
        args.put("version", "1.0.0");
        args.put("geoms",geoms);
        args.put("distance",distance);

        try {
            loginback = HttpClientUtil.httpPost(analysisUrl,args);
        } catch (Exception e) {
            e.printStackTrace();
        }

		return loginback;
	}
	
	
	
	/**
	 * 获取图层名对应的表名
	 * */
	public String getTableName(LayerEnum layerEnum) {
		if (prop == null) {
			try {
				prop = getDatasource();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String tableName = prop.getProperty(layerEnum.toString());
		return tableName;
	};
	
	/**
	 * 同步读取配置文件方法 保证实例只有一个，减少内存消耗,第一次调用时同步过程需要消耗较大内存
	 * 之后不需要再创建prop
	 * */
	private synchronized Properties getDatasource() throws IOException {
		String datasource = "/com/xiangxun/atms/module/geoServer/config/datasource.properties";
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(datasource));
		
		return properties;
	}

	@Override
	public String getClosestPointFromGeos(String point, String[] geos) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("point", point);
		map.put("geos", geos);
		return spatialOperationMapper.getClosestPointFromGeos(map);
	}

	@Override
	public String getClosestPointFromLayer(String geo, String[] tableNames) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("geo", geo);
		map.put("tableNames", tableNames);
		String result = spatialOperationMapper.getClosestPointFromLayer(map);
		return result;
	}

	@Override
	public String split(String geotarget, String geosplit) {
		return spatialOperationMapper.split(geotarget, geosplit);
	}

	@Override
	public String union(String[] geos) {
		if(null == geos)return "";
		String temp = "";
		for(int i=0,len=geos.length; i<len-1; i++) {
			if(i == 0) temp = spatialOperationMapper.union(geos[i], geos[i+1]);
			else temp = spatialOperationMapper.union(temp, geos[i+1]);
		}
		return temp;
	}
	
}
