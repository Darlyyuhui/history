package com.xiangxun.atms.module.repository.web;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.service.PublicMethodService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.repository.domain.CatalogInfo;
import com.xiangxun.atms.module.repository.domain.KnowledgeInfo;
import com.xiangxun.atms.module.repository.service.CatalogInfoService;
import com.xiangxun.atms.module.repository.service.KnowledgeInfoService;

/**
 * 知识库查询
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value = "repository/search")
public class KnowledgeSearchCtl extends BaseCtl {
	
	@Resource 
	KnowledgeInfoService knowledgeInfoService;
	
	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	CatalogInfoService catalogInfoService;
	
	@Resource
	PublicMethodService publicMethodService;
	
	@Resource
	DicService dicService;
	
	@Resource
	UserService userService;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "name") String sortType,
		@RequestParam(value = "page", defaultValue = "0") int pageNumber, String type, HttpServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("verifyresult", "1");
		String deptid = getCurrentUser().getDeptId();
		if(!deptid.equals("00")){
			searchParams.put("operator", getCurrentUserId());
		}
		Page page = knowledgeInfoService.getListByCondition(searchParams, pageNumber,Page.DEFAULT_PAGE_SIZE, sortType);
		Object obj = page.getResult();
		if(obj!=null && obj instanceof List){
			@SuppressWarnings("unchecked")
			List<KnowledgeInfo> list = (List<KnowledgeInfo>)obj;
			if(list != null && list.size() != 0){
				for(KnowledgeInfo knowledge : list){
					CatalogInfo catalog = catalogInfoService.getById(knowledge.getPid());
					if(catalog != null){
						knowledge.setKnowledgetype(catalog.getName());
					}
				}
			}
		}
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		KnowledgeInfo knowledge = new KnowledgeInfo();
		try {
			//将查询的map转换成user对象
			BeanUtils.populate(knowledge, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("knowledge", knowledge);
		request.getSession().setAttribute("catalogList", catalogInfoService.findAll());
		return "repository/search/list";
	}
	
	/**
	 * 获取详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String showView(@PathVariable("id")
	String id, @PathVariable("menuid")
	String menuid, String page, Model model, HttpServletRequest request) {
		KnowledgeInfo knowledge  = knowledgeInfoService.getById(id);
		String path = knowledge.getPath();
		if(path != null){
			String uploadname = "";
			File file = new File(request.getSession().getServletContext()
					.getRealPath("/")+path);
			if(!file.exists()){
				model.addAttribute("filexist", "-1");
			}else{
				uploadname = path.replace(path.substring(path.lastIndexOf(".")), ".swf");
			}
			model.addAttribute("uploadname", uploadname);
		}
		CatalogInfo catalog = catalogInfoService.getById(knowledge.getPid());
		if(catalog != null){
			knowledge.setKnowledgetype(catalog.getName());
		}
		WorkorderInfo workorder = workorderInfoService.getById(knowledge.getNote());
		if(workorder != null){
			publicMethodService.setStatusColor(workorder);
			model.addAttribute("workorder", workorder);
		}
		model.addAttribute("knowledge", knowledge);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "repository/search/view";
	}
	
	/***
	 * 文档 导出
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="doExport/{menuid}/",method=RequestMethod.GET)
	public void exportXls(@PathVariable String menuid,HttpServletResponse response,@RequestParam(value = "sortType", defaultValue = "NAME") String sortType,ServletRequest request){
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
		// 接收前台参数
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		// 获取数据
		Page page = knowledgeInfoService.getListByCondition(searchParams, 0,999999, sortType);
		// 设置内容
		List<Object[]> result = new ArrayList<Object[]>();
		List<KnowledgeInfo> list = page.getResult();
		for (int i = 0; i < list.size(); i++) {
			KnowledgeInfo knowledge = list.get(i);
			String type = knowledge.getType();
			if(type.equals("1")){
				type = "文档上传";
			}else{
				type = "人工录入";
			}
			String knowledgetype = "";
			CatalogInfo catalog = catalogInfoService.getById(knowledge.getPid());
			if(catalog != null){
				knowledgetype = catalog.getName();
			}
			String userName = "";
			User user = userService.getById(knowledge.getOperator());
			if(user != null){
				userName = user.getName();
			}
			String createTime = DateUtil.dateFormatToString(knowledge.getCreatetime(), "yyyy-MM-dd HH:mm:ss");
			//封装数组
			Object[] values = new Object[] { 
				i+1, 
				knowledge.getName(),
				type,
				knowledgetype,
				userName,
				createTime,
				knowledge.getNote(),
			};
			result.add(values);
		}
		
		String nowtimeStr = DateUtil.dateFormatToString(new java.util.Date(), "yyyy年MM月dd日HH时mm分ss秒");
		export.setFileName("知识库文档信息一览表["+nowtimeStr+"].xls");
		export.exportExcelFileStream("xls" + File.separator + "knowledgeinfo_exp.xls", null, result, response);
	}
}
