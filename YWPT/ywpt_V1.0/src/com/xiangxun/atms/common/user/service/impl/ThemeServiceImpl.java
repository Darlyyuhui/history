/**
 * 
 */
package com.xiangxun.atms.common.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.user.dao.ThemeMapper;
import com.xiangxun.atms.common.user.service.ThemeService;
import com.xiangxun.atms.common.user.vo.Theme;
import com.xiangxun.atms.common.user.vo.ThemeSearch;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;

/**
 * 主题管理业务逻辑接口实现类
 * @author kouyunhao
 * @version 1.0
 * May 13, 2014
 */
@Service("themeService")
public class ThemeServiceImpl extends AbstractBaseService<Theme, ThemeSearch> implements ThemeService {
	
	@Resource
	ThemeMapper themeMapper;

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.framework.base.AbstractBaseService#getBaseMapper()
	 */
	@Override
	protected BaseMapper<Theme, ThemeSearch> getBaseMapper() {
		return themeMapper;
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.user.service.ThemeService#findByUser(java.lang.String)
	 */
	@Override
	public List<Theme> findByUser(String userId) {
		ThemeSearch example = new ThemeSearch();
		example.createCriteria().andUserIdEqualTo(userId);
		return themeMapper.selectByExample(example);
	}

}
