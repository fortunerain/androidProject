package com.sample.naverapi;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.sample.naverapi.medel.XmlData;

import android.util.Log;

public class HttpClientCustomThread extends Thread {
	private String TAG = "CustomThread";
	private String key1 = "578350292d7fe5fc0be6940112c7653d";
	private String searchTxt = "";
	private int count = 0;
	private ArrayList<XmlData> m_xmlData;
	/**
	 * HttpClientCustomThread 생성자 url : API url dispatch : get방식의 경우 null,
	 * post방식의 경우 not null
	 * 
	 * @param url
	 * @param requestModel
	 */
	public HttpClientCustomThread(String searchTxt, int count) {
		this.searchTxt = searchTxt;
		this.count = count;
	}

	public ArrayList<XmlData> getResult() {
		return m_xmlData;
	}

	@Override
	public void run() {

		m_xmlData = new ArrayList<XmlData>();

		XmlData xmlData = null;

		String m_searchTxt = "";

		try {
			m_searchTxt = URLEncoder.encode(searchTxt, "UTF8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// XML 결과물 파싱하기
		try {

			URL text = new URL("http://openapi.naver.com/search?key=" + key1
					+ "&query=" + m_searchTxt + "&display=" + count
					+ "&start=1&target=news");

			XmlPullParserFactory parserCreator = XmlPullParserFactory
					.newInstance();
			XmlPullParser parser = parserCreator.newPullParser();

			parser.setInput(text.openStream(), null);

			Log.i("NET", "Parsing...");

			// status.setText("Parsing....");

			int parseEvent = parser.getEventType();
			while (parseEvent != XmlPullParser.END_DOCUMENT) {

				switch (parseEvent) {
				case XmlPullParser.START_TAG:
					String tag = parser.getName();

					if (tag.compareTo("title") == 0) {
						xmlData = new XmlData();
						// 자료 구조 1개 생성

						String titleSrc = parser.nextText();
						xmlData.setTitle(titleSrc);
					}

					if (tag.compareTo("originallink") == 0) {
						String originallink = parser.nextText();
						xmlData.setOriginallink(originallink);
					}

					if (tag.compareTo("link") == 0) {
						String link = parser.nextText();
						xmlData.setLink(link);
					}

					if (tag.compareTo("description") == 0) {
						String description = parser.nextText();
						xmlData.setDescription(description);
					}

					if (tag.compareTo("pubDate") == 0) {
						String pubDate = parser.nextText();
						xmlData.setPubDate(pubDate);
						m_xmlData.add(xmlData);
						// 자료 구조 1개 넣기

					}
					break;
				}
				parseEvent = parser.next();

				// xmlData = null;
				// While next 다음으로 넘긴다.
			}
			Log.i("NET", "End...");

		} catch (Exception e) {
			// TODO: handle exception
			Log.i("NET", "Parsing Failed!");
		}
	}

}
