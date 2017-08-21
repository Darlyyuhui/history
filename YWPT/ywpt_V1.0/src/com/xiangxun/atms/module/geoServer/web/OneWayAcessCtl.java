package com.xiangxun.atms.module.geoServer.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.module.geoServer.domain.OneWayAcessInfo;
import com.xiangxun.atms.module.geoServer.service.OneWayAcessService;

@Controller
@RequestMapping(value = "openmap/oneWayAcess")
public class OneWayAcessCtl  extends BaseCtl{
	@Resource 
	OneWayAcessService oneWayAcessServices;
	// 单行道信息查询
	/**
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="search")
	@ResponseBody
	public List<OneWayAcessInfo> oneWayAcessList(String name) {	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name",name);
		List<OneWayAcessInfo> list=oneWayAcessServices.searchOneWayAcessInfo(map);
		System.out.println(list);
		return oneWayAcessServices.searchOneWayAcessInfo(map);
	}
	//单行道信息插入
	@RequestMapping(value="addoneWayAcess")
	@ResponseBody
	public String addoneWayAcessInfo(String name,String starttime,String endtime,String geometryText) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			map.put("starttime", starttime);
			map.put("endtime", endtime);
			map.put("geometryText", geometryText);	
			oneWayAcessServices.addOneWayAcessInfo(map);
		} catch (Exception e) {
			return returnErrorJson();
		}		
		return returnJson();	
	}
	//删除单行道信息
	
	@RequestMapping(value="deleteOneWayAcess")
	@ResponseBody
	public String deleteOneWayAcessInfo(Integer gid) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("gid", gid);	
			oneWayAcessServices.deleteOneWayAcess(map);
		} catch (Exception e) {
			return returnErrorJson();
		}		
		return returnJson();	
	}
	//更新单行道信息
	@RequestMapping(value="updateOneWayAcess")
	@ResponseBody
	public String updateOneWayAcess(String name,String starttime,String endtime,String geometryText,Integer gid) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			map.put("starttime", starttime);
			map.put("endtime", endtime);
			map.put("geometryText", geometryText);	
			map.put("gid", gid);	
			oneWayAcessServices.updateOneWayAcessInfo(map);
		} catch (Exception e) {
			return returnErrorJson();
		}		
		return returnJson();	
	}
}
