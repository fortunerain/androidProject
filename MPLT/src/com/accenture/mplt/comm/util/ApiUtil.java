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
	 * ������ �α��� api
	 * @param request
	 * @dispatch POST
	 * @return
	 */
	private static ResponseModel checkPassword(RequestModel request) {
		return callMpltAPI(CommonConstants.CHECKPASSWORD_URL, request);
	}
	/**
	 * ���API
	 * @param request
	 * @dispatch POST
	 * @return
	 */
	private static ResponseModel registrationUserInfo(RequestModel request) {
		return callMpltAPI(CommonConstants.REGISTRATION_URL, request);
	}
	/**
	 * ����API
	 * @param request
	 * @dispatch POST
	 * @return
	 */
	private static ResponseModel cancelUserInfo(RequestModel request) {
		return callMpltAPI(CommonConstants.CANCEL_URL, request);
	}
	/**
	 * ���� ���� ����API
	 * @param request
	 * @dispatch POST
	 * @return
	 */
	private static ResponseModel updateSetting(RequestModel request) {
		return callMpltAPI(CommonConstants.UPDATE_URL, request);
	}
	/**
	 * �������� ��ȸAPI
	 * @param request
	 * @dispatch GET
	 * @return
	 */
	public static ResponseModel getSettingInfo(RequestModel request) {
		String url = addParam(CommonConstants.SETTINGINFO_URL, request);
		return callMpltAPI(url, null);
	}
	/**
	 * �������� ��ȸAPI
	 * @param request
	 * @dispatch GET
	 * @return
	 */
	public static ResponseModel getUserListInfo(RequestModel request) {
		String url = addParam(CommonConstants.USERLISTINFO_URL, request);
		return callMpltAPI(url, null);
	}
	/**
	 * ��ݳ���API
	 * @param request
	 * @dispatch POST
	 * @return
	 */
	private static ResponseModel updatePayment(RequestModel request) {
		return callMpltAPI(CommonConstants.PAY_URL, request);
	}
	/**
	 * ��ݳ��� ���API
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
	        			// response ���� users�� parsing ����.
	        			responseModel = gson.fromJson(responseJson.toString(), ResponseModel.class);
	        		} catch(JSONException e) {
	        			//���, ����� ��� response�� ����
	        			Log.i("JSONException", "���, ����� ��� response�� ����");
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
