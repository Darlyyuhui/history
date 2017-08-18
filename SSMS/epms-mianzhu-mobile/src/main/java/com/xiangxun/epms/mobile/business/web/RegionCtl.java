package com.xiangxun.epms.mobile.business.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangxun.epms.mobile.business.domain.Region;
import com.xiangxun.epms.mobile.business.service.RegionService;

@Controller
@RequestMapping(value="samply/server/region")
public class RegionCtl extends BaseCtl {
  @Resource
  RegionService regionService;
  @RequestMapping(value = "queryAll")
  public  void  queryAll(HttpServletRequest request, HttpServletResponse response){
	  String p="绵竹市九龙镇";
	  List< Region> list=regionService.getAllRegionByName(p);
	 // List< Region> list =regionService.getAllRegion();
	  super.dataResult("1000", "查询成功", list, request, response);
  }
}
