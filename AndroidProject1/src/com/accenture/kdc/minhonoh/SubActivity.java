package com.accenture.kdc.minhonoh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_sub);
	    // TODO Auto-generated method stub
	    
	    Intent intent = getIntent();
	    int menuNum = intent.getIntExtra("menuNum",0);
	    Button subMenuBtn1 = (Button) findViewById(R.id.subMenuBtn1);
	    Button subMenuBtn2 = (Button) findViewById(R.id.subMenuBtn2);
	    TextView subMenuTextView = (TextView) findViewById(R.id.subMenuTextView);
	    
	    
	    /**
		 * OnClickListener로 접근하는 방법
		 */
	    switch (menuNum) {
		case 1:
			subMenuTextView.setText("화면 테스트");
			subMenuBtn1.setText("불량 화소 테스트");
			subMenuBtn2.setText("멀티 터치 테스트");
			subMenuBtn1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(getApplicationContext(), ExplainView.class);
					intent.putExtra("subMenuNum", "screenAndDeadPixels");
					startActivity(intent);
				}
			});
			
			subMenuBtn2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(getApplicationContext(), ExplainView.class);
					intent.putExtra("subMenuNum", "muliTouch");
					startActivity(intent);
				}
			});
			
			break;
			
		case 2:
			subMenuTextView.setText("알림 테스트");
			subMenuBtn1.setText("사운드 테스트");
			subMenuBtn2.setText("진동 테스트");
			
			subMenuBtn1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(getApplicationContext(), ExplainView.class);
					intent.putExtra("subMenuNum", "soundTest");
					startActivity(intent);
				}
			});
			
			subMenuBtn2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(getApplicationContext(), ExplainView.class);
					intent.putExtra("subMenuNum", "vibration");
					startActivity(intent);
				}
			});
			
			break;

		}
	    
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(intent);
		finish();
		super.onBackPressed();
	}

}
