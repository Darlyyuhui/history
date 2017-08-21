package com.xiangxun.atms.common.file;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.util.FileDelete;
import java.io.File;

/***
 * 文件服务
 * @author zhouhaij
 *
 */
public class FileRemoveSchedule {

	private Logging logger = new Logging(FileRemoveSchedule.class);
	
	private Long days = 3l;
	
	private FileDelete fileDelete = new FileDelete();
	
	/***
	 * 删除已下载目录中大于days天的文件
	 */
	public void removeFile(){
		  String contentPath = System.getProperty("atms.root");
		  String  filepath = contentPath+File.separator+"download";
		  File file = new File(filepath);
		  //获取所有的文件
		  List<File> files = new ArrayList<File>();
		  //递归获取所有的文件
		  getFiles(files,file);
		  //按修改时间进行升序排列
		  /**Collections.sort(files, new Comparator<File>(){
			@Override
			public int compare(File arg0, File arg1) {
				return Integer.parseInt(arg0.lastModified()-arg1.lastModified()+"");
			}
		  });**/
		  long day = 24*days;
		  Date now = new Date();
		  for (File file2 : files) {
			  logger.debug(file2.getName());
			  //距离现在的小时
			  long curentSep = (now.getTime()-file2.lastModified())/(1000*60*60);
			  logger.debug("距今小时{0}",curentSep+"");
			  //如果大于x天则删除
			  if(curentSep>=day){
				  logger.debug("正在删除:{0}",file2.getName());
				  fileDelete.deleteFile(file2.getAbsolutePath());
			  }
			 
		  }
		  
	}
	/***
	 * 递归获取所有的文件
	 * @param lists
	 * @param files
	 */
	private void getFiles(List<File> lists,File files){
		 if(lists==null) return;
		 if(files==null) return;
		 for (File file :  files.listFiles()) {
			 if(file.isFile()){
				 lists.add(file);
			 }
			 
			 if(file.listFiles()!=null && file.listFiles().length>0){
				 getFiles(lists,file);
			 }
		 }
	}
	
	public static void main(String[] args) {
		new FileRemoveSchedule().removeFile();
	}

	public Long getDays() {
		return days;
	}

	public void setDays(Long days) {
		this.days = days;
	}
	
	
}
