package com.xiangxun.atms.common.deptment.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Table;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.deptment.vo.DepartmentSearch;
import com.xiangxun.atms.common.deptment.vo.DepartmentSearch.Criteria;
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.common.system.service.ParamsService;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.service.DeviceInfoService;

/***
 * 组织机构
 * @author zhouhaij
 * @Apr 16, 2013 5:39:59 PM
 */
@Controller
@RequestMapping(value = "system/org")
@SessionAttributes("plateArea")
public class DepartmentCtl extends BaseCtl {

	@Resource
	DepartmentService departmentService;
	
	@Resource
	DicService dicService;
	
	@Resource
	DeviceInfoService deviceInfoService;
	
	@Resource
	Cache cache;

	@Resource
	private Validator validator;

	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid, ModelMap model,ServletRequest request) {
		logger.info("正在进行设备列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		List<Department> departs = new ArrayList<Department>();
		String name="",code="";
		if(searchParams.isEmpty()){
		   departs = departmentService.getAllChildDepartment("00");
		}else{
			DepartmentSearch search = new DepartmentSearch();
			Criteria criteria = search.createCriteria();
			if(searchParams.get("name")!=null){
				criteria = criteria.andNameLike("%"+searchParams.get("name").toString()+"%");
				name = searchParams.get("name").toString();
			}
			
			if(searchParams.get("code")!=null){
				criteria = criteria.andCodeEqualTo(searchParams.get("code").toString());
				code = searchParams.get("code").toString();
			}
			List<Department> newList = new ArrayList<Department>();
			List<Department> resultList = departmentService.selectByExample(search);
			for (Department department : resultList) {
				department.setParentid("0");
				List<Department> childlist = departmentService.getChildren(department.getId());
				if (childlist.size() > 0) {
					newList.add(department);
				}
			}
			if (newList.size() > 0) {
				for (Department department : newList) {
					department.setParentid("0");
					List<Department> subList = departmentService.getAllChildDepartment(department.getId());
					List<Department> childlist = departmentService.getChildren(department.getId());
					if (childlist.size() > 0) {
						departs.addAll(subList);
					}
				}
			}else {
				departs.addAll(resultList);
			}
			
		}
		
		//行政区域编码
		List<Dic> plateArea = dicService.getDicByType(DicType.PLACEAREA_CODE);
		model.addAttribute("plateArea", plateArea);
		
		model.addAttribute("menuid", menuid);
		model.put("list", departs);
		model.put("name", name);
		model.put("orgcode",code);
		return "system/org/list";
	}


	/***
	 * 异步树形结构服务
	 * 
	 * @param id
	 * @param resp
	 */
	@RequestMapping(value = "showTree/", method = RequestMethod.POST)
	public void showTree(@RequestParam(value = "id", defaultValue = "0") String id, ServletResponse resp,HttpServletRequest req) {
		List<Department> departList = departmentService.getChildren(id);
		Collections.sort(departList, new Comparator<Department>() {
			@Override
			public int compare(Department o1, Department o2) {
				return o1.getCode().compareTo(o2.getCode());
			}
		});
		JsonArray jsonArray = new JsonArray();
		for (Department department : departList) {
			JsonObject json = new JsonObject();
			json.addProperty("id", department.getId());
			json.addProperty("pid", department.getParentid());
			json.addProperty("isParent", departmentService.hasChild(department.getId()));
			if (!departmentService.hasChild(department.getId())) {
				json.addProperty("icon",req.getContextPath()+"/images/3-3.png");
			}
			json.addProperty("name", department.getName());
			// 组织机构根节点指定图标
			json.addProperty("iconClose", req.getContextPath()+"/images/1-1.png");
			json.addProperty("iconOpen", req.getContextPath()+"/images/2-2.png");
			jsonArray.add(json);
		}
		logger.debug("response json:{0}", jsonArray.toString());
		try {
			resp.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/***
	 * 同步树形结构服务
	 * 
	 * @param id
	 * @param resp
	 */
	@RequestMapping(value = "getTreeNode/", method = RequestMethod.GET)
	public void getTreeNode(ServletResponse resp) {
		List<Department> departList = departmentService.findAll();
		JsonArray jsonArray = new JsonArray();
		for (Department department : departList) {
			JsonObject json = new JsonObject();
			json.addProperty("id", department.getId());
			json.addProperty("pId", department.getParentid());
			json.addProperty("code", department.getCode());
			json.addProperty("name", department.getName());
			jsonArray.add(json);
		}
		logger.debug("response json:{0}", jsonArray.toString());
		try {
			resp.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 同步树形结构服务
	 * 
	 * @param id
	 * @param resp
	 */
	@RequestMapping(value = "showTreeNode/{menuid}/", method = RequestMethod.GET)
	public void showTreeNode(@PathVariable String menuid, ServletResponse resp) {
		JsonArray jsonArray = departmentService.getDeptJsonArray(menuid);
		logger.debug("response json:{0}", jsonArray.toString());
		try {
			resp.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "add/{id}/{menuid}/", method = RequestMethod.GET)
	public String add(@PathVariable("id") String id, @PathVariable("menuid") String menuid,Model model){
		Department  depart = departmentService.getById(id);
		String middle ="";
		String parentcode = depart.getCode().substring(6, 9);
		//获取孩子个数
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parentid", id);
		int counts = departmentService.countList(map)+1;
		//小于10加2个0，保证3位
		if(counts<10){
			middle="00"+counts;
		}
		//大于10小于100，加1个0，保证3位数
		if(counts>10 && counts<100){
			middle="0"+counts;
		}
		String code = parentcode+middle;
		model.addAttribute("org", depart);
		model.addAttribute("code", code);
		model.addAttribute("menuid", menuid);
		return "system/org/add";
	}
	
	@RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
	@LogAspect(desc = "添加新部门")
	public String doAdd(Department department, ModelMap modal, RedirectAttributes redirectAttributes, @PathVariable String menuid) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<Department>> failures = validator.validate(department);
		if (!failures.isEmpty()) {
			redirectAttributes.addAttribute("message", "部门添加失败");
		}
		String areacode = department.getAreaCode();
		department.setCode(areacode + department.getCode());
		department.setId(UuidGenerateUtil.getUUIDLong());
		department.setStatus("0");
		departmentService.save(department);
		redirectAttributes.addFlashAttribute("message", "添加成功");

		return "redirect:/system/org/list/" + menuid + "/";
	}

	/***
	 * 判断编码是否重复
	 * 
	 * @param req
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "codeExist")
	@ResponseBody
	public String codeExist(HttpServletRequest req, @RequestParam(value = "code") String code) {
		Department depart = departmentService.getOneByCode(ParamsService.SYSTEM_PARAMS.get("areacode") + code);
		String returnStr = Boolean.FALSE.toString();
		if (depart == null) {
			returnStr = Boolean.TRUE.toString();
		}
		return returnStr;
	}

	/***
	 * 判断名称是否重复
	 * 
	 * @param req
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "nameExist")
	@ResponseBody
	public String nameExist(HttpServletRequest req, @RequestParam(value = "name") String name) {
		Department depart = departmentService.getOneByName(name.trim());
		String returnStr = Boolean.FALSE.toString();
		if (depart == null) {
			returnStr = Boolean.TRUE.toString();
		}
		return returnStr;
	}

	/***
	 * 获取待修改部门信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, @PathVariable("menuid") String menuid, String page, Model model) {
		model.addAttribute("org", departmentService.getById(id));
		model.addAttribute("menuid", menuid);
		return "system/org/update";
	}

	/***
	 * 修改部门
	 * 
	 * @param role
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc = "修改部门")
	public String doUpdate(@ModelAttribute("preloadDepartment") Department department, String page, String menuid, RedirectAttributes redirectAttributes) {
		departmentService.updateByIdSelective(department);
		// 刷新缓存
		departmentService.refreshCache();
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/system/org/list/" + menuid + "/?page=" + page;
	}

	/***
	 * 删除部门
	 * 
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value = "delete/{ids}/", method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc = "删除部门")
	public ResponseEntity delete(@PathVariable String ids) {
		ResponseEntity entity = new ResponseEntity();
		try {
			logger.info("正在进行部门数据删除。。。。。。");
			String[] id = ids.split(",");
//			for (String string : id) {
//				departmentService.deleteById(string);
//			}
			//========================== add by kouyunhao 2014-04-04 遍历所要删除部门的子部门，如果存在设备信息则阻止删除，防止垃圾数据产生============ start
			StringBuffer returnBuffer = null;
			for (String string : id) {
				boolean hasChild = departmentService.hasChild(string);
				if(hasChild){
					entity.setResult("hasChild");
					return entity;
				}else{
					List<DeviceInfo> deviceList = deviceInfoService.findDevlistByOrgId(string);
					if(deviceList != null && deviceList.size() != 0){
						returnBuffer = new StringBuffer();
						for(DeviceInfo device : deviceList){
							returnBuffer.append("[");
							returnBuffer.append(device.getName());
							returnBuffer.append("]");
							returnBuffer.append(",");
						}
					}
				}
			}
			if(returnBuffer != null){
				entity.setResult("exist");
				entity.setMessage(org.apache.commons.lang.StringUtils.removeEnd(returnBuffer.toString(), ","));
				return entity;
			}else{
				for (String string : id) {
					departmentService.deleteById(string);
				}
			}
			//========================== add by kouyunhao 2014-04-04 遍历所要删除部门的子部门，如果存在设备信息则阻止删除，防止垃圾数据产生============ end
			// 刷新缓存
			departmentService.refreshCache();
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setMessage("该部门已被其他数据所引用，无法删除，请先删除部门下的用户和设备后再试");
			entity.setResult("error");
			return entity;
		}
	}

	/***
	 * 先根据form的id从数据库查出对象，再把Form提交的内容绑定到该对象上
	 * 
	 * @param id
	 * @return
	 */
	@ModelAttribute("preloadDepartment")
	public Department getDepartment(@RequestParam(value = "id", required = false) String id) {
		if (id != null) {
			return departmentService.getById(id);
		}
		return null;
	}

	/***
	 * 验证部门编号是否存在
	 * 
	 * @param code
	 */
	@RequestMapping(value = "dept_code_check/{code}/", method = RequestMethod.GET)
	@ResponseBody
	@LogAspect(desc = "检查部门编码是否存在")
	public ResponseEntity checkDeptCode(@PathVariable String code) {
		// 刷新缓存
		departmentService.refreshCache();
		ResponseEntity entity = new ResponseEntity();
		try {
			DepartmentSearch search = new DepartmentSearch();
			search.createCriteria().andCodeEqualTo(code.trim());
			List<Department> deptList = departmentService.selectByExample(search);
			if (deptList != null && deptList.size() != 0) {
				entity.setResult("error");
			} else {
				entity.setResult("ok");
			}
			return entity;
		} catch (Exception e) {
			entity.setMessage("该部门编码已存在！");
			entity.setResult("error");
			return entity;
		}
	}

	/***
	 * 验证部门名称是否存在
	 * 
	 * @param name
	 */
	@RequestMapping(value = "dept_name_check/{name}/", method = RequestMethod.GET)
	@ResponseBody
	@LogAspect(desc = "检查部门名称是否存在")
	public ResponseEntity checkDeptName(@PathVariable String name) {
		// 刷新缓存
		departmentService.refreshCache();
		ResponseEntity entity = new ResponseEntity();
		try {
			DepartmentSearch search = new DepartmentSearch();
			search.createCriteria().andNameEqualTo(StringUtils.getCN(name.trim()));
			List<Department> deptList = departmentService.selectByExample(search);
			if (deptList != null && deptList.size() != 0) {
				entity.setResult("error");
			} else {
				entity.setResult("ok");
			}
			return entity;
		} catch (Exception e) {
			entity.setMessage("该部门名称已存在！");
			entity.setResult("error");
			return entity;
		}
	}

	@RequestMapping(value = "getall")
	@ResponseBody
	public List<Department> getAllDept() {
		List<Department> list = departmentService.findAll();
		return list;
	}
	
	@RequestMapping(value = "getbyid/{id}")
	@ResponseBody
	public Department getById(@PathVariable String id) {
		Department org = departmentService.getById(id);
		return org;
	}
	
	/**
	 * 查询所有部门信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getallbycache")
	@ResponseBody
	public Map<String, String> getAllDepartment() {
		Table<String, String, String> table = (Table<String, String, String>)cache.get(Department.class.getSimpleName());
		return table.column(Department.class.getSimpleName());
	}
	
}
