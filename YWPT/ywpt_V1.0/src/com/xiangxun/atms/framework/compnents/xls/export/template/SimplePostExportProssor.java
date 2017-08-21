package com.xiangxun.atms.framework.compnents.xls.export.template;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.xiangxun.atms.framework.compnents.xls.export.PostXlsExportProcessor;
import com.xiangxun.atms.framework.util.ExcelUtil;
import com.xiangxun.atms.framework.util.FormatUtils;

/**
 * <h2>xls处理器接口实现</h2>
 * @author zhouhaijian
 * @version 1.0
 * @since 1.0
 * 
 */
public class SimplePostExportProssor implements PostXlsExportProcessor{

	ExcelEntity[] excelEntitys;
	
	ExcelMerge[] excelMerges;
	
	HSSFWorkbook workbook = null;
	
	/**
	 * @return the workbook
	 */
	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	/**
	 * @param workbook the workbook to set
	 */
	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;	
		hssf = ExcelUtil.getHSSFCellStyle(workbook);
	}

	private HSSFCellStyle hssf = null;
	
	public SimplePostExportProssor(ExcelEntity[] excelEntitys){
		this.excelEntitys = excelEntitys;
	}
	
	public SimplePostExportProssor(ExcelEntity[] excelEntitys,ExcelMerge[] excelMerges){
		this.excelEntitys = excelEntitys;
		this.excelMerges = excelMerges;
	}
	
	/* (non-Javadoc)
	 * @see com.diablo.intelligentTraffic.domain.count.export.xls.XlsWriter#xlsProcessor(org.apache.poi.hssf.usermodel.HSSFWorkbook)
	 */
	@Override
	public void xlsProcessor(HSSFWorkbook wbook,int sheetNo) {
		
		HSSFSheet wsheet = wbook.getSheetAt(sheetNo);
		
		if(excelEntitys ==null){
			return;
		}
		
		for (int i = 0; i < excelEntitys.length; i++) {
			ExcelEntity excelEntity = excelEntitys[i];
			if(excelEntity==null){
				continue;
			}
			HSSFRow row = wsheet.getRow(excelEntity.getRowNo());
			if(row==null){
				ExcelUtil.insertRow(wbook, wsheet, excelEntity.getRowNo(),1);
				row = wsheet.getRow(excelEntity.getRowNo());
			}
			HSSFCell cell = row.getCell(excelEntity.getCell());
			if(cell==null){
				ExcelUtil.addCell(wsheet.getRow(excelEntity.getRowNo()),excelEntity.getCell(),"",wbook);
				cell = row.getCell(excelEntity.getCell());
				cell.setCellStyle(cell.getCellStyle());
			}
			cell.setCellStyle(hssf);
			
			//poi 3.6 以下版本需要设置编码格式，否则会出现中文乱码
			//cell.setEncoding((short) 1);
			cell.setCellValue(FormatUtils.formatObject(excelEntity.getValue()).replaceAll("\"", ""));
		}
	}

	
	@Override
	public void xlsMergeRegion(HSSFWorkbook wb, int wsheetNo) {
		HSSFSheet wsheet = wb.getSheetAt(wsheetNo);
		if(excelMerges ==null){
			return;
		}
		for (int i = 0; i < excelMerges.length; i++) {
			ExcelMerge excelMerge = excelMerges[i];
			ExcelUtil.mergeRegion(wb, wsheet, excelMerge.getFirstRow(), excelMerge.getLastRow(), excelMerge.getFirstCol(), excelMerge.getLastCol());
		}
		
	}
}
