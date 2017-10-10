package com.xiangxun.epms.mobile.business.dao;



import java.util.List;

import com.xiangxun.epms.mobile.business.domain.SamplingReg;


public interface SamplingManureRegMapper {
	
	List<SamplingReg> findById(String id);
}