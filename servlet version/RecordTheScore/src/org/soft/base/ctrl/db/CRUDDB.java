package org.soft.base.ctrl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDDB {
	private static Connection con = null;
	private static PreparedStatement pstmt = null;


	public static boolean executionUpdate(String sql , Object[] objects) {
		boolean b = false;
		con = DBUtils.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			if(objects != null) {
				for(int i = 0 ; i < objects.length ; i++) {
					pstmt.setObject(i + 1, objects[i]);
				}
			}
			
			int row = pstmt.executeUpdate();
			if(row > 0){
				b  = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(null, pstmt, con);
		}
		return b;
	}
	
	public static int executionUpdateToId(String sql , Object[] objects) {
		boolean b = false;
		con = DBUtils.getConnection();
		int id = 0;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
			if(objects != null) {
				for(int i = 0 ; i < objects.length ; i++) {
					pstmt.setObject(i + 1, objects[i]);
				}
			}
			
			int row = pstmt.executeUpdate();
			if(row > 0){
				rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					id = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, con);
		}
		return id;
	}
	

	public ResultSet executiongQuery(String sql , Object[] objects) {
		ResultSet rs = null;
		con = DBUtils.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			if(objects != null) {
				for(int i = 0 ; i < objects.length ; i++) {
					pstmt.setObject(i + 1, objects[i]);
				}
			}
			
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void close(ResultSet rs) {
		DBUtils.close(rs, pstmt, con);
	}
}
