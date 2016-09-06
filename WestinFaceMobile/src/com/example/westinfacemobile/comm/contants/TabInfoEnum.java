package com.example.westinfacemobile.comm.contants;


import android.support.v4.app.Fragment;

import com.example.westinfacemobile.ActivityListFragment;
import com.example.westinfacemobile.ActivityScheduleFragment;
import com.example.westinfacemobile.GroupActivityListFragment;
import com.example.westinfacemobile.R;

public enum TabInfoEnum {
	TAB_ACTIVITY (R.string.tab_title1, R.color.wine_color, R.color.dark_gray_color, ActivityListFragment.class, new ActivityListFragment()),
	TAB_GROUP (R.string.tab_title2, R.color.wine_color, R.color.dark_gray_color, GroupActivityListFragment.class, new GroupActivityListFragment()),
	TAB_SCHEDULE (R.string.tab_title3, R.color.wine_color, R.color.dark_gray_color, ActivityScheduleFragment.class, new ActivityScheduleFragment())
	;

	private int tabName;
	private int tabOnColor;
	private int tabOffColor;
	private Class<?> targetClass;
	private Fragment tabFragment;
	
	private TabInfoEnum(int tabName, int tabOnColor, int tabOffColor, Class<?> targetClass, Fragment tabFragment) {
		this.tabName = tabName;
		this.tabOnColor = tabOnColor;
		this.tabOffColor = tabOffColor;
		this.targetClass = targetClass;
		this.tabFragment = tabFragment;
	}

	public int getTabName() {
		return tabName;
	}

	public void setTabName(int tabName) {
		this.tabName = tabName;
	}

	public int getTabOnColor() {
		return tabOnColor;
	}

	public void setTabOnColor(int tabOnColor) {
		this.tabOnColor = tabOnColor;
	}

	public int getTabOffColor() {
		return tabOffColor;
	}

	public void setTabOffColor(int tabOffColor) {
		this.tabOffColor = tabOffColor;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(Class<?> targetClass) {
		this.targetClass = targetClass;
	}

	public Fragment getTabFragment() {
		return tabFragment;
	}

	public void setTabFragment(Fragment tabFragment) {
		this.tabFragment = tabFragment;
	}
	
}
