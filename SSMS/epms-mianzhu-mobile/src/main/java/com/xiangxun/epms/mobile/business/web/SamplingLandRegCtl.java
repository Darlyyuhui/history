package com.xiangxun.epms.mobile.business.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.epms.mobile.business.domain.LandBlock;
import com.xiangxun.epms.mobile.business.domain.LandSamplingSchemePoint;
import com.xiangxun.epms.mobile.business.domain.LandSamplingSheme;
import com.xiangxun.epms.mobile.business.domain.SamplingLandReg;
import com.xiangxun.epms.mobile.business.domain.SamplingReg;
import com.xiangxun.epms.mobile.business.service.LandBlockService;
import com.xiangxun.epms.mobile.business.service.LandSampingSchemePointService;
import com.xiangxun.epms.mobile.business.service.LandSamplingShemeService;
import com.xiangxun.epms.mobile.business.service.LandTypeService;
import com.xiangxun.epms.mobile.business.service.SamplingLandRegService;
import com.xiangxun.epms.mobile.business.util.UuidGenerateUtil;

@Controller
@RequestMapping("samply/server/land/reg")
public class SamplingLandRegCtl extends BaseCtl {
	@Resource
	SamplingLandRegService samplingLandRegService;
	@Resource
	LandSampingSchemePointService landSampingSchemePointService;
	@Resource
	LandSamplingShemeService landSamplingShemeService;
	@Resource
	LandTypeService landTypeService;
	@Resource
	LandBlockService LandBlockService;


	@RequestMapping(value = "collect", method = RequestMethod.POST)
	public void add(SamplingLandReg info,String samplingType, HttpServletRequest request, HttpServletResponse response) {
		if (!super.validateAdd(info, request, response)) {
			return;
		}
		try {
			String id = UuidGenerateUtil.getUUIDLong();
			info.setId(id);
			info.setCode(UuidGenerateUtil.getUUCODE());
			info.setCreateId(super.getLoginId(request));
			info.setCreateTime(new Date());
			info.setSamplingSource("2");
			info.setSamplingType(samplingType);
			info.setSamplingTime(new Date());
			info.setSamplingUser(super.getLoginData(request).getName());
			samplingLandRegService.insertSelective(info);
			LandSamplingSchemePoint point = new LandSamplingSchemePoint();
			point.setId(info.getPointId());
			point.setIsSampling(1);
			landSampingSchemePointService.updateLandSamplingSchemePointById(point);
			List<SamplingReg> list = samplingLandRegService.selectByPrimaryKey(id);
			super.dataResult("1000", "添加成功", list, request, response);
			logger.info("table T_Sampling_land_reg add success");
		} catch (Exception e) {
			super.TimeResult("1001", "添加失败", null, request, response);
			e.printStackTrace();
			logger.error("table T_Sampling_land_reg add failed", e.getMessage());
		}
	}
  
	@RequestMapping(value = "uploud", method = RequestMethod.POST)
	public void uploud(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest file) {

		try {
			samplingLandRegService.saveFile(file);
			super.simpleResult("1000", "上传成功", request, response);
			logger.info("table T_Sampling_land_reg add uploadfile successs");
		} catch (Exception e) {
			super.simpleResult("1001", "上传失败", request, response);
			logger.error("table T_Sampling_land_reg add uploadfile failed" + e.getMessage());
		}

	}

	@RequestMapping(value = "select", method = RequestMethod.POST)
	public void select(String missionId, HttpServletRequest request, HttpServletResponse response) {

		try {
			List<Map<String, Object>> list = samplingLandRegService.selectByMissionId(missionId);
			super.dataResult("1000", "查询成功", list, request, response);
			logger.info(" reg query by missionId  successs");
		} catch (Exception e) {
			super.simpleResult("1001", "查询失败", request, response);
			logger.error(" reg query by missionId  failed" + e.getMessage());
		}

	}

	@RequestMapping(value = "showView", method = RequestMethod.POST)
	public void view(SamplingLandReg it, HttpServletRequest request, HttpServletResponse response) {

		try {
			List<SamplingReg> list = samplingLandRegService.particular(it);
			SamplingReg info = new SamplingReg();
			if (list != null && list.size() > 0) {
				info = list.get(0);
			}
			LandSamplingSheme sheme = landSamplingShemeService.findById(it.getSchemeId());
			if (sheme != null) {
				List<LandBlock> blist = LandBlockService.findById(sheme.getBlockId());
				if (blist != null && blist.size() > 0) {
					info.setBlockName(blist.get(0).getName());
				}
				info.setRegionName(sheme.getRegionName());
				info.setSampleName(sheme.getSampleName());
			} else {
				info.setBlockName("");
				info.setRegionName("");
				info.setSampleName("");
			}
			if (info.getSampleName() == null) {
				info.setSampleName("");
			}

			super.dataResult("1000", "查询成功", info, request, response);
			logger.info("table T_Sampling_land_reg query by id  successs");
		} catch (Exception e) {
			super.simpleResult("1001", "查询失败", request, response);
			logger.error("table T_Sampling_land_reg query by Id  failed" + e.getMessage());
		}

	}
	  

}
