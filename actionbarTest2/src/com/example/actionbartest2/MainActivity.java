package com.example.actionbartest2;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener, TabListener, ActionBar.OnNavigationListener {

	private int menu_resource_id = R.menu.main;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar ab = getActionBar();
		
		//ab.setHomeButtonEnabled(true); // 이 코드를 통해 타이틀바의 ICON 터치에 반응하도록 함
		
		// 액션바 아이콘 옆에 < 가 표시되도록 함(이 코드가 실행되면 자동으로 타이틀 옆의 ICON 터치가 반응하도록 됨)
		ab.setDisplayHomeAsUpEnabled(true);
	
		// 액션바에 사용자정의 뷰 추가
		View myButtonLayout = getLayoutInflater().inflate(R.layout.mybutton, null);
		myButtonLayout.findViewById(R.id.mybutton1).setOnClickListener(this);
		myButtonLayout.findViewById(R.id.mybutton2).setOnClickListener(this);
		
		ab.setCustomView(myButtonLayout);
		ab.setDisplayShowCustomEnabled(true);
		// .
		
		// 탭 네비게이션
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ab.addTab(ab.newTab().setText("Tab1").setTabListener(this));
		ab.addTab(ab.newTab().setText("Tab2").setTabListener(this));
		// .
		
		// 드롭다운 네비게이션
		//SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.action_list, android.R.layout.simple_spinner_dropdown_item);
		//ab.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		//ab.setListNavigationCallbacks(spinnerAdapter, this);
		// .
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		Toast.makeText(this, tab.getText() + " onTabReselected", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Toast.makeText(this, tab.getText() + " onTabSelected", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		Toast.makeText(this, tab.getText() + " onTabUnselected", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View view) {
		Toast.makeText(this, "Button Clicked " + ((Button)view).getText(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(menu_resource_id, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case android.R.id.home:
			Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.menu_refesh:
			Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();

			// 액션바 스위칭
			if(menu_resource_id == R.menu.main) menu_resource_id = R.menu.main2;
			else menu_resource_id = R.menu.main;
			this.invalidateOptionsMenu();

			break;
			
		case R.id.menu_search:
			Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
			getActionBar().setSubtitle("Search ActionBar Item을 실행하였습니다.");
			
			break;
			
		case R.id.menu_share:
			
			// 액션바 보이기/감추기
			ActionBar ab = getActionBar();
			if(ab.isShowing()) getActionBar().hide();
			else ab.show();
			
			Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		String[] actions = getResources().getStringArray(R.array.action_list);
		Toast.makeText(this, actions[itemPosition], Toast.LENGTH_SHORT).show();
		
		return false;
	}
}