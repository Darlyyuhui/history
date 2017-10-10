package com.xiangxun.atms.module.check.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.module.check.dao.LandCheckMapper;
import com.xiangxun.atms.module.check.service.LandCheckService;
import com.xiangxun.atms.module.check.vo.DataCheckInfo;
import com.xiangxun.atms.module.check.vo.DataCheckResult;
import com.xiangxun.atms.module.check.vo.DataCheckRule;
import com.xiangxun.atms.module.check.vo.LandAnalysis;

@Service
public class LandCheckServiceImpl implements LandCheckService {

	@Resource
	LandCheckMapper landCheckMapper;
	
	@Override
	public List<DataCheckResult> doLandCheck(Map<String, List<DataCheckRule>> cacheMap, DataCheckInfo info) {
		//获取方案采样分析数据
		List<LandAnalysis> dataList = this.getLandData(info.getSchemeId());
		
		//存放不同校验对象、不同维度判断的异常项
		List<DataCheckResult> list = new ArrayList<DataCheckResult>();
		//土壤镉、PH、有效态镉的校验
		String[] mapKey = {"001", "002", "003"};
		for (String mk : mapKey) {
			//获取不同校验对象的校验规则
			List<DataCheckRule> ruleList = cacheMap.get(mk);
			if (ruleList == null) {
				continue;
			}
			//循环校验规则
			for (DataCheckRule rule : ruleList) {
				System.out.println(rule.getCheckObj());
				//不同维度的标准差，平均值
				LandAnalysis dev = this.getLandDev(info, rule);
				if (dev == null) {
					continue;
				}
				//将所有异常内容存入临时集合
				list.addAll(this.landCheck(rule, dev, dataList));
			}
		}
		//将不同规则判断出结果转成map
		Map<String, List<DataCheckResult>> map = this.makeMap(list);
		
		List<DataCheckResult> returnList = new ArrayList<DataCheckResult>();
		List<DataCheckResult> temp = null;
		DataCheckResult dcr = null;
		//存放拆解的map键值，0=id，1=code
		String[] strs = null;
		
		for (String key : map.keySet()) {
			if (StringUtils.isEmpty(key) || ",".equals(key)) {
				continue;
			}
			String errItems = "";
			String errTypes = "";
			int isInvalid = 0;
			dcr = new DataCheckResult();
			dcr.setId(UuidGenerateUtil.getUUIDLong());
			dcr.setInfoId(info.getId());
			strs = key.split(",");
			dcr.setRegId(strs[0]);
			dcr.setRegCode(strs[1]);
			temp = map.get(key);
			for (DataCheckResult t : temp) {
				errItems += t.getErrItems() + ",";
				errTypes += t.getErrTypes() + ",";
				if (isInvalid < t.getIsInvalid()) {
					isInvalid = t.getIsInvalid();
				}
			}
			dcr.setErrItems(errItems.substring(0, errItems.length()-1));
			dcr.setErrTypes(errTypes.substring(0, errTypes.length()-1));
			dcr.setIsInvalid(isInvalid);
			
			returnList.add(dcr);
		}
		return returnList;
	}
	
	/**
	 * 获取方案下的所有采样数据
	 * @param schemeId
	 * @return
	 */
	private List<LandAnalysis> getLandData(String schemeId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("schemeId", schemeId);
		return landCheckMapper.getLandDataBySchemeId(args);
	}
	
