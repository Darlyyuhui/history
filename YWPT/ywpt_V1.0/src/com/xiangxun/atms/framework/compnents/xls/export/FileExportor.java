package com.xiangxun.atms.framework.compnents.xls.export;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * <h2>文件导出接口</h2>
 * @author zhouhaij
 * @version 1.0
 * @since 1.0
 */
public interface  FileExportor{
	
	/**
	 * 获取文件输入流
	 * @param exportName 文件名称
	 * @param fileHeader 文件表头
	 * @param values     数据文件
	 * @return
	 */
	public InputStream getInputStream(String exportName,Object[] fileHeader, List<Object[]> values);
	
	
	/***
	 * 文件导出
	 * @param exportName
	 * @param fileHeader
	 * @param resultList
	 * @param response
	 */
	public void exportExcelFileStream(String exportName,Object[] fileHeader, List<Object[]> values,HttpServletResponse response);
	
	/***
	 * 生成文件
	 * @param exportName
	 * @param fileHeader
	 * @param values
	 * @param filePath
	 */
	public void buildFile(String exportName,Object[] fileHeader, List<Object[]> values,String filePath);
	
}
