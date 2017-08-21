package com.xiangxun.atms.module.gis.web;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.module.gis.domain.LayerBean;
import com.xiangxun.atms.module.gis.domain.LayerEnum;
import com.xiangxun.atms.module.gis.service.IMapService;

@Controller
@RequestMapping(value="roadmaintenance")
public class RoadMaintenance extends BaseCtl {

	@Resource
	IMapService mapService;

	@RequestMapping(value="updateroadinfo/",method=RequestMethod.POST)
	@ResponseBody public String updateRoadInfo(@RequestParam(value="graphicList")String graphicList){
		JSONArray jsonArr = JSONArray.fromObject(graphicList);
		for(int i=0,len=jsonArr.size();i<len;i++){
			JSONObject jsonO = (JSONObject)jsonArr.get(i);
			//Graphic graphic = (Graphic)jsonArr.get(i);
			JSONObject geometry = jsonO.getJSONObject("geo");
			JSONObject attributes = jsonO.getJSONObject("attributes");
			LayerBean layerBean = new LayerBean();
			String id = "";
			if(attributes.containsKey("OBJECTID")){
				id = attributes.getString("OBJECTID");
			}else{
				id = attributes.getString("ID");
			}
			layerBean.setId(Long.parseLong(id));
			layerBean.setGeometry(geometry.toString());
			if(attributes.containsKey("CODE")){
				layerBean.setCode(attributes.getString("CODE"));
			}
			if(attributes.containsKey("NAME")){
				layerBean.setName(attributes.getString("NAME"));
			}
			if(attributes.containsKey("ROADID")){
				layerBean.setRoadId(attributes.getString("ROADID"));
			}
			mapService.updateAttrById(LayerEnum.ROAD_LINE,layerBean);
		}
		return this.returnJson();
	}
}