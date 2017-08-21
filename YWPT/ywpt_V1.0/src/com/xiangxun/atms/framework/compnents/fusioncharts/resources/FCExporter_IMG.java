package com.xiangxun.atms.framework.compnents.fusioncharts.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.fusioncharts.exporter.ErrorHandler;
import com.fusioncharts.exporter.FusionChartsExportHelper;
import com.fusioncharts.exporter.beans.ChartMetadata;
import com.fusioncharts.exporter.beans.ExportBean;
import com.fusioncharts.exporter.encoders.BasicEncoder;
import com.fusioncharts.exporter.encoders.JPEGEncoder;
import com.fusioncharts.exporter.generators.ImageGenerator;
/**
 * 
 * @author InfosoftGlobal (P) Ltd.
 *
 */
public class FCExporter_IMG extends FCExporter_Format {
	
	private ExportBean exportBean=null;
	/* (non-Javadoc)
	 * @see com.fusioncharts.exporter.resources.FcExporter_Format#exportOutput(java.lang.Object, javax.servlet.http.HttpServletResponse, java.lang.String, int)
	 */
	
	public String exportOutput(Object exportObj, HttpServletResponse response) {
		System.out.print("4444");
		String action= (String)exportBean.getExportParameterValue("exportaction");
		String exportFormat = (String)exportBean.getExportParameterValue("exportformat");
		String exportTargetWindow = (String)exportBean.getExportParameterValue("exporttargetwindow");

		String fileNameWithoutExt = (String)exportBean.getExportParameterValue("exportfilename");
		String extension = FusionChartsExportHelper.getExtensionFor(exportFormat.toLowerCase());;
		String fileName = fileNameWithoutExt+"."+ extension;
		String stream = (String)exportBean.getStream();
		
		StringBuffer err_warn_Codes = new StringBuffer();

		BufferedImage chartImage = (BufferedImage)exportObj;
		boolean isHTML = false;
		if(action.equals("download"))
			isHTML=true;

		String noticeMessage = "";
		String meta_values= exportBean.getMetadataAsQueryString(null,false,isHTML);
		

		if(!action.equals("download")){
			noticeMessage = "&notice=";
		     // For servlet api before 2.1 use the following
			//String requestURL = HttpUtils.getRequestURL(request).toString();
			// in servlet api 2.1 use the following
			//String requestURL = request.getRequestURL().toString();

			String pathToSaveFolder = (String)exportBean.getExportParameterValue("webapproot")+File.separator+FusionChartsExportHelper.SAVEPATH;
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
			// In Save mode, send back Successful response back to chart
			// In save mode, isHTML is false
			String pathToDisplay=FusionChartsExportHelper.HTTP_URI+"/"+fileName;
			if (FusionChartsExportHelper.HTTP_URI.endsWith("/")) {
				pathToDisplay=FusionChartsExportHelper.HTTP_URI+fileName;
			}
						
			  meta_values =exportBean.getMetadataAsQueryString(pathToDisplay,false,isHTML);
			try{
			// Now encode and save to file
			FileImageOutputStream fios = new FileImageOutputStream(new File(completeFilePath));
			if( exportFormat.toLowerCase().equalsIgnoreCase("jpg") || exportFormat.toLowerCase().equalsIgnoreCase("jpeg")){
				JPEGEncoder jpegEncoder = new JPEGEncoder();
				try {
					jpegEncoder.encode(chartImage,fios);
				}catch(Throwable e){
					//TODO Unable to encode the buffered image
					err_warn_Codes.append("E516,");
				}
				chartImage=null;
			}
			else {
				
				BasicEncoder basicEncoder = new BasicEncoder();
				try {
					basicEncoder.encode(chartImage,fios,1F,exportFormat.toLowerCase());
				}catch(Throwable e){
					System.out.println(" Unable to encode the buffered image");
					err_warn_Codes.append("E516,");
				}
				chartImage=null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				if(err_warn_Codes.indexOf("E")<0){
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
			else{
				try { 
					response.setContentType(FusionChartsExportHelper.getMimeTypeFor(exportFormat.toLowerCase()));
					
					OutputStream os = response.getOutputStream();
					
					if(exportTargetWindow.equalsIgnoreCase("_self")){
						response.addHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
						//response.addHeader("Content-length",""+ios.length());
					}
					else {
						response.addHeader("Content-Disposition", "inline; filename=\""+fileName+"\"");
					}
					if( exportFormat.toLowerCase().equalsIgnoreCase("jpg") || exportFormat.toLowerCase().equalsIgnoreCase("jpeg")){
						JPEGEncoder jpegEncoder = new JPEGEncoder();
						try {
							jpegEncoder.encode(chartImage,os);
							os.flush();
						}catch(Throwable e){
							//Unable to encode the buffered image
							System.out.println("Unable to (JPEG) encode the buffered image");
							err_warn_Codes.append("E516,");
							//os.flush();
							// Note forward will not work in this case, as the output stream has already been opened
							// Hence we have to output the error directly.
							meta_values =exportBean.getMetadataAsQueryString(null,true,isHTML);
							// Reset the response to set new content type and use out instead of outputstream
							response.reset();
							response.setContentType("text/html");
							PrintWriter out = response.getWriter();
							out.print(meta_values+noticeMessage+ErrorHandler.buildResponse(err_warn_Codes.toString(),isHTML));
							return null;
						}
						chartImage=null;
					}
					else {
						
						BasicEncoder basicEncoder = new BasicEncoder();
						try {
							basicEncoder.encode(chartImage,os,1F,exportFormat.toLowerCase());
							os.flush();
						}catch(Throwable e){
							System.out.println("Unable to encode the buffered image");
							err_warn_Codes.append("E516,");
							//os.flush();
							// Note forward will not work in this case, as the output stream has already been opened
							// Hence we have to output the error directly.
							meta_values =exportBean.getMetadataAsQueryString(null,true,isHTML);
							// Reset the response to set new content type and use out instead of outputstream
							response.reset();
							response.setContentType("text/html");
							PrintWriter out = response.getWriter();
							out.print(meta_values+noticeMessage+ErrorHandler.buildResponse(err_warn_Codes.toString(),isHTML));
							return null;
						}
						chartImage=null;
					}
					
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

	
	public Object exportProcessor(ExportBean pExportBean) {
		exportBean = pExportBean;
		String stream = (String)exportBean.getStream();
		ChartMetadata metadata= exportBean.getMetadata();
		BufferedImage chartImage = ImageGenerator.getChartImage(stream,metadata);
		
		return chartImage;
	}

}
