/**
 * 
 */
package com.xiangxun.atms.common.user.service;

import java.util.List;

import com.xiangxun.atms.common.user.vo.Skin;
import com.xiangxun.atms.common.user.vo.SkinSearch;
import com.xiangxun.atms.framework.base.BaseService;

/**
 * 皮肤管理业务逻辑接口
 * @author kouyunhao
 * @version 1.0
 * Apr 9, 2014
 */
public interface SkinService extends BaseService<Skin, SkinSearch> {
	
	List<Skin> findByUser(String userId);

}
