package com.xiangxun.ywpt.mobile.business.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangxun.ywpt.mobile.business.domain.AssetInfo;
import com.xiangxun.ywpt.mobile.business.service.CabInfoService;
import com.xiangxun.ywpt.mobile.business.service.DatabaseInfoService;
import com.xiangxun.ywpt.mobile.business.service.DeviceInfoService;
import com.xiangxun.ywpt.mobile.business.service.FtpInfoService;
import com.xiangxun.ywpt.mobile.business.service.ProjectInfoService;
import com.xiangxun.ywpt.mobile.business.service.ServerInfoService;
import com.xiangxun.ywpt.mobile.business.util.Page;


@Controller
@RequestMapping(value = "server/device/refer")
public class SearchDeviceCtl extends BaseCtl {

	@Resource
	DeviceInfoService deviceInfoService;
	
	@Resource
	DatabaseInfoService databaseInfoService;
	
	@Resource
	ProjectInfoService projectInfoService;
	
	@Resource
	FtpInfoService ftpInfoService;
	
	@Resource
	ServerInfoService serverInfoService;
	
	@Resource
	CabInfoService cabInfoService;
	
	/**
	 * 给app端返回设备的分页数据(device,ftp,project,server,database)
	 * @author MIAOXU
	 *
	 */
	@RequestMapping(value="searchDevice")
	public void searchDevice(HttpServletRequest request,HttpServletResponse response){
		super.pageParams(request);	
		AssetInfo assetInfo =new AssetInfo();
		assetInfo.setAssettype(request.getParameter("type"));
		Page page = deviceInfoService.queryList(assetInfo, pageSize, pageNo);

		super.pageResult("1", "查询成功", page, request, response);
	}
	
	/**
	 * 在巡视管理中,根据设备编号或者设备名称查询一个设备信息,返回给app端
	 * @author MIAOXU
	 *
	 */
	@RequestMapping(value="searchOneDevice")
	public void searchOneDevice(HttpServletRequest request,HttpServletResponse response){
		List<AssetInfo> assetList = new ArrayList<>();

		if("device".equals(request.getParameter("type"))){
			if(request.getParameter("name") !=""){
				//将查询条件放入对象
				AssetInfo AssetInfo = new AssetInfo();
				//AssetInfo.setCode(request.getParameter("code"));
				AssetInfo.setAssetname("%" + request.getParameter("name") + "%");
				AssetInfo.setAssettype(request.getParameter("type"));
				assetList =  deviceInfoService.deviceDetails(AssetInfo);
				super.dataResult("1", "查询成功", assetList, request, response);
				
			}else{
				super.dataResult("1", "请输入查询条件", assetList, request, response);

			}
 		}
		if("server".equals(request.getParameter("type"))){
			//将查询条件放入对象
			AssetInfo AssetInfo = new AssetInfo();
			AssetInfo.setAssetname("%" + request.getParameter("name") + "%");
			AssetInfo.setAssettype(request.getParameter("type"));
			assetList =  deviceInfoService.serverDetails(AssetInfo);
			super.dataResult("1", "查询成功", assetList, request, response);			
		}
		if("cabinet".equals(request.getParameter("type"))){
			if(request.getParameter("name") !=""){
				//将查询条件放入对象
				AssetInfo AssetInfo = new AssetInfo();
				//AssetInfo.setCode(request.getParameter("code"));
				AssetInfo.setAssetname("%" + request.getParameter("name") + "%");

				AssetInfo.setAssettype("cabinet");
				
				assetList =  deviceInfoService.cabinefDetails(AssetInfo);
				super.dataResult("1", "查询成功", assetList, request, response);
				
			}else{
				super.dataResult("1", "请输入查询条件", assetList, request, response);
			}	
		}
		if("database".equals(request.getParameter("type"))){
			//将查询条件放入对象
			if(request.getParameter("name") !=""){
				AssetInfo AssetInfo = new AssetInfo();
				AssetInfo.setAssetname("%" + request.getParameter("name") + "%");
				AssetInfo.setAssettype(request.getParameter("type"));
				
				assetList =  deviceInfoService.databaseDetails(AssetInfo);
				
				super.dataResult("1", "查询成功", assetList, request, response);
				
			}else{
				super.dataResult("1", "请输入查询条件", assetList, request, response);
			}			
		}
		
		if("ftp".equals(request.getParameter("type"))){
			
			if(request.getParameter("name") != ""){
				//将查询条件放入对象
				AssetInfo AssetInfo = new AssetInfo();
				AssetInfo.setAssetname("%" + request.getParameter("name") + "%");
				AssetInfo.setAssettype(request.getParameter("type"));
				
				assetList =  deviceInfoService.ftpDetails(AssetInfo);
				super.dataResult("1", "查询成功", assetList, request, response);
				
			}else{
				super.dataResult("1", "请输入查询条件", assetList, request, response);
			}
		}
		
		if("project".equals(request.getParameter("type"))){
			if(request.getParameter("name") != ""){
				//将查询条件放入对象
				AssetInfo AssetInfo = new AssetInfo();
				AssetInfo.setAssetname("%" + request.getParameter("name") + "%");
				AssetInfo.setAssettype(request.getParameter("type"));
				
				assetList =  deviceInfoService.projectDetails(AssetInfo);
				super.dataResult("1", "查询成功", assetList, request, response);
			}else{
				super.dataResult("1", "请输入查询条件", assetList, request, response);

			}
		}
		super.dataResult("1", "无设备", assetList, request, response);
	}
	
}
