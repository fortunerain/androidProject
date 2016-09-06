package com.example.westinfacemobile;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.westinfacemobile.comm.activities.BaseFragmentActivity;
import com.example.westinfacemobile.comm.contants.TabInfoEnum;
import com.example.westinfacemobile.comm.util.BackPressCloseHandler;

@SuppressLint("NewApi")
public class MainTabActivity extends BaseFragmentActivity implements OnTabChangeListener {
	private FragmentTabHost tabHost;
	private String mainTitle = "";
	private TabInfoEnum[] tabInfoEnum;
	private Fragment parentFragment;
	private BackPressCloseHandler backPressCloseHandler;
	
	public Fragment getParentFragment() {
		return parentFragment;
	}

	public void setParentFragment(Fragment parentFragment) {
		this.parentFragment = parentFragment;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_tab_host);
		
		mainTitle = getString(R.string.tab_title1);

		tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(),
				android.R.id.tabcontent);

		tabHost.setOnTabChangedListener(this);
		initTab();
		setTabImage();
		backPressCloseHandler = new BackPressCloseHandler(this);
	}

	/*
	 * TabInfoEnum 에서 탭 정보를 가져와서 갯수 만큼 탭 만들기
	 */
	public void initTab() {
		tabHost.clearAllTabs();
		tabInfoEnum = TabInfoEnum.values();
		int tabCnt = TabInfoEnum.values().length;
		for (int i = 0; i < tabCnt; i++) {
			TabInfoEnum field = tabInfoEnum[i];
			tabHost.addTab(	
					setIndicator(MainTabActivity.this,
							tabHost.newTabSpec(getString(field.getTabName())),
							field.getTabOnColor(),
							field.getTabOffColor()), field.getTargetClass(), null);
			
		}
		
	}

	public static TabSpec setIndicator(Context ctx, TabSpec spec, int tabOnColor,
			int tabOffColor) {
		View v = LayoutInflater.from(ctx).inflate(R.layout.fragment_tab_content_layout,
				null);
		
		TextView text_tab = (TextView) v.findViewById(R.id.text_tab);
		text_tab.setTextColor(tabOffColor);
		text_tab.setText(spec.getTag());
		return spec.setIndicator(v);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabChanged(String tabId) {
		//메인 화면일 때는 설정 탭 삭제
//		if(mainTitle.equals(tabId)) {
//			appTitle = "";
//			removeSettingTab();
//		}else {
			//탭이 바뀔때마다 action bar에 제목 변경함.
			appTitle = tabHost.getCurrentTabTag();
//		}
		initialize();
		setTabImage();
//		clearBackStack(tabId);
		Log.i(TAG, tabId);
	}

	private void setTabImage() {
		TextView text_tab = null;
		int currIdx = tabHost.getCurrentTab();
		//모든 탭 초기화
		for(int i=0;i<tabHost.getTabWidget().getChildCount();i++) {
			text_tab = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.text_tab);
			text_tab.setTextColor(getResources().getColorStateList(tabInfoEnum[i].getTabOffColor()));
		}
		
		//해당 탭 활성화
		text_tab = (TextView) tabHost.getTabWidget().getChildAt(currIdx).findViewById(R.id.text_tab);
		text_tab.setTextColor(getResources().getColorStateList(tabInfoEnum[currIdx].getTabOnColor()));
		
	}
	
	
	public void addFragments(Fragment fragment) {
		FragmentTransaction ft = manager.beginTransaction();
		ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
		ft.replace(android.R.id.tabcontent, fragment);
		ft.commit();
	}
	
	public void removeFragment(Fragment fragment) {
		FragmentTransaction ft = manager.beginTransaction();
		ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
		ft.replace(android.R.id.tabcontent, fragment);
		ft.commit();
	}

	@Override
	public void onBackPressed() {
		int currIdx = tabHost.getCurrentTab();
		if(currIdx != 0) {
			backPressCloseHandler.onBackPressed();
		}else {
			boolean isExistChildFragment = false;
			List<Fragment> listFragment = manager.getFragments();
			int size = listFragment.size();
			
			//child fragment 존재할때 remove함.
			for (int i = 0; i < size; i++) {
				Fragment frag = listFragment.get(i);
				if(frag != null) {
					if("ActivityDetailFragment".equals(frag.getClass().getSimpleName())) {
						isExistChildFragment = true;
						break;
					}
				}
			}
			if(isExistChildFragment) {
				removeFragment(getParentFragment());
			}else {
				backPressCloseHandler.onBackPressed();
			}
		}
		
	}
}
