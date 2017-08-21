package com.xiangxun.atms.common.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.xiangxun.atms.common.system.dao.SystemResourceTempMapper;
import com.xiangxun.atms.common.system.service.ResourceService;
import com.xiangxun.atms.common.system.service.ResourceTempService;
import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.common.system.vo.SystemResourceSearch;
import com.xiangxun.atms.common.system.vo.SystemResourceTemp;
import com.xiangxun.atms.common.user.service.MenuCacheService;
import com.xiangxun.atms.common.user.type.MenuType;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
@Service("resourceTempService")
public class ResourceTempServiceImpl extends AbstractBaseService<SystemResourceTemp,SystemResourceSearch> implements ResourceTempService {

		@Resource
		SystemResourceTempMapper systemResourceTempMapper;
		
		@Resource
		MenuCacheService menuCacheService;
		
		/* (non-Javadoc)
		 * @see com.xiangxun.atms.common.system.service.ResourceService#grantRoleResource(java.lang.String, java.lang.String)
		 */
		@Override
		@Transactional
		public void grantRoleResource(String roleid, String... resid) {
			systemResourceTempMapper.deleteResourceByRoleId(roleid);
			for (String string : resid) {
				systemResourceTempMapper.addRoleResource(roleid, string);
			}
			menuCacheService.refreshCache(roleid);
		}

		/* (non-Javadoc)
		 * @see com.xiangxun.atms.common.system.service.ResourceService#getChildren(java.lang.String)
		 */
		@Override
		public List<SystemResourceTemp> getChildren(String menuid,MenuType type) {
			SystemResourceSearch search = new SystemResourceSearch();
			search.createCriteria().andParentidEqualTo(menuid).andDisabledEqualTo(false).andTypeEqualTo(type.toString());
			return systemResourceTempMapper.selectByExample(search);
		}

		/* (non-Javadoc)
		 * @see com.xiangxun.atms.common.system.service.ResourceService#hasChild(java.lang.String)
		 */
		@Override
		public boolean hasChild(String menuid,MenuType type) {
			SystemResourceSearch search = new SystemResourceSearch();
			search.createCriteria().andParentidEqualTo(menuid).andDisabledEqualTo(false).andTypeEqualTo(type.toString());
			int result = systemResourceTempMapper.countByExample(search);
			return result>0;
		}

		/* (non-Javadoc)
		 * @see com.xiangxun.atms.framework.base.AbstractBaseService#getBaseMapper()
		 */
		protected BaseMapper<SystemResourceTemp,SystemResourceSearch> getBaseMapper() {
			return systemResourceTempMapper;
		}

		/* (non-Javadoc)
		 * @see com.xiangxun.atms.common.user.service.ResourceService#findAll(boolean disabled)
		 */
		@Override
		public List<SystemResourceTemp> findAll(boolean disabled) {
			SystemResourceSearch search = new SystemResourceSearch();
			search.setOrderByClause("SORT_ORDER asc");
			search.createCriteria().andCodeIsNotNull().andDisabledEqualTo(disabled);
			return systemResourceTempMapper.selectByExample(search);
		}
		

		/* (non-Javadoc)
		 * @see com.xiangxun.atms.common.system.service.ResourceService#findAllMenuByKeyWord(String)
		 */
		@Override
		public List<SystemResourceTemp> findAllMenuByKeyWord(String keyWord,String exinclude){
			SystemResourceSearch search = new SystemResourceSearch();
			search.setOrderByClause("SORT_ORDER asc");
			search.createCriteria()
			.andCodeIsNotNull()
			.andDisabledEqualTo(false)
			.andNameLike("%"+keyWord+"%")
			.andNameNotIn(Arrays.asList(exinclude.split(",")))
			.andTypeEqualTo(MenuType.MODULE.toString());
			List<SystemResourceTemp> res = systemResourceTempMapper.selectByExample(search);
			//添加节点的孩子节点
			List<SystemResourceTemp> resultList = new ArrayList<SystemResourceTemp>();
			for (SystemResourceTemp systemResource : res) {
				getChildResource(res,resultList,systemResource);
			}
			
			String[] exincludeMenus = exinclude.split(",");
			
			for (Iterator<SystemResourceTemp> iterator = resultList.iterator(); iterator.hasNext();) {
				SystemResourceTemp systemResource = (SystemResourceTemp) iterator.next();
				for (int i = 0; i < exincludeMenus.length; i++) {
					if(systemResource.getName().equals(exincludeMenus[i])){
						iterator.remove();
					}
				}
			}
			
			res.addAll(resultList);
			return res;
		}

		/***
		 * 递归查找子节点
		 * @param res
		 * @param resultList
		 * @param systemResource
		 */
		private void getChildResource(List<SystemResourceTemp> res,List<SystemResourceTemp> resultList,SystemResourceTemp systemResource){
			String id = systemResource.getId();
			List<SystemResourceTemp> children = getChildren(id,MenuType.MODULE);
			for (SystemResourceTemp resource : children) {
				if(!res.contains(resource) && !resultList.contains(resource)){
					if(resource.getType().equals(MenuType.MODULE.toString())){
						resultList.add(resource);
					}
				}
				getChildResource(res,resultList,resource);
			}
			
		}
		
		/* (non-Javadoc)
		 * @see com.xiangxun.atms.common.user.service.ResourceService#getDeskTopMenuByuserId(java.lang.String)
		 */
		@Override
		public List<SystemResourceTemp> getDeskTopMenuByuserId(String userId) {
			return systemResourceTempMapper.getDeskTopMenuByuserId(userId);
		}

