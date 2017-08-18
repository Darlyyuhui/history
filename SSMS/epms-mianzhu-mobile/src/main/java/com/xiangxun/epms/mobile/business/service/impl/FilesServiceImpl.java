package com.xiangxun.epms.mobile.business.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.epms.mobile.business.dao.FilesMapper;
import com.xiangxun.epms.mobile.business.domain.Files;
import com.xiangxun.epms.mobile.business.service.FilesService;
import com.xiangxun.epms.mobile.business.util.UploadFileUtils;
import com.xiangxun.epms.mobile.business.util.UuidGenerateUtil;
import com.xiangxun.epms.mobile.config.parameter.CustomParameters;

@Service
public class FilesServiceImpl implements FilesService {

	@Resource
	FilesMapper filesMapper;
	@Resource
	CustomParameters custom;

	@Override
	public List<Files> queryByBusinessId(String businessId) {
		List<Files> list = filesMapper.queryByBusinessId(businessId);
		for (Files f : list) {
			//返回完整的图片路径
			//f.setFilePath(f.getFilePath());
			f.setFilePath(custom.getWebUrl()+"/"+f.getFilePath());
		}
		return list;
	}

	@Override
	public void saveFile(String businessId, String businessType, boolean isUseInputName, String uploadFileType, Long uploadFileSize,
			MultipartHttpServletRequest fileRequest) {
		if (fileRequest == null) {
			return;
		}
		if (StringUtils.isEmpty(businessType)) {
			return;
		}
		File newFile;
		Files fs;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		MultiValueMap<String, MultipartFile> fileMap = fileRequest.getMultiFileMap();
		if (fileMap != null && fileMap.size() > 0) {
			Date date = new Date();
			String filePath = "upload/files/" + businessType + "/" + sdf.format(date);
			// 上传文件保存目录
			String mkdir = custom.getFileRootPath() + File.separator + filePath;
			UploadFileUtils.mkDirectory(mkdir);
			for (String key : fileMap.keySet()) {
				List<MultipartFile> list = fileMap.get(key);
				for (MultipartFile file : list) {
					if (file == null || file.getSize() == 0) {
						continue;
					}
					fs = new Files();
					fs.setId(UuidGenerateUtil.getUUIDLong());
					fs.setBusinessId(businessId);
					fs.setBusinessType(isUseInputName ? this.makeBusinessType(businessType, file.getName()) : businessType);
					fs.setFileName(file.getOriginalFilename());
					fs.setFileSize(file.getSize());
					fs.setFileType(this.getFileType(fs.getFileName()));
					//附件类型不符合要求
					if (!StringUtils.isEmpty(uploadFileType) && uploadFileType.indexOf(fs.getFileType()) == -1) {
						continue;
					}
					//附件大小不符合要求
					if (uploadFileSize != null && (uploadFileSize*1024*1024) < fs.getFileSize()) {
						continue;
					}
					//创建新文件
					newFile = UploadFileUtils.getCreateFile(mkdir, this.makeNewFileName(fs.getFileType()));
					//上传操作
					UploadFileUtils.up(file, newFile.getAbsolutePath());
					fs.setFilePath(filePath + "/" + newFile.getName());
					this.saveFile(fs);
				}
			}
		}
	}
	@Override
	public List<String> saveFilepackid( String businessType, boolean isUseInputName, String uploadFileType, Long uploadFileSize,
			MultipartHttpServletRequest fileRequest) {
		List<String>  Failedlist = new ArrayList<String>();
		if (fileRequest == null) {
			return Failedlist;
		}
		if (StringUtils.isEmpty(businessType)) {
			return Failedlist;
		}
		File newFile;
		Files fs;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		MultiValueMap<String, MultipartFile> fileMap = fileRequest.getMultiFileMap();
		if (fileMap != null && fileMap.size() > 0) {
			Date date = new Date();
			String filePath = "upload/files/" + businessType + "/" + sdf.format(date);
			// 上传文件保存目录
			String mkdir = custom.getFileRootPath() + File.separator + filePath;
			UploadFileUtils.mkDirectory(mkdir);
			for (String key : fileMap.keySet()) {
				List<MultipartFile> list = fileMap.get(key);
				
				for (MultipartFile file : list) {
					if (file == null || file.getSize() == 0) {
						continue;
					}
					fs = new Files();
					fs.setId(UuidGenerateUtil.getUUIDLong());
					String nameid=file.getOriginalFilename();
					int i=nameid.indexOf("@");
					if(i==-1){
						continue;
					}
					String businessId=nameid.substring(0, i);
					String fiNmae= nameid.substring(i+1, nameid.length());
					fs.setBusinessId(businessId);
					fs.setBusinessType(isUseInputName ? this.makeBusinessType(businessType, file.getName()) : businessType);
					fs.setFileName(fiNmae);
					fs.setFileSize(file.getSize());
					fs.setFileType(this.getFileType(fs.getFileName()));
					//附件类型不符合要求
					if (!StringUtils.isEmpty(uploadFileType) && uploadFileType.indexOf(fs.getFileType()) == -1) {
						Failedlist.add(businessId);
						continue;
					}
					//附件大小不符合要求
					if (uploadFileSize != null && (uploadFileSize*1024*1024) < fs.getFileSize()) {
						Failedlist.add(businessId);
						continue;
					}
					//创建新文件
					newFile = UploadFileUtils.getCreateFile(mkdir, this.makeNewFileName(fs.getFileType()));
					//上传操作
					UploadFileUtils.up(file, newFile.getAbsolutePath());
					fs.setFilePath(filePath + "/" + newFile.getName());
					this.saveFile(fs);
				}
			}
		}
		return Failedlist;
	}
	/**
	 * 扩展业务类型
	 * @param businessType
	 * @param inputName
	 * @return
	 */
	private String makeBusinessType(String businessType, String inputName) {
		String[] names = inputName.split("_");
		if (names == null || names.length > 2) {
			return businessType;
		}
		return businessType + "_" + names[0];
	}

	/**
	 * 获取文件类型
	 * @param fileName
	 * @return
	 */
	private String getFileType(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
	}

	/**
	 * 避免重名，生成新的文件名存储
	 * @param fileType 文件类型
	 * @return
	 */
	private String makeNewFileName(String fileType) {
		if (".bmp".equals(fileType.toLowerCase())) {
			return UuidGenerateUtil.getUUIDLong() + ".jpg";
		}
		return UuidGenerateUtil.getUUIDLong() + fileType;
	}

	@Override
	public void saveFile(Files info) {
		filesMapper.saveInfo(info);
	}
	
}
