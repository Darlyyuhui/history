/**
 * 
 */
package com.xiangxun.atms.framework.cache;

/**
 * 缓存的上级接口
 * @author zhouhaij
 */
public interface Cache {

	/***
	 * 获得缓存对象
	 * @param key
	 * @param value
	 */
	public Object get(String key);
	

	/***
	 * 设置缓存对象
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value);
	
	
	/***
	 * 删除缓存对象
	 * @param key
	 */
	public void removeCacheByKey(String key);
	
	/***
	 * 设置cache的key
	 * @param cacheKey
	 */
	public void setCacheKey(String cacheKey);

	/***
	 * 删除所有缓存内容
	 */
	public void removeAll();
	
	
	public void flush();
	
}
