package com.xiangxun.epms.mobile.business.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiangxun.epms.mobile.business.domain.LandSamplingSchemePoint;
import com.xiangxun.epms.mobile.business.service.LandSampingSchemePointService;
import com.xiangxun.epms.mobile.business.util.UuidGenerateUtil;

@Controller
@RequestMapping(value="samply/server/land/point")
public class LandSamplingSchemePointCtl extends BaseCtl {
   @Resource
   LandSampingSchemePointService landSampingSchemePointService;
   @SuppressWarnings({ "rawtypes", "unchecked" })
   @RequestMapping(value ="queryBySchemeId", method = RequestMethod.POST )
   public void findById(String schemeId,HttpServletRequest request, HttpServletResponse response){
	   if(StringUtils.isEmpty(schemeId)) {
			super.simpleResult("1001", "缺失查询参数", request, response);
			return;
		}
	   List<LandSamplingSchemePoint> list=null;
	   List resultList = new ArrayList();
	   String resTime=request.getParameter("resTime");
	  
	   try{
	     if(resTime==null){
	  	   list = landSampingSchemePointService.getLandSamplingSchemePointByPlanId(super.getQueryParams(request, "schemeId"));
	  	 for(LandSamplingSchemePoint info:list){
	    		Map<String,Object> map = new HashMap<String,Object>();
	    		map.put("unique", info.getId());
	    		map.put("data", info);
	    		resultList.add(map);
	    	}
	    	super.TimeResult("1000", "查询成功", resultList, request, response);
	     }else{
		   list = landSampingSchemePointService.getLandSamplingSchemePointByPlanId(super.getQueryParams(request, "schemeId","resTime"));
		   if(list!=null&&list.size()>0){
		    	for(LandSamplingSchemePoint info:list){
		    		Map<String,Object> map = new HashMap<String,Object>();
		    		map.put("unique", info.getId());
		    		map.put("data", info);
		    		resultList.add(map);
		    	}
		    	super.TimeResult("1000", "数据有更新", resultList, request, response);
		      }else{
		    	super.TimeResult("2000", "数据没有更新", resultList, request, response);
		      }
	      }
	    logger.info("table T_Land_Sampling_Sheme_Point list query success");
	   }catch(Exception e){
		   super.TimeResult("1001", "查询失败", list, request, response);
		   logger.error("table T_Land_Sampling_Sheme_Point list query failed:"+e.getMessage());
	   }
	   
   }
   @RequestMapping(value = "doUpdate", method = RequestMethod.POST)
   public void  update(LandSamplingSchemePoint  landSamplingSchemePoint,HttpServletRequest request, HttpServletResponse response){
	   if(!super.validateUpdate(landSamplingSchemePoint, request, response)){
		   return;
	   }
	   List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
	   try{ 
		   landSamplingSchemePoint.setUpdateId(super.getLoginId(request));
		   landSamplingSchemePoint.setUpdateTime(new Date());
	       landSampingSchemePointService.updateLandSamplingSchemePointById(landSamplingSchemePoint);
	       resultList = landSampingSchemePointService.getLandSamplingSchemePointById(landSamplingSchemePoint.getId());
		   super.dataResult("1000", "修改成功",resultList, request, response);
		   logger.info("table T_Land_Sampling_Sheme_Point update success");
	   }catch(Exception e){
		   super.dataResult("1001", "修改失败",resultList, request, response);
		   logger.error("table T_Land_Sampling_Sheme_Point update failed:"+e.getMessage());
	   }
   }
   @RequestMapping(value = "doAdd", method = RequestMethod.POST)
	public void doAdd(LandSamplingSchemePoint info, HttpServletRequest request, HttpServletResponse response) {
	   if(!super.validateAdd(info, request, response)){
		   return;
	   }
	   List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
	   try{
		   String id=UuidGenerateUtil.getUUIDLong();
		   info.setId(id);
		   info.setCreateId(super.getLoginId(request));
		   info.setCode(UuidGenerateUtil.getUUCODE());
		   info.setCreateTime(new Date());
		   info.setIsSamplingPoint(1);
		   info.setIsRelease(1);
		   info.setCheckStatus(1);
		   landSampingSchemePointService.insertSelective(info);
		   resultList = landSampingSchemePointService.getLandSamplingSchemePointById(id);
		   super.dataResult("1000", "添加成功",resultList, request, response);
		   logger.info("table T_Land_Sampling_Sheme_Point add success");
	   }catch(Exception e){
		   super.dataResult("1001", "添加失败",resultList, request, response);
		   logger.error("table T_Land_Sampling_Sheme_Point add failed:"+e.getMessage());
	   }
   }
   @RequestMapping(value ="queryBySampling", method = RequestMethod.POST )
   public void findSampling(String schemeId,HttpServletRequest request, HttpServletResponse response){
	   if(StringUtils.isEmpty(schemeId)) {
			super.simpleResult("1001", "缺失查询参数", request, response);
			return;
		}
	   List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
	   try{
	    
		   List<LandSamplingSchemePoint> list = landSampingSchemePointService.getLandSamplingSchemePointByPlanId(super.getQueryParams(request, "schemeId"));
		   for(LandSamplingSchemePoint info:list){
	    		Map<String,Object> map = new HashMap<String,Object>();
	    		map.put("unique", info.getId());
	    		map.put("data", info);
	    		resultList.add(map);
	    	}
	    	super.dataResult("1000", "查询成功", resultList, request, response);
	     
	    logger.info("table T_Land_Sampling_Sheme_Point list query success");
	   }catch(Exception e){
		   super.simpleResult("1001", "查询失败", request, response);
		   logger.error("table T_Land_Sampling_Sheme_Point list query failed:"+e.getMessage());
	   }
	   
   }
}
