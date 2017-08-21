package com.xiangxun.atms.module.repository.web;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.FileUtils;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UploadFileUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.service.PublicMethodService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.repository.domain.CatalogInfo;
import com.xiangxun.atms.module.repository.domain.KnowledgeInfo;
import com.xiangxun.atms.module.repository.domain.KnowledgeInfoSearch;
import com.xiangxun.atms.module.repository.service.CatalogInfoService;
import com.xiangxun.atms.module.repository.service.KnowledgeInfoService;

/**
 * 知识库管理
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value = "repository/input")
public class KnowledgeInputCtl extends BaseCtl {
	
	private static final String FILE_PATH = "upload_res/repository";
	
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
	
	@Resource
	private Validator validator;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "name") String sortType,
		@RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("type", "0");
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
		return "repository/input/list";
	}
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="sublist/{menuid}/")
	public String sublist(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "name") String sortType,
		@RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request){
		
		this.list(menuid, model, sortType, pageNumber, request);
		return "repository/input/sublist";
	}
	
	/***
	 * 删除知识库文件
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{codes}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除知识库文件")
	public ResponseEntity delete(@PathVariable String codes){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在进行字典数据删除。。。。。。");
			String[] codess = codes.split(",");
			List<String> dels = Arrays.asList(codess);
			KnowledgeInfoSearch search = new KnowledgeInfoSearch();
			search.createCriteria().andIdIn(dels);
			knowledgeInfoService.deleteByExample(search);
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/***
	 * 添加知识库文件
	 * @param dic
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加知识库文件")
	public String doAdd(KnowledgeInfo knowledge,String menuid,String page,RedirectAttributes redirectAttributes,HttpServletRequest request){
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<KnowledgeInfo>> failures = validator.validate(knowledge);
		if (!failures.isEmpty()) {
			redirectAttributes.addAttribute("message","添加知识库文件失败");
		}
		knowledge.setId(UuidGenerateUtil.getUUID());
		knowledge.setType("0");
		knowledge.setOperator(getCurrentUserId());
		knowledge.setCreatetime(new Date());
		knowledgeInfoService.save(knowledge);
		redirectAttributes.addFlashAttribute("message","添加成功");
		return "redirect:/repository/input/sublist/"+menuid + "/";
	}
	
	/***
	 * 判断号牌号码是否重复 add by kouyunhao 2013-09-02 
	 * @param req
	 * @param carPlateNumExist
	 * @return
	 */
	@RequestMapping(value = "nameExist")
	@ResponseBody
	public String nameExist(HttpServletRequest req, @RequestParam(value = "name") String name) {
		List<KnowledgeInfo> knowledgeList = knowledgeInfoService.findKnowledgeByName(name);
		String returnStr = Boolean.FALSE.toString();
		if (knowledgeList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(StringUtils.isNotBlank(oper)){
			if(name.equals(oper)){
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}

	/***
	 * 获取待修改知识库文件
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		KnowledgeInfo knowledge  = knowledgeInfoService.getById(id);
		model.addAttribute("knowledge",knowledge);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "repository/input/update";
	}

	/***
	 * 修改知识库文件
	 * @param role
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改知识库文件")
	public String doUpdate(@ModelAttribute("preloadKnowledgeInfo") KnowledgeInfo knowledge,String page,String menuid,RedirectAttributes redirectAttributes,HttpServletRequest request) {
		knowledgeInfoService.updateByIdSelective(knowledge);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/repository/input/sublist/"+menuid + "/";
	}
	
	/**
	 * 文件上传
	 * @author kouyunhao
	 * @version 1.0
	 * @param menuid
	 * @param redirectAttributes
	 * @param file
	 * @param request
	 * @return
	 * Sep 30, 2013
	 */
	@RequestMapping(value="doUpload",method = RequestMethod.POST)
	//@LogAspect(desc="文件上传")
	public String doUpload(@ModelAttribute("preloadKnowledgeInfo") KnowledgeInfo knowledge1,ModelMap model,String menuid,RedirectAttributes redirectAttributes, 
			@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request){
		File attach1 = null;
		String mkdir = request.getSession().getServletContext()
		.getRealPath("/")+FILE_PATH;
		// 附件1
		if (!file.getOriginalFilename().equals("")) {
			try {
				if(file.getBytes().length == 0){
					model.addAttribute("message", "不允许上传空文件");
				}else{
					FileUtils.mkDirectory(mkdir);
					String fileName = file.getOriginalFilename();
					KnowledgeInfo knowledge = new KnowledgeInfo();
					knowledge.setId(UuidGenerateUtil.getUUID());
					knowledge.setName(fileName);
					knowledge.setType("0");
					knowledge.setPath(FILE_PATH.concat(fileName));
					knowledge.setContext(knowledge1.getContext());
//					knowledge.setKnowledgeType(knowledge1.getKnowledgeType());
					knowledge.setOperator(getCurrentUserId());
					knowledge.setCreatetime(new Date());
					attach1 = FileUtils.getCreateFile(mkdir, fileName);
					if (StringUtils.isNotEmpty(attach1
							.getAbsolutePath())) {
						UploadFileUtils.up(file, mkdir.concat(fileName));
					}
					knowledgeInfoService.save(knowledge);
					model.addAttribute("message", "文件上传成功"); 
				}
			} catch (Exception e) {
				model.addAttribute("message", "文件上传失败，请检查上传文件！");
			}
		}
		model.addAttribute("menuid", request.getParameter("menuid")); 
		return "repository/input/upload";
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
	String menuid, String page, String type, Model model) {
		KnowledgeInfo knowledge  = knowledgeInfoService.getById(id);
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
		model.addAttribute("type", type);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "repository/input/view";
	}
	
	/****
	 * 判断下载文件是否存在
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="isFileExist/{id}/",method = RequestMethod.GET)
	public void is_file_exist(HttpServletRequest request,HttpServletResponse response,@PathVariable String id){
		KnowledgeInfo knowledge = knowledgeInfoService.getById(id);
		try {
			if(knowledge != null){
				String savePath = request.getSession().getServletContext()
				.getRealPath("/")+knowledge.getPath();
				File file = new File(savePath);
				if(!file.exists()){
					//文件不存在
					response.getWriter().write("-1");
				}else{
					response.getWriter().write("0");
				}
			}else{
				//数据库中找不到对应记录
				response.getWriter().write("-2");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/****
	 * 下载附件
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="download/{id}/",method = RequestMethod.GET)
	public void download(HttpServletRequest request,HttpServletResponse response,@PathVariable String id){
		KnowledgeInfo knowledge  = knowledgeInfoService.getById(id);
		try {
			String savePath = request.getSession().getServletContext()
			.getRealPath("/")+knowledge.getPath();
			this.downloadFile(savePath, knowledge.getName(), request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
