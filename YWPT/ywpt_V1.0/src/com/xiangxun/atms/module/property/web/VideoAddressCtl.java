package com.xiangxun.atms.module.property.web;


import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.property.domain.VideoAddressInfo;
import com.xiangxun.atms.module.property.service.VideoAddressService;

/***
 * 监控设备配置信息维护
 * @author YanTao
 * @Apr 26, 2013 5:39:59 PM
 */
@Controller
@RequestMapping(value="property/videoaddress")
public class VideoAddressCtl extends BaseCtl{
	
	@Resource
	VideoAddressService videoAddressService;

	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行监控设备配置信息列表查询。。。。。。");
		
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        

        //解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
      	SessionParameter sp = new SessionParameter();
      	sp.updateSessionMap(request, menuid, searchParams);
		
      	Page page = videoAddressService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
        //Page page = videoAddressService.getVideoAddressByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);

		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		VideoAddressInfo videoAddressInfo = new VideoAddressInfo();
		try {
			BeanUtils.populate(videoAddressInfo, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("videoAddressInfo", videoAddressInfo);
		return "property/videoaddress/list";
	}
	
	/***
	 * 删除一个FTP服务器
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除了一个监控设备配置")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在删除了一个监控设备配置。。。。。。");
			String[] id = ids.split(",");
			for (String string : id) {
				videoAddressService.deleteById(string);
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/***
	 * 添加一个FTP服务器
	 * @param ftpInfo
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加一个监控设备配置")
	public String doAdd(VideoAddressInfo videoAddressInfo,String menuid,RedirectAttributes redirectAttributes){
		videoAddressInfo.setId(UuidGenerateUtil.getUUID());
		videoAddressService.save(videoAddressInfo);
		redirectAttributes.addFlashAttribute("message","监控设备配置添加成功");
		return "redirect:/property/videoaddress/list/"+menuid+"/";
	}

	/***
	 * 获取待修改FTP服务器信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		model.addAttribute("videoAddressInfo",videoAddressService.getById(id));
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "property/videoaddress/update";
	}
	/***
	 * 获取详情信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		model.addAttribute("videoAddressInfo",videoAddressService.getById(id));
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "property/videoaddress/view";
	}
	
	/***
	 * 修改FTP服务器信息
	 * @param ftpInfo
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改F监控设备配置信息")
	public String doUpdate(@ModelAttribute("preloadRole")VideoAddressInfo videoAddressInfo,String page,String menuid,RedirectAttributes redirectAttributes) {
		videoAddressService.updateByIdSelective(videoAddressInfo);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/property/videoaddress/list/"+menuid+"/?page="+page+"&isgetsession=1";
	}
	
	
	
	/***
	 * 先根据form的id从数据库查出FtpInfo对象
	 * @param id
	 * @return
	 */
	@ModelAttribute("preloadRole")
	public VideoAddressInfo getvideoAddressInfo(@RequestParam(value = "id", required = false) String id) {
		if (id != null) {
			return videoAddressService.getById(id);
		}
		return null;
	}
	
}
