package com.xiangxun.atms.module.gis.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.system.service.ResourceService;
import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.common.user.type.MenuType;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.gis.domain.LayerBean;
import com.xiangxun.atms.module.gis.domain.LayerEnum;
import com.xiangxun.atms.module.gis.domain.Module;
import com.xiangxun.atms.module.gis.service.IMapService;

@Controller
@RequestMapping(value = "map")
public class MapCtl extends BaseCtl {

	@Resource
	IMapService mapService;

	@Resource
	ResourceService resourceService;
	
	public static List<Map<String, Object>> specialomList;
	
	/**
	 * 组织机构服务
	 */
	@Resource
	DepartmentService departmentService;

	/**
	 * 根据道路名称获取道路信息                                              
	 * @param roadName
	 * @return
	 */
	@RequestMapping(value="getmaproad")
	@ResponseBody
	public List<LayerBean> getMapRoad(String roadName) {
		return mapService.getByName(LayerEnum.ROAD_LINE, roadName);
	}

	/**
	 * GIS首页跳转
	 * @param engine String GIS引擎
	 * @param menuid String 权限id
	 */
	@RequestMapping(value="home/{menuid}/")
	public String home(@PathVariable String menuid,ModelMap model,HttpServletRequest request){
		model.addAttribute("menuid", menuid);
		model.addAttribute("menulist", createTree(menuid));
		model.addAttribute("baseUrl",request.getScheme()+"://"+request.getLocalAddr()+":"+request.getServerPort()+request.getContextPath()+"/");
		
		// 地图引擎 0：ARCGIS 1：PIGIS 2：开源地图。
		String mapEngineType = request.getSession().getServletContext().getAttribute("mapEngineType")+"";
		
		if("0".equals(mapEngineType)){
			model.addAttribute("engine","ArcGIS");
		}
		else if("2".equals(mapEngineType)){
			model.addAttribute("engine","OpenLayers");
		}

		return "map/home";
	}

	/**
	 * 地图工具路由
	 * @param url 工具url
	 * @return
	 */
	@RequestMapping(value = "address/{url}")
	public String addressUrl(@PathVariable String url) {
		return "map/" + url.replace('.', '/');
	}
	
	// 地图工具路由
	@RequestMapping(value = "/home/mapTools/{toolsbox}/{tool}", method = RequestMethod.GET)
	public String mapToolsDir(@PathVariable String toolsbox, @PathVariable String tool) {
		return "map/mapTools/" + toolsbox + "/" + tool;
	}

	private String createTree(String menuid) {
		OperatorDetails userInfo = SpringSecurityUtils.getCurrentUser();
		if (userInfo == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		SystemResource sys = resourceService.getById(menuid);

		// 一级菜单
		List<SystemResource> resList = resourceService.getChildMenusByUserId(userInfo.getId(),sys.getId(),MenuType.MODULE);
		HashMap<String,Module> resMap = new HashMap<String,Module>();
		List<Module> modules = new ArrayList<Module>();
		for(SystemResource res : resList){
			if(res.getDisabled()){
				continue;
			}
			String id = res.getId();
			Module module = new Module();
			module.setId(res.getId());
			module.setName(res.getName());
			module.setUrl(res.getContent());
			// 二级菜单
			List<SystemResource> subList = resourceService.getChildMenusByUserId(userInfo.getId(),id,MenuType.MODULE);
			for(SystemResource subRes : subList){
				if(subRes.getDisabled()){
					continue;
				}
				Module subModule = new Module();
				subModule.setId(subRes.getId());
				subModule.setName(subRes.getName());
				subModule.setUrl(subRes.getContent());
				module.getChildren().add(subModule);
			}
			modules.add(module);
		}

		return JSONArray.fromObject(modules).toString();
	}
	
	//街景弹出框
	@RequestMapping(value = "/home/pano/panoInfoWindow/", method = RequestMethod.POST)
	public String getPano(@RequestParam(value="name") String name, ModelMap model) {
		model.addAttribute("name", name);
		return "map/mapTools/threedimension/pano";
	}
	// 街景模块中上传图例，图例处理后的命名格式为时间戳+文件类型
	@RequestMapping(value = "/home/pano/addlegend/", method = RequestMethod.POST)
	public void addLegend(MultipartHttpServletRequest req, HttpServletResponse res) throws Exception {

		// 存储目录
		String basePath = req.getSession().getServletContext().getRealPath("/");
		String filePath = basePath + "/streetview/smallpic/";

		MultiValueMap<String, MultipartFile> multiValueMap = req.getMultiFileMap();
		Iterator<String> iterator = multiValueMap.keySet().iterator();

		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			List<MultipartFile> list = multiValueMap.get(key);
			Iterator<MultipartFile> listIterator = list.iterator();

			while (listIterator.hasNext()) {
				MultipartFile multipartFile = (MultipartFile) listIterator.next();
				long time = new Date().getTime();
				String oldFileName = multipartFile.getOriginalFilename();
				String newFileName = time + oldFileName.substring(oldFileName.lastIndexOf("."));
				File upFile = new File(filePath + newFileName);
				multipartFile.transferTo(upFile);
				res.getWriter().write(newFileName);
			}
		}
	}

