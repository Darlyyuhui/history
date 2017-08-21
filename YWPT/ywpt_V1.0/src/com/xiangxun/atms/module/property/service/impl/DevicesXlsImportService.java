package com.xiangxun.atms.module.property.service.impl;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.compnents.xls.imports.XLSUtil;
import com.xiangxun.atms.framework.compnents.xls.imports.anotation.AbstractXlsMapping;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ExcelForMartValidtor;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ExcelImportValidator;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.UniqueValidator;
import com.xiangxun.atms.module.property.domain.DeviceOuery;

@Service
public class DevicesXlsImportService extends AbstractXlsMapping {

	@Resource(name="devicesXlsUniqueValidator")
	UniqueValidator uniqueValidator;
	
	
	@PostConstruct
	public void init(){
		ExcelImportValidator validator = new ExcelImportValidator();
		validator.setUniqueValidator(uniqueValidator);
		this.setExcelImportValidator(validator);
	}
	
	DevicesXlsImportService(){
		this.setFormartRowNo(0);
		this.setExcelForMartValidtor(new ExcelForMartValidtor(){

			@Override
			public boolean isValid(Row row) {
				boolean flag = true;
				String cel1 = XLSUtil.getCell(row.getCell(1));
				String cel2 = XLSUtil.getCell(row.getCell(2));
				String cel3 = XLSUtil.getCell(row.getCell(3));
				String cel4 = XLSUtil.getCell(row.getCell(4));
				String cel5 = XLSUtil.getCell(row.getCell(5));
				String cel6 = XLSUtil.getCell(row.getCell(6));
				String cel7 = XLSUtil.getCell(row.getCell(7));
				String cel8 = XLSUtil.getCell(row.getCell(8));
				String cel9 = XLSUtil.getCell(row.getCell(9));
				String cel10 = XLSUtil.getCell(row.getCell(10));
				String cel11 = XLSUtil.getCell(row.getCell(11));
				if (!"设备编号".equals( XLSUtil.getCell(row.getCell(0)))) {
					flag = false;
				}
				if (!"设备名称".equals(cel1)) {
					flag = false;
				}
				if (!"设备IP".equals(cel2)) {
					flag = false;
				}
				if (!"所在道路".equals(cel3)) {
					flag = false;
				}
				if (!"管理部门".equals(cel4)) {
					flag = false;
				}
				if (!"建设厂商".equals(cel5)) {
					flag = false;
				}
				if (!"服务厂商".equals(cel6)) {
					flag = false;
				}
				if (!"设备功能".equals(cel7)) {
					flag = false;
				}
				if (!"资产状态".equals(cel8)) {
					flag = false;
				}
				if (!"保修日期".equals(cel9)) {
					flag = false;
				}
				if (!"采购日期".equals(cel10)) {
					flag = false;
				}
				if (!"安装日期".equals(cel11)) {
					flag = false;
				}
				return flag;
			}
			
		});
		
	}
	
	@Override
	public Map<String, String> getTransferMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<DeviceOuery> getClsName() {
		// TODO Auto-generated method stub
		return DeviceOuery.class;
	}

}
