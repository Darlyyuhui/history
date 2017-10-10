package com.xiangxun.atms.module.pollute.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.pollute.vo.PolluteCompany;
import com.xiangxun.atms.module.pollute.vo.PolluteCompanySearch;


public interface PolluteCompanyService extends BaseService<PolluteCompany,PolluteCompanySearch>{
    void saveInfo(PolluteCompany info, MultipartHttpServletRequest fileRequest);
	
	void updateInfo(PolluteCompany info, MultipartHttpServletRequest fileRequest);
	List<PolluteCompany> selectAll();
}