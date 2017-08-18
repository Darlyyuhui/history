package com.xiangxun.atms.module.geoServer.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.module.geoServer.mapper.LayerBeanMapper;
import com.xiangxun.atms.module.geoServer.vo.GeoserverSearch;
import com.xiangxun.atms.module.geoServer.domain.Geometry;
import com.xiangxun.atms.module.geoServer.domain.LayerBean;
import com.xiangxun.atms.module.geoServer.domain.LayerEnum;
import com.xiangxun.atms.module.geoServer.service.IMapOperation;
import com.xiangxun.atms.module.geoServer.util.GeoUtil;

@Service
public class GeoLayerOperationImpl implements IMapOperation {
	
	@Resource 
	LayerBeanMapper layerBeanMapper;
	
	private static Properties prop;

	/**
	 * 图层中添加记录
	 * @param layerName 图层名称
	 * @param record 要添加的记录
	 * */
	@Override
	public int add(LayerEnum layerName, LayerBean record) {
		String tableName = getTableName(layerName);
		corverToWKTLayerBean(record);
		String geo = record.getGeometry();
		if(null == geo || "".equals(geo))return 0;
		List<LayerBean> records = new ArrayList<LayerBean>();
		records.add(record);
		
		return layerBeanMapper.add(records, tableName);
	}
	
	/**
	 * 图层添加多条记录
	 * */
	@Override
	public int addList(LayerEnum layerName, List<LayerBean> records) {
		String tableName = getTableName(layerName);
		for(int i=0,len=records.size(); i<len; i++) {
			corverToWKTLayerBean(records.get(i));
		}
		return layerBeanMapper.add(records, tableName);
	}
	
	/**
	 * 删除记录
	 * @param layerName 图层名
	 * @param 记录id
	 * */
	@Override
	public int delete(LayerEnum layerName, long id) {
		String tableName = getTableName(layerName);
		GeoserverSearch search = new GeoserverSearch();
		search.createCriteria().andIdEqualTo(id);
		
		return layerBeanMapper.delete(tableName, search);
	}
	
	/**
	 * 根据code值删除记录
	 * @param layerName 图层名
	 * */
	@Override
	public int deleteByCode(LayerEnum layerName, String code) {
		String tableName = getTableName(layerName);
		GeoserverSearch search = new GeoserverSearch();
		search.createCriteria().andCodeEqualTo(code);
			
		return layerBeanMapper.delete(tableName, search);	
	}
	
	/**
	 * 根据where条件删除记录
	 * */
	@Override
	public int deleteByWhere(LayerEnum layerName, String where) {		
		String tableName = getTableName(layerName);
		GeoserverSearch search = new GeoserverSearch();
		search.createCriteria().addCriterion(where);
			
		return layerBeanMapper.delete(tableName, search);
	}
	
	/**
	 * 获取指定图层的所有元素
	 * */
	@Override
	public List<LayerBean> getAll(LayerEnum layerName) {
		String tableName = getTableName(layerName);
		GeoserverSearch search = new GeoserverSearch();
		List<LayerBean> layers = layerBeanMapper.selectByExample(tableName, search);
		corverToGeometryLayerBeans(layers);
		return layers;
	}
	
	/**
	 * 根据code值获取图层记录
	 * */
	@Override
	public List<LayerBean> getByCode(LayerEnum layerName, String code) {
		String tableName = getTableName(layerName);
		GeoserverSearch search = new GeoserverSearch();
		search.createCriteria().andCodeEqualTo(code);
		List<LayerBean> records = layerBeanMapper.selectByExample(tableName, search);
		corverToGeometryLayerBeans(records);
		return records;
	}

	@Override
	public List<LayerBean> getByCodes(LayerEnum layerName, String[] codes) {
		String tableName = getTableName(layerName);
		GeoserverSearch search = new GeoserverSearch();

		for (int i = 0; i < codes.length; i++) {
			search.createCriteria().andCodeEqualTo(codes[i]);
		}
		
		List<LayerBean> records = layerBeanMapper.selectByExample(tableName, search);		
		corverToGeometryLayerBeans(records);
		return records;
	}

