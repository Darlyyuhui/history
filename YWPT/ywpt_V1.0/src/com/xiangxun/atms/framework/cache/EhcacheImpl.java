package com.xiangxun.atms.framework.cache;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 本地缓存策略,使用EhCache, 支持限制总数, Idle time/LRU失效,持久化到磁盘等功能.
 * @author zhouhaijian
 * @version 1.0
 */
public  class EhcacheImpl implements Cache{
	protected Log logger = LogFactory.getLog(EhcacheImpl.class);
	private net.sf.ehcache.Cache cache;
	
	//默认的cache
	private String cacheKey ="dicCache";
	
	@Resource
	CacheManager ehcacheManager;
	
	public EhcacheImpl(){
	}
	
	@PostConstruct
	public void init(){
		cache = ehcacheManager.getCache(cacheKey);
	}

	/***
	 * 获取缓存对象
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		Element element = cache.get(key);
		if (element == null) {
			return null;
		}
		return element.getObjectValue();
	}

	/***
	 * 设置缓存对象
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		logger.debug("正在缓存数据......");
		logger.debug("key:"+key);
		Element element = new Element(key, value);
		try{
			cache.put(element);
		}catch (net.sf.ehcache.CacheException e) {
			removeCacheByKey(key);
			cache.put(element);
		}
	}

	/***
	 * 删除缓存对象
	 * @param key
	 */
	public void removeCacheByKey(String key) {
		cache.remove(key);
	}

	/***
	 * 删除所有缓存内容
	 */
	public void removeAll() {
		cache.removeAll();
	}

	public void flush() {
		cache.flush();
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
		cache = ehcacheManager.getCache(cacheKey);
	}
	
	/**
	 * @return the cacheKey
	 */
	public String getCacheKey() {
		return cacheKey;
	}
	
}