		/* (non-Javadoc)
		 * @see com.xiangxun.atms.common.user.service.ResourceService#getLeafMenusByUserId(java.lang.String)
		 */
		@Override
		public List<SystemResourceTemp> getLeafMenusByUserId(String userId) {
			return systemResourceTempMapper.getLeafMenusByUserId(userId);
		}

		/* (non-Javadoc)
		 * @see com.xiangxun.atms.common.user.service.ResourceService#getMenuButtonsByParentId(java.lang.String, java.lang.String)
		 */
		@Override
		public List<String> getMenuButtonsByParentId(String userId, String menuId) {
			return systemResourceTempMapper.getMenuButtonsByParentId(userId, menuId);
		}

		/* (non-Javadoc)
		 * @see com.xiangxun.atms.common.user.service.ResourceService#getMenuButtonsByUserId(java.lang.String)
		 */
		@Override
		public List<SystemResourceTemp> getMenuButtonsByUserId(String userId) {
			return systemResourceTempMapper.getMenusByUserId(userId,MenuType.BUTTON.toString());
		}

		/* (non-Javadoc)
		 * @see com.xiangxun.atms.common.user.service.ResourceService#getMenusByUserId(java.lang.String, com.xiangxun.atms.common.user.type.MenuType)
		 */
		@Override
		public List<SystemResourceTemp> getMenusByUserId(String userId,MenuType menuType) {
			return systemResourceTempMapper.getMenusByUserId(userId,menuType==null?"":menuType.toString());
		}
		@Override
		public List<SystemResourceTemp> getAllMenusByUserId(String userId) {
			// TODO Auto-generated method stub
			return systemResourceTempMapper.getAllMenusByUserId(userId);
		}
		/* (non-Javadoc)
		 * @see com.xiangxun.atms.common.user.service.ResourceService#getMenusByUserId(java.lang.String, java.lang.String, com.xiangxun.atms.common.user.type.MenuType)
		 */
		@Override
		public List<SystemResourceTemp> getChildMenusByUserId(String userId,String parentId, MenuType menuType) {
			return systemResourceTempMapper.getChildMenusByUserId(userId, parentId, menuType.toString());
		}
		

		/* (non-Javadoc)
		 * @see com.xiangxun.atms.common.system.service.ResourceService#hasResourceByRoleId(java.lang.String, java.lang.String)
		 */
		@Override
		public boolean hasResourceByRoleId(String roleid, String resid) {
			return systemResourceTempMapper.getRecordByResIdAndRoleId(roleid, resid)>0;
		}

		
		
		/* (non-Javadoc)
		 * @see com.xiangxun.atms.framework.base.AbstractBaseService#deleteById(java.lang.String)
		 */
		@Override
		public int deleteById(String id) {
			List<SystemResourceTemp> btns = getChildren(id,MenuType.BUTTON);
			
			List<SystemResourceTemp> menus = new ArrayList<SystemResourceTemp>();
			
			List<SystemResourceTemp> children = getChildren(id,MenuType.MODULE);
			
			getAllChildResource(children,menus);
			getButtonsByMenuid(children,btns);
			
			children.addAll(btns);
			children.addAll(menus);
			
			List<String> ids = new ArrayList<String>();
			for (SystemResourceTemp res : children) {
				ids.add(res.getId());
			}
			
			ids.add(id);
			
			SystemResourceSearch search = new SystemResourceSearch();
			search.createCriteria().andIdIn(ids);
			return deleteByExample(search);
		}

		/**
		 * 递归查找所有的子菜单
		 * @param children
		 * @param menus
		 */
		private void getAllChildResource(List<SystemResourceTemp> children,List<SystemResourceTemp> menus){
			for (SystemResourceTemp systemResource : children) {
				List<SystemResourceTemp> chs = getChildren(systemResource.getId(),MenuType.MODULE);
				menus.addAll(chs);
				getAllChildResource(chs, menus);
			}
		}
		
		/***
		 * 递归查找按钮
		 * @param children
		 * @param btns
		 */
		private void getButtonsByMenuid(List<SystemResourceTemp> children,List<SystemResourceTemp> btns){
			for (SystemResourceTemp systemResource : children) {
				List<SystemResourceTemp> childrens = getChildren(systemResource.getId(),MenuType.BUTTON);
				if(childrens!=null && !childrens.isEmpty()){
					btns.addAll(childrens);
				}else{
					getButtonsByMenuid(getChildren(systemResource.getId(),MenuType.MODULE),btns);
				}
			}
		}
		
		/* (non-Javadoc)
		 * @see com.xiangxun.atms.framework.base.AbstractBaseService#save(java.lang.Object)
		 */
		@Override
		public int save(SystemResourceTemp record) {
			record.setId(UuidGenerateUtil.getUUIDLong());
			record.setDisabled(false);
			record.setCreateBy(SpringSecurityUtils.getCurrentUserName());
			record.setUpdateBy(SpringSecurityUtils.getCurrentUserName());
			record.setCreateDate(new Date());
			record.setStatus("0");
			return systemResourceTempMapper.insertSelective(record);
		}

		@Override
		public List<SystemResourceTemp> getChildMenusByUserIdAndParentId(String userId,
				String parentId) {
			return systemResourceTempMapper.getChildMenusByUserIdAndParentId(userId, parentId);
		}

		

		
		
	}