package com.xiangxun.atms.module.bs.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.util.FileUtils;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UploadFileUtils;
import com.xiangxun.atms.module.bs.dao.MobileAppMapper;
import com.xiangxun.atms.module.bs.service.MobileAppService;
import com.xiangxun.atms.module.bs.vo.MobileApp;
import com.xiangxun.atms.module.bs.vo.MobileAppSearch;

@Service
public class MobileAppServiceImpl extends AbstractBaseService<MobileApp, MobileAppSearch> implements MobileAppService {
    @Resource
    private MobileAppMapper mobileAppMapper;
    /**
	 * 允许上传的类型
	 */
	private final String FILE_TYPE = ".apk";
    
    @Override
    public BaseMapper<MobileApp, MobileAppSearch> getBaseMapper() {
         return mobileAppMapper;
    }

	@Override
	public void saveInfo(MobileApp info, MultipartHttpServletRequest fileRequest) {
		String version = info.getVersion();
		String path = this.saveFilesByFileRequest(version, fileRequest);
		info.setSaveUrl(path);
		this.save(info);
	}
	
	/**
	 * 保存APP附件
	 * @param version
	 * @param fileRequest
	 * @return
	 */
	private String saveFilesByFileRequest(String version, MultipartHttpServletRequest fileRequest) {
		if (fileRequest == null) {
			return null;
		}
		File newFile;
		MultiValueMap<String, MultipartFile> fileMap = fileRequest.getMultiFileMap();
		String filePath = "upload/files/MobileApp/" + version;
		// 上传文件保存目录
		String mkdir = fileRequest.getSession().getServletContext().getRealPath("") + File.separator + filePath;
		FileUtils.mkDirectory(mkdir);
		if (fileMap != null && fileMap.size() > 0) {
			for (String key : fileMap.keySet()) {
				List<MultipartFile> list = fileMap.get(key);
				for (MultipartFile file : list) {
					if (file == null || file.getSize() == 0) {
						continue;
					}
					//附件类型不符合要求
					if (StringUtils.isNotEmpty(FILE_TYPE) && FILE_TYPE.indexOf(this.getFileType(file.getOriginalFilename())) == -1) {
						continue;
					}
					//创建新文件
					newFile = FileUtils.getCreateFile(mkdir, file.getOriginalFilename());
					//上传操作
					UploadFileUtils.up(file, newFile.getAbsolutePath());
					
					filePath += "/" + newFile.getName();
				}
			}
		}
		return filePath;
	}
	
	/**
	 * 获取文件类型
	 * @param fileName
	 * @return
	 */
	private String getFileType(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
	}
	
}