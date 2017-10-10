package com.xiangxun.atms.module.map.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.module.geoServer.domain.LayerBean;
import com.xiangxun.atms.module.geoServer.domain.LayerEnum;
import com.xiangxun.atms.module.geoServer.service.IMapOperation;
import com.xiangxun.atms.module.map.service.MapDataService;
import com.xiangxun.atms.module.map.vo.RepairCount;
import com.xiangxun.atms.module.map.vo.RepairLandBlock;




@Controller
@RequestMapping(value = "compare/land")
public class LandRepairCompare extends BaseCtl{

	@Resource
	IMapOperation iMapOperation;
	@Resource
	MapDataService mapDataService;
	
	
	
	@RequestMapping(value = "compare/{menuid}")
	public String compare(@PathVariable String menuid){
	
		
		
		
		
		
		return "compare/repairLandCompare";
	}
	
	@RequestMapping(value = "searchInfo/{proId}/{scheduleCodes}/")
	  public String searchInfo(@PathVariable("proId") String proId,@PathVariable("scheduleCodes") String scheduleCodes,Model model){
		//修复阶段为两个阶段故传过来为字符串
		String[] scheduleCode=scheduleCodes.split(",");
		//输入修复阶段的时间,第一阶段
		RepairCount  repairCount1 =  mapDataService.getRepairCount(proId, scheduleCode[0],"" ,"" );
		//输入修复阶段的时间,第二阶段
		RepairCount  repairCount2 =  mapDataService.getRepairCount(proId, scheduleCode[1],"" ,"" );
		List<RepairLandBlock> repairLand=mapDataService.getRepairLandBlock(proId);
		String geoJson="";
		   if(repairLand!=null ){
			   for(int i=0 ;i<repairLand.size();i++){
				   geoJson+=repairLand.get(i).getGeoJson()+"#";
			   }
		   }
		repairCount1.setGeoJson(geoJson);
		repairCount2.setGeoJson(geoJson);
		model.addAttribute("info1",repairCount1);
		model.addAttribute("info2",repairCount2);
		return "compare/repairLandCompare";
	}
}
