package com.accenture.mplt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.accenture.mplt.comm.activities.BaseFragmentActivity;
import com.accenture.mplt.comm.constant.TabInfoEnum;

@SuppressLint("NewApi")
public class MainTabActivity extends BaseFragmentActivity implements OnTabChangeListener {
	private FragmentTabHost tabHost;
	private String mainTitle = "";
	private TabInfoEnum[] tabInfoEnum;
	
	public FragmentTabHost getTabHost() {
		return tabHost;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_tab);
		
		mainTitle = getString(R.string.main_title);

		tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(),
				android.R.id.tabcontent);

		tabHost.setOnTabChangedListener(this);
		initTab();
		setTabImage();
	
	}

	//TabInfoEnum 에서 탭 정보를 가져와서 갯수 만큼 탭 만들기
	public void initTab() {
		tabHost.clearAllTabs();
		tabInfoEnum = TabInfoEnum.values();
		int tabCnt = TabInfoEnum.values().length;
		if(!isAdmin) {
			tabCnt = tabCnt -1;
		}
		for (int i = 0; i < tabCnt; i++) {
			TabInfoEnum field = tabInfoEnum[i];
			tabHost.addTab(
					setIndicator(MainTabActivity.this,
							tabHost.newTabSpec(getString(field.getTabName())),
							field.getTabBarImage(),
							field.getTabIconImageGray()), field.getTargetClass(), null);
			
		}
		
	}

	public static TabSpec setIndicator(Context ctx, TabSpec spec, int tabBarImage,
			int tabIconImageGray) {
		View v = LayoutInflater.from(ctx).inflate(R.layout.tab_content_layout,
				null);
//		ImageView tab_bar = (ImageView) v.findViewById(R.id.tab_bar);
		ImageView tab_icon = (ImageView) v.findViewById(R.id.tab_image_icon);

//		tab_bar.setBackgroundResource(tabBarImage);
		tab_icon.setBackgroundResource(tabIconImageGray);
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
		if(mainTitle.equals(tabId)) {
			appTitle = "";
			removeSettingTab();
		}else {
			//탭이 바뀔때마다 action bar에 제목 변경함.
			appTitle = tabHost.getCurrentTabTag();
		}
		initialize();
		setTabImage();
		
	}

	private void removeSettingTab() {
		tabHost.getTabWidget().removeView(tabHost.getTabWidget().getChildTabViewAt(3));
	}

	private void setTabImage() {
		ImageView tab_bar = null;
		ImageView tab_icon = null;
		//모든 아이콘 초기화
		for(int i=0;i<tabHost.getTabWidget().getChildCount();i++) {
			tab_bar = (ImageView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.tab_image_bar);
			tab_icon = (ImageView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.tab_image_icon);
			
			
			tab_bar.setBackground(null);
			tab_icon.setBackgroundResource(tabInfoEnum[i].getTabIconImageGray());
		}
		
		int currIdx = tabHost.getCurrentTab();
		
		//해당 탭 아이콘 활성화
		tab_bar = (ImageView) tabHost.getTabWidget().getChildAt(currIdx).findViewById(R.id.tab_image_bar);
		tab_bar.setBackgroundResource(tabInfoEnum[currIdx].getTabBarImage());
		
		tab_icon = (ImageView) tabHost.getTabWidget().getChildAt(currIdx).findViewById(R.id.tab_image_icon);
		tab_icon.setBackgroundResource(tabInfoEnum[currIdx].getTabIconImageRed());
		
	}
}
