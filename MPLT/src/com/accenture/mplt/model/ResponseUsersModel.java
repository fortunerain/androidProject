package com.accenture.mplt.model;


public class ResponseUsersModel {
	// ����� EID
	private String username;
	// 0:�Ϲ�����, 1:Admin
	private String is_superuser;
	// 0: unpaid, 1:paid
	private String paid_fee;
	// ��������
	private String created;
	// ��������
	private String modified;
	// R : ��û , C : Cancel	
	private String status;
	
	// ��û�� : 0, �ֱ� 6���� ��û�� : 1, �̽�û�� ��ü : 2 
	private String registType;
	
	private String first_name;
	
	private String last_name;
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getRegistType() {
		return registType;
	}
	public void setRegistType(String registType) {
		this.registType = registType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIs_superuser() {
		return is_superuser;
	}
	public void setIs_superuser(String is_superuser) {
		this.is_superuser = is_superuser;
	}
	public String getPaid_fee() {
		return paid_fee;
	}
	public void setPaid_fee(String paid_fee) {
		this.paid_fee = paid_fee;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
