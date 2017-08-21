package com.xiangxun.atms.framework.cache;
/**
 * <p>类说明：缓存基础接口</p>
 * @author zhouhaij
 * @version 1.0
 */
public interface BaseCache {
	
	String DIC_CACHE="dicCache";
	
	String ANALYZE_CACHE= "analyzeCache";
	
	/**
	 * 
	 * <p>方法说明：初始化</p>
	 * @throws Exception
	 */
	public void init()throws Exception;
	
	/***
	 * 这是缓存的key值
	 * @return
	 */
	public String getCacheKey();

}
