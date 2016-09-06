package com.sample.naverapi;

import java.util.ArrayList;

import com.sample.naverapi.medel.XmlData;

public class NaverParser {	
	
	public ArrayList<XmlData> GetXmlData(String searchTxt,int count) {
		HttpClientCustomThread thread = new HttpClientCustomThread(searchTxt, count);
		
		thread.start();

		try {
		    thread.join();
		} catch(Exception e) {
		    e.printStackTrace();
		}
		ArrayList<XmlData> m_xmlData = thread.getResult();
		return m_xmlData;
	}
	
}
