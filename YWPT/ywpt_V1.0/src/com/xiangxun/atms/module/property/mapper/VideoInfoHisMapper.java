package com.xiangxun.atms.module.property.mapper;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.VideoInfoHis;
import com.xiangxun.atms.module.property.domain.VideoInfoHisSearch;

public interface VideoInfoHisMapper extends BaseMapper<VideoInfoHis, VideoInfoHisSearch> {
	
	int countRecordInHistory(String id);
	
}