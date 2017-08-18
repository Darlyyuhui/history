package com.xiangxun.atms.module.files.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.files.vo.Files;

@Controller
@RequestMapping(value = "files")
public class FilesCtl extends BaseCtl {

	@Resource
	FilesService filesService;
	
	/**
	 * 查询附件
	 * @param businessId
	 * @param isDel		是否可删除
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getFiles/{businessId}/", produces = "text/html; charset=utf-8", method = RequestMethod.GET)
	public String getFiles(@PathVariable String businessId
			, @RequestParam(value = "isDel", required = true, defaultValue = "0") String isDel
			, Model model, HttpServletRequest request) {
		List<Files> list = filesService.queryByBusinessId(businessId, request);
		String root = request.getContextPath();
		String url = "";
		StringBuilder str = new StringBuilder();
		if ("1".equals(isDel)) {
			str.append("<h5>已上传附件：");
			if (list == null || list.size() == 0) {
				str.append("无。");
			}
			str.append("</h5>");
		}
		for (Files f : list) {
			url = request.getContextPath() + "/downloadfile/" + f.getFileName() + "/?filepath=" + f.getFilePath();
			str.append("<p>");
			str.append("<a href=\"" + url + "\" title=\"点击下载附件\" style=\"margin-right: 15px;\">" + f.getFileName() + "</a>");
			if (this.isImage(f.getFileType())) {
				if (f.getFileExists()) {
					str.append("<a href=\"javascript:showDialogImg('"+f.getFileName()+"','"+(root+"/"+f.getFilePath())+"')\" title=\"点击显示大图\">查看</a>");	
				} else {
					str.append("<a href=\"javascript:showDialogImg('"+f.getFileName()+"','"+root+"/images/files/error.png')\" title=\"点击显示大图\">查看</a>");
				}
			} else if (this.isOnlineView(f.getFileType())) {
				str.append("<a href=\"javascript:onlineView('"+f.getId()+"')\" title=\"点击在线浏览\">浏览</a>");
			} else if ("8".equals(f.getFileType())) {
				str.append("<a href=\"javascript:onlineVideo('"+f.getId()+"')\" title=\"点击在线浏览\">浏览</a>");
			} 
			if ("1".equals(isDel)) {
				str.append("<a href=\"javascript:deleteFile('" + f.getId() + "')\" style=\"margin-left: 15px;\">删除</a>");
			}
			str.append("</p>");
		}
		return str.toString();
	}
	
	@RequestMapping(value = "viewFiles/{businessId}/", method = RequestMethod.GET)
	public String viewFiles(@PathVariable String businessId
			, @RequestParam(value = "isDel", required = true, defaultValue = "0") String isDel
			, Model model, HttpServletRequest request) {
		List<Files> list = filesService.queryByBusinessId(businessId, request);
		model.addAttribute("fileList", list);
		model.addAttribute("isDel", isDel);
		return "files/files_view";
	}
	
	/**
	 * 验证是否是图片
	 * @param fileType
	 * @return
	 */
	private boolean isImage(String fileType) {
		if ("1".indexOf(fileType) > -1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 是否可在线浏览
	 * @param fileType
	 * @return
	 */
	private boolean isOnlineView(String fileType) {
		if ("5".indexOf(fileType) > -1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 删除附件
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteFile/{id}/", method = RequestMethod.DELETE)
	@LogAspect(desc = "删除附件")
	public ResponseEntity getFiles(@PathVariable String id) {
		ResponseEntity entity = new ResponseEntity();
		try {
			filesService.deleteById(id);
			entity.setResult("ok");
		}catch (Exception e) {
			logger.error("删除附件出错：" + e.getMessage());
			entity.setResult("err");
		}
		return entity;
	}
	
	@RequestMapping(value = "onlineView/{fileId}/")
	public String onlineView(@PathVariable String fileId, String type, Model model, HttpServletRequest request) {
		Files f = filesService.getById(fileId);
		String path = f.getViewPath();
		if (".pdf".equals(f.getFileType())) {
			path = f.getFilePath();
		}
		model.addAttribute("fileUrl", path);
		return "files/online_view";
	}
	
	@RequestMapping(value = "onlineVideo/{fileId}/")
	public String onlineVideo(@PathVariable String fileId, Model model, HttpServletRequest request) {
		Files f = filesService.getById(fileId);
		model.addAttribute("fileUrl", f.getFilePath());
		return "files/online_video";
	}

    /**
     * 根据附件地址，在线预览附件内容  xiongjie
     * @return
     */
    @RequestMapping(value = "viewByPath")
    public String viewByPath(String path, Model model) {
        model.addAttribute("fileUrl", path);
        return "files/online_view";
    }
}
