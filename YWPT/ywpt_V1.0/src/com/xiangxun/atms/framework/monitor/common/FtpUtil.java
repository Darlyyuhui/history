package com.xiangxun.atms.framework.monitor.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.StringTokenizer;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;
/*
 * 
 */
/**
 * FTP上传工具类
 * 
 * @author icelove
 * 
 */
public class FtpUtil {
	private String ip = "";
	private String username = "";
	private String password = "";
	private int port = -1;
	private String path = "";
	private FtpClient ftpClient = null;
	private OutputStream os = null;
	private FileInputStream is = null;

	public FtpUtil(String serverIP, String username, String password) {
		this.ip = serverIP;
		this.username = username;
		this.password = password;
	}

	public FtpUtil(String serverIP, int port, String username, String password) {
		this.ip = serverIP;
		this.username = username;
		this.password = password;
		this.port = port;
	}
	
	public FtpUtil(String ip, String username,
			String password,String path) {
		this.username=username;
		this.ip=ip;
		this.password=password;
		this.path=path;
	}
	


	/**
	 * 连接ftp服务器
	 * @throws IOException 
	 * 
	 * @throws IOException
	 */
	public boolean connectServer() throws IOException{
			try {
				ftpClient = FtpClient.create();
			} catch (Exception e1) {
				System.out.println("创建FtpClient时发生运行时异常！");
			}
			try {
				SocketAddress addr = new InetSocketAddress(ip, port);
				
				ftpClient.connect(addr);
				ftpClient.login(username, password.toCharArray());
				
				if (!"".equals(path)||path.length() != 0) {
					// 把远程系统上的目录切换到参数path所指定的目录
					ftpClient.changeDirectory(path);
				}
				ftpClient.setBinaryType();
				return true;
			} catch (Exception e) {
				System.out.println("FTP"+ip+"连接发生异常！");
				return false;
			}
	}

