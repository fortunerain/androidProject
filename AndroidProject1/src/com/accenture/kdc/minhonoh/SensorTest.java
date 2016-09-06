package com.accenture.kdc.minhonoh;

import java.util.List;

import com.accenture.kdc.minhonoh.R;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensorTest extends Activity implements SensorEventListener{
	private static final String LOG_TAG = "SensorTest";
	// 센서 관리자
	private SensorManager sm = null;
	// 가속도 센서
	private Sensor accSensor = null;
	// MAGNETIC FIELD 센서
	private Sensor magSensor = null;
	// 방향 센서
	private Sensor oriSensor = null;
	// GYROSCOPE 센서
	private Sensor gyroSensor = null;
	// LIGHT 센서
	private Sensor lightSensor = null;
	// PROXIMITY 센서
	private Sensor proSensor = null;
			
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.sensor);
	    
	    

		int sensorSize = 0;
		
		//textView setting
		TextView tv1 = (TextView)findViewById(R.id.sensor_textView1);
		TextView tv2 = (TextView)findViewById(R.id.sensor_textView2);
		
		// SensorManager 인스턴스를 가져옴
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		
		List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);
		sensorSize = sensorList.size();
		tv1.setText("발견된 센서의 수 = "+sensorSize+"개");
		
		String sensorListMsg = "";
		for (int i = 0; i < sensorSize; i++) {
			sensorListMsg += "센서"+(i+1)+" = "+sensorList.get(i).getName()+"\n";
		}
		tv2.setText(sensorListMsg);
		
		accSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		magSensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		oriSensor = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		gyroSensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		lightSensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
		proSensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		
		
		
	}
	
	@Override  
	public void onResume() {  
	    super.onResume();  
	    // 가속도 센서 리스너 오브젝트를 등록  
	    sm.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);  
	    // 방향 센서 리스너 오브젝트를 등록  
	    sm.registerListener(this, magSensor, SensorManager.SENSOR_DELAY_NORMAL);  
	    sm.registerListener(this, oriSensor, SensorManager.SENSOR_DELAY_NORMAL);  
	    sm.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);  
	    sm.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);  
	    sm.registerListener(this, proSensor, SensorManager.SENSOR_DELAY_NORMAL);  
	}  
	  
	@Override  
	public void onPause() {  
	    super.onPause();  
	    // 센서에서 이벤트 리스너 분리  
	    sm.unregisterListener(this);  
	    sm.unregisterListener(this);  
	    sm.unregisterListener(this);  
	    sm.unregisterListener(this);  
	    sm.unregisterListener(this);  
	    sm.unregisterListener(this);  
	} 
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		String textViewMsg = "";
		TextView accelerometer_textView = (TextView)findViewById(R.id.accelerometer_textView);
		TextView magnetic_field_textView = (TextView)findViewById(R.id.magnetic_field_textView);
		TextView orientation_textView = (TextView)findViewById(R.id.orientation_textView);
		TextView gyroscope_textView = (TextView)findViewById(R.id.gyroscope_textView);
		TextView light_textView = (TextView)findViewById(R.id.light_textView);
		TextView proximity_textView = (TextView)findViewById(R.id.proximity_textView);
		
		
		switch (event.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			accelerometer_textView.setText(makeMsg(event.values));
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			magnetic_field_textView.setText(makeMsg(event.values));
			break;
		case Sensor.TYPE_ORIENTATION:
			String msg = "";
			for (int i = 0; i < 3; i++) {
				String priFix = i==0?"azimuth":i==1?"pitch":"roll";
				msg +=  priFix+" : " + event.values[i]+"\n";
			}
			orientation_textView.setText(msg);
			break;
		case Sensor.TYPE_GYROSCOPE:
			gyroscope_textView.setText(makeMsg(event.values));
			break;
		case Sensor.TYPE_LIGHT:
			light_textView.setText(String.valueOf(event.values[0]));
			break;
		case Sensor.TYPE_PROXIMITY:
			if(event.values[0] != 0)
				proximity_textView.setText("떨어짐");
	        else
	        	proximity_textView.setText("근접");
			break;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private String makeMsg(float[] val){
		String msg="";
		for (int i = 0; i < 3; i++) {
			String priFix = i==0?"X":i==1?"Y":"Z";
			msg +=  priFix+" : " + val[i]+"\n";
		}
		
		return msg;
	}
}
