package org.soft.base.ctrl.dao;

import java.sql.ResultSet;
import java.util.List;

import org.soft.base.model.Student;

/**
 * 学生管理
 * 
 * @author Administrator
 *
 */
public interface StudentDao {

	/**
	 *	 创建学生信息
	 * @param student
	 * @return
	 */
	public boolean studentCreate(Student student);
	
	/**
	 *	 通过ID查询学生信息
	 * @param studentId
	 * @return
	 */
	public Student studentById(int studentId);
	
	/**
	 * 
	 * @param studentName
	 * @return
	 */
	public List<Student> studentByName(String studentName);

	/**
	 * 	修改学生信息
	 * @param student
	 * @return
	 */
	public boolean studentUpdate(Student student);

	/**
	 * 	删除学生信息
	 * @param studentId
	 * @return
	 */
	public boolean studentDelete(int studentId);
	
	
	/**
	 *	 查询班级学员信息
	 * @param classesId
	 * @return
	 */
	public List<Student> studentShowToClasses(int classesId);
	
	/**
	 * 	更换头像
	 * @return
	 */
	public boolean studentUpdateHeadImage(Student student);
	
	/**
	 * 	每页学生显示的内容
	 * @return
	 */
	public List<Student> studentShowPage(ResultSet rs);
}
