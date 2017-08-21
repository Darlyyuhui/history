/**
 * 
 */
package com.xiangxun.atms.common.system.service.impl;

import java.text.DecimalFormat;
import java.util.Properties;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarNotImplementedException;
import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.system.service.SystemLoadService;

/**
 * 系统参数获取接口实现类
 * @author kouyunhao
 * @version 1.0
 * May 26, 2014
 */
@Service("systemLoadService")
public class SystemLoadServiceImpl implements SystemLoadService {
	
	/**
	 * 创建sigar对象用来获取系统信息  
	 */
	 private Sigar sigar = new Sigar(); 
	 private CpuPerc cpuCerc; 
	 private Properties props = System.getProperties(); 
	 private Mem mem;

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.system.service.SystemLoadIF#getCpuInfo()
	 */
	@Override
	public String getCpuInfo() {
		 try {   
			 /**
			  * 获取cpu信息 
			  */
			 cpuCerc = sigar.getCpuPerc();   
			 /**
			  * 保留两位小数
			  */
			 DecimalFormat df = new DecimalFormat("#.00");   
			 /**
			  * 如果cpu使用率为零时，不保留小数位
			  */
			 if (cpuCerc.getCombined() == 0) { 
				 return 0 + "%";  
				 } else {    
					 return df.format(cpuCerc.getCombined() * 100) + "%";   
				 } 
			 } catch (SigarException e) { 
				 e.printStackTrace(); 
			 }
			 System.out.println("当前CPU信息：" + cpuCerc.getCombined());
			 return String.valueOf(cpuCerc.getCombined()); 
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.system.service.SystemLoadIF#getNetworkInfo()
	 */
	@Override
	public String getNetworkInfo() {
		String ifNames[]=null;  
		String txbyte=null;  
		try { 
			ifNames = sigar.getNetInterfaceList();  
		} catch (SigarException e2) {   
			e2.printStackTrace();  
		}  
		String name = ifNames[0];   
		try {    
			/**
			 * 获取网络流量
			 */
			NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);    
			/**
			 * 获取发送的总字节数
			 */
			txbyte=ifstat.getTxBytes()+"byte";   
		} catch (SigarNotImplementedException e) {   
			
		} catch (SigarException e) {    
			System.out.println(e.getMessage());   
		}  
		System.out.println("当前网络流量信息：" + txbyte);
		return txbyte; 
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.system.service.SystemLoadIF#getRamInfo()
	 */
	@Override
	public String getRamInfo() {
		try {   
			/**
			 * 获取系统内存信息
			 */
			mem = sigar.getMem();  
			} 
		catch (SigarException e) {   
			e.printStackTrace();  
		}
		System.out.println("当前系统内存使用量：" + mem.getUsed() / 1024 / 1024 + "M");
		/**
		 * 返回系统内存使用量
		 */
		return mem.getUsed() / 1024 / 1024 + "M"; 
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.system.service.SystemLoadIF#getRunLoadInfo()
	 */
	@Override
	public String getRunLoadInfo() {
		/**
		 * 获取运行环境信息
		 */
		String runload=props.getProperty("java.vm.name");  
		System.out.println("当前系统运行环境：" + runload);
		return runload;
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.system.service.SystemLoadIF#getSystemInfo()
	 */
	@Override
	public String getSystemInfo() {
		/**
		 * 获取操作系统名称
		 */
		System.out.println("当前操作系统名称：" + props.getProperty("os.name"));
		return props.getProperty("os.name"); 
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.system.service.SystemLoadIF#getVmRamInfo()
	 */
	@Override
	public String getVmRamInfo() {
		/**
		 * 获取虚拟机剩余内存
		 */
		System.out.println("当前虚拟机剩余内存：" + Runtime.getRuntime().freeMemory() / (1024 * 1024) + "M");
		return Runtime.getRuntime().freeMemory() / (1024 * 1024) + "M";
	}

}
