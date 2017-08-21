package com.xiangxun.atms.module.gis.util;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xiangxun.atms.framework.util.FileUtils;
import com.xiangxun.atms.framework.util.UploadFileUtils;


/**   
 *    
 * @类描述：   
 * @创建人：cd   
 * @创建时间：2013-7-30 下午12:47:14   
 * @修改人：Administrator   
 * @修改时间：2013-7-30 下午12:47:14   
 * @修改备注：   
 * @version  1.0
 *    
 */

public class PicUpLoadUtil {

	/**
	 * 
	 * @param request 文件上传请求:
	 */
	public String dealUploadFile(HttpServletRequest request){
		  //获取web系统的绝对路径
		 String webPath=request.getSession().getServletContext().getRealPath(File.separator);
		  //建立上传文件夹
		 String folder=webPath+MapConstant.MAP_PIC_FORLDER;
		 //转化成上传文件的系统
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		 //获取上传文件
		 MultipartFile mufile= multipartRequest.getFile("myFile");
		 //上传文件不为空的处理方式:
		 if(mufile!=null&&mufile.getSize()>0){
			 //获取原来的文件
			String file= mufile.getOriginalFilename();
			//得到文件的类型:jpg？
			String extName = file.substring(file.lastIndexOf("."));
			Random r = new Random();
			int rannum = (int) (r.nextDouble()); // 获取随机数
			SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间格式化的格式
			String nowTimeStr = sDateFormat.format(new Date()); // 当前时间
			String newFileName = nowTimeStr + rannum + extName; // 文件重命名后的名字
			File dirfile = new File(folder);   
	        if (!dirfile.isDirectory()) {
	        	dirfile.mkdir();
	        }
	        File path= FileUtils.getCreateFile(folder, newFileName);
	        try {
	        	//保存传上来的文件:
				mufile.transferTo(path);
			} catch (IllegalStateException e) {
				
				return "fail:"+e.getMessage();
			} catch (IOException e) {
				return "fail:"+e.getMessage();
			}
	        return newFileName;
		 }
			return "fail";
	}


	/**
	 * 获取所有已经上传的图片操作:
	 * @param request
	 * @return
	 */
	public List<String> getSelfImages(HttpServletRequest request){
		  //获取web系统的绝对路径
		  String webPath=request.getSession().getServletContext().getRealPath(File.separator);
		  //建立上传文件夹
		  String folder=webPath+MapConstant.MAP_PIC_FORLDER;
	      File file=new File(folder);
	      File[] files= file.listFiles();
	      List<String> list=new ArrayList<String>();
	      for(File f:files){
	    	  list.add(f.getPath().substring(f.getPath().indexOf(MapConstant.MAP_PIC_PARENT)));
	    	  //System.out.println(f.getPath().substring(f.getPath().indexOf("images")));
	      }
	      return list;
	}
	
	/**
	 * 获取文件的扩展名
	 * @author ZLT
	 * @param fileName String 传入文件名
	 * @return String 返回文件名
	 */
	public String getFileExtension(String fileName){
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	/**
	 * 获取新文件名
	 * @author ZLT
	 * @param fileName String 传入文件名
	 * @return String 返回重命名后的文件名
	 */
	public String getFormattedFileName(String fileName){
		Random r = new Random();
		Integer rNum = new Integer(r.nextInt(100)); // 获取随机数
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间格式化的格式
		String nowTimeStr = sDateFormat.format(new Date()); // 当前时间
		return nowTimeStr.concat(rNum.toString()).concat(getFileExtension(fileName)); // 文件重命名后的名字
	}
	
	/**
	 * 上传文件
	 * @author ZLT
	 * @param file CommonsMultipartFile 要上传的文件
	 * @param targetPath String 文件上传具体路径
	 * @return String 返回文件是否上传成功,成功返回文件名，不成功返回failure
	 */
	public String uploadFile(CommonsMultipartFile file,String targetPath){
		String formattedFileName = "";
		if(file.getSize() != 0){
			// 创建路径
			FileUtils.mkDirectory(targetPath);
			formattedFileName = getFormattedFileName(file.getOriginalFilename());
			File tempFile = FileUtils.getCreateFile(targetPath, formattedFileName);
			if(!StringUtils.isEmpty(tempFile.getAbsolutePath())){
				UploadFileUtils.up(file, tempFile.getAbsolutePath());
			}else{
				return "failure";
			}
		}else{
			return "failure";
		}
		return formattedFileName;
	}
	
	/**
	 * 获取指定路径图片列表
	 * @param imgsPath
	 * @return
	 */
	public List<String> getImageList(String imgsPath){
		File imgFolder = new File(imgsPath);
		File[] files = imgFolder.listFiles();
		ArrayList<String> list=new ArrayList<String>();
		for(File f:files){
			list.add(f.getPath().substring(f.getPath().indexOf(MapConstant.MAP_PIC_PARENT)));
		}
		return list;
	}
	
	/**
	 * 删除指定路径的指定图片
	 * @param imgPath
	 * @return
	 */
	public Boolean deleteUploadedTarget(String imgPath){
		File img = new File(imgPath);
		if(img.exists() && img.isFile()){
			img.delete();
			return true;
		}
		return false;
	}
}


