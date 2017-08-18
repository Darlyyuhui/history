package com.xiangxun.atms.module.geoServer.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Table;
import com.google.gson.JsonArray;
import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.common.system.service.ResourceService;
import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.common.user.type.MenuType;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;
import com.xiangxun.atms.framework.util.HttpClientUtil;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.analysis.service.AirAnalysisService;
import com.xiangxun.atms.module.analysis.service.LandAnalysisService;
import com.xiangxun.atms.module.analysis.vo.AirAnalysis;
import com.xiangxun.atms.module.analysis.vo.AirAnalysisSearch;
import com.xiangxun.atms.module.analysis.vo.LandAnalysis;
import com.xiangxun.atms.module.analysis.vo.LandAnalysisSearch;
import com.xiangxun.atms.module.apb.service.ApbInfoService;
import com.xiangxun.atms.module.apb.vo.ApbInfo;
import com.xiangxun.atms.module.apb.vo.ApbInfoSearch;
import com.xiangxun.atms.module.bs.cache.StandardMonitorCahe;
import com.xiangxun.atms.module.bs.cache.TRegionCache;
import com.xiangxun.atms.module.bs.service.AirPointService;
import com.xiangxun.atms.module.bs.service.TRegionService;
import com.xiangxun.atms.module.bs.service.VideoDeviceService;
import com.xiangxun.atms.module.bs.vo.Region;
import com.xiangxun.atms.module.bs.vo.RegionSearch;
import com.xiangxun.atms.module.bs.vo.StandardMonitor;
import com.xiangxun.atms.module.bs.vo.VideoDevice;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.files.vo.Files;
import com.xiangxun.atms.module.files.vo.FilesSearch;
import com.xiangxun.atms.module.geoServer.domain.Geometry;
import com.xiangxun.atms.module.geoServer.domain.GeometryType;
import com.xiangxun.atms.module.geoServer.domain.LayerBean;
import com.xiangxun.atms.module.geoServer.domain.LayerEnum;
import com.xiangxun.atms.module.geoServer.domain.Module;
import com.xiangxun.atms.module.geoServer.service.IMapOperation;
import com.xiangxun.atms.module.land.service.LandBlockService;
import com.xiangxun.atms.module.land.vo.LandBlock;
import com.xiangxun.atms.module.land.vo.LandBlockSearch;
import com.xiangxun.atms.module.map.service.MapDataService;
import com.xiangxun.atms.module.map.vo.RegAnalysis;
import com.xiangxun.atms.module.reg.service.AirRegService;
import com.xiangxun.atms.module.reg.service.BackRegService;
import com.xiangxun.atms.module.reg.service.FarmRegService;
import com.xiangxun.atms.module.reg.service.LandRegService;
import com.xiangxun.atms.module.reg.service.ManureRegService;
import com.xiangxun.atms.module.reg.service.WaterRegService;
import com.xiangxun.atms.module.reg.vo.AirReg;
import com.xiangxun.atms.module.reg.vo.BackReg;
import com.xiangxun.atms.module.reg.vo.BackRegSearch;
import com.xiangxun.atms.module.reg.vo.BaseReg;
import com.xiangxun.atms.module.reg.vo.FarmReg;
import com.xiangxun.atms.module.reg.vo.FarmRegSearch;
import com.xiangxun.atms.module.reg.vo.LandReg;
import com.xiangxun.atms.module.reg.vo.ManureReg;
import com.xiangxun.atms.module.reg.vo.ManureRegSearch;
import com.xiangxun.atms.module.reg.vo.WaterReg;
import com.xiangxun.atms.module.reg.vo.WaterRegSearch;
@Controller
@RequestMapping(value = "map")
public class MapCtl extends BaseCtl {

	@Resource
    ResourceService resourceService;

    /**
     * 土壤地块
     * */
    @Resource
    LandBlockService landBlockService;

    /**
     * 农产品基地信息
     * */
    @Resource
    protected ApbInfoService apbInfoService;

    /**
     * 样地视频监控
     * */
    @Resource
    VideoDeviceService videoDeviceService;

