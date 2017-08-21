package com.xiangxun.atms.framework.util;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.xiangxun.atms.framework.resource.MessageResources;





public class FileToSwf {
	public static String toolsInstallPath(String key){
		MessageResources msg = MessageResources.getMessageInstance("flextools.properties", null,Locale.CHINA);
		return msg.getMessage(key);
	}
	/**
	 * 文件转swf
	 * 
	 * @param: swfName 
	 *            指定生成的swf的文档路径
	 * @param documentName
	 *            需要转换为swf的文档路径
	 */
	public static void file2swf(String swfName, String documentName) {
		String converter = "F:/软件/falsh/falshpaper/FlashPaper2.2/FlashPrinter.exe -o "
				+ swfName + " " + documentName;
//		String converter = toolsInstallPath("sys.flashpaper")+" -o "
//			+ swfName + " " + documentName;
		Runtime r = Runtime.getRuntime();
		try {
			Process p = r.exec(converter);
			System.out.println("成功了");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String swfFilepath(){
		Date date= new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fileName= sdf.format(date);
		return fileName+".swf";
	}
	public static void main(String[] args) {
		file2swf("d:\\1234mmm.swf", "d:\\1111.docx");
	}

	
}
