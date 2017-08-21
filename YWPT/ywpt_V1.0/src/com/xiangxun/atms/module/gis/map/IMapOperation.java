package com.xiangxun.atms.module.gis.map;

import java.util.List;

import com.xiangxun.atms.module.gis.domain.LayerBean;
import com.xiangxun.atms.module.gis.domain.LayerEnum;

public interface IMapOperation {
	
	/**
	 * 根据name字段模糊查询对象列表
	 * @param name
	 * @return
	 */
	List<LayerBean> getByName(LayerEnum layer, String name);

	/**
	 * 根据where条件查询对象列表
	 * @param layer LayerEnum枚举，指定针对哪个图层的操作
	 * @param where
	 * @return
	 */
	List<LayerBean> getByWhere(LayerEnum layer, String where);
	
	/**
	 * 根据要素code精确值查询指定图层要素
	 * @param layer LayerEnum枚举，指定针对哪个图层的操作
	 * @param code
	 * @return
	 */
	List<LayerBean> getByCode(LayerEnum layer, String code);
	
	/**
	 * 根据要素codes精确值查询指定图层要素
	 * @param layer LayerEnum枚举，指定针对哪个图层的操作
	 * @param codes
	 * @return
	 */
	List<LayerBean> getByCodes(LayerEnum layer, String[] codes);
	
	/**
	 * 查询指定图层所有要素
	 * @param layer LayerEnum枚举，指定针对哪个图层的操作
	 * @return
	 */
	List<LayerBean> getAll(LayerEnum layer);
	
	/**
	 * 根据唯一标识fid或objectid查询要素对象
	 * @param id
	 * @return
	 */
	LayerBean getById(LayerEnum layer, long id);
	
	/**
	 * 添加要素
	 * @param t
	 * @return
	 */
	int add(LayerEnum layer, LayerBean t);
	
	/**
	 * 批量添加要素
	 * @param layer
	 * @param list
	 * @return
	 */
	int addList(LayerEnum layer, List<LayerBean> list);
	
	/**
	 * 更新要素
	 * @param t
	 * @return
	 */
	int update(LayerEnum layer, LayerBean t);

	/**
	 * 更新要素属性
	 * @param layer
	 * @param t
	 * @return
	 */
	int updateAttrById(LayerEnum layer, LayerBean t);

	/**
	 * 添加或更新要素
	 * @param t
	 * @return
	 */
	int save(LayerEnum layer, LayerBean t);
	
	/**
	 * 根据要素的唯一标识fid或objectid删除要素
	 * @param t
	 * @return
	 */
	int delete(LayerEnum layer, long id);
	
	/**
	 * 根据code(业务库对应的code或主键)删除要素
	 * @param code
	 * @return
	 */
	int deleteByCode(LayerEnum layer, String code);
	
	/**
	 * 根据where条件删除要素
	 * @param layer LayerEnum枚举，指定针对哪个图层的操作
	 * @param where
	 * @return
	 */
	int deleteByWhere(LayerEnum layer, String where);
}
