package com.xiangxun.atms.module.reg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.reg.dao.ManureInfoMapper;
import com.xiangxun.atms.module.reg.service.ManureInfoService;
import com.xiangxun.atms.module.reg.vo.ManureInfo;
import com.xiangxun.atms.module.reg.vo.ManureInfoSearch;

@Service
public class ManureInfoServiceImpl extends AbstractBaseService<ManureInfo, ManureInfoSearch> implements ManureInfoService {
    @Resource
    private ManureInfoMapper manureInfoMapper;

    @Override
    public BaseMapper<ManureInfo, ManureInfoSearch> getBaseMapper() {
         return manureInfoMapper;
    }
}