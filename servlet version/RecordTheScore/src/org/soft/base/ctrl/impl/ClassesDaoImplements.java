package org.soft.base.ctrl.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.soft.base.ctrl.dao.ClassesDao;
import org.soft.base.ctrl.db.CRUDDB;
import org.soft.base.model.Classes;


public class ClassesDaoImplements extends CRUDDB implements ClassesDao{

	@Override
	public boolean classesCreate(Classes classes) {
		String sql = "insert into classes (classesName,classesDesc,adminId,classesCreateTime) value (?,?,?,now())";
		String classesName = classes.getClassesName();
		String classesDesc = classes.getClassesDesc();
		int adminId = classes.getAdminId();
		Object[] objects = {classesName,classesDesc,adminId};
		boolean b = executionUpdate(sql, objects);
		return b;
	}

	@Override
	public Classes classesShowById(int classesId) {
		String sql = "select * from classes where classesId = ?";
		Object[] objects = {classesId};
		ResultSet rs = executiongQuery(sql, objects);
		Classes classes = null;
		try {
			if(rs.next()) {
				String classesCreateTime = rs.getString("classesCreateTime");
				String classesDesc = rs.getString("classesDesc");
				int adminId = rs.getInt("adminId");
				String classesName = rs.getString("classesName");
				classes = new Classes(classesId, classesName, classesCreateTime, classesDesc, adminId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classes;
	}

	@Override
	public boolean classesUpdate(Classes classes) {
		String sql="update classes set classesName=?,classesDesc=? , classesCreateTime =now() where classesId=?";
		String classesName=classes.getClassesName();
		String classesDesc= classes.getClassesDesc();
		int classesId = classes.getClassesId();
		Object[] objects = {classesName,classesDesc,classesId};
		boolean b = executionUpdate(sql, objects);
		return b;
	}

	@Override
	public List<Classes> classesShowAll() {
		List<Classes> list = new ArrayList<Classes>();
		String sql = "select * from classes order by classesCreateTime desc";
		ResultSet rs = executiongQuery(sql, null);
		try {
			while(rs.next()) {
				int classesId = rs.getInt("classesId");
				String classesName = rs.getString("classesName");
				String classesCreateTime = rs.getDate("classesCreateTime").toString();
				String classesDesc = rs.getString("classesDesc");
				int adminId = rs.getInt("adminId");
				Classes classes  = new Classes(classesId, classesName, classesCreateTime, classesDesc, adminId);
				list.add(classes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
		}
		return list;
	}

	@Override
	public List<Classes> classesShowToAdmin(int adminId) {
		List<Classes> list = new ArrayList<Classes>();
		String sql = "select * from classes where adminId =? order by classesCreateTime desc";
		Object[] objs = {adminId};
		ResultSet rs = executiongQuery(sql, objs);
		try {
			while(rs.next()) {
				int classesId = rs.getInt("classesId");
				String classesName = rs.getString("classesName");
				String classesCreateTime = rs.getDate("classesCreateTime").toString();
				String classesDesc = rs.getString("classesDesc");
				Classes classes  = new Classes(classesId, classesName, classesCreateTime, classesDesc, adminId);
				list.add(classes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
		}
		return list;

	}

	@Override
	public List<Classes> classesShowPage(ResultSet rs) {
		List<Classes> classeslist = new ArrayList<Classes>();
		try {
			while(rs.next()) {
				int classesId = rs.getInt("classesId");
				String classesName = rs.getString("classesName");
				String classesCreateTime = rs.getDate("classesCreateTime").toString();
				String classesDesc = rs.getString("classesDesc");
				int adminId = rs.getInt("adminId");
				Classes classes  = new Classes(classesId, classesName, classesCreateTime, classesDesc, adminId);
				classeslist.add(classes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
		}
		return classeslist;
	}

}
