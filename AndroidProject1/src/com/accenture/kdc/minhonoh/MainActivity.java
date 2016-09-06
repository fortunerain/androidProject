package com.accenture.kdc.minhonoh;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;


public class MainActivity extends Activity {

    private static final String LOG_TAG = "activity lifecycle";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG, "onCreate");
    }
	
	//서브 메뉴로 이동
	public void goSubMenu(View v){
		Intent intent = new Intent(getApplicationContext(), SubActivity.class);
		switch (v.getId()) {
		case R.id.screenTestMenuBtn:
			intent.putExtra("menuNum", 1);
			break;
			
		case R.id.soundTestMenuBtn:
			intent.putExtra("menuNum", 2);
			break;
		}
		startActivity(intent);
	}
	
	/**
	 * xml에서 onClick 속성으로 접근하는 방법
	 * @param v
	 */
	//개발자 정보 화면
	public void basicInfomation(View v){
		Intent intent = new Intent(getApplicationContext(), BasicInfomation.class);
		startActivity(intent);
	}
	
	//센서 테스트 화면
	public void sensorTest(View v){
		Intent intent = new Intent(getApplicationContext(), SensorTest.class);
		startActivity(intent);
	}
	
	//gps 테스트 화면
	public void gpsTest(View v){
		Intent intent = new Intent(getApplicationContext(), ExplainView.class);
		intent.putExtra("subMenuNum", "gpsTest");
		startActivity(intent);
	}
	
	//camera 테스트 화면
	public void cameraTest(View v){
		Intent intent = new Intent(getApplicationContext(), ExplainView.class);
		intent.putExtra("subMenuNum", "cameraTest");
		startActivity(intent);
	}
	
	@Override
	public void onBackPressed() {
		//super.onBackPressed();
		exitDialog();
	}
	
	private void exitDialog(){
	    AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
	    alt_bld.setMessage(this.getTitle()+" 프로그램(App)을 종료하시겠습니까?").setCancelable(
	        false).setPositiveButton("Yes",
	        new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int id) {
	        	moveTaskToBack(true); 
                finish();
	        }
	        }).setNegativeButton("No",
	        new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int id) {
	            dialog.cancel();
	        }
	        });
	    AlertDialog alert = alt_bld.create();
	    alert.setTitle(this.getTitle()+ " 프로그램 종료");
	    // Icon for AlertDialog
	    //alert.setIcon(R.drawable.icon);
	    alert.show();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		clearApplicationCache(null);
		android.os.Process.killProcess(android.os.Process.myPid() );
	}

	
	@SuppressWarnings("deprecation")
	public void clearApplicationCache(java.io.File dir){  //종료시 모든 캐쉬 삭제 
        if(dir==null) dir = getCacheDir(); 
        if(dir==null) return; 
        java.io.File[] children = dir.listFiles(); 
        try{ 
            CookieManager cookieManager = CookieManager.getInstance(); 
            cookieManager.removeSessionCookie(); 
            
            for(int i=0;i<children.length;i++) 
                if(children[i].isDirectory()) 
                    clearApplicationCache(children[i]); 
                else children[i].delete(); 
        } 
        catch(Exception e){} 
    }
}
