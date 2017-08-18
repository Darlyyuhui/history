package com.xiangxun.ywpt.mobile.business.service;

import com.xiangxun.ywpt.mobile.business.domain.CabInfo;
import com.xiangxun.ywpt.mobile.business.domain.PerambulateInfo;

public interface CabInfoService {
	CabInfo cabDetails(CabInfo cabInfo);
	
	CabInfo cabDetailByContions(PerambulateInfo perambulateInfo);


}
