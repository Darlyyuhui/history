package com.xiangxun.epms.mobile.business.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiangxun.epms.mobile.business.cache.AirPointCache;
import com.xiangxun.epms.mobile.business.cache.DicCache;
import com.xiangxun.epms.mobile.business.cache.RegBusinessTypeCache;
import com.xiangxun.epms.mobile.business.domain.AirPoint;
import com.xiangxun.epms.mobile.business.domain.Dic;
import com.xiangxun.epms.mobile.business.domain.RegType;
import com.xiangxun.epms.mobile.business.domain.SamplingLandReg;
import com.xiangxun.epms.mobile.business.service.RegTypeService;

@Controller
@RequestMapping("samply/server/reg")
public class RegTypeCtl extends BaseCtl{
    @Resource
    RegTypeService regTypeService;
	@RequestMapping(value = "getType", method = RequestMethod.POST)
	public void add(SamplingLandReg info,String soil_type, HttpServletRequest request, HttpServletResponse response) {
		String key = request.getParameter("sampleCode");
		if(StringUtils.isEmpty(key)){
			super.simpleResult("1001", "缺失采样名称", request, response);
			return;
		}
		String regType= RegBusinessTypeCache.DIC_REGCODE_TABLENAME.get(key);
		super.dataResult("1000", "查询成功", regType, request, response);
		}
	/**
	 * 获取采样下拉框的值
	 * @param mold 值为1时查询typeCode 值为2时查询samplingType 值为3时查询背景土壤的wallSource 值为4查询大气点位
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getSample", method = RequestMethod.POST)
	public void query(String mold,HttpServletRequest request, HttpServletResponse response) {
		String key = request.getParameter("sampleCode"); 
		if(StringUtils.isEmpty(key)||StringUtils.isEmpty(mold)){
			super.simpleResult("1001", "缺失参数", request, response);
			return;
		}
		try{
				List<Dic> list= new ArrayList<Dic>();
				if("1".equals(mold)){
					
					if("WATER".equals(key)){
						 list=DicCache.DIC_SIMPMAP.get(RegType.WATER_TYPECODE);
					}
					super.dataResult("1000", "查询成功", list, request, response);
					return;
					
				}
				if("2".equals(mold)){
					if("BJTR".equals(key)){
						 list=DicCache.DIC_SIMPMAP.get(RegType.BJTR_TYPECODE);
					}
					if("SD".equals(key)){
						 list=DicCache.DIC_SIMPMAP.get(RegType.SD_SAMPLINGTYPE);
					}
					if("NTTR".equals(key)){
						 list=DicCache.DIC_SIMPMAP.get(RegType.NTTR_SAMPLINGTYPE);
					}
					if("WATER".equals(key)){
						 list=DicCache.DIC_SIMPMAP.get(RegType.WATER_SAMPLINGTYPE);
					}
					super.dataResult("1000", "查询成功", list, request, response);
					return;
				}
				if("3".equals(mold)){
					if("BJTR".equals(key)){
						 list=DicCache.DIC_SIMPMAP.get(RegType.BJTR_WALLSOURCE);
					}
					super.dataResult("1000", "查询成功", list, request, response);
					return;
					
				}
				if("4".equals(mold)){
				
					List<AirPoint> airPointListist=regTypeService.findPointIdByMissionId(request.getParameter("missionId"));
					super.dataResult("1000", "查询成功", airPointListist, request, response);
					return;
				}
				super.dataResult("1000", "查询成功", list, request, response);
		}catch(Exception e){
		   super.simpleResult("1001", "查询失败", request, response);
		}
	}
    
}
