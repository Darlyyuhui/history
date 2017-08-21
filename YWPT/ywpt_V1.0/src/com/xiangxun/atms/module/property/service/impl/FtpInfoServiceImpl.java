package com.xiangxun.atms.module.property.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.property.domain.DatabaseInfo;
import com.xiangxun.atms.module.property.domain.FtpInfo;
import com.xiangxun.atms.module.property.domain.FtpInfoSearch;
import com.xiangxun.atms.module.property.mapper.FtpInfoMapper;
import com.xiangxun.atms.module.property.service.FtpInfoService;

/**
 * FTP信息业务逻辑接口实现类
 * @author kouyunhao
 *
 */
@Service("ftpInfoService")
public class FtpInfoServiceImpl extends AbstractBaseService<FtpInfo, FtpInfoSearch> implements FtpInfoService {

	@Resource
	FtpInfoMapper ftpInfoMapper;
	
	@Override
	protected BaseMapper<FtpInfo, FtpInfoSearch> getBaseMapper() {
		return ftpInfoMapper;
	}
	
	@Override
	public List<FtpInfo> findAll() {
		FtpInfoSearch example = new FtpInfoSearch();
		example.createCriteria();
		return ftpInfoMapper.selectByExample(example);
	}

	@Override
	public List<FtpInfo> findByIp(String ip) {
		FtpInfoSearch example = new FtpInfoSearch();
		example.createCriteria().andIpEqualTo(ip);
		return ftpInfoMapper.selectByExample(example);
	}

	@Override
	public List<FtpInfo> findByName(String name) {
		FtpInfoSearch example = new FtpInfoSearch();
		example.createCriteria().andNameEqualTo(name);
		return ftpInfoMapper.selectByExample(example);
	}
	
	@Override
	public List<FtpInfo> getFtpListByFactoryid(String factoryId) {
		FtpInfoSearch example = new FtpInfoSearch();
		example.createCriteria().andFactoryIdEqualTo(factoryId);
		return ftpInfoMapper.selectByExample(example);
	}
	
	/***
	 * 导出EXCEL专用
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FtpInfo> selectByExampleDiy(
			Map<String, Object> map, String sortType, String menuid) {
		if (map != null) {
			if (StringUtils.notEmpty(map.get("name") + "")) {
				map.put("name", "%" + map.get("name").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("dialect") + "")) {
				map.put("dialect", "%" + map.get("dialect").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("ip") + "")) {
				map.put("ip", "%" + map.get("ip").toString() + "%");
			}
		}
		if (StringUtils.notEmpty(sortType)) {
			map.put("sortColumn", sortType);
		}
		
		//Page page = databaseInfoService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);

		
		//Page page = getListByCondition(map, 1, 2000, sortType, menuid);
		Page page = getListByCondition(map, 1, 2000, sortType);

		return page.getResult();
	}

}
