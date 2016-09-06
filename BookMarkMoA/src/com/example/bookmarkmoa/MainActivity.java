package com.example.bookmarkmoa;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bookmarkmoa.model.BookMarkModel;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks, OnQueryTextListener {
	private final static String TAG = "MainActivity";
	private PlaceholderFragment fragment;
	
	
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
	}

	
	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		// 누를때 마다 새로운 fragment 객체를 가져와서 셋팅해준다.
		fragment = PlaceholderFragment.newInstance(position + 1);
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container, fragment).commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setTitle(mTitle);
		actionBar.setIcon(R.drawable.ic_launcher);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			
			MenuItem searchItem = menu.findItem(R.id.action_search);
		    SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
		    // Configure the search info and add any event listeners
		    searchView.setQueryHint("북마크 제목을 입력하세요.");
		    searchView.setOnQueryTextListener(this);
		    
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_search) {
			//do nothing
			return true;
		} else if(id == R.id.action_refresh) {
			//새로고침
			searchInternetBookMark("bookmark = 1");
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public boolean onQueryTextSubmit(String query) {
		//search에서 글자 입력후 키보드의 검색 버튼 눌렀을 때
		String whereStr = "bookmark = 1 and title like '%"+query+"%'";
		searchInternetBookMark(whereStr);
		return false;
	}


	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	 * 핸드폰의 기본 북마크 가져오기
	 */
	public void searchInternetBookMark(String whereStr) {
		List<BookMarkModel> list = fragment.getInternetBookmarks(this, whereStr);
		ListViewAdapter adapter = fragment.getmAdapter();
		
		if(list.isEmpty()) {
			Toast.makeText(this, "No result!!", Toast.LENGTH_SHORT).show();
		}else {
			adapter.clear();
			for (int i = 0; i < list.size(); i++) {
				adapter.add(list.get(i));
			}
			adapter.notifyDataSetChanged();
		}
		
	}


}
