package com.xiangxun.epms.mobile.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.dao.SamplingAirRegMapper;
import com.xiangxun.epms.mobile.business.dao.SamplingBackRegMapper;
import com.xiangxun.epms.mobile.business.dao.SamplingFarmRegMapper;
import com.xiangxun.epms.mobile.business.dao.SamplingLandRegMapper;
import com.xiangxun.epms.mobile.business.dao.SamplingManureRegMapper;
import com.xiangxun.epms.mobile.business.dao.SamplingRegMapper;
import com.xiangxun.epms.mobile.business.dao.SamplingWaterRegMapper;
import com.xiangxun.epms.mobile.business.domain.AutoCode;
import com.xiangxun.epms.mobile.business.domain.Files;
import com.xiangxun.epms.mobile.business.domain.LandBlock;
import com.xiangxun.epms.mobile.business.domain.LandSamplingSheme;
import com.xiangxun.epms.mobile.business.domain.SamplingReg;
import com.xiangxun.epms.mobile.business.service.DicService;
import com.xiangxun.epms.mobile.business.service.FilesService;
import com.xiangxun.epms.mobile.business.service.LandBlockService;
import com.xiangxun.epms.mobile.business.service.LandSamplingShemeService;
import com.xiangxun.epms.mobile.business.service.NewestCodeService;
import com.xiangxun.epms.mobile.business.service.SamplingRegService;
import com.xiangxun.epms.mobile.business.util.UuidGenerateUtil;
import com.xiangxun.epms.mobile.business.web.BaseCtl;
@Service
public class SamplingRegServiceImpl implements SamplingRegService {
    @Resource
    SamplingRegMapper  samplingRegMapper;
    @Resource
    DicService dicService;
    @Resource
	NewestCodeService newestCodeservice;
    @Resource
    private SamplingFarmRegMapper samplingFarmRegMapper;
    @Resource
    private SamplingManureRegMapper samplingManureRegMapper;
    @Resource
    private SamplingAirRegMapper samplingAirRegMapper;
    @Resource
    private SamplingWaterRegMapper samplingWaterRegMapper;
    @Resource
    private SamplingBackRegMapper samplingBackRegMapper;
    @Resource
    private SamplingLandRegMapper samplingLandRegMapper;
    @Resource
	LandBlockService landBlockService;
    @Resource
   	FilesService filesService;
    @Resource
    LandSamplingShemeService landSamplingShemeService;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
 
	
	@Override
	public List<SamplingReg> insertSelective(HttpServletRequest request) {
		Map<String,Object> map=getMap(request);
		samplingRegMapper.insertSelective(map);
		if(map!=null&&(map.get("tableName")!=null&&!"".equals(map.get("tableName")))){
			Map<String,String> arg = new HashMap<String,String>();
			arg.put("id",(String) map.get("id"));
			arg.put("tableName",(String) map.get("tableName"));
			List<SamplingReg> list = samplingRegMapper.selectFindById(arg);
			for(SamplingReg info:list){
				LandSamplingSheme sheme = landSamplingShemeService.findById(landSamplingShemeService.getIdbyMissionId(info.getMissionId()));
				if (sheme != null) {
					
						info.setRegionName(sheme.getRegionName());
						info.setSamplingCode(sheme.getSampleCode());
						
				}
				//info.setSamplingCode((String)map.get("samplingCode"));
			}
			return list;
			
		}else{
			return new ArrayList<>();
		}
		
		
	}
	/**samplingType选择采样类型编码samplingCode采样类型编码otherType选择备选类型编码
	 * 根据请求参数值构造map
	 * @param request
	 * @return
	 */
	private Map<String,Object>  getMap(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		BaseCtl baseCtl = new BaseCtl();
		Date date =new Date();
		map.put("id", UuidGenerateUtil.getUUIDLong());
		map.put("createId", baseCtl.getLoginId(request));
		map.put("createTime", date);
		map.put("samplingSource", "2");
		map.put("samplingTime", date);
		map.put("samplingUser", baseCtl.getLoginData(request).getName());
		String missionId=request.getParameter("missionId");
		map.put("missionId", missionId);
		
		String keyType=request.getParameter("samplingCode");
		map.put("samplingCode", keyType);
		if("NTTR".equals(keyType)){
			/*map.put("soilType", request.getParameter("soil_type"));*/
			map.put("depth", request.getParameter("depth"));
			map.put("samplingType", request.getParameter("samplingType"));
			map.put("tableName","T_"+AutoCode.SAMPLING_LAND_REG);
			map.put("name","农田土壤");
			//设置code
			map.put("code", AutoCode.SAMPLING_LAND_REG);
			if(request.getParameter("longitude")!=null&&!"".equals(request.getParameter("longitude"))){
				map.put("longitude", new BigDecimal(request.getParameter("longitude")));
			}
			if(request.getParameter("latitude")!=null&&!"".equals(request.getParameter("latitude"))){
				map.put("latitude", new BigDecimal(request.getParameter("latitude")));
			}
		}
		else if("WATER".equals(keyType)){
			
			map.put("typeCode", request.getParameter("otherType"));
			map.put("samplingType",  request.getParameter("samplingType"));
			map.put("riversName",  request.getParameter("riversName"));
			
			map.put("tableName","T_"+AutoCode.SAMPLING_WATER_REG);
			map.put("code", AutoCode.SAMPLING_WATER_REG);
			if(request.getParameter("longitude")!=null&&!"".equals(request.getParameter("longitude"))){
				map.put("longitude", new BigDecimal(request.getParameter("longitude")));
			}
			if(request.getParameter("latitude")!=null&&!"".equals(request.getParameter("latitude"))){
				map.put("latitude", new BigDecimal(request.getParameter("latitude")));
			}

		}
		else if("SD".equals(keyType)){
        	map.put("samplingType",  request.getParameter("samplingType"));
        	map.put("position",  request.getParameter("position"));
        	map.put("name","农作物");
        	map.put("tableName","T_"+AutoCode.SAMPLING_FARM_REG);
        	map.put("code", AutoCode.SAMPLING_FARM_REG);
        	if(request.getParameter("longitude")!=null&&!"".equals(request.getParameter("longitude"))){
    			map.put("longitude", new BigDecimal(request.getParameter("longitude")));
    		}
    		if(request.getParameter("latitude")!=null&&!"".equals(request.getParameter("latitude"))){
    			map.put("latitude", new BigDecimal(request.getParameter("latitude")));
    		}
        	
		}
		
		else if("BJTR".equals(keyType)){
			map.put("typeCode",  request.getParameter("samplingType"));
		    map.put("ambient",  request.getParameter("ambient"));
		    map.put("years",  request.getParameter("years"));
		    map.put("wallSource",  request.getParameter("otherType"));
		    map.put("tableName","T_"+AutoCode.SAMPLING_BACK_REG);
		    map.put("code", AutoCode.SAMPLING_BACK_REG);
		    if(request.getParameter("longitude")!=null&&!"".equals(request.getParameter("longitude"))){
				map.put("longitude", new BigDecimal(request.getParameter("longitude")));
			}
			if(request.getParameter("latitude")!=null&&!"".equals(request.getParameter("latitude"))){
				map.put("latitude", new BigDecimal(request.getParameter("latitude")));
			}

		}
		else if("FL".equals(keyType)){
		    map.put("shopName",  request.getParameter("shopName"));
		    map.put("tel",  request.getParameter("tel"));
		    map.put("dealManure",  request.getParameter("dealManure"));
		    map.put("shopkeeper",  request.getParameter("shopkeeper"));
		    map.put("tableName","T_"+AutoCode.SAMPLING_MANURE_REG);
		    map.put("code", AutoCode.SAMPLING_MANURE_REG);
		    if(request.getParameter("longitude")!=null&&!"".equals(request.getParameter("longitude"))){
				map.put("longitude", new BigDecimal(request.getParameter("longitude")));
			}
			if(request.getParameter("latitude")!=null&&!"".equals(request.getParameter("latitude"))){
				map.put("latitude", new BigDecimal(request.getParameter("latitude")));
			}

		}
		else if("DQ".equals(keyType)){
		    map.put("containerVolume",  request.getParameter("containerVolume"));
		    map.put("collectVolume",  request.getParameter("collectVolume"));
		    map.put("pointId",  request.getParameter("otherType"));
		    map.put("tableName","T_"+AutoCode.SAMPLING_AIR_REG);
		    map.put("code", AutoCode.SAMPLING_AIR_REG);
		}
		return map;
	}

	
	@Override
	public SamplingReg selectFindById(String tableName,String id,String missionId ) {
		
		
		List<SamplingReg>  list = null;
		SamplingReg Reg = new SamplingReg();
		//SamplingReg  reg = new SamplingReg();
			if("T_SAMPLING_LAND_REG".equals(tableName)){
				list=samplingLandRegMapper.selectByPrimaryKey(id);
				
					
			}
			else if("T_SAMPLING_MANURE_REG".equals(tableName)){
				list=samplingManureRegMapper.findById(id);
			}
			else if("T_SAMPLING_AIR_REG".equals(tableName)){
				list= samplingAirRegMapper.findById(id);
			}
			else if("T_SAMPLING_WATER_REG".equals(tableName)){
				list=samplingWaterRegMapper.findById(id);
			}
			else if("T_SAMPLING_FARM_REG".equals(tableName)){
				list=  samplingFarmRegMapper.findById(id);
			}
			else if("T_SAMPLING_BACK_REG".equals(tableName)){
				list=  samplingBackRegMapper.findById(id);
			}
			if(list!=null&&list.size()>0){
				
				
				
				for(SamplingReg info:list){
					List<Files> fList=filesService.queryByBusinessId(info.getId());
					info.setFile(fList);
					if(!"T_SAMPLING_AIR_REG".equals(tableName)){
							if(info.getLatitude()==null){
								info.setLatitude(new BigDecimal(0));
							}
							if(info.getLongitude()==null){
								info.setLongitude(new BigDecimal(0));
							}
							
							LandSamplingSheme sheme = landSamplingShemeService.findById(landSamplingShemeService.getIdbyMissionId(missionId));
							if (sheme != null) {
								List<LandBlock> blist = landBlockService.findById(sheme.getBlockId());
								if (blist != null && blist.size() > 0) {
										info.setBlockName(blist.get(0).getName());
									}
									info.setRegionName(sheme.getRegionName());
									info.setSamplingCode(sheme.getSampleCode());
									
							} else {
									info.setBlockName("");
									info.setRegionName("");
									
							}
				  }else {
					 LandSamplingSheme sheme = landSamplingShemeService.findById(landSamplingShemeService.getIdbyMissionId(missionId));
					 if (sheme != null) {
						//List<LandBlock> blist = landBlockService.findById(sheme.getBlockId());
						info.setSamplingCode(sheme.getSampleCode());
							info.setRegionName(sheme.getRegionName());
							
					 } else {
							
							info.setRegionName("");
							
					
				    }
					
				  }
					if("DXS".equals(info.getSamplingCode())||"DBS".equals(info.getSamplingCode())||"DN".equals(info.getSamplingCode())){
						info.setSamplingCode("WATER");
					}
				}
				Reg=list.get(0);
				
			}
			
			
			
		return Reg;
	}
	@SuppressWarnings("unused")
	@Override
	public List<SamplingReg> selectFindByMissionId(String missionId,String samplingCode,String code) {
		SamplingReg reg = new SamplingReg();
		
		if("NTTR".equals(samplingCode)){
			reg.setTableName("T_"+AutoCode.SAMPLING_LAND_REG);
			reg.setMissionId(missionId);
			reg.setCode(code);
			
		}
		else if("WATER".equals(samplingCode)){
			
			reg.setTableName("T_"+AutoCode.SAMPLING_WATER_REG);
			reg.setMissionId(missionId);
			reg.setCode(code);

		}
		else if("SD".equals(samplingCode)){
        	
			reg.setTableName("T_"+AutoCode.SAMPLING_FARM_REG);
			reg.setMissionId(missionId);
			reg.setCode(code);
		}
		
		else if("BJTR".equals(samplingCode)){
			
			reg.setTableName("T_"+AutoCode.SAMPLING_BACK_REG);
			reg.setMissionId(missionId);
			reg.setCode(code);
		}
		else if("FL".equals(samplingCode)){
			reg.setTableName("T_"+AutoCode.SAMPLING_MANURE_REG);
			reg.setMissionId(missionId);
			reg.setCode(code);

		}
		else if("DQ".equals(samplingCode)){
			reg.setTableName("T_"+AutoCode.SAMPLING_AIR_REG);
			reg.setMissionId(missionId);
			reg.setCode(code);
		}
		
			
		if (reg!=null){
			return samplingRegMapper.selectFindByMissionId(reg);
		}else{
			return null;
		}
		
	}
	
}
