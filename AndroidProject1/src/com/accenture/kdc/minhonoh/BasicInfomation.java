package com.accenture.kdc.minhonoh;

import java.util.ArrayList;
import java.util.List;

import com.accenture.kdc.minhonoh.R;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BasicInfomation extends Activity {
	private ArrayAdapter<String> arrayAdaper;
	private ListView listView;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.basic_infomation);
	    String id = Build.ID;
	    String version = Build.VERSION.RELEASE;
	    String brand = Build.BRAND;
	    String model = Build.MODEL;
	    String display = Build.DISPLAY;
	    
	    
	    List<String> list = new ArrayList<String>();
	    list.add("id : "+id);
	    list.add("version : "+version);
	    list.add("brand : "+brand);
	    list.add("model : "+model);
	    list.add("display : "+display);
	    
	    
	    arrayAdaper = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
	    listView = (ListView)findViewById(R.id.listView1);
	    listView.setAdapter(arrayAdaper);
	    listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	    
	    
	}


}