    /**
     * 土壤采样点
     * */
    @Resource
    LandRegService landRegService;

    /**
     * 大气采样点
     * */
    @Resource
    AirRegService airRegService;

    /**
     * 大气采样点位信息
     * */
    @Resource
    AirPointService airPointService;

    /**
     * 水样底泥采样点
     * */
    @Resource
    WaterRegService waterRegService;

    /**
     * 肥料采样点
     * */
    @Resource
    ManureRegService manureRegService;

    /**
     * 背景土壤采样点
     * */
    @Resource
    BackRegService backRegService;

    /**
     * 农产品采样点
     * */
    @Resource
    FarmRegService farmRegService;

    @Resource
    Cache cache;

    @Resource
    DicService dicService;

    /**
     * 行政区域
     * */
    @Resource
    TRegionService regionService;
	
	/**
	 * 组织机构服务
	 */
	@Resource
    DepartmentService departmentService;

    @Resource
    IMapOperation mapOperation;
    
    @Resource
    MapDataService mapDataService;
    @Resource
    FilesService filesService;
    /**
     * 土壤分析
     */
    @Resource
    LandAnalysisService landAnalysisService;
    /**
     * 大气分析
     */
    @Resource
    AirAnalysisService airAnalysisService;


	/**
	 * GIS首页根据menuid获取当前用户的地图菜单权限
	 * @param menuid String 权限id
	 */
	@RequestMapping(value="menus/{menuid}/")
    @ResponseBody
	public List<Module> menus(@PathVariable String menuid,ModelMap model,HttpServletRequest request){
		return createTree(menuid);
	}

    /**
     * 行政区污染详细信息
     * */
    @RequestMapping(value="pollute/region/info/{regionid}/")
    public String regionPolluteInfo(@PathVariable String regionid, ModelMap model,HttpServletRequest request){
        // 行政区信息
        model.addAttribute("region", regionService.getById(regionid));
        // 行政区地块信息
        LandBlockSearch search = new LandBlockSearch();
        LandBlockSearch.Criteria criteria = search.createCriteria();
        criteria.andRegionIdEqualTo(regionid);
        model.addAttribute("lands", landBlockService.selectByExample(search));
        // 行政区污染信息和指标信息
        model.addAttribute("pollute", this.makeData(regionid));
        return "iframe/pollute/regioninfo";
    }
    
    private RegAnalysis makeData(String regionId) {
    	RegAnalysis ra = new RegAnalysis();
    	
    	Table<String, String, Float> table = mapDataService.getDataByMap(regionId, null, null);
    	//无污染
    	ra.setPolluteNull(this.getLvNum("无污染", table));
    	//轻微污染
    	ra.setPolluteLittle(this.getLvNum("轻微污染", table));
    	//轻度污染
    	ra.setPolluteSlight(this.getLvNum("轻度污染", table));
    	//中度污染
    	ra.setPolluteModerate(this.getLvNum("中度污染", table));
    	//重度污染
    	ra.setPolluteSevere(this.getLvNum("重度污染", table));
    	
    	Map<String, Float> map = table.column("最小值");
    	ra.setPhMin(map.get("PH"));
    	ra.setCrMin(map.get("镉"));
    	ra.setCrValidMin(map.get("有效态镉"));
    	
    	map = table.column("最大值");
    	ra.setPhMax(map.get("PH"));
    	ra.setCrMax(map.get("镉"));
    	ra.setCrValidMax(map.get("有效态镉"));
    	
    	map = table.column("平均值");
    	ra.setPhAvg(map.get("PH"));
    	ra.setCrAvg(map.get("镉"));
    	ra.setCrValidAvg(map.get("有效态镉"));
    	
    	map = table.column("标准差");
    	ra.setPhSD(map.get("PH"));
    	ra.setCrSD(map.get("镉"));
    	ra.setCrValidSD(map.get("有效态镉"));
    	
    	map = table.column("变异系数");
    	ra.setPhCoefficient(map.get("PH"));
    	ra.setCrCoefficient(map.get("镉"));
    	ra.setCrValidCoefficient(map.get("有效态镉"));
    	
    	map = table.column("超标率");
    	ra.setExceedRate(map.get("镉"));
    	
    	return ra;
    }
    
