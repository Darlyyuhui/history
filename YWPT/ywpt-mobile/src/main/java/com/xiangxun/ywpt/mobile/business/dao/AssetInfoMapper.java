package com.xiangxun.ywpt.mobile.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiangxun.ywpt.mobile.business.domain.AssetInfo;

@Mapper
public interface AssetInfoMapper {

	List<AssetInfo> queryList(AssetInfo assetInfo);
	
	int countList(AssetInfo assetInfo);

	List<AssetInfo> deviceDetails(AssetInfo assetInfo);

	List<AssetInfo> serverDetails(AssetInfo assetInfo);

	List<AssetInfo> cabinefDetails(AssetInfo assetInfo);

	List<AssetInfo> databaseDetails(AssetInfo assetInfo);

	List<AssetInfo> ftpDetails(AssetInfo assetInfo);

	List<AssetInfo> projectDetails(AssetInfo assetInfo);
}
