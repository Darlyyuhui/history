package com.xiangxun.atms.common.dictionary.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.common.dictionary.dao.DicMapper;
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.common.dictionary.vo.DicSearch;
import com.xiangxun.atms.common.dictionary.vo.DicSearch.Criteria;
import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.util.StringUtils;

/***
 * 数据字典服务实现类
 * @author zhouhaij
 * @2013-3-22 下午01:44:04
 */
@Service("dicService")
public class DicServiceImpl extends AbstractBaseService<Dic,DicSearch> implements DicService {

	@Resource
	DicMapper  dicMapper;
	
	@Resource
	Cache cache;
	
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.dictionary.service.DicService#getByCondition(java.util.Map, int, int, java.lang.String)
	 */
	@Override
	public Page getByCondition(Map<String, Object> map, int pageNo,int pageSize, String sortType) {
		DicSearch search = new DicSearch();
		Criteria criteria = search.createCriteria();
		if(map!=null){
			
			if(StringUtils.notEmpty(map.get("code")+"")){
				criteria.andCodeEqualTo(map.get("code").toString());
			}
			if(StringUtils.notEmpty(map.get("name")+"")){
				criteria.andNameLike("%"+map.get("name").toString()+"%");
			}
			if(StringUtils.notEmpty(map.get("types")+"")){
				criteria.andTypeIn(Arrays.asList(map.get("types").toString().split(",")));
			}
			if(StringUtils.notEmpty(map.get("type")+"")){
				criteria.andTypeEqualTo(map.get("type").toString());
			}
		}
		if(StringUtils.notEmpty(sortType)){
			search.setOrderByClause(sortType);
		}
		Page page = selectByExample(search, pageNo, pageSize);
		return page;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.dictionary.service.DicService#getDicByCodeAndType(java.lang.String, com.xiangxun.atms.common.dictionary.type.DicType)
	 */
	@Override
	public Dic getDicByCodeAndType(String code, DicType dicType) {
		Assert.notNull(code, "code must not be null");
		Assert.notNull(dicType,"dicType must not be null");
		DicSearch search = new DicSearch();
		search.createCriteria().andTypeEqualTo(dicType.toString()).andCodeEqualTo(code);
		List<Dic> dics = dicMapper.selectByExample(search);
		if(dics == null || dics.isEmpty())  return null;
		return dics.get(0);
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.dictionary.service.DicService#getNextCodeByType(com.xiangxun.atms.common.dictionary.type.DicType)
	 */
	@Override
	public String getNextCodeByType(DicType dicType) {
		String maxcode = dicMapper.selectMaxCode(dicType.toString());
		try{
			Integer max = Integer.parseInt(maxcode);
			Integer nextCode = max+1;
			return nextCode.toString();
		}catch (java.lang.NumberFormatException e) {
			//如果不是数据则返回默认编码001
			return "0";
		}
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.dictionary.service.DicService#getDicByType(com.xiangxun.atms.common.dictionary.type.DicType)
	 */
	@Override
	public List<Dic> getDicByType(DicType dicType) {
		Assert.notNull(dicType,"dicType must not be null");
		//获取缓存
		@SuppressWarnings("unchecked")
		Table<String, String, String> table = (Table)cache.get(Dic.class.getSimpleName());
		//缓存中没有则查数据库
		if(table ==null || table.size()==0){
			DicSearch search = new DicSearch();
			search.createCriteria().andTypeEqualTo(dicType.toString());
			List<Dic> dics = dicMapper.selectByExample(search);
			//add by kouyunhao 2014-04-22 添加排序。
			Collections.sort(dics,new Comparator<Dic>(){
				@Override
				public int compare(Dic o1, Dic o2) {
					return o1.getCode().hashCode()-o2.getCode().hashCode();
				}
			});
			return dics;
		}else{
			Map<String,String> maps = table.column(dicType.toString());
			List<Dic> result = new ArrayList<Dic>();
			for (Iterator<String> iterator = maps.keySet().iterator(); iterator.hasNext();) {
				String key = iterator.next().toString();
				Dic dic = new Dic(key,maps.get(key),dicType);
				result.add(dic);
			}
			//add by kouyunhao 2014-04-22 添加排序。
			Collections.sort(result,new Comparator<Dic>(){
				@Override
				public int compare(Dic o1, Dic o2) {
					return o1.getCode().hashCode()-o2.getCode().hashCode();
				}
			});
			return result;
		}
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.dictionary.service.DicService#save(com.xiangxun.atms.common.dictionary.vo.Dic)
	 */
	@Override
	@Transactional
	public int save(Dic dic) {
		int result =  dicMapper.insertSelective(dic);
		//刷新缓存
		refreshCache();
		return result;
	}

	
	
	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.base.AbstractBaseService#deleteByExample(java.lang.Object)
	 */
	@Override
	@Transactional
	public int deleteByExample(DicSearch example) {
		int result = super.deleteByExample(example);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.base.AbstractBaseService#deleteById(java.lang.String)
	 */
	@Override
	@Transactional
	public int deleteById(String id) {
		int result =  super.deleteById(id,getById(id));
		//刷新缓存
	    refreshCache();
	    return result;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.base.AbstractBaseService#updateByExample(java.lang.Object, java.lang.Object)
	 */
	@Override
	@Transactional
	public int updateByExample(Dic record, DicSearch example) {
		int result =  super.updateByExample(record, example);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.base.AbstractBaseService#updateByExampleSelective(java.lang.Object, java.lang.Object)
	 */
	@Override
	@Transactional
	public int updateByExampleSelective(Dic record, DicSearch example) {
		int result =  super.updateByExampleSelective(record, example);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.base.AbstractBaseService#updateById(java.lang.Object)
	 */
	@Override
	@Transactional
	public int updateById(Dic record) {
		int result =  super.updateById(record);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.framework.base.AbstractBaseService#updateByIdSelective(java.lang.Object)
	 */
	@Override
	@Transactional
	public int updateByIdSelective(Dic record) {
		int result =  super.updateByIdSelective(record);
		//刷新缓存
	    refreshCache();
	    return result;
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.dictionary.service.DicService#findAll()
	 */
	@Override
	public List<Dic> findAll() {
		DicSearch search = new DicSearch();
		search.createCriteria().andCodeIsNotNull();
		search.setOrderByClause("code");
		return dicMapper.selectByExample(search);
	}

	@Override
	protected BaseMapper<Dic, DicSearch> getBaseMapper() {
		return dicMapper;
	}
	
	/***
	 * 刷新缓存
	 */
	private void  refreshCache(){
		Table<String, String, String> table = HashBasedTable.create();
		Table<String, String, String> table1 = HashBasedTable.create();
		for (Dic dic : findAll()) {
			table.put(dic.getCode(),dic.getType().toString(),dic.getName());
			table1.put(dic.getName(),dic.getType().toString(),dic.getCode());
		}
		cache.put(Dic.class.getSimpleName(),table);
		cache.put("dic_name^code_cache",table1);
	}

	/* (non-Javadoc)
	 * @see com.xiangxun.atms.common.dictionary.service.DicService#getNameByKey(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings({"unchecked" })
	@Override
	public String getNameByKey(String keyName, String typeCode, String id) {
		Table table = (Table)cache.get(keyName);
		if(table==null){
			return "";
		}else{
		    if(typeCode==null){
		        String str = table.column(keyName).get(id)==null?"":table.column(keyName).get(id)+"";
		  	    return str;
		      }else{
		        String str = table.column(typeCode).get(id)==null?"":table.column(typeCode).get(id)+"";
		        return str;
		      }
		}
	}

	/* (non-Javadoc)
	 * @see@see com.xiangxun.atms.common.dictionary.service.DicService#getCodeByKey(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getCodeByKey(String keyName, String typeCode, String name) {
		Table table = (Table)cache.get(keyName);
		if(table==null){
			return "";
		}else{
		    if(typeCode==null){
		        String str = table.column(keyName).get(name)==null?"":table.column(keyName).get(name)+"";
		  	    return str;
		      }else{
		        String str = table.column(typeCode).get(name)==null?"":table.column(typeCode).get(name)+"";
		        return str;
		      }
		}
	}
	

}
