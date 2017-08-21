package com.xiangxun.atms.module.gis.util;

import java.io.File;

/**   
 *    
   
 * @类描述：   
 * @创建人：cd   
 * @创建时间：2013-7-17 上午8:42:42   
 * @修改人：Administrator   
 * @修改时间：2013-7-17 上午8:42:42   
 * @修改备注：   
 * @version  1.0
 *    
 */
public interface MapConstant {

	//设备图层定义
	String DEVICE_LAYER="device_class";
	//道路图层定义
	String ROAD_LAYER="road_class";
	//存放gis 图片的的文件夹的上级文件夹
	String MAP_PIC_PARENT="images";
	//存放gis 图片的的文件夹
	String MAP_PIC_FORLDER=MAP_PIC_PARENT+File.separator+"mapUpload";
	//map配置文件
	String MAP_PROPERTIES="mapconfig.properties";
	//定义按照设备号的查询(布控中使用到)
	String DEVICECOND_BY_CODE="code ={0}";
	//按照道路名称查询(布控中使用到)
	String ROAD_BY_NAME="DLMC like'%{0}%'";
}


