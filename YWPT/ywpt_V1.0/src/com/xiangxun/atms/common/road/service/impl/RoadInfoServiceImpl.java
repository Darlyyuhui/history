package com.xiangxun.atms.common.road.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.common.road.dao.RoadInfoMapper;
import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.common.road.vo.RoadInfo;
import com.xiangxun.atms.common.road.vo.RoadInfoSearch;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
/**
 * 道路信息 服务接口
 * @author YanTao
 * @Apr 19, 2013 2:07:20 PM
 */
@Service("roadInfoService")
public class RoadInfoServiceImpl extends AbstractBaseService<RoadInfo, RoadInfoSearch> implements RoadInfoService {

	@Resource
	RoadInfoMapper roadInfoMapper;
	
	@Resource
	Cache cache;
	
	@Override
	protected BaseMapper<RoadInfo, RoadInfoSearch> getBaseMapper() {
		return roadInfoMapper;
	}
	
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.deptment.service.DepartmentService#getChildren(java.lang.String)
	 */
	@Override
	public List<RoadInfo> getChildren(String id) {
		Assert.notNull("id must not be null");
		return roadInfoMapper.getLeafNodeById(id);
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.deptment.service.DepartmentService#hasChild(java.lang.String)
	 */
	@Override
	public boolean hasChild(String id) {
		Assert.notNull("id must not be null");
		return roadInfoMapper.hasChild(id)>0?true:false;
	}
	
	
// =============== 刷新缓存 重载父类 ===========================
	

	@Override
	public List<RoadInfo> findAll() {
		RoadInfoSearch search = new RoadInfoSearch();
		search.setOrderByClause("nlssort(name,'NLS_SORT=SCHINESE_PINYIN_M')");
		search.createCriteria().andIdIsNotNull();
		return roadInfoMapper.selectByExample(search);
	}
	
	private void refreshCache() {
		List<RoadInfo> caches =  findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (RoadInfo roadInfo : caches) {
			//存储的对象为table
			table.put(roadInfo.getId(), RoadInfo.class.getSimpleName(), roadInfo.getName());
		}
		cache.put(RoadInfo.class.getSimpleName(), table);
	}
	
	@Override
	public int save(RoadInfo roadInfo) {
		String id = roadInfo.getPid();
		if(!id.equals("")){
			if(id.equals("00")){
				roadInfo.setLevels(1);
			}else{
				int level = roadInfoMapper.selectByPrimaryKey(id).getLevels();
				roadInfo.setLevels(level+1);
			}
		}
		int result = super.save(roadInfo);
		if(result > 0){
			refreshCache();
		}
		return result;
	}
	
	@Transactional
	public int deleteByExample(RoadInfoSearch example) {
		int result = super.deleteByExample(example);
		if(result > 0){
		    refreshCache();
		}
	    return result;
	}
	
	@Override
	@Transactional
	public int deleteById(String id) {
		int result =  super.deleteById(id);
		if(result > 0){
		    refreshCache();
		}
	    return result;
	}
	

	@Override
	@Transactional
	public int updateByExample(RoadInfo record, RoadInfoSearch example) {
		int result =  super.updateByExample(record, example);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	@Override
	@Transactional
	public int updateByExampleSelective(RoadInfo record, RoadInfoSearch example) {
		int result =  super.updateByExampleSelective(record, example);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	@Override
	@Transactional
	public int updateById(RoadInfo record) {
		int result =  super.updateById(record);
		//刷新缓存
	    refreshCache();
	    return result;
	}
	

	@Override
	@Transactional
	public int updateByIdSelective(RoadInfo record) {
		int result =  super.updateByIdSelective(record);
		if(result > 0){
			refreshCache();
		}
	    return result;
	}


	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.device.service.RoadInfoService#findByRoadname()
	 */
	@Override
	public List<RoadInfo> findByRoadname(String name) {
		RoadInfoSearch search = new RoadInfoSearch();
		search.createCriteria().andIdIsNotNull().andNameEqualTo(name);
		return roadInfoMapper.selectByExample(search);
	}


	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.device.service.RoadInfoService#findByUploadcode(java.lang.String)
	 */
	@Override
	public List<RoadInfo> findByUploadcode(String uploadcode) {
		RoadInfoSearch search = new RoadInfoSearch();
		search.createCriteria().andIdIsNotNull().andUploadcodeEqualTo(uploadcode);
		return roadInfoMapper.selectByExample(search);
	}


	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.device.service.RoadInfoService#findByLevels(int)
	 */
	@Override
	public List<RoadInfo> findByLevels(String levels) {
		RoadInfoSearch search = new RoadInfoSearch();
		search.createCriteria().andIdIsNotNull().andLevelsEqualTo(new BigDecimal(Integer.parseInt(levels)));
		return roadInfoMapper.selectByExample(search);
	}


}
