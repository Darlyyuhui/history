package com.xiangxun.atms.framework.compnents.xls.export;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * <h2>Xls数据处理器</h2>
 * <li>文件导出前的处理，主要为按模板导出设计</li>
 * <li>在按模板导出前，如果想修改某些cell，则使用此接口</li>
 * @author zhouhaijian
 * @version 1.0
 * @since 1.0
 */
public interface PostXlsExportProcessor {

	public void setWorkbook(HSSFWorkbook workbook);
	
	/****
	 * 对工作薄进行处理
	 * @param wbook
	 * @param wsheetNo 工作表号 默认从0开始
	 */
	public void xlsProcessor(HSSFWorkbook wbook,int wsheetNo);
	
	/***
	 * 合并单元格
	 * @param wb
	 * @param sheet
	 */
	public void xlsMergeRegion(HSSFWorkbook wb,int wsheetNo);

	
	
}
