package com.xiangxun.epms.mobile.business.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.dao.LandSamplingShemeMapper;
import com.xiangxun.epms.mobile.business.domain.LandBlock;
import com.xiangxun.epms.mobile.business.domain.LandSamplingSheme;
import com.xiangxun.epms.mobile.business.domain.Region;
import com.xiangxun.epms.mobile.business.service.LandBlockService;
import com.xiangxun.epms.mobile.business.service.LandSampingSchemePointService;
import com.xiangxun.epms.mobile.business.service.LandSamplingShemeService;
import com.xiangxun.epms.mobile.business.service.RegionService;
import com.xiangxun.epms.mobile.business.util.Page;

@Service
public class LandSamplingShemeServiceImpl implements LandSamplingShemeService{
    @Resource
    LandSamplingShemeMapper landSamplingShemeMapper;
    @Resource
    RegionService  regionService;
    @Resource
    LandBlockService  landBlockService;
    @Resource
    LandSampingSchemePointService landSampingSchemePointService;
	@Override
	public List<LandSamplingSheme> findAll() {
		return landSamplingShemeMapper.findAll();
	}
	@Override
	public Page queryList(LandSamplingSheme landSamplingSheme,int pageSize, int pageNo) {
		
		List<LandSamplingSheme> list = landSamplingShemeMapper.findAllBySelective(landSamplingSheme);
		for(LandSamplingSheme info :list){
			/* List<LandBlock> blist=landBlockService.findById(info.getBlockId());
			 if(blist!=null&&blist.size()>0){
				 info.setBlockName(blist.get(0).getName());
			 }
			 List<Region> rlist=regionService.getAllRegionById(info.getRegionId());
			 if(rlist!=null&&rlist.size()>0){
				 info.setRegionName(rlist.get(0).getName());
			 }*/
			 if(info.getRegionName()==null){
				info.setRegionName("");
			 }
			 if(info.getSampleName()==null){
				 info.setSampleName("");
			 }
			 if(info.getRegionName()==null){
				 info.setRegionName("");
			 }
			 if(info.getName()==null){
				  info.setName("");
			 }
			 info.setQuantity(landSampingSchemePointService.contlist(info.getId()));
			 if(info.getQuantity()==null){
				 info.setQuantity(0);
			 }
			 if(info.getMissionName()==null){
					info.setMissionName("");
				 }
			 if(info.getDept()==null){
				 info.setDept("");
			 }
			  
		  }
		int totalCount = 0;
		if(list!=null&&list.size()>0){
			totalCount = list.size();
		}
		
		return Page.getPage(totalCount, list, pageNo, pageSize);
	}
	@Override
	public List<LandSamplingSheme> findAllByFinsh(Map<String, Object> args) {
		List<LandSamplingSheme> list = landSamplingShemeMapper.findAllByFinsh(args);
		if(list!=null&&list.size()>0){
		  for(LandSamplingSheme info :list){
			    List<LandBlock> blist=landBlockService.findById(info.getBlockId());
				 if(blist!=null&&blist.size()>0){
					 info.setBlockName(blist.get(0).getName()==null?"":blist.get(0).getName());
				 }
				 List<Region> rlist=regionService.getAllRegionById(info.getRegionId());
				 if(rlist!=null&&rlist.size()>0){
					 info.setRegionName(rlist.get(0).getName()==null?"":rlist.get(0).getName());
				 }
				  info.setQuantity(landSampingSchemePointService.contlist(info.getId()));
			  
		  }
		}
		return list;
	}
	@Override
	public LandSamplingSheme findById(String id) {
		LandSamplingSheme info = landSamplingShemeMapper.findById(id);
		if(info!=null){
			 List<LandBlock> blist=landBlockService.findById(info.getBlockId());
			 if(blist!=null&&blist.size()>0){
				 info.setBlockName(blist.get(0).getName()==null?"":blist.get(0).getName());
			 }
			 List<Region> rlist=regionService.getAllRegionById(info.getRegionId());
			 if(rlist!=null&&rlist.size()>0){
				 info.setRegionName(rlist.get(0).getName()==null?"":rlist.get(0).getName());
			 }
		}
		return info;
	}

}
