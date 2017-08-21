package com.xiangxun.atms.framework.compnents.xls.imports;

import java.util.List;

/**
 * <p>标题：导入文件校验器</p>
 * @author zhouhaijian 
 * @version 1.0 
 */
public interface XlsImportProcessor {

	/***
	 * 检验导入文件的正确性
	 * @param files
	 * @return 返回错误消息
	 */
	public List<String> importValite(List<String> files);
	
	/***
	 * 得到要导入的记录对象
	 * @return
	 */
	public List<Object> getObjectList();
	
	
	/***
	 * 设置是否使用Model注解方式进行对象转换
	 * @return
	 */
	public Boolean isAnotation();
	
}

