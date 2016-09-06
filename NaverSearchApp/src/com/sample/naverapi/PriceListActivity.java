package com.sample.naverapi;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.sample.naverapi.medel.XmlData;

public class PriceListActivity extends Activity {
	public static final String TAG = "PriceListActivity";
	private ListView myListview;
	private String Data;
	private int count = 6;
	//더보기 기능으로 1개가 지워져 보인다.
	
	CustomListAdapter adapter;
	
	NaverParser naverPaser;
	
	ArrayList<XmlData> m_xmlData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		
		myListview = (ListView) findViewById(R.id.myListview);
	
		naverPaser = new NaverParser();
		//네이버 인증 키 
		
		Intent intent = getIntent();
		Bundle myBundle = intent.getExtras();
		Data = myBundle.getString("key");
		//검색값 가져오기

		getNewList(Data,count);
		//처음 5개 화면

		myListview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub

				int Last = adapter.getCount();
				//adapter 전체 갯수 카운터

				Log.i("NET", Last + "Parsing...");

				if ((Last - 1) == position) {
					count = count + 5;					
					getNewList(Data,count);							
					//더보기를 클릭했을 때, 카운터를 5 증가시키고 GetNewList()
					
				} 
			}
		});
	}

	public void getNewList(String searchTxt , int NumberOfList) {		
		m_xmlData = naverPaser.GetXmlData(searchTxt, count);		
		adapter = new CustomListAdapter(this,R.layout.listitem, m_xmlData, getLayoutInflater());
		myListview.setAdapter(adapter);		
	}
	
//	public String[] StringArrayData(int arg2) {
//		// TODO Auto-generated method stub
//				
//		XmlData xmlData = m_xmlData.get(arg2);
//		
//		String[] StringArrayData = new String[5];
//		
//		StringArrayData[0]=xmlData.getTitle();
//		StringArrayData[1]=xmlData.getOriginallink();
//		StringArrayData[2]=xmlData.getLink();
//		StringArrayData[3]=xmlData.getDescription();
//		StringArrayData[4]=xmlData.getPubDate();
//		
//		return StringArrayData ;
//	}	
	
}
