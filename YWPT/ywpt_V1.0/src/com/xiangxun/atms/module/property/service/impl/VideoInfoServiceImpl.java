package com.xiangxun.atms.module.property.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.common.user.service.DepartmentAuthorityFilter;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.property.domain.VideoInfo;
import com.xiangxun.atms.module.property.domain.VideoInfoSearch;
import com.xiangxun.atms.module.property.domain.VideoInfoSearch.Criteria;
import com.xiangxun.atms.module.property.mapper.VideoInfoMapper;
import com.xiangxun.atms.module.property.service.VideoInfoService;


/***
 * 监控设备相关的 服务接口类
 * @author yantao
 */
@Service("videoInfoService")
public class VideoInfoServiceImpl extends AbstractBaseService<VideoInfo, VideoInfoSearch> implements VideoInfoService {

	@Resource
	Cache cache;
	
	@Resource
	DepartmentAuthorityFilter departmentAuthorityFilter;
	
	@Resource
	VideoInfoMapper videoInfoMapper;
	
	@Override
	protected BaseMapper<VideoInfo, VideoInfoSearch> getBaseMapper() {
		return videoInfoMapper;
	}
	
	@Override
	public Page getVideoInfoByCondition(Map<String, Object> map, int pageNo,int pageSize, String sortType,String menuid) {
		if(map!=null){
			if(StringUtils.notEmpty(map.get("name")+"")){
				map.put("name", "%"+map.get("name").toString()+"%");
			}
			if(StringUtils.notEmpty(map.get("companyid")+"")){
				map.put("companyid", map.get("companyid").toString());
			}
			if(StringUtils.notEmpty(map.get("videotypeCode")+"")){
				map.put("videotypeCode", map.get("videotypeCode").toString());
			}
			if(StringUtils.notEmpty(map.get("directionCode")+"")){
				map.put("directionCode", map.get("directionCode").toString());
			}
			if(StringUtils.notEmpty(map.get("orgNames")+"")){
				map.put("orgNames", "%"+map.get("orgNames").toString()+"%");
			}
			if(StringUtils.notEmpty(map.get("roadinfoId")+"")){
				if("00".equals(map.get("roadinfoId"))){
					map.put("roadinfoId", null);
				}else{
					map.put("roadinfoId", map.get("roadinfoId").toString());
				}
			}
		}
		if(StringUtils.notEmpty(sortType)){
			map.put("sortColumn", sortType);
		}
		Page page = getListByCondition(map, pageNo, pageSize,sortType,menuid);
		
		return page;
	}
	