	// 街景模块中删除图例
	@RequestMapping(value = "/home/pano/dellegend/", method = RequestMethod.POST)
	public void deleteLegend(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 存储目录
		String basePath = req.getSession().getServletContext().getRealPath("/");
		String filePath = basePath + "/streetview/smallpic/";

		String fileName = req.getParameter("fileName");
		File file = new File(filePath + fileName);
		if (file.exists()) {
			file.delete();
		}
	}

	// 获取街景模块中的图例,将目录中的文件名处理成以；分隔的字符串发送到前端
	@RequestMapping(value = "/home/pano/getlegend/")
	public void getLegend(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// //存储目录
		String basePath = req.getSession().getServletContext().getRealPath("/");
		String filePath = basePath + "/streetview/smallpic/";
		File file = new File(filePath);
		String fileNames[] = file.list();
		String netPath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/";
		String resString = netPath + "streetview/smallpic/";
		for (int i = 0; i < fileNames.length; i++) {
			resString = resString + ";" + fileNames[i];
		}
		res.getWriter().write(resString);
	}

	/**
	 * 获取相关数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/home/getMapGraphicsAttr/", method = RequestMethod.POST)
	@SuppressWarnings("unused")
	@ResponseBody
	public String getGraphicAttributes(ServletRequest request) {
		String objID = request.getParameter("objID");
		String objType = request.getParameter("objType");
		String layerName = request.getParameter("layerName");
		JsonObject jo = new JsonObject();
		jo.addProperty("Layer", layerName);
		jo.addProperty("P1", "这是个图层");
		jo.addProperty("P2", "这个是属性二");
		return jo.toString();
	}
	
	/**
	 * 获取地图查询框页面
	 * @return
	 */
	@RequestMapping(value = "/home/mapquerypage/",method = RequestMethod.GET)
	public String getQueryPage(){
		return "map/mapTools/MapQuery/QueryWidget";
	}
	
	/**
	 * 获取地图标注页面
	 * @return
	 */
	@RequestMapping(value = "/home/mapsigns/",method = RequestMethod.GET)
	public String getSignsPage(){
		return "map/mapTools/EmergencySigns/EmergencySigns";
	}
	
