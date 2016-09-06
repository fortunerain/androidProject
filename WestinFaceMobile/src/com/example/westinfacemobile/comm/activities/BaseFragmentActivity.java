package com.example.westinfacemobile.comm.activities;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.westinfacemobile.R;
import com.example.westinfacemobile.comm.CommApplication;
import com.example.westinfacemobile.comm.contants.TabInfoEnum;

public abstract class BaseFragmentActivity extends FragmentActivity {
    public static final String TAG = "BaseActivity";
    public static String appTitle = "";
    public static String appMonth = "";
    public static ActionBar actionbar;
    public static FragmentManager manager;
    public TabInfoEnum[] tabInfoEnum;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionbar_custom);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initialize();
        Log.i("onStart", "onStart");
    }
    
	@SuppressLint("NewApi")
	public void initialize() {
		CommApplication app = (CommApplication) getApplication();
    	//application 변수 셋팅
    	app.setFragmentManager(getSupportFragmentManager());
    	manager = app.getFragmentManager();
    	
    	//action bar 셋팅
    	actionbar = getActionBar();
    	actionbar.setCustomView(R.layout.actionbar_custom);
    	actionbar.setDisplayShowCustomEnabled(true);
    	actionbar.setDisplayShowHomeEnabled(false);
    	actionbar.setDisplayShowTitleEnabled(false);
    	
    	//action bar 배경 셋팅
		RelativeLayout customLayout = (RelativeLayout)findViewById(R.id.actionBar_layout);
		customLayout.setBackgroundResource(R.color.action_bar_background_gray);
    }
	
	@Deprecated
	protected void clearBackStack(String tabId) {
		List<Fragment> listFragment = manager.getFragments();
		if(listFragment != null) {
			int size = listFragment==null?0:listFragment.size();
			for (int i = 0; i < size; i++) {
				Fragment frag = listFragment.get(i);
				
				if(frag != null && "ActivityDetailFragment".equals(frag.getClass().getSimpleName())) {
					FragmentTransaction ft = manager.beginTransaction();
					ft.remove(frag);
					ft.commit();
				}
			}
		}
	}
	
}
