/**
 * 
 */
package com.xiangxun.atms.framework.compnents.fusioncharts.resources;

import javax.servlet.http.HttpServletResponse;

import com.fusioncharts.exporter.beans.ExportBean;

/**
 * @author InfosoftGlobal (P) Ltd.
 *
 */
public abstract class FCExporter_Format {
	
	abstract public Object exportProcessor(ExportBean exportBean);
	
	abstract public String exportOutput ( Object exportObj, HttpServletResponse response);
}
