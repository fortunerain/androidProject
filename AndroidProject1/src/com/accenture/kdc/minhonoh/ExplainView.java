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
			tt.setText("�ҷ� ȭ�� �׽�Ʈ");
			iv1.setImageResource(R.drawable.deadpixel);
	    	tv1.setText("��� �ҷ� ȭ�Ҹ� ã�Ƴ��� �׽�Ʈ �Դϴ�. \n�� ȭ�鸶�� �������� �ҷ�ȭ�Ұ� �ִ��� ã�Ƴ�����.\n" +
	    			"�˻��ϱ� ���� ȭ���� �����ϰ� ���ּ���.");
			break;
		case "muliTouch":
			tt.setText("��Ƽ ��ġ �׽�Ʈ");
			iv1.setImageResource(R.drawable.multitouch);
	    	tv1.setText("����� ��Ƽ��ġ ����� �̻��� �ִ��� ������ Ȯ���ϴ� �׽�Ʈ�Դϴ�.\n" +
	    			"������ ��ġ �����մϴ�.");
			break;
		case "soundTest":
			tt.setText("���� �׽�Ʈ");
			iv1.setImageResource(R.drawable.deadpixel);
	    	tv1.setText("����� ���� �׽�Ʈ �Դϴ�. \nroot ��ο� �ִ� mp3 ������ ã�Ƽ� ������� �÷��� �մϴ�.\n");
			break;
		case "vibration":
			tt.setText("���� �׽�Ʈ");
			iv1.setImageResource(R.drawable.deadpixel);
	    	tv1.setText("��� ���� �׽�Ʈ �Դϴ�. \nȭ���� ��ġ�ϸ� ������ �︮���� Ȯ���ϼ���.\n");
			break;
		case "gpsTest":
			tt.setText("GPS �׽�Ʈ");
			iv1.setImageResource(R.drawable.gps);
			tv1.setText("GPS��ȣ�� �˻��մϴ�. ��Ȯ�� �˻縦 ���ؼ�\n�ǿܿ��� �׽�Ʈ�ϱ⸦ �����մϴ�. \n�׽�Ʈ ���� �� �ý��� ������ ���� ��Ʈ��ũ��\nGPS������ ����Ͻñ� �ٶ��ϴ�.\n");
			break;
		case "cameraTest":
			tt.setText("ī�޶� �׽�Ʈ");
			iv1.setImageResource(R.drawable.camera);
			tv1.setText("����� ī�޶� ����� �̻��� �ִ��� ������\n Ȯ���ϴ� �׽�Ʈ �Դϴ�.\n�Կ� �� �ڵ� ���� �˴ϴ�.\n");
			break;
		}
	    
	    TextView tv2 = (TextView)findViewById(R.id.explain_textView2);
	    tv2.setText("ȭ���� ��ġ�ϸ� �׽�Ʈ�� �����մϴ�.");
	    
	    
	    
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
