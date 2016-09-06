package com.example.westinfacemobile.comm.util;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.westinfacemobile.comm.CommApplication;
import com.example.westinfacemobile.comm.contants.ProgressCodeEnum;
import com.example.westinfacemobile.model.ActivityModel;
import com.example.westinfacemobile.model.ResponseModel;


public class ProgressDialogThread extends AsyncTask<Object, Object, ResponseModel> {
	public static final String TAG = "ProgressDialogThread";
	private ProgressDialog mProgressDialog;
	private Context mContext;
	public AsyncResponse asyncResponse;
	private ResponseModel response;
	private CommApplication app;
	
	public ProgressDialogThread(Context context, AsyncResponse listener) {
		this.mContext = context;
		this.asyncResponse = listener;
		this.app = (CommApplication) context.getApplicationContext();
	}
	/**
	 * params[0] : ProgressCodeEnum Code
	 * params[1] : requestModel
	 * params[2] : StatusCodeEnum Code
	 */
	@Override
	protected ResponseModel doInBackground(Object... params) {
		if(params!=null) {
			Object codeObj = params[0];
			ProgressCodeEnum code = (ProgressCodeEnum)codeObj;
			response = new ResponseModel();
			
			if(ProgressCodeEnum.GET_ACTIVITY_LIST.equals(code)) {
				List<ActivityModel> activityItemList = callActivityList();
				response.setActivityList(activityItemList);
			}else if(ProgressCodeEnum.GET_GROUP_ACTIVITY_LIST.equals(code)){
				List<ActivityModel> groupActivityItemList = callGroupActivityList();
				response.setGroupActivityList(groupActivityItemList);
			}
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
		FragmentManager fm = app.getFragmentManager();
		if(fm != null) {
			//count가 계속 증가함. 증가안하도록 수정필요.
			//wait 다이얼로그가 두번 로딩 안되도록 수정필요.
			Log.i(TAG, "count : "+fm.getFragments().size());
		}
		
		mProgressDialog = ProgressDialog.show(mContext, "", "wait", true);
	}
	
	public ResponseModel getResult() {
		return response;
	}
	
	/*
	 * 액티비티 프로그램 조회
	 */
	private List<ActivityModel> callActivityList(){
		List<ActivityModel> list = new ArrayList<ActivityModel>();
		Resources res = mContext.getResources();
		String[] title = app.getActivity_list_title();
		String[] description = app.getActivity_list_desc();
		
		for (int i = 0; i < 7; i++) {
			int imgIdx = i+1;
			ActivityModel activityItemModel = new ActivityModel();
			int tmpId = res.getIdentifier("activity_list_img"+imgIdx, "drawable" , "com.example.westinfacemobile");
			Bitmap img = BitmapFactory.decodeResource(res, tmpId);
			activityItemModel.setImg_acti_icon(img);
			activityItemModel.setText_acti_header(title[i]);
			activityItemModel.setText_acti_body(description[i]);
			list.add(activityItemModel);
		}
			
		return list;
	}
	
	private ActivityModel callActivityDetail(){
		
		
		return null;
	}
	
	/*
	 * 단체 프로그램 조회
	 */
	private List<ActivityModel> callGroupActivityList(){
		List<ActivityModel> list = new ArrayList<ActivityModel>();
		Resources res = mContext.getResources();
		String[] title = app.getGroup_list_title();
		String[] description = app.getGroup_list_desc();
		int size = title.length;
		
		for (int i = 0; i < size; i++) {
			int imgIdx = i+1;
			ActivityModel activityItemModel = new ActivityModel();
			int tmpId = res.getIdentifier("group_activity_list_img"+imgIdx, "drawable" , "com.example.westinfacemobile");
			Bitmap img = BitmapFactory.decodeResource(res, tmpId);
			activityItemModel.setImg_acti_icon(img);
			activityItemModel.setText_acti_header(title[i]);
			activityItemModel.setText_acti_body(description[i]);
			list.add(activityItemModel);
		}
		
		return list;
	}
	
}