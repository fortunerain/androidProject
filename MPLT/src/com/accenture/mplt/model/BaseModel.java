package com.accenture.mplt.model;

public class BaseModel {
	// EID를 넣으면 해당 유저 검색/ null 일 경우 모든 유저 검색
	private String keyword;		
	// 0: 신청자, 1: 미 신청자, 2: keyword 검색
	private String searchType;
	// 주차권 신청 시작 일자
	private String start_registration_date;
	// 주차권 신청 마지막 일자
	private String end_registration_date;
	// 활성화 여부
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
