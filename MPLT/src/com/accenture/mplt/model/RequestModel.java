package com.accenture.mplt.model;

public class RequestModel extends BaseModel {
	// ����� EID
	private String user_id;
	// ��й�ȣ
	private String password;	
	// �����ڿ� ����
	private String adminMode = "false";
	
	public String getAdminMode() {
		return adminMode;
	}
	public void setAdminMode(String adminMode) {
		this.adminMode = adminMode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
