package com.xiangxun.atms.module.apb.dao;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.apb.vo.ApbInfo;
import com.xiangxun.atms.module.apb.vo.ApbInfoProductLink;
import com.xiangxun.atms.module.apb.vo.ApbInfoProductLinkSearch;
import com.xiangxun.atms.module.apb.vo.ApbProductType;

public interface ApbInfoProductLinkMapper extends  BaseMapper<ApbInfoProductLink, ApbInfoProductLinkSearch> {
	public void updateByProductId(ApbInfoProductLink apbInfoProductLink);
	public int insertSelective(ApbInfoProductLink apbInfoProductLink);
	public List<ApbInfo> selectApbinfoNameByApbProductId(String productId);
	public void deleteByApbInfoProductLink(ApbInfoProductLink apbInfoProductLink);
	public List<ApbProductType> selectProductTypeNameByApbInfoId(String infoId);
}