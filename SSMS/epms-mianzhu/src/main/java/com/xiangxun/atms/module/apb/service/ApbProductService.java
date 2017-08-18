package com.xiangxun.atms.module.apb.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.apb.vo.ApbProduct;
import com.xiangxun.atms.module.apb.vo.ApbProductSearch;

public interface ApbProductService extends BaseService<ApbProduct, ApbProductSearch> {
	/**
	 * 刷新缓存
	 */
	void refreshCache();

	void saveInfo(ApbProduct info, MultipartHttpServletRequest fileRequest);

	void updateInfo(ApbProduct info, MultipartHttpServletRequest fileRequest);
	 /**
	  * 根据typeCode查询基地产品
	  * 
	  */
	 List<ApbProduct> 	selectByTypeCode(String contion);
}