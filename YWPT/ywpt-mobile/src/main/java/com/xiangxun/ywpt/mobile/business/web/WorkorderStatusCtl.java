package com.xiangxun.ywpt.mobile.business.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiangxun.ywpt.mobile.business.domain.ExceptionInfo;
import com.xiangxun.ywpt.mobile.business.domain.User;
import com.xiangxun.ywpt.mobile.business.domain.WorkorderInfo;
import com.xiangxun.ywpt.mobile.business.domain.WorkorderLog;
import com.xiangxun.ywpt.mobile.business.service.ExceptionInfoService;
import com.xiangxun.ywpt.mobile.business.service.UserService;
import com.xiangxun.ywpt.mobile.business.service.WorkorderLogService;
import com.xiangxun.ywpt.mobile.business.service.WorkorderStatusService;
import com.xiangxun.ywpt.mobile.business.service.impl.ExceptionInfoServiceImpl;
import com.xiangxun.ywpt.mobile.business.util.UuidGenerateUtil;

@Controller
@RequestMapping(value = "server/workorder/change")
public class WorkorderStatusCtl extends BaseCtl {

	@Resource
	WorkorderStatusService workorderStatusService;
	@Resource
	WorkorderLogService worderLogService;
	@Resource
	ExceptionInfoService exceptionInfoservice;
	@Resource UserService userService;
	/***
	 * app端运维人员接收或拒绝接收工单,状态的更改及原因的说明
	 * @param 
	 * @return 
	 */
	@RequestMapping(value="status")
	public void ChangeStatus(HttpServletRequest request,HttpServletResponse response){
		
		System.out.println("正在更改工单状态");
		WorkorderInfo workorderInfo = new WorkorderInfo();
		workorderInfo.setStatus(request.getParameter("status"));
		workorderInfo.setReason(request.getParameter("reason"));

		workorderInfo.setId(request.getParameter("id"));
		

		workorderStatusService.changeWorkorderStatus(workorderInfo);
		//日志记录
		WorkorderLog log = new WorkorderLog();
		log.setId(UuidGenerateUtil.getUUID());
		log.setOperator(request.getHeader("userid"));
		log.setAccount(request.getHeader("loginName"));
		log.setOpertime(new Date());
		log.setOpercontent(request.getParameter("status").equals("1")?"工单接收":"工单退回");
		log.setWorkstatus(request.getParameter("status"));
		log.setWorkid(request.getParameter("id"));
		log.setNote(request.getParameter("reason"));
		worderLogService.save(log);
		super.simpleResult("1", "工单的信息更改成功", request, response);

	}
	/***
	 * app端运维人员上报工单,分为正常上报()和异常上报()
	 * @param 
	 * @return
	 */
	@RequestMapping(value="workorderUp")
	public void workorderUp(HttpServletRequest request,HttpServletResponse response){
		
		System.out.println("正在更改工单状态");
		ExceptionInfo exc=new ExceptionInfo();
		WorkorderInfo workorderInfo = new WorkorderInfo();		
			workorderInfo.setId(request.getParameter("id"));
			workorderInfo.setStatus(request.getParameter("status"));
			workorderInfo.setNote(request.getParameter("note"));
			workorderInfo.setReason(request.getParameter("reason"));
			String exceptionid=UuidGenerateUtil.getUUID();
			workorderInfo.setExceptionid(exceptionid);
			workorderStatusService.changeWorkorderStatus(workorderInfo);
			//存入上报(alarm_Exception_Info)表中
			exc.setContent(request.getParameter("reason"));
			exc.setId(exceptionid);
			Date date=new Date();
			exc.setReporttime(date);
			User user=userService.getUserByAccount(request.getHeader("loginName"));
			exc.setOperator(user.getName());
			exceptionInfoservice.save(exc);
			
			
		
		
		//日志记录
				WorkorderLog log = new WorkorderLog();
				log.setId(UuidGenerateUtil.getUUID());
				log.setOperator(request.getHeader("userid"));
				log.setAccount(request.getHeader("loginName"));
				log.setOpertime(new Date());
				log.setOpercontent(request.getParameter("status").equals("8")?"正常上报":"异常上报");
				log.setWorkstatus(request.getParameter("status"));
				log.setWorkid(request.getParameter("id"));
				log.setNote(request.getParameter("reason"));
				worderLogService.save(log);
		super.simpleResult("1", "工单的信息更改成功", request, response);

		
		
	}
	
	/***
	 * app端运维人员上传图片
	 * @param 
	 * @return
	 */
	@RequestMapping(value="upLoadPicture", method = RequestMethod.POST)
	public void upLoadPicture(@RequestBody Map<String, String> pictureDetails,HttpServletRequest request,HttpServletResponse response){
		
		System.out.println("正在上传图片");
		
		WorkorderInfo workorderInfo = new WorkorderInfo();	
		//workorderInfo.setId(request.getParameter("id"));
		workorderInfo.setId(pictureDetails.get("id"));
		//保存图片(byte[]数组存放的是二进制格式的)
		byte[] picture1;
		byte[] picture2;
		byte[] picture3;
		
		try {
			 picture1 = pictureDetails.get("picture1").getBytes();			
			if(picture1 != null){
				workorderInfo.setPicture1(picture1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			picture2 = pictureDetails.get("picture2").getBytes();
			if(picture2 != null){
				workorderInfo.setPicture2(picture2);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			picture3 = pictureDetails.get("picture3").getBytes();
			if(picture3 != null){
				workorderInfo.setPicture3(picture3);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		workorderStatusService.upLoadPicture(workorderInfo);	
		super.simpleResult("1", "图片上传成功", request, response);
	}
}
