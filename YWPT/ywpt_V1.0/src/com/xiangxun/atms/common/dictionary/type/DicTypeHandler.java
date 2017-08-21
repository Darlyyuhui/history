package com.xiangxun.atms.common.dictionary.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * 数据字典类型处理器
 * @author zhouhaij
 * @2013-3-22 下午01:25:48
 */
public class DicTypeHandler implements TypeHandler<DicType> {

	@Override
	public DicType getResult(ResultSet rs, String arg1) throws SQLException {
		return DicType.valuesOf(rs.getString(arg1));
	}

	@Override
	public DicType getResult(ResultSet arg0, int arg1) throws SQLException {
		return DicType.valuesOf(arg0.getString(arg1));
	}

	@Override
	public DicType getResult(CallableStatement arg0, int arg1)throws SQLException {
		return DicType.valuesOf(arg0.getString(arg1));
	}

	@Override
	public void setParameter(PreparedStatement ps, int arg1, DicType arg2,JdbcType arg3) throws SQLException {
		ps.setString(arg1,arg2.toString());
	}


}
