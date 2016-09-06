package com.accenture.mplt;

import java.text.ParseException;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.accenture.mplt.comm.CommApplication;
import com.accenture.mplt.comm.constant.ProgressCodeEnum;
import com.accenture.mplt.comm.constant.StatusCodeEnum;
import com.accenture.mplt.comm.util.ApiUtil;
import com.accenture.mplt.comm.util.AsyncResponse;
import com.accenture.mplt.comm.util.CommonUtil;
import com.accenture.mplt.comm.util.ProgressDialogThread;
import com.accenture.mplt.model.RequestModel;
import com.accenture.mplt.model.ResponseModel;
import com.accenture.mplt.model.ResponseUsersModel;

public class UserListFragment extends Fragment implements OnItemClickListener {
	public static final String TAG = "UserListFragment";
	private ListView listview;
	private UserListViewAdapter adapter;
	private List<ResponseUsersModel> list;
	private PasswordCustomDialog passwordCustomDialog;
	private LayoutInflater layoutInflater;
	private Context context;
	private ResponseModel settingResponse;
	private Fragment userListFragment;
	
	private TextView userCnt;
	private TextView fee;
	private TextView head_deadline;
	private Button payButton;	
	
	private boolean isAdmin, isPaid = false;
	private String user_name;
	private int viewId;
	private int payId = R.id.userlist_button_pay;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		userListFragment = (UserListFragment) getActivity().getSupportFragmentManager().findFragmentByTag(getString(R.string.userlist_title));
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_user_list, container, false);
        
  		listview = (ListView) v.findViewById(R.id.userlist_layout_listview);
		listview.setOnItemClickListener(this);
		
		layoutInflater = this.getActivity().getLayoutInflater();
		context = v.getContext();
  		
		// baseFragment 의 셋팅 정보 가져오기.
		CommApplication app = (CommApplication) context.getApplicationContext();
		settingResponse = app.getSetting();
		isAdmin = app.isAdmin();
		
		userCnt = (TextView) v.findViewById(R.id.userlist_text_userCnt);
		fee = (TextView) v.findViewById(R.id.userlist_text_fee);
		head_deadline = (TextView) v.findViewById(R.id.userlist_text_deadline);
		
		AsyncResponse listener = new AsyncResponse() {
			
			@Override
			public void processDialogFinish(ResponseModel responseModel) {
				list = responseModel.getUsers();
				View.OnClickListener mOnClickListener = new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						switch (v.getId()) {
						case R.id.userlist_button_pay:
							RelativeLayout listViewLayout = (RelativeLayout)v.getParent();
							payButton = (Button) listViewLayout.findViewById(R.id.userlist_button_pay);
							user_name = ((TextView) listViewLayout.findViewById(R.id.userlist_text_username)).getText().toString();
							String message = "";
							String tag = payButton.getTag().toString();
							
							//paid 버튼에 관한 얼럿 메세지 설정
							if(getString(R.string.paid).equals(tag)) {
								isPaid = true;
								message = getString(R.string.popup_text_paid);
							}else {
								isPaid = false;
								message = getString(R.string.popup_text_pay);
							}
							showAlert(v, getActivity(), message);
							break;
						case R.id.userlist_button_dialog:
							showDialog(v);
							break;
						}
					}
				};

				adapter = new UserListViewAdapter(context, R.layout.adapter_userlistview, list, layoutInflater, mOnClickListener);
				listview.setAdapter(adapter);
				
				// DeadLine setting
				try {
					head_deadline.setText(getString(R.string.userlist_text_dueDate)+"\n"+CommonUtil.makeDeadLine(settingResponse));
				} catch (ParseException e) {
					head_deadline.setText("");
				}
				setHeadContents(responseModel);

			}
		};
		
		//getUserListInfo api 호출하여 user 정보만 list에 담기
		ProgressDialogThread thread = new ProgressDialogThread(context,listener);
		thread.execute(ProgressCodeEnum.GET_REGISTE_USER_LIST);
		
		
        return v;
    }
    
    private void setHeadContents(ResponseModel responseModel) {
    	//현재인원 셋팅
		userCnt.setText(getString(R.string.userlist_text_currCnt)+"\n"+responseModel.getCurr_user_cnt()+"/"+responseModel.getTotal_user_cnt());
		
		//금액 셋팅
		fee.setText(getString(R.string.userlist_text_monetary_unit)+"\n"+CommonUtil.toNumFormat(responseModel.getFee())+"/P");
    }
    
    /*
     * 리스트 목록을 클릭했을 때 팝업 호출
     */
	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			int position, long id) {
		String user_name = list.get(position).getUsername();
		String popupDesc = getString(R.string.popup_text_cancel);
//		openDialog(view, popupDesc, user_name);
		
		checkShowDialog(user_name, popupDesc, view);
	}
	
	/*
	 * 리스트목록을 클릭, 이미지 버튼을 클릭해도 해당 dialog 를 호출하므로 분리함.
	 * 주차권 신청 취소.
	 */
	private void openDialog(View view, String popupDesc, String user_name) {
		passwordCustomDialog = new PasswordCustomDialog(view.getContext(), popupDesc, user_name, StatusCodeEnum.CANCEL, userListFragment);
		passwordCustomDialog.show();
	}
	
	public void showDialog(View v) {
		String popupDesc = getString(R.string.popup_text_cancel);
		RelativeLayout listViewLayout = (RelativeLayout) v.getParent();
		String user_name = ((TextView) listViewLayout.findViewById(R.id.userlist_text_eid)).getText().toString();
		
		checkShowDialog(user_name, popupDesc, v);
	}	
	
	
	private void checkShowDialog(String user_name, String popupDesc, View view) {
		this.user_name = user_name;
		if(!isAdmin) {
			openDialog(view, popupDesc, user_name);
		} else {
			showAlert(view, getActivity(), getString(R.string.popup_text_cancel_admin));
		}
	}
	
	/*
	 * 유저 리스트 갱신
	 */
	public void refreshUserList() {
		RequestModel request = new RequestModel();
		request.setSearchType("0");	//0:신청자 현황 조회
		ResponseModel responseModel = ApiUtil.getUserListInfo(request);
		list = responseModel.getUsers();
		int size = list.size();
		adapter.clear();
		if(!list.isEmpty()) {
			for (int i = 0; i < size; i++) {
				list.get(i).setRegistType("0");
				adapter.add(list.get(i));
			}
		}
		//어댑터만 갱신함.
		adapter.notifyDataSetChanged();
		
		//현재 인원과 금액 갱신
		setHeadContents(responseModel);
	}
	
	public void showAlert(View v, Context context, String message) {
		viewId = v.getId();
		
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setPositiveButton(context.getString(R.string.popup_button_ok), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				RequestModel request = new RequestModel();
				request.setUser_id(user_name);
				ResponseModel response = new ResponseModel();
				int resId = 0;
				String tagId = "";
				if(viewId == payId) {
					
					if(isPaid) {
						response = ApiUtil.callApi(StatusCodeEnum.CANCEL_PAYMENT, request);
						resId = R.drawable.pay;
						tagId = getString(R.string.pay);
					}else {
						response = ApiUtil.callApi(StatusCodeEnum.UPDATE_PAYMENT, request);
						resId = R.drawable.paid;
						tagId = getString(R.string.paid);
					}

					//pay 버튼 배경 및 tag 변경.
					String resultCode = response.getResult().getCode();
					if("200".equals(resultCode)) {
						payButton.setBackgroundResource(resId);
						payButton.setTag(tagId);
					}
				} else {
					request.setAdminMode("true");
					response = ApiUtil.callApi(StatusCodeEnum.CANCEL, request);
					refreshUserList();
				}
				
				showToast(response);
				
				dialog.dismiss();     //닫기
			}
		});
		
		alert.setNegativeButton(context.getString(R.string.popup_button_cancel), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();     //닫기
			}
		});
		alert.setMessage(message);
		alert.show();
	}
	
	private void showToast(ResponseModel response) {
		String resultMsg = response.getResult().getMessage();
		
		Toast.makeText(getActivity(), resultMsg, Toast.LENGTH_SHORT).show();
	}
}
