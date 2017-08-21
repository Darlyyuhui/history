package com.xiangxun.atms.framework.cache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.ObjectExistsException;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;
import net.sf.ehcache.config.DiskStoreConfiguration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

/*******************************************************************************
 * 主要对spring提供的EhCacheManagerFactoryBean进行扩展，可以基于Web根目录指定diskStore地址。
 * 
 * @since 1.0
 * @author zhouhaij
 */
public class CacheManagerFactoryBean implements FactoryBean<CacheManager>, InitializingBean, DisposableBean {

	protected Log logger = LogFactory.getLog(CacheManagerFactoryBean.class);

	private Resource configLocation;
	private String diskStoreLocation;
	private String cacheManagerName;
	private CacheManager cacheManager;

	/**
	 * 设置EHCache配置文件
	 * 
	 * @param configLocation
	 *            the configLocation to set
	 * @see net.sf.ehcache.CacheManager#create(java.io.InputStream)
	 * @see net.sf.ehcache.CacheManager#CacheManager(java.io.InputStream)
	 */
	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	/**
	 * 设置ehcahce的diskStore path，例如：/WEB-INF/cache 如设置了此项，则在ehcache配置文件中不要配置<diskStore
	 * path=""/>，否则本设置无效。
	 * 
	 * @param diskStoreLocation
	 */
	public void setDiskStoreLocation(String diskStoreLocation) {
		this.diskStoreLocation = diskStoreLocation;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("Initializing EHCache CacheManager");
		Configuration config = null;
		if (this.configLocation != null) {
			config = ConfigurationFactory.parseConfiguration(this.configLocation.getInputStream());
			if (this.diskStoreLocation != null) {
				DiskStoreConfiguration dc = new DiskStoreConfiguration();
				String path = ResourceUtils.getFile(this.diskStoreLocation).getAbsolutePath();
				logger.info("cache path :" + path);
				dc.setPath(path);
				try {
					config.addDiskStore(dc);
				} catch (ObjectExistsException e) {
					logger.warn("if you want to config distStore in spring," + " please remove diskStore in config file!", e);
				}
			}
		}
		if (config != null) {
			this.cacheManager = new CacheManager(config);
		} else {
			this.cacheManager = new CacheManager();
		}
		if (this.cacheManagerName != null) {
			this.cacheManager.setName(this.cacheManagerName);
		}

	}

	@Override
	public CacheManager getObject() throws Exception {
		return this.cacheManager;
	}

	@Override
	public Class<?> getObjectType() {
		return (this.cacheManager != null ? this.cacheManager.getClass() : CacheManager.class);
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void destroy() throws Exception {
		logger.info("Shutting down EHCache CacheManager");
		this.cacheManager.shutdown();
	}

}
