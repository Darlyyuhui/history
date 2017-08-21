/**
 * 
 */
package com.xiangxun.atms.common.system.service;

/**
 * 系统参数获取接口
 * @author kouyunhao
 * @version 1.0
 * May 26, 2014
 */
public interface SystemLoadService {
	
	//获取cpu使用率 
	public String getCpuInfo(); 
	//获取系统内存使用量 
	public String getRamInfo(); 
	//获取网络使用流量 
	public String getNetworkInfo(); 
	//获取操作系统信息 
	public String getSystemInfo(); 
	// 获取运行环境信息 
	public String getRunLoadInfo(); 
	//获取虚拟机剩余内存 
	public String getVmRamInfo();

}
