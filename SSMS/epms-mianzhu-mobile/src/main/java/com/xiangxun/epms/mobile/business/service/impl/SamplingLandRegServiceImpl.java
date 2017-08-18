package com.xiangxun.epms.mobile.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.epms.mobile.business.dao.SamplingLandRegMapper;
import com.xiangxun.epms.mobile.business.domain.Files;
import com.xiangxun.epms.mobile.business.domain.LandSamplingSchemePoint;
import com.xiangxun.epms.mobile.business.domain.SamplingLandReg;
import com.xiangxun.epms.mobile.business.service.FilesService;
import com.xiangxun.epms.mobile.business.service.SamplingLandRegService;
import com.xiangxun.epms.mobile.business.util.Constant;
@Service
public class SamplingLandRegServiceImpl  implements SamplingLandRegService {
    @Resource
    private SamplingLandRegMapper samplingLandRegMapper;
    @Resource
	FilesService filesService;
	@Override
	public List<SamplingLandReg> getAllSamplingLandReg() {
		return samplingLandRegMapper.getAllSamplingLandReg();
	}

	@Override
	public void insertSelective(SamplingLandReg reg) {
		
		samplingLandRegMapper.insertSelective(reg);
	}

	@Override
	public List<String> saveFile( MultipartHttpServletRequest request) {
	  List<String> list=	filesService.saveFilepackid( SamplingLandReg.class.getSimpleName(), false, Constant.UPLOAD_FILE_TYPE, Constant.UPLOAD_FILE_SIZE, request);
	  return list;
	}


	@Override
	public  List<Map<String,Object>> selectByMissionId(String missionId) {
		List<SamplingLandReg > list=samplingLandRegMapper.selectByMissionId( missionId);
		List<Map<String,Object>> poinList = new ArrayList<Map<String,Object>>();
		for(SamplingLandReg info:list){
			Map<String,Object> map = new HashMap<String,Object>();
			LandSamplingSchemePoint point = new LandSamplingSchemePoint();
			point.setId(info.getId());
			if(info.getLatitude()==null){
				point.setLatitude(new BigDecimal(0));
			}else{
				point.setLatitude(info.getLatitude());
			}
			if(info.getLongitude()==null){
				point.setLongitude(new BigDecimal(0));
			}else{
				point.setLongitude(info.getLongitude());
			}
			
			point.setName(info.getName()==null?"":info.getName());
			point.setCode(info.getCode()==null?"":info.getCode());
			map.put("id", info.getId());
			map.put("data", point);
			poinList.add(map);
		}
		return poinList;
	}

	@Override
	public List<SamplingLandReg> selectByPrimaryKey(String id) {
		
		return samplingLandRegMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SamplingLandReg> particular(SamplingLandReg it) {
		List<SamplingLandReg> list=samplingLandRegMapper.selectByPrimaryKey(it.getId());
		for(SamplingLandReg info :list){
			List<Files> fList=filesService.queryByBusinessId(info.getId());
			info.setFile(fList);
			if(info.getLatitude()==null){
				info.setLatitude(new BigDecimal(0));
			}
			if(info.getLongitude()==null){
				info.setLongitude(new BigDecimal(0));
			}
			
		}
		
		return list;
	}

}