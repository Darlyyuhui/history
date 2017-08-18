package com.xiangxun.atms.module.repair.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.repair.dao.RepairCapitalMapper;
import com.xiangxun.atms.module.repair.service.RepairCapitalService;
import com.xiangxun.atms.module.repair.vo.RepairCapital;
import com.xiangxun.atms.module.repair.vo.RepairCapitalSearch;

@Service
public class RepairCapitalServiceImpl extends AbstractBaseService<RepairCapital, RepairCapitalSearch> implements RepairCapitalService {
    @Resource
    private RepairCapitalMapper repairCapitalMapper;

    @Resource
   	FilesService filesService;
   	/**
   	 * 允许上传的类型
   	 */
   	private final String FILE_TYPE = ".jpg.jpeg.png.bmp.pdf";
    
    @Override
    public BaseMapper<RepairCapital, RepairCapitalSearch> getBaseMapper() {
         return repairCapitalMapper;
    }

	@Override
	public void saveInfo(RepairCapital info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(RepairCapital.class), FILE_TYPE, 50L, fileRequest);
		this.save(info);
	}

	@Override
	public void updateInfo(RepairCapital info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(RepairCapital.class), FILE_TYPE, 50L, fileRequest);
		this.updateByIdSelective(info);
	}
}