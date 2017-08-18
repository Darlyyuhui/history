package com.xiangxun.atms.module.pollute.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.pollute.dao.ControllPlanMapper;
import com.xiangxun.atms.module.pollute.service.ControllPlanService;
import com.xiangxun.atms.module.pollute.vo.ControllPlan;
import com.xiangxun.atms.module.pollute.vo.ControllPlanSearch;

@Service
public class ControllPlanServiceImpl extends AbstractBaseService<ControllPlan, ControllPlanSearch> implements ControllPlanService {
    @Resource
    private ControllPlanMapper controllPlanMapper;
    
    @Resource
	FilesService filesService;
	/**
	 * 允许上传的类型
	 */
	private final String FILE_TYPE = ".pdf";

    @Override
    public BaseMapper<ControllPlan, ControllPlanSearch> getBaseMapper() {
         return controllPlanMapper;
    }

	@Override
	public void saveInfo(ControllPlan info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(ControllPlan.class), FILE_TYPE, 50L, fileRequest);
		this.save(info);
	}

	@Override
	public void updateInfo(ControllPlan info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(ControllPlan.class), FILE_TYPE, 50L, fileRequest);
		this.updateByIdSelective(info);
	}
}