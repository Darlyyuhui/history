package com.xiangxun.atms.module.reg.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.reg.dao.FarmRegMapper;
import com.xiangxun.atms.module.reg.service.FarmRegService;
import com.xiangxun.atms.module.reg.vo.FarmReg;
import com.xiangxun.atms.module.reg.vo.FarmRegSearch;

@Service
public class FarmRegServiceImpl extends AbstractBaseService<FarmReg, FarmRegSearch> implements FarmRegService {
    @Resource
    private FarmRegMapper farmRegMapper;

    @Resource
   	FilesService filesService;
   	/**
   	 * 允许上传的类型
   	 */
   	private final String FILE_TYPE = ".jpg.jpeg.png.bmp";
    
    @Override
    public BaseMapper<FarmReg, FarmRegSearch> getBaseMapper() {
         return farmRegMapper;
    }

	@Override
	public void checkById(String id, int status, String checkUser) {
		FarmReg t = new FarmReg();
		t.setId(id);
		t.setCheckStatus(status);
		t.setCheckUser(checkUser);
		t.setCheckTime(new Date());
		this.updateByIdSelective(t);
	}

	@Override
	public void saveInfo(FarmReg info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(FarmReg.class), FILE_TYPE, 20L, fileRequest);
		this.save(info);
	}

	@Override
	public void updateInfo(FarmReg info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(FarmReg.class), FILE_TYPE, 20L, fileRequest);
		this.updateByIdSelective(info);
	}
}