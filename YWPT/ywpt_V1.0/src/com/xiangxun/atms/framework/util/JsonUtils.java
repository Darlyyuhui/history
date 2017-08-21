package com.xiangxun.atms.framework.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import oracle.sql.BLOB;



public class JsonUtils {
	
	/**  
	 * 
     * 把数据对象转换成json字符串  
     * DTO对象形如：{"id" : idValue, "name" : nameValue, ...}  
     * 数组对象形如：[{}, {}, {}, ...]  
     * map对象形如：{key1 : {"id" : idValue, "name" : nameValue, ...}, key2 : {}, ...}  
     * @param object  
     * @return  
     */  
    public static String getJSONString(Object object) throws Exception{   
        String jsonString = null;   
        //日期值处理器   
        JsonConfig jsonConfig = new JsonConfig();   
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonUtils.JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new JsonUtils.JsonDateValueProcessor());   
        if(object != null){   
            if(object instanceof Collection || object instanceof Object[]){   
                jsonString = JSONArray.fromObject(object, jsonConfig).toString();   
            }else{   
                jsonString = JSONObject.fromObject(object, jsonConfig).toString();   
            }
        }   
        return jsonString == null ? "{}" : jsonString;   
    }
    /**  
     * 从json数组中得到相应java数组  
     * json形如：["123", "456"]  
     * @param jsonString  
     * @return  
     */  
    public static Object[] getObjectArrayFromJson(String jsonString) {   
        JSONArray jsonArray = JSONArray.fromObject(jsonString);   
        return jsonArray.toArray();   
    }   
    /**
     * 日期格式的转换
     * @author Administrator
     *
     */
    private static class JsonDateValueProcessor implements JsonValueProcessor {   
    	  
        private String format = "yyyy-MM-dd HH:mm:ss";   
      
        public JsonDateValueProcessor() {   
      
        }   
      
        public JsonDateValueProcessor(String format) {   
            this.format = format;   
        }   
      
        public Object processArrayValue(Object value, JsonConfig jsonConfig) {   
            String[] obj = {};   
            if (value instanceof Date[]) {   
                SimpleDateFormat sf = new SimpleDateFormat(format);   
                Date[] dates = (Date[]) value;   
                obj = new String[dates.length];   
                for (int i = 0; i < dates.length; i++) {   
                    obj[i] = sf.format(dates[i]);   
                }   
            }   
            return obj;   
        }   
      
        public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {   
            if (value instanceof Date) {   
                String str = new SimpleDateFormat(format).format((Date) value);   
                return str;   
            }   
            return value == null ? null : value.toString();   
        }   
      
        public String getFormat() {   
            return format;   
        }   
      
        public void setFormat(String format) {   
            this.format = format;   
        }   
    }  
//    public static String getBlobtoString(Blob blobs) throws IOException{
//    	   
//		   String str="";
//		   BufferedInputStream input = null;  
//		   java.io.InputStream in = null;  
//		try {
//		 SerializableBlob sb = (SerializableBlob)blobs;
////			Serializable sb =(Serializable)blobs
//		 BLOB blob=(BLOB)sb.getWrappedBlob();
//			
//		 in = blobs.getBinaryStream();   
//         input = new BufferedInputStream(in);   
//         int len = 0;  
//         byte [] b = new byte[(int) blob.length()];  
//         input.read(b);  
//         str=new String(b);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "";
//		}finally{
//			input.close();
//			in.close();
//		}
//	     return str;      
//	}
    public static String getBLOBoString(BLOB blobs) throws IOException{
 	   
		   String str="";
		   BufferedInputStream input = null;  
		   java.io.InputStream in = null;  
		   java.io.OutputStream out=null;
		try {	
		// in = blobs.getBinaryStream();   
		 out=blobs.getBinaryOutputStream();
         input = new BufferedInputStream(in);   
     // int len = 0;  
       str=  new String (blobs.getLocator());
       byte [] b = new byte[(int)blobs.length()];  
        input.read(b);  
        str=new String(b);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}finally{
			input.close();
			//in.close();
		}
	     return str;      
	}
    public static byte[] getStringtoBlob(String blobsting) throws IOException{
    	byte filedata[] =null;
		   try {
			   ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			   InputStream stream = new ByteArrayInputStream(blobsting.getBytes());
				int ch;
				while ((ch = stream.read()) != -1) {
					bytestream.write(ch);
				}
				 filedata= bytestream.toByteArray();
				bytestream.close();
		    } catch (Exception e) {
			   e.printStackTrace();
		   }
	  return filedata;		
	}
}


