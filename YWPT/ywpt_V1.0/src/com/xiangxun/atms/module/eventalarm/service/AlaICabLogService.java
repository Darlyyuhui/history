package com.xiangxun.atms.module.eventalarm.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.eventalarm.domain.AlaICabLog;
import com.xiangxun.atms.module.eventalarm.domain.AlaICabLogSearch;



public interface AlaICabLogService extends BaseService<AlaICabLog, AlaICabLogSearch> {
	
   Page getAll(int pageNo, int pageSize,String assetname,String ip,String installplace);
   
   byte[]  getphoto1(String id);
   byte[]  getphoto2(String id);
}
