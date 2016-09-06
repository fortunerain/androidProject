package com.android.kdc.minhonoh.nmhproject;

import java.util.ArrayList;
import java.util.List;

import model.ArtworkModel;
import util.JsonUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class SearchActivity extends Activity {
	private EditText et;
	private List<ArtworkModel> list;
	private ListViewAdapter adapter1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		list = new ArrayList<ArtworkModel>();
		adapter1 = new ListViewAdapter(this, R.layout.adapter_listview, list, getLayoutInflater() );
		
		ListView listview = (ListView)findViewById(R.id.search_listView);
		listview.setAdapter(adapter1);
		
		
		et = (EditText)findViewById(R.id.editText1);
		et.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				String urlString = "http://sng-dev.elasticbeanstalk.com/artwork/search?keyword="+v.getText().toString();
				list = JsonUtil.getArtworkArrayListFromJson(urlString);
				adapter1.clear();
				for (int i = 0; i < list.size(); i++) {
					adapter1.add(list.get(i));
				}
				adapter1.notifyDataSetChanged();
				return false;
			}
		});
		/*et.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				String text = s.toString();
				if(text.length() > 0) {
					adapter1.clear();
					adapter1.add(object);
				}
				
			}
		});*/
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void cancelClick(View v) {
		et.setText("");
	}
}
