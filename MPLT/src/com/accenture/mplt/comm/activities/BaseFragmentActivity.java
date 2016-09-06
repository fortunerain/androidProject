package com.accenture.mplt.comm.activities;

import java.text.ParseException;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.accenture.mplt.BackPressCloseHandler;
import com.accenture.mplt.R;
import com.accenture.mplt.comm.CommApplication;
import com.accenture.mplt.comm.util.ApiUtil;
import com.accenture.mplt.comm.util.CommonUtil;
import com.accenture.mplt.model.ResponseModel;

public abstract class BaseFragmentActivity extends FragmentActivity {
    public static final String TAG = "BaseActivity";
    public static boolean isAdmin = false;
    public static String appTitle = "";
    public static String appMonth = "";
    public static ActionBar actionbar;
    public static ResponseModel settingModel = null;
    private BackPressCloseHandler backPressCloseHandler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionbar_custom);
        isAdmin = false;
        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initialize();
        Log.i("onStart", "onStart");
    }
    
	@Override
	public void onBackPressed() {
		 backPressCloseHandler.onBackPressed();
	}

	@SuppressLint("NewApi")
	public void initialize() {
    	//application ���� ����
		CommApplication app = (CommApplication) getApplication();
    	isAdmin = app.isAdmin();
    	settingModel = app.getSetting();
    	
    	//action bar ����
    	actionbar = getActionBar();
    	actionbar.setCustomView(R.layout.actionbar_custom);
    	actionbar.setDisplayShowCustomEnabled(true);
    	actionbar.setDisplayShowHomeEnabled(false);
    	actionbar.setDisplayShowTitleEnabled(false);
    	
    	// Tab host�� ���ÿ� ���� �б� ���� 
    	FragmentTabHost mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
    	int tabindex = mTabHost.getCurrentTab();
    	ImageView actionBar_rightImage = (ImageView) findViewById(R.id.actionBar_image_right);
    	actionBar_rightImage.setBackgroundResource(R.drawable.accenturelogo);
    	//�̹����� �ڵ����� �ػ󵵿� ���� �� drawable ������ �����Ͽ� �־����. ������ �̹��� ũ�⸦ ��ġ�� ����.
//    	actionBar_rightImage.getLayoutParams().width = 163;
//    	actionBar_rightImage.getLayoutParams().height = 56;
    	
    	if(tabindex == 0) {	// home �ϰ�� isAdmin�� ������ false
			isAdmin = false;
//			settingModel = null;
		} else if(tabindex == 3) { //setting tab �� ��� save ��ư���� �����
			actionBar_rightImage.setClickable(true);
			actionBar_rightImage.setBackgroundResource(R.drawable.save);
//			actionBar_rightImage.getLayoutParams().width = 113;
//	    	actionBar_rightImage.getLayoutParams().height = 56;
		} else {
			actionBar_rightImage.setClickable(false);
		}
    	
    	
    	//action bar title text ����
    	if(appTitle.isEmpty()) {
    		appTitle = getString(R.string.app_name);
    	}
    	if(settingModel == null) {
    		settingModel = ApiUtil.getSettingInfo(null);
    	}
		try {
			appMonth = String.format("%02d", CommonUtil.makeMMDate(settingModel.getStart_date())) + " ~ " +
						String.format("%02d", CommonUtil.makeMMDate(settingModel.getEnd_date()));
		} catch (ParseException e) {
			Log.e("ParseException", "appMonth ParseException");
		}
		
		TextView actionBarTitleView = (TextView) findViewById(R.id.actionBar_text_title);
    	TextView actionBarMonth = (TextView) findViewById(R.id.actionBar_text_month);
    	actionBarTitleView.setText(appTitle);
    	actionBarMonth.setText(appMonth);
		
    	//action bar ��� ����
		ImageView authImage = (ImageView) findViewById(R.id.actionBar_image_auth);
		RelativeLayout customLayout = (RelativeLayout)findViewById(R.id.actionBar_layout);
		authImage.setBackground(null);
		if(isAdmin) {
			authImage.setBackgroundResource(R.drawable.a);
			actionBarMonth.setTextColor(getResources().getColor(R.color.white_color));
			customLayout.setBackgroundResource(R.color.black_color);
			actionBarTitleView.setTextColor(getResources().getColor(R.color.white_color));
		} else {
			authImage.setBackgroundResource(R.drawable.p);
			actionBarMonth.setTextColor(getResources().getColor(R.color.wine_color));
			customLayout.setBackgroundResource(R.color.action_bar_background_gray);
			actionBarTitleView.setTextColor(getResources().getColor(R.color.wine_color));
		}
    	
    	app.setAdmin(isAdmin);
    	app.setSetting(settingModel);
    }
    
}
