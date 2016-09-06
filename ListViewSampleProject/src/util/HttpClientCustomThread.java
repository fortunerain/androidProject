package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpClientCustomThread extends Thread {
	private String TAG = "CustomThread";
	private static StringBuffer output = new StringBuffer();
	private String urlString = "";
	public HttpClientCustomThread(String url) {
	    output = new StringBuffer();
	    this.urlString = url;
	}
	
	public String getResult() { 
		return output.toString();
	}
	
	@Override
	public void run() {
	    try{
	           HttpClient httpclient = new DefaultHttpClient();

	           HttpGet request = new HttpGet();
	           URI website = new URI(urlString);
	           request.setURI(website);
	           HttpResponse response = httpclient.execute(request);
	           InputStream in = response.getEntity().getContent();
	           BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	           
               String line = null;

               while(true) {
                   line = reader.readLine();
                   if(line == null) {
                       break;
                   }

                   output.append(line + "\n");
               }

               reader.close();
	       }catch(Exception e){
	           Log.e(TAG, "Error in http connection "+e.toString());
	       }
	}
		
}
