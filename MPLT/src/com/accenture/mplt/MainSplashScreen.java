package com.accenture.mplt;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.accenture.mplt.comm.CommApplication;
import com.accenture.mplt.comm.util.ApiUtil;
import com.accenture.mplt.model.RequestModel;
import com.accenture.mplt.model.ResponseModel;

public class MainSplashScreen extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		new SplashScreenAsync().execute();
	}
	
	/**
	 * Async Task to make http call
	 */
	private class SplashScreenAsync extends AsyncTask<Void, Void, ResponseModel> {

		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected ResponseModel doInBackground(Void... params) {
			RequestModel request = new RequestModel();
			
			ResponseModel responseModel = ApiUtil.getSettingInfo(request);
			return responseModel;
		} 

		@Override
		protected void onPostExecute(ResponseModel result) {
			super.onPostExecute(result);
			
			CommApplication app = (CommApplication) getApplication();
			app.setSetting(result);
			
			Intent i = new Intent(MainSplashScreen.this, MainTabActivity.class);
			startActivity(i);

            // close this activity
            finish();
		}
	}
}
