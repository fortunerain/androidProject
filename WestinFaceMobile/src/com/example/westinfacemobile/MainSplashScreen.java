package com.example.westinfacemobile;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.example.westinfacemobile.model.ActivityModel;

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
	private class SplashScreenAsync extends AsyncTask<Void, Void, ActivityModel> {

		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected ActivityModel doInBackground(Void... params) {
//			RequestModel request = new RequestModel();
			
			ActivityModel responseModel = new ActivityModel();
			return responseModel;
		} 

		@Override
		protected void onPostExecute(ActivityModel result) {
			super.onPostExecute(result);
			Handler hd = new Handler();
	        hd.postDelayed(new Runnable() {
	 
	            @Override
	            public void run() {
	                finish();       // 3 초후 이미지를 닫아버림
	                Intent i = new Intent(MainSplashScreen.this, MainTabActivity.class);
	                startActivity(i);
	            }
	        }, 3000);		

		}
	}
}
