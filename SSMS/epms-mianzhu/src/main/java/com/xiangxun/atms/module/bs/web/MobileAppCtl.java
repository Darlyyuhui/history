package com.xiangxun.atms.module.bs.web;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.service.MobileAppService;
import com.xiangxun.atms.module.bs.vo.MobileApp;
import com.xiangxun.atms.module.bs.vo.MobileAppSearch;

@Controller
@RequestMapping(value = "bs/mobile/app")
public class MobileAppCtl extends BaseCtl<MobileApp, MobileAppSearch> {

	@Resource
	MobileAppService mobileAppService;

	@Override
	protected BaseService<MobileApp, MobileAppSearch> getBaseService() {
		return mobileAppService;
	}
	
	@RequestMapping(value="list/{menuid}/")
    public String list(@PathVariable String menuid, Model model, @RequestParam(value = "sortType", defaultValue = "CREATE_TIME DESC") String sortType
    		, @RequestParam(value = "page", defaultValue = "0") int pageNumber, HttpServletRequest request) {
        logger.info("正在进行【移动端APP】数据列表查询。。。。。。");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		Page page = mobileAppService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
        return "bs/mobile/app/list";
    }

    @RequestMapping(value="add/{menuid}/")
    public String add(@PathVariable String menuid, Model model, HttpServletRequest request) {
        model.addAttribute("menuid",menuid);
        return "bs/mobile/app/add";
    }

    @RequestMapping(value = "doAdd/{menuid}/", method = RequestMethod.POST)
    @LogAspect(desc="添加【移动端APP】")
    public String doAdd(MobileApp info, @PathVariable String menuid
    		, RedirectAttributes redirectAttributes, MultipartHttpServletRequest fileRequest) {
    	info.setId(UuidGenerateUtil.getUUIDLong());
    	info.setCreateId(super.getCurrentUserId());
    	info.setCreateTime(new Date());
    	mobileAppService.saveInfo(info, fileRequest);
        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/bs/mobile/app/list/"+menuid+"/";
    }
    
    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【移动端APP】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【移动端APP】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
            	mobileAppService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
	
}
