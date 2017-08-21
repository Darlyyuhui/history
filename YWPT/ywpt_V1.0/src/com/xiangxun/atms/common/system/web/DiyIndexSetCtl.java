package com.xiangxun.atms.common.system.web;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.common.system.service.SysIndexModelService;
import com.xiangxun.atms.common.system.service.SysIndexModelSetService;
import com.xiangxun.atms.common.system.vo.SysIndexModel;
import com.xiangxun.atms.common.system.vo.SysIndexModelSet;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
/***
 * 自定义首页显示设置
 * @author yantao
 * @2014-10-11
 */
@Controller
@RequestMapping("system/diyindexset")
public class DiyIndexSetCtl extends BaseCtl{

	@Resource
	SysIndexModelService sysIndexModelService;
	
	@Resource
	SysIndexModelSetService sysIndexModelSetService;
	
	@RequestMapping(value = "indexset/{menuid}/",method = RequestMethod.GET)
	public String indexSet(@PathVariable("menuid") String menuid,ModelMap model){
		
		List<SysIndexModel> modulListLeft = new ArrayList<SysIndexModel>();
		List<SysIndexModel> modulListCenter = new ArrayList<SysIndexModel>();
		List<SysIndexModel> modulListRight = new ArrayList<SysIndexModel>();
		
		//先判断用户是否配置过自定义首页
		SysIndexModelSet setinfo = sysIndexModelSetService.selectByUserId(getCurrentUserId());
		if(setinfo!=null){
			String leftstr = setinfo.getLeft();
			String centerstr = setinfo.getCenter();
			String rightstr = setinfo.getRight();
			if(leftstr!=null && !"".equals(leftstr)){
				String[] leftArray = leftstr.substring(0,leftstr.length()-1).split(",");
				for(int i=0;i<leftArray.length;i++){
					SysIndexModel m = sysIndexModelService.getByCode(leftArray[i]);
					modulListLeft.add(m);
				}
			}
			if(centerstr!=null && !"".equals(centerstr)){
				String[] centerArray = centerstr.substring(0,centerstr.length()-1).split(",");
				for(int i=0;i<centerArray.length;i++){
					SysIndexModel m = sysIndexModelService.getByCode(centerArray[i]);
					modulListCenter.add(m);
				}
			}
			if(rightstr!=null && !"".equals(rightstr)){
				String[] rightArray = rightstr.substring(0,rightstr.length()-1).split(",");
				for(int i=0;i<rightArray.length;i++){
					SysIndexModel m = sysIndexModelService.getByCode(rightArray[i]);
					modulListRight.add(m);
				}
			}
		}else{
			//如果没设置过 则按照默认的组件信息表显示默认的
			List<SysIndexModel> modulList = sysIndexModelService.selectByIsShow("1");
			if(modulList!=null && modulList.size()>0){
				for(int i=0;i<modulList.size();i++){
					SysIndexModel modul = modulList.get(i);
					if("1".equals(modul.getIsshow())){
						if("left".equals(modul.getLayout())){
							modulListLeft.add(modul);
						}
						if("center".equals(modul.getLayout())){
							modulListCenter.add(modul);
						}
						if("right".equals(modul.getLayout())){
							modulListRight.add(modul);
						}
					}
				}
			}
		}
		model.addAttribute("modulListLeft", modulListLeft);
		model.addAttribute("modulListCenter", modulListCenter);
		model.addAttribute("modulListRight", modulListRight);
		
		return "system/diyindexset/index_set";
	}
	
	/***
	 * 保存首页组件配置
	 * @return
	 */
	@RequestMapping(value = "saveindexset", method = RequestMethod.POST)
	@LogAspect(desc="保存首页组件配置")
	public String saveindexset(String selecta,String selectb,String selectc,String menuid,RedirectAttributes redirectAttributes){
		SysIndexModelSet sysIndexModelSet = new SysIndexModelSet();
		sysIndexModelSet.setId(UuidGenerateUtil.getUUIDLong());
		sysIndexModelSet.setLeft(selecta);
		sysIndexModelSet.setCenter(selectb);
		sysIndexModelSet.setRight(selectc);
		sysIndexModelSet.setInserttime(new java.util.Date());
		//获得当前登陆用户的ID
		sysIndexModelSet.setUserid(getCurrentUserId());
		sysIndexModelSetService.save(sysIndexModelSet);
		return "redirect:/system/diyindexset/indexset/"+menuid+"/";
	}
	
}
