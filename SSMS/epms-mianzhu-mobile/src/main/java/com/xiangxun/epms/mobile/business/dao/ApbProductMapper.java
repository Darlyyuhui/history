package com.xiangxun.epms.mobile.business.dao;

import java.util.List;
import com.xiangxun.epms.mobile.business.domain.ApbProduct;

public interface ApbProductMapper {
	List<ApbProduct> findById(String infoId);
	ApbProduct findByCode(String codes);
}