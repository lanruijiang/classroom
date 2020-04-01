package org.soft.base.ctrl.dao;

import java.sql.ResultSet;
import java.util.List;

import org.soft.base.model.Classes;

/**
 * 	班级管理
 * @author Administrator
 *
 */
public interface ClassesDao {

	/**
	 *	创建班级
	 * @param classes
	 * @return
	 */
	public boolean classesCreate(Classes classes);
	
	/**
	 * 通过Id查询班级信息
	 * @param classesId
	 * @return
	 */
	public Classes classesShowById(int classesId);
	
	/**
	 * 
	 * @param classes
	 * @return
	 */
	public boolean classesUpdate(Classes classes);
	
	/**
	 *	 显示所有的班级
	 * @return
	 */
	public List<Classes> classesShowAll();
	
	/**
	 *	 显示特定管理员管理的班级
	 * @return
	 */
	public List<Classes> classesShowToAdmin(int adminId);
	
	/**
	 * 	每页显示的班级信息
	 * @param rs
	 * @return
	 */
	public List<Classes> classesShowPage(ResultSet rs);
	
	
}

