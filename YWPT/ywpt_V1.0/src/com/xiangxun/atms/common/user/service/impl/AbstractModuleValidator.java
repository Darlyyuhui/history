package com.xiangxun.atms.common.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiangxun.atms.common.system.service.ParamsService;
import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.common.user.cache.AuthorityMenuCache;
import com.xiangxun.atms.common.user.service.ModuleValidator;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.resource.MessageResources;

/**
 * <p>卡口模块验证器</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public abstract class AbstractModuleValidator implements ModuleValidator{
	
	protected String type;
	
	@Resource
	Cache cache;
	
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.ModuleValidator#isDeviceCode(java.lang.String, boolean)
	 */
	@Override
	public boolean isDeviceCode(String menuid, boolean status) {
		if(!status){
			String[] modules = getModuleCodes();
			for (String string : modules) {
				if(getType().equals(string)){
					Map<String,List<SystemResource>> map = getModuleMap();
					if(map != null){
						List<SystemResource> menus = map.get(getType());
						for (SystemResource systemResource : menus) {
							if(systemResource.getId().equals(menuid)){
								status = true;
								break;
							}
						}
						break;
					}
				}
			}
		}
		return status;
	}
	
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.ModuleValidator#isEnable(String menuid)
	 */
	@Override
	public boolean isEnable(String menuid) {
		boolean enabled = false;
		if(enable()) enabled = true;
		return enabled;
	}

	/***
	 * 获取模块类型
	 * @return
	 */
	protected String[] getModuleCodes(){
		MessageResources resource = MessageResources.getMessageInstance(null,null);
		String[] menuCodes = resource.getMessage("sys.module.code").split(",");
		return menuCodes;
	}
	
	/***
	 * 获取模块类型对应的菜单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Map<String,List<SystemResource>> getModuleMap() {
		return (Map<String, List<SystemResource>>) cache.get(AuthorityMenuCache.AUTHORITY_MENUS);
	}
	
	/***
	 * 违法数据权限是否开启
	 * @return
	 */
	protected boolean vioEnable() {
		return "1".equals(ParamsService.SYSTEM_PARAMS.get("isVio"));
	}
	
	/***
	 * 监控数据权限是否开启
	 * @return
	 */
	protected boolean videoEnable() {
		return "1".equals(ParamsService.SYSTEM_PARAMS.get("isVideo"));
	}
	
	
	/***
	 * 流量数据权限是否开启
	 * @return
	 */
	protected boolean countAcrossEnable() {
		return "1".equals(ParamsService.SYSTEM_PARAMS.get("isCount"));
	}
	
	
	/***
	 * 卡口数据权限是否开启
	 * @return
	 */
	protected boolean crossEnable() {
		return "1".equals(ParamsService.SYSTEM_PARAMS.get("isCross"));
	}
	

	//子类实现具体的业务模块编码
	protected abstract String getType();
	
	//子类实现判断具体的数据权限是否开启
	protected abstract boolean enable();
	
}
