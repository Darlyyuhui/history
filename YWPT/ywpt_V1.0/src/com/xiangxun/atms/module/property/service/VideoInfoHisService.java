package com.xiangxun.atms.module.property.service;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.property.domain.VideoInfoHis;
import com.xiangxun.atms.module.property.domain.VideoInfoHisSearch;

public interface VideoInfoHisService extends BaseService<VideoInfoHis, VideoInfoHisSearch> {
	
	public void updateAfterRecordId(String id, String afterId);
	
	boolean hasRecordModified(String id);

}
