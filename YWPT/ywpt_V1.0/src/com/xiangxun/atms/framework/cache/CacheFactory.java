package com.xiangxun.atms.framework.cache;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

/**
 * <p>
 * 类说明：缓存工厂，对注入进来且已实现BaseCache的Object进行初始化
 * </p>
 * @author zhouhaij
 * @version 1.0
 */
public class CacheFactory {
	/**
	 * 日志 logger
	 */
	public static Logger log = Logger.getLogger(CacheFactory.class);
	/**
	 * 缓存组件是否已被初始化,true：已初始化,false:未被初始化
	 */
	private boolean cacheInitialized = false;
	/**
	 * 需要初始化的组件对象
	 */
	private List<Object> items;

	/**
	 * <p>
	 * 方法说明：初始化
	 * </p>
	 */
	@PostConstruct
	public void initCache() {

		if (log.isDebugEnabled()) {
			log.info("启动数据缓存服务...");
			log.info("缓存前内存情况（free/total）:"
					+ String.valueOf(getMemorySize("free")) + " M/ "
					+ String.valueOf(getMemorySize("total")) + " M");
		}

		initItems();

		if (log.isDebugEnabled()) {
			log.info("缓存后内存情况（free/total）:"
					+ String.valueOf(getMemorySize("free")) + " M/ "
					+ String.valueOf(getMemorySize("total")) + " M");
			log.info("数据缓存服务执行结束");
		}
	}

	/**
	 * <p>
	 * 方法说明：对每个组件进行初始化
	 * </p>
	 */
	private void initItems() {
		if (this.getItems() == null || this.getItems().size() == 0) {
			return;
		}
		try {
			for (int i = 0; i < this.getItems().size(); i++) {
				if (!(items.get(i) instanceof BaseCache)) {
					throw new Exception("缓存对象必须是BaseCache接口的实现");
				}
				BaseCache baseCache = (BaseCache) this.getItems().get(i);
				baseCache.init();
			}
			
			cacheInitialized = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取当前系统的内存大小
	 * 
	 * @param mode
	 * @return
	 */
	public long getMemorySize(String mode) {
		if ("total".equals(mode)) {
			return Runtime.getRuntime().totalMemory() / (1024 * 1024);
		} else if ("free".equals(mode)) {
			return Runtime.getRuntime().freeMemory() / (1024 * 1024);
		}
		return 0;
	}

	/**
	 * <p>
	 * 方法说明：缓存工厂是否在系统
	 * </p>
	 * 
	 * @return true:已初始化,false:未初始化
	 */
	public boolean isCacheInitialized() {
		return cacheInitialized;
	}

	public List<Object> getItems() {
		return items;
	}

	public void setItems(List<Object> items) {
		this.items = items;
	}

}
