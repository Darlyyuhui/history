package com.xiangxun.epms.mobile.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.dao.MobileDeviceMapper;
import com.xiangxun.epms.mobile.business.domain.MobileDevice;
import com.xiangxun.epms.mobile.business.service.MobileDeviceService;
@Service
public class MobileDeviceServiceImpl implements MobileDeviceService {
    @Resource
    MobileDeviceMapper  mobileDeviceMapper;
	@Override
	public MobileDevice findByImei(String imeiNo) {
		List<MobileDevice> list=mobileDeviceMapper.findByImei(imeiNo);
		if(list==null||list.size()==0){
			return null;
		}
		return list.get(0);
	}

}
