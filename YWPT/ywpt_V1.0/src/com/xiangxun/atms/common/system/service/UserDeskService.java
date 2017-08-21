package com.xiangxun.atms.common.system.service;

import java.util.List;

import com.xiangxun.atms.common.system.vo.UserDesk;
import com.xiangxun.atms.common.system.vo.UserDeskSearch;
import com.xiangxun.atms.framework.base.BaseService;

/**
 * 用户自定义桌面接口
 * @author kouyunhao
 * @version 1.0
 * 2013-6-28
 */
public interface UserDeskService extends BaseService<UserDesk,UserDeskSearch>{

	public String USER_DESK_CACHE ="user_desk_cache";
	
	/***
	 * 获取当前用户所有桌面
	 * @param keyword
	 * @return
	 */
	public List<UserDesk> findAll();
	
	/***
	 * 根据角色id和资源id查找是否有记录
	 * @param roleid
	 * @return
	 */
	public boolean hasResourceByRoleId(String roleid,String resid);
	
	/***
	 * 刷新缓存
	 */
	public void refreshCache();
	
	public List<UserDesk> findListByUserIdAndScreennum(String userId, String screennum);
	
	public List<UserDesk> findListByUserId(String userId);
}
