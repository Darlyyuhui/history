package com.xiangxun.atms.module.perambulate.service.impl;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.util.FileUtils;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.module.perambulate.domain.Filedescribe;
import com.xiangxun.atms.module.perambulate.domain.PerambulateInfo;
import com.xiangxun.atms.module.perambulate.domain.PerambulateInfoSearch;
import com.xiangxun.atms.module.perambulate.mapper.PerambulateInfoMapper;
import com.xiangxun.atms.module.perambulate.service.PerambulateInfoService;
import com.xiangxun.atms.module.perambulate.web.PerambulateExport;


/**
 * 巡检模块接口实现类
 * @author yangzhenyu
 *
 */
@Service("PerambulateInfoService")
public class PerambulateInfoServiceImpl extends AbstractBaseService<PerambulateInfo, PerambulateInfoSearch> implements PerambulateInfoService {

	
	@Resource
	PerambulateInfoMapper perambulateInfoMapper;
	
	@Override
	protected BaseMapper<PerambulateInfo, PerambulateInfoSearch> getBaseMapper() {
		// TODO Auto-generated method stub
		return perambulateInfoMapper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PerambulateInfo> selectByExampleDiy(Map<String, Object> map, String sortType) {
		if (map != null) {
			if (StringUtils.notEmpty(map.get("devicename") + "")) {
				map.put("devicename", "%" + map.get("devicename").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("devicecode") + "")) {
				map.put("devicecode", "%" + map.get("devicecode").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("deviceip") + "")) {
				map.put("deviceip", "%" + map.get("deviceip").toString() + "%");
			}
			/*if (StringUtils.notEmpty(map.get("roadId") + "")) {
				if ("00".equals(map.get("roadId"))) {
					map.put("roadId", null);
				} else {
					map.put("roadId", "%" + map.get("roadId").toString() + "%");
				}
			}
			if (StringUtils.notEmpty(map.get("orgId") + "")) {
				map.put("orgId", "%" + map.get("orgId").toString() + "%");
			}
			if (StringUtils.notEmpty(map.get("deviceTypeIds") + "")) {
				List<String> deviceTypeList = getDeviceTypeList(map.get("deviceTypeIds").toString());
				map.put("deviceTypeIds", deviceTypeList);
			}
			if (StringUtils.notEmpty(map.get("deviceTypeCode") + "")) {
				map.put("devicetypecode", "%" + map.get("deviceTypeCode").toString() + "%");
			}*/
		}
		if (StringUtils.notEmpty(sortType)) {
			map.put("sortColumn", sortType);
		}
		Page page = getListByCondition(map, 1, 2000, sortType);
		return page.getResult();
	}
	@SuppressWarnings("rawtypes")
	public List getCountByDeviceType(Map<String, Object> map){
		return perambulateInfoMapper.getCountByDeviceType(map);
		
	}
	public List getCountByUser(Map<String, Object> map){
		return perambulateInfoMapper.getCountByUser(map);
		
	}

/**
 * 获取服务器上所有巡检报告信息
 * @author yangzhenyu
 */
	public List<Filedescribe> getFileMes(String Bydate){
		List<Filedescribe> filelist=new ArrayList<Filedescribe>();
		try {
			URI uri = PerambulateExport.class.getProtectionDomain().getCodeSource().getLocation().toURI();
			File classPath = new File(uri);
			classPath = classPath.getParentFile().getParentFile();
			String p = Paths.get(classPath.getPath(), "download", "xls").toString();
			File folder = new File(p);
			File[] files =  folder.listFiles();
			if(files==null){
				return filelist;
			}
			Filedescribe fd=null;
			for(File f1:files){
				fd=new Filedescribe();
				String filename=f1.getName();
				String filesize=FileUtils.format(f1.length());
				 long time = f1.lastModified();
				String creattime= new SimpleDateFormat("yyyy-MM-dd").format(new Date(time));
				fd.setFilename(filename);
				fd.setFilesize(filesize);
				fd.setCreattime(creattime);
				if(Bydate==null){
					filelist.add(fd);
				}else {
					if(Bydate.equals(creattime)){
						filelist.add(fd);
					}
				}
				
				
			}
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		ListSort(filelist);
		return filelist;
	}
	/**
	 * 按照文件日期排序
	 * @param list
	 * @author admin
	 */
	 private static void ListSort(List<Filedescribe> list) {
		          Collections.sort(list, new Comparator<Filedescribe>() {
		              @Override
		              public int compare(Filedescribe o1, Filedescribe o2) {
		                 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		                  try {
		                      Date dt1 = format.parse(o1.getCreattime());
		                      Date dt2 = format.parse(o2.getCreattime());
		                      if (dt1.getTime() > dt2.getTime()) {
		                          return -1;
		                      } else if (dt1.getTime() < dt2.getTime()) {
		                          return 1;
		                      } else {
		                          return 0;
		                      }
		                  } catch (Exception e) {
		                      e.printStackTrace();
		                  }
		                  return 0;
		              }
		          });
		      }
}
