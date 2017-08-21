package com.xiangxun.atms.module.geoServer.web;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xiangxun.atms.module.geoServer.domain.CrossInfo;
import com.xiangxun.atms.module.geoServer.mapper.CrossInfoMapper;
@Controller
@RequestMapping("openmap/crossInfo")
public class CrossInfoCtl {
@Resource 
CrossInfoMapper crossInfoMapper; 
@RequestMapping(value="search")
@ResponseBody
public List<CrossInfo> searchCross(){
	return crossInfoMapper.searchCrossInfo();
    }
}
