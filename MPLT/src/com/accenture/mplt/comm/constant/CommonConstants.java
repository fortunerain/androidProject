package com.accenture.mplt.comm.constant;


public class CommonConstants {
	
	public static final String ADMIN_USER_NAME = "admin";
	public static final String DOMAIN_URI = "http://192.168.2.3:3360/";
//	public static final String DOMAIN_URI = "http://112.162.17.79:12346/";
	public static final String USERLISTINFO_URL = DOMAIN_URI + "service/GetUserListInfo";
	public static final String SETTINGINFO_URL = DOMAIN_URI + "service/GetSettingInfo";
	public static final String REGISTRATION_URL = DOMAIN_URI + "service/RegisterParkingLotTicket/";
	public static final String CANCEL_URL = DOMAIN_URI + "service/CancelParkingLotTicket/";
	public static final String UPDATE_URL = DOMAIN_URI + "service/UpdateSettingInfo/";
	public static final String CHECKPASSWORD_URL = DOMAIN_URI + "service/CheckAdminPassword/";
	public static final String PAY_URL = DOMAIN_URI + "service/PayParkingLotTicket/";
	public static final String CANCEL_PAY_URL = DOMAIN_URI + "service/CancelParkingLotTicketPayment/";

}
