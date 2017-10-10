package com.xiangxun.atms.module.land.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.land.dao.LandBlockErrorMapper;
import com.xiangxun.atms.module.land.service.LandBlockErrorService;
import com.xiangxun.atms.module.land.vo.LandBlockError;
import com.xiangxun.atms.module.land.vo.LandBlockErrorSearch;

@Service
public class LandBlockErrorServiceImpl extends AbstractBaseService<LandBlockError, LandBlockErrorSearch> implements LandBlockErrorService {
    @Resource
    private LandBlockErrorMapper landBlockErrorMapper;
    @Resource
	FilesService filesService;
	/**
	 * 允许上传的类型
	 */
	private final String FILE_TYPE = ".jpg.jpeg.png.bmp";
    @Override
    public BaseMapper<LandBlockError, LandBlockErrorSearch> getBaseMapper() {
         return landBlockErrorMapper;
    }
    @Override
	public void saveInfo(LandBlockError info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(LandBlockError.class), FILE_TYPE, 10L, fileRequest);
		this.save(info);
	}
	@Override
	public void updateInfo(LandBlockError info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(LandBlockError.class), FILE_TYPE, 10L, fileRequest);
		this.updateByIdSelective(info);
	}
}