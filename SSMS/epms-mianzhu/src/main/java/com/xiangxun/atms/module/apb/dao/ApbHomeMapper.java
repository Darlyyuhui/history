package com.xiangxun.atms.module.apb.dao;

import java.util.List;
import java.util.Map;

public interface ApbHomeMapper {

	/**
	 * 产品类型统计
	 * @return
	 */
	List<Map<String, Object>> getProductTypeCount();
	
	/**
	 * 首页顶部统计
	 * @return
	 */
	List<Map<String, Object>> getTopCount();
	
	/**
	 * 基地产量面积统计
	 * @return
	 */
	List<Map<String, Object>> getApbInfoAreaCount();
	
	/**
	 * 获取最新的产品信息
	 * @param args
	 * @return
	 */
	List<Map<String, Object>> getApbProductList(Map<String, Object> args);
}
