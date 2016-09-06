package com.accenture.mplt.comm.constant;


public enum StatusCodeEnum implements ICodeEnum {
	
	REGISTRATION("registration", "등록"),
	GET_USER_LIST("getUserList", "유저 리스트"),
	CANCEL("cancel", "취소"),
	GET_SETTING("getSetting", "설정정보 조회"),
	UPDATE_SETTING("updateSetting", "설정정보 수정"),
	UPDATE_PAYMENT("updatePAYMENT", "요금납부 수정"),
	CANCEL_PAYMENT("cancelPAYMENT", "요금납부 취소"),
	CHECK_ADMIN_PASSWORD("checkAdminPassword", "관리자 비밀번호 체크");

	private String code;
	private String message;

	private StatusCodeEnum(String code, String message) {
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
