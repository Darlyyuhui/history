package com.xiangxun.atms.framework.compnents.xls.export.template;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.util.ResourceUtils;

import com.xiangxun.atms.framework.compnents.xls.export.FileExportor;
import com.xiangxun.atms.framework.compnents.xls.export.PostXlsExportProcessor;
import com.xiangxun.atms.framework.compnents.xls.export.XlsExportException;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.util.ExcelUtil;
import com.xiangxun.atms.framework.util.FormatUtils;
/**
 * 
 * <h2>通过模板EXCEL文件导出基类</h2>
 * @author zhouhaij
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractXlsTemplateExportor implements FileExportor{
	
	private static final Logging logger = new Logging(AbstractXlsTemplateExportor.class);
	// 可写工作薄对象
	private HSSFWorkbook wbook = null;
	// 可写工作表对象
	private HSSFSheet wsheet = null;

	private PostXlsExportProcessor postProcessor = null;
	
	private String fileName = null;
	
	/* (non-Javadoc)
	 * @see com.xiangxun.framework.web.export.FileExportor#getInputStream(java.lang.String, java.lang.Object[], java.util.List)
	 */
	@SuppressWarnings("unused")
	@Override
	public InputStream getInputStream(String exportName, Object[] fileHeader,List<Object[]> values) {
		// 读取模版XLS文件
		try {
			URL url = this.getClass().getClassLoader().getResource(exportName);
			
			if(url==null){
				url  = ResourceUtils.getURL("classpath:"+exportName);
			}
			
			if(url==null){
				url  = ResourceUtils.getURL("file:"+exportName);
			}
			
			logger.debug("xls file url is: "+ url);
			
			InputStream ins = new FileInputStream(url.getFile());
			if(ins==null){
				throw new XlsExportException("XLS-0003");
			}
			//获取excel文件
		    wbook = new HSSFWorkbook(ins);
			// 取得工作表
			wsheet = wbook.getSheetAt(0);
			// 行号
			int row = getWriterRowNo();
			
			if(!values.isEmpty()){
				for (int i = 0; i < values.size(); i++) {
					Object[] object = values.get(i);
					ExcelUtil.insertRow(wbook, wsheet, row,1);
					for (int j = 0; j < object.length; j++) {
						if (object[j] != null) {
							String value = FormatUtils.formatObject(object[j]).replaceAll("\"","");
							ExcelUtil.addCell(wsheet.getRow(row),j,value,wbook);
						}
					}
					row++;
				}
			}
			
			if(postProcessor != null ){
				postProcessor.setWorkbook(wbook);
				postProcessor.xlsProcessor(wbook,0);
				postProcessor.xlsMergeRegion(wbook,0);
			}
			
			return ins;
		} catch (FileNotFoundException e) {
			throw new XlsExportException("XLS-0002",new Object[]{exportName});
		} catch (IOException e) {
			logger.error(e);
		}
		return null;
	}

	public HSSFWorkbook getWorkbook(){
		return this.wbook;
	}
	
	/* (non-Javadoc)
	 * @see com.xiangxun.framework.web.export.FileExportor#exportExcelFileStream(java.lang.String, java.lang.Object[], java.util.List, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void exportExcelFileStream(String exportName, Object[] fileHeader,List<Object[]> values, HttpServletResponse response) {
		InputStream	ins = getInputStream(exportName,fileHeader,values);
		if(ins==null){
			throw new XlsExportException("XLS-0003");
		}
		try {
			
			if(wbook==null){
				// 获得一个工作薄对象
				wbook = new HSSFWorkbook(ins);
			}
			// 设置导出的文件名
			String file = getFileName()==null?exportName:getFileName();
			try {
				//兼容ie6
				response.setContentType("text/plain");
				//设置中文文件名
				response.setHeader("Content-Disposition", "attachment;filename="+ new String(file.getBytes("GBK"), "iso8859-1"));
				response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			} catch (Exception e) {

			}
			wbook.write(response.getOutputStream());
			wbook = null;
			wbook = null;
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



	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.compnents.xls.export.FileExportor#buildFile(java.lang.String, java.lang.Object[], java.util.List, java.lang.String)
	 */
	@Override
	public void buildFile(String exportName, Object[] fileHeader, List<Object[]> values, String filePath) {
		InputStream	ins = getInputStream(exportName,fileHeader,values);
		if(ins==null){
			throw new XlsExportException("XLS-0003");
		}
		try {
			
			if(wbook==null){
				// 获得一个工作薄对象
				wbook = new HSSFWorkbook(ins);
			}
			// 设置导出的文件名
			String file = getFileName()==null?exportName:getFileName();
			OutputStream os = new FileOutputStream(new File(filePath,file));
			wbook.write(os);
			wbook = null;
			wbook = null;
			if (ins != null)
				ins.close();
			if (os != null) {
				os.flush();
				os.close();
				os = null;
			}
		} catch (IOException e) {
			logger.fatal(e);
		}		
	}



	/***
	 * 开始从第几行写入xls文件
	 * @return
	 */
	protected abstract int getWriterRowNo();

	public PostXlsExportProcessor getPostProcessor() {
		return postProcessor;
	}

	public void setPostProcessor(PostXlsExportProcessor postProcessor) {
		this.postProcessor = postProcessor;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return this.fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	} 


}
