package com.xiangxun.atms.module.check.service.impl;

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
import com.xiangxun.atms.module.check.cache.DataCheckRuleCache;
import com.xiangxun.atms.module.check.dao.DataCheckRuleMapper;
import com.xiangxun.atms.module.check.service.DataCheckRuleService;
import com.xiangxun.atms.module.check.vo.DataCheckRule;
import com.xiangxun.atms.module.check.vo.DataCheckRuleSearch;

@Service
public class DataCheckRuleServiceImpl extends AbstractBaseService<DataCheckRule, DataCheckRuleSearch> implements DataCheckRuleService {
   
	@Resource
    DataCheckRuleMapper dataCheckRuleMapper;
    @Resource
    Cache cache;
    
    @Override
    public BaseMapper<DataCheckRule, DataCheckRuleSearch> getBaseMapper() {
         return dataCheckRuleMapper;
    }

	@Override
	public boolean checkOnly(String type, String objCode, String dimension) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("type", type);
		args.put("objCode", objCode);
		args.put("dimension", dimension);
		
		Integer count = dataCheckRuleMapper.checkOnly(args);
		return count == null || count == 0 ? true : false;
	}

	@Override
	public int deleteById(String id) {
		int isOK = super.deleteById(id);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int save(DataCheckRule record) {
		int isOK = super.save(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public int updateByIdSelective(DataCheckRule record) {
		int isOK = super.updateByIdSelective(record);
		this.refreshCache();
		return isOK;
	}

	@Override
	public void refreshCache() {
		List<DataCheckRule> list = this.selectByExample(new DataCheckRuleSearch());
		// row=dimension column=obj value=
		Table<String, String, List<DataCheckRule>> table = HashBasedTable.create();
		List<DataCheckRule> temp = null;
		String columnKey = null;
		String rowKey = null;
		for (DataCheckRule dcr : list) {
			columnKey = dcr.getCheckType();
			rowKey = dcr.getCheckObj();
			Map<String, List<DataCheckRule>> m = table.column(columnKey);
			if (m == null) {
				temp = new ArrayList<DataCheckRule>();
				temp.add(dcr);
				table.put(rowKey, columnKey, temp);
			} else {
				temp = m.get(rowKey);
				if (temp == null) {
					temp = new ArrayList<DataCheckRule>();
				}
				temp.add(dcr);
				table.put(rowKey, columnKey, temp);
			}
		}
		cache.put(DataCheckRuleCache.TYPE_ITEMS, table);
	}
}