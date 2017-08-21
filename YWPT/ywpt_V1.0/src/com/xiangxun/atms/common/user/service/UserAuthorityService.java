package com.xiangxun.atms.common.user.service;

import java.util.List;

import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.user.vo.UserAuthority;
/***
 * 用户数据权限接口
 * @author zhouhaij
 */
public interface UserAuthorityService {

	/***
	 * 保存
	 * @param userAuthority
	 */
	public void save(String userid,List<UserAuthority> userAuthority);
	
	/***
	 * 根据用户id和菜单id获取对应的部门权限,返回null是表示拥有整个组织机构的权限
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List<String> getDeptIdByUserAndMenu(String userid,String menuid);
	
	/***
	 * 根据用户id获取菜单和部门权限,返回null是表示拥有整个组织机构的权限
	 * @param userid
	 * @return
	 */
	public List<UserAuthority> getDeptIdByUserId(String userid);
	
	/***
	 * 根据用户id和菜单id获取对应的部门,返回null是表示拥有整个组织机构的权限
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List<Department> getDeptartMentsByUserAndMenu(String userid,String menuid);
	
	/***
	 * 根据用户id获取该用户的所有菜单id
	 * @param userid
	 * @return
	 */
	public List<String> getMenuIdsByUserId(String userid);
}
