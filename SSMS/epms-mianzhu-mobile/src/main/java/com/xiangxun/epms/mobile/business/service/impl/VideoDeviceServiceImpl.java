package com.xiangxun.epms.mobile.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.epms.mobile.business.dao.VideoDeviceMapper;
import com.xiangxun.epms.mobile.business.domain.VideoDevice;
import com.xiangxun.epms.mobile.business.service.FilesService;
import com.xiangxun.epms.mobile.business.service.VideoDeviceService;
@Service
public class VideoDeviceServiceImpl implements VideoDeviceService {
    @Resource
    VideoDeviceMapper videoDeviceMapper;
    @Resource
	FilesService filesService;
	@Override
	public List<VideoDevice> findAll() {
		List<VideoDevice> list= new ArrayList<VideoDevice>();
		list = videoDeviceMapper.findAll();
		if(list!=null&&list.size()>0){
			 for(VideoDevice info:list){
				 info.setFiles(filesService.queryByBusinessId(info.getId()));
			  }
		   }
	    
		return list;
	}

}
