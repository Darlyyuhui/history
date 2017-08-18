package com.xiangxun.atms.module.reg.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.reg.dao.BackRegMapper;
import com.xiangxun.atms.module.reg.service.BackRegService;
import com.xiangxun.atms.module.reg.vo.BackReg;
import com.xiangxun.atms.module.reg.vo.BackRegSearch;

@Service
public class BackRegServiceImpl extends AbstractBaseService<BackReg, BackRegSearch> implements BackRegService {
    @Resource
    private BackRegMapper backRegMapper;

    @Resource
   	FilesService filesService;
   	/**
   	 * 允许上传的类型
   	 */
   	private final String FILE_TYPE = ".jpg.jpeg.png.bmp";
    
    @Override
    public BaseMapper<BackReg, BackRegSearch> getBaseMapper() {
         return backRegMapper;
    }

	@Override
	public void checkById(String id, int status, String checkUser) {
		BackReg t = new BackReg();
		t.setId(id);
		t.setCheckStatus(status);
		t.setCheckUser(checkUser);
		t.setCheckTime(new Date());
		this.updateByIdSelective(t);
	}

	@Override
	public void saveInfo(BackReg info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(BackReg.class), FILE_TYPE, 20L, fileRequest);
		this.save(info);
	}

	@Override
	public void updateInfo(BackReg info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(BackReg.class), FILE_TYPE, 20L, fileRequest);
		this.updateByIdSelective(info);
	}
}