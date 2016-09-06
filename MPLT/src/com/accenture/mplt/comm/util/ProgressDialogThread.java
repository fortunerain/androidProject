package com.accenture.mplt.comm.util;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.accenture.mplt.comm.constant.ProgressCodeEnum;
import com.accenture.mplt.comm.constant.StatusCodeEnum;
import com.accenture.mplt.model.RequestModel;
import com.accenture.mplt.model.ResponseModel;
import com.accenture.mplt.model.ResponseUsersModel;


public class ProgressDialogThread extends AsyncTask<Object, Object, ResponseModel> {
	private ProgressDialog mProgressDialog;
	private Context mContext;
	public AsyncResponse asyncResponse;
	ResponseModel response;
	// request search Type
	private final String NON_REGISTERED = "1";
	private final String SEARCH = "2";
	private final String SIX_MONTH_USER = "3";
	public ProgressDialogThread(Context context, AsyncResponse listener) {
		this.mContext = context;
		this.asyncResponse = listener;
	}
	/**
	 * params[0] : ProgressCodeEnum Code
	 * params[1] : requestModel
	 * params[2] : StatusCodeEnum Code
	 */
	@Override
	protected ResponseModel doInBackground(Object... params) {
		Object codeObj = params[0];
		Object requestObj = null;
		Object statusCodeObj = null;
		int paramsLength = params.length;
		switch (paramsLength) {
		case 2:
			requestObj = params[1];
			break;
		case 3:
			requestObj = params[1];
			statusCodeObj = params[2];
			break;
		default:
			break;
		}
		
		RequestModel request = (RequestModel)requestObj;
		ProgressCodeEnum code = (ProgressCodeEnum)codeObj;
		StatusCodeEnum statusCode = (StatusCodeEnum)statusCodeObj;
		response = new ResponseModel();
		
		if(ProgressCodeEnum.GET_KEYWORD_USER.equals(code)) {
			List<ResponseUsersModel> searchUserList = searchMember(request);
			response.setUsers(searchUserList);
		} else if(ProgressCodeEnum.GET_REGISTE_USER_LIST.equals(code)) {
			response = callRegistedApi(request);
		} else if(ProgressCodeEnum.GET_SETTING.equals(code)) {
			response = ApiUtil.getSettingInfo(request);
		} else if(ProgressCodeEnum.GET_UNREGISTE_USER_LIST.equals(code)) {
			// 최근 6개월 신청자 중 미신청자
			List<ResponseUsersModel> recentlyUserList = callRecentlyApi();
			List<ResponseUsersModel> nonRegisterList = callNonRegistedApi();
			List<ResponseUsersModel> allUserList = callAllUserApi();
			
			List<ResponseUsersModel> userList = new ArrayList<ResponseUsersModel>();
			userList.addAll(recentlyUserList);
			userList.addAll(nonRegisterList);
			userList.addAll(allUserList);
			
			response.setUsers(userList);
			
		} else if(ProgressCodeEnum.DIALOG_API_CALL.equals(code)) {
			response = ApiUtil.callApi(statusCode, request);
		}
		
		return response;
	}
	/**
	 * 최근 6개월 신청자 중 미신청자 조회
	 * @return
	 */
	private List<ResponseUsersModel> callRecentlyApi() {
		RequestModel request = new RequestModel();
		request.setSearchType(SIX_MONTH_USER);
		ResponseModel response = ApiUtil.getUserListInfo(request);
		List<ResponseUsersModel> recentlyUserList = response.getUsers();
		int recentlyUserCnt = recentlyUserList.size();
		for(int index=0; index<recentlyUserCnt; index++) {
			ResponseUsersModel model = recentlyUserList.get(index);
			model.setRegistType("1");
		}
		
		return recentlyUserList;
	}
	/**
	 * 미신청자 조회
	 * @return
	 */
	private List<ResponseUsersModel> callNonRegistedApi() {
		RequestModel request = new RequestModel();
		request.setSearchType(NON_REGISTERED);
		ResponseModel response = ApiUtil.getUserListInfo(request);
		List<ResponseUsersModel> nonRegisterList = response.getUsers();
		int nonRegisterCnt = nonRegisterList.size();
		for(int index=0; index<nonRegisterCnt; index++) {
			ResponseUsersModel model = nonRegisterList.get(index);
			model.setRegistType("2");
		}
		
		return nonRegisterList;
	}
	/**
	 * 모든 사용자 조회
	 * @return
	 */
	private List<ResponseUsersModel> callAllUserApi() {
		RequestModel request = new RequestModel();
		request.setSearchType(SEARCH);
		request.setKeyword(null);
		ResponseModel response = ApiUtil.getUserListInfo(request);
		List<ResponseUsersModel> allUserList = response.getUsers();
		
		return allUserList;
	}
	/**
	 * 키워드를 이용한 사용자 조회
	 * @param searchMember
	 * @return
	 */
	private List<ResponseUsersModel> searchMember(RequestModel request) {
		ResponseModel response = ApiUtil.getUserListInfo(request);
		
		return response.getUsers();
	}
	
	private ResponseModel callRegistedApi(RequestModel request) {
		ResponseModel response = ApiUtil.getUserListInfo(request);
		List<ResponseUsersModel> registerList = response.getUsers();
		int registerCnt = registerList.size();
		for(int index=0; index<registerCnt; index++) {
			ResponseUsersModel model = registerList.get(index);
			model.setRegistType("0");
		}
		
		return response;
	}
	
	@Override
	protected void onPostExecute(ResponseModel result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		mProgressDialog.dismiss();
		asyncResponse.processDialogFinish(result);
	}
	@Override
	protected void onPreExecute() {
		mProgressDialog = ProgressDialog.show(mContext, "", "wait", true);
	}
	
	public ResponseModel getResult() {
		return response;
	}
}