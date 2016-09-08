package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.ArtworkModel;
import model.ColorObject;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.JsonReader;

public class JsonUtil {
	public static List<ColorObject> readJsonFile(Context context, String file_name){
        AssetManager am = context.getApplicationContext().getAssets();
        InputStream is = null;
        List<ColorObject> list = null;
        try {
            is = am.open(file_name);
            list = readJsonStream(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null){
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        am = null;
        return list;
    }

    public static List<ColorObject> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        reader.setLenient(true);
        return readColorArray(reader);
    }
    public static List<ColorObject> readColorArray(JsonReader reader) throws IOException {
        List<ColorObject> messages = new ArrayList<ColorObject>();

        reader.beginArray();
        while (reader.hasNext()) {
            messages.add(readColor(reader));
        }
        reader.endArray();
        return messages;
    }
    
    
    public static ColorObject readColor(JsonReader reader) throws IOException {
        String key = null;
        String value = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("color")) {
                key = reader.nextString();
            } else if (name.equals("value")) {
                value = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new ColorObject(key, value);
    }
    
    public static List<ArtworkModel> getArtworkArrayListFromJson(String urlString){
    	List<ArtworkModel> list = new ArrayList<ArtworkModel>();
    	HttpClientCustomThread thread = new HttpClientCustomThread(urlString);
		thread.start();

		try {
		    thread.join();
		} catch(Exception e) {
		    e.printStackTrace();
		}

		String result = thread.getResult();
		try {
			JSONObject jsonObj = new JSONObject(result);
			JSONArray ja = new JSONArray(jsonObj.get("response").toString());
			
			
            int n = ja.length();
            for (int i = 0; i < n; i++) {
                JSONObject jo = ja.getJSONObject(i);

                String title = jo.getString("title");
                String artistName = jo.getString("artistName");
                String likesCnt = jo.getString("likesCnt");

                ArtworkModel a = new ArtworkModel(title, artistName, likesCnt);
                list.add(a);
            }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
    	
    }
}
