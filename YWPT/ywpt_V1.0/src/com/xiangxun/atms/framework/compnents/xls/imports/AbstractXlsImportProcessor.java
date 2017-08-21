package com.xiangxun.atms.framework.compnents.xls.imports;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.xiangxun.atms.framework.compnents.xls.imports.validate.ExcelForMartValidtor;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ExcelImportValidator;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.UniqueValidator;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ValidateBean;


/**
 * <p>导入文件校验器实现类</p>
 * @author zhouhaijian
 */
public abstract class AbstractXlsImportProcessor implements XlsImportProcessor {
	
	/**
	 * 待装入导入记录对象容器
	 */
	private List<Object> resultObjectLists = new ArrayList<Object>();
	/***
	 * 格式校验器
	 */
	private ExcelForMartValidtor excelForMartValidtor;
	/**
	 * excel导入校验器
	 */
	private ExcelImportValidator excelImportValidator;
	
	/***
	 * 数据唯一性校验器
	 */
	private UniqueValidator uniqueValidator;
	
	/***
	 * 表头行号
	 */
	private int formartRowNo = 0;
	
	private Row  headerRow = null;
	
	protected Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings("static-access")
	public List<String> importValite(List<String> files) {
		List<String> errors = excelImportValidator.errorMessages;
		errors.clear();
		resultObjectLists.clear();
		for (int i = 0; i < files.size(); i++) {
			try {
				File file = new File(files.get(i));
				if (!file.getName().toLowerCase().endsWith(".xls")) {
					errors.add(file.getName()+ "文件类型有误！");
					continue;
				}
				Workbook workbook = new HSSFWorkbook(new FileInputStream(file));
				Sheet sheet = workbook.getSheetAt(0);
				
				headerRow = sheet.getRow(formartRowNo);
				
				if(excelForMartValidtor !=null){
					if (!excelForMartValidtor.isValid(headerRow)){
						errors.add(file.getName()+ "表格格式有误，请确认是否套用了模版！");
						break;
					}
				}
				
				for (Row row : sheet) {
					if (row.getRowNum() >formartRowNo) {
						Object object = getRowModel(row);
						List<ValidateBean>  validateBeans = getValidateBeans(row);
						if(validateBeans!=null && !validateBeans.isEmpty()){
							excelImportValidator.validate(file,validateBeans);
						}
						resultObjectLists.add(object);
					}
				}
				if (errors.size() > 0) {
					errors.add(file.getName()+ ":文件内容数据不正确,请检查并修改后再试。");
				}
			} catch (java.io.FileNotFoundException e) {
				errors.add("文件名称有误！");
				e.printStackTrace();
				logger.error(e);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}

		}
		return errors;
	}
	
	/**
	 * 有日期的单元格
	 * 
	 */
	@SuppressWarnings("static-access")
	public List<String> importValiteDate(List<String> files,String formatStr) {
		List<String> errors = excelImportValidator.errorMessages;
		errors.clear();
		resultObjectLists.clear();
		for (int i = 0; i < files.size(); i++) {
			try {
				File file = new File(files.get(i));
				if (!file.getName().toLowerCase().endsWith(".xls")) {
					errors.add(file.getName()+ "文件类型有误！");
					continue;
				}
				Workbook workbook = new HSSFWorkbook(new FileInputStream(file));
				Sheet sheet = workbook.getSheetAt(0);
				
				headerRow = sheet.getRow(formartRowNo);
				
				if(excelForMartValidtor !=null){
					if (!excelForMartValidtor.isValid(headerRow)){
						errors.add(file.getName()+ "表格格式有误，请确认是否套用了模版！");
						break;
					}
				}
				
				for (Row row : sheet) {
					if (row.getRowNum() >formartRowNo) {
						Object object = getRowModel(row,formatStr);
						List<ValidateBean>  validateBeans = getValidateBeans(row);
						if(validateBeans!=null && !validateBeans.isEmpty()){
							excelImportValidator.validate(file,validateBeans);
						}
						resultObjectLists.add(object);
					}
				}
				if (errors.size() > 0) {
					errors.add(file.getName()+ ":文件内容数据不正确,请检查并修改后再试。");
				}
			} catch (java.io.FileNotFoundException e) {
				errors.add("文件名称有误！");
				e.printStackTrace();
				logger.error(e);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}

		}
		return errors;
	}
	
	/***
	 * 设置验证的bean
	 * @param row
	 * @return
	 */
	public abstract List<ValidateBean> getValidateBeans(Row row);
	
	/***
	 * 设置row对应的Model对象
	 * @param row
	 * @return
	 */
	public abstract Serializable getRowModel(Row row);
	/***
	 * 设置row对应的Model对象,有日期值
	 * @param row
	 * @return
	 */
	public abstract Serializable getRowModel(Row row,String formatStr);
	
	/* (non-Javadoc)
	 * @see com.xiangxun.framework.common.excel.ImportValidator#getObjectList()
	 */
	@Override
	public List<Object> getObjectList() {
		return resultObjectLists;
	}

	
	/**
	 * @param excelForMartValidtor the excelForMartValidtor to set
	 */
	public void setExcelForMartValidtor(ExcelForMartValidtor excelForMartValidtor) {
		this.excelForMartValidtor = excelForMartValidtor;
	}

	/**
	 * @param 设置xls模板验证器
	 */
	public void setExcelImportValidator(ExcelImportValidator excelImportValidator) {
		this.excelImportValidator = excelImportValidator;
	}

	/**
	 * @return the formartRowNo
	 */
	public int getFormartRowNo() {
		return this.formartRowNo;
	}

	/**
	 * @param  设置表头行号
	 */
	public void setFormartRowNo(int formartRowNo) {
		this.formartRowNo = formartRowNo;
	}

	/**
	 * @return the headerRow
	 */
	protected Row getHeaderRow() {
		return headerRow;
	}

	/**
	 * @return the uniqueValidator
	 */
	protected UniqueValidator getUniqueValidator() {
		return uniqueValidator;
	}

	/**
	 * @param uniqueValidator the uniqueValidator to set
	 */
	protected void setUniqueValidator(UniqueValidator uniqueValidator) {
		this.uniqueValidator = uniqueValidator;
	}
	
	
	
}
