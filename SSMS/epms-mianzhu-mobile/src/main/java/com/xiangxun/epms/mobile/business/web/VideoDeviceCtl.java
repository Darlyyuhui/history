package com.xiangxun.epms.mobile.business.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xiangxun.epms.mobile.business.domain.LandBlock;
import com.xiangxun.epms.mobile.business.domain.VideoDevice;
import com.xiangxun.epms.mobile.business.service.LandBlockService;
import com.xiangxun.epms.mobile.business.service.MapService;
import com.xiangxun.epms.mobile.business.service.VideoDeviceService;
import com.xiangxun.epms.mobile.business.domain.LayerBean;
@Controller
@RequestMapping(value="server/video")
public class VideoDeviceCtl extends BaseCtl {
	@Resource
	VideoDeviceService videoDeviceService;
	@Resource
	LandBlockService landBlockService;
	@Resource
	MapService mapService;
	@RequestMapping(value="queryAll")
	public void queryAll(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		try{
			List<VideoDevice> list = videoDeviceService.findAll();
			List<LandBlock> block = landBlockService.findAll();
			for(LandBlock info :block){
				List<LayerBean>  layerList= mapService.getLandByCode(info.getId());
						if(layerList!=null && layerList.size() > 0){
							 for (LayerBean layerBean : layerList) {
						        	info.setGeoJson(layerBean.getGeometry()==null?" ":layerBean.getGeometry());
						        	
								}
						}
			}
		
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("VideoList", list);map.put("LandBlockList", block);
			super.dataResult("1000", "查询成功", map, request, response);
			logger.info("videoDevice query success!");
		}catch(Exception  e){
			logger.error("videoDevice query failed:"+e.getMessage());
			super.simpleResult("1001", "查询失败", request, response);
		}
	}

}
