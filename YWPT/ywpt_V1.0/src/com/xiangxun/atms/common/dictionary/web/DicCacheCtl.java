package com.xiangxun.atms.common.dictionary.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.framework.base.BaseCtl;

/***
 * 数据字典缓存
 * 
 */
@Controller
@RequestMapping(value="system/diccache")
public class DicCacheCtl extends BaseCtl{

	@Resource
	DicService dicService;
	
	/**
	 * 从缓存中 获取车辆颜色数据字典
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "platecolor/{code}")
	@ResponseBody
	public Dic getCachePlatecolor(@PathVariable String code) {
		return dicService.getDicByCodeAndType(code, DicType.PLATE_COLOR);
	}
	
	/**
	 * 从缓存中 获取车辆类型数据字典
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "platetype/{code}")
	@ResponseBody
	public Dic getCachePlatetype(@PathVariable String code) {
		return dicService.getDicByCodeAndType(code, DicType.PLATE_TYPE);
	}
	
	/**
	 * 通讯运营商
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "isp/{code}")
	@ResponseBody
	public Dic getCacheIspId(@PathVariable String code) {
		return dicService.getDicByCodeAndType(code, DicType.NET_COMPANY);
	}
	
	/**
	 * 通讯方式
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "network/{code}")
	@ResponseBody
	public Dic getCacheNetWorkId(@PathVariable String code) {
		return dicService.getDicByCodeAndType(code, DicType.NET_IN_TYPE);
	}
	
	/**
	 * 卡口类型
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "devicetypecode/{code}")
	@ResponseBody
	public Dic getCacheDevicetypecode(@PathVariable String code) {
		return dicService.getDicByCodeAndType(code, DicType.DEVICE_TYPE);
	}
	
	/**
	 * 获取所有的卡口类型
	 * @return
	 */
	@RequestMapping(value = "devicetypecode")
	@ResponseBody
	public List<Dic> getCacheDevicetypecode() {
		return dicService.getDicByType(DicType.DEVICE_TYPE);
	}
	
	/**
	 * 获取所有的设备方向
	 * @return
	 */
	@RequestMapping(value = "devicetdirection")
	@ResponseBody
	public List<Dic> getCacheDeviceDirection() {
		return dicService.getDicByType(DicType.DIRECT);
	}
	
	/**
	 * 获取所有设备监控类型
	 * @return
	 */
	@RequestMapping(value = "devicetmonittype")
	@ResponseBody
	public List<Dic> getCacheDeviceMonittype() {
		return dicService.getDicByType(DicType.MONITOR_TYPE);
	}
	
}
