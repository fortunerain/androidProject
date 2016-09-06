package com.example.westinfacemobile.comm.contants;

public enum ProgressCodeEnum {
	GET_ACTIVITY_LIST("get_activity_list", "액티비티 조회") ,
	GET_GROUP_ACTIVITY_LIST("get_group_activity_list", "단체 프로그램 조회")
	;

	private String code;
	private String message;
	
	private ProgressCodeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