	@Override
	public LayerBean getById(LayerEnum layerName, long id) {
		String tableName = getTableName(layerName);
		GeoserverSearch search = new GeoserverSearch();
		search.createCriteria().andIdEqualTo(id);
		List<LayerBean> records = layerBeanMapper.selectByExample(tableName, search);
		corverToGeometryLayerBeans(records);
		return records.get(0);
	}

	@Override
	public List<LayerBean> getByName(LayerEnum layerName, String name) {
		String tableName = getTableName(layerName);
		GeoserverSearch search = new GeoserverSearch();
		search.createCriteria().andNameLike(name);
		List<LayerBean> records = layerBeanMapper.selectByExample(tableName, search);
		corverToGeometryLayerBeans(records);
		return records;
	}
	
	public List<LayerBean> getByNames(LayerEnum layerName, List<String> names) {
		String tableName = getTableName(layerName);
		GeoserverSearch search = new GeoserverSearch();
		for (String name : names) {
			search.createCriteria().andNameLike(name);
		}
		
		List<LayerBean> records = layerBeanMapper.selectByExample(tableName, search);
		corverToGeometryLayerBeans(records);
		return records;
	}
	
	@Override
	public List<LayerBean> getByWhere(LayerEnum layerName, String where) {
		String tableName = getTableName(layerName);
		List<LayerBean> layers = layerBeanMapper.selectByWhere(tableName, where);
		corverToGeometryLayerBeans(layers);
		return layers;
	}
	
	@Override
	public int save(LayerEnum layerName, LayerBean record) {
		corverToWKTLayerBean(record);
		String tableName  = getTableName(layerName);
		GeoserverSearch search = new GeoserverSearch();
		search.createCriteria().andCodeEqualTo(record.getCode());
		List<LayerBean> layers = layerBeanMapper.selectByExample(tableName, search);
		//应该判断是否存在然后再更新或者插入
		if(null != layers && layers.size() > 0) {
			// update操作
			update(layerName, record);
		}
		else {
			// add操作
			add(layerName, record);
		}
		return 0;
	}

	@Override
	public int update(LayerEnum layerName, LayerBean layer) {
		corverToWKTLayerBean(layer);
		String geo = layer.getGeometry();
		if(null == geo || "".equals(geo))return 0;
		String tableName = getTableName(layerName);
		GeoserverSearch search = new GeoserverSearch();
		search.createCriteria().andCodeEqualTo(layer.getCode());
		return layerBeanMapper.updateByExample(layer, tableName, search);
	}
	
	@Override
	public int updateAttrById(LayerEnum layerName,LayerBean layer){
		corverToWKTLayerBean(layer);
		String geo = layer.getGeometry();
		if(null == geo || "".equals(geo))return 0;
		layer.setGeometry(null);
		String tableName = getTableName(layerName);
		GeoserverSearch search = new GeoserverSearch();
		search.createCriteria().andIdEqualTo(layer.getId());
		return layerBeanMapper.updateByExample(layer, tableName, search);
	}
		
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
	 * geoStr格式的geometry字符串转换为wkt格式的geometry字符串
	 * @param record
	 */
	private void corverToWKTLayerBean(LayerBean record) {
		try {
			String geoStr = record.getGeometry();
			Geometry geo = (Geometry)JSONObject.toBean(JSONObject.fromObject(geoStr), Geometry.class);
			record.setGeometry(GeoUtil.convertGeoClassToWKT(geo));
		} catch (Exception e) {
		}
	}
	/**
	 * wkt格式的geometry字符串转换为geoStr格式的geometry字符串
	 * @param record
	 */
	private void corverToGeometryLayerBean(LayerBean record) {
		try {
			String wktStr = record.getGeometry();
			Geometry geo = GeoUtil.convertWKTToGeoClass(wktStr);
			if(null == geo)record.setGeometry("");
			else record.setGeometry(geo.toString());
		} catch (Exception e) {
		}
	}
	private void corverToGeometryLayerBeans(List<LayerBean> records) {
		for(int i=0,len=records.size(); i<len; i++) {
			LayerBean record = records.get(i);
			corverToGeometryLayerBean(record);
		}
	}

	/**
	 * 同步读取配置文件方法 保证实例只有一个，减少内存消耗
	 * 之后不需要再创建prop
	 * */
	private synchronized Properties getDatasource() throws IOException {
		String datasource = "/com/xiangxun/atms/module/geoServer/config/datasource.properties";
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(datasource));
		return properties;
	}

}
