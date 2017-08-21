package com.xiangxun.atms.icabinet.statusserver.protocal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CabinetSN {
	private String id, sn;

	public CabinetSN(String id, String sn) {
		this.id = id;
		this.sn = sn;
	}

	public String getId() {
		return id;
	}

	public String getSn() {
		return sn;
	}
    
	/**
	 * 更新SN
	 * @param conn
	 * @throws SQLException
	 */
	public void update(Connection conn) throws SQLException {
		String sql = "update property_icabinet_info set sn=? where id =?";
		PreparedStatement stat = conn.prepareStatement(sql);
		stat.setString(1, sn);
		stat.setString(2, id);
		stat.executeUpdate();
	}
}
