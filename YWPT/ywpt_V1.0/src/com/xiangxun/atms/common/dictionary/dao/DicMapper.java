package com.xiangxun.atms.common.dictionary.dao;

import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.common.dictionary.vo.DicSearch;
import com.xiangxun.atms.framework.base.BaseMapper;
/***
 * 数据字典mapper
 * @author zhouhaij
 * @2013-3-22 下午03:33:20
 */
public interface DicMapper extends BaseMapper<Dic,DicSearch>{
	//获取最新的编码
	String selectMaxCode(String type);
}