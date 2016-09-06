package com.sample.naverapi;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private TextView textView;
	private BackPressCloseHandler backPressCloseHandler;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serch);        
        backPressCloseHandler = new BackPressCloseHandler(this);
        
        //키보드 강제로 띄우기
  		new Handler().postDelayed(new Runnable() {
  		      public void run() {
  		      	InputMethodManager mImm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
  		          mImm.showSoftInput(textView, InputMethodManager.SHOW_IMPLICIT);
  		      }
  		  }, 100);
  		
  		
        textView = (TextView)findViewById(R.id.EditText01);       
        Button searchBtn = (Button)findViewById(R.id.searchBtn);
        Button searchBtnx10 = (Button)findViewById(R.id.searchBtnx10);
        Button searchBtnx20 = (Button)findViewById(R.id.searchBtnx20);
        Button searchBtnx40 = (Button)findViewById(R.id.searchBtnx40);
        
        
    	searchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				searchNaverNews(1, true);
			}
		});
        searchBtnx10.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				searchNaverNews(10, true);
			}
		});
        searchBtnx20.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				searchNaverNews(20, true);
			}
		});
        searchBtnx40.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				searchNaverNews(40, true);
			}
		});
        
        
    }

	@Override
	public void onBackPressed() {
		 backPressCloseHandler.onBackPressed();
	}
	
	private void searchNaverNews(int repeatCnt, Boolean isSearchBtn) {
		if(textView.getText().toString().isEmpty()) {
        	Toast.makeText(this, "검색어를 입력하세요!!", Toast.LENGTH_SHORT).show();
        }else {
        	for (int i = 0; i < repeatCnt; i++) {
        		Intent intent = new Intent (MainActivity.this,PriceListActivity.class);						
        		Bundle myData = new Bundle();				
        		myData.putString("key", textView.getText().toString());	           
        		intent.putExtras(myData);
        		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		startActivity(intent);
        	}
        }
	}
}