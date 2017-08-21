package com.xiangxun.atms.module.geoServer.service.impl;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiangxun.atms.module.geoServer.domain.SnapPoint;
import com.xiangxun.atms.module.geoServer.mapper.SnapPointMapper;
import com.xiangxun.atms.module.geoServer.service.SnapPointService;
@Service
public class SnapPointServiceImpl implements SnapPointService {
	
	@Resource 
	SnapPointMapper snapPointMapper;
	
	public List<SnapPoint> searchSnapPoints(Map<String, Object> map) {
		return snapPointMapper.searchSnapPoints(map);
	}
	public List<SnapPoint> searchIntersectGeometryList(String geometryText) {
		return snapPointMapper.searchIntersectGeometryList(geometryText);
	}
	/*
	 * 获取距离一直点最近的道路--------
	 * (non-Javadoc)
	 * @see com.xiangxun.atms.module.geoServer.service.SnapPointService#getMinDistanceLine(java.lang.String)
	 */
	@Override
	public SnapPoint getMinDistanceLine(String geometryText) {
		// TODO Auto-generated method stub
		return snapPointMapper.getMinDistanceLine(geometryText);
	}
	/*
	 * 
	 * (non-Javadoc)获取点到线的投影点
	 * @see com.xiangxun.atms.module.geoServer.service.SnapPointService#queryClosedPoint(java.lang.String, java.lang.String)
	 */
	@Override
	public SnapPoint queryClosedPoint(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return  snapPointMapper.queryClosedPoint(map);
	}
}
