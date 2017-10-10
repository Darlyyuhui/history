package com.xiangxun.atms.module.statistics.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.common.dictionary.vo.DicSearch;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.bs.cache.TRegionCache;
import com.xiangxun.atms.module.bs.vo.Region;
import com.xiangxun.atms.module.land.cache.LandBlockCache;
import com.xiangxun.atms.module.land.vo.LandBlock;
import com.xiangxun.atms.module.statistics.dao.RepairMapper;
import com.xiangxun.atms.module.statistics.service.RepairService;
import com.xiangxun.atms.module.statistics.vo.RepairInfo;

@Service
public class RepairServiceImpl implements RepairService {

	@Resource
	RepairMapper repairMapper;
	@Resource
	DicService dicService;
	@Resource
    Cache cache;
	
	@Override
	public List<String[]> getDicBySchedule() {
		DicSearch search = new DicSearch();
		//查询修复进度字典
		search.createCriteria().andTypeEqualTo(DicType.LAND_REPAIR_SCHEDULE.getValue());
		//按编号排序
		search.setOrderByClause("CODE ASC");
		List<Dic> list = dicService.selectByExample(search);
		List<String[]> returnList = new ArrayList<String[]>();
		for (Dic d : list) {
			//不取最终阶段
			if ("099".equals(d.getCode())) {
				continue;
			}
			returnList.add(new String[]{d.getCode(), d.getName()});
		}
		return returnList;
	}
	
	/**
	 * 获取区域地块总面积
	 * @return
	 */
	private Map<String, BigDecimal> getLandBlockTotal() {
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		@SuppressWarnings("unchecked")
		List<LandBlock> cacheList = (List<LandBlock>)cache.get(LandBlockCache.LB_ALL);
		BigDecimal bd = null;
		for (LandBlock lb : cacheList) {
			bd = map.get(lb.getRegionId());
			if (bd == null) {
				bd = new BigDecimal(0);
			}
			map.put(lb.getRegionId(), bd.add(lb.getArea()));
		}
		return map;
	}

	@Override
	public List<RepairInfo> getList(String regionId, String beginTime, String endTime) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("regionId", regionId);
		args.put("beginTime", beginTime);
		args.put("endTime", endTime);
		
		List<String[]> dicList = this.getDicBySchedule();
		String[] dicCodes = new String[dicList.size()];
		for (int i = 0; i < dicList.size(); i++) {
			dicCodes[i] = dicList.get(i)[0];
		}
		args.put("dicCodes", dicCodes);
		return this.makeData(repairMapper.getList(args));
	}
	
	/**
	 * 加工数据
	 * @param list
	 * @return
	 */
	private List<RepairInfo> makeData(List<RepairInfo> list) {
		//将查询出的集合转成map，key=regionId, value=RepairInfo
		Map<String, RepairInfo> map = new HashMap<String, RepairInfo>();
		for (RepairInfo ri : list) {
			if (ri == null) {
				continue;
			}
			map.put(ri.getRegionId(), ri);
		}
		//从缓存中取出所有区域
		@SuppressWarnings("unchecked")
		List<Region> regionList = (List<Region>)cache.get(TRegionCache.ALL_ITEM);
		
		//区域地块总面积集合
		Map<String, BigDecimal> totalMap = this.getLandBlockTotal();
		
		//初始化需要返回的结果集
		List<RepairInfo> returnList = new ArrayList<RepairInfo>();
		RepairInfo rinfo = null;
		String key = null;
		//循环所有区域信息
		for (Region r : regionList) {
			//只处理各乡镇
			if ("0".equals(r.getPid())) {
				key = r.getId();
				rinfo = map.get(key);
				//如果查询结果集中没有，则默认
				if (rinfo == null) {
					rinfo = new RepairInfo();
					rinfo.setRegionId(key);
				}
				//赋值总面积
				rinfo.setTotal(totalMap.get(key)==null?0.00:totalMap.get(key).doubleValue());
				returnList.add(rinfo);
			}
		}
		return returnList;
	}

}
