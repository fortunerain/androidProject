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
    	//application 변수 셋팅
		CommApplication app = (CommApplication) getApplication();
    	isAdmin = app.isAdmin();
    	settingModel = app.getSetting();
    	
    	//action bar 셋팅
    	actionbar = getActionBar();
    	actionbar.setCustomView(R.layout.actionbar_custom);
    	actionbar.setDisplayShowCustomEnabled(true);
    	actionbar.setDisplayShowHomeEnabled(false);
    	actionbar.setDisplayShowTitleEnabled(false);
    	
    	// Tab host의 선택에 따른 분기 설정 
    	FragmentTabHost mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
    	int tabindex = mTabHost.getCurrentTab();
    	ImageView actionBar_rightImage = (ImageView) findViewById(R.id.actionBar_image_right);
    	actionBar_rightImage.setBackgroundResource(R.drawable.accenturelogo);
    	//이미지는 핸드폰의 해상도에 따라 각 drawable 폴더에 구분하여 있어야함. 강제로 이미지 크기를 고치진 않음.
//    	actionBar_rightImage.getLayoutParams().width = 163;
//    	actionBar_rightImage.getLayoutParams().height = 56;
    	
    	if(tabindex == 0) {	// home 일경우 isAdmin은 무조건 false
			isAdmin = false;
//			settingModel = null;
		} else if(tabindex == 3) { //setting tab 일 경우 save 버튼으로 변경됨
			actionBar_rightImage.setClickable(true);
			actionBar_rightImage.setBackgroundResource(R.drawable.save);
//			actionBar_rightImage.getLayoutParams().width = 113;
//	    	actionBar_rightImage.getLayoutParams().height = 56;
		} else {
			actionBar_rightImage.setClickable(false);
		}
    	
    	
    	//action bar title text 셋팅
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
		
    	//action bar 배경 셋팅
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
