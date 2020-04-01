package org.soft.base.model;

public class Score {

	private int scoreId;
	private int scoreNumber;
	private int classesId;
	private int studentId;
	private String studentName;

	public Score() {

	}

	public Score(int scoreId, int scoreNumber, int classesId, int studentId) {
		super();
		this.scoreId = scoreId;
		this.scoreNumber = scoreNumber;
		this.classesId = classesId;
		this.studentId = studentId;
	}
	
	

	public Score(int scoreId, int scoreNumber, int classesId, int studentId, String studentName) {
		super();
		this.scoreId = scoreId;
		this.scoreNumber = scoreNumber;
		this.classesId = classesId;
		this.studentId = studentId;
		this.studentName = studentName;
	}

	public int getScoreId() {
		return scoreId;
	}

	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}

	public int getScoreNumber() {
		return scoreNumber;
	}

	public void setScoreNumber(int scoreNumber) {
		this.scoreNumber = scoreNumber;
	}

	public int getClassesId() {
		return classesId;
	}

	public void setClassesId(int classesId) {
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

	
}
