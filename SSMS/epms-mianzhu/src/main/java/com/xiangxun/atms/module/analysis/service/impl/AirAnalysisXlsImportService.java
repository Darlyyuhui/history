package com.xiangxun.atms.module.analysis.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.google.common.collect.Table;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.compnents.xls.imports.XLSUtil;
import com.xiangxun.atms.framework.compnents.xls.imports.anotation.AbstractXlsMapping;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ExcelForMartValidtor;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ExcelImportValidator;
import com.xiangxun.atms.module.analysis.vo.AirAnalysis;
import com.xiangxun.atms.module.bs.cache.AirPointCache;
import com.xiangxun.atms.module.bs.cache.TRegionCache;

@Service
public class AirAnalysisXlsImportService extends AbstractXlsMapping {

	/**
	 * 存储将文本转成ID
	 */
	Map<String, String> transferMap = new HashMap<String, String>();
	
	@Resource
	Cache cache;
	
	public AirAnalysisXlsImportService() {
		this.setFormartRowNo(0);
		this.setExcelForMartValidtor(new ExcelForMartValidtor() {
			/***
			 * 检验是否套用了模板
			 */
			@Override
			public boolean isValid(Row row) {
				String cel0 = XLSUtil.getCell(row.getCell(0)).trim();
				String cel1 = XLSUtil.getCell(row.getCell(1)).trim();
				String cel2 = XLSUtil.getCell(row.getCell(2)).trim();
				String cel3 = XLSUtil.getCell(row.getCell(3)).trim();
				String cel4 = XLSUtil.getCell(row.getCell(4)).trim();
				String cel5 = XLSUtil.getCell(row.getCell(5)).trim();
				String cel6 = XLSUtil.getCell(row.getCell(6)).trim();
				String cel7 = XLSUtil.getCell(row.getCell(7)).trim();
				String cel8 = XLSUtil.getCell(row.getCell(8)).trim();
				String cel9 = XLSUtil.getCell(row.getCell(9)).trim();
				if (!"分析编号".equals(cel0)) {
					return false;
				}
				if (!"针对地块".equals(cel1)) {
					return false;
				}
				if (!"样品日期".equals(cel2)) {
					return false;
				}
				if (!"样品点位".equals(cel3)) {
					return false;
				}
				if (!"镉含量".equals(cel4)) {
					return false;
				}
				if (!"样品体积".equals(cel5)) {
					return false;
				}
				if (!"所属乡镇".equals(cel6)) {
					return false;
				}
				if (!"污染通量".equals(cel7)) {
					return false;
				}
				if (!"是否超标".equals(cel8)) {
					return false;
				}
				if (!"备注".equals(cel9)) {
					return false;
				}
				return true;
			}
		});
	}
	
	@PostConstruct
	public void init(){
        ExcelImportValidator validator = new ExcelImportValidator();
        this.setExcelImportValidator(validator);
		initMap();
	}
	
	@SuppressWarnings("unchecked")
	public void initMap(){
		transferMap.put("是", "1");
		transferMap.put("否", "0");
		
		Table<String, String, String> table = (Table<String, String, String>)cache.get(TRegionCache.ID_NAME);
		Map<String, String> map = table.column(TRegionCache.ID_NAME);
		for (String key : map.keySet()) {
			transferMap.put(map.get(key), key);
		}
		
		Table<String, String, String> pointTable = (Table<String, String, String>)cache.get(AirPointCache.ID_NAME);
		Map<String, String> pointMap = pointTable.column(AirPointCache.ID_NAME);
		for (String key : pointMap.keySet()) {
			transferMap.put(pointMap.get(key), key);
		}
	}
	
	@Override
	public Class<AirAnalysis> getClsName() {
		return AirAnalysis.class;
	}

	@Override
	public Map<String, String> getTransferMap() {
		return transferMap;
	}
	
}
