package org.soft.base.model;

public class Classes {

	private int classesId;
	private String classesName;
	private String classesCreateTime;
	private String classesDesc;
	private int adminId;
	
	public Classes(){
		
	}

	public Classes(int classesId, String classesName, String classesCreateTime, String classesDesc, int adminId) {
		super();
		this.classesId = classesId;
		this.classesName = classesName;
		this.classesCreateTime = classesCreateTime;
		this.classesDesc = classesDesc;
		this.adminId = adminId;
	}

	public int getClassesId() {
		return classesId;
	}

	public void setClassesId(int classesId) {
		this.classesId = classesId;
	}

	public String getClassesName() {
		return classesName;
	}

	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}

	public String getClassesCreateTime() {
		return classesCreateTime;
	}

	public void setClassesCreateTime(String classesCreateTime) {
		this.classesCreateTime = classesCreateTime;
	}

	public String getClassesDesc() {
		return classesDesc;
	}

	public void setClassesDesc(String classesDesc) {
		this.classesDesc = classesDesc;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	
}
