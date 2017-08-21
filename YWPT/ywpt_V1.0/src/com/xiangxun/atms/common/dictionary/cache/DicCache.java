package com.xiangxun.atms.common.dictionary.cache;

import java.util.List;

import javax.annotation.Resource;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;

/**
 * 数据字典缓存
 * @author zhouhaij
 * @Apr 19, 2013 3:52:33 PM
 */
public class DicCache implements BaseCache {
	private final Logging logger = new Logging(DicCache.class);
	@Resource
	Cache cache;
	
	@Resource
	DicService dicService;

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.cache.BaseCache#getCacheKey()
	 */
	@Override
	public String getCacheKey() {
		return DIC_CACHE;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.cache.BaseCache#init()
	 */
	@Override
	public void init() throws Exception {
		List<Dic> dics = dicService.findAll();
		Table<String, String, String> table = HashBasedTable.create();
		Table<String, String, String> table1 = HashBasedTable.create();
		for (Dic dic : dics) {
			table.put(dic.getCode(),dic.getType().toString(),dic.getName());
			table1.put(dic.getName(),dic.getType().toString(),dic.getCode());
		}
		cache.put(Dic.class.getSimpleName(),table);
		cache.put("dic_name^code_cache",table1);
		logger.info("[数据字典] 缓存初始化完成");
	}

}
