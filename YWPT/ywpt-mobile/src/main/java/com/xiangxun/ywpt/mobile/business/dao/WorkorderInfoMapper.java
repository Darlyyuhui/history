package com.xiangxun.ywpt.mobile.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiangxun.ywpt.mobile.business.domain.WorkorderInfo;

@Mapper
public interface WorkorderInfoMapper {
	
	List<WorkorderInfo> queryList(WorkorderInfo workorderInfo);
	
	int countList(WorkorderInfo workorderInfo);

	void changeWorkorderStatus(WorkorderInfo workorderInfo);

	WorkorderInfo watchPicture(WorkorderInfo workorderInfo);
/*	int countByContact(String contact);

	List getWorkorderCountByStatus();

	List getWorkorderCountByType();*/

	List<WorkorderInfo> totalWorkOrder(WorkorderInfo workorderInfo);

	void upLoadPicture(WorkorderInfo workorderInfo);
}