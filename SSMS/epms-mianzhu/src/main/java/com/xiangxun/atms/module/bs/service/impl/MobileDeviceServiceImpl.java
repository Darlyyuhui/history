package com.xiangxun.atms.module.bs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.bs.dao.MobileDeviceMapper;
import com.xiangxun.atms.module.bs.service.MobileDeviceService;
import com.xiangxun.atms.module.bs.vo.MobileDevice;
import com.xiangxun.atms.module.bs.vo.MobileDeviceSearch;

@Service
public class MobileDeviceServiceImpl extends AbstractBaseService<MobileDevice, MobileDeviceSearch> implements MobileDeviceService {
    @Resource
    private MobileDeviceMapper mobileDeviceMapper;

    @Override
    public BaseMapper<MobileDevice, MobileDeviceSearch> getBaseMapper() {
         return mobileDeviceMapper;
    }
}