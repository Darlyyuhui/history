package com.xiangxun.atms.module.property.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.property.domain.VideoAddressInfo;
import com.xiangxun.atms.module.property.domain.VideoAddressInfoSearch;
import com.xiangxun.atms.module.property.domain.VideoAddressInfoSearch.Criteria;
import com.xiangxun.atms.module.property.mapper.VideoAddressInfoMapper;
import com.xiangxun.atms.module.property.service.VideoAddressService;

/***
 * 视频监控播放配置 服务实现类 
 * @author yantao
 */
@Service("videoAddressService")
public class VideoAddressServiceImpl extends AbstractBaseService<VideoAddressInfo, VideoAddressInfoSearch> implements VideoAddressService{
	
	@Resource
	VideoAddressInfoMapper videoAddressInfoMapper;

	@Override
	protected BaseMapper<VideoAddressInfo, VideoAddressInfoSearch> getBaseMapper() {
		return videoAddressInfoMapper;
	}
	

	@Override
	public Page getVideoAddressByCondition(Map<String, Object> map, int pageNo,int pageSize, String sortType) {
		VideoAddressInfoSearch search = new VideoAddressInfoSearch();
		Criteria criteria = search.createCriteria();
		if(map!=null){
			if(StringUtils.notEmpty(map.get("name")+"")){
				criteria.andNameLike("%"+map.get("name").toString()+"%");
			}
			if(StringUtils.notEmpty(map.get("ip")+"")){
				criteria.andIpLike("%"+map.get("ip").toString()+"%");
			}
			if(StringUtils.notEmpty(map.get("tracode")+"")){
				criteria.andTracodeEqualTo(map.get("tracode").toString());
			}
			//===================add by kouyunhao 2013-09-03 添加“是否实时”查询条件相应添加处理代码。================start
			if(StringUtils.notEmpty(map.get("isrealitime")+"")){
				criteria.andIsrealitimeEqualTo(map.get("isrealitime").toString());
			}
			//===================add by kouyunhao 2013-09-03 添加“是否实时”查询条件相应添加处理代码。================end
		}
		if(StringUtils.notEmpty(sortType)){
			search.setOrderByClause(sortType);
		}
		Page page = selectByExample(search, pageNo, pageSize);
		return page;
	}
	
	
	@Override
	public List<VideoAddressInfo> findIsRealiTimeAll(String isRealiTime) {
		VideoAddressInfoSearch search = new VideoAddressInfoSearch();
		Criteria criteria = search.createCriteria();
		//criteria.andIsrealitimeEqualTo(isRealiTime);
		criteria.andIdIsNotNull();
		return videoAddressInfoMapper.selectByExample(search);
	}
	
	
}
