package com.xiangxun.atms.module.eventalarm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.service.PublicMethodService;

@Service("publicMethodService")
public class PublicMethodServiceImpl implements PublicMethodService {

	//状态颜色数组
	private String statuscolor[] = { "#ABF0FC", "#A6D94E","#FF0033","#44C6F8","#FF00FF", 
			"#2C2255","#008000","#8E468E", "#588526", "#B3AA00", "#008ED6",
			"#9D080D", "#A186BE","#346DB2", "#C13A36", "#A02F2B", "#7C9D35",
			"#6A4A8D", "#2B8CAC","#D2721E", "#7792C1", "#BF7B7A", "#A6BC7C","#9183A7" };
	
	//工单状态数组：0-已派发；1-已接收；2-已退回；3-已上报；4-遗留中；5-已关闭。8-已完成
	private String statusname[] = {"已派发","已接收","已退回","已转派","已上报","遗留中","已关闭","已评估","已完成"};
	
	@Override
	public void setStatusColor(Page page) {
		//动态构造状态信息
		Object obj = page.getResult();
		if(obj!=null && obj instanceof List){
			@SuppressWarnings("unchecked")
			List<WorkorderInfo> list = (List<WorkorderInfo>)obj;
			if(list != null && list.size() != 0){
				for (WorkorderInfo resource : list) {
					setStatusColor(resource);
				}
			}
		}
	}

	@Override
	public void setStatusColor(WorkorderInfo workorder) {
		StringBuffer htmlBuffer = new StringBuffer();
		for(int i = 0; i < statuscolor.length; i++){
			if(workorder.getStatus().equals(String.valueOf(i))){
				htmlBuffer.append("<font style='background-color: "+statuscolor[i]+";color: white;'>");
				htmlBuffer.append(statusname[i]);
				htmlBuffer.append("</font>");
			}
		}
		workorder.setStatusHtml(htmlBuffer.toString());
	}

}
