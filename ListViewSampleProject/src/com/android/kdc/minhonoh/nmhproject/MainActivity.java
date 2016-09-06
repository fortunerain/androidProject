package com.android.kdc.minhonoh.nmhproject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.ColorObject;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private List<ColorObject> list;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.mainLayout);
//        List<String> myStringArray = new ArrayList<String>();
//        //빈 껍데기 arraylist 20개 생성
//        for (int i=0; i<20; i++){
//            myStringArray.add("");
//        }
        list = readJsonFile("colors.json");
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStringArray);
        CommentListAdapter adapter1 = new CommentListAdapter(this.getApplicationContext(), R.layout.adapter_viewholder, list, getLayoutInflater() );


        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter1);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //intent로 BlankActivity에 전달함.
                Intent in = new Intent(getApplicationContext(),BlankActivity.class);
                in.putExtra("color", list.get(position).getValue());
                startActivityForResult(in, 100);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 100) {
            if (data != null) {
                relativeLayout.setBackgroundColor(Color.parseColor(data.getStringExtra("value")));
            }
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public List<ColorObject> readJsonFile(java.lang.String file_name){
        AssetManager am = this.getApplication().getAssets();
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

    public List<ColorObject> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        reader.setLenient(true);
        return readMessagesArray(reader);
    }
    public List<ColorObject> readMessagesArray(JsonReader reader) throws IOException {
        List<ColorObject> messages = new ArrayList<ColorObject>();

        reader.beginArray();
        while (reader.hasNext()) {
            messages.add(readColor(reader));
        }
        reader.endArray();
        return messages;
    }

    public ColorObject readColor(JsonReader reader) throws IOException {
        java.lang.String color = null;
        java.lang.String value = null;

        reader.beginObject();
        while (reader.hasNext()) {
            java.lang.String name = reader.nextName();
            if (name.equals("color")) {
                color = reader.nextString();
            } else if (name.equals("value")) {
                value = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new ColorObject(color, value);
    }

}
