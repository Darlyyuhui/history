package com.xiangxun.atms.module.pollute.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.pollute.dao.ReservePlanMapper;
import com.xiangxun.atms.module.pollute.service.ReservePlanService;
import com.xiangxun.atms.module.pollute.vo.ReservePlan;
import com.xiangxun.atms.module.pollute.vo.ReservePlanSearch;

@Service
public class ReservePlanServiceImpl extends AbstractBaseService<ReservePlan, ReservePlanSearch> implements ReservePlanService {
    @Resource
    private ReservePlanMapper reservePlanMapper;

    @Resource
	FilesService filesService;
	/**
	 * 允许上传的类型
	 */
	private final String FILE_TYPE = ".pdf";
    
    @Override
    public BaseMapper<ReservePlan, ReservePlanSearch> getBaseMapper() {
         return reservePlanMapper;
    }

	@Override
	public void saveInfo(ReservePlan info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(ReservePlan.class), FILE_TYPE, 50L, fileRequest);
		this.save(info);
	}

	@Override
	public void updateInfo(ReservePlan info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(ReservePlan.class), FILE_TYPE, 50L, fileRequest);
		this.updateByIdSelective(info);
	}
}