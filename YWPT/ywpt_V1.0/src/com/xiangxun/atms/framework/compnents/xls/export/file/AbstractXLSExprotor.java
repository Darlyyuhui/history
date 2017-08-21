package com.xiangxun.atms.framework.compnents.xls.export.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.xiangxun.atms.framework.compnents.xls.export.FileExportor;
import com.xiangxun.atms.framework.compnents.xls.export.XlsExportException;
import com.xiangxun.atms.framework.constant.Constant;
import com.xiangxun.atms.framework.constant.PltMessage;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.util.ServletUtils;
import com.xiangxun.atms.framework.util.StringUtils;

/**
 * <h2>xls 文件导出处理器</h2>
 * @author zhouhaij
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractXLSExprotor implements FileExportor{
	
	private static Logging logger = new Logging(AbstractXLSExprotor.class);

	/* (non-Javadoc)
	 * @see com.xiangxun.framework.web.export.FileExportor#exportExcelFileStream(java.lang.String, java.lang.Object[], java.util.List, javax.servlet.http.HttpServletResponse)
	 */
	public void exportExcelFileStream(String exportName, Object[] fileHeader, List<Object[]> values, HttpServletResponse response) {
		InputStream	ins = getInputStream(exportName,fileHeader,values);
		if(ins==null){
			throw new XlsExportException("XLS-0003");
		}
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

	/* (non-Javadoc)
	 * @see com.xiangxun.framework.web.export.FileExportor#getInputStream(java.lang.String, java.lang.Object[], java.util.List)
	 */
	@Override
	public InputStream getInputStream(String exportName, Object[] tableHeader,List<Object[]> values) {
		
		if(StringUtils.isEmpty(exportName)){
			logger.error(PltMessage.BSN_TRANS_PARAM_ERROR);
			throw new IllegalArgumentException(PltMessage.BSN_TRANS_PARAM_ERROR);
		}
		
		if(!exportName.endsWith(Constant.XLS_FILE_FORMAT) || exportName.endsWith(Constant.XLSX_FILE_FORMAT)){
			throw new XlsExportException("XLS-0001");
		}
		
		//初始化样式
		this.initStyle();
		//表头
		this.headHandler(tableHeader);
		//表体
		this.bodyHandler(values);
		//关闭输出
		return  this.closeWorkbook(new File(exportName));
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
			FileOutputStream fos = null;
			try {
				File saveFile = new File(filePath,exportName);
				if(saveFile.exists()){
					saveFile.delete();			
				}
				saveFile.createNewFile();
				// 设置导出的文件名
				fos = new FileOutputStream(saveFile);
				byte[] buffer = new byte[8*1024];
				int len = 0;
				while ((len = ins.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw e;
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			} finally {
				try {
					if (fos != null) {
						fos.close();
					}
					if (ins != null) {
						ins.close();
					}
				} catch (IOException e) {
					throw e;
				}
			}
		} catch (IOException e) {
			logger.fatal(e);
		}		
		
	}

	/***
	 * 初始化样式
	 */
	protected abstract void initStyle();
	
	/****
	 * 设置表头
	 * @param heads
	 */
	protected abstract void  headHandler(Object[] heads) ;
	
	/***
	 * 设置表体
	 * @param tableHeader
	 * @param values
	 */
	protected abstract void  bodyHandler(List<Object[]> values);
	
	/***
	 * 关闭输出
	 * @param file
	 * @return
	 */
	protected abstract InputStream closeWorkbook(File file);
}
