package com.xiangxun.atms.framework.compnents.xls.imports;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 * <p>
 * 标题：Xls工具类
 * </p>
 * @author zhouhaijian
 */
public abstract class XLSUtil {

	/**
	 * 获取单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCell(Cell cell) {
		if (cell == null)
			return "";
		int cellType = cell.getCellType();
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				 if (HSSFDateUtil.isCellDateFormatted(cell)) {   
				        //  如果是date类型则 ，获取该cell的date值   
					 double numericCellValue = cell.getNumericCellValue();
					 Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
					 return com.xiangxun.atms.framework.util.DateUtil.dateFormatToString(date, "yyyy-MM-dd HH:mm:ss"); 
				    } else { // 纯数字   
				    	String numberCellValue = getNumberCellValue(cell);
				    return getNumberCellValue(cell);  
				}  
				
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			case Cell.CELL_TYPE_FORMULA:
				return cell.getCellFormula();
			case Cell.CELL_TYPE_BLANK:
				return "";
			case Cell.CELL_TYPE_BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			case Cell.CELL_TYPE_ERROR:
				return String.valueOf(cell.getErrorCellValue());
			}
		return "";
	}

	private static String getNumberCellValue(Cell cell) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
		return cell.getStringCellValue();
	}



		/**
		 * 获取单元格的值,处理日期值
		 * 
		 * @param cell
		 * @return
		 */
		public static String getCell(Cell cell,String formatStr) {
			if (cell == null)
				return "";
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					return getNumberCellValue(cell,formatStr);
				case Cell.CELL_TYPE_STRING:
					
					return cell.getStringCellValue();
				case Cell.CELL_TYPE_FORMULA:
					return cell.getCellFormula();
				case Cell.CELL_TYPE_BLANK:
					return "";
				case Cell.CELL_TYPE_BOOLEAN:
					return String.valueOf(cell.getBooleanCellValue());
				case Cell.CELL_TYPE_ERROR:
					return String.valueOf(cell.getErrorCellValue());
				}
			return "";
		}
		
		private static String getNumberCellValue(Cell cell,String formatStr) {
			double dd = cell.getNumericCellValue();
			if (DateUtil.isCellDateFormatted(cell)){
		         java.util.Date date = DateUtil.getJavaDate(dd);
//		         System.out.println(date);
		        return com.xiangxun.atms.framework.util.DateUtil.dateFormatToString(date, formatStr);
			}else{
			// 返回整数
			return Math.round(dd) + "";
			}
		}
		
	}

