package com.xiangxun.epms.mobile.business.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.epms.mobile.business.domain.LandBlockError;
import com.xiangxun.epms.mobile.business.service.FilesService;
import com.xiangxun.epms.mobile.business.service.LandBlockErrorService;
import com.xiangxun.epms.mobile.business.util.Page;
import com.xiangxun.epms.mobile.business.util.RegionUtil;
import com.xiangxun.epms.mobile.business.util.UuidGenerateUtil;

@Controller
@RequestMapping(value="samply/server/land/error")
public class LandBlockErrorCtl extends BaseCtl {
     @Resource
     LandBlockErrorService landBlockErrorService;
     @Resource 
     FilesService filesService;
     @RequestMapping(value="queryAll", method = RequestMethod.POST)
     public void query(LandBlockError it,HttpServletRequest request,HttpServletResponse response){
    	try{
    	  super.pageParams(request);
    	  String regionId = request.getParameter("regionId");
			if (regionId == null || "".equals(regionId)) {
				String regionName = request.getParameter("regionName");
				if (StringUtils.isEmpty(regionName)) {
					regionId = null;
				} else {
					regionId = RegionUtil.getRegionId(regionName);
				}
			}
		  it.setRegionId(regionId);
    	  Page page=landBlockErrorService.getListByCondition(it,pageSize, pageNo);
    	  super.pageResult("1000", "查询成功",regionId==null?"":regionId, page, request, response);
    	  logger.info("landBlockError query success");
    	}catch(Exception  e){
    		logger.error("landBlockError query failed"+e.getMessage());
    		super.simpleResult("1001", "查询失败", request, response);
    	}
     }
     
	 @RequestMapping(value="doAdd", method = RequestMethod.POST)
     public void add(LandBlockError it,HttpServletRequest request,HttpServletResponse response,MultipartHttpServletRequest file) {
    	  super.validateAdd(it, request, response);
        try{
    	     LandBlockError info = new LandBlockError();
    	     info.setId(UuidGenerateUtil.getUUIDLong());
    	     info.setDescribe(file.getParameter("describe"));
    	     info.setLandBlockId(file.getParameter("landBlockId"));
    	     info.setLongitude( new BigDecimal(file.getParameter("longitude")));
    	     info.setLatitude( new BigDecimal(file.getParameter("latitude")));
    	     info.setErrorTime(new Date());
    	     info.setCreateId(super.getLoginId(request));
    	     info.setCreateTime(new Date());
    	     landBlockErrorService.saveInfo(info, file);
    	     super.simpleResult("1000", "添加成功", request, response);
    	     logger.info("LandBlockError add success");
    	}catch(Exception  e){
    		logger.error(e.getMessage());
    		super.simpleResult("1001", "添加失败", request, response);
    		logger.error("LandBlockError add failed");
    	}
     }
     @RequestMapping(value="showView", method = RequestMethod.POST)
     public void view(LandBlockError it,HttpServletRequest request,HttpServletResponse response){
    	try{
    		String id = request.getParameter("id");
    		LandBlockError info = landBlockErrorService.selectByPrimaryKey(id);
    	    super.dataResult("1000", "查询成功", info, request, response);
    	}catch(Exception  e){
    		logger.error(e.getMessage());
    		super.simpleResult("1001", "查询失败", request, response);
    	}
     }
     @RequestMapping(value="update", method = RequestMethod.POST)
     public void update(LandBlockError it,HttpServletRequest request,HttpServletResponse response){
    	try{
    		it.setUpdateId(super.getLoginId(request));
		    it.setUpdateTime(new Date());
    	    landBlockErrorService.updateByPrimaryKeySelective(it);;
    	    LandBlockError landBlockError=landBlockErrorService.selectByPrimaryKey(it.getId());
    	    List<LandBlockError> list=new ArrayList<LandBlockError>();
    	    list.add(landBlockError);
    	  super.dataResult("1000", "修改成功", list, request, response);
    	}catch(Exception  e){
    		logger.error(e.getMessage());
    		super.simpleResult("1001", "查询失败", request, response);
    	}
     }
}
