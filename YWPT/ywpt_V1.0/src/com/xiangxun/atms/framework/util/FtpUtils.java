package com.xiangxun.atms.framework.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/** 注意每次使用完必须调用closeFtp()方法关闭Ftp连接,由于上传可能比较耗时,所以此类不能设计成单根的
 * FtpUtils ftp=new FtpUtils();
 * .........处理具体业务.....
 * ftp.closeFtp();
 */

public final class FtpUtils {

	private String UserName = null;
	private String Password = null;
	private String Ip = null;
	private Integer Port = 21;
	private FTPClient ftpClient = null;
	private static final String ConfigFile = "ftp.properties";

	public FtpUtils() {
		setArg(ConfigFile);
	}

	public FtpUtils(String username, String pass, String ip, Integer port) {
		this.UserName = username;
		this.Password = pass;
		this.Ip = ip;
		this.Port = port;
		this.connectServer();
	}

	/** 列出文件夹下的所有文件名和文件夹名 
	 * 返回路径加文件名
	 * Staoml 2011-07-05
	 */
	public List<String> listFileAndFolder(String remoteFolder) {
		this.checkConnect();
		List<String> list = new ArrayList<String>();
		try {
			String[] files = ftpClient.listNames(remoteFolder);
			if (files != null) {
				for (String string : files) {
					list.add(string);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/** 列出文件夹下的所有文件名 
	 * 只返回文件名 
	 * Staoml 2011-07-05
	 */
	public List<String> listFiles(String remoteFolder) {
		this.checkConnect();
		List<String> list = new ArrayList<String>();
		try {
			FTPFile[] files = ftpClient.listFiles(remoteFolder);
			if (files != null && files.length > 0)
				for (FTPFile file : files) {
					if (file.isFile())
						list.add(file.getName());
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/** 列出文件夹下的所有文件夹 
	 * 只返回文件夹名字 
	 * Staoml 2011-07-05
	 */
	public List<String> listFolders(String remoteFolder) {
		this.checkConnect();
		List<String> list = new ArrayList<String>();
		try {
			FTPFile[] files = ftpClient.listFiles(remoteFolder);
			if (files != null && files.length > 0) {
				for (FTPFile file : files) {
					if (file.isDirectory())
						list.add(file.getName());
				}
			}  
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/** 删除ftp服务器上的一个文件夹
	 * 由于操作系统的锁定,不能保证绝对能删除此文件夹及其所有文件
	 * Staoml 2011-07-05
	 */
	public void deleteFolder(String remoteFolder) {
		this.checkConnect();
		try {
			FTPFile[] files = ftpClient.listFiles(remoteFolder);
			for (FTPFile file : files) {
				if (file.isFile()) {
					ftpClient.deleteFile(remoteFolder + "\\" + file.getName());
				} else {
					this.deleteFolder(remoteFolder + "\\" + file.getName());
				}
			}
			ftpClient.removeDirectory(remoteFolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 删除ftp服务器上的一个文件
	 * 返回成功与否的标记
	 * Staoml 2011-07-05
	 */
	public boolean deleteFile(String filename) {
		this.checkConnect();
		boolean flag = true;
		try {
			flag = ftpClient.deleteFile(filename);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return flag;
	}	
	/** 级联创建目录 注意folderPath为ftp文件夹路径,不能包含文件名 
	 * Staoml 2011-06-19
	 */
	public void createfolder(String folderPath) {
		if (folderPath == null || "".equals(folderPath))
			return;
		this.checkConnect();
		try {
			String separate = folderPath.indexOf("/") > 0 ? "/" : "\\\\";// 路径中使用的下一级文件夹的分隔符,java中用split方法用"\\\\"来当"\"分隔
			String[] pathL = folderPath.split(separate);
			String path = "";
			for (String string : pathL) {
				path += path.length() > 0 ? "/" + string : string;
				ftpClient.makeDirectory(path);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 移动重命名文件 fromPath,toPath包含相信路径和文件名 当源文件不存在或目标文件已存在时返回false
	 * Staoml 2011-07-05
	 */
	public boolean moveAndRenameFile(String fromPath, String toPath) {
		this.checkConnect();
		boolean mark = true;
		try {
			mark = ftpClient.rename(fromPath, toPath);
			if (!mark) {
				this.createfolder(this.getFolder(toPath));
				mark = ftpClient.rename(fromPath, toPath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mark;
	}

	// 得到文件夹路径    Staoml 2011-07-05
	private String getFolder(String path) {
		String separate = path.indexOf("/") > 0 ? "/" : "\\";
		int add = path.lastIndexOf(separate);
		if (add > 0) {
			return path.substring(0, add);
		} else {
			return "";
		}
	}
	
	
	
	/** 将ftp服务器的文件下载到本地
	 * Staoml 2011-07-05
	 */
	public boolean loadFile(String remoteFile, String localFile) {
		this.checkConnect();
		boolean flag = true;
		BufferedOutputStream buffOut = null;
		try {
			buffOut = new BufferedOutputStream(new FileOutputStream(localFile));
			flag = ftpClient.retrieveFile(remoteFile, buffOut);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffOut != null)
					buffOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/** 将ftp服务器的文件放到一个输出流中
	 * remoteFile:FTP文件  
	 * Staoml 2011-07-05
	 */
	public boolean loadtoStream(String remoteFile, OutputStream output) {
		this.checkConnect();
		boolean flag = false;
		try {
			flag = ftpClient.retrieveFile(remoteFile, output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	/** 将ftp服务器的文件放到一个输出流中
	 * 需要手动关闭得到的输出流
	 * Staoml 2011-07-05
	 */
	public OutputStream loadtoStream(String remoteFile) {
		this.checkConnect();
		try {
			return ftpClient.storeFileStream(remoteFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/** 将ftp服务器的文件放到一个输入流中
	 * 需要手动关闭得到的输入流
	 * Staoml 2011-07-05
	 */
	public InputStream loadtoInputStream(String remoteFile) {
		this.checkConnect();
		try {
			return ftpClient.retrieveFileStream(remoteFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/** 得到ftp文件的大小
	 * 返回-1 表示文件不存在
	 * Staoml 2011-07-05
	 */
	public long getFileSize(String remoteFile) {
		this.checkConnect();
		try {
			FTPFile[] ftpFile = ftpClient.listFiles(remoteFile);
			if(ftpFile==null||ftpFile.length==0){
				return -1;
			}
			else{
				return ftpFile[0].getSize();
			}		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	/** 上传本地文件
	 * localFile:本地文件,remoteFile:目标FTP文件
	 * Staoml 2011-07-06
	 */
	public boolean uploadFile(String localFile, String remoteFile) {
		this.checkConnect();
		InputStream input = null;
		boolean mark=true;
		try {
			ftpClient.enterLocalPassiveMode();
			input = new FileInputStream(localFile);
			mark=ftpClient.storeFile(remoteFile, input);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return mark;
	}

	/** 上传本地文件
	 * input:输入流,remoteFile:目标FTP文件
	 * Staoml 2011-07-06
	 */
	public boolean uploadFile(InputStream input, String remoteFile) {
		this.checkConnect();
		boolean mark=true;
		try {
			 mark=ftpClient.storeFile(remoteFile, input);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return mark;
	}

	/** 上传文件 把一个file文件对象上传到ftp
	 * file:文件对象,remoteFile:目标FTP文件
	 * Staoml 2011-07-06
	 */
	public void uploadFile(File file, String remoteFile) {
		this.checkConnect();
		InputStream input = null;
		try {
			ftpClient.enterLocalPassiveMode();
			input = new FileInputStream(file);
			ftpClient.storeFile(remoteFile, input);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// *****************************************************************************************************
	/** 建立ftp连接 Staoml 2011-06-19
	 */
	private boolean connectServer() {
		boolean flag = true;
		try {
			if (!(StringUtils.notEmpty(this.Ip) && (this.Port != null) && StringUtils.notEmpty(this.UserName) && StringUtils
					.notEmpty(this.Password))) {
			
				setArg(ConfigFile);// 如果当前ftp信息不全则从配置文件取连接信息
			}
			
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("UTF-8");
			ftpClient.setDefaultPort(Port);
			ftpClient.connect(Ip);
			ftpClient.login(UserName, Password);
			ftpClient.setDataTimeout(20000);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);// 设置文件类型为2进制文件，否着文件可能就用不成了。
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {// 测试连接是否成功，
				ftpClient.disconnect();
				System.err.println("FTP服务器拒绝连接");
				flag = false;
			}
		} catch (SocketException e) {
			flag = false;
			e.printStackTrace();
			System.err.println("登录ftp服务器【" + Ip + "】失败,连接超时！");
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
			System.err.println("登录ftp服务器【" + Ip + "】失败，FTP服务器无法打开！");
		}
		return flag;
	}

	/** 如果当前ftp链接已经关闭,则重连 Staoml 2011-06-19
	 */
	private void checkConnect() {
		if (!ftpClient.isConnected()) {
		
			this.connectServer();
		}
	}

	/** 关闭ftp链接 Staoml 2011-06-19
	 */
	public void closeFtp() {
		try {
			if (ftpClient != null) {
				ftpClient.disconnect();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 读取配置文件 Staoml 2011-06-19
	 */
	private void setArg(String configFile) {
		Properties property = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = this.getClass().getClassLoader().getResourceAsStream(configFile);
			property.load(inputStream);
			this.UserName = property.getProperty("ftp.name");
			this.Password = property.getProperty("ftp.password");
			this.Ip = property.getProperty("ftp.ip");
			this.Port = Integer.valueOf(property.getProperty("ftp.port"));
		} catch (FileNotFoundException e1) {
			System.out.println("配置文件 \"" + configFile + "\" 不存在！");
			e1.printStackTrace();
		} catch (IOException e) {
			System.out.println("配置文件 \"" + configFile + "\" 无法读取！");
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	
	// ****************************************************

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getIp() {
		return Ip;
	}

	public void setIp(String ip) {
		Ip = ip;
	}

	public Integer getPort() {
		return Port;
	}

	public void setPort(Integer port) {
		Port = port;
	}

}
