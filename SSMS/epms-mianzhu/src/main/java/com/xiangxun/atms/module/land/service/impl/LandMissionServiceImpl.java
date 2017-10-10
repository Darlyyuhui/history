package com.xiangxun.atms.module.land.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.land.cache.LandMissionCache;
import com.xiangxun.atms.module.land.dao.LandMissionMapper;
import com.xiangxun.atms.module.land.service.LandMissionService;
import com.xiangxun.atms.module.land.vo.LandMission;
import com.xiangxun.atms.module.land.vo.LandMissionSearch;

@Service
public class LandMissionServiceImpl extends AbstractBaseService<LandMission, LandMissionSearch> implements LandMissionService {
    @Resource
    private LandMissionMapper landMissionMapper;
    @Resource
    Cache cache;
    
    @Override
    public BaseMapper<LandMission, LandMissionSearch> getBaseMapper() {
         return landMissionMapper;
    }

	@Override
	public List<LandMission> queryRegSelect(String sampleCode) {
		Map<String, Object> args = new HashMap<String, Object>();
		if (sampleCode.indexOf(",") > -1) {
			List<LandMission> list = new ArrayList<LandMission>();
			String[] codes = sampleCode.split(",");
			for (String code : codes) {
				args.put("sampleCode", code);
				list.addAll(landMissionMapper.queryRegSelect(args));
			}
			return list;
		} else {
			args.put("sampleCode", sampleCode);
			return landMissionMapper.queryRegSelect(args);
		}
	}

	@Override
	public LandMission getMissionById(String id) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", id);
		return landMissionMapper.getMissionById(args);
	}

	@Override
	public int deleteById(String id) {
		int isOK = super.deleteById(id);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int save(LandMission record) {
		int isOK = super.save(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int updateByIdSelective(LandMission record) {
		int isOK = super.updateByIdSelective(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public void refreshCache() {
		List<LandMission> list = this.selectByExample(null);
		
		Table<String, String, String> table1 = HashBasedTable.create();
		Table<String, String, String> table2 = HashBasedTable.create();
		Table<String, String, String> table3 = HashBasedTable.create();
		Table<String, String, String> table4 = HashBasedTable.create();
		
		for (LandMission m : list) {
			table1.put(m.getId(), LandMissionCache.LM_ID_NAMECODE, m.getName()+"【"+m.getCode()+"】");
			table2.put(m.getId(), LandMissionCache.LM_ID_NAME, m.getName());
			table3.put(m.getId(), LandMissionCache.LM_ID_CODE, m.getCode());
			table4.put(m.getCode(), LandMissionCache.LM_CODE_ID, m.getId());
		}
		cache.put(LandMissionCache.LM_ID_NAMECODE, table1);
        cache.put(LandMissionCache.LM_ID_NAME, table2);
        cache.put(LandMissionCache.LM_ID_CODE, table3);
        cache.put(LandMissionCache.LM_CODE_ID, table4);
	}

	@Override
	public boolean isDelete(String id) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", id);
		Integer num = landMissionMapper.isDelete(args);
		return num==null||num==0?true:false;
	}
    
}