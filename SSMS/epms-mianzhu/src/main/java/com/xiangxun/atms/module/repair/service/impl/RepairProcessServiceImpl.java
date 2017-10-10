package com.xiangxun.atms.module.repair.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.repair.dao.RepairProcessMapper;
import com.xiangxun.atms.module.repair.service.RepairProcessService;
import com.xiangxun.atms.module.repair.vo.RepairProcess;
import com.xiangxun.atms.module.repair.vo.RepairProcessSearch;

@Service
public class RepairProcessServiceImpl extends AbstractBaseService<RepairProcess, RepairProcessSearch> implements RepairProcessService {
    
	@Resource
    RepairProcessMapper repairProcessMapper;

	@Resource
   	FilesService filesService;
   	/**
   	 * 允许上传的类型
   	 */
   	private final String FILE_TYPE = ".jpg.jpeg.png.bmp";
	
    @Override
    public BaseMapper<RepairProcess, RepairProcessSearch> getBaseMapper() {
         return repairProcessMapper;
    }

	@Override
	public void saveInfo(RepairProcess info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(RepairProcess.class), FILE_TYPE, 10L, fileRequest);
		this.save(info);
	}

	@Override
	public void updateInfo(RepairProcess info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(RepairProcess.class), FILE_TYPE, 10L, fileRequest);
		this.updateByIdSelective(info);
	}
	
}