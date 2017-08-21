
package com.xiangxun.atms.module.sergrade.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.common.user.service.AuthorityFilterContext;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.sergrade.domain.GradeInfo;
import com.xiangxun.atms.module.sergrade.domain.GradeInfoSearch;
import com.xiangxun.atms.module.sergrade.mapper.GradeInfoMapper;
import com.xiangxun.atms.module.sergrade.service.GradeInfoService;

/**
 * 服务级别信息服务
 * @author guikaiping
 * @version 1.0
 */
@Service("gradeInfoService")
public class GradeInfoServiceImpl extends AbstractBaseService<GradeInfo, GradeInfoSearch> implements GradeInfoService {

	@Resource
	GradeInfoMapper gradeInfoMapper;
	
	@Resource(name="authorityFilterContext")
	AuthorityFilterContext authorityFilterContext;
	
	@Override
	protected BaseMapper<GradeInfo, GradeInfoSearch> getBaseMapper() {
		return gradeInfoMapper;
	}
	
	/***
	 * 查询服务级别列表信息服务
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @param menuid
	 * @return
	 */
	@Override
	public Page getReport(Map<String, Object> map, int pageNo, int pageSize, String sortType, String menuid) {
		//加入权限过滤及条件查询
		Map<String, Object> params = filter(map, menuid, null);
		//获取分页数据
		List<GradeInfo> reports = gradeInfoMapper.selectList(params,Page.getRowBounds(pageNo, pageSize));
		//获取记录总数
		int totalCount = gradeInfoMapper.selectTotal(params);
		
		return Page.getPage(totalCount, reports, pageNo, pageSize);
	}
	
	/***
     * 根据编号获取对象
     * @param model
     * @return GradeInfo
     */
	@Override
	public List<GradeInfo> getByCode(String code) {
		GradeInfoSearch search = new GradeInfoSearch();
		search.createCriteria().andCodeEqualTo(code);
		return gradeInfoMapper.selectByExample(search);
	}
	/**
	 * 获取所有级别信息
	 * @return
	 */
	@Override
	public List<GradeInfo> findAll() {
		GradeInfoSearch example = new GradeInfoSearch();
		example.createCriteria();
		return gradeInfoMapper.selectByExample(example);
	}
	
	/**
	 * 处理查询参数
	 * @param map
	 * @param menuid
	 * @param stateType
	 * @return
	 */
	private Map<String, Object> filter(Map<String, Object> map, String menuid, String stateType) {
		//加入权限过滤
		Map<String, Object> params = authorityFilterContext.filter(map, menuid);
		return params;
	}
	
}
