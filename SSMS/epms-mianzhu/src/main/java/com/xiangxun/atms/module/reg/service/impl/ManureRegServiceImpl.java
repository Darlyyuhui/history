package com.xiangxun.atms.module.reg.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.reg.dao.ManureRegMapper;
import com.xiangxun.atms.module.reg.service.ManureRegService;
import com.xiangxun.atms.module.reg.vo.ManureReg;
import com.xiangxun.atms.module.reg.vo.ManureRegSearch;

@Service
public class ManureRegServiceImpl extends AbstractBaseService<ManureReg, ManureRegSearch> implements ManureRegService {
    @Resource
    private ManureRegMapper manureRegMapper;

    @Resource
   	FilesService filesService;
   	/**
   	 * 允许上传的类型
   	 */
   	private final String FILE_TYPE = ".jpg.jpeg.png.bmp";
    
    @Override
    public BaseMapper<ManureReg, ManureRegSearch> getBaseMapper() {
         return manureRegMapper;
    }

	@Override
	public void checkById(String id, int status, String checkUser) {
		ManureReg t = new ManureReg();
		t.setId(id);
		t.setCheckStatus(status);
		t.setCheckUser(checkUser);
		t.setCheckTime(new Date());
		this.updateByIdSelective(t);
	}

	@Override
	public void saveInfo(ManureReg info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(ManureReg.class), FILE_TYPE, 20L, fileRequest);
		this.save(info);
	}

	@Override
	public void updateInfo(ManureReg info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(ManureReg.class), FILE_TYPE, 20L, fileRequest);
		this.updateByIdSelective(info);
	}
}