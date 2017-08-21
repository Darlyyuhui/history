package com.xiangxun.atms.module.eventalarm.web;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.module.eventalarm.domain.AlarmLog;
import com.xiangxun.atms.module.eventalarm.domain.EventtypeInfo;
import com.xiangxun.atms.module.eventalarm.service.AlarmLogService;
import com.xiangxun.atms.module.eventalarm.service.EventtypeInfoService;
import com.xiangxun.atms.module.eventalarm.service.PublicMethodService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.question.domain.MonthReport;
import com.xiangxun.atms.module.question.service.TrendCountService;

/**
 * 事件告警首页
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value = "alarm/main")
public class EventalarmCtl extends BaseCtl {
	
	@Resource
	AlarmLogService alarmLogService;
	
	@Resource
	EventtypeInfoService eventtypeInfoService; 
	
	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	PublicMethodService publicMethodService;
	
	@Resource
	AssetInfoService assetInfoService;
	
	@Resource
	TrendCountService trendCountService;
	
	//FLASH图标颜色数组
			private String color[] = { "FFCC33", "51DCFF","5F96FC", "8BBA00", "FF8E46",
					"008E8E", "D64646", "8E468E", "588526", "B3AA00", "008ED6",
					"9D080D", "A186BE", "346DB2", "C13A36", "A02F2B", "7C9D35",
					"6A4A8D", "2B8CAC", "D2721E", "7792C1", "BF7B7A", "A6BC7C",
					"9183A7" };
	
	@RequestMapping(value="show/{menuid}/")
	public String show(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "ALARM_TIME desc") String sortType,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,HttpServletRequest request){
		/*List list = alarmLogService.getAlarmtypeCount();
		List listGrade = alarmLogService.getAlarmgradeCount();
		for (int i = list.size() - 1; i >= 0; i--) {
			Map valueMap = (Map) list.get(i);
			List<EventtypeInfo> typelist = eventtypeInfoService.findByCode(valueMap.get("EVENTTYPE").toString());
			if(!typelist.isEmpty() && typelist.size() > 0){
				valueMap.put("TYPES",typelist.get(0).getName());
			}else{
				list.remove(i);
			}
		}*/
		
	//所有资产数量柱状图
		List<AssetInfo> countByTypeList = assetInfoService.countByType();
		for(AssetInfo info:countByTypeList){
			String assettype = info.getAssettype();
			if("project".equals(assettype)){
				info.setAssettype("平台");
			}
			if("server".equals(assettype)){
				info.setAssettype("服务器");
			}
			if("cabinet".equals(assettype)){
				info.setAssettype("智能机柜");
			}
			if("device".equals(assettype)){
				info.setAssettype("卡口");
			}
			if("database".equals(assettype)){
				info.setAssettype("数据库");
			}
			if("ftp".equals(assettype)){
				info.setAssettype("FTP服务");
			}
		}
		model.addAttribute("countByTypeList", countByTypeList);
				
	//折线图xml
		Date d = DateUtil.getPerDay(new java.util.Date(), 9);
		String s = DateUtil.dateFormatToString(d, "yyyy-MM-dd");
		String e = DateUtil.dateFormatToString(new java.util.Date(),"yyyy-MM-dd");
				
		Map<String, Object> searchParams = new HashMap<String,Object>();
		searchParams.put("startTime",s);
		searchParams.put("endTime",e);
		//获取时间段内的所有日期
		List<String> mdays = DateUtil.getAllDatesByDate(s,e);
		List<MonthReport> clist = trendCountService.getLineSpaceReport(searchParams, menuid,s, e);
		boolean noDataFlag = this.getSpaceNodateStatus(clist);
		if (noDataFlag) {
			model.addAttribute("nodata", "nodata");
		}else{
			this.createLineDataXmlMonth(clist, model,mdays);
		}
		
	//查询告警事件
		List<AlarmLog> listByConditionOne = alarmLogService.getListByConditionOne();
			
		if(listByConditionOne != null && listByConditionOne.size() != 0){
				for(AlarmLog log : listByConditionOne){
					String[] attribute = alarmLogService.findEventAttributes(log.getEventType());
					log.setEventTypeName(attribute[0]);
					log.setEventLevel(attribute[1]);
					log.setAlarmType(attribute[2]);
					log.setAlarmColor(attribute[3]);
				}
		}
		Map<String, Object> workParams = new HashMap<String, Object>();
//		workParams.put("contact", getCurrentUserId().equals("130521090208040c87f4ab27fd194da7")?null:getCurrentUserId());
		//权限过滤（按照部门和角色）
				String userDept = getCurrentUser().getDeptId();
				Collection<GrantedAuthority> authoritys = getCurrentUser().getAuthorities();
				boolean hasAuthority = false;
				Iterator<GrantedAuthority> it = authoritys.iterator();
				while(it.hasNext()){
					GrantedAuthority authority = it.next();
					if(authority.equals("ADMINISTRATOR")){
						hasAuthority = true;
					}
				}
		if(userDept.equals("00") || hasAuthority){
			
		}else{
			workParams.put("contact", getCurrentUserId());
		}
		workParams.put("sortType", "ASSIGNTIME desc");
		Page workPage = workorderInfoService.getListByCondition(workParams,pageNumber,9,sortType);
		publicMethodService.setStatusColor(workPage);
		model.addAttribute("workList", workPage);
		model.addAttribute("pageList", listByConditionOne);
/*		model.addAttribute("alarmList", list);
		model.addAttribute("listGrade", listGrade);*/
		model.addAttribute("sortType", sortType);
		model.addAttribute("page", pageNumber);
		return "alarm/main/show";
	}
	
	private void createLineDataXmlMonth(List<MonthReport> list,ModelMap model,List<String> mdays) {
		//以下xml为生成局部的数据集合所用
		StringBuffer categoriexml = new StringBuffer("");
		categoriexml.append("<categories>");
		for (int i = 0; i < mdays.size(); i++) {
			categoriexml.append("<category name='"+mdays.get(i).substring(8,10)+"日' />");
		}
		categoriexml.append("</categories>");
		if(list!=null && list.size()>0){
			Set<MonthReport> set = new HashSet<MonthReport>();
			//封装数据
			for(int n=0;n<list.size();n++){
				MonthReport vo =  list.get(n);
				StringBuffer datasetxml = new StringBuffer("");
				datasetxml.append("<dataset seriesname='"+(vo.getTypeName()!=null ? vo.getTypeName().toString():"")+"' color='"+color[n % 10]+"'  areaAlpha='50' showAreaBorder='1' areaBorderThickness='2' areaBorderColor='FF0000'>");
				BigDecimal[] days = vo.getDays();
				for(int j=0;j<days.length;j++){
					datasetxml.append("<set value='"+days[j]+"' />");
				}
				datasetxml.append("</dataset>");
				vo.setViewId(String.valueOf(n));
				vo.setDataXml(datasetxml.toString());
				set.add(vo);
			}
			model.addAttribute("datasetss", set);
		}
		model.addAttribute("categoriexml", categoriexml);
	}
	
	private boolean getSpaceNodateStatus(List<MonthReport> list){
		boolean flag = false;
		long num = 0;
		for(int n=0;n<list.size();n++){
			MonthReport vo = list.get(n);
			BigDecimal[] days = vo.getDays();
			for(int j=0;j<days.length;j++){
				num += Double.valueOf(days[j].toString());
			}
	    }
		
		if(num==0){
			flag = true;
		}
		return flag;
	}

}
