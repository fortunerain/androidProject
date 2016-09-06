package com.example.bookmarkmoa;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.Browser.BookmarkColumns;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.bookmarkmoa.model.BookMarkModel;

public class PlaceholderFragment extends Fragment {
	private ListView listView;
	private List<BookMarkModel> list;
	private ListViewAdapter mAdapter;
	private WebView webView;
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	public ListViewAdapter getmAdapter() {
		return mAdapter;
	}

	public void setmAdapter(ListViewAdapter mAdapter) {
		this.mAdapter = mAdapter;
	}

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static PlaceholderFragment newInstance(int sectionNumber) {
		PlaceholderFragment fragment = new PlaceholderFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public PlaceholderFragment() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		int i = getArguments().getInt(ARG_SECTION_NUMBER);
		WebView webView = (WebView) rootView.findViewById(R.id.webView1);
		listView = (ListView) rootView.findViewById(android.R.id.list);
		
		// list view setting
		switch (i) {
		case 1:
			webView.setVisibility(View.INVISIBLE);
			listView.setVisibility(View.VISIBLE);
			String whereStr = "bookmark = 1";
			list = getInternetBookmarks(this.getActivity().getApplicationContext(), whereStr);
			mAdapter = new ListViewAdapter(this.getActivity().getApplicationContext(), R.layout.adapter_listview, list, this.getActivity().getLayoutInflater() );
			listView.setAdapter(mAdapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					String urlString = list.get(position).getUrl();
					Intent k = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
	                startActivity(k);
				}
			});
			break;
		case 2:
			webView.setVisibility(View.VISIBLE);
			listView.setVisibility(View.INVISIBLE);
			//getChromeBookmarks(webView);
			break;
		case 3:
			webView.setVisibility(View.VISIBLE);
			listView.setVisibility(View.INVISIBLE);
			getNaverBookmarks(webView);
			break;
		}
		
		
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
	
	

	public List<BookMarkModel> getInternetBookmarks(Context context, String whereStr) {
		List<BookMarkModel> result = new ArrayList<BookMarkModel>();
		String[] projection = { Browser.BookmarkColumns._ID,
				Browser.BookmarkColumns.URL, Browser.BookmarkColumns.TITLE,
				Browser.BookmarkColumns.VISITS, Browser.BookmarkColumns.FAVICON, Browser.BookmarkColumns.BOOKMARK };

		//bookmark = ? ���·� �־, selectionArgs ���� new String[]{"1"} �������� �Ķ���͸� �ִµ�, 
		//������ ���� �ʾ� �ٷ� selection�� bookmark = 1�� ��.
		CursorLoader cursorLoader = new CursorLoader(context, 
				Browser.BOOKMARKS_URI,				//���� ���� ��� URI
				projection, 						//���� ���� �÷� �̸�
				whereStr, 							//where ������. bookmark = 1�̸� �ϸ�ũ, bookmark = 0�̸� ���ͳ� �����丮��.
				null,								//where �������� ��.
				BookmarkColumns.VISITS+" desc"		//order by
				);
		
		Cursor cursor = null;
		cursorLoader.startLoading();
		cursor = cursorLoader.loadInBackground();
		cursor.moveToFirst();
		//if (cursor.moveToFirst()) {
			int url = cursor.getColumnIndex(BookmarkColumns.URL);
			int title = cursor.getColumnIndex(BookmarkColumns.TITLE);
			int visits = cursor.getColumnIndex(BookmarkColumns.VISITS);
			int favicon = cursor.getColumnIndex(BookmarkColumns.FAVICON);
			
			//�̹��� ũ�⸦ 1/4�� ���δ�.
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 4;
			
			while (!cursor.isAfterLast()) {
				Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher, options);
				BookMarkModel bookMarkModel = new BookMarkModel();
				bookMarkModel.setUrl(cursor.getString(url));
				bookMarkModel.setTitle(cursor.getString(title));
				bookMarkModel.setVisits(cursor.getString(visits));
				
				// �̹����� ������ ȭ�鿡 ǥ��(�̹��� ���� ��� ����)
				byte[] b = cursor.getBlob(favicon);
				if(b != null) {
					ByteArrayInputStream inputStream = new ByteArrayInputStream(b);
					bmp = BitmapFactory.decodeStream(inputStream);
				}
				Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, 30, 30, true);
				bookMarkModel.setFavicon(resizedbitmap);
				result.add(bookMarkModel);
				cursor.moveToNext();
			}
		//}
		
		return result;
	}
	
	private void getNaverBookmarks(WebView webView) {
		
		//naver �ϸ�ũapi�� �����Ƿ� �����Ұ���
//		List<BookMarkModel> result = new ArrayList<BookMarkModel>();
//		BookMarkModel bookMarkModel = new BookMarkModel();
//		bookMarkModel.setUrl("123");
//		bookMarkModel.setTitle("123");
//		bookMarkModel.setVisits("123");
//		
//		
//		result.add(bookMarkModel);
		
		String urlString = "http://m.bookmark.naver.com";
		setInAppWebView(webView, urlString);
//		���ͳ� â���� ���� ���
//		Intent k = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
//      startActivity(k);
        
	}
	
	private void getChromeBookmarks(WebView webView) {
		
//		List<BookMarkModel> result = new ArrayList<BookMarkModel>();
//		BookMarkModel bookMarkModel = new BookMarkModel();
//		bookMarkModel.setUrl("asdf");
//		bookMarkModel.setTitle("asdf");
//		bookMarkModel.setVisits("asdf");
//		
//		result.add(bookMarkModel);
		
		String urlString = "chrome://bookmarks";
		setInAppWebView(webView, urlString);
		
	}
	
	private void setInAppWebView(WebView webView, String urlString) {
		webView.setWebChromeClient(new WebChromeClient());
		webView.setWebViewClient(new WebViewClient());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		//�̰� ����� �ش� view�ȿ��� ���䰡 ����
		webView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
				// TODO backŰ ������ �� ���� ����ǹǷ� ���߿� ó���ؾ���
				return super.shouldOverrideKeyEvent(view, event);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//view.loadUrl(url);
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
		
		
		webView.loadUrl(urlString);
	}
	
}
