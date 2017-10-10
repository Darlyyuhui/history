package com.xiangxun.atms.module.analysis.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.compnents.xls.imports.XLSUtil;
import com.xiangxun.atms.framework.compnents.xls.imports.anotation.AbstractXlsMapping;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ExcelForMartValidtor;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ExcelImportValidator;
import com.xiangxun.atms.module.analysis.vo.xls.LandImport;

@Service
public class LandAnalysisXlsImportService extends AbstractXlsMapping {

	/**
	 * 存储将文本转成ID
	 */
	Map<String, String> transferMap = new HashMap<String, String>();
	
	public LandAnalysisXlsImportService() {
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
				if (!"采样编号".equals(cel0)) {
					return false;
				}
				if (!"分析单位".equals(cel1)) {
					return false;
				}
				if (!"PH值".equals(cel2)) {
					return false;
				}
				if (!"镉".equals(cel3)) {
					return false;
				}
				if (!"有效态镉".equals(cel4)) {
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
	}
	
	@Override
	public Class<LandImport> getClsName() {
		return LandImport.class;
	}

	@Override
	public Map<String, String> getTransferMap() {
		return transferMap;
	}

}
