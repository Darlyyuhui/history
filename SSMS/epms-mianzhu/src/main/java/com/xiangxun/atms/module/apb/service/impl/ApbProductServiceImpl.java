package com.xiangxun.atms.module.apb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xiangxun.atms.core.service.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.module.apb.cache.ApbProductCache;
import com.xiangxun.atms.module.apb.dao.ApbProductMapper;
import com.xiangxun.atms.module.apb.service.ApbProductService;
import com.xiangxun.atms.module.apb.vo.ApbProduct;
import com.xiangxun.atms.module.apb.vo.ApbProductSearch;
import com.xiangxun.atms.module.files.service.FilesService;
@Service
public class ApbProductServiceImpl extends AbstractBaseService<ApbProduct, ApbProductSearch> implements ApbProductService {
    @Resource
    private ApbProductMapper apbProductMapper;
    @Resource
    Cache cache;
    @Resource
    FilesService filesService;
    /**
	 * 允许上传的类型
	 * .jpg.jpeg.png.bmp.doc.docx.xls.xlsx.ppt.pptx.rar.zip.7z.wps.pdf
	 */
    private final String FILE_TYPE = ".jpg.jpeg.bmp.png";
    @Override
    public BaseMapper<ApbProduct, ApbProductSearch> getBaseMapper() {
         return  apbProductMapper;
    }
	@Override
	public void refreshCache() {
		ApbProductSearch search = new ApbProductSearch();
		List<ApbProduct> list = this.selectByExample(search);
		Table<String,String,String > table =HashBasedTable.create();
		for(ApbProduct apb:list){
			table.put(apb.getId(), ApbProductCache.ID_NAME, apb.getName());
		}
		cache.put(ApbProductCache.ID_NAME, table);
		
	}
	   @Override
		public void saveInfo(ApbProduct info, MultipartHttpServletRequest fileRequest) {
			String id = info.getId();
			filesService.saveFile(id, filesService.getBusinessType(ApbProduct.class), FILE_TYPE, 60L, fileRequest);
			this.save(info);
		}

		@Override
		public void updateInfo(ApbProduct info, MultipartHttpServletRequest fileRequest) {
			String id = info.getId();
			filesService.saveFile(id, filesService.getBusinessType(ApbProduct.class), FILE_TYPE, 60L, fileRequest);
			this.updateByIdSelective(info);
		}
		@Override
		public List<ApbProduct> selectByTypeCode(String contion) {
			return apbProductMapper.selectByTypeCode(contion);
		}
}