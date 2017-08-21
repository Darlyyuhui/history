package com.xiangxun.atms.framework.compnents.xls.export.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.util.FormatUtils;
import com.xiangxun.atms.framework.util.ServletUtils;

/**
 * <h2>excel 文件导出实现类</h2>
 * @author yantao
 */
public class CrossXlsExportor extends AbstractXLSExprotor{
	
	private static Logging logger = new Logging(CrossXlsExportor.class);
	
	//工作薄
	private HSSFWorkbook workbook = new HSSFWorkbook();
	
	//工作表
	private HSSFSheet sheet = workbook.createSheet("sheet1");;
	
	//行
	private HSSFRow row = sheet.createRow(0);
	
	//单元格样式
	private HSSFCellStyle style = workbook.createCellStyle();
	
	private HSSFCellStyle style2 = workbook.createCellStyle();
	
	private HSSFCellStyle errstyle = workbook.createCellStyle();

	private int headercount;

	/* (non-Javadoc)
	 * @see com.xiangxun.framework.web.export.AbstractXLSExprotor#bodyHandler(java.lang.Object[], java.util.List)
	 */
	@Override
	protected void bodyHandler(List<Object[]> values) {
		if (null != values && values.size() > 0) {
			int allsize = values.size();
			int errsize =0;
			for (int i = 0; i < values.size(); i++) {
				HSSFRow row = sheet.createRow(i + 1);
				HSSFRichTextString text = null;
				Object[] val = values.get(i);
				
				//判断是否是异常图片  ADD BY YanTao 2014.11.19
				if("1".equals(val[10])){
					errsize ++;
					for (int j = 0; j < val.length; j++) {
						if(j==9){
							continue;
						}
						if(j==10){
							HSSFCell cell = row.createCell(j);
							text = new HSSFRichTextString("图片下载失败");
							cell.setCellValue(text);
						}else{
							String value = FormatUtils.formatObject(val[j]);
							value = value.replaceAll("\"", "");
							HSSFCell cell = row.createCell(j);
							cell.setCellStyle(errstyle);
							text = new HSSFRichTextString(value);
							cell.setCellValue(text);
						}
					}
				}else{
					for (int j = 0; j < val.length-2; j++) {
						String value = FormatUtils.formatObject(val[j]);
						value = value.replaceAll("\"", "");
						HSSFCell cell = row.createCell(j);
						cell.setCellStyle(style2);
						text = new HSSFRichTextString(value);
						cell.setCellValue(text);
					}
				}
			}
			
			//卡口导出 暂不封装异常记录总计信息
			HSSFRow row = sheet.createRow(allsize + 1);
			//封装表尾 ADD BY YanTao 2014.11.19
			for (int j = 0; j < 9; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellStyle(style2);
				if(j==7){
					HSSFRichTextString text = new HSSFRichTextString("记录总数：");
					cell.setCellValue(text);
				}else if(j==8){
					HSSFRichTextString text = new HSSFRichTextString(""+allsize);
					cell.setCellValue(text);
//				}else if(j==7){
//					HSSFRichTextString text = new HSSFRichTextString("图片失败：");
//					cell.setCellValue(text);
//				}else if(j==8){
//					HSSFRichTextString text = new HSSFRichTextString(""+errsize);
//					cell.setCellValue(text);
				}else{
					HSSFRichTextString text = new HSSFRichTextString("");
					cell.setCellValue(text);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.framework.web.export.AbstractXLSExprotor#closeWorkbook(java.io.File)
	 */
	@Override
	protected InputStream closeWorkbook(File file) {
		InputStream excelStream = null;
		try {
			OutputStream out = new FileOutputStream(file);
			logger.debug("export file path-->>"+file.getAbsolutePath());
			
			workbook.write(out);
			out.close();
			excelStream = new FileInputStream(file);
			
		} catch (Exception e) {
			logger.error(e);
		}
		return excelStream;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.framework.web.export.AbstractXLSExprotor#headHandler(java.lang.Object[])
	 */
	@Override
	protected void headHandler(Object[] heads) {
		headercount = heads.length;		
		for (int i = 0; i < heads.length; i++) {
			String str = FormatUtils.formatObject(heads[i]);
			str = str.replaceAll("\"", "");
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(str);
			cell.setCellValue(text);
		}
	}

	/**
	 * 样式初始化
	 */
	@SuppressWarnings("static-access")
	public void initStyle() {
		//设置列宽
		for (int i = 0; i < headercount; i++) {
			sheet.setColumnWidth(i, 6000);
		}
		
		// 设置样式1
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("Arial");
		font.setFontHeight((short) 200);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		
		// 设置样式2
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成字体
		HSSFFont font2 = workbook.createFont();
		font2.setFontHeightInPoints((short) 12);
		font2.setFontName("Arial");
		font2.setFontHeight((short) 200);
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		
		//设置样式3
		errstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		errstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		errstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		errstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		errstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		errstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//设置行背景色
		errstyle.setFillForegroundColor(IndexedColors.AQUA.YELLOW.getIndex());
		errstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		// 生成字体
		HSSFFont font3 = workbook.createFont();
		font3.setFontHeightInPoints((short) 12);
		font3.setFontName("Arial");
		font3.setFontHeight((short) 200);
		font3.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		errstyle.setFont(font3);
		
		row.setHeight((short) 500);
	}
	
	
	/*
	 * @see com.xiangxun.framework.web.export.FileExportor#getOutputStream(java.lang.String, java.util.List, java.util.List)
	 */
	@Override
	public void exportExcelFileStream(String exportName,Object[] fileHeader, List<Object[]> values,HttpServletResponse response) {
		InputStream	ins = getInputStream(exportName,fileHeader,values);
		// 获得一个工作薄对象
		try {
			HSSFWorkbook outWbook = new HSSFWorkbook(ins);
			ServletUtils.setExcelheader(response,exportName);
			outWbook.write(response.getOutputStream());
			outWbook = null;
			outWbook = null;
			if (ins != null)
				ins.close();
			if (response != null) {
				if (response.getOutputStream() != null)
					response.getOutputStream().flush();
				if (response.getOutputStream() != null)
					response.getOutputStream().close();
			}
			
			
		} catch (IOException e) {
			logger.fatal(e);
		}
	}

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
	}

	/**
	 * @return the sheet
	 */
	public HSSFSheet getSheet() {
		return sheet;
	}

	/**
	 * @param sheet the sheet to set
	 */
	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * @return the row
	 */
	public HSSFRow getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(HSSFRow row) {
		this.row = row;
	}

	/**
	 * @return the style
	 */
	public HSSFCellStyle getStyle() {
		return style;
	}

	/**
	 * @param style the style to set
	 */
	public void setStyle(HSSFCellStyle style) {
		this.style = style;
	}

	public HSSFCellStyle getStyle2() {
		return style2;
	}

	public void setStyle2(HSSFCellStyle style2) {
		this.style2 = style2;
	}
	

	public HSSFCellStyle getErrstyle() {
		return errstyle;
	}

	public void setErrstyle(HSSFCellStyle errstyle) {
		this.errstyle = errstyle;
	}

	public static void main(String[] args) {
		
		CrossXlsExportor export = new CrossXlsExportor();
		
		
		List<Object[]> result = new ArrayList<Object[]>();
		
		Object[] header = new Object[10];		
		for (int i = 0; i < 10; i++) {
			String col = "第"+i+"列";
			header[i] = col;
		}
		
		for (int i = 0; i < 1000; i++) {
			Object[] values = new Object[]{i,i+1,i+2,i+3,i+4,i+5,i+6,i+7,i+8,+i+9};
			result.add(values);
		}
		
		
		export.getInputStream("d:\\aa.xls", header, result);
		
	}
	
}
