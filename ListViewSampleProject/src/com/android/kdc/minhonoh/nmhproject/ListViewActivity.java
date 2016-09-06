package com.android.kdc.minhonoh.nmhproject;

import java.util.List;

import model.ArtworkModel;
import util.JsonUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class ListViewActivity extends Activity {
	private List<ArtworkModel> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		
		String urlString = "http://sng-dev.elasticbeanstalk.com/artwork/list";
		list = JsonUtil.getArtworkArrayListFromJson(urlString);
		
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStringArray);
		ListViewAdapter adapter1 = new ListViewAdapter(this.getApplicationContext(), R.layout.adapter_listview, list, getLayoutInflater() );
		
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter1);

		
//		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//		StrictMode.setThreadPolicy(policy);
		
		/*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		      @Override
		      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		          //intent로 BlankActivity에 전달함.
		          Intent in = new Intent(getApplicationContext(),BlankActivity.class);
		          //in.putExtra("color", list.get(position).getValue());
		          //startActivityForResult(in, 100);
		
		      }
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view, menu);
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
	
	public void goSearchActivity(View v) {
		Intent in = new Intent(getApplicationContext(),SearchActivity.class);
        //in.putExtra("list", list);
        startActivity(in);
	}
}
