/**
 * 
 */
package com.xiangxun.atms.common.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.user.dao.SkinMapper;
import com.xiangxun.atms.common.user.service.SkinService;
import com.xiangxun.atms.common.user.vo.Skin;
import com.xiangxun.atms.common.user.vo.SkinSearch;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;

/**
 * 皮肤管理业务逻辑接口实现类
 * @author kouyunhao
 * @version 1.0
 * Apr 9, 2014
 */
@Service("skinService")
public class SkinServiceImpl extends AbstractBaseService<Skin, SkinSearch> implements SkinService {
	
	@Resource
	SkinMapper skinMapper;

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.framework.base.AbstractBaseService#getBaseMapper()
	 */
	@Override
	protected BaseMapper<Skin, SkinSearch> getBaseMapper() {
		return skinMapper;
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.user.service.SkinService#findByUser(java.lang.String)
	 */
	@Override
	public List<Skin> findByUser(String userId) {
		SkinSearch example = new SkinSearch();
		example.createCriteria().andUserIdEqualTo(userId);
		return skinMapper.selectByExample(example);
	}

}
