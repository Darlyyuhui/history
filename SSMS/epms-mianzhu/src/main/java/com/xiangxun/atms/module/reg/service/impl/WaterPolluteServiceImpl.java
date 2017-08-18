package com.xiangxun.atms.module.reg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.reg.dao.WaterPolluteMapper;
import com.xiangxun.atms.module.reg.service.WaterPolluteService;
import com.xiangxun.atms.module.reg.vo.WaterPollute;
import com.xiangxun.atms.module.reg.vo.WaterPolluteSearch;

@Service
public class WaterPolluteServiceImpl extends AbstractBaseService<WaterPollute, WaterPolluteSearch> implements WaterPolluteService {
    @Resource
    private WaterPolluteMapper waterPolluteMapper;

    @Resource
   	FilesService filesService;
   	/**
   	 * 允许上传的类型
   	 */
   	private final String FILE_TYPE = ".jpg.jpeg.png.bmp";
    
    @Override
    public BaseMapper<WaterPollute, WaterPolluteSearch> getBaseMapper() {
         return waterPolluteMapper;
    }

	@Override
	public void saveInfo(WaterPollute info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(WaterPollute.class), FILE_TYPE, 20L, fileRequest);
		this.save(info);
	}

	@Override
	public void updateInfo(WaterPollute info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(WaterPollute.class), FILE_TYPE, 20L, fileRequest);
		this.updateByIdSelective(info);
	}
}