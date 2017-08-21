package com.xiangxun.atms.module.property.service;

import java.util.List;

import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.AssetInfoSearch;
import com.xiangxun.atms.module.property.domain.ICabinetLog;
import com.xiangxun.atms.module.property.domain.ICabinetLogSearch;

public interface ICabinetLogService extends BaseService<ICabinetLog, ICabinetLogSearch>{

	 public List<ICabinetLog> getMessage(String execno);
     
}
