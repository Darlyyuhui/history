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
import com.xiangxun.atms.module.property.domain.VideoInfo;
import com.xiangxun.atms.module.property.service.VideoInfoService;
/***
 * 视频设备信息缓存
 * @author YanTao
 * @Apr 21, 2013 2:04:23 PM
 */
@Component
public class VideoInfoCache implements BaseCache{

	private final Logging logger = new Logging(VideoInfoCache.class);
	
	/**
	 * add by haoxiang, 2015/05/26
	 * 缓存视频设备实体信息
	 */
	public static final String VIDEO_INFO = "videoInfoEntity";
	
	@Resource
	Cache cache;

	@Resource
	VideoInfoService videoInfoService;
	
	@Override
	public String getCacheKey() {
		return ANALYZE_CACHE;
	}

	@Override
	public void init() throws Exception {
		logger.info("[监控设备信息] 缓存初始化开始");
		List<VideoInfo> videoInfo =  videoInfoService.findAll();
		
		Table<String, String, String> table = HashBasedTable.create();
		for (VideoInfo vinfo : videoInfo) {
			//存储的对象为table
			table.put(vinfo.getCode(), VideoInfo.class.getSimpleName(), vinfo.getName());
		}
		cache.put(VideoInfo.class.getSimpleName(), table);
		logger.info("[监控设备信息] 缓存初始化完成");
		
		
		logger.info("[监控设备全量信息] 缓存初始化开始");
		Table<String, String, VideoInfo> table2 = HashBasedTable.create();
		for (VideoInfo vinfo : videoInfo) {
			//存储的对象为table
			table2.put(vinfo.getCode(), VIDEO_INFO, vinfo);
		}
		cache.put(VIDEO_INFO, table2);
		logger.info("[监控设备全量信息] 缓存初始化完成");
		
		
		
		logger.warn("[监控设备 按拼音排序] 缓存初始化开始");
		//以下缓存 供地点设备对话框用
		
		//根据首字母分组合
		Set<VideoInfo> videoListAll = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterA = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterB = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterC = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterD = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterE = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterF = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterG = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterH = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterI = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterJ = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterK = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterL = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterM = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterN = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterO = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterP = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterQ = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterR = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterS = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterT = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterU = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterV = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterW = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterX = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterY = new HashSet<VideoInfo>();
		Set<VideoInfo> videoListLetterZ = new HashSet<VideoInfo>();
		
		for (VideoInfo dev : videoInfo) {
			String devName = dev.getName();
			if(!StringUtils.isEmpty(devName)){
				String pinyin = ZhongWenToPinYin.getPinYinNormal(devName);
				if(pinyin!=null && pinyin.length()>0){
					videoListAll.add(dev);
					pinyin = pinyin.toLowerCase();
					char word = pinyin.charAt(0);
					if("a".equals(String.valueOf(word))){
						videoListLetterA.add(dev);
					}else if("b".equals(String.valueOf(word))){
						videoListLetterB.add(dev);
					}else if("c".equals(String.valueOf(word))){
						videoListLetterC.add(dev);
					}else if("d".equals(String.valueOf(word))){
						videoListLetterD.add(dev);
					}else if("e".equals(String.valueOf(word))){
						videoListLetterE.add(dev);
					}else if("f".equals(String.valueOf(word))){
						videoListLetterF.add(dev);
					}else if("g".equals(String.valueOf(word))){
						videoListLetterG.add(dev);
					}else if("h".equals(String.valueOf(word))){
						videoListLetterH.add(dev);
					}else if("i".equals(String.valueOf(word))){
						videoListLetterI.add(dev);
					}else if("j".equals(String.valueOf(word))){
						videoListLetterJ.add(dev);
					}else if("k".equals(String.valueOf(word))){
						videoListLetterK.add(dev);
					}else if("l".equals(String.valueOf(word))){
						videoListLetterL.add(dev);
					}else if("m".equals(String.valueOf(word))){
						videoListLetterM.add(dev);
					}else if("n".equals(String.valueOf(word))){
						videoListLetterN.add(dev);
					}else if("o".equals(String.valueOf(word))){
						videoListLetterO.add(dev);
					}else if("p".equals(String.valueOf(word))){
						videoListLetterP.add(dev);
					}else if("q".equals(String.valueOf(word))){
						videoListLetterQ.add(dev);
					}else if("r".equals(String.valueOf(word))){
						videoListLetterR.add(dev);
					}else if("s".equals(String.valueOf(word))){
						videoListLetterS.add(dev);
					}else if("t".equals(String.valueOf(word))){
						videoListLetterT.add(dev);
					}else if("u".equals(String.valueOf(word))){
						videoListLetterU.add(dev);
					}else if("v".equals(String.valueOf(word))){
						videoListLetterV.add(dev);
					}else if("w".equals(String.valueOf(word))){
						videoListLetterW.add(dev);
					}else if("x".equals(String.valueOf(word))){
						videoListLetterX.add(dev);
					}else if("y".equals(String.valueOf(word))){
						videoListLetterY.add(dev);
					}else if("z".equals(String.valueOf(word))){
						videoListLetterZ.add(dev);
					}
				}
			}
		}
		
		Table<String, String, Set<VideoInfo>> tablelist = HashBasedTable.create();
		tablelist.put("videoListAll", "videoListLetter",videoListAll);
		tablelist.put("videoListLetterA", "videoListLetter",videoListLetterA);
		tablelist.put("videoListLetterB", "videoListLetter",videoListLetterB);
		tablelist.put("videoListLetterC", "videoListLetter",videoListLetterC);
		tablelist.put("videoListLetterD", "videoListLetter",videoListLetterD);
		tablelist.put("videoListLetterE", "videoListLetter",videoListLetterE);
		tablelist.put("videoListLetterF", "videoListLetter",videoListLetterF);
		tablelist.put("videoListLetterG", "videoListLetter",videoListLetterG);
		tablelist.put("videoListLetterH", "videoListLetter",videoListLetterH);
		tablelist.put("videoListLetterI", "videoListLetter",videoListLetterI);
		tablelist.put("videoListLetterJ", "videoListLetter",videoListLetterJ);
		tablelist.put("videoListLetterK", "videoListLetter",videoListLetterK);
		tablelist.put("videoListLetterL", "videoListLetter",videoListLetterL);
		tablelist.put("videoListLetterM", "videoListLetter",videoListLetterM);
		tablelist.put("videoListLetterN", "videoListLetter",videoListLetterN);
		tablelist.put("videoListLetterO", "videoListLetter",videoListLetterO);
		tablelist.put("videoListLetterP", "videoListLetter",videoListLetterP);
		tablelist.put("videoListLetterQ", "videoListLetter",videoListLetterQ);
		tablelist.put("videoListLetterR", "videoListLetter",videoListLetterR);
		tablelist.put("videoListLetterS", "videoListLetter",videoListLetterS);
		tablelist.put("videoListLetterT", "videoListLetter",videoListLetterT);
		tablelist.put("videoListLetterU", "videoListLetter",videoListLetterU);
		tablelist.put("videoListLetterV", "videoListLetter",videoListLetterV);
		tablelist.put("videoListLetterW", "videoListLetter",videoListLetterW);
		tablelist.put("videoListLetterX", "videoListLetter",videoListLetterX);
		tablelist.put("videoListLetterY", "videoListLetter",videoListLetterY);
		tablelist.put("videoListLetterZ", "videoListLetter",videoListLetterZ);
		cache.put("videoListLetter", tablelist);
		
		logger.warn("[监控设备 按拼音排序] 缓存初始化完成");
	}

}
