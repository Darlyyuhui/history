package com.xiangxun.atms.module.files.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.FileUtils;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UploadFileUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.module.files.dao.FilesMapper;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.files.vo.Files;
import com.xiangxun.atms.module.files.vo.FilesSearch;

/**
 * 附件操作
 * @author HaoXiang
 * 2016年12月5日
 */
@Service
public class FilesServiceImpl extends AbstractBaseService<Files, FilesSearch> implements FilesService {

	@Resource
	FilesMapper filesMapper;

	@Override
	protected BaseMapper<Files, FilesSearch> getBaseMapper() {
		return filesMapper;
	}

	@Override
	public void saveFile(String businessId, String businessType, String uploadFileType, Long uploadFileSize,
			MultipartHttpServletRequest fileRequest) {
		if (fileRequest == null) {
			return;
		}
		if (StringUtils.isEmpty(businessType)) {
			return;
		}
		File newFile;
		Files fs;
		MultiValueMap<String, MultipartFile> fileMap = fileRequest.getMultiFileMap();
		if (fileMap != null && fileMap.size() > 0) {
			Date date = new Date();
			String filePath = "upload/files/" + businessType + "/" + DateUtil.formatDate("yyyyMMdd", date);
			// 上传文件保存目录
			String mkdir = fileRequest.getSession().getServletContext().getRealPath("") + File.separator + filePath;
			FileUtils.mkDirectory(mkdir);
			for (String key : fileMap.keySet()) {
				List<MultipartFile> list = fileMap.get(key);
				for (MultipartFile file : list) {
					if (file == null || file.getSize() == 0) {
						continue;
					}
					fs = new Files();
					fs.setId(UuidGenerateUtil.getUUIDLong());
					fs.setBusinessId(businessId);
					fs.setBusinessType(businessType);
					fs.setFileName(file.getOriginalFilename());
					fs.setFileSize(file.getSize());
					fs.setFileType(this.getFileType(fs.getFileName()));
					//附件类型不符合要求
					if (StringUtils.isNotEmpty(uploadFileType) && uploadFileType.indexOf(fs.getFileType()) == -1) {
						continue;
					}
					//附件大小不符合要求
					if (uploadFileSize != null && (uploadFileSize*1024*1024) < fs.getFileSize()) {
						continue;
					}
					//创建新文件
					newFile = FileUtils.getCreateFile(mkdir, this.makeNewFileName(fs.getFileType()));
					//上传操作
					UploadFileUtils.up(file, newFile.getAbsolutePath());
					fs.setFilePath(filePath + "/" + newFile.getName());
					this.save(fs);
				}
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public String getBusinessType(Class clazz) {
		return clazz.getSimpleName();
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
	public void deleteByBusinessId(String businessId) {
		FilesSearch search = new FilesSearch();
		search.createCriteria().andBusinessIdEqualTo(businessId);
		//删除硬盘上的文件
		List<Files> list = this.selectByExample(search);
		try {
			if (list != null) {
				for (Files f : list) {
					this.deleteForDisk(f);
				}
			}
		}catch(Exception e) {
			logger.error("删除附件硬盘文件出错：" + e.getMessage());
		}
		filesMapper.deleteByExample(search);
	}

	@Override
	public List<Files> queryByBusinessId(String businessId) {
		return this.queryByBusinessId(businessId, null);
	}

	@Override
	public List<Files> queryByBusinessId(String businessId, HttpServletRequest request) {
		FilesSearch search = new FilesSearch();
		search.createCriteria().andBusinessIdEqualTo(businessId);
		search.setOrderByClause("FILE_SIZE DESC");
		List<Files> list = filesMapper.selectByExample(search);
		
		String newType = "";
		for (Files file : list) {
			if (".jpg.jpeg.png.gif.bmp".contains(file.getFileType())) {
				newType = "1";
				file.setFileExists(this.fileIsExists(request, file.getFilePath()));
			} 
			else if (".doc.docx".contains(file.getFileType())) {
				newType = "2";
			} 
			else if (".xls.xlsx".contains(file.getFileType())) {
				newType = "3";
			} 
			else if (".ppt.pptx".contains(file.getFileType())) {
				newType = "4";
			}
			else if (".pdf".contains(file.getFileType())) {
				newType = "5";
			}
			else if (".txt".contains(file.getFileType())) {
				newType = "6";
			} 
			else if (".7z.zip.rar".contains(file.getFileType())) {
				newType = "7";
			}
			else if (".mp4".contains(file.getFileType())) {
				newType = "8";
			}
			else {
				newType = "99";
				file.setFileExists(this.fileIsExists(request, file.getFilePath()));
			}
			file.setFileType(newType);
		}
		return list;
	}
	
	/**
	 * 判断文件是否存在
	 * @param request
	 * @param filePath
	 * @return
	 */
	private boolean fileIsExists(HttpServletRequest request, String filePath) {
		if (request == null) {
			return true;
		}
		String realPath = request.getSession().getServletContext().getRealPath("") + File.separator + filePath;
		File file = new File(realPath);
		try {
			return file.exists();
		} catch(Exception e) {
			return false;
		} finally {
			file = null;
		}
	}

	@Override
	public int deleteById(String id) {
		try {
			this.deleteForDisk(this.getById(id));
		}catch(Exception e) {
			logger.error("删除附件硬盘文件出错：" + e.getMessage());
		}
		return super.deleteById(id);
	}
	
	/**
	 * 删除硬盘文件
	 * @param id
	 */
	private void deleteForDisk(Files f) {
		if (f == null) {
			return;
		}
		String rootPath = this.getClass().getResource("").toString();
		String str = "file:/";
		rootPath = rootPath.substring(0, rootPath.indexOf("WEB-INF"));
		if (rootPath.indexOf(str) > -1) {
			rootPath = rootPath.substring(rootPath.indexOf(str)+str.length(), rootPath.length());
		}
		if (rootPath.indexOf("%20") > -1) {
			rootPath = rootPath.replaceAll("%20", " ");
		}
		//构建File对象，如果文件存在即删除
		File file = new File(rootPath + f.getFilePath());
		if (file.exists()) {
			file.delete();
		}
		if (StringUtils.isNotEmpty(f.getViewPath())) {
			file = new File(rootPath + f.getViewPath());
			if (file.exists()) {
				file.delete();
			}
		}
	}
	
}
