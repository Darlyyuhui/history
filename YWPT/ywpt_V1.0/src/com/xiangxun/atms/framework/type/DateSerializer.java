package com.xiangxun.atms.framework.type;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.xiangxun.atms.framework.constant.FORMAT;
import com.xiangxun.atms.framework.util.DateUtil;

/**
 * <p>jackson的日期转换器</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public class DateSerializer extends JsonSerializer<Date>{

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
	 */
	@Override
	public void serialize(Date arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {
		String result ="";
		if(arg0 != null){
			result = DateUtil.dateFormatToString(arg0,FORMAT.DATETIME.DEFAULT);
		}
		arg1.writeString(result);
	}

}
