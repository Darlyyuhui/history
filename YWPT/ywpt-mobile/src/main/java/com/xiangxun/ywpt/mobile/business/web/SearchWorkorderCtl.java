package com.xiangxun.ywpt.mobile.business.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangxun.ywpt.mobile.business.domain.WorkorderInfo;
import com.xiangxun.ywpt.mobile.business.service.WorkorderInfoService;
import com.xiangxun.ywpt.mobile.business.util.Page;

@Controller
@RequestMapping(value = "server/workorder/refer")
public class SearchWorkorderCtl extends BaseCtl {

	@Resource
	WorkorderInfoService workorderInfoService;

	/***
	 * APP端查询所有此人的工单,返回
	 * 
	 * @param menuid
	 *            //查询分页工单信息
	 * @param model
	 * @return
	 */

	@RequestMapping(value="details")
	public void list(HttpServletRequest request,HttpServletResponse response) {
		
		super.pageParams(request);
		
		WorkorderInfo workorderInfo = new WorkorderInfo();
		
		if(request.getParameter("status") != null && !"".equals(request.getParameter("status"))){
			workorderInfo.setStatus(request.getParameter("status"));
		}
		if(request.getParameter("devicecode") != null && !"".equals(request.getParameter("devicecode"))){
			workorderInfo.setDevicecode("%" + request.getParameter("devicecode") + "%");
		}
		if(request.getParameter("devicename") != null && !"".equals(request.getParameter("devicename"))){
			workorderInfo.setDevicename("%" + request.getParameter("devicename") + "%");
		}
		if(request.getParameter("deviceip") != null && !"".equals(request.getParameter("deviceip"))){
			workorderInfo.setDeviceip("%" + request.getParameter("deviceip") + "%");
		}
		if(request.getHeader("userId") != null && !"".equals(request.getHeader("userId"))){
			workorderInfo.setContact(request.getHeader("userId"));
		}

		
		Page page = workorderInfoService.queryList(workorderInfo, pageSize, pageNo);
		List<WorkorderInfo> workorderInfoList = page.getResult();
		
		for (WorkorderInfo workorder : workorderInfoList) {
			if(workorder.getMapx() == null){
				workorder.setMapx("");
			}
			if(workorder.getMapy() == null){
				workorder.setMapy("");
			}
			if(workorder.getPhoto1() == null){
				workorder.setPhoto1("");
			}
			if(workorder.getPhoto2() == null){
				workorder.setPhoto2("");
			}
			if(workorder.getPhoto3() == null){
				workorder.setPhoto3("");
			}
			if(workorder.getCompanyname() == null){
				workorder.setCompanyname("");
			}
			if(workorder.getOrgname() == null){
				workorder.setOrgname("");
			}
			if(workorder.getContactname() == null){
				workorder.setContactname("");
			}
			if(workorder.getReason() == null){
				workorder.setReason("");
			}
			if(workorder.getNote() == null){
				workorder.setNote("");
			}
			if(workorder.getIsleave() == null){
				workorder.setIsleave("");
			}
			if(workorder.getIsouter() == null){
				workorder.setIsouter("");
			}
			if(workorder.getExceptionid() == null){
				workorder.setExceptionid("");
			}
			if(workorder.getOrgname() == null){
				workorder.setOrgname("");
			}
			if ("database".equals(workorder.getDevicetype()) || "ftp".equals(workorder.getDevicetype()) || "project".equals(workorder.getDevicetype())) {
				workorder.setDevicecode("");
			}
			
			//若存在图片则将信息('1' '2' '3')返回给APP端,app端需要查看图片时重新发送请求
			if(workorder.getPicture1() != null){
				workorder.setPicture1("1".getBytes());
			}
			if(workorder.getPicture2() != null){
				workorder.setPicture2("2".getBytes());
			}
			if(workorder.getPicture3() != null){
				workorder.setPicture3("3".getBytes());
			}
		}
		super.pageResult("1", "查询成功", page, request, response);
	}
	
	
	/***
	 * APP端查询图片
	 * app端巡检工单和工单里面查询图片都使用此方法
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping(value="watchPicture")
	public void watchPicture(HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println("正在获取图片...");
		
		//查询条件放入对象中
		WorkorderInfo workorderInfo = new WorkorderInfo();
		workorderInfo.setId(request.getParameter("id"));
		
		//接收查询出来的对象
		WorkorderInfo workorder = workorderInfoService.watchPicture(workorderInfo);

		if(workorder == null){
			super.simpleResult("0","该工单没有图片", request, response);
			
		}else{
			String[] picture = new String[3];
			if(workorder.getPicture1() != null){
				picture[0] =new String(workorder.getPicture1());
			}
			if(workorder.getPicture2() != null){
				picture[1] =new String(workorder.getPicture2());
			}
			if(workorder.getPicture3() != null){
				picture[2] =new String(workorder.getPicture3());
			}
			super.dataResult("1", "查询成功",picture, request, response);			
		}		
	}
	
	/***
	 * APP端查询总工单数
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping(value="totalCount")
	public void totalCount(HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println("app端进行总工单条数的测试................");
		
		WorkorderInfo workorderInfo = new WorkorderInfo();
		//0:代表已派发....app端查询已派发工单
		workorderInfo.setStatus("0");
		if(request.getParameter("status") != null && !"".equals(request.getParameter("status"))){
			workorderInfo.setStatus("0");
		}
		if(request.getParameter("devicecode") != null && !"".equals(request.getParameter("devicecode"))){
			workorderInfo.setDevicecode("%" + request.getParameter("devicecode") + "%");
		}
		if(request.getParameter("devicename") != null && !"".equals(request.getParameter("devicename"))){
			workorderInfo.setDevicename("%" + request.getParameter("devicename") + "%");
		}
		if(request.getParameter("deviceip") != null && !"".equals(request.getParameter("deviceip"))){
			workorderInfo.setDeviceip("%" + request.getParameter("deviceip") + "%");
		}
		if(request.getHeader("userId") != null && !"".equals(request.getHeader("userId"))){
			workorderInfo.setContact(request.getHeader("userId"));
		}
		 int totalCount = workorderInfoService.searchTotalCount(workorderInfo);
		 super.dataResult("1", "查询成功",totalCount, request, response);
	}
	
	/***
	 * APP端滚动功能查询总工单
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping(value="totalWorkOrder")
	public void totalWorkOrder(HttpServletRequest request,HttpServletResponse response){
		
		System.out.println("app端进行工单滚动展示.....");
		
		WorkorderInfo workorderInfo = new WorkorderInfo();
		//0:代表已派发....app端查询已派发工单
		workorderInfo.setStatus("0");
		if(request.getParameter("status") != null && !"".equals(request.getParameter("status"))){
			workorderInfo.setStatus("0");
		}
		if(request.getHeader("userId") != null && !"".equals(request.getHeader("userId"))){
			workorderInfo.setContact(request.getHeader("userId"));
		}

		List<WorkorderInfo> list = workorderInfoService.totalWorkOrder(workorderInfo);

		super.dataResult("1", "查询成功",list, request, response);
	}
	
}
