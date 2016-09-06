package com.example.westinfacemobile.comm.contants;

public enum ProgressCodeEnum {
	GET_ACTIVITY_LIST("get_activity_list", "��Ƽ��Ƽ ��ȸ") ,
	GET_GROUP_ACTIVITY_LIST("get_group_activity_list", "��ü ���α׷� ��ȸ")
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
