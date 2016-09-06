package com.accenture.mplt;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.accenture.mplt.comm.CommApplication;
import com.accenture.mplt.comm.constant.ProgressCodeEnum;
import com.accenture.mplt.comm.constant.ResponseCodeEnum;
import com.accenture.mplt.comm.constant.StatusCodeEnum;
import com.accenture.mplt.comm.constant.TabInfoEnum;
import com.accenture.mplt.comm.util.AsyncResponse;
import com.accenture.mplt.comm.util.ProgressDialogThread;
import com.accenture.mplt.model.RequestModel;
import com.accenture.mplt.model.ResponseModel;

public class PasswordCustomDialog extends Dialog implements View.OnClickListener {

	private EditText password;
	private TextView tv;
	private Button btn_ok, btn_cancel;
	private String explainTextView;
	private String user_name;
	private StatusCodeEnum status;
	private Fragment returnFragment;
	private FragmentTabHost mTabHost;
			
	// ProgressDialog Thread Listener 
	AsyncResponse listener = new AsyncResponse() {
		
		@Override
		public void processDialogFinish(ResponseModel responseModel) {
			String resultcode = responseModel.getResult().getCode();
			String resultMsg = responseModel.getResult().getMessage();
			
			if(!ResponseCodeEnum.SUCCESS.getCode().equals(resultcode)) {
				Toast.makeText(getContext(), resultMsg, Toast.LENGTH_SHORT).show();
			}else {
				//�������� �� 
				if(StatusCodeEnum.CHECK_ADMIN_PASSWORD.equals(status)) {
					//�� ���� �������� ����
					CommApplication app = (CommApplication) getContext().getApplicationContext();
					app.setAdmin(true);
					
					addSettingTab();
					
					mTabHost.setCurrentTab(1);
				}else {
					//�θ��� ����Ʈ �� �����������.
					int currTabIdx = mTabHost.getCurrentTab();
					if(currTabIdx == 1) {
						UserListFragment userListFragment = (UserListFragment) returnFragment;
						userListFragment.refreshUserList();
					}else if(currTabIdx == 2) {
						RegistFragment registFragment = (RegistFragment) returnFragment;
						registFragment.refreshUserList();
					}
				}
				dismiss();
				Toast.makeText(getContext(), resultMsg, Toast.LENGTH_SHORT).show();
			}
		}

		private void addSettingTab() {
			mTabHost.addTab(MainTabActivity.setIndicator(
					getOwnerActivity(),
					mTabHost.newTabSpec(getContext().getString(
							TabInfoEnum.TAB_SETT.getTabName())),
					TabInfoEnum.TAB_SETT.getTabBarImage(),
					TabInfoEnum.TAB_SETT.getTabIconImageGray()),
					TabInfoEnum.TAB_SETT.getTargetClass(), null);
		}
	};
	
	/**
	 * 
	 * @param context
	 * @param explainTextView : �˾��� ������ ����
	 * @param user_name : Ŭ���� ������ �̸�
	 * @param status : ������� ��������� ���� ����
	 * @param returnFragment : �ش� �۾��� ������ ������ ȭ�� class
	 */
	public PasswordCustomDialog(Context context, String explainTextView,
			String user_name, StatusCodeEnum status, Fragment returnFragment) {
		super(context);
		//�˾��� ȣ���� �θ��� activity�� ������
		setOwnerActivity((Activity) context);
		this.explainTextView = explainTextView;
		this.user_name = user_name;
		this.status = status;
		this.returnFragment = returnFragment;
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//title ���ֱ�
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.password_custom_dialog);
		
		//�ִϸ��̼� ȿ�� �ֱ�      
		getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
		getWindow().setGravity(Gravity.CENTER);
		
		
		mTabHost = (FragmentTabHost) getOwnerActivity().findViewById(android.R.id.tabhost);
		tv = (TextView) findViewById(R.id.popup_text_desc);
		tv.setText(explainTextView);
		password = (EditText) findViewById(R.id.popup_text_password);
		btn_ok = (Button) findViewById(R.id.popup_button_ok);
		btn_cancel = (Button) findViewById(R.id.popup_button_cancel);
		
		btn_ok.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
		
		//Ű���� ������ ����
		new Handler().postDelayed(new Runnable() {
            public void run() {
            	InputMethodManager mImm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                mImm.showSoftInput(password, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 100);
		
	}


	@Override
	public void onClick(View v) {
		String passwordStr = password.getText().toString();
		
		if(v == btn_ok) {
			if(passwordStr.isEmpty()) {
				Toast.makeText(getContext(), R.string.error_eno_empty, Toast.LENGTH_SHORT).show();
			}else {
				RequestModel requestModel = makeRequestModel(user_name, passwordStr);
				
				//�� ȭ���� ���¿� ���� api ȣ��
				ProgressDialogThread thread = new ProgressDialogThread(getContext(),listener);
				thread.execute(ProgressCodeEnum.DIALOG_API_CALL, requestModel,status);
			}
		}else if(v == btn_cancel) {
			cancel();
		}
	}
	
	public RequestModel makeRequestModel(String user_name, String passwordStr) {
		RequestModel model = new RequestModel();
		model.setUser_id(user_name);
		model.setPassword(passwordStr);
		
		return model;
		
	} 
	
}
