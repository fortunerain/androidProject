package com.accenture.mplt.comm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Build;
import android.util.Log;

import com.accenture.mplt.model.RequestModel;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

public class HttpClientCustomThread extends Thread {
	private String TAG = "CustomThread";
	private static HttpTransport httpTransport;
	private static final int GINGERBREAD = 9;
	private String API_URL;
	private RequestModel requestModel;
	private String jsonStr = "";	
	/**
	 * HttpClientCustomThread 생성자
	 * url : API url
	 * dispatch : get방식의 경우 null, post방식의 경우 not null
	 * @param url
	 * @param requestModel
	 */
	public HttpClientCustomThread(String url, RequestModel requestModel) {
		this.API_URL = url;
		this.requestModel = requestModel;
	}
	
	public String getResult() { 
		return jsonStr.toString();
	}
	
	@Override
	public void run() {
	    try{
			newCompatibleTransport();
			HttpRequest request;
	    	HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
	    	if(requestModel == null){
	    		request = requestFactory.buildGetRequest(new GenericUrl(API_URL));
	    	} else {
	    		Map<String, Object> map = new HashMap<String, Object>();
	    		map = getMap(requestModel);
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType("application/json");
	    		request = requestFactory.buildPostRequest(new GenericUrl(API_URL), new UrlEncodedContent(map));
	    		request.setHeaders(headers);
	    	}
	        
	        
	        HttpResponse response = request.execute();
	        
	        jsonStr = httpResponseSet(response);
	        
	       }catch(Exception e){
	           Log.e(TAG, "Error in http connection "+e.toString());
	       }
	}
	
	public String httpResponseSet(HttpResponse response) throws IOException{

        String returnStr = null;
        if(response.getStatusCode() == 200) {
            InputStreamReader inputStreamReader = new InputStreamReader(response.getContent());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer("");
            String oneLine = null;

            while ((oneLine=bufferedReader.readLine())!=null) {
                stringBuffer.append(oneLine);
            }
            returnStr =stringBuffer.toString();
        }
        return returnStr;
    }
	
	public static HttpTransport newCompatibleTransport() {
        return httpTransport = isGingerbreadOrHigher() ? new NetHttpTransport() : new ApacheHttpTransport();
    }

    public static boolean isGingerbreadOrHigher() {
        return Build.VERSION.SDK_INT >= GINGERBREAD;
    }
    public static Map<String, Object> getMap(Object o) {
	    Map<String, Object> result = new HashMap<String, Object>();
	    List<Field> declaredFields = getAllFields(o.getClass());
	    
	    for(Field field : declaredFields) {
			
	    	field.setAccessible(true);
	    	try {
				result.put(field.getName(), field.get(o));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result.put(field.getName(), null);
			}
			
		}
	    return result;
	}
    
	private static List<Field> getAllFields(Class clazz) {
	    List<Field> fields = new ArrayList<Field>();

	    fields.addAll(Arrays.asList( clazz.getDeclaredFields() ));

	    Class superClazz = clazz.getSuperclass();
	    if(superClazz != null){
	        fields.addAll( getAllFields(superClazz) );
	    }

	    return fields;
	}
}
