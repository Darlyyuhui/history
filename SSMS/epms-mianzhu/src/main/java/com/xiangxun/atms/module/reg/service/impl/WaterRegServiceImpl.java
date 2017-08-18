package com.xiangxun.atms.module.reg.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.reg.dao.WaterRegMapper;
import com.xiangxun.atms.module.reg.service.WaterRegService;
import com.xiangxun.atms.module.reg.vo.WaterReg;
import com.xiangxun.atms.module.reg.vo.WaterRegSearch;

@Service
public class WaterRegServiceImpl extends AbstractBaseService<WaterReg, WaterRegSearch> implements WaterRegService {
    @Resource
    private WaterRegMapper waterRegMapper;

    @Resource
   	FilesService filesService;
   	/**
   	 * 允许上传的类型
   	 */
   	private final String FILE_TYPE = ".jpg.jpeg.png.bmp";
    
    @Override
    public BaseMapper<WaterReg, WaterRegSearch> getBaseMapper() {
         return waterRegMapper;
    }

	@Override
	public void checkById(String id, int status, String checkUser) {
		WaterReg t = new WaterReg();
		t.setId(id);
		t.setCheckStatus(status);
		t.setCheckUser(checkUser);
		t.setCheckTime(new Date());
		this.updateByIdSelective(t);
	}

	@Override
	public void saveInfo(WaterReg info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(WaterReg.class), FILE_TYPE, 20L, fileRequest);
		this.save(info);
	}

	@Override
	public void updateInfo(WaterReg info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(WaterReg.class), FILE_TYPE, 20L, fileRequest);
		this.updateByIdSelective(info);
	}
}