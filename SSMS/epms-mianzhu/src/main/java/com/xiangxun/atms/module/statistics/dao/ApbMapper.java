package com.xiangxun.atms.module.statistics.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.xiangxun.atms.module.statistics.vo.ApbInfo;

public interface ApbMapper {

	List<ApbInfo> queryList(Map<String, Object> args, RowBounds rowBounds);
	
	int countList(Map<String, Object> args);
	
}
