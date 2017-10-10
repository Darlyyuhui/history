package com.xiangxun.atms.module.base.vo;

import java.math.BigDecimal;

/**
 * 基础vo实体
 * @author HX
 *
 */
public class BaseVo implements java.io.Serializable {
	
	private static final long serialVersionUID = -9168584691013655503L;

	/**
     * 如果小数后面全是0000，则截取
     * @param val
     * @return
     */
    protected BigDecimal makeNum(BigDecimal val) {
    	if (val == null) {
    		return val;
    	}
    	String str = val.toString();
    	if (str.indexOf(".") > -1) {
    		String[] ss = str.split("\\.");
    		if (ss.length == 2) {
    			//将小数点后面的拆成字符
    			char[] cs = ss[1].toCharArray();
    			boolean isAllZero = true;
    			for (char c : cs) {
    				//判断字符是否都是0
    				if (!"0".equals(c+"")) {
    					isAllZero = false;
    					break;
    				}
    			}
    			if (isAllZero) {
    				return new BigDecimal(ss[0]);
    			}
    		}
    	}
    	return val;
    }
	
}
