package com.xiangxun.epms.mobile.business.dao;

import java.util.List;

import com.xiangxun.epms.mobile.business.domain.Files;

public interface FilesMapper {

	List<Files> queryByBusinessId(String businessId);
	
	void saveInfo(Files info);
	
}
