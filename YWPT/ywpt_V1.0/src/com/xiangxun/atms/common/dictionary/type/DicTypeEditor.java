package com.xiangxun.atms.common.dictionary.type;

import java.beans.PropertyEditorSupport;

import com.xiangxun.atms.framework.util.StringUtils;

/***
 * 数据字典属性解析器
 * @author zhouhaij
 * @May 2, 2013 11:24:32 AM
 */
public class DicTypeEditor extends PropertyEditorSupport{

	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	@Override
	public String getAsText() {
		DicType dicType = (DicType)getValue();
		return dicType.toString();
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.isEmpty(text)){
			setValue(null);
		}else{
			DicType dicType = DicType.valuesOf(text);
			setValue(dicType);
		}
	}

	
	
}

