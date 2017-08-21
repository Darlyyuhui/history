package com.xiangxun.atms.common.dictionary.service;

import java.util.List;
import java.util.Map;

import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.common.dictionary.vo.DicSearch;
import com.xiangxun.atms.framework.base.BaseService;
import com.xiangxun.atms.framework.base.Page;

/***
 * 数据字典服务
 * @author zhouhaij
 * @2013-3-22 下午01:38:03
 */
public interface DicService extends BaseService<Dic, DicSearch>{
	
	
	/***
	 * 获取分页列表数据
	 * @param orgid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getByCondition(Map<String, Object> map,int pageNo,int pageSize,String sortType);
	
	/***
	 * 根据code和type获取数据字典数据
	 * @param code
	 * @param dicType
	 * @return
	 */
	public Dic getDicByCodeAndType(String code,DicType dicType);
	
	/***
	 * 根据数据字典类型获取信息
	 * @param dicType
	 * @return
	 */
	public List<Dic> getDicByType(DicType dicType);
	
	/***
	 * 添加
	 */
	public int save(Dic dic);
	
	/***
	 * 获取所有的
	 * @return
	 */
	public List<Dic> findAll();
	
	/****
	 * 获取字典类型的编码
	 * @param dicType
	 * @return
	 */
	public String getNextCodeByType(DicType dicType);
	
	/***
	 * 根据key获取对应的name
	 * @param keyName  缓存名称
	 * @param typeCode 字典类型
	 * @param id 数据的id
	 * @return
	 */
	public String getNameByKey(String keyName,String typeCode,String id);
	
	public String getCodeByKey(String keyName,String typeCode,String name);
	
	
}
