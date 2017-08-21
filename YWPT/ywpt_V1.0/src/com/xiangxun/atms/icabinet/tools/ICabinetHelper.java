package com.xiangxun.atms.icabinet.tools;

import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ICabinetHelper {
	private DataSource ds;
	private ThreadPoolExecutor service;
	private Logger logger = LoggerFactory.getLogger(ICabinetHelper.class);

	public ICabinetHelper(DataSource ds) {
		this.ds = ds;
		service = (ThreadPoolExecutor) Executors.newFixedThreadPool(10); // 最大并行处理10台设备
	}

	/**
	 * 开始执行下发
	 * 
	 * @param options 机柜参数列表
	 * 
	 * @return 本次执行的执行批号,null表示没有执行（IP重复,没有机柜参数）
	 */
	public String start(ICabinetOption[] options) {
		if (options == null || options.length == 0)
			return null;// 没有参数
		HashSet<String> ips = new HashSet<String>();
		for (ICabinetOption op : options) {
			if (ips.contains(op.getIp())) {
				logger.error("智能机柜IP重复，不进行下发");
				return null;// ip重复
			}
			ips.add(op.getIp());
		}
		return new ICabinetOptionner(service, ds, options).start();
	}

}
