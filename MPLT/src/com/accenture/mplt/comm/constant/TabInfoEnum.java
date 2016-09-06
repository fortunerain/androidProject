package com.accenture.mplt.comm.constant;

import com.accenture.mplt.HomeFragment;
import com.accenture.mplt.R;
import com.accenture.mplt.RegistFragment;
import com.accenture.mplt.SettingFragment;
import com.accenture.mplt.UserListFragment;

public enum TabInfoEnum {
	TAB_HOME (R.string.main_title, R.drawable.redline_bottom_user, R.drawable.redline_bottom_admin, R.drawable.accent_gray, R.drawable.accent_red, HomeFragment.class),
	TAB_USER (R.string.userlist_title, R.drawable.redline_bottom_user, R.drawable.redline_bottom_admin, R.drawable.userlist_gray, R.drawable.userlist_red, UserListFragment.class),
	TAB_REGI (R.string.regist_title, R.drawable.redline_bottom_user, R.drawable.redline_bottom_admin, R.drawable.register_gray, R.drawable.register_red, RegistFragment.class),
	TAB_SETT (R.string.setting_title, R.drawable.redline_bottom_user, R.drawable.redline_bottom_admin, R.drawable.seettings_gray, R.drawable.seettings_red, SettingFragment.class);

	private int tabName;
	private int tabBarImage;
	private int tabBarImageAdmin;
	private int tabIconImageGray;
	private int tabIconImageRed;
	private Class<?> targetClass;
	
	private TabInfoEnum(int tabName, int tabBarImage, int tabBarImageAdmin,
			int tabIconImageGray, int tabIconImageRed, Class<?> targetClass) {
		this.tabName = tabName;
		this.tabBarImage = tabBarImage;
		this.tabBarImageAdmin = tabBarImageAdmin;
		this.tabIconImageGray = tabIconImageGray;
		this.tabIconImageRed = tabIconImageRed;
		this.targetClass = targetClass;
	}
	
	
	public int getTabName() {
		return tabName;
	}
	public void setTabName(int tabName) {
		this.tabName = tabName;
	}
	public int getTabBarImage() {
		return tabBarImage;
	}
	public void setTabBarImage(int tabBarImage) {
		this.tabBarImage = tabBarImage;
	}
	public int getTabBarImageAdmin() {
		return tabBarImageAdmin;
	}
	public void setTabBarImageAdmin(int tabBarImageAdmin) {
		this.tabBarImageAdmin = tabBarImageAdmin;
	}
	public int getTabIconImageGray() {
		return tabIconImageGray;
	}
	public void setTabIconImageGray(int tabIconImageGray) {
		this.tabIconImageGray = tabIconImageGray;
	}
	public int getTabIconImageRed() {
		return tabIconImageRed;
	}
	public void setTabIconImageRed(int tabIconImageRed) {
		this.tabIconImageRed = tabIconImageRed;
	}
	public Class<?> getTargetClass() {
		return targetClass;
	}
	public void setTargetClass(Class<?> targetClass) {
		this.targetClass = targetClass;
	}
	
	
	
}
