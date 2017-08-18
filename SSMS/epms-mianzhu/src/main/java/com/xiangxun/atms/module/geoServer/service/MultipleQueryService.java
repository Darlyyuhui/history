package com.xiangxun.atms.module.geoServer.service;

import java.util.List;
import java.util.Map;
import com.xiangxun.atms.module.geoServer.domain.MultipleQueryInfo;

public interface MultipleQueryService {
	   List<MultipleQueryInfo>	searchMultipleQueryInfo(Map<String, Object> map);
	   List<MultipleQueryInfo>	identifyMultipleQueryInfo(Map<String, Object> map);
}
