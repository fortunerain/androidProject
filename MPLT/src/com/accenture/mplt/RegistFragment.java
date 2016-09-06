package com.accenture.mplt;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils.StringSplitter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.accenture.mplt.comm.CommApplication;
import com.accenture.mplt.comm.constant.ProgressCodeEnum;
import com.accenture.mplt.comm.constant.StatusCodeEnum;
import com.accenture.mplt.comm.util.ApiUtil;
import com.accenture.mplt.comm.util.AsyncResponse;
import com.accenture.mplt.comm.util.ProgressDialogThread;
import com.accenture.mplt.model.RequestModel;
import com.accenture.mplt.model.ResponseModel;
import com.accenture.mplt.model.ResponseUsersModel;

public class RegistFragment extends Fragment implements OnItemClickListener, OnClickListener {
	private final String REGIST_FLAG = "REGIST ACTIVITY";

	// component
	private ListView listView;
	private AutoCompleteTextView textView;
	private Button regist_search_button;
	// String Array (user name)
	private String[] totalUserName;
	private String[] userListName;
	// List Array (response.getUsers)
	List<ResponseUsersModel> nonRegisterList = new ArrayList<ResponseUsersModel>();
	List<ResponseUsersModel> recentlyUserList = new ArrayList<ResponseUsersModel>();
	List<ResponseUsersModel> allUserList = new ArrayList<ResponseUsersModel>();
	//
	UserListViewAdapter adapter;
	// request search Type
	private final String SEARCH = "2";
	// Custom Dialog
	private PasswordCustomDialog passwordCustomDialog;
	private Fragment registFragment;
	
