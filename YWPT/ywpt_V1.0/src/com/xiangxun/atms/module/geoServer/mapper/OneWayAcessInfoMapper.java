package com.xiangxun.atms.module.geoServer.mapper;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.module.geoServer.domain.OneWayAcessInfo;

public interface OneWayAcessInfoMapper {
  List<OneWayAcessInfo>	searchOneWayAcessInfo(Map<String, Object> map);
  void	addOneWayAcessInfo(Map<String, Object> map);
  void	deleteOneWayAcess(Map<String, Object> map);
  void	updateOneWayAcessInfo(Map<String, Object> map);
  
}
