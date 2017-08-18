package com.xiangxun.ywpt.mobile.business.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiangxun.ywpt.mobile.business.domain.CabInfo;
import com.xiangxun.ywpt.mobile.business.domain.DeviceInfo;
import com.xiangxun.ywpt.mobile.business.domain.PerambulateInfo;
import com.xiangxun.ywpt.mobile.business.service.CabInfoService;
import com.xiangxun.ywpt.mobile.business.service.DeviceInfoService;
import com.xiangxun.ywpt.mobile.business.service.PerambulateInfoService;
import com.xiangxun.ywpt.mobile.business.util.Page;
import com.xiangxun.ywpt.mobile.business.util.UuidGenerateUtil;

@Controller
@RequestMapping(value = "server/perambulate/refer")
public class PerambulateInsertCtl extends BaseCtl {
	
	@Resource
	PerambulateInfoService perambulateInfoService;
	
	@Resource
	DeviceInfoService deviceInfoService;
	
	@Resource
	CabInfoService cabInfoService;
	
	
	/***
	 * APP端查看巡检工单
	 * 分页展示
	 * @return
	 */
	@RequestMapping(value="details")
	public void list(HttpServletRequest request,HttpServletResponse response) {
		
		super.pageParams(request);
		PerambulateInfo perambulateInfo = new PerambulateInfo();
		
		if(request.getHeader("userid") != null && !"".equals(request.getHeader("userid"))){
			perambulateInfo.setUserid(request.getHeader("userid"));
		}
		
		Page page = perambulateInfoService.queryList(perambulateInfo, pageSize, pageNo);
		
		//查询device和智能机柜的经纬度
		List<PerambulateInfo> perambulateInfos = page.getResult();
		
		for (PerambulateInfo perambulateInfo1 : perambulateInfos) {
			
			if("device".equals(perambulateInfo1.getAssettype())){
				DeviceInfo deviceInfo = deviceInfoService.deviceDetailByContions(perambulateInfo1);
				perambulateInfo1.setMapx(deviceInfo.getMapx());
				perambulateInfo1.setMapx(deviceInfo.getMapy());
			}
			
			if("cabinet".equals(perambulateInfo1.getAssettype())){

				CabInfo cabInfo = cabInfoService.cabDetailByContions(perambulateInfo1);
				perambulateInfo1.setMapx(cabInfo.getMapx());
				perambulateInfo1.setMapx(cabInfo.getMapy());
			}	
		}

		super.pageResult("1", "查询成功", page, request, response);
	}
	
	
	/***
	 * app端运维人员巡检后,新增巡检工单
	 * @param 
	 * @return
	 */
	@RequestMapping(value="perambulateUp", method = RequestMethod.POST)
	public void perambulateUp(@RequestBody Map<String, String> perambulateDetails,HttpServletRequest request,HttpServletResponse response) {
		System.out.println("正在进行巡检工单上传");

		PerambulateInfo perambulateInfo = new PerambulateInfo();
		
		//获取32位的uuid
		String UUID = UuidGenerateUtil.getUUIDLong();
		perambulateInfo.setId(UUID);
		//用户id
		perambulateInfo.setUserid(request.getHeader("userid"));
		//设备id
		perambulateInfo.setDeviceid( perambulateDetails.get("deviceid"));
		//原因
		perambulateInfo.setReason(perambulateDetails.get("reason"));
		//备注
		perambulateInfo.setNote(perambulateDetails.get("note"));
		//设备类型
		perambulateInfo.setAssettype(perambulateDetails.get("type"));
		//设备编码
		perambulateInfo.setCode(perambulateDetails.get("code"));
		//设备名字
		perambulateInfo.setDevicename(perambulateDetails.get("assetname"));
		//安装位置
		perambulateInfo.setDeviceplace(perambulateDetails.get("installplace"));
		//设备ip
		perambulateInfo.setIp(perambulateDetails.get("ip"));
		
		//保存图片(byte[]数组存放的是二进制格式的)
		byte[] picture1;
		byte[] picture2;
		byte[] picture3;
		
		try {
			 picture1 = perambulateDetails.get("picture1").getBytes();			
			if(picture1 != null){
				perambulateInfo.setPicture1(picture1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			picture2 = perambulateDetails.get("picture2").getBytes();
			if(picture2 != null){
				perambulateInfo.setPicture2(picture2);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			picture3 = perambulateDetails.get("picture3").getBytes();
			if(picture3 != null){
				perambulateInfo.setPicture3(picture3);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			picture3 = perambulateDetails.get("picture3").getBytes();
			if(picture3 != null){
				perambulateInfo.setPicture3(picture3);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		perambulateInfoService.perambulateUp(perambulateInfo);
		super.simpleResult("1", "巡检信息上传成功", request, response);		
	}

}
