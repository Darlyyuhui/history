package com.xiangxun.atms.common.user.dao;

import java.util.List;

import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.user.vo.UserAuthority;

/***
 * 用户数据权限映射器
 * @author zhouhaij
 * @May 3, 2013 4:52:37 PM
 */
public interface UserAuthorityMapper {

	/***
	 * 保存
	 * @param userAuthority
	 */
	public void save(UserAuthority userAuthority);
	
	/***
	 * 根据用户id和菜单id获取对应的部门权限,返回null是表示拥有整个组织机构的权限
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List<String> getDeptIdByUserAndMenu(String userid,String menuid);
	
	/***
	 * 根据用户id获取对应的菜单和部门权限,返回null是表示拥有整个组织机构的权限
	 * @param userid
	 * @return
	 */
	public List<UserAuthority> getDeptIdByUserId(String userid);
	
	/**
	 * 获取用户的部门
	 */
	public List<Department> getDeptartMentsByUserAndMenu(String userid,String menuid);
	
	/***
	 * 根据userid删除对应的数据权限
	 * @param userid
	 */
	public void deleteByUserid(String userid);
	
	/***
	 * 根据用户id获取对应的所有menuid
	 * @param userid
	 * @return
	 */
	public List<String> getMenusByUserId(String userid);
}
