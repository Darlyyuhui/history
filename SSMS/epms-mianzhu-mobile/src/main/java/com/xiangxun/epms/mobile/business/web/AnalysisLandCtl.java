package com.xiangxun.epms.mobile.business.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiangxun.epms.mobile.business.domain.AnalysisLand;
import com.xiangxun.epms.mobile.business.service.AnalysisLandService;
import com.xiangxun.epms.mobile.business.util.Page;
import com.xiangxun.epms.mobile.business.util.RegionUtil;

@Controller
@RequestMapping(value="samply/server/land/analysis")
public class AnalysisLandCtl extends BaseCtl {
      @Resource
      AnalysisLandService analysisLandService;
      @RequestMapping(value="query" ,method = RequestMethod.POST)
      public void query(HttpServletRequest request, HttpServletResponse response){
    	  AnalysisLand analysisLand =new AnalysisLand();
    	  String regionId=request.getParameter("regionId");
    	  if(StringUtils.isEmpty(regionId)) {
    		  String regionName = request.getParameter("regionName");
     	     if (StringUtils.isEmpty(regionName)) {
     	    	 regionId= null;
     		}else{
     			 regionId= RegionUtil.getRegionId(regionName);
     		}
    	  }
    	  super.pageParams(request);
    	    try{
    	      analysisLand.setRegionId(regionId);
    	      String name =request.getParameter("type_name");
    	      if(name!=null&&!"".equals(name)){
    	    	  analysisLand.setName(name); 
    	      }
    	      String isOver=request.getParameter("isOver");
    	      if(isOver!=null&&!"".equals(isOver)){
    	    	  
    	    	  analysisLand.setIsOver(Integer.valueOf(isOver));
    	    		  
    	    	  
    	      }else{
    	    	  analysisLand.setIsOver(null); 
    	      }
    	      String temple=request.getParameter("analy_name");
    	      Page page =analysisLandService.selectByCondition(analysisLand,temple,pageSize, pageNo);
    		  super.pageResult("1000", "查询成功", regionId==null?"":regionId,page, request, response);
    		  
    		  logger.info("analysisLand query success");
    	  }catch(Exception e){
    		  logger.error(e.getMessage());
    		  super.simpleResult("1001", "查询失败", request, response);
    	  }
      }
      @RequestMapping(value="select" ,method = RequestMethod.POST)
      public void selectByOther(AnalysisLand analysisLand,HttpServletRequest request, HttpServletResponse response){
    	  analysisLand.setName(request.getParameter("type_name"));
    	  String temple=request.getParameter("analy_name");
    	  super.pageParams(request);
    	  try{
    		  Page page  =analysisLandService.selectByCondition(analysisLand,temple,pageSize, pageNo);
    		  super.pageResult("1000", "查询成功",null, page, request, response);
    		  logger.info("analysisLand query success");
    	  }catch(Exception e){
    		  logger.error(e.getMessage());
    		  super.simpleResult("1001", "查询失败", request, response);
    	  }
      }
}
