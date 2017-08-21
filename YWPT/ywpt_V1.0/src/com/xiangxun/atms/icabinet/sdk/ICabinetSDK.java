package com.xiangxun.atms.icabinet.sdk;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;

/**
 * 智能机柜SDK
 * 
 * @author Administrator
 *
 */
public class ICabinetSDK {
	static {
		String libPaths = System.getProperty("java.library.path");
		File appPath = SDKTools.getAppPath();
		String rootPath = appPath.getParentFile().getParentFile().getPath();
		Path path = java.nio.file.Paths.get(rootPath, "dlls");
		String libPath = path.toString();
		libPaths = String.format("%s%s%s", libPath, File.pathSeparator, libPaths);
		path = java.nio.file.Paths.get(rootPath, "dlls","HCNetSDKCom");
		libPath = path.toString();			
		libPaths = String.format("%s%s%s", libPath, File.pathSeparator, libPaths);
		System.out.println(libPaths);
		System.setProperty("java.library.path", libPaths);
		try {
			Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
			fieldSysPath.setAccessible(true);
			fieldSysPath.set(null, null);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		System.out.println(System.getProperty("java.library.path"));
		System.loadLibrary("HCCore");
		System.loadLibrary("HCNetSDK");	
		System.loadLibrary("icabinet");
	}

	/**
	 * 初始化（在系统系统时调用一次，必须调用）
	 * 
	 * @return 是否成功
	 */
	public native static boolean init();

	/**
	 * 析构（在系统关闭时调用，最好不要使用）
	 * 
	 * @return 是否成功
	 */
	public native static boolean cleanup();

	/**
	 * 开始监听
	 * 
	 * @param ip 使用的IP，可以使用null
	 * @param port 使用的端口号
	 * @param sender 用于回调的对象
	 * @return 监听的句柄
	 */
	public native static long startListen(String ip, int port, MessageSender sender);

	/**
	 * 结束监听
	 * 
	 * @param listenHandler 监听的句柄
	 * @return 是否成功
	 */
	public native static boolean stopListen(long listenHandler);

}
