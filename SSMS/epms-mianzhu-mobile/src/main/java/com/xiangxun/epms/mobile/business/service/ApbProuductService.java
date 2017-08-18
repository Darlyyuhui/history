package com.xiangxun.epms.mobile.business.service;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.ApbProduct;


public interface ApbProuductService {
	List<ApbProduct> findById(String infoId);
	ApbProduct findByCode(String codes);
}
