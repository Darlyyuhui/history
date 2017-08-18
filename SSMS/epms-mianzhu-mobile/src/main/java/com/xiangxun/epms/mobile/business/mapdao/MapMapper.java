package com.xiangxun.epms.mobile.business.mapdao;

import java.util.List;
import java.util.Map;

import com.xiangxun.epms.mobile.business.domain.LayerBean;

public interface MapMapper {
	
	List<LayerBean> getLandByCode(Map<String, Object> args);
}