	/**
	 * 获取部门
	 * @return
	 */
	@RequestMapping(value="getDeptAsJson/",method=RequestMethod.POST)
	public @ResponseBody String getDepartmentAsJson(@RequestParam String menuid,HttpServletResponse response){
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

	/**
	 * 获取导出页面
	 * @return
	 */
	@RequestMapping(value="getExportPage/",method = RequestMethod.POST)
	public String getExportPage(){
		return "map/mapTools/ThemeMapWidget/ThemeMapWork";
	}

	/**
	 * 地图导出控制器
	 * @param request
	 * @return
	 */
	@RequestMapping(value="exportMap/",method = RequestMethod.POST)
	@ResponseBody public String exportMap(HttpServletRequest request){
		Map<String,Object> requestParams = Servlets.getParametersStartingWith(request, "export_");

		String taskUrl = (String)requestParams.get("url");
		String width = (String)requestParams.get("width");
		String height = (String)requestParams.get("height");
		String title = (String)requestParams.get("title");
		String templateIndex = (String)requestParams.get("templateIndex");
		String xMin = (String)requestParams.get("xMin");
		String xMax = (String)requestParams.get("xMax");
		String yMin = (String)requestParams.get("yMin");
		String yMax = (String)requestParams.get("yMax");
		String graphics = (String)requestParams.get("graphics");
		String legends = (String)requestParams.get("legends");
		
		if("null"==graphics || null == graphics){
			graphics = "[]";
		}
		
		graphics = graphics.replace("\"","'");
		legends = legends.replace("\"","'");

		HttpClient hc = new HttpClient();
		PostMethod pm = new PostMethod(taskUrl);
		String result = "";

		NameValuePair param1 = new NameValuePair("width",width);
		NameValuePair param2 = new NameValuePair("height",height);
		NameValuePair param3 = new NameValuePair("title",title);
		NameValuePair param4 = new NameValuePair("templateIndex",templateIndex);
		NameValuePair param5 = new NameValuePair("xMin",xMin);
		NameValuePair param6 = new NameValuePair("xMax",xMax);
		NameValuePair param7 = new NameValuePair("yMin",yMin);
		NameValuePair param8 = new NameValuePair("yMax",yMax);
		NameValuePair param9 = new NameValuePair("graphics",graphics);
		NameValuePair param10 = new NameValuePair("legends",legends);

		NameValuePair[] params = {param1,param2,param3,param4,param5,param6,param7,param8,param9,param10};

		pm.setRequestBody(params);
		String mm = pm.getRequestCharSet();
		pm.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");

		try {
			hc.executeMethod(pm);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			result = pm.getResponseBodyAsString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		pm.releaseConnection();

		return result;
	}
	
	@RequestMapping(value="getTime/")
	@ResponseBody
	public long getSysTime(){
		return new Date().getTime();
	}
	
	@RequestMapping(value="getExportMapPage/")
	public String getExportMapPage(){
		return "map/mapTools/export/export";
	}
	
	@RequestMapping(value = "/get/specialom/")
	@ResponseBody
	public List<Map<String,Object>> getSpecialOM(String somId) {
		initSOMList();
		if(null == somId || "".equals(somId))return somList;
		else {
			List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
			for(int i=0,len=somList.size(); i<len; i++) {
				if(somList.get(i).get("somId").equals(somId)) {
					result.add(somList.get(i));
					break;
				}
			}
			return result;
		}
	}
	
	@RequestMapping(value = "/add/specialom/", method = RequestMethod.POST)
	@ResponseBody
	public String doAddSpecialOM(ServletRequest request) {
		// 保存成功后，返回id和name或者返回该特勤任务对象
		initSOMList();
		if(null == specialomList)specialomList = new ArrayList<Map<String,Object>>();
		Map<String,Object> som = new HashMap<String, Object>();
		String somName = request.getParameter("somName");// 特勤任务名称，如果为空，则自动生成为  特勤任务+num
		String startGeo = request.getParameter("startGeo");// 开始点坐标
		String endGeo = request.getParameter("endGeo");// 结束点坐标
		String roadGeo = request.getParameter("roadGeo");// 特勤路线
		String ctlPointGeos = request.getParameter("ctlPointGeos");// 控制点以及控制区域
		String gpsCar = request.getParameter("gpsCar");// gps导车 {name:gpsCar,polices:gpsPerson}
		String policePointGeos = request.getParameter("policePointGeos");// 警力点以及民警信息
		String resCross = request.getParameter("resCross");// 周边卡口资源
		String resVideo = request.getParameter("resVideo");// 周边视频资源
		String resSignal = request.getParameter("resSignal");// 周边信号机资源
		
		som.put("somId", somList.size()+"");
		som.put("somName", somName);
		som.put("startGeo", startGeo);
		som.put("endGeo", endGeo);
		som.put("roadGeo", roadGeo);
		som.put("ctlPointGeos", ctlPointGeos);
		som.put("gpsCar", gpsCar);
		som.put("policePointGeos", policePointGeos);
		som.put("resCross", resCross);
		som.put("resVideo", resVideo);
		som.put("resSignal", resSignal);
		somList.add(som);
		
		request.setAttribute("somList", somList);
		
		/*JSONObject obj = JSONObject.fromObject(startGeo);
		JSONObject obj2 = JSONObject.fromObject(endGeo);
		JSONObject obj3 = JSONObject.fromObject(roadGeo);
		JSONObject obj4 = JSONObject.fromObject(gpsCar);
		JSONArray ja = JSONArray.fromObject(ctlPointGeos);
		JSONArray ja2 = JSONArray.fromObject(policePointGeos);
		JSONArray ja3 = JSONArray.fromObject(resCross);
		JSONArray ja4 = JSONArray.fromObject(resVideo);
		JSONArray ja5 = JSONArray.fromObject(resSignal);*/
		
		return somList.size()+"";
	}
	
	public static List<Map<String,Object>> somList = null;
	
	private void initSOMList() {
		if(null == somList)somList = new ArrayList<Map<String,Object>>();
		else return;
		somList = new ArrayList<Map<String,Object>>();
		Map<String,Object> som = new HashMap<String, Object>();
		som.put("somId", "0");
		som.put("somName", "火车站到支队");
		som.put("startGeo", "{\"type\":\"point\",\"points\":\"108.95599876583,34.278683632067\"}");
		som.put("endGeo", "{\"type\":\"point\",\"points\":\"108.91237118573,34.227032299861\"}");
		som.put("roadGeo", "{\"type\":\"multipolyline\",\"points\":\"108.9558757471182,34.27843927725101,108.95585899139951,34.27845073371458,108.9557702552932,34.27846157144455,108.9535966038004,34.27858610416553,108.95334180158306,34.27859111968456,108.95301206875217,34.278569512573085,108.95284642082584,34.27854535228636,108.95268077200018,34.27852119110025,108.9525178768987,34.27848872377575,108.95235498179716,34.27845625555193,108.95235498179716,34.27845625555193,108.95169535155941,34.27849063303643,108.95119595892652,34.27851614230627,108.9472735739165,34.278454736596984,108.94658768977024,34.27846504912287,108.94658768977024,34.27846504912287,108.94658654043667,34.27846506710932,108.94658654043667,34.27846506710932,108.94508436505157,34.27848763739473,108.94483235612853,34.27849618455144,108.94483235612853,34.27849618455144,108.94418252220584,34.27845828891907,108.94418252220584,34.27845828891907,108.94383988320425,34.278508025025474,108.94344989669554,34.278544997054155,108.94295890103263,34.278594275405624,108.94295890103263,34.278594275405624,108.94211652855512,34.27860398088916,108.94211652855512,34.27860398088916,108.94189231497853,34.27858940737542,108.94111185362931,34.27851591657645,108.94111185362931,34.27851591657645,108.94035286539258,34.27843289296379,108.93866298800668,34.278392158171755,108.93866298800668,34.278392158171755,108.93801455073111,34.27837410697964,108.93284379970174,34.27834362355952,108.93284379970174,34.27834362355952,108.93037943876027,34.27830125559865,108.93037943876027,34.27830125559865,108.92866175613528,34.27827169758092,108.92866175613528,34.27827169758092,108.92840674617457,34.27826940341038,108.92840674617457,34.27826940341038,108.92840341958231,34.27826937283345,108.92812001532661,34.2782661388714,108.92812001532661,34.2782661388714,108.92651106073703,34.278247754930135,108.92651106073703,34.278247754930135,108.92368428110484,34.2782154099134,108.92368428110484,34.2782154099134,108.92042297963695,34.27818707587301,108.92042297963695,34.27818707587301,108.92029796128207,34.27817252933892,108.92029796128207,34.27817252933892,108.91956848699624,34.27808764143168,108.91939130706334,34.27807019008736,108.91926737329084,34.27803938920664,108.91915127441194,34.27799428640736,108.91907730427437,34.27795695285124,108.91907730427437,34.27795695285124,108.9190307904388,34.27793347694859,108.91894163794643,34.277852923773594,108.91887982664286,34.27778581276522,108.91880698065779,34.27768573440926,108.91806124932737,34.27646652171279,108.91802540055198,34.276378640861765,108.91798621529176,34.27620428210213,108.91794546071463,34.27607431208003,108.91778035957611,34.27576266101818,108.91778035957611,34.27576266101818,108.91778809734302,34.275060057575786,108.91778809734302,34.275060057575786,108.91778870258673,34.27498437512895,108.91778870258673,34.27498437512895,108.91778871787523,34.2749825477066,108.91778871787523,34.2749825477066,108.91781373701451,34.271859334440876,108.91781373701451,34.271859334440876,108.91746331707861,34.27172155290748,108.91725062741472,34.27153429517176,108.91714094699728,34.27138568759955,108.91710871889245,34.271269966435966,108.91710497141747,34.27117412298753,108.91710497141747,34.27117412298753,108.91711694768918,34.27106985379089,108.91724829996923,34.27089202184948,108.91742777766996,34.27074105175831,108.9178315310005,34.27056015492735,108.9178315310005,34.27056015492735,108.91793726968945,34.26715995496812,108.91793726968945,34.26715995496812,108.91796625034237,34.2652061724271,108.91796625034237,34.2652061724271,108.91801643700916,34.26433286776779,108.91806857610408,34.26393183128914,108.91811374275522,34.2637179329364,108.9181501419157,34.263537778046214,108.9181501419157,34.263537778046214,108.91851163790193,34.26123408728347,108.91851163790193,34.26123408728347,108.91854972778788,34.26099132608897,108.91854972778788,34.26099132608897,108.91861091586128,34.260640694812366,108.91861091586128,34.260640694812366,108.9187003966062,34.260127929260705,108.9187240164004,34.259785453935706,108.91873077570489,34.25876040936555,108.91873083506016,34.25867532360746,108.91873083506016,34.25867532360746,108.9187533262052,34.255102033831974,108.91877507361096,34.25238265393648,108.91877507361096,34.25238265393648,108.91877692621432,34.25213882075013,108.91877692621432,34.25213882075013,108.91879782555935,34.24938477378845,108.91881001586972,34.247608437379654,108.91881001586972,34.247608437379654,108.91882028073155,34.24530461261793,108.91882110990645,34.245102127561324,108.91882110990645,34.245102127561324,108.91883075873272,34.24275480079359,108.91883075873272,34.24275480079359,108.91883176057746,34.242594983172125,108.91883176057746,34.242594983172125,108.91884729007057,34.24012580995833,108.91884827842546,34.23996974610708,108.91884827842546,34.23996974610708,108.91885904780696,34.23827004272846,108.91885904780696,34.23827004272846,108.91886294456941,34.237653856940085,108.91885001681499,34.23697443082551,108.91882399942818,34.23660580681269,108.91873274611953,34.236229944156776,108.9185914842098,34.235911908807736,108.91838718072398,34.235572173717,108.91807315904924,34.23508652092636,108.91807315904924,34.23508652092636,108.91754820498585,34.234274647157406,108.91725043765774,34.23383732843013,108.91532263832926,34.23085554344874,108.91478692387761,34.23002972029553,108.91478692387761,34.23002972029553,108.91409910439035,34.22897609796871,108.9127759273673,34.22694670043006,108.9127759273673,34.22694670043006,108.91240914009335,34.227111683546596\"}");
		som.put("ctlPointGeos", "[{\"geo\":{\"type\":\"point\",\"points\":\"108.917935330195,34.2672223225701\"},\"name\":\"控制点2\",\"geoExtent\":\"\"},{\"geo\":{\"type\":\"point\",\"points\":\"108.917964978002,34.265291949521\"},\"name\":\"控制点3\",\"geoExtent\":\"\"},{\"geo\":{\"type\":\"point\",\"points\":\"108.918504979313,34.2771919857112\"},\"name\":\"控制点4\",\"geoExtent\":\"\"},{\"geo\":{\"type\":\"point\",\"points\":\"108.91877443967,34.2524619243686\"},\"name\":\"控制点5\",\"geoExtent\":\"\"},{\"geo\":{\"type\":\"point\",\"points\":\"108.918831632598,34.2426153988199\"},\"name\":\"控制点6\",\"geoExtent\":\"\"},{\"geo\":{\"type\":\"point\",\"points\":\"108.914765398247,34.2299967466951\"},\"name\":\"控制点7\",\"geoExtent\":\"\"}]");
		som.put("gpsCar", "{\"id\":\"1311280910475312b9bfa299b4cf3117\",\"name\":\"陕A3998警\",\"polices\":[\"131127171629046d8587a82a24407783\",\"1311271716290462800752c1b54998cf\",\"1311271716290462ba00a65824b90361\"]}");
		som.put("policePointGeos", "[{\"geo\":{\"type\":\"point\",\"points\":\"108.917935330195,34.2672223225701\"},\"name\":\"控制点2\",\"geoPolices\":[\"131127171628968392f5dd1eb04bcc4f\"]},{\"geo\":{\"type\":\"point\",\"points\":\"108.917964978002,34.265291949521\"},\"name\":\"控制点3\",\"geoPolices\":[\"131127171629000ba639a57a3a5b3d52\"]},{\"geo\":{\"type\":\"point\",\"points\":\"108.918504979313,34.2771919857112\"},\"name\":\"控制点4\",\"geoPolices\":[\"1311271716290783a2f365d1533a6fcb\"]},{\"geo\":{\"type\":\"point\",\"points\":\"108.91877443967,34.2524619243686\"},\"name\":\"控制点5\",\"geoPolices\":[\"1311271716290784a29c05e823d4df71\"]},{\"geo\":{\"type\":\"point\",\"points\":\"108.918831632598,34.2426153988199\"},\"name\":\"控制点6\",\"geoPolices\":[\"131127171628968dbe5b881f41ceefb0\"]},{\"geo\":{\"type\":\"point\",\"points\":\"108.914765398247,34.2299967466951\"},\"name\":\"控制点7\",\"geoPolices\":[\"1311271716290007858c36a8e93b4399\"]}]");
		som.put("resCross", "[{\"geo\":{\"type\":\"point\",\"points\":\"108.91825687535,34.224499667368\"},\"attr\":{\"NAME\":\"北二环市政二公司门口由西向东\",\"ROADID\":\"1312111737101875cca5\",\"ROADNAME\":\"吉祥路\",\"ID\":\"386\",\"code\":\"610100000900021015\",\"TYPE\":null,\"CODE\":\"610100000900021015\",\"layerType\":\"cross\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.9130490353,34.25234357963\"},\"attr\":{\"NAME\":\"丰庆路东路幸福苑小区门口由东向西\",\"ROADID\":\"1405081446376251a138\",\"ROADNAME\":\"城区道路\",\"ID\":\"399\",\"code\":\"610100001600021042\",\"TYPE\":null,\"CODE\":\"610100001600021042\",\"layerType\":\"cross\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.9330305591,34.271325729804\"},\"attr\":{\"NAME\":\"莲湖路许世庙街\",\"ROADID\":\"1405081446376251a138\",\"ROADNAME\":\"城区道路\",\"ID\":\"413\",\"code\":\"610100001600021069\",\"TYPE\":null,\"CODE\":\"610100001600021069\",\"layerType\":\"cross\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.92755829612,34.243225225529\"},\"attr\":{\"NAME\":\"三爻村\",\"ROADID\":\"14060415365523410f66\",\"ROADNAME\":\"凤城二路\",\"ID\":\"418\",\"code\":\"610100002404021096\",\"TYPE\":null,\"CODE\":\"610100002404021096\",\"layerType\":\"cross\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.91000980913,34.242746404895\"},\"attr\":{\"NAME\":\"生产力大厦\",\"ROADID\":\"131128081947828d41d5\",\"ROADNAME\":\"友谊西路\",\"ID\":\"419\",\"code\":\"610103610103021008\",\"TYPE\":null,\"CODE\":\"610103610103021008\",\"layerType\":\"cross\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.91767033906,34.234207209661\"},\"attr\":{\"NAME\":\"太白路立交南由南向北\",\"ROADID\":\"131128081350984103e1\",\"ROADNAME\":\"太白北路\",\"ID\":\"422\",\"code\":\"610100001500021082\",\"TYPE\":null,\"CODE\":\"610100001500021082\",\"layerType\":\"cross\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.94245322468,34.28588803116\"},\"attr\":{\"NAME\":\"未央路振华路十字东南角\",\"ROADID\":\"1312111737101875cca5\",\"ROADNAME\":\"吉祥路\",\"ID\":\"432\",\"code\":\"610113610113011009\",\"TYPE\":null,\"CODE\":\"610113610113011009\",\"layerType\":\"cross\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.94247701929,34.285953466338\"},\"attr\":{\"NAME\":\"未央路振华路十字西北角\",\"ROADID\":\"1405081446376251a138\",\"ROADNAME\":\"城区道路\",\"ID\":\"433\",\"code\":\"610100001600021068\",\"TYPE\":null,\"CODE\":\"610100001600021068\",\"layerType\":\"cross\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.94046042609,34.277815709697\"},\"attr\":{\"NAME\":\"环城北路环北西闸口由东向西\",\"ROADID\":\"1405081446376251a138\",\"ROADNAME\":\"城区道路\",\"ID\":\"407\",\"code\":\"610100001400021084\",\"TYPE\":\"公路卡口设备\",\"CODE\":\"610100001400021084\",\"layerType\":\"cross\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.92613607084,34.242671070641\"},\"attr\":{\"NAME\":\"友谊路急救中心由西向东\",\"ROADID\":\"1405081446376251a138\",\"ROADNAME\":\"城区道路\",\"ID\":\"444\",\"code\":\"610100001500021060\",\"TYPE\":\"公路卡口设备\",\"CODE\":\"610100001500021060\",\"layerType\":\"cross\"}}]");
		som.put("resVideo", "[{\"geo\":{\"type\":\"point\",\"points\":\"108.91886153738,34.242754832861\"},\"attr\":{\"NAME\":\"测试视频十\",\"ROADID\":\"131128081947828d41d5\",\"ROADNAME\":\"友谊西路\",\"ID\":\"12\",\"code\":\"610115610115050023\",\"TYPE\":\"新奕天\",\"CODE\":\"610115610115050023\",\"layerType\":\"video\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.91842399235,34.224497222565\"},\"attr\":{\"NAME\":\"岩涛测试修改\",\"ROADID\":\"1312111737101875cca5\",\"ROADNAME\":\"吉祥路\",\"ID\":\"14\",\"code\":\"610100000300050032\",\"TYPE\":\"新奕天\",\"CODE\":\"610100000300050032\",\"layerType\":\"video\"}}]");
		som.put("resSignal", "[]");
		
		Map<String,Object> som2 = new HashMap<String, Object>();
		som.put("somId", "1");
		som2.put("somName", "城西客运站到支队");
		som2.put("startGeo", "{\"type\":\"point\",\"points\":\"108.86855495942,34.270980578506\"}");
		som2.put("endGeo", "{\"type\":\"point\",\"points\":\"108.89981992457,34.232798585669\"}");
		som2.put("roadGeo", "{\"type\":\"multipolyline\",\"points\":\"108.8673761041022,34.27056717742407,108.8674047749106,34.270567701138646,108.8674047749106,34.270567701138646,108.86740584870114,34.270432474579195,108.86740584870114,34.270432474579195,108.8674280457679,34.267635441822335,108.86744137641858,34.266579625149745,108.86744137641858,34.266579625149745,108.86746024689307,34.26508512188104,108.86746024689307,34.26508512188104,108.86747539417428,34.2638922764076,108.86747539417428,34.2638922764076,108.86749930894615,34.260965336581194,108.86749930894615,34.260965336581194,108.8675649018989,34.2560900389243,108.8675649018989,34.2560900389243,108.86757894121536,34.25430520931815,108.86757894121536,34.25430520931815,108.86758207355405,34.25390708484423,108.86758207355405,34.25390708484423,108.86749609566846,34.25374925922108,108.86753445085441,34.250696824399085,108.86753445085441,34.250696824399085,108.86755923257272,34.2482812930474,108.8675750732312,34.24672589128704,108.8675750732312,34.24672589128704,108.86759309294706,34.24495652012746,108.86764432642468,34.24226762365208,108.86764432642468,34.24226762365208,108.86767983165907,34.23944074779246,108.86768707210086,34.238985172827654,108.86768707210086,34.238985172827654,108.86788734302661,34.23898178058488,108.86788734302661,34.23898178058488,108.87021916098564,34.23894224818639,108.87255998205768,34.238905233889625,108.87255998205768,34.238905233889625,108.87718045411486,34.23884848936643,108.87718045411486,34.23884848936643,108.8806926952119,34.23879274309081,108.88119158422438,34.23879085721245,108.88119158422438,34.23879085721245,108.88345450242355,34.23878476340627,108.88345450242355,34.23878476340627,108.88509291340739,34.23879289867352,108.88509291340739,34.23879289867352,108.8859710860969,34.23878231005574,108.88621068797306,34.23877378358338,108.8864678410182,34.23872788038756,108.88668607230454,34.238662497876135,108.88694862308068,34.23855580140929,108.88976995641855,34.237305357956075,108.89041536567674,34.23700314168178,108.89041536567674,34.23700314168178,108.89267055600152,34.23597846943096,108.89267055600152,34.23597846943096,108.8941207361832,34.23534092564336,108.89514420244313,34.234863996276545,108.89514420244313,34.234863996276545,108.89515080976224,34.23486091609857,108.89762970105005,34.233757127789374,108.89762970105005,34.233757127789374,108.89877661544864,34.23324879409671,108.89975357956422,34.23281161206643,108.89975357956422,34.23281161206643,108.89977784731997,34.2328494480063\"}");
		som2.put("ctlPointGeos", "[{\"geo\":{\"type\":\"point\",\"points\":\"108.867437648546,34.2668748808334\"},\"name\":\"控制点1\",\"geoExtent\":\"\"},{\"geo\":{\"type\":\"point\",\"points\":\"108.867496349485,34.261327546336\"},\"name\":\"控制点2\",\"geoExtent\":\"\"},{\"geo\":{\"type\":\"point\",\"points\":\"108.867581174619,34.2540213407119\"},\"name\":\"控制点3\",\"geoExtent\":\"\"},{\"geo\":{\"type\":\"point\",\"points\":\"108.867687072101,34.2389851728277\"},\"name\":\"控制点4\",\"geoExtent\":{\"type\":\"polygon\",\"points\":\"108.86510902199,34.241520328763,108.8644047428,34.235445920812,108.86959880178,34.234829676527,108.87074325545,34.241872468354,108.86510902199,34.241520328763\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.87725453591,34.2388473135405\"},\"name\":\"控制点5\",\"geoExtent\":\"\"},{\"geo\":{\"type\":\"point\",\"points\":\"108.890265751261,34.2370731994175\"},\"name\":\"控制点6\",\"geoExtent\":\"\"}]");
		som2.put("gpsCar", "{\"id\":\"131128091047593b85512ce0dea2ac73\",\"name\":\"陕A6307警\",\"polices\":[\"131127171629062193422b5b073a5655\",\"1311271716290789b722d4608ec277c8\",\"131127171629078b868884ae47f39f33\"]}");
		som2.put("policePointGeos", "[{\"geo\":{\"type\":\"point\",\"points\":\"108.867437648546,34.2668748808334\"},\"name\":\"控制点1\",\"geoPolices\":[\"1311271716290007858c36a8e93b4399\"]},{\"geo\":{\"type\":\"point\",\"points\":\"108.867496349485,34.261327546336\"},\"name\":\"控制点2\",\"geoPolices\":[\"1311271716290158af7112aa5ad059c3\"]},{\"geo\":{\"type\":\"point\",\"points\":\"108.867581174619,34.2540213407119\"},\"name\":\"控制点3\",\"geoPolices\":null},{\"geo\":{\"type\":\"point\",\"points\":\"108.867687072101,34.2389851728277\"},\"name\":\"控制点4\",\"geoPolices\":null},{\"geo\":{\"type\":\"point\",\"points\":\"108.87725453591,34.2388473135405\"},\"name\":\"控制点5\",\"geoPolices\":[\"131127171628984fa739daddddc8810c\",\"1311271716290155b387174b851b272a\",\"1311271716290159909c095c6765e5e2\"]},{\"geo\":{\"type\":\"point\",\"points\":\"108.890265751261,34.2370731994175\"},\"name\":\"控制点6\",\"geoPolices\":null}]");
		som2.put("resCross", "[{\"geo\":{\"type\":\"point\",\"points\":\"108.87715486603,34.261456915282\"},\"attr\":{\"NAME\":\"沣镐路团结南路十字西由东向西\",\"ROADID\":\"1405081446376251a138\",\"ROADNAME\":\"城区道路\",\"ID\":\"402\",\"code\":\"610100001600021041\",\"TYPE\":null,\"CODE\":\"610100001600021041\",\"layerType\":\"cross\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.87937966207,34.260445644355\"},\"attr\":{\"NAME\":\"沣镐路团结南路十字西由西向东\",\"ROADID\":\"1405081446376251a138\",\"ROADNAME\":\"城区道路\",\"ID\":\"403\",\"code\":\"610100001600021060\",\"TYPE\":null,\"CODE\":\"610100001600021060\",\"layerType\":\"cross\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.87065893749,34.260945331166\"},\"attr\":{\"NAME\":\"沣镐西路中信银行东西双向\",\"ROADID\":\"1405081446376251a138\",\"ROADNAME\":\"城区道路\",\"ID\":\"404\",\"code\":\"610100001600021063\",\"TYPE\":null,\"CODE\":\"610100001600021063\",\"layerType\":\"cross\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.8676251247,34.260719282371\"},\"attr\":{\"NAME\":\"红光路药厂十字东西双向\",\"ROADID\":\"1405081446376251a138\",\"ROADNAME\":\"城区道路\",\"ID\":\"405\",\"code\":\"610100001600021055\",\"TYPE\":null,\"CODE\":\"610100001600021055\",\"layerType\":\"cross\"}},{\"geo\":{\"type\":\"point\",\"points\":\"108.90842098365,34.239494490199\"},\"attr\":{\"NAME\":\"南二环二十所由西向东\",\"ROADID\":\"1405081446376251a138\",\"ROADNAME\":\"城区道路\",\"ID\":\"414\",\"code\":\"610100001500021064\",\"TYPE\":null,\"CODE\":\"610100001500021064\",\"layerType\":\"cross\"}}]");
		som2.put("resVideo", "[{\"geo\":{\"type\":\"point\",\"points\":\"108.89113156009,34.245910292123\"},\"attr\":{\"NAME\":\"岩涛测试二\",\"ROADID\":\"1312111737101875cca5\",\"ROADNAME\":\"吉祥路\",\"ID\":\"10\",\"code\":\"610100000300050033\",\"TYPE\":\"新奕天\",\"CODE\":\"610100000300050033\",\"layerType\":\"video\"}}]");
		som2.put("resSignal", "[]");
		
		somList.add(som);
		somList.add(som2);
	}
	
}
