package com.xiangxun.atms.framework.compnents.xls.imports.validate;

import org.apache.poi.ss.usermodel.Row;

/**
 * <p>EXCEL导入格式校验器</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public interface ExcelForMartValidtor {
	
	/***
	 * 对传入的excel文件进行校验，检查其是否套用了导入的模板
	 * @param row
	 * @return true 正确    false(错误)
	 */
	boolean isValid(Row row);

}
