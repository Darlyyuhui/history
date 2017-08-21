package com.xiangxun.atms.module.property.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.VideoInfoHis;
import com.xiangxun.atms.module.property.domain.VideoInfoHisSearch;
import com.xiangxun.atms.module.property.mapper.VideoInfoHisMapper;
import com.xiangxun.atms.module.property.service.VideoInfoHisService;

@Service("videoInfoHisService")
public class VideoInfoHisServiceImpl extends AbstractBaseService<VideoInfoHis, VideoInfoHisSearch> implements VideoInfoHisService {
	
	@Resource
	VideoInfoHisMapper videoInfoHisMapper;

	@Override
	protected BaseMapper<VideoInfoHis, VideoInfoHisSearch> getBaseMapper() {
		return videoInfoHisMapper;
	}

	@Override
	public void updateAfterRecordId(String id, String afterId) {
		VideoInfoHis deviceHis = super.getById(id);
		deviceHis.setAfterRecordId(afterId);
		videoInfoHisMapper.updateByPrimaryKeySelective(deviceHis);
	}

	@Override
	public boolean hasRecordModified(String id) {
		int count = videoInfoHisMapper.countRecordInHistory(id);
		if(count == 0){
			return false;
		}else{
			return true;
		}
	}

}