	/**
	 * 获取不同维度的标准差
	 * @param info
	 * @param rule
	 * @return
	 */
	private LandAnalysis getLandDev(DataCheckInfo info, DataCheckRule rule) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -1);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("schemeId", info.getSchemeId());
		//判断不同维度
		if ("01".equals(rule.getCheckDimension())) {
			//年份
			args.put("beginTime", "1980-01-01 00:00:00");
			args.put("endTime", DateUtil.formatDate("yyyy", c.getTime())+"-12-31 23:59:59");
		}else if ("02".equals(rule.getCheckDimension())) {
			//区域
			args.put("regionId", info.getRegionId());
		} else if ("03".equals(rule.getCheckDimension())) {
			//年份区域
			args.put("regionId", info.getRegionId());
			args.put("beginTime", "1980-01-01 00:00:00");
			args.put("endTime", DateUtil.formatDate("yyyy", c.getTime())+"-12-31 23:59:59");
		}
		//获取标准差和平均值
		return landCheckMapper.getLandStdevAndAvg(args);
	}
	
	/**
	 * 土壤数据校验
	 * @param rule		校验规则
	 * @param dev		标准差
	 * @param dataList	数据集合
	 * @return
	 */
	private List<DataCheckResult> landCheck(DataCheckRule rule, LandAnalysis dev, List<LandAnalysis> dataList) {
		List<DataCheckResult> returnList = new ArrayList<DataCheckResult>();
		for (LandAnalysis la : dataList) {
			if ("001".equals(rule.getCheckObj())) {//镉
				//异常标准差值
				double cdDev1 = dev.getStdevCd() * rule.getOutlierParameter();
				//失效标准差值
				double cdDev2 = dev.getStdevCd() * rule.getInvalidParameter();
				//判断的绝对值
				double val = this.getVal(la.getCd(), dev.getAvgCd());
				//数据值
				if (val >= cdDev1 && val < cdDev2) {
					returnList.add(this.makeResult(la, rule, "镉", false));
				} else if (val >= cdDev2){
					returnList.add(this.makeResult(la, rule, "镉", true));
				}
			} else if ("002".equals(rule.getCheckObj())) {//PH
				//异常标准差值
				double phDev1 = dev.getStdevPh() * rule.getOutlierParameter();
				//失效标准差值
				double phDev2 = dev.getStdevPh() * rule.getInvalidParameter();
				//判断的绝对值
				double val = this.getVal(la.getPh(), dev.getAvgPh());
				//数据值
				if (val >= phDev1 && val < phDev2) {
					returnList.add(this.makeResult(la, rule, "PH", false));
				} else if (val >= phDev2){
					returnList.add(this.makeResult(la, rule, "PH", true));
				}
			} else if ("003".equals(rule.getCheckObj())) {//有效态镉
				//异常标准差值
				double acdDev1 = dev.getStdevAcd() * rule.getOutlierParameter();
				//失效标准差值
				double acdDev2 = dev.getStdevAcd() * rule.getInvalidParameter();
				//判断的绝对值
				double val = this.getVal(la.getAcd(), dev.getAvgAcd());
				//数据值
				if (val >= acdDev1 && val < acdDev2) {
					returnList.add(this.makeResult(la, rule, "有效态镉", false));
				} else if (val >= acdDev2){
					returnList.add(this.makeResult(la, rule, "有效态镉", true));
				}
			}
		}
		return returnList;
	}
	
	/**
	 * 获取判断的绝对值
	 * @param num
	 * @param avgNum
	 * @return
	 */
	private double getVal(double num, double avgNum) {
		double val = num - avgNum;
		return Math.abs(val);
	}
	
	/**
	 * 创建校验结果对象
	 * @param la
	 * @param rule
	 * @param errItem
	 * @param isInvalid 是否失效
	 * @return
	 */
	private DataCheckResult makeResult(LandAnalysis la, DataCheckRule rule, String errItem, boolean isInvalid) {
		DataCheckResult info = new DataCheckResult();
		info.setRegId(la.getrId());
		info.setRegCode(la.getrCode());
		info.setErrItems(errItem);
		if (isInvalid) {
			info.setErrTypes(rule.getInvalidRemark());
			info.setIsInvalid(1);
		} else {
			info.setErrTypes(rule.getOutlierRemark());
			info.setIsInvalid(0);
		}
		return info;
	}
	
	/**
	 * 将不同规则判断出的转成map
	 * @param list
	 * @return key=regId   value=object
	 */
	private Map<String, List<DataCheckResult>> makeMap(List<DataCheckResult> list) {
		Map<String, List<DataCheckResult>> map = new LinkedHashMap<String, List<DataCheckResult>>();
		List<DataCheckResult> t = null;
		String key = null;
		for (DataCheckResult dcr : list) {
			key = dcr.getRegId()+","+dcr.getRegCode();
			t = map.get(key);
			if (t == null) {
				t = new ArrayList<DataCheckResult>();
			}
			t.add(dcr);
			map.put(key, t);
		}
		return map;
	}

}
