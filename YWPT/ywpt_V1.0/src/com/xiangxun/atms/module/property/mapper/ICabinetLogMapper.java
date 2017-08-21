package com.xiangxun.atms.module.property.mapper;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.AssetInfoSearch;
import com.xiangxun.atms.module.property.domain.ICabinetLog;
import com.xiangxun.atms.module.property.domain.ICabinetLogSearch;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ICabinetLogMapper extends BaseMapper<ICabinetLog, ICabinetLogSearch> {
	List<ICabinetLog> selectByIp(String execno);
 
    
}