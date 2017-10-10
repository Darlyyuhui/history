package com.xiangxun.atms.module.pollute.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.pollute.dao.PolluteCompanyMapper;
import com.xiangxun.atms.module.pollute.service.PolluteCompanyService;
import com.xiangxun.atms.module.pollute.vo.PolluteCompany;
import com.xiangxun.atms.module.pollute.vo.PolluteCompanySearch;

@Service
public class PolluteCompanyServiceImpl extends  AbstractBaseService<PolluteCompany, PolluteCompanySearch> implements PolluteCompanyService {
    @Resource
    private PolluteCompanyMapper polluteCompanyMapper;
    @Resource
	FilesService filesService;
	/**
	 * 允许上传的类型
	 */
    private final String FILE_TYPE = ".jpg.jpeg.bmp.png";
    

	@Override
	protected BaseMapper<PolluteCompany, PolluteCompanySearch> getBaseMapper() {
		
		return polluteCompanyMapper;
	}

	@Override
	public void saveInfo(PolluteCompany info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(PolluteCompany.class), FILE_TYPE, 60L, fileRequest);
		this.save(info);
		
	}

	@Override
	public void updateInfo(PolluteCompany info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		filesService.saveFile(id, filesService.getBusinessType(PolluteCompany.class), FILE_TYPE, 60L, fileRequest);
		this.updateByIdSelective(info);
		
	}

	@Override
	public List<PolluteCompany> selectAll() {
		
		return polluteCompanyMapper.selectAll();
	}

	
    
}