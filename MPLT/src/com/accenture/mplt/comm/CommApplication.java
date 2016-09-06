package com.accenture.mplt.comm;

import com.accenture.mplt.model.ResponseModel;

import android.app.Application;
import android.content.res.Configuration;

public class CommApplication extends Application {
	private boolean isAdmin = false;
	private ResponseModel setting = null;
	
	public ResponseModel getSetting() {
		return setting;
	}

	public void setSetting(ResponseModel setting) {
		this.setting = setting;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	/** onCreate()
     * ��Ƽ��Ƽ, ���ù�, ���񽺰� �����Ǳ��� ���ø����̼��� ���� ���϶�
     * Application onCreate() �޼��尡 ����� ���ٰ� ���� �ֽ��ϴ�. 
     * by. Developer ����Ʈ 
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }
 
    /**
     * onConfigurationChanged()
     * ������Ʈ�� ����Ǵ� ���� �ܸ��� ȭ���� �ٲ�� �ý����� ���� �Ѵ�.
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
	
}
