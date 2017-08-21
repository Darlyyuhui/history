package com.xiangxun.atms.module.eventalarm.service.impl;

import java.sql.Blob;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.xiangxun.atms.framework.base.AbstractBaseService;
import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.eventalarm.domain.AlaICabLog;
import com.xiangxun.atms.module.eventalarm.domain.AlaICabLogSearch;
import com.xiangxun.atms.module.eventalarm.service.AlaICabLogService;

@Service("AlaICabLogService")
public class AlaICabLogServiceImpl extends AbstractBaseService<AlaICabLog, AlaICabLogSearch> implements AlaICabLogService {
	@Resource
	com.xiangxun.atms.module.eventalarm.mapper.AlaICabLogMapper alaicablogMapper;
	@Resource
	JdbcTemplate jdbctemp;

	@Override
	protected BaseMapper<AlaICabLog, AlaICabLogSearch> getBaseMapper() {
		return alaicablogMapper;
	}

	@Override
	public Page getAll(int pageNo, int pageSize,String assetname,String ip,String installplace) {
		int total = alaicablogMapper.selectAllcount(assetname,ip,installplace);
		List<AlaICabLog> log = alaicablogMapper.selectAll(assetname,ip,installplace,Page.getRowBounds(pageNo, pageSize));
		return Page.getPage(total, log, pageNo, pageSize);
	}

	@Override
	public byte[] getphoto1(String id) {
		String sql = "select photo1 from alarm_icabinet_log where id=?";
		try {
			Blob b = jdbctemp.queryForObject(sql, Blob.class, id);
			return b.getBytes(1, (int) b.length());
		} catch (Exception ex) {
			return new byte[0];
		}
	}

	@Override
	public byte[] getphoto2(String id) {
		String sql = "select photo2 from alarm_icabinet_log where id=?";
		try {
			Blob b = jdbctemp.queryForObject(sql, Blob.class, id);
			return b.getBytes(1, (int) b.length());
		} catch (Exception ex) {
			return new byte[0];
		}
	}
}
