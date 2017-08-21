package com.xiangxun.atms.module.eventalarm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.xiangxun.atms.framework.base.BaseMapper;
import com.xiangxun.atms.module.eventalarm.domain.AlaICabLog;
import com.xiangxun.atms.module.eventalarm.domain.AlaICabLogSearch;
import com.xiangxun.atms.module.eventalarm.domain.AlaICabLogWithBLOBs;


public interface AlaICabLogMapper extends BaseMapper<AlaICabLog, AlaICabLogSearch> {
//    long countByExample(AlaICabLogSearch example);

    int deleteByExample(AlaICabLogSearch example);

    int insert(AlaICabLogWithBLOBs record);

    int insertSelective(AlaICabLogWithBLOBs record);

//    List<AlaICabLogWithBLOBs> selectByExampleWithBLOBs(AlaICabLogSearch example);

    List<AlaICabLog> selectByExample(AlaICabLogSearch example);

//    int updateByExampleSelective(@Param("record") AlaICabLogWithBLOBs record, @Param("example") AlaICabLogSearch example);
//
//    int updateByExampleWithBLOBs(@Param("record") AlaICabLogWithBLOBs record, @Param("example") AlaICabLogSearch example);
//
//    int updateByExample(@Param("record") AlaICabLog record, @Param("example") AlaICabLogSearch example);
    List<AlaICabLog> selectAll(@Param("assetname")String assetname,@Param("ip")String ip,@Param("installplace")String installplace, RowBounds rowBounds);
    
    int selectAllcount(@Param("assetname")String assetname,@Param("ip")String ip,@Param("installplace")String installplace);
    
    byte[] getPhoto1ByID(@Param("id")String id);
    byte[] getPhoto2ByID(@Param("id")String id);
}