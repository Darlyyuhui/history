package com.xiangxun.epms.mobile.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.dao.AirPointMapper;
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
    AirPointMapper airPointMapper;
    @Resource
    LandSampingSchemePointService landSampingSchemePointService;
	@Override
	public List<LandSamplingSheme> findAll() {
		return landSamplingShemeMapper.findAll();
	}
	/*@Override
	public Page queryList(LandSamplingSheme landSamplingSheme,int pageSize, int pageNo) {
		
		List<LandSamplingSheme> list = landSamplingShemeMapper.findAllBySelective(landSamplingSheme);
		for(LandSamplingSheme info :list){
			 List<LandBlock> blist=landBlockService.findById(info.getBlockId());
			 if(blist!=null&&blist.size()>0){
				 info.setBlockName(blist.get(0).getName());
			 }
			 List<Region> rlist=regionService.getAllRegionById(info.getRegionId());
			 if(rlist!=null&&rlist.size()>0){
				 info.setRegionName(rlist.get(0).getName());
			 }
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
			 
			 if("DQ".equals(info.getSampleCode())){
				 info.setQuantity(airPointMapper.countNUmber());
				 info.setSubtype("N");
			 }else {
				 info.setSubtype("Y");
				 info.setQuantity(landSampingSchemePointService.contlist(info.getId()));
				 if(info.getQuantity()==null){
					 info.setQuantity(0);
				 }
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
	}*/
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
					 
					 if("DQ".equals(info.getSampleCode())){
						 info.setQuantity(airPointMapper.countNUmber());
						 info.setSubtype("N");
					 }else {
						 info.setSubtype("Y");
						 info.setQuantity(landSampingSchemePointService.contlist(info.getId()));
						 if(info.getQuantity()==null){
							 info.setQuantity(0);
						 }
					 }
					 if(info.getMissionName()==null){
							info.setMissionName("");
						 }
					 if(info.getDept()==null){
						 info.setDept("");
					 }
			  
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
	@Override
	public List<Map<String,Object>> findAllByFinshAndRegType() {
		List<LandSamplingSheme> list=landSamplingShemeMapper.findAllByFinshAndRegType();
		
		return this.gradingDate(list);
	}
	/**
	 * 构造分级数据
	 * @param list
	 * @return
	 */
	private List<Map<String,Object>> gradingDate(List<LandSamplingSheme> list){
		List<Map<String,Object>> resultList= new ArrayList<Map<String,Object>>();
		if(list!=null&&list.size()>0){
			List<LandSamplingSheme> DQ = new ArrayList<LandSamplingSheme>();
			List<LandSamplingSheme> SD = new ArrayList<LandSamplingSheme>();
			List<LandSamplingSheme> NTTR = new ArrayList<LandSamplingSheme>();
			List<LandSamplingSheme> FL = new ArrayList<LandSamplingSheme>();
			List<LandSamplingSheme> BJTR = new ArrayList<LandSamplingSheme>();
			List<LandSamplingSheme> WATER = new ArrayList<LandSamplingSheme>();
			Map<String,Object> DQMap= new HashMap<String, Object>();
			Map<String,Object> SDMap= new HashMap<String, Object>();
			Map<String,Object> NTTRMap= new HashMap<String, Object>();
			Map<String,Object> FMap= new HashMap<String, Object>();
			Map<String,Object> BJTRMap= new HashMap<String, Object>();
			Map<String,Object> WATERMap= new HashMap<String, Object>();
			for(LandSamplingSheme info :list){
				if("DQ".equals(info.getSampleCode())){
					DQ.add(info);
				}
				if("SD".equals(info.getSampleCode())){
						SD.add(info);
				}
				if("NTTR".equals(info.getSampleCode())){
					NTTR.add(info);
			    }
				if("FL".equals(info.getSampleCode())){
					FL.add(info);
			    }
				if("BJTR".equals(info.getSampleCode())){
					BJTR.add(info);
			    }
				if("WATER".equals(info.getSampleCode())){
					WATER.add(info);
			    }
			}
			DQMap.put("regType", "大气沉降");
			DQMap.put("result", DQ);
			DQMap.put("shemeNum", DQ==null?0:DQ.size());
			SDMap.put("regType", "农作物");
			SDMap.put("result", SD);
			SDMap.put("shemeNum", SD==null?0:SD.size());
			NTTRMap.put("regType", "农田土壤");
			NTTRMap.put("result", NTTR);
			NTTRMap.put("shemeNum", NTTR==null?0:NTTR.size());
			FMap.put("regType", "肥料");
			FMap.put("result", FL);
			FMap.put("shemeNum", FL==null?0:FL.size());
			BJTRMap.put("regType","背景土壤");
			BJTRMap.put("result", BJTR);
			BJTRMap.put("shemeNum", BJTR==null?0:BJTR.size());
			WATERMap.put("regType", "水样底泥");
			WATERMap.put("result",WATER );
			WATERMap.put("shemeNum", WATER==null?0:WATER.size());
			
			resultList.add(WATERMap);
			resultList.add(DQMap);
			resultList.add(SDMap);
			resultList.add(NTTRMap);
			resultList.add(FMap);
			resultList.add(BJTRMap);
		}
		return resultList;
	}

	@Override
	public Page historysheme(LandSamplingSheme landSamplingSheme, int pageSize, int pageNo) {
		List<LandSamplingSheme> list=landSamplingShemeMapper.historysheme(landSamplingSheme);
		int totalCount = 0;
		if(list!=null&&list.size()>0){
			totalCount = list.size();
		}
		List<?> resultlist=Page.sublist(pageNo, pageSize, totalCount, list);
		return Page.getPage(totalCount, resultlist, pageNo, pageSize);
		
	}
	@Override
	public String getIdbyMissionId(String missionId) {
		
		return landSamplingShemeMapper.getIdbyMissionId(missionId);
	}

	

}
