package com.accenture.kdc.minhonoh;

import java.util.ArrayList;
import java.util.List;

import com.accenture.kdc.minhonoh.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ScreenAndDeadPixels extends Activity {
	private List<Integer> colorList = null;
	private int count = 0;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.screen_and_dead_pixels);
	    
	    colorList = new ArrayList<Integer>();
	    colorList.add(Color.GREEN);
	    colorList.add(Color.RED);
	    colorList.add(Color.BLUE);
	    colorList.add(Color.WHITE);
	    colorList.add(Color.YELLOW);
	    colorList.add(Color.GRAY);
	    
	    
	    View v = (View)findViewById(R.id.screen1);
	    //최초 배경색 설정
	    v.setBackgroundColor(colorList.get(count));
	    v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(count >= colorList.size()-1){
					count = 0;
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(intent);
				}else{
					if(count >= colorList.size()-1){
						count = colorList.size()-1;
					}else{
						count++;
					}
					v.setBackgroundColor(colorList.get(count));
				}
			}
		});
	    
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
