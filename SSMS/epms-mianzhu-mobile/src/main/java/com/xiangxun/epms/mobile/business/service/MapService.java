package com.xiangxun.epms.mobile.business.service;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.LayerBean;

public interface MapService {

	List<LayerBean> getLandByCode(String code);
	
}
