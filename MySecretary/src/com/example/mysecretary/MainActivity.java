package com.example.mysecretary;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mysecretary.dragdrop.AppListAdapter;
import com.example.mysecretary.dragdrop.DragDropListView;
import com.example.mysecretary.dragdrop.DragDropListView.OnDropListener;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		List<String> myStringArray1 = new ArrayList<String>();
		List<String> myStringArray2 = new ArrayList<String>();
		//빈 껍데기 arraylist 20개 생성
		for (int i=0; i<20; i++){
			myStringArray1.add(i+"test");
			myStringArray2.add(i+"test2");
		}
	    AppListAdapter adapter1 = new AppListAdapter(this);
	    AppListAdapter adapter2 = new AppListAdapter(this);
	    for (String s : myStringArray1)
	    	adapter1.addItem(1 + "", R.drawable.ic_launcher, s);
	    
	    for (String s : myStringArray2)
	    	adapter2.addItem(2 + "", R.drawable.ic_launcher, s);
       
	    DragDropListView listView1 = (DragDropListView) findViewById(R.id.listView1);
	    DragDropListView listView2 = (DragDropListView) findViewById(R.id.listView2);
		listView1.setAdapter(adapter1);
		listView2.setAdapter(adapter2);
		
		listView1.setOnDropListener(onDropListener);
		listView2.setOnDropListener(onDropListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	// 아이템 이동 후 손을 뗐을 때
	private OnDropListener onDropListener = new OnDropListener()
	{
		
		@Override
		public void drop(int from, int to)
		{
			Toast.makeText(getApplicationContext(), "drop", Toast.LENGTH_SHORT).show();
//			AppListItem item = _adapter.removeItemAt(from);
//			_adapter.addItemAt(to, item);
//			_adapter.notifyDataSetChanged();
		}
	};
}
