package com.xiangxun.atms.module.bs.web;

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
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.bs.service.RepairStageService;
import com.xiangxun.atms.module.bs.vo.RepairStage;
import com.xiangxun.atms.module.bs.vo.RepairStageSearch;

@Controller
@RequestMapping(value = "bs/repairstage")
public class RepairStageCtl extends BaseCtl<RepairStage, RepairStageSearch> {

	@Resource
	RepairStageService repairStageService;

	@Override
	protected BaseService<RepairStage, RepairStageSearch> getBaseService() {
		return repairStageService;
	}
	
	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid, Model model) {
		model.addAttribute("treeData", repairStageService.makeTreeData());
		return "bs/repairstage/list";
	}
	
	@RequestMapping(value = "add/{menuid}/{pid}/")
	public String add(@PathVariable("menuid") String menuid
			, @PathVariable("pid") String pid, Model model, HttpServletRequest request, RedirectAttributes attr) {
		
		if (StringUtils.isEmpty(pid)) {
			attr.addFlashAttribute("message", "未选择上级节点。");
			return "redirect:/bs/repairstage/list/"+menuid+"/";
		}
		String pname = "";
		if ("0".equals(pid)) {
			pname = "土壤修复阶段";
		} else {
			RepairStage prs = repairStageService.getById(pid);
			if (prs == null) {
				attr.addFlashAttribute("message", "上级节点信息获取错误。");
				return "redirect:/bs/repairstage/list/"+menuid+"/";
			}
			pname = prs.getName();
		}
		
		model.addAttribute("pid", pid);
		model.addAttribute("pname", pname);
		model.addAttribute("menuid", menuid);
		return "bs/repairstage/add";
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【土壤修复阶段】")
	public String doAdd(@PathVariable String menuid, RepairStage info, String isContinue, RedirectAttributes attr) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		repairStageService.save(info);
		attr.addFlashAttribute("message", "新增成功");
		if ("1".equals(isContinue)) {
			return "redirect:/bs/repairstage/add/"+menuid+"/"+info.getPid()+"/";
		}
		return "redirect:/bs/repairstage/list/"+menuid+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, Model model) {
		RepairStage rs = repairStageService.getById(id);
		String pname = "";
		if ("0".equals(rs.getPid())) {
			pname = "土壤修复阶段";
		} else {
			RepairStage plt = repairStageService.getById(rs.getPid());
			pname = plt.getName();
		}
		
		model.addAttribute("pname", pname);
		model.addAttribute("info", rs);
		model.addAttribute("menuid", menuid);
		return "bs/repairstage/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【土壤修复阶段】")
	public String doUpdate(@PathVariable String menuid, @ModelAttribute("info")RepairStage info, RedirectAttributes attr) {
		repairStageService.updateByIdSelective(info);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/bs/repairstage/list/"+menuid+"/";
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【土壤修复阶段】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【土壤修复阶段】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
            	if ("0".equals(string)) {
            		continue;
            	}
                repairStageService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
	
    @RequestMapping(value = "showType/")
	public String list(String idElement, String nameElement, String isCallback, Model model) {
		model.addAttribute("treeData", repairStageService.makeTreeData());
		model.addAttribute("idElement", idElement);
    	model.addAttribute("nameElement", nameElement);
    	model.addAttribute("isCallback", isCallback==null?"0":isCallback);
		return "bs/repairstage/show_type";
	}
    
}
