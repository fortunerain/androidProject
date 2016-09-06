package com.example.fragmenttabhostsample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity {
	private FragmentTabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(setIndicator(MainActivity.this, mTabHost.newTabSpec("Tab 1"), R.drawable.abc_tab_indicator_material, R.drawable.ic_launcher),
                FragmentTab1.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Tab 2", getResources().getDrawable(R.drawable.ic_launcher)),
                FragmentTab2.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Tab 3", getResources().getDrawable(R.drawable.ic_launcher)),
                FragmentTab3.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab4").setIndicator("Tab 4", getResources().getDrawable(R.drawable.ic_launcher)),
                FragmentTab4.class, null);
        
        //탭별 배경화면 바꾸기
        //배경화면만 바꿀 수 있다. 탭 자체를 customize 할 수 없다.
        /*final TabWidget tabWidget = mTabHost.getTabWidget();
        for (int i = 0; i < tabWidget.getTabCount(); i++) {
            final View tab = tabWidget.getChildTabViewAt(i);
            tab.setBackground(getResources().getDrawable(R.drawable.ic_launcher));
        }*/
	}

	private TabSpec setIndicator(Context ctx, TabSpec spec, int tab_bar_res, int tab_icon_res) {
		View v = LayoutInflater.from(ctx).inflate(R.layout.tab_content_layout, null);
//		v.setBackgroundResource(resid);
		ImageView tab_bar = (ImageView) v.findViewById(R.id.tab_bar);
		ImageView tab_icon = (ImageView) v.findViewById(R.id.tab_icon);

		tab_bar.setBackgroundResource(tab_bar_res);
		tab_icon.setBackgroundResource(tab_icon_res);
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
}