    private float getLvNum(String lvName, Table<String, String, Float> table) {
    	if (table == null || table.column(lvName) == null) {
    		return 0.0f;
    	}
    	Map<String, Float> map = table.column(lvName);
    	Float f = map.get(lvName);
    	return f == null ? 0.0f : f;
    }

    /**
     * 农产品基地详细信息
     * */
    @RequestMapping(value="apb/info/{apbid}/")
    public String apbInfo(@PathVariable String apbid, ModelMap model,HttpServletRequest request){
        model.addAttribute("bpb", apbInfoService.getById(apbid));
        return "iframe/apb/info";
    }

    /**
     * 普查采样点历史数据
     * */
    @RequestMapping(value="point/hisinfo/{type}/{pointid}/")
    public String pointHisInfo(@PathVariable String type, @PathVariable String pointid, ModelMap model,HttpServletRequest request){
        return "iframe/point/hisinfo";
    }
    
    /**
     * 普查采样点
     * */
    @RequestMapping(value="point/baseinfo/")
    public String cydPointInfo(ModelMap model,HttpServletRequest request){
    	//String id = request.getParameter("id");
    	
    	//System.out.println(obj.toString());
    	return "iframe/point/pointinfo";
    }

    /**
     * 普查采样点最新数据
     * */
    @RequestMapping(value="point/info/{type}/{pointid}/")
    public String pointInfo(@PathVariable String type, @PathVariable String pointid, ModelMap model,HttpServletRequest request){
        return "iframe/point/info";
    }

    /**
     * 获取所有普查采样点数据信息
     * */
    @RequestMapping(value="getall/points/")
    @ResponseBody
    public Map<String, Object> getAllPoints() {
    	@SuppressWarnings("unchecked")
		Table<String, String, String> table = (Table<String, String, String>)cache.get(TRegionCache.ID_NAME);
    	Map<String, String> cacheMap = table.column(TRegionCache.ID_NAME);
    	
    	//附件集合
    	Map<String, List<Files>> filesMap = this.getFilesMap();
    	
        Map<String, Object> result = new HashMap<String, Object>();
        //{"dxs","dbs","dn","dqcj","bjtr","nttr","nzw","fl"};
        //{"地下水监测点","地表灌溉水监测点","底泥监测点","大气沉降采样点","背景土壤监测点","农田土壤监测点","农作物监测点","肥料采集点"};
        WaterRegSearch wrs = new WaterRegSearch();
        wrs.createCriteria().andCheckStatusEqualTo(1);
        List<WaterReg> list = waterRegService.selectByExample(wrs);
        List<WaterReg> listDXS = new ArrayList<WaterReg>();
        List<WaterReg> listDBS = new ArrayList<WaterReg>();
        List<WaterReg> listDN = new ArrayList<WaterReg>();
        List<Dic> dics = dicService.getDicByType(DicType.SAMPLING_WATER_TYPE1);
        Map<String, String> dicMap = new HashMap<String, String>();
        for(Dic dic : dics) {
            dicMap.put(dic.getCode(), dic.getRemark());
        }
        String type;
        for(WaterReg reg : list) {
        	this.queryFiles(reg.getId(), reg, filesMap);
        	reg.setRegionId(this.getRegionNameByCache(reg.getRegionId(), cacheMap));
            type = dicMap.get(reg.getTypeCode());//获取类型
            if("dxs".equals(type)) {
                listDXS.add(reg);
            }
            else if("dbs".equals(type)) {
                listDBS.add(reg);
            }
            else if("dn".equals(type)) {
                listDN.add(reg);
            }
        }
        result.put("dxs", listDXS);
        result.put("dbs", listDBS);
        result.put("dn", listDN);
        List<AirReg> alist = mapDataService.getAirReg();
        for (AirReg reg : alist) {
        	reg.setRegionId(this.getRegionNameByCache(reg.getRegionId(), cacheMap));
        	this.queryFiles(reg.getId(), reg, filesMap);
        }
        result.put("dqcj", alist);
        
        BackRegSearch brs = new BackRegSearch();
        brs.createCriteria().andCheckStatusEqualTo(1);
        List<BackReg> blist = backRegService.selectByExample(brs);
        for (BackReg reg : blist) {
        	reg.setRegionId(this.getRegionNameByCache(reg.getRegionId(), cacheMap));
        	this.queryFiles(reg.getId(), reg, filesMap);
        }
        result.put("bjtr", blist);
        
        List<LandReg> llist = mapDataService.getLandReg();
        for (LandReg reg : llist) {
        	reg.setRegionId(this.getRegionNameByCache(reg.getRegionId(), cacheMap));
        	this.queryFiles(reg.getId(), reg, filesMap);
        }
        result.put("nttr", llist);
        
        FarmRegSearch frs = new FarmRegSearch();
        frs.createCriteria().andCheckStatusEqualTo(1);
        List<FarmReg> flist = farmRegService.selectByExample(frs);
        for (FarmReg reg : flist) {
        	reg.setRegionId(this.getRegionNameByCache(reg.getRegionId(), cacheMap));
        	this.queryFiles(reg.getId(), reg, filesMap);
        }
        result.put("nzw", flist);
        
        ManureRegSearch mrs = new ManureRegSearch();
        mrs.createCriteria().andCheckStatusEqualTo(1);
        List<ManureReg> mlist = manureRegService.selectByExample(mrs);
        for (ManureReg reg : mlist) {
        	reg.setRegionId(this.getRegionNameByCache(reg.getRegionId(), cacheMap));
        	this.queryFiles(reg.getId(), reg, filesMap);
        }
        result.put("fl", mlist);
        return result;
    }
    
    /**
     * 将区域ID转成名称
     * @param regionId
     * @param map
     * @return
     */
    private String getRegionNameByCache(String regionId, Map<String, String> map) {
    	if (StringUtils.isEmpty(regionId)) {
    		return null;
    	}
    	if (map == null) {
    		return null;
    	}
    	return map.get(regionId);
    }
    
    /**
     * 获取附件
     * @param id
     * @param br
     */
    private void queryFiles(String id, BaseReg br, Map<String, List<Files>> map) {
    	br.setFiles(map.get(id));
    }
    
    /**
     * 构建附件集合
     * @return
     */
    private Map<String, List<Files>> getFilesMap() {
    	FilesSearch search = new FilesSearch();
    	List<Files> list = filesService.selectByExample(search);
    	Map<String, List<Files>> map = new HashMap<String, List<Files>>();
    	List<Files> temp = null;
    	String key = null;
    	for (Files f : list) {
    		key = f.getBusinessId();
    		temp = map.get(key);
    		if (temp == null) {
    			temp = new ArrayList<Files>();
    		}
    		temp.add(f);
    		map.put(key, temp);
    	}
    	return map;
    }
    
    /**
     * 获取采样点的分析数据
     * */
    @RequestMapping(value="getpoint/analysis/{pointId}/{type}/")
    @ResponseBody
    public Map<String, Object> getPointAnalysis(@PathVariable("pointId") String pointId, @PathVariable("pointId") String type) {
    	//{"dxs","dbs","dn","dqcj","bjtr","nttr","nzw","fl"};
        //{"地下水监测点","地表灌溉水监测点","底泥监测点","大气沉降采样点","背景土壤监测点","农田土壤监测点","农作物监测点","肥料采集点"};
    	Map<String, Object> result = new HashMap<String, Object>();
    	Object obj = null;
    	if ("dxs".equals(type)) {
    		
    	} 
    	else if ("dbs".equals(type)) {
    		
    	} 
    	else if ("dn".equals(type)) {
    		
    	} 
    	else if ("dqcj".equals(type)) {
    		AirAnalysisSearch search = new AirAnalysisSearch();
			search.createCriteria().andRegIdEqualTo(pointId);
			List<AirAnalysis> list = airAnalysisService.selectByExample(search);
			if (list != null && list.size() > 0) {
				obj = list.get(0);
			}
    	} 
		else if ("bjtr".equals(type)) {
			
		} 
		else if ("nttr".equals(type)) {
			LandAnalysisSearch search = new LandAnalysisSearch();
			search.createCriteria().andRegIdEqualTo(pointId);
			List<LandAnalysis> list = landAnalysisService.selectByExample(search);
			if (list != null && list.size() > 0) {
				obj = list.get(0);
			}
		} 
		else if ("nzw".equals(type)) {
    		
    	}
		else if ("fl".equals(type)) {
    		
    	}
    	result.put(type, obj);
    	return result;
    }

