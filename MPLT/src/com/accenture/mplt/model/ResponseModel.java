package com.accenture.mplt.model;

import java.util.List;

public class ResponseModel extends BaseModel {

	ResultModel result;
	// �����ο���
	private String curr_user_cnt;
	// ���ο���
	private String total_user_cnt;
	// ���� �ݾ�
	private int fee;
	// ����� ����
	private List<ResponseUsersModel> users;
	
	// ���� ���� API : CommonRequestModel & start_date & end_date
	private String start_date;		//���Ⱓ
	private String end_date;		//��� ���� �Ⱓ

	// �ش� ������ ��� ���� ����
	private String paid;
		
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public String getPaid() {
		return paid;
	}
	public void setPaid(String paid) {
		this.paid = paid;
	}
	public ResultModel getResult() {
		return result;
	}
	public void setResult(ResultModel result) {
		this.result = result;
	}
	public String getCurr_user_cnt() {
		return curr_user_cnt;
	}
	public void setCurr_user_cnt(String curr_user_cnt) {
		this.curr_user_cnt = curr_user_cnt;
	}
	public String getTotal_user_cnt() {
		return total_user_cnt;
	}
	public void setTotal_user_cnt(String total_user_cnt) {
		this.total_user_cnt = total_user_cnt;
	}
	public List<ResponseUsersModel> getUsers() {
		return users;
	}
	public void setUsers(List<ResponseUsersModel> users) {
		this.users = users;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
}
