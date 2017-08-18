package com.xiangxun.atms.module.apb.service;

import java.util.List;
import java.util.Map;

public interface ApbHomeService {

	/**
	 * 顶部统计信息
	 * @return
	 */
	Map<String, Object> getTopCount();
	
	/**
	 * 基地面积、产量
	 * @return
	 */
	List<Map<String, Object>> getApbInfoAreaCount();
	
	/**
	 * 基地产品类型统计
	 * @return
	 */
	List<Map<String, Object>> getProductTypeCount();
	
	/**
	 * 获取最新的基地产品信息
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> getApbProductList(int pageSize);
}
