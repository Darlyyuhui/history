package com.xiangxun.atms.module.property.cache;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.framework.cache.BaseCache;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.ZhongWenToPinYin;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
/***
 * 卡口设备 信息缓存
 * @author YanTao
 * @Apr 21, 2013 2:04:23 PM
 */
@Component
public class DeviceInfoCache implements BaseCache{
	
	private static Logging logger = new Logging(DeviceInfoCache.class);

	@Resource
	Cache cache;

	@Resource
	DeviceInfoService deviceInfoService;
	
	@Override
	public String getCacheKey() {
		return ANALYZE_CACHE;
	}

	@Override
	public void init() throws Exception {
		//全部符合权限的设备信息 按CODE存储
		logger.warn("[卡口设备 按CODE缓存] 缓存初始化开始");
		List<DeviceInfo> devList =  deviceInfoService.getAllDeviceByAuthority();
		Table<String, String, DeviceInfo> table = HashBasedTable.create();
		for (DeviceInfo device : devList) {
			//存储的对象为table
			table.put(device.getCode(), DeviceInfo.class.getSimpleName(),device);
		}
		cache.put(DeviceInfo.class.getSimpleName(), table);
		logger.warn("[卡口设备 按CODE缓存] 缓存初始化完成");
		
		//===================== add by kouyunhao 2014-01-21 添加deviceCode-deviceName缓存。
		Table<String, String, String> table1 = HashBasedTable.create();
		for (DeviceInfo device : devList) {
			//存储的对象为table
			table1.put(device.getCode(), "devcode_name",device.getName());
		}
		cache.put("devcode_name", table1);
		
		
		//全部符合权限的设备信息 按ID存储
		logger.warn("[卡口设备 按ID缓存] 缓存初始化开始");
		List<DeviceInfo> devList2 =  deviceInfoService.getAllDeviceByAuthority();
		Table<String, String, DeviceInfo> table2 = HashBasedTable.create();
		for (DeviceInfo device : devList2) {
			//存储的对象为table
			table2.put(device.getId(),"DeviceInfoById",device);
		}
		cache.put("DeviceInfoById", table2);
		logger.warn("[卡口设备 按ID缓存] 缓存初始化完成");
		
		logger.warn("[卡口设备 按拼音排序] 缓存初始化开始");
		//以下缓存 供地点设备对话框用
		
		//根据首字母分组合
		Set<DeviceInfo> devListAll = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterA = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterB = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterC = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterD = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterE = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterF = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterG = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterH = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterI = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterJ = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterK = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterL = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterM = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterN = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterO = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterP = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterQ = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterR = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterS = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterT = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterU = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterV = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterW = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterX = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterY = new HashSet<DeviceInfo>();
		Set<DeviceInfo> devListLetterZ = new HashSet<DeviceInfo>();
		
		for (DeviceInfo dev : devList) {
			String devName = dev.getName();
			if(!StringUtils.isEmpty(devName)){
				String pinyin = ZhongWenToPinYin.getPinYinNormal(devName);
				if(pinyin!=null && pinyin.length()>0){
					devListAll.add(dev);
					pinyin = pinyin.toLowerCase();
					char word = pinyin.charAt(0);
					if("a".equals(String.valueOf(word))){
						devListLetterA.add(dev);
					}else if("b".equals(String.valueOf(word))){
						devListLetterB.add(dev);
					}else if("c".equals(String.valueOf(word))){
						devListLetterC.add(dev);
					}else if("d".equals(String.valueOf(word))){
						devListLetterD.add(dev);
					}else if("e".equals(String.valueOf(word))){
						devListLetterE.add(dev);
					}else if("f".equals(String.valueOf(word))){
						devListLetterF.add(dev);
					}else if("g".equals(String.valueOf(word))){
						devListLetterG.add(dev);
					}else if("h".equals(String.valueOf(word))){
						devListLetterH.add(dev);
					}else if("i".equals(String.valueOf(word))){
						devListLetterI.add(dev);
					}else if("j".equals(String.valueOf(word))){
						devListLetterJ.add(dev);
					}else if("k".equals(String.valueOf(word))){
						devListLetterK.add(dev);
					}else if("l".equals(String.valueOf(word))){
						devListLetterL.add(dev);
					}else if("m".equals(String.valueOf(word))){
						devListLetterM.add(dev);
					}else if("n".equals(String.valueOf(word))){
						devListLetterN.add(dev);
					}else if("o".equals(String.valueOf(word))){
						devListLetterO.add(dev);
					}else if("p".equals(String.valueOf(word))){
						devListLetterP.add(dev);
					}else if("q".equals(String.valueOf(word))){
						devListLetterQ.add(dev);
					}else if("r".equals(String.valueOf(word))){
						devListLetterR.add(dev);
					}else if("s".equals(String.valueOf(word))){
						devListLetterS.add(dev);
					}else if("t".equals(String.valueOf(word))){
						devListLetterT.add(dev);
					}else if("u".equals(String.valueOf(word))){
						devListLetterU.add(dev);
					}else if("v".equals(String.valueOf(word))){
						devListLetterV.add(dev);
					}else if("w".equals(String.valueOf(word))){
						devListLetterW.add(dev);
					}else if("x".equals(String.valueOf(word))){
						devListLetterX.add(dev);
					}else if("y".equals(String.valueOf(word))){
						devListLetterY.add(dev);
					}else if("z".equals(String.valueOf(word))){
						devListLetterZ.add(dev);
					}
				}
			}
		}
		
		Table<String, String, Set<DeviceInfo>> tablelist = HashBasedTable.create();
		tablelist.put("devListAll", "devListLetter",devListAll);
		tablelist.put("devListLetterA", "devListLetter",devListLetterA);
		tablelist.put("devListLetterB", "devListLetter",devListLetterB);
		tablelist.put("devListLetterC", "devListLetter",devListLetterC);
		tablelist.put("devListLetterD", "devListLetter",devListLetterD);
		tablelist.put("devListLetterE", "devListLetter",devListLetterE);
		tablelist.put("devListLetterF", "devListLetter",devListLetterF);
		tablelist.put("devListLetterG", "devListLetter",devListLetterG);
		tablelist.put("devListLetterH", "devListLetter",devListLetterH);
		tablelist.put("devListLetterI", "devListLetter",devListLetterI);
		tablelist.put("devListLetterJ", "devListLetter",devListLetterJ);
		tablelist.put("devListLetterK", "devListLetter",devListLetterK);
		tablelist.put("devListLetterL", "devListLetter",devListLetterL);
		tablelist.put("devListLetterM", "devListLetter",devListLetterM);
		tablelist.put("devListLetterN", "devListLetter",devListLetterN);
		tablelist.put("devListLetterO", "devListLetter",devListLetterO);
		tablelist.put("devListLetterP", "devListLetter",devListLetterP);
		tablelist.put("devListLetterQ", "devListLetter",devListLetterQ);
		tablelist.put("devListLetterR", "devListLetter",devListLetterR);
		tablelist.put("devListLetterS", "devListLetter",devListLetterS);
		tablelist.put("devListLetterT", "devListLetter",devListLetterT);
		tablelist.put("devListLetterU", "devListLetter",devListLetterU);
		tablelist.put("devListLetterV", "devListLetter",devListLetterV);
		tablelist.put("devListLetterW", "devListLetter",devListLetterW);
		tablelist.put("devListLetterX", "devListLetter",devListLetterX);
		tablelist.put("devListLetterY", "devListLetter",devListLetterY);
		tablelist.put("devListLetterZ", "devListLetter",devListLetterZ);
		cache.put("devListLetter", tablelist);
		
		logger.warn("[卡口设备 按拼音排序] 缓存初始化完成");
	}

}
