package com.xiangxun.atms.module.gis.service.impl;

import java.util.List;

import com.xiangxun.atms.module.gis.domain.LayerBean;
import com.xiangxun.atms.module.gis.domain.LayerEnum;
import com.xiangxun.atms.module.gis.map.IMapOperation;
import com.xiangxun.atms.module.gis.service.IMapService;

public class MapServiceImpl implements IMapService {

	private IMapOperation mapOperation;
	
	public void setMapOperation(IMapOperation mapOperation) {
		if(null == mapOperation)return;
		this.mapOperation = mapOperation;
	}

	@Override
	public int add(LayerEnum layer, LayerBean t) {
		if(null == mapOperation)return -1;
		return mapOperation.add(layer, t);
	}
	
	@Override
	public int addList(LayerEnum layer, List<LayerBean> list) {
		if(null == mapOperation)return -1;
		return mapOperation.addList(layer, list);
	}

	@Override
	public int delete(LayerEnum layer, long id) {
		if(null == mapOperation)return -1;
		return mapOperation.delete(layer, id);
	}

	@Override
	public LayerBean getById(LayerEnum layer, long id) {
		if(null == mapOperation)return null;
		return mapOperation.getById(layer, id);
	}

	@Override
	public List<LayerBean> getByName(LayerEnum layer, String name) {
		if(null == mapOperation)return null;
		return mapOperation.getByName(layer, name);
	}

	@Override
	public int save(LayerEnum layer, LayerBean t) {
		if(null == mapOperation)return -1;
		return mapOperation.save(layer, t);
	}

	@Override
	public int update(LayerEnum layer, LayerBean t) {
		if(null == mapOperation)return -1;
		return mapOperation.update(layer, t);
	}

	@Override
	public int updateAttrById(LayerEnum layer,LayerBean t){
		if(null == mapOperation) return -1;
		return mapOperation.updateAttrById(layer, t);
	}

	@Override
	public int deleteByCode(LayerEnum layer, String code) {
		if(null == mapOperation)return -1;
		return mapOperation.deleteByCode(layer, code);
	}

	@Override
	public List<LayerBean> getByCode(LayerEnum layer, String code) {
		if(null == mapOperation)return null;
		return mapOperation.getByCode(layer, code);
	}

	@Override
	public int deleteByWhere(LayerEnum layer, String where) {
		if(null == mapOperation)return -1;
		return mapOperation.deleteByWhere(layer, where);
	}

	@Override
	public List<LayerBean> getAll(LayerEnum layer) {
		if(null == mapOperation)return null;
		return mapOperation.getAll(layer);
	}

	@Override
	public List<LayerBean> getByCodes(LayerEnum layer, String[] codes) {
		if(null == mapOperation)return null;
		return mapOperation.getByCodes(layer, codes);
	}

	@Override
	public List<LayerBean> getByWhere(LayerEnum layer, String where) {
		if(null == mapOperation)return null;
		return mapOperation.getByWhere(layer, where);
	}

}
