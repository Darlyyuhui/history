package com.xiangxun.atms.module.reg.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.files.service.FilesService;
import com.xiangxun.atms.module.reg.dao.LandRegMapper;
import com.xiangxun.atms.module.reg.service.LandRegService;
import com.xiangxun.atms.module.reg.vo.LandReg;
import com.xiangxun.atms.module.reg.vo.LandRegSearch;

@Service
public class LandRegServiceImpl extends AbstractBaseService<LandReg, LandRegSearch> implements LandRegService {
    @Resource
    private LandRegMapper landRegMapper;

    @Resource
   	FilesService filesService;
   	/**
   	 * 允许上传的类型
   	 */
   	private final String FILE_TYPE = ".jpg.jpeg.png.bmp";
    @Override
    public BaseMapper<LandReg, LandRegSearch> getBaseMapper() {
         return landRegMapper;
    }

	@Override
	public void checkById(String id, int status, String checkUser) {
		LandReg lr = new LandReg();
		lr.setId(id);
		lr.setCheckStatus(status);
		lr.setCheckUser(checkUser);
		lr.setCheckTime(new Date());
		this.updateByIdSelective(lr);
	}

	@Override
	public void saveInfo(LandReg info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		this.saveLbLink(id, info.getBlockIds());
		filesService.saveFile(id, filesService.getBusinessType(LandReg.class), FILE_TYPE, 20L, fileRequest);
		this.save(info);
	}

	@Override
	public void updateInfo(LandReg info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		this.saveLbLink(id, info.getBlockIds());
		filesService.saveFile(id, filesService.getBusinessType(LandReg.class), FILE_TYPE, 20L, fileRequest);
		this.updateByIdSelective(info);
	}
	
	/**
	 * 保存土壤登记与地块的关联关系
	 * @param regId
	 * @param lbArray
	 */
	private void saveLbLink(String regId, String blockIds) {
		if (StringUtils.isEmpty(blockIds)) {
			return;
		}
		String[] lbArray = blockIds.split(",");
		if (lbArray != null && lbArray.length > 0) {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("regId", regId);
			landRegMapper.deleteLandBlocksByRegId(args);
			
			for (String lbId : lbArray) {
				args.put("lbId", lbId);
				landRegMapper.saveLandBlocksLink(args);
			}
		}
	}

	@Override
	public List<String> getLandBlocksByRegId(String regId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("regId", regId);
		
		List<Map<String, Object>> list = landRegMapper.getLandBlocksByRegId(args);
		List<String> rList = new ArrayList<String>();
		for (Map<String, Object> map : list) {
			rList.add(map.get("BLOCK_ID").toString());
		}
		return rList;
	}

	@Override
	public List<LandReg> getInfoByAnalysis() {
		return landRegMapper.getInfoByAnalysis();
	}
    
}