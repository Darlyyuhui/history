package com.xiangxun.atms.common.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.user.cache.AuthorityMenuCache;
import com.xiangxun.atms.common.user.dao.UserAuthorityMapper;
import com.xiangxun.atms.common.user.service.UserAuthorityService;
import com.xiangxun.atms.common.user.vo.UserAuthority;

/***
 * 用户数据权限接口实现类
 * @author zhouhaij
 * @May 3, 2013 5:05:39 PM
 */
@Service
public class UserAuthorityServiceImpl implements UserAuthorityService {

	@Resource
	UserAuthorityMapper userAuthorityMapper;
	
	@Resource(name="authorityMenuCache")
	AuthorityMenuCache authorityMenuCache;
	
	@Override
	public List<String> getDeptIdByUserAndMenu(String userid, String menuid) {
		return userAuthorityMapper.getDeptIdByUserAndMenu(userid, menuid);
	}

	@Transactional
	@Override
	public void save(String userid,List<UserAuthority> userAuthoritys) {
		userAuthorityMapper.deleteByUserid(userid);
		for (UserAuthority userAuthority : userAuthoritys) {
			userAuthorityMapper.save(userAuthority);
		}
		//刷新数据权限的缓存
		try {
			authorityMenuCache.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.UserAuthorityService#getDeptartMentsByUserAndMenu(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Department> getDeptartMentsByUserAndMenu(String userid, String menuid) {
		return userAuthorityMapper.getDeptartMentsByUserAndMenu(userid, menuid);
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.UserAuthorityService#getDeptIdByUserId(java.lang.String)
	 */
	@Override
	public List<UserAuthority> getDeptIdByUserId(String userid) {
		return userAuthorityMapper.getDeptIdByUserId(userid);
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.user.service.UserAuthorityService#getMenuIdsByUserId(java.lang.String)
	 */
	@Override
	public List<String> getMenuIdsByUserId(String userid) {
		return userAuthorityMapper.getMenusByUserId(userid);
		
	}
	
	
	

}
