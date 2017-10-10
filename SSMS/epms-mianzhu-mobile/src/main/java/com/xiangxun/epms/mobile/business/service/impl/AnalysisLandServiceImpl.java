package com.xiangxun.epms.mobile.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.dao.AnalysisLandMapper;
import com.xiangxun.epms.mobile.business.domain.AnalysisLand;
import com.xiangxun.epms.mobile.business.service.AnalysisLandService;
import com.xiangxun.epms.mobile.business.service.SamplingLandRegService;
import com.xiangxun.epms.mobile.business.util.Page;

@Service
public class AnalysisLandServiceImpl implements AnalysisLandService {
	@Resource
	AnalysisLandMapper analysisLandMapper;
	@Resource
	SamplingLandRegService samplingLandRegService;

	@Override
	public Page findByConttion(AnalysisLand analysisLand, int pageSize, int pageNo) {
		List<AnalysisLand> list = analysisLandMapper.findByConttion(null);
		int totalCount = analysisLandMapper.countList(null);
		return Page.getPage(totalCount, list, pageNo, pageSize);
	}

	@Override
	public List<AnalysisLand> selectbyContion(String region, int pageSize, int pageNo) {
		List<AnalysisLand> resultList = this.getListByCondition(region);
		return resultList;
	}

	@Override
	public List<AnalysisLand> getListByCondition(String regionId) {
		List<AnalysisLand> list = analysisLandMapper.getListByCondition(regionId);
		return this.subsection(list,null);
	}

	@Override
	public Page selectByCondition(AnalysisLand analysisLand,String temple,int pageSize, int pageNo) {
		List<AnalysisLand> list = analysisLandMapper.selectByCondition(analysisLand);
		int totalCount = 0;
		List<AnalysisLand> resultList =this.subsection(list,temple);
		if(resultList!=null&&resultList.size()>0){
			totalCount = resultList.size();
		}
		List<?> subList= Page.sublist(pageNo, pageSize, totalCount, resultList);
		return Page.getPage(totalCount, subList, pageNo, pageSize);
	}
     //行转列处理
	private List<AnalysisLand> subsection(List<AnalysisLand> list,String temple) {
		List<AnalysisLand> resultList = new ArrayList< AnalysisLand>();
		for (AnalysisLand info : list) {
		   if(temple==null||"".equals(temple)){
			AnalysisLand ph = new AnalysisLand();
			ph.setId(info.getId());
			ph.type_name =info.getName()==null?"":info.getName();
			ph.analy_name = "ph值";
			ph.analy_value = info.getPh()==null?new BigDecimal(0): info.getPh();
			ph.setIsOver(info.getIsOver()==null?0:info.getIsOver());
			resultList.add(ph);
			AnalysisLand cadmium = new AnalysisLand();
			cadmium.setId(info.getId());
			cadmium.type_name = info.getName()==null?"":info.getName();
			cadmium.analy_name = "镉";
			cadmium.analy_value = info.getCadmium();
			cadmium.setIsOver(info.getIsOver()==null?0:info.getIsOver());
			resultList.add(cadmium);
			AnalysisLand availabl = new AnalysisLand();
			availabl.setId(info.getId());
			availabl.type_name = info.getName()==null?"":info.getName();
			availabl.analy_name = "有效态镉";
			availabl.analy_value = info.getAvailableCadmium();
			availabl.setIsOver(info.getIsOver()==null?0:info.getIsOver());
			resultList.add(availabl);
		   }else if("ph".equals(temple)){
			    AnalysisLand ph = new AnalysisLand();
				ph.setId(info.getId());
				ph.type_name=info.getName()==null?"":info.getName();
				ph.analy_name = "ph值";
				ph.analy_value = info.getPh()==null?new BigDecimal(0): info.getPh();
				ph.setIsOver(info.getIsOver()==null?0:info.getIsOver());
				resultList.add(ph);
		   }else if("cadmium".equals(temple)){
			    AnalysisLand cadmium = new AnalysisLand();;
			    cadmium.setId(info.getId());
				cadmium.type_name = info.getName()==null?"":info.getName();
				cadmium.analy_name = "镉";
				cadmium.analy_value = info.getCadmium();
				cadmium.setIsOver(info.getIsOver()==null?0:info.getIsOver());
				resultList.add(cadmium);
		   }else if("availabl".equals(temple)){
			   AnalysisLand availabl = new AnalysisLand();
				availabl.setId(info.getId());
				availabl.type_name = info.getName()==null?"":info.getName();
				availabl.analy_name = "有效态镉";
				availabl.analy_value = info.getAvailableCadmium();
				availabl.setIsOver(info.getIsOver()==null?0:info.getIsOver());
				resultList.add(availabl);
		   }
		}
		return resultList;
	}
	
	public List<AnalysisLand>  sublist(int pageNo,int pageSize,int totalCount,List<AnalysisLand> list){
		List<AnalysisLand> resultList = new ArrayList<AnalysisLand>();
		if(list==null)
		{
			return null;
		}
		if((pageNo-1)*pageSize>=totalCount){
			return null;
		}else{
			if(pageNo*pageSize<=totalCount){
				 resultList = list.subList((pageNo-1)*pageSize, pageNo*pageSize);
		    }else {
		    	 resultList = list.subList((pageNo-1)*pageSize, totalCount);
		    }
			return resultList;
		}
		
	}
}