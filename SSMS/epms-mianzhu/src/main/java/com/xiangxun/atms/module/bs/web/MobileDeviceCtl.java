package com.xiangxun.atms.module.bs.web;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.service.MobileDeviceService;
import com.xiangxun.atms.module.bs.vo.MobileDevice;
import com.xiangxun.atms.module.bs.vo.MobileDeviceSearch;

@Controller
@RequestMapping(value = "bs/mobile/device")
public class MobileDeviceCtl extends BaseCtl<MobileDevice, MobileDeviceSearch> {

	@Resource
	MobileDeviceService mobileDeviceService;

	@Override
	protected BaseService<MobileDevice, MobileDeviceSearch> getBaseService() {
		return mobileDeviceService;
	}
	
	@RequestMapping(value="list/{menuid}/")
    public String list(@PathVariable String menuid, Model model, @RequestParam(value = "sortType", defaultValue = "CREATE_TIME DESC") String sortType
    		, @RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
        logger.info("正在进行【移动端设备】数据列表查询。。。。。。");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		Page page = mobileDeviceService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
        return "bs/mobile/device/list";
    }

    @RequestMapping(value="add/{menuid}/")
    public String add(@PathVariable String menuid, Model model, HttpServletRequest request) {
        model.addAttribute("menuid",menuid);
        return "bs/mobile/device/add";
    }

    @RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
    @LogAspect(desc="添加【移动端设备】")
    public String doAdd(MobileDevice info, @PathVariable String menuid, RedirectAttributes redirectAttributes, Model model) {
    	info.setId(UuidGenerateUtil.getUUIDLong());
    	info.setCreateId(super.getCurrentUserId());
    	info.setCreateTime(new Date());
        mobileDeviceService.save(info);
        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/bs/mobile/device/list/"+menuid+"/";
    }

    @RequestMapping(value = "update/{menuid}/{id}/", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") String id, @PathVariable String menuid, String page, Model model) {
    	MobileDevice info = mobileDeviceService.getById(id);
        model.addAttribute("menuid",menuid);
        model.addAttribute("page", page);
        model.addAttribute("info",info);
        return "bs/mobile/device/update";
    }

    @RequestMapping(value = "doUpdate/{menuid}/", method = RequestMethod.POST)
    @LogAspect(desc="修改【移动端设备】")
    public String doUpdate(@ModelAttribute("info") MobileDevice info, @PathVariable String menuid, String page, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    	info.setUpdateId(super.getCurrentUserId());
    	info.setUpdateTime(new Date());
    	mobileDeviceService.updateByIdSelective(info);
        redirectAttributes.addFlashAttribute("message", "修改成功");
        return "redirect:/bs/mobile/device/list/"+menuid+"/?page="+page;
    }

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【移动端设备】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【移动端设备】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                mobileDeviceService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
	
}
