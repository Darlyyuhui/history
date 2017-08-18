package com.xiangxun.atms.module.files.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.module.files.vo.Files;
import com.xiangxun.atms.module.files.vo.FilesSearch;

public interface FilesService extends BaseService<Files, FilesSearch> {

	/**
	 * 保存附件
	 * @param businessId	业务ID
	 * @param businessType	业务类型（模块简称）
	 * @param uploadFileType	允许上传的附件类型，为null则不限制
	 * @param uploadFileSize	允许上传的附件大小，为null则不限制，单位：Mb
	 * @param fileRequest	上传附件的request
	 */
	void saveFile(String businessId, String businessType, String uploadFileType, Long uploadFileSize, MultipartHttpServletRequest fileRequest);
	
	@SuppressWarnings("rawtypes")
	String getBusinessType(Class clazz);
	
	/**
	 * 删除附件
	 * @param businessId	业务ID
	 */
	void deleteByBusinessId(String businessId);
	/**
	 * 查询
	 * @param businessId	业务ID
	 * @return
	 */
	List<Files> queryByBusinessId(String businessId);
	List<Files> queryByBusinessId(String businessId, HttpServletRequest request);
	
}
