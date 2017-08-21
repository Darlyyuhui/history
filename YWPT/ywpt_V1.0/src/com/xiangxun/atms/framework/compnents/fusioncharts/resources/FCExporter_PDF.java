/**
 * 
 */
package com.xiangxun.atms.framework.compnents.fusioncharts.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fusioncharts.exporter.ErrorHandler;
import com.fusioncharts.exporter.FusionChartsExportHelper;
import com.fusioncharts.exporter.beans.ChartMetadata;
import com.fusioncharts.exporter.beans.ExportBean;
import com.fusioncharts.exporter.generators.PDFGenerator;

/**
 * @author InfosoftGlobal (P) Ltd.
 *
 */
public class FCExporter_PDF extends FCExporter_Format {
	private ExportBean exportBean=null;
	/* (non-Javadoc)
	 * @see com.fusioncharts.exporter.resources.FcExporter_Format#exportOutput(java.lang.Object, javax.servlet.http.HttpServletResponse, java.lang.String, int)
	 */
	public String exportOutput(Object exportObj, HttpServletResponse response) {
		byte[] pdfBytes= (byte[])exportObj;
		String action= (String)exportBean.getExportParameterValue("exportaction");
		String exportFormat = (String)exportBean.getExportParameterValue("exportformat");
		String exportTargetWindow = (String)exportBean.getExportParameterValue("exporttargetwindow");

		String fileNameWithoutExt = (String)exportBean.getExportParameterValue("exportfilename");
		String extension = FusionChartsExportHelper.getExtensionFor(exportFormat.toLowerCase());;
		String fileName = fileNameWithoutExt+"."+ extension;

		String stream = (String)exportBean.getStream();
		ChartMetadata metadata= exportBean.getMetadata();

		boolean isHTML = false;
		if(action.equals("download"))
			isHTML=true;

		StringBuffer err_warn_Codes = new StringBuffer();

	
		String noticeMessage = "";
		String meta_values= exportBean.getMetadataAsQueryString(null,false,isHTML);

		if(!action.equalsIgnoreCase("download")){
			noticeMessage = "&notice=";
			String pathToWebAppRoot = (String)exportBean.getExportParameterValue("webapproot");
			String pathToSaveFolder = pathToWebAppRoot+File.separator+FusionChartsExportHelper.SAVEPATH;
			File saveFolder = new File(pathToSaveFolder);

			
			String completeFilePath = pathToSaveFolder + File.separator + fileName;
			String completeFilePathWithoutExt = pathToSaveFolder + File.separator + fileNameWithoutExt;
			File saveFile = new File(completeFilePath);
			if(saveFile.exists()) {
				noticeMessage+=ErrorHandler.getErrorForCode("W509");
				if(!FusionChartsExportHelper.OVERWRITEFILE){
					if(FusionChartsExportHelper.INTELLIGENTFILENAMING) {
						noticeMessage+=ErrorHandler.getErrorForCode("W514");
						completeFilePath= FusionChartsExportHelper.getUniqueFileName(completeFilePathWithoutExt,extension);
						File tempFile= new File(completeFilePath);
						fileName = tempFile.getName();
						noticeMessage+=ErrorHandler.getErrorForCode("W515")+ fileName;
						err_warn_Codes.append("W515,");
					}
				}
			}
			
			
			try {
				FileOutputStream fos = new FileOutputStream(completeFilePath);
				
				for(int i = 0; i < pdfBytes.length; i++)
					fos.write(pdfBytes[i]);
			    fos.flush();
				fos.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch(IOException e){
				err_warn_Codes.append("E600,");
				
			}
			// In Save mode, send back Successful response back to chart
			
			String pathToDisplay=FusionChartsExportHelper.HTTP_URI+"/"+fileName;
			if (FusionChartsExportHelper.HTTP_URI.endsWith("/")) {
				pathToDisplay=FusionChartsExportHelper.HTTP_URI+fileName;
			}
			// In save mode, isHTML is false			
			meta_values =exportBean.getMetadataAsQueryString(pathToDisplay,false,isHTML);
			/*noticeMessage+="&fileName="+ pathToDisplay;
			noticeMessage+="&width="+ metadata.getWidth();
			noticeMessage+="&height="+ metadata.getHeight();*/
			if(err_warn_Codes.indexOf("E")== -1){
				// if there are no errors
				PrintWriter out;
				try {
					out = response.getWriter();
					out.print(meta_values+noticeMessage+"&statusCode=1&statusMessage=successful");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		else {
			// PDF Streaming
			response.setContentType(FusionChartsExportHelper.getMimeTypeFor(exportFormat.toLowerCase()));

			if(exportTargetWindow.equalsIgnoreCase("_self")){
				response.addHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
				//response.addHeader("Content-length",""+ios.length());
			}
			else {
				response.addHeader("Content-Disposition", "inline; filename=\""+fileName+"\"");
			}
			OutputStream os;
			try {
				os = response.getOutputStream();
			
			for(int i = 0; i < pdfBytes.length; i++)
				os.write(pdfBytes[i]);
			os.flush();
			//os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if(err_warn_Codes.indexOf("E") != -1) {
			meta_values =exportBean.getMetadataAsQueryString(null,true,isHTML);
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(meta_values+noticeMessage+"&statusCode=1&statusMessage=successful");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.fusioncharts.exporter.resources.FcExporter_Format#exportProcessor(com.fusioncharts.exporter.beans.ExportBean)
	 */
	public Object exportProcessor(ExportBean pExportBean) {
		exportBean = pExportBean;
		String stream = (String)exportBean.getStream();
		ChartMetadata metadata= exportBean.getMetadata();
		PDFGenerator pdf = new PDFGenerator(stream,metadata);
		byte[] pdfBytes= pdf.getPDFObjects(true);
		return pdfBytes;
	}

}
