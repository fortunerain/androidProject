package com.sample.naverapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SerchActivity extends Activity {
	
	TextView textView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serch);        
        
        textView = (TextView)findViewById(R.id.EditText01);       
        Button btn3 = (Button)findViewById(R.id.searchBtn);
        
        btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Intent intent = new Intent (SerchActivity.this,MainActivity.class);						
				Bundle myData = new Bundle();				
	            myData.putString("key", textView.getText().toString());	           
	            intent.putExtras(myData);							
				startActivity(intent);
						
			}
		});
        
        
    }
}