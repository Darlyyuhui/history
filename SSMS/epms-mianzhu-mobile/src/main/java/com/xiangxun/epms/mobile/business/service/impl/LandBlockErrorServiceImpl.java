package com.xiangxun.epms.mobile.business.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.epms.mobile.business.dao.LandBlockErrorMapper;
import com.xiangxun.epms.mobile.business.domain.Files;
import com.xiangxun.epms.mobile.business.domain.LandBlock;
import com.xiangxun.epms.mobile.business.domain.LandBlockError;
import com.xiangxun.epms.mobile.business.service.FilesService;
import com.xiangxun.epms.mobile.business.service.LandBlockErrorService;
import com.xiangxun.epms.mobile.business.service.LandBlockService;
import com.xiangxun.epms.mobile.business.service.RegionService;
import com.xiangxun.epms.mobile.business.util.Constant;
import com.xiangxun.epms.mobile.business.util.Page;
import com.xiangxun.epms.mobile.util.DateUtils;

@Service
public class LandBlockErrorServiceImpl implements LandBlockErrorService {
	@Resource
	LandBlockErrorMapper landBlockErrorMapper;
	@Resource
	FilesService filesService;
	@Resource
	LandBlockService landBlockService;
	@Resource
	RegionService regionService;

	@Override
	public Page getListByCondition(LandBlockError it,int pageSize,int  pageNo) {
		List<LandBlockError> list = landBlockErrorMapper.getListByCondition(it);
		if (list != null && list.size() > 0) {
			for (LandBlockError info : list) {

				info=this.defaultValue(info);
			}
		}
		int totalCount=0;
		if(list!=null&&list.size()>0){
			totalCount = list.size();
		}
		List<?> subList= Page.sublist(pageNo, pageSize, totalCount, list);
		return Page.getPage(totalCount, subList, pageNo, pageSize);
	}

	@Override
	public int countList(LandBlockError it) {
		return landBlockErrorMapper.countList(it);
	}

	@Override
	public void updateByPrimaryKeySelective(LandBlockError it) {
		landBlockErrorMapper.updateByPrimaryKeySelective(it);

	}

	@Override
	public void insertSelective(LandBlockError it) {
		landBlockErrorMapper.insertSelective(it);

	}

	@Override
	public LandBlockError selectByPrimaryKey(String id) {
		LandBlockError info = landBlockErrorMapper.selectByPrimaryKey(id);
		List<Files> list = filesService.queryByBusinessId(id);
		if (info != null) {
			info.setFileList(list);
			List<LandBlock> b = landBlockService.findById(info.getLandBlockId());
			if (b != null && b.size() > 0) {
				info.setLandBlockName(b.get(0).getName());
			}
			if (info.getLatitude() == null) {
				info.setLatitude(new BigDecimal(0));
			}
			if (info.getLongitude() == null) {
				info.setLongitude(new BigDecimal(0));
			}
			if (info.getRegionName() == null) {
				info.setRegionName("绵竹市");
			}
			if (info.getDescribe() == null) {
				info.setDescribe("");
			}
		}
		return info;
	}

	@Override
	public void saveInfo(LandBlockError info, MultipartHttpServletRequest request) {
		String id = info.getId();
		if (id == null) {
			return;
		}
		filesService.saveFile(id, LandBlockError.class.getSimpleName(), false, Constant.UPLOAD_FILE_TYPE,
				Constant.UPLOAD_FILE_SIZE, request);
		landBlockErrorMapper.insertSelective(info);
	}
	private  LandBlockError defaultValue(LandBlockError info){
		if(info!=null){
			if (info.getRegionId() != null) {
				info.setRegionName(regionService.getFullNameById(info.getRegionId()));
			}
            if(info.getLandBlockName()==null){
            	info.setLandBlockName("");
            }
			if (info.getLatitude() == null) {
				info.setLatitude(new BigDecimal(0));
			}
			if (info.getLongitude() == null) {
				info.setLongitude(new BigDecimal(0));
			}
			/*if (info.getRegionName() == null) {
				info.setRegionName("绵竹市");
			}*/
			if (info.getDescribe() == null) {
				info.setDescribe("");
			}
			if (info.getErrorTime() == null) {
				try {
					info.setErrorTime(DateUtils.stringToDateThrowException("2017", "yyyy"));
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
			return info ;
		}
		return info;
	}

}
