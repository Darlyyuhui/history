package com.xiangxun.atms.framework.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.xiangxun.atms.framework.resource.MessageResources;
import com.xiangxun.atms.module.sergrade.web.ContactInfoCtl;


/**
 * @author lhh
 * */
public class FileUtils {

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static boolean delFile(String filePathAndName) {
		File myDelFile = new java.io.File(filePathAndName);
		if (!myDelFile.exists()) {
			return true;
		}
		return myDelFile.delete();
	}

	/**
	 * 删除指定文件路径下面的所有文件和文件夹
	 * 
	 * @param file
	 */
	public static boolean delFiles(File file) {
		boolean flag = false;
		try {
			if (file.exists()) {
				if (file.isDirectory()) {
					String[] contents = file.list();
					for (int i = 0; i < contents.length; i++) {
						File file2X = new File(file.getAbsolutePath() + "/" + contents[i]);
						if (file2X.exists()) {
							if (file2X.isFile()) {
								flag = file2X.delete();
							} else if (file2X.isDirectory()) {
								delFiles(file2X);
							}
						} else {
							throw new RuntimeException("File not exist!");
						}
					}
				}
				flag = file.delete();
			} else {
				throw new RuntimeException("File not exist!");
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 判断文件名是否已经存在，如果存在则在后面家(n)的形式返回新的文件名，否则返回原始文件名 例如：已经存在文件名 log4j.htm
	 * 则返回log4j(1).htm
	 * 
	 * @param fileName
	 *            文件名
	 * @param dir
	 *            判断的文件路径
	 * @return 判断后的文件名
	 */
	public static String checkFileName(String fileName, String dir) {
		boolean isDirectory = new File(dir + fileName).isDirectory();
		if (FileUtils.isFileExist(fileName, dir)) {
			int index = fileName.lastIndexOf(".");
			StringBuffer newFileName = new StringBuffer();
			String name = isDirectory ? fileName : fileName.substring(0, index);
			String extendName = isDirectory ? "" : fileName.substring(index);
			int nameNum = 1;
			while (true) {
				newFileName.append(name).append("(").append(nameNum).append(")");
				if (!isDirectory) {
					newFileName.append(extendName);
				}
				if (FileUtils.isFileExist(newFileName.toString(), dir)) {
					nameNum++;
					newFileName = new StringBuffer();
					continue;
				}
				return newFileName.toString();
			}
		}
		return fileName;
	}

	/**
	 * 返回上传的结果，成功与否
	 * 
	 * @param uploadFileName
	 * @param savePath
	 * @param uploadFile
	 * @return
	 */
	public static boolean upload(String uploadFileName, String savePath, File uploadFile) {
		boolean flag = false;
		try {
			uploadForName(uploadFileName, savePath, uploadFile);
			flag = true;
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 上传文件并返回上传后的文件名
	 * 
	 * @param uploadFileName
	 *            被上传的文件名称
	 * @param savePath
	 *            文件的保存路径
	 * @param uploadFile
	 *            被上传的文件
	 * @return 成功与否
	 * @throws IOException
	 */
	public static String uploadForName(String uploadFileName, String savePath, File uploadFile) throws IOException {
		String newFileName = checkFileName(uploadFileName, savePath);
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream(savePath + newFileName);
			fis = new FileInputStream(uploadFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return newFileName;
	}

	/**
	 * 根据路径创建一系列的目录,只创建文件夹
	 * 
	 * @param path
	 */
	public static boolean mkDirectory(String path) {
		File file = null;
		try {
			file = new File(path);
			if (!file.exists()) {
				return file.mkdirs();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			file = null;
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		fileChannelCopy("D:\\apache-tomcat-atms\\webapps\\itms\\attachfile\\occupy\\pic\\temp\\201403171646050383056318.jpg", "D:\\apache-tomcat-atms\\webapps\\itms\\attachfile\\occupy\\pic\\201403171646050383056318.jpg");
	}
	
	
	/**
	 * 将对象数组的每一个元素分别添加到指定集合中,调用Apache commons collections 中的方法
	 * 
	 * @param collection
	 *            目标集合对象
	 * @param arr
	 *            对象数组
	 */
	public static void addToCollection(Collection collection, Object[] arr) {
		if (null != collection && null != arr) {
			CollectionUtils.addAll(collection, arr);
		}
	}

	/**
	 * 将字符串已多个分隔符拆分为数组,调用Apache commons lang 中的方法
	 * 
	 * <pre>
	 *                                               Example:
	 *                                                String[] arr = StringUtils.split(&quot;a b,c d,e-f&quot;, &quot; ,&quot;);
	 *                                                System.out.println(arr.length);//输出6
	 * </pre>
	 * 
	 * @param str
	 *            目标字符串
	 * @param separatorChars
	 *            分隔符字符串
	 * @return 字符串数组
	 */
	public static String[] split(String str, String separatorChars) {
		return StringUtils.split(str, separatorChars);
	}

	/**
	 * 调用指定字段的setter方法
	 * 
	 * <pre>
	 *               Example:
	 *                User user = new User();
	 *                MyUtils.invokeSetMethod(&quot;userName&quot;, user, new Object[] {&quot;张三&quot;});
	 * </pre>
	 * 
	 * @param fieldName
	 *            字段(属性)名称
	 * @param invokeObj
	 *            被调用方法的对象
	 * @param args
	 *            被调用方法的参数数组
	 * @return 成功与否
	 */
	public static boolean invokeSetMethod(String fieldName, Object invokeObj, Object[] args) {
		boolean flag = false;
		Field[] fields = invokeObj.getClass().getDeclaredFields(); // 获得对象实体类中所有定义的字段
		Method[] methods = invokeObj.getClass().getDeclaredMethods(); // 获得对象实体类中所有定义的方法
		for (Field f : fields) {
			String fname = f.getName();
			if (fname.equals(fieldName)) {// 找到要更新的字段名
				String mname = "set" + (fname.substring(0, 1).toUpperCase() + fname.substring(1));// 组建setter方法
				for (Method m : methods) {
					String name = m.getName();
					if (mname.equals(name)) {
						// 处理Integer参数
						if (f.getType().getSimpleName().equalsIgnoreCase("integer") && args.length > 0) {
							args[0] = Integer.valueOf(args[0].toString());
						}
						// 处理Boolean参数
						if (f.getType().getSimpleName().equalsIgnoreCase("boolean") && args.length > 0) {
							args[0] = Boolean.valueOf(args[0].toString());
						}
						try {
							m.invoke(invokeObj, args);
							flag = true;
						} catch (IllegalArgumentException e) {
							flag = false;
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							flag = false;
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							flag = false;
							e.printStackTrace();
						}
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * 根据路径和文件名称，创建该路径下文件
	 * @param path 创建文件目录
	 * @param name 创建文件的名称
	 * @return boolean 返回创建该文件是否成功
	 */
	public static boolean createFile(String path,String name){
		boolean flag = false;
		File file = new File (path+File.separator+name);
		if (file.exists()) {
			flag = true;
		}else {
			try {
				if (file.createNewFile()) {
					flag = true;
				}else {
					flag = false;
				}
			} catch (IOException e) {
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * 根据路径和文件名称，创建该路径下文件
	 * @param path 创建文件目录
	 * @param name 创建文件的名称
	 * @return File 返回该文件
	 */
	public static File getCreateFile(String path,String name){
		
		File file = new File (path+File.separator+name);
		if (file.exists()) {
			return file;
		}else {
			try {
				if (file.createNewFile()) {
					return file;
				}else {
					return  new File("");
				}
			} catch (IOException e) {
				return new File("");
			}
		}
	}
	
	/**
	 * 判断文件是否存在
	 * 
	 * @param fileName
	 * @param dir
	 * @return
	 */
	public static boolean isFileExist(String fileName, String dir) {
		File files = new File(dir + fileName);
		return (files.exists()) ? true : false;
	}

	/**
	 * 获得随机文件名,保证在同一个文件夹下不同名
	 * 
	 * @param fileName
	 * @param dir
	 * @return
	 */
	public static String getRandomName(String fileName, String dir) {
		String[] split = fileName.split("\\.");// 将文件名已.的形式拆分
		String extendFile = "." + split[split.length - 1].toLowerCase(); // 获文件的有效后缀

		Random random = new Random();
		int add = random.nextInt(1000000); // 产生随机数10000以内
		String ret = add + extendFile;
		while (isFileExist(ret, dir)) {
			add = random.nextInt(1000000);
			ret = fileName + add + extendFile;
		}
		return ret;
	}

	/**
	 * 创建缩略图
	 * 
	 * @param file
	 *            上传的文件流
	 * @param height
	 *            最小的尺寸
	 * @throws IOException
	 */
	public static void createMiniPic(File file, float width, float height) throws IOException {
		Image src = javax.imageio.ImageIO.read(file); // 构造Image对象
		int old_w = src.getWidth(null); // 得到源图宽
		int old_h = src.getHeight(null);
		int new_w = 0;
		int new_h = 0; // 得到源图长
		float tempdouble;
		if (old_w >= old_h) {
			tempdouble = old_w / width;
		} else {
			tempdouble = old_h / height;
		}

		if (old_w >= width || old_h >= height) { // 如果文件小于锁略图的尺寸则复制即可
			new_w = Math.round(old_w / tempdouble);
			new_h = Math.round(old_h / tempdouble);// 计算新图长宽
			while (new_w > width && new_h > height) {
				if (new_w > width) {
					tempdouble = new_w / width;
					new_w = Math.round(new_w / tempdouble);
					new_h = Math.round(new_h / tempdouble);
				}
				if (new_h > height) {
					tempdouble = new_h / height;
					new_w = Math.round(new_w / tempdouble);
					new_h = Math.round(new_h / tempdouble);
				}
			}
			BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null); // 绘制缩小后的图
			FileOutputStream newimage = new FileOutputStream(file); // 输出到文件流
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
			param.setQuality((float) (100 / 100.0), true);// 设置图片质量,100最大,默认70
			encoder.encode(tag, param);
			encoder.encode(tag); // 将JPEG编码
			newimage.close();
		}
	}

	/**
	 * 判断文件类型是否是合法的,就是判断allowTypes中是否包含contentType
	 * 
	 * @param contentType
	 *            文件类型
	 * @param allowTypes
	 *            文件类型列表
	 * @return 是否合法
	 */
	public static boolean isValid(String contentType, String[] allowTypes) {
		if (null == contentType || "".equals(contentType)) {
			return false;
		}
		for (String type : allowTypes) {
			if (contentType.equals(type)) {
				return true;
			}
		}
		return false;
	}

	private static void getDir(String directory, String subDirectory){
	     String dir[];
	     File fileDir = new File(directory);
	     try {
	      if (subDirectory == "" && fileDir.exists() != true)
	       fileDir.mkdir();
	      else if (subDirectory != "") {
	       dir = subDirectory.replace('\\', '/').split("/");
	       for (int i = 0; i < dir.length; i++) {
	        File subFile = new File(directory + File.separator + dir[i]);
	        if (subFile.exists() == false)
	         subFile.mkdir();
	        directory += File.separator + dir[i];
	       }
	      }
	     }catch (Exception ex) {
	       System.out.println(ex.getMessage());
	     }
	 }
	
	 // 转换文件大小的方法
	public static String format(long size) {
		if (size < 1024) {
			return size + "B";
		} else if (size < 1048576) {
			double beforeNum = size / 1024;
			double temp = size % 1024;
			double afterNum = temp / 1024;
			// 取小数点后1位
			double num = new BigDecimal(beforeNum + afterNum).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
			return num + "KB";
		} else if (size < 1073741824) {
			double beforeNum = size / 1048576;
			double temp = size % 1048576;
			double afterNum = temp / 1048576;
			double num = new BigDecimal(beforeNum + afterNum).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			return num + "MB";
		} else {
			double beforeNum = size / 1073741824;
			double temp = size % 1073741824;
			double afterNum = temp / 1073741824;
			double num = new BigDecimal(beforeNum + afterNum).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			return num + "GB";
		}
	}
	
	/**
	 * 
	 * 附件保存路径获取
	 * @author kouyunhao
	 * @version 1.0
	 * @param key
	 * @return
	 * Oct 8, 2013
	 */
	public static String getAttachSavePath(String key){
		MessageResources msg = MessageResources.getMessageInstance("attach-savepath.properties", null,Locale.CHINA);
		return msg.getMessage(key);
	}
	
	/**
	 * 
	 * 消息发送配置信息获取
	 * @author kouyunhao
	 * @version 1.0
	 * @param key
	 * @return
	 * Oct 8, 2013
	 */
	public static String getSendmessageParams(String key){
		MessageResources msg = MessageResources.getMessageInstance("sendmessage.properties", null,Locale.CHINA);
		return msg.getMessage(key);
	}
	
	public static String getLatestFileInDir(String savePath){
		String fileName = "";
		Map<Long, String> nameMap = new HashMap<Long, String>();
		File file = new File(savePath);
		File[] fileList = file.listFiles();
		for(int i = 0; i < fileList.length; i++){
			long value = Long.parseLong(fileList[i].getName().substring(2, 14)); 
			nameMap.put(value, fileList[i].getName());
		}
		long max = Long.parseLong(fileList[0].getName().substring(2, 14));
		for(int i = 0; i < fileList.length; i++){
			long curr = Long.parseLong(fileList[i].getName().substring(2, 14)); 
			if(curr > max){
				max = curr;
			}
		}
		//System.out.println("max: " + max);
		fileName = nameMap.get(max);
		//System.out.println("fileName: " + fileName);
		return fileName;
	}
	
	/**
	 * 文件重命名
	 * @author kouyunhao
	 * @version 1.0
	 * @param suffix
	 * @return
	 * Oct 10, 2013
	 */
	public static String getFormattedFileName(String suffix){
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatFileName = simpleFormat.format(new Date()) + new Random().nextInt(1000) + suffix;
		return formatFileName;
	}
	
	/**
	 * 文件管道流复制（效率高线程安全）
	 * @author kouyunhao
	 * @version 1.0
	 * @param srcFile
	 * @param targetFile
	 * Mar 18, 2014
	 */
	public static void fileChannelCopy(String srcFile, String targetFile) {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
		    fi = new FileInputStream(new File(srcFile));
		    fo = new FileOutputStream(new File(targetFile));
		    in = fi.getChannel();//得到对应的文件通道
			out = fo.getChannel();//得到对应的文件通道
			in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if(fi != null){fi.close();}
	            if(fi != null){in.close();}
	            if(fi != null){fo.close();}
	            if(fi != null){out.close();}
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	/**
	 * 利用FileInputStream读取文件
	 * @author kouyunhao
	 * @version 1.0
	 * @param path
	 * @return
	 * @throws IOException
	 * Jun 4, 2014
	 */
	public static String readFile(String path) throws IOException{
        File file = new File(path);
        StringBuffer sb = null;
        if(file.exists()&& !file.isDirectory()){
        	FileInputStream fis = new FileInputStream(file);
        	byte[] buf = new byte[1024];
        	sb = new StringBuffer();
        	while((fis.read(buf)) != -1){
        		sb.append(new String(buf));    
        		buf = new byte[1024];//重新生成，避免和上次读取的数据重复
        	}
        }else{
        	throw new FileNotFoundException();
        }
    	return sb.toString();
    }

	/**
	 * 根据properties文件路径获取对应key的value值
	 * 
	 * @param filePath
	 *            文件路径
	 * @param key
	 *            key值
	 * @return
	 */
	public static String getKey(String filePath, String key, Class<? extends ContactInfoCtl> class1) {
		Properties prop = new Properties();
		String keyValue = "";
		try {
			if(class1 != null){
				prop.load(class1.getResourceAsStream(filePath));
			}
			keyValue = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return keyValue;
	}


}