    /**
     * 样地监控详细信息
     * */
    @RequestMapping(value="video/info/{videoid}/")
    public String videoInfo(@PathVariable String videoid, ModelMap model,HttpServletRequest request){
        model.addAttribute("video", videoDeviceService.getById(videoid));
        model.addAttribute("videoId", videoid);
        model.addAttribute("video_service_ip", "193.169.100.31:9021");
        return "iframe/video/info";
    }

    /**
     * 行政区污染统计
     * */
    @RequestMapping(value="pollute/region/")
    @ResponseBody
    public Map<String, RegAnalysis> regionPollute(ModelMap model,HttpServletRequest request){
        Map<String, RegAnalysis> result = new HashMap<String, RegAnalysis>();
        List<LayerBean> regions = mapOperation.getAll(LayerEnum.REGION_POLYGON);
        List<LayerBean> regionCenters = mapOperation.getAll(LayerEnum.REGION_POINT);
        RegionSearch search = new RegionSearch();
        RegionSearch.Criteria criteria =  search.createCriteria();
        criteria.andPidEqualTo("0");// 查询绵竹市下的所有镇
        List<Region> list = regionService.selectByExample(search);
        for(Region info : list) {
            RegAnalysis reg = new RegAnalysis();
            //查询所有类型的统计数
            this.makeRegTypeCount(info.getId(), reg);
            for(LayerBean layer : regions) {
                if(info.getName().equals(layer.getName())) {
                    reg.setGeo(layer.getGeometry());
                }
            }
            for(LayerBean layer : regionCenters) {
                if(info.getName().equals(layer.getName())) {
                    reg.setCenter(layer.getGeometry());
                }
            }
            result.put(info.getId(), reg);
        }
        return result;
    }
    
    private void makeRegTypeCount(String regionId, RegAnalysis ra) {
    	Map<String, Integer> map = mapDataService.getRegTypeDataByMap(regionId, null, null);
    	ra.setAirNum(map.get("AIR"));
    	ra.setBackNum(map.get("BACK"));
    	ra.setLandNum(map.get("LAND"));
    	ra.setFarmNum(map.get("FARM"));
    	ra.setManureNum(map.get("MANURE"));
    	ra.setUndergroundWaterNum(map.get("UNDER_WATER"));
    	ra.setSurfaceWaterNum(map.get("SURFACE_WATER"));
    	ra.setDirtNum(map.get("DIRT"));
    }

    /**
     * 获取所有地块信息和几何信息
     * */
    @RequestMapping(value="all/landblock/")
    @ResponseBody
    public List<LandBlock> getAllLandBlock(){
        List<LandBlock> list = landBlockService.selectByExample(null);
        List<LayerBean> lands = mapOperation.getAll(LayerEnum.LAND);
        for(LandBlock block : list) {
            for(LayerBean layer : lands) {
                if(block.getId().equals(layer.getCode())) {
                    block.setGeoJson(layer.getGeometry());
                }
            }
        }
        
        return list;
    }
    
    /**
     * 获取所有农产品基地信息和几何信息
     * */
    @RequestMapping(value="all/apblandblock/")
    @ResponseBody
    public List<ApbInfo> getAllApbLandBlock(){
        List<ApbInfo> list = apbInfoService.selectByExample(null);
        List<LayerBean> lands = mapOperation.getAll(LayerEnum.APBLAND);
        for(ApbInfo block : list) {
            for(LayerBean layer : lands) {
                if(block.getId().equals(layer.getCode())) {
                    block.setGeoJson(layer.getGeometry());
                }
            }
        }
        return list;
    }
    