	/**
	 * 断开与ftp服务器连接
	 * 
	 * @throws IOException
	 */
	public boolean closeServer() {
		try {
			ftpClient.close();
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 检查文件夹在当前目录下是否存在
	 * 
	 * @param dir
	 * @return
	 */
	private boolean isDirExist(String dir) {
		 try {
	            ftpClient.changeDirectory(dir);
	        } catch (Exception e) {
	            return false;
	        }
	        return true;
	}

	/**
	 * 在当前目录下创建文件夹
	 * 
	 * @param dir
	 * @return
	 * @throws IOException 
	 * @throws FtpProtocolException 
	 * @throws Exception
	 */
	private boolean createDir(String dir) throws FtpProtocolException, IOException {
		 ftpClient.setAsciiType();
	        StringTokenizer s = new StringTokenizer(dir, "/"); // sign
	        s.countTokens();
	        String pathName = "";
	        while (s.hasMoreElements()) {
	            pathName = pathName + "/" + (String) s.nextElement();
	            try {
	                ftpClient.makeDirectory(pathName);
	            } catch (Exception e) {
	                e = null;
	                return false;
	            }
	        }
	        ftpClient.setBinaryType();
	        return true;
	}

	/**
	 * ftp上传 如果服务器段已存在名为filename的文件夹，该文件夹中与要上传的文件夹中同名的文件将被替换
	 * 
	 * @param filename
	 *            要上传的文件（或文件夹）名
	 * @return
	 * @throws Exception 
	 */
	public boolean upload(String filename) throws Exception {
		String newname = "";
		if (filename.indexOf("/") > -1) {
			newname = filename.substring(filename.lastIndexOf("/") + 1);
		} else {
			newname = filename;
		}
		return upload(filename, newname);
	}

	/**
	 * ftp上传 如果服务器段已存在名为newName的文件夹，该文件夹中与要上传的文件夹中同名的文件将被替换
	 * 
	 * @param fileName
	 *            要上传的文件（或文件夹）名
	 * @param newName
	 *            服务器段要生成的文件（或文件夹）名
	 * @return
	 * @throws Exception 
	 */
	public boolean upload(String fileName, String newName) throws Exception {
		try {

			File file_in = new File(fileName);// 打开本地待长传的文件
			if (!file_in.exists()) {
				throw new Exception("此文件或文件夹[" + file_in.getName() + "]有误或不存在!");
			}
			if (file_in.isDirectory()) {
				upload(file_in.getPath(), newName, "");
			} else {
				uploadFile(file_in.getPath(), newName);
			}

			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 真正用于上传的方法
	 * 
	 * @param fileName
	 * @param newName
	 * @param path
	 * @throws Exception
	 */
	private void upload(String fileName, String newName, String path)
			throws Exception {
		File file_in = new File(fileName);// 打开本地待长传的文件
		if (!file_in.exists()) {
			throw new Exception("此文件或文件夹[" + file_in.getName() + "]有误或不存在!");
		}
		if (file_in.isDirectory()) {
			if (!isDirExist(newName)) {
				createDir(newName);
			}
			File sourceFile[] = file_in.listFiles();
			for (int i = 0; i < sourceFile.length; i++) {
				if (!sourceFile[i].exists()) {
					continue;
				}
				if (sourceFile[i].isDirectory()) {
					this.upload(sourceFile[i].getPath(), sourceFile[i]
							.getName(), path + "/" + newName);
				} else {
					this.uploadFile(sourceFile[i].getPath(), sourceFile[i]
							.getName());
				}
			}
		} else {
			uploadFile(file_in.getPath(), newName);
		}
	}

	/**
	 * upload 上传文件
	 * 
	 * @param filename
	 *            要上传的文件名
	 * @param newname
	 *            上传后的新文件名
	 * @return -1 文件不存在 >=0 成功上传，返回文件的大小
	 * @throws Exception
	 */
	public long uploadFile(String localFile, String remoteFile) throws Exception {
		long result = 0;
		OutputStream os = null;
		FileInputStream is = null;
		try {
			// 将远程文件加入输出流中
			try {
				os = ftpClient.putFileStream(remoteFile);
			} catch (FtpProtocolException e) {
				e.printStackTrace();
			} // 获取本地文件的输入流
			File file_in = new File(localFile);
			is = new FileInputStream(file_in);
			// 创建一个缓冲区
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
				result = result + c ;
			}
			System.out.println("upload success");
		} catch (IOException ex) {
			System.out.println("not upload");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 从ftp下载文件到本地
	 * 
	 * @param filename
	 *            服务器上的文件名
	 * @param newfilename
	 *            本地生成的文件名
	 * @return
	 * @throws Exception
	 */
	public long downloadFile(String remoteFile, String localFile) {
		long result = 0;
		//TODO 这个方法有BUG
				InputStream is = null;
				FileOutputStream os = null;
				try {
					// 获取远程机器上的文件filename，借助TelnetInputStream把该文件传送到本地。
					try {
						is = ftpClient.getFileStream(remoteFile);
					} catch (FtpProtocolException e) {
						e.printStackTrace();
					}
					File file_in = new File(localFile);
					os = new FileOutputStream(file_in);
					byte[] bytes = new byte[1024];
					int c;
					while ((c = is.read(bytes)) != -1) {
						os.write(bytes, 0, c);
						result = result + c ;
					}
					System.out.println("download success");
				} catch (IOException ex) {
					System.out.println("not download");
					ex.printStackTrace();
					throw new RuntimeException(ex);
				} finally {
					try {
						if (is != null) {
							is.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							if (os != null) {
								os.flush();
								os.close();
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		return result;
	}
	
	/**
	 * 切换目录
	 * @param ftp
	 * @param path
	 */
	public static void changeDirectory(FtpClient ftp,String path) {
		try {
			ftp.changeDirectory(path);
			System.out.println(ftp.getWorkingDirectory());
		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean createDirPath(String path) throws FtpProtocolException, IOException {
		return this.createDir(path);
	}
	
	
	public static void main(String agrs[]) throws Exception {
//		FtpUtil fu = new FtpUtil("10.173.11.194", 21, "xiangxun", "88151312");
//		String fuwuqiaddress = "/ftp.txt";
//		String bendiaddress = "D:/tianbo/tianbo.txt";
//		// 下载
//		fu.upload(bendiaddress,fuwuqiaddress);
//		fu.closeServer();
	}

}
