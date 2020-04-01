package org.soft.base.ctrl.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.soft.base.ctrl.dao.AdminDao;
import org.soft.base.ctrl.db.CRUDDB;
import org.soft.base.model.Admin;

public class AdminDaoImplements extends CRUDDB implements AdminDao{

	@Override
	public boolean adminCreate(Admin admin) {
		String sql = "insert into admin(adminName,adminPass,adminDesc) values (?,?,?)";
		String adminName = admin.getAdminName();
		String adminPass = admin.getAdminPass();
		String adminDesc = admin.getAdminDesc();
		Object[] objects = {adminName,adminPass,adminDesc};
		boolean b = executionUpdate(sql, objects);
		return b;
	}

	@Override
	public Admin adminLogin(Admin admin) {
		String sql = "select * from admin where adminName = ? and adminPass = ?";
		String adminName = admin.getAdminName();
		String adminPass = admin.getAdminPass();
		Object[] objects = {adminName,adminPass};
		ResultSet rs = executiongQuery(sql, objects);
		try {
			if(rs.next()) {
				int adminId = rs.getInt("adminId");
				String adminDesc = rs.getString("adminDesc");
				admin = new Admin(adminId, adminName, adminPass, adminDesc);
			}else {
				admin = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
		}
		return admin;
	}
}
