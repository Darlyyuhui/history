package com.xiangxun.atms.module.apb.dao;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.apb.vo.ApbProduct;
import com.xiangxun.atms.module.apb.vo.ApbProductSearch;

public interface ApbProductMapper extends BaseMapper<ApbProduct,ApbProductSearch> {
public List<ApbProduct> 	selectByTypeCode(String contion);
}