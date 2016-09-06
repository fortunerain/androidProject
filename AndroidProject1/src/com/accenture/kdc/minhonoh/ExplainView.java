package com.accenture.kdc.minhonoh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class ExplainView extends Activity {
	private static final String LOG_TAG = "ExplainView";	
	private String subMenuNum = "";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.explainview);
	    
	    Intent intent = getIntent();
	    subMenuNum = intent.getStringExtra("subMenuNum");
	    Log.i(LOG_TAG, "subMenuNum : "+subMenuNum);
	    
	    TextView tt = (TextView)findViewById(R.id.explain_title);
	    TextView tv1 = (TextView)findViewById(R.id.explain_textView1);
	    ImageView iv1 = (ImageView)findViewById(R.id.explain_imageView1);
	    
	    switch (subMenuNum) {
		case "screenAndDeadPixels":
			tt.setText("불량 화소 테스트");
			iv1.setImageResource(R.drawable.deadpixel);
	    	tv1.setText("기기 불량 화소를 찾아내는 테스트 입니다. \n각 화면마다 육안으로 불량화소가 있는지 찾아내세요.\n" +
	    			"검사하기 전에 화면을 깨끗하게 해주세요.");
			break;
		case "muliTouch":
			tt.setText("멀티 터치 테스트");
			iv1.setImageResource(R.drawable.multitouch);
	    	tv1.setText("기기의 멀티터치 기능이 이상이 있는지 없는지 확인하는 테스트입니다.\n" +
	    			"여러개 터치 가능합니다.");
			break;
		case "soundTest":
			tt.setText("사운드 테스트");
			iv1.setImageResource(R.drawable.deadpixel);
	    	tv1.setText("기기의 사운드 테스트 입니다. \nroot 경로에 있는 mp3 파일을 찾아서 순서대로 플레이 합니다.\n");
			break;
		case "vibration":
			tt.setText("진동 테스트");
			iv1.setImageResource(R.drawable.deadpixel);
	    	tv1.setText("기기 진동 테스트 입니다. \n화면을 터치하면 진동이 울리는지 확인하세요.\n");
			break;
		case "gpsTest":
			tt.setText("GPS 테스트");
			iv1.setImageResource(R.drawable.gps);
			tv1.setText("GPS신호를 검사합니다. 정확한 검사를 위해서\n실외에서 테스트하기를 권장합니다. \n테스트 시작 전 시스템 설정의 무선 네트워크와\nGPS위성을 사용하시길 바랍니다.\n");
			break;
		case "cameraTest":
			tt.setText("카메라 테스트");
			iv1.setImageResource(R.drawable.camera);
			tv1.setText("기기의 카메라 기능이 이상이 있는지 없는지\n 확인하는 테스트 입니다.\n촬영 후 자동 저장 됩니다.\n");
			break;
		}
	    
	    TextView tv2 = (TextView)findViewById(R.id.explain_textView2);
	    tv2.setText("화면을 터치하면 테스트를 시작합니다.");
	    
	    
	    
	}

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Intent intent = null;
		switch (subMenuNum) {
		case "screenAndDeadPixels":
			intent = new Intent(getApplicationContext(), ScreenAndDeadPixels.class);
			break;
		case "muliTouch":
			intent = new Intent(getApplicationContext(), MuliTouch.class);
			break;
		case "soundTest":
			intent = new Intent(getApplicationContext(), SoundTest.class);
			break;
		case "vibration":
			Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		    vibrator.vibrate(300);
			break;
		case "gpsTest":
			intent = new Intent(getApplicationContext(), Gps.class);
			break;
		case "cameraTest":
			intent = new Intent(getApplicationContext(), CameraTest.class);
			break;
		}
		if(!"vibration".equals(subMenuNum)) {
			startActivity(intent);
			finish();
		}
		return true;
	}

}
