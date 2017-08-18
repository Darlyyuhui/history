package com.xiangxun.epms.mobile.business.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.epms.mobile.business.domain.Files;

public interface FilesService {

	/**
	 * 根据业务ID获取附件信息
	 * @param businessId
	 * @return
	 */
	List<Files> queryByBusinessId(String businessId);
	
	/**
	 * 保存附件
	 * @param businessId	业务ID
	 * @param businessType	业务类型（模块简称）
	 * @param isUseInputName	是否使用文本域名作为业务类型扩展
	 * @param uploadFileType	允许上传的附件类型，为null则不限制
	 * @param uploadFileSize	允许上传的附件大小，为null则不限制，单位：Mb
	 * @param fileRequest	上传附件的request
	 */
	void saveFile(String businessId, String businessType, boolean isUseInputName
			, String uploadFileType, Long uploadFileSize, MultipartHttpServletRequest fileRequest);
	
	void saveFile(Files info);
	public  List<String> saveFilepackid( String businessType, boolean isUseInputName, String uploadFileType, Long uploadFileSize,
			MultipartHttpServletRequest fileRequest);
}