	//监控设备导出专用
	public List<VideoInfo> selectByExampleDiy(Map<String, Object> searchParams,String sortType){
		VideoInfoSearch search = new VideoInfoSearch();
		//判断封装前台条件
		Criteria criteria = search.createCriteria();
		criteria.andIdIsNotNull();
		if(searchParams!=null){
			if(StringUtils.notEmpty(searchParams.get("name")+"")){
				criteria.andNameLike("%"+searchParams.get("name").toString()+"%");
			}
			if(StringUtils.notEmpty(searchParams.get("companyid")+"")){
				criteria.andCompanyidEqualTo(searchParams.get("companyid").toString());
			}
			if(StringUtils.notEmpty(searchParams.get("directionCode")+"")){
				criteria.andDirectionCodeEqualTo(searchParams.get("directionCode").toString());
			}
			if(StringUtils.notEmpty(searchParams.get("videotypeCode")+"")){
				criteria.andVideotypeCodeEqualTo(searchParams.get("videotypeCode").toString());
			}
			if(StringUtils.notEmpty(searchParams.get("orgId")+"")){
				criteria.andOrgIdEqualTo(searchParams.get("orgId").toString());
			}
		}
		if(StringUtils.notEmpty(sortType)){
			search.setOrderByClause(sortType);
		}
		return videoInfoMapper.selectByExample(search);
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.device.service.VideoInfoService#getMaxCodeNum()
	 */
	@Override
	public Integer getMaxCodeNum() {
		return videoInfoMapper.getMaxCodeNum();
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.device.service.VideoInfoService#countByCode(java.lang.String)
	 */
	@Override
	public Integer countByCode(String code) {
		return videoInfoMapper.countByCode(code);
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.device.service.VideoInfoService#findByCode()
	 */
	@Override
	public List<VideoInfo> findByCode(String code) {
		VideoInfoSearch example = new VideoInfoSearch();
		//判断封装前台条件
		example.createCriteria().andIdIsNotNull().andCodeEqualTo(code);
		return videoInfoMapper.selectByExample(example);
	}
	
	
	@Override
	public List<VideoInfo> findByRoadId(String id) {
		VideoInfoSearch example = new VideoInfoSearch();
		Criteria criteria = example.createCriteria();
		criteria.andIdIsNotNull().andRoadinfoIdEqualTo(id);
		return videoInfoMapper.selectByExample(example);
	}
	
	/**
	 * 根据部门ID 获得 视频设备列表
	 */
	@Override
	public List<VideoInfo> findDevlistByOrgId(String orgId) {
		return videoInfoMapper.selectDevlistByOrgId(orgId);
	}
	
	/**
	 * 根据道路ID 获得 视频设备列表
	 */
	@Override
	public List<VideoInfo> selectDevlistByRoadId(String roadId) {
		return videoInfoMapper.selectDevlistByRoadId(roadId);
	}

	
	//===================== 基本操作方法 缓存刷新 ============================
	
	@Override
	public List<VideoInfo> findAll() {
		VideoInfoSearch search = new VideoInfoSearch();
		search.createCriteria().andIdIsNotNull();
		return videoInfoMapper.selectByExample(search);
	}
	
	private void refreshCache() {
		List<VideoInfo> caches =  findAll();
		Table<String, String, String> table = HashBasedTable.create();
		for (VideoInfo info : caches) {
			//存储的对象为table
			table.put(info.getCode(), VideoInfo.class.getSimpleName(), info.getName());
		}
		cache.put(VideoInfo.class.getSimpleName(), table);
	}

	
	@Override
	public int save(VideoInfo info) {
		int result = super.save(info);
		if(result > 0){
			refreshCache();
		}
		return result;
	}
	
	@Transactional
	public int deleteByExample(VideoInfoSearch example) {
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
	public int updateByExample(VideoInfo record, VideoInfoSearch example) {
		int result =  super.updateByExample(record, example);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	@Override
	@Transactional
	public int updateByExampleSelective(VideoInfo record, VideoInfoSearch example) {
		int result =  super.updateByExampleSelective(record, example);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	@Override
	@Transactional
	public int updateById(VideoInfo record) {
		int result =  super.updateById(record);
		//刷新缓存
	    refreshCache();
	    return result;
	}
	
	@Override
	@Transactional
	public int updateByIdSelective(VideoInfo record) {
		int result =  super.updateByIdSelective(record);
		if(result > 0){
			//刷新缓存
			refreshCache();
		}
	    return result;
	}

	@Override
	public List<VideoInfo> getAllVideoByAuthority(Map<String, Object> map, String menuid) {
		if(map != null){
			if(StringUtils.notEmpty(map.get("name")+"")){
				map.put("name", "%"+map.get("name").toString()+"%");
			}
			if(StringUtils.notEmpty(map.get("code")+"")){
				map.put("code", "%"+map.get("code").toString()+"%");
			}
			if(StringUtils.notEmpty(map.get("companyId")+"")){
				if("00".equals(map.get("companyId"))){
					map.put("companyId", null);
				}
			}
			if(StringUtils.notEmpty(map.get("orgId")+"")){
				if("00".equals(map.get("orgId"))){
					map.put("orgId", null);
				}
			}
		}
		else {
			map = new HashMap<String, Object>();
		}
		map = departmentAuthorityFilter.filter(map, menuid);
		return videoInfoMapper.getListByCondition(map);
	}

	@Override
	public void saveHistoryRecord(String id) {
		videoInfoMapper.insertHistoryRecord(id);
		refreshCache();
	}


	@Override
	public void updatePrimaryKey(String afterId, String beforeId) {
		videoInfoMapper.updatePrimaryKey(afterId, beforeId);
		refreshCache();
	}
	
	/**
	 * 根据厂商ID获取监控设备信息
	 * @param factoryId
	 * @param menuid
	 * @return
	 */
	@Override
	public List<VideoInfo> getVideoListByFactoryid(String factoryId, String menuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		//厂商ID
		map.put("factoryId", factoryId);
		//添加数据权限
		map = departmentAuthorityFilter.filter(map, menuid);
		List<VideoInfo> list = videoInfoMapper.getListByFactoryid(map);
		return list;
	}
	
	@Override
	public List<VideoInfo> findByName(String name) {
		VideoInfoSearch example = new VideoInfoSearch();
		example.createCriteria().andIdIsNotNull().andNameEqualTo(name);
		return videoInfoMapper.selectByExample(example);
	}

	@Override
	public List<VideoInfo> findByIp(String ip) {
		VideoInfoSearch example = new VideoInfoSearch();
		example.createCriteria().andIdIsNotNull().andDeviceIpEqualTo(ip);
		return videoInfoMapper.selectByExample(example);
	}

}
