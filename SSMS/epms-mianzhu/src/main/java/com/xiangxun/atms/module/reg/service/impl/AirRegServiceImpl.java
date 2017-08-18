package com.xiangxun.atms.module.reg.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.reg.dao.AirRegMapper;
import com.xiangxun.atms.module.reg.service.AirRegService;
import com.xiangxun.atms.module.reg.vo.AirReg;
import com.xiangxun.atms.module.reg.vo.AirRegSearch;

@Service
public class AirRegServiceImpl extends AbstractBaseService<AirReg, AirRegSearch> implements AirRegService {
    @Resource
    private AirRegMapper airRegMapper;

    @Resource
   	FilesService filesService;
   	/**
   	 * 允许上传的类型
   	 */
   	private final String FILE_TYPE = ".jpg.jpeg.png.bmp";
    @Override
    public BaseMapper<AirReg, AirRegSearch> getBaseMapper() {
         return airRegMapper;
    }

	@Override
	public void checkById(String id, int status, String checkUser) {
		AirReg t = new AirReg();
		t.setId(id);
		t.setCheckStatus(status);
		t.setCheckUser(checkUser);
		t.setCheckTime(new Date());
		this.updateByIdSelective(t);
	}

	@Override
	public void saveInfo(AirReg info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(AirReg.class), FILE_TYPE, 20L, fileRequest);
		this.save(info);
	}

	@Override
	public void updateInfo(AirReg info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(AirReg.class), FILE_TYPE, 20L, fileRequest);
		this.updateByIdSelective(info);
	}

	@Override
	public List<AirReg> getInfoByAnalysis() {
		return airRegMapper.getInfoByAnalysis();
	}
}