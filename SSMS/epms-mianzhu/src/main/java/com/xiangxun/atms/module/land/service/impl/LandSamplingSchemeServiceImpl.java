package com.xiangxun.atms.module.land.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.land.dao.LandSamplingSchemeMapper;
import com.xiangxun.atms.module.land.service.LandSamplingSchemePointService;
import com.xiangxun.atms.module.land.service.LandSamplingSchemeService;
import com.xiangxun.atms.module.land.vo.LandSamplingScheme;
import com.xiangxun.atms.module.land.vo.LandSamplingSchemeSearch;

@Service
public class LandSamplingSchemeServiceImpl extends AbstractBaseService<LandSamplingScheme, LandSamplingSchemeSearch> implements LandSamplingSchemeService {
    @Resource
    private LandSamplingSchemeMapper landSamplingSchemeMapper;
    @Resource
    LandSamplingSchemePointService landSamplingSchemePointService;
    @Resource
	FilesService filesService;
    @Resource
    Cache cache;
	/**
	 * 允许上传的类型
	 */
	private final String FILE_TYPE = ".pdf";
    
    @Override
    public BaseMapper<LandSamplingScheme, LandSamplingSchemeSearch> getBaseMapper() {
         return landSamplingSchemeMapper;
    }

	@Override
	public void saveInfo(LandSamplingScheme info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(LandSamplingScheme.class), FILE_TYPE, 50L, fileRequest);
		this.save(info);
	}

	@Override
	public void updateInfo(LandSamplingScheme info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(LandSamplingScheme.class), FILE_TYPE, 50L, fileRequest);
		this.updateByIdSelective(info);
	}

	@Override
	public List<LandSamplingScheme> queryMissionSelectSchemes() {
		return landSamplingSchemeMapper.queryMissionSelectSchemes();
	}

	@Override
	public int deleteById(String id) {
		//删除方案下点位
		landSamplingSchemePointService.deleteBySchemeId(id);
		return super.deleteById(id);
	}

	@Override
	public boolean isDelete(String id) {
		Integer num = landSamplingSchemeMapper.isDelete(id);
		return num==null||num==0?true:false;
	}
}