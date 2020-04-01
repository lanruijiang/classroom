package org.soft.base.ctrl.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.soft.base.ctrl.dao.ScoreDao;
import org.soft.base.ctrl.dao.StudentDao;
import org.soft.base.ctrl.db.CRUDDB;
import org.soft.base.model.Score;
import org.soft.base.model.Student;

public class StudentDaoImplements extends CRUDDB implements StudentDao {

	@Override
	public boolean studentCreate(Student student) {
		// TODO Auto-generated method stub
		boolean b = false;
		String sql = "insert into student ( studentName,  "
				+ "studentEmail,  studentPhone,  studentAddr, studentGender,  "
				+ "studentHeadImage,  studentDescribe,  studentBirthday, " + "classesId) values (?,?,?,?,?,?,?,?,?)";
		String studentName = student.getStudentName();
		String studentEmail = student.getStudentEmail();
		String studentPhone = student.getStudentPhone();
		Date studentBirthday = student.getStudentBirthday();
		String studentAddr = student.getStudentAddr();
		String studentDescribe = student.getStudentDescribe();
		String studentHeadImage = student.getStudentHeadImage();
		int classesId = student.getClassesId();
		String studentGender = student.getStudentGender();
		Object[] objects = { studentName, studentEmail, studentPhone, studentAddr, studentGender, studentHeadImage,
				studentDescribe, studentBirthday, classesId };
		int studentId = executionUpdateToId(sql, objects);
		System.out.println("studentId= " + studentId);
		if (studentId != 0) {
			ScoreDao scoreDao = new ScoreDaoImplements();
			Score score = new Score(0, 0, classesId, studentId);
			b = scoreDao.scoreCreate(score);
		}
		return b;
	}

	@Override
	public Student studentById(int studentId) {
		// TODO Auto-generated method stub
		String sql = "select * from student where studentId = ?";
		Object[] objects = { studentId };
		ResultSet rs = executiongQuery(sql, objects);
		Student student = null;
		try {
			if (rs.next()) {
				String studentEmail = rs.getString("studentEmail");
				String studentName = rs.getString("studentName");
				String studentGender = rs.getString("studentGender");
				String studentAddr = rs.getString("studentAddr");
				String studentPhone = rs.getString("studentPhone");
				String studentHeadImage = rs.getString("studentHeadImage");
				String studentDescribe = rs.getString("studentDescribe");
				Date studentBirthday = rs.getDate("studentBirthday");
				int classesId = rs.getInt("classesId");
				student = new Student(studentId, studentName, studentEmail, studentPhone, studentAddr, studentGender,
						studentHeadImage, studentDescribe, studentBirthday, classesId);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
		}
		return student;
	}

	@Override
	public boolean studentUpdate(Student student) {
		String sql = "update student set studentName=?,studentEmail=? , "
				+ "classesId = ? , studentAddr = ? , studentPhone = ? ,"
				+ " studentBirthday = ? , studentDescribe = ? where studentId=?";

		String studentName = student.getStudentName();
		String studentEmail = student.getStudentEmail();
		String studentPhone = student.getStudentPhone();
		Date studentBirthday = student.getStudentBirthday();
		String studentAddr = student.getStudentAddr();
		String studentDescribe = student.getStudentDescribe();
		int classesId = student.getClassesId();
		int studentId = student.getStudentId();

		Object[] objects = { studentName, studentEmail, classesId, studentAddr, studentPhone, studentBirthday,
				studentDescribe, studentId };
		boolean b = executionUpdate(sql, objects);
		return b;
	}

	@Override
	public boolean studentDelete(int studentId) {
		String sql = "delete from student where studentId = ?";
		Object[] objects = { studentId };
		boolean b = executionUpdate(sql, objects);
		return b;
	}

	@Override
	public List<Student> studentShowToClasses(int classesId) {
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from student where classesId =?";
		Object[] objects = { classesId };
		ResultSet rs = executiongQuery(sql, objects);
		Student student = null;
		try {
			while (rs.next()) {
				int studentId = rs.getInt("studentId");
				String studentName = rs.getString("studentName");
				String studentEmail = rs.getString("studentEmail");
				student = new Student(studentId, studentName, studentEmail, classesId);
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
		}
		return list;
	}

	@Override
	public List<Student> studentByName(String studentName) {
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from student where studentName =?";
		Object[] objects = { studentName };
		ResultSet rs = executiongQuery(sql, objects);
		Student student = null;
		try {
			while (rs.next()) {
				int studentId = rs.getInt("studentId");
				String studentEmail = rs.getString("studentEmail");
				int classesId = rs.getInt("classesId");
				student = new Student(studentId, studentName, studentEmail, classesId);
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
		}
		return list;
	}

	@Override
	public boolean studentUpdateHeadImage(Student student) {
		String sql = "update student set studentHeadImage = ?  where studentId = ?";
		String studentHeadImage = student.getStudentHeadImage();
		int studentId = student.getStudentId();
		Object[] objects = {studentHeadImage,studentId};
		boolean b = executionUpdate(sql, objects);
		return b;
	}

	@Override
	public List<Student> studentShowPage(ResultSet rs) {
		List<Student> students = new ArrayList<Student>();
		try {
			while(rs.next()) {
				int studentId = rs.getInt("studentId");
				String studentEmail = rs.getString("studentEmail");
				String studentName = rs.getString("studentName");
				String studentGender = rs.getString("studentGender");
				String studentAddr = rs.getString("studentAddr");
				String studentPhone = rs.getString("studentPhone");
				String studentHeadImage = rs.getString("studentHeadImage");
				String studentDescribe = rs.getString("studentDescribe");
				Date studentBirthday = rs.getDate("studentBirthday");
				int classesId = rs.getInt("classesId");
				Student student = new Student(studentId, studentName, studentEmail, 
						studentPhone, studentAddr, studentGender, studentHeadImage,
						studentDescribe, studentBirthday, classesId);
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
		}
		return students;
	}

}
