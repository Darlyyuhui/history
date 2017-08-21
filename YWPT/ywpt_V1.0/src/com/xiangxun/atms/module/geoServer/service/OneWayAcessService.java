package com.xiangxun.atms.module.geoServer.service;

import java.util.List;
import java.util.Map;
import com.xiangxun.atms.module.geoServer.domain.OneWayAcessInfo;

public interface OneWayAcessService   {
	List<OneWayAcessInfo>	searchOneWayAcessInfo(Map<String, Object> map);
	public void	addOneWayAcessInfo(Map<String, Object> map);
	public void	deleteOneWayAcess(Map<String, Object> map);
	public void	updateOneWayAcessInfo(Map<String, Object> map);
}
