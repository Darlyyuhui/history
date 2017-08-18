package com.xiangxun.epms.mobile.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.dao.ApbInfoMapper;
import com.xiangxun.epms.mobile.business.domain.ApbInfo;
import com.xiangxun.epms.mobile.business.domain.ApbProduct;
import com.xiangxun.epms.mobile.business.domain.ApbProductType;
import com.xiangxun.epms.mobile.business.domain.LandType;
import com.xiangxun.epms.mobile.business.service.ApbInfoService;
import com.xiangxun.epms.mobile.business.service.ApbProductTypeService;
import com.xiangxun.epms.mobile.business.service.ApbProuductService;
import com.xiangxun.epms.mobile.business.service.LandTypeService;
@Service
public class ApbInfoServiceImpl implements ApbInfoService {
    @Resource
    ApbInfoMapper  apbInfoMapper;
    @Resource
    ApbProuductService apbProuductService;
    @Resource
    ApbProductTypeService  apbProductTypeService;
    @Resource 
    LandTypeService landTypeService;
	@Override
	public List<ApbInfo> findAll() {
		List<ApbInfo> list = apbInfoMapper.findAll();
		if(list!=null&&list.size()>0){
			for(ApbInfo info :list){
				String names="";
				if(info.getMainProduct()!=null&&!"".equals(info.getMainProduct())){
					String[] codes=info.getMainProduct().split(",");
					for(String apbProductTypeCode :codes){
						ApbProductType apbProductType=apbProductTypeService.selectByPrimaryKey(apbProductTypeCode);
						if(apbProductType!=null){
							if(apbProductType.getName()!=null&&!"".equals(apbProductType.getName())){
								names+=apbProductType.getName()+",";
							}
						}
					}
				
					if(names!=null&&!"".equals(names)){
						names=names.substring(0,names.length()-1);
					}
					info.setMainProduct(names);
				}
				if(info.getSoilType()!=null&&!"".equals(info.getSoilType())){
					LandType landType=landTypeService.findByCode(info.getSoilType());
					if(landType!=null){
						info.setSoilType(landType.getName());
					}
					if(info.getSoilType()==null){
						info.setSoilType("");
					}
				}
				
			   List<ApbProduct>	produactList=apbProuductService.findById(info.getId());
			   info.setProduactList(produactList);
			}
		}
		return list;
	}

}
