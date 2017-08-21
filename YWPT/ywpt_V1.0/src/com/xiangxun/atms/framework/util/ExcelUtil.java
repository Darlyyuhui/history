package com.xiangxun.atms.framework.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * <p>
 * 标题：
 * </p>
 * 
 * @author zhouhaijian
 * @version 1.0 创建时间：11:26:58 AMNov 16, 2010
 */
public class ExcelUtil {

	/**
	 * 给单元格赋值
	 * 
	 * 说明：保持原模版的单元格样式
	 * 
	 * @param row
	 *            行对象
	 * 
	 * @param cell
	 *            单元格
	 * 
	 * @param value
	 *            值
	 * 
	 */
	public static void addCell(HSSFRow row, int cell, Object value, HSSFWorkbook wb) {
		String strvalue = "";
		NumberFormatTools numft = NumberFormatTools.getInstance();
		ValidateUtil util = ValidateUtil.getInstance();
		if (value != null && !"".equals(value)) {
			strvalue = String.valueOf(value);
		}
		HSSFCell sourceCell = row.getCell(cell);
		try {
			if (sourceCell == null) {
				sourceCell = row.getCell(cell-1);
				HSSFCell targetCell = row.createCell( cell);//
				targetCell.setCellStyle(sourceCell.getCellStyle());
				int type = 1;
				if (strvalue != null && util.checkPercent(strvalue)) {
					type = sourceCell.CELL_TYPE_BLANK;
				}
				if (strvalue != null && util.checkMny(strvalue)) {
					type = sourceCell.CELL_TYPE_NUMERIC;
				}
				
				if (sourceCell.CELL_TYPE_NUMERIC == type && strvalue != null && util.checkMny(strvalue)) {
					// log.info("百分比类型值＝"+strvalue.replace("%",""));
					targetCell.setCellValue(numft.toDouble(strvalue));
				} else {
					// 单元格类型是百分比类型的，设置默认值
					if (sourceCell.CELL_TYPE_BLANK == type && strvalue != null && util.checkPercent(strvalue)) {
						//targetCell.setCellValue(numft.toDouble(strvalue.replace("%", "")));
						targetCell.setCellValue(strvalue);
					} else {
						if (strvalue != null && !"".equals(strvalue)) {
							targetCell.setCellValue(strvalue);
						}
					}
				}
			} else {
				HSSFCellStyle style = wb.createCellStyle();
				HSSFFont font = wb.createFont();
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);
				style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				if(value.equals("正常")){
					font.setColor(HSSFColor.GREEN.index);
				}else if(value.equals("异常")){
					font.setColor(HSSFColor.RED.index);
				}
				style.setFont(font);
				sourceCell.setCellStyle(style);
				
				
				int type = 1;
				if (strvalue != null && util.checkPercent(strvalue)) {
					type = sourceCell.CELL_TYPE_BLANK;
				}
				if (strvalue != null && util.checkMny(strvalue)) {
					type = sourceCell.CELL_TYPE_NUMERIC;
				}
				// log.info("值="+String.valueOf(numft.toDouble(strvalue))+"
				// 类型="+type);
				// 单元格类型是数值类型的，设置默认值
				if (sourceCell.CELL_TYPE_NUMERIC == type && strvalue != null && util.checkMny(strvalue)) {
					// log.info("数值值="+strvalue+" 类型="+type);
					sourceCell.setCellValue(numft.toDouble(strvalue));
				} else {
					if (sourceCell.CELL_TYPE_BLANK == type && strvalue != null && util.checkPercent(strvalue)) {
						// log.info("百分比值="+strvalue+" 类型="+type);
						sourceCell.setCellValue(numft.toDouble(strvalue.replace("%", "")) / 100);
					} else {
						if (strvalue != null && !"".equals(strvalue)) {
							sourceCell.setCellValue(strvalue);
						}
						// log.info("文本值="+strvalue+" 类型="+type);
					}
				}
			}
		} catch (Exception error) {
			sourceCell.setCellValue(strvalue);
		}
	}

	/**
	 * 插入行
	 * 
	 * 说明：插入行的样式取starRow行的样式
	 * 
	 * @param wb
	 * @param sheet
	 * @param starRow
	 * @param rows
	 *            一次插入行数
	 * 
	 */
	public static void insertRow(HSSFWorkbook wb, HSSFSheet sheet, int starRow, int rows) {
		sheet.shiftRows(starRow + 1, sheet.getLastRowNum(), rows);
		starRow = starRow - 1;
		// 插入行的样式行（参照样式行）
		HSSFRow sourceRow = null;
		// 插入的行
		HSSFRow targetRow = null;
		// 插入列的样式列（参照样式列）
		HSSFCell sourceCell = null;
		// 插入的列
		HSSFCell targetCell = null;
		for (int i = 0; i < rows; i++) {
			short m;
			starRow = starRow + 1;
			sourceRow = sheet.getRow(starRow);
			targetRow = sheet.createRow(starRow + 1);
			// targetRow.setHeight(sourceRow.getHeight());
			int h = sourceRow.getPhysicalNumberOfCells();
			for (m = sourceRow.getFirstCellNum(); m < sourceRow.getPhysicalNumberOfCells(); m++) {
				sourceCell = sourceRow.getCell(m);
				targetCell = targetRow.createCell(m);
				// targetCell.setEncoding(sourceCell.getEncoding());
				targetCell.setCellStyle(sourceCell.getCellStyle());
				// 单元格类型是数值类型的，设置默认值
				if (sourceCell.CELL_TYPE_NUMERIC == sourceCell.getCellType() && sourceCell.getNumericCellValue() == 0) {
					// targetCell.setCellValue(0);
				}
				// 单元格类型是百分比类型的，设置默认值
				if (sourceCell.CELL_TYPE_BLANK == sourceCell.getCellType() && sourceCell.getNumericCellValue() == 0) {
					// targetCell.setCellValue(0.00);
				}

			}
		}
	}

	/***
	 * excel 合并单元格 只负责合并单元格，具体往excel单元格里写入值时请调用insertRow方法
	 * 
	 * @param wb
	 * @param sheet
	 * @param firstRow
	 * @param lastRow
	 * @param firstCol
	 * @param lastCol
	 */
	public static void mergeRegion(HSSFWorkbook wb, HSSFSheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {

		CellRangeAddress cellRangeAddress = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
		cellRangeAddress.setFirstColumn(firstCol);
		cellRangeAddress.setFirstRow(firstRow);
		cellRangeAddress.setLastColumn(lastCol);
		cellRangeAddress.setLastRow(lastRow);
		sheet.addMergedRegion(cellRangeAddress);
		HSSFRow sourceRow = sheet.getRow(firstRow); 
		ExcelUtil.addMergedRegionStyle(sheet, cellRangeAddress, sourceRow.getCell(firstCol).getCellStyle());
	}

	/**
	 * 插入行
	 * 
	 * 说明：插入行的样式取starRow行的样式
	 * 
	 * @param wb
	 * @param sheet
	 * @param starRow
	 * @param rows
	 *            插入的行数
	 * @param style
	 *            单元格样式
	 */
	public static void insertRow(HSSFWorkbook wb, HSSFSheet sheet, int starRow, int rows, HSSFCellStyle style, int columns) {

		// HSSFSheet.shiftRows(rowIndex, rowNum, 1, true, true, true)
		sheet.shiftRows(starRow + 1, sheet.getLastRowNum(), rows);

		starRow = starRow - 1;
		// 插入行的样式行（参照样式行）
		HSSFRow sourceRow = null;
		// 插入的行
		HSSFRow targetRow = null;
		// 插入列的样式列（参照样式列）
		HSSFCell sourceCell = null;
		// 插入的列
		HSSFCell targetCell = null;
		for (int i = 0; i < rows; i++) {
			short m;
			starRow = starRow + 1;
			sourceRow = sheet.getRow(starRow);
			targetRow = sheet.createRow(starRow + 1);
			for (m = sourceRow.getFirstCellNum(); m < columns; m++) {
				sourceCell = sourceRow.getCell(m);
				targetCell = targetRow.createCell(m);
				targetCell.setCellStyle(style);
			}
		}
	}

	/***
	 * 
	 * @param wb
	 * @param sheet
	 * @param starRow
	 * @param rows
	 * @param columns
	 */
	public static void insertRow(HSSFWorkbook wb, HSSFSheet sheet, int starRow, int rows, int columns) {
		ExcelUtil.insertRow(wb, sheet, starRow, rows, ExcelUtil.getHSSFCellStyle(wb), columns);

	}

	/***************************************************************************
	 * 设置单元格样式
	 * 
	 * @param wb
	 * @return
	 */
	public static HSSFCellStyle getHSSFCellStyle(HSSFWorkbook wb) {
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 9);
		font.setItalic(false);
		font.setBoldweight((short) 9);
		font.setStrikeout(false);
//		font.setFontName("微软雅黑");
		HSSFCellStyle style = wb.createCellStyle();
		
		style.setFont(font);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		return style;
	}

	public static void addMergedRegionStyle(HSSFSheet targetSheet, CellRangeAddress address, HSSFCellStyle style) {
		int rowFrom = address.getFirstRow();
		int rowTo = address.getLastRow();
		int colFrom = address.getFirstColumn();
		int colTo = address.getLastColumn();
		for (int r = rowFrom; r <= rowTo; r++) {
			HSSFRow row = targetSheet.getRow(r);
			if (row != null) {
				for (int c = colFrom; c <= colTo; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						cell.getCellStyle().setAlignment(style.getAlignment());
					}
				}
			}
		}
	}
}
