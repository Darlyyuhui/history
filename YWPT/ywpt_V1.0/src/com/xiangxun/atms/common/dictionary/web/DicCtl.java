package com.xiangxun.atms.common.dictionary.web;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.type.DicTypeConverter;
import com.xiangxun.atms.common.dictionary.type.DicTypeEditor;
import com.xiangxun.atms.common.dictionary.type.DicTypeNode;
import com.xiangxun.atms.common.dictionary.type.DicTypeParser;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;

/***
 * 数据字典
 * @author zhouhaij
 * @Apr 16, 2013 5:39:59 PM
 */
@Controller
@RequestMapping(value="system/dic")
public class DicCtl extends BaseCtl{

	@Resource
	DicService dicService;
	
	DicTypeParser parser = new DicTypeParser();
	
	@InitBinder
	public void initBinder(WebDataBinder arg0, WebRequest arg1) {
		arg0.registerCustomEditor(DicType.class,new DicTypeEditor());
	}

	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{groupid}/{menuid}/")
	@LogAspect(desc="查询字典")
	public String list(@PathVariable String groupid,@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "TYPE,CODE") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		logger.info("正在进行数据字典列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("types", parser.getTypesByGroupId(groupid));
		Page page = dicService.getByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
		
		model.addAttribute("menuid", menuid);
		model.addAttribute("groupid", groupid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		Dic dic = new Dic();
		try {
			ConvertUtils.register(new DicTypeConverter(),DicType.class);
			//将查询的map转换成对象
			BeanUtils.populate(dic, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		/***
		 * 构造类型下拉框数据
		 */
		List<DicTypeNode> secs = parser.getDicTypesByGroupId(groupid);
		model.addAttribute("dic", dic);
		request.getSession().setAttribute("dic_sec", secs);
		return "system/dic/list";
	}
	
	/***
	 * 删除字典
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除字典")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在进行字典数据删除。。。。。。");
			String[] id = ids.split(",");
			for (String string : id) {
				dicService.deleteById(string);
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/***
	 * 添加字典
	 * @param dic
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加字典")
	public String doAdd(Dic dic,String menuid,String groupid,@RequestParam("search_type") String type,RedirectAttributes redirectAttributes){

		logger.info("menuid:"+menuid);
		dic.setId(UuidGenerateUtil.getUUIDLong());
		dicService.save(dic);
		
		redirectAttributes.addFlashAttribute("message","添加成功");
		return "redirect:/system/dic/list/"+groupid+"/"+menuid+"/?search_type="+type;
	}

	/***
	 * 判断编码是否重复
	 * @param req
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "codeExist")
	@ResponseBody
	public String codeExist(HttpServletRequest req, @RequestParam(value = "code") String code,@RequestParam(value = "type") String type) {
		Dic dic = dicService.getDicByCodeAndType(code, DicType.valuesOf(type));
		String returnStr = Boolean.FALSE.toString();
		if (dic == null) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(StringUtils.isNotBlank(oper)){
			if(code.equals(oper)){
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}
	
	/***
	 * 获取下个编码
	 * @param req
	 * @param code
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "nextCode")
	@ResponseBody
	public String getNextCode(HttpServletRequest req,@RequestParam(value = "type") String type) {
		String code = dicService.getNextCodeByType(DicType.valuesOf(type));
		if(code.length()<3){
			try {
				Integer.parseInt(code);
				int prefix = 3 - code.length();
				StringBuffer sb = new StringBuffer();
	             for (int i =0 ;i<prefix;i++){
		              sb.append("0");
	             }
				sb.append(code);
				return sb.toString();
			}catch (NumberFormatException e){

			}

		}
		return code;
	}
	
	/***
	 * 获取待修改字典
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{groupid}/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("groupid") String groupid,@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		Dic dic = dicService.getById(id);
		model.addAttribute("dic",dic);
		model.addAttribute("type",dic.getType().toString());
		model.addAttribute("groupid",groupid);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "system/dic/update";
	}

	/***
	 * 查看详情
	 * @param groupid
	 * @param id
	 * @param menuid
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "showView/{groupid}/{id}/{menuid}/", method = RequestMethod.GET)
	public String showView(@PathVariable("groupid") String groupid,@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		Dic dic = dicService.getById(id);
		model.addAttribute("dic",dic);
		model.addAttribute("type",dic.getType().toString());
		model.addAttribute("groupid",groupid);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "system/dic/showview";
	}
	
	/***
	 * 修改字典
	 * @param role
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改字典")
	public String doUpdate(@ModelAttribute("preloadDic") Dic dic,String page,String menuid,String groupid,RedirectAttributes redirectAttributes) {
		String type = dic.getType().toString();
		dicService.updateByIdSelective(dic);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/system/dic/list/"+groupid+"/"+menuid+"/?page="+page+"&search_type="+type;
	}
	
	
	/***
	 * 先根据form的id从数据库查出对象，再把Form提交的内容绑定到该对象上
	 * @param id
	 * @return
	 */
	@ModelAttribute("preloadDic")
	public Dic getDic(@RequestParam(value = "id", required = false) String id) {
		if (id != null) {
			return dicService.getById(id);
		}
		return null;
	}
	
}
