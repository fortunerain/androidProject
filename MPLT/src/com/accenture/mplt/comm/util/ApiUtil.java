package com.accenture.mplt.comm.util;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.NetworkOnMainThreadException;
import android.util.Log;

import com.accenture.mplt.comm.constant.CommonConstants;
import com.accenture.mplt.comm.constant.StatusCodeEnum;
import com.accenture.mplt.model.RequestModel;
import com.accenture.mplt.model.ResponseModel;
import com.accenture.mplt.model.ResultModel;
import com.google.gson.Gson;
 
public class ApiUtil {

	public static ResponseModel callApi(StatusCodeEnum code, RequestModel request) {
		
		if(StatusCodeEnum.CHECK_ADMIN_PASSWORD.equals(code)) {
			return checkPassword(request);
		}else if(StatusCodeEnum.REGISTRATION.equals(code)) {
			return registrationUserInfo(request);
		}else if(StatusCodeEnum.CANCEL.equals(code)) {
			return cancelUserInfo(request);
		}else if(StatusCodeEnum.UPDATE_SETTING.equals(code)) {
			return updateSetting(request);
		}else if(StatusCodeEnum.UPDATE_PAYMENT.equals(code)) {
			return updatePayment(request);
		}else if(StatusCodeEnum.CANCEL_PAYMENT.equals(code)) {
			return cancelPayment(request);
		}
		return new ResponseModel();
	}
	/**
	 * 관리자 로그인 api
	 * @param request
	 * @dispatch POST
	 * @return
	 */
	private static ResponseModel checkPassword(RequestModel request) {
		return callMpltAPI(CommonConstants.CHECKPASSWORD_URL, request);
	}
	/**
	 * 등록API
	 * @param request
	 * @dispatch POST
	 * @return
	 */
	private static ResponseModel registrationUserInfo(RequestModel request) {
		return callMpltAPI(CommonConstants.REGISTRATION_URL, request);
	}
	/**
	 * 삭제API
	 * @param request
	 * @dispatch POST
	 * @return
	 */
	private static ResponseModel cancelUserInfo(RequestModel request) {
		return callMpltAPI(CommonConstants.CANCEL_URL, request);
	}
	/**
	 * 설정 정보 수정API
	 * @param request
	 * @dispatch POST
	 * @return
	 */
	private static ResponseModel updateSetting(RequestModel request) {
		return callMpltAPI(CommonConstants.UPDATE_URL, request);
	}
	/**
	 * 설정정보 조회API
	 * @param request
	 * @dispatch GET
	 * @return
	 */
	public static ResponseModel getSettingInfo(RequestModel request) {
		String url = addParam(CommonConstants.SETTINGINFO_URL, request);
		return callMpltAPI(url, null);
	}
	/**
	 * 유저정보 조회API
	 * @param request
	 * @dispatch GET
	 * @return
	 */
	public static ResponseModel getUserListInfo(RequestModel request) {
		String url = addParam(CommonConstants.USERLISTINFO_URL, request);
		return callMpltAPI(url, null);
	}
	/**
	 * 요금납부API
	 * @param request
	 * @dispatch POST
	 * @return
	 */
	private static ResponseModel updatePayment(RequestModel request) {
		return callMpltAPI(CommonConstants.PAY_URL, request);
	}
	/**
	 * 요금납부 취소API
	 * @param request
	 * @dispatch POST
	 * @return
	 */
	private static ResponseModel cancelPayment(RequestModel request) {
		return callMpltAPI(CommonConstants.CANCEL_PAY_URL, request);
	}
	
	private static String addParam(String url, RequestModel request) {
		if(request != null) {
			Object obj = request;
			int j = 0;
			
			try {
				List<Field> field = getAllFields(obj.getClass());
				int length = field.size();
				for(int i=0; i<length; i++) {
					
					field.get(i).setAccessible(true);
					Object value = field.get(i).get(obj);
					if(value != null) {
						url += j==0?"?":"&";
						url += field.get(i).getName()+"="+value;
						j++;
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return url;
	}
	
	private static List<Field> getAllFields(Class clazz) {
	    List<Field> fields = new ArrayList<Field>();

	    fields.addAll(Arrays.asList( clazz.getDeclaredFields() ));

	    Class superClazz = clazz.getSuperclass();
	    if(superClazz != null){
	        fields.addAll( getAllFields(superClazz) );
	    }

	    return fields;
	}
	private static ResponseModel callMpltAPI(String url, RequestModel requestModel) {
		HttpClientCustomThread thread = new HttpClientCustomThread(url, requestModel);
		
		thread.start();

		try {
		    thread.join();
		} catch(Exception e) {
		    e.printStackTrace();
		}

		String jsonString = thread.getResult();
		Log.i("===API URL : ", url);
		Log.i("===API RESPONSE : ", jsonString);
		ResponseModel responseModel = new ResponseModel();
		ResultModel resultModel = new ResultModel();
		try {
	        
	        if(jsonString.isEmpty()) {
	        	
	        }else{
	        	Gson gson = new Gson();
	        	
	        	JSONObject jsonObject = new JSONObject(jsonString);
	        	JSONObject resultJson = jsonObject.getJSONObject("result");
	        	resultModel = gson.fromJson(resultJson.toString(), ResultModel.class);
	        	
	        	if("200".equals(resultModel.getCode())) {
		        	// response
	        		try {
	        			JSONObject responseJson = jsonObject.getJSONObject("response");
	        			// response 안의 users와 parsing 해줌.
	        			responseModel = gson.fromJson(responseJson.toString(), ResponseModel.class);
	        		} catch(JSONException e) {
	        			//등록, 취소일 경우 response가 없음
	        			Log.i("JSONException", "등록, 취소일 경우 response가 없음");
	        		}
	        	} 
	        	responseModel.setResult(resultModel);
	        }
		} catch (NetworkOnMainThreadException e) {
            Log.e("httpAndroid", "NetworkOnMainThreadException");

        } catch(Exception e) {
            Log.e("httpAndroid", "Exception");
        }
		
		return responseModel;
	}
	
}
