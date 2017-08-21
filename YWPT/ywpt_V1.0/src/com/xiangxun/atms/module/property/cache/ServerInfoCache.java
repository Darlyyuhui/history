package com.xiangxun.atms.module.property.cache;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.module.property.domain.ServerInfo;
import com.xiangxun.atms.module.property.service.ServerInfoService;

/***
 * 服务器信息缓存
 * @author kouyunhao
 *
 */
@Component
public class ServerInfoCache implements BaseCache {

private final Logging logger = new Logging(ServerInfoCache.class);
	
	@Resource
	Cache cache;

	@Resource
	ServerInfoService serverInfoService;
	
	@Override
	public void init() throws Exception {
		logger.info("[服务器信息] 缓存初始化开始");
		List<ServerInfo> caches =  serverInfoService.findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (ServerInfo server : caches) {
			//存储的对象为table
			table.put(server.getId(), ServerInfo.class.getSimpleName(), server.getName()+" [ "+server.getServerip()+" ] ");
		}
		cache.put(ServerInfo.class.getSimpleName(), table);
		logger.info("[服务器信息] 缓存初始化完成");
	}

	@Override
	public String getCacheKey() {
		return ANALYZE_CACHE;
	}
	

}
