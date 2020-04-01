package org.soft.base.model;

public class Admin {

	private int adminId;
	private String adminName;
	private String adminPass;
	private String adminDesc;

	public Admin() {

	}

	public Admin(int adminId, String adminName, String adminPass, String adminDesc) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPass = adminPass;
		this.adminDesc = adminDesc;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

	public String getAdminDesc() {
		return adminDesc;
	}

	public void setAdminDesc(String adminDesc) {
		this.adminDesc = adminDesc;
	}

}
