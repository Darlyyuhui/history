package com.xiangxun.epms.mobile.business.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiangxun.epms.mobile.business.domain.SamplingReg;
import com.xiangxun.epms.mobile.business.service.SamplingRegService;

@Controller
@RequestMapping("samply/server/reg")
public class SamplingRegCtl extends BaseCtl {
	@Resource
	SamplingRegService  samplingRegService;
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void add(SamplingReg info, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isEmpty(request.getParameter("samplingCode"))) {
			super.simpleResult("1001", "缺失参数samplingCode", request, response);
			return;
		}
		try{
			List<SamplingReg> list=samplingRegService.insertSelective(request);
			super.dataResult("1000", "添加成功",list, request, response);
			logger.info("reg add sucess");
		}catch(Exception e){
			logger.error(e.getMessage());
			super.simpleResult("1001", "添加失败", request, response);
			logger.info("reg add failed");
		}
		//samplingUser,samplingSource,missionId,sampleName,tableName
	}
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public void query(String code, HttpServletRequest request, HttpServletResponse response) {
		String missionId=request.getParameter("missionId");
		String samplingCode=request.getParameter("sampleCode");
		if (StringUtils.isEmpty(samplingCode)) {
			super.simpleResult("1001", "缺失参数samplingCode", request, response);
			return;
		}
		if (StringUtils.isEmpty(missionId)) {
			super.simpleResult("1001", "缺失参数missionId", request, response);
			return;
		}
		try{
			List<SamplingReg> list=samplingRegService.selectFindByMissionId(missionId,samplingCode,code);
			super.dataResult("1000", "查询成功",list, request, response);
			logger.info("reg query sucess");
		}catch(Exception e){
			logger.error(e.getMessage());
			logger.info("reg query failed");
			super.simpleResult("1001", "查询失败", request, response);
		}
		
	}
	@RequestMapping(value = "view", method = RequestMethod.POST)
	public void view(SamplingReg info, HttpServletRequest request, HttpServletResponse response) {
		String missionId=request.getParameter("missionId");
		String id=request.getParameter("id");
		String tableName = request.getParameter("tableName");
		if (StringUtils.isEmpty(id)) {
			super.simpleResult("1001", "缺失参数id", request, response);
			return;
		}
		if (StringUtils.isEmpty(missionId)) {
			super.simpleResult("1001", "缺失参数tableName", request, response);
			return;
		}
		try{
			SamplingReg reg=samplingRegService.selectFindById(tableName,id,missionId);
			super.dataResult("1000", "查询成功",reg, request, response);
			logger.info("reg view sucess");
		}catch(Exception e){
			logger.error(e.getMessage());
			logger.info("reg view failed");
			super.simpleResult("1001", "查询失败", request, response);
		}
		
	}
}
