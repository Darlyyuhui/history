package com.xiangxun.atms.module.property.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.AssetInfoSearch;
import com.xiangxun.atms.module.property.domain.AssetInfoSearch.Criteria;
import com.xiangxun.atms.module.property.mapper.AssetInfoMapper;
import com.xiangxun.atms.module.property.service.PropertyPercentService;
@Service("propertyPercentService")
public class PropertyPercentServiceImpl implements PropertyPercentService{
	
	@Resource
	AssetInfoMapper assetInfoMapper;
	
	public double getAllpercent(int flag){
		AssetInfoSearch infosearch = new AssetInfoSearch();
		Criteria criteria = infosearch.createCriteria();
		criteria.andIdIsNotNull();
		//是否开启 资产状态影响整体健康度
		if(flag == 0){
			criteria.andAssetstatusEqualTo("001");
		}
		List<AssetInfo> list = assetInfoMapper.selectByExample(infosearch);
		
		if(list!=null && list.size()>0){
			double alljkz = 0;
			for(AssetInfo info:list){
				String assettype = info.getAssettype();
				double perjkz = 0;
				if("server".equals(assettype)){
					if(null==info.getCpuStatus() && null == info.getMemoryStatus() && null == info.getDiskStatus() ){
						//不可用 直接赋全值
						perjkz = 1;
					}else{
						//可用  则扣除性能值
						if("3".equals(info.getCpuStatus())){
							perjkz = perjkz + 0.2;//这里先写死  后面查出对应报警级别的系数
						}
						if("3".equals(info.getMemoryStatus())){
							perjkz = perjkz + 0.2;//这里先写死  后面查出对应报警级别的系数
						}
						if("3".equals(info.getDiskStatus())){
							perjkz = perjkz + 0.2;//这里先写死  后面查出对应报警级别的系数
						}
					}
				}else if("device".equals(assettype)){
					if("3".equals(info.getPowerStatus()) || "3".equals(info.getNetStatus())){
						//不可用 直接赋全值
						perjkz = 1;
					}else{
						if("3".equals(info.getDataStatus())){
							perjkz = perjkz + 0.4;//这里先写死  后面查出对应报警级别的系数
						}
					}
				}else if("ftp".equals(assettype) || "database".equals(assettype) || "project".equals(assettype)){
					if("3".equals(info.getNetStatus())){
						//不可用 直接赋全值
						perjkz = 1;
					}else{
						if("3".equals(info.getDataStatus())){
							perjkz = perjkz + 0.5;//这里先写死  后面查出对应报警级别的系数
						}
					}
				}else{
					perjkz = 0;
				}
				info.setJkd(1-perjkz);
				alljkz = alljkz + (1-perjkz);
			}
			int allnum = list.size();
			double returnvalue = (alljkz / allnum)*100;
			return returnvalue;
		}else{
			//无资产时候 返回100%
			return 1;
		}
		
	}

}
