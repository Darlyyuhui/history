package com.xiangxun.atms.module.repair.service.impl;

import java.util.ArrayList;
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
import com.xiangxun.atms.module.repair.dao.RepairProjectMapper;
import com.xiangxun.atms.module.repair.service.RepairProjectService;
import com.xiangxun.atms.module.repair.vo.RepairProject;
import com.xiangxun.atms.module.repair.vo.RepairProjectSearch;

@Service
public class RepairProjectServiceImpl extends AbstractBaseService<RepairProject, RepairProjectSearch> implements RepairProjectService {
    @Resource
    private RepairProjectMapper repairProjectMapper;

    @Resource
   	FilesService filesService;
   	/**
   	 * 允许上传的类型
   	 */
   	private final String FILE_TYPE = ".jpg.jpeg.png.bmp.pdf";
    
    @Override
    public BaseMapper<RepairProject, RepairProjectSearch> getBaseMapper() {
         return repairProjectMapper;
    }

	@Override
	public void saveProBlockLink(String proId, String[] blockId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("proId", proId);
		for (String bId : blockId) {
			if (StringUtils.isEmpty(bId)) {
				continue;
			}
			args.put("blockId", bId);
			repairProjectMapper.saveProBlockLink(args);
		}
	}

	@Override
	public int deleteById(String id) {
		this.deleteProBlockLink(id);
		return super.deleteById(id);
	}

	@Override
	public void deleteProBlockLink(String proId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("proId", proId);
		repairProjectMapper.deleteProBlockLink(args);
	}

	@Override
	public List<String> getLandBlocksById(String proId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("proId", proId);
		List<Map<String, Object>> list = repairProjectMapper.getLandBlocksById(args);
		List<String> rList = new ArrayList<String>();
		for (Map<String, Object> map : list) {
			rList.add(map.get("BLOCK_ID").toString());
		}
		return rList;
	}

	@Override
	public void saveInfo(RepairProject info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		this.saveLink(id, info.getBlockIds());
		filesService.saveFile(id, filesService.getBusinessType(RepairProject.class), FILE_TYPE, 50L, fileRequest);
		this.save(info);
	}
	
	/**
	 * 保存关系表
	 * @param proId
	 * @param blockIds
	 */
	private void saveLink(String proId, String blockIds) {
		if (StringUtils.isEmpty(blockIds)) {
			return;
		}
		String[] ids = blockIds.split(",");
		this.deleteProBlockLink(proId);
		this.saveProBlockLink(proId, ids);
	}

	@Override
	public void updateInfo(RepairProject info, MultipartHttpServletRequest fileRequest) {
		String id = info.getId();
		this.saveLink(id, info.getBlockIds());
		filesService.saveFile(id, filesService.getBusinessType(RepairProject.class), FILE_TYPE, 50L, fileRequest);
		this.updateByIdSelective(info);
	}
    
}