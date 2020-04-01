package org.soft.base.model;

import java.util.Date;

public class Student {

	private int studentId;
	private String studentName;
	private String studentEmail;
	private String studentPhone;
	private String studentAddr;
	private String studentGender;
	private String studentHeadImage;
	private String studentDescribe;
	private Date studentBirthday;
	private int classesId;
	
	public Student() {
		
	}

	public Student(int studentId, String studentName, String studentEmail, int classesId) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.classesId = classesId;
	}
	
	

	public Student(int studentId, String studentName, String studentEmail, String studentPhone, String studentAddr,
			String studentGender, String studentHeadImage, String studentDescribe, Date studentBirthday,
			int classesId) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.studentPhone = studentPhone;
		this.studentAddr = studentAddr;
		this.studentGender = studentGender;
		this.studentHeadImage = studentHeadImage;
		this.studentDescribe = studentDescribe;
		this.studentBirthday = studentBirthday;
		this.classesId = classesId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public int getClassesId() {
		return classesId;
	}

	public void setClassesId(int classesId) {
		this.classesId = classesId;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	public String getStudentAddr() {
		return studentAddr;
	}

	public void setStudentAddr(String studentAddr) {
		this.studentAddr = studentAddr;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}

	public String getStudentHeadImage() {
		return studentHeadImage;
	}

	public void setStudentHeadImage(String studentHeadImage) {
		this.studentHeadImage = studentHeadImage;
	}

	public String getStudentDescribe() {
		return studentDescribe;
	}

	public void setStudentDescribe(String studentDescribe) {
		this.studentDescribe = studentDescribe;
	}

	public Date getStudentBirthday() {
		return studentBirthday;
	}

	public void setStudentBirthday(Date studentBirthday) {
		this.studentBirthday = studentBirthday;
	}
	
	
	
}