	private List<Module> createTree(String menuid) {
		OperatorDetails userInfo = SpringSecurityUtils.getCurrentUser();
		if (userInfo == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		SystemResource sys = resourceService.getById(menuid);

		// 一级菜单
		List<SystemResource> resList = resourceService.getChildMenusByUserId(userInfo.getId(),sys.getId(), MenuType.MODULE);
		List<Module> modules = new ArrayList<Module>();
		for(SystemResource res : resList){
			if(res.getDisabled()){
				continue;
			}
			Module module = new Module();
			module.setId(res.getId());
			module.setName(res.getName());
			module.setUrl(res.getContent());
            // 二级菜单
            if(res.getContent().equals("region"))module.setChildren(getRegionModuleItem());
            if(res.getContent().equals("nchp"))module.setChildren(getAPBModuleItem());
            if(res.getContent().equals("cyd"))module.setChildren(getPointsModuleItem());
            if(res.getContent().equals("dy"))module.setChildren(getVideoModuleItem());
            if(res.getContent().equals("fx"))module.setChildren(getAnalysisModuleItem());
			modules.add(module);
		}
        return modules;
	}

	//TODO 存在模拟数据
    // 获取所有的镇，并封装成arrayList
    private List<Module> getRegionModuleItem() {
        RegionSearch search = new RegionSearch();
        RegionSearch.Criteria criteria =  search.createCriteria();
        criteria.andPidEqualTo("0");// 查询绵竹市下的所有镇
        List<Region> list = regionService.selectByExample(search);
        List<LayerBean> regions = mapOperation.getAll(LayerEnum.REGION_POLYGON);
        Random rd = new Random();
        // 根据name进行关联，组织新对象
        List<Module> module = new ArrayList<Module>();
        for (Region region : list) {
            Module subModule = new Module();
            subModule.setId(region.getId());
            subModule.setName(region.getName());
            int num = rd.nextInt(20)+1;
            subModule.setUrl(num+"");
            for(LayerBean layer : regions) {
                if(layer.getName().equals(region.getName())) {
                    // 名称相同时候，设置几何信息
                    subModule.setGeom(layer.getGeometry());
                }
            }
            module.add(subModule);
        }
        return module;
    }
    // 农产品基地子菜单
    private List<Module> getAPBModuleItem() {
        List<ApbInfo> list = apbInfoService.selectByExample(new ApbInfoSearch());
        List<Module> module = new ArrayList<Module>();
        for(ApbInfo info : list) {
            Module subModule = new Module();
            subModule.setId(info.getId());
            subModule.setName(info.getName());
            subModule.setGeometry(new Geometry(GeometryType.POINT, info.getLongitude() + "," + info.getLatitude()));
            module.add(subModule);
        }
        return module;
    }
    // 普查采样点子菜单
    private List<Module> getPointsModuleItem() {
        String[] pointIds = {"dxs","dbs","dn","dqcj","bjtr","nttr","nzw","fl"};
        String[] points = {"地下水采样点","地表水采样点","底泥采样点","大气沉降采样点","背景土壤采样点","农田土壤采样点","农作物采样点","肥料采样点"};
        List<Module> module = new ArrayList<Module>();
        for(int i=0,len=points.length; i<len; i++) {
            Module subModule = new Module();
            subModule.setId(pointIds[i]);
            subModule.setName(points[i]);
            module.add(subModule);
        }
        return module;
    }
    // 地样监控子菜单
    private List<Module> getVideoModuleItem() {
        List<VideoDevice> list =  videoDeviceService.selectByExample(null);
        List<Module> module = new ArrayList<Module>();
        for(VideoDevice info : list) {
            Module subModule = new Module();
            subModule.setId(info.getId());
            subModule.setName(info.getName());
            subModule.setGeometry(new Geometry(GeometryType.POINT, info.getLongitude() + "," + info.getLatitude()));
            module.add(subModule);
        }
        return module;
    }
    // 专题分析子菜单
    private List<Module> getAnalysisModuleItem() {
        List<Module> module = new ArrayList<Module>();
        Module subModule = new Module();
        subModule.setId(UuidGenerateUtil.getUUID());
        subModule.setName("土壤PH值梯度分布");
        Module subModule2 = new Module();
        subModule2.setId(UuidGenerateUtil.getUUID());
        subModule2.setName("土壤总铬梯度分布");
        Module subModule3 = new Module();
        subModule3.setId(UuidGenerateUtil.getUUID());
        subModule3.setName("土壤有效态铬分布");
        Module subModule4 = new Module();
        subModule4.setId(UuidGenerateUtil.getUUID());
        subModule4.setName("大米总铬分布");
        Module subModule5 = new Module();
        subModule5.setId(UuidGenerateUtil.getUUID());
        subModule5.setName("修复对比专题");
        module.add(subModule);
        module.add(subModule2);
        module.add(subModule3);
        //module.add(subModule4);
        //module.add(subModule5);
        return module;
    }


    /**
     * 地图中视频请求地址
     * */
    @RequestMapping(value = "video/show/{id}/{ip}/")
    public String map(@PathVariable("id") String id, @PathVariable("ip") String ip, Model model) {
        //TODO 这里需要做将平台存储的设备ID转成海康86平台的设备ID
        String str = "";
        try {
            str = HttpClientUtil.httpGet("http://" + ip + "/previewAction!getAllCameraDeviceInfoTree.action", null);
        }catch(Exception e) { }
        model.addAttribute("videoId", id);
        model.addAttribute("treeData", str);
        model.addAttribute("ip", ip);
        return "videoshow/mapshow";
    }

    /**
     * 处理跨域请求
     * @param url	请求url
     * @param type	请求方式，默认get
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "video/getData/")
    public ResponseEntity getDataByUrl(@RequestParam(value = "url", required = true) String url
            , @RequestParam(value = "type", defaultValue = "get") String type) {
        ResponseEntity entity = new ResponseEntity();
        String str = "";
        try {
            if ("get".equals(type)) {
                str = HttpClientUtil.httpGet(url, null);
            } else if ("post".equals(type)) {
                String data = url.substring(url.indexOf("?")+1, url.length());
                url = url.substring(0, url.indexOf("?"));
                Map<String, Object> args = new HashMap<String, Object>();
                String[] ds = data.split("&");
                String[] temp;
                for (String d : ds) {
                    temp = d.split("=");
                    if (temp.length == 1) {
                        args.put(temp[0], "");
                    } else {
                        args.put(temp[0], temp[1]);
                    }
                }
                str = HttpClientUtil.httpPost(url, args);
            }
        }catch(Exception e) { }
        entity.setResult(str);
        return entity;
    }

	/**
	 * 获取部门
	 * @return
	 */
	@RequestMapping(value="getDeptAsJson/",method= RequestMethod.POST)
	public @ResponseBody
    String getDepartmentAsJson(@RequestParam String menuid,HttpServletResponse response){
		JsonArray departmentjsonArray = departmentService.getDeptJsonArray(menuid);
		String s = departmentjsonArray.toString();
		try{
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(s);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="getTime/")
	@ResponseBody
	public long getSysTime(){
		return new Date().getTime();
	}
	
	@ResponseBody
	@RequestMapping(value = "getStandardMonitor/{typeCode}/")
	public List<StandardMonitor> getStandardMonitor(@PathVariable String typeCode) {
		List<StandardMonitor> list = null;
		if (StringUtils.isEmpty(typeCode)) {
			list = new ArrayList<StandardMonitor>();
		}
		@SuppressWarnings("unchecked")
		Map<String, List<StandardMonitor>> cacheMap = (Map<String, List<StandardMonitor>>)cache.get(StandardMonitorCahe.TYPE_ITEMS);
		if (cacheMap.containsKey(typeCode)) {
			list = cacheMap.get(typeCode);
		} else {
			list = new ArrayList<StandardMonitor>();
		}
		return list;
	}
	
}
