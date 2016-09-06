package com.accenture.mplt.comm.constant;

public enum ProgressCodeEnum implements ICodeEnum {
	GET_SETTING("getSetting", "���� ���� ��ȸ") ,
	GET_REGISTE_USER_LIST("getRegisteUserList", "��ϵ� ����� ��ȸ") ,
	GET_UNREGISTE_USER_LIST("getUnRegisteUserList", "�̵�� ����� ��ȸ") ,
	GET_KEYWORD_USER("getKeywordUser", "Ű���带 �̿��� ����� ��ȸ") ,
	DIALOG_API_CALL("dialog call", "dialog�ȿ����� APIȣ��")
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