	private boolean isAdmin;
	private String user_name;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registFragment = (RegistFragment) getActivity().getSupportFragmentManager().findFragmentByTag(getString(R.string.regist_title));
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_regist, container, false);
        
		CommApplication app = (CommApplication) v.getContext().getApplicationContext();
		isAdmin = app.isAdmin();
		
		listView = (ListView) v.findViewById(R.id.regist_layout_listview);
		listView.setOnItemClickListener(this);
		regist_search_button = (Button) v.findViewById(R.id.regist_button_search);
		regist_search_button.setOnClickListener(this);
		textView = (AutoCompleteTextView) v.findViewById(R.id.regist_text_search);
		textView.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(actionId == EditorInfo.IME_ACTION_SEARCH) {
					searchMember(v);
				}
				return false;
			}
		});

		AsyncResponse listener = new AsyncResponse() {
			@Override
			public void processDialogFinish(ResponseModel responseModel) {
				// TODO Auto-generated method stub
				List<ResponseUsersModel> responseUserList = responseModel.getUsers();
				recentlyUserList = new ArrayList<ResponseUsersModel>();
				nonRegisterList = new ArrayList<ResponseUsersModel>();
				allUserList = new ArrayList<ResponseUsersModel>();
				for(ResponseUsersModel model : responseUserList) {
					if("1".equals(model.getRegistType())) {
						recentlyUserList.add(model);
					} else if("2".equals(model.getRegistType())) {
						nonRegisterList.add(model);
					} else {
						allUserList.add(model);
					}
				}
				makeAutoCompleteStringArray();
				makeListAdapter();
				// Auto Complete TextView Adapter
		 		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
		                 android.R.layout.simple_dropdown_item_1line, totalUserName);
		 		
		        textView.setAdapter(adapter);
			}
		};
		
		ProgressDialogThread thread = new ProgressDialogThread(getActivity(),listener);
		thread.execute(ProgressCodeEnum.GET_UNREGISTE_USER_LIST);
		return v;
	}
	/**
	 * passwordCustomDialog 셋팅 및 show
	 * @param view
	 * @param popupDesc
	 * @param user_name
	 */
	private void openDialog(View view, String popupDesc, String user_name) {
		passwordCustomDialog = new PasswordCustomDialog(view.getContext(), popupDesc, user_name, StatusCodeEnum.REGISTRATION, registFragment);
		passwordCustomDialog.show();
	}
	
	private void makeAutoCompleteStringArray(){
		ResponseModel response = new ResponseModel();
		response.setUsers(allUserList);
	    int allUserCnt = allUserList.size();
	    totalUserName = new String[allUserCnt];
	    totalUserName = makeStringArray(totalUserName, response);
	}
	public void makeListAdapter() {
		//미 신청자 + 최근 6개월
 		ResponseModel resultResponse = new ResponseModel();
		recentlyUserList.addAll(nonRegisterList);
		View.OnClickListener mOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!isAdmin) {
					showDialog(v);
				} else {
					RelativeLayout listViewLayout = (RelativeLayout)v.getParent();
					user_name = ((TextView) listViewLayout.findViewById(R.id.userlist_text_eid)).getText().toString();
					
					showAlert(v, getActivity(), getString(R.string.popup_text_regist_admin));
				}
			}
		};
		adapter = new UserListViewAdapter(getActivity(), R.id.regist_layout_listview, recentlyUserList, this.getActivity().getLayoutInflater(), mOnClickListener);
		
		listView.setAdapter(adapter);
		userListName = new String[recentlyUserList.size()];
		resultResponse.setUsers(recentlyUserList);
		userListName = makeStringArray(userListName, resultResponse);
	}
	
	public void checkShowDialog(String user_name, String popupDesc, View v) {
		if(!isAdmin) {
			openDialog(v, popupDesc, user_name);
		} else {
			showAlert(v, getActivity(), getString(R.string.popup_text_regist_admin));
		}
	}
	private String[] makeStringArray(String[] strArray, ResponseModel response) {
		int responseSize = response.getUsers().size();
		for(int index=0; index<responseSize; index++) {
			strArray[index] = response.getUsers().get(index).getUsername();
		}
		return strArray;
	}
	
	public void searchMember(View view) {
		Log.i(REGIST_FLAG, "search member");
		String searchMember = textView.getText().toString();		// 검색란에 입력한 값

		if(searchMember == null || "".equals(searchMember)) {			// 검색 하고자 하는 enterprise Id를 입력하지 않았을 경우 초기화면 리스트
			refreshUserList();
		} else {														// 검색 하고자 하는 enterprise id를 입력
			searchOneMember(searchMember);
		}
	}
	
	private void searchOneMember(String searchMember) {
		RequestModel request = new RequestModel();
		request.setSearchType(SEARCH);
		request.setKeyword(searchMember);
		
		AsyncResponse listener = new AsyncResponse() {
			
			@Override
			public void processDialogFinish(ResponseModel responseModel) {
				refreshAdapter(responseModel.getUsers());
			}
		};
		
		ProgressDialogThread thread = new ProgressDialogThread(getActivity(),listener);
		thread.execute(ProgressCodeEnum.GET_KEYWORD_USER, request);
		
	}
	

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position,
			long id) {
		user_name = userListName[position];
		
//		String userName = userListName[position];
		String popupDesc = getString(R.string.popup_text_regist); 
//		openDialog(view, popupDesc, userName);
		checkShowDialog(user_name, popupDesc, view);
	}
	
	public void refreshUserList() {
		ResponseModel result = new ResponseModel();
		// user_name과 동일한 이름의 recentlyUserList안의 index를 가져온다.
		int listSize = recentlyUserList.size();
		List<Integer> indexList = new ArrayList<Integer>();
		for(int index=0; index<listSize; index++) {
			if(recentlyUserList.get(index).getUsername().equals(user_name)) {
				indexList.add(index);
			}
		}
		// indexList에 있는 index를 큰 수(뒤)부터 시작.
		// 앞에서부터 remove할 경우 큰 수(뒤)의 index에 접근할 시 Exception발생
		int indexListSize = indexList.size();
		for(int index=(indexListSize-1); index>=0; index--) {
			recentlyUserList.remove(recentlyUserList.get(indexList.get(index)));
		}
		
		userListName = new String[recentlyUserList.size()];
		result.setUsers(recentlyUserList);
		userListName = makeStringArray(userListName, result);
		
		
		AsyncResponse refreshListener = new AsyncResponse() {
			@Override
			public void processDialogFinish(ResponseModel responseModel) {
				List<ResponseUsersModel> responseUserList = responseModel.getUsers();
				recentlyUserList = new ArrayList<ResponseUsersModel>();
				nonRegisterList = new ArrayList<ResponseUsersModel>();
				
				for(ResponseUsersModel model : responseUserList) {
					if("1".equals(model.getRegistType())) {
						recentlyUserList.add(model);
					} else if("2".equals(model.getRegistType())) {
						nonRegisterList.add(model);
					}
				}
				
				List<ResponseUsersModel> refreshList = new ArrayList<ResponseUsersModel>();
				refreshList.addAll(recentlyUserList);
				refreshList.addAll(nonRegisterList);
				recentlyUserList.addAll(nonRegisterList);
				refreshAdapter(refreshList);
			}
		};
		ProgressDialogThread thread = new ProgressDialogThread(getActivity(),refreshListener);
		thread.execute(ProgressCodeEnum.GET_UNREGISTE_USER_LIST);
	}
	public void refreshAdapter(List<ResponseUsersModel> list) {
		if(list.isEmpty()) {
			Toast.makeText(getActivity(), "No result!!", Toast.LENGTH_SHORT).show();
		}else {
			adapter.clear();
			for (int i = 0; i < list.size(); i++) {
				adapter.add(list.get(i));
			}
			//어댑터만 갱신함.
			adapter.notifyDataSetChanged();
		}
	}
	
	public void showDialog(View view) {
		String popupDesc = getString(R.string.popup_text_regist);
		String user_name = "";
		RelativeLayout rl = (RelativeLayout)view.getParent();
		TextView eid = (TextView) rl.findViewById(R.id.userlist_text_eid);
		user_name = eid.getText().toString();
		
		checkShowDialog(user_name, popupDesc, view);
//		openDialog(view, popupDesc, user_name);
	}

	@Override
	public void onClick(View v) {
		if(v == regist_search_button) {
			searchMember(v);
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		// 화면 전환 시 조회 TextView 초기화 
		textView.setText(null);
	}
	
	public void showAlert(View v, Context context, String message) {
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setPositiveButton(context.getString(R.string.popup_button_ok), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				RequestModel request = new RequestModel();
				request.setUser_id(user_name);
				ResponseModel response = new ResponseModel();
				request.setAdminMode("true");
				response = ApiUtil.callApi(StatusCodeEnum.REGISTRATION, request);
				refreshUserList();
				
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
