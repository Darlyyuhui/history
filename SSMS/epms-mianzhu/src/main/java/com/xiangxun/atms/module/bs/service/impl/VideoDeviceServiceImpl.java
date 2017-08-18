package com.xiangxun.atms.module.bs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.bs.dao.VideoDeviceMapper;
import com.xiangxun.atms.module.bs.service.VideoDeviceService;
import com.xiangxun.atms.module.bs.vo.VideoDevice;
import com.xiangxun.atms.module.bs.vo.VideoDeviceSearch;

@Service
public class VideoDeviceServiceImpl extends AbstractBaseService<VideoDevice, VideoDeviceSearch> implements VideoDeviceService {
    @Resource
    private VideoDeviceMapper videoDeviceMapper;

    @Override
    public BaseMapper<VideoDevice, VideoDeviceSearch> getBaseMapper() {
         return videoDeviceMapper;
    }
}