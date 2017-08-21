package com.xiangxun.atms.module.geoServer.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Service;

import com.xiangxun.atms.module.geoServer.mapper.SpatialOperationMapper;
import com.xiangxun.atms.module.geoServer.service.SpatialOperationService;
import com.xiangxun.atms.module.gis.domain.LayerBean;
import com.xiangxun.atms.module.gis.domain.LayerEnum;

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

		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(analysisUrl);
		
		NameValuePair param = new NameValuePair("request", method);
		NameValuePair param2 = new NameValuePair("service", "na");
		NameValuePair param3 = new NameValuePair("version", "1.0.0");
		NameValuePair param4 = new NameValuePair("graph", layerName);
		NameValuePair param5 = new NameValuePair("stops", stops);
		
		//默认障碍点
		String point = "POINT(106.92841546699 34.278213465353)";
		NameValuePair param6 = null;		
		if("".equals(barriers)){
			param6 = new NameValuePair("barriers", point);	      
		} else{			
			param6 = new NameValuePair("barriers", barriers);
		}
		
		NameValuePair[] params = new NameValuePair[] {param, param2, param3, param4, param5, param6};
		postMethod.setRequestBody(params);
		
		try {
			client.executeMethod(postMethod);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	
		try {
			returnBacks = postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(200 == postMethod.getStatusCode()) {
			// 请求登录成功
			System.out.println("请求成功！");
		}
		// 释放连接
		postMethod.releaseConnection();
		
		return returnBacks;
	}

	/**
	 * 缓冲分析
	 * */
	@Override
	public String bufferTask(String analysisUrl, String geoms, String method, String distance) {
		String loginback = "";
		HttpClient client = new HttpClient();
		
		PostMethod postMethod = new PostMethod(analysisUrl);
		NameValuePair param = new NameValuePair("request", method);
		NameValuePair param2 = new NameValuePair("service", "gp");
		NameValuePair param3 = new NameValuePair("version", "1.0.0");
		NameValuePair param4 = new NameValuePair("geoms", geoms);
		NameValuePair param5 = new NameValuePair("distance", distance);
		
		NameValuePair[] params = new NameValuePair[] {param, param2, param3, param4, param5};
		
		postMethod.setRequestBody(params);
		try {
			client.executeMethod(postMethod);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	
		try {
			loginback = postMethod.getResponseBodyAsString();
			System.out.println(loginback);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(200 == postMethod.getStatusCode()) {
			// 请求登录成功
			System.out.println("请求成功！");
		}
		// 释放连接
		postMethod.releaseConnection();
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
