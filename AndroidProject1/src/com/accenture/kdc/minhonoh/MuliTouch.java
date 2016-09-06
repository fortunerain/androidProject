package com.accenture.kdc.minhonoh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MuliTouch extends Activity {
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    MuliTouchView muliTouchView = new MuliTouchView(this);
        setContentView(muliTouchView);
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(getApplicationContext(), SubActivity.class);
		intent.putExtra("menuNum", 1);
		startActivity(intent);
		finish();
		super.onBackPressed();
	}
}
