package com.xiangxun.atms.module.apb.service;

import java.util.List;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.apb.vo.ApbInfo;
import com.xiangxun.atms.module.apb.vo.ApbInfoProductLink;
import com.xiangxun.atms.module.apb.vo.ApbInfoProductLinkSearch;
import com.xiangxun.atms.module.apb.vo.ApbProductType;



public interface ApbInfoProductLinkService extends BaseService<ApbInfoProductLink, ApbInfoProductLinkSearch> {
	/**
	 * 刷新缓存
	 */
	void refreshCache();
	/**
	 * 根据productId更新产品和基地关联表
	 */
	 void updateByProductId(ApbInfoProductLink apbInfoProductLink);
	 void insertSelective(ApbInfoProductLink apbInfoProductLink);
	 /**
	  * 根据productID获取info名称
	  */
	 List<ApbInfo> selectApbinfoNameByApbProductId(String productId);
	 /**
	  * 
	  * 删除数据
	  */
	 void deleteByApbInfoProductLink(ApbInfoProductLink apbInfoProductLink);
	 /**
	  * 根据infoId查询产品类型
	  * 
	  */
	 
	 List<ApbProductType> selectProductTypeNameByApbInfoId(String infoId);
	
}