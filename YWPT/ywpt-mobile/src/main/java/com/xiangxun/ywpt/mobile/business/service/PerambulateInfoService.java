package com.xiangxun.ywpt.mobile.business.service;

import com.xiangxun.ywpt.mobile.business.domain.PerambulateInfo;
import com.xiangxun.ywpt.mobile.business.util.Page;

/**
 * app端运维人巡检功能模块接口
 * @author miaoxu
 *
 */
public interface PerambulateInfoService {
	void perambulateUp(PerambulateInfo perambulateInfo);

	Page queryList(PerambulateInfo perambulateInfo, int pageSize, int pageNo);

}
