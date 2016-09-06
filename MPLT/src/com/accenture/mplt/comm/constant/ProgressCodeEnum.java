package com.accenture.mplt.comm.constant;

public enum ProgressCodeEnum implements ICodeEnum {
	GET_SETTING("getSetting", "설정 정보 조회") ,
	GET_REGISTE_USER_LIST("getRegisteUserList", "등록된 사용자 조회") ,
	GET_UNREGISTE_USER_LIST("getUnRegisteUserList", "미등록 사용자 조회") ,
	GET_KEYWORD_USER("getKeywordUser", "키워드를 이용한 사용자 조회") ,
	DIALOG_API_CALL("dialog call", "dialog안에서의 API호출")
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
