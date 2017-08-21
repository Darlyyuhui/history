/**
 * 
 */
package com.xiangxun.atms.common.user.service;

import java.util.List;

import com.xiangxun.atms.common.user.vo.Theme;
import com.xiangxun.atms.common.user.vo.ThemeSearch;
import com.xiangxun.atms.framework.base.BaseService;

/**
 * 主题管理业务逻辑接口
 * @author kouyunhao
 * @version 1.0
 * May 13, 2014
 */
public interface ThemeService extends BaseService<Theme, ThemeSearch> {
	
	List<Theme> findByUser(String userId);

}
