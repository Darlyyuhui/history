/**
 * 
 */
package com.xiangxun.atms.framework.util; 

import java.io.File;
import java.io.InputStream;
import java.util.Locale;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.resource.MessageResources;

/**
 * 
 * @author kouyunhao
 * @version 1.0
 * Sep 22, 2013
 */
public class SwfUtils {
private static final Logging logger = new Logging(SwfUtils.class);
	
	public static String converte(File source, boolean pdf){
		if(pdf){
			return pdf2swf(source,pdf);
		}else{
			return pdf2swf(file2pdf(source),pdf);
		}
	}
	public static File file2pdf(File source){
		File pdf = null;
		//转换成pdf文件
		if(source.exists()) {
			String pdfPath = source.getPath().substring(0, source.getPath().lastIndexOf(".")).concat(".pdf");
			pdf = new File(pdfPath);
			if(!pdf.exists()) {
				try { 
					String command= toolsInstallPath("sys.openoffice") + " -accept=";
					Runtime r = Runtime.getRuntime();
					r.exec("cmd");
					r.exec(command + "socket,port=8100;urp;");
					OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
					connection.connect();
					DocumentConverter converter = new OpenOfficeDocumentConverter(connection);   
					converter.convert(source, pdf);
					pdf.createNewFile();
					connection.disconnect();  
					logger.info("转换为PDF格式路径==================================" + pdf.getPath());
				} catch (java.net.ConnectException e) {
					logger.info("OpenOffice服务未启动");
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					logger.error("读取错误：不是WinWord97文件");
				} catch (Exception e){
					logger.info("转换PDF文件操作失败");
				}
			} else {
				logger.info("已转换为PDF，无需再次转换");
			}
		} else {
			logger.info("要转换的文件不存在");
		} 
		return pdf;
	}
	
	public static String pdf2swf(File pdf, boolean ispdf){
		//转换成swf文件
		String swfPath = pdf.getPath().substring(0, pdf.getPath().lastIndexOf(".")).concat(".swf");
		File swf = new File(swfPath);
		if(!swf.exists()){
			if(pdf.exists()) {
				try {
					String command= toolsInstallPath("sys.swftools") + "/pdf2swf.exe -t \""+pdf.getPath()+"\" -o \""+swf.getPath()+"\" -s flashversion=9";
					Process p = Runtime.getRuntime().exec("cmd.exe /c start "+command);
					swf.createNewFile();
					logger.info("转换为SWF格式路径================================== " + swf.getPath());
					logger.info("转换为SWF格式名称================================== " + swf.getName());
					InputStream input = null;
					try {
						input = p.getInputStream();
						while(input.read() != -1){
							p.waitFor();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						if(input != null){input.close();}
						p.destroy();
						if(!ispdf){
							if(pdf.exists()){
								pdf.delete();
							}
						}
					}
				} catch (Exception e) {
					logger.info("转换SWF文件操作失败");
				}
			} else {
				logger.info("PDF文件不存在，无法转换");
			}
		} else {
			logger.info("已经转为SWF文件，无需再次转换");
		}
		return swfPath;
	}
	
	public static String toolsInstallPath(String key){
		MessageResources msg = MessageResources.getMessageInstance("flextools.properties", null,Locale.CHINA);
		return msg.getMessage(key);
	}
	
	public static void main(String[] args) {
//		File file= new File("D:\\MyWorkspace\\ywpt_v1.0\\WebRoot\\upload_res\\repository\2015工资条631.xlsx");
//		System.out.println(converte(file));
	}
	
}
