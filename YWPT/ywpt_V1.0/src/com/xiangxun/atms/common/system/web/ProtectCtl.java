package com.xiangxun.atms.common.system.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.validator.ResponseEntity;

/***
 * 系统维护
 * @author zhouhaij
 * @since 1.0
 * @version
 */
@Controller
@RequestMapping("system/protect")
public class ProtectCtl extends BaseCtl{
	
	/**
	 * 进入主页面
	 * @return
	 */
	@RequestMapping("list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model){
		model.addAttribute("menuid", menuid);
		return "system/protect/list";	
	}
	
	/***
	 * 获取页面树
	 * @param model
	 * @param resp
	 * @param request
	 * @param name
	 */
	@RequestMapping("folderList")
	public void folderList(ModelMap model,ServletResponse resp,HttpServletRequest request,@RequestParam("name") String name){
			String file  = System.getProperty("atms.root");
			File folder = new File(file+File.separator+"WEB-INF"+File.separator+"jsp","jsp");
			JsonArray json = new JsonArray();
			File f = null;
			if(name==null || name.length()==0){
				f = folder.getParentFile();
			}else{
				f = new File(name);
			}
			String context = request.getContextPath();
			getFiles(f,json,context);
			try {
				resp.getWriter().write(json.toString());
			} catch (IOException e) {
				logger.error(e);
			}
		
	}
	
	/***
	 * 取得页面内容
	 * @param resp
	 * @param request
	 * @param name
	 */
	@RequestMapping("getFileContent/")
	public void getFileContent(HttpServletResponse resp,HttpServletRequest request,@RequestParam("name") String name){
		String result = null;
		if(name==null || name.length()==0){
			result ="";
		}else{
			try {
				result = FileUtils.readFileToString(new File(name),"UTF-8");
				resp.getWriter().write(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("doSave")
	@ResponseBody
	@LogAspect(desc="修改页面")
	public ResponseEntity doSave(HttpServletResponse resp,HttpServletRequest request,@RequestParam("root") String file){
		ResponseEntity  entity = new ResponseEntity();	
		try {
				String source = request.getParameter("source");
	            FileUtils.writeStringToFile(new File(file), source, "UTF-8");
	            entity.setResult("ok");
	            return entity;
			} catch (Exception e) {
				entity.setResult("error");
				entity.setMessage("保存失败");
		        return entity;
		}
	}
	
	private void getFiles(File f,JsonArray array,String context){
		File[] files = f.listFiles();
		JsonObject js = null;
		if(files!=null){
			for (File fl : files) {
				if(fl.isDirectory()){
					if (!fl.getName().equals("classes") && !fl.getName().equals("lib") ){
					js = new JsonObject();
					js.addProperty("id",fl.getName());
					js.addProperty("pId",fl.getParentFile().getName());
					js.addProperty("name",fl.getName());
					js.addProperty("isfolder","1");
					js.addProperty("path",fl.getPath());
					array.add(js);
					getFiles(fl,array,context);
					}
				}else{
					if(!(fl.getName().contains(".tld"))){
						js = new JsonObject();
						js.addProperty("id",fl.getName());
						js.addProperty("pId",fl.getParentFile().getName());
						js.addProperty("name",fl.getName());
						js.addProperty("isfolder","0");
						js.addProperty("path",fl.getPath());
						array.add(js);
					}
				}
			}
		}
	}
	
}
