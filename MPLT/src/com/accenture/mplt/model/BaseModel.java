package com.accenture.mplt.model;

public class BaseModel {
	// EID�� ������ �ش� ���� �˻�/ null �� ��� ��� ���� �˻�
	private String keyword;		
	// 0: ��û��, 1: �� ��û��, 2: keyword �˻�
	private String searchType;
	// ������ ��û ���� ����
	private String start_registration_date;
	// ������ ��û ������ ����
	private String end_registration_date;
	// Ȱ��ȭ ����
	private String locked;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getStart_registration_date() {
		return start_registration_date;
	}
	public void setStart_registration_date(String start_registration_date) {
		this.start_registration_date = start_registration_date;
	}
	public String getEnd_registration_date() {
		return end_registration_date;
	}
	public void setEnd_registration_date(String end_registration_date) {
		this.end_registration_date = end_registration_date;
	}
	public String getLocked() {
		if(locked.isEmpty()) {
			return locked;
		} else {
 			if("TRUE".equals(locked.toUpperCase())) {
 				locked = "0";
 			} else {
 				locked = "1";
 			}
 			return locked;
		}
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	
	
}
