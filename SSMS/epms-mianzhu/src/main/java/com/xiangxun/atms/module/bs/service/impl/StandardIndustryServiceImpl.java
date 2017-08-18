package com.xiangxun.atms.module.bs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.bs.dao.StandardIndustryMapper;
import com.xiangxun.atms.module.bs.service.StandardIndustryService;
import com.xiangxun.atms.module.bs.vo.StandardIndustry;
import com.xiangxun.atms.module.bs.vo.StandardIndustrySearch;
import com.xiangxun.atms.module.files.service.FilesService;

@Service
public class StandardIndustryServiceImpl extends AbstractBaseService<StandardIndustry, StandardIndustrySearch> implements StandardIndustryService {
    @Resource
    private StandardIndustryMapper standardIndustryMapper;
    @Resource
	FilesService filesService;
    @Resource
    Cache cache;
    /**
	 * 允许上传的类型
	 */
	private final String FILE_TYPE = ".pdf";
    @Override
    public BaseMapper<StandardIndustry, StandardIndustrySearch> getBaseMapper() {
         return standardIndustryMapper;
    }
    
    @Override
	public void saveInfo(StandardIndustry info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(StandardIndustry.class), FILE_TYPE, 60L, fileRequest);
		this.save(info);
	}

	@Override
	public void updateInfo(StandardIndustry info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(StandardIndustry.class), FILE_TYPE, 60L, fileRequest);
		this.updateById(info);
	}

}