package com.xiangxun.epms.mobile.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.domain.Geometry;
import com.xiangxun.epms.mobile.business.domain.LayerBean;
import com.xiangxun.epms.mobile.business.mapdao.MapMapper;
import com.xiangxun.epms.mobile.business.service.MapService;
import com.xiangxun.epms.mobile.business.util.GeoUtil;

@Service
public class MapServiceImpl implements MapService {

	@Resource
	MapMapper mapMapper;

	@Override
	public List<LayerBean> getLandByCode(String code) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("code", code);
		List<LayerBean> records= mapMapper.getLandByCode(args);
		corverToGeometryLayerBeans(records);
		return records;
	}
	private void corverToGeometryLayerBeans(List<LayerBean> records) {
		for(int i=0,len=records.size(); i<len; i++) {
			LayerBean record = records.get(i);
			corverToGeometryLayerBean(record);
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
			if(null == geo)
			{record.setGeometry("");
			}else{
				record.setGeometry(geo.toString());
			}
		} catch (Exception e) {
		}
	}
}
