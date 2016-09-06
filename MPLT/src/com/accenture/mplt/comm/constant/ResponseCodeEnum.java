package com.accenture.mplt.comm.constant;

public enum ResponseCodeEnum implements ICodeEnum {
	
	SUCCESS("200", "성공"),
	FAILURE_406("406", "비지니스 에러"),
	FAILURE_412("412", "사전 조건이 만족하지 않을 경우"),
	ERROR("500", "예외적인 에러 발생했을 경우");

	private String code;
	private String message;

	private ResponseCodeEnum(String code, String message) {
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
