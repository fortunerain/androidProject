package com.accenture.mplt.model;

public class RequestModel extends BaseModel {
	// 사용자 EID
	private String user_id;
	// 비밀번호
	private String password;	
	// 관리자용 여부
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